/**
 * Project Soul Reapers
 * Copyright (c) 2014 Chengwu Huang (dxcloud) <chengwhuang@gmail.com>
 *
 * This file is part of 'Soul Reapers'.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.soulreapers.object;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;

/**
 * @since 2014.10.30
 * @version 0.1 (alpha)
 * @author dxcloud
 *
 */
public class HealthBar {
	private static final int BAR_WIDTH = 200;
	private static final int BAR_HEIGHT = 20;
	private static final int BAR_X = 20;
	private static final int BAR_Y = 400;
	private Rectangle mCurrentBar = new Rectangle(BAR_X, BAR_Y,
			BAR_WIDTH, BAR_HEIGHT,
			ResourceManager.getInstance().getVertexBufferObjectManager());
	private Rectangle mMaxBar = new Rectangle(BAR_X, BAR_Y,
			BAR_WIDTH, BAR_HEIGHT,
			ResourceManager.getInstance().getVertexBufferObjectManager());
	private Text mHealthText;
	private int mHealth, mMaxHealth;
	private static final Color COLOR_GREY = new Color(0.5F, 0.5F, 0.5F);
	private static final int HEALTH_TEXT_LENGTH = 10;
	private static final int FONT_ID = ResourceManager.FONT_STATS_ID;

	public HealthBar(int maxHealth) {
		mMaxHealth = maxHealth;
		mHealth = maxHealth;
		mMaxBar.setColor(COLOR_GREY);
		mCurrentBar.setColor(Color.RED);

		mHealthText = new Text(20, 380,
				ResourceManager.getInstance().getFont(FONT_ID),
				"HP\n", HEALTH_TEXT_LENGTH,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mHealthText.setText("HP\n" + mHealth);
	}

	public int getHealth() {
		return mHealth;
	}

	public int getMaxHealth() {
		return mMaxHealth;
	}

	public void setHealth(int health) {
		mHealth = health;
		updateWidth();
	}

	public void setMaxHealth(int maxHealth) {
		mMaxHealth = maxHealth;
		updateWidth();
	}

	public void increase(int amount) {
		mHealth += amount;
		if (mHealth > mMaxHealth) {
			mHealth = mMaxHealth;
		}
		updateWidth();
	}

	public void decrease(int amount) {
		mHealth -= amount;
		if (mHealth < 0) {
			mHealth = 0;
		}
		updateWidth();
	}

	public void attachToScene(Scene scene) {
		scene.attachChild(mMaxBar);
		scene.attachChild(mCurrentBar);
		scene.attachChild(mHealthText);
	}

	public void setVisible(boolean pVisible) {
		mHealthText.setVisible(pVisible);
		mCurrentBar.setVisible(pVisible);
		mMaxBar.setVisible(pVisible);
	}

	public void detachFromScene() {
		mHealthText.detachSelf();
		mCurrentBar.detachSelf();
		mMaxBar.detachSelf();
	}

	private void updateWidth() {
		mCurrentBar.setWidth((float) BAR_WIDTH * mHealth / mMaxHealth);
	}

	public void destroy() {
		mHealthText.dispose();
		mCurrentBar.dispose();
		mMaxBar.dispose();
	}
}

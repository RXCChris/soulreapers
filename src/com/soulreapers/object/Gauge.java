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

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.color.Color;

import com.soulreapers.core.ResourceManager;

/**
 * @since 2010.10.30
 * @version 0.1 (alpha)
 * @author dxcloud
 */
public class Gauge extends Entity {
	public static final int DEFAULT_GAUGE_WIDTH = 268;
	public static final int DEFAULT_GAUGE_HEIGHT = 12;
	public static final int DEFAULT_GAUGE_X = 20;
	public static final int DEFAULT_GAUGE_Y = 50;
	private Rectangle mMax;
	private Rectangle mCurrent;

	private static final Color COLOR_GREY = new Color(0.5F, 0.5F, 0.5F);

	public Gauge(int pX, int pY, int pWidth, int pHeight, Color pColor) {
		this(pX, pY, pWidth, pHeight);
		mCurrent.setColor(pColor);
	}

	public Gauge(int pX, int pY, int pWidth, int pHeight) {
		create(pX, pY, pWidth, pHeight);
	}

	public Gauge(float pX, float pY, float pWidth, float pHeight, Color pColor) {
		mMax = new Rectangle(pX, pY, pWidth, pHeight,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mCurrent = new Rectangle(pX, pY, pWidth, pHeight,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mMax.setColor(COLOR_GREY);
		mCurrent.setColor(pColor);
	}

	public Gauge() {
		this(DEFAULT_GAUGE_X, DEFAULT_GAUGE_Y,
				DEFAULT_GAUGE_WIDTH, DEFAULT_GAUGE_HEIGHT);
	}

	private void create(int pX, int pY, int pWidth, int pHeight) {
		mMax = new Rectangle(pX, pY, pWidth, pHeight,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mCurrent = new Rectangle(pX, pY, pWidth, pHeight,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mMax.setColor(COLOR_GREY);
		mCurrent.setColor(Color.RED);
	}

	public void setFilledColor(Color pColor) {
		mCurrent.setColor(pColor);
	}

	public void setEmptyColor(Color pColor) {
		mMax.setColor(pColor);
	}

	@Override
	public void onAttached() {
		this.attachChild(mMax);
		this.attachChild(mCurrent);
		super.onAttached();
	}


	@Override
	public void onDetached() {
		mMax.detachSelf();
		mCurrent.detachSelf();
		super.onDetached();
	}

	@Override
	public void dispose() {
		mMax.dispose();
		mCurrent.dispose();
		super.dispose();
	}

	/**
	 */
	public void update(final float pCurrent, final float pMax) {
		float ratio = pCurrent / pMax;
		if (ratio > 1.0F) {
			ratio = 1.0F;
		}
		mCurrent.setWidth(ratio * mMax.getWidth());
	}
}

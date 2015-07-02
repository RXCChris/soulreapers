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
package com.soulreapers.scene;

import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.scene.background.Background;
import org.andengine.util.color.Color;

import android.opengl.GLES20;

import com.soulreapers.core.FontManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.ui.UI_Label;
import com.soulreapers.ui.UI_Panel;

/**
 * This <code>SplashScene</code> consists in showing a fake studio name.
 *
 * @since  2014.10.29
 * @version 0.1 (alpha)
 * @author dxcloud
 */
public class SplashScene extends UI_Scene {
	/** Required duration to display the name of the fake studio */
	private static final float FADE_IN_DURATION = 5.0F;

	/** Name of the fake studio to display */
	private static final String STRING_DEVELOPER = "Fictive Studio";
	private static final String STRING_PRESENTS = "Presents";

	private UI_Panel<UI_Label> mPanelLabels = new UI_Panel<UI_Label>();

	/**
	 * @see com.soulreapers.scene.UI_Scene#onLoadResources()
	 */
	@Override
	public void onLoadResources() {
		FontManager.getInstance().loadFont(FontType.FONT_TITLE_HUGE);
		FontManager.getInstance().loadFont(FontType.FONT_TEXT_SMALL);
	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onCreate()
	 */
	@Override
	public void onCreate() {
		this.setBackground(new Background(Color.WHITE));

		final UI_Label labelDev = new UI_Label(STRING_DEVELOPER, FontType.FONT_TITLE_HUGE);
		labelDev.setAlpha(0);
		labelDev.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		labelDev.registerEntityModifier(new FadeInModifier(FADE_IN_DURATION));

		final UI_Label labelPresents = new UI_Label(STRING_PRESENTS, FontType.FONT_TEXT_SMALL);
		labelPresents.setAlpha(0);
		labelPresents.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		labelPresents.registerEntityModifier(new FadeInModifier(FADE_IN_DURATION));

		mPanelLabels.add(labelDev);
		mPanelLabels.add(labelPresents);

		final float x = (this.getWidth() - mPanelLabels.getWidth()) / 2;
		final float y = (this.getHeight() - mPanelLabels.getHeight()) / 2;
		mPanelLabels.setPosition(x, y);
		this.attachChild(mPanelLabels);
	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onDestroyResources()
	 */
	@Override
	public void onDestroyResources() {
		// Nothing to do
	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onDestroy()
	 */
	@Override
	public void onDestroy() {
		mPanelLabels.destroy();
		mPanelLabels = null;
	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onPause()
	 */
	@Override
	public void onPause() {
		// Nothing to do
	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onResume()
	 */
	@Override
	public void onResume() {
		// Nothing to do
	}

	@Override
	public String toString() {
		return "SceneSplash";
	}
}

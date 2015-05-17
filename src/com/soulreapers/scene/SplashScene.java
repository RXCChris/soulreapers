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
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import android.opengl.GLES20;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;

/**
 * This <code>SplashScene</code> consists in showing a fake studio name.
 *
 * @since  2014.10.29
 * @version 0.1 (alpha)
 * @author dxcloud
 */
public class SplashScene extends BaseScene {
	/** Required duration to display the name of the fake studio */
	private static final float FADE_IN_DURATION = 5.0F;
	private static final int FONT_ID = ResourceManager.FONT_TITLE_ID;
	private static final int STUDIO_ID = R.string.mg_studio;

	/** Name of the fake studio to display */
	private Text mTextStudio = new Text(0, 0,
			ResourceManager.getInstance().getFont(FONT_ID),
			ResourceManager.getInstance().getResourceString(STUDIO_ID),
			new TextOptions(HorizontalAlign.CENTER),
			ResourceManager.getInstance().getVertexBufferObjectManager());

	/**
	 * @see com.soulreapers.scene.BaseScene#onLoadResources()
	 */
	@Override
	public void onLoadResources() {
		// Nothing to do
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onCreate()
	 */
	@Override
	public void onCreate() {
		this.setBackground(new Background(Color.BLACK));

		mTextStudio.setAlpha(0.0f);
		mTextStudio.setPosition((GameConstants.CAMERA_WIDTH - mTextStudio.getWidth()) / 2,
				(GameConstants.CAMERA_HEIGHT - mTextStudio.getHeight()) / 2);
		mTextStudio.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		mTextStudio.registerEntityModifier(new FadeInModifier(FADE_IN_DURATION));

		this.attachChild(mTextStudio);
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onDestroyResources()
	 */
	@Override
	public void onDestroyResources() {
		// Nothing to do
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onDestroy()
	 */
	@Override
	public void onDestroy() {
		mTextStudio.clearEntityModifiers();
		mTextStudio.detachSelf();
		mTextStudio.dispose();
		this.detachSelf();
		this.dispose();
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onPause()
	 */
	@Override
	public void onPause() {
		// Nothing to do
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onResume()
	 */
	@Override
	public void onResume() {
		// Nothing to do
	}
}

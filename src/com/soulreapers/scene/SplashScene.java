package com.soulreapers.scene;

import org.andengine.entity.modifier.AlphaModifier;
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
 * This <code>SplashScene</code> consists in shown a name of a fake studio.
 *
 * @since  2014.10.29
 * @version 0.1 (alpha)
 * @author dxcloud
 */
public class SplashScene extends BaseScene {
	private static final float FADE_IN_DURATION = 5.0F;
	private Text mTextStudio;

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

		mTextStudio = new Text(0, 0,
				ResourceManager.getInstance().getFont(R.string.ft_03),
				ResourceManager.getInstance().getResourceString(R.string.mg_studio),
				new TextOptions(HorizontalAlign.CENTER),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mTextStudio.setScale(2.0f);
		mTextStudio.setPosition((GameConstants.CAMERA_WIDTH - mTextStudio.getWidth()) / 2,
				(GameConstants.CAMERA_HEIGHT - mTextStudio.getHeight()) / 2);
		mTextStudio.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		mTextStudio.registerEntityModifier(new AlphaModifier(FADE_IN_DURATION, 0.0f, 1.0f));
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

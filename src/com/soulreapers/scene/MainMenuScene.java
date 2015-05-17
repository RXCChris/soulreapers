package com.soulreapers.scene;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.item.GameDataDictionary;
import com.soulreapers.object.item.ItemBase;
import com.soulreapers.util.GameDataBase;
import com.soulreapers.util.SRButton;

/**
 * This class represents main title menu.
 * <p>
 * This menu contains a horizontally scrolling background, and also 2 buttons.
 * </p>
 * <p>
 * An Ester egg has been added to this menu. Try to find out. ^^
 * </p>
 *
 * @since 2014.10.29
 * @version 0.1 (alpha)
 * @author dxcloud
 */
public class MainMenuScene extends BaseScene implements IOnSceneTouchListener {
	private static final int MUSIC_ID              = R.string.ms_01;
	private static final int TEXTURE_BACKGROUND_ID = R.string.bg_04;
	private static final int TEXTURE_FOREGROUND_ID = R.string.bg_03;

	private static final int BUTTON_OFFSET_X  = 500;
	private static final int BUTTON_OFFSET_Y  = 300;
	private static final int BUTTON_PADDING_Y = 50;

//	private static final String STRING_PLAY      = "Commencer";
//	private static final String STRING_OPTIONS   = "Options";
//	private static final String STRING_EXTRAS    = "Extras";

	private Sprite mForegroundSprite;
	private Sprite mBackgroundSprite;

	private SRButton mButtonPlay;
	private SRButton mButtonOptions;
	private SRButton mButtonExtras;

	/**
	 * @see com.soulreapers.scene.BaseScene#onLoadResources()
	 */
	@Override
	public void onLoadResources() {
		ResourceManager.getInstance().loadTexture(TEXTURE_BACKGROUND_ID, 800, 800);
		ResourceManager.getInstance().loadTexture(TEXTURE_FOREGROUND_ID, 800, 480);
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onCreate()
	 */
	@Override
	public void onCreate() {
		AudioManager.getInstance().playMusic(MUSIC_ID, true, true);

		mBackgroundSprite = new Sprite(0, 0,
				ResourceManager.getInstance().getTextureRegion(TEXTURE_BACKGROUND_ID),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mForegroundSprite = new Sprite(0, 0,
				ResourceManager.getInstance().getTextureRegion(TEXTURE_FOREGROUND_ID),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		
		mBackgroundSprite.registerEntityModifier(
				new LoopEntityModifier(new MoveYModifier(10, -228, 0)));

		mButtonPlay = new SRButton(BUTTON_OFFSET_X,
				BUTTON_OFFSET_Y,
				GameConstants.STRING.PLAY) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				SceneManager.getInstance().showScene(SceneNavigator.class);
			}
		};
		mButtonOptions = new SRButton(BUTTON_OFFSET_X,
				BUTTON_OFFSET_Y + BUTTON_PADDING_Y,
				GameConstants.STRING.OPTIONS) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				SceneManager.getInstance().showScene(OptionsMenuScene.class);
			}
		};
		mButtonExtras = new SRButton(BUTTON_OFFSET_X,
				BUTTON_OFFSET_Y + BUTTON_PADDING_Y * 2,
				GameConstants.STRING.EXTRAS) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				SceneManager.getInstance().showScene(ExtrasMenuScene.class);
			}
		};
		mButtonExtras.setVisible(false);

		this.attachChild(mBackgroundSprite);
		this.attachChild(mForegroundSprite);
		this.attachChild(mButtonPlay);
		this.attachChild(mButtonOptions);
		this.attachChild(mButtonExtras);

		this.registerTouchArea(mButtonPlay);
		this.registerTouchArea(mButtonOptions);

		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setOnSceneTouchListener(this);
		this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);

		GameDataBase db = new GameDataBase();
		db.debug();
		db.close();
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onDestroyResources()
	 */
	@Override
	public void onDestroyResources() {
		ResourceManager.getInstance().unloadTexture(TEXTURE_BACKGROUND_ID);
		ResourceManager.getInstance().unloadTexture(TEXTURE_FOREGROUND_ID);
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onDestroy()
	 */
	@Override
	public void onDestroy() {
		AudioManager.getInstance().pauseMusic();

		mBackgroundSprite.clearEntityModifiers();
		mBackgroundSprite.detachSelf();
		mForegroundSprite.detachSelf();

		mButtonPlay.detachSelf();
		mButtonOptions.detachSelf();
		mButtonExtras.detachSelf();

		mForegroundSprite.dispose();
		mBackgroundSprite.dispose();

		mButtonPlay.dispose();
		mButtonOptions.dispose();
		mButtonExtras.dispose();

		this.clearTouchAreas();
		this.detachSelf();
		this.dispose();
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onPause()
	 */
	@Override
	public void onPause() {
		// nothing to do
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onResume
	 */
	@Override
	public void onResume() {
		// nothing to do
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		final int[] r = {0, 2, 5, 8, 9, 12, 15, 17, 19, 21, 22, 23, 25};
		if (pSceneTouchEvent.isActionDown()) {
			if (e != 0) e -= (1 << r[m++]);
			if (e == 0) {
				mButtonExtras.setVisible(true);
				this.registerTouchArea(mButtonExtras);
			}
		}
		return true;
	}
	private int m = 0;
	private int e = 0x2ea9325;
}

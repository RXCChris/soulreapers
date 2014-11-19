package com.soulreapers.scene;

import java.util.HashMap;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.HorizontalAlign;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.GameConstants;

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

	// Textures and Sprites for background use
	private ITextureRegion mForegroundTextureRegion;
	private BitmapTextureAtlas mForegroundTextureAtlas;
	private Sprite mForegroundSprite;

	private ITextureRegion mBackgroundTextureRegion;
	private BitmapTextureAtlas mBackgroundTextureAtlas;
	private Sprite mBackgroundSprite;

	private static final int FONT_ID = R.string.ft_command;

	private enum GameOptions {
		PLAY {
			@Override
			public String toString() {
				return ">> Commencer";
			}
		},
		OPTIONS {
			@Override
			public String toString() {
				return ">> Options";
			}
		},
		EXTRAS {
			@Override
			public String toString() {
				return ">> Extras";
			}
		}
	}

	/**
	 * Map containing text buttons.
	 */
	private HashMap<GameOptions, Text> mGameOptionsTextMap = new HashMap<GameOptions, Text>();

	/**
	 * @see com.soulreapers.scene.BaseScene#onLoadResources()
	 */
	@Override
	public void onLoadResources() {
		mForegroundTextureAtlas = new BitmapTextureAtlas(
				ResourceManager.getInstance().getTextureManager(),
				800, 480, TextureOptions.BILINEAR);
		mForegroundTextureRegion = ResourceManager.getInstance()
				.getTextureRegion(mForegroundTextureAtlas, R.string.bg_03);
		mForegroundTextureAtlas.load();

		mBackgroundTextureAtlas = new BitmapTextureAtlas(
				ResourceManager.getInstance().getTextureManager(),
				800, 800, TextureOptions.BILINEAR);
		mBackgroundTextureRegion = ResourceManager.getInstance()
				.getTextureRegion(mBackgroundTextureAtlas, R.string.bg_04);
		mBackgroundTextureAtlas.load();
	}

	private void addGameOption(final GameOptions pOption,
			final int pPadding,
			final Class<? extends BaseScene> pBaseScene,
			boolean pVisible) {
		Text optionText = new Text(GameConstants.X_OPTION_TEXT_PADDING,
				GameConstants.Y_OPTION_TEXT_PADDING * pPadding,
				ResourceManager.getInstance().getFont(FONT_ID),
				pOption.toString(),
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					SceneManager.getInstance().showScene(pBaseScene);
				}
				return true;
			}
		};

		mGameOptionsTextMap.put(pOption, optionText);

		this.attachChild(optionText);
		if (pVisible) {
			this.registerTouchArea(optionText);
		} else {
			optionText.setVisible(false);
		}
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onCreate()
	 */
	@Override
	public void onCreate() {
		mBackgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mBackgroundSprite.registerEntityModifier(
				new LoopEntityModifier(new MoveYModifier(10, -228, 0)));
		attachChild(mBackgroundSprite);

		mForegroundSprite = new Sprite(0, 0, mForegroundTextureRegion,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		attachChild(mForegroundSprite);

		addGameOption(GameOptions.PLAY, 6, SceneNavigator.class, true);
		addGameOption(GameOptions.OPTIONS, 7, OptionsMenuScene.class, true);
		addGameOption(GameOptions.EXTRAS, 8, ExtrasMenuScene.class, false);

		mGameOptionsTextMap.get(GameOptions.EXTRAS).setVisible(false);

		AudioManager.getInstance().playMusic(R.string.ms_01, true, true);

		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setOnSceneTouchListener(this);
		this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onDestroyResources()
	 */
	@Override
	public void onDestroyResources() {
		mForegroundTextureAtlas.unload();
		mForegroundTextureRegion = null;
		mBackgroundTextureAtlas.unload();
		mBackgroundTextureRegion = null;
	}

	/**
	 * @see com.soulreapers.scene.BaseScene#onDestroy()
	 */
	@Override
	public void onDestroy() {
		AudioManager.getInstance().pauseMusic();

		mForegroundSprite.detachSelf();
		mForegroundSprite.dispose();
		mBackgroundSprite.detachSelf();
		mBackgroundSprite.dispose();

		for (Text optionsText : mGameOptionsTextMap.values()) {
			this.unregisterTouchArea(optionsText);
			optionsText.detachSelf();
			optionsText.dispose();
		}
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
	 * @see com.soulreapers.scene.BaseScene#onResume
	 */
	@Override
	public void onResume() {
		// Nothing to do
	}

	/* (non-Javadoc)
	 * @see org.andengine.entity.scene.IOnSceneTouchListener#onSceneTouchEvent(org.andengine.entity.scene.Scene, org.andengine.input.touch.TouchEvent)
	 */
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		final int[] r = {0, 2, 5, 8, 9, 12, 15, 17, 19, 21, 22, 23, 25};
		if (pSceneTouchEvent.isActionDown()) {
			if (e != 0) e -= (1 << r[m++]);
			if (e == 0) {
				mGameOptionsTextMap.get(GameOptions.EXTRAS).setVisible(true);
				this.registerTouchArea(mGameOptionsTextMap.get(GameOptions.EXTRAS));
			}
		}
		return true;
	}
	private int m = 0;
	private int e = 0x2ea9325;
}

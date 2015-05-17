/**
 * 
 */
package com.soulreapers.scene;

import java.util.HashMap;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.GameConstants;

/**
 * @author chris
 *
 */
public class ExtrasMenuScene extends BaseScene {
	private ITextureRegion mBackgroundTextureRegion;
	private BitmapTextureAtlas mBackgroundTextureAtlas;
	private Sprite mBackgroundSprite;

	private HashMap<Integer, Text> mOptionsTextMap = new HashMap<Integer, Text>();
	private HashMap<Integer, Text> mPlaylistTextMap = new HashMap<Integer, Text>();
	private static final int FONT_ID = ResourceManager.FONT_OPTION_ID;

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onLoadResources()
	 */
	@Override
	public void onLoadResources() {
		mBackgroundTextureAtlas = new BitmapTextureAtlas(
				ResourceManager.getInstance().getTextureManager(),
				GameConstants.CAMERA_WIDTH, GameConstants.CAMERA_HEIGHT,
				TextureOptions.BILINEAR);
		mBackgroundTextureRegion = ResourceManager.getInstance()
				.getTextureRegion(mBackgroundTextureAtlas, R.string.bg_extra);
		mBackgroundTextureAtlas.load();
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onCreate()
	 */
	@Override
	public void onCreate() {
		mBackgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		attachChild(mBackgroundSprite);

		// (1) Artwork
		int key = R.string.tb_e01;
		mOptionsTextMap.put(key,
				new Text(GameConstants.X_OPTION_TEXT_PADDING,
						GameConstants.Y_OPTION_TEXT_PADDING,
						ResourceManager.getInstance().getFont(FONT_ID),
						ResourceManager.getInstance().getResourceString(key),
						ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Artwork");
				}
				return true;
			}
		});

		// (2) Jukebox
		key = R.string.tb_e02;
		mOptionsTextMap.put(key,
				new Text(GameConstants.X_OPTION_TEXT_PADDING,
						GameConstants.Y_OPTION_TEXT_PADDING * 2,
						ResourceManager.getInstance().getFont(FONT_ID),
						ResourceManager.getInstance().getResourceString(key),
						ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Jukebox");
					createJukebox();
				}
				return true;
			}
		});

		// (8) Back
		key = R.string.tb_09;
		mOptionsTextMap.put(key,
				new Text(GameConstants.X_OPTION_TEXT_PADDING,
						GameConstants.Y_OPTION_TEXT_PADDING * 8,
						ResourceManager.getInstance().getFont(FONT_ID),
						ResourceManager.getInstance().getResourceString(key),
						ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Back");
					SceneManager.getInstance().showScene(MainMenuScene.class);
				}
				return true;
			}
		});

		for (Text optionText : mOptionsTextMap.values()) {
			this.attachChild(optionText);
			this.registerTouchArea(optionText);
		}
		this.setTouchAreaBindingOnActionDownEnabled(true);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onDestroyResources()
	 */
	@Override
	public void onDestroyResources() {
		mBackgroundTextureAtlas.unload();
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onDestroy()
	 */
	@Override
	public void onDestroy() {
		AudioManager.getInstance().pauseMusic();
		mBackgroundSprite.detachSelf();
		mBackgroundSprite.dispose();
		for (Text optionText : mOptionsTextMap.values()) {
			optionText.detachSelf();
			optionText.dispose();
		}
		this.detachSelf();
		this.dispose();
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onPause()
	 */
	@Override
	public void onPause() {
		// Nothing to do
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onResume()
	 */
	@Override
	public void onResume() {
		// Nothing to do
	}

	private void createJukebox() {
		createPlaylist(R.string.ms_01, R.string.mg_m01, 1);
		createPlaylist(R.string.ms_02, R.string.mg_m02, 2);
		createPlaylist(R.string.ms_03, R.string.mg_m03, 3);
		createPlaylist(R.string.ms_04, R.string.mg_m04, 4);
		createPlaylist(R.string.ms_05, R.string.mg_m05, 5);
		createPlaylist(R.string.ms_06, R.string.mg_m06, 6);
		createPlaylist(R.string.ms_07, R.string.mg_m07, 7);
		createPlaylist(R.string.ms_08, R.string.mg_m08, 8);
		createPlaylist(R.string.ms_09, R.string.mg_m09, 9);
		for (Text musicName : mPlaylistTextMap.values()) {
			this.attachChild(musicName);
			this.registerTouchArea(musicName);
		}
	}

	private void destroyJukebox() {
		for (Text musicName : mPlaylistTextMap.values()) {
			this.unregisterTouchArea(musicName);
			musicName.detachSelf();
			musicName.dispose();
		}
	}

	private void createPlaylist(final int pResId, int pTrackNameId, int pNumTrack) {
		mPlaylistTextMap.put(pResId,
				new Text(GameConstants.Y_OPTION_TEXT_PADDING,
						GameConstants.Y_OPTION_TEXT_PADDING * (pNumTrack - 1),
						ResourceManager.getInstance().getFont(FONT_ID),
						ResourceManager.getInstance().getResourceString(pTrackNameId),
						ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					AudioManager.getInstance().pauseMusic();
					AudioManager.getInstance().playMusic(pResId, true);
				}
				return true;
			}
		});
	}
}

package com.soulreapers.scene;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.misc.MusicTrack;

public class MainMenuScene extends BaseScene {

	private ITextureRegion mMenuBackground;
	private ITextureRegion mPlayButton;
	private ITextureRegion mOptionsButton;
	private BitmapTextureAtlas mMenuTextureAtlas;
	private BitmapTextureAtlas mPlayTextureAtlas;
	private BitmapTextureAtlas mOptionsTextureAtlas;
	private Sprite mMainMenu;
	private Sprite mPlay;
	private Sprite mOptions;

	//private Music mMusic;

	@Override
	public void loadResources() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mMenuTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 800, 480, TextureOptions.BILINEAR);
		mPlayTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 330, 100, TextureOptions.BILINEAR);
		mOptionsTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 330, 100, TextureOptions.BILINEAR);

		mMenuBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, mActivity, mActivity.getString(R.string.background_menu), 0, 0);
		mPlayButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mPlayTextureAtlas, mActivity, mActivity.getString(R.string.button_play), 0, 0);
		mOptionsButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mOptionsTextureAtlas, mActivity, mActivity.getString(R.string.button_options), 0, 0);

		mMenuTextureAtlas.load();
		mPlayTextureAtlas.load();
		mOptionsTextureAtlas.load();

		// Load music
/*		try {
			mMusic = MusicFactory.createMusicFromAsset(mActivity.getMusicManager(), mActivity, mActivity.getString(R.string.mus_lullaby_of_reaper));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mMusic.setLooping(true);
*/	}

	@Override
	public void create() {
		mMainMenu = new Sprite(0, 0, mMenuBackground, mVbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};

		mPlay = new Sprite(400, 300, mPlayButton, mVbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}

			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Debug.i("Touch Play");
				mSceneManager.showScene(GameScene.class);
				return true;
			}
		};

		mOptions = new Sprite(400, 400, mOptionsButton, mVbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}

			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Debug.i("Touch Option");
				return true;
			}
		};
		registerTouchArea(mPlay);
		registerTouchArea(mOptions);
		setTouchAreaBindingOnActionDownEnabled(true);
//		mMainMenu.setPosition(0, 0);
		attachChild(mMainMenu);
		attachChild(mPlay);
		attachChild(mOptions);

		//mMusic.play();
		AudioManager.getInstance().playMusic(MusicTrack.TRACK_01, true);

/*		attachChild(new Sprite(800, 480, mMenuBackground, mVbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.postDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		});
*/
		//createMenuChildScene();
	}
/*
	private void createMenuChildScene() {
		mMenuChildScene = new MenuScene();
		mMenuChildScene.setPosition(400, 240);
		final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, mPlayButton, mVbom), 1.0f, 1.0f);
		final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, mOptionsButton, mVbom), 1.0f, 1.0f);

		mMenuChildScene.addMenuItem(playMenuItem);
		mMenuChildScene.addMenuItem(optionsMenuItem);

		mMenuChildScene.buildAnimations();
		mMenuChildScene.setBackgroundEnabled(false);

		playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY());
		optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() - 100);

		mMenuChildScene.setOnMenuItemClickListener(this);
		setChildScene(mMenuChildScene);
	}
*/
	@Override
	public void unloadResources() {
		mMenuTextureAtlas.unload();
		mPlayTextureAtlas.unload();
		mOptionsTextureAtlas.unload();
		mMenuBackground = null;
		mPlayButton = null;
		mOptionsButton = null;
	}

	@Override
	public void destroy() {
		mMainMenu.detachSelf();
		mPlay.detachSelf();
		mOptions.detachSelf();
		mMainMenu.dispose();
		mPlay.dispose();
		mOptions.dispose();
		this.detachSelf();
		this.dispose();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "MenuScene";
	}

	@Override
	public void onBackKeyPressed() {
		// Nothing to do
	}
/*
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		switch (pMenuItem.getID()) {
		case MENU_PLAY:
			return true;
		case MENU_OPTIONS:
			return true;
		default:
			return false;
		}
	}*/
}

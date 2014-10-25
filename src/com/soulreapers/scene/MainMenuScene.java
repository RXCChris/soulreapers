package com.soulreapers.scene;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;

public class MainMenuScene extends BaseScene {

	private ITextureRegion mForegroundTextureRegion;
	private BitmapTextureAtlas mForegroundTextureAtlas;
	private Sprite mForegroundSprite;

	private ITextureRegion mBackgroundTextureRegion;
	private BitmapTextureAtlas mBackgroundTextureAtlas;
	private Sprite mBackgroundSprite;

	private Text mTextPlay;
	private Text mTextOptions;
	private Text mTextExtras;
	private Text mTextQuit;

	@Override
	public void onLoadResources() {
//		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mForegroundTextureAtlas = new BitmapTextureAtlas(ResourceManager.getInstance().getTextureManager(),
				800, 480, TextureOptions.BILINEAR);
		mForegroundTextureRegion = ResourceManager.getInstance()
				.getTextureRegion(mForegroundTextureAtlas, R.string.bg_03);
//		mForegroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mForegroundTextureAtlas, mActivity, mActivity.getString(R.string.bg_03), 0, 0);
		mForegroundTextureAtlas.load();

		mBackgroundTextureAtlas = new BitmapTextureAtlas(ResourceManager.getInstance().getTextureManager(), 800, 800, TextureOptions.BILINEAR);
//		mBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBackgroundTextureAtlas, mActivity, mActivity.getString(R.string.bg_04), 0, 0);
		mBackgroundTextureRegion = ResourceManager.getInstance()
				.getTextureRegion(mBackgroundTextureAtlas, R.string.bg_04);
		mBackgroundTextureAtlas.load();
	}

	@Override
	public void onCreate() {
//		mBackgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion, mVbom);
		mBackgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mBackgroundSprite.registerEntityModifier(new LoopEntityModifier(new MoveYModifier(10, -228, 0)));
//		mMainMenu2.registerEntityModifier(new LoopEntityModifier(new RotationModifier(6, 0, 360)));

		attachChild(mBackgroundSprite);		

//		mForegroundSprite = new Sprite(0, 0, mForegroundTextureRegion, mVbom);
		mForegroundSprite = new Sprite(0, 0, mForegroundTextureRegion,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		attachChild(mForegroundSprite);

//		mTextPlay = new Text(500, 240, ResourceManager.getInstance().getFont(R.string.ft_03),
//				mActivity.getString(R.string.tb_01), new TextOptions(HorizontalAlign.RIGHT), mVbom) {
		mTextPlay = new Text(500, 240, ResourceManager.getInstance().getFont(R.string.ft_03),
				ResourceManager.getInstance().getResourceString(R.string.tb_01),
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Debug.i("You touched Play");
				SceneManager.getInstance().showScene(GameScene.class);
				return true;
			}
		};

//		mTextOptions = new Text(500, 300, ResourceManager.getInstance().getFont(R.string.ft_03),
//				mActivity.getString(R.string.tb_02), new TextOptions(HorizontalAlign.RIGHT), mVbom)	 {
		mTextOptions = new Text(500, 300, ResourceManager.getInstance().getFont(R.string.ft_03),
				ResourceManager.getInstance().getResourceString(R.string.tb_02),
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Debug.i("You touched Options");
				SceneManager.getInstance().showScene(OptionsMenuScene.class);
				return true;
			}
		};

//		mTextExtras = new Text(500, 360, ResourceManager.getInstance().getFont(R.string.ft_03),
//				mActivity.getString(R.string.tb_03), new TextOptions(HorizontalAlign.RIGHT), mVbom) {
		mTextExtras = new Text(500, 360, ResourceManager.getInstance().getFont(R.string.ft_03),
				ResourceManager.getInstance().getResourceString(R.string.tb_03),
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Debug.i("You touched Extras");
				SceneManager.getInstance().showScene(BattleScene.class);
				return true;
			}
		};

//		mTextQuit = new Text(500, 420, ResourceManager.getInstance().getFont(R.string.ft_03),
//				mActivity.getString(R.string.tb_04), new TextOptions(HorizontalAlign.RIGHT), mVbom) {
//		
		mTextQuit = new Text(500, 420, ResourceManager.getInstance().getFont(R.string.ft_03),
				ResourceManager.getInstance().getResourceString(R.string.tb_04),
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Debug.i("You touched Quit");
				return true;
			}
		};

		registerTouchArea(mTextPlay);
		registerTouchArea(mTextOptions);
		registerTouchArea(mTextExtras);
		registerTouchArea(mTextQuit);

		attachChild(mTextPlay);
		attachChild(mTextOptions);
		attachChild(mTextExtras);
		attachChild(mTextQuit);

		AudioManager.getInstance().playMusic(R.string.ms_02, true);
	}

	@Override
	public void onDestroyResources() {
		mForegroundTextureAtlas.unload();
		mForegroundTextureRegion = null;
		mBackgroundTextureAtlas.unload();
		mBackgroundTextureRegion = null;
	}

	@Override
	public void onDestroy() {
		mForegroundSprite.detachSelf();
		mForegroundSprite.dispose();
		mTextPlay.detachSelf();
		mTextPlay.dispose();
		mTextOptions.detachSelf();
		mTextOptions.dispose();
		mTextExtras.detachSelf();
		mTextExtras.dispose();
		mTextQuit.detachSelf();
		mTextQuit.dispose();
		this.detachSelf();
		this.dispose();
	}

	@Override
	public void onPause() {
		AudioManager.getInstance().pauseMusic();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		AudioManager.getInstance().resumeMusic();
	}

	@Override
	public String toString() {
		return "MenuScene";
	}

	@Override
	public void onBackKeyPressed() {
		// Nothing to do
	}
}

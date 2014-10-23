package com.soulreapers.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;

import com.soulreapers.R;

public class SplashScene extends BaseScene {

	private BitmapTextureAtlas mSplashTextureAtlas;
	private ITextureRegion mSplashRegion;
	private Sprite mSplash;

	@Override
	public void loadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		mSplashTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 800, 480, TextureOptions.BILINEAR);
		mSplashRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mSplashTextureAtlas, mActivity, mActivity.getString(R.string.background_splash), 0, 0);
		mSplashTextureAtlas.load();
	}

	@Override
	public void create() {
		mSplash = new Sprite(0, 0, mSplashRegion, mVbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
//		mSplash.setPosition(0, 0);
		//mSplash.setScale(2.0f);
		attachChild(mSplash);
	}

	@Override
	public void unloadResources() {
		// TODO Auto-generated method stub
		mSplashTextureAtlas.unload();
		mSplashRegion = null;
	}

	@Override
	public void destroy() {
		mSplash.detachSelf();
		mSplash.dispose();
		this.detachSelf();
		this.dispose();
	}

	@Override
	public void onPause() {
		// nothing to do
	}

	@Override
	public void onResume() {
		// nothing to do
	}

	@Override
	public String toString() {
		return "SplashScene";
	}
}

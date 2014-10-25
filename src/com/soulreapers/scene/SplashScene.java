package com.soulreapers.scene;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;

public class SplashScene extends BaseScene {

	private BitmapTextureAtlas mSplashTextureAtlas;
	private ITextureRegion mSplashRegion;
	private Sprite mSplash;

	@Override
	public void onLoadResources() {
//		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
//		mSplashTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 800, 480, TextureOptions.BILINEAR);
//		mSplashRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mSplashTextureAtlas, mActivity, mActivity.getString(R.string.bg_01), 0, 0);
//		mSplashTextureAtlas.load();
		mSplashTextureAtlas = new BitmapTextureAtlas(ResourceManager.getInstance().getTextureManager(),
				800, 480, TextureOptions.BILINEAR);
		mSplashRegion = ResourceManager.getInstance().getTextureRegion(mSplashTextureAtlas, R.string.bg_01);
		mSplashTextureAtlas.load();
	}

	@Override
	public void onCreate() {
//		mSplash = new Sprite(0, 0, mSplashRegion, mVbom);
		mSplash = new Sprite(0, 0, mSplashRegion, ResourceManager.getInstance().getVertexBufferObjectManager());
		attachChild(mSplash);
	}

	@Override
	public void onDestroyResources() {
		mSplashTextureAtlas.unload();
		mSplashRegion = null;
	}

	@Override
	public void onDestroy() {
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

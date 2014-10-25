/**
 * 
 */
package com.soulreapers.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.object.BattleObject;

/**
 * @author CChris
 *
 */
public class BattleScene extends BaseScene /*implements IOnSceneTouchListener*/ {
	private ITextureRegion mBackgroundRegion;
	private BitmapTextureAtlas mBackgroundTextureAtlas;
	private Sprite mBackground;

	private ITextureRegion mGridRegion;
	private BitmapTextureAtlas mGridTextureAtlas;
	private Sprite mGrid;

	private final int mOffsetX = 360;
	private final int mOffsetY = 40;
	private final int mCellLength = 50;
	private final int mCellPerLine = 8;

	private BattleObject mbo;

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#loadResources()
	 */
	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		Debug.i(">>load background");
//		mBackgroundTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 800, 480, TextureOptions.BILINEAR);
//		mBackgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBackgroundTextureAtlas, mActivity, mActivity.getString(R.string.bg_05), 0, 0);
//		mBackgroundTextureAtlas.load();
		mBackgroundTextureAtlas = new BitmapTextureAtlas(ResourceManager.getInstance().getTextureManager(), 800, 480, TextureOptions.BILINEAR);
		mBackgroundRegion = ResourceManager.getInstance().getTextureRegion(mBackgroundTextureAtlas, R.string.bg_05);
		mBackgroundTextureAtlas.load();

		Debug.i(">>load grid");
//		mGridTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 400, 400, TextureOptions.BILINEAR);
//		mGridRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGridTextureAtlas, mActivity, mActivity.getString(R.string.bg_06), 0, 0);
//		mGridTextureAtlas.load();
		mGridTextureAtlas = new BitmapTextureAtlas(ResourceManager.getInstance().getTextureManager(), 400, 400, TextureOptions.BILINEAR);
		mGridRegion = ResourceManager.getInstance().getTextureRegion(mGridTextureAtlas, R.string.bg_06);
		mGridTextureAtlas.load();
		Debug.i(">>load end");
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#create()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Debug.i(">>create background");
//		mBackground = new Sprite(0, 0, mBackgroundRegion, mVbom) {
//			@Override
//			protected void preDraw(GLState pGLState, Camera pCamera) {
//				super.preDraw(pGLState, pCamera);
//				pGLState.enableDither();
//			}
//		};
		mBackground = new Sprite(0, 0, mBackgroundRegion, ResourceManager.getInstance().getVertexBufferObjectManager());
		Debug.i(">>create grid");
//		mGrid = new Sprite(mOffsetX, mOffsetY, mGridRegion, mVbom) {
//			@Override
//			protected void preDraw(GLState pGLState, Camera pCamera) {
//				super.preDraw(pGLState, pCamera);
//				pGLState.enableDither();
//			}
//		};
		mGrid = new Sprite(mOffsetX, mOffsetY, mGridRegion, ResourceManager.getInstance().getVertexBufferObjectManager());

		Debug.i(">>attach sprite");
		attachChild(mBackground);
		attachChild(mGrid);
		Debug.i(">>create end");
		mbo = new BattleObject(R.string.ic_01, 200,200, true);
//		mbo = new BattleObject(mActivity, R.string.ic_01, 200,200, true);
		mbo.display(this);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#unloadResources()
	 */
	@Override
	public void onDestroyResources() {
		// TODO Auto-generated method stub

	}

	private int CoordToIndex(int x, int y) {
		final int maxX = mOffsetX + mCellPerLine * mCellLength;
		final int maxY = mOffsetY + mCellPerLine * mCellLength;

		if (x < mOffsetX) {
			x = mOffsetX;
		}
		if (x > maxX) {
			x = maxX;
		}
		if (y < mOffsetY) {
			y = mOffsetY;
		}
		if (y > maxY) {
			y = maxY;
		}
		return x / mCellLength + mCellPerLine * y / mCellLength;
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#destroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onPause()
	 */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onResume()
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.andengine.entity.scene.IOnSceneTouchListener#onSceneTouchEvent(org.andengine.entity.scene.Scene, org.andengine.input.touch.TouchEvent)
	 */
//	@Override
//	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}

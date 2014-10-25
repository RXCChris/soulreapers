/**
 * 
 */
package com.soulreapers.object;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.soulreapers.GameActivity;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.object.character.PlayableCharacter;
import com.soulreapers.scene.GameScene;

/**
 * @author chris
 *
 */
public class BattleObject {
	private BitmapTextureAtlas mObjectTextureAtlas;
	private ITextureRegion mObjectTextureRegion;
	private Sprite mObjectSprite;
	private boolean mMovable; // user may move it
	private static final int SPRITE_SIZE = 50;

	private final int mOffsetX = 360;
	private final int mOffsetY = 40;
	private final int mCellLength = 50;
	private final int mCellPerLine = 8; // 


//	public BattleObject(GameActivity activity, int resId, int x, int y, boolean movable) {
//		mMovable = movable;
//		mObjectTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), SPRITE_SIZE, SPRITE_SIZE, TextureOptions.BILINEAR);
//		mObjectTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mObjectTextureAtlas, activity, activity.getString(resId), 0, 0);
//		mObjectTextureAtlas.load();
//		create(x, y, activity.getVertexBufferObjectManager());
//	}
	public BattleObject(int resId, int x, int y, boolean movable) {
		mMovable = movable;
		mObjectTextureAtlas = new BitmapTextureAtlas(ResourceManager.getInstance().getTextureManager(), SPRITE_SIZE, SPRITE_SIZE, TextureOptions.BILINEAR);
		mObjectTextureRegion = ResourceManager.getInstance().getTextureRegion(mObjectTextureAtlas, resId);
		mObjectTextureAtlas.load();
		create(x, y);
	}

//	private void create(int x, int y, VertexBufferObjectManager vbom) {
//		if (mMovable) {
//			mObjectSprite = new Sprite(x, y, mObjectTextureRegion, vbom) {
//				@Override
//				public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
//						final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
//					if (pSceneTouchEvent.isActionMove()) {
//						int gridX = toGridCoordX(pSceneTouchEvent.getX() - this.getWidth() / 2);
//						int gridY = toGridCoordY(pSceneTouchEvent.getY() - this.getHeight() / 2);
//						int index = toGridIndex(gridX, gridY);
//						this.setPosition(gridToCoordX(index), gridToCoordY(index));
//					}
//					return true;
//				}
//			};
//		} else {
//			mObjectSprite = new Sprite(x, y, mObjectTextureRegion, vbom);
//		}
//	}
	private void create(int x, int y) {
		if (mMovable) {
			mObjectSprite = new Sprite(x, y, mObjectTextureRegion, ResourceManager.getInstance().getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
						final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
					if (pSceneTouchEvent.isActionMove()) {
						int gridX = toGridCoordX(pSceneTouchEvent.getX() - this.getWidth() / 2);
						int gridY = toGridCoordY(pSceneTouchEvent.getY() - this.getHeight() / 2);
						int index = toGridIndex(gridX, gridY);
						this.setPosition(gridToCoordX(index), gridToCoordY(index));
					}
					return true;
				}
			};
		} else {
			mObjectSprite = new Sprite(x, y, mObjectTextureRegion, ResourceManager.getInstance().getVertexBufferObjectManager());
		}
	}

	private int gridToCoordX(int index) {
		int x = mOffsetX + (index % mCellPerLine) * mCellLength;
		return x;
	}

	private int gridToCoordY(int index) {
		int y = mOffsetY + (index / mCellPerLine) * mCellLength;
		return y;
	}

	public void display(Scene scene) {
		scene.attachChild(mObjectSprite);
		if (mMovable) {
			scene.registerTouchArea(mObjectSprite);
			scene.setTouchAreaBindingOnActionDownEnabled(true);
		}
	}

	public void visit(PlayableCharacter character) {
		
	}

	private int toGridIndex(int x, int y) {
		int gridX = (toGridCoordX(x) - mOffsetX) / mCellLength;
		int gridY = (toGridCoordY(y) - mOffsetY) / mCellLength;
		return gridX + mCellPerLine * gridY;
	}

	private int toGridCoordX(float x) {
		return toGridCoord(x, mOffsetX);
	}

	private int toGridCoordY(float y) {
		return toGridCoord(y, mOffsetY);
	}

	private int toGridCoord(float coord, int offset) {
		final int max = offset + (mCellPerLine - 1) * mCellLength;
		if (coord < offset) {
			coord = offset;
		}
		if (coord > max) {
			coord = max;
		}
		return (int) coord;
	}

	public void destroy() {
		mObjectSprite.detachSelf();
		mObjectSprite.dispose();
	}
}

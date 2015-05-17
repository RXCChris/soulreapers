/**
 * Project Soul Reapers
 * Copyright (c) 2014 Chengwu Huang (dxcloud) <chengwhuang@gmail.com>
 *
 * This file is part of 'Soul Reapers'.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.soulreapers.object;

import java.util.ArrayList;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.Attributes;
import com.soulreapers.misc.Attributes.AttributeType;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.GameCharacter.CharacterType;
import com.soulreapers.scene.BattleScene;
import com.soulreapers.ui.BattleActionUI.ActionType;

/**
 * @since 2014.10.31
 * @version 0.1 (alpha)
 * @author dxcloud
 *
 */
public class BattleObject extends Sprite {
	private boolean mMovable; // user may move it
	private BattleCharacter mCharacter;
	private ArrayList<Rectangle> mMoveSurface = new ArrayList<Rectangle>();

	private int mGridIndexX;
	private int mGridIndexY;

	private BattleScene mBattleScene;

//	private CharacterType mCharacterType;

	public CharacterType getCharacterType() {
//		return mCharacterType;
		return mCharacter.getCharacterType();
	}

	public Attributes getAttributes() {
		return mCharacter.getAttributes();
	}

	public BattleObject(BattleCharacter pPlayableCharacter,
//			CharacterType pCharacterType,
			int pResId,
			int gridIndexX,
			int gridIndexY,
			boolean movable,
			BattleScene pBattleScene) {
		super(gridIndexToCoordX(gridIndexX),
				gridIndexToCoordY(gridIndexY),
				ResourceManager.getInstance().getTextureRegion(pResId,
						GameConstants.BATTLE_CELL_SIZE,
						GameConstants.BATTLE_CELL_SIZE),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mCharacter = pPlayableCharacter;
//		mCharacterType = pCharacterType;
		mMovable = movable;
		mGridIndexX = gridIndexX;
		mGridIndexY = gridIndexY;
		mBattleScene = pBattleScene;
		mCharacter.setVisible(false);
	}

//	public void setCharacterStatusVisible(boolean pVisible) {
//		mCharacter.setVisible(pVisible);
//	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		if (mBattleScene.getActionUI().isCurrentAction(ActionType.STATUS)) {
//			mBattleScene.getActionUI().enableStatusUI(true);
//			mCharacter.setVisible(true);
			mBattleScene.showCharacterArt(this);
			mBattleScene.showStatus(this);
		} else if (mBattleScene.getActionUI().isCurrentAction(ActionType.DEFEND)) {
			mBattleScene.prepareToDefend(this);
		} else if (mBattleScene.getActionUI().isCurrentAction(ActionType.ATTACK)) {
			if (getCharacterType() == CharacterType.REMNANT) {
				mBattleScene.prepareToAttack(this);
			}
		} else if (mBattleScene.getActionUI().isCurrentAction(ActionType.MOVE)) {
			if (true == mMovable) {
				if ((pSceneTouchEvent.isActionMove())
						|| (pSceneTouchEvent.isActionDown())) {
					int centerX = gridCoordToIndexX(indexToGridCoordX(
							pSceneTouchEvent.getX() - this.getWidth() / 2));
					int centerY = gridCoordToIndexY(indexToGridCoordY(
							pSceneTouchEvent.getY() - this.getHeight() / 2));
					if (isWithinMoveSurface(centerX, centerY)) {
						this.setPosition(gridIndexToCoordX(centerX),
								gridIndexToCoordY(centerY));
					}
				} else if (pSceneTouchEvent.isActionUp()) { mBattleScene.prepareToMove(this); }
			}
		}
		return true;
	}

	public void setMoveSurfaceVisible(boolean pVisible) {
		showMoveSurface(pVisible);
	}

	public BattleCharacter getCharacter() {
		return mCharacter;
	}

//	public boolean isMoveToValidate() {
//		return mMoveToValidate;
//	}
	private static final float AUTOMOVE_DURATION = 2.0F;

	public void autoMove(int pToIndexX, int pToIndexY) {
		this.registerEntityModifier(
				new MoveModifier(
						AUTOMOVE_DURATION,
						gridIndexToCoordX(mGridIndexX),
						gridIndexToCoordX(pToIndexX),
						gridIndexToCoordY(mGridIndexY),
						gridIndexToCoordY(pToIndexY)));
		validateMove();
	}

	private static float gridIndexToCoordX(int indexX) {
		float x = GameConstants.X_BATTLE_FIELD_PADDING
				+ indexX * GameConstants.BATTLE_CELL_SIZE;
		return x;
	}

	public void validateMove() {
		mGridIndexX = getCurrentIndexX();
		mGridIndexY = getCurrentIndexY();
//		mMoveToValidate = false;
		updateMoveSurface();
	}

	public int getCurrentIndexX() {
		return gridCoordToIndexX(getCenterX());
	}

	public int getCurrentIndexY() {
		return gridCoordToIndexY(getCenterY());
	}

	public int getOriginIndexX() {
		return mGridIndexX;
	}

	public int getOriginIndexY() {
		return mGridIndexY;
	}

	public float getCenterX() {
		return (this.getX() - this.getWidth() / 2);
	}

	public float getCenterY() {
		return (this.getY() - this.getHeight() / 2);
	}

	public void invalidateMove() {
//		mMoveToValidate = false;
		this.setPosition(gridIndexToCoordX(mGridIndexX),
				gridIndexToCoordY(mGridIndexY));
	}

	private void updateMoveSurface() {
//		for (int i = 0; i < mMoveSurface.size(); ++i) {
//			mMoveSurface.get(i).detachSelf();
//			mMoveSurface.get(i).dispose();
//		}
//		mMoveSurface.clear();
		clearMoveSurface();
		createMoveSurface();
	}

	private void clearMoveSurface() {
		for (int i = 0; i < mMoveSurface.size(); ++i) {
			mMoveSurface.get(i).detachSelf();
			mMoveSurface.get(i).dispose();
		}
		mMoveSurface.clear();
	}

	private static float gridIndexToCoordY(int indexY) {
		return GameConstants.Y_BATTLE_FIELD_PADDING
				+ indexY * GameConstants.BATTLE_CELL_SIZE;
	}

	public void setMovable(boolean movable) {
		mMovable = movable;
	}

	public boolean isMovable() {
		return mMovable;
	}

	private void showMoveSurface(boolean pVisible) {
		for (int i = 0; i < mMoveSurface.size(); i++) {
			mMoveSurface.get(i).setVisible(pVisible);
		}
	}

	private void addToMoveSurface(int indexX, int indexY) {
		if ((indexX >= 0)
				&& (indexX < GameConstants.BATTLE_CELLS_PER_ROW)
				&& (indexY >= 0)
				&& (indexY < GameConstants.BATTLE_CELLS_PER_COLUMN)) {
			Rectangle rectangle = new Rectangle(gridIndexToCoordX(indexX),
					gridIndexToCoordY(indexY),
					GameConstants.BATTLE_CELL_SIZE,
					GameConstants.BATTLE_CELL_SIZE,
					ResourceManager.getInstance().getVertexBufferObjectManager());
			rectangle.setColor(1.0f, 0, 0, 0.5f);
			rectangle.setVisible(false);
			mMoveSurface.add(rectangle);
			// Must be attached to its parent, otherwise the surface moves
			// along with the object.
			this.getParent().attachChild(rectangle);
		}
	}

	@Override
	public void onAttached() {
//		mCharacter.detachSelf();
//		this.getParent().attachChild(mCharacter);
		super.onAttached();
		createMoveSurface(); // Creates surface here in order to get its parent
		mBattleScene.attachChild(mCharacter);
	}

	private void createMoveSurface() {
		int move = mCharacter.getAttributes().getCurrent(AttributeType.TEMPERANCE);
		for (int i = 0; i <= move; ++i) {
			for (int j = 0; j <= (move - i); ++j) {
				addToMoveSurface(mGridIndexX + i, mGridIndexY + j);
				if (i != 0) {
					addToMoveSurface(mGridIndexX - i, mGridIndexY - j);
				}
				if (j != 0) {
					addToMoveSurface(mGridIndexX + i, mGridIndexY - j);
				}
				if ((i != 0) && (j != 0)) {
					addToMoveSurface(mGridIndexX - i, mGridIndexY + j);
				}
			}
		}
	}

	private boolean isWithinMoveSurface(int indexX, int indexY) {
		int move = mCharacter.getAttributes().getCurrent(AttributeType.TEMPERANCE);
		for (int i = 0; i <= move; ++i) {
			if (indexX <= (mGridIndexX - i)){
				if (indexX < mGridIndexX - move) {
					return false;
				}
				if ((indexY < (mGridIndexY - move + i))
						|| (indexY > (mGridIndexY + move - i))){
					return false;
				}
			}
			if (indexX >= (mGridIndexX + i)) {
				if (indexX > mGridIndexX + move) {
					return false;
				}
				if ((indexY < (mGridIndexY - move + i))
						|| (indexY > (mGridIndexY + move - i))) {
					return false;
				}
			}
		}
		return true;
	}

	private static int gridCoordToIndexX(float gridCoordX) {
		return Math.round((gridCoordX - GameConstants.X_BATTLE_FIELD_PADDING)
				/ GameConstants.BATTLE_CELL_SIZE);
	}

	private static int gridCoordToIndexY(float gridCoordY) {
		return Math.round((gridCoordY - GameConstants.Y_BATTLE_FIELD_PADDING)
				/ GameConstants.BATTLE_CELL_SIZE);
	}

	private static float indexToGridCoordX(float x) {
		return toGridCoord(x, GameConstants.X_BATTLE_FIELD_PADDING);
	}

	private static float indexToGridCoordY(float y) {
		return toGridCoord(y, GameConstants.Y_BATTLE_FIELD_PADDING);
	}

	private static int toGridCoord(float coord, int padding) {
		final int max = padding +
				(GameConstants.BATTLE_CELLS_PER_ROW - 1)
				* GameConstants.BATTLE_CELL_SIZE;
		if (coord < padding) {
			coord = padding;
		}
		if (coord > max) {
			coord = max;
		}
		return (int) coord;
	}

//	@Override
//	public boolean detachSelf() {
//		mCharacter.detachSelf();
//		clearMoveSurface();
//		return super.detachSelf();
//	}

	@Override
	public void onDetached() {
		super.onDetached();
		mCharacter.detachSelf();
		clearMoveSurface();
	}

	public boolean isWithinArea(BattleObject pTarget, int pMaxDistance) {
		if (pTarget.getCurrentIndexX() < this.getCurrentIndexX() - pMaxDistance) {
			return false;
		}
		if (pTarget.getCurrentIndexY() < this.getCurrentIndexY() - pMaxDistance) {
			return false;
		}
		if (pTarget.getCurrentIndexX() > this.getCurrentIndexX() + pMaxDistance) {
			return false;
		}
		if (pTarget.getCurrentIndexY() > this.getCurrentIndexY() + pMaxDistance) {
			return false;
		}
		return true;
	}
}

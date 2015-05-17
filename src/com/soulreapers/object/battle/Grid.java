/**
 * 
 */
package com.soulreapers.object.battle;

import java.util.HashMap;

import org.andengine.entity.primitive.Rectangle;

import com.soulreapers.core.ResourceManager;

/**
 * @author chris
 *
 */
public class Grid {
	static class Cell extends Rectangle {
		private static final int MAX_CELL_PER_ROW = 8;
		private static final int MAX_CELL_PER_COLUMN = 8;
		private boolean mOccupied = false;
		private Unit mUnit = null;
		private int mIndexX;
		private int mIndexY;
		private HashMap<Adjacent, Cell> mAdjacentCells = new HashMap<Adjacent, Cell>();

		public Cell(final int pX, final int pY) {
			super(0,0,0,0,ResourceManager.getInstance().getVertexBufferObjectManager());
			mIndexX = pX;
			mIndexY = pY;
		}
		public void addAdjacent(final Adjacent pAdjacent, final Cell pCell) {
			if (!mAdjacentCells.containsKey(pAdjacent)) {
				mAdjacentCells.put(pAdjacent, pCell);
			}
		}
		public boolean isAdjacent(final int pX, final int pY, final int pDistance) {
			if ((pX > mIndexX + pDistance)
					|| (pX < mIndexX - pDistance)
					|| (pY < mIndexY - pDistance)
					|| (pY > mIndexY + pDistance)) {
				return false;
			} else {
				return true;
			}
		}
		public boolean isAdjacent(final Cell pCell, final int pDistance) {
			return isAdjacent(pCell.getIndexX(), pCell.getIndexY(), pDistance);
		}

		public int getIndexX() {
			return mIndexX;
		}
		public int getIndexY() {
			return mIndexY;
		}
		public enum Adjacent {
			ABOVE,
			BELOW,
			LEFT,
			RIGHT,
			ABOVE_LEFT,
			ABOVE_RIGHT,
			BELOW_LEFT,
			BELOW_RIGHT
		}
		public Cell getAdjacent(final Adjacent pAdjacent) {
			return mAdjacentCells.get(pAdjacent);
		}

		public void setUnit(final Unit pUnit) {
			mUnit = pUnit;
			mOccupied = true;
		}

		public Unit getUnit() {
			return mUnit;
		}

		public void removeUnit() {
			mUnit = null;
			mOccupied = false;
		}

		public boolean isOccupied() {
			return mOccupied;
		}
	}
}

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

import org.andengine.entity.sprite.Sprite;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;

/**
 * @author dxcloud
 *
 */
public class FieldGrid extends Sprite {
	private boolean[][] mGridCells = new boolean[GameConstants.BATTLE_CELLS_PER_ROW]
			[GameConstants.BATTLE_CELLS_PER_COLUMN];
	private static final int FIELD_WIDTH = 400;
	private static final int FIELD_HEIGHT = 400;

	public FieldGrid() {
		super(GameConstants.X_BATTLE_FIELD_PADDING, GameConstants.Y_BATTLE_FIELD_PADDING,
				ResourceManager.getInstance().getTextureRegion(R.string.bg_grid,
						FIELD_WIDTH, FIELD_HEIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		resetAll();
	}

	public boolean isOccupied(int pIndexX, int pIndexY) throws ArrayIndexOutOfBoundsException {
		if ((0 > pIndexX)
				|| (GameConstants.BATTLE_CELLS_PER_ROW <= pIndexX)
				|| (0 > pIndexY)
				|| (GameConstants.BATTLE_CELLS_PER_COLUMN <= pIndexY)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return mGridCells[pIndexX][pIndexY];
	}

	public boolean move(int pFromIndexX, int pFromIndexY, int pToIndexX, int pToIndexY)
			throws ArrayIndexOutOfBoundsException {
		if (removeFromCell(pFromIndexX, pFromIndexY)
				&& putIntoCell(pToIndexX, pToIndexY)) {
			return true;
		}
		return false;
	}

	public boolean putIntoCell(int pIndexX, int pIndexY) throws ArrayIndexOutOfBoundsException {
		if (isOccupied(pIndexX, pIndexY)) {
			return false;
		}
		mGridCells[pIndexX][pIndexY] = true;
		return true;
	}

	public boolean removeFromCell(int pIndexX, int pIndexY) throws ArrayIndexOutOfBoundsException {
		if (!isOccupied(pIndexX, pIndexY)) {
			return false;
		}
		mGridCells[pIndexX][pIndexY] = false;
		return true;
	}

	public void resetAll() {
		for (int i = 0; i < GameConstants.BATTLE_CELLS_PER_ROW; ++i) {
			for (int j = 0; j < GameConstants.BATTLE_CELLS_PER_COLUMN; ++j) {
				mGridCells[i][j] = false;
			}
		}
	}

	/**
	 * <b>Only for Debug use.</b>
	 * <p>
	 * Prints the current field map where an occupied place is indicated by 'X'.
	 * </p>
	 */
	@Override
	public String toString() {
		String s = "---Field Map---\n";
		for (int j = 0; j < GameConstants.BATTLE_CELLS_PER_COLUMN; ++j) {
			for (int i = 0; i < GameConstants.BATTLE_CELLS_PER_ROW; ++i) {
				if (mGridCells[i][j] == true) {
					s += "X";
				} else s += ".";
			}
			s += "\n";
		}
		return s;
	}

}

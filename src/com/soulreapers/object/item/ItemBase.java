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
package com.soulreapers.object.item;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.reaper.Reaper;

/**
 * @author chris
 *
 */
public class ItemBase implements Comparable<ItemBase> {

	public enum ItemType {
		COMBAT {
			@Override
			public String toString() {
				return GameConstants.STRING.COMBAT;
			}
		},
		WEAPON {
			@Override
			public String toString() {
				return GameConstants.STRING.WEAPON;
			}
		},
		ACCESSORY {
			@Override
			public String toString() {
				return GameConstants.STRING.ACCESSORY;
			}
		},
		LOOT {
			@Override
			public String toString() {
				return GameConstants.STRING.LOOT;
			}
		},
		SKILL {
			@Override
			public String toString() {
				return GameConstants.STRING.SKILL;
			}
		},
		KEY {
			@Override
			public String toString() {
				return GameConstants.STRING.KEY;
			}
		}
	}

	public static final int MAX_QUANTITY = 99;
	/**
	 * Each item has an unique ID.
	 */
	private final int mItemId;
	private final ItemType mItemType;
	private final String mName;
	private final String mDescription;
	private int mQuantity = 1;
	private int mAvailability;

	protected Effect mEffect;

	public ItemBase(final int pItemId,
			final ItemType pItemType,
			final String pName,
			final String pDescription,
			int pQuantity) {
		mItemId = pItemId;
		mItemType = pItemType;
		mName = pName;
		mDescription = pDescription;
		setQuantity(pQuantity);
		mAvailability = mQuantity;
	}

	public static final ItemBase EMPTY = new ItemBase(0, null, "EMPTY", "", 0);

	public int getItemId() {
		return mItemId;
	}

	public int getQuantity() {
		return mQuantity;
	}

	public ItemType getItemType() {
		return mItemType;
	}

	public String getName() {
		return mName;
	}

	public String getDescription() {
		return mDescription;
	}

	private void setQuantity(int pQuantity) {
		mQuantity = pQuantity;
		if (mQuantity > MAX_QUANTITY) {
			mQuantity = MAX_QUANTITY;
		} else if (pQuantity < 0) {
			mQuantity = 0;
		}
	}

	public int getAvailability() {
		return mAvailability;
	}

//	public void increase(int pAmount) {
//		setQuantity(mQuantity + pAmount);
//	}
//
//	public void decrease(int pAmount) {
//		setQuantity(mQuantity - pAmount);
//	}

	public void setQuantity(Inventory pInventory, int pQuantity) {
		mQuantity = pQuantity;
		if (mQuantity >= MAX_QUANTITY) {
			mQuantity = MAX_QUANTITY;
//			this.onFull();
		} else if (pQuantity <= 0) {
			mQuantity = 0;
			this.onDepleted(pInventory);
		}
		setAvailability(pQuantity);
	}

	public void increase(Inventory pInventory, int pAmount) {
		setQuantity(pInventory, mQuantity + pAmount);
	}

	public void decrease(Inventory pInventory, int pAmount) {
		setQuantity(pInventory, mQuantity - pAmount);
	}

	public void incrementAvailability(int pAmount) {
		setAvailability(mAvailability + pAmount);
	}
	public void decrementAvailability(int pAmount) {
		setAvailability(mAvailability - pAmount);
	}

	public void setAvailability(int pAmount) {
		if (pAmount > mQuantity) {
			pAmount = mQuantity;
		}
		mAvailability = pAmount;
//		mAvailability = (mQuantity >= pAmount) ? pAmount : mQuantity;
		Debug.i("setAvailability: " + mAvailability);
	}

	protected void onFull() {
		
	}

	protected void onDepleted(Inventory pInventory) {
		pInventory.removeAll(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ItemBase another) {
		return this.getItemId() - another.getItemId();
	}

	public interface IConsumable {
		void onConsumed(BattleCharacter pUser, BattleCharacter pTarget);
	}

	public interface IEquipable {
		void onEquiped(Reaper pUser);
		void onRemoved(Reaper pUser);
	}
}

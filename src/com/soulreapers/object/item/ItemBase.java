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
	private final int mItemID;
	private final ItemType mItemType;
	private final String mName;
	private final String mDescription;
	private int mQuantity = 1;
	private int mAvailability = 1;

	protected Effect mEffect = null;

	public ItemBase(final int pItemID,
			final ItemType pItemType,
			final String pName,
			final String pDescription) {
		mItemID = pItemID;
		mItemType = pItemType;
		mName = pName;
		mDescription = pDescription;
	}

//	public static final ItemBase EMPTY = new ItemBase(0, null, "EMPTY", "", 0);

	public int getItemID() {
		return mItemID;
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

//	private void setQuantity(int pQuantity) {
//		mQuantity = pQuantity;
//		if (mQuantity > MAX_QUANTITY) {
//			mQuantity = MAX_QUANTITY;
//		} else if (pQuantity < 0) {
//			mQuantity = 0;
//		}
//	}

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

	private void setQuantity(int pQuantity) {
		mQuantity = pQuantity;
		if (mQuantity >= MAX_QUANTITY) {
			mQuantity = MAX_QUANTITY;
			this.onFull();
		} else if (pQuantity <= 0) {
			mQuantity = 0;
			this.onDepleted();
		}
		Debug.d(mName + " has " + mQuantity + " copies.");
	}

	public void increaseQuantity(int pAmount) {
		setQuantity(mQuantity + pAmount);
		incrementAvailability(pAmount);
	}

	public void decreaseQuantity(int pAmount) {
		setQuantity(mQuantity - pAmount);
		decrementAvailability(pAmount);
	}

	public void incrementAvailability(int pAmount) {
		setAvailability(mAvailability + pAmount);
	}
	public void decrementAvailability(int pAmount) {
		setAvailability(mAvailability - pAmount);
	}

	private void setAvailability(int pAmount) {
		mAvailability = pAmount;
		if (mAvailability > mQuantity) {
			mAvailability = mQuantity;
			Debug.d("Availability of an item cannot be higher than its quantity.");
		} else if (mAvailability < 0) {
			mAvailability = 0;
			Debug.d("Availability of an item cannot be negative.");
		}
		Debug.i("setAvailability: " + mAvailability);
	}

	protected void onFull() {
		Debug.d(mName + " is full.");
	}

	protected void onDepleted() {
//		pInventory.removeAll(this);
		Debug.d(mName + "is depleted, proceed to remove it from Inventory.");
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ItemBase another) {
		return this.getItemID() - another.getItemID();
	}

	public interface IConsumable {
		void onConsumed(Reaper pUser, BattleCharacter pTarget);
	}

	public interface IEquipable {
		void onEquiped(Reaper pUser);
		void onRemoved(Reaper pUser);
	}
}

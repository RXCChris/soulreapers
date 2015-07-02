/**
 * 
 */
package com.soulreapers.object.character.reaper;

import java.util.ArrayList;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.item.Accessory;
import com.soulreapers.object.item.CombatItem;
import com.soulreapers.object.item.Weapon;

/**
 * @author chris
 *
 */
public class Equipment {
	private Weapon mWeapon = null;
	private ArrayList<Accessory> mAccessoryList =
			new ArrayList<Accessory>(GameConstants.REAPER.ACCESSORY_CAPACITY);
	private ArrayList<CombatItem> mCombatItemList =
			new ArrayList<CombatItem>(GameConstants.REAPER.COMBAT_ITEM_CAPACITY);

	/**
	 * 
	 */
	public Equipment() {
//		for (int i = 0; i < GameConstants.REAPER.ACCESSORY_CAPACITY; ++i) {
//			mAccessoryList.add(Accessory.EMPTY);
//		}
//		for (int i = 0; i < GameConstants.REAPER.COMBAT_ITEM_CAPACITY; ++i) {
//			mCombatItemList.add(CombatItem.EMPTY);
//		}
	}

	public boolean equipWeapon(final Reaper pReaper, final Weapon pWeapon) {
		if (pWeapon.getAvailability() >= 1) {
			mWeapon.onRemoved(pReaper);
			mWeapon = pWeapon;
			mWeapon.onEquiped(pReaper);
			return true;
		}
		return false;
	}

//	public Weapon equipeWeapon(Reaper pReaper, Weapon pWeapon) {
//		final Weapon removed = mWeapon;
//		mWeapon.onRemoved(pReaper);
//		mWeapon = pWeapon;
//		mWeapon.onEquiped(pReaper);
//		return removed;
//	}
//
//	public Weapon removeWeapon(Reaper pReaper) {
//		final Weapon removed = mWeapon;
//		mWeapon.onRemoved(pReaper);
//		mWeapon = Weapon.EMPTY;
//		return removed;
//	}

	public Weapon getWeapon() {
		return mWeapon;
	}

	public boolean equipCombatItem(Reaper pReaper, int pIndex, CombatItem pItem) {
		if (pItem.getAvailability() >= 1) {
			mCombatItemList.set(pIndex, pItem).onRemoved(pReaper);
			pItem.onEquiped(pReaper);
			return true;
		}
		return false;
	}

	public CombatItem removeItem(Reaper pReaper, int pIndex) {
		CombatItem removed = mCombatItemList.set(pIndex, null);
		removed.onRemoved(pReaper);
		return removed;
	}

	public CombatItem getCombatItem(int pIndex) {
		return mCombatItemList.get(pIndex);
	}

	public void useCombatItem(int pIndex, Reaper pUser, BattleCharacter pTarget) {
		CombatItem item = removeItem(pUser, pIndex);
		item.onConsumed(pUser, pTarget);
	}

	public boolean equipAccessory(int pIndex, Reaper pReaper, Accessory pItem) {
		if (pItem.getAvailability() >= 1) {
			mAccessoryList.set(pIndex, pItem).onRemoved(pReaper);
			pItem.onEquiped(pReaper);
			return true;
		}
		return false;
	}

	public Accessory removeAccessory(Reaper pReaper, int pIndex) {
		Accessory removed = mAccessoryList.set(pIndex, null);
		removed.onRemoved(pReaper);
		return removed;
	}

	public Accessory getAccessory(int pIndex) {
		return mAccessoryList.get(pIndex);
	}
}

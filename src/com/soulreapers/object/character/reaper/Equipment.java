/**
 * 
 */
package com.soulreapers.object.character.reaper;

import java.util.ArrayList;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.item.CombatItem;
import com.soulreapers.object.item.Weapon;

/**
 * @author chris
 *
 */
public class Equipment {
	private Reaper mReaper;
	private Weapon mWeapon = Weapon.EMPTY;
	private ArrayList<CombatItem> mCombatItemList = new ArrayList<CombatItem>();

	/**
	 * 
	 */
	public Equipment(final Reaper pReaper) {
		mReaper = pReaper;
		for (int i = 0; i < GameConstants.REAPER.COMBAT_ITEM_CAPACITY; ++i) {
			mCombatItemList.add(CombatItem.EMPTY);
		}
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

	public boolean equipCombatItem(int pIndex, CombatItem pItem) {
		if (pItem.getAvailability() >= 1) {
			mCombatItemList.set(pIndex, pItem).onRemoved(mReaper);
			pItem.onEquiped(mReaper);
			return true;
		}
		return false;
	}

	public CombatItem removeItem(int pIndex) {
		CombatItem removed = mCombatItemList.set(pIndex, CombatItem.EMPTY);
		removed.onRemoved(mReaper);
		return removed;
	}

	public CombatItem getCombatItem(int pIndex) {
		return mCombatItemList.get(pIndex);
	}

	public void useCombatItem(int pIndex, BattleCharacter pUser, BattleCharacter pTarget) {
		CombatItem item = removeItem(pIndex);
		item.onConsumed(pUser, pTarget);
	}
}

/**
 * 
 */
package com.soulreapers.object.item;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.object.item.ItemBase.IConsumable;
import com.soulreapers.object.item.ItemBase.IEquipable;

/**
 * @author chris
 *
 */
public class CombatItem extends ItemBase implements IConsumable, IEquipable {
	/**
	 * @param pItemId
	 * @param pName
	 * @param pDescription
	 * @param pQuantity
	 */
	public CombatItem(int pItemID, String pName,
			String pDescription, int pQuantity) {
		super(pItemID, ItemType.COMBAT, pName, pDescription);
		// TODO
		// Implement factory which creates Effect class for the item.
		mEffect = new HealEffect(50);
	}

//	public static final CombatItem EMPTY = new CombatItem(0, GameConstants.STRING.EMPTY, "", 0);
//	public static final CombatItem REMOVE = new CombatItem(0, GameConstants.STRING.REMOVE, "", 0);

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.Item.IConsumable#onConsumed(com.soulreapers.object.character.Reaper, com.soulreapers.object.character.Reaper)
	 */
	@Override
	public void onConsumed(Reaper pUser, BattleCharacter pTarget) {
		mEffect.apply(pUser, pTarget);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.ItemBase.IEquipable#onEquiped(com.soulreapers.object.character.reaper.Reaper)
	 */
	@Override
	public void onEquiped(Reaper pUser) {
		this.decrementAvailability(1);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.ItemBase.IEquipable#onRemoved(com.soulreapers.object.character.reaper.Reaper)
	 */
	@Override
	public void onRemoved(Reaper pUser) {
		this.incrementAvailability(1);
	}


}

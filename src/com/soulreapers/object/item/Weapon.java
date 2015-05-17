/**
 * 
 */
package com.soulreapers.object.item;

import com.soulreapers.misc.Attributes.AttributeType;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.GameCharacter.CharacterType;
import com.soulreapers.object.character.Modifier;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.object.item.Effect.ValueType;
import com.soulreapers.object.item.ItemBase.IEquipable;

/**
 * @author chris
 *
 */
public class Weapon extends ItemBase implements IEquipable {
	private Modifier mModifier;

	/**
	 * @param pItemId
	 * @param pItemType
	 * @param pName
	 * @param pDescription
	 * @param pQuantity
	 */
	public Weapon(int pItemId, String pName,
			String pDescription, int pQuantity) {
		super(pItemId, ItemType.WEAPON, pName, pDescription, pQuantity);
		mEffect = new BuffEffect(0.1F, CharacterType.REAPER, AttributeType.COURAGE);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.ItemBase.IEquipable#onEquiped(com.soulreapers.object.character.reaper.Reaper)
	 */
	@Override
	public void onEquiped(Reaper pUser) {
		pUser.addEffect(mEffect);
	}

	public static final Weapon EMPTY = new Weapon(0, GameConstants.STRING.EMPTY, "", 0);

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.ItemBase.IEquipable#onRemoved(com.soulreapers.object.character.reaper.Reaper)
	 */
	@Override
	public void onRemoved(Reaper pUser) {
		// TODO Auto-generated method stub
		pUser.removeEffect(mEffect);
	}

}

/**
 * 
 */
package com.soulreapers.object.item;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.character.ParameterModifier;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.object.item.ItemBase.IEquipable;

/**
 * @author chris
 *
 */
public class Weapon extends ItemBase implements IEquipable {
	private final ParameterModifier mModifier;

	/**
	 * @param pItemId
	 * @param pItemType
	 * @param pName
	 * @param pDescription
	 * @param pQuantity
	 */
	public Weapon(int pItemID, String pName,
			String pDescription, int pPower) {
		super(pItemID, ItemType.WEAPON, pName, pDescription);
		mModifier = new ParameterModifier(AttributeType.COURAGE, pPower);
		Debug.d("Instanciate new Weapon " + pName + ".");
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.ItemBase.IEquipable#onEquiped(com.soulreapers.object.character.reaper.Reaper)
	 */
	@Override
	public void onEquiped(Reaper pReaper) {
		this.decrementAvailability(1);
		pReaper.addModifier(mModifier);
//		pUser.addEffect(mEffect);
	}

//	public static final Weapon EMPTY = new Weapon(0, ResourceManager.getInstance().getResourceString(R.string.empty), "", 0);

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.ItemBase.IEquipable#onRemoved(com.soulreapers.object.character.reaper.Reaper)
	 */
	@Override
	public void onRemoved(Reaper pReaper) {
		this.incrementAvailability(1);
//		pUser.removeEffect(mEffect);
		pReaper.removeModifier(mModifier);
	}

}

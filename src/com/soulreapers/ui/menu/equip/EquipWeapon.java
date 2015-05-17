/**
 * 
 */
package com.soulreapers.ui.menu.equip;

import org.andengine.util.debug.Debug;

import com.soulreapers.object.item.ItemBase;
import com.soulreapers.object.item.Weapon;
import com.soulreapers.ui.slot.SlotItem;
import com.soulreapers.ui.slot.SlotItemList;

/**
 * @author chris
 *
 */
public class EquipWeapon extends EquipOption<ItemBase> {
	private SlotItem<Weapon> mSlotWeapon;
	/**
	 * @param pMenuEquip
	 * @param pName
	 */
	public EquipWeapon(MenuEquip pMenuEquip, String pName) {
		super(pMenuEquip, pName);
		mSlotWeapon = new SlotItem<Weapon>(Weapon.EMPTY) {
			@Override
			protected void onSelected() {
				Debug.i("Display equipable weapons.");
			}
		};
		pMenuEquip.getLayout().attachChild(mSlotWeapon);
		mSlotWeapon.setQuantityVisible(false);
		this.setLayoutVisible(false);
	}
	@Override
	protected void onCreateEquippableList() {
//		mEquippableList = new SlotItemList(mMenuEquip.getLayout());
	}
	/* (non-Javadoc)
	 * @see com.soulreapers.ui.menu.equip.EquipOption#onSetReaper()
	 */
	@Override
	protected void onSetReaper() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLayoutVisible(boolean pVisible) {
		super.setLayoutVisible(pVisible);
		mSlotWeapon.setVisible(pVisible);
		if (pVisible) {
			mMenuEquip.getMenuScene().registerTouchArea(mSlotWeapon);
		} else {
			mSlotWeapon.unselect();
			mMenuEquip.getMenuScene().unregisterTouchArea(mSlotWeapon);
		}
	}
	/* (non-Javadoc)
	 * @see com.soulreapers.ui.menu.equip.EquipOption#setEquippedVisible(boolean)
	 */
//	@Override
//	protected void setEquippedVisible(boolean pVisible) {
//		// TODO Auto-generated method stub
//		
//	}
	/* (non-Javadoc)
	 * @see com.soulreapers.ui.menu.equip.EquipOption#onCreateEquippedList()
	 */
	@Override
	protected void onCreateEquippedList() {
		// TODO Auto-generated method stub
		
	}
//	/* (non-Javadoc)
//	 * @see com.soulreapers.ui.menu.equip.EquipOption#equip(java.lang.Object)
//	 */
//	@Override
//	protected void equip(ItemBase pNewItem) {
//		// TODO Auto-generated method stub
//		
//	}

}

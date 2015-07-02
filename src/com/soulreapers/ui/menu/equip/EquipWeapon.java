/**
 * 
 */
package com.soulreapers.ui.menu.equip;

import java.util.ArrayList;

import org.andengine.util.debug.Debug;

import com.soulreapers.core.DataManager;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.item.CombatItem;
import com.soulreapers.object.item.Inventory;
import com.soulreapers.object.item.ItemBase;
import com.soulreapers.object.item.ItemBase.ItemType;
import com.soulreapers.object.item.Weapon;
import com.soulreapers.ui.slot.SlotItem;
import com.soulreapers.ui.slot.SlotItemList;

/**
 * @author chris
 *
 */
public class EquipWeapon extends EquipOption<Weapon> {
//	private SlotItem<Weapon> mSlotWeapon;
	/**
	 * @param pMenuEquip
	 * @param pName
	 */
	public EquipWeapon(MenuEquip pMenuEquip, String pName) {
		super(pMenuEquip, pName);
//		mSlotWeapon = new SlotItem<Weapon>(Weapon.EMPTY) {
//			@Override
//			protected void onSelected() {
//				Debug.i("Display equipable weapons.");
//			}
//		};
//		pMenuEquip.getLayout().attachChild(mSlotWeapon);
//		mSlotWeapon.setQuantityVisible(false);
//		this.setLayoutVisible(false);
	}
	@Override
	protected void onCreateEquippableList() {
//		mEquippableList = new SlotItemList(mMenuEquip.getLayout());
		mEquippableList = new SlotItemList<Weapon>(mMenuEquip.getLayout()) {
			@Override
			protected void onSlotSelected() {
//				int index = mEquippedList.getSelectedSlot().getIndex();

//				if (this.getSelectedSlot().getIndex() == 0) {
//					EquipWeapon.this.mReaper.getEquipment().removeWeapon();
//				} else {
				Weapon newItem = this.getSelectedSlot().getElement();
				if (!EquipWeapon.this.mReaper.getEquipment().equipWeapon(mReaper, newItem)) {
					return;
				}
//				}
				EquipWeapon.this.onSetReaper();
				EquipWeapon.this.setEquippableListVisible(false);
				EquipWeapon.this.setEquippedListVisible(true);
				mEquippableList.updateList();
				mEquippedList.getSelectedSlot().select();
			}
		};
//		ArrayList<ItemBase> inventory = DataManager.getInstance().getInventory().getItemList(ItemType.WEAPON);
//		for (int i = 0; i < inventory.size(); ++i) {
//			mEquippableList.addSlot((Weapon) inventory.get(i));
//		}
		mMenuEquip.getLayout().attachChild(mEquippableList);
	}
	/* (non-Javadoc)
	 * @see com.soulreapers.ui.menu.equip.EquipOption#onSetReaper()
	 */
	@Override
	protected void onSetReaper() {
		mEquippedList.modifyContent(0, mReaper.getEquipment().getWeapon());
//		for (int i = 0; i < GameConstants.REAPER.COMBAT_ITEM_CAPACITY; ++i) {
//			mEquippedList.modifyContent(i, mReaper.getEquipment().getCombatItem(i));
//		}
	}

//	@Override
//	public void setLayoutVisible(boolean pVisible) {
//		super.setLayoutVisible(pVisible);
//		mSlotWeapon.setVisible(pVisible);
//		if (pVisible) {
//			mMenuEquip.getMenuScene().registerTouchArea(mSlotWeapon);
//		} else {
//			mSlotWeapon.unselect();
//			mMenuEquip.getMenuScene().unregisterTouchArea(mSlotWeapon);
//		}
//	}
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
		final SlotItemList<Weapon> list =
				new SlotItemList<Weapon>(mMenuEquip.getLayout()) {
			@Override
			protected void onSlotSelected() {
				EquipWeapon.this.setEquippableListVisible(true);
				EquipWeapon.this.setEquippedListVisible(false);
			}
		};

		list.addSlot(null);
		list.setQuantityVisible(false);
		mEquippedList = list;
		mMenuEquip.getLayout().attachChild(mEquippedList);
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

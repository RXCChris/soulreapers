/**
 * 
 */
package com.soulreapers.ui.menu.equip;

import java.util.ArrayList;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.item.CombatItem;
import com.soulreapers.object.item.Inventory;
import com.soulreapers.object.item.ItemBase;
import com.soulreapers.object.item.ItemBase.ItemType;
import com.soulreapers.ui.slot.SlotItemList;

/**
 * @author chris
 *
 */
public class EquipItem extends EquipOption<CombatItem> {
	/**
	 * @param pMenuEquip
	 */
	public EquipItem(MenuEquip pMenuEquip, String pName) {
		super(pMenuEquip, pName);
	}

	@Override
	protected void onCreateEquippableList() {
//		final ArrayList<ItemBase> inventory =
//				mMenuEquip.getMenuScene().getInventory().getItemList(ItemType.COMBAT);
		final ArrayList<ItemBase> inventory = Inventory.getInstance().getItemList(ItemType.COMBAT);
//				mMenuEquip.getMenuScene().getInventory().getItemList(ItemType.COMBAT);
		mEquippableList = new SlotItemList<CombatItem>(mMenuEquip.getLayout()) {
			@Override
			protected void onSlotSelected() {
//				CombatItem newItem = this.getSelectedSlot().getElement();
				int index = mEquippedList.getSelectedSlot().getIndex();

				if (this.getSelectedSlot().getIndex() == 0) {
					EquipItem.this.mReaper.getEquipment().removeItem(index);
				} else {
					CombatItem newItem = this.getSelectedSlot().getElement();
					if (!EquipItem.this.mReaper.getEquipment().equipCombatItem(index, newItem)) {
						return;
//						EquipItem.this.onSetReaper();
//						EquipItem.this.setEquippableListVisible(false);
//						EquipItem.this.setEquippedListVisible(true);
//						mEquippableList.updateList();
//						mEquippedList.getSelectedSlot().select();
					}
				}
				EquipItem.this.onSetReaper();
				EquipItem.this.setEquippableListVisible(false);
				EquipItem.this.setEquippedListVisible(true);
				mEquippableList.updateList();
				mEquippedList.getSelectedSlot().select();
//				Debug.d("Selected equippable: " + newItem.getAvailability());

//				if (EquipItem.this.mReaper.getEquipment().equipCombatItem(index, newItem)) {
//					EquipItem.this.onSetReaper();
//					EquipItem.this.setEquippableListVisible(false);
//					EquipItem.this.setEquippedListVisible(true);
//					mEquippableList.updateList();
//					mEquippedList.getSelectedSlot().select();
//				} else {
//					Debug.i("not enough");
//				}
			}
		};

		mEquippableList.addSlot(CombatItem.REMOVE);
		for (int i = 0; i < inventory.size(); ++i) {
			mEquippableList.addSlot((CombatItem) inventory.get(i));
		}
		mMenuEquip.getLayout().attachChild(mEquippableList);
	}

	@Override
	protected void onCreateEquippedList() {
		final SlotItemList<CombatItem> list =
				new SlotItemList<CombatItem>(mMenuEquip.getLayout()) {
			@Override
			protected void onSlotSelected() {
				Debug.i("Display equipable items.");
				EquipItem.this.setEquippableListVisible(true);
				EquipItem.this.setEquippedListVisible(false);
			}
		};

		for (int i = 0; i < GameConstants.REAPER.COMBAT_ITEM_CAPACITY; ++i) {
			list.addSlot(CombatItem.EMPTY);
		}
		list.setQuantityVisible(false);
		mEquippedList = list;
		mMenuEquip.getLayout().attachChild(mEquippedList);
	}

	@Override
	protected void onSetReaper() {
		for (int i = 0; i < GameConstants.REAPER.COMBAT_ITEM_CAPACITY; ++i) {
			mEquippedList.modifyContent(i, mReaper.getEquipment().getCombatItem(i));
		}
	}
}

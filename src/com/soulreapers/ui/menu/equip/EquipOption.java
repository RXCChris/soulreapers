/**
 * 
 */
package com.soulreapers.ui.menu.equip;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.ui.slot.Slot;
import com.soulreapers.ui.slot.SlotList;
import com.soulreapers.util.SRButton;

/**
 * @author chris
 *
 */
public abstract class EquipOption<T> {
	protected MenuEquip mMenuEquip;
	protected Reaper mReaper;
	protected final String mName;

	private SRButton mOptionButton;
	private static int BUTTON_PADDING_X = 0;

	public EquipOption(final MenuEquip pMenuEquip, final String pName) {
		mMenuEquip = pMenuEquip;
		mName = pName;
		mOptionButton = new SRButton(GameConstants.UI.MENU_OPTION_OFFSET_X,
				GameConstants.UI.MENU_OPTION_OFFSET_Y + GameConstants.UI.MENU_OPTION_PADDING_Y * (BUTTON_PADDING_X++),
				pName) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				mMenuEquip.setCurrentOption(EquipOption.this);
				mMenuEquip.setAllReaperSlotVisible(true);
//				EquipOption.this.setButtonVisible(false);
				mMenuEquip.setAllOptionButtonVisible(false);
			}
		};
		mMenuEquip.attachChild(mOptionButton);
		mMenuEquip.getMenuScene().registerTouchArea(mOptionButton);
		this.setButtonVisible(false);
		///
		// Equipable
		///
		this.onCreateEquippedList();
		this.onCreateEquippableList();
		this.setLayoutVisible(false);
	}

	public final void setButtonVisible(boolean pVisible) {
		mOptionButton.setVisible(pVisible);
		mOptionButton.setEnabled(pVisible);
	}

	public void setLayoutVisible(boolean pVisible) {
		mMenuEquip.getLayout().setVisible(pVisible);
		mMenuEquip.getLayout().setSubMenuName(mName);
		mMenuEquip.getLayout().setFirstColumnName(GameConstants.STRING.NAME);
		mMenuEquip.getLayout().setSecondColumnName("");

		this.setEquippedListVisible(pVisible);
		this.setEquippableListVisible(false);
	}
	
	public void setReaper(final Reaper pReaper) {
		mReaper = pReaper;
		onSetReaper();
	}

	protected abstract void onSetReaper();
//	protected abstract void setEquippedVisible(boolean pVisible);

	/////////
	// Equipable
	/////////
//	protected SubMenuLayout mEquipableLayout = new SubMenuLayout();
	protected SlotList<T, ? extends Slot<T>> mEquippableList;
	protected SlotList<T, ? extends Slot<T>> mEquippedList;

	protected abstract void onCreateEquippableList();
	protected abstract void onCreateEquippedList();

	public void setEquippableListVisible(boolean pVisible) {
		if (pVisible) {
			mMenuEquip.getLayout().setSubMenuName("Equippable");
			mMenuEquip.getLayout().setSecondColumnName(GameConstants.STRING.TOTAL);
		}
//		mEquippableList.setVisible(pVisible);
//		if (pVisible) {
//			mEquippableList.enableTouchArea(mMenuEquip.getMenuScene());
//		} else {
//			mEquippableList.unselectAll();
//			mEquippableList.disableTouchArea(mMenuEquip.getMenuScene());
//		}
		this.setListVisible(mEquippableList, pVisible);
	}

	public void setEquippedListVisible(boolean pVisible) {
		if (pVisible) {
			mMenuEquip.getLayout().setSubMenuName(mName);
			mMenuEquip.getLayout().setSecondColumnName("");
		}
		this.setListVisible(mEquippedList, pVisible);
	}

	private void setListVisible(SlotList<T, ? extends Slot<T>> pList, boolean pVisible) {
		pList.setVisible(pVisible);
		if (pVisible) {
			pList.enableTouchArea(mMenuEquip.getMenuScene());
		} else {
			pList.unselectAll();
			pList.disableTouchArea(mMenuEquip.getMenuScene());
		}
	}

//	protected abstract void equip(T pNewItem);
//		Debug.i("EquipOption -  equip: Modify Equipment");
//		pNewItem.
//		// TODO Implement method
//	}
}

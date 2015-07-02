/**
 * 
 */
package com.soulreapers.ui.menu;

import java.util.ArrayList;
import java.util.HashMap;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.item.Inventory;
import com.soulreapers.object.item.ItemBase;
import com.soulreapers.object.item.ItemBase.ItemType;
import com.soulreapers.scene.GameMenuScene;
import com.soulreapers.ui.slot.SlotItemList;
import com.soulreapers.util.SRButton;
//import com.soulreapers.ui.ItemUI;

/**
 * @author chris
 *
 */
public class MenuInventory extends MenuUI {
	private HashMap<ItemType, SubMenuItem> mItemMap = new HashMap<ItemType, SubMenuItem>();
	private SubMenuLayout mSubMenuLayout = new SubMenuLayout();

	public MenuInventory(GameMenuScene pScene) {
		super(pScene);

		int padding = 0;
		for (ItemType type : ItemType.values()) {
			mItemMap.put(type, new SubMenuItem(type.toString(), padding++));
		}

		mSubMenuLayout.setFirstColumnName(GameConstants.STRING.NAME);
		mSubMenuLayout.setSecondColumnName(GameConstants.STRING.TOTAL);

//		Inventory inventory = mMenuScene.getInventory();
		for (ItemType type : ItemType.values()) {
//			ArrayList<ItemBase> list = inventory.getItemList(type);
//			ArrayList<ItemBase> list = Inventory.getInstance().getItemList(type);
//			for (int i = 0; i < list.size(); ++i) {
//				Debug.d("Inventory item added >> [type:" + type.toString() + ",index:" + i + "]");
//				ItemBase item = list.get(i);
//				mItemMap.get(type).mSlotList.addSlot(item);
//			}
		}
	}


	@Override
	public void setUiEnabled(boolean pEnabled) {
		super.setUiEnabled(pEnabled);
		for (SubMenuItem menuItem : mItemMap.values()) {
			menuItem.enableItemList(false);
			menuItem.enableButton(pEnabled);
		}
		mSubMenuLayout.setVisible(false);
		mSubMenuLayout.setInfoDescription("");
	}

	@Override
	public void onAttached() {
		for (SubMenuItem menu : mItemMap.values()) {
			this.attachChild(menu.mButton);
			mMenuScene.registerTouchArea(menu.mButton);
		}
		this.attachChild(mSubMenuLayout);
		super.onAttached();
	}

//	@Override
//	public void onDetached() {
//		this.detachChildren();
//		super.onDetached();
//	}

//	@Override
//	public void dispose() {
//		for (SubMenuItem menu : mItemMap.values()) {
//			menu.mButton.dispose();
//		}
//		mSubMenuLayout.dispose();
//		super.dispose();
//	}


//	public void setAllButtonVisible(boolean pVisible) {
//		for (SubMenuItem menu : mItemMap.values()) {
//			menu.mButton.setEnabled(pVisible);
//			menu.mButton.setVisible(pVisible);
//		}
//	}

//	public void setLayoutVisible(boolean pVisible) {
//		
//	}

	private class SubMenuItem {
		private SRButton mButton;
		private SlotItemList<ItemBase> mSlotList = new SlotItemList<ItemBase>(mSubMenuLayout);

		public SubMenuItem(final String pButtonText, int pPaddingY) {
			mButton = new SRButton(GameConstants.UI.MENU_OPTION_OFFSET_X,
					GameConstants.UI.MENU_OPTION_OFFSET_Y + GameConstants.UI.MENU_OPTION_PADDING_Y * pPaddingY,
					pButtonText) {
				@Override
				public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
					mMainState = false;
					setUiEnabled(false);

					mButtonBack.setEnabled(true);
					mButtonBack.setVisible(true);

					mSubMenuLayout.setVisible(true);
					mSubMenuLayout.setSubMenuName(pButtonText);
					SubMenuItem.this.enableItemList(true);
				}
			};

			mSubMenuLayout.attachChild(mSlotList);
		}

		public void enableItemList(boolean pEnabled) {
			mSlotList.setVisible(pEnabled);
			if (pEnabled) {
				mSlotList.updateList();
				mSlotList.enableTouchArea(mMenuScene);
			} else {
				mSlotList.unselectAll();
				mSlotList.disableTouchArea(mMenuScene);
			}
		}

		public void enableButton(boolean pEnabled) {
			mButton.setEnabled(pEnabled);
			mButton.setVisible(pEnabled);
		}
	}
}

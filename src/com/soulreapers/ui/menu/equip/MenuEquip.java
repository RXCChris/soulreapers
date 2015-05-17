/**
 * 
 */
package com.soulreapers.ui.menu.equip;

import java.util.ArrayList;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.scene.GameMenuScene;
import com.soulreapers.ui.menu.MenuUI;
import com.soulreapers.ui.menu.SubMenuLayout;
import com.soulreapers.ui.slot.SlotReaperList;

/**
 * @author chris
 *
 */
public class MenuEquip extends MenuUI {
	private SubMenuLayout mSubMenuLayout = new SubMenuLayout();
	private SlotReaperList mSlotReaperList;

	private ArrayList<EquipOption<?>> mEquipOptionList = new ArrayList<EquipOption<?>>();
	private EquipOption<?> mCurrentOption = null;

	public EquipOption<?> getCurrentOption() {
		return mCurrentOption;
	}

	public void setCurrentOption(final EquipOption<?> pEquipOption) {
		mMainState = false;
		mCurrentOption = pEquipOption;
	}

	public void setAllOptionButtonVisible(boolean pVisible) {
		for (EquipOption<?> option : mEquipOptionList) {
			option.setButtonVisible(pVisible);
		}
	}

	public void setAllReaperSlotVisible(boolean pVisible) {
		mSlotReaperList.setVisible(pVisible);
		if (pVisible) {
			mSlotReaperList.enableTouchArea(mMenuScene);
		} else {
			mSlotReaperList.unselectAll();
			mSlotReaperList.disableTouchArea(mMenuScene);
		}
	}

	protected void onBackPressed() {
		if (mCurrentOption == null) {
			mMainState = true;
		} else {
			mCurrentOption.setButtonVisible(true);
			mCurrentOption.setLayoutVisible(false);
			this.setAllReaperSlotVisible(false);
			mCurrentOption = null;
		}
	}

	/**
	 * @param pMenuScene
	 */
	public MenuEquip(GameMenuScene pMenuScene) {
		super(pMenuScene);

		mSlotReaperList = new SlotReaperList(mSubMenuLayout) {
			@Override
			protected void onSlotSelect() {
//				Debug.d("Show layout: " + MenuEquip.this.mCurrentOption);
				MenuEquip.this.mCurrentOption.setLayoutVisible(false);
				MenuEquip.this.mCurrentOption.setReaper(getSelectedSlot().getElement());
				MenuEquip.this.mCurrentOption.setLayoutVisible(true);
			}
			@Override
			protected void onSlotSelected() {
				onSlotSelect();
			}
		};
		ArrayList<Reaper> reaperList = mMenuScene.getReapers();
		for (int i = 0; i < reaperList.size(); ++i) {
			Debug.d("Add Reaper: " + reaperList.get(i).getName());
			mSlotReaperList.addSlot(reaperList.get(i));
		}
		MenuEquip.this.attachChild(mSlotReaperList);
		this.setAllReaperSlotVisible(false);

//		mEquipOptionList.add(new EquipWeapon(MenuEquip.this, "Weapon"));
//		mEquipOptionList.add(new EquipWeapon(MenuEquip.this, "Accessory"));
		mEquipOptionList.add(new EquipItem(MenuEquip.this, GameConstants.STRING.ITEM));

		MenuEquip.this.attachChild(mSubMenuLayout);
		mSubMenuLayout.setVisible(false);

//		mEquipableMenuLayout.setX(mEquipableMenuLayout.getX() + 10);
//		MenuEquip.this.attachChild(mEquipableMenuLayout);
//		mEquipableMenuLayout.setVisible(false);
	}

//	public SubMenuLayout getEquipableMenuLayout() {
//		return mEquipableMenuLayout;
//	}

	@Override
	public void setUiEnabled(boolean pEnabled) {
		Debug.d("set Enabled " + pEnabled);
		super.setUiEnabled(pEnabled);
		this.setAllOptionButtonVisible(pEnabled);
	}
	public SubMenuLayout getLayout() {
		return mSubMenuLayout;
	}
}

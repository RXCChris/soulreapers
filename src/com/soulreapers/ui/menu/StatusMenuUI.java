/**
 * 
 */
package com.soulreapers.ui.menu;

import java.util.ArrayList;
import java.util.HashMap;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.CharacterParameters;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.scene.GameMenuScene;
import com.soulreapers.ui.AttributesUI;
import com.soulreapers.ui.menu.equip.MenuEquip;
import com.soulreapers.ui.slot.SlotAttributes;
import com.soulreapers.ui.slot.SlotReaperList;

import com.soulreapers.util.SRButton;

/**
 * @author chris
 *
 */
public class StatusMenuUI extends MenuUI {
//	private SubMenuLayout mBackground = new SubMenuLayout();
//	private AttributesUI mStatsUI = new AttributesUI();
//	private Attributes mStatus = new Attributes();
//	private static final String STRING_PROFILE = "Profile";

	private SlotAttributes mSlotAttributes;

	private SlotReaperList mSlotReaperList;

//	private enum StatusOptions {
//		PROFILE {
//			@Override
//			public String toString() {
//				return STRING_PROFILE;
//			}
//		}
//	}

//	private HashMap<StatusOptions, SRButton> mStatusOptionButtons = new HashMap<StatusOptions, SRButton>();

	/**
	 * @param pMenuScene
	 */
	public StatusMenuUI(GameMenuScene pMenuScene) {
		super(pMenuScene);
//		mStatsUI.setStatus(mStatus);

//		mStatusOptionButtons.put(StatusOptions.PROFILE,
//				new SRButton(GameConstants.UI.MENU_OPTION_OFFSET_X,
//						GameConstants.UI.MENU_OPTION_OFFSET_Y + GameConstants.UI.MENU_OPTION_PADDING_Y * 0,
//				StatusOptions.PROFILE.toString()) {
//			@Override
//			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
//				mMainState = false;
//				StatusMenuUI.this.setUiEnabled(true);
//
//				Debug.d("Pressed " + StatusOptions.PROFILE.toString());
//				mStatus.increaseCurrent(AttributeType.EXPERIENCE, 110);
//				mStatus.setBase(AttributeType.TEMPERANCE, 20);
//				mStatus.setBonus(AttributeType.JUSTICE, 10);
//				mStatsUI.update();
//
//
//				mButtonBack.setEnabled(true);
//				mButtonBack.setVisible(true);
//				mBackground.setVisible(false);
//			}
//		});
		//-------
		mSlotReaperList = new SlotReaperList(null) {
			@Override
			protected void onSlotSelect() {
				mSlotAttributes.setElement(this.getSelectedSlot().getElement().getParameters());
				mSlotAttributes.setVisible(true);
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
		StatusMenuUI.this.attachChild(mSlotReaperList);
		this.setAllReaperSlotVisible(false);

		mSlotAttributes = new SlotAttributes(CharacterParameters.DEFAULT);
		this.attachChild(mSlotAttributes);
		mSlotAttributes.setVisible(false);
	}

	@Override
	public void setUiEnabled(boolean pEnabled) {
		super.setUiEnabled(pEnabled);
//		mStatsUI.setVisible(pEnabled);
//		for (SRButton button : mStatusOptionButtons.values()) {
//			button.setEnabled(pEnabled);
//			button.setVisible(pEnabled);
//		}
//		mBackground.setVisible(false);
		this.setAllReaperSlotVisible(pEnabled);
	}

//	@Override
//	public void onAttached() {
//		this.attachChild(mStatsUI);
//		for (SRButton button : mStatusOptionButtons.values()) {
//			this.attachChild(button);
//			mMenuScene.registerTouchArea(button);
//		}
//		super.onAttached();
//	}

//	@Override
//	public void onDetached() {
//		mStatsUI.detachSelf();
//		for (SRButton button : mStatusOptionButtons.values()) {
//			this.detachChild(button);
//			mMenuScene.unregisterTouchArea(button);
//		}
//		super.onDetached();
//	}
//
//	@Override
//	public void dispose() {
//		mStatsUI.dispose();
//		for (SRButton button : mStatusOptionButtons.values()) {
//			button.dispose();
//		}
//		super.dispose();
//	}

	//---
	public void setAllReaperSlotVisible(boolean pVisible) {
		mSlotReaperList.setVisible(pVisible);
		if (pVisible) {
			mSlotReaperList.enableTouchArea(mMenuScene);
		} else {
			mSlotReaperList.unselectAll();
			mSlotReaperList.disableTouchArea(mMenuScene);
		}
	}
}

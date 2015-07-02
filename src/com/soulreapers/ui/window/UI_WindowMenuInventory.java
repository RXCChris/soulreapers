/**
 * 
 */
package com.soulreapers.ui.window;

import org.andengine.util.debug.Debug;

import com.soulreapers.scene.UI_Scene;
import com.soulreapers.scene.SceneMenu;
import com.soulreapers.ui.UI_Button;

/**
 * @author chris
 *
 */
public class UI_WindowMenuInventory extends UI_WindowMenu {
	/**
	 * @param pScene
	 */
	public UI_WindowMenuInventory(SceneMenu pScene) {
		super(pScene, "Inventory");
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_WindowMenu#onCreateButtons(com.soulreapers.scene.SceneMenu)
	 */
	@Override
	protected void onCreateButtons(final UI_Scene pScene) {
		final UI_Button buttonCombat = new UI_Button("Combat") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Inventory:Combat");
			}
		};
		final UI_Button buttonWeapon = new UI_Button("Weapon") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Inventory:Weapon");
			}
		};
		final UI_Button buttonAcc = new UI_Button("Accessory") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Inventory:Accessory");
			}
		};
		final UI_Button buttonSkill = new UI_Button("Skill") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Inventory:Skill");
			}
		};
		final UI_Button buttonLoot = new UI_Button("Loot") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Inventory:Loot");
			}
		};
		final UI_Button buttonKey = new UI_Button("Key Item") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Inventory:Key Item");
			}
		};
		mPanelButtons.add(buttonCombat);
		mPanelButtons.add(buttonWeapon);
		mPanelButtons.add(buttonAcc);
		mPanelButtons.add(buttonSkill);
		mPanelButtons.add(buttonLoot);
		mPanelButtons.add(buttonKey);
	}

}

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
public class UI_WindowMenuEquip extends UI_WindowMenu {

	/**
	 * @param pScene
	 * @param pLabel
	 */
	public UI_WindowMenuEquip(SceneMenu pScene) {
		super(pScene, "Equip");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_WindowMenu#onCreateButtons(com.soulreapers.scene.SceneMenu)
	 */
	@Override
	protected void onCreateButtons(final UI_Scene pScene) {
		final UI_Button buttonWeapon = new UI_Button("Weapon") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Equip:Weapon");
			}
		};
		final UI_Button buttonAcc = new UI_Button("Accessory") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Equip:Accessory");
			}
		};
		final UI_Button buttonItem = new UI_Button("Item") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Equip:Item");
			}
		};
		final UI_Button buttonAttack = new UI_Button("Attack") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Equip:Attack");
			}
		};
		final UI_Button buttonDefense = new UI_Button("Defense") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Equip:Defense");
			}
		};
		final UI_Button buttonSupport = new UI_Button("Support") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Equip:Support");
			}
		};
		mPanelButtons.add(buttonWeapon);
		mPanelButtons.add(buttonAcc);
		mPanelButtons.add(buttonItem);
		mPanelButtons.add(buttonAttack);
		mPanelButtons.add(buttonDefense);
		mPanelButtons.add(buttonSupport);
	}

}

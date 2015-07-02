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
public class UI_WindowMenuTactic extends UI_WindowMenu {

	/**
	 * @param pScene
	 * @param pLabel
	 */
	public UI_WindowMenuTactic(final SceneMenu pScene) {
		super(pScene, "Tactic");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_WindowMenu#onCreateButtons(com.soulreapers.scene.SceneMenu)
	 */
	@Override
	protected void onCreateButtons(final UI_Scene pScene) {
		final UI_Button buttonMembers = new UI_Button("Members") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Tactic:Members");
			}
		};
		final UI_Button buttonFormation = new UI_Button("Formation") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Tactic:Formation");
			}
		};
		mPanelButtons.add(buttonMembers);
		mPanelButtons.add(buttonFormation);
	}

}

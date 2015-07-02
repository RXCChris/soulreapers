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
public class UI_WindowMenuReport extends UI_WindowMenu {

	/**
	 * @param pScene
	 * @param pLabel
	 */
	public UI_WindowMenuReport(SceneMenu pScene) {
		super(pScene, "Report");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_WindowMenu#onCreateButtons(com.soulreapers.scene.SceneMenu)
	 */
	@Override
	protected void onCreateButtons(final UI_Scene pScene) {
		final UI_Button buttonMissions = new UI_Button("Missions") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Report:Missions");
			}
		};
		final UI_Button buttonBestiary = new UI_Button("Formation") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Report:Bestiary");
			}
		};
		final UI_Button buttonHelp = new UI_Button("Help") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed Report:Help");
			}
		};
		mPanelButtons.add(buttonMissions);
		mPanelButtons.add(buttonBestiary);
		mPanelButtons.add(buttonHelp);
	}

}

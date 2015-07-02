/**
 * 
 */
package com.soulreapers.ui.window;

import org.andengine.util.debug.Debug;

import com.soulreapers.core.SceneManager;
import com.soulreapers.scene.UI_Scene;
import com.soulreapers.scene.SceneMenu;
import com.soulreapers.scene.SceneTitle;
import com.soulreapers.ui.UI_Button;

/**
 * @author chris
 *
 */
public class UI_WindowMenuSystem extends UI_WindowMenu {
	/**
	 * 
	 */
	public UI_WindowMenuSystem(final SceneMenu pScene) {
		super(pScene, "System");
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_WindowMenu#onCreateButtons(com.soulreapers.scene.SceneMenu)
	 */
	@Override
	protected void onCreateButtons(final UI_Scene pScene) {
		final UI_Button buttonSave = new UI_Button("Save") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed System:Save");
			}
		};
		final UI_Button buttonLoad = new UI_Button("Load") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed System:Load");
			}
		};
		final UI_Button buttonTitle = new UI_Button("Title") {
			@Override
			protected void onPressed() {
				Debug.d("Pressed System:Title");
				SceneManager.getInstance().showScene(SceneTitle.class);
			}
		};
		mPanelButtons.add(buttonSave);
		mPanelButtons.add(buttonLoad);
		mPanelButtons.add(buttonTitle);
	}

}

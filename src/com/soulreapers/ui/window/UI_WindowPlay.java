/**
 * 
 */
package com.soulreapers.ui.window;

import org.andengine.util.debug.Debug;

import com.soulreapers.core.SceneManager;
import com.soulreapers.scene.SceneMenu;
import com.soulreapers.scene.SceneTitle;
import com.soulreapers.scene.UI_Scene;
import com.soulreapers.ui.UI_Button;

/**
 * @author chris
 *
 */
public class UI_WindowPlay extends UI_WindowMenu {
	
	public UI_WindowPlay(final SceneTitle pScene) {
		super(pScene, "");

	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_WindowMenu#onCreateButtons(com.soulreapers.scene.BaseScene)
	 */
	@Override
	protected void onCreateButtons(UI_Scene pScene) {
		final UI_Button buttonNewGame = new UI_Button("New Game") {
			@Override
			protected void onPressed() {
				Debug.d("New Game");
				SceneManager.getInstance().showScene(SceneMenu.class);
			}
		};
		final UI_Button buttonContinue = new UI_Button("Continue") {
			@Override
			protected void onPressed() {
				Debug.d("Continue");
			}
		};
		mPanelButtons.add(buttonNewGame);
		mPanelButtons.add(buttonContinue);
	}
}

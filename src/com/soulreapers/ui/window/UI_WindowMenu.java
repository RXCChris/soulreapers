/**
 * 
 */
package com.soulreapers.ui.window;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.scene.UI_Scene;
import com.soulreapers.ui.UI_Button;
import com.soulreapers.ui.UI_PanelButtons;

/**
 * @author chris
 *
 */
public abstract class UI_WindowMenu extends UI_Window {
	protected UI_Button mButtonBack;
	protected UI_PanelButtons mPanelButtons = new UI_PanelButtons(
					GameConstants.UI.MENU_OPTION_OFFSET_X,
					GameConstants.UI.MENU_OPTION_OFFSET_Y);

	public UI_WindowMenu(final UI_Scene pScene, final String pLabel) {
		super(pLabel);
		mButtonBack = new UI_Button("Back",
				GameConstants.UI.MENU_OPTION_OFFSET_X,
				GameConstants.UI.MENU_OPTION_OFFSET_Y * 8) {
			@Override
			protected void onPressed() {
				UI_WindowMenu.this.disable();
				pScene.restore();
			}
		};

		this.onCreateButtons(pScene);
		this.attachChild(mPanelButtons);
		mPanelButtons.registerButtons(pScene);
		this.attachChild(mButtonBack);
		pScene.registerTouchArea(mButtonBack);
	}

	protected abstract void onCreateButtons(final UI_Scene pScene);

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_Window#onEnabled()
	 */
	@Override
	protected void onEnabled() {
		mPanelButtons.setButtonsEnabled(true);
		mButtonBack.setEnabled(true);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_Window#onDisabled()
	 */
	@Override
	protected void onDisabled() {
		mPanelButtons.setButtonsEnabled(false);
		mButtonBack.setEnabled(false);
	}

	public void setBackButtonEnabled(boolean pEnabled) {
		mButtonBack.setVisible(pEnabled);
		mButtonBack.setEnabled(pEnabled);
	}

	@Override
	protected void onDestroy() {
		mButtonBack.destroy();
		mPanelButtons.destroy();
		super.onDestroy();
	}
}

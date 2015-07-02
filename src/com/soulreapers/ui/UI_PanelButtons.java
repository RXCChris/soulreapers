/**
 * 
 */
package com.soulreapers.ui;

import com.soulreapers.scene.UI_Scene;

/**
 * @author chris
 *
 */
public class UI_PanelButtons extends UI_Panel<UI_Button> {
	public UI_PanelButtons() {
		super();
	}

	public UI_PanelButtons(final float pX, final float pY) {
		super(pX, pY);
	}

	public void registerButtons(final UI_Scene pScene) {
		for (UI_Button button : mList) {
			pScene.registerTouchArea(button);
		}
	}

	public void unregisterButtons(final UI_Scene pScene) {
		for (UI_Button button : mList) {
			pScene.unregisterTouchArea(button);
		}
	}

	public void setButtonsEnabled(boolean pEnabled) {
		for (UI_Button button : mList) {
			button.setEnabled(pEnabled);
		}
	}

	@Override
	public String toString() {
		return "PanelButtons";
	}
}

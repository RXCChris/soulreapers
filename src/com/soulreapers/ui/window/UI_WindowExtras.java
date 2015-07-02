/**
 * 
 */
package com.soulreapers.ui.window;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.scene.UI_Scene;
import com.soulreapers.scene.SceneTitle;
import com.soulreapers.ui.UI_Button;
import com.soulreapers.ui.UI_Panel;

/**
 * @author chris
 *
 */
public class UI_WindowExtras extends UI_WindowMenu {
	private static final float PANEL_BUTTON_OFFSET_X = 500;
	private static final float PANEL_BUTTON_OFFSET_Y = 300;

//	private UI_Panel<UI_Button> mPanelButtons = new UI_Panel<UI_Button>(PANEL_BUTTON_OFFSET_X, PANEL_BUTTON_OFFSET_Y);

	public UI_WindowExtras(final SceneTitle pScene) {
		super(pScene, "Extras");
//		UI_Button buttonBack = new UI_Button("Back") {
//			@Override
//			protected void onPressed() {
//				UI_WindowExtras.this.disable();
//				pScene.restore();
//			}
//		};
//		pScene.registerTouchArea(buttonBack);
//		mPanelButtons.add(buttonBack);
//		this.attachChild(mPanelButtons);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_WindowMenu#onCreateButtons(com.soulreapers.scene.BaseScene)
	 */
	@Override
	protected void onCreateButtons(UI_Scene pScene) {
		
	}
}

/**
 * 
 */
package com.soulreapers.ui.menu;

import org.andengine.util.debug.Debug;

import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.scene.BattleScene;
import com.soulreapers.scene.GameMenuScene;
import com.soulreapers.util.SRButton;

/**
 * @author chris
 *
 */
public class MapUI extends MenuUI {

	/**
	 * @param pMenuScene
	 */
	public MapUI(GameMenuScene pMenuScene) {
		super(pMenuScene);
		// TODO Auto-generated constructor stub

	}

	SRButton mButton = new SRButton(GameConstants.UI.MENU_OPTION_OFFSET_X,
			GameConstants.UI.MENU_OPTION_OFFSET_Y + GameConstants.UI.MENU_OPTION_PADDING_Y * 0,
			"Battle") {
		@Override
		public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
			mMainState = false;

			SceneManager.getInstance().showScene(BattleScene.class);
		}
	};

	@Override
	public void setUiEnabled(boolean pEnabled) {
		mButton.setVisible(pEnabled);
		mButton.setEnabled(pEnabled);
		super.setUiEnabled(pEnabled);
	}

	@Override
	public void onAttached() {
		this.attachChild(mButton);
		mMenuScene.registerTouchArea(mButton);
		super.onAttached();
	}

	@Override
	public void onDetached() {
		mMenuScene.unregisterTouchArea(mButton);
		mButton.detachSelf();
		super.onDetached();
	}

	@Override
	public void dispose() {
		mButton.dispose();
		super.dispose();
	}
}

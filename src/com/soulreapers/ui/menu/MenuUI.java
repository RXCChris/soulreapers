/**
 * 
 */
package com.soulreapers.ui.menu;

import org.andengine.entity.Entity;
import org.andengine.util.debug.Debug;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.scene.GameMenuScene;
import com.soulreapers.util.SRButton;

/**
 * @author dxcloud
 *
 */
public abstract class MenuUI extends Entity {
	protected GameMenuScene mMenuScene;
	protected boolean mLayoutEnabled = false;
	protected boolean mMainState = true;
	protected SRButton mButtonBack;

	public GameMenuScene getMenuScene() {
		return mMenuScene;
	}

	public MenuUI(GameMenuScene pMenuScene) {
		super();
		mMenuScene = pMenuScene;

		mButtonBack = new SRButton(GameConstants.UI.MENU_OPTION_OFFSET_X,
				GameConstants.UI.MENU_OPTION_OFFSET_Y + GameConstants.UI.MENU_OPTION_PADDING_Y * 7,
				GameConstants.STRING.BACK) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				MenuUI.this.onBackPressed();
				Debug.d("Bakc button pressed in main state? " +  mMainState);
				if (mMainState) {
					MenuUI.this.setUiEnabled(false);
					mMenuScene.backToMainMenu();
				} else {
					MenuUI.this.setUiEnabled(true);
					mMainState = true;
				}
//				mBackground.mTextInfoDescription.setText("");
			}
		};
		mMenuScene.registerTouchArea(mButtonBack);
	}

	protected void onBackPressed() {
		
	}

	public void setUiEnabled(boolean pEnabled) {
		Debug.d("Set Background " + pEnabled);
//		mBackground.setVisible((mLayoutEnabled && pEnabled));
		mButtonBack.setEnabled(pEnabled);
		mButtonBack.setVisible(pEnabled);
	}

	@Override
	public void onAttached() {
		super.onAttached();
//		this.attachChild(mBackground);
		this.attachChild(mButtonBack);
		setUiEnabled(false);
	}

	@Override
	public void onDetached() {
//		this.detachChild(mBackground);
		this.detachChild(mButtonBack);
		super.onDetached();
	}

	@Override
	public void dispose() {
//		mBackground.dispose();
		mButtonBack.dispose();
		super.dispose();
	}


}

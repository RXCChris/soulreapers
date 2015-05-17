/**
 * 
 */
package com.soulreapers.ui.menu;

import java.util.HashMap;

import org.andengine.util.debug.Debug;

import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.scene.ConfirmDialog;
import com.soulreapers.scene.GameMenuScene;
import com.soulreapers.scene.MainMenuScene;
import com.soulreapers.util.SRButton;

/**
 * @author chris
 *
 */
public class MenuDataUI extends MenuUI {
	private SubMenuLayout mBackground = new SubMenuLayout();
	private enum MenuDataOptions {
		SAVE {
			@Override
			public String toString() {
				return GameConstants.STRING.SAVE;
			}

			@Override
			public void execute(final MenuDataUI pDataUI) {
				pDataUI.mBackground.setVisible(true);
			}
		},
		LOAD {
			@Override
			public String toString() {
				return GameConstants.STRING.LOAD;
			}

			@Override
			public void execute(final MenuDataUI pDataUI) {
				pDataUI.mBackground.setVisible(true);
			}
		}
		,
		TITLE {
			@Override
			public String toString() {
				return GameConstants.STRING.TITLE;
			}

			@Override
			public void execute(final MenuDataUI pDataUI) {
				pDataUI.mMenuScene.setChildScene(
						new ConfirmDialog(GameConstants.STRING.CONFIRM_RETURN_TO_TITLE) {
					@Override
					protected void onPositive() {
						SceneManager.getInstance().showScene(MainMenuScene.class);
						super.onPositive();
					}
					@Override
					protected void onNegative() {
						pDataUI.mMainState = true;
						pDataUI.setUiEnabled(true);
						super.onNegative();
					}
				}, false, true, true);
			}
		};
		public abstract void execute(final MenuDataUI pDataUI);
	}

	private HashMap<MenuDataOptions, SRButton> mMenuDataOptionButtonMap =
			new HashMap<MenuDataOptions, SRButton>();
	/**
	 * @param pMenuScene
	 */
	public MenuDataUI(GameMenuScene pMenuScene) {
		super(pMenuScene);

		int padding = 0;
		for (final MenuDataOptions option : MenuDataOptions.values()) {
			final SRButton button = new SRButton(GameConstants.UI.MENU_OPTION_OFFSET_X,
					GameConstants.UI.MENU_OPTION_OFFSET_Y + GameConstants.UI.MENU_OPTION_PADDING_Y * (padding++),
					option.toString()) {
				@Override
				public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
					mMainState = false;
					MenuDataUI.this.setUiEnabled(false);
					MenuDataUI.this.mMenuDataOptionButtonMap.get(option).setVisible(true);

					Debug.d("Pressed " + option.toString());

					mButtonBack.setEnabled(true);
					mButtonBack.setVisible(true);
					option.execute(MenuDataUI.this);
				}
			};
			mMenuDataOptionButtonMap.put(option, button);
		}
	}

	@Override
	public void setUiEnabled(boolean pEnabled) {
		for (SRButton button : mMenuDataOptionButtonMap.values()) {
			button.setVisible(pEnabled);
			button.setEnabled(pEnabled);
		}
		super.setUiEnabled(pEnabled);
	}

	@Override
	public void onAttached() {
		for (SRButton button : mMenuDataOptionButtonMap.values()) {
			this.attachChild(button);
			mMenuScene.registerTouchArea(button);
		}
		super.onAttached();
	}

	@Override
	public void onDetached() {
		for (SRButton button : mMenuDataOptionButtonMap.values()) {
			mMenuScene.unregisterTouchArea(button);
			button.detachSelf();
		}
		super.onDetached();
	}

	@Override
	public void dispose() {
		for (SRButton button : mMenuDataOptionButtonMap.values()) {
			button.dispose();
		}
		mMenuDataOptionButtonMap.clear();
		super.dispose();
	}
}

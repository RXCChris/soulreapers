/**
 * 
 */
package com.soulreapers.scene;

import java.util.HashMap;

import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.DataManager;
import com.soulreapers.ui.UI_Button;
import com.soulreapers.ui.UI_Panel;
import com.soulreapers.ui.UI_Panel.Layout;
import com.soulreapers.ui.UI_Reaper;
import com.soulreapers.ui.UI_Sprite;
import com.soulreapers.ui.window.UI_Window;
import com.soulreapers.ui.window.UI_WindowMenu;
import com.soulreapers.ui.window.UI_WindowMenuEquip;
import com.soulreapers.ui.window.UI_WindowMenuInventory;
import com.soulreapers.ui.window.UI_WindowMenuMap;
import com.soulreapers.ui.window.UI_WindowMenuReport;
import com.soulreapers.ui.window.UI_WindowMenuStatus;
import com.soulreapers.ui.window.UI_WindowMenuSystem;
import com.soulreapers.ui.window.UI_WindowMenuTactic;

/**
 * @author chris
 *
 */
public class SceneMenu extends UI_Scene {
	private UI_Window mWindowMain;
	private UI_Sprite mBackground;
	private UI_Panel<UI_Reaper>	mPanelReaper = new UI_Panel<UI_Reaper>(10, 80, 10, 0, Layout.HORIZONTAL);
	private HashMap<MenuOptionType, UI_Window> mWindowMap = new HashMap<MenuOptionType, UI_Window>();

	private enum MenuOptionType {
		INVENTORY {
			@Override
			public UI_Window create(SceneMenu pScene) {
				return new UI_WindowMenuInventory(pScene);
			}
		},
		EQUIP {
			@Override
			public UI_Window create(SceneMenu pScene) {
				return new UI_WindowMenuEquip(pScene);
			}
		},
		STATUS {
			@Override
			public UI_Window create(SceneMenu pScene) {
				return new UI_WindowMenuStatus(pScene);
			}
		},
		TACTIC {
			@Override
			public UI_Window create(SceneMenu pScene) {
				return new UI_WindowMenuTactic(pScene);
			}
		},
		MAP {
			@Override
			public UI_Window create(SceneMenu pScene) {
				return new UI_WindowMenuMap(pScene);
			}
		},
		REPORT {
			@Override
			public UI_Window create(SceneMenu pScene) {
				return new UI_WindowMenuReport(pScene);
			}
		},
		SYSTEM {
			@Override
			public UI_Window create(SceneMenu pScene) {
				return new UI_WindowMenuSystem(pScene);
			}
		};
		public abstract UI_Window create(final SceneMenu pScene);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onLoadResources()
	 */
	@Override
	public void onLoadResources() {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onCreate()
	 */
	@Override
	public void onCreate() {
		mBackground = new UI_Sprite(R.string.bg_04);
		this.attachChild(mBackground);
		mWindowMain = new UI_WindowMain(this);

		for (MenuOptionType type : MenuOptionType.values()) {
			mWindowMap.put(type, type.create(this));
		}

		mPanelReaper.add(new UI_Reaper(DataManager.getInstance().getReaper(DataManager.REAPER_DANTE)));
		mPanelReaper.add(new UI_Reaper(DataManager.getInstance().getReaper(DataManager.REAPER_VERGIL)));
		mPanelReaper.add(new UI_Reaper(DataManager.getInstance().getReaper(DataManager.REAPER_BEATRIX)));

		this.attachChild(mWindowMain);
		mWindowMain.attachChild(mPanelReaper);

		for (UI_Window window : mWindowMap.values()) {
			this.attachChild(window);
		}

		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
		this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
		this.restore();
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onDestroyResources()
	 */
	@Override
	public void onDestroyResources() {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onDestroy()
	 */
	@Override
	public void onDestroy() {
		mBackground.destroy();
		mPanelReaper.destroy();
		for (UI_Window window : mWindowMap.values()) {
			window.destroy();
		}
		mWindowMap.clear();
		mWindowMain.destroy();
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onPause()
	 */
	@Override
	public void onPause() {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onResume()
	 */
	@Override
	public void onResume() {
		// nothing to do
	}

	@Override
	public void restore() {
		mWindowMain.enable();
	}

	private class UI_WindowMain extends UI_WindowMenu {
		public UI_WindowMain(final SceneMenu pScene) {
			super(pScene, "Main Menu");
			mButtonBack.setVisible(false);
			mButtonBack.setEnabled(false);
		}

		protected void onCreateButtons(final UI_Scene pScene) {
			for (final MenuOptionType type : MenuOptionType.values()) {
				final UI_Button button = new UI_Button(type.toString()) {
					@Override
					protected void onPressed() {
						Debug.d("Pressed " + type.toString());
						UI_WindowMain.this.disable();
						SceneMenu.this.mWindowMap.get(type).enable();
					}
				};
				mPanelButtons.add(button);
			}
		}

		@Override
		public String toString() {
			return "UI_WindowMain";
		}
	}

	@Override
	public String toString() {
		return "SceneMenu";
	}
}

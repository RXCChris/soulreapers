package com.soulreapers.scene;

import java.util.HashMap;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.ui.UI_Button;
import com.soulreapers.ui.UI_Sprite;
import com.soulreapers.ui.window.UI_Window;
import com.soulreapers.ui.window.UI_WindowExtras;
import com.soulreapers.ui.window.UI_WindowMenu;
import com.soulreapers.ui.window.UI_WindowOptions;
import com.soulreapers.ui.window.UI_WindowPlay;

/**
 * This class represents main title menu.
 * <p>
 * This menu contains a horizontally scrolling background, and also 2 buttons.
 * </p>
 * <p>
 * An Ester egg has been added to this menu. Try to find out. ^^
 * </p>
 *
 * @since 2014.10.29
 * @version 0.1 (alpha)
 * @author dxcloud
 */
public class SceneTitle extends UI_Scene {
	//
	// Constants
	//
	private static final int MUSIC_ID              = R.string.ms_01;
	private static final int TEXTURE_BACKGROUND_ID = R.string.bg_04;
	private static final int TEXTURE_FOREGROUND_ID = R.string.bg_03;

	//---
	// UI elements
	//---
	private UI_Sprite[] mBackgrounds = new UI_Sprite[2];
	private UI_Window mWindowTitle;
	private HashMap<TitleOptionType, UI_Window> mWindowMap = new HashMap<TitleOptionType, UI_Window>();


	public enum TitleOptionType {
		PLAY(ResourceManager.getInstance().getResourceString(R.string.play)) {
			@Override
			public UI_Window create(final SceneTitle pScene) {
				return new UI_WindowPlay(pScene);
			}
		},
		OPTIONS(ResourceManager.getInstance().getResourceString(R.string.options)) {
			@Override
			public UI_Window create(final SceneTitle pScene) {
				return new UI_WindowOptions(pScene);
			}
		},
		EXTRAS(ResourceManager.getInstance().getResourceString(R.string.extras)) {
			@Override
			public UI_Window create(final SceneTitle pScene) {
				return new UI_WindowExtras(pScene);
			}
		};

		private final String mLabel;

		private TitleOptionType(final String pLabel) {
			mLabel = pLabel;
		}

		public String getLabel() {
			return this.mLabel;
		}

		public abstract UI_Window create(final SceneTitle pScene);
	}

	public void restore() {
		mWindowTitle.enable();
	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onLoadResources()
	 */
	@Override
	public void onLoadResources() {
		ResourceManager.getInstance().loadTexture(TEXTURE_BACKGROUND_ID);
		ResourceManager.getInstance().loadTexture(TEXTURE_FOREGROUND_ID);
	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onCreate()
	 */
	@Override
	public void onCreate() {
		AudioManager.getInstance().playMusic(MUSIC_ID, true, true);

		mBackgrounds[0] = new UI_Sprite(TEXTURE_BACKGROUND_ID);
		mBackgrounds[0].registerEntityModifier(
				new LoopEntityModifier(new MoveYModifier(10, -228, 0)));
		mBackgrounds[1] = new UI_Sprite(TEXTURE_FOREGROUND_ID);

		mWindowTitle = new UI_WindowTitle(this);
		for (final TitleOptionType type : TitleOptionType.values()) {
			mWindowMap.put(type, type.create(this));
		}

		for (UI_Sprite sprite : mBackgrounds) {
			this.attachChild(sprite);
		}

		for (UI_Window window : mWindowMap.values()) {
			this.attachChild(window);
		}

//		this.setOnSceneTouchListener(this);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);

		this.attachChild(mWindowTitle);
		this.restore();
	}

	private class UI_WindowTitle extends UI_WindowMenu {
		public UI_WindowTitle(final SceneTitle pScene) {
			super(pScene, "");
			this.setBackButtonEnabled(false);
		}

		@Override
		protected void onCreateButtons(final UI_Scene pScene) {
			for (final TitleOptionType type : TitleOptionType.values()) {
				final UI_Button button = new UI_Button(type.getLabel()) {
					@Override
					protected void onPressed() {
						Debug.d("Pressed " + type);
						UI_WindowTitle.this.disable();
						SceneTitle.this.mWindowMap.get(type).enable();
					}
				};
				mPanelButtons.add(button);
			}


		}


	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onDestroyResources()
	 */
	@Override
	public void onDestroyResources() { }

	/**
	 * @see com.soulreapers.scene.UI_Scene#onDestroy()
	 */
	@Override
	public void onDestroy() {
		AudioManager.getInstance().pauseMusic();

		mWindowTitle.destroy();
		for (UI_Sprite background : mBackgrounds) { background.destroy(); }
		for (UI_Window window : mWindowMap.values()) { window.destroy(); }
		mWindowMap.clear();

		this.clearTouchAreas();
	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onPause()
	 */
	@Override
	public void onPause() {
		// nothing to do
	}

	/**
	 * @see com.soulreapers.scene.UI_Scene#onResume
	 */
	@Override
	public void onResume() {
		// nothing to do
	}

//	@Override
//	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
//		final int[] r = {0, 2, 5, 8, 9, 12, 15, 17, 19, 21, 22, 23, 25};
//		if (pSceneTouchEvent.isActionDown()) {
//			if (e != 0) e -= (1 << r[m++]);
//			if (e == 0) {
////				mButtonExtras.setVisible(true);
////				this.registerTouchArea(mButtonExtras);
//			}
//		}
//		return true;
//	}
//	private int m = 0;
//	private int e = 0x2ea9325;
	@Override
	public String toString() {
		return "SceneTitle";
	}
}

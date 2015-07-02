package com.soulreapers.scene;

import java.util.HashMap;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.misc.GameConstants;

@Deprecated
public class OptionsMenuScene extends UI_Scene {
	private static final int MAX_CHAR_OPTION = 12;
	private HashMap<Integer, Text> mOptionsTextMap = new HashMap<Integer, Text>();
//	private static final int FONT_ID = ResourceManager.FONT_TEXT_ID;

	@Override
	public void onLoadResources() {
		// Nothing to do
	}

	@Override
	public void onCreate() {
		int key;
		key = R.string.tb_o01;
		mOptionsTextMap.put(key,
				new Text(100, 100,
						FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
						ResourceManager.getInstance().getResourceString(key),
						MAX_CHAR_OPTION,
						new TextOptions(HorizontalAlign.LEFT),
						ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Debug.i("You touched BGM");
				if (pSceneTouchEvent.isActionDown()) {
					AudioManager.getInstance().setMusicEnabled(
							!AudioManager.getInstance().isMusicEnabled());
				}
				return true;
			}
		});

		key = R.string.tb_09;
		mOptionsTextMap.put(key, new Text(GameConstants.X_OPTION_TEXT_PADDING,
				GameConstants.Y_OPTION_TEXT_PADDING * 8,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				ResourceManager.getInstance().getResourceString(R.string.tb_09),
				new TextOptions(HorizontalAlign.LEFT),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Back");
					SceneManager.getInstance().showScene(SceneTitle.class);
				}
				return true;
			}
		});

		this.registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				if (AudioManager.getInstance().isMusicEnabled()) {
					mOptionsTextMap.get(R.string.tb_o01).setText(
							ResourceManager.getInstance().getResourceString(R.string.tb_o01)
							+ "      "
							+ ResourceManager.getInstance().getResourceString(R.string.tb_yes));
				} else {
					mOptionsTextMap.get(R.string.tb_o01).setText(
							ResourceManager.getInstance().getResourceString(R.string.tb_o01)
							+ "      "
							+ ResourceManager.getInstance().getResourceString(R.string.tb_no));
				}
			}
			@Override
			public void reset() {
				// Nothing to do
			}
			
		});

		for (Text optionText : mOptionsTextMap.values()) {
			this.registerTouchArea(optionText);
			this.attachChild(optionText);
		}
		this.setTouchAreaBindingOnActionDownEnabled(true);
	}

	@Override
	public void onDestroyResources() {
		// Nothing to do
	}

	@Override
	public void onDestroy() {
		for (Text optionText : mOptionsTextMap.values()) {
			this.unregisterTouchArea(optionText);
			optionText.detachSelf();
			optionText.dispose();
		}
		this.detachSelf();
		this.dispose();
	}

	@Override
	public void onPause() {
		// Nothing to do
	}

	@Override
	public void onResume() {
		// Nothing to do
	}
}

/**
 * 
 */
package com.soulreapers.ui.window;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.scene.UI_Scene;
import com.soulreapers.scene.SceneTitle;
import com.soulreapers.ui.UI_Label;
import com.soulreapers.ui.UI_Panel;
import com.soulreapers.ui.slot.UI_Slot;

/**
 * @author chris
 *
 */
public class UI_WindowOptions extends UI_WindowMenu {
	private static final float OPTION_OFFSET_X = 50;
	private static final float OPTION_OFFSET_Y = 80;

	private UI_Panel<UI_SlotOption> mParameters = 
			new UI_Panel<UI_SlotOption>(OPTION_OFFSET_X, OPTION_OFFSET_Y);

	public UI_WindowOptions(final SceneTitle pScene) {
		super(pScene, ResourceManager.getInstance().getResourceString(R.string.options));

		final UI_SlotOption bgm = new UI_SlotOption("BGM",
				(AudioManager.getInstance().isMusicEnabled() ? "ON" : "OFF"),
				20,
				50) {
			@Override
			protected void onSelection() {
				boolean enable = !AudioManager.getInstance().isMusicEnabled();
				AudioManager.getInstance().setMusicEnabled(enable);
				setValue((enable ? "ON" : "OFF"));
			}
		};
		pScene.registerTouchArea(bgm);
		mParameters.add(bgm);
		this.attachChild(mParameters);
	}


	private abstract class UI_SlotOption extends UI_Slot {
		private static final float OPTION_SLOT_WIDTH = 300;
		private static final float OPTION_SLOT_HEIGHT = 30;
		private static final float NAME_PADDING_X = 30;
		private static final float VALUE_PADDING_X = 120;

		private UI_Label mName;
		private UI_Label mValue;

		public UI_SlotOption(String pName, String pValue, float pX, float pY) {
			super(0, pX, pY, OPTION_SLOT_WIDTH, OPTION_SLOT_HEIGHT);
			mName = new UI_Label(pName, FontType.FONT_TEXT_SMALL, 3);
			mValue = new UI_Label(pValue, FontType.FONT_TEXT_SMALL, 3);

			this.setColorEnabled(false);
			this.attachChild(mName);
			this.attachChild(mValue);
			mName.setPosition(NAME_PADDING_X, (this.getHeight() - mName.getHeight()) / 2);
			mValue.setPosition(VALUE_PADDING_X, (this.getHeight() - mValue.getHeight()) / 2);
		}

		public void setValue(String pValue) {
			mValue.setText(pValue);
		}

		/* (non-Javadoc)
		 * @see com.soulreapers.ui.slot.UI_Slot#onSelected()
		 */
		@Override
		protected void onSelected() {
			UI_SlotOption.this.onSelection();
		}

		/* (non-Javadoc)
		 * @see com.soulreapers.ui.slot.UI_Slot#onDeselected()
		 */
		@Override
		protected void onDeselected() {
			
		}

		/* (non-Javadoc)
		 * @see com.soulreapers.ui.slot.UI_Slot#updateContent()
		 */
		@Override
		public void updateContent() {
			
		}
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_WindowMenu#onCreateButtons(com.soulreapers.scene.BaseScene)
	 */
	@Override
	protected void onCreateButtons(UI_Scene pScene) {
		// nothing to do		
	}
}

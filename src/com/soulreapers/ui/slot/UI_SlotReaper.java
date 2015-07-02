/**
 * 
 */
package com.soulreapers.ui.slot;

import com.soulreapers.R;
import com.soulreapers.core.DataManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.ui.UI_Label;

/**
 * @author chris
 *
 */
public class UI_SlotReaper extends UI_Slot {
	private static final float RECTANGLE_X = 420;
	private static final float RECTANGLE_Y = 80;
	private static final float RECTANGLE_WIDTH = 360;
	private static final float RECTANGLE_HEIGHT = 100;
	private static final float RECTANGLE_PADDING_Y = 5;

	private static final float OFFSET_X = 100;
	private static final float OFFSET_Y = 10;

	private static final float PADDING_X = 230;
	private static final float PADDING_Y = 30;

	private static final float GAUGE_X = 100;
	private static final float GAUGE_Y = 48;
	private static final float GAUGE_WIDTH = 240;
	private static final float GAUGE_HEIGHT = 10;


	private static final String LEVEL_FORMAT = "Lv. \t %2d";

	private UI_Label mName = new UI_Label("", FontType.FONT_TEXT_MEDIUM);
	private UI_Label mLevel = new UI_Label("", FontType.FONT_TEXT_MEDIUM);
	private UI_Label mLabelSoul = new UI_Label(ResourceManager.getInstance().getResourceString(R.string.soul), FontType.FONT_TEXT_SMALL);
	private UI_Label mSoulValue = new UI_Label("", FontType.FONT_TEXT_SMALL);
	private UI_Label mLabelExp = new UI_Label(ResourceManager.getInstance().getResourceString(R.string.exp), FontType.FONT_TEXT_SMALL);
	private UI_Label mExpValue = new UI_Label("", FontType.FONT_TEXT_SMALL);

	public UI_SlotReaper(int pReaperID) {
		super(pReaperID, RECTANGLE_X, RECTANGLE_Y,
				RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		mPaddingY = RECTANGLE_PADDING_Y;

		mName.setPosition(OFFSET_X, OFFSET_Y);
		mLevel.setPosition(OFFSET_X + PADDING_X, OFFSET_Y);
		mLabelSoul.setPosition(OFFSET_X, OFFSET_Y + PADDING_Y);
		mLabelExp.setPosition(OFFSET_X, OFFSET_Y + PADDING_Y * 2);
		mSoulValue.setPosition(OFFSET_X + PADDING_X, OFFSET_Y + PADDING_Y);
		mExpValue.setPosition(OFFSET_X + PADDING_X, OFFSET_Y + PADDING_Y * 2);

		this.attachChild(mName);
		this.attachChild(mLevel);
		this.attachChild(mLabelSoul);
		this.attachChild(mSoulValue);
		this.attachChild(mLabelExp);
		this.attachChild(mExpValue);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.slot.UI_Slot#onSelection()
	 */
	@Override
	protected void onSelection() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.slot.UI_Slot#onSelected()
	 */
	@Override
	protected void onSelected() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.slot.UI_Slot#onDeselected()
	 */
	@Override
	protected void onDeselected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateContent() {
		final Reaper reaper = DataManager.getInstance().getReaper(mID);
		mName.setText(reaper.getName());
		mLevel.setText(String.format(LEVEL_FORMAT, reaper.getParameters().getLevel()));
		mSoulValue.setText(String.format("%d", reaper.getParameters().getTotal(AttributeType.SOUL)));
		mExpValue.setText(String.format("%d", reaper.getParameters().getTotal(AttributeType.EXPERIENCE)));
	}

	@Override
	protected void onDestroy() {
		mName.destroy();
		mLevel.destroy();
		mLabelSoul.destroy();
		mSoulValue.destroy();
		mLabelExp.destroy();
		mExpValue.destroy();
	}

	@Override
	public String toString() {
		return "SlotReaper";
	}
}

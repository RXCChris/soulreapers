/**
 * 
 */
package com.soulreapers.ui;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.color.Color;

import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.reaper.Reaper;

/**
 * @author chris
 *
 */
public class UI_Reaper extends UI_Rectangle {
	private static final float DEFAULT_WIDTH = 140;
	private static final float DEFAULT_HEIGHT = 320;
	private static final Color DEFAULT_COLOR = new Color(0.2f, 0.2f, 0.8f, 0.5f);

	private static final String LEVEL_FORMAT = "Lv. %02d";

	private UI_Sprite mIllust;
	private UI_Label mName;
	private UI_Label mLevel;

	public UI_Reaper(final Reaper pReaper) {
		super(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setColor(DEFAULT_COLOR);
		this.onCreateUI_Illust(pReaper.getIllustrationID());
		this.onCreateUI_Name(pReaper.getName());
		this.onCreateUI_Level(pReaper.getParameters().getLevel());
	}

	private void onCreateUI_Illust(int pIllustrationID) {
		mIllust = new UI_Sprite(pIllustrationID);
		this.attachChild(mIllust);

		final float scale = DEFAULT_WIDTH / mIllust.getWidth();
		final float w = mIllust.getWidth() * scale;
		final float h = mIllust.getHeight() * scale;
		mIllust.setSize(w, h);
	}

	private void onCreateUI_Name(final String pName) {
		mName = new UI_Label(pName, FontType.FONT_TEXT_MEDIUM, GameConstants.MAX_CHARACTER_SIZE);
		this.attachChild(mName);

		final float y = mIllust.getY() + mIllust.getHeight();
		mName.setY(y);
	}

	private void onCreateUI_Level(int pLevel) {
		mLevel = new UI_Label(String.format(LEVEL_FORMAT, pLevel), FontType.FONT_TEXT_SMALL);
		this.attachChild(mLevel);
		final float y = mName.getY() + mName.getHeight();
		mLevel.setY(y);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.IUserInterface#cleanup()
	 */
	@Override
	protected void onDestroy() {
		mIllust.destroy();
		mName.destroy();
		mLevel.destroy();
	}

	@Override
	public String toString() {
		return "Reaper";
	}
}

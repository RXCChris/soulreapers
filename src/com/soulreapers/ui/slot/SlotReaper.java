/**
 * 
 */
package com.soulreapers.ui.slot;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.Gauge;
import com.soulreapers.object.character.reaper.Reaper;

/**
 * @author chris
 *
 */
@Deprecated
public class SlotReaper extends Slot<Reaper> {
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

//	private static final int FONT_ID = ResourceManager.FONT_TEXT_ID;
//	private static final int FONT_STATS_ID = ResourceManager.FONT_STATS_ID;

	private Text mTextName;
	private Text mTextLevel;

	private final Text mTextSoul = new Text(OFFSET_X, OFFSET_Y + PADDING_Y,
			FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
			AttributeType.SOUL.toString(),
			ResourceManager.getInstance().getVertexBufferObjectManager());
	private final Text mTextExp = new Text(OFFSET_X, OFFSET_Y + PADDING_Y * 2,
			FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
			AttributeType.EXPERIENCE.toString(),
			ResourceManager.getInstance().getVertexBufferObjectManager());

	private Text mTextSoulValue;
	private Text mTextExpValue;

	private Gauge mGaugeSoul = new Gauge(GAUGE_X, GAUGE_Y,
			GAUGE_WIDTH, GAUGE_HEIGHT, Color.RED);
	private Gauge mGaugeExp = new Gauge(GAUGE_X, GAUGE_Y + PADDING_Y,
			GAUGE_WIDTH, GAUGE_HEIGHT, Color.BLUE);

	public SlotReaper(final Reaper pReaper) {
		super(RECTANGLE_X, RECTANGLE_Y,
				RECTANGLE_WIDTH, RECTANGLE_HEIGHT,
				pReaper);
		mPaddingY = RECTANGLE_PADDING_Y;

		mTextName = new Text(OFFSET_X, OFFSET_Y,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				pReaper.getName(),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mTextLevel = new Text(OFFSET_X + PADDING_X, OFFSET_Y,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				"Lv.\t" + pReaper.getParameters().getLevel(),
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mTextSoulValue = new Text(OFFSET_X + PADDING_X, OFFSET_Y + PADDING_Y,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				"" + pReaper.getParameters().getTotal(AttributeType.SOUL),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mTextExpValue = new Text(OFFSET_X + PADDING_X, OFFSET_Y + PADDING_Y * 2,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				"" + pReaper.getParameters().getTotal(AttributeType.EXPERIENCE),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mGaugeExp.update(pReaper.getParameters().getCurrent(AttributeType.EXPERIENCE),
				pReaper.getParameters().getTotal(AttributeType.EXPERIENCE));

		mTextLevel.setX(mTextLevel.getX() - mTextLevel.getWidth());
		mTextSoulValue.setX(mTextSoulValue.getX() - mTextSoulValue.getWidth());
		mTextExpValue.setX(mTextExpValue.getX() - mTextExpValue.getWidth());

		this.attachChild(mTextName);
		this.attachChild(mTextLevel);
		this.attachChild(mGaugeSoul);
		this.attachChild(mTextSoul);
		this.attachChild(mTextSoulValue);
		this.attachChild(mGaugeExp);
		this.attachChild(mTextExp);
		this.attachChild(mTextExpValue);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.menu.Slot#getDescription()
	 */
//	@Override
//	public String getDescription() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.menu.Slot#updateContent()
	 */
	@Override
	public void updateContent() {
		mTextName.setText(mElement.getName());
		mTextLevel.setText(String.format("Lv.\t %d", mElement.getParameters().getLevel()));
		mTextSoulValue.setText(String.format("%d", mElement.getParameters().getTotal(AttributeType.SOUL)));
		mTextExpValue.setText(String.format("%d", mElement.getParameters().getTotal(AttributeType.EXPERIENCE)));
	}
}

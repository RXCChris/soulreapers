/**
 * 
 */
package com.soulreapers.ui;

import java.util.HashMap;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.soulreapers.core.FontManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.CharacterParameters;
import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.Gauge;


/**
 * @author chris
 *
 */
@Deprecated
public class AttributesUI extends Rectangle {
//	private static final int FONT_STATS_ID = ResourceManager.FONT_STATS_ID;
//	private static final int FONT_NAME_ID = ResourceManager.FONT_TEXT_ID;
	private static final String STRING_LEVEL = "Lv. ";
	private static final String STATUS_DOUBLE_VALUE_FORMAT = "%03d (%+03d)";
	private static final String STATUS_MIN_MAX_VALUE_FORMAT = "%03d/%03d";
	private static final String STATUS_SINGLE_VALUE_FORMAT = "%7d";

	private static final int RECTANGLE_X = 16;
	private static final int RECTANGLE_Y = 118;
	private static final int RECTANGLE_WIDTH = 294;
	private static final int RECTANGLE_HEIGHT = 168;

	private static final int OFFSET_X = 10;
	private static final int OFFSET_Y = 10;

//	private static final int PADDING_X = RECTANGLE_WIDTH / 2;
	private static final int PADDING_Y = 18;
	private static final int PADDING_LEVEL_X = 180;

	private static final int MAX_CHAR_STATUS_VALUE = 12;

	private static final int GAUGE_PADDING = 8;
	private static final int GAUGE_WIDTH = 226;
	private static final int GAUGE_HEIGHT = 10;

	private boolean mMinMax = false;

//	private Text mTextName;
	private Text mTextLevel;

	private CharacterParameters mAttributes;

	private HashMap<AttributeType, AttributeText> mStatusTextMap =
			new HashMap<AttributeType, AttributeText>();
	public Gauge mHealthGauge = null;
	public Gauge mExpGauge = null;

	public AttributesUI() {
		this(new CharacterParameters());
	}

	public AttributesUI(CharacterParameters pStatus) {
		super(RECTANGLE_X, RECTANGLE_Y,
				RECTANGLE_WIDTH, RECTANGLE_HEIGHT,
				ResourceManager.getInstance().getVertexBufferObjectManager());

		onCreateUI(pStatus);
		setStatus(pStatus);
		this.setColor(Color.BLACK);
		this.setAlpha(0.75F);
	}

	public void setStatus(CharacterParameters pStatus) {
		if (pStatus == null) { return; }

		updateAll(pStatus);
		mAttributes = pStatus;
	}

	public void update() {
		updateAll(mAttributes);
	}

	public void updateAll(CharacterParameters pAttribute) {
		for (AttributeType type : AttributeType.values()) {
			updateAttribute(type, pAttribute);
		}
		mTextLevel.setText(STRING_LEVEL + pAttribute.getLevel());
	}

	private void addAttributeText(int pX, int pY, AttributeType pAttribute,
			int pBase, int pBonus, int pCurrent) {
		Text statusName = new Text(pX, pY,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				pAttribute.toString(),
				new TextOptions(HorizontalAlign.LEFT),
				ResourceManager.getInstance().getVertexBufferObjectManager());

		Text statusValue;
		int padding = AttributeText.PADDING_VALUE;
		if (pAttribute == AttributeType.SOUL || pAttribute == AttributeType.EXPERIENCE ) {
			padding = AttributeText.PADDING_GAUGE;
			statusValue = new Text(pX + AttributeText.PADDING_GAUGE, pY,
					FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
					String.format(STATUS_SINGLE_VALUE_FORMAT, pCurrent),
					MAX_CHAR_STATUS_VALUE,
					new TextOptions(HorizontalAlign.RIGHT),
					ResourceManager.getInstance().getVertexBufferObjectManager());
		} else {
			statusValue = new Text(pX + padding, pY,
					FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
					String.format(STATUS_DOUBLE_VALUE_FORMAT, pBase, pBonus),
					MAX_CHAR_STATUS_VALUE,
					new TextOptions(HorizontalAlign.LEFT),
					ResourceManager.getInstance().getVertexBufferObjectManager());
		}

		mStatusTextMap.put(pAttribute, new AttributeText(statusName, statusValue));
	}

	private void onCreateUI(CharacterParameters pStatus) {
		mTextLevel = new Text(OFFSET_X + PADDING_LEVEL_X, OFFSET_Y,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				STRING_LEVEL + pStatus.getLevel(),
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager());

		int paddingY = 1;
		for (final AttributeType attribute : AttributeType.values()) {
			int offsetX = OFFSET_X;
			++paddingY;
			addAttributeText(offsetX, OFFSET_Y + PADDING_Y * paddingY,
					attribute,
					pStatus.getBase(attribute),
					pStatus.getBonus(attribute),
					pStatus.getCurrent(attribute));
		}
		mHealthGauge = new Gauge(OFFSET_X,
				mStatusTextMap.get(AttributeType.SOUL).mAttributeName.getY() + GAUGE_PADDING,
				GAUGE_WIDTH, GAUGE_HEIGHT, Color.RED);
		mExpGauge = new Gauge(OFFSET_X,
				mStatusTextMap.get(AttributeType.EXPERIENCE).mAttributeName.getY() + GAUGE_PADDING,
				GAUGE_WIDTH, GAUGE_HEIGHT, Color.BLUE);
//		mExpGauge.setColor(Color.BLUE);
	}

	private class AttributeText {
		public Text mAttributeName;
		public Text mAttributeValue;
		public static final int PADDING_VALUE = 160;
		public static final int PADDING_GAUGE = 220;
		public AttributeText(Text pStatName, Text pStatValue) {
			mAttributeName = pStatName;
			mAttributeValue = pStatValue;
		}
		
	}

	private void updateAttribute(AttributeType pType, CharacterParameters pAttribute) {
		Text textValue = mStatusTextMap.get(pType).mAttributeValue;
		if (pType == AttributeType.SOUL) {
			textValue.setText(String.format(STATUS_SINGLE_VALUE_FORMAT,
					pAttribute.getCurrent(pType)));
			mHealthGauge.update(pAttribute.getCurrent(pType),
					pAttribute.getTotal(pType));
			textValue.setPosition(OFFSET_X + AttributeText.PADDING_GAUGE - textValue.getWidth(),
					textValue.getY());
		} else if (pType == AttributeType.EXPERIENCE) {
			textValue.setText(String.format(STATUS_SINGLE_VALUE_FORMAT,
					pAttribute.getCurrent(pType)));
			mExpGauge.update(pAttribute.getCurrent(pType),
					pAttribute.getTotal(pType));
			textValue.setPosition(OFFSET_X + AttributeText.PADDING_GAUGE - textValue.getWidth(),
					textValue.getY());
		} else {
			if (mMinMax == true) {
				textValue.setText(String.format(STATUS_MIN_MAX_VALUE_FORMAT,
						pAttribute.getCurrent(pType), pAttribute.getTotal(pType)));
			} else {
				textValue.setText(String.format(STATUS_DOUBLE_VALUE_FORMAT,
						pAttribute.getBase(pType), pAttribute.getBonus(pType)));
			}
		}
	}

	public void setMinMaxEnabled(boolean pEnabled) {
		mMinMax = pEnabled;
	}


	@Override
	public void onAttached() {
//		this.attachChild(mTextName);
		this.attachChild(mTextLevel);
		this.attachChild(mHealthGauge);
		this.attachChild(mExpGauge);

		for (AttributeText status : mStatusTextMap.values()) {
			this.attachChild(status.mAttributeName);
			this.attachChild(status.mAttributeValue);
		}
		super.onAttached();
	}

	@Override
	public void onDetached() {
//		mTextName.detachSelf();
		mTextLevel.detachSelf();
		mHealthGauge.detachSelf();
		mExpGauge.detachSelf();
		for (AttributeText status : mStatusTextMap.values()) {
			status.mAttributeName.detachSelf();
			status.mAttributeValue.detachSelf();
		}
		this.detachChildren();
		super.onDetached();
	}

	@Override
	public void dispose() {
//		mTextName.dispose();
		mTextLevel.dispose();
		mHealthGauge.dispose();
		mExpGauge.dispose();
		for (AttributeText status : mStatusTextMap.values()) {
			status.mAttributeName.dispose();
			status.mAttributeValue.dispose();
		}
		super.dispose();
	}
}

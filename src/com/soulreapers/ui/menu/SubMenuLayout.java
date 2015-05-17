/**
 * 
 */
package com.soulreapers.ui.menu;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;

/**
 * @author chris
 *
 */
public class SubMenuLayout extends Rectangle {
	private static final int FONT_ID = ResourceManager.FONT_SUB_TITLE_ID;
	private static final int FONT_TEXT_ID = ResourceManager.FONT_TEXT_ID;
	private static final int INFO_BOX_HEIGHT = 80;
	private static final int LINE_HEIGHT = 4;
	private static final int LINE_PADDING_Y = 20;
	private static final int PADDING_FIRST_X = 10;
	private static final int PADDING_SECOND_X = 280;
	private static final int RECTANGLE_X = 10;
	private static final int RECTANGLE_Y = 90;
	private static final int RECTANGLE_WIDTH = 400;
	private static final int RECTANGLE_HEIGHT = 380;
	private static final int MAX_CHAR_NAME_SIZE = 16;
	private static final int MAX_CHAR_INFO_SIZE = 50;

	protected Rectangle mLineFirst =
			new Rectangle(0, LINE_PADDING_Y, RECTANGLE_WIDTH, LINE_HEIGHT,
					ResourceManager.getInstance().getVertexBufferObjectManager());
	protected Rectangle mLineSecond =
			new Rectangle(0, LINE_PADDING_Y * 2, RECTANGLE_WIDTH, LINE_HEIGHT,
					ResourceManager.getInstance().getVertexBufferObjectManager());

	protected Rectangle mInfoBox =
			new Rectangle(0, RECTANGLE_HEIGHT - INFO_BOX_HEIGHT,
					RECTANGLE_WIDTH, INFO_BOX_HEIGHT,
					ResourceManager.getInstance().getVertexBufferObjectManager());

	protected Text mTextInfo = new Text(0, RECTANGLE_HEIGHT - INFO_BOX_HEIGHT - FONT_ID / 2,
			ResourceManager.getInstance().getFont(FONT_ID),
			GameConstants.STRING.INFO,
			ResourceManager.getInstance().getVertexBufferObjectManager());

	protected Text mTextSubMenuName = new Text(PADDING_FIRST_X, 0,
			ResourceManager.getInstance().getFont(FONT_ID),
			"",
			MAX_CHAR_NAME_SIZE,
			ResourceManager.getInstance().getVertexBufferObjectManager());

	protected Text mTextFirstColumn = new Text(PADDING_FIRST_X * 2, LINE_PADDING_Y,
			ResourceManager.getInstance().getFont(FONT_ID),
			"",
			MAX_CHAR_NAME_SIZE,
			ResourceManager.getInstance().getVertexBufferObjectManager());

	protected Text mTextSecondColumn = new Text(PADDING_SECOND_X, LINE_PADDING_Y,
			ResourceManager.getInstance().getFont(FONT_ID),
			"",
			MAX_CHAR_NAME_SIZE,
			ResourceManager.getInstance().getVertexBufferObjectManager());

	protected Text mTextInfoDescription = new Text(PADDING_FIRST_X, RECTANGLE_HEIGHT - INFO_BOX_HEIGHT + FONT_ID / 2,
			ResourceManager.getInstance().getFont(FONT_TEXT_ID),
			"",
			MAX_CHAR_INFO_SIZE,
			new TextOptions(AutoWrap.WORDS, RECTANGLE_WIDTH - FONT_ID, HorizontalAlign.LEFT),
			ResourceManager.getInstance().getVertexBufferObjectManager());

	public SubMenuLayout() {
		super(RECTANGLE_X,
				RECTANGLE_Y,
				RECTANGLE_WIDTH,
				RECTANGLE_HEIGHT,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		this.setColor(GameConstants.UI.COLOR_BACKGROUND);

		mInfoBox.setColor(Color.BLUE);
		mInfoBox.setAlpha(0.5f);

		this.attachChild(mTextSubMenuName);
		this.attachChild(mLineFirst);
		this.attachChild(mTextFirstColumn);
		this.attachChild(mTextSecondColumn);
		this.attachChild(mLineSecond);
		this.attachChild(mInfoBox);
		this.attachChild(mTextInfo);
		this.attachChild(mTextInfoDescription);
	}

	public void setSubMenuName(String pName) {
		mTextSubMenuName.setText(pName);
	}

	public void setFirstColumnName(String pName) {
		mTextFirstColumn.setText(pName);
	}

	public void setSecondColumnName(String pName) {
		mTextSecondColumn.setText(pName);
	}

	public void setInfoDescription(String pDescription) {
		mTextInfoDescription.setText(pDescription);
	}

//	@Override
//	public void onAttached() {
//		this.attachChild(mTextSubMenuName);
//		this.attachChild(mLineFirst);
//		this.attachChild(mTextFirstColumn);
//		this.attachChild(mTextSecondColumn);
//		this.attachChild(mLineSecond);
//		this.attachChild(mInfoBox);
//		this.attachChild(mTextInfo);
//		this.attachChild(mTextInfoDescription);
//
//		super.onAttached();
//	}
//
//	@Override
//	public void onDetached() {
//		SubMenuLayout.this.detachChildren();
//		super.onDetached();
//	}

//	@Override
//	public void dispose() {
//		mTextSubMenuName.dispose();
//		mLineFirst.dispose();
//		mTextFirstColumn.dispose();
//		mTextSecondColumn.dispose();
//		mLineSecond.dispose();
//		mInfoBox.dispose();
//		mTextInfo.dispose();
//		mTextInfoDescription.dispose();
//
//		super.dispose();
//	}
}

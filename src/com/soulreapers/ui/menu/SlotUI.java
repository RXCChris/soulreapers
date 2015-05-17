/**
 * 
 */
package com.soulreapers.ui.menu;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;

import com.soulreapers.core.ResourceManager;

/**
 * @author chris
 *
 */
@Deprecated
public class SlotUI extends Rectangle {
	protected static final int FONT_TEXT_ID = ResourceManager.FONT_TEXT_ID;

	protected static final float RECTANGLE_WIDTH = 380;
	protected static final float RECTANGLE_HEIGHT = 36;

	private static final float RECTANGLE_OFFSET_X = 10;
	private static final float RECTANGLE_OFFSET_Y = 48;

	private static final float RECTANGLE_PADDING_Y = RECTANGLE_HEIGHT + 6;
	private static final float TEXT_PADDING_X = 10;
	private static final int MAX_CHAR_SIZE = 20;

	private static final String EMPTY = "Vide";
	private Text mText;
	private boolean mSelected = false;

	private static final Color COLOR_NORMAL = new Color(1F, 0F, 1F, 0.5F);
	private static final Color COLOR_SELECTED = new Color(1F, 0F, 0F, 0.5F);

	public SlotUI(float pIndexY) {
		this(pIndexY, EMPTY);
	}

	public SlotUI(float pIndexY, String pText) {
		super(RECTANGLE_OFFSET_X, RECTANGLE_OFFSET_Y + RECTANGLE_PADDING_Y * pIndexY,
				RECTANGLE_WIDTH, RECTANGLE_HEIGHT,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mText = new Text(TEXT_PADDING_X, 0,
				ResourceManager.getInstance().getFont(FONT_TEXT_ID),
				pText,
				MAX_CHAR_SIZE,
				ResourceManager.getInstance().getVertexBufferObjectManager());

		mText.setY(RECTANGLE_HEIGHT / 2 - mText.getHeight() / 2);
		this.setColor(COLOR_NORMAL);
	}

	public void select() {
		mSelected = true;
		this.setColor(COLOR_SELECTED);
	}

	public void unselect() {
		mSelected = false;
		this.setColor(COLOR_NORMAL);
	}

	public boolean isSelected() {
		return mSelected;
	}

	public void setText(String pText) {
		mText.setText(pText);
	}

	@Override
	public void onAttached() {
		this.attachChild(mText);
		super.onAttached();
	}

	@Override
	public void onDetached() {
		this.detachChildren();
		super.onDetached();
	}

	@Override
	public void dispose() {
		mText.dispose();
		super.dispose();
	}
}

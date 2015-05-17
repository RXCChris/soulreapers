/**
 * 
 */
package com.soulreapers.ui.slot;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.item.ItemBase;

/**
 * @author chris
 *
 */
public class SlotItem<T extends ItemBase> extends Slot<T> {
	private static final float RECTANGLE_OFFSET_X = 10;
	private static final float RECTANGLE_OFFSET_Y = 48;

	protected static final float RECTANGLE_WIDTH = 380;
	protected static final float RECTANGLE_HEIGHT = 36;

	private static final float TEXT_PADDING_X = 10;

//	private Text mTextName;
//	private Text mTextQuantity;
	private Text mTextName = new Text(TEXT_PADDING_X, 0,
			ResourceManager.getInstance().getFont(FONT_TEXT_ID),
			"",
			GameConstants.MAX_CHARACTER_SIZE,
			ResourceManager.getInstance().getVertexBufferObjectManager());

	private Text mTextQuantity = new Text(QUANTITY_PADDING_X, 0,
			ResourceManager.getInstance().getFont(FONT_TEXT_ID),
			"",
			QUANTITY_CHAR_SIZE,
			new TextOptions(HorizontalAlign.CENTER),
			ResourceManager.getInstance().getVertexBufferObjectManager());

//	private static final int NAME_CHAR_SIZE = GameConstant;
	private static final int QUANTITY_CHAR_SIZE = 5;
	private static final int QUANTITY_PADDING_X = 300;
	

	public SlotItem(T pItem) {
		super(RECTANGLE_OFFSET_X, RECTANGLE_OFFSET_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT, pItem);

//		mTextName = new Text(TEXT_PADDING_X, 0,
//				ResourceManager.getInstance().getFont(FONT_TEXT_ID),
//				"",
//				NAME_CHAR_SIZE,
//				ResourceManager.getInstance().getVertexBufferObjectManager());
//
//		mTextQuantity = new Text(QUANTITY_PADDING_X, 0,
//				ResourceManager.getInstance().getFont(FONT_TEXT_ID),
//				"",
//				QUANTITY_CHAR_SIZE,
//				new TextOptions(HorizontalAlign.CENTER),
//				ResourceManager.getInstance().getVertexBufferObjectManager());

		mTextName.setY(RECTANGLE_HEIGHT / 2 - mTextName.getHeight() / 2);
		mTextQuantity.setY(RECTANGLE_HEIGHT / 2 - mTextQuantity.getHeight() / 2);
		mTextQuantity.setX(QUANTITY_PADDING_X - mTextQuantity.getWidth() / 2);

		this.updateContent();

		this.attachChild(mTextName);
		this.attachChild(mTextQuantity);
	}

//	@Override
	public String getDescription() {
		return mElement.getDescription();
	}

//	private boolean mQuantityVisible = true;

	public void setQuantityVisible(boolean pVisible) {
//		mQuantityVisible = pVisible;
		mTextQuantity.setVisible(pVisible);
	}


	@Override
	public void updateContent() {
//		if (mTextName == null) Debug.w("SlotItem - updateContent: null pointer (mTextName)");
//		if (mTextQuantity == null) Debug.w("SlotItem - updateContent: null pointer (mTextQuantity)");
		mTextName.setText(mElement.getName());
		if (mElement.getQuantity() >= 1) {
			mTextQuantity.setText(String.format("%02d/%02d", mElement.getAvailability(), mElement.getQuantity()));
		} else {
			mTextQuantity.setText("");
		}
//		mTextQuantity.setText(String.format("%02d/%02d", mElement.getAvailability(), mElement.getQuantity()));
//		} else {
//			mTextQuantity.setText("");
//		}

	}
}

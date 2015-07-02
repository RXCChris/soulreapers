/**
 * 
 */
package com.soulreapers.ui.slot;

import com.soulreapers.core.DataManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.object.item.ItemBase;
import com.soulreapers.ui.UI_Label;

/**
 * @author chris
 *
 */
public class UI_SlotItem extends UI_Slot {

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

	private static final float RECTANGLE_OFFSET_X = 10;
	private static final float RECTANGLE_OFFSET_Y = 48;

	protected static final float RECTANGLE_WIDTH = 380;
	protected static final float RECTANGLE_HEIGHT = 36;

	private static final float TEXT_PADDING_X = 10;

	private UI_Label mName = new UI_Label("", FontType.FONT_TEXT_MEDIUM);
	private UI_Label mQuantity = new UI_Label("", FontType.FONT_TEXT_MEDIUM);
	private static final String QUANTITY_FORMAT = "%2d/%2d";
	

	private static final float QUANTITY_PADDING_X = 300;


	public UI_SlotItem(int pItemID) {
		super(pItemID, RECTANGLE_OFFSET_X, RECTANGLE_OFFSET_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);

		mName.setX(TEXT_PADDING_X);
		mName.setY(RECTANGLE_HEIGHT / 2 - mName.getHeight() / 2);

		mQuantity.setY(RECTANGLE_HEIGHT / 2 - mQuantity.getHeight() / 2);
		mQuantity.setX(QUANTITY_PADDING_X - mQuantity.getWidth() / 2);

		this.updateContent();

		this.attachChild(mName);
		this.attachChild(mQuantity);
	}

	public String getDescription() {
		return DataManager.getInstance().getItem(mID).getDescription();
	}

	public void setQuantityVisible(boolean pVisible) {
		mQuantity.setVisible(pVisible);
	}


	@Override
	public void updateContent() {
		final ItemBase item = DataManager.getInstance().getItem(mID);
		mName.setText(item.getName());
		mQuantity.setText(String.format(QUANTITY_FORMAT, item.getAvailability(), item.getQuantity()));
	}

	@Override
	protected void onDestroy() {
		mName.destroy();
		mQuantity.destroy();
	}
}

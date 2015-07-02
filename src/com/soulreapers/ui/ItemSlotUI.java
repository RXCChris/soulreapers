/**
 * 
 */
package com.soulreapers.ui;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.object.item.ItemBase;
import com.soulreapers.ui.menu.SlotUI;


/**
 * @author chris
 *
 */
@Deprecated
public class ItemSlotUI extends SlotUI {
	private static final int QUANTITY_PADDING_X = 300;

	private Text mTextQuantity;

	private ItemBase mItem;

	public ItemSlotUI(int pIndex, ItemBase pItem) {
		super(pIndex, pItem.getName());

		mTextQuantity = new Text(QUANTITY_PADDING_X, 0,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				String.format("%02d", pItem.getQuantity()),
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mTextQuantity.setY(RECTANGLE_HEIGHT / 2 - mTextQuantity.getHeight() / 2);
		mItem = pItem;
	}

	public String getDescription() {
		return mItem.getDescription();
	}

	@Override
	public void onAttached() {
		this.attachChild(mTextQuantity);
		super.onAttached();
	}

	@Override
	public void dispose() {
		mTextQuantity.dispose();
		super.dispose();
	}
}

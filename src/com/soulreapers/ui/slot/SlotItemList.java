/**
 * 
 */
package com.soulreapers.ui.slot;

import com.soulreapers.object.item.ItemBase;
import com.soulreapers.ui.menu.SubMenuLayout;

/**
 * @author chris
 *
 */
public class SlotItemList<T extends ItemBase> extends SlotList<T, SlotItem<T>> {
//	private SubMenuLayout mSubMenuLayout;

	public SlotItemList(final SubMenuLayout pLayout) {
		super(pLayout);
	}

//	public SlotItemList(final SubMenuLayout pLayout, int pCapacity) {
//		super(pLayout);
//		for (int i = 0; i < pCapacity; ++i)  {
//			this.addSlot(ItemBase.EMPTY);
//		}
//	}

	public void setQuantityVisible(boolean pVisible) {
		for (SlotItem<T> slot : mList) {
			slot.setQuantityVisible(pVisible);
		}
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.menu.SlotList#onInstanciateSlot(java.lang.Object)
	 */
	@Override
	protected SlotItem<T> createSlot(T pElement) {
		return new SlotItem<T>(pElement) {
			@Override
			protected void onSelect() {
				mSelectedSlot = this;
				SlotItemList.this.unselectOthers(this);
				mSubMenuLayout.setInfoDescription(this.getDescription());
				SlotItemList.this.onSlotSelect();
			}

			@Override
			protected void onSelected() {
				SlotItemList.this.onSlotSelected();
			}
		};
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.slot.SlotList#onSlotSelect()
	 */
	@Override
	protected void onSlotSelect() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.slot.SlotList#onSlotSelected()
	 */
	@Override
	protected void onSlotSelected() {
		// TODO Auto-generated method stub
		
	}
}

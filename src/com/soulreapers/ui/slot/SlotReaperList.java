/**
 * 
 */
package com.soulreapers.ui.slot;

import org.andengine.util.debug.Debug;

import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.ui.menu.SubMenuLayout;

/**
 * @author chris
 *
 */
public class SlotReaperList extends SlotList<Reaper, SlotReaper> {

	/**
	 * @param pLayout
	 */
	public SlotReaperList(SubMenuLayout pLayout) {
		super(pLayout);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.menu.SlotList#onInstanciateSlot(java.lang.Object)
	 */
	@Override
	protected SlotReaper createSlot(Reaper pElement) {
		return new SlotReaper(pElement) {
			@Override
			protected void onSelect() {
				Debug.d("Reaper selected");
				SlotReaperList.this.mSelectedSlot = this;
				SlotReaperList.this.unselectOthers(this);
//				mSubMenuLayout.setVisible(true);
				SlotReaperList.this.onSlotSelect();
			}
			@Override
			protected void onSelected() {
				SlotReaperList.this.onSlotSelected();
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

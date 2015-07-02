/**
 * 
 */
package com.soulreapers.ui.slot;

import java.util.ArrayList;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.util.debug.Debug;

import com.soulreapers.ui.menu.SubMenuLayout;


/**
 * @author chris
 *
 */
@Deprecated
public abstract class SlotList<T, S extends Slot<T>> extends Entity {
	protected SubMenuLayout mSubMenuLayout;
	protected ArrayList<S> mList = new ArrayList<S>();
	protected S mSelectedSlot = null;

	public SlotList(SubMenuLayout pLayout) {
		mSubMenuLayout = pLayout;
//		mSubMenuLayout.attachChild(this);
//		mList = new ArrayList<Slot<T>>();
//		this(pLayout, 0);
	}

//	public SlotList(SubMenuLayout pLayout, int pCapacity) {
//		mSubMenuLayout = pLayout;
////		mList = new ArrayList<Slot<T>>(pCapacity);
//	}

//	public SlotList() {
		// nothing to do
//	}
	public S getSelectedSlot() {
		return mSelectedSlot;
	}

	public S getSlot(int pIndex) {
		return mList.get(pIndex);
	}

	public int getSize() {
		return mList.size();
	}

	public void modifyContent(int pIndex, T pElement) {
		final S slot = mList.get(pIndex);
		slot.setElement(pElement);
	}

	public void addSlot(T pElement) {
		final S slot = this.createSlot(pElement);

		final int index = mList.size();
		slot.setIndex(index);

		mList.add(slot);
		this.attachChild(slot);
	}

	protected abstract S createSlot(T pElement);
	protected abstract void onSlotSelect();
	protected abstract void onSlotSelected();

	public void unselectAll() {
		for (final S slot : mList) {
			slot.unselect();
		}
	}

	public void updateList() {
		for (final S slot : mList) {
			slot.updateContent();
		}
	}

	protected void unselectOthers(S pSlot) {
		for (final S slot : mList) {
			if (!slot.equals(pSlot)) {
				slot.unselect();
			}
		}
	}

	public void enableTouchArea(Scene pScene) {
		for (final S slot : mList) {
			pScene.registerTouchArea(slot);
		}
	}

	public void disableTouchArea(Scene pScene) {
		for (final S slot : mList) {
			pScene.unregisterTouchArea(slot);
		}
	}

	public void clear() {
		mSelectedSlot = null;
		mList.clear();
	}
}

/**
 * 
 */
package com.soulreapers.ui.slot;

import org.andengine.input.touch.TouchEvent;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.ui.UI_Rectangle;

/**
 * @author chris
 *
 */
public abstract class UI_Slot extends UI_Rectangle {
	private static final float RECTANGLE_PADDING_Y = 6;

	protected int mID;
	private boolean mSelected = false;
	protected float mPaddingY = RECTANGLE_PADDING_Y;
	private boolean mEnabled = true;

	private int mIndex = 0;
	private boolean mColorEnabled = true;

	protected abstract void onSelection();
	protected abstract void onSelected();
	protected abstract void onDeselected();
	public abstract void updateContent();

	public UI_Slot(int pContentID, final float pX, final float pY, final float pWidth, final float pHeight) {
		super(pX, pY, pWidth, pHeight);

		this.setColor(GameConstants.UI.COLOR_NORMAL);
	}

	public void setEnabled(boolean pEnabled) {
		mEnabled = pEnabled;
	}

	public final void setIndex(final int pIndex) {
		mIndex = pIndex;
		final float y = (this.getHeight() + mPaddingY) * pIndex + this.getY();
		this.setY(y);
	}

	public final int getIndex() {
		return mIndex;
	}

	public final void setContentID(int pID) {
		mID = pID;
	}

	public final int getContentID() {
		return mID;
	}

	@Override
	public final boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX,
			final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown() && mEnabled) {
			if (mSelected) {
				UI_Slot.this.onSelected();
			} else {
				UI_Slot.this.select();
			}
			return true;
		}
		return false;
	}

	public final void select() {
		if (!mSelected) {
			mSelected = true;
			if (mColorEnabled) {
				this.setColor(GameConstants.UI.COLOR_SELECTED);
			}
			this.onSelection();
		}
	}

	public final void deselect() {
		if (mSelected) {
			mSelected = false;
			if (mColorEnabled) {
				this.setColor(GameConstants.UI.COLOR_NORMAL);
			}
			this.onDeselected();
		}
	}

	public final void setColorEnabled(boolean pEnabled) {
		mColorEnabled = pEnabled;
	}

	public final boolean isSelected() {
		return mSelected;
	}

	@Override
	public String toString() {
		return "Slot";
	}
}

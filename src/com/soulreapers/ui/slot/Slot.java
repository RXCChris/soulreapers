/**
 * 
 */
package com.soulreapers.ui.slot;

import java.util.ArrayList;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.input.touch.TouchEvent;

import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;

/**
 * @author chris
 *
 */
@Deprecated
public abstract class Slot<T> extends Rectangle {
//	protected static final int FONT_TEXT_ID = ResourceManager.FONT_TEXT_ID;

//	protected static final float RECTANGLE_WIDTH = 380;
//	protected static final float RECTANGLE_HEIGHT = 36;
//
//	private static final float RECTANGLE_OFFSET_X = 10;
//	private static final float RECTANGLE_OFFSET_Y = 48;

	private static final float RECTANGLE_PADDING_Y = 6;

//	private static final int MAX_CHAR_SIZE = 20;

//	protected Text mTextName = new Text(TEXT_PADDING_X, 0,
//			ResourceManager.getInstance().getFont(FONT_TEXT_ID),
//			GameConstants.STRING.EMPTY,
//			MAX_CHAR_SIZE,
//			ResourceManager.getInstance().getVertexBufferObjectManager());
	
	private boolean mSelected = false;
	protected float mPaddingY = RECTANGLE_PADDING_Y;

	protected T mElement;
	protected ArrayList<Entity> mEntityList = new ArrayList<Entity>();
	private int mIndex = 0;

	protected void onSelected() {}
	protected void onSelect() {}
	protected void onUnselect() {}

//	public abstract String getDescription();
	abstract public void updateContent();

	public Slot(final float pX, final float pY, final float pWidth, final float pHeight, final T pElement) {
		super(pX, pY, pWidth, pHeight, ResourceManager.getInstance().getVertexBufferObjectManager());
//		this.setElement(pElement);
		mElement = pElement;
		this.setColor(GameConstants.UI.COLOR_UNSELECTED);
	}

	public void setIndex(final int pIndex) {
		mIndex = pIndex;
		final float y = (this.getHeight() + mPaddingY) * pIndex + this.getY();
		this.setY(y);
	}

	public int getIndex() {
		return mIndex;
	}

	public void setElement(final T pElement) {
		mElement = pElement;
		this.updateContent();
	}

	public T getElement() {
		return mElement;
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX,
			final float pTouchAreaLocalY) {
		if (pSceneTouchEvent.isActionDown()) {
			if (mSelected) {
				onSelected();
			} else {
				select();
			}
			return true;
		}
		return false;
	}

	public final void select() {
		if (!mSelected) {
			mSelected = true;
			this.setColor(GameConstants.UI.COLOR_SELECTED);
			this.onSelect();
		}
	}

	public final void unselect() {
		if (mSelected) {
			mSelected = false;
			this.setColor(GameConstants.UI.COLOR_UNSELECTED);
			this.onUnselect();
		}
	}

	public final boolean isSelected() {
		return mSelected;
	}
}

/**
 * 
 */
package com.soulreapers.ui;

import java.util.ArrayList;

/**
 * @author chris
 *
 */
public class UI_Panel<T extends IUserInterface> extends UI_Layer {
	public enum Layout {
		HORIZONTAL,
		VERTICAL
	}

	protected float mWidth = 0;
	protected float mHeight = 0;
	protected float mPaddingX;
	protected float mPaddingY;
	protected Layout mLayout;

	protected ArrayList<T> mList = new ArrayList<T>();

	public UI_Panel() {
		this(0, 0);
	}

	public UI_Panel(final float pX, final float pY) {
		this(pX, pY, Layout.VERTICAL);
	}

	public UI_Panel(final float pX, final float pY, final Layout pLayout) {
		this(pX, pY, 0, 0, pLayout);
	}

	public UI_Panel(final float pX, final float pY,
			final float pPaddingX, final float pPaddingY,
			final Layout pLayout) {
		super(pX, pY);
		mPaddingX = pPaddingX;
		mPaddingY = pPaddingY;
		mLayout = pLayout;
	}

	public void add(final T pEntity) {
		this.attachChild(pEntity);
		final float x;
		final float y;

		final float last_x = (this.getCount() > 0) ? this.getLast().getX() + mPaddingX : 0;
		final float last_y = (this.getCount() > 0) ? this.getLast().getY() + mPaddingY : 0;
		final float last_w = (this.getCount() > 0) ? this.getLast().getWidth() : 0;
		final float last_h = (this.getCount() > 0) ? this.getLast().getHeight() : 0;

		switch (mLayout) {
			case HORIZONTAL:
				this.updateSize(
						mWidth + pEntity.getWidth() + ((this.getCount() > 0) ? mPaddingX : 0),
						pEntity.getHeight());
				x = last_x + last_w;
				y = (this.getHeight() - pEntity.getHeight()) / 2;
				break;
			case VERTICAL:
				this.updateSize(
						pEntity.getWidth(),
						mHeight + pEntity.getHeight() + ((this.getCount() > 0) ? mPaddingY : 0));
				x = (this.getWidth() - pEntity.getWidth()) / 2;
				y = last_y + last_h;
				break;
			default:
				x = 0;
				y = 0;
				break;
		}
		pEntity.setPosition(x, y);

		mList.add(pEntity);
	}

	public T get(int pIndex) {
		return mList.get(pIndex);
	}

	public int getCount() {
		return mList.size();
	}

	@Override
	protected void onDestroy() {
		for (T entity : mList) {
			entity.destroy();
		}
		mList.clear();
	}

	public T getLast() {
		if (mList.size() > 0) {
			return mList.get(mList.size() - 1);
		}
		return null;
	}

	private void updateSize(float pWidth, float pHeight) {
		if (pWidth > mWidth) { mWidth = pWidth; }
		if (pHeight > mHeight) { mHeight = pHeight; }
	}

	@Override
	public float getWidth() {
		return mWidth;
	}

	@Override
	public float getHeight() {
		return mHeight;
	}

	@Override
	public String toString() {
		return "Panel";
	}
}

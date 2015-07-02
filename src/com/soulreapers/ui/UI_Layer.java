/**
 * 
 */
package com.soulreapers.ui;

import org.andengine.entity.Entity;
import org.andengine.util.debug.Debug;

/**
 * @author chris
 *
 */
public class UI_Layer extends Entity implements IUserInterface {

	/**
	 * 
	 */
	public UI_Layer() {
		super();
	}

	/**
	 * @param pX
	 * @param pY
	 */
	public UI_Layer(float pX, float pY) {
		super(pX, pY);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.IUserInterface#cleanup()
	 */
	@Override
	public final void destroy() {
		this.onDestroy();
		this.detachSelf();
		this.dispose();
		Debug.d("Destroyed " + toString());
	}

	protected void onDestroy() {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.IUserInterface#getWidth()
	 */
	@Override
	public float getWidth() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.IUserInterface#getHeight()
	 */
	@Override
	public float getHeight() {
		return 0;
	}

	@Override
	public String toString() {
		return "Layer";
	}
}

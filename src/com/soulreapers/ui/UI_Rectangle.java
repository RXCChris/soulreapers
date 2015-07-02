/**
 * 
 */
package com.soulreapers.ui;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.ResourceManager;

/**
 * @author chris
 *
 */
public class UI_Rectangle extends Rectangle implements IUserInterface {

	public UI_Rectangle(float pX, float pY, float pWidth, float pHeight) {
		super(pX, pY, pWidth, pHeight, ResourceManager.getInstance().getVertexBufferObjectManager());
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

	@Override
	public String toString() {
		return "Rectangle";
	}
}

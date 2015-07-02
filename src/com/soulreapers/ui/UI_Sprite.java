/**
 * 
 */
package com.soulreapers.ui;

import org.andengine.entity.sprite.Sprite;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.ResourceManager;

/**
 * @author chris
 *
 */
public class UI_Sprite extends Sprite implements IUserInterface {
	public UI_Sprite(final int pResourceID) {
		super(0, 0,
				ResourceManager.getInstance().getTextureWidth(pResourceID),
				ResourceManager.getInstance().getTextureHeight(pResourceID),
				ResourceManager.getInstance().getTextureRegion(pResourceID),
				ResourceManager.getInstance().getVertexBufferObjectManager());
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

	public void onDestroy() {
		// nothing to do
	}

	@Override
	public String toString() {
		return "Sprite [" + this.getWidth() + "x" + this.getHeight()+"px]";
	}
}

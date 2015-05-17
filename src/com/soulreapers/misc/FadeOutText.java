/**
 * 
 */
package com.soulreapers.misc;

import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

import android.opengl.GLES20;

import com.soulreapers.core.ResourceManager;

/**
 * @author chris
 */
/**
 * The Object starts fading when attached.
 * <b>FadeOutText</b> is automatically destroyed its modifier is finished.
 */
public class FadeOutText extends Text {
	private static final int FONT_ID = ResourceManager.FONT_TEXT_ID;
	private FadeOutModifier mFadeOutModifier;

	/**
	 * @param pX
	 * @param pY
	 * @param pText
	 */
	public FadeOutText(float pX, float pY, final String pText, float pDuration) {
		super(pX, pY,
				ResourceManager.getInstance().getFont(FONT_ID),
				pText,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		this.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		mFadeOutModifier = new FadeOutModifier(pDuration) {
//			@Override
//			protected void onModifierFinished(IEntity pItem) {
//				Debug.d("fade out done: " + getEntityModifierCount());
//				mFadeOutDone = true;
//				dispose();
//			}
		};
//		mFadeOutModifier.setAutoUnregisterWhenFinished(false);
	}

	@Override
	public void onAttached() {
		super.onAttached();
		registerEntityModifier(mFadeOutModifier);
	}

//	@Override
//	public void dispose() {
//		if (mFadeOutDone) {
//			detachSelf();
//			super.dispose();
//		}
//	}
	public void done() {}
}

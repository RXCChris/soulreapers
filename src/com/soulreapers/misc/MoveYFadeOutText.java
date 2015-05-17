/**
 * 
 */
package com.soulreapers.misc;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.util.debug.Debug;

/**
 * @author chris
 *
 */
public class MoveYFadeOutText extends FadeOutText {
	MoveYModifier mMoveYModifier;
//	protected boolean mMoveDone = false;

	public MoveYFadeOutText(float pX, float pFromY, float pToY, String pText, float pDuration) {
		super(pX, pFromY, pText, pDuration);
		mMoveYModifier = new MoveYModifier(pDuration, pFromY, pToY) {
//			@Override
//			protected void onModifierFinished(IEntity pItem) {
//				Debug.d("move done: " + getEntityModifierCount());
//				mMoveDone = true;
//				dispose();
////				dispose();
//			}
		};
//		mMoveYModifier.setAutoUnregisterWhenFinished(false);
	}

	@Override
	public void onAttached() {
		super.onAttached();
		registerEntityModifier(mMoveYModifier);
	}

//	@Override
//	public void dispose() {
//		if (mMoveDone) {
//			super.dispose();
//		}
//	}
}

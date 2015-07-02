/**
 * 
 */
package com.soulreapers.ui;

import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.MoveYModifier;

import android.opengl.GLES20;

import com.soulreapers.core.FontManager.FontType;

/**
 * @author chris
 *
 */
public class UI_Damage extends UI_Label {
	private static final float FADE_OUT_DURATION = 2;
	private static final float POSITION_FROM_Y = 200;
	private static final float POSITION_TO_Y = 100;

	public UI_Damage(final int pDamage) {
		super(""+pDamage, FontType.FONT_TEXT_MEDIUM);
		this.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.registerEntityModifier(new MoveYModifier(FADE_OUT_DURATION, POSITION_FROM_Y, POSITION_TO_Y));
		this.registerEntityModifier(new FadeOutModifier(FADE_OUT_DURATION));
	}

	@Override
	public String toString() {
		return "Damage [" + this.getText() + "]";
	}
}

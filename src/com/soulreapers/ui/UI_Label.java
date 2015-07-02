/**
 * 
 */
package com.soulreapers.ui;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.FontManager.FontType;

/**
 * @author chris
 *
 */
public class UI_Label extends Text implements IUserInterface {
	private static final FontType DEFAULT_FONT_TYPE = FontType.FONT_TEXT_MEDIUM;

	public UI_Label(final String pText) {
		this(pText, DEFAULT_FONT_TYPE);
	}

	public UI_Label(final String pText, final FontType pFontType) {
		this(pText, pFontType, 0, 0);
	}

	public UI_Label(final String pText, final FontType pFontType, final int pMaxChar) {
		this(pText, pFontType, 0, 0, pMaxChar, HorizontalAlign.LEFT);
	}

	public UI_Label(final String pText, final FontType pFontType, final float pX, final float pY) {
		this(pText, pFontType, pX, pY, pText.length(), HorizontalAlign.LEFT);
	}

	public UI_Label(final String pText, final FontType pFontType, final float pX, final float pY, final int pMaxChar) {
		this(pText, pFontType, pX, pY, pMaxChar, HorizontalAlign.LEFT);
	}

	public UI_Label(final String pText, final FontType pFontType, final HorizontalAlign pHorizontalAlign) {
		this(pText, pFontType, 0, 0, pText.length(), pHorizontalAlign);
	}

	public UI_Label(final String pText, final FontType pFontType, final float pX, final float pY, final int pMaxChar, final HorizontalAlign pHorizontalAlign) {
		super(pX, pY,
				FontManager.getInstance().getFont(pFontType),
				pText,
				pMaxChar,
				new TextOptions(pHorizontalAlign),
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

	protected void onDestroy() {
		// nothing to do
	}

	@Override
	public String toString() {
		return "Label ["+this.getText()+"]";
	}
}

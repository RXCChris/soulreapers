/**
 * 
 */
package com.soulreapers.util;

import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;

/**
 * @author chris
 *
 */
public class SRButton extends ButtonSprite {
	public static final int DEFAULT_BUTTON_WIDTH = 280;
	public static final int DEFAULT_BUTTON_HEIGHT = 50;

	private static final int BUTTON_TEXTURE_ID = R.string.bt_normal;
	private static final int BUTTON_PRESSED_TEXTURE_ID = R.string.bt_pressed;
	private static final int BUTTON_DISABLED_TEXTURE_ID = R.string.bt_disabled;
	private static final int BUTTON_TEXTURE_WIDTH  = 114;
	private static final int BUTTON_TEXTURE_HEIGHT = 114;

	private static final int FONT_ID = ResourceManager.FONT_OPTION_ID;
	private static final int MAX_CHAR_LENGHT = 16;

	private Text mButtonText;
	private TextAlignment mAlignment = TextAlignment.MIDDLE_CENTER;
	private int mPaddingX = 0;
	private int mPaddingY = 0;

	public enum TextAlignment {
		TOP_LEFT,
		TOP_CENTER,
		TOP_RIGHT,
		MIDDLE_LEFT,
		MIDDLE_CENTER,
		MIDDLE_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_CENTER,
		BOTTOM_RIGHT
	};

	public SRButton(int pX, int pY, int pWidth, int pHeight, String pText, TextAlignment pAlignment) {
		super(pX, pY,
				ResourceManager.getInstance().loadTexture(BUTTON_TEXTURE_ID,
						BUTTON_TEXTURE_WIDTH, BUTTON_TEXTURE_HEIGHT),
				ResourceManager.getInstance().loadTexture(BUTTON_TEXTURE_ID,
						BUTTON_TEXTURE_WIDTH, BUTTON_TEXTURE_HEIGHT),
				ResourceManager.getInstance().loadTexture(BUTTON_TEXTURE_ID,
						BUTTON_TEXTURE_WIDTH, BUTTON_TEXTURE_HEIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mButtonText = new Text(0, 0,
				ResourceManager.getInstance().getFont(FONT_ID),
				pText, MAX_CHAR_LENGHT,
				new TextOptions(HorizontalAlign.LEFT),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final ButtonSprite pButtonSprite,
					final float pTouchAreaLocalX,
					final float pTouchAreaLocalY) {
				onPressed(pTouchAreaLocalX, pTouchAreaLocalY);
			}
		});
		this.setSize(pWidth, pHeight);
		this.setTextAlignment(pAlignment);
	}

	public SRButton(int pX, int pY, int pWidth, int pHeight, String pText) {
		this(pX, pY, pWidth, pHeight, pText, TextAlignment.MIDDLE_CENTER);
	}

	public SRButton(int pX, int pY, String pText) {
		this(pX, pY, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, pText, TextAlignment.MIDDLE_CENTER);
	}

	public void setTextAlignment(TextAlignment pAlignment) {
		setTextAlignment(pAlignment, 0, 0);
	}

	public void setTextAlignment(TextAlignment pAlignment, int pPaddingX, int pPaddingY) {
		switch (pAlignment) {
		case TOP_LEFT:
			mButtonText.setPosition(pPaddingX, pPaddingY);
			break;
		case TOP_CENTER:
			mButtonText.setPosition(mWidth / 2 - mButtonText.getWidth() / 2,
					pPaddingY);
			break;
		case TOP_RIGHT:
			mButtonText.setPosition(mWidth - mButtonText.getWidth() - pPaddingX,
					pPaddingY);
			break;
		case MIDDLE_LEFT:
			mButtonText.setPosition(pPaddingX,
					mHeight / 2 - mButtonText.getHeight() / 2);
			break;
		case MIDDLE_CENTER:
			mButtonText.setPosition(mWidth / 2 - mButtonText.getWidth() / 2,
					mHeight / 2 - mButtonText.getHeight() / 2);
			break;
		case MIDDLE_RIGHT:
			mButtonText.setPosition(mWidth - mButtonText.getWidth() - pPaddingX,
					mHeight / 2 - mButtonText.getHeight() / 2);
			break;
		case BOTTOM_LEFT:
			mButtonText.setPosition(pPaddingX,
					mHeight - mButtonText.getHeight() - pPaddingY);
			break;
		case BOTTOM_CENTER:
			mButtonText.setPosition(pPaddingX + mWidth / 2 - mButtonText.getWidth() / 2,
					mHeight - mButtonText.getHeight() - pPaddingY);
			break;
		case BOTTOM_RIGHT:
			mButtonText.setPosition(mWidth - mButtonText.getWidth() - pPaddingX,
					mHeight - mButtonText.getHeight() - pPaddingY);
			break;
		default:
			break;
		}
		mAlignment = pAlignment;
		mPaddingX = pPaddingX;
		mPaddingY = pPaddingY;
	}

	public Text getText() {
		return mButtonText;
	}

	public void setText(String pText) {
		mButtonText.setText(pText);
		setTextAlignment(mAlignment, mPaddingX, mPaddingY);
	}

	public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		// nothing to do
	}

	@Override
	public void onAttached() {
		this.attachChild(mButtonText);
		super.onAttached();
	}

	@Override
	public void onDetached() {
		this.detachChildren();
		super.onDetached();
	}

	@Override
	public void dispose() {
		mButtonText.dispose();
		super.dispose();
	}
}

/**
 * 
 */
package com.soulreapers.ui;

import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.core.ResourceManager;

/**
 * @author chris
 *
 */
public class UI_Button extends ButtonSprite implements IUserInterface {
	private static final float DEFAULT_BUTTON_WIDTH = 280;
	private static final float DEFAULT_BUTTON_HEIGHT = 50;

	private static final int BUTTON_TEXTURE_ID = R.string.bt_normal;
	private static final int BUTTON_PRESSED_TEXTURE_ID = R.string.bt_pressed;
	private static final int BUTTON_DISABLED_TEXTURE_ID = R.string.bt_disabled;

	private UI_Label mLabel;

	public UI_Button(final String pLabel) {
		this(pLabel, 0, 0);
	}

	public UI_Button(final String pLabel, final float pX, final float pY) {
		super(pX, pY,
				ResourceManager.getInstance().getTextureRegion(BUTTON_TEXTURE_ID),
				ResourceManager.getInstance().getTextureRegion(BUTTON_TEXTURE_ID),
				ResourceManager.getInstance().getTextureRegion(BUTTON_TEXTURE_ID),
				ResourceManager.getInstance().getVertexBufferObjectManager());

		this.setSize(DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);

		mLabel = new UI_Label(pLabel, FontType.FONT_OPTION_MEDIUM, HorizontalAlign.CENTER);
		this.attachChild(mLabel);
		final float label_x = (DEFAULT_BUTTON_WIDTH - mLabel.getWidth()) / 2;
		final float label_y = (DEFAULT_BUTTON_HEIGHT - mLabel.getHeight()) / 2;
		mLabel.setPosition(label_x, label_y);

		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(ButtonSprite pButtonSprite,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				UI_Button.this.onPressed();
			}
		});
	}

	protected void onPressed() { }

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
		mLabel.destroy();
	}

	@Override
	public String toString() {
		return "Button [" + mLabel.getText() + "]";
	}
}

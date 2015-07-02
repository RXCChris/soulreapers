/**
 * @author CChris
 */
package com.soulreapers.scene;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import android.opengl.GLES20;

import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.util.SRButton;

/**
 * This class displays a confirmation dialog with two choices.
 * The following example shows how to display this dialog on
 * the top of the current scene.
 * <pre>
 * {@code
 * MyScene.setChildScene(new ConfirmDialog() {
 *		public void onPositive() {
 *			// Whether positive answer is clicked
 *		}
 *		public void onNegative() {
 *			// Whether negative answer is clicked
 *		}
 * }, false, true, true);
 * </pre>
 */
@Deprecated
public class ConfirmDialog extends Scene {
	private static final int X_PADDING = 40;
	private static final int Y_PADDING = 30;
//	private static final int FONT_ID = ResourceManager.FONT_TEXT_ID;
	private static final String STRING_DEFAULT_MESSAGE = "Confirmer ?";
	private static final String STRING_YES = "Oui";
	private static final String STRING_NO = "Non";

	private Rectangle mBackground = new Rectangle(0, 0,
			GameConstants.CAMERA_WIDTH, GameConstants.CAMERA_HEIGHT,
			ResourceManager.getInstance().getVertexBufferObjectManager());
	Text mMessageText;
	SRButton mButtonPositive;
	SRButton mButtonNegative;

	/**
	 * Constructs a new <b>ConfirmDialog</b> with default messages,
	 * and sets a black background.
	 * @see ConfirmDialog(String, String, String, boolean)
	 */
	public ConfirmDialog() {
		this(STRING_DEFAULT_MESSAGE, STRING_YES, STRING_NO);
	}

	/**
	 * Constructs a new <code>ConfrimDialog</code> with a specified message.
	 * @param pMessage String to display as confirmation message.
	 * @see ConfirmDialog#ConfirmDialog(String, String, String, boolean)
	 * @see ConfirmDialog#ConfirmDialog()
	 */
	public ConfirmDialog(final String pMessage) {
		this(pMessage, STRING_YES, STRING_NO);
	}

	/**
	 * Constructs a new <b>ConfirmDialog</b> with custom messages.
	 * @param pMessage String to display as confirmation message.
	 * @param pPositive String to display as positive choice.
	 * @param pNegative String to display as negative choice.
	 * @param pBackgroundEnabled Set to <b>true</b> to enable background,
	 *        otherwise the background is transparent.
	 * @see ConfirmDialog()
	 */
	public ConfirmDialog(final String pMessage,
			final String pPositive,
			final String pNegative) {
		super();
		this.setBackgroundEnabled(false);
		mBackground.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
//		mBackground.setColor(Color.BLACK);
//		mBackground.setAlpha(0.75F);
		mBackground.setColor(GameConstants.UI.COLOR_BACKGROUND);

		mMessageText = new Text(0, 0,
				FontManager.getInstance().getFont(FontType.FONT_TEXT_MEDIUM),
				pMessage,
				new TextOptions(HorizontalAlign.CENTER),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mButtonPositive = new SRButton(0, 0, pPositive) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				ConfirmDialog.this.onPositive();
			}
		};
		mButtonNegative = new SRButton(0, 0, pNegative) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				ConfirmDialog.this.onNegative();
			}
		};

		final float centerX = GameConstants.CAMERA_WIDTH / 2;
		final float centerY = GameConstants.CAMERA_HEIGHT / 2;
		// Displays message in the centre of the screen
		mMessageText.setPosition(centerX - mMessageText.getWidth() / 2,
				centerY - mMessageText.getHeight() - Y_PADDING);
		mButtonPositive.setPosition(centerX - X_PADDING - mButtonPositive.getWidth(),
				centerY + Y_PADDING);
		mButtonNegative.setPosition(centerX + X_PADDING,
				centerY + Y_PADDING);

		this.attachChild(mBackground);
		this.attachChild(mMessageText);
		this.attachChild(mButtonPositive);
		this.attachChild(mButtonNegative);

		this.registerTouchArea(mButtonPositive);
		this.registerTouchArea(mButtonNegative);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		Debug.i("ConfirmDialog: New ConfirmDialog created.");
	}

	/**
	 * Called when the button with the positive choice is pressed.
	 * <p>
	 * Override this method to have a custom action.
	 * </p>
	 */
	protected void onPositive() {
		this.back();
	}

	/**
	 * Called when the button with the negative choice is pressed.
	 * <p>
	 * Override this method to have a custom action.
	 * </p>
	 */
	protected void onNegative() {
		this.back();
	}

	/**
	 * @see org.andengine.entity.scene.Scene#back()
	 */
	@Override
	public void back() {
		this.unregisterTouchArea(mButtonPositive);
		this.unregisterTouchArea(mButtonNegative);

		this.detachChildren();
		mBackground.dispose();
		mMessageText.dispose();
		mButtonPositive.dispose();
		mButtonNegative.dispose();

		this.detachSelf();
		this.dispose();

		super.back();
	}
}

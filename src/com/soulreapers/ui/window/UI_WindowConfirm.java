/**
 * 
 */
package com.soulreapers.ui.window;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.debug.Debug;

import android.opengl.GLES20;

import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.scene.UI_Scene;
import com.soulreapers.util.SRButton;

/**
 * @author chris
 *
 */
public class UI_WindowConfirm extends UI_Window {

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_Window#onEnabled()
	 */
	@Override
	protected void onEnabled() {
		((UI_Scene)this.getParent()).registerTouchArea(mButtonPositive);
		((UI_Scene)this.getParent()).registerTouchArea(mButtonNegative);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.window.UI_Window#onDisabled()
	 */
	@Override
	protected void onDisabled() {
		((UI_Scene)this.getParent()).unregisterTouchArea(mButtonPositive);
		((UI_Scene)this.getParent()).unregisterTouchArea(mButtonNegative);
	}

	private static final float X_PADDING = 40;
	private static final float Y_PADDING = 30;
	private static final String STRING_DEFAULT_MESSAGE = "Confirmer ?";
	private static final String STRING_YES = "Oui";
	private static final String STRING_NO = "Non";

	private Rectangle mBackground = new Rectangle(0, 0,
			GameConstants.CAMERA_WIDTH, GameConstants.CAMERA_HEIGHT,
			ResourceManager.getInstance().getVertexBufferObjectManager());


	private SRButton mButtonPositive;
	private SRButton mButtonNegative;

	/**
	 * Constructs a new <b>ConfirmDialog</b> with default messages,
	 * and sets a black background.
	 * @see ConfirmDialog(String, String, String, boolean)
	 */
	public UI_WindowConfirm(UI_Scene pScene) {
		this(pScene, STRING_DEFAULT_MESSAGE, STRING_YES, STRING_NO);
	}

	/**
	 * Constructs a new <code>ConfrimDialog</code> with a specified message.
	 * @param pMessage String to display as confirmation message.
	 * @see ConfirmDialog#ConfirmDialog(String, String, String, boolean)
	 * @see ConfirmDialog#ConfirmDialog()
	 */
	public UI_WindowConfirm(UI_Scene pScene, final String pMessage) {
		this(pScene, pMessage, STRING_YES, STRING_NO);
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
	public UI_WindowConfirm(UI_Scene pScene,
			final String pMessage,
			final String pPositive,
			final String pNegative) {
		super(pMessage);
		mBackground.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		mBackground.setColor(GameConstants.UI.COLOR_BACKGROUND);

		mButtonPositive = new SRButton(0, 0, pPositive) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				UI_WindowConfirm.this.onPositive();
			}
		};
		mButtonNegative = new SRButton(0, 0, pNegative) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				UI_WindowConfirm.this.onNegative();
			}
		};

		final float centerX = GameConstants.CAMERA_WIDTH / 2;
		final float centerY = GameConstants.CAMERA_HEIGHT / 2;
		// Displays message in the centre of the screen
		mLabel.setPosition(centerX - mLabel.getWidth() / 2,
				centerY - mLabel.getHeight() - Y_PADDING);
		mButtonPositive.setPosition(centerX - X_PADDING - mButtonPositive.getWidth(),
				centerY + Y_PADDING);
		mButtonNegative.setPosition(centerX + X_PADDING,
				centerY + Y_PADDING);

		this.attachChild(mBackground);
		this.attachChild(mLabel);
		this.attachChild(mButtonPositive);
		this.attachChild(mButtonNegative);


		Debug.i("ConfirmDialog: New ConfirmDialog created.");
	}

	/**
	 * Called when the button with the positive choice is pressed.
	 * <p>
	 * Override this method to have a custom action.
	 * </p>
	 */
	protected void onPositive() {

	}

	/**
	 * Called when the button with the negative choice is pressed.
	 * <p>
	 * Override this method to have a custom action.
	 * </p>
	 */
	protected void onNegative() {
		this.disable();
	}

	protected void onDestroy() {
		// TODO
	}
}

/**
 * @author CChris
 */
package com.soulreapers.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;

/**
 * This class displays a confirmation dialog with two choices.
 * The following example shows how to display this dialog on
 * the top of the current scene.
 * <pre>
 * {@code
 * // 'this' refers to the current scene
 * this.setChildScene(new ConfirmDialog() {
 *   public void onPositive() {
 *     // Whether positive answer is clicked
 *   }
 *   public void onNegative() {
 *     // Whether negative answer is clicked
 *   }
 * }, false, true, true);
 * }
 * </pre>
 */
public class ConfirmDialog extends Scene {
	private static final int X_PADDING = 100;
	private static final int Y_PADDING = 300;

	/**
	 * Constructs a new <b>ConfirmDialog</b> with custom messages.
	 * @param message String to display as confirmation message
	 * @param positive String to display as positive choice
	 * @param negative String to display as negative choice
	 * @param pBackgroundEnabled Set to <b>true</b> to enable background, otherwise the background is transparent
	 * @see ConfirmDialog()
	 */
	public ConfirmDialog(String message, String positive, String negative,
			boolean pBackgroundEnabled) {
		super();
		create(message, positive, negative, pBackgroundEnabled);
	}

	/**
	 * Constructs a new <b>ConfirmDialog</b> with default messages,
	 * and sets a black background.
	 * <p>
	 * See string.xml to view default messages.
	 * </p>
	 * @see ConfirmDialog(String, String, String, boolean)
	 */
	public ConfirmDialog() {
		super();
		create(ResourceManager.getInstance().getResourceString(R.string.mg_06),
				ResourceManager.getInstance().getResourceString(R.string.tb_yes),
				ResourceManager.getInstance().getResourceString(R.string.tb_no),
				false);
	}

	/**
	 * Creates a new <b>ConfirmDialog</b> with custom messages.
	 * <p>
	 * This is the actual method called by Constructors.
	 * </p>
	 * @param message String to display as confirmation message
	 * @param positive String to display as positive choice
	 * @param negative String to display as negative choice
	 * @param pBackgroundEnabled Set to <b>true</b> to enable background, otherwise the background is transparent
	 * @see ConfirmDialog(), ConfirmDialog(String, String, String, boolean)
	 */
	private void create(String message, String positive, String negative, boolean pBackgroundEnabled) {
		this.setBackgroundEnabled(pBackgroundEnabled);

		Text mMessageText = new Text(0, 0,
				ResourceManager.getInstance().getFont(R.string.ft_03),
				message,
				new TextOptions(HorizontalAlign.CENTER),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		Text mPositiveText = new Text(0, 0,
				ResourceManager.getInstance().getFont(R.string.ft_03),
				positive,
				new TextOptions(HorizontalAlign.CENTER),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					onPositive();
				}
				return false;
			}
		};
		Text mNegativeText = new Text(0, 0,
				ResourceManager.getInstance().getFont(R.string.ft_03),
				negative,
				new TextOptions(HorizontalAlign.CENTER),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					onNegative();
				}
				return false;
			}
		};

		Camera camera = SceneManager.getInstance().getCamera();
		// Displays message in the centre of the screen
		mMessageText.setPosition(camera.getCenterX() - mMessageText.getWidth() / 2,
				camera.getCenterY() - mMessageText.getHeight() / 2);
		mPositiveText.setPosition(camera.getCenterX() - X_PADDING - mPositiveText.getWidth(),
				Y_PADDING);
		mNegativeText.setPosition(camera.getCenterX() + X_PADDING,
				Y_PADDING);
		this.attachChild(mMessageText);
		this.attachChild(mPositiveText);
		this.attachChild(mNegativeText);
		this.registerTouchArea(mPositiveText);
		this.registerTouchArea(mNegativeText);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		Debug.i("ConfirmDialog: New ConfirmDialog created");
	}

	/**
	 * Method called when the user click on the positive answer.
	 * <p>
	 * Override this method to have a custom action.
	 * </p>
	 */
	protected void onPositive() {
		// Nothing to do
	}

	/**
	 * Method called when the user click on the negative answer.
	 * <p>
	 * Override this method to have a custom action.
	 * </p>
	 */
	protected void onNegative() {
		// Nothing to do
	}
}

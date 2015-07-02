/**
 * 
 */
package com.soulreapers.ui.window;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveXModifier;

import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.ui.UI_Label;
import com.soulreapers.ui.UI_Layer;

/**
 * @author chris
 *
 */
public abstract class UI_Window extends UI_Layer {
	private static final float DEFAULT_LABEL_X = 10;
	private static final float DEFAULT_LABEL_Y = 20;
	private static final float DEFAULT_MOVE_DURATION = 0.5f;

	protected final UI_Label mLabel;

	protected abstract void onEnabled();
	protected abstract void onDisabled();

	public UI_Window(final String pLabel) {
		super(GameConstants.CAMERA_WIDTH, 0);
		mLabel = new UI_Label(pLabel, FontType.FONT_TITLE_HUGE,
				DEFAULT_LABEL_X, DEFAULT_LABEL_Y);
		this.attachChild(mLabel);
	}

	public final void enable() {
		this.registerEntityModifier(new MoveXModifier(DEFAULT_MOVE_DURATION,
				-GameConstants.CAMERA_WIDTH, 0) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				UI_Window.this.onEnabled();
			}
		});
	}

	public final void disable() {
		this.registerEntityModifier(new MoveXModifier(DEFAULT_MOVE_DURATION,
				0, GameConstants.CAMERA_WIDTH) {
			@Override
			protected void onModifierFinished(IEntity pItem) {
				UI_Window.this.onDisabled();
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.IUserInterface#getWidth()
	 */
	@Override
	public float getWidth() {
		return GameConstants.CAMERA_WIDTH;
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.IUserInterface#getHeight()
	 */
	@Override
	public float getHeight() {
		return GameConstants.CAMERA_HEIGHT;
	}

	@Override
	protected void onDestroy() {
		mLabel.destroy();
		super.onDestroy();
	}

	@Override
	public String toString() {
		return "Window [" + mLabel.getText() + "]";
	}
}

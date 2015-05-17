/**
 * 
 */
package com.soulreapers.ui.slot;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

import com.soulreapers.core.ResourceManager;
import com.soulreapers.skill.Skill;


/**
 * @author chris
 *
 */
public class SlotSkill extends Slot<Skill> {
	private static final float RECTANGLE_OFFSET_X = 10;
	private static final float RECTANGLE_OFFSET_Y = 48;

	protected static final float RECTANGLE_WIDTH = 380;
	protected static final float RECTANGLE_HEIGHT = 36;

	private static final float TEXT_PADDING_X = 10;
	private static final int COST_PADDING_X = 300;

	private Text mTextName;
	private Text mTextCost;

	public SlotSkill(Skill pSkill) {
		super(RECTANGLE_OFFSET_X, RECTANGLE_OFFSET_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT, pSkill);

		mTextName = new Text(TEXT_PADDING_X, 0,
				ResourceManager.getInstance().getFont(FONT_TEXT_ID),
				pSkill.getName(),
				20,
				ResourceManager.getInstance().getVertexBufferObjectManager());

		mTextCost = new Text(COST_PADDING_X, 0,
				ResourceManager.getInstance().getFont(FONT_TEXT_ID),
				String.format("%02d", pSkill.getCost()),
				2,
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager());

		mTextName.setY(RECTANGLE_HEIGHT / 2 - mTextName.getHeight() / 2);
		mTextCost.setY(RECTANGLE_HEIGHT / 2 - mTextCost.getHeight() / 2);

		this.attachChild(mTextName);
		this.attachChild(mTextCost);
	}

//	@Override
//	public String getDescription() {
//		return mElement.getDescription();
//	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.slot.Slot#updateContent()
	 */
	@Override
	public void updateContent() {
		mTextName.setText(mElement.getName());
		mTextCost.setText(String.format("%02d", mElement.getCost()));
	}
}

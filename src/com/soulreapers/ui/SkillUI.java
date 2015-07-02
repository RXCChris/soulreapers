/**
 * Project Soul Reapers
 * Copyright (c) 2014 Chengwu Huang (dxcloud) <chengwhuang@gmail.com>
 *
 * This file is part of 'Soul Reapers'.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.soulreapers.ui;

import java.util.HashMap;
import java.util.Map.Entry;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.Text;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.skill.Skill;
import com.soulreapers.skill.SkillGesture;

/**
 * @author chris
 *
 */
@Deprecated
public class SkillUI extends Entity {
//	private static final int SKILL_FONT = R.string.ft_text;
	private static final int RECTANGLE_X = 16;
	private static final int RECTANGLE_Y = 286;
	private static final int RECTANGLE_WIDTH = 294;
	private static final int RECTANGLE_HEIGHT = 118;

//	private static final int FONT_ID = ResourceManager.FONT_STATS_ID;
	private static final String STRING_EMPTY = "---";
	private static final int PADDING_Y = 20;
	private static final int OFFSET_X = RECTANGLE_X + 10;
	private static final int OFFSET_Y = RECTANGLE_Y + 10;
	private static final int PADDING_NAME = 40;
	private static final int PADDING_COST = 240;



	private Rectangle mBackground = new Rectangle(RECTANGLE_X, RECTANGLE_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT,
			ResourceManager.getInstance().getVertexBufferObjectManager());

	private class SkillText {
		public Text mGesture;
		public Text mName;
		public Text mCost;

		public SkillText(Text pGesture, Text pName, Text pCost) {
			mGesture = pGesture;
			mName = pName;
			mCost = pCost;
		}
	}

	private HashMap<SkillGesture, SkillText> mSkillTextMap = new HashMap<SkillGesture, SkillText>();

	public SkillUI(final BattleCharacter pPlayableCharacter) {
		mBackground.setColor(0.2f, 0.2f, 0.2f, 0.6f);
//		int i = 0;
//		for (SkillGesture gesture : SkillGesture.values()) {
//			insertSkillText(OFFSET_X, OFFSET_Y + PADDING_Y * (i++), gesture, pPlayableCharacter.getOffensiveSkill(gesture));
//		}
	}

	private void insertSkillText(int pX, int pY, SkillGesture pGesture, Skill pSkill) {
		String skillName = STRING_EMPTY;
		int skillCost = 0;
		if (pSkill != null) {
			skillName = pSkill.getName();
			skillCost = pSkill.getCost();
		} else {
			Debug.i("Can t find skill associated with " + pGesture.toString());
		}

		Text gesture = new Text(pX, pY, FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL), pGesture.toString(), ResourceManager.getInstance().getVertexBufferObjectManager());
		Text name = new Text(pX + PADDING_NAME, pY, FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL), skillName, 24, ResourceManager.getInstance().getVertexBufferObjectManager());
		Text cost = new Text(pX + PADDING_COST, pY, FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL), String.format("%2d", skillCost), ResourceManager.getInstance().getVertexBufferObjectManager());
		mSkillTextMap.put(pGesture, new SkillText(gesture, name, cost));

	}

	@Override
	public void onAttached() {
		this.attachChild(mBackground);
		for (SkillText text : mSkillTextMap.values()) {
			this.attachChild(text.mGesture);
			this.attachChild(text.mName);
			this.attachChild(text.mCost);
		}
		super.onAttached();
	}

	@Override
	public void onDetached() {
		for (SkillText text : mSkillTextMap.values()) {
			text.mGesture.detachSelf();
			text.mName.detachSelf();
			text.mCost.detachSelf();
		}
		this.detachChildren();
		super.onDetached();
	}

	@Override
	public void dispose() {
		for (SkillText text : mSkillTextMap.values()) {
			text.mGesture.dispose();
			text.mName.dispose();
			text.mCost.dispose();
		}
		super.dispose();
	}

	@Override
	public void setVisible(boolean pVisible) {
		mBackground.setVisible(pVisible);
		for (SkillText text : mSkillTextMap.values()) {
			text.mGesture.setVisible(pVisible);
			text.mName.setVisible(pVisible);
			text.mCost.setVisible(pVisible);
		}
		super.setVisible(pVisible);
	}

	public void update(BattleCharacter pPlayableCharacter) {
		for (Entry<SkillGesture, SkillText> entry : mSkillTextMap.entrySet()) {
//			Skill skill = pPlayableCharacter.getOffensiveSkill(entry.getKey());
//			if (skill != null) {
//				entry.getValue().mName.setText(skill.getName());
//				entry.getValue().mCost.setText(String.format("%2d", skill.getCost()));
//			} else {
//				entry.getValue().mName.setText(STRING_EMPTY);
//				entry.getValue().mCost.setText(String.format("%2d", 0));
//			}
		}
	}
}

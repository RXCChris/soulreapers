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
package com.soulreapers.skill;

import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.object.item.ItemBase;
import com.soulreapers.object.item.ItemBase.IEquipable;;

/**
 * @author dxcloud
 *
 */
public abstract class Skill extends ItemBase implements ISkill, IEquipable {
//	private final String mName;
//	private final String mDescription;
	private final int mCost;

	public Skill(int pSkillId, final String pName, final String pDescription, int pCost) {
		super(pSkillId, ItemType.SKILL, pName, pDescription);
		mCost = pCost;
//		mName = pName;
//		mDescription = pDescription;
	}

	public int getCost() {
		return mCost;
	}

	@Override
	public void onEquiped(Reaper pReaper) {
		this.decrementAvailability(1);
	}

	@Override
	public void onRemoved(Reaper pReaper) {
		this.incrementAvailability(1);
	}

//	public String getName() {
//		return mName;
//	}
//
//	public String getDescription() {
//		return mDescription;
//	}

	/* (non-Javadoc)
	 * @see com.soulreapers.skill.ISkill#onSkillStarted(com.soulreapers.object.character.PlayableCharacter, com.soulreapers.object.character.PlayableCharacter)
	 */
//	@Override
//	public void onSkillStarted(BattleCharacter pUser,
//			BattleCharacter pTarget) {
//		// Nothing to do
//	}

	/* (non-Javadoc)
	 * @see com.soulreapers.skill.ISkill#onSkillFinished(com.soulreapers.object.character.PlayableCharacter, com.soulreapers.object.character.PlayableCharacter)
	 */
//	@Override
//	public void onSkillFinished(BattleCharacter pUser,
//			BattleCharacter pTarget) {
//		// Nothing to do
//	}
//
//	public int getCost() {
//		return 0;
//	}
}

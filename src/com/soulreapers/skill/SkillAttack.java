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


import org.andengine.util.debug.Debug;

import com.soulreapers.misc.Stat;
import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.GameCharacter.CharacterType;
import com.soulreapers.object.item.Effect.ValueType;
import com.soulreapers.object.item.EffectAttack;
import com.soulreapers.util.Util;

/**
 * @author dxcloud
 *
 */
public class SkillAttack extends Skill {
	public enum SkillAttackType {
		SLASHING,
		PIERCING,
		BLUDGEONING
	}
//	private int mBaseDamage;
//	private int mCost;

//	private int mDamage = 0;
	private final SkillGesture mGesture;

	/**
	 * @param pName
	 * @param pDescription
	 */
	public SkillAttack(int pSkillId,
			final SkillGesture pGesture,
			final String pName,
			final String pDescription,
			final int pBaseDamage,
			final int pCost) {
		super(pSkillId, pName, pDescription, pCost);
//		mBaseDamage = pBaseDamage;
//		mCost = pCost;
		mGesture = pGesture;
		mEffect = new EffectAttack(pBaseDamage, CharacterType.REMNANT, ValueType.DIRECT);
	}

	public static final SkillAttack EMPTY = new SkillAttack(0, null, "Empty", "", 0, 0);

	public SkillGesture getSkillGesture() {
		return mGesture;
	}

	@Override
	public void onSkillStarted(BattleCharacter pUser, BattleCharacter pTarget) {
//		int extra = pUser.getAttributes().getCurrent(AttributeType.COURAGE)
//				- pTarget.getAttributes().getCurrent(AttributeType.PRUDENCE);
//		if (extra >= 0) {
//			mDamage = mBaseDamage + Util.random(0, extra);
//		} else {
//			mDamage = mBaseDamage - Util.random(0, -extra);
//		}
//		pTarget.receiveDamage(mDamage);
//		Debug.d(pTarget.toString() + " receives dmg : " + mDamage);
		mEffect.apply(pUser, pTarget);
	}

	public int getDamage() {
//		return mDamage;
		return 0;
	}

	public int getOverkillDamage() {
//		return mBaseDamage + Util.random(0, mBaseDamage);
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.skill.ISkill#onSkillFinished(com.soulreapers.object.character.BattleCharacter, com.soulreapers.object.character.BattleCharacter)
	 */
	@Override
	public void onSkillFinished(BattleCharacter pUser, BattleCharacter pTarget) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public int getCost() {
//		return mCost;
//	}
}

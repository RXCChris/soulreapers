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
package com.soulreapers.object.character;

import java.util.ArrayList;
import java.util.HashMap;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.Attributes;
import com.soulreapers.misc.Attributes.AttributeType;
import com.soulreapers.object.item.BuffEffect;
import com.soulreapers.object.item.Effect;
import com.soulreapers.skill.SkillAttack;
import com.soulreapers.skill.Skill;
import com.soulreapers.skill.SkillGesture;

/**
 * 
 * @author dxcloud
 *
 */
public abstract class BattleCharacter extends GameCharacter {
	protected HashMap<SkillGesture, SkillAttack> mSkillMap = new HashMap<SkillGesture, SkillAttack>();
	protected Attributes mAttributes = new Attributes();

	protected final int mIconId;

	protected ArrayList<Effect> mBuffEffectList = new ArrayList<Effect>();

	public BattleCharacter(String pName, int pIllustrationId, int pIconId, CharacterType pCharacterType) {
		super(pName, pIllustrationId, pCharacterType);
		mIconId = pIconId;
	}

	public int getIconId() {
		return mIconId;
	}

	public SkillAttack getOffensiveSkill(SkillGesture pGesture) {
		return mSkillMap.get(pGesture);
	}

	public Attributes getAttributes() {
		return mAttributes;
	}

	public boolean isDead() {
		return (mAttributes.isCurrentLessThan(AttributeType.SOUL, 0));
	}

	public void receiveDamage(int pAmount) {
		mAttributes.decreaseCurrent(AttributeType.SOUL, pAmount);
		Debug.i("current SP="+mAttributes.getCurrent(AttributeType.SOUL));
	}

	public abstract void onDie();

	private void resetAttributeModifiers() {
		mAttributes.resetBonus();
		for (Effect modifier : mBuffEffectList) {
			modifier.apply(null, this);
		}
	}

	public void addEffect(final Effect pEffect) {
		mBuffEffectList.add(pEffect);
		this.resetAttributeModifiers();
	}

	public void removeEffect(final Effect pEffect) {
		mBuffEffectList.remove(pEffect);
		this.resetAttributeModifiers();
	}

//	public abstract BattleCharacter instanciate();
}

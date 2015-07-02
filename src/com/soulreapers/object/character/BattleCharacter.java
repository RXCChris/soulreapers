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

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.CharacterParameters;
import com.soulreapers.misc.CharacterParameters.AttributeType;

/**
 *
 * @author dxcloud
 *
 */
public abstract class BattleCharacter extends GameCharacter {
	protected final int mIconID;
	protected CharacterParameters mParameters = new CharacterParameters();
	protected ArrayList<ParameterModifier> mModifiers = new ArrayList<ParameterModifier>();

	public BattleCharacter(final int pID,
			final CharacterType pCharacterType,
			final String pName,
			final int pIllustrationID,
			final int pIconID) {
		super(pID, pCharacterType, pName, pIllustrationID);
		mIconID = pIconID;
	}

	public int getIconID() {
		return mIconID;
	}

	public CharacterParameters getParameters() {
		return mParameters;
	}

	public boolean isDead() {
		return (mParameters.isCurrentLessThan(AttributeType.SOUL, 0));
	}

	public void receiveDamage(int pAmount) {
		mParameters.decreaseCurrent(AttributeType.SOUL, pAmount);
		Debug.i("current SP="+mParameters.getCurrent(AttributeType.SOUL));
	}

	public abstract void onDie();

	public void removeAllModifiers() {
		for (ParameterModifier modifier : mModifiers) {
			modifier.remove(this.mParameters);
		}
		mModifiers.clear();
	}

	public void addModifier(final ParameterModifier pModifier) {
		mModifiers.add(pModifier);
		pModifier.apply(this.mParameters);
	}

	public void removeModifier(final ParameterModifier pModifier) {
		pModifier.remove(this.mParameters);
		mModifiers.remove(pModifier);
	}

//	public abstract BattleCharacter instanciate();
}

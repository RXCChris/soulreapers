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

import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.misc.OverkillListener;

/**
 * @author chris
 *
 */
public class Remnant extends BattleCharacter {
	public enum RemnantType {
		BEAST,
		INSECT,
		DRAGON,
		HUMANOID,
		MACHINE,
		DEAMON,
		PLANT,
		DEITY
	}
	private final RemnantType mRemnantType;

	/**
	 * @param pName
	 * @param pResId
	 */
	public Remnant(final int pID,
			final String pName,
			final int pIllustrationID,
			final int pIconID,
			final RemnantType pRemnantType) {
		super(pID, CharacterType.REMNANT, pName, pIllustrationID, pIconID);

		mRemnantType = pRemnantType;
	}

	public RemnantType getRemnantType() {
		return mRemnantType;
	}

//	@Override
//	public Remnant clone() throws CloneNotSupportedException {
//		return (Remnant) super.clone();
//	}

//	@Override
//	public BattleCharacter instanciate() {
//		Remnant copy = new Remnant(mName, mResId);
//		copy.mAttributes.setLevel(mAttributes.getLevel());
//		for (AttributeType type : AttributeType.values()) {
//			copy.mAttributes.setBase(type, mAttributes.getBase(type));
//			copy.mAttributes.setBonus(type, mAttributes.getBonus(type));
//		}
//		// TODO
//		// Copy skill, etc.
//		return copy;
//	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.character.PlayableCharacter#onDie()
	 */
	@Override
	public void onDie() {
//		if (mOverkillListener != null) {
//			mOverkillListener.onOverkillStarted();
//		}
	}
	

}

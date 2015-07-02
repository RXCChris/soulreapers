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


public abstract class GameCharacter {
	public enum CharacterType {
		REAPER,
		REMNANT,
		NPC
	}

	protected final int mID;
	protected final String mName;
	protected final CharacterType mCharacterType;
	protected final int mIllustrationID;

	public GameCharacter(final int pID,
			final CharacterType pCharacterType,
			final String pName,
			final int pIllustrationID) {
		mID = pID;
		mCharacterType = pCharacterType;
		mName = pName;
		mIllustrationID = pIllustrationID;
	}

	public int getID() {
		return mID;
	}
	/**
	 * @return The name of the character
	 */
	public String getName() {
		return mName;
	}

	public int getIllustrationID() {
		return mIllustrationID;
	}

	public CharacterType getCharacterType() {
		return mCharacterType;
	}
}

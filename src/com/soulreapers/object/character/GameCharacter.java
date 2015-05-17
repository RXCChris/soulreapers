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

import org.andengine.entity.sprite.Sprite;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;

public abstract class GameCharacter extends Sprite {
	public enum CharacterType {
		REAPER,
		REMNANT,
		NPC
	}

	private static final int SPRITE_CHARACTER_WIDTH = 320;
	private static final int SPRITE_CHARACTER_HEIGHT = 480;

	protected final String mName;
	protected final CharacterType mCharacterType;
	protected final int mIllustrationId;

	public GameCharacter(final String pName, final int pResId, CharacterType pCharacterType) {
		super(0, 0,
				SPRITE_CHARACTER_WIDTH,
				SPRITE_CHARACTER_HEIGHT,
				ResourceManager.getInstance().loadTexture(pResId,
						GameConstants.CHARACTER_SPRITE_WIDTH,
						GameConstants.CHARACTER_SPRITE_HEIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mName = pName;
		mCharacterType = pCharacterType;
		mIllustrationId = pResId;
	}

	/**
	 * @return The name of the character
	 */
	public String getName() {
		return mName;
	}

	public int getIllustrationId() {
		return mIllustrationId;
	}

	public CharacterType getCharacterType() {
		return mCharacterType;
	}

//	public abstract T instanciate();
}

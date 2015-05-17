/**
 * 
 */
package com.soulreapers.object.character;

import java.util.HashMap;

import com.soulreapers.R;
import com.soulreapers.object.character.reaper.Reaper;

/**
 * @author dxcloud
 *
 */
public class CharacterCollection {
	private static final CharacterCollection INSTANCE = new CharacterCollection();
	private HashMap<String, GameCharacter> mCharacterMap = new HashMap<String, GameCharacter>();

	public static CharacterCollection getInstance() {
		return INSTANCE;
	}

	private CharacterCollection() {
		String name = "Dante";
		mCharacterMap.put(name, new Reaper(name, R.string.pc_01, R.string.ic_01));
		mCharacterMap.put("Chiroptera", new Remnant("Chiroptera", R.string.pc_e01, R.string.ic_e01));
	}

	public GameCharacter getCharacter(String name) {
		return mCharacterMap.get(name);
	}

	
}

/**
 * 
 */
package com.soulreapers.object.item;

import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.GameCharacter.CharacterType;

/**
 * @author chris
 *
 */
public abstract class Effect {

	protected final float mBaseValue;
	protected final CharacterType mTargetType;
	protected final ValueType mValueType;

	public enum ValueType {
		PROPORTIONAL,
		DIRECT
	}

	/**
	 * 
	 */
	public Effect(float pBaseValue, CharacterType pTargetType, ValueType pValueType) {
		mBaseValue = pBaseValue;
		mTargetType = pTargetType;
		mValueType = pValueType;
	}

	public float getBaseValue() {
		return mBaseValue;
	}

	public ValueType getValueType() {
		return mValueType;
	}

	public CharacterType getTargetType() {
		return mTargetType;
	}

	public abstract void apply(BattleCharacter pUser, BattleCharacter pTarget);
//	public abstract void cancel(BattleCharacter pUser, BattleCharacter pTarget);

}

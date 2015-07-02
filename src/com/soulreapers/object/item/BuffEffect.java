/**
 * 
 */
package com.soulreapers.object.item;

import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.GameCharacter.CharacterType;

/**
 * @author chris
 *
 */
public class BuffEffect extends Effect {
	private AttributeType mAttributeType;

	/**
	 * @param pBaseValue
	 * @param pTargetType
	 */
	public BuffEffect(float pBaseValue, CharacterType pTargetType, AttributeType pAttributeType) {
		super(pBaseValue, pTargetType, ValueType.PROPORTIONAL);
		mAttributeType = pAttributeType;
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.Effect#apply(com.soulreapers.object.character.BattleCharacter, com.soulreapers.object.character.BattleCharacter)
	 */
	@Override
	public void apply(BattleCharacter pUser, BattleCharacter pTarget) {
//		float value = mBaseValue;
//		if (mValueType == ValueType.PROPORTIONAL) {
//			value *= pTarget.getAttributes().getBase(mAttributeType);
//		}
//		pTarget.getAttributes().increaseBonus(mAttributeType, (int) value);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.Effect#cancel(com.soulreapers.object.character.BattleCharacter, com.soulreapers.object.character.BattleCharacter)
	 */

}

/**
 * 
 */
package com.soulreapers.object.item;

import com.soulreapers.misc.Attributes.AttributeType;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.GameCharacter.CharacterType;

/**
 * @author chris
 *
 */
public class HealEffect extends Effect {

	/**
	 * @param pBaseValue
	 */
	public HealEffect(int pBaseValue) {
		super(pBaseValue, CharacterType.REAPER, ValueType.DIRECT);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.Effect#apply(com.soulreapers.object.character.BattleCharacter, com.soulreapers.object.character.BattleCharacter)
	 */
	@Override
	public void apply(BattleCharacter pUser, BattleCharacter pTarget) {
		float value = mBaseValue;
		if (getValueType() == ValueType.PROPORTIONAL) {
			value *= pTarget.getAttributes().getTotal(AttributeType.SOUL);
		}
//		final float value = 
		pTarget.getAttributes().increaseCurrent(AttributeType.SOUL, (int) value);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.Effect#cancel(com.soulreapers.object.character.BattleCharacter, com.soulreapers.object.character.BattleCharacter)
	 */
//	@Override
//	public void cancel(BattleCharacter pUser, BattleCharacter pTarget) {
//		// nothing to do
//	}

}

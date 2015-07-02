/**
 * 
 */
package com.soulreapers.object.item;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.GameCharacter.CharacterType;
import com.soulreapers.util.Util;

/**
 * @author chris
 *
 */
public class EffectAttack extends Effect {

	/**
	 * @param pBaseValue
	 * @param pTargetType
	 * @param pValueType
	 */
	public EffectAttack(float pBaseValue, CharacterType pTargetType,
			ValueType pValueType) {
		super(pBaseValue, pTargetType, pValueType);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.object.item.Effect#apply(com.soulreapers.object.character.BattleCharacter, com.soulreapers.object.character.BattleCharacter)
	 */
	@Override
	public void apply(BattleCharacter pUser, BattleCharacter pTarget) {
//		int mDamage = 0;
//		int extra = pUser.getAttributes().getCurrent(AttributeType.COURAGE)
//				- pTarget.getAttributes().getCurrent(AttributeType.PRUDENCE);
//		if (extra >= 0) {
//			mDamage = (int) mBaseValue + Util.random(0, extra);
//		} else {
//			mDamage = (int) mBaseValue - Util.random(0, -extra);
//		}
//		pTarget.receiveDamage(mDamage);
//		Debug.d(pTarget.toString() + " receives dmg : " + mDamage);
	}

}

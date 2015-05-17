/**
 * 
 */
package com.soulreapers.skill;

import com.soulreapers.object.character.BattleCharacter;

/**
 * @author chris
 *
 */
public interface ISkill {
	public void onSkillStarted(BattleCharacter pUser, BattleCharacter pTarget);
	public void onSkillFinished(BattleCharacter pUser, BattleCharacter pTarget);
}

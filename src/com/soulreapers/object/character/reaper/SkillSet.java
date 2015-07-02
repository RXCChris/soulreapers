/**
 * 
 */
package com.soulreapers.object.character.reaper;

import java.util.HashMap;

import com.soulreapers.skill.SkillAttack;
import com.soulreapers.skill.SkillGesture;

/**
 * @author chris
 *
 */
public class SkillSet {
//	private final Reaper mReaper;
	private static final int NUM_SKILL_SLOT = SkillGesture.values().length;
	private HashMap<SkillGesture, SkillAttack> mSkillAttackMap =
			new HashMap<SkillGesture, SkillAttack>(NUM_SKILL_SLOT);
	// TODO Skill defense
	// TODO Skill support

	/**
	 * 
	 */
	public SkillSet() {
//		for (SkillGesture gesture : SkillGesture.values()) {
//			mSkillAttackMap.put(gesture, SkillAttack.EMPTY);
//		}
//		mReaper = pReaper;
	}

	public boolean equipSkillAttack(Reaper pReaper, SkillAttack pSkill) {
		if (pSkill.getAvailability() >= 1) {
			mSkillAttackMap.put(pSkill.getSkillGesture(), pSkill).onRemoved(pReaper);
			pSkill.onEquiped(pReaper);
			return true;
		}
		return false;
	}

	public SkillAttack removeSkillAttack(Reaper pReaper, SkillGesture pGesture) {
		SkillAttack removed = mSkillAttackMap.remove(pGesture);
		removed.onRemoved(pReaper);
		return removed;
	}

	public SkillAttack getSkillAttack(SkillGesture pGesture) {
		return mSkillAttackMap.get(pGesture);
	}
}

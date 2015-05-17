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
	private final Reaper mReaper;
	private HashMap<SkillGesture, SkillAttack> mSkillAttackMap = new HashMap<SkillGesture, SkillAttack>();
	// TODO Skill defense
	// TODO Skill support

	/**
	 * 
	 */
	public SkillSet(Reaper pReaper) {
		for (SkillGesture gesture : SkillGesture.values()) {
			mSkillAttackMap.put(gesture, SkillAttack.EMPTY);
		}
		mReaper = pReaper;
	}

	public boolean equipSkillAttack(SkillAttack pSkill) {
		if (pSkill.getAvailability() >= 1) {
			mSkillAttackMap.put(pSkill.getSkillGesture(), pSkill).onRemoved(mReaper);
			pSkill.onEquiped(mReaper);
			return true;
		}
		return false;
	}

	public SkillAttack removeSkillAttack(SkillGesture pGesture) {
		SkillAttack removed = mSkillAttackMap.remove(pGesture);
		removed.onRemoved(mReaper);
		return removed;
	}

	public SkillAttack getSkillAttack(SkillGesture pGesture) {
		return mSkillAttackMap.get(pGesture);
	}
}

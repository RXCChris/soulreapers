/**
 * 
 */
package com.soulreapers.ia;

import java.util.HashMap;
import java.util.Map.Entry;

import com.soulreapers.misc.Stat;
import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.BattleObject;
import com.soulreapers.skill.Skill;

/**
 * @author dxcloud
 * TODO
 */
public class IntelligentAgent {
//	public static enum Condition {
//		TARGET_IN_SIGHT {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return isWithinSurface(pUser, pTarget, pUser.getCharacter().getAttributes().getCurrent(AttributeType.TEMPERANCE));
//			}
//		},
//		TARGET_ADJACENT {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return isWithinSurface(pUser, pTarget, 0);
//			}
//			
//		},
////		TARGET_MORE_LEVEL {
////			@Override
////			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
////				return (pTarget.getCharacter().getLevel() > pUser.getCharacter().getLevel());
////			}
////		},
//		TARGET_MORE_HEALTH {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return (pTarget.getCharacter().getAttributes().getCurrent(AttributeType.SOUL) > pUser.getCharacter().getAttributes().getCurrent(AttributeType.SOUL));
//			}
//		},
//		TARGET_MORE_COURAGE {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return (pTarget.getCharacter().getAttributes().getCurrent(AttributeType.COURAGE) > pUser.getCharacter().getAttributes().getCurrent(AttributeType.COURAGE));
//			}
//		},
//		TARGET_MORE_PRUDENCE {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return (pTarget.getCharacter().getAttributes().getCurrent(AttributeType.PRUDENCE) > pUser.getCharacter().getAttributes().getCurrent(AttributeType.PRUDENCE));
//			}
//		},
//		TARGET_MORE_TEMPERANCE {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return (pTarget.getCharacter().getAttributes().getCurrent(AttributeType.TEMPERANCE) > pUser.getCharacter().getAttributes().getCurrent(AttributeType.TEMPERANCE));
//			}
//		},
//		TARGET_MORE_JUSTICE {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return (pTarget.getCharacter().getAttributes().getCurrent(AttributeType.JUSTICE) > pUser.getCharacter().getAttributes().getCurrent(AttributeType.JUSTICE));
//			}
//		},
//		TARGET_HEALTH_LESS_90 {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return isFeatureLess(pTarget, AttributeType.SOUL, 0.9f);
//			}
//		},
//		TARGET_HEALTH_LESS_50 {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return isFeatureLess(pTarget, AttributeType.SOUL, 0.5f);
//			}
//		},
//		TARGET_HEALTH_LESS_10 {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return isFeatureLess(pTarget, AttributeType.SOUL, 0.1f);
//			}
//		},
//		SELF_HEALTH_LESS_90 {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return isFeatureLess(pUser, AttributeType.SOUL, 0.9f);
//			}
//		},
//		SELF_HEALTH_LESS_50 {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return isFeatureLess(pUser, AttributeType.SOUL, 0.5f);
//			}
//		},
//		SELF_HEALTH_LESS_10 {
//			@Override
//			public boolean isOK(BattleObject pUser, BattleObject pTarget) {
//				return isFeatureLess(pUser, AttributeType.SOUL, 0.1f);
//			}
//		}
//		;
//		public abstract boolean isOK(BattleObject pUser, BattleObject pTarget);
//		private static boolean isWithinSurface(BattleObject pUser, BattleObject pTarget, int pMove) {
//			if (pTarget.getCurrentIndexX() > (pUser.getCurrentIndexX() + pMove + 1)) {
//				return false;
//			}
//			if (pTarget.getCurrentIndexX() < (pUser.getCurrentIndexX() - pMove - 1)) {
//				return false;
//			}
//			if (pTarget.getCurrentIndexY() > (pUser.getCurrentIndexY() + pMove + 1)) {
//				return false;
//			}
//			if (pTarget.getCurrentIndexY() < (pUser.getCurrentIndexY() - pMove - 1)) {
//				return false;
//			}
//			return true;
//		}
//		private static boolean isFeatureLess(BattleObject pTarget, AttributeType pFeature, float pValue) {
//			return (pTarget.getCharacter().getAttributes().getCurrent(pFeature) <
//					(pTarget.getCharacter().getAttributes().getBase(pFeature) * pValue));
//		}
//	}
//	private HashMap<Condition, Skill> mIntelligentMap = new HashMap<Condition, Skill>();
//
//	public IntelligentAgent() { }
//
//	public void addCondition(IntelligentAgent.Condition pCondition, Skill pSkill) {
//		mIntelligentMap.put(pCondition, pSkill);
//	}
//
//	public void execute(BattleObject pUser, BattleObject pTarget) {
//		for (Entry<Condition, Skill> entry : mIntelligentMap.entrySet()) {
//			if (entry.getKey().isOK(pUser, pTarget)) {
//				entry.getValue().onSkillStarted(pUser.getCharacter(), pTarget.getCharacter());
//				return;
//			}
//		}
//	}
}

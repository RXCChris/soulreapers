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
package com.soulreapers.object.character.reaper;

import org.andengine.util.debug.Debug;

import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.skill.SkillAttack;
import com.soulreapers.skill.SkillGesture;

/**
 * 
 * @author dxcloud
 *
 */
public class Reaper extends BattleCharacter {
//	private static final int COMBAT_ITEM_CAPACITY = 5;

//	private ArrayList<CombatItem> mCombatItemList = new ArrayList<CombatItem>();

	private Equipment mEquipment = new Equipment();
	private SkillSet mSkillSet = new SkillSet();

//	private Skill mSkill;

	public Reaper(final int pID, final String pName, final int pIllustrationID, final int pIconID) {
		super(pID, CharacterType.REAPER, pName, pIllustrationID, pIconID);
//		mSkill = new OffensiveSkill("Combo horizontal", "Effectue une attaque horizontal", 20, 1);
//		mSkillMap.put(SkillGesture.SINGLE_TAP, SkillCollection.getInstance().getOffensiveSkill(SkillCollection.O.SLAP_SHOT));
//		mSkillMap.put(SkillGesture.DOUBLE_TAP, SkillCollection.getInstance().getSkill(SkillCollection.O.DOUBLE_SLASH));
//		mSkillMap.put(SkillGesture.SWIPE_UP, SkillCollection.getInstance().getOffensiveSkill(SkillCollection.O.UPPER_SLASH));
//		mSkillMap.put(SkillGesture.SWIPE_DOWN, SkillCollection.getInstance().getOffensiveSkill(SkillCollection.O.AERIAL_DIVE));
//		mSkillMap.put(SkillGesture.SWIPE_LEFT, SkillCollection.getInstance().getOffensiveSkill(SkillCollection.O.AERIAL_SWEEP));
//		super.updateSkill();

//		for (int i = 0; i < GameConstants.REAPER.COMBAT_ITEM_CAPACITY; ++i) {
//			mCombatItemList.add(CombatItem.EMPTY);
//		}
//		mEquipment = new Equipment();
//		mSkillSet = new SkillSet();
	}

	public Equipment getEquipment() {
		return mEquipment;
	}
	public SkillSet getSkillSet() {
		return mSkillSet;
	}

	@Override
	public void onDie() {
		// TODO Auto-generated method stub
	}

//	public void useItem(CombatItem pCombatItem, BattleCharacter pTarget) {
//		Debug.d("use item " + pCombatItem.getName() + " on " + pTarget.getName());
//		pCombatItem.onConsumed(this, pTarget);
//		mCombatItemList.remove(pCombatItem);
////		mCombatItemList.add(CombatItem.EMPTY);
//	}

//	public boolean equipItem(int pIndex, CombatItem pCombatItem) {
//		if (pCombatItem.getAvailability() < 1) {
//			return false;
//		}
//
//		mCombatItemList.set(pIndex, pCombatItem);
//		return true;
//	}

//	public CombatItem getCombatItem(int pIndex) {
//		return mCombatItemList.get(pIndex);
//	}
//
//	public ArrayList<CombatItem> getEquippedItemList() {
//		return mCombatItemList;
//	}

//	public boolean executeOffensiveSkill(SkillGesture pGesture,
//			BattleCharacter pUser, BattleCharacter pTarget) {
//		Debug.d("Reaper trying to use skill");
//		if (mSkillMap.containsKey(pGesture)) {
//			SkillAttack skill = mSkillMap.get(pGesture);
//
//			Debug.d("Reaper needs " + skill.getCost() + "/" + mAttributes.getCurrent(AttributeType.JUSTICE));
//			if (mAttributes.isCurrentGreaterThan(AttributeType.JUSTICE, skill.getCost())) {
//				Debug.d("Reapers has enough justice");
//				skill.onSkillStarted(pUser, pTarget);
//				mAttributes.decreaseCurrent(AttributeType.JUSTICE, skill.getCost());
//				return true;
//			}
//		}
//		return false;
//	}
}

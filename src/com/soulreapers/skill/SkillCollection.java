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
package com.soulreapers.skill;

import java.util.HashMap;

/**
 * @author dxcloud
 *
 */
// TODO Load skill from XML file
@Deprecated
public class SkillCollection {
	public class O {
		public static final int UPPER_SLASH = 0x010001;
		public static final int HORIZONTAL_SLASH = 0x010002;
		public static final int AERIAL_SWEEP = 0x010003;
		public static final int AERIAL_DIVE = 0x010004;
		public static final int SLAP_SHOT = 0x010005;
		public static final int DOUBLE_SLASH = 0x010006;
	}

	private static final int OFFENSIVE_MASK = 0x010000;
	public class D {
		public static final int GUARD = 0x020001;
	}

	private static final SkillCollection INSTANCE = new SkillCollection();
	private HashMap<Integer, Skill> mSkillMap = new HashMap<Integer, Skill>();

	public static SkillCollection getInstance() {
		return INSTANCE;
	}


	private SkillCollection() {
		mSkillMap.put(O.SLAP_SHOT, new SkillAttack(1,SkillGesture.SINGLE_TAP,"Frappe éclair", "DMG : 25", 25, 1));
		mSkillMap.put(O.DOUBLE_SLASH, new SkillAttack(2,SkillGesture.SINGLE_TAP,"Coup Double", "DMG : 52", 52, 2));
		mSkillMap.put(O.UPPER_SLASH, new SkillAttack(3,SkillGesture.SINGLE_TAP,"Frappe ascendante", "DMG : 20", 20, 1));
		mSkillMap.put(O.AERIAL_DIVE, new SkillAttack(4,SkillGesture.SINGLE_TAP,"Plongée aérienne", "DMG : 80", 24, 4));
		mSkillMap.put(O.HORIZONTAL_SLASH, new SkillAttack(5,SkillGesture.SINGLE_TAP,"Frappe horizontale", "DMG : 10", 10, 1));
		mSkillMap.put(O.AERIAL_SWEEP, new SkillAttack(6,SkillGesture.SINGLE_TAP,"Vrille aérienne", "DMG : 18", 18, 1));
	}

	public Skill getSkill(int pSkillId) {
		return mSkillMap.get(pSkillId);
	}

	public SkillAttack getOffensiveSkill(int pSkillId) {
		if ((mSkillMap.containsKey(pSkillId) && ((pSkillId & OFFENSIVE_MASK) != 0)) ){
			return (SkillAttack) mSkillMap.get(pSkillId);
		}
		return null;
	}
}

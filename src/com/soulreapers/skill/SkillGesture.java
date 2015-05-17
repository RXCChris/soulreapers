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

/**
 * @author chris
 *
 */
public enum SkillGesture {
	SINGLE_TAP {
		@Override
		public String toString() {
			return "ST";
		}
	},
//	DOUBLE_TAP {
//		@Override
//		public String toString() {
//			return "DT";
//		}
//	},
	SWIPE_UP {
		@Override
		public String toString() {
			return "SU";
		}
	},
	SWIPE_DOWN {
		@Override
		public String toString() {
			return "SD";
		}
	},
	SWIPE_RIGHT {
		@Override
		public String toString() {
			return "SR";
		}
	},
	SWIPE_LEFT {
		@Override
		public String toString() {
			return "SL";
		}
	}
}

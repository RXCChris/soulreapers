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
package com.soulreapers.misc;

/**
 * @since 2014.10.30
 * @version 0.1 (alpha)
 * @author dxcloud
 *
 */
public enum Stat {
	HEALTH {
		@Override
		public String toString() {
			return "HP";
		}
	},
	COURAGE {
		@Override
		public String toString() {
			return "Courage";
		}
	},
	PRUDENCE {
		@Override
		public String toString() {
			return "Prudence";
		}
	},
	TEMPERANCE {
		@Override
		public String toString() {
			return "Temperance";
		}
	},
	JUSTICE {
		@Override
		public String toString() {
			return "Justice";
		}
	},
	EXP {
		@Override
		public String toString() {
			return "EXP";
		}
	}
}

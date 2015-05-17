/**
 * 
 */
package com.soulreapers.ui.menu.equip;

import com.soulreapers.misc.GameConstants;

/**
 * @author chris
 *
 */
public enum EnumEquipOption {
	WEAPON {
		@Override
		public String toString() {
			return GameConstants.STRING.WEAPON;
		}
	},
	ACCESSORY {
		@Override
		public String toString() {
			return GameConstants.STRING.ACCESSORY;
		}
	},
	ITEM {
		@Override
		public String toString() {
			return GameConstants.STRING.ITEM;
		}
	},
	SKILL {
		@Override
		public String toString() {
			return GameConstants.STRING.SKILL;
		}
	}
}

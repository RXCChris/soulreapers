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

import java.util.HashMap;

import org.andengine.util.debug.Debug;

/**
 * @since 2014.10.30
 * @version 0.1 (alpha)
 * @author dxcloud
 *
 */
public class Attributes implements LevelUpListener {
	private static final String STRING_SOUL       = "SOUL";
	private static final String STRING_EXPERIENCE = "EXP";
	private static final String STRING_COURAGE    = "Courage";
	private static final String STRING_PRUDENCE   = "Prudence";
	private static final String STRING_TEMPERANCE = "Temperance";
	private static final String STRING_JUSTICE    = "Justice";

	private static final int DEFAULT_SOUL       = 200;
	private static final int DEFAULT_COURAGE    = 50;
	private static final int DEFAULT_PRUDENCE   = 30;
	private static final int DEFAULT_TEMPERANCE = 4;
	private static final int DEFAULT_JUSTICE    = 7;
	private static final int DEFAULT_EXP        = 100;
	private static final int DEFAULT_LEVEL      = 0;

	private static final int MAX_SOUL       = 99999;
	private static final int MAX_ATTRIBUTE  = 999;

	public enum AttributeType {
		SOUL {
			@Override
			public String toString() {
				return STRING_SOUL;
			}
		},
		EXPERIENCE {
			@Override
			public String toString() {
				return STRING_EXPERIENCE;
			}
		},
		COURAGE {
			@Override
			public String toString() {
				return STRING_COURAGE;
			}
		},
		PRUDENCE {
			@Override
			public String toString() {
				return STRING_PRUDENCE;
			}
		},
		TEMPERANCE {
			@Override
			public String toString() {
				return STRING_TEMPERANCE;
			}
		},
		JUSTICE {
			@Override
			public String toString() {
				return STRING_JUSTICE;
			}
		}
	}

	private final LevelUpListener mLevelUpListener;
	private int mLevel = DEFAULT_LEVEL;
	private HashMap<AttributeType, AttributeRange> mAttributeMap =
			new HashMap<AttributeType, AttributeRange>();

	public Attributes() {
		this(DEFAULT_LEVEL,
				DEFAULT_SOUL,
				DEFAULT_COURAGE,
				DEFAULT_PRUDENCE,
				DEFAULT_TEMPERANCE,
				DEFAULT_JUSTICE,
				DEFAULT_EXP);
	}

	private Attributes(int pLevel,
			int pSoul,
			int pCourage,
			int pPrudence,
			int pTemperance,
			int pJustice,
			int pExp) {

		setLevel(pLevel);
		mLevelUpListener = this;

		mAttributeMap.put(AttributeType.SOUL, new AttributeRange(pSoul));
		mAttributeMap.put(AttributeType.COURAGE, new AttributeRange(pCourage));
		mAttributeMap.put(AttributeType.PRUDENCE, new AttributeRange(pPrudence));
		mAttributeMap.put(AttributeType.TEMPERANCE, new AttributeRange(pTemperance));
		mAttributeMap.put(AttributeType.JUSTICE, new AttributeRange(pJustice));
		mAttributeMap.put(AttributeType.EXPERIENCE, new AttributeRange(pExp));
		levellingUp(pExp);
	}

	public static final Attributes DEFAULT = new Attributes();

	public void setBase(int pSoul,
			int pCourage,
			int pPrudence,
			int pTemperance,
			int pJustice,
			int pExp) {
		setBase(AttributeType.SOUL, pSoul);
		setBase(AttributeType.COURAGE, pCourage);
		setBase(AttributeType.PRUDENCE, pPrudence);
		setBase(AttributeType.TEMPERANCE, pTemperance);
		setBase(AttributeType.JUSTICE, pJustice);
		setBase(AttributeType.EXPERIENCE, pExp);
	}
	public void setCurrent(int pSoul,
			int pCourage,
			int pPrudence,
			int pTemperance,
			int pJustice,
			int pExp) {
		setCurrent(AttributeType.SOUL, pSoul);
		setCurrent(AttributeType.COURAGE, pCourage);
		setCurrent(AttributeType.PRUDENCE, pPrudence);
		setCurrent(AttributeType.TEMPERANCE, pTemperance);
		setCurrent(AttributeType.JUSTICE, pJustice);
		setCurrent(AttributeType.EXPERIENCE, pExp);
	}
	public void setBonus(int pSoul,
			int pCourage,
			int pPrudence,
			int pTemperance,
			int pJustice,
			int pExp) {
		setBonus(AttributeType.SOUL, pSoul);
		setBonus(AttributeType.COURAGE, pCourage);
		setBonus(AttributeType.PRUDENCE, pPrudence);
		setBonus(AttributeType.TEMPERANCE, pTemperance);
		setBonus(AttributeType.JUSTICE, pJustice);
		setBonus(AttributeType.EXPERIENCE, pExp);
	}

	public int getLevel() {
		return mLevel;
	}
	public void setLevel(int pLevel) {
		mLevel = pLevel;
	}

	public int getBase(AttributeType pAttribute) {
		return mAttributeMap.get(pAttribute).mBase;
	}
	public int getBonus(AttributeType pAttribute) {
		return mAttributeMap.get(pAttribute).mBonus;
	}
	public int getCurrent(AttributeType pAttribute) {
		return mAttributeMap.get(pAttribute).mCurrent;
	}
	public int getTotal(AttributeType pAttribute) {
		return mAttributeMap.get(pAttribute).getAttributeTotal();
	}

	public void resetCurrent() {
		for (AttributeType type : AttributeType.values()) {
			if (type != AttributeType.EXPERIENCE) {
				final int baseValue = mAttributeMap.get(type).mBase;
				final int bonusValue = mAttributeMap.get(type).mBonus;
				mAttributeMap.get(type).mCurrent = baseValue + bonusValue;
			}
		}
	}

	public void setBase(AttributeType pType, int pValue) {
//		if (pValue < 0) {
//			pValue = 0;
//		} else if (Math.abs(pValue) > MAX_ATTRIBUTE && pType != AttributeType.SOUL) {
//			pValue = MAX_ATTRIBUTE;
//		} else if (Math.abs(pValue) > MAX_SOUL && pType == AttributeType.SOUL) {
//			pValue = MAX_SOUL;
//		}
		mAttributeMap.get(pType).mBase = pValue;
	}

	public void setBonus(AttributeType pAttribute, int pValue) {
		mAttributeMap.get(pAttribute).mBonus = pValue;
	}

	public void setCurrent(AttributeType pAttribute, int pValue) {
		if (pAttribute == AttributeType.EXPERIENCE) {
			levellingUp(pValue);
		} else {
			if (pValue > getTotal(pAttribute)) {
				mAttributeMap.get(pAttribute).mCurrent = getTotal(pAttribute);
			} else if (pValue < 0) {
				mAttributeMap.get(pAttribute).mCurrent = 0;
			} else {
				mAttributeMap.get(pAttribute).mCurrent = pValue;
			}
		}
	}

	public void increaseBase(AttributeType pAttribute, int pAmount) {
		setBase(pAttribute, getBase(pAttribute) + pAmount);
	}
	public void increaseBonus(AttributeType pAttribute, int pAmount) {
		setBonus(pAttribute, getBonus(pAttribute) + pAmount);
	}
	public void increaseCurrent(AttributeType pAttribute, int pAmount) {
		setCurrent(pAttribute, getCurrent(pAttribute) + pAmount);
	}

	public void decreaseBase(AttributeType pAttribute, int pAmount) {
		setBase(pAttribute, getBase(pAttribute) - pAmount);
	}
	public void decreaseBonus(AttributeType pAttribute, int pAmount) {
		setBonus(pAttribute, getBonus(pAttribute) - pAmount);
	}
	public void decreaseCurrent(AttributeType pAttribute, int pAmount) {
		setCurrent(pAttribute, getCurrent(pAttribute) - pAmount);
	}

	public boolean isBaseGreaterThan(AttributeType pAttribute, int pValue) {
		return (getBase(pAttribute) >= pValue);
	}
	public boolean isBonusGreaterThan(AttributeType pAttribute, int pValue) {
		return (getBonus(pAttribute) >= pValue);
	}
	public boolean isCurrentGreaterThan(AttributeType pAttribute, int pValue) {
		Debug.d("current : " + getCurrent(pAttribute) + " >= " + pValue + "?");
		return (getCurrent(pAttribute) >= pValue);
	}
	public boolean isTotalGreaterThan(AttributeType pAttribute, int pValue) {
		return (getTotal(pAttribute) >= pValue);
	}

	public boolean isBaseLessThan(AttributeType pAttribute, int pValue) {
		return (getBase(pAttribute) <= pValue);
	}
	public boolean isBonusLessThan(AttributeType pAttribute, int pValue) {
		return (getBonus(pAttribute) <= pValue);
	}
	public boolean isCurrentLessThan(AttributeType pAttribute, int pValue) {
		return (getCurrent(pAttribute) <= pValue);
	}
	public boolean isTotalLessThan(AttributeType pAttribute, int pValue) {
		return (getTotal(pAttribute) <= pValue);
	}
	private class AttributeRange {
		private int mBase;
		private int mBonus;
		private int mCurrent;
		public AttributeRange(int pBase) {
			this(pBase, 0, pBase);
		}

		public AttributeRange(int pBase, int pBonus, int pCurrent) {
			mBase = pBase;
			mBonus = pBonus;
			mCurrent = pCurrent;
		}
		public int getAttributeTotal() {
			return mBase + mBonus;
		}
	}

	private void levellingUp(int pExp) {
		pExp = (pExp >= 0) ? pExp : 0;
		mAttributeMap.get(AttributeType.EXPERIENCE).mCurrent = pExp;
		while (mAttributeMap.get(AttributeType.EXPERIENCE).mCurrent
				>= mAttributeMap.get(AttributeType.EXPERIENCE).mBase) {
			mAttributeMap.get(AttributeType.EXPERIENCE).mCurrent -=
					mAttributeMap.get(AttributeType.EXPERIENCE).mBase;
			mLevelUpListener.onLevelUp();
		}
	}
	/* (non-Javadoc)
	 * @see com.soulreapers.misc.LevelUpListener#onLevelUp()
	 */
	@Override
	public void onLevelUp() {
		++mLevel;
//		this.setBase(AttributeType.EXPERIENCE, nextExp(mLevel));
		for (AttributeType type : AttributeType.values()) {
			this.setBase(type, nextAttributeValue(type, mLevel));
		}
		resetCurrent();
		Debug.d("Level UP");
	}

	final public int nextExp(final int pNextLevel) {
		return 100 * pNextLevel * pNextLevel;
	}

	final public int nextCourage(final int pNextLevel) {
		final double coeff_a = 8;
		final double coeff_b = 5;
		final double coeff_c = 48;

		double result = Math.log10(coeff_a * pNextLevel);
		result = Math.pow(result, coeff_b) + coeff_c;
		return (int) Math.floor(result);
	}

	final public int nextSoul(final int pNextLevel) {
		final double coeff_a = 218;
		final double coeff_b = 6;

		double result = Math.log10(coeff_a * pNextLevel);
		result = Math.pow(result, coeff_b);
		return (int) Math.floor(result);
	}

	final public int nextTemperance(final int pNextLevel) {
		final double coeff_a = 11;
		final double coeff_b = 2;

		double result = Math.log10(coeff_a * pNextLevel);
		return (int) Math.floor(result + coeff_b);
	}

	final public int nextJustice(final int pNextLevel) {
		final double coeff_a = 42;
		final double coeff_b = 2;
		final double coeff_c = 5;

		double result = Math.log10(coeff_a * pNextLevel);
		result = Math.pow(result, coeff_b) + coeff_c;
		return (int) Math.floor(result);
	}

	final public int nextPrudence(final int pNextLevel) {
		final double coeff_a = 9;
		final double coeff_b = 4;
		final double coeff_c = 32;

		double result = Math.log10(coeff_a * pNextLevel);
		result = Math.pow(result, coeff_b) + coeff_c;
		return (int) Math.floor(result);
	}

	final public int nextAttributeValue(AttributeType pType, int pNextLevel) {
		switch (pType) {
		case SOUL:
			return nextSoul(pNextLevel);
		case EXPERIENCE:
			return nextExp(pNextLevel);
		case COURAGE:
			return nextCourage(pNextLevel);
		case PRUDENCE:
			return nextPrudence(pNextLevel);
		case TEMPERANCE:
			return nextTemperance(pNextLevel);
		case JUSTICE:
			return nextJustice(pNextLevel);
		default:
			return 0;
		}
	}

	public void resetBonus() {
		for (AttributeType type : AttributeType.values()) {
			if (type != AttributeType.EXPERIENCE) {
				mAttributeMap.get(type).mBonus = 0;
			}
		}
	}
}

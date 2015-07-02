/**
 * 
 */
package com.soulreapers.scene;

import java.util.ArrayList;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.util.SRButton;

/**
 * @author chris
 *
 */
// Base:      100    -> = base exp get from enemy
// Combo:    x1.4    -> = 1 + number of combo / 10
// Damage:   x0.2    -> = damage / total soul point
// Overkill: x2.3    -> = overkill total damage * bias
// Bonus:      +0    -> = enemy died
// --------------
// Total:      64    -> = total rounded down
public class ResultScene extends Scene {
	private static final String STRING_END = "Fin";
//	private static final int FONT_ID = ResourceManager.FONT_TEXT_ID;

	private static final String STRING_BASE = "EXP";
	private static final String STRING_COMBO = "Combo";
	private static final String STRING_DAMAGE = "Dégât";
	private static final String STRING_OVERKILL = "Overkill";
	private static final String STRING_BONUS = "Bonus";
	private static final String STRING_TOTAL = "Total";

	private enum ExpType {
		BASE {
			@Override
			public String toString() {
				return STRING_BASE;
			}
		},
		COMBO {
			@Override
			public String toString() {
				return STRING_COMBO;
			}
		},
		DAMAGE {
			@Override
			public String toString() {
				return STRING_DAMAGE;
			}
		},
		OVERKILL {
			@Override
			public String toString() {
				return STRING_OVERKILL;
			}
		},
		BONUS {
			@Override
			public String toString() {
				return STRING_BONUS;
			}
		},
		TOTAL {
			@Override
			public String toString() {
				return STRING_TOTAL;
			}
		}
		
	}

	private SRButton mResultEndButton;
	private ArrayList<Exp> mExpMap = new ArrayList<Exp>();


	private static final float OFFSET_X = 300;
	private static final float OFFSET_Y = 120;
	private static final float PADDING_Y = 30;

	ResultScene(float pBase, float pCombo, float pDamage, float pOverkill, float pBonus) {
		super();
		mResultEndButton = new SRButton(0,
				400,
				STRING_END) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				endResult();
			}
		};

		mResultEndButton.setX(GameConstants.CAMERA_WIDTH / 2 - mResultEndButton.getWidth() / 2);

		mExpMap.add(new Exp(ExpType.BASE, pBase, OFFSET_X, OFFSET_Y));
		mExpMap.add(new Exp(ExpType.COMBO, pCombo, OFFSET_X, OFFSET_Y + PADDING_Y * 2));
		mExpMap.add(new Exp(ExpType.DAMAGE, pDamage, OFFSET_X, OFFSET_Y + PADDING_Y));
		mExpMap.add(new Exp(ExpType.OVERKILL, pOverkill, OFFSET_X, OFFSET_Y + PADDING_Y * 3));
		mExpMap.add(new Exp(ExpType.BONUS, pBonus, OFFSET_X, OFFSET_Y + PADDING_Y * 4));

		final float total = (float) Math.floor(pBase * pDamage * pCombo * pOverkill + pBonus);
		mExpMap.add(new Exp(ExpType.TOTAL, total, OFFSET_X, OFFSET_Y + PADDING_Y * 6));


		for (Exp exp : mExpMap) {
			this.attachChild(exp);
		}
		this.attachChild(mResultEndButton);
		this.registerTouchArea(mResultEndButton);
	}

	protected void endResult() {
		Debug.d("Close result scene");
	}

	@Override
	public void back() {
		super.back();
		ResultScene.this.unregisterTouchArea(mResultEndButton);
		for (Exp exp : mExpMap) {
			exp.detachSelf();
			exp.dispose();
		}
		mResultEndButton.detachSelf();
		mResultEndButton.dispose();
		this.detachSelf();
		this.dispose();
	}

	public static float computeDamageMultiplicator(float pInflictedDamage, float pTotalSoul) {
		float ratio = pInflictedDamage / pTotalSoul;
		if (ratio > 1) {
			ratio = 1;
		}
		return ratio;
	}

	public static float computeComboMultiplicator(float pNumCombo) {
		if (pNumCombo % 2 != 0) {
			--pNumCombo;
		}
		return 1 + pNumCombo / 20;
	}

	public static float computeOverkillMultiplicator(float pOverkillDamage, float pTotalSoul) {
		float ratio = 1 + pOverkillDamage / pTotalSoul / 10;
		return ratio;
	}

	public static int computeTotalExpGained(float pBase, float pTotalSoul, float pInflictedDamage, float pOverkillDamage, float pNumCombo, float pBonus) {
		double total = pBase
				* computeDamageMultiplicator(pInflictedDamage, pTotalSoul)
				* computeComboMultiplicator(pNumCombo)
				* computeOverkillMultiplicator(pOverkillDamage, pTotalSoul)
				+ pBonus;
		total = Math.floor(total);
		return (int) total;
	}

	private class Exp extends Entity {
		private static final float PADDING_X = 200;
		private Text mType;
		private Text mValue;

		public Exp(ExpType pType, float pValue, float pX, float pY) {
			String value;
			if (pType == ExpType.BONUS) {
				value = String.format("+%d", (int) pValue);
			} else if (pType == ExpType.BASE || pType == ExpType.TOTAL) {
				value = String.format("%d", (int) pValue);
			} else {
				value = String.format("x%.1f", pValue);
			}
			mType = new Text(pX, pY,
					FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
					pType.toString(),
					ResourceManager.getInstance().getVertexBufferObjectManager());

			mValue = new Text(0, pY,
					FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
					value,
					new TextOptions(HorizontalAlign.RIGHT),
					ResourceManager.getInstance().getVertexBufferObjectManager());
			mValue.setX(pX + PADDING_X - mValue.getWidth());

			Exp.this.attachChild(mType);
			Exp.this.attachChild(mValue);
		}
	}
}

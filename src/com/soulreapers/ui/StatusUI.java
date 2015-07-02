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
package com.soulreapers.ui;

import java.util.HashMap;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.misc.Stat;
import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.object.Gauge;
import com.soulreapers.object.character.BattleCharacter;

/**
 * @author dxcloud
 *
 */
@Deprecated
public class StatusUI extends Entity {
//	private static final int STATUS_FONT = R.string.ft_text;
//	private static final int FONT_ID = ResourceManager.FONT_STATS_ID;
	private Text mNameText;
	private Text mLevelText;
	private Rectangle mRectangle;

	private HashMap<AttributeType, StatusText> mStatusTextMap = new HashMap<AttributeType, StatusText>();
	public Gauge mHealthGauge;
	public Gauge mExpGauge;

	private static final int X_STATUS_INFO = 20;
	private static final int Y_STATUS_INFO = 120;
	private static final int X_PADDING = 200;
	private static final int Y_PADDING = 30;
	private static final int RECTANGLE_X = 16;
	private static final int RECTANGLE_Y = 118;
	private static final int RECTANGLE_WIDTH = 294;
	private static final int RECTANGLE_HEIGHT = 228;
	private static final int MAX_CHAR_STATUS_VALUE = 6;
	private static final int GAUGE_PADDING_X = 18;
	private static final String STATUS_VALUE_FORMAT = "%6d";

	public StatusUI(BattleCharacter pCharacter) {
		mRectangle = new Rectangle(RECTANGLE_X, RECTANGLE_Y,
				RECTANGLE_WIDTH, RECTANGLE_HEIGHT,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mRectangle.setColor(0.2f, 0.2f, 0.2f, 0.6f);

		mNameText = new Text(X_STATUS_INFO, Y_STATUS_INFO,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				pCharacter.getName(),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mLevelText = new Text(X_STATUS_INFO + X_PADDING, Y_STATUS_INFO,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				String.format("Lv. " + STATUS_VALUE_FORMAT, pCharacter.getParameters().getLevel()),
				ResourceManager.getInstance().getVertexBufferObjectManager());

		int i = 1;
		for (AttributeType feature : AttributeType.values()) {
			createStatusText(X_STATUS_INFO, Y_STATUS_INFO + Y_PADDING * (i++),
					feature, pCharacter.getParameters().getCurrent(feature));
		}

		mHealthGauge = new Gauge(X_STATUS_INFO, Y_STATUS_INFO + Y_PADDING + GAUGE_PADDING_X,
				Gauge.DEFAULT_GAUGE_WIDTH, Gauge.DEFAULT_GAUGE_HEIGHT);
		mExpGauge = new Gauge(X_STATUS_INFO, Y_STATUS_INFO + Y_PADDING * 6 + GAUGE_PADDING_X,
				Gauge.DEFAULT_GAUGE_WIDTH, Gauge.DEFAULT_GAUGE_HEIGHT);
		mExpGauge.setColor(Color.BLUE);
	}

	private class StatusText {
		private Text mStatusName;
		private Text mStatusValue;
		public StatusText(Text pStatusName, Text pStatusValue) {
			mStatusName = pStatusName;
			mStatusValue = pStatusValue;
		}
	}

	private void createStatusText(int pX, int pY, AttributeType pFeature, int pValue) {
		Text statusName = new Text(pX, pY,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				pFeature.toString(),
				pFeature.toString().length(),
				new TextOptions(HorizontalAlign.LEFT),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		Text statusValue = new Text(pX + X_PADDING, pY,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				String.format(STATUS_VALUE_FORMAT, pValue),
				MAX_CHAR_STATUS_VALUE,
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		
		mStatusTextMap.put(pFeature, new StatusText(statusName, statusValue));
	}

	public void updateStatus(AttributeType pFeature, int pValue) {
		mStatusTextMap.get(pFeature).mStatusValue.setText(String.format(STATUS_VALUE_FORMAT, pValue));
		Debug.i(pFeature.toString() + " : " + pValue);
	}

	public void updateGauge(Stat pFeature, int pCurrent, int pMax) {
		if (pFeature == Stat.HEALTH) {
			mHealthGauge.update(pCurrent, pMax);
		} else if (pFeature == Stat.EXP) {
			mExpGauge.update(pCurrent, pMax);
		}
	}

	public void updateAllStatus(BattleCharacter pCharacter) {
		for (AttributeType feature : AttributeType.values()) {
			updateStatus(feature, pCharacter.getParameters().getCurrent(feature));
		}
		mLevelText.setText(String.format("Lv. " + STATUS_VALUE_FORMAT, pCharacter.getParameters().getLevel()));
		updateGauge(Stat.HEALTH, pCharacter.getParameters().getCurrent(AttributeType.SOUL), pCharacter.getParameters().getCurrent(AttributeType.SOUL));
		updateGauge(Stat.EXP, pCharacter.getParameters().getCurrent(AttributeType.EXPERIENCE), pCharacter.getParameters().getCurrent(AttributeType.EXPERIENCE));
	}

	@Override
	public void onAttached() {
		this.getParent().attachChild(mRectangle);
		this.getParent().attachChild(mNameText);
		this.getParent().attachChild(mLevelText);
		this.getParent().attachChild(mHealthGauge);
		this.getParent().attachChild(mExpGauge);

		for (StatusText status : mStatusTextMap.values()) {
			this.getParent().attachChild(status.mStatusName);
			this.getParent().attachChild(status.mStatusValue);
		}
		super.onAttached();
	}

	@Override
	public void onDetached() {
		mRectangle.detachSelf();
		mNameText.detachSelf();
		mLevelText.detachSelf();
		mHealthGauge.detachSelf();
		mExpGauge.detachSelf();
		for (StatusText status : mStatusTextMap.values()) {
			status.mStatusName.detachSelf();
			status.mStatusValue.detachSelf();
		}
		super.onDetached();
	}

	@Override
	public void dispose() {
		mRectangle.dispose();
		mNameText.dispose();
		mLevelText.dispose();
		mHealthGauge.dispose();
		mExpGauge.dispose();
		for (StatusText status : mStatusTextMap.values()) {
			status.mStatusName.dispose();
			status.mStatusValue.dispose();
		}
		super.dispose();
	}

	@Override
	public void setVisible(boolean pVisible) {
		mHealthGauge.setVisible(pVisible);
		mExpGauge.setVisible(pVisible);
		for (StatusText status : mStatusTextMap.values()) {
			status.mStatusName.setVisible(pVisible);
			status.mStatusValue.setVisible(pVisible);
		}
		super.setVisible(pVisible);
	}
}

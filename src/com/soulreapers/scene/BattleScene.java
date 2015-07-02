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
package com.soulreapers.scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import android.opengl.GLES20;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.FontManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.DefaultVictory;
import com.soulreapers.misc.Stat;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.misc.CharacterParameters.AttributeType;
import com.soulreapers.misc.VictoryListener;
import com.soulreapers.object.BattleObject;
import com.soulreapers.object.FieldGrid;
import com.soulreapers.object.character.BattleCharacter;
import com.soulreapers.object.character.Remnant;
import com.soulreapers.object.character.RemnantFactory;
import com.soulreapers.object.character.GameCharacter.CharacterType;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.ui.AttributesUI;
import com.soulreapers.ui.BattleActionUI;

/**
 * @since 2014.11.01
 * @version 0.1 (alpha)
 * @author dxcloud
 *
 */
public class BattleScene extends UI_Scene {
	private Sprite mBackground;
	private FieldGrid mGrid = new FieldGrid();
	private BattleActionUI mActionUI;

	private static final String STRING_YOUR_TURN = "Your Turn";
	private static final String STRING_ENEMY_TURN = "Enemy Turn";
//	private static final int FONT_TITLE_ID = ResourceManager.FONT_TITLE_ID;
	private static final float FADE_OUT_DURATION = 2.0F;

	private VictoryListener mVictoryListener = new DefaultVictory(this);

	private FadeOutModifier mTurnModifier = new FadeOutModifier(FADE_OUT_DURATION) {
		@Override
		protected void onModifierStarted(IEntity pItem) {
			Debug.d("modifier starts");
			super.onModifierStarted(pItem);
			mActionUI.setCommandButtonsEnabled(false);
		}

		@Override
		protected void onModifierFinished(IEntity pItem) {
			super.onModifierFinished(pItem);
			Debug.d("modifier finished");
			mActionUI.setCommandButtonsEnabled(true);
			executeAI();
		}
	};

	public AttributesUI getAttributesUI() {
		return mActionUI.getAttributeUI();
	}

	private Text mTurnIndication = new Text(0, 0,
			FontManager.getInstance().getFont(FontType.FONT_TEXT_MEDIUM),
			STRING_YOUR_TURN,
			GameConstants.MAX_CHARACTER_SIZE,
			new TextOptions(HorizontalAlign.CENTER),
			ResourceManager.getInstance().getVertexBufferObjectManager());

//	private Entity mBackgroundLayer = new Entity();

//	private ArrayList<BattleObject> mBattleObjectList = new ArrayList<BattleObject>();

	private Queue<BattleObject> mBattleObjectList = new LinkedList<BattleObject>();

	private Rectangle mCurrentMarker;

	public void showStatus(BattleObject pObject) {
		mActionUI.getAttributeUI().setStatus(pObject.getAttributes());
		mActionUI.getAttributeUI().setVisible(true);
	}

	public void setStatusVisible(boolean pVisible, BattleObject pObject) {
		if (true == pVisible) {
			mActionUI.getAttributeUI().setStatus(pObject.getAttributes());
		}
		mActionUI.getAttributeUI().setVisible(pVisible);
	}

	public void hideStatus() {
		mActionUI.getAttributeUI().setVisible(false);
	}


	public BattleActionUI getActionUI() {
		return mActionUI;
	}


	private static final int TEXTURE_BACKGROUND_ID = R.string.bg_battle;
	private static final int TEXTURE_BACKGROUND_WIDTH = 800;
	private static final int TEXTURE_BACKGROUND_HEIGHT = 480;
	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#loadResources()
	 */
	@Override
	public void onLoadResources() {
		ResourceManager.getInstance().loadTexture(TEXTURE_BACKGROUND_ID);
	}

	// TODO Implements enemy generation algorithm.
	private void generateEnemy() {
		// enemy 1
		BattleObject object = new BattleObject(RemnantFactory.create(0),
				R.string.ic_e01, 4, 2, false, this);
		mBattleObjectList.add(object);
		mGrid.putIntoCell(object.getCurrentIndexX(), object.getCurrentIndexY());
		attachChild(object);
		registerTouchArea(object);

		// enemy 2
		BattleObject object2 = new BattleObject(RemnantFactory.create(0),
				R.string.ic_e01, 4, 6, false, this);
		mBattleObjectList.add(object2);
		mGrid.putIntoCell(object2.getCurrentIndexX(), object2.getCurrentIndexY());
		attachChild(object2);
		registerTouchArea(object2);
	}

	private void generateObjects() {
		generateEnemy();
		generateAlly();
	}


	private void generateAlly() {
		BattleObject object = new BattleObject(new Reaper(1,"Dante", R.string.pc_01, R.string.ic_01),
				R.string.ic_01, 3, 5, true, this);
		mBattleObjectList.add(object);
		mGrid.putIntoCell(object.getCurrentIndexX(), object.getCurrentIndexY());
		attachChild(object);
		registerTouchArea(object);
	}

	private void removeBattleObject(final BattleObject pObject) {
		pObject.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		pObject.registerEntityModifier(new FadeOutModifier(2) {
			@Override
			protected void onModifierFinished(final IEntity pItem) {
				super.onModifierFinished(pItem);
				mGrid.removeFromCell(pObject.getCurrentIndexX(), pObject.getCurrentIndexY());
				BattleScene.this.unregisterTouchArea(pObject);
			}
		});
	}

	public void endTurn() {
		mActionUI.endTurn();

		sortBattleObjects();

		for(Iterator<BattleObject> iterator = mBattleObjectList.iterator();
				iterator.hasNext();) {
			BattleObject object = iterator.next();
			if (object.getCharacter().isDead()) {
				iterator.remove();
//				mGrid.removeFromCell(object.getCurrentIndexX(), object.getCurrentIndexY());
//				unregisterTouchArea(object);
//				object.detachSelf();
//				object.dispose();
				removeBattleObject(object);
			} else {
				object.getCharacter().getParameters().increaseCurrent(AttributeType.JUSTICE, 1);
			}
		}

		if(!mVictoryListener.checkVitoryCondition(mBattleObjectList)) {
			updateCurrent();
		}
	}

	private void updateCurrent() {
		BattleObject current = getCurrentObject();
//		Debug.d("end turn " + firstToLast.getCharacterType() + " -> begin turn " + current.getCharacterType());
		if (CharacterType.REAPER == current.getCharacterType()) {
			mTurnIndication.setText(STRING_YOUR_TURN);
		} else if (CharacterType.REMNANT == current.getCharacterType()) {
			mTurnIndication.setText(STRING_ENEMY_TURN);
		}
		mTurnIndication.reset();
		mCurrentMarker.setPosition(current);
		showCharacterArt(current);
	}

	private void sortBattleObjects() {
		if (!mBattleObjectList.isEmpty()) {
			BattleObject firstToLast = mBattleObjectList.poll();
			mBattleObjectList.add(firstToLast);

//			BattleObject current = getCurrentObject();
//			Debug.d("end turn " + firstToLast.getCharacterType() + " -> begin turn " + current.getCharacterType());
//			if (CharacterType.ALLY == current.getCharacterType()) {
//				mTurnIndication.setText(STRING_YOUR_TURN);
//			} else if (CharacterType.ENEMY == current.getCharacterType()) {
//				mTurnIndication.setText(STRING_ENEMY_TURN);
//				
//			}
//			mTurnIndication.reset();
//			mCurrentMarker.setPosition(current);
//			showCharacterArt(current);
		}		
	}

	public void showCharacterArt(BattleObject pObject) {
//		for (BattleObject object : mBattleObjectList) {
//			object.getCharacter().setVisible(false);
//		}
//		pObject.getCharacter().setVisible(true);
	}

//	private int initD = 99;

	private void executeAI() {
		BattleObject current = getCurrentObject();
		if (current.getCharacterType() == CharacterType.REMNANT) {
			// TODO
			// Test to remove
			Debug.d("executing ai, soul decreased");
//			current.getCharacter().getAttributes().decreaseCurrent(AttributeType.SOUL, initD);
//			initD = initD/2;
			endTurn();
		}
		Debug.d("ai unavailable");
	}



	public void prepareToAttack(BattleObject pTarget) {
		final BattleObject current = getCurrentObject();
		if (current.isWithinArea(pTarget, 1) == true) {
			Debug.d("Prepare to attack");

			if (pTarget.getCharacterType() == CharacterType.REMNANT
					&& current.getCharacterType() == CharacterType.REAPER) {
				final Reaper reaper = (Reaper) current.getCharacter();
				final Remnant remnant = (Remnant) pTarget.getCharacter();
				DuelScene duel = new DuelScene(reaper, remnant, BattleScene.this);
				BattleScene.this.setChildScene(duel, false, true, true);
			}
		} // enemy is within player's attack area
	}

	public void prepareToDefend(BattleObject pTarget) {
		final BattleObject current = getCurrentObject();
		if (current.equals(pTarget)) {
			Debug.d("Ready to defend");
			// TODO
			// Implements defend algorithm
			endTurn();
		} else {
			mActionUI.cancelAction();
		}
	}

	public void setMoveSurfaceVisible(boolean pVisible) {
		BattleObject current = getCurrentObject();
		current.setMovable(pVisible);
		current.setMoveSurfaceVisible(pVisible);
	}

	public BattleObject getCurrentObject() {
		return mBattleObjectList.peek();
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#create()
	 */
	@Override
	public void onCreate() {
		AudioManager.getInstance().playMusic(R.string.ms_09, true, true);

		mBackground = new Sprite(0, 0,
				ResourceManager.getInstance().getTextureRegion(TEXTURE_BACKGROUND_ID),
				ResourceManager.getInstance().getVertexBufferObjectManager());

		mActionUI = new BattleActionUI(this);
		mActionUI.setTouchAreaEnabled(true);

		mCurrentMarker = new Rectangle(0,0,GameConstants.BATTLE_CELL_SIZE, GameConstants.BATTLE_CELL_SIZE,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mCurrentMarker.setColor(0.0f, 0.0f, 1.0f, 0.5f);

		mTurnIndication.setPosition((GameConstants.CAMERA_WIDTH - mTurnIndication.getWidth()) / 2,
				(GameConstants.CAMERA_HEIGHT - mTurnIndication.getHeight()) / 2);
		mTurnIndication.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		mTurnModifier.setAutoUnregisterWhenFinished(false);

		attachChild(mBackground);
		attachChild(mGrid);

		generateObjects();
		attachChild(mActionUI);

		final BattleObject current = getCurrentObject();
		if (current.getCharacterType() == CharacterType.REMNANT) {
			mTurnIndication.setText(STRING_ENEMY_TURN);
		}

		showCharacterArt(current);
		mCurrentMarker.setPosition(current);
		attachChild(mCurrentMarker);
		attachChild(mTurnIndication);
		mTurnIndication.registerEntityModifier(mTurnModifier);

		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
	}


	public void prepareToMove(BattleObject pTarget) {
		if (mGrid.isOccupied(pTarget.getCurrentIndexX(), pTarget.getCurrentIndexY())) {
			pTarget.invalidateMove();
			mActionUI.cancelAction();
		} else {
			mGrid.move(pTarget.getOriginIndexX(), pTarget.getOriginIndexY(),
					pTarget.getCurrentIndexX(), pTarget.getCurrentIndexY());
			pTarget.validateMove();
			endTurn(); // Ends ally's turn
		}
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#unloadResources()
	 */
	@Override
	public void onDestroyResources() {
		ResourceManager.getInstance().unloadTexture(TEXTURE_BACKGROUND_ID);
//		mBackgroundTextureAtlas.unload();
//		mBackgroundRegion = null;
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#destroy()
	 */
	@Override
	public void onDestroy() {
		AudioManager.getInstance().pauseMusic();

		if (this.hasChildScene()) {
			this.clearChildScene();
		}
		this.detachChildren();
		this.detachSelf();
		this.dispose();
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onPause()
	 */
	@Override
	public void onPause() {
		// Nothing to do
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onResume()
	 */
	@Override
	public void onResume() {
		// Nothing to do
	}
}

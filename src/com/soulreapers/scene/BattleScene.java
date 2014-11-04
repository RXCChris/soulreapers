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

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.BattleObject;
import com.soulreapers.object.FieldGrid;
import com.soulreapers.object.character.Ally;
import com.soulreapers.object.character.CharacterCollection;
import com.soulreapers.object.character.CharacterType;
import com.soulreapers.object.character.Enemy;

/**
 * @since 2014.11.01
 * @version 0.1 (alpha)
 * @author dxcloud
 *
 */
public class BattleScene extends BaseScene {
	private ITextureRegion mBackgroundRegion;
	private BitmapTextureAtlas mBackgroundTextureAtlas;
	private Sprite mBackground;
	private FieldGrid mGrid = new FieldGrid();

	private Text mMenuText;
	private Rectangle mMenuLine;

	private Entity mBackgroundLayer = new Entity();
	private Entity mMenuLayer = new Entity();

	private ArrayList<BattleObject> mBattleObjectList = new ArrayList<BattleObject>();
	private ActionType mCurrentAction = ActionType.NONE;

	private static final String ACTION_STRING = "Action";
	private static final String ACTION_ATTACK_STRING = "Attaquer";
	private static final String ACTION_DEFEND_STRING = "Defendre";
	private static final String ACTION_MOVE_STRING = "Deplacer";
	private static final String ACTION_STATUS_STRING = "Status";
	private static final String ACTION_ITEM_STRING = "Objet";
	private static final String SELECTION_STRING = ">> ";
	private static final String CANCEL_STRING = "Annuler <<";

	private HashMap<ActionType, Text> mActionCommandMap = new HashMap<ActionType, Text>();

	private Rectangle mCurrentMarker;

	private void createBattleMenuInterface() {
		mMenuLine = new Rectangle(GameConstants.X_SUBMENU_PADDING,
				GameConstants.Y_SUBMENU_PADDING + GameConstants.FONT_SIZE,
				GameConstants.CAMERA_WIDTH - GameConstants.X_OPTION_TEXT_PADDING,
				5, ResourceManager.getInstance().getVertexBufferObjectManager());
		mMenuText = new Text(GameConstants.X_SUBMENU_PADDING,
				GameConstants.Y_SUBMENU_PADDING,
				ResourceManager.getInstance().getFont(R.string.ft_03),
				ACTION_STRING,
				GameConstants.MAX_CHARACTER_SIZE,
				ResourceManager.getInstance().getVertexBufferObjectManager());

		mActionCommandMap.put(ActionType.ATTACK, new Text(GameConstants.X_SUBMENU_PADDING,
				GameConstants.Y_OPTION_TEXT_PADDING * 2,
				ResourceManager.getInstance().getFont(R.string.ft_01),
				SELECTION_STRING + ACTION_ATTACK_STRING,
				GameConstants.MAX_CHARACTER_SIZE,
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if (mCurrentAction == ActionType.NONE) {
						this.setText(CANCEL_STRING);
						mCurrentAction = ActionType.ATTACK;
					} else if (mCurrentAction == ActionType.ATTACK) {
						this.setText(SELECTION_STRING + ACTION_ATTACK_STRING);
						mCurrentAction = ActionType.NONE;
					}
				}
				return true;
			}
		});

		mActionCommandMap.put(ActionType.DEFEND, new Text(GameConstants.X_SUBMENU_PADDING,
				GameConstants.Y_OPTION_TEXT_PADDING * 3,
				ResourceManager.getInstance().getFont(R.string.ft_01),
				SELECTION_STRING + ACTION_DEFEND_STRING,
				GameConstants.MAX_CHARACTER_SIZE,
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if (mCurrentAction == ActionType.NONE) {
						this.setText(CANCEL_STRING);
						mCurrentAction = ActionType.DEFEND;
					} else if (mCurrentAction == ActionType.DEFEND) {
						this.setText(SELECTION_STRING + ACTION_ATTACK_STRING);
						mCurrentAction = ActionType.NONE;
					}
				}
				return true;
			}
		});

		mActionCommandMap.put(ActionType.MOVE, new Text(GameConstants.X_SUBMENU_PADDING,
				GameConstants.Y_OPTION_TEXT_PADDING * 4,
				ResourceManager.getInstance().getFont(R.string.ft_01),
				SELECTION_STRING + ACTION_MOVE_STRING,
				GameConstants.MAX_CHARACTER_SIZE,
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if (mCurrentAction == ActionType.NONE) {
						this.setText(CANCEL_STRING);
						mCurrentAction = ActionType.MOVE;
					} else if (mCurrentAction == ActionType.MOVE) {
						this.setText(SELECTION_STRING + ACTION_MOVE_STRING);
						mCurrentAction = ActionType.NONE;
					}
				}
				return true;
			}
		});

		mActionCommandMap.put(ActionType.ITEM, new Text(GameConstants.X_SUBMENU_PADDING,
				GameConstants.Y_OPTION_TEXT_PADDING * 5,
				ResourceManager.getInstance().getFont(R.string.ft_01),
				SELECTION_STRING + ACTION_ITEM_STRING,
				GameConstants.MAX_CHARACTER_SIZE,
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if (mCurrentAction == ActionType.NONE) {
						this.setText(CANCEL_STRING);
						mCurrentAction = ActionType.ITEM;
					} else if (mCurrentAction == ActionType.ITEM) {
						this.setText(SELECTION_STRING + ACTION_ITEM_STRING);
						mCurrentAction = ActionType.NONE;
					}
				}
				return true;
			}
		});

		mActionCommandMap.put(ActionType.STATUS, new Text(GameConstants.X_SUBMENU_PADDING,
				GameConstants.Y_OPTION_TEXT_PADDING * 8,
				ResourceManager.getInstance().getFont(R.string.ft_01),
				SELECTION_STRING + ACTION_STATUS_STRING,
				GameConstants.MAX_CHARACTER_SIZE,
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if (mCurrentAction == ActionType.STATUS) {
						mCurrentAction = ActionType.NONE;
						this.setText(SELECTION_STRING + ACTION_STATUS_STRING);
						mMenuText.setText(ACTION_STRING);
						setActionCommandVisible(true);
						clearStatus();
					} else if (ActionType.NONE == mCurrentAction) {
						mCurrentAction = ActionType.STATUS;
						this.setText(SELECTION_STRING + ACTION_STRING);
						mMenuText.setText(ACTION_STATUS_STRING);
						setActionCommandVisible(false);
						mBattleObjectList.get(0).getCharacter().setVisible(true);
					}
				}
				return true;
			}
		});

		BattleScene.this.mMenuLayer.attachChild(mMenuLine);
		BattleScene.this.mMenuLayer.attachChild(mMenuText);

		for (Text actionText : mActionCommandMap.values()) {
			BattleScene.this.mMenuLayer.attachChild(actionText);
			BattleScene.this.registerTouchArea(actionText);
		}
	}


	/**
	 * Hide all characters' status stats.
	 */
	public void clearStatus() {
		for (int i = 0; i < mBattleObjectList.size(); ++i) {
			mBattleObjectList.get(i).getCharacter().setVisible(false);
		}
	}

	/**
	 * Show/Hide action commands.
	 * <p>
	 * Action 'Status' is always visible.
	 * </p>
	 * @param pVisible
	 */
	private void setActionCommandVisible(boolean pVisible) {
		for (Text actionText : mActionCommandMap.values()) {
			actionText.setVisible(pVisible);
		}
		mActionCommandMap.get(ActionType.STATUS).setVisible(true);
	}

	public enum ActionType {
		ATTACK,
		DEFEND,
		MOVE,
		ITEM,
		STATUS,
		NONE
	}



	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#loadResources()
	 */
	@Override
	public void onLoadResources() {
		mBackgroundTextureAtlas = new BitmapTextureAtlas(
				ResourceManager.getInstance().getTextureManager(),
				800, 480,
				TextureOptions.BILINEAR);
		mBackgroundRegion = ResourceManager.getInstance().getTextureRegion(
				mBackgroundTextureAtlas, R.string.bg_battle);
		mBackgroundTextureAtlas.load();
	}

	// TODO Defines enemy generation algorithm.
	private void generateEnemy() {
		Enemy enemy = (Enemy) CharacterCollection.getInstance().getCharacter("Chiroptera");
		enemy.setVisible(false);
		mBattleObjectList.add(new BattleObject(enemy, CharacterType.ENEMY,
				R.string.ic_e01, 4, 1, false, this));
		BattleScene.this.mBackgroundLayer.attachChild(enemy);
	}


	private void generateAlly() {
		Ally ally = (Ally) CharacterCollection.getInstance().getCharacter("Dante");
		mBattleObjectList.add(new BattleObject(ally, CharacterType.ALLY,
				R.string.ic_01, 3, 5, true, this));
		BattleScene.this.mBackgroundLayer.attachChild(ally);
	}

	private void endTurn() {
//		mAttackCommand.setText(SELECTION_STRING + ACTION_ATTACK_STRING);
//		mDefendCommand.setText(SELECTION_STRING + ACTION_DEFEND_STRING);
//		mMoveCommand.setText(SELECTION_STRING + ACTION_MOVE_STRING);
//		mItemCommand.setText(SELECTION_STRING + ACTION_ITEM_STRING);
		mActionCommandMap.get(ActionType.ATTACK).setText(SELECTION_STRING + ACTION_ATTACK_STRING);
		mActionCommandMap.get(ActionType.DEFEND).setText(SELECTION_STRING + ACTION_DEFEND_STRING);
		mActionCommandMap.get(ActionType.MOVE).setText(SELECTION_STRING + ACTION_MOVE_STRING);
		mActionCommandMap.get(ActionType.ITEM).setText(SELECTION_STRING + ACTION_ITEM_STRING);
		mCurrentAction = ActionType.NONE;

		// Places the character at the end of the list
		BattleObject object = mBattleObjectList.get(0);
		mBattleObjectList.remove(0);
		mBattleObjectList.add(object);
	}

	/**
	 * Tells whether the current action chosen by the user is the one
	 * specified by the parameter.
	 * @param pType
	 * @return
	 */
	public boolean isAction(ActionType pType) {
		return (pType == mCurrentAction);
	}

	public void setEnemyForDuel(BattleObject pEnemy) {
		BattleObject currentAlly = mBattleObjectList.get(0);
		if (pEnemy.getCurrentIndexX() < currentAlly.getCurrentIndexX() - 1) {
			return;
		}
		if (pEnemy.getCurrentIndexY() < currentAlly.getCurrentIndexY() - 1) {
			return;
		}
		if (pEnemy.getCurrentIndexX() > currentAlly.getCurrentIndexX() + 1) {
			return;
		}
		if (pEnemy.getCurrentIndexY() > currentAlly.getCurrentIndexY() + 1) {
			return;
		}

		pEnemy.getCharacter().detachSelf();
		currentAlly.getCharacter().detachSelf();
		BattleScene.this.setChildScene(
				new DuelScene(
						(Ally) currentAlly.getCharacter(),
						(Enemy) pEnemy.getCharacter(), this),
				false, true, true);
		endTurn();
	}

	// TODO Implements "Defense Mode"
	public void prepareAllyToDefend(BattleObject pAlly) {
		BattleObject currentAlly = mBattleObjectList.get(0);
		if (currentAlly.equals(pAlly)) {
			Debug.i("Ready to defend");
			endTurn();
		}
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#create()
	 */
	@Override
	public void onCreate() {
		createBattleMenuInterface();

		mBackground = new Sprite(0, 0,
				mBackgroundRegion,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		BattleScene.this.mBackgroundLayer.attachChild(mBackground);
		BattleScene.this.mBackgroundLayer.attachChild(mGrid);

		generateAlly();
		generateEnemy();

		for (int i = 0; i < mBattleObjectList.size(); ++i) {
			BattleObject object = mBattleObjectList.get(i);
			BattleScene.this.registerTouchArea(object);
			BattleScene.this.mBackgroundLayer.attachChild(object);
			mGrid.putIntoCell(object.getCurrentIndexX(), object.getCurrentIndexY());
		}

//		mGrid.printGrid();

		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);

		this.registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				BattleObject current = mBattleObjectList.get(0);
				mCurrentMarker.setPosition(current);
				// Ally's turn
				if (current.getCharacterType() == CharacterType.ALLY) {
					if (ActionType.MOVE == mCurrentAction) {
						current.setMoveSurfaceVisible(true);
						current.setMovable(true);
					} else {
						current.setMoveSurfaceVisible(false);
						current.setMovable(false);
					}
					if (current.isMoveToValidate()) {
						if (mGrid.isOccupied(current.getCurrentIndexX(),
								current.getCurrentIndexY())) {
							current.invalidateMove();
						} else {
							mGrid.move(current.getOriginIndexX(), current.getOriginIndexY(),
									current.getCurrentIndexX(), current.getCurrentIndexY());
							current.validateMove();
							endTurn(); // Ends ally's turn
						}
					}
				}
				else {
					Debug.i("=> Turn ends");
					// TODO Applies enemy AI
					endTurn();
				} // Enemy's turn
			}

			@Override
			public void reset() {
				// Nothing to do
			}
		});

		this.attachChild(mBackgroundLayer);
		this.attachChild(mMenuLayer);

		mCurrentMarker = new Rectangle(0, 0, 50, 50,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mCurrentMarker.setColor(0.0f, 0.0f, 1.0f, 0.5f);
		this.mBackgroundLayer.attachChild(mCurrentMarker);
		AudioManager.getInstance().playMusic(R.string.ms_09, true, true);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#unloadResources()
	 */
	@Override
	public void onDestroyResources() {
		mBackgroundTextureAtlas.unload();
		mBackgroundRegion = null;
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
		for (int i = 0; i < mBattleObjectList.size(); ++i) {
			mBattleObjectList.get(i).detachSelf();
			mBattleObjectList.get(i).dispose();
		}
		for (Text actionText : mActionCommandMap.values()) {
			actionText.detachSelf();
			actionText.dispose();
		}

		mGrid.detachSelf();
		mGrid.dispose();
		mBackground.detachSelf();
		mBackground.dispose();

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

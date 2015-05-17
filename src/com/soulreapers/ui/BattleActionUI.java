/**
 * 
 */
package com.soulreapers.ui;

import java.util.HashMap;
import java.util.Map.Entry;

import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.ResourceManager;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.scene.BattleScene;
import com.soulreapers.util.SRButton;


/**
 * @author chris
 *
 */
public class BattleActionUI extends Entity {
	private static final String STRING_ACTION    = "Action";
	private static final String STRING_ATTACK    = "Attaquer";
	private static final String STRING_DEFEND    = "Defendre";
	private static final String STRING_MOVE      = "Deplacer";
	private static final String STRING_STATUS    = "Status";
	private static final String STRING_ITEM      = "Objet";

	private static final String STRING_CANCEL    = "Annuler";

//	private static final int FONT_ID = ResourceManager.FONT_TEXT_ID;
//	private static final int FONT_COMMAND_ID = ResourceManager.FONT_OPTION_ID;
	private static final int FONT_TITLE_ID = ResourceManager.FONT_TITLE_ID;

	private ActionType mCurrentAction = ActionType.NONE;
//	private HashMap<ActionType, Text> mActionCommandMap = new HashMap<ActionType, Text>();
	private HashMap<ActionType, SRButton> mActionCommandMap = new HashMap<ActionType, SRButton>();

	private BattleScene mBattleScene;
	private AttributesUI mAttributesUI = new AttributesUI();

	// Menu interface
	private Text mCurrentCommandText = new Text(GameConstants.X_SUBMENU_PADDING,
			GameConstants.Y_SUBMENU_PADDING,
			ResourceManager.getInstance().getFont(FONT_TITLE_ID),
			STRING_ACTION,
			GameConstants.MAX_CHARACTER_SIZE,
			ResourceManager.getInstance().getVertexBufferObjectManager());

	private Rectangle mLine = new Rectangle(GameConstants.X_SUBMENU_PADDING,
			GameConstants.Y_SUBMENU_PADDING + GameConstants.FONT_SIZE,
			GameConstants.CAMERA_WIDTH - GameConstants.X_OPTION_TEXT_PADDING,
			5, ResourceManager.getInstance().getVertexBufferObjectManager());

	public enum ActionType {
		ATTACK {
			@Override
			public String toString() {
				return STRING_ATTACK;
			}

			@Override
			void exec(BattleScene pScene) {
				// TODO Auto-generated method stub
				
			}

			@Override
			void cancel(BattleScene pScene) {
				// TODO Auto-generated method stub
				
			}

		},
		DEFEND {
			@Override
			public String toString() {
				return STRING_DEFEND;
			}

			@Override
			void exec(BattleScene pScene) {
				// TODO Auto-generated method stub
			}

			@Override
			void cancel(BattleScene pScene) {
				// TODO Auto-generated method stub
				
			}
		},
		MOVE {
			@Override
			public String toString() {
				return STRING_MOVE;
			}

			@Override
			void exec(BattleScene pScene) {
				pScene.getCurrentObject().setMoveSurfaceVisible(true);
			}

			@Override
			void cancel(BattleScene pScene) {
				pScene.getCurrentObject().setMoveSurfaceVisible(false);
			}
		},
		ITEM {
			@Override
			public String toString() {
				return STRING_ITEM;
			}

			@Override
			void exec(BattleScene pScene) {
				// TODO Auto-generated method stub
				
			}

			@Override
			void cancel(BattleScene pScene) {
				// TODO Auto-generated method stub
				
			}


		},
		STATUS {
			@Override
			public String toString() {
				return STRING_STATUS;
			}

			@Override
			void exec(BattleScene pScene) {
//				pScene.getAttributesUI().setStatus(pScene.getCurrentObject().getAttributes());
				pScene.getActionUI().mCurrentCommandText.setText(STRING_STATUS);
				pScene.showStatus(pScene.getCurrentObject());
			}

			@Override
			void cancel(BattleScene pScene) {
				pScene.getActionUI().mCurrentCommandText.setText(STRING_ACTION);
				pScene.hideStatus();
				pScene.showCharacterArt(pScene.getCurrentObject());
			}

		},
		NONE {
			@Override
			public String toString() {
				return STRING_ACTION;
			}

			@Override
			void exec(BattleScene pScene) {
				// TODO Auto-generated method stub
				
			}

			@Override
			void cancel(BattleScene pScene) {
				// TODO Auto-generated method stub
				
			}
		};
		abstract void exec(BattleScene pScene);
		abstract void cancel(BattleScene pScene);
	}
	/**
	 * 
	 */
	public BattleActionUI(BattleScene pBattleScene) {
		super();
		mBattleScene = pBattleScene;
		mAttributesUI.setVisible(false);
		createCommandMenu();
		mAttributesUI.setMinMaxEnabled(true);
	}

	public AttributesUI getAttributeUI() {
		return mAttributesUI;
	}

	public boolean isCurrentAction(ActionType pType) {
		return (mCurrentAction == pType);
	}

//	public boolean isCurrentActionMove() {
//		return (mCurrentAction == ActionType.MOVE);
//	}
//
//	public boolean isCurrentActionAttack() {
//		return (mCurrentAction == ActionType.ATTACK);
//	}
//
//	public boolean isCurrentActionDefend() {
//		return (mCurrentAction == ActionType.DEFEND);
//	}
//
//	public boolean isCurrentActionItem() {
//		return (mCurrentAction == ActionType.ITEM);
//	}
//
//	public boolean isCurrentActionStatus() {
//		return (mCurrentAction == ActionType.STATUS);
//	}

	private void addCommand(final ActionType pType, final int pPadding) {
		mActionCommandMap.put(pType, new SRButton(GameConstants.X_SUBMENU_PADDING,
				GameConstants.Y_OPTION_TEXT_PADDING * pPadding,
				pType.toString()) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (BattleActionUI.this.mCurrentAction == ActionType.NONE) {
					BattleActionUI.this.mCurrentAction = pType;
					this.setText(STRING_CANCEL);
					BattleActionUI.this.setTouchAreaEnabled(false);
					this.setEnabled(true);
					this.setVisible(true);
					pType.exec(mBattleScene);
				} // execute action
				else {
					cancelAction();
				} // cancel current action
			}
		});
		Debug.d("Add command " + pType.toString());
	}

	private void createCommandMenu() {
		addCommand(ActionType.ATTACK, 2);
		addCommand(ActionType.DEFEND, 3);
		addCommand(ActionType.MOVE, 4);
		addCommand(ActionType.ITEM, 5);
		addCommand(ActionType.STATUS, 8);
		Debug.d("Command menu created");
	}

	public void setTouchAreaEnabled(boolean pEnabled) {
		for (SRButton button : mActionCommandMap.values()) {
			button.setEnabled(pEnabled);
			button.setVisible(pEnabled);
		}
	}

	public void setCommandButtonsEnabled(boolean pEnabled) {
		for (SRButton button : mActionCommandMap.values()) {
			button.setEnabled(pEnabled);
		}
	}

	public void setCommandButtonsVisible(boolean pVisible) {
		for (SRButton button : mActionCommandMap.values()) {
			button.setVisible(pVisible);
		}
	}

	@Override
	public void onAttached() {
		this.attachChild(mLine);
		this.attachChild(mCurrentCommandText);
		this.attachChild(mAttributesUI);

		for (SRButton button : mActionCommandMap.values()) {
			Debug.d("Attach " + button.getText());
			this.attachChild(button);
			mBattleScene.registerTouchArea(button);
		}
		super.onAttached();
		Debug.d("->>Attached");
	}

	@Override
	public void onDetached() {
		mLine.detachSelf();
		mCurrentCommandText.detachSelf();
		mAttributesUI.detachSelf();
		for (SRButton button : mActionCommandMap.values()) {
			mBattleScene.unregisterTouchArea(button);
			button.detachSelf();
		}
		this.detachChildren();
		super.onDetached();
	}

	@Override
	public void dispose() {
		mCurrentCommandText.dispose();
		mLine.dispose();
		mAttributesUI.dispose();
		for (SRButton actionText : mActionCommandMap.values()) {
			actionText.dispose();
		}
		super.dispose();
	}

	public void endTurn() {
		mCurrentCommandText.setText(ActionType.NONE.toString());
		for (Entry<ActionType, SRButton> action : mActionCommandMap.entrySet()) {
			action.getValue().setText(action.getKey().toString());
		}
		setCommandButtonsVisible(true);
		mCurrentAction = ActionType.NONE;
	}

	public void cancelAction() {
		mCurrentAction.cancel(mBattleScene);
		setTouchAreaEnabled(true);
		mActionCommandMap.get(mCurrentAction).setText(mCurrentAction.toString());
		mCurrentAction = ActionType.NONE;
	}
}

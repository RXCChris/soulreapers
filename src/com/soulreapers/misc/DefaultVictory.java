/**
 * 
 */
package com.soulreapers.misc;

import java.util.Queue;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.FontManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.object.BattleObject;
import com.soulreapers.object.character.GameCharacter.CharacterType;
import com.soulreapers.scene.BattleScene;
import com.soulreapers.scene.SceneNavigator;

/**
 * @author chris
 *
 */
public class DefaultVictory implements VictoryListener {

	private BattleScene mBattleScene;
//	private static final int FONT_ID = ResourceManager.FONT_TITLE_ID;
	private static final String STRING_VICTORY   = "Victoire";
	private static final String STRING_GAME_OVER = "Fin de la Partie";
	private static final int DELAY_DURATION = 3;
//	private static final int MAX_CHAR_SIZE = 18;

	private Text mTextEndBattle = new Text(GameConstants.CAMERA_WIDTH / 2,
			GameConstants.CAMERA_HEIGHT / 2,
			FontManager.getInstance().getFont(FontType.FONT_TITLE_BIG),
			STRING_GAME_OVER,
//			MAX_CHAR_SIZE,
			new TextOptions(HorizontalAlign.CENTER),
			ResourceManager.getInstance().getVertexBufferObjectManager());


	private DelayModifier mModifier = new DelayModifier(DELAY_DURATION) {
		@Override
		public void onModifierStarted(final IEntity pItem) {
			mBattleScene.attachChild(mTextEndBattle);
		}

		@Override
		public void onModifierFinished(final IEntity pItem) {
			SceneManager.getInstance().showScene(SceneNavigator.class);
		}
	};
	/**
	 * 
	 */
	public DefaultVictory(BattleScene pBattleScene) {
		mBattleScene = pBattleScene;
	}

	@Override
	public boolean checkVitoryCondition(final Queue<BattleObject> pBattleObjectList) {
		int enemyCount = 0;
		int reaperCount = 0;
		for (BattleObject object : pBattleObjectList) {
			if (object.getCharacterType() == CharacterType.REMNANT) {
				++enemyCount;
			} else if (object.getCharacterType() == CharacterType.REAPER) {
				++reaperCount;
			}
		}

		if (reaperCount == 0) {
			onGameOverFired();
			return true;
		} else if (enemyCount == 0) {
			onVictoryFired();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.misc.VictoryListener#onGameOverFired()
	 */
	@Override
	public void onGameOverFired() {
		Debug.d("Game Over!!!");
		mTextEndBattle.setText(STRING_GAME_OVER);
		mTextEndBattle.setPosition(mTextEndBattle.getX() - mTextEndBattle.getWidth() / 2,
				mTextEndBattle.getY() - mTextEndBattle.getHeight() / 2);
		mBattleScene.registerEntityModifier(mModifier);
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.misc.VictoryListener#onVictoryFired()
	 */
	@Override
	public void onVictoryFired() {
		Debug.d("Victory!!!");
		mTextEndBattle.setText(STRING_VICTORY);
		mTextEndBattle.setPosition(mTextEndBattle.getX() - mTextEndBattle.getWidth() / 2,
				mTextEndBattle.getY() - mTextEndBattle.getHeight() / 2);
		mBattleScene.registerEntityModifier(mModifier);
	}

}

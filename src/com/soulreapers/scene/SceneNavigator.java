/**
 * 
 */
package com.soulreapers.scene;

import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.ConditionState;
import com.soulreapers.misc.GameProgressionRecord;

/**
 * @author CChris
 *
 */
public class SceneNavigator extends BaseScene {

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onLoadResources()
	 */
	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		if (GameProgressionRecord.getInstance().isCondition(0, ConditionState.NONE)) {
			SceneManager.getInstance().showScene(PrologueScene.class);
		} else {
			SceneManager.getInstance().showScene(GameMenuScene.class);
		}
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onDestroyResources()
	 */
	@Override
	public void onDestroyResources() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onPause()
	 */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onResume()
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

}

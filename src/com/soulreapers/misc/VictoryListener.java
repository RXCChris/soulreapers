/**
 * 
 */
package com.soulreapers.misc;

import java.util.Queue;

import com.soulreapers.object.BattleObject;

/**
 * @author chris
 *
 */
public interface VictoryListener {
	public abstract boolean checkVitoryCondition(final Queue<BattleObject> pBattleObjectList);
	public abstract void onGameOverFired();
	public abstract void onVictoryFired();
}

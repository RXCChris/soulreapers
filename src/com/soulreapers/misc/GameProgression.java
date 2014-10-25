/**
 * 
 */
package com.soulreapers.misc;

import java.util.ArrayList;

/**
 * @author CChris
 *
 */
public class GameProgression {
	private static final int CONDITIONS = 5;
	private static ArrayList<ConditionState> mProgress = new ArrayList<ConditionState>();

	public GameProgression() {
//		Iterator<ProgressionState> it = mProgress.iterator();
		for (int i = 0; i < CONDITIONS; i++) {
			mProgress.add(ConditionState.NONE);
		}
	}

	/**
	 * 
	 * @param conditionNum
	 * @param conditionState
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean isConditon(int conditionNum, ConditionState conditionState) throws IllegalArgumentException {
		if (CONDITIONS <= conditionNum) {
			throw new IllegalArgumentException("Condition number exceeds the total number of conditions");
		} else {
			if (mProgress.get(conditionNum) == conditionState) {
				return true;
			} else {
				return false;
			}
		}
	}

	public ConditionState getConditionState(int conditionNum) throws IllegalArgumentException {
		if (CONDITIONS <= conditionNum) {
			throw new IllegalArgumentException("Condition number exceeds the total number of conditions");
		} else {
			return mProgress.get(conditionNum);
		}
	}

	public void setConditionState(int conditionNum, ConditionState conditionState) throws IllegalArgumentException {
		if (CONDITIONS <= conditionNum) {
			throw new IllegalArgumentException("Condition number exceeds the total number of conditions");
		} else {
			mProgress.set(conditionNum, conditionState);
		}
	}
}

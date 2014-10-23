package com.soulreapers.object.character;

import com.soulreapers.GameActivity;

public abstract class PlayableCharacter extends Character {

	protected int mHp, mHpMax;
	protected int mPosX, mPosY;

	public PlayableCharacter(String name, GameActivity activity, int resId,
			int width, int height, int hpMax) {
		super(name, activity, resId, width, height);
		mHp = hpMax;
		mHpMax = hpMax;
	}

	public int getHp() {
		return mHp;
	}

	public void setHp(int hp) {
		this.mHp = hp;
	}

	public int getHpMax() {
		return mHpMax;
	}

	public void setHpMax(int hpMax) {
		this.mHpMax = hpMax;
	}

	public int getPosX() {
		return mPosX;
	}

	public void setPosX(int posX) {
		this.mPosX = posX;
	}

	public int getPosY() {
		return mPosY;
	}

	public void setPosY(int posY) {
		this.mPosY = posY;
	}

	public abstract void onDie();

	public void move(int offsetX, int offsetY) {
		mPosX += offsetX;
		mPosY += offsetY;
	}

	public void setPosition(int posX, int posY) {
		mPosX = posX;
		mPosY = posY;
	}
}

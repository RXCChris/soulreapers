package com.soulreapers.object.character;

import org.andengine.entity.scene.Scene;

import com.soulreapers.GameActivity;
import com.soulreapers.misc.HealthBar;

public abstract class PlayableCharacter extends Character {

//	protected int mHp, mHpMax;
	protected int mPosX, mPosY;
	protected HealthBar mHealth;

//	public PlayableCharacter(String name, GameActivity activity, int resId,
//			int width, int height, int hpMax) {
//		super(name, activity, resId, width, height);
////		mHp = hpMax;
////		mHpMax = hpMax;
//		mHealth = new HealthBar(100, 100, activity.getVertexBufferObjectManager());
//	}
	public PlayableCharacter(String name, int resId,
			int width, int height, int hpMax) {
		super(name, resId, width, height);
//		mHp = hpMax;
//		mHpMax = hpMax;
//		mHealth = new HealthBar(100, 100, activity.getVertexBufferObjectManager());
		mHealth = new HealthBar(100, 100);
	}

	public int getHp() {
//		return mHp;
		return mHealth.getHp();
	}

	public void setHp(int hp) {
//		this.mHp = hp;
		mHealth.setHp(hp);
	}

	public int getHpMax() {
//		return mHpMax;
		return mHealth.getHpMax();
	}

	public void setHpMax(int hpMax) {
//		this.mHpMax = hpMax;
		mHealth.setHpMax(hpMax);
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

	@Override
	public void display(Scene scene) {
		super.display(scene);
		mHealth.display(scene);
	}

	@Override
	public void destroy() {
		mHealth.clear();
		super.destroy();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		mHealth.setVisible(visible);
	}
}

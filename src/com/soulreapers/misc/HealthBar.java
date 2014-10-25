/**
 * 
 */
package com.soulreapers.misc;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;

/**
 * @author CChris
 *
 */
public class HealthBar extends Rectangle {
	private int mHp, mHpMax;
	private final static float mWidth = 200;
	private final static float mHeight = 20;
	private Text mTextHp;

	/**
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pRectangleVertexBufferObject
	 */

//	public HealthBar(int hp, int hpMax, VertexBufferObjectManager vbom) {
//		super(20, 420, mWidth, mHeight, vbom);
//		mHp = hp;
//		mHpMax = hpMax;
//		setColor(Color.RED);
//		mTextHp = new Text(20, 380, ResourceManager.getInstance().getFont(R.string.ft_01), "HPXXXXXXXXXXXX", vbom);
////		mTextHp.setScale(0.5f);
//		mTextHp.setText("HP      " + mHp);
//	}

	public HealthBar(int hp, int hpMax) {
		super(20, 420, mWidth, mHeight, ResourceManager.getInstance().getVertexBufferObjectManager());
		mHp = hp;
		mHpMax = hpMax;
		setColor(Color.RED);
		mTextHp = new Text(20, 380, ResourceManager.getInstance().getFont(R.string.ft_01), "HPXXXXXXXXXXXX", ResourceManager.getInstance().getVertexBufferObjectManager());
//		mTextHp.setScale(0.5f);
		mTextHp.setText("HP      " + mHp);
	}


	public int getHp() {
		return mHp;
	}

	public int getHpMax() {
		return mHpMax;
	}

	public void setHp(int hp) {
		mHp = hp;
		updateWidth();
	}

	public void setHpMax(int hpMax) {
		mHpMax = hpMax;
		updateWidth();
	}

	public void increaseHp(int amount) {
		mHp += amount;
		if (mHp > mHpMax) {
			mHp = mHpMax;
		}
		updateWidth();
	}

	public void decreaseHp(int amount) {
		mHp -= amount;
		if (mHp < 0) {
			mHp = 0;
		}
		updateWidth();
	}

	public void display(Scene scene) {
		scene.attachChild(mTextHp);
		scene.attachChild(this);
	}

	private void updateWidth() {
		setWidth((float) mWidth * ((float) mHp / (float) mHpMax));
	}

	public void clear() {
		mTextHp.detachSelf();
		mTextHp.dispose();
		super.detachSelf();
		super.dispose();
	}
}

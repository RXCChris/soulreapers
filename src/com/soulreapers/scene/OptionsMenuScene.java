package com.soulreapers.scene;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;

public class OptionsMenuScene extends BaseScene {
	private Text mTextBgm;
	private boolean mBgmEnabled = true;

	private Text mTextBack;

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate() {
//		mTextBgm = new Text(100, 100, ResourceManager.getInstance().getFont(R.string.ft_04), "BGM  ON", "BGMXXXXXXX".length(), new TextOptions(HorizontalAlign.LEFT), mVbom) {
		mTextBgm = new Text(100, 100, ResourceManager.getInstance().getFont(R.string.ft_04), "BGM  ON", "BGMXXXXXXX".length(), new TextOptions(HorizontalAlign.LEFT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Debug.i("You touched BGM");
				if (pSceneTouchEvent.isActionDown()) {
					mBgmEnabled = !mBgmEnabled;
					if (mBgmEnabled) {
						AudioManager.getInstance().enableMusic(true);
						mTextBgm.setText("BGM    " + "ON");
					} else {
						AudioManager.getInstance().enableMusic(false);
						mTextBgm.setText("BGM    " + "OFF");
					}
				}
				return true;
			}
		};

//		mTextBack = new Text(500, 420, ResourceManager.getInstance().getFont(R.string.ft_04), mActivity.getString(R.string.tb_09), new TextOptions(HorizontalAlign.LEFT), mVbom) {
		mTextBack = new Text(500, 420, ResourceManager.getInstance().getFont(R.string.ft_04), ResourceManager.getInstance().getResourceString(R.string.tb_09), new TextOptions(HorizontalAlign.LEFT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Back");
					SceneManager.getInstance().showScene(MainMenuScene.class);
				}
				return true;
			}
		};
		registerTouchArea(mTextBgm);
		registerTouchArea(mTextBack);

		attachChild(mTextBgm);
		attachChild(mTextBack);
	}

	@Override
	public void onDestroyResources() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
//		this.detachSelf();
//		this.dispose();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

}

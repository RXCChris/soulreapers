package com.soulreapers.scene;

import java.util.HashMap;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.ConditionState;
import com.soulreapers.misc.GameProgression;
import com.soulreapers.object.character.MainPlayer;

public class GameScene extends BaseScene {

	private HUD mGameHUD;
	private Text mCharacterName;
	private MainPlayer mPlayer;
	private GameProgression mProgression = new GameProgression();
	private Text mWarning;

//	private HashMap<String, Text> mTextButtons = new HashMap<String, Text>();
	private HashMap<Integer, Text> mTextButtons = new HashMap<Integer, Text>();

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate() {
		mGameHUD = new HUD();
		SceneManager.getInstance().getCamera().setHUD(mGameHUD);
//		mCamera.setHUD(mGameHUD);
//		mPlayer = new MainPlayer("Dante", mActivity, R.string.pc_01, 800, 1060, 500);
		mPlayer = new MainPlayer("Dante", R.string.pc_01, 800, 1060, 500);

		mCharacterName = new Text(400, 240, ResourceManager.getInstance().getFont(R.string.ft_01), mPlayer.getName(), "XXXXXXXXX".length(), new TextOptions(HorizontalAlign.LEFT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				Debug.i("You touched the name of the character");
				return true;
			}
		};

		mWarning = new Text(10, 240, ResourceManager.getInstance().getFont(R.string.ft_03), ResourceManager.getInstance().getResourceString(R.string.mg_02), new TextOptions(HorizontalAlign.LEFT), ResourceManager.getInstance().getVertexBufferObjectManager());
		mWarning.setVisible(false);

		registerTouchArea(mCharacterName);

//		mCamera.setHUD(mGameHUD);
		SceneManager.getInstance().getCamera().setHUD(mGameHUD);
		AudioManager.getInstance().pauseMusic();
		AudioManager.getInstance().playMusic(R.string.ms_01, true);
		mPlayer.setSpritePosition(-400, 0);
		mPlayer.display(mGameHUD);


		if (mProgression.isConditon(1, ConditionState.DONE)) {
			mCharacterName.setText("FINISHED");
		}

		mGameHUD.attachChild(mCharacterName);
		mGameHUD.attachChild(mWarning);

		createSubMenu();
	}

	private void createSubMenu() {
//		String key;
//
//		// Items
//		key = mActivity.getString(R.string.tb_05);
		int key;
		key = R.string.tb_05;
		mTextButtons.put(key,
				new Text(500, 50, ResourceManager.getInstance().getFont(R.string.ft_03),
						ResourceManager.getInstance().getResourceString(key), new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					mWarning.setVisible(true);
					Debug.i("You touched Items");
				}
				return true;
			}
		});

		// Equip
		key = R.string.tb_06;
		mTextButtons.put(key,
				new Text(500, 100, ResourceManager.getInstance().getFont(R.string.ft_03),
						ResourceManager.getInstance().getResourceString(key), new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					mWarning.setVisible(false);
					Debug.i("You touched Equip");
				}
				return true;
			}
		});

		key = R.string.tb_07;
		mTextButtons.put(key,
				new Text(500, 150, ResourceManager.getInstance().getFont(R.string.ft_03),
						ResourceManager.getInstance().getResourceString(key), new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Status");
				}
				return true;
			}
		});

		// Map
		key = R.string.tb_10;
		mTextButtons.put(key,
				new Text(500, 200, ResourceManager.getInstance().getFont(R.string.ft_03),
						ResourceManager.getInstance().getResourceString(key), new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Map");
				}
				return true;
			}
		});

		// Diary
		key = R.string.tb_11;
		mTextButtons.put(key,
				new Text(500, 250, ResourceManager.getInstance().getFont(R.string.ft_03),
						ResourceManager.getInstance().getResourceString(key), new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Diary");
				}
				return true;
			}
		});

		// Save
		key = R.string.tb_08;
		mTextButtons.put(key,
				new Text(500, 2000, ResourceManager.getInstance().getFont(R.string.ft_03),
						ResourceManager.getInstance().getResourceString(key), new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Save");
				}
				return true;
			}
		});

		// Back
		key = R.string.tb_09;
		mTextButtons.put(key,
				new Text(500, 420, ResourceManager.getInstance().getFont(R.string.ft_03),
						ResourceManager.getInstance().getResourceString(key), new TextOptions(HorizontalAlign.RIGHT), ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Back");
					SceneManager.getInstance().showScene(MainMenuScene.class);
				}
				return true;
			}
		});


		for (Text object : mTextButtons.values()) {
			mGameHUD.attachChild(object);
			registerTouchArea(object);
		}
	}

	@Override
	public void onDestroyResources() {
		mPlayer.clear();
	}

	@Override
	public void onDestroy() {
		mPlayer.destroy();

		for (Text object : mTextButtons.values()) {
			unregisterTouchArea(object);
			object.detachSelf();
			object.dispose();
		}

		mCharacterName.detachSelf();
		mCharacterName.dispose();
		mWarning.detachSelf();
		mWarning.dispose();

		AudioManager.getInstance().pauseMusic();

		this.detachSelf();
		this.dispose();
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

package com.soulreapers.scene;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.misc.MusicTrack;
import com.soulreapers.object.character.MainPlayer;

public class GameScene extends BaseScene {

	private HUD mGameHUD;
	private Text mCharacterName;
	private MainPlayer mPlayer;

	@Override
	public void loadResources() {
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		mGameHUD = new HUD();
		mCamera.setHUD(mGameHUD);
		mPlayer = new MainPlayer("Dante", mActivity, R.string.player_dante, 800, 1060, 500);
		mCharacterName = new Text(20, 420, mResourceManager.getFont(), mPlayer.getName(), new TextOptions(HorizontalAlign.LEFT), mVbom);
		mGameHUD.attachChild(mCharacterName);
		mCamera.setHUD(mGameHUD);
		AudioManager.getInstance().playMusic(MusicTrack.TRACK_02, true);
	}

	@Override
	public void unloadResources() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

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

package com.soulreapers;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.GameConstants;

public class GameActivity extends BaseGameActivity {

	/*
	 * Constants
	 */


	@Override
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0,
				GameConstants.CAMERA_WIDTH,
				GameConstants.CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED,
				new RatioResolutionPolicy(GameConstants.CAMERA_WIDTH,
						GameConstants.CAMERA_HEIGHT), camera);
		engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		engineOptions.getRenderOptions().setDithering(true);
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engineOptions;
	}

	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		Engine engine = new LimitedFPSEngine(pEngineOptions,
				GameConstants.FPS_LIMIT);
		return engine;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		ResourceManager.getInstance().initialize(this);
		AudioManager.getInstance().initialize(this);
		SceneManager.getInstance().initialize(this);
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onDestroy() {
		Debug.i(">>GameActivity call onDestroy");
		ResourceManager.getInstance().onDestroy();
		AudioManager.getInstance().onDestroy();
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (this.isGameLoaded()) {
			SceneManager.getInstance().getCurrentScene().onPause();
			AudioManager.getInstance().pauseMusic();
		}
		super.onPause();
	}

	@Override
	protected synchronized void onResume() {
		System.gc();
		if (this.isGameLoaded()) {
			SceneManager.getInstance().getCurrentScene().onResume();
			AudioManager.getInstance().resumeMusic();
		}
		super.onResume();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws IOException {
		SceneManager.getInstance().showSplash(pOnCreateSceneCallback);
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	@Override
	public void onDestroyResources() {
		if (this.isGameLoaded()) {
			SceneManager.getInstance().getCurrentScene().onDestroyResources();
		}
		try {
			super.onDestroyResources();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onKeyDown(int pKeyCode, final KeyEvent pEvent) {
		if ((pKeyCode == KeyEvent.KEYCODE_MENU) && (pEvent.getAction() == KeyEvent.ACTION_DOWN)) {
			if (this.mEngine.isRunning()) {
				this.mEngine.stop();
			} else {
				this.mEngine.start();
			}
		}
		if (pKeyCode == KeyEvent.KEYCODE_BACK) {
			quit();
		}
		return false;
	}

	public void quit() {
		new AlertDialog.Builder(this).setMessage("Etes-vous sur de vouloir quitter ?")
		                             .setCancelable(false)
		                             .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											finish();
										}
									})
									.setNegativeButton("Non", null)
									.show();
	}
}

package com.soulreapers.core;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.util.debug.Debug;

import com.soulreapers.GameActivity;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.scene.*;

import android.os.AsyncTask;

/**
 * 
 * @author chris
 *
 */
public class SceneManager {

	private static final SceneManager INSTANCE = new SceneManager();

	private Engine mEngine;
	private GameActivity mActivity;

	private BaseScene mCurrentScene;
	private LoadingScene mLoadingScene;

	private SceneManager() {
		// nothing to do
	}

	public Camera getCamera() {
		return mActivity.getEngine().getCamera();
	}

	public static SceneManager getInstance() {
		return INSTANCE;
	}

	public void initialize(GameActivity activity) {
		getInstance().mActivity = activity;
		getInstance().mEngine = activity.getEngine();
	}

	public BaseScene getCurrentScene() {
		return mCurrentScene;
	}

	public void setCurrentScene(BaseScene scene) {
		mCurrentScene = scene;
		Debug.i("Set a new scene " + scene.toString());
	}

	public void showSplash(OnCreateSceneCallback pOnCreateSceneCallback) {
		Debug.i("Scene: Splash");
		final SplashScene splash = new SplashScene();
		setCurrentScene(splash);
//		splash.initialize(mActivity, mResourceManager);
		splash.onLoadResources();
		splash.onCreate();
		mEngine.setScene(splash);


		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				long timestamp = System.currentTimeMillis();
				// TODO load common resources
				MainMenuScene menu = new MainMenuScene();
//				menu.initialize(mActivity, mResourceManager);
				menu.onLoadResources();
				menu.onCreate();
				mLoadingScene = new LoadingScene();
//				mLoadingScene.initialize(mActivity, mResourceManager);
				mLoadingScene.onLoadResources();
				mLoadingScene.onCreate();

				long elapsed = System.currentTimeMillis() - timestamp;
				if (elapsed < GameConstants.SPLASH_DURATION) {
					try {
						Thread.sleep(GameConstants.SPLASH_DURATION - elapsed);
					} catch (InterruptedException e) {
						Debug.w("This should not happen");
					}
				}
				setCurrentScene(menu);
				mEngine.setScene(menu);
				splash.onDestroy();
				splash.onDestroyResources();
				return null;
			}
		}.execute();
		pOnCreateSceneCallback.onCreateSceneFinished(splash);
	}

	public void showScene(Class<? extends BaseScene> sceneClass) {
		if (sceneClass == LoadingScene.class) {
			throw new IllegalArgumentException("you can't switch to Loading scene");
		}
		try {
			final BaseScene scene = sceneClass.newInstance();
			Debug.i("Showing scene: " + scene.getClass().getName());

			final BaseScene oldScene = getCurrentScene();
			setCurrentScene(mLoadingScene);
			mEngine.setScene(mLoadingScene);
			new AsyncTask<Void, Void, Void>() {
				@Override
				protected Void doInBackground(Void... params) {
					if (oldScene != null) {
						oldScene.onDestroyResources();
						oldScene.onDestroy();
					}
//					scene.initialize(mActivity, mResourceManager);
					scene.onLoadResources();
					scene.onCreate();
					setCurrentScene(scene);
					mEngine.setScene(scene);
					return null;
				}
			}.execute();
		} catch (Exception e) {
			Debug.e("Error while changing scene", e);
			throw new RuntimeException("Error", e);
		}
	}
}

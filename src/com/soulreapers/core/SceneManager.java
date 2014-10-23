package com.soulreapers.core;

import org.andengine.engine.Engine;
import org.andengine.util.debug.Debug;

import com.soulreapers.GameActivity;
import com.soulreapers.scene.*;

import android.os.AsyncTask;

public class SceneManager {

	private static final SceneManager INSTANCE = new SceneManager();

	private ResourceManager mResourceManager = ResourceManager.getInstance();
	private Engine mEngine;
	private GameActivity mActivity;

	private BaseScene mCurrentScene;
	private LoadingScene mLoadingScene;

	private SceneManager() {
		// nothing to do
	}

	public static SceneManager getInstance() {
		return INSTANCE;
	}

	public void init(GameActivity activity) {
		mActivity = activity;
		mEngine = activity.getEngine();
	}

	public BaseScene getCurrentScene() {
		return mCurrentScene;
	}

	public void setCurrentScene(BaseScene scene) {
		mCurrentScene = scene;
		Debug.i("Set a new scene " + scene.toString());
	}

	public void showSplash() {
		Debug.i("Scene: Splash");
		final SplashScene splash = new SplashScene();
		setCurrentScene(splash);
		splash.initialize(mActivity, mResourceManager);
		splash.loadResources();
		splash.create();
		mEngine.setScene(splash);

		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				long timestamp = System.currentTimeMillis();
				// TODO load common resources
				MainMenuScene menu = new MainMenuScene();
				menu.initialize(mActivity, mResourceManager);
				menu.loadResources();
				menu.create();
				mLoadingScene = new LoadingScene();
				mLoadingScene.initialize(mActivity, mResourceManager);
				mLoadingScene.loadResources();
				mLoadingScene.create();

				long elapsed = System.currentTimeMillis() - timestamp;
				if (elapsed < GameActivity.SPLASH_DURATION) {
					try {
						Thread.sleep(GameActivity.SPLASH_DURATION - elapsed);
					} catch (InterruptedException e) {
						Debug.w("This should not happen");
					}
				}
				setCurrentScene(menu);
				mEngine.setScene(menu);
				splash.destroy();
				splash.unloadResources();
				return null;
			}
		}.execute();
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
						oldScene.destroy();
						oldScene.unloadResources();
					}
					scene.initialize(mActivity, mResourceManager);
					scene.loadResources();
					scene.create();
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

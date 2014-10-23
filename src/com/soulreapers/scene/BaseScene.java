package com.soulreapers.scene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.soulreapers.core.*;
import com.soulreapers.GameActivity;

public abstract class BaseScene extends Scene {

	protected ResourceManager mResourceManager = ResourceManager.getInstance();
	protected SceneManager mSceneManager = SceneManager.getInstance();

	protected Engine mEngine;
	protected GameActivity mActivity;
	protected VertexBufferObjectManager mVbom;
	protected Camera mCamera;

	public void initialize(GameActivity activity, ResourceManager resourceManager) {
		mActivity = activity;
		mVbom = activity.getVertexBufferObjectManager();
		mEngine = activity.getEngine();
		mCamera = mEngine.getCamera();
	}

	public abstract void loadResources();
	public abstract void create();
	public abstract void unloadResources();
	public abstract void destroy();
	public abstract void onPause();
	public abstract void onResume();

	public void onBackKeyPressed() {
		Debug.d("Back key Pressed");
	}
}

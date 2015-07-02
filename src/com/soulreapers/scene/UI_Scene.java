package com.soulreapers.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.util.debug.Debug;

import com.soulreapers.misc.GameConstants;
import com.soulreapers.ui.IUserInterface;

/**
 * This class is a basic representation of each scene in the game.
 * <p>
 * Notice that it is an abstract class, and every methods must be
 * overridden by any inherited class.
 * </p>
 *
 * @since 2014.10.29
 * @version 0.1 (alpha)
 * @author dxcloud
 */
public abstract class UI_Scene extends Scene implements IUserInterface {
	/**
	 * Called when the scene is instanciated.
	 * Use this method to load any asset used for the scene.
	 */
	protected abstract void onLoadResources();

	public final void loadResources() {
		this.onLoadResources();
	}

	/**
	 * Called after {@link #onLoadResources()}.
	 * <P>
	 * This method is used for initializing the scene, such as
	 * positionning sprites, texts, and others entities...
	 * </P>
	 */
	protected abstract void onCreate();

	public final void create() {
		this.onCreate();
	}

	/**
	 * Called when the scene needs unload resources previously loaded
	 * with {@link #onLoadResources()}.
	 */
	protected abstract void onDestroyResources();
	public final void unloadResource() {
		this.onDestroyResources();
	}

	/**
	 * Called when the scene is about to destroy.
	 */
	protected abstract void onDestroy();

	public final void destroy() {
		this.onDestroy();
		this.detachSelf();
		this.dispose();
		Debug.d("Destroyed " + toString());
	}

	public final void pause() {
		this.onPause();
	}

	/**
	 * Called when the activity is going into pause state.
	 */
	protected abstract void onPause();


	/**
	 * Called to resume the game after being paused.
	 */
	protected abstract void onResume();

	public final void resume() {
		this.onResume();
	}

	public void restore() { }

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.IUserInterface#getWidth()
	 */
	@Override
	public float getWidth() {
		return GameConstants.CAMERA_WIDTH;
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.ui.IUserInterface#getHeight()
	 */
	@Override
	public float getHeight() {
		return GameConstants.CAMERA_HEIGHT;
	}

	@Override
	public String toString() {
		return "UI_Scene";
	}
}

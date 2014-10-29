package com.soulreapers.scene;

import org.andengine.entity.scene.Scene;

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
public abstract class BaseScene extends Scene {
	/**
	 * Called when the scene is instanciated.
	 * Use this method to load any asset used for the scene.
	 */
	public abstract void onLoadResources();

	/**
	 * Called after {@link #onLoadResources()}.
	 * <P>
	 * This method is used for initializing the scene, such as
	 * positionning sprites, texts, and others entities...
	 * </P>
	 */
	public abstract void onCreate();

	/**
	 * Called when the scene needs unload resources previously loaded
	 * with {@link #onLoadResources()}.
	 */
	public abstract void onDestroyResources();

	/**
	 * Called when the scene is about to destroy.
	 */
	public abstract void onDestroy();

	/**
	 * Called when the activity is going into pause state.
	 */
	public abstract void onPause();

	/**
	 * Called to resume the game after being paused.
	 */
	public abstract void onResume();
}

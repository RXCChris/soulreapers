package com.soulreapers.core;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;

import com.soulreapers.GameActivity;

/**
 * Class for handling musics and sounds.
 * <p>
 * Only one music can be played at time.
 * </p>
 * This class is implemented with Singleton Pattern.
 * @author CChris
 * TODO Sound handling methods
 */
public class AudioManager {
	/**
	 * Only instance of the class.
	 */
	private static final AudioManager INSTANCE = new AudioManager();
	private GameActivity mActivity;

	private boolean mMusicEnabled = true;
	private Music mMusic = null;

	/**
	 * Get the instance of the class.
	 * @return Only instance of the class
	 */
	public static AudioManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Private constructor.
	 * Instantiation is not allowed.
	 */
	private AudioManager() {
		// Nothing to do
	}

	public void initialize(GameActivity activity) {
		mActivity = activity;
	}

	/**
	 * Play a new track.
	 * @param track Number of track to play
	 * @see #playMusic(int, boolean)
	 */
	public void playMusic(int track) {
		playMusic(track, false);
	}

	/**
	 * Play a new track.
	 * @param track Number of track to play
	 * @param loop Loop the music whether set to true.
	 * @see #playMusic(int)
	 */
	public void playMusic(int track, boolean loop) {
		if (mMusicEnabled) {
			releaseMusic();
			loadMusic(track);
			mMusic.setLooping(loop);
			mMusic.play();
		}
	}

	/**
	 * Load music.
	 * @param track Track to load
	 * @see MusicTrack
	 */
	private void loadMusic(int track) {
		try {
			mMusic = MusicFactory.createMusicFromAsset(mActivity.getMusicManager(), mActivity, mActivity.getString(track));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pauseMusic() {
		mMusic.pause();
	}

	public void stopMusic() {
		mMusic.stop();
	}

	public void enableMusic(boolean enabled) {
		mMusicEnabled = enabled;
	}

	private void releaseMusic() {
		if (mMusic != null) {
			mMusic.release();
		}
	}
}

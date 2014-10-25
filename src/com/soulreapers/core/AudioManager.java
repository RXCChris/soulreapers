package com.soulreapers.core;

import java.io.IOException;
import java.util.HashMap;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.util.debug.Debug;

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
	 * Unique instance of the class.
	 */
	private static final AudioManager INSTANCE = new AudioManager();
	private GameActivity mActivity;

	/**
	 * Enable music to play whether set to <b>true</b>, disable it otherwise.
	 */
	private boolean mMusicEnabled = true;

	private HashMap<Integer, Music> mMusicMap = new HashMap<Integer, Music>();

	/**
	 * Current playing track.
	 */
	private int mPlayingTrack = 0;

	/**
	 * Get the unique instance of the Class.
	 * @return Unique instance of the Class
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

	/**
	 * Initialize <b>AudioManager</b>.
	 * <p>
	 * This methods must be called explicitly once.
	 * </p>
	 * @param activity Reference of the game context
	 */
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
	 * @param loop Enable to loop the music whether set to <b>true</b>.
	 * @see #playMusic(int)
	 */
	public void playMusic(int track, boolean loop) {
		if (mMusicEnabled) {
			if (!mMusicMap.containsKey(track)) {
				loadMusic(track);
			}
			mPlayingTrack = track;
			mMusicMap.get(mPlayingTrack).setLooping(loop);
			mMusicMap.get(mPlayingTrack).play();
		}
		Debug.i(">>>play music");
	}

	/**
	 * Load music.
	 * @param track Track to load
	 * @see MusicTrack
	 */
	private void loadMusic(int track) {
		Debug.i(">>>load music");
		try {
			mMusicMap.put(track,
					MusicFactory.createMusicFromAsset(mActivity.getMusicManager(),
							mActivity, mActivity.getString(track)));
		} catch (IOException e) {
			Debug.e(e);
		}
	}

	/**
	 * Pause the current playing music.
	 */
	public void pauseMusic() {
		if (mMusicMap.containsKey(mPlayingTrack)) {
			if (mMusicMap.get(mPlayingTrack).isPlaying()) {
				mMusicMap.get(mPlayingTrack).pause();
				Debug.i(">>>pause music");
			}
		}
	}

	/**
	 * Resume the previously paused music
	 */
	public void resumeMusic() {
		if (mMusicMap.containsKey(mPlayingTrack)) {
			mMusicMap.get(mPlayingTrack).resume();
			Debug.i(">>>resume music");
		}
	}

	/**
	 * Stop playing the music.
	 * <p>
	 * <b>Warning:</p> Do not call this method whether the music
	 * may be replaying again.
	 * </p>
	 */
	public void stopMusic() {
		if (mMusicMap.containsKey(mPlayingTrack)) {
			if (mMusicMap.get(mPlayingTrack).isPlaying()) {
				mMusicMap.get(mPlayingTrack).stop();
			}
		}
	}

	/**
	 * Enable/Disable the music to play.
	 * @param enabled Enable the music to play whether set to <b>true</p>,
	 * disable it otherwise
	 */
	public void enableMusic(boolean enabled) {
		mMusicEnabled = enabled;
		if (mMusicEnabled == false) {
			pauseMusic();
		} else {
			resumeMusic();
		}

	}

	public void onDestroy() {
		for (Music music : mMusicMap.values()) {
			music.release();
			Debug.i(">>>release music");
		}
		mMusicMap.clear();
		mPlayingTrack = 0;
	}
}

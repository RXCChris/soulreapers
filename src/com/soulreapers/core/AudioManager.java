package com.soulreapers.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.music.exception.MusicReleasedException;
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
	public enum MusicID {
		TITLE,
		MENU,
		BATTLE_1,
		BATTLE_2,
		BATTLE_BOSS
	}
	/**
	 * Unique instance of the class.
	 */
	private static final AudioManager INSTANCE = new AudioManager();
	private GameActivity mActivity;

	/**
	 * Enable music to play whether set to <b>true</b>, disable it otherwise.
	 */
	private boolean mMusicEnabled = false;

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

	public boolean isMusicEnabled() {
		return mMusicEnabled;
	}

	/**
	 * Play a new track.
	 * @param track Number of track to play
	 * @see #playMusic(int, boolean, boolean)
	 */
	public void playMusic(int pTrack, boolean pReset) {
		playMusic(pTrack, true, pReset);
	}

	/**
	 * Play a new track.
	 * @param track Number of track to play
	 * @see #playMusic(int, boolean, boolean)
	 */
	public void playMusic(int pTrack) {
		playMusic(pTrack, true, true);
	}

	/**
	 * Play a new track.
	 * @param track Number of track to play
	 * @param loop Enable to loop the music whether set to <b>true</b>.
	 */
	public void playMusic(int pTrack, boolean pLoop, boolean pReset) {
		if (!mMusicMap.containsKey(pTrack)) {
			loadMusic(pTrack);
		}

		if (mMusicMap.containsKey(pTrack) && isMusicEnabled()) {
			Music music = mMusicMap.get(pTrack);
			try {
				music.setLooping(pLoop);
				if (pReset) {
					music.seekTo(0);
				}
				if (mMusicEnabled) { music.play(); }
			} catch (MusicReleasedException e) {
				e.printStackTrace();
			}
		}
		mPlayingTrack = pTrack;
		Debug.d("AudioManager : playing " + mActivity.getString(pTrack));

	}

	/**
	 * Load music.
	 * @param track Track to load
	 * @see MusicTrack
	 */
	private void loadMusic(int pTrack) {
		try {
			mMusicMap.put(pTrack,
					MusicFactory.createMusicFromAsset(mActivity.getMusicManager(),
							mActivity, mActivity.getString(pTrack)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Debug.i("AudioManager : loaded " + mActivity.getString(pTrack));
	}

	/**
	 * Pause the current playing music.
	 */
	public void pauseMusic() {
		if (mMusicMap.containsKey(mPlayingTrack)) {
			if (mMusicMap.get(mPlayingTrack).isPlaying()) {
				try {
					mMusicMap.get(mPlayingTrack).pause();
				} catch (MusicReleasedException e) {
					e.printStackTrace();
				}
				Debug.i("AudioManager : paused " + mActivity.getString(mPlayingTrack));
			}
		}
	}

	/**
	 * Resume the previously paused music
	 */
	public void resumeMusic() {
		if (mMusicMap.containsKey(mPlayingTrack) && mMusicEnabled) {
			try {
				mMusicMap.get(mPlayingTrack).resume();
			} catch (MusicReleasedException e) {
				e.printStackTrace();
			}
			Debug.d("AudioManager : resumed " + mActivity.getString(mPlayingTrack));
		}
	}

	/**
	 * Stop playing the music.
	 * <p>
	 * <b>Warning:</p> Do not call this method whether the music
	 * may be replaying again.
	 * </p>
	 */
//	public void stopMusic() {
//		if (mMusicMap.containsKey(mPlayingTrack)) {
//			if (mMusicMap.get(mPlayingTrack).isPlaying()) {
//				mMusicMap.get(mPlayingTrack).stop();
//			}
//		}
//	}

	/**
	 * Enable/Disable the music to play.
	 * @param enabled Enable the music to play whether set to <b>true</p>,
	 * disable it otherwise
	 */
	public void setMusicEnabled(boolean pEnabled) {
		Debug.d("AudioManager : "
				+ ((mMusicEnabled) ? "enabled" : "disabled")
				+ " playing music.");

		mMusicEnabled = pEnabled;
		if (mMusicEnabled == false) {
			pauseMusic();
		} else {
			resumeMusic();
		}
	}

	public void onDestroy() {
		for (Entry<Integer, Music> musicEntry : mMusicMap.entrySet()) {
			try {
				musicEntry.getValue().stop();
				musicEntry.getValue().release();
				Debug.d("AudioManager : released " + mActivity.getString(musicEntry.getKey()));
			} catch (MusicReleasedException e) {
				e.printStackTrace();
			}
		}
		Debug.i("AudioManager : destroyed AudioManager.");
		mMusicMap.clear();
		mPlayingTrack = 0;
	}
}

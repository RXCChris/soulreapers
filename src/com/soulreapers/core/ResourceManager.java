package com.soulreapers.core;

import java.util.HashMap;

import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.content.res.AssetManager;

import com.soulreapers.GameActivity;
import com.soulreapers.R;

public class ResourceManager implements IManager {
	/**
	 * Unique instance of the class.
	 */
	private static final ResourceManager INSTANCE = new ResourceManager();
	private static final String TEXTURE_PATH = "gfx/";

	private GameActivity mActivity;
	private static boolean mInitialized = false;

	private HashMap<Integer, MappedTexture> mMappedTextures = new HashMap<Integer, MappedTexture>();


//	private enum TextureType {
//		ILLUSTRATION("illust/"),
//		ICON("icon/"),
//		BACKGROUND("bg/"),
//		MISC("misc/");
//
//		private String mPathPrefix;
//
//		private TextureType(final String pPathPrefix) {
//			mPathPrefix = pPathPrefix;
//		}
//	}

	public AssetManager getAssetManager() {
		return mActivity.getAssets();
	}

	private ResourceManager() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath(TEXTURE_PATH);
	}

	public TextureRegion getTextureRegion(final int pTextureID) {
		if (!this.loadTexture(pTextureID)) {
			Debug.e("[ResourceManager]: Loading texture ID:"
					+ pTextureID + " >> failed.");
		}
		return mMappedTextures.get(pTextureID).getTextureRegion();
	}

	public boolean loadTexture(final int pTextureID) {
		if (!mMappedTextures.containsKey(pTextureID)) {
			if (!this.loadTextureFromData(pTextureID)) { return false; }
		}
		MappedTexture texture = mMappedTextures.get(pTextureID);
		if (!texture.isLoaded()) { texture.load(); }
		return true;
	}

	private boolean loadTextureFromData(final int pTextureID) {
		// TODO
		final String filename = "";
		final int width = 0;
		final int height = 0;
		MappedTexture texture = new MappedTexture(filename, width, height);
		mMappedTextures.put(pTextureID, texture);
		Debug.i("[ResourceManager]: Loading texture from Data [ID:"+pTextureID+"].");
		return true;
	}

	public float getTextureWidth(final int pTextureID) {
		return mMappedTextures.get(pTextureID).getWidth();
	}
	public float getTextureHeight(final int pTextureID) {
		return mMappedTextures.get(pTextureID).getHeight();
	}

	public boolean unloadTexture(final int pTextureID) {
		if (mMappedTextures.containsKey(pTextureID)) { return false; }

		MappedTexture texture = mMappedTextures.get(pTextureID);
		if (texture.isLoaded()) { texture.unload(); }
		return true;
	}

	public static ResourceManager getInstance() {
		if (!mInitialized) {
			Debug.w("[ResourceManager]: ResourceManager may not initialized!");
		}
		return INSTANCE;
	}

	public TextureManager getTextureManager() {
		return mActivity.getTextureManager();
	}

	/**
	 * Initialize ResourceManager.
	 * <p>
	 * This method must be called explicitly once.
	 * </p>
	 * @param pActivity Game activity
	 */
	public void initialize(GameActivity pActivity) {
		mActivity = pActivity;
		mInitialized = true;
		Debug.i("[ResourceManager]: Initialization >> completed.");
		//---------------------------------------------------------------------
		// TEST
		//---------------------------------------------------------------------
		mMappedTextures.put(1, new MappedTexture("background/bg_splash.png", 800, 480));
		mMappedTextures.put(R.string.bg_04, new MappedTexture("background/bg_menu_back.png", 800, 800));
		mMappedTextures.put(R.string.bg_03, new MappedTexture("background/bg_menu_3.png", 800, 480));
		mMappedTextures.put(R.string.bt_normal, new MappedTexture("button/bt_texture.png", 114, 114));
		mMappedTextures.put(R.string.pc_01, new MappedTexture("object/pc_dante.png", 320, 480));
		mMappedTextures.put(R.string.ic_01, new MappedTexture("object/ic_dante.png", 50, 50));
	}

	/**
	 * Get the string associated with the ID.
	 * @param resId
	 * @return
	 */
	public String getResourceString(int pResourceID) {
		return getInstance().mActivity.getString(pResourceID);
	}


	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return getInstance().mActivity.getVertexBufferObjectManager();
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.core.IManager#destroy()
	 */
	@Override
	public void destroy() {
		for (MappedTexture texture : mMappedTextures.values()) {
			texture.unload();
		}
		mMappedTextures.clear();
		Debug.i("[ResourceManager]: Unloading textures >> completed.");
	}

	private class MappedTexture {
		private BitmapTextureAtlas mTextureAtlas;
		private TextureRegion      mTextureRegion;
		private boolean mLoaded = false;

		public MappedTexture(final String pAssetPath, final int pWidth, final int pHeight, boolean pLoad) {
			mTextureAtlas = new BitmapTextureAtlas(
					mActivity.getTextureManager(),
					pWidth,
					pHeight,
					TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			mTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(mTextureAtlas,
							mActivity,
							pAssetPath, 0, 0);
			if (pLoad) { MappedTexture.this.load(); }
		}

		public MappedTexture(final String pAssetPath, final int pWidth, final int pHeight) {
			this(pAssetPath, pWidth, pHeight, false);
		}

		public boolean isLoaded() {
			return mLoaded;
		}

		public void load() {
			mTextureAtlas.load();
			mLoaded = true;
		}

		public void unload() {
			mTextureAtlas.unload();
			mTextureRegion = null;
			mLoaded = false;
		}

		public float getWidth() {
			return mTextureRegion.getWidth();
		}

		public float getHeight() {
			return mTextureRegion.getHeight();
		}

		public TextureRegion getTextureRegion() {
			return mTextureRegion;
		}
	};
}

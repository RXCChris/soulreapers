package com.soulreapers.core;

import java.util.HashMap;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import android.content.res.AssetManager;

import com.soulreapers.GameActivity;
import com.soulreapers.R;
import com.soulreapers.misc.GameConstants;

public class ResourceManager {
	/**
	 * Unique instance of the class.
	 */
	private static final ResourceManager INSTANCE = new ResourceManager();

	private GameActivity mActivity;
	private HashMap<Integer, Font> mFontMap = new HashMap<Integer, Font>();

	public static final int FONT_TITLE_ID = 48;
	public static final int FONT_TEXT_ID  = 24;
	public static final int FONT_SUB_TITLE_ID = 20;
	public static final int FONT_OPTION_ID = 36;
	public static final int FONT_STATS_ID = 14;


	private HashMap<Integer, Texture> mMappedTexture = new HashMap<Integer, Texture>();

	private class Texture {
		public BitmapTextureAtlas mTextureAtlas;
		public TextureRegion      mTextureRegion;
		public Texture(final String pAssetPath, final int pWidth, final int pHeight) {
			mTextureAtlas = new BitmapTextureAtlas(getTextureManager(),
					pWidth, pHeight, TextureOptions.BILINEAR);
			mTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(mTextureAtlas, getInstance().mActivity,
							pAssetPath, 0, 0);
			mTextureAtlas.load();
		}
	};

	public AssetManager getAssetManager() {
		return mActivity.getAssets();
	}

	public TextureRegion getTextureRegion(final int pResId) {
		return getInstance().mMappedTexture.get(pResId).mTextureRegion;
	}

	public TextureRegion loadTexture(final int pResId, final int pWidth, final int pHeight) {
		if (!getInstance().mMappedTexture.containsKey(pResId)) {
			getInstance().mMappedTexture.put(pResId,
					new Texture(getInstance().mActivity.getString(pResId), pWidth, pHeight));
		}
		return getInstance().mMappedTexture.get(pResId).mTextureRegion;
	}

	public void unloadTexture(final int pResId) {
		if (!getInstance().mMappedTexture.containsKey(pResId)) { return; }
		Texture texture = getInstance().mMappedTexture.remove(pResId);
		texture.mTextureAtlas.unload();
		texture.mTextureRegion = null;
	}

	private ResourceManager() {
		// nothing to do
	}

	public static ResourceManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Get the ITextureRegion.
	 * @param pBitmapTextureAtlas BitmapTextureAtlas to build ITextureRegion
	 * @param pResId
	 * @return ITextureRegion
	 */
	public ITextureRegion getTextureRegion(BitmapTextureAtlas pBitmapTextureAtlas,
			int pResId) {
		return BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(pBitmapTextureAtlas, getInstance().mActivity,
						getInstance().mActivity.getString(pResId), 0, 0);
	}

	/**
	 * Get the TextureManager.
	 * @return TextureManager
	 */
	public TextureManager getTextureManager() {
		return getInstance().mActivity.getTextureManager();
	}

	/**
	 * Initialize ResourceManager.
	 * <p>
	 * This method must be called explicitly once.
	 * </p>
	 * @param pActivity Game activity
	 */
	public void initialize(GameActivity pActivity) {
		getInstance().mActivity = pActivity;
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
	}

	/**
	 * Get the string associated with the ID.
	 * @param resId
	 * @return
	 */
	public String getResourceString(int resId) {
		return getInstance().mActivity.getString(resId);
	}

	public ITextureRegion getTextureRegion(int pResId, int pWidth, int pHeight) {
		BitmapTextureAtlas characterTextureAtlas = new BitmapTextureAtlas(
				ResourceManager.getInstance().getTextureManager(),
				pWidth,
				pHeight,
				TextureOptions.BILINEAR);
		ITextureRegion characterTextureRegion = getTextureRegion(characterTextureAtlas, pResId);
		characterTextureAtlas.load();
		return characterTextureRegion;
	}

	private void loadFont(int resId) {
		Debug.i(">> load font");
		FontFactory.setAssetBasePath("gfx/");
		Font font;
//		if (resId == R.string.ft_text) {
		if (resId == FONT_TEXT_ID) {
			final ITexture fontTexture = new BitmapTextureAtlas(getInstance().getTextureManager(),
					256, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			font = FontFactory.createStrokeFromAsset(getInstance().mActivity.getFontManager(),
					fontTexture, getInstance().mActivity.getAssets(), getInstance().mActivity.getString(R.string.ft_text),
					FONT_TEXT_ID, true, Color.WHITE_ARGB_PACKED_INT, 0.5f, Color.BLACK_ARGB_PACKED_INT);
		} else if (resId == FONT_TITLE_ID) {
			final ITexture fontTexture = new BitmapTextureAtlas(getInstance().getTextureManager(),
				256, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			font = FontFactory.createStrokeFromAsset(getInstance().mActivity.getFontManager(),
				fontTexture, getInstance().mActivity.getAssets(), getInstance().mActivity.getString(R.string.ft_03),
				FONT_TITLE_ID, true, Color.BLACK_ARGB_PACKED_INT, 1.0f, Color.WHITE_ARGB_PACKED_INT);
		} else if (resId == FONT_STATS_ID) {
			final ITexture fontTexture = new BitmapTextureAtlas(getInstance().getTextureManager(),
					256, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
				font = FontFactory.createStrokeFromAsset(getInstance().mActivity.getFontManager(),
					fontTexture, getInstance().mActivity.getAssets(), getInstance().mActivity.getString(R.string.ft_text),
					FONT_STATS_ID, true, Color.BLACK_ARGB_PACKED_INT, 1.0f, Color.WHITE_ARGB_PACKED_INT);
		} else if (resId == FONT_SUB_TITLE_ID) {
			final ITexture fontTexture = new BitmapTextureAtlas(getInstance().getTextureManager(),
					256, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
				font = FontFactory.createStrokeFromAsset(getInstance().mActivity.getFontManager(),
					fontTexture, getInstance().mActivity.getAssets(), getInstance().mActivity.getString(R.string.ft_command),
					resId, true, Color.WHITE_ARGB_PACKED_INT, 0.5f, Color.BLACK_ARGB_PACKED_INT);
		} else {
			final ITexture fontTexture = new BitmapTextureAtlas(getInstance().getTextureManager(),
					256, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
				font = FontFactory.createStrokeFromAsset(getInstance().mActivity.getFontManager(),
					fontTexture, getInstance().mActivity.getAssets(), getInstance().mActivity.getString(R.string.ft_command),
					FONT_OPTION_ID, true, Color.BLACK_ARGB_PACKED_INT, 1.0f, Color.WHITE_ARGB_PACKED_INT);
		}
		font.load();
		getInstance().mFontMap.put(resId, font);
	}

	public Font getFont(int resId) {
		if (!getInstance().mFontMap.containsKey(resId)) {
			loadFont(resId);
		}
		return getInstance().mFontMap.get(resId);
	}

	private void unloadFont() {
		Debug.i(">>ResourceManager unload font");
		for (Font object : getInstance().mFontMap.values()) {
			object.unload();
		}
		getInstance().mFontMap.clear();
	}

	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return getInstance().mActivity.getVertexBufferObjectManager();
	}

	public void onDestroy() {
		unloadFont();
		for (Texture texture : getInstance().mMappedTexture.values()) {
			texture.mTextureAtlas.unload();
			texture.mTextureRegion = null;
		}
		getInstance().mMappedTexture.clear();
	}
}

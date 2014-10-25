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
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import com.soulreapers.GameActivity;
import com.soulreapers.misc.GameConstants;

public class ResourceManager {
	/**
	 * Unique instance of the class.
	 */
	private static final ResourceManager INSTANCE = new ResourceManager();

	private GameActivity mActivity;
	private HashMap<Integer, Font> mFontMap = new HashMap<Integer, Font>();



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
						mActivity.getString(pResId), 0, 0);
	}

	/**
	 * Get the TextureManager.
	 * @return TextureManager
	 */
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
		getInstance().mActivity = pActivity;
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
	}

	/**
	 * Get the string associated with the ID.
	 * @param resId
	 * @return
	 */
	public String getResourceString(int resId) {
		return mActivity.getString(resId);
	}

	private void loadFont(int resId) {
		Debug.i(">> load font");
		FontFactory.setAssetBasePath("gfx/");
		final ITexture fontTexture = new BitmapTextureAtlas(getInstance().getTextureManager(),
				256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Font font = FontFactory.createStrokeFromAsset(getInstance().mActivity.getFontManager(),
				fontTexture, getInstance().mActivity.getAssets(), getInstance().mActivity.getString(resId),
				GameConstants.FONT_SIZE, true, Color.BLACK_ARGB_PACKED_INT, 2.0f, Color.WHITE_ARGB_PACKED_INT);
		font.load();
		mFontMap.put(resId, font);
	}

	public Font getFont(int resId) {
		if (!mFontMap.containsKey(resId)) {
			loadFont(resId);
		}
		return mFontMap.get(resId);
	}

	private void unloadFont() {
		Debug.i(">>ResourceManager unload font");
		for (Font object : mFontMap.values()) {
			object.unload();
		}
		mFontMap.clear();
	}

	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return mActivity.getVertexBufferObjectManager();
	}

	public void onDestroy() {
		unloadFont();
	}
}

package com.soulreapers.core;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.soulreapers.GameActivity;
import com.soulreapers.R;

public class ResourceManager {
	private static final ResourceManager INSTANCE = new ResourceManager();

	private GameActivity mActivity;
	private Engine mEngine;
	private Camera mCamera;
	private VertexBufferObjectManager mVbom;

	private Font mFont;
	private final int FONT_SIZE = 48;

	private ResourceManager() {
		// nothing to do
	}

	public static ResourceManager getInstance() {
		return INSTANCE;
	}

	public void init(GameActivity activity) {
		mActivity = activity;
		mEngine = activity.getEngine();
		mCamera = mEngine.getCamera();
		mVbom = mEngine.getVertexBufferObjectManager();
		loadFont();
	}

	public void loadFont() {
		FontFactory.setAssetBasePath("gfx/");
		final ITexture fontTexture = new BitmapTextureAtlas(mActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mFont = FontFactory.createStrokeFromAsset(mActivity.getFontManager(), fontTexture, mActivity.getAssets(), mActivity.getString(R.string.roses_kingdom), FONT_SIZE, true, Color.WHITE_ARGB_PACKED_INT, 2.0f, Color.BLACK_ARGB_PACKED_INT);
		mFont.load();
	}

	public Font getFont() {
		return mFont;
	}
}

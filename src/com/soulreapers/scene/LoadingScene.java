package com.soulreapers.scene;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.util.color.Color;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;

public class LoadingScene extends BaseScene {

//	private Font mFont;
//	private final int FONT_SIZE = 48;
	private static final int FONT_ID = ResourceManager.FONT_TEXT_ID;

	@Override
	public void onLoadResources() {
//		FontFactory.setAssetBasePath("gfx/");
//		final ITexture fontTexture = new BitmapTextureAtlas(mActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		//mFont = FontFactory.createStrokeFromAsset(mActivity.getFontManager(), fontTexture, mActivity.getAssets(), mActivity.getString(R.string.roses_kingdom), FONT_SIZE, true, Color.WHITE, 2f, Color.BLACK);
//		mFont = FontFactory.createStrokeFromAsset(mActivity.getFontManager(), fontTexture, mActivity.getAssets(), mActivity.getString(R.string.ft_02), FONT_SIZE, true, Color.WHITE_ARGB_PACKED_INT, 2.0f, Color.BLACK_ARGB_PACKED_INT);
//		mFont.load();
	}

	@Override
	public void onCreate() {
//		setBackground(new Background(Color.WHITE));
//		attachChild(new Text(400, 240, mFont, mActivity.getString(R.string.mg_01), mVbom));
		attachChild(new Text(400, 240, ResourceManager.getInstance().getFont(FONT_ID), ResourceManager.getInstance().getResourceString(R.string.mg_01), ResourceManager.getInstance().getVertexBufferObjectManager()));
	}

	@Override
	public void onDestroyResources() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "LoadingScene";
	}
}

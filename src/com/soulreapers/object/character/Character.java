package com.soulreapers.object.character;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.soulreapers.GameActivity;

public abstract class Character {
	private String mName;
	protected ITextureRegion mCharacterRegion;
	protected BitmapTextureAtlas mCharacterTextureAtlas;
	protected Sprite mCharacterSprite;

	// Constructor
	public Character(String name, GameActivity activity, int resId, int width, int height) {
		mName = name;
		create(activity, resId, width, height);
	}

	private void create(GameActivity activity, int resId, int width, int height) {
		mCharacterTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), width, height, TextureOptions.BILINEAR);
		mCharacterRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mCharacterTextureAtlas, activity, activity.getString(resId), 0, 0);
		mCharacterTextureAtlas.load();
	}

	public void destroy() {
		mCharacterTextureAtlas.unload();
		mCharacterRegion = null;
	}

	protected abstract void createSprite(VertexBufferObjectManager vbom);

	/**
	 * @return the mName
	 */
	public String getName() {
		return mName;
	}

	public Sprite getSprite() {
		return mCharacterSprite;
	}

	public void setSpritePosition(float x, float y) {
		mCharacterSprite.setPosition(x, y);
	}

	public void setVisible(boolean visible) {
		mCharacterSprite.setVisible(visible);
	}
}

package com.soulreapers.object.character;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.soulreapers.GameActivity;

public class MainPlayer extends PlayableCharacter {

	public MainPlayer(String name, GameActivity activity, int resId, int width,
			int height, int hpMax) {
		super(name, activity, resId, width, height, hpMax);
		// TODO Auto-generated constructor stub
		createSprite(activity.getVertexBufferObjectManager());
	}

	@Override
	public void onDie() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createSprite(VertexBufferObjectManager vbom) {
		mCharacterSprite = new Sprite(0, 0, mCharacterRegion, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
	}

}

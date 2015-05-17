/**
 * Project Soul Reapers
 * Copyright (c) 2014 Chengwu Huang (dxcloud) <chengwhuang@gmail.com>
 *
 * This file is part of 'Soul Reapers'.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.soulreapers.scene;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.ConditionState;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.misc.GameProgressionRecord;

/**
 *
 * @since 2014.10.29
 * @version 0.1 (alpha)
 * @author dxcloud
 *
 */
public class PrologueScene extends BaseScene implements IOnSceneTouchListener, IScrollDetectorListener {
	private static final int CARD_PADDING = 20;
	private static final int MAX_NUM_SELECTABLE_CARD = 3;
	private static final int MAX_NUM_CARD = 22;
	private static final int FONT_ID = ResourceManager.FONT_TEXT_ID;
	private SurfaceScrollDetector mSurfaceScrollDetector;
	private Sprite mHintBackgroundSprite;
	private Text mHintText;

	private Rectangle mScrollBar;
	private ArrayList<Sprite> mCardSpriteList = new ArrayList<Sprite>();
	private ArrayList<CardDescription> mCardDescriptionList = new ArrayList<CardDescription>();

	private int mXmax = 0;
	private int mXmin = 0;

	private int mNumSelectedCard = 0;

	private Text mContinueText;
	private int mNumCardToShow;
	private int mNumClick = 0;

	private class CardDescription {
		private int mCardId;
		private int mDescriptionId;
		private ITextureRegion mCardTextureRegion;
		public CardDescription(int pCardId, int pDescriptionId) {
			mCardId = pCardId;
			mDescriptionId = pDescriptionId;

			BitmapTextureAtlas cardBitmapTextureAtlas = new BitmapTextureAtlas(
					ResourceManager.getInstance().getTextureManager(),
					100, 200, TextureOptions.BILINEAR);
			mCardTextureRegion = ResourceManager.getInstance()
					.getTextureRegion(cardBitmapTextureAtlas, mCardId);
			cardBitmapTextureAtlas.load();
		}
		public String getCardDescription() {
			return ResourceManager.getInstance().getResourceString(mDescriptionId);
		}
		public ITextureRegion getCardTextureRegion() {
			return mCardTextureRegion;
		}
	}



	/* (non-Javadoc)
	 * @see org.andengine.entity.scene.IOnSceneTouchListener#onSceneTouchEvent(org.andengine.entity.scene.Scene, org.andengine.input.touch.TouchEvent)
	 */
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		this.mSurfaceScrollDetector.onTouchEvent(pSceneTouchEvent);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onLoadResources()
	 */
	@Override
	public void onLoadResources() {
		// TODO modify
		mCardDescriptionList.add(new CardDescription(R.string.ic_t02, R.string.mg_t00)); // 0
		mCardDescriptionList.add(new CardDescription(R.string.ic_t03, R.string.mg_t01)); // 1

		// To keep
		mCardDescriptionList.add(new CardDescription(R.string.ic_t02, R.string.mg_t02)); // 2
		mCardDescriptionList.add(new CardDescription(R.string.ic_t03, R.string.mg_t03)); // 3
		mCardDescriptionList.add(new CardDescription(R.string.ic_t04, R.string.mg_t04)); // 4
		mCardDescriptionList.add(new CardDescription(R.string.ic_t05, R.string.mg_t05)); // 5
		mCardDescriptionList.add(new CardDescription(R.string.ic_t06, R.string.mg_t06)); // 6
		mCardDescriptionList.add(new CardDescription(R.string.ic_t07, R.string.mg_t07)); // 7

		// TODO modify
		mCardDescriptionList.add(new CardDescription(R.string.ic_t02, R.string.mg_t08)); // 8
		mCardDescriptionList.add(new CardDescription(R.string.ic_t03, R.string.mg_t09)); // 9
		mCardDescriptionList.add(new CardDescription(R.string.ic_t04, R.string.mg_t10)); // 10
		mCardDescriptionList.add(new CardDescription(R.string.ic_t05, R.string.mg_t11)); // 11
		mCardDescriptionList.add(new CardDescription(R.string.ic_t06, R.string.mg_t12)); // 12
		mCardDescriptionList.add(new CardDescription(R.string.ic_t07, R.string.mg_t13)); // 13
		mCardDescriptionList.add(new CardDescription(R.string.ic_t22, R.string.mg_t14)); // 14
		mCardDescriptionList.add(new CardDescription(R.string.ic_t02, R.string.mg_t15)); // 15
		mCardDescriptionList.add(new CardDescription(R.string.ic_t03, R.string.mg_t16)); // 16
		mCardDescriptionList.add(new CardDescription(R.string.ic_t04, R.string.mg_t17)); // 17
		mCardDescriptionList.add(new CardDescription(R.string.ic_t05, R.string.mg_t18)); // 18
		mCardDescriptionList.add(new CardDescription(R.string.ic_t06, R.string.mg_t19)); // 19
		mCardDescriptionList.add(new CardDescription(R.string.ic_t07, R.string.mg_t20)); // 20
		mCardDescriptionList.add(new CardDescription(R.string.ic_t22, R.string.mg_t21)); // 21

		// To keep
		mCardDescriptionList.add(new CardDescription(R.string.ic_t22, R.string.mg_t22)); // 22 (empty)
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onCreate()
	 */
	@Override
	public void onCreate() {
		AudioManager.getInstance().playMusic(R.string.ms_07, true, true);
		createHint();
		this.setTouchAreaBindingOnActionDownEnabled(true);
	}

	private void createHint() {
		// Continue button
		BitmapTextureAtlas hintBitmapTextureAtlas = new BitmapTextureAtlas(
				ResourceManager.getInstance().getTextureManager(),
				800, 480, TextureOptions.BILINEAR);
		ITextureRegion hintTextureRegion = ResourceManager.getInstance()
				.getTextureRegion(hintBitmapTextureAtlas, R.string.bg_05);
		hintBitmapTextureAtlas.load();
		mHintBackgroundSprite = new Sprite(0, 0,
				hintTextureRegion,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		this.attachChild(mHintBackgroundSprite);

		mHintText = new Text(200, 200,
				ResourceManager.getInstance().getFont(FONT_ID),
				ResourceManager.getInstance().getResourceString(R.string.mg_04),
				ResourceManager.getInstance().getResourceString(R.string.mg_04).length(),
				new TextOptions(HorizontalAlign.CENTER),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		this.attachChild(mHintText);

		mContinueText = new Text(SceneManager.getInstance().getCamera().getCenterX() + 100, 420,
				ResourceManager.getInstance().getFont(FONT_ID),
				ResourceManager.getInstance().getResourceString(R.string.tb_12),
				new TextOptions(HorizontalAlign.LEFT),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if (mNumSelectedCard == 0) {
						destroyHint();
						createCard();
						return true;
					}
					if (mNumClick == 0) {
						mHintText.setVisible(false);
						mNumCardToShow = randomInt(-1);
						showResolvedCard(0, mNumCardToShow);
					} else if (mNumClick == 1) {
						showResolvedCard(1, randomInt(mNumCardToShow));
					} else if (mNumClick == 2) {
						showResolvedCard(2, 13);
					} else {
						GameProgressionRecord.getInstance().setConditionState(0, ConditionState.DONE);
						SceneManager.getInstance().showScene(GameScene.class);
					}
					++mNumClick;
				}
				return true;
			}
		};

		this.attachChild(mContinueText);
		this.registerTouchArea(mContinueText);
		this.setTouchAreaBindingOnActionDownEnabled(true);
	}

	private void destroyHint() {
		mHintBackgroundSprite.detachSelf();
		mHintBackgroundSprite.dispose();
		mHintText.setText(ResourceManager.getInstance().getResourceString(R.string.mg_05));
		mHintText.setVisible(false);
		this.unregisterTouchArea(mContinueText);
		mContinueText.setVisible(false);
	}

	private void createCard() {
		this.setBackground(new Background(Color.BLACK));
		this.mSurfaceScrollDetector = new SurfaceScrollDetector(this);
		this.setOnSceneTouchListener(this);
		this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);

		mXmax = CARD_PADDING;
		for (int i = 0; i < MAX_NUM_CARD; ++i) {
			Sprite sprite = new Sprite(mXmax, CARD_PADDING,
					mCardDescriptionList.get(mCardDescriptionList.size() - 1).getCardTextureRegion(),
					ResourceManager.getInstance().getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
						final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
					if ((pSceneTouchEvent.isActionDown())
							&& (pTouchAreaLocalX < getWidth() - CARD_PADDING)) {
						if (mNumSelectedCard <= MAX_NUM_SELECTABLE_CARD) {
							if (getY() <= CARD_PADDING) {
								if (++mNumSelectedCard > MAX_NUM_SELECTABLE_CARD) {
									mNumSelectedCard = MAX_NUM_SELECTABLE_CARD;
								} else {
									setPosition(getX(), getY() + CARD_PADDING);
								}
							} else {
								if (--mNumSelectedCard <= 0) {
									mNumSelectedCard = 0;
								}
								setPosition(getX(), CARD_PADDING);
							}
						}
						confirmCard();
					}
					return false;
				}
			};
			mCardSpriteList.add(sprite);
			this.attachChild(sprite);
			this.registerTouchArea(sprite);
			mXmax += sprite.getWidth() - CARD_PADDING;
		}
		mXmax += CARD_PADDING * 2;
		float scrollBarSize = GameConstants.CAMERA_WIDTH * GameConstants.CAMERA_WIDTH / mXmax;
		mScrollBar = new Rectangle(0,
				GameConstants.CAMERA_HEIGHT - CARD_PADDING,
				scrollBarSize, CARD_PADDING,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mScrollBar.setColor(Color.BLUE);
		this.attachChild(mScrollBar);
	}

	private void resetCard() {
		for (int i = 0; i < mCardSpriteList.size(); ++i){
			Sprite sprite = mCardSpriteList.get(i);
			sprite.setPosition(sprite.getX(), CARD_PADDING);
		}
		mNumSelectedCard = 0;
	}

	private void resolveCard() {
		destroyCard();
		SceneManager.getInstance().getCamera().setCenter(GameConstants.CAMERA_WIDTH / 2,
				GameConstants.CAMERA_HEIGHT / 2);
		mContinueText.setVisible(true);
		mHintText.setVisible(true);
		this.registerTouchArea(mContinueText);
	}

	private int randomInt(int previous) {
		int r = (int) (Math.random() * MAX_NUM_CARD); // from 0 to MAX_NUM_CARD - 1 (i.e. 21)
		if (r == previous) {
			r = (r + 1) % MAX_NUM_CARD;
		}
		if (r == 13) {
			++r;
		}
		return r;
	}

	private void showResolvedCard(int numCard, int numCardToShow) {
		float x = CARD_PADDING;
		float y = numCard * (100 + CARD_PADDING) + CARD_PADDING;

		Sprite sprite = new Sprite(x, y,
				mCardDescriptionList.get(numCardToShow).getCardTextureRegion(),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		this.attachChild(sprite);

		Text text = new Text(x + sprite.getWidth() + CARD_PADDING, y,
				ResourceManager.getInstance().getFont(FONT_ID),
				mCardDescriptionList.get(numCardToShow).getCardDescription(),
				new TextOptions(HorizontalAlign.LEFT),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		this.attachChild(text);
	}

	private void confirmCard() {
		if ((mNumSelectedCard >= MAX_NUM_SELECTABLE_CARD)) {
			mSurfaceScrollDetector.setEnabled(false); // Disables scrolling while confirm dialog is active
			this.setChildScene(new ConfirmDialog() {
				@Override
				protected void onPositive() {
					resolveCard();
					back();
				}
				@Override
				protected void onNegative() {
					resetCard();
					mSurfaceScrollDetector.setEnabled(true);
					back();
				}
			}, false, true, true);
		}
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onDestroyResources()
	 */
	@Override
	public void onDestroyResources() {
		if (this.hasChildScene()) {
			this.clearChildScene();
		}
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onDestroy()
	 */
	@Override
	public void onDestroy() {
		AudioManager.getInstance().pauseMusic();
		this.detachSelf();
		this.dispose();
	}

	private void destroyCard() {
		for (int i = 0; i < mCardSpriteList.size(); ++i) {
			Sprite sprite = mCardSpriteList.get(i);
			this.unregisterTouchArea(sprite);
			sprite.detachSelf();
			sprite.dispose();
		}
		mScrollBar.detachSelf();
		mScrollBar.dispose();
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onPause()
	 */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.soulreapers.scene.BaseScene#onResume()
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener#onScrollStarted(org.andengine.input.touch.detector.ScrollDetector, int, float, float)
	 */
	@Override
	public void onScrollStarted(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
		// Nothing to do
	}

	/* (non-Javadoc)
	 * @see org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener#onScroll(org.andengine.input.touch.detector.ScrollDetector, int, float, float)
	 */
	@Override
	public void onScroll(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
		Camera camera = SceneManager.getInstance().getCamera();
		camera.offsetCenter(-pDistanceX, 0); // Moves camera

		// Controls side effect
		if (camera.getCenterX() < (mXmin + GameConstants.CAMERA_WIDTH / 2)) {
			camera.setCenter(mXmin + GameConstants.CAMERA_WIDTH / 2,
					camera.getCenterY());
		}
		if (camera.getCenterX() > (mXmax - GameConstants.CAMERA_WIDTH / 2)) {
			camera.setCenter(mXmax - GameConstants.CAMERA_WIDTH / 2,
					camera.getCenterY());
		}
		float x = camera.getCenterX() - GameConstants.CAMERA_WIDTH / 2;
		x += x * GameConstants.CAMERA_WIDTH / mXmax;
		mScrollBar.setPosition(x, mScrollBar.getY());
	}

	/* (non-Javadoc)
	 * @see org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener#onScrollFinished(org.andengine.input.touch.detector.ScrollDetector, int, float, float)
	 */
	@Override
	public void onScrollFinished(ScrollDetector pScollDetector, int pPointerID,
			float pDistanceX, float pDistanceY) {
		// Nothing to do
	}

}

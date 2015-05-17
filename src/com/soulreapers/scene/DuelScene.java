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

import java.util.LinkedList;
import java.util.Queue;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.particle.SpriteParticleSystem;
import org.andengine.entity.particle.emitter.CircleParticleEmitter;
import org.andengine.entity.particle.emitter.PointParticleEmitter;
import org.andengine.entity.particle.initializer.AlphaParticleInitializer;
import org.andengine.entity.particle.initializer.BlendFunctionParticleInitializer;
import org.andengine.entity.particle.initializer.ColorParticleInitializer;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ColorParticleModifier;
import org.andengine.entity.particle.modifier.ExpireParticleInitializer;
import org.andengine.entity.particle.modifier.ScaleParticleModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.SurfaceGestureDetectorAdapter;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;
import org.andengine.util.modifier.IModifier;

import android.opengl.GLES20;

import com.soulreapers.R;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.misc.Attributes.AttributeType;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.misc.MoveYFadeOutText;
import com.soulreapers.misc.OverkillListener;
import com.soulreapers.object.Gauge;
import com.soulreapers.object.character.Remnant;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.skill.SkillAttack;
import com.soulreapers.skill.SkillGesture;
import com.soulreapers.util.SRButton;

/**
 * @author dxcloud
 *
 */
public class DuelScene extends Scene implements IOnSceneTouchListener {
	private static final float RATE_MIN = 30;
	private static final float RATE_MAX = 60;
	private static final int PARTICLES_MAX = 200;
	private static final int FONT_ID = ResourceManager.FONT_TEXT_ID;
	private static final int TEXTURE_PARTICLE_ID     = R.string.bo_particle_2;
	private static final int TEXTURE_PARTICLE_WIDTH  = 32;
	private static final int TEXTURE_PARTICLE_HEIGHT = 32;

	private static final int GAUGE_X = 100;
	private static final int GAUGE_Y = 20;
	private static final int GAUGE_WIDTH = 600;
	private static final int GAUGE_HEIGHT = 10;
	private static final int GAUGE_PADDING_Y = 20;

	private static final String STRING_END_DUEL     = "Terminer";
	private static final String STRING_MSG_END_DUEL = "Terminer le duel ?";
//	private static final String STRING_SOUL = "SOUL";

	private SurfaceGestureDetectorAdapter mSgda;
	private BattleScene mBattleScene;
	private Reaper mPlayer;
	private Remnant mEnemy;
	private SRButton mEndDuelText;

	private CircleParticleEmitter mParticleEmitter;
	private SpriteParticleSystem mParticleSystem;

	private OverkillMode mOverkillMode = new OverkillMode();
	private Gauge mSoulGauge = new Gauge(GAUGE_X, GAUGE_Y, GAUGE_WIDTH, GAUGE_HEIGHT, Color.RED);
	private Text mTextSoul;// = new Text(GAUGE_X, GAUGE_Y - FONT_ID / 2,
	private static final String STRING_JUSTICE_FORMAT = "Justice : % 9d";
	private static final String STRING_COMBO_FORMAT = "Combo : % 9d";

	private Text mTextJustice = new Text(20, 400, ResourceManager.getInstance().getFont(FONT_ID), String.format(STRING_JUSTICE_FORMAT, 0), ResourceManager.getInstance().getVertexBufferObjectManager());
	private Text mTextCombo = new Text(20, 360, ResourceManager.getInstance().getFont(FONT_ID), String.format(STRING_COMBO_FORMAT, 0), ResourceManager.getInstance().getVertexBufferObjectManager());
//			ResourceManager.getInstance().getFont(FONT_ID),
//			STRING_SOUL,
//			ResourceManager.getInstance().getVertexBufferObjectManager());


	private int mTotalRegularDamage = 0;
	private int mNumCombo = 0;

	public DuelScene(Reaper pPlayer, Remnant pEnemy, BattleScene pBattleScene) {
		super();
		mBattleScene = pBattleScene;
		mPlayer = pPlayer;
		mEnemy = pEnemy;

//		mPlayer.detachSelf();
		mEnemy.detachSelf();
		mSoulGauge.update(mEnemy.getAttributes().getCurrent(AttributeType.SOUL),
				mEnemy.getAttributes().getTotal(AttributeType.SOUL));
		mTextSoul = new Text(GAUGE_X, GAUGE_Y - FONT_ID * 0.75F,
				ResourceManager.getInstance().getFont(FONT_ID),
				mEnemy.getName(),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mTextJustice.setText(String.format(STRING_JUSTICE_FORMAT, mPlayer.getAttributes().getCurrent(AttributeType.JUSTICE)));
		create();

		this.attachChild(mOverkillMode);

		this.attachChild(mSoulGauge);
		this.attachChild(mTextSoul);

		this.attachChild(mTextJustice);
		this.attachChild(mTextCombo);
	}

	private static final String STRING_MSG_TRIGGER_OVERKILL = "Enclencher le mode OVERKILL ?";

	public void startOverkillMode() {
		DuelScene.this.setChildScene(new ConfirmDialog(STRING_MSG_TRIGGER_OVERKILL) {
			@Override
			public void onPositive() {
				super.onPositive();
				mOverkillMode.onOverkillStarted();
			}
			@Override
			public void onNegative() {
				super.onNegative();
				displayResult();
			}
		}, false, true, true);


//		mOverkillMode.onOverkillStarted();
	}

	@Override
	public void back() {
		Debug.i(">>call duelscene back");
		super.back();
		mEnemy.setPosition(0, 0);
//		mPlayer.detachSelf();
		mEnemy.detachSelf();
//		mPlayer.setVisible(false);
		mEnemy.setVisible(false);

		DuelScene.this.detachChildren();
		mBattleScene.getChildByIndex(0).attachChild(mEnemy);
//		mBattleScene.getChildByIndex(0).attachChild(mPlayer);
		mBattleScene.endTurn();
	}


	public void create() {
		Debug.d("start creating ui");

		mEndDuelText = new SRButton(GameConstants.UI.MENU_OPTION_OFFSET_X,
				GameConstants.UI.MENU_OPTION_OFFSET_Y * 8,
				STRING_END_DUEL) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				DuelScene.this.setChildScene(new ConfirmDialog(STRING_MSG_END_DUEL) {
					@Override
					public void onPositive() {
						super.onPositive();
						displayResult();
					}
					@Override
					public void onNegative() {
						super.onNegative();
					}
				}, false, true, true);
			}
		};

//		mPlayer.setVisible(true);
		mEnemy.setVisible(true);
		mEnemy.setPosition(GameConstants.X_BATTLE_FIELD_PADDING, 0);

//		this.attachChild(mPlayer);
		this.attachChild(mEnemy);
		this.attachChild(mEndDuelText);

		createParticleSystem();

		this.registerTouchArea(mEndDuelText);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.setTouchAreaBindingOnActionMoveEnabled(true);
		this.setOnSceneTouchListener(DuelScene.this);
		this.setOnSceneTouchListenerBindingOnActionDownEnabled(true);
	}

	/* (non-Javadoc)
	 * @see org.andengine.entity.scene.IOnSceneTouchListener#onSceneTouchEvent(org.andengine.entity.scene.Scene, org.andengine.input.touch.TouchEvent)
	 */
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if (pSceneTouchEvent.isActionDown() || pSceneTouchEvent.isActionMove()) {
			mParticleSystem.setParticlesSpawnEnabled(true);
			final float x = pSceneTouchEvent.getX();
			final float y = pSceneTouchEvent.getY();
//			for (int i = 0; i < 50; ++i) {
//				mParticleEmitter.setCenter(x + Util.random(-50, 50), y + Util.random(-50, 50));
//			}
			mParticleEmitter.setCenter(x, y);
		} else if (pSceneTouchEvent.isActionUp()) {
			mParticleSystem.setParticlesSpawnEnabled(false);
		}
		return DuelScene.this.mSgda.onTouchEvent(pSceneTouchEvent);
	}

	private void createParticleSystem() {
		mParticleEmitter = new CircleParticleEmitter(0.0F, 0.0F, 50.0F);
		mParticleSystem = new SpriteParticleSystem(mParticleEmitter,
				RATE_MIN, RATE_MAX, PARTICLES_MAX,
				ResourceManager.getInstance().loadTexture(TEXTURE_PARTICLE_ID, TEXTURE_PARTICLE_WIDTH, TEXTURE_PARTICLE_HEIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager());

		mParticleSystem.addParticleInitializer(new AlphaParticleInitializer<Sprite>(0));
		mParticleSystem.addParticleInitializer(new BlendFunctionParticleInitializer<Sprite>(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE));
		mParticleSystem.addParticleInitializer(new ExpireParticleInitializer<Sprite>(1));
		mParticleSystem.addParticleInitializer(new VelocityParticleInitializer<Sprite>(-40, 40, -40, 40));

		mParticleSystem.addParticleModifier(new ScaleParticleModifier<Sprite>(0.0f, 1.0f, 1.0f, 5.0f));
		mParticleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(0, 0.5f, 0, 1));
		mParticleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(0.5f, 1.0f, 1, 0));
		DuelScene.this.attachChild(mParticleSystem);
		mParticleSystem.setParticlesSpawnEnabled(false);

		SceneManager.getInstance().getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				DuelScene.this.mSgda = new SurfaceGestureDetectorAdapter(
						SceneManager.getInstance().getActivity()) {
					@Override
					protected boolean onSingleTap() {
						executeAction(SkillGesture.SINGLE_TAP);
						return true;
					}
					@Override
					protected boolean onDoubleTap() {
						executeAction(SkillGesture.SINGLE_TAP);
						return true;
					}
					@Override
					protected boolean onSwipeUp() {
						executeAction(SkillGesture.SWIPE_UP);
						return true;
					}
					@Override
					protected boolean onSwipeDown() {
						executeAction(SkillGesture.SWIPE_DOWN);
						return true;
					}
					@Override
					protected boolean onSwipeLeft() {
						executeAction(SkillGesture.SWIPE_LEFT);
						return true;
					}
					@Override
					protected boolean onSwipeRight() {
						executeAction(SkillGesture.SWIPE_RIGHT);
						return true;
					}
				}; // SurfaceGestureDetectorAdapter
			} // run
		}); // runOnUiThread
	}

	private void executeAction(SkillGesture pGesture) {
		Debug.d("executing attack : " + pGesture.toString());
		SkillAttack skill = mPlayer.getOffensiveSkill(pGesture);
		if (skill != null) {
			if (mOverkillMode.isOverkillModeEnabled()) {
				final int damage = skill.getOverkillDamage();
				mOverkillMode.addDamage(damage);
				++mNumCombo;
				this.attachChild(new MoveYFadeOutText(400, 200, 100,
						skill.getName() + "\n-" + damage, 2));
			} else if (mPlayer.executeOffensiveSkill(pGesture, mPlayer, mEnemy)) {
				final int damage = skill.getDamage();
				mTotalRegularDamage += damage;
				++mNumCombo;
				mSoulGauge.update(mEnemy.getAttributes().getCurrent(AttributeType.SOUL),
						mEnemy.getAttributes().getTotal(AttributeType.SOUL));
				mTextJustice.setText(String.format(STRING_JUSTICE_FORMAT, mPlayer.getAttributes().getCurrent(AttributeType.JUSTICE)));
				mTextCombo.setText(String.format(STRING_COMBO_FORMAT, mNumCombo));
				this.attachChild(new MoveYFadeOutText(400, 200, 100,
						skill.getName() + "\n-" + damage, 2));
				if (mEnemy.isDead()) {
					startOverkillMode();
				}
			}
		}
	}

	private DelayModifier mDelayModifier = new DelayModifier(1, new IEntityModifierListener() {
		@Override
		public void onModifierStarted(IModifier<IEntity> pModifier,
				IEntity pItem) {
			DuelScene.this.unregisterTouchArea(mEndDuelText);
			mSgda.setEnabled(false);
			DuelScene.this.setTouchAreaBindingOnActionDownEnabled(false);
			DuelScene.this.setTouchAreaBindingOnActionMoveEnabled(false);
			DuelScene.this.setOnSceneTouchListenerBindingOnActionDownEnabled(false);
		}

		@Override
		public void onModifierFinished(IModifier<IEntity> pModifier,
				IEntity pItem) {
			final int base = 100;
			final int remnantSoul = mEnemy.getAttributes().getTotal(AttributeType.SOUL);
			final int bonus = (mEnemy.isDead() ? base : 0);
			final int total = ResultScene.computeTotalExpGained(base, remnantSoul, mTotalRegularDamage, mOverkillMode.mTotalDamage, mNumCombo, bonus);
			mPlayer.getAttributes().increaseCurrent(AttributeType.EXPERIENCE, total);

			DuelScene.this.setChildScene(new ResultScene(base,
					ResultScene.computeComboMultiplicator(mNumCombo),
					ResultScene.computeDamageMultiplicator(mTotalRegularDamage, remnantSoul),
					ResultScene.computeOverkillMultiplicator(mOverkillMode.mTotalDamage, remnantSoul),
					bonus) {
				@Override
				protected void endResult() {
					super.endResult();
					this.back();
					DuelScene.this.back();
				}
			}, false, true, true);

		}
	});

	private void displayResult() {
		this.registerEntityModifier(mDelayModifier);
	}

	//---
	// Overkill Mode
	//---
	private class OverkillMode extends Entity implements OverkillListener {
		private static final String STRING_OVERKILL = "OVERKILL";
		private static final String STRING_TOTAL_DAMAGE = "Dégât Total :\n";
		private static final int MAX_DAMAGE_LENGTH = 30;
		private static final float OVERKILL_BASE_TIME  = 0.5F;

		private boolean mEnabled = false;
		private int mTotalDamage = 0;
		private float mTotalTime = 0.0F;
		private float mElapsedTime = 0.0F;

		private Gauge mTimerGauge = new Gauge(GAUGE_X, GAUGE_Y + GAUGE_PADDING_Y, GAUGE_WIDTH, GAUGE_HEIGHT, Color.BLUE);

		private Text mTextOverkill = new Text(GAUGE_X, GAUGE_Y + GAUGE_PADDING_Y - FONT_ID / 2,
				ResourceManager.getInstance().getFont(FONT_ID),
				STRING_OVERKILL,
				ResourceManager.getInstance().getVertexBufferObjectManager());

		private Text mTextDamage = new Text(600, 400,
				ResourceManager.getInstance().getFont(FONT_ID),
				STRING_TOTAL_DAMAGE + 0,
				MAX_DAMAGE_LENGTH,
				ResourceManager.getInstance().getVertexBufferObjectManager());

		public boolean isOverkillModeEnabled() {
			return mEnabled;
		}

		public void addDamage(int pDamage) {
			mTotalDamage += pDamage;
			mSoulGauge.update(pDamage,
					mEnemy.getAttributes().getTotal(AttributeType.SOUL));		
			mTextDamage.setText(STRING_TOTAL_DAMAGE + mTotalDamage);
		}

		private IUpdateHandler mOverkillUpdateHandler = new IUpdateHandler() {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				mElapsedTime += pSecondsElapsed;
				if (mElapsedTime < mTotalTime) {
					mTimerGauge.update(mTotalTime - mElapsedTime, mTotalTime);
				} else {
					mTimerGauge.update(0, mTotalTime);
					OverkillMode.this.onOverkillFinished();
				}
			}

			@Override
			public void reset() {
				mElapsedTime = 0.0F;
				mTimerGauge.update(mTotalTime, mTotalTime);
			}
		};


		public OverkillMode() {
			super();
			OverkillMode.this.setVisible(false);
		}

		@Override
		public void onAttached() {
			Debug.d("OK - attached");
			super.onAttached();
			OverkillMode.this.attachChild(mTimerGauge);
			OverkillMode.this.attachChild(mTextOverkill);
			OverkillMode.this.attachChild(mTextDamage);
		}

		@Override
		public void onDetached() {
			super.onDetached();
			OverkillMode.this.detachChildren();
		}

		/**
		 * @see org.andengine.entity.Entity#dispose()
		 */
		@Override
		public void dispose() {
			mTimerGauge.dispose();
			mTextOverkill.dispose();
			mTextDamage.dispose();
			super.dispose();
		}

//		public void startOverkillMode() {
//			onOverkillStarted();
//		}

		/* (non-Javadoc)
		 * @see com.soulreapers.misc.OverkillListener#onOverkillStarted()
		 */
		@Override
		public void onOverkillStarted() {
			mEnabled = true;
			mSoulGauge.setFilledColor(Color.YELLOW);

			Debug.d("justice=" + mPlayer.getAttributes().getCurrent(AttributeType.JUSTICE));
			mOverkillMode.mTotalTime = (float) mPlayer.getAttributes().getCurrent(AttributeType.JUSTICE) * OverkillMode.OVERKILL_BASE_TIME;
			Debug.d("total time=" + mTotalTime);
			mPlayer.getAttributes().setCurrent(AttributeType.JUSTICE, 0);

			OverkillMode.this.setVisible(true);
			OverkillMode.this.registerUpdateHandler(mOverkillUpdateHandler);
		}

		/* (non-Javadoc)
		 * @see com.soulreapers.misc.OverkillListener#onOverkillFinished()
		 */
		@Override
		public void onOverkillFinished() {
			OverkillMode.this.unregisterUpdateHandler(mOverkillUpdateHandler);
			mSoulGauge.update(0, 1);
			Debug.d("DuelScene : Ended Overkill Mode.");
			displayResult();
		}
	}

}

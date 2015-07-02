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

import java.util.HashMap;
import java.util.Map.Entry;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.SceneManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.misc.GameConstants;
//import com.soulreapers.object.character.CharacterCollection;
import com.soulreapers.object.character.reaper.Reaper;

@Deprecated
public class GameScene extends UI_Scene {
//	private static final int FONT_ID = ResourceManager.FONT_OPTION_ID;
//	private static final int FONT_TITLE_ID = ResourceManager.FONT_TITLE_ID;

	private HashMap<OptionMenu, Text> mOptionTextMap = new HashMap<OptionMenu, Text>();

	private OptionMenu mCurrentMenu = OptionMenu.MAIN;

	private ITextureRegion mBackgroundTextureRegion;
	private BitmapTextureAtlas mBackgroundTextureAtlas;
	private Sprite mBackgroundSprite;

	private Text mTextMenuTitle = new Text(GameConstants.X_SUBMENU_PADDING,
			GameConstants.Y_SUBMENU_PADDING,
			FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
			ResourceManager.getInstance().getResourceString(R.string.tb_main),
			GameConstants.MAX_CHARACTER_SIZE,
			ResourceManager.getInstance().getVertexBufferObjectManager());

	private enum OptionMenu {
		MAIN {
			@Override
			public String toString() {
				return "Menu Principal";
			}

			@Override
			public void setSubMenu(GameScene scene) {

			}

			@Override
			public void back(GameScene scene) {
				scene.setChildScene(new ConfirmDialog(
						"Retourner a l'ecran titre ?",
						ResourceManager.getInstance().getResourceString(R.string.tb_yes),
						ResourceManager.getInstance().getResourceString(R.string.tb_no)) {
					@Override
					protected void onPositive() {
						back();
						SceneManager.getInstance().showScene(SceneTitle.class);
					}
					@Override
					protected void onNegative() {
						back();
					}
				}, false, true, true);
			}
		},
		// Items: tb_05
		ITEMS {
			@Override
			public String toString() {
				return "Inventaire";
			}
			@Override
			public void setSubMenu(GameScene scene) {
				scene.mTextMenuTitle.setText(OptionMenu.ITEMS.toString());
				createSubMenu(scene);
			}

			@Override
			public void back(GameScene scene) {
				destroySubMenu(scene);
			}
		},
		// Equip: tb_06
		EQUIP {
			@Override
			public String toString() {
				return "Equipement";
			}
			@Override
			public void setSubMenu(GameScene scene) {
				scene.mTextMenuTitle.setText(OptionMenu.EQUIP.toString());
				createSubMenu(scene);
			}

			@Override
			public void back(GameScene scene) {
				destroySubMenu(scene);
			}
		},
		// Status: tb_07
		STATUS {
			@Override
			public String toString() {
				return "Status";
			}
			@Override
			public void setSubMenu(GameScene scene) {
				scene.mTextMenuTitle.setText(OptionMenu.STATUS.toString());
				createSubMenu(scene);
//				scene.mPlayer.setVisible(true);
//				scene.mPlayer.updateSkill();
//				scene.mPlayer.updateStatus();
//				scene.mPlayer.setSkillVisible(true);
//				scene.mPlayer.setStatusVisible(false);
			}

			@Override
			public void back(GameScene scene) {
				destroySubMenu(scene);
//				scene.mPlayer.setVisible(false);
			}
		},
		// Map: tb_10
		MAP {
			@Override
			public String toString() {
				return "Map";
			}
			@Override
			public void setSubMenu(GameScene scene) {
				scene.mTextMenuTitle.setText(OptionMenu.MAP.toString());
				createSubMenu(scene);
				SceneManager.getInstance().showScene(BattleScene.class);
			}

			@Override
			public void back(GameScene scene) {
				destroySubMenu(scene);
			}
		},
		// Diary: tb_11
		DIARY {
			@Override
			public String toString() {
				return "Journal";
			}
			@Override
			public void setSubMenu(GameScene scene) {
				scene.mTextMenuTitle.setText(OptionMenu.DIARY.toString());
				createSubMenu(scene);
			}

			@Override
			public void back(GameScene scene) {
				destroySubMenu(scene);
			}
		},
		// Save: tb_08
		SAVE {
			@Override
			public String toString() {
				return "Sauvegarde";
			}
			@Override
			public void setSubMenu(GameScene scene) {
				scene.mTextMenuTitle.setText(OptionMenu.SAVE.toString());
				createSubMenu(scene);
			}

			@Override
			public void back(GameScene scene) {
				destroySubMenu(scene);
			}
		},
		BACK {
			@Override
			public String toString() {
				return "Retour";
			}

			@Override
			public void setSubMenu(GameScene scene) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void back(GameScene scene) {
				// TODO Auto-generated method stub
				
			}
			
		};
		public abstract void setSubMenu(GameScene scene);

		public abstract void back(GameScene scene);

		private static void createSubMenu(GameScene scene) {
			for (Entry<OptionMenu, Text> entry : scene.mOptionTextMap.entrySet()) {
				if (entry.getKey() != OptionMenu.BACK) {
					scene.unregisterTouchArea(entry.getValue());
					entry.getValue().setVisible(false);
				}
			}
		}

		private static void destroySubMenu(GameScene scene) {
			for (Entry<OptionMenu, Text> entry : scene.mOptionTextMap.entrySet()) {
				if (entry.getKey() != OptionMenu.BACK) {
					scene.registerTouchArea(entry.getValue());
					entry.getValue().setVisible(true);
				}
			}
			scene.mCurrentMenu = MAIN;
			scene.mTextMenuTitle.setText(ResourceManager.getInstance()
					.getResourceString(R.string.tb_main));
		}
	} // enum OptionMenu

	@Override
	public void onLoadResources() {
		mBackgroundTextureAtlas = new BitmapTextureAtlas(
				ResourceManager.getInstance().getTextureManager(),
				800, 800, TextureOptions.BILINEAR);
		mBackgroundTextureRegion = ResourceManager.getInstance()
				.getTextureRegion(R.string.bg_04);
		mBackgroundTextureAtlas.load();
	}

//	private Reaper mPlayer = (Reaper) CharacterCollection.getInstance().getCharacter("Dante");

	@Override
	public void onCreate() {
		AudioManager.getInstance().playMusic(R.string.ms_02, true, true);

		mBackgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion,
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mBackgroundSprite.registerEntityModifier(
				new LoopEntityModifier(new MoveYModifier(10, -228, 0)));
		attachChild(mBackgroundSprite);

//		GameScene.this.attachChild(mPlayer);
//		mPlayer.setVisible(false);

		this.attachChild(new Rectangle(GameConstants.X_SUBMENU_PADDING,
				GameConstants.Y_SUBMENU_PADDING + GameConstants.FONT_SIZE,
				GameConstants.CAMERA_WIDTH - GameConstants.X_OPTION_TEXT_PADDING,
				5, ResourceManager.getInstance().getVertexBufferObjectManager()));
		this.attachChild(mTextMenuTitle);
		createSubMenu();
		this.setTouchAreaBindingOnActionDownEnabled(true);
	}

	private void addOptionMenu(final OptionMenu pOption,
			final int pPadding) {
		Text optionMenu = new Text(GameConstants.X_OPTION_TEXT_PADDING,
				GameConstants.Y_OPTION_TEXT_PADDING * pPadding,
				FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
				">> " + pOption.toString(),
				new TextOptions(HorizontalAlign.RIGHT),
				ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
//					Debug.i("You touched Items");
//					goToSubMenu(pOption);
					GameScene.this.mCurrentMenu = pOption;
					pOption.setSubMenu(GameScene.this);
				}
				return true;
			}
		};
		mOptionTextMap.put(pOption, optionMenu);
	}

	private void createSubMenu() {
		addOptionMenu(OptionMenu.ITEMS, 1);
		addOptionMenu(OptionMenu.EQUIP, 2);
		addOptionMenu(OptionMenu.STATUS, 3);
		addOptionMenu(OptionMenu.MAP, 4);
		addOptionMenu(OptionMenu.DIARY, 5);
		addOptionMenu(OptionMenu.SAVE, 6);

		mOptionTextMap.put(OptionMenu.BACK,
				new Text(GameConstants.X_OPTION_TEXT_PADDING, GameConstants.Y_OPTION_TEXT_PADDING * 8,
						FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
						">> " + OptionMenu.BACK.toString(),
						new TextOptions(HorizontalAlign.RIGHT),
						ResourceManager.getInstance().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
					final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					Debug.i("You touched Back");
//					backFromSubMenu();
					GameScene.this.mCurrentMenu.back(GameScene.this);
				}
				return true;
			}
		});


		for (Text object : mOptionTextMap.values()) {
			this.attachChild(object);
			this.registerTouchArea(object);
		}
	}

	@Override
	public void onDestroyResources() {
		
	}


	@Override
	public void onDestroy() {
		AudioManager.getInstance().pauseMusic();
//		mPlayer.detachSelf(); // Do not dispose GameCharacter

		mTextMenuTitle.detachSelf();
		mTextMenuTitle.dispose();

		for (Text object : mOptionTextMap.values()) {
			unregisterTouchArea(object);
			object.detachSelf();
			object.dispose();
		}

		this.detachSelf();
		this.dispose();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
	}
}

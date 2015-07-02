package com.soulreapers.scene;

import java.util.ArrayList;
import java.util.HashMap;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

import com.soulreapers.R;
import com.soulreapers.core.AudioManager;
import com.soulreapers.core.FontManager;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.core.FontManager.FontType;
import com.soulreapers.misc.GameConstants;
import com.soulreapers.object.character.reaper.Reaper;
import com.soulreapers.object.item.GameDataDictionary;
import com.soulreapers.object.item.Inventory;
import com.soulreapers.object.item.ItemBase;
import com.soulreapers.object.item.ItemBase.ItemType;
import com.soulreapers.ui.ItemSlotUI;
import com.soulreapers.ui.menu.MenuDataUI;
import com.soulreapers.ui.menu.MenuInventory;
import com.soulreapers.ui.menu.JournalUI;
import com.soulreapers.ui.menu.MapUI;
import com.soulreapers.ui.menu.MenuUI;
import com.soulreapers.ui.menu.ReaperSlot;
import com.soulreapers.ui.menu.StatusMenuUI;
import com.soulreapers.ui.menu.equip.MenuEquip;
import com.soulreapers.util.SRButton;

public class GameMenuScene extends UI_Scene {
	private static final int TEXTURE_BACKGROUND_ID = R.string.bg_04;
//	private static final int FONT_TITLE_ID = ResourceManager.FONT_TITLE_ID;
	private static final int MUSIC_ID = R.string.ms_02;
	private static final int MENU_TITLE_OFFSET_X = 50;
	private static final int MENU_TITLE_OFFSET_Y = 20;

	private static final int REAPER_CAPACITY = 3;
	private Sprite mBackgroundSprite;
	private HashMap<MenuOptions, SRButton> mSubMenuButtons = new HashMap<MenuOptions, SRButton>();

	private HashMap<MenuOptions, MenuUI> mSubMenuMap = new HashMap<MenuOptions, MenuUI>();
	private ArrayList<Reaper> mReapers = new ArrayList<Reaper>(REAPER_CAPACITY);

//	private Inventory mInventory = new Inventory();

	private Text mTextMenuTitle = new Text(MENU_TITLE_OFFSET_X,
			MENU_TITLE_OFFSET_Y,
			FontManager.getInstance().getFont(FontType.FONT_OPTION_SMALL),
			GameConstants.STRING.MAIN,
			GameConstants.MAX_CHARACTER_SIZE,
			new TextOptions(HorizontalAlign.LEFT),
			ResourceManager.getInstance().getVertexBufferObjectManager());

	private enum MenuOptions {
		INVENTORY {
			@Override
			public String toString() {
				return GameConstants.STRING.INVENTORY;
			}
			@Override
			public MenuUI instanciate(GameMenuScene pMenuScene) {
				return new MenuInventory(pMenuScene);
			}
		},
		EQUIP {
			@Override
			public String toString() {
				return GameConstants.STRING.EQUIP;
			}
			@Override
			public MenuUI instanciate(GameMenuScene pMenuScene) {
				return new MenuEquip(pMenuScene);
			}
		},
		STATUS {
			@Override
			public String toString() {
				return GameConstants.STRING.STATUS;
			}
			@Override
			public MenuUI instanciate(GameMenuScene pMenuScene) {
				return new StatusMenuUI(pMenuScene);
			}
		},
		MAP {
			@Override
			public String toString() {
				return GameConstants.STRING.MAP;
			}
			@Override
			public MenuUI instanciate(GameMenuScene pMenuScene) {
				return new MapUI(pMenuScene);
			}
		},
		JOURNAL {
			@Override
			public String toString() {
				return GameConstants.STRING.JOURNAL;
			}
			@Override
			public MenuUI instanciate(GameMenuScene pMenuScene) {
				return new JournalUI(pMenuScene);
			}
		},
		DATA {
			@Override
			public String toString() {
				return GameConstants.STRING.DATA;
			}
			@Override
			public MenuUI instanciate(GameMenuScene pMenuScene) {
				return new MenuDataUI(pMenuScene);
			}
		};
		abstract public MenuUI instanciate(GameMenuScene pMenuScene);
	}

	@Override
	public void onLoadResources() {
		ResourceManager.getInstance().loadTexture(TEXTURE_BACKGROUND_ID);
	}

	@Override
	public void onCreate() {
		AudioManager.getInstance().playMusic(MUSIC_ID, true, true);

		mBackgroundSprite = new Sprite(0, 0,
				ResourceManager.getInstance().getTextureRegion(TEXTURE_BACKGROUND_ID),
				ResourceManager.getInstance().getVertexBufferObjectManager());
		mBackgroundSprite.registerEntityModifier(
				new LoopEntityModifier(new MoveYModifier(10, -228, 0)));

		//------------------
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.soul_ruby), 1);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.soul_sapphire), 1);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.soul_emerald), 1);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.soul_topaz), 1);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.soul_quartz), 10);
//
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.reapers_scythe), 1);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.scarf), 1);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.reapers_sigil), 1);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.vampire_fang), 1);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem(R.string.reapers_scythe), 2);
//		//------------------
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem("[ST] Frappe éclaire"), 3);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem("[ST] Coup double"), 1);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem("[SU] Frappe ascendante"), 3);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem("[SD] Plongée aérienne"), 2);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem("[SL] Frappe horizontale"), 2);
//		Inventory.getInstance().add(GameDataDictionary.getInstance().getItem("[SR] Vrille aérienne"), 1);
//
//		//------------------
//		mReapers.add(new Reaper("Dante", R.string.pc_01, R.string.ic_01));
//		mReapers.add(new Reaper("Beatrix", R.string.pc_01, R.string.ic_01));
//		mReapers.add(new Reaper("Vergil", R.string.pc_01, R.string.ic_01));
//		//------------------


		this.attachChild(mBackgroundSprite);
		this.attachChild(mTextMenuTitle);

		int padding = 0;
		for (MenuOptions option : MenuOptions.values()) {
			addSubMenuButton(option, padding++);
			MenuUI menu = option.instanciate(GameMenuScene.this);
			mSubMenuMap.put(option, menu);
			GameMenuScene.this.attachChild(menu);
		}
		for (SRButton button : mSubMenuButtons.values()) {
			this.attachChild(button);
			this.registerTouchArea(button);
		}

		this.setTouchAreaBindingOnActionDownEnabled(true);
	}

	public ArrayList<Reaper> getReapers() {
		return mReapers;
	}

	@Override
	public void onDestroyResources() {
		ResourceManager.getInstance().unloadTexture(TEXTURE_BACKGROUND_ID);
	}

	@Override
	public void onDestroy() {
		AudioManager.getInstance().pauseMusic();

		mBackgroundSprite.clearEntityModifiers();
		mBackgroundSprite.detachSelf();
		mBackgroundSprite.dispose();

		mTextMenuTitle.detachSelf();
		mTextMenuTitle.dispose();

		for (SRButton button : mSubMenuButtons.values()) {
			this.unregisterTouchArea(button);
			button.detachSelf();
			button.dispose();
		}

		for (MenuUI menu : mSubMenuMap.values()) {
			menu.setUiEnabled(false);
			menu.detachSelf();
			menu.dispose();
		}

		this.detachChildren();
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


//	public Inventory getInventory() {
//		return mInventory;
//	}


	private void addSubMenuButton(final MenuOptions pOption,
			final int pPaddingY) {
		mSubMenuButtons.put(pOption,
				new SRButton(GameConstants.UI.MENU_OPTION_OFFSET_X,
						GameConstants.UI.MENU_OPTION_OFFSET_Y + GameConstants.UI.MENU_OPTION_PADDING_Y * pPaddingY,
						pOption.toString()) {
			@Override
			public void onPressed(final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				mTextMenuTitle.setText(pOption.toString());

				for (SRButton button : mSubMenuButtons.values()) {
					button.setEnabled(false);
					button.setVisible(false);
				}
				mSubMenuMap.get(pOption).setUiEnabled(true);
			}
		});
	}

	public void backToMainMenu() {
		mTextMenuTitle.setText(GameConstants.STRING.MAIN);
		for (SRButton button : mSubMenuButtons.values()) {
			button.setEnabled(true);
			button.setVisible(true);
		}
	}
}

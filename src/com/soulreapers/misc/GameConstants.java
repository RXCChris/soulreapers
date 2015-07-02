package com.soulreapers.misc;

import org.andengine.util.color.Color;

/**
 * @since 2014.10.29
 * @version 0.1 (alpha)
 * @author dxcloud
 */
public final class GameConstants {
	public static final int FPS_LIMIT = 30;
	public static final int CAMERA_WIDTH = 800;
	public static final int CAMERA_HEIGHT = 480;
	public static final int SPLASH_DURATION = 8000; // 8 seconds
	public static final int FONT_SIZE = 36;
	public static final int X_OPTION_TEXT_PADDING = 500;
	public static final int Y_OPTION_TEXT_PADDING = 50;
	public static final int X_SUBMENU_PADDING = 50;
	public static final int Y_SUBMENU_PADDING = 20;
	public static final int MAX_CHARACTER_SIZE = 30;
	public static final int BATTLE_CELL_SIZE = 50;
	public static final int X_BATTLE_FIELD_PADDING = 360;
	public static final int Y_BATTLE_FIELD_PADDING = 40;
	public static final int BATTLE_CELLS_PER_ROW = 8;
	public static final int BATTLE_CELLS_PER_COLUMN = 8;
	public static final int CHARACTER_SPRITE_WIDTH = 320;
	public static final int CHARACTER_SPRITE_HEIGHT = 480;

	public static final class UI {
		public static final Color COLOR_BACKGROUND = new Color(0, 0, 0, 0.9F);
		public static final Color COLOR_SELECTED = new Color(0.8F, 0.2F, 0.2F, 0.5F);
		public static final Color COLOR_UNSELECTED = new Color(0.2F, 0.8F, 0.8F, 0.5F);
		public static final Color COLOR_NORMAL = new Color(0.2F, 0.8F, 0.8F, 0.5F);

		public static final int MENU_OPTION_OFFSET_X = 500;
		public static final int MENU_OPTION_OFFSET_Y = 50;
		public static final int MENU_OPTION_PADDING_X = 0;
		public static final int MENU_OPTION_PADDING_Y = 50;
	}

	public static final class REAPER {
		public static final int COMBAT_ITEM_CAPACITY = 6;
		public static final int ACCESSORY_CAPACITY = 2;
	}

	public static final class STRING {
		// TODO
		// Multi-language support
		public static final String BACK       = "Retour";
		public static final String SOUL       = "SOUL";
		public static final String EXP        = "EXP";
		public static final String COURAGE    = "Courage";
		public static final String PRUDENCE   = "Prudence";
		public static final String TEMPERANCE = "Tempérance";
		public static final String JUSTICE    = "Justice";

		public static final String INVENTORY  = "Inventaire";
		public static final String COMBAT     = "Combat";
		public static final String WEAPON     = "Armes";
		public static final String ACCESSORY  = "Accessoires";
		public static final String LOOT       = "Butins";
		public static final String KEY        = "Objets Clés";

		public static final String NAME       = "Nom";
		public static final String EQUIP      = "Equipement";
		public static final String SKILL      = "Compétences";
		public static final String ITEM       = "Objets";
		public static final String STATUS     = "Status";
		public static final String INFO       = "Information";
		public static final String HELP       = "Aide";
		public static final String MAP        = "Map";
		public static final String JOURNAL    = "Journal";
		public static final String DATA       = "Donnees";
		public static final String MAIN       = "Menu Principal";
		public static final String TOTAL      = "Total";

		public static final String PLAY       = "Commencer";
		public static final String OPTIONS    = "Options";
		public static final String EXTRAS     = "Extras";
		public static final String SAVE       = "Savegarder" ;
		public static final String LOAD       = "Charger";
		public static final String TITLE      = "Ecran Titre";
		public static final String SYSTEM     = "Système";

		public static final String END        = "Fin";
		public static final String EMPTY      = "Vide";
		public static final String REMOVE     = "Enlever";


		public static final String CONFIRM_RETURN_TO_TITLE = "Retourner sur l'Ecran-titre ?";
	}
}

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
package com.soulreapers.object.item;

import java.io.IOException;
import java.util.HashMap;

import org.andengine.entity.IEntity;
import org.andengine.util.SAXUtils;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.IEntityLoader;
import org.andengine.util.level.LevelLoader;
import org.xml.sax.Attributes;

import com.soulreapers.GameActivity;
import com.soulreapers.core.ResourceManager;
import com.soulreapers.object.item.ItemBase.ItemType;
import com.soulreapers.skill.SkillAttack;
import com.soulreapers.skill.Skill;
import com.soulreapers.skill.SkillGesture;

/**
 * @author dxcloud
 *
 */
public class GameDataDictionary {
	private static final String TAG_INVENTORY = "inventory";

	private static final String TAG_ITEM = "item";
	private static final String TAG_ATTRIBUTE_ID = "id";
	private static final String TAG_ATTRIBUTE_TYPE = "type";
	private static final String TAG_ATTRIBUTE_NAME = "name";
	private static final String TAG_ATTRIBUTE_DESCRIPTION = "description";

	private static final Object TAG_ITEM_ATTRIBUTE_TYPE_VALUE_COMBAT = "combat";
	private static final Object TAG_ITEM_ATTRIBUTE_TYPE_VALUE_WEAPON = "weapon";
	private static final Object TAG_ITEM_ATTRIBUTE_TYPE_VALUE_ACCESSORY = "accessory";
	private static final Object TAG_ITEM_ATTRIBUTE_TYPE_VALUE_LOOT = "loot";
	private static final Object TAG_ITEM_ATTRIBUTE_TYPE_VALUE_KEY = "key";

	private static final Object TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_ST = "st";
	private static final Object TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_SU = "su";
	private static final Object TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_SD = "sd";
	private static final Object TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_SL = "sl";
	private static final Object TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_SR = "sr";

	private static final GameDataDictionary INSTANCE = new GameDataDictionary();
	private HashMap<String, ItemBase> mItemMap = new HashMap<String, ItemBase>();

//	private HashMap<Integer, Skill> mSkillMap = new HashMap<Integer, Skill>();

	public static GameDataDictionary getInstance() {
		return INSTANCE;
	}

	public ItemBase getItem(String pName) {
		return mItemMap.get(pName);
	}

	public ItemBase getItem(int pItemId) {
		String name = ResourceManager.getInstance().getResourceString(pItemId);
		return mItemMap.get(name);
	}

	public void initialize(GameActivity pActivity) {
		loadInventory(pActivity);
		loadSkills(pActivity);
	}

	private void loadInventory(GameActivity pActivity) {
		final LevelLoader levelLoader = new LevelLoader();
		levelLoader.setAssetBasePath("data/");

		levelLoader.registerEntityLoader(TAG_INVENTORY, new IEntityLoader() {

			@Override
			public IEntity onLoadEntity(String pEntityName,
					Attributes pAttributes) {
				// nothing to do
				return null;
			}
			
		});

		levelLoader.registerEntityLoader(TAG_ITEM, new IEntityLoader() {
			@Override
			public IEntity onLoadEntity(String pEntityName,
					Attributes pAttributes) {
				final int id = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_ID);
				final String name = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_NAME);
				final String type = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_TYPE);
				final String description = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_DESCRIPTION);

				Debug.i("GameData: [id:" + id+",type:"+ type + ",name:"+name+"]");

				if (type.equals(TAG_ITEM_ATTRIBUTE_TYPE_VALUE_COMBAT)) {
					mItemMap.put(name, new CombatItem(id, name, description, 0));
				} else if (type.equals(TAG_ITEM_ATTRIBUTE_TYPE_VALUE_WEAPON)) {
					mItemMap.put(name, new Weapon(id, name, description, 0));
				} else if (type.equals(TAG_ITEM_ATTRIBUTE_TYPE_VALUE_ACCESSORY)) {
					mItemMap.put(name, new ItemBase(id, ItemType.ACCESSORY, name, description, 0));
				} else if (type.equals(TAG_ITEM_ATTRIBUTE_TYPE_VALUE_LOOT)) {
					mItemMap.put(name, new ItemBase(id, ItemType.LOOT, name, description, 0));
				} else if (type.equals(TAG_ITEM_ATTRIBUTE_TYPE_VALUE_KEY)) {
					mItemMap.put(name, new ItemBase(id, ItemType.KEY, name, description, 0));
				}
				return null;
			}
			
		});

		try {
			levelLoader.loadLevelFromAsset(pActivity.getAssets(), "inventory.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private GameDataDictionary() {

	}

	private static final String TAG_ATTACK = "attack";
	private static final String TAG_SKILL = "skill";
	private static final String TAG_ATTRIBUTE_DAMAGE = "damage";
	private static final String TAG_ATTRIBUTE_COST = "cost";

	private void loadSkills(GameActivity pActivity) {
		final LevelLoader levelLoader = new LevelLoader();
		levelLoader.setAssetBasePath("data/");

		levelLoader.registerEntityLoader(TAG_SKILL, new IEntityLoader() {
			@Override
			public IEntity onLoadEntity(String pEntityName,
					Attributes pAttributes) {
				// nothing to do
				return null;
			}
			
		});

		levelLoader.registerEntityLoader(TAG_ATTACK, new IEntityLoader() {
			@Override
			public IEntity onLoadEntity(String pEntityName,
					Attributes pAttributes) {
				final int id = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_ID);
				final String name = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_NAME);
				final String type = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_TYPE);
				final int damage = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_DAMAGE);
				final int cost = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_COST);
				final String description = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ATTRIBUTE_DESCRIPTION);

				Debug.i("GameData skill: [id:" + id+",type:"+ type + ",name:"+name+",dmg:"+damage+",cost:"+cost+"]");

				if (type.equals(TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_ST)) {
					mItemMap.put(name, new SkillAttack(id, SkillGesture.SINGLE_TAP, name, description, damage, cost));
				} else if (type.equals(TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_SU)) {
					mItemMap.put(name, new SkillAttack(id, SkillGesture.SWIPE_UP, name, description, damage, cost));
				} else if (type.equals(TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_SD)) {
					mItemMap.put(name, new SkillAttack(id, SkillGesture.SWIPE_DOWN, name, description, damage, cost));
				} else if (type.equals(TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_SL)) {
					mItemMap.put(name, new SkillAttack(id, SkillGesture.SWIPE_LEFT, name, description, damage, cost));
				} else if (type.equals(TAG_SKILL_ATTACK_ATTRIBUTE_TYPE_VALUE_SR)) {
					mItemMap.put(name, new SkillAttack(id, SkillGesture.SWIPE_RIGHT, name, description, damage, cost));
				}
//				mSkillMap.put(id, new SkillAttack(name, description, damage, cost));
				return null;
			}
		});

		try {
			levelLoader.loadLevelFromAsset(pActivity.getAssets(), "skill.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

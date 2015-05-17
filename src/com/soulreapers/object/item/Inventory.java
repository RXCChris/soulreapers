/**
 * 
 */
package com.soulreapers.object.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.andengine.util.debug.Debug;

import com.soulreapers.object.item.ItemBase.ItemType;

/**
 * @author chris
 *
 */
public class Inventory {
	private HashMap<ItemType, ArrayList<ItemBase>> mItemMap = new HashMap<ItemType, ArrayList<ItemBase>>();

	private static final Inventory INSTANCE = new Inventory();

	public static Inventory getInstance() {
		return INSTANCE;
	}

	private Inventory () {
		for (ItemType type : ItemType.values()) {
			mItemMap.put(type, new ArrayList<ItemBase>());
		}
	}

	public ArrayList<ItemBase> getItemList(ItemType pType) {
		return mItemMap.get(pType);
	}

	public void add(ItemBase pItem, int pAmount) {
		ArrayList<ItemBase> list = mItemMap.get(pItem.getItemType());
		int index = list.indexOf(pItem);
		Debug.d("Inventory add >> [type:" + pItem.getItemType() + ",amount:" + pAmount + "]");
		if (index > -1) {
			Debug.d("Inventory item increased");
			list.get(index).increase(this, pAmount);
		} else {
			Debug.d("Inventory new added");
			pItem.setQuantity(this, pAmount);
			list.add(pItem);
			Collections.sort(list);
		}
	}

	public void remove(ItemBase pItem, int pAmount) {
		final ItemType type = pItem.getItemType();
		ArrayList<ItemBase> list = mItemMap.get(type);
		int index = list.indexOf(pItem);
		if (index > -1) {
			ItemBase item = list.get(index);
			item.decrease(this, pAmount);
		}
	}

	public void removeAll(ItemBase pItem) {
		final ItemType type = pItem.getItemType();
		ArrayList<ItemBase> list = mItemMap.get(type);
		list.remove(pItem);
	}
}

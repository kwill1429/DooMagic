package com.psutton.utilities;

import com.epicbot.api.rs3.methods.tab.inventory.Inventory;


public class ItemHelper {
	
	public static boolean isNotedItemInInventory(int itemID) {
		if (Inventory.contains(itemID)) {
			return true;
		}
		return false;
	}
	
	public static boolean areItemsInInventory(int[] items, int[] quantities) {
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (quantities[i] == -1) {
					if (!Inventory.contains(items[i])) {
						return false;
					}
				}
				else {
					if (Inventory.getCount(items[i]) < quantities[i]) {
						return false;
					}
				}	
			}
		}
		return true;
	}

}

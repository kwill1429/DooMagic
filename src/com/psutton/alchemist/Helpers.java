package com.psutton.alchemist;

import java.util.ArrayList;

import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.psutton.utilities.objects.PSRune;

public class Helpers {

	public static PSRune[] areNecessaryRunesInBank(PSRune[] runes) {
		int numOfDifferentRunesNeeded = runes.length;
		PSRune[] associatedRunes;
		PSRune rune, associatedRune;
		PSRune[] runesToWithdraw = null;
		ArrayList<PSRune> runesToWithdrawList = new ArrayList<PSRune>(numOfDifferentRunesNeeded);
		
		for (int i = 0; i < runes.length; i++) {
			rune = runes[i];
			
			System.out.println("Checking for: " +rune+" in bank with ID of: "+rune.getItemID());
			if (isRuneInBank(rune)) {
				runesToWithdrawList.add(rune);
				System.out.println(rune+" is in bank!");
			}
			else {
				associatedRunes = rune.getAssociatedRunes();
				if (associatedRunes != null) {
					for (int j = 0; j < associatedRunes.length; j++) {
						associatedRune = associatedRunes[j];
						if (isRuneInBank(associatedRune)) {
							runesToWithdrawList.add(associatedRune);
							break;
						}
					}
				}
			}
		}
		
		runesToWithdrawList.trimToSize();
		
		if (runesToWithdrawList.size() == numOfDifferentRunesNeeded) {
			int index = 0;
			runesToWithdraw = new PSRune[numOfDifferentRunesNeeded];
			
			for (PSRune runeToWithdraw : runesToWithdrawList) {
				runesToWithdraw[index] = runeToWithdraw;
				index ++;
			}
		}
		
		return runesToWithdraw;
	}
	
	
	public static boolean isRuneInBank(PSRune rune) {
		if (Bank.getItemCount(true, rune.getItemID()) >= rune.getNumOfRunes()) {
			return true;
		}
		return false;
	}
	
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
	
	public static PSRune[] getRunesToWithdraw(PSRune[] runes) {
		ArrayList<PSRune> runesToWithdrawList = new ArrayList<PSRune>();
		PSRune rune;
		PSRune[] runesToWithdraw = null;
		int index = 0;
		
		for (int i = 0; i < runes.length; i++) {
			rune = runes[i];
			if (!Spells.meetsRuneRequirement(rune)) {
				runesToWithdrawList.add(rune);
			}
		}
		
		runesToWithdrawList.trimToSize();
		
		if (!runesToWithdrawList.isEmpty()) {
			runesToWithdraw = new PSRune[runesToWithdrawList.size()];
			for (PSRune runeToWithdraw : runesToWithdrawList) {
				runesToWithdraw[index] = runeToWithdraw;
				index ++;
			}
		}
		
		return runesToWithdraw;
	}

}

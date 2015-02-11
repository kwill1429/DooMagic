package com.psutton.alchemist;

import java.util.ArrayList;

import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.psutton.utilities.objects.PSRune;
import com.psutton.utilities.objects.PSSpell;
import com.psutton.utilities.objects.PSStaff;

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
			if (!meetsRuneRequirement(rune)) {
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
	
	public static boolean areRunesForSpellInInventory() {
		PSSpell spell = AlchemistGlobal.selectedSpell;
		PSRune rune;
		PSRune[] requiredRunes = spell.getRunes(); 
		
		for (int i = 0; i < requiredRunes.length; i++) {
			rune = requiredRunes[i];
			System.out.println("Required rune: "+ rune);
			if (!meetsRuneRequirement(rune)) {
				System.out.println("do not have enough of: " + rune.getItemName());
				return false;
			}
		}	
		
		return true;
	}
	
	public static boolean isValidStaffEquipped(PSStaff[] staves) {
		for (int i = 0; i < staves.length; i++) {
			if (Equipment.containsOneOf(staves[i].getItemID())) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean meetsRuneRequirement(PSRune rune) {
		int runeID, numOfRunes;
		PSRune[] relatedRunes;
		PSStaff[] staves;
		
		runeID = rune.getItemID();
		numOfRunes = rune.getNumOfRunes();
		relatedRunes = rune.getAssociatedRunes();
		staves = rune.getAssociatedStaves();
		
		if (staves != null) {
			if (isValidStaffEquipped(staves)) {
				return true;
			}
		}
		
		
		if (hasEnoughOfRune(runeID, numOfRunes)) {
			return true;
		}
		else {
			if (relatedRunes != null) {
				for (int i = 0; i < relatedRunes.length; i++) {
					runeID = relatedRunes[i].getItemID();
					if (hasEnoughOfRune(runeID, numOfRunes)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private static boolean hasEnoughOfRune(int runeID, int numOfRunes) {
		if (Inventory.contains(runeID)) {
			if (Inventory.getCount(true, runeID) >= numOfRunes) {
				return true;
			}
		}
		return false;
	}

}

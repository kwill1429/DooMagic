package helpers;

import helpers.objects.MagicSpell;
import helpers.objects.RuneForSpell;
import alchemist.AlchemistGlobal;

import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;

public class InventoryHelper {
	
	public static boolean isValidStaffEquipped(int[] staves) {
		for (int i = 0; i < staves.length; i++) {
			if (Equipment.containsOneOf(staves[i])) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean areItemsInInventory() {
		MagicSpell spell = AlchemistGlobal.selectedSpell;
		RuneForSpell rune;
		RuneForSpell[] requiredRunes = spell.getRequiredRunes(); 
		
		for (int i = 0; i < requiredRunes.length; i++) {
			rune = requiredRunes[i];
			
			if (!meetsRuneRequirement(rune)) {
				System.out.println("do not have enough of: " + rune.getRuneName());
				break;
			}
		}	
		
		return false;
	}
	
	private static boolean meetsRuneRequirement(RuneForSpell rune) {
		int runeID, numOfRunes;
		int[] relatedRunes;
		int[] staves;
		
		runeID = rune.getRuneID();
		numOfRunes = rune.getNumOfRunes();
		relatedRunes = MagicHelper.getRelatedRunes(runeID);
		staves = MagicHelper.getStavesForRune(runeID);
		
		if (rune.canUseStaff()) {
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
					runeID = relatedRunes[i];
						
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

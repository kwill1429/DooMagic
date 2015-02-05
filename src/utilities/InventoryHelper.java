package utilities;

import utilities.objects.MagicSpell;
import alchemist.AlchemistGlobal;
import alchemist.AlchemistRune;

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
	
	public static boolean areRunesInInventory() {
		MagicSpell spell = AlchemistGlobal.selectedSpell;
		AlchemistRune rune;
		AlchemistRune[] requiredRunes = spell.getRequiredRunes(); 
		
		for (int i = 0; i < requiredRunes.length; i++) {
			rune = requiredRunes[i];
			
			if (!meetsRuneRequirement(rune)) {
				System.out.println("do not have enough of: " + rune.getRuneName());
				return false;
				//break;
			}
		}	
		
		return true;
	}
	
	private static boolean meetsRuneRequirement(AlchemistRune rune) {
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

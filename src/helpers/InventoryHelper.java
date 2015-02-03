package helpers;

import alchemist.AlchemistGlobal;

import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;

public class InventoryHelper {

	public static boolean validStaffEquipped(int[] staves) {
//		if (Equipment.containsOneOf(AlchemistGlobal.staffFire) || 
//				Equipment.containsOneOf(AlchemistGlobal.staffLava) || 
//				Equipment.containsOneOf(AlchemistGlobal.staffSteam) ) {
//					return true;
//		}
		for (int staff : staves) {
			if (Equipment.containsOneOf(staff)) {
				return true;
			}
		}
		return false;
	}
	
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
		int[] staves = spell.getStaves();
		RuneForSpell[] requiredRunes = spell.getRequiredRunes(); 
		RuneForSpell[] substituteRunes = spell.getSubstituteRunes();
		boolean haveSubstituteRunes = false;
		boolean haveRequiredRunes = false;
		boolean result = false;
		
		if (!isValidStaffEquipped(staves)) {
			System.out.println("Staff Not Equipped");
			for (int i=0; i < substituteRunes.length; i++) {
				if (Inventory.getItem(substituteRunes[i].getRuneID()) != null) {
					System.out.println("Num of substituteRunes: " + Inventory.getCount(true, substituteRunes[i].getRuneID())); 
					if (Inventory.getCount(true, substituteRunes[i].getRuneID()) >= substituteRunes[i].getNumOfRunes()) {
						haveSubstituteRunes = true;
						break;
					}
				}
			}
			
		}
		else {
			haveSubstituteRunes = true;
			System.out.println("Staff equipped");
		}
		
		for (int j = 0; j < requiredRunes.length; j++) {
			if (Inventory.getItem(requiredRunes[j].getRuneID()) != null) {
				if (Inventory.getCount(true, requiredRunes[j].getRuneID()) >= requiredRunes[j].getNumOfRunes()) {
					System.out.println("Have enough of required runes");
					haveRequiredRunes = true;
					break;
				}
			}
		}
		
		if (haveSubstituteRunes && haveRequiredRunes) {
			System.out.println("Have necessary runes.");
			result = true;	
		}
		return result;
	}	
}

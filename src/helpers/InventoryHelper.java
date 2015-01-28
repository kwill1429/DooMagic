package helpers;

import com.epicbot.api.rs3.methods.tab.Equipment;

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
}

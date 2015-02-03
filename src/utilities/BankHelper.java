package helpers;

import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;

public class BankHelper {
	
	public static void withdrawRunes(int[] runesToKeep, int id) {
		if (!Inventory.contains(id)) {
			if (Inventory.isFull()) {
				Bank.depositAllExcept(runesToKeep);
				withdrawRunes(runesToKeep, id);
			}
			else {
				if (Bank.getItem(id) != null) {
					System.out.println("Withdrawing nature rune");
					Bank.withdraw(id, Bank.Amount.ALL);
				}
			}
		}
	}
	
	public static void fillInventoryWithItem(int id) {
		int numToWithdraw;
		if (Bank.getItem(id) != null) {
			numToWithdraw = 28 - Inventory.getCount();
			Bank.withdraw(id, numToWithdraw);
			Bank.close();
		}
	}
}

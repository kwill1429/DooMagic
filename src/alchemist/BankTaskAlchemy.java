package alchemist;

import helpers.BankHelper;
import helpers.InventoryHelper;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;

public class BankTaskAlchemy extends Node implements Task {
		
	@Override
	public void run() {
		System.out.println("It's time to bank!");
		if (Bank.BankLocations.atBank()) {
			System.out.println("We are in a bank already.");
			Bank.open();
			if (Bank.isOpen()) {
				System.out.println("Bank is open");
				if (Bank.isWithdrawNotedEnabled()) {
					System.out.println("WithdrawNote is enabled.");
					Bank.setWithdrawNoted(false);
				}
				else {
					System.out.println("Banking with noted disabled");
					withdrawRunesForAlchemy();
					BankHelper.fillInventoryWithItem(AlchemistGlobal.itemToAlch);
				}
			}
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null) {
			if (!haveItemsInInventoryForAlchemy()) {
				return true;
			}
		}
		return false;
	}
	
	private void withdrawRunesForAlchemy() {
		BankHelper.withdrawRunes(AlchemistGlobal.runesAlchemy, AlchemistGlobal.runeNature);
		if (!InventoryHelper.validStaffEquipped(AlchemistGlobal.stavesAlchemy)) {
			BankHelper.withdrawRunes(AlchemistGlobal.runesAlchemy, AlchemistGlobal.runeFire);
		}
	}
	
	
	public static boolean haveItemsInInventoryForAlchemy() {
		if (InventoryHelper.validStaffEquipped(AlchemistGlobal.stavesAlchemy)) {
			if (Inventory.contains(AlchemistGlobal.runeNature) && 
					Inventory.contains(AlchemistGlobal.itemToAlch)) {
						return true;
			}
		}
		else {
			if (Inventory.contains(AlchemistGlobal.runeNature) && 
					Inventory.contains(AlchemistGlobal.runeFire) &&
					Inventory.contains(AlchemistGlobal.itemToAlch)) {
						return true;
			}
		}
		return false;
	}
}

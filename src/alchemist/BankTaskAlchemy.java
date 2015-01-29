package alchemist;

import helpers.BankHelper;
import helpers.InventoryHelper;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.epicbot.api.rs3.methods.widget.Bank.Amount;

public class BankTaskAlchemy extends Node implements Task {
		
	@Override
	public void run() {
		System.out.println("It's time to bank!");
		if (Bank.BankLocations.atBank()) {
			System.out.println("We are in a bank already.");
			Bank.open();
			if (Bank.isOpen()) {
				System.out.println("Bank is open");
				if (!Bank.isWithdrawNotedEnabled()) {
					Bank.setWithdrawNoted(true);
				}
				withdrawRunesForAlchemy();
				Bank.withdraw(AlchemistGlobal.itemToAlch, Amount.ALL);
				Bank.close();
				//BankHelper.fillInventoryWithItem(AlchemistGlobal.itemToAlch);
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
		System.out.println("Entering withdrawRunes");
		BankHelper.withdrawRunes(AlchemistGlobal.runesAlchemy, AlchemistGlobal.runeNature);
		if (!InventoryHelper.validStaffEquipped(AlchemistGlobal.stavesAlchemy)) {
			BankHelper.withdrawRunes(AlchemistGlobal.runesAlchemy, AlchemistGlobal.runeFire);
		}
	}
	
	
	public static boolean haveItemsInInventoryForAlchemy() {
		if (InventoryHelper.validStaffEquipped(AlchemistGlobal.stavesAlchemy)) {
			if (Inventory.contains(AlchemistGlobal.runeNature) && 
					Inventory.contains(AlchemistGlobal.itemToAlchNoted)) {
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

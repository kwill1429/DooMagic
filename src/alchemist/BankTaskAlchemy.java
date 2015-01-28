package alchemist;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Equipment;
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
					withdrawMoreOfItemToAlch();
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
	
	private void withdrawRunes(int[] runesToKeep, int id) {
		if (!Inventory.contains(id)) {
			if (Inventory.isFull()) {
				Bank.depositAllExcept(runesToKeep);
				withdrawRunes(runesToKeep, id);
			}
			else {
				if (Bank.getItem(id) != null) {
					Bank.withdraw(id, Bank.Amount.ALL);
				}
			}
		}
	}
	
	private void withdrawRunesForAlchemy() {
		withdrawRunes(AlchemistGlobal.runesAlchemy, AlchemistGlobal.runeNature);
		if (!staffEquippedForAlch()) {
			withdrawRunes(AlchemistGlobal.runesAlchemy, AlchemistGlobal.runeFire);
		}
	}
	
	private static boolean staffEquippedForAlch() {
		if (Equipment.containsOneOf(AlchemistGlobal.staffFire) || 
				Equipment.containsOneOf(AlchemistGlobal.staffLava) || 
				Equipment.containsOneOf(AlchemistGlobal.staffSteam) ) {
					return true;
		}
		return false;
	}
	
	private void withdrawMoreOfItemToAlch() {
		System.out.println("Entered withdrawMoreOfItemToAlch");
		int numToWithdraw;
		if (Bank.getItem(AlchemistGlobal.itemToAlch) != null) {
			numToWithdraw = 28 - Inventory.getCount();
			Bank.withdraw(AlchemistGlobal.itemToAlch, numToWithdraw);
			Bank.close();
		}
	}
	
	public static boolean haveItemsInInventoryForAlchemy() {
		if (staffEquippedForAlch()) {
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

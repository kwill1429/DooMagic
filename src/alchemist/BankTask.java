package alchemist;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;

public class BankTask extends Node implements Task {
	
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
					if (AlchemistGlobal.isStaffEquipped()) {
						System.out.println("Staff is equipped.");
						withdrawRunesIfNeeded(AlchemistGlobal.idNatureRune);
						withdrawMoreOfItemToAlch();
					}
					else {
						withdrawRunesIfNeeded(AlchemistGlobal.idNatureRune);
						withdrawRunesIfNeeded(AlchemistGlobal.idFireRune);
						withdrawMoreOfItemToAlch();
					}
				}
			}
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null) {
			if (!AlchemistGlobal.areNecessaryItemsInInventory()) {
				return true;
			}
		}
		return false;
	}
	
	private void withdrawRunesIfNeeded(int idOfRunes) {
		if (!Inventory.contains(idOfRunes)) {
			if (Inventory.isFull()) {
				Bank.deposit(AlchemistGlobal.idOfItemToAlch, 1);
			}
			System.out.println("No Nature Runes in Inventory");
			if (Bank.getItem(idOfRunes) != null) {
				System.out.println("Nature Runes in Bank");
				Bank.withdraw(idOfRunes, Bank.Amount.ALL);
			}
		}
	}

	private void withdrawMoreOfItemToAlch() {
		if (Bank.getItem(AlchemistGlobal.idOfItemToAlch) != null) {
			System.out.println("Number of items to alch: "+Bank.getItemCount(true, AlchemistGlobal.idOfItemToAlch));
			if (Bank.getItemCount(AlchemistGlobal.idOfItemToAlch) > 26) {
				Bank.withdraw(AlchemistGlobal.idOfItemToAlch, 27);
			}
			else {
				Bank.withdraw(AlchemistGlobal.idOfItemToAlch, Bank.Amount.ALL);
			}
			Bank.close();
		}
	}
	
	
}

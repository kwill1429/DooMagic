import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;

public class BankTask extends Node implements Task {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
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
					if (FreeAlchemist.isStaffEquipped()) {
						System.out.println("Staff is equipped.");
						withdrawNatureRunesIfNeeded();
						withdrawMoreOfItemToAlch();
					}
				}
			}
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null) {
			if(Inventory.getCount() <= 1) {
				return true;
			}
		}
		return false;
	}
	
	
	
	private void withdrawNatureRunesIfNeeded() {
		if (!Inventory.contains(561)) {
			System.out.println("No Nature Runes in Inventory");
			if (Bank.getItem(561) != null) {
				System.out.println("Nature Runes in Bank");
				Bank.withdraw(561, Bank.Amount.ALL);
			}
		}
	}

	private void withdrawMoreOfItemToAlch() {
		if (Bank.getItem(FreeAlchemist.idOfItemToAlch) != null) {
			System.out.println("withdrawMoreOfItemToAlch");
			if (Bank.getItemCount(FreeAlchemist.idOfItemToAlch) > 26) {
				Bank.withdraw(FreeAlchemist.idOfItemToAlch, 26);
			}
			else {
				Bank.withdraw(FreeAlchemist.idOfItemToAlch, Bank.getItemCount(FreeAlchemist.idOfItemToAlch));
			}
		}
	}
}

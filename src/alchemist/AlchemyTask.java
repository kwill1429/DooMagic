package alchemist;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;

public class AlchemyTask extends Node implements Task {
	public boolean shouldStop = false;
	
	@Override
	public void run() {
		Item item;
		
		if (Magic.canCastSpell(AlchemistGlobal.selectedSpell)) {
			while (BankTaskAlchemy.haveItemsInInventoryForAlchemy() && !shouldStop) {
				item = Inventory.getItem(AlchemistGlobal.itemToAlch);
				if (item != null) {
					Magic.castSpell(AlchemistGlobal.selectedSpell, false);
					Mouse.click(item.getCentralPoint(), true);
				}
//				Time.sleep(2500, 3000);
			}
		}
		else {
			System.out.println("Stopping script as you have a high enough Magic level to cast the selected spell");	
			shouldStop = true;
			AlchemistGlobal.script.stop();
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null && !shouldStop) {
			if (BankTaskAlchemy.haveItemsInInventoryForAlchemy()) {
				System.out.println("Have necessary items in inventory.");
				return true;
			}
		}
		return false;
	}
	
}

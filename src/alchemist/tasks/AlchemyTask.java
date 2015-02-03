package alchemist.tasks;

import alchemist.AlchemistGlobal;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;

public class AlchemyTask extends Node implements Task {
	public boolean shouldStop = false;
	
	@Override
	public void run() {
//		Item item;
		
//		if (Magic.canCastSpell(AlchemistGlobal.selectedSpell)) {
//			while (BankTaskAlchemy.haveItemsInInventoryForAlchemy() && !shouldStop) {
//				item = Inventory.getItem(AlchemistGlobal.itemToAlchNoted);
//				if (item != null) {
//					Magic.castSpell(AlchemistGlobal.selectedSpell, false);
//					Mouse.click(item.getCentralPoint(), true);
//				}
//				try {
//					Thread.sleep(2500, 3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		else {
			System.out.println("Stopping script as you have a high enough Magic level to cast the selected spell");	
			shouldStop = true;
			AlchemistGlobal.script.stop();
//		}
	}
	
	@Override
	public boolean shouldExecute() {
//		if (Players.getLocal() != null && !shouldStop) {
//			if (BankTaskAlchemy.haveItemsInInventoryForAlchemy()) {
//				System.out.println("Have necessary items in inventory.");
//				return true;
//			}
//		}
		return false;
	}
	
}

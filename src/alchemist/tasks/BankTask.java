package alchemist.tasks;

import helpers.InventoryHelper;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;

public class BankTask extends Node implements Task {
	public int count = 0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		count ++;
		System.out.println("Need To Bank!");
	}
	
	public boolean shouldExecute() {
		//System.out.println("BankTask shouldExecute");
		if (Players.getLocal() != null && count < 1) {
			System.out.println("Count less than 1");
			if (InventoryHelper.areRunesInInventory()) {
				System.out.println("NecessaryItems are in Inventory.");
				return false;
			}
			else {
				return true;
			}
			//if (!haveItemsInInventoryForAlchemy()) {
			//	return true;
			//}
		}
		return false;
	}

}

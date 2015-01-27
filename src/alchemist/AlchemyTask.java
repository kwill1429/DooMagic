package alchemist;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.Time;

public class AlchemyTask extends Node implements Task {
	public boolean shouldStop = false;
	
	@Override
	public void run() {
		Item item;
		
		if (Magic.canCastSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY)) {
			while (AlchemistGlobal.areNecessaryItemsInInventory()) {
				System.out.println("Casting High Level Alchemy");
				Magic.castSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY, false);
			}
		}
		else if (Magic.canCastSpell(Magic.Spell.LOW_LEVEL_ALCHEMY)) {
			if (AlchemistGlobal.areNecessaryItemsInInventory()) {
				while (Inventory.contains(AlchemistGlobal.idOfItemToAlch) && !shouldStop) {
					item = Inventory.getItem(AlchemistGlobal.idOfItemToAlch);
					if (item != null) {
						Magic.castSpell(Magic.Spell.LOW_LEVEL_ALCHEMY, false);
						Mouse.click(item.getCentralPoint(), true);
					}
					Time.sleep(500, 1500);
				}			
			}
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null && !shouldStop) {
			if (AlchemistGlobal.areNecessaryItemsInInventory()) {
				System.out.println("Have necessary items in inventory.");
				return true;
			}
		}
		return false;
	}

}
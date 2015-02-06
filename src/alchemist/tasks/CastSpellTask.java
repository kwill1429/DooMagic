package alchemist.tasks;

import alchemist.AlchemistGlobal;
import alchemist.Spells;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item; 

public class CastSpellTask extends Node implements Task {
	public static boolean shouldStop = false;

	@Override
	public void run() {
		long timeStart, timeEnd, timeToSleep;
		System.out.println("Selected spell: "+ AlchemistGlobal.selectedSpell.getSpell());
		openTab(AlchemistGlobal.selectedSpell.getAbilityTab());
		if (AlchemistGlobal.selectedSpell.requiresAnItem()) {
			Item item = Inventory.getItem(AlchemistGlobal.itemToAlchNoted);
			while (Spells.areRunesForSpellInInventory() && item != null && !shouldStop) {
				Magic.castSpell(AlchemistGlobal.selectedSpell.getSpell(), false);
				timeStart = System.currentTimeMillis();
				Mouse.click(item.getCentralPoint(), true);
				timeEnd = System.currentTimeMillis();
				timeToSleep = (AlchemistGlobal.selectedSpell.getTimeToCast() - (timeEnd - timeStart));
				
				try {
					System.out.println("Time to sleep: "+ timeToSleep);
					Thread.sleep(timeToSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			while (Spells.areRunesForSpellInInventory() && !shouldStop) {
				Magic.castSpell(AlchemistGlobal.selectedSpell.getSpell(), false);
				timeStart = System.currentTimeMillis();
				timeEnd = System.currentTimeMillis();
				timeToSleep = (AlchemistGlobal.selectedSpell.getTimeToCast() - (timeEnd - timeStart));
				
				try {
					System.out.println("Time to sleep: "+ timeToSleep);
					Thread.sleep(timeToSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null && !shouldStop) {
			System.out.println("First if statement in shouldExecute");
			if (Spells.areRunesForSpellInInventory()) {
				System.out.println("Required items are in inventory");
				if (AlchemistGlobal.selectedSpell.requiresAnItem()) {
					System.out.println("Spell requires an item");
					if (Inventory.contains(AlchemistGlobal.itemToAlchNoted)) {
						return true;
					}
				}
				else {
					return true;
				}
			}
		}
		AlchemistGlobal.script.revoke(this);
		AlchemistGlobal.script.stop();
		AlchemistGlobal.script.kill();
		return false;
	}
	
	private void openTab(Magic.InnerAbilityTab tab) {
		System.out.println("Tab passed in: "+tab);
		if (!tab.isOpen()) {
			tab.open();
		}
	}
}

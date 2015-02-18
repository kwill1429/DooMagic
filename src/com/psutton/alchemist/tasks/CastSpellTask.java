package com.psutton.alchemist.tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.actionbar.abilities.inner.InnerTab;
import com.epicbot.api.rs3.wrappers.node.Item; 
import com.epicbot.api.util.Time;
import com.psutton.alchemist.AlchemistGlobal;
import com.psutton.alchemist.Helpers;

public class CastSpellTask extends Node implements Task {
	public boolean shouldStop = false;

	@Override
	public void run() {
		long timeStart, timeEnd, timeToSleep;
		Long timeToSleepActual;
		
		InnerTab.MAGIC_SKILLING_SPELLS.open();
		
		if (AlchemistGlobal.selectedSpell.requiresAnItem()) {
			Item item = Inventory.getItem(AlchemistGlobal.itemToAlchNoted);
			while (Helpers.areRunesForSpellInInventory() && item != null && !shouldStop) {
				System.out.println("Ability Tab: "+ AlchemistGlobal.selectedSpell.getAbilityTab());
				openTab(AlchemistGlobal.selectedSpell.getAbilityTab());
				Magic.castSpell(AlchemistGlobal.selectedSpell.getSpell(), false);
				Mouse.click(item.getCentralPoint(), true);
				timeStart = System.currentTimeMillis();
				timeEnd = System.currentTimeMillis();
				timeToSleep = (AlchemistGlobal.selectedSpell.getTimeToCast() - (timeEnd - timeStart));
				timeToSleepActual = new Long(timeToSleep);
				
				Time.sleep(timeToSleepActual.intValue());
			}
		}
		else {
			while (Helpers.areRunesForSpellInInventory() && !shouldStop) {
				openTab(AlchemistGlobal.selectedSpell.getAbilityTab());
				Magic.castSpell(AlchemistGlobal.selectedSpell.getSpell(), false);
				timeStart = System.currentTimeMillis();
				timeEnd = System.currentTimeMillis();
				timeToSleep = (AlchemistGlobal.selectedSpell.getTimeToCast() - (timeEnd - timeStart));
				timeToSleepActual = new Long(timeToSleep);

				Time.sleep(timeToSleepActual.intValue());
			}
		}
	}

	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null && !shouldStop) {
			if (Helpers.areRunesForSpellInInventory()) {
				if (AlchemistGlobal.selectedSpell.requiresAnItem()) {
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
		if (!tab.isOpen()) {
			System.out.println("Tab is closed, opening now");
			tab.open();
		}
	}
}

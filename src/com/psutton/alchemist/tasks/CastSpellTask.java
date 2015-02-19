package com.psutton.alchemist.tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.Time;
import com.psutton.alchemist.AlchemistGlobal;
import com.psutton.alchemist.Helpers;

public class CastSpellTask extends Node implements Task {
	public boolean shouldStop = false;
	private int timeToSleep;
	
	@Override
	public void run() {
		if (AlchemistGlobal.selectedSpell.requiresAnItem()) {
			Item item = Inventory.getItem(AlchemistGlobal.itemToAlchNoted);
			while (Helpers.areRunesForSpellInInventory() && item != null && !shouldStop) {
				openTab(AlchemistGlobal.selectedSpell.getAbilityTab());
				AlchemistGlobal.scriptStatus = "Casting "+AlchemistGlobal.selectedSpell;
				Magic.castSpell(AlchemistGlobal.selectedSpell.getSpell(), false);
				Mouse.click(item.getCentralPoint(), true);
				
				AlchemistGlobal.numOfTimesCast++;
				
				if (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted)) {
					shouldStop = true;
				}
				
				if (AlchemistGlobal.numOfCasts != -1) {
					if (AlchemistGlobal.numOfTimesCast == AlchemistGlobal.numOfCasts || Inventory.getItem(AlchemistGlobal.itemToAlchNoted) == null) {
						shouldStop = true;
					}
				}
				
				AlchemistGlobal.scriptStatus = "Sleeping";
				Time.sleep(100, 1500);
				//				Uncomment once spell tabs are fixed.
				//				timeStart = System.currentTimeMillis();
				//				timeEnd = System.currentTimeMillis();
				//				timeToSleep = (AlchemistGlobal.selectedSpell.getTimeToCast() - (timeEnd - timeStart));
				//				timeToSleepActual = new Long(timeToSleep);
				//				Time.sleep(timeToSleepActual.intValue());
			}
		}
		else {
			while (Helpers.areRunesForSpellInInventory() && !shouldStop) {
				openTab(AlchemistGlobal.selectedSpell.getAbilityTab());
				AlchemistGlobal.scriptStatus = "Casting "+AlchemistGlobal.selectedSpell;
				Magic.castSpell(AlchemistGlobal.selectedSpell.getSpell(), false);

				AlchemistGlobal.numOfTimesCast++;
				if (AlchemistGlobal.numOfCasts != -1) {
					if (AlchemistGlobal.numOfTimesCast == AlchemistGlobal.numOfCasts) {
						shouldStop = true;
					}
				}

				timeToSleep = AlchemistGlobal.selectedSpell.getTimeToCast();
				AlchemistGlobal.scriptStatus = "Sleeping";
				Time.sleep(timeToSleep,timeToSleep + 1500);
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
			tab.open();
		}
	}
}

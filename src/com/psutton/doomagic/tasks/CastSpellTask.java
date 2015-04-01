package com.psutton.doomagic.tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.Time;
import com.psutton.doomagic.DooMagicGlobal;
import com.psutton.doomagic.Helpers;

public class CastSpellTask extends Node implements Task {
	public boolean shouldStop = false;
	private int timeToSleep;

	@Override
	public void run() {
		if (DooMagicGlobal.selectedSpell.requiresAnItem()) {
			Item item = Inventory.getItem(DooMagicGlobal.itemToAlchNoted);
			while (Helpers.areRunesForSpellInInventory() && item != null && !shouldStop) {
				DooMagicGlobal.scriptStatus = "Casting Spell";
				Magic.castSpell(DooMagicGlobal.selectedSpell.getSpell(), false);
				Mouse.click(item.getCentralPoint(), true);

				DooMagicGlobal.numOfTimesCast++;

				if (!Helpers.isNotedItemInInventory(DooMagicGlobal.itemToAlchNoted)) {
					shouldStop = true;
				}

				if (DooMagicGlobal.numOfCasts != -1) {
					if (DooMagicGlobal.numOfTimesCast == DooMagicGlobal.numOfCasts || Inventory.getItem(DooMagicGlobal.itemToAlchNoted) == null) {
						shouldStop = true;
					}
				}

				DooMagicGlobal.scriptStatus = "Sleeping";
				Time.sleep(100, 1500);
			}
		}
		else {
			while (Helpers.areRunesForSpellInInventory() && !shouldStop) {
				DooMagicGlobal.scriptStatus = "Casting Spell";
				Magic.castSpell(DooMagicGlobal.selectedSpell.getSpell(), false);

				DooMagicGlobal.numOfTimesCast++;
				if (DooMagicGlobal.numOfCasts != -1) {
					if (DooMagicGlobal.numOfTimesCast == DooMagicGlobal.numOfCasts) {
						shouldStop = true;
					}
				}

				timeToSleep = DooMagicGlobal.selectedSpell.getTimeToCast();
				DooMagicGlobal.scriptStatus = "Sleeping";
				Time.sleep(timeToSleep,timeToSleep + 500);
			}
		}
	}

	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null && !shouldStop) {
			if (Helpers.areRunesForSpellInInventory()) {
				if (DooMagicGlobal.selectedSpell.requiresAnItem()) {
					if (Inventory.contains(DooMagicGlobal.itemToAlchNoted)) {
						return true;
					}
				}
				else {
					return true;
				}
			}
		}
		DooMagicGlobal.script.revoke(this);
		DooMagicGlobal.script.stop();
		DooMagicGlobal.script.kill();
		return false;
	}
}

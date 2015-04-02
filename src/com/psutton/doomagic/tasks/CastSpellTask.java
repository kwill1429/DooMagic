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
	private Item itemForSpell;

	@Override
	public void run() {
		DooMagicGlobal.scriptStatus = "Casting Spell";

		Magic.castSpell(DooMagicGlobal.selectedSpell.getSpell(), false);

		if (DooMagicGlobal.selectedSpell.requiresAnItem()) {
			Mouse.click(this.itemForSpell.getCentralPoint(), true);
			DooMagicGlobal.scriptStatus = "Sleeping";
			Time.sleep(100, 1500);
		}
		else {
			timeToSleep = DooMagicGlobal.selectedSpell.getTimeToCast();
			DooMagicGlobal.scriptStatus = "Sleeping";
			Time.sleep(timeToSleep,timeToSleep + 500);
		}

		DooMagicGlobal.numOfTimesCast++;
	}

	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null && (DooMagicGlobal.numOfCasts != DooMagicGlobal.numOfTimesCast)) {
			if (this.canCastSpell()) {
				return true;
			}
			else {
				return false;
			}	
		}
		DooMagicGlobal.script.revoke(this);
		DooMagicGlobal.script.stop();
		DooMagicGlobal.script.kill();
		return false;
	}

	private boolean canCastSpell()
	{
		boolean hasRunes = Helpers.areRunesForSpellInInventory();
		boolean hasItems = true;

		if (DooMagicGlobal.selectedSpell.requiresAnItem()) {
			this.itemForSpell = Inventory.getItem(DooMagicGlobal.itemToAlchNoted);
			if (this.itemForSpell == null) {
				hasItems = false;
			}
		}

		if (hasItems == true && hasRunes == true) {
			return true;
		}
		return false;
	}
}

package com.psutton.alchemist.tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.psutton.alchemist.AlchemistGlobal;
import com.psutton.alchemist.Helpers;
import com.psutton.utilities.objects.PSRune;

public class BankTask extends Node implements Task {
	public int count = 0;
	private PSRune[] runesNeeded;
	private PSRune rune;

	@Override
	public void run() {
		if (Bank.BankLocations.atBank()) {
			Bank.open();
			if (Bank.isOpen()) {
				Bank.depositInventory();
				Bank.setWithdrawNoted(true);
				if (!Helpers.areRunesForSpellInInventory()) {
					AlchemistGlobal.scriptStatus = "Withdrawing Runes";
					this.runesNeeded = Helpers.getRunesToWithdraw(AlchemistGlobal.selectedSpell.getRunes());
					PSRune[] runesToWithdraw = Helpers.areNecessaryRunesInBank(this.runesNeeded);

					if (runesToWithdraw == null) {
						System.out.println("Necessary Runes are NOT in bank!");
					}
					else {
						for (int i = 0; i < runesToWithdraw.length; i++) {
							rune = runesToWithdraw[i];
							Bank.waitForInputWidget(true);
							Bank.withdraw(rune.getItemID(), Bank.Amount.ALL);
						}
					}
				}
				if (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted)) {
					AlchemistGlobal.scriptStatus = "Withdrawing Items";
					if (Bank.getItem(AlchemistGlobal.itemToAlch) != null) {
						Bank.withdraw(AlchemistGlobal.itemToAlch, Bank.Amount.ALL);
					}
				}
			}
			Bank.close();
		}
		else {
			System.out.println("Not in a bank!");
			AlchemistGlobal.script.revoke(this);
		}
	}

	@Override
	public boolean shouldExecute() {
		System.out.println("BankTask shouldExecute");
		if (Players.getLocal() != null) {
			if (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted) ||
					!Helpers.areRunesForSpellInInventory()) {
				//				if (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted)) {
				//					needItem = true;
				//				}
				//				if (!Helpers.areRunesForSpellInInventory()) {
				//					needRunes = true;
				//					this.runesNeeded = Helpers.getRunesToWithdraw(AlchemistGlobal.selectedSpell.getRunes());
				//				}
				return true;
			}
		}
		System.out.println("Don't need to bank!");
		return false;
	}

}

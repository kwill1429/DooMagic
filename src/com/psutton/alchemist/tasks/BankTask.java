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
	private boolean needItems;
	private boolean needRunes;

	@Override
	public void run() {
		int numOfRune;
		int numOfItem;

		if (Bank.BankLocations.atBank()) {
			Bank.open();
			if (Bank.isOpen()) {
				Bank.depositInventory();
				Bank.setWithdrawNoted(true);
				while (!Helpers.areRunesForSpellInInventory()) {
					AlchemistGlobal.scriptStatus = "Withdrawing Runes";
					this.runesNeeded = Helpers.getRunesToWithdraw(AlchemistGlobal.selectedSpell.getRunes());
					PSRune[] runesToWithdraw = Helpers.areNecessaryRunesInBank(this.runesNeeded);

					if (runesToWithdraw == null) {
						System.out.println("Necessary Runes are NOT in bank!");
					}
					else {
						for (int i = 0; i < runesToWithdraw.length; i++) {
							rune = runesToWithdraw[i];

							numOfRune = Bank.getItemCount(true, rune.getItemID());
							Bank.waitForInputWidget(true);
							Bank.withdraw(rune.getItemID(), numOfRune);
						}
					}
				}
				while (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted)) {
					AlchemistGlobal.scriptStatus = "Withdrawing Items";
					if (Bank.getItem(AlchemistGlobal.itemToAlch) != null) {
						numOfItem = Bank.getItemCount(true, AlchemistGlobal.itemToAlch);
						Bank.waitForInputWidget(true);
						Bank.withdraw(AlchemistGlobal.itemToAlch, numOfItem);
					}
					else {
						break;
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
			if (AlchemistGlobal.selectedSpell.requiresAnItem()) {
				if (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted)) {
					needItems = true;
				}
				else {
					needItems = false;
				}
			}
			else {
				needItems = false;

				if (!Helpers.areRunesForSpellInInventory()) {
					this.runesNeeded = Helpers.getRunesToWithdraw(AlchemistGlobal.selectedSpell.getRunes());
					needRunes = true;
				}
				else {
					needRunes = false;
				}
			}

			if (needItems || needRunes) {
				return true;
			}	
		}
		System.out.println("Don't need to bank!");
		return false;
	}
}

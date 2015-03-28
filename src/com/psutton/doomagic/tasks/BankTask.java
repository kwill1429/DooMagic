package com.psutton.doomagic.tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.psutton.doomagic.DooMagicGlobal;
import com.psutton.doomagic.Helpers;
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
					DooMagicGlobal.scriptStatus = "Withdrawing Runes";
					this.runesNeeded = Helpers.getRunesToWithdraw(DooMagicGlobal.selectedSpell.getRunes());
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
				while (!Helpers.isNotedItemInInventory(DooMagicGlobal.itemToAlchNoted)) {
					DooMagicGlobal.scriptStatus = "Withdrawing Items";
					if (Bank.getItem(DooMagicGlobal.itemToAlch) != null) {
						numOfItem = Bank.getItemCount(true, DooMagicGlobal.itemToAlch);
						Bank.waitForInputWidget(true);
						Bank.withdraw(DooMagicGlobal.itemToAlch, numOfItem);
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
			DooMagicGlobal.script.revoke(this);
		}
	}

	@Override
	public boolean shouldExecute() {
		System.out.println("BankTask shouldExecute");
		if (Players.getLocal() != null) {
			if (DooMagicGlobal.selectedSpell.requiresAnItem()) {
				if (!Helpers.isNotedItemInInventory(DooMagicGlobal.itemToAlchNoted)) {
					needItems = true;
				}
				else {
					needItems = false;
				}
			}
			else {
				needItems = false;

				if (!Helpers.areRunesForSpellInInventory()) {
					this.runesNeeded = Helpers.getRunesToWithdraw(DooMagicGlobal.selectedSpell.getRunes());
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
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
	private boolean needRunes = false;
	private boolean needItem = false;
	private PSRune[] runesNeeded;
	private PSRune rune;
	
	@Override
	public void run() {
		if (Bank.BankLocations.atBank()) {
			System.out.println("At a bank already!");
			Bank.open();
			if (Bank.isOpen()) {
				Bank.setWithdrawNoted(true);
				if (needRunes) {
					System.out.println("Need runes!");
					PSRune[] runesToWithdraw = Helpers.areNecessaryRunesInBank(this.runesNeeded);
					
					if (runesToWithdraw == null) {
						System.out.println("Necessary Runes are NOT in bank!");
					}
					else {
						for (int i = 0; i < runesToWithdraw.length; i++) {
							rune = runesToWithdraw[i];
							Bank.withdraw(rune.getItemID(), Bank.Amount.ALL);
						}
					}
				}
				if (needItem) {
					System.out.println("Need item");
					if (Bank.getItem(AlchemistGlobal.itemToAlch) != null) {
						System.out.println("Withdrawing item");
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
	
	public boolean shouldExecute() {
		System.out.println("BankTask shouldExecute");
		if (Players.getLocal() != null) {
			if (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted) ||
					!Helpers.areRunesForSpellInInventory()) {
				if (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted)) {
					needItem = true;
				}
				if (!Helpers.areRunesForSpellInInventory()) {
					needRunes = true;
					this.runesNeeded = Helpers.getRunesToWithdraw(AlchemistGlobal.selectedSpell.getRunes());
				}
				return true;
			}
		}
		System.out.println("Don't need to bank!");
		return false;
	}

}

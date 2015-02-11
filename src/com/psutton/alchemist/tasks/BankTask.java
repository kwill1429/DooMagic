package com.psutton.alchemist.tasks;

import java.util.ArrayList;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.psutton.alchemist.AlchemistGlobal;
import com.psutton.alchemist.Helpers;
import com.psutton.alchemist.Spells;
import com.psutton.utilities.objects.PSRune;

public class BankTask extends Node implements Task {
	public int count = 0;
	private boolean needRunes = false;
	private boolean needItem = false;
	private PSRune[] runesNeeded;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (Bank.BankLocations.atBank()) {
			System.out.println("At a bank already!");
			Bank.open();
			if (Bank.isOpen()) {
				Bank.setWithdrawNoted(true);
				if (needItem) {
					System.out.println("Need item");
					if (Bank.getItem(AlchemistGlobal.itemToAlch) != null) {
						System.out.println("Withdrawing item");
						Bank.withdraw(AlchemistGlobal.itemToAlch, Bank.Amount.ALL);
					}
				}
				
				if (needRunes) {
					System.out.println("Need runes!");
					PSRune[] runesToWithdraw = Helpers.areNecessaryRunesInBank(this.runesNeeded);
					
					if (runesToWithdraw == null) {
						System.out.println("Necessary Runes are NOT in bank!");
					}
					else {
						for (int i = 0; i < runesToWithdraw.length; i++) {
							System.out.println("Necessary Rune #"+i+" :"+runesToWithdraw[i]);
						}
					}
				}
				
			}
		}
		else {
			System.out.println("Not in a bank!");
		}
		
		AlchemistGlobal.script.stop();
		AlchemistGlobal.script.kill();
	}
	
	public boolean shouldExecute() {
		System.out.println("BankTask shouldExecute");
		if (Players.getLocal() != null) {
			if (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted) ||
					!Spells.areRunesForSpellInInventory()) {
				if (!Helpers.isNotedItemInInventory(AlchemistGlobal.itemToAlchNoted)) {
					needItem = true;
				}
				if (!Spells.areRunesForSpellInInventory()) {
					needRunes = true;
					this.runesNeeded = Helpers.getRunesToWithdraw(AlchemistGlobal.selectedSpell.getRunes());
				}
				return true;
			}
		}
		System.out.println("Don't need to bank!");
		AlchemistGlobal.script.stop();
		AlchemistGlobal.script.kill();
		return false;
	}

}

package com.psutton.doomagic.tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.psutton.doomagic.DooMagicGlobal;
import com.psutton.doomagic.Helpers;
import com.psutton.utilities.objects.PSRune;

public class BankTask extends Node implements Task {
    private PSRune[] runesNeeded;
    private PSRune rune;

    @Override
    public void run() {
        int numToWithdraw = 0;

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
                    } else {
                        for (int i = 0; i < runesToWithdraw.length; i++) {
                            rune = runesToWithdraw[i];
                            numToWithdraw = numToWithdraw(rune.getItemID(), rune.getNumOfRunes());

                            Bank.withdraw(rune.getItemID(), numToWithdraw);
                        }
                    }
                }

                while (!Helpers.isNotedItemInInventory(DooMagicGlobal.itemToAlchNoted)) {
                    DooMagicGlobal.scriptStatus = "Withdrawing Items";

                    if (Bank.getItem(DooMagicGlobal.itemToAlch) != null) {
                        numToWithdraw = numToWithdraw(DooMagicGlobal.itemToAlch, 1);

                        Bank.withdraw(DooMagicGlobal.itemToAlch, numToWithdraw);
                    } else {
                        break;
                    }
                }
            }
            Bank.close();
        } else {
            DooMagicGlobal.script.revoke(this);
        }
    }

    @Override
    public boolean shouldExecute() {
        boolean needItems = false;
        boolean needRunes = false;

        if (Players.getLocal() != null) {
            if (DooMagicGlobal.selectedSpell.requiresAnItem()) {
                if (!Helpers.isNotedItemInInventory(DooMagicGlobal.itemToAlchNoted)) {
                    needItems = true;
                }
            }

            if (!Helpers.areRunesForSpellInInventory()) {
                this.runesNeeded = Helpers.getRunesToWithdraw(DooMagicGlobal.selectedSpell.getRunes());
                needRunes = true;
            }

            if (needItems || needRunes) {
                return true;
            }
        }

        return false;
    }

    private int numToWithdraw(int itemID, int numPerCast) {
        int numOfItemInBank = Bank.getItemCount(true, itemID);

        if (DooMagicGlobal.numOfCasts != 0) {
            if (DooMagicGlobal.numOfCasts <= numOfItemInBank) {
                return numPerCast * DooMagicGlobal.numOfCasts;
            }
        }

        return numOfItemInBank;
    }
}

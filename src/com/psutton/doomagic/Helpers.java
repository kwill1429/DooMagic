package com.psutton.doomagic;

import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.methods.widget.Bank;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.filters.IdFilter;
import com.psutton.utilities.objects.PSRune;
import com.psutton.utilities.objects.PSSpell;
import com.psutton.utilities.objects.PSStaff;

import java.util.ArrayList;

public class Helpers {
    private static IdFilter itemFilter;

    public static PSRune[] areNecessaryRunesInBank(PSRune[] runes) {
        int numOfDifferentRunesNeeded = runes.length;
        PSRune[] associatedRunes;
        PSRune rune, associatedRune;
        PSRune[] runesToWithdraw = null;
        ArrayList<PSRune> runesToWithdrawList = new ArrayList<PSRune>(numOfDifferentRunesNeeded);

        for (int i = 0; i < runes.length; i++) {
            rune = runes[i];

            if (isRuneInBank(rune)) {
                runesToWithdrawList.add(rune);
                System.out.println(rune+" is in bank!");
            }
            else {
                associatedRunes = rune.getAssociatedRunes();

                if (associatedRunes != null) {
                    for (int j = 0; j < associatedRunes.length; j++) {
                        associatedRune = associatedRunes[j];

                        if (isRuneInBank(associatedRune)) {
                            runesToWithdrawList.add(associatedRune);
                            break;
                        }
                    }
                }
            }
        }

        runesToWithdrawList.trimToSize();

        if (runesToWithdrawList.size() == numOfDifferentRunesNeeded) {
            int index = 0;
            runesToWithdraw = new PSRune[numOfDifferentRunesNeeded];

            for (PSRune runeToWithdraw : runesToWithdrawList) {
                runesToWithdraw[index] = runeToWithdraw;
                index ++;
            }
        }

        return runesToWithdraw;
    }


    public static boolean isRuneInBank(PSRune rune) {
        itemFilter = new IdFilter(true, rune.getItemID());
        if (Bank.getItemCount(true, itemFilter) >= rune.getNumOfRunes()) {
            return true;
        }

        return false;
    }

    public static boolean isNotedItemInInventory(int itemID) {
        itemFilter = new IdFilter(true, itemID);

        if (Inventory.contains(itemFilter)) {
            System.out.println("Is In inventory");
            return true;
        }
        System.out.println("Is not in inventory");
        return false;
    }

    public static PSRune[] getRunesToWithdraw(PSRune[] runes) {
        ArrayList<PSRune> runesToWithdrawList = new ArrayList<PSRune>();
        PSRune rune;
        PSRune[] runesToWithdraw = null;
        int index = 0;

        for (int i = 0; i < runes.length; i++) {
            rune = runes[i];
            if (!meetsRuneRequirement(rune)) {
                runesToWithdrawList.add(rune);
            }
        }

        runesToWithdrawList.trimToSize();

        if (!runesToWithdrawList.isEmpty()) {
            runesToWithdraw = new PSRune[runesToWithdrawList.size()];
            for (PSRune runeToWithdraw : runesToWithdrawList) {
                runesToWithdraw[index] = runeToWithdraw;
                index ++;
            }
        }

        return runesToWithdraw;
    }

    public static boolean areRunesForSpellInInventory() {
        PSSpell spell = DooMagicGlobal.selectedSpell;
        PSRune rune;
        PSRune[] requiredRunes = spell.getRunes();

        for (int i = 0; i < requiredRunes.length; i++) {
            rune = requiredRunes[i];

            if (!meetsRuneRequirement(rune)) {
                System.out.println("do not have enough of: " + rune.getItemName());
                return false;
            }
        }

        return true;
    }

    public static boolean isValidStaffEquipped(PSStaff[] staves) {

        for (int i = 0; i < staves.length; i++) {
            itemFilter = new IdFilter(true, staves[i].getItemID());
            if (Equipment.containsOneOf(itemFilter)) {
                return true;
            }
        }
        return false;
    }

    public static void loadItemToUse() {
        Item itemToAlch;

        if (Inventory.getCount() == 1) {
            itemToAlch = Inventory.getItemAt(0);
            if (itemToAlch != null) {
                DooMagicGlobal.itemToAlch = itemToAlch.getID();
                if (Helpers.isItemNoteable(itemToAlch)) {
                    DooMagicGlobal.itemToAlchNoted = DooMagicGlobal.itemToAlch + 1;
                } else {
                    DooMagicGlobal.itemToAlchNoted = DooMagicGlobal.itemToAlch;
                }
            }
        } else {
            System.out.println("Inventory is setup incorrectly to detect item to use.");
            DooMagicGlobal.script.stop();
        }
    }

    public static boolean isItemNoteable(Item item) {
        if (item.getStackSize() > 1) {
            return false;
        }

        return true;
    }

    private static boolean meetsRuneRequirement(PSRune rune) {
        int runeID, numOfRunes;
        PSRune[] relatedRunes;
        PSStaff[] staves;

        runeID = rune.getItemID();
        numOfRunes = rune.getNumOfRunes();
        relatedRunes = rune.getAssociatedRunes();
        staves = rune.getAssociatedStaves();

        if (staves != null) {
            if (isValidStaffEquipped(staves)) {
                return true;
            }
        }


        if (hasEnoughOfRune(runeID, numOfRunes)) {
            return true;
        } else {
            if (relatedRunes != null) {
                for (int i = 0; i < relatedRunes.length; i++) {
                    runeID = relatedRunes[i].getItemID();
                    if (hasEnoughOfRune(runeID, numOfRunes)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean hasEnoughOfRune(int runeID, int numOfRunes) {
        itemFilter = new IdFilter(true, runeID);

        if (Inventory.contains(itemFilter)) {
            if (Inventory.getCount(true, itemFilter) >= numOfRunes) {
                return true;
            }
        }

        return false;
    }
}

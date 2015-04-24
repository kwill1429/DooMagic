package com.psutton.doomagic.tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.Tabs;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;
import com.epicbot.api.util.Time;
import com.psutton.doomagic.DooMagicGlobal;
import com.psutton.doomagic.Helpers;
import com.psutton.utilities.helpers.MagicHelper;

public class CastSpellTask extends Node implements Task {
    private boolean openedAbilityTab;
    private int timeToSleep;
    private Item itemForSpell;

    @Override
    public void run() {
        DooMagicGlobal.scriptStatus = "Casting Spell";

        castSpell();

        timeToSleep = DooMagicGlobal.selectedSpell.getTimeToCast();

        if (DooMagicGlobal.selectedSpell.requiresAnItem()) {
            Mouse.click(this.itemForSpell.getCentralPoint(), true);
        }

        DooMagicGlobal.scriptStatus = "Sleeping";
        DooMagicGlobal.numOfTimesCast++;

        Time.sleep(timeToSleep,timeToSleep + 500);
    }

    @Override
    public boolean shouldExecute() {
        if (Players.getLocal() != null && (DooMagicGlobal.numOfCasts != DooMagicGlobal.numOfTimesCast)) {
            if (this.canCastSpell()) {
                return true;
            } else {
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

    private void castSpell() {
        int x = MagicHelper.centerX(DooMagicGlobal.selectedSpell.getSpellPosition());
        int y = MagicHelper.centerY(DooMagicGlobal.selectedSpell.getSpellPosition());

        if (!Tabs.MAGIC_ABILITIES.isOpen()) {
            Tabs.MAGIC_ABILITIES.open();
        }

        if (!openedAbilityTab) {
            DooMagicGlobal.selectedSpell.getAbilityTab().open();
            openedAbilityTab = true;
        }

        Mouse.click(x, y, true);
    }
}

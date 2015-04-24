package com.psutton.doomagic.tasks;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.rs3.methods.widget.Camera;
import com.epicbot.api.rs3.methods.widget.actionbar.abilities.inner.GeneralTab;
import com.epicbot.api.util.Time;
import com.psutton.doomagic.DooMagicGlobal;

import java.util.Random;

public class AntiBanTask extends Node implements Task {
    private int antiBanCounter;

    @Override
    public void run() {
        boolean didHitAntiBan = true;
        int angle, distance;
        Random random;

        this.antiBanCounter = DooMagicGlobal.antiBanCounter;

        DooMagicGlobal.scriptStatus = "Performing AntiBan";

        if (antiBanCounter % 9 == 0) {
            Skills.Skill.MAGIC.hover(5000);
        } else if (antiBanCounter % 8 == 0) {
            random = new Random();
            angle = random.nextInt(340) + 20;
            Camera.setAngle(angle);
            Time.sleep(750, 1250);
            angle = random.nextInt(340) + 20;
            Camera.setAngle(angle);
            Time.sleep(2000, 7000);
        } else if (antiBanCounter % 7 == 0) {
            random = new Random();
            distance = random.nextInt(150) + 100;
            Mouse.moveRandomly(distance, distance + 200);
            Time.sleep(500, 1000);
            Mouse.moveRandomly(distance, distance + 200);
            Time.sleep(1500, 6500);
        } else if (antiBanCounter % 6 == 0) {
            GeneralTab.MELEE.open();
            Time.sleep(1000, 1500);
            GeneralTab.OTHER.open();
            Time.sleep(1250, 6250);
        } else if (antiBanCounter % 5 == 0) {
            Time.sleep(2250, 7250);
        } else {
            DooMagicGlobal.antiBanCounter ++;
        }

        if (didHitAntiBan == true) {
            AntiBanTask.generateAntiBanCounter();
        }
    }

    @Override
    public boolean shouldExecute() {
        if (Players.getLocal() != null && (DooMagicGlobal.numOfTimesCast == DooMagicGlobal.antiBanCounter)) {
            return true;
        }

        return false;
    }

    public static void generateAntiBanCounter() {
        Random random = new Random();
        DooMagicGlobal.antiBanCounter += random.nextInt(100) + 50;
    }
}

package com.psutton.utilities.helpers;

import com.epicbot.api.rs3.methods.Calculations;
import com.epicbot.api.rs3.methods.Game;

public class MagicHelper {

    public static int centerX(int spellBookPosition) {
        int increment = 40; // Width of spell + horizontal spacer
        int baseX, leftX, rightX, centerX;

        if (isBigScreen()) {
            baseX = 600 + (Game.getWidth() - 800);
        } else {
            baseX = 546 + (Game.getWidth() - 765);
        }

        if ((spellBookPosition + 4) % 5 == 0) {
            leftX = baseX;
        } else if ((spellBookPosition + 3) % 5 == 0) {
            leftX = baseX + increment;
        } else if ((spellBookPosition + 2) % 5 == 0) {
            leftX = baseX + (2 * increment);
        } else if ((spellBookPosition + 1) % 5 == 0) {
            leftX = baseX + (3 * increment);
        } else {
            leftX = baseX + (4 * increment);
        }

        rightX = leftX + 29;
        centerX = (leftX + rightX) / 2;

        return (centerX);
    }

    public static int centerY(int spellBookPosition) {
        int increment = 34; // Height of spell + vertical spacer
        int topY = 0;
        int baseY, bottomY, centerY;

        if (isBigScreen()) {
            baseY = 208 + (Game.getHeight() - 540);
        } else {
            baseY = 249 + (Game.getHeight() - 540);
        }

        if (spellBookPosition < 6) {
            topY = baseY;
        } else if (spellBookPosition < 11) {
            topY = baseY + increment;
        } else if (spellBookPosition < 16) {
            topY = baseY + (2 * increment);
        } else if (spellBookPosition < 21) {
            topY = baseY + (3 * increment);
        } else if (spellBookPosition < 26) {
            topY = baseY + (4 * increment);
        } else if (spellBookPosition < 31) {
            topY = baseY + (5 * increment);
        } else if (spellBookPosition < 36) {
            topY = baseY + (6 * increment);
        }

        bottomY = topY + 29;
        centerY = (topY + bottomY) / 2;

        return (centerY);
    }

    public static boolean isBigScreen() {
        Calculations.OnScreenManager onScreenManager = Calculations.OnScreenManager.instance();

        if (onScreenManager.onCanvas(800, 100)) {
            return true;
        }

        return false;
    }
}

package com.psutton.utilities.helpers;

import com.epicbot.api.rs3.methods.Calculations;

public class MagicHelper {
    private Calculations.OnScreenManager onScreenManager;

    public static int getAdjustedXCoord(int x) {
        int offset;

        if (isBigScreen() == true) {
            offset = 200;
        } else {
            offset = 219;
        }

        return (x - offset);
    }

    public static int getAdjustedYCoord(int y) {
        int offset;

        if (isBigScreen()) {
            offset = 332;
        } else {
            offset = 309;
        }

        return (y - offset);
    }

    private static boolean isBigScreen() {
        Calculations.OnScreenManager onScreenManager = Calculations.OnScreenManager.instance();

        if (onScreenManager.onCanvas(801, 100) == false) {
            return false;
        }

        return true;
    }
}

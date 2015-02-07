package com.psutton.utilities;

import com.psutton.alchemist.AlchemistGlobal;

public class MagicHelper {
	
	public static int[] getRelatedRunes(int runeID) {
		if (runeID == AlchemistGlobal.runeFire) {
			int[] relatedRunes = { AlchemistGlobal.runeSteam, AlchemistGlobal.runeLava };
			return relatedRunes;
		}
		return null;
	}
	
	public static int[] getStavesForRune(int runeID) {
		if (runeID == AlchemistGlobal.runeFire) {
			int[] staves = { AlchemistGlobal.staffFire, AlchemistGlobal.staffSteam, AlchemistGlobal.staffLava };
			return staves;
		}
		return null;
	}
}

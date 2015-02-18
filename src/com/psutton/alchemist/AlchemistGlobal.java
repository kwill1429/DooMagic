package com.psutton.alchemist;

import java.util.HashMap;
import com.epicbot.api.ActiveScript;
import com.epicbot.api.util.SkillData;
import com.epicbot.api.util.Time;
import com.psutton.utilities.objects.PSSpell;

public class AlchemistGlobal {
	public static AlchemistFrame frame;
	public static ActiveScript script;
	public static int numOfCasts;
	public static int xpGained, lvlsGained;
	public static int itemToAlch = 62;
	public static int itemToAlchNoted = itemToAlch + 1;
	public static long timeStart, timeEnd, runtimeLong;
	public static String runtimeString, xpPerHour;
	public static int[] stavesAlchemy = {
		3053, 11736, 1387
	};
	public static String[] spellList;
	public static HashMap<String, PSSpell> availableSpells;
	public static PSSpell selectedSpell;

	
	public static void retrieveSessionStats() {
		xpGained = SkillData.MAGIC.getXpGained();
		lvlsGained = SkillData.MAGIC.getLevelsGained();
		timeEnd = System.currentTimeMillis();
		runtimeLong = timeEnd - timeStart;
		runtimeString = Time.format(runtimeLong);
		xpPerHour = Time.getPerHour(xpGained, timeStart);
		System.out.println("Runtime: "+ runtimeString);
		System.out.println("Xp/hr: "+ xpPerHour);
	}
}

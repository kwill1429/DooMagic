package com.psutton.alchemist;

import java.util.HashMap;

import javax.swing.JFrame;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.util.SkillData;
import com.epicbot.api.util.Time;
import com.psutton.utilities.objects.PSSpell;

public class AlchemistGlobal {
	public static JFrame frame;
	public static ActiveScript script;
	public static int numOfCasts;
	public static int xpGained, lvlsGained;
	public static int staffLava = 3053;
	public static int staffSteam = 11736;
	public static int staffFire = 1387;
	public static int itemToAlch = 62;
	public static int itemToAlchNoted = itemToAlch + 1;
	public static int runeFire = 554;
	public static int runeNature = 561;
	public static int runeSteam = 4694;
	public static int runeLava = 4699;
	public static long timeStart, timeEnd, runtimeLong;
	public static String runtimeString, xpPerHour;
	public static int[] runesAlchemy = {
		AlchemistGlobal.runeNature, AlchemistGlobal.runeFire
	};
	public static int[] stavesAlchemy = {
		3053, 11736, 1387
	};
	
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

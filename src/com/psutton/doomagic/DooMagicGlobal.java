package com.psutton.doomagic;

import java.util.HashMap;

import com.epicbot.api.ActiveScript;
import com.psutton.utilities.objects.PSSpell;

public class DooMagicGlobal {
	public static int numOfCasts, antiBanCounter;
	public static int numOfTimesCast = 0;
	public static int itemToAlch, itemToAlchNoted;
	public static int startingLvl, startingXP;
	public static long startTime;
	public final static String scriptVersion = "v1.30";
	public final static String scriptName = "DooMagic";
	public static String scriptStatus = "Starting Up";
	public static DooMagicGUI frame;
	public static ActiveScript script;
	public static String[] spellList;
	public static HashMap<String, PSSpell> availableSpells;
	public static PSSpell selectedSpell;
}

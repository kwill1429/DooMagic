package com.psutton.doomagic;

import java.util.HashMap;

import com.epicbot.api.ActiveScript;
import com.psutton.utilities.objects.PSSpell;

public class DooMagicGlobal {
	public static int numOfCasts;
	public static int numOfTimesCast = 0;
	public static int xpGained, lvlsGained;
	public static int itemToAlch, itemToAlchNoted;
	public static String scriptVersion = "v1.21";
	public static String scriptName = "DooMagic " + scriptVersion;
	public static String scriptStatus = "Starting Up";
	public static DooMagicFrame frame;
	public static ActiveScript script;
	public static String[] spellList;
	public static HashMap<String, PSSpell> availableSpells;
	public static PSSpell selectedSpell;
}
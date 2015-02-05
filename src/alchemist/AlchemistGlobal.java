package alchemist;

import javax.swing.JFrame;

import utilities.objects.PSSpell;
import alchemist.spells.alchemy.HighAlchemySpell;
import alchemist.spells.alchemy.LowAlchemySpell;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.util.SkillData;
import com.epicbot.api.util.Time;

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
	
	public static PSSpell[] spells;
	public static PSSpell selectedSpell;
//	public static Magic.Spell[] spells = {
//		 Magic.Spell.LOW_LEVEL_ALCHEMY, Magic.Spell.HIGH_LEVEL_ALCHEMY 
//	};
//	public static Magic.Spell selectedSpell = spells[0];


	public static void setupAvailableSpells() {
		spells = new PSSpell[2];
		PSSpell lowAlchemy = new LowAlchemySpell();
		PSSpell highAlchemy = new HighAlchemySpell();
		spells[0] = lowAlchemy;
		spells[1] = highAlchemy;
		selectedSpell = spells[1];
	}
	
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
	
//	public static boolean isStaffEquipped() {
//		if (Equipment.containsOneOf(staffFire) || Equipment.containsOneOf(staffLava) || Equipment.containsOneOf(staffSteam) ) {
//			return true;
//		}
//		return false;
//	}
//	
//	public static boolean areNecessaryItemsInInventory() {
//		if (Inventory.contains(runeNature) && Inventory.contains(itemToAlchNoted)) {
//			return true;
//		}
//		return false;
//	}
}

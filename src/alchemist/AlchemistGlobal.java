package alchemist;


import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.util.SkillData;
import com.epicbot.api.util.Time;

public class AlchemistGlobal {
	private static int startingXP, endingXP;
	private static int startingLvl, endingLvl;
	private static int xpGained, lvlsGained;	
	private static int idLavaStaff = 3053;
	private static int idSteamStaff = 11736;
	private static int idFireStaff = 1387;
	public static int idNatureRune = 561;
	public static int idFireRune = 554;
	public static int idOfItemToAlch = 62;
	public static long startingTime, endingTime, runtimeLong;
	public static String runtimeString, xpPerHour;
	
	public static void recordInitialStats() {
		if (Players.getLocal() != null) {
			startingXP = Skills.Skill.MAGIC.getExperience();
			startingLvl = Skills.Skill.MAGIC.getRealLevel();
			System.out.println("Starting XP: "+ startingXP);
			System.out.println("Starting Level: "+ startingLvl);
		}
	}
	
	public static void recordEndingStats() {
		if (Players.getLocal() != null) {
			endingXP = Skills.Skill.MAGIC.getExperience();
			endingLvl = Skills.Skill.MAGIC.getRealLevel();
		}
	}
	
	public static void calculateFinalStats() {
		xpGained = endingXP - startingXP;
		lvlsGained = startingLvl - endingLvl;
		System.out.println("Xp Gained: "+xpGained);
		System.out.println("Levels Gained: "+lvlsGained);
	}
	
	public static void retrieveSessionStats() {
		xpGained = SkillData.MAGIC.getXpGained();
		lvlsGained = SkillData.MAGIC.getLevelsGained();
		endingTime = System.currentTimeMillis();
		runtimeLong = endingTime - startingTime;
		runtimeString = Time.format(runtimeLong);
		xpPerHour = Time.getPerHour(xpGained, startingTime);
		System.out.println("Runtime: "+ runtimeString);
		System.out.println("Xp/hr: "+ xpPerHour);
	}
	
	public static boolean isStaffEquipped() {
		if (Equipment.containsOneOf(idFireStaff) || Equipment.containsOneOf(idLavaStaff) || Equipment.containsOneOf(idSteamStaff) ) {
			return true;
		}
		return false;
	}
	
	public static boolean areNecessaryItemsInInventory() {
		if (Inventory.contains(idNatureRune) && Inventory.contains(idOfItemToAlch)) {
			return true;
		}
		return false;
	}
}
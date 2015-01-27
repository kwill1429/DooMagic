import java.awt.Graphics2D;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.event.listeners.PaintListener;
	@Manifest(game=GameType.RS3, name="FreeAlchemist", author="Doomboy5888", description="Handles all your alchemy needs", version=0.5)

public class FreeAlchemist extends ActiveScript implements PaintListener 
{
	private static int startingXP, endingXP;
	private static int startingLvl, endingLvl;
	private static int xpGained, lvlsGained;
	public static int idOfItemToAlch = 62;	
	private static int idLavaStaff = 3053;
	private static int idSteamStaff = 11736;
	private static int idFireStaff = 1387;
		
	@Override
	public boolean onStart() {
		recordInitialStats();
		provide(new BankTask());
		provide(new CastAlchemyTask());
		return true;
	}
	
	@Override
	public void onStop() {
		recordEndingStats();
		calculateFinalStats();
	}
	

	@Override
	public void onRepaint(Graphics2D arg0) {
		// TODO Auto-generated method stub
	}
	
	public void recordInitialStats() {
		if (Players.getLocal() != null) {
			startingXP = Skills.Skill.MAGIC.getExperience();
			startingLvl = Skills.Skill.MAGIC.getRealLevel();
			System.out.println("Starting XP: "+ startingXP);
			System.out.println("Starting Level: "+ startingLvl);
		}
	}
	
	public void recordEndingStats() {
		if (Players.getLocal() != null) {
			endingXP = Skills.Skill.MAGIC.getExperience();
			endingLvl = Skills.Skill.MAGIC.getRealLevel();
		}
	}
	
	public void calculateFinalStats() {
		xpGained = startingXP - endingXP;
		lvlsGained = startingLvl - endingLvl;
		System.out.println("Xp Gained: "+xpGained);
		System.out.println("Levels Gained: "+lvlsGained);
	}
	
	public static boolean isStaffEquipped() {
		if (Equipment.containsOneOf(idFireStaff) || Equipment.containsOneOf(idLavaStaff) || Equipment.containsOneOf(idSteamStaff) ) {
			return true;
		}
		return false;
	}
	
	public static boolean areNecessaryItemsInInventory() {
		if (Inventory.contains(BankTask.idNatureRune) && Inventory.contains(FreeAlchemist.idOfItemToAlch)) {
			return true;
		}
		return false;
	}

}

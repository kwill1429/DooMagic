import java.awt.Graphics2D;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.util.paint.Paint;
import com.epicbot.event.listeners.PaintListener;
	@Manifest(game=GameType.RS3, name="FreeAlchemist", author="Doomboy5888", description="Handles all your alchemy needs")

public class FreeAlchemist extends ActiveScript implements PaintListener 
{
	public static int idOfItemToAlch = 62;	
	private static int idLavaStaff = 3053;
	private static int idSteamStaff = 11736;
	private static int idFireStaff = 1387;
		
	@Override
	public boolean onStart() {
		provide(new BankTask());
		provide(new CastAlchemyTask());
		return true;
	}

	@Override
	public void onRepaint(Graphics2D arg0) {
		// TODO Auto-generated method stub
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

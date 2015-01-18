import java.awt.Graphics2D;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.event.listeners.PaintListener;
	@Manifest(game=GameType.RS3, name="FreeAlchemist", author="Doomboy5888", description="Handles all your alchemy needs")

public class FreeAlchemist extends ActiveScript implements PaintListener 
{
	public static int idOfItemToAlch = 62;	
	
		
	@Override
	public boolean onStart() {
		provide(new BankTask());
		return true;
	}

	@Override
	public void onRepaint(Graphics2D arg0) {
		// TODO Auto-generated method stub
	}
	
	public static boolean isStaffEquipped() {
		if (Equipment.containsOneOf(1387)) {
			return true;
		}
		return false;
	}
	
	public static boolean areNecessaryItemsInInventory() {
		if (Inventory.contains(561) && Inventory.contains(FreeAlchemist.idOfItemToAlch)) {
			return true;
		}
		return false;
	}

}

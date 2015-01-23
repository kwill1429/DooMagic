import java.awt.Graphics;
import java.awt.Graphics2D;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.Game;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;


public class CastAlchemyTask extends Node implements Task {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (Magic.canCastSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY)) {
			while (FreeAlchemist.areNecessaryItemsInInventory()) {
				System.out.println("Casting High Level Alchemy");
				Magic.castSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY, false);
			}
		}
		else if (Magic.canCastSpell(Magic.Spell.LOW_LEVEL_ALCHEMY)) {
			int count = 0;
			//while (FreeAlchemist.areNecessaryItemsInInventory()) {
			while (count == 0) {
				System.out.println("Casting Low Level Alchemy");
				//Magic.castSpell(Magic.Spell.LOW_LEVEL_ALCHEMY, false);
				int height = Game.getHeight();
				int width = Game.getWidth();
				System.out.println("Width x Height: "+width+" x "+height);
				// Mouse.click(752, 304, true);
				try {
					Thread.sleep(15000);
					count++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null) {
			if (FreeAlchemist.areNecessaryItemsInInventory()) {
				System.out.println("Have necessary items in inventory.");
				return true;
			}
		}
		return false;
	}

}

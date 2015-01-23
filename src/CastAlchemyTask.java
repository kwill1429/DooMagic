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
		if (Magic.canCastSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY)) {
			while (FreeAlchemist.areNecessaryItemsInInventory()) {
				System.out.println("Casting High Level Alchemy");
				Magic.castSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY, false);
			}
		}
		else if (Magic.canCastSpell(Magic.Spell.LOW_LEVEL_ALCHEMY)) {
			int canvasHeight = Game.getHeight();
			int canvasWidth = Game.getWidth();
			int x, y;
			
			System.out.println("Width x Height: "+canvasWidth+" x "+canvasHeight);
	
			if (FreeAlchemist.areNecessaryItemsInInventory()) {
				for (int i = 0; i < 29; i++) {
					if (Inventory.getItemAt(i) != null) {
						if (Inventory.getItemAt(i).getID() == FreeAlchemist.idOfItemToAlch) {
							Magic.castSpell(Magic.Spell.LOW_LEVEL_ALCHEMY, false);
							x = Inventory.getItemAt(i).getCentralPoint().x;
							y = Inventory.getItemAt(i).getCentralPoint().y;
							try {
								Thread.sleep(500);
								Mouse.click(x, y, true);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
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


import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.input.Mouse;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.epicbot.api.rs3.wrappers.node.Item;


public class CastAlchemyTask extends Node implements Task {

	@Override
	public void run() {
		Item item;
		
		if (Magic.canCastSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY)) {
			while (FreeAlchemist.areNecessaryItemsInInventory()) {
				System.out.println("Casting High Level Alchemy");
				Magic.castSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY, false);
			}
		}
		else if (Magic.canCastSpell(Magic.Spell.LOW_LEVEL_ALCHEMY)) {
			if (FreeAlchemist.areNecessaryItemsInInventory()) {
				for (int i = 0; i < 28; i++) {
					item = Inventory.getItemAt(i);
					if (item != null) {
						if (item.getID() == FreeAlchemist.idOfItemToAlch) {
							Magic.castSpell(Magic.Spell.LOW_LEVEL_ALCHEMY, false);
							
							try {
								Thread.sleep(1000);
								Mouse.click(item.getCentralPoint(), true);
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


import java.awt.Graphics2D;

import alchemist.AlchemistGlobal;
import alchemist.tasks.BankTask;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.event.listeners.PaintListener;
//import alchemist.AlchemyTask;
	@Manifest(game=GameType.RS3, name="TheAlchemist", author="Doomboy5888", description="Handles all your alchemy, teleporting and superheating needs", version=0.5)

public class TheAlchemist extends ActiveScript implements PaintListener
{
	private BankTask bankTask;
		
	@Override
	public boolean onStart() {
		AlchemistGlobal.script = this;
		this.setName("The Alchemist");
		AlchemistGlobal.timeStart = getStartTime();
		System.out.println("Start Time: "+ AlchemistGlobal.timeStart);
		AlchemistGlobal.setupAvailableSpells();
		AlchemistGlobal.selectedSpell = AlchemistGlobal.spells[0];
		bankTask = new BankTask();
		provide(bankTask);
		return true;
	}
	
	@Override
	public void onStop() {
	//	alchemyTask.shouldStop = true;
	//	revoke(alchemyTask);
		revoke(bankTask);
	//	terminated(alchemyTask);
		terminated(bankTask);
		AlchemistGlobal.retrieveSessionStats();
	}
	
	@Override
	public void onRepaint(Graphics2D arg0) {
		// TODO Auto-generated method stub
	}
}


import java.awt.Graphics2D;
import alchemist.AlchemistGlobal;
import alchemist.BankTaskAlchemy;
import alchemist.AlchemyTask;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.event.listeners.PaintListener;
	@Manifest(game=GameType.RS3, name="TheAlchemist", author="Doomboy5888", description="Handles all your alchemy, teleporting and superheating needs", version=0.5)

public class TheAlchemist extends ActiveScript implements PaintListener
{
	private BankTaskAlchemy bankTask;
	private AlchemyTask alchemyTask;
		
	@Override
	public boolean onStart() {
		AlchemistGlobal.script = this;
		this.setName("The Alchemist");
		//setupOptionPanel();
		AlchemistGlobal.timeStart = getStartTime();
		System.out.println("Start Time: "+ AlchemistGlobal.timeStart);
		bankTask = new BankTaskAlchemy();
		alchemyTask = new AlchemyTask();
		provide(bankTask);
		provide(alchemyTask);
		return true;
	}
	
	@Override
	public void onStop() {
		alchemyTask.shouldStop = true;
		revoke(alchemyTask);
		revoke(bankTask);
		terminated(alchemyTask);
		terminated(bankTask);
		AlchemistGlobal.retrieveSessionStats();
	}
	
	@Override
	public void onRepaint(Graphics2D arg0) {
		// TODO Auto-generated method stub
	}
}

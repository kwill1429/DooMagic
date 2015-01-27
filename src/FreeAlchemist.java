
import java.awt.Graphics2D;

import alchemist.AlchemistGlobal;
import alchemist.BankTask;
import alchemist.CastAlchemyTask;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.event.listeners.PaintListener;
	@Manifest(game=GameType.RS3, name="TheAlchemist", author="Doomboy5888", description="Handles all your alchemy, teleporting and superheating needs", version=0.5)

public class FreeAlchemist extends ActiveScript implements PaintListener 
{
		
	@Override
	public boolean onStart() {
		AlchemistGlobal.recordInitialStats();
		provide(new BankTask());
		provide(new CastAlchemyTask());
		return true;
	}
	
	@Override
	public void onStop() {
		AlchemistGlobal.recordEndingStats();
		AlchemistGlobal.calculateFinalStats();
	}
	

	@Override
	public void onRepaint(Graphics2D arg0) {
		// TODO Auto-generated method stub
	}
}

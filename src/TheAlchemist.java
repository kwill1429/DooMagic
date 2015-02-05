
import java.awt.Graphics2D;

import alchemist.AlchemistGlobal;
import alchemist.Spells;
import alchemist.tasks.BankTask;
import alchemist.tasks.CastSpellTask;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.event.listeners.PaintListener;
//import alchemist.AlchemyTask;
	@Manifest(game=GameType.RS3, name="TheAlchemist", author="Doomboy5888", description="Handles all your alchemy, teleporting and superheating needs", version=0.5)

public class TheAlchemist extends ActiveScript implements PaintListener
{
	private BankTask bankTask;
	private CastSpellTask castSpellTask;
		
	@Override
	public boolean onStart() {
		AlchemistGlobal.script = this;
		this.setName("The Alchemist");
		AlchemistGlobal.timeStart = getStartTime();
		System.out.println("Start Time: "+ AlchemistGlobal.timeStart);
		AlchemistGlobal.availableSpells = new Spells().getSpells();
		AlchemistGlobal.selectedSpell = AlchemistGlobal.availableSpells.get("Low Alchemy");
		
		if (!Magic.canCastSpell(AlchemistGlobal.selectedSpell.getSpell())) {
			System.out.println("Stopping Script - Necessary Magic level requirement for selected spell not met");
			return false;
		}
		
		castSpellTask = new CastSpellTask();
		provide(castSpellTask);
		
		//bankTask = new BankTask();
		//provide(bankTask);
		//return true;
		return true;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void onStop() {
		castSpellTask.shouldStop = true;
		revoke(castSpellTask);
	//	revoke(bankTask);
	//	terminated(alchemyTask);
		terminated(bankTask);
		AlchemistGlobal.retrieveSessionStats();
		this.stop();
		this.kill();
	}
	
	@Override
	public void onRepaint(Graphics2D arg0) {
		// TODO Auto-generated method stub
	}
}


import java.awt.EventQueue;
import java.awt.Graphics2D;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.util.SkillData;
import com.epicbot.api.util.paint.Paint;
import com.epicbot.event.listeners.PaintListener;
import com.psutton.alchemist.AlchemistFrame;
import com.psutton.alchemist.AlchemistGlobal;
import com.psutton.alchemist.Spells;
import com.psutton.alchemist.tasks.BankTask;
import com.psutton.alchemist.tasks.CastSpellTask;
	@Manifest(game=GameType.RS3, name="TheAlchemist", author="Doomboy5888", description="Handles all your alchemy, teleporting and superheating needs", version=0.5)

public class TheAlchemist extends ActiveScript implements PaintListener
{
	private BankTask bankTask;
	private CastSpellTask castSpellTask;
	private int startingLvl;
		
	@Override
	public boolean onStart() {
		AlchemistGlobal.script = this;
		this.setName("The Alchemist");
		AlchemistGlobal.timeStart = getStartTime();
		System.out.println("Start Time: "+ AlchemistGlobal.timeStart);
		Spells spells = new Spells();
		startingLvl = Skills.Skill.MAGIC.getCurrentLevel();
		
		AlchemistGlobal.availableSpells = spells.getSpells();
		AlchemistGlobal.spellList = spells.getSpellList();
		AlchemistGlobal.selectedSpell = AlchemistGlobal.availableSpells.get("Low Alchemy");
		
		if (!Magic.canCastSpell(AlchemistGlobal.selectedSpell.getSpell())) {
			System.out.println("Stopping Script - Necessary Magic level requirement for selected spell not met");
			return false;
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlchemistFrame frame = new AlchemistFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		bankTask = new BankTask();
//		provide(bankTask);
//		castSpellTask = new CastSpellTask();
//		provide(castSpellTask);
		
//		return true;
		return false;
	}
	
	@Override
	public void onStop() {
		castSpellTask.shouldStop = true;
		revoke(castSpellTask);
		revoke(bankTask);
		terminated(castSpellTask);
		terminated(bankTask);
		AlchemistGlobal.retrieveSessionStats();
		this.stop();
		this.kill();
	}
	
	@Override
	public void onRepaint(Graphics2D g) {
		int lvlsGained = Skills.Skill.MAGIC.getCurrentLevel() - this.startingLvl;
		String lvlString = "Magic Level: "+Skills.Skill.MAGIC.getCurrentLevel()+"("+lvlsGained+")";
		String xpString = "XP Gained: "+SkillData.MAGIC.getXpGained();
		Paint.drawLine(g, 0, lvlString);
		Paint.drawLine(g, 1, xpString);
		// TODO Auto-generated method stub
	}
}

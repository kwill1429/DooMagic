
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.util.SkillData;
import com.epicbot.api.util.Time;
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
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlchemistFrame frame = new AlchemistFrame();
					frame.setVisible(true);
					
					frame.addWindowListener(new WindowAdapter() {

			            @Override
			            public void windowClosed(WindowEvent e) {
			                System.out.println("User has closed window");
			                startScript();
			            }
			        });
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		System.out.println("Script resumed");
		
		return true;
//		return false;
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
		long runtime = System.currentTimeMillis() - getStartTime();
		
		String runtimeString = "Runtime: "+Time.format(runtime);
		String lvlString = "Magic Level: "+Skills.Skill.MAGIC.getCurrentLevel()+"("+lvlsGained+")";
		String xpString = "XP Gained: "+SkillData.MAGIC.getXpGained();
		String xpPerHrString = "XP/Hr: "+SkillData.MAGIC.getXpPerHour(runtime);
		Paint.drawLine(g, 0, runtimeString);
		Paint.drawLine(g, 1, lvlString);
		Paint.drawLine(g, 2, xpString);
		Paint.drawLine(g, 3, xpPerHrString);
		// TODO Auto-generated method stub
	}
	
	private void startScript() {
		System.out.println("Selected Spell: "+AlchemistGlobal.selectedSpell+" Num of casts: "+AlchemistGlobal.numOfCasts);
		
		if (!Magic.canCastSpell(AlchemistGlobal.selectedSpell.getSpell())) {
			System.out.println("Stopping Script - Necessary Magic level requirement for selected spell not met");
			this.stop();
		} else {
			bankTask = new BankTask();
			provide(bankTask);
			castSpellTask = new CastSpellTask();
			provide(castSpellTask);
		}
	}
}

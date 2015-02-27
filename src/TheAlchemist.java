
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.util.Time;
import com.epicbot.api.util.paint.Paint;
import com.epicbot.event.listeners.PaintListener;
import com.psutton.alchemist.AlchemistFrame;
import com.psutton.alchemist.AlchemistGlobal;
import com.psutton.alchemist.Helpers;
import com.psutton.alchemist.Spells;
import com.psutton.alchemist.tasks.BankTask;
import com.psutton.alchemist.tasks.CastSpellTask;
@Manifest(game=GameType.RS3, name="The Alchemist", author="Doomboy5888", description="Handles all your alchemy, teleporting and superheating needs", version=1.20)

public class TheAlchemist extends ActiveScript implements PaintListener
{
	private BankTask bankTask;
	private CastSpellTask castSpellTask;
	private int startingLvl, startingXp, xpGained, lvlsGained;
	private long startTime = 0;
	private long runtime;
	private String scriptInfoString, runtimeString, statusString, lvlString, xpString, xpPerHrString, numOfTimesCast;

	@Override
	public boolean onStart() {
		AlchemistGlobal.script = this;
		this.setName("The Alchemist");
		Spells spells = new Spells();
		startingLvl = Skills.Skill.MAGIC.getCurrentLevel();
		startingXp = Skills.Skill.MAGIC.getExperience();

		AlchemistGlobal.availableSpells = spells.getSpells();
		AlchemistGlobal.spellList = spells.getSpellList();


		EventQueue.invokeLater(new Runnable() {
			@Override
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
	}

	@Override
	public void onStop() {
		castSpellTask.shouldStop = true;
		revoke(castSpellTask);
		revoke(bankTask);
		terminated(castSpellTask);
		terminated(bankTask);
		this.kill();
	}

	@Override
	public void onRepaint(Graphics2D g) {
		lvlsGained = Skills.Skill.MAGIC.getCurrentLevel() - this.startingLvl;
		xpGained = Skills.Skill.MAGIC.getExperience() - this.startingXp;
		runtime = System.currentTimeMillis() - startTime;

		scriptInfoString = "The Alchemist "+AlchemistGlobal.scriptVersion;
		runtimeString = "Runtime: "+Time.format(runtime);
		statusString = "Script status: "+AlchemistGlobal.scriptStatus;
		lvlString = "Magic Level: "+Skills.Skill.MAGIC.getCurrentLevel()+"("+lvlsGained+")";
		xpString = "XP Gained: "+xpGained;
		xpPerHrString = "XP/Hr: "+Time.getPerHour(xpGained, startTime);
		numOfTimesCast = "Num of Casts: "+AlchemistGlobal.numOfTimesCast;

		Paint.drawLine(g, 0, scriptInfoString);
		Paint.drawLine(g, 1, runtimeString);
		Paint.drawLine(g, 2, statusString);
		Paint.drawLine(g, 3, lvlString);
		Paint.drawLine(g, 4, xpString);
		Paint.drawLine(g, 5, xpPerHrString);
		Paint.drawLine(g, 6, numOfTimesCast);
		// TODO Auto-generated method stub
	}

	private void startScript() {
		System.out.println("Selected Spell: "+AlchemistGlobal.selectedSpell+" Num of casts: "+AlchemistGlobal.numOfCasts);

		if (!Magic.canCastSpell(AlchemistGlobal.selectedSpell.getSpell())) {
			System.out.println("Stopping Script - Necessary Magic level requirement for selected spell not met");
			this.stop();
		} else {
			if (AlchemistGlobal.selectedSpell != null) {
				if (AlchemistGlobal.selectedSpell.requiresAnItem()) {
					Helpers.loadItemToUse();
				}
				startTime = getStartTime();
				bankTask = new BankTask();
				provide(bankTask);
				castSpellTask = new CastSpellTask();
				provide(castSpellTask);
			}
		}
	}
}

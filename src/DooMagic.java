
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
import com.psutton.doomagic.DooMagicFrame;
import com.psutton.doomagic.DooMagicGlobal;
import com.psutton.doomagic.Helpers;
import com.psutton.doomagic.Spells;
import com.psutton.doomagic.tasks.BankTask;
import com.psutton.doomagic.tasks.CastSpellTask;
@Manifest(game=GameType.RS3, name="DooMagic", author="Doomboy5888", description="Handles all your alchemy, teleporting and superheating needs", version=1.21)

public class DooMagic extends ActiveScript implements PaintListener
{
	private BankTask bankTask;
	private CastSpellTask castSpellTask;
	private int startingLvl, startingXp, xpGained, lvlsGained;
	private long startTime = 0;
	private long runtime;
	private String scriptInfoString, runtimeString, statusString, lvlString, xpString, xpPerHrString, numOfTimesCast;

	@Override
	public boolean onStart() {
		DooMagicGlobal.script = this;
		this.setName("The Alchemist");
		Spells spells = new Spells();
		startingLvl = Skills.Skill.MAGIC.getCurrentLevel();
		startingXp = Skills.Skill.MAGIC.getExperience();

		DooMagicGlobal.availableSpells = spells.getSpells();
		DooMagicGlobal.spellList = spells.getSpellList();


		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DooMagicFrame frame = new DooMagicFrame();
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

		scriptInfoString = DooMagicGlobal.scriptName;
		runtimeString = "Runtime: "+Time.format(runtime);
		statusString = "Script status: "+DooMagicGlobal.scriptStatus;
		lvlString = "Magic Level: "+Skills.Skill.MAGIC.getCurrentLevel()+"("+lvlsGained+")";
		xpString = "XP Gained: "+xpGained;
		xpPerHrString = "XP/Hr: "+Time.getPerHour(xpGained, startTime);
		numOfTimesCast = "Num of Casts: "+DooMagicGlobal.numOfTimesCast;

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
		System.out.println("Selected Spell: "+DooMagicGlobal.selectedSpell+" Num of casts: "+DooMagicGlobal.numOfCasts);

		if (!Magic.canCastSpell(DooMagicGlobal.selectedSpell.getSpell())) {
			System.out.println("Stopping Script - Necessary Magic level requirement for selected spell not met");
			this.stop();
		} else {
			if (DooMagicGlobal.selectedSpell != null) {
				if (DooMagicGlobal.selectedSpell.requiresAnItem()) {
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

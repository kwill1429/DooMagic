import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.util.SkillData;
import com.epicbot.event.listeners.PaintListener;
import com.psutton.doomagic.*;
import com.psutton.doomagic.tasks.BankTask;
import com.psutton.doomagic.tasks.CastSpellTask;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
@Manifest(game=GameType.RS3, name="DooMagic", author="Doomboy5888", description="Handles all your alchemy, teleporting and superheating needs", version=1.21)

public class DooMagic extends ActiveScript implements PaintListener
{
	private BankTask bankTask;
	private CastSpellTask castSpellTask;
	private DooMagicPaintUtil paintUtil = new DooMagicPaintUtil();

	@Override
	public boolean onStart() {
		Spells spells = new Spells();

		DooMagicGlobal.script = this;
		DooMagicGlobal.startingLvl = Skills.Skill.MAGIC.getCurrentLevel();
		DooMagicGlobal.startingXP = Skills.Skill.MAGIC.getExperience();
		DooMagicGlobal.availableSpells = spells.getSpells();
		DooMagicGlobal.spellList = spells.getSpellList();
		this.setName("DooMagic");

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DooMagicFrame frame = new DooMagicFrame();
					frame.setVisible(true);

					frame.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosed(WindowEvent e) {
							startScript();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		return true;
	}

	@Override
	public void onStop() {
		if (DooMagicGlobal.frame.isEnabled()) {
			DooMagicGlobal.frame.dispose();
		}

		revoke(castSpellTask);
		revoke(bankTask);
		terminated(castSpellTask);
		terminated(bankTask);
		this.kill();
	}

	@Override
	public void onRepaint(Graphics2D g) {
		paintUtil.createPaint(g);
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
				DooMagicGlobal.startTime = getStartTime();
				SkillData.MAGIC.resetStartData();
				bankTask = new BankTask();
				provide(bankTask);
				castSpellTask = new CastSpellTask();
				provide(castSpellTask);
			}
		}
	}
}

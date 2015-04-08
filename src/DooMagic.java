import com.epicbot.api.ActiveScript;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.util.SkillData;
import com.epicbot.event.listeners.PaintListener;
import com.psutton.doomagic.*;
import com.psutton.doomagic.tasks.AntiBanTask;
import com.psutton.doomagic.tasks.BankTask;
import com.psutton.doomagic.tasks.CastSpellTask;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DooMagic extends ActiveScript implements PaintListener
{
	private boolean shouldPaint = false;
	private BankTask bankTask;
	private CastSpellTask castSpellTask;
	private AntiBanTask antiBanTask;
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
					DooMagicGUI frame = new DooMagicGUI();
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

		revoke(bankTask);
		revoke(antiBanTask);
		revoke(castSpellTask);

		terminated(bankTask);
		terminated(antiBanTask);
		terminated(castSpellTask);

		this.kill();
	}

	@Override
	public void onRepaint(Graphics2D g) {
		if (shouldPaint) {
			paintUtil.createPaint(g);
		}
	}

	private void startScript() {
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
				AntiBanTask.generateAntiBanCounter();

				shouldPaint = true;

				bankTask = new BankTask();
				provide(bankTask);
				antiBanTask = new AntiBanTask();
				provide(antiBanTask);
				castSpellTask = new CastSpellTask();
				provide(castSpellTask);
			}
		}
	}
}

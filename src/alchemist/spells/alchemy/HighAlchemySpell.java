package alchemist.spells.alchemy;

import alchemist.AlchemistGlobal;

import com.epicbot.api.rs3.methods.tab.Magic;

import helpers.objects.MagicSpell;
import helpers.objects.RuneForSpell;

public class HighAlchemySpell extends MagicSpell {

	public HighAlchemySpell() {
		RuneForSpell natureRune = new RuneForSpell("Nature Rune", AlchemistGlobal.runeNature, 1, false, false);
		RuneForSpell fireRune = new RuneForSpell("Fire Rune", AlchemistGlobal.runeFire, 5, true, true);
		RuneForSpell[] requiredRunes = { natureRune };
		
		this.setRequiredRunes(requiredRunes);
		this.setSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY);
		this.setSpellName("High Alchemy");
		this.setTimeToCast(3000);
	}
}

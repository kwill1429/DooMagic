package alchemist.spells.alchemy;

import utilities.objects.MagicSpell;
import utilities.objects.RuneForSpell;
import alchemist.AlchemistGlobal;

import com.epicbot.api.rs3.methods.tab.Magic;

public class HighAlchemySpell extends MagicSpell {

	public HighAlchemySpell() {
		RuneForSpell natureRune = new RuneForSpell("Nature Rune", AlchemistGlobal.runeNature, 1, false, false);
		RuneForSpell fireRune = new RuneForSpell("Fire Rune", AlchemistGlobal.runeFire, 5, true, true);
		RuneForSpell[] requiredRunes = { natureRune, fireRune };
		
		this.setRequiredRunes(requiredRunes);
		this.setSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY);
		this.setSpellName("High Alchemy");
		this.setTimeToCast(3000);
		this.setLevelToCast(55);
		this.setRequiresAnItem(true);
	}
}

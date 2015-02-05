package alchemist.spells.alchemy;

import utilities.objects.MagicSpell;
import alchemist.AlchemistGlobal;
import alchemist.AlchemistRune;

import com.epicbot.api.rs3.methods.tab.Magic;

public class HighAlchemySpell extends MagicSpell {

	public HighAlchemySpell() {
		AlchemistRune natureRune = new AlchemistRune("Nature Rune", AlchemistGlobal.runeNature, 1, false, false);
		AlchemistRune fireRune = new AlchemistRune("Fire Rune", AlchemistGlobal.runeFire, 5, true, true);
		AlchemistRune[] requiredRunes = { natureRune, fireRune };
		
		this.setRequiredRunes(requiredRunes);
		this.setSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY);
		this.setSpellName("High Alchemy");
		this.setTimeToCast(3000);
		this.setLevelToCast(55);
		this.setRequiresAnItem(true);
	}
}

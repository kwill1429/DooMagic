package alchemist.spells.alchemy;

import utilities.objects.MagicSpell;
import alchemist.AlchemistGlobal;
import alchemist.AlchemistRune;

import com.epicbot.api.rs3.methods.tab.Magic;

public class LowAlchemySpell extends MagicSpell {
	
	public LowAlchemySpell() {
		AlchemistRune natureRune = new AlchemistRune("Nature Rune", AlchemistGlobal.runeNature, 1, false, false);
		AlchemistRune fireRune = new AlchemistRune("Fire Rune", AlchemistGlobal.runeFire, 3, true, true);
		AlchemistRune[] requiredRunes = { natureRune, fireRune };
		
		this.setSpell(Magic.Spell.LOW_LEVEL_ALCHEMY);
		this.setSpellName("Low Alchemy");
		this.setTimeToCast(3000);
		this.setLevelToCast(21);
		this.setRequiredRunes(requiredRunes);
		this.setRequiresAnItem(true);
	}
}

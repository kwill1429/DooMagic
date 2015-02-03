package alchemist.spells.alchemy;

import alchemist.AlchemistGlobal;

import com.epicbot.api.rs3.methods.tab.Magic;

import helpers.objects.MagicSpell;
import helpers.objects.RuneForSpell;

public class LowAlchemySpell extends MagicSpell {
	
	public LowAlchemySpell() {
		RuneForSpell natureRune = new RuneForSpell("Nature Rune", AlchemistGlobal.runeNature, 1, false, false);
		RuneForSpell fireRune = new RuneForSpell("Fire Rune", AlchemistGlobal.runeFire, 3, true, true);
		RuneForSpell[] requiredRunes = { natureRune, fireRune };
		
		this.setSpell(Magic.Spell.LOW_LEVEL_ALCHEMY);
		this.setSpellName("Low Alchemy");
		this.setTimeToCast(3000);
		this.setRequiredRunes(requiredRunes);
	}
}

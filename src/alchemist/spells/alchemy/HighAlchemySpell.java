package alchemist.spells.alchemy;

import alchemist.AlchemistGlobal;

import com.epicbot.api.rs3.methods.tab.Magic;

import helpers.objects.MagicSpell;
import helpers.objects.RuneForSpell;

public class HighAlchemySpell extends MagicSpell {

	public HighAlchemySpell() {
		this.setSpell(Magic.Spell.HIGH_LEVEL_ALCHEMY);
		this.setSpellName("High Alchemy");
		this.setTimeToCast(3000);
		
		int[] staves = { AlchemistGlobal.staffFire,
				AlchemistGlobal.staffLava,
				AlchemistGlobal.staffSteam 
		};
		this.setStaves(staves);
		
		RuneForSpell natureRune = new RuneForSpell("Nature Rune", AlchemistGlobal.runeNature, 1, false, false);
		RuneForSpell[] requiredRunes = { natureRune };
		this.setRequiredRunes(requiredRunes);
		
		RuneForSpell fireRune = new RuneForSpell("Fire Rune", AlchemistGlobal.runeFire, 5, true, true);
		RuneForSpell steamRune = new RuneForSpell("Steam Rune", AlchemistGlobal.runeSteam, 5, false, true);
		RuneForSpell lavaRune = new RuneForSpell("Lava Rune", AlchemistGlobal.runeLava, 5, false, true);
		RuneForSpell[] substituteRunes = { fireRune, steamRune, lavaRune };
		this.setSubstituteRunes(substituteRunes);
	}
}

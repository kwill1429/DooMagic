package alchemist.spells;

import alchemist.AlchemistGlobal;

import com.epicbot.api.rs3.methods.tab.Magic;

import helpers.MagicSpell;
import helpers.RuneForSpell;

public class LowAlchemySpell extends MagicSpell {
	
	public LowAlchemySpell() {
		this.setSpell(Magic.Spell.LOW_LEVEL_ALCHEMY);
		this.setSpellName("Low Alchemy");
		this.setTimeToCast(3000);
		
		int[] staves = { AlchemistGlobal.staffFire,
				AlchemistGlobal.staffLava,
				AlchemistGlobal.staffSteam 
		};
		this.setStaves(staves);
		
		RuneForSpell natureRune = new RuneForSpell("Nature Rune", AlchemistGlobal.runeNature, 1, false, false);
		RuneForSpell[] requiredRunes = { natureRune };
		this.setRequiredRunes(requiredRunes);
		
		RuneForSpell fireRune = new RuneForSpell("Fire Rune", AlchemistGlobal.runeFire, 3, true, true);
		RuneForSpell steamRune = new RuneForSpell("Steam Rune", AlchemistGlobal.runeSteam, 3, false, true);
		RuneForSpell lavaRune = new RuneForSpell("Lava Rune", AlchemistGlobal.runeLava, 3, false, true);
		RuneForSpell[] substituteRunes = { fireRune, steamRune, lavaRune };
		this.setSubstituteRunes(substituteRunes);
		
	}
}

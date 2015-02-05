package alchemist;

import java.util.HashMap;

import com.epicbot.api.rs3.methods.tab.Magic;

import utilities.items.Runes;
import utilities.objects.PSRune;
import utilities.objects.PSSpell;

public class Spells {
	private PSSpell lowAlch;
	private PSSpell highAlch;
	private HashMap<String, PSSpell> spells;
	
	public Spells() {
		HashMap<String, PSRune> runes = new Runes().getRunes();
		PSRune runeOne, runeTwo;
		
		runeOne = runes.get("NatureRune");
		runeOne.setNumOfRunes(1);
		runeTwo = runes.get("FireRune");
		runeTwo.setNumOfRunes(3);
		PSRune[] lowAlchRunes = { runeOne, runeTwo };
		lowAlch = new PSSpell("Low Level Alchemy", Magic.Spell.LOW_LEVEL_ALCHEMY);
		lowAlch.setTimeToCast(3000);
		lowAlch.setLevelToCast(21);
		lowAlch.setRequiresAnItem(true);
		lowAlch.setRunes(lowAlchRunes);
		
		runeOne = runes.get("NatureRune");
		runeOne.setNumOfRunes(1);
		runeTwo = runes.get("FireRune");
		runeTwo.setNumOfRunes(5);
		PSRune[] highAlchRunes = { runeOne, runeTwo };
		highAlch = new PSSpell("High Level Alchemy", Magic.Spell.HIGH_LEVEL_ALCHEMY);
		highAlch.setTimeToCast(3000);
		highAlch.setLevelToCast(55);
		highAlch.setRequiresAnItem(true);
		highAlch.setRunes(highAlchRunes);
		
		generateSpells();
	}

	public HashMap<String, PSSpell> getSpells() {
		return spells;
	}

	public void setSpells(HashMap<String, PSSpell> spells) {
		this.spells = spells;
	}
	
	private void generateSpells() {
		HashMap<String, PSSpell> spells = new HashMap<String, PSSpell> (2);
		
		spells.put("Low Alchemy", lowAlch);
		spells.put("High Alchemy", highAlch);
		
		this.spells = spells;
	}
}

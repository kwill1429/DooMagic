package helpers;

import com.epicbot.api.rs3.methods.tab.Magic;

public class MagicSpell {
	private Magic.Spell spell;
	private String spellName;
	private int timeToCast;
	private int[] staves;
	private RuneForSpell[] requiredRunes;
	private RuneForSpell[] substituteRunes;
	

	public Magic.Spell getSpell() {
		return spell;
	}

	public void setSpell(Magic.Spell spell) {
		this.spell = spell;
	}

	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	public int getTimeToCast() {
		return timeToCast;
	}

	public void setTimeToCast(int timeToCast) {
		this.timeToCast = timeToCast;
	}

	public int[] getStaves() {
		return staves;
	}

	public void setStaves(int[] staves) {
		this.staves = staves;
	}

	public RuneForSpell[] getRequiredRunes() {
		return requiredRunes;
	}

	public void setRequiredRunes(RuneForSpell[] requiredRunes) {
		this.requiredRunes = requiredRunes;
	}

	public RuneForSpell[] getSubstituteRunes() {
		return substituteRunes;
	}

	public void setSubstituteRunes(RuneForSpell[] substituteRunes) {
		this.substituteRunes = substituteRunes;
	}

	@Override
	public String toString() {
		return this.spellName;
	}
}

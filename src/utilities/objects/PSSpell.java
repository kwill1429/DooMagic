package utilities.objects;

import alchemist.AlchemistRune;

import com.epicbot.api.rs3.methods.tab.Magic;

public class PSSpell {
	private int timeToCast;
	private int levelToCast;
	private boolean requiresAnItem;
	private String spellName;
	private Magic.Spell spell;
	private AlchemistRune[] requiredRunes;
	private PSRune[] runes;
	
	public PSSpell(String spellName, Magic.Spell spell) {
		this.spellName = spellName;
		this.spell = spell;		
	}
	
	public int getTimeToCast() {
		return timeToCast;
	}

	public void setTimeToCast(int timeToCast) {
		this.timeToCast = timeToCast;
	}

	public int getLevelToCast() {
		return levelToCast;
	}

	public void setLevelToCast(int levelToCast) {
		this.levelToCast = levelToCast;
	}

	public boolean requiresAnItem() {
		return requiresAnItem;
	}

	public void setRequiresAnItem(boolean requiresAnItem) {
		this.requiresAnItem = requiresAnItem;
	}

	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	public Magic.Spell getSpell() {
		return spell;
	}

	public void setSpell(Magic.Spell spell) {
		this.spell = spell;
	}

	public AlchemistRune[] getRequiredRunes() {
		return requiredRunes;
	}

	public void setRequiredRunes(AlchemistRune[] requiredRunes) {
		this.requiredRunes = requiredRunes;
	}

	public PSRune[] getRunes() {
		return runes;
	}

	public void setRunes(PSRune[] runes) {
		this.runes = runes;
	}

	@Override
	public String toString() {
		return this.spellName;
	}
}

package helpers.objects;

import com.epicbot.api.rs3.methods.tab.Magic;

public class MagicSpell {
	private Magic.Spell spell;
	private RuneForSpell[] requiredRunes;
	private String spellName;
	private int timeToCast;
	private int levelToCast;
	private boolean requiresAnItem;
	
		
	public Magic.Spell getSpell() {
		return spell;
	}

	public void setSpell(Magic.Spell spell) {
		this.spell = spell;
	}

	public RuneForSpell[] getRequiredRunes() {
		return requiredRunes;
	}

	public void setRequiredRunes(RuneForSpell[] requiredRunes) {
		this.requiredRunes = requiredRunes;
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

	@Override
	public String toString() {
		return this.spellName;
	}
}

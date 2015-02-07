package com.psutton.utilities.objects;

import com.epicbot.api.rs3.methods.tab.Magic;

public class PSSpell {
	private int timeToCast;
	private boolean requiresAnItem;
	private String spellName;
	private Magic.Spell spell;
	private Magic.InnerAbilityTab abilityTab;
	

	private PSRune[] runes;
	
	public PSSpell(String spellName, Magic.Spell spell) {
		this.spellName = spellName;
		this.spell = spell;	
		this.abilityTab = spell.getInnerTabSetting();
		this.requiresAnItem = false;
	}
	
	public int getTimeToCast() {
		return timeToCast;
	}

	public void setTimeToCast(int timeToCast) {
		this.timeToCast = timeToCast;
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
	
	public Magic.InnerAbilityTab getAbilityTab() {
		return abilityTab;
	}
	
	public void setAbilityTab(Magic.InnerAbilityTab abilityTab) {
		this.abilityTab = abilityTab;
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

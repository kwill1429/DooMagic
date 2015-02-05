package alchemist;

import java.util.HashMap;

import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;

import utilities.items.Runes;
import utilities.objects.PSRune;
import utilities.objects.PSSpell;
import utilities.objects.PSStaff;

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
	
	public static boolean areRunesForSpellInInventory() {
		PSSpell spell = AlchemistGlobal.selectedSpell;
		PSRune rune;
		PSRune[] requiredRunes = spell.getRunes(); 
		
		for (int i = 0; i < requiredRunes.length; i++) {
			rune = requiredRunes[i];
			
			if (!meetsRuneRequirement(rune)) {
				System.out.println("do not have enough of: " + rune.getItemName());
				return false;
				//break;
			}
		}	
		
		return true;
	}
	
	public static boolean isValidStaffEquipped(PSStaff[] staves) {
		for (int i = 0; i < staves.length; i++) {
			if (Equipment.containsOneOf(staves[i].getItemID())) {
				return true;
			}
		}
		return false;
	}
	
	
	
	private static boolean meetsRuneRequirement(PSRune rune) {
		int runeID, numOfRunes;
		PSRune[] relatedRunes;
		PSStaff[] staves;
		
		runeID = rune.getItemID();
		numOfRunes = rune.getNumOfRunes();
		relatedRunes = rune.getAssociatedRunes();
		staves = rune.getAssociatedStaves();
		
		if (relatedRunes != null) {
			if (isValidStaffEquipped(staves)) {
				return true;
			}
		}
		
		if (hasEnoughOfRune(runeID, numOfRunes)) {
			return true;
		}
		else {
			if (relatedRunes != null) {
				for (int i = 0; i < relatedRunes.length; i++) {
					runeID = relatedRunes[i].getItemID();
						
					if (hasEnoughOfRune(runeID, numOfRunes)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private static boolean hasEnoughOfRune(int runeID, int numOfRunes) {
		if (Inventory.contains(runeID)) {
			if (Inventory.getCount(true, runeID) >= numOfRunes) {
				return true;
			}
		}
		return false;
	}
}

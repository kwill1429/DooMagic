package alchemist;

import java.util.HashMap;

import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;

import utilities.items.RuneList;
import utilities.objects.PSRune;
import utilities.objects.PSSpell;
import utilities.objects.PSStaff;

public class Spells {
	private PSSpell lowAlch, highAlch, superheatItem;
	private PSSpell mobilisingArmiesTeleport, varrockTeleport, lumbridgeTeleport, faladorTeleport;
	private PSSpell houseTeleport, camelotTeleport, ardougneTeleport;
	
	private HashMap<String, PSSpell> spells;
	
	public Spells() {
		HashMap<String, PSRune> runes = new RuneList().getRunes();
		PSRune natureRune, fireRune, lawRune, waterRune, earthRune, airRune;
		
		natureRune = runes.get("NatureRune");
		fireRune = runes.get("FireRune");
		lawRune = runes.get("LawRune");
		waterRune = runes.get("WaterRune");
		earthRune = runes.get("EarthRune");
		airRune = runes.get("AirRune");
			
		natureRune.setNumOfRunes(1);
		fireRune.setNumOfRunes(3);
		PSRune[] lowAlchRunes = { natureRune, fireRune };
		lowAlch = new PSSpell("Low Level Alchemy", Magic.Spell.LOW_LEVEL_ALCHEMY);
		lowAlch.setTimeToCast(3000);
		lowAlch.setAbilityTab(Magic.InnerAbilityTab.TELEPORT_SPELL);
		lowAlch.setRequiresAnItem(true);
		lowAlch.setRunes(lowAlchRunes);
		
		
		natureRune.setNumOfRunes(1);
		fireRune.setNumOfRunes(5);
		PSRune[] highAlchRunes = { natureRune, fireRune };
		highAlch = new PSSpell("High Level Alchemy", Magic.Spell.HIGH_LEVEL_ALCHEMY);
		highAlch.setTimeToCast(3000);
		highAlch.setAbilityTab(Magic.InnerAbilityTab.TELEPORT_SPELL);
		highAlch.setRequiresAnItem(true);
		highAlch.setRunes(highAlchRunes);
		
		natureRune.setNumOfRunes(1);
		fireRune.setNumOfRunes(4);
		PSRune[] superheatItemRunes = { natureRune, fireRune };
		superheatItem = new PSSpell("Superheat Item", Magic.Spell.SUPERHEAT_ITEM);
		superheatItem.setTimeToCast(3000);
		superheatItem.setAbilityTab(Magic.InnerAbilityTab.TELEPORT_SPELL);
		superheatItem.setRequiresAnItem(true);
		superheatItem.setRunes(superheatItemRunes);
		
		lawRune.setNumOfRunes(1);
		airRune.setNumOfRunes(1);
		waterRune.setNumOfRunes(1);
		PSRune[] mobilisingArmiesRunes = { lawRune, airRune, waterRune };
		mobilisingArmiesTeleport = new PSSpell("Mobilising Armies Teleport", Magic.Spell.MOBILISING_ARMIES_TELEPORT);
		mobilisingArmiesTeleport.setTimeToCast(3000);
		mobilisingArmiesTeleport.setRunes(mobilisingArmiesRunes);
		
		lawRune.setNumOfRunes(1);
		airRune.setNumOfRunes(3);
		fireRune.setNumOfRunes(1);
		PSRune[] varrockTeleRunes = { lawRune, airRune, fireRune };
		varrockTeleport = new PSSpell("Varrock Teleport", Magic.Spell.VARROCK_TELEPORT);
		varrockTeleport.setTimeToCast(3000);
		varrockTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		varrockTeleport.setRunes(varrockTeleRunes);
		
		lawRune.setNumOfRunes(1);
		airRune.setNumOfRunes(3);
		earthRune.setNumOfRunes(1);
		PSRune[] lumbridgeTeleRunes = { lawRune, airRune, earthRune };
		lumbridgeTeleport = new PSSpell("Lumbridge Teleport", Magic.Spell.LUMBRIDGE_TELEPORT);
		lumbridgeTeleport.setTimeToCast(3000);
		lumbridgeTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		lumbridgeTeleport.setRunes(lumbridgeTeleRunes);
		
		lawRune.setNumOfRunes(1);
		airRune.setNumOfRunes(1);
		waterRune.setNumOfRunes(1);
		PSRune[] faladorTeleRunes = { lawRune, airRune, waterRune };
		faladorTeleport = new PSSpell("Falador Teleport", Magic.Spell.FALADOR_TELEPORT);
		faladorTeleport.setTimeToCast(3000);
		faladorTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		faladorTeleport.setRunes(faladorTeleRunes);
		
		lawRune.setNumOfRunes(1);
		airRune.setNumOfRunes(1);
		earthRune.setNumOfRunes(1);
		PSRune[] houseTeleRunes = { lawRune, airRune, earthRune };
		houseTeleport = new PSSpell("House Teleport", Magic.Spell.HOME_TELEPORT);
		houseTeleport.setTimeToCast(3000);
		houseTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		houseTeleport.setRunes(houseTeleRunes);
		
		lawRune.setNumOfRunes(1);
		airRune.setNumOfRunes(5);
		PSRune[] camelotTeleRunes = { lawRune, airRune };
		camelotTeleport = new PSSpell("Camelot Teleport", Magic.Spell.CAMELOT_TELEPORT);
		camelotTeleport.setTimeToCast(3000);
		camelotTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		camelotTeleport.setRunes(camelotTeleRunes);
		
		lawRune.setNumOfRunes(2);
		waterRune.setNumOfRunes(2);
		PSRune[] ardougneTeleRunes = { lawRune, waterRune };
		ardougneTeleport = new PSSpell("Ardougne Teleport", Magic.Spell.ARDOUGNE_TELEPORT);
		ardougneTeleport.setTimeToCast(3000);
		ardougneTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		ardougneTeleport.setRunes(ardougneTeleRunes);
		
		generateSpells();
	}

	public HashMap<String, PSSpell> getSpells() {
		return spells;
	}

	public void setSpells(HashMap<String, PSSpell> spells) {
		this.spells = spells;
	}
	
	private void generateSpells() {
		HashMap<String, PSSpell> spells = new HashMap<String, PSSpell> (10);
		
		spells.put("Low Alchemy", lowAlch);
		spells.put("High Alchemy", highAlch);
		spells.put("Superheat Item", superheatItem);
		spells.put("Mobilising Armies Teleport", mobilisingArmiesTeleport);
		spells.put("Varrock Teleport", varrockTeleport);
		spells.put("Lumbridge Teleport", lumbridgeTeleport);
		spells.put("Falador Teleport", faladorTeleport);
		spells.put("House Teleport", houseTeleport);
		spells.put("Camelot Teleport", camelotTeleport);
		spells.put("ArdougneTeleport", ardougneTeleport);
		
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
		System.out.println("Num of"+ rune+ " : "+numOfRunes);
		relatedRunes = rune.getAssociatedRunes();
		staves = rune.getAssociatedStaves();
		
		if (staves != null) {
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
			System.out.println("Rune count: " + Inventory.getCount(false, runeID)+ "Num of Runes: "+numOfRunes );
			if (Inventory.getCount(false, runeID) >= numOfRunes) {
				return true;
			}
		}
		return false;
	}
}

package com.psutton.alchemist;

import java.util.HashMap;

import com.epicbot.api.rs3.methods.tab.Equipment;
import com.epicbot.api.rs3.methods.tab.Magic;
import com.epicbot.api.rs3.methods.tab.inventory.Inventory;
import com.psutton.utilities.items.RuneList;
import com.psutton.utilities.objects.PSRune;
import com.psutton.utilities.objects.PSSpell;
import com.psutton.utilities.objects.PSStaff;

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
			
		PSRune lowAlchOne = new PSRune(natureRune, 1);
		PSRune lowAlchTwo = new PSRune(fireRune, 3);
		PSRune[] lowAlchRunes = { lowAlchOne, lowAlchTwo };
		lowAlch = new PSSpell("Low Level Alchemy", Magic.Spell.LOW_LEVEL_ALCHEMY);
		lowAlch.setTimeToCast(3000);
		lowAlch.setAbilityTab(Magic.InnerAbilityTab.TELEPORT_SPELL);
		lowAlch.setRequiresAnItem(true);
		lowAlch.setRunes(lowAlchRunes);
		
		PSRune highAlchOne = new PSRune(natureRune, 1);
		PSRune highAlchTwo = new PSRune(fireRune, 1);
		PSRune[] highAlchRunes = { highAlchOne, highAlchTwo };
		highAlch = new PSSpell("High Level Alchemy", Magic.Spell.HIGH_LEVEL_ALCHEMY);
		highAlch.setTimeToCast(3000);
		highAlch.setAbilityTab(Magic.InnerAbilityTab.TELEPORT_SPELL);
		highAlch.setRequiresAnItem(true);
		highAlch.setRunes(highAlchRunes);
		
		PSRune superheatOne = new PSRune(natureRune, 1);
		PSRune superheatTwo = new PSRune(fireRune, 4);
		PSRune[] superheatItemRunes = { superheatOne, superheatTwo };
		superheatItem = new PSSpell("Superheat Item", Magic.Spell.SUPERHEAT_ITEM);
		superheatItem.setTimeToCast(3000);
		superheatItem.setAbilityTab(Magic.InnerAbilityTab.TELEPORT_SPELL);
		superheatItem.setRequiresAnItem(true);
		superheatItem.setRunes(superheatItemRunes);
		
		
		PSRune mobilisingOne = new PSRune(lawRune, 1);
		PSRune mobilisingTwo = new PSRune(airRune, 3);
		PSRune mobilisingThree = new PSRune(waterRune, 1);
		PSRune[] mobilisingArmiesRunes = { mobilisingOne, mobilisingTwo, mobilisingThree };
		mobilisingArmiesTeleport = new PSSpell("Mobilising Armies Teleport", Magic.Spell.MOBILISING_ARMIES_TELEPORT);
		mobilisingArmiesTeleport.setTimeToCast(3000);
		mobilisingArmiesTeleport.setRunes(mobilisingArmiesRunes);
		
		
		PSRune varrockOne = new PSRune(lawRune, 1);
		PSRune varrockTwo = new PSRune(airRune, 3);
		PSRune varrockThree = new PSRune(fireRune, 1);
		PSRune[] varrockTeleRunes = { varrockOne, varrockTwo, varrockThree };
		varrockTeleport = new PSSpell("Varrock Teleport", Magic.Spell.VARROCK_TELEPORT);
		varrockTeleport.setTimeToCast(3000);
		varrockTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		varrockTeleport.setRunes(varrockTeleRunes);
		
		PSRune lumbridgeOne = new PSRune(lawRune, 1);
		PSRune lumbridgeTwo = new PSRune(airRune, 3);
		PSRune lumbridgeThree = new PSRune(earthRune, 1);
		PSRune[] lumbridgeTeleRunes = { lumbridgeOne, lumbridgeTwo, lumbridgeThree };
		lumbridgeTeleport = new PSSpell("Lumbridge Teleport", Magic.Spell.LUMBRIDGE_TELEPORT);
		lumbridgeTeleport.setTimeToCast(3000);
		lumbridgeTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		lumbridgeTeleport.setRunes(lumbridgeTeleRunes);
		
		PSRune faladorOne = new PSRune(lawRune, 1);
		PSRune faladorTwo = new PSRune(airRune, 3);
		PSRune faladorThree = new PSRune(waterRune, 1);
		PSRune[] faladorTeleRunes = { faladorOne, faladorTwo, faladorThree };
		faladorTeleport = new PSSpell("Falador Teleport", Magic.Spell.FALADOR_TELEPORT);
		faladorTeleport.setTimeToCast(3000);
		faladorTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		faladorTeleport.setRunes(faladorTeleRunes);
		
		PSRune houseOne = new PSRune(lawRune, 1);
		PSRune houseTwo = new PSRune(airRune, 1);
		PSRune houseThree = new PSRune(earthRune, 1);
		PSRune[] houseTeleRunes = { houseOne, houseTwo, houseThree };
		houseTeleport = new PSSpell("House Teleport", Magic.Spell.HOME_TELEPORT);
		houseTeleport.setTimeToCast(3000);
		houseTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		houseTeleport.setRunes(houseTeleRunes);
		
		PSRune camelotOne = new PSRune(lawRune, 1);
		PSRune camelotTwo = new PSRune(airRune, 5);
		PSRune[] camelotTeleRunes = { camelotOne, camelotTwo };
		camelotTeleport = new PSSpell("Camelot Teleport", Magic.Spell.CAMELOT_TELEPORT);
		camelotTeleport.setTimeToCast(3000);
		camelotTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		camelotTeleport.setRunes(camelotTeleRunes);
		
		PSRune ardougneOne = new PSRune(lawRune, 2);
		PSRune ardougneTwo = new PSRune(waterRune, 2);
		PSRune[] ardougneTeleRunes = { ardougneOne, ardougneTwo };
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
			System.out.println("Required rune: "+ rune);
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
			if (Inventory.getCount(true, runeID) >= numOfRunes) {
				return true;
			}
		}
		return false;
	}
}

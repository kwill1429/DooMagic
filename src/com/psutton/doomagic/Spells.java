package com.psutton.doomagic;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import com.epicbot.api.rs3.methods.tab.Magic;
import com.psutton.utilities.items.RuneList;
import com.psutton.utilities.objects.PSRune;
import com.psutton.utilities.objects.PSSpell;

public class Spells {
	private PSSpell lowAlch, highAlch;
	//	private PSSpell superheatItem;
	private PSSpell mobilisingArmiesTeleport, varrockTeleport, lumbridgeTeleport, faladorTeleport;
	private PSSpell houseTeleport, camelotTeleport, ardougneTeleport, trollheimTeleport;
	private String[] spellList;
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
		lowAlch.setCentralCoordinate(new Point(715, 323));

		PSRune highAlchOne = new PSRune(natureRune, 1);
		PSRune highAlchTwo = new PSRune(fireRune, 1);
		PSRune[] highAlchRunes = { highAlchOne, highAlchTwo };
		highAlch = new PSSpell("High Level Alchemy", Magic.Spell.HIGH_LEVEL_ALCHEMY);
		highAlch.setTimeToCast(3000);
		highAlch.setAbilityTab(Magic.InnerAbilityTab.SKILLING_SPELL);
		highAlch.setRequiresAnItem(true);
		highAlch.setRunes(highAlchRunes);
		highAlch.setCentralCoordinate(new Point(715, 357));

		//		PSRune superheatOne = new PSRune(natureRune, 1);
		//		PSRune superheatTwo = new PSRune(fireRune, 4);
		//		PSRune[] superheatItemRunes = { superheatOne, superheatTwo };
		//		superheatItem = new PSSpell("Superheat Item", Magic.Spell.SUPERHEAT_ITEM);
		//		superheatItem.setTimeToCast(3000);
		//		superheatItem.setAbilityTab(Magic.InnerAbilityTab.TELEPORT_SPELL);
		//		superheatItem.setRequiresAnItem(true);
		//		superheatItem.setRunes(superheatItemRunes);
		//		superheatItem.setCentralCoordinate(new Point(635, 357));

		PSRune mobilisingOne = new PSRune(lawRune, 1);
		PSRune mobilisingTwo = new PSRune(airRune, 1);
		PSRune mobilisingThree = new PSRune(waterRune, 1);
		PSRune[] mobilisingArmiesRunes = { mobilisingOne, mobilisingTwo, mobilisingThree };
		mobilisingArmiesTeleport = new PSSpell("Mobilising Armies Teleport", Magic.Spell.MOBILISING_ARMIES_TELEPORT);
		mobilisingArmiesTeleport.setTimeToCast(3000);
		mobilisingArmiesTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		mobilisingArmiesTeleport.setRunes(mobilisingArmiesRunes);
		mobilisingArmiesTeleport.setCentralCoordinate(new Point(635, 323));

		PSRune varrockOne = new PSRune(lawRune, 1);
		PSRune varrockTwo = new PSRune(airRune, 3);
		PSRune varrockThree = new PSRune(fireRune, 1);
		PSRune[] varrockTeleRunes = { varrockOne, varrockTwo, varrockThree };
		varrockTeleport = new PSSpell("Varrock Teleport", Magic.Spell.VARROCK_TELEPORT);
		varrockTeleport.setTimeToCast(3500);
		varrockTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		varrockTeleport.setRunes(varrockTeleRunes);
		varrockTeleport.setCentralCoordinate(new Point(675, 323));

		PSRune lumbridgeOne = new PSRune(lawRune, 1);
		PSRune lumbridgeTwo = new PSRune(airRune, 3);
		PSRune lumbridgeThree = new PSRune(earthRune, 1);
		PSRune[] lumbridgeTeleRunes = { lumbridgeOne, lumbridgeTwo, lumbridgeThree };
		lumbridgeTeleport = new PSSpell("Lumbridge Teleport", Magic.Spell.LUMBRIDGE_TELEPORT);
		lumbridgeTeleport.setTimeToCast(3500);
		lumbridgeTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		lumbridgeTeleport.setRunes(lumbridgeTeleRunes);
		lumbridgeTeleport.setCentralCoordinate(new Point(715, 323));

		PSRune faladorOne = new PSRune(lawRune, 1);
		PSRune faladorTwo = new PSRune(airRune, 3);
		PSRune faladorThree = new PSRune(waterRune, 1);
		PSRune[] faladorTeleRunes = { faladorOne, faladorTwo, faladorThree };
		faladorTeleport = new PSSpell("Falador Teleport", Magic.Spell.FALADOR_TELEPORT);
		faladorTeleport.setTimeToCast(3500);
		faladorTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		faladorTeleport.setRunes(faladorTeleRunes);
		faladorTeleport.setCentralCoordinate(new Point(755, 323));

		PSRune houseOne = new PSRune(lawRune, 1);
		PSRune houseTwo = new PSRune(airRune, 1);
		PSRune houseThree = new PSRune(earthRune, 1);
		PSRune[] houseTeleRunes = { houseOne, houseTwo, houseThree };
		houseTeleport = new PSSpell("House Teleport", Magic.Spell.HOME_TELEPORT);
		houseTeleport.setTimeToCast(3500);
		houseTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		houseTeleport.setRunes(houseTeleRunes);
		houseTeleport.setCentralCoordinate(new Point(595, 357));

		PSRune camelotOne = new PSRune(lawRune, 1);
		PSRune camelotTwo = new PSRune(airRune, 5);
		PSRune[] camelotTeleRunes = { camelotOne, camelotTwo };
		camelotTeleport = new PSSpell("Camelot Teleport", Magic.Spell.CAMELOT_TELEPORT);
		camelotTeleport.setTimeToCast(3500);
		camelotTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		camelotTeleport.setRunes(camelotTeleRunes);
		camelotTeleport.setCentralCoordinate(new Point(635, 357));

		PSRune ardougneOne = new PSRune(lawRune, 2);
		PSRune ardougneTwo = new PSRune(waterRune, 2);
		PSRune[] ardougneTeleRunes = { ardougneOne, ardougneTwo };
		ardougneTeleport = new PSSpell("Ardougne Teleport", Magic.Spell.ARDOUGNE_TELEPORT);
		ardougneTeleport.setTimeToCast(3500);
		ardougneTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		ardougneTeleport.setRunes(ardougneTeleRunes);
		ardougneTeleport.setCentralCoordinate(new Point(675, 357));

		PSRune trollheimOne = new PSRune(lawRune, 2);
		PSRune trollheimTwo = new PSRune(fireRune, 2);
		PSRune[] trollheimTeleRunes = { trollheimOne, trollheimTwo };
		trollheimTeleport = new PSSpell("Trollheim Teleport", Magic.Spell.TROLLHEIM_TELEPORT);
		trollheimTeleport.setTimeToCast(3500);
		trollheimTeleport.setAbilityTab(Magic.InnerAbilityTab.COMBAT_SPELL);
		trollheimTeleport.setRunes(trollheimTeleRunes);
		//		trollheimTeleport.setCentralCoordinate(new Point(675, 357));

		generateSpells();
	}

	public String[] getSpellList() {
		return spellList;
	}

	public HashMap<String, PSSpell> getSpells() {
		return spells;
	}

	private void generateSpells() {
		int index = 0;
		HashMap<String, PSSpell> spells = new HashMap<String, PSSpell> (10);
		String[] spellList;

		spells.put("Low Alchemy", lowAlch);
		spells.put("High Alchemy", highAlch);
		//		spells.put("Superheat Item", superheatItem);
		spells.put("Mobilising Armies Teleport", mobilisingArmiesTeleport);
		spells.put("Varrock Teleport", varrockTeleport);
		spells.put("Lumbridge Teleport", lumbridgeTeleport);
		spells.put("Falador Teleport", faladorTeleport);
		spells.put("House Teleport", houseTeleport);
		spells.put("Camelot Teleport", camelotTeleport);
		spells.put("Ardougne Teleport", ardougneTeleport);
		spells.put("Trollheim Teleport", trollheimTeleport);

		this.spells = spells;

		Set<String> keys = spells.keySet();

		index = 0;
		spellList = new String[spells.size()];
		for (String key: keys) {
			spellList[index] = (key);
			index ++;
		}

		Arrays.sort(spellList);
		this.spellList = spellList;
	}

}

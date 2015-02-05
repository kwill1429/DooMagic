package utilities.items;

import java.util.HashMap;

import utilities.objects.PSRune;
import utilities.objects.PSStaff;

public class RuneList {
	private PSRune airRune;
	private PSRune armadylRune;
	private PSRune astralRune;
	private PSRune bloodRune;
	private PSRune bodyRune;
	private PSRune chaosRune;
	private PSRune cosmicRune;
	private PSRune deathRune;
	private PSRune dustRune;
	private PSRune earthRune;
	private PSRune fireRune;
	private PSRune lavaRune;
	private PSRune lawRune;
	private PSRune mindRune;
	private PSRune mistRune;
	private PSRune mudRune;
	private PSRune natureRune;
	private PSRune smokeRune;
	private PSRune soulRune;
	private PSRune steamRune;
	private PSRune waterRune;
	private HashMap<String, PSRune> runes;
	
	
	public RuneList() {
		StaffList allStaves = new StaffList();
		HashMap<String, PSStaff> staves = allStaves.getStaves();
		
		airRune = new PSRune("Air rune", 556);
		PSStaff[] airStaves = { 
				staves.get("AirStaff"),
				staves.get("AirBattle"),
				staves.get("AirMystic")
		};
		airRune.setAssociatedStaves(airStaves);
		
		armadylRune = new PSRune("Armadyl rune", 21733);
		PSStaff[] armadylStaves = { 
				staves.get("ArmadylBattle")
		};
		armadylRune.setAssociatedStaves(armadylStaves);
		
		astralRune = new PSRune("Astral rune", 9075);
		
		bloodRune = new PSRune("Blood rune", 565);
		
		bodyRune = new PSRune("Body rune", 559);
		
		chaosRune = new PSRune("Chaos rune", 562);
		
		cosmicRune = new PSRune("Cosmic rune", 564);
		
		deathRune = new PSRune("Death rune", 560);
		
		dustRune = new PSRune("Dust rune", 4696);
		
		earthRune = new PSRune("Earth rune", 556);
		PSStaff[] earthStaves = {
			staves.get("MudBattle"),
			staves.get("MudMystic"),
			staves.get("LavaBattle"),
			staves.get("LavaMystic"),
			staves.get("EarthStaff"),
			staves.get("EarthBattle"),
			staves.get("EarthMystic")
		};
		earthRune.setAssociatedStaves(earthStaves);
		
		fireRune = new PSRune("Fire rune", 554);
		PSStaff[] fireStaves = {
			staves.get("LavaBattle"),
			staves.get("LavaMystic"),
			staves.get("SteamBattle"),
			staves.get("SteamMystic"),
			staves.get("FireStaff"),
			staves.get("FireBattle"),
			staves.get("FireMystic")
		};
		fireRune.setAssociatedStaves(fireStaves);
		
		lavaRune = new PSRune("Lava rune", 557);
		PSStaff[] lavaStaves = {
			staves.get("LavaBattle"),
			staves.get("LavaMystic")
		};
		lavaRune.setAssociatedStaves(lavaStaves);
		
		lawRune = new PSRune("Law rune", 4699);
		
		mindRune = new PSRune("Mind rune", 558);
		
		mistRune = new PSRune("Mist rune", 4695);
		
		mudRune = new PSRune("Mud rune", 4698);
		PSStaff[] mudStaves = {
			staves.get("MudBattle"),
			staves.get("MudMystic")
		};
		mudRune.setAssociatedStaves(mudStaves);
		
		natureRune = new PSRune("Nature rune", 561);
		
		smokeRune = new PSRune("Smoke rune", 4697);
		
		soulRune = new PSRune("Soul rune", 566);
		
		steamRune = new PSRune("Steam rune", 4694);
		PSStaff[] steamStaves = {
			staves.get("SteamBattle"),
			staves.get("SteamMystic")
		};
		steamRune.setAssociatedStaves(steamStaves);
		
		waterRune = new PSRune("Water rune", 555);
		PSStaff[] waterStaves = {
			staves.get("MudBattle"),
			staves.get("MudMystic"),
			staves.get("SteamBattle"),
			staves.get("SteamMystic"),
			staves.get("WaterStaff"),
			staves.get("WaterBattle"),
			staves.get("WaterMystic")
		};
		waterRune.setAssociatedStaves(waterStaves);
		
		
		PSRune[] airRunes = { dustRune, mistRune, smokeRune	};
		airRune.setAssociatedRunes(airRunes);
		
		PSRune[] earthRunes = { dustRune, lavaRune, mudRune };
		earthRune.setAssociatedRunes(earthRunes);
		
		PSRune[] fireRunes = { lavaRune, steamRune, smokeRune };
		fireRune.setAssociatedRunes(fireRunes);
		
		PSRune[] waterRunes = { mistRune, mudRune, steamRune };
		waterRune.setAssociatedRunes(waterRunes);
		
		generateRunes();
	}


	public HashMap<String, PSRune> getRunes() {
		return runes;
	}


	public void setRunes(HashMap<String, PSRune> runes) {
		this.runes = runes;
	}
	
	private void generateRunes() {
		HashMap<String, PSRune> runes = new HashMap<String, PSRune> (21);
		
		runes.put("AirRune", airRune);
		runes.put("ArmadylRune", armadylRune);
		runes.put("AstralRune", astralRune);
		runes.put("BloodRune", bloodRune);
		runes.put("BodyRune", bodyRune);
		runes.put("ChaosRune", chaosRune);
		runes.put("CosmicRune", cosmicRune);
		runes.put("DeathRune", deathRune);
		runes.put("DustRune", dustRune);
		runes.put("EarthRune", earthRune);
		runes.put("FireRune", fireRune);
		runes.put("LavaRune", lavaRune);
		runes.put("LawRune", lawRune);
		runes.put("MindRune", mindRune);
		runes.put("MistRune", mistRune);
		runes.put("MudRune", mudRune);
		runes.put("NatureRune", natureRune);
		runes.put("SmokeRune", smokeRune);
		runes.put("SoulRune", soulRune);
		runes.put("SteamRune", steamRune);
		runes.put("WaterRune", waterRune);
		
		this.setRunes(runes);
	}
}

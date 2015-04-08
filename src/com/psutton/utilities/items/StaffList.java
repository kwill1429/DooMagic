package com.psutton.utilities.items;

import com.psutton.utilities.objects.PSStaff;

import java.util.HashMap;

public class StaffList {
	private PSStaff airStaff;
	private PSStaff airBattle;
	private PSStaff airMystic;
	private PSStaff armadylBattle;
	private PSStaff earthStaff;
	private PSStaff earthBattle;
	private PSStaff earthMystic;
	private PSStaff fireStaff;
	private PSStaff fireBattle;
	private PSStaff fireMystic;
	private PSStaff lavaBattle;
	private PSStaff lavaMystic;
	private PSStaff mudBattle;
	private PSStaff mudMystic;
	private PSStaff steamBattle;
	private PSStaff steamMystic;
	private PSStaff waterStaff;
	private PSStaff waterBattle;
	private PSStaff waterMystic;
	private HashMap<String, PSStaff> staves;

	public StaffList() {
		airStaff = new PSStaff("Staff of air", 1381);
		airBattle = new PSStaff("Air battlestaff", 1398);
		airMystic = new PSStaff("Mystic air staff", 1405);
		armadylBattle = new PSStaff("Armadyl battlestaff", 21777);
		earthStaff = new PSStaff("Staff of earth", 1385);
		earthBattle = new PSStaff("Earth battlestaff", 1399);
		earthMystic = new PSStaff("Mystic earth staff", 1407);
		fireStaff = new PSStaff("Staff of fire", 1387);
		fireBattle = new PSStaff("Fire battlestaff", 1393);
		fireMystic = new PSStaff("Mystic fire staff", 1401);
		lavaBattle = new PSStaff("Lava battlestaff", 3053);
		lavaMystic = new PSStaff("Mystic lava staff", 3054);
		mudBattle = new PSStaff("Mud battlestaff", 6562);
		mudMystic = new PSStaff("Mystic mud staff", 6563);
		steamBattle = new PSStaff("Steam battlestaff", 11736);
		steamMystic = new PSStaff("Mystic steam staff", 11738);
		waterStaff = new PSStaff("Staff of water", 1383);
		waterBattle = new PSStaff("Water battlestaff", 1395);
		waterMystic = new PSStaff("Mystic water staff", 1403);

		generateStaves();
	}

	public HashMap<String, PSStaff> getStaves() {
		return staves;
	}

	public void setStaves(HashMap<String, PSStaff> staves) {
		this.staves = staves;
	}

	private void generateStaves() {
		HashMap<String, PSStaff> staves = new HashMap<String, PSStaff>(19);

		staves.put("AirStaff", airStaff);
		staves.put("AirBattle", airBattle);
		staves.put("AirMystic", airMystic);

		staves.put ("ArmadylBattle", armadylBattle);

		staves.put("EarthStaff", earthStaff);
		staves.put("EarthBattle", earthBattle);
		staves.put("EarthMystic", earthMystic);

		staves.put("FireStaff", fireStaff);
		staves.put("FireBattle", fireBattle);
		staves.put("FireMystic", fireMystic);

		staves.put("LavaBattle", lavaBattle);
		staves.put("LavaMystic", lavaMystic);

		staves.put("MudBattle", mudBattle);
		staves.put("MudMystic", mudMystic);

		staves.put("SteamBattle", steamBattle);
		staves.put("SteamMystic", steamMystic);

		staves.put("WaterStaff", waterStaff);
		staves.put("WaterBattle", waterBattle);
		staves.put("WaterMystic", waterMystic);

		this.setStaves(staves);
	}
}

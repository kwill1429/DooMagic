package com.psutton.doomagic.tasks;

import java.util.Random;

import com.epicbot.api.concurrent.Task;
import com.epicbot.api.concurrent.node.Node;
import com.epicbot.api.rs3.methods.interactive.Players;
import com.epicbot.api.util.Time;
import com.psutton.doomagic.DooMagicGlobal;

public class AntiBanTask extends Node implements Task {
	private int antiBanCounter;

	@Override
	public void run() {
		boolean didHitAntiBan = true;
		this.antiBanCounter = DooMagicGlobal.antiBanCounter;

		if (antiBanCounter % 9 == 0) {
			System.out.println("Antiban % 9");
		} else if (antiBanCounter % 8 == 0) {
			System.out.println("Antiban % 8");
		} else if (antiBanCounter % 7 == 0) {
			System.out.println("Antiban % 7");
		} else if (antiBanCounter % 6 == 0) {
			System.out.println("Antiban % 6");
		} else if (antiBanCounter % 5 == 0) {
			System.out.println("Antiban % 5");
		} else if (antiBanCounter % 4 == 0) {
			System.out.println("Antiban % 4");
		} else if (antiBanCounter % 3 == 0) {
			System.out.println("Antiban % 3");
		} else {
			DooMagicGlobal.antiBanCounter ++;
		}

		DooMagicGlobal.scriptStatus = "Performing AntiBan";
		Time.sleep(3000);

		if (didHitAntiBan == true) {
			AntiBanTask.generateAntiBanCounter();
		}
	}

	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null && (DooMagicGlobal.numOfTimesCast == DooMagicGlobal.antiBanCounter)) {
			return true;
		}

		return false;
	}

	public static void generateAntiBanCounter() {
		Random random = new Random();
		DooMagicGlobal.antiBanCounter += random.nextInt(10) + 3;
	}
}

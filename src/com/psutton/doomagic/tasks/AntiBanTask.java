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
		// TODO Auto-generated method stub
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
			System.out.println("Antiban Else");
			this.antiBanCounter++;
			didHitAntiBan = false;
		}
		
		Time.sleep(2000);
		if (didHitAntiBan == true) {
			this.generateAntiBanCounter();
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if (Players.getLocal() != null && (DooMagicGlobal.numOfTimesCast == this.antiBanCounter)) {
			return true;
		}
		
		return false;
	}

	private void generateAntiBanCounter()
	{
		Random random = new Random();
		antiBanCounter += random.nextInt(20) + 1;
	}
}

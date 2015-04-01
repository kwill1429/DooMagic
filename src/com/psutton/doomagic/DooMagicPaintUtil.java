package com.psutton.doomagic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.util.Time;

public class DooMagicPaintUtil 
{
	private int startingLvl, startingXp, xpGained, lvlsGained;
	private long startTime = 0;
	private long runtime;
	private String scriptInfoString, runtimeString, statusString, lvlString, xpString, xpPerHrString, numOfTimesCast;
	
	private final Color frameColor = new Color(0, 0, 0, 175);
	private final int frameTopX = 0;
	private final int frameTopY = 200;
	private final int frameWidth = 500;
	private final int frameHeight = 150;
		
	private final Color textColor = new Color(0, 17, 255, 255);
	private final int textTopX = frameTopX + 150;
	private final int textTopY = frameTopY + 25;
	
	private final Font labelFont = new Font("Helvetica Neue", Font.PLAIN, 13);
	private final Font titleFont = new Font ("Helvetica Neue", Font.BOLD, 24);
	
	public void createPaint(Graphics g)
	{
		g.setColor(frameColor);
		g.fillRoundRect(frameTopX, frameTopY, frameWidth, frameHeight, 10, 10);
		
		this.gatherStats();
		
		g.setColor(textColor);
		g.setFont(titleFont);
		g.drawString(scriptInfoString, textTopX + 75, textTopY);
		
		g.setFont(labelFont);
		
		g.drawString(statusString, textTopX, textTopY + 40);
		g.drawString(runtimeString, textTopX, textTopY + 65);
		g.drawString(numOfTimesCast, textTopX, textTopY + 90);
		
		g.drawString(lvlString, textTopX + 175, textTopY + 40);
		g.drawString(xpString, textTopX + 175, textTopY + 65);
		g.drawString(xpString, textTopX + 175, textTopY + 90);
	}
	
	public void gatherStats()
	{
		lvlsGained = Skills.Skill.MAGIC.getCurrentLevel() - this.startingLvl;
		xpGained = Skills.Skill.MAGIC.getExperience() - this.startingXp;
		runtime = System.currentTimeMillis() - DooMagicGlobal.startTime;

		scriptInfoString = DooMagicGlobal.scriptName;
		runtimeString = "Runtime: "+Time.format(runtime);
		statusString = "Script status: "+DooMagicGlobal.scriptStatus;
		lvlString = "Magic Level: "+Skills.Skill.MAGIC.getCurrentLevel()+"("+lvlsGained+")";
		xpString = "XP Gained: "+xpGained;
		xpPerHrString = "XP/Hr: "+Time.getPerHour(xpGained, startTime);
		numOfTimesCast = "Num of Casts: "+DooMagicGlobal.numOfTimesCast;
	}
	
}

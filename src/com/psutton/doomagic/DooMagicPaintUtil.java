package com.psutton.doomagic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.epicbot.api.rs3.methods.tab.Skills;
import com.epicbot.api.util.SkillData;
import com.epicbot.api.util.Time;

public class DooMagicPaintUtil 
{
	private int xpGained, lvlsGained, xpPerHour;
	private long runtime;

	private final int frameTopX = 0;
	private final int frameTopY = 200;
	private final int frameWidth = 500;
	private final int frameHeight = 150;
	private final int textTopX = frameTopX + 150;
	private final int textTopY = frameTopY + 25;

	private String scriptInfoString, runtimeString, statusString, lvlString, xpString;
	private String xpPerHrString, numOfTimesCast, timeToLevelString;

	private final Color frameColor = new Color(0, 0, 0, 150);
	private final Color textColor = new Color(26, 73, 153, 255);
	private final Color versionColor = new Color(255, 255, 255, 255);

	private final Font versionFont = new Font("Helvetica Neue", Font.BOLD, 13);
	private final Font labelFont = new Font("Helvetica Neue", Font.PLAIN, 13);
	private final Font titleFont = new Font ("Helvetica Neue", Font.BOLD, 24);

	private final Image magicHat = this.getScriptImage();

	public void createPaint(Graphics g)
	{
		// Draws the background
		g.setColor(frameColor);
		g.fillRoundRect(frameTopX, frameTopY, frameWidth, frameHeight, 10, 10);

		this.gatherStats();

		if (magicHat != null) {
			g.drawImage(magicHat, frameTopX + 25, frameTopY + 30, 100, 89, null);
		}

		// Draws the script title
		g.setColor(textColor);
		g.setFont(titleFont);
		g.drawString(scriptInfoString, textTopX + 115, textTopY);

		// Draws the first column of stats
		g.setFont(labelFont);
		g.drawString(statusString, textTopX, textTopY + 25);
		g.drawString(runtimeString, textTopX, textTopY + 50);
		g.drawString(timeToLevelString, textTopX, textTopY + 75);
		g.drawString(numOfTimesCast, textTopX, textTopY + 100);


		// Draws the second column of stats
		g.drawString(lvlString, textTopX + 215, textTopY + 25);
		g.drawString(xpString, textTopX + 215, textTopY + 50);
		g.drawString(xpPerHrString, textTopX + 215, textTopY + 75);

		// Draws the version number
		g.setColor(versionColor);
		g.setFont(versionFont);
		g.drawString(DooMagicGlobal.scriptVersion, (frameTopX + frameWidth) - 50, (frameTopY + frameHeight) - 10);
	}

	public void gatherStats()
	{
		lvlsGained = SkillData.MAGIC.getLevelsGained();
		xpGained = SkillData.MAGIC.getXpGained();
		runtime = System.currentTimeMillis() - DooMagicGlobal.startTime;
		xpPerHour = SkillData.MAGIC.getXpPerHour(runtime);

		scriptInfoString = DooMagicGlobal.scriptName;

		runtimeString = "Runtime: " + Time.format(runtime);
		statusString = "Script status: " + DooMagicGlobal.scriptStatus;
		timeToLevelString = "TTL: " + SkillData.MAGIC.getTTL(runtime);
		numOfTimesCast = "Num of Casts: " + DooMagicGlobal.numOfTimesCast;

		lvlString = "Magic Level: "+ Skills.Skill.MAGIC.getCurrentLevel() + "(" + lvlsGained + ")";
		xpString = "XP Gained: " + xpGained;
		xpPerHrString = "XP/Hr: " + xpPerHour;

	}

	public Image getScriptImage() {		
		try {
			return ImageIO.read(new URL("http://i837.photobucket.com/albums/zz298/Heheurfunny01/MagicHat.png"));
		} catch (IOException e) {
			System.out.println("IOException: " + e);
			return null;
		}
	}
}

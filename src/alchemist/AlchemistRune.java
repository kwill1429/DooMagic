package alchemist;

public class AlchemistRune {
	private int numOfRunes;
	private int runeID;
	private boolean hasSubstitute;
	private boolean canUseStaff;
	private String runeName;
	
	public AlchemistRune(String runeName, int runeID, int numOfRunes, boolean hasSubstitute, boolean canUseStaff) {
		this.runeName = runeName;
		this.setRuneID(runeID);
		this.setNumOfRunes(numOfRunes);
		this.setHasSubstitute(hasSubstitute);
		this.setCanUseStaff(canUseStaff);
	}
	
	public int getNumOfRunes() {
		return numOfRunes;
	}
	
	public void setNumOfRunes(int numOfRunes) {
		this.numOfRunes = numOfRunes;
	}
	
	public int getRuneID() {
		return runeID;
	}
	
	public void setRuneID(int runeID) {
		this.runeID = runeID;
	}

	public String getRuneName() {
		return runeName;
	}

	public void setRuneName(String runeName) {
		this.runeName = runeName;
	}
	
	public boolean hasSubstitute() {
		return hasSubstitute;
	}

	public void setHasSubstitute(boolean hasSubstitute) {
		this.hasSubstitute = hasSubstitute;
	}

	public boolean canUseStaff() {
		return canUseStaff;
	}

	public void setCanUseStaff(boolean canUseStaff) {
		this.canUseStaff = canUseStaff;
	}

	@Override
	public String toString() {
		return this.runeName;
	}
	
	
}

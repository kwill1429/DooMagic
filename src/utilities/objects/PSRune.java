package utilities.objects;

public class PSRune extends PSItem {
	private int numOfRunes;
	private PSStaff[] associatedStaves;
	private PSRune[] associatedRunes;
	
	public PSRune(String runeName, int runeID) {
		super(runeName, runeID);
	}
	
	public int getNumOfRunes() {
		return numOfRunes;
	}
	
	public void setNumOfRunes(int numOfRunes) {
		this.numOfRunes = numOfRunes;
	}
	
	public PSStaff[] getAssociatedStaves() {
		return associatedStaves;
	}

	public void setAssociatedStaves(PSStaff[] associatedStaves) {
		this.associatedStaves = associatedStaves;
	}

	public PSRune[] getAssociatedRunes() {
		return associatedRunes;
	}

	public void setAssociatedRunes(PSRune[] associatedRunes) {
		this.associatedRunes = associatedRunes;
	}	
}

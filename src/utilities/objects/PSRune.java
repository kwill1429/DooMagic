package utilities.objects;

public class PSRune extends PSItem {
	private PSStaff[] associatedStaves;
	private PSRune[] associatedRunes;
	
	public PSRune(String runeName, int runeID) {
		super(runeName, runeID);
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

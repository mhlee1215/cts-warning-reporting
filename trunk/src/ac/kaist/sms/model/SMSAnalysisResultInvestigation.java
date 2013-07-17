package ac.kaist.sms.model;

/**
 * @author user
 * Investigation (A4) °á°ú
 */
public class SMSAnalysisResultInvestigation {
	/**
	 * Internal investigation È½¼ö
	 */
	private int internalInvestigationCount;
	
	/**
	 * Internal investigation Finding È½¼ö
	 */
	private int internalInvestigationFindingCount;
	/**
	 * External investigation È½¼ö
	 */
	private int externalInvestigationCount;
	/**
	 * External investigation Finding È½¼ö
	 */
	private int externalInvestigationFindingCount;

	//Initial/residual risk
	//corrective action
	private SMSAnalysisSheetModel iiSheet;
	private SMSAnalysisSheetModel eiSheet;
	public int getInternalInvestigationCount() {
		return internalInvestigationCount;
	}
	public void setInternalInvestigationCount(int internalInvestigationCount) {
		this.internalInvestigationCount = internalInvestigationCount;
	}
	public int getInternalInvestigationFindingCount() {
		return internalInvestigationFindingCount;
	}
	public void setInternalInvestigationFindingCount(
			int internalInvestigationFindingCount) {
		this.internalInvestigationFindingCount = internalInvestigationFindingCount;
	}
	public int getExternalInvestigationCount() {
		return externalInvestigationCount;
	}
	public void setExternalInvestigationCount(int externalInvestigationCount) {
		this.externalInvestigationCount = externalInvestigationCount;
	}
	public int getExternalInvestigationFindingCount() {
		return externalInvestigationFindingCount;
	}
	public void setExternalInvestigationFindingCount(
			int externalInvestigationFindingCount) {
		this.externalInvestigationFindingCount = externalInvestigationFindingCount;
	}
	public SMSAnalysisSheetModel getIiSheet() {
		return iiSheet;
	}
	public void setIiSheet(SMSAnalysisSheetModel iiSheet) {
		this.iiSheet = iiSheet;
	}
	public SMSAnalysisSheetModel getEiSheet() {
		return eiSheet;
	}
	public void setEiSheet(SMSAnalysisSheetModel eiSheet) {
		this.eiSheet = eiSheet;
	}
	
	@Override
	public String toString() {
		return "<<SMSAnalysisResultInvestigation>>\n\tinternalInvestigationCount="
				+ internalInvestigationCount
				+ "\n\tinternalInvestigationFindingCount="
				+ internalInvestigationFindingCount
				+ "\n\texternalInvestigationCount="
				+ externalInvestigationCount
				+ "\n\texternalInvestigationFindingCount="
				+ externalInvestigationFindingCount + "\n";
	}
}

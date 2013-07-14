package ac.kaist.sms.model;

/**
 * @author user
 * Audit (A3) °á°ú
 */
public class SMSAnalysisResultAudit {
	//Internal Audit È½¼ö
	private int internalAuditCount;
	//Internal Audit finding È½¼ö
	private int internalAuditFindingCount;
	//Internal Audit È½¼ö
	private int externalAuditCount;
	//External Audit finding È½¼ö
	private int externalAuditFindingCount;
	
	//initial/residual risk 
	//corrective action
	private SMSAnalysisSheetModel iaSheet;
	private SMSAnalysisSheetModel eaSheet;
	public int getInternalAuditCount() {
		return internalAuditCount;
	}
	public void setInternalAuditCount(int internalAuditCount) {
		this.internalAuditCount = internalAuditCount;
	}
	public int getInternalAuditFindingCount() {
		return internalAuditFindingCount;
	}
	public void setInternalAuditFindingCount(int internalAuditFindingCount) {
		this.internalAuditFindingCount = internalAuditFindingCount;
	}
	public int getExternalAuditCount() {
		return externalAuditCount;
	}
	public void setExternalAuditCount(int externalAuditCount) {
		this.externalAuditCount = externalAuditCount;
	}
	public int getExternalAuditFindingCount() {
		return externalAuditFindingCount;
	}
	public void setExternalAuditFindingCount(int externalAuditFindingCount) {
		this.externalAuditFindingCount = externalAuditFindingCount;
	}
	public SMSAnalysisSheetModel getIaSheet() {
		return iaSheet;
	}
	public void setIaSheet(SMSAnalysisSheetModel iaSheet) {
		this.iaSheet = iaSheet;
	}
	public SMSAnalysisSheetModel getEaSheet() {
		return eaSheet;
	}
	public void setEaSheet(SMSAnalysisSheetModel eaSheet) {
		this.eaSheet = eaSheet;
	}
	
	@Override
	public String toString() {
		return "<<SMSAnalysisResultAudit>>\n\tinternalAuditCount="
				+ internalAuditCount + "\n\tinternalAuditFindingCount="
				+ internalAuditFindingCount + "\n\texternalAuditCount="
				+ externalAuditCount + "\n\texternalAuditFindingCount="
				+ externalAuditFindingCount + "\n";
	}
}

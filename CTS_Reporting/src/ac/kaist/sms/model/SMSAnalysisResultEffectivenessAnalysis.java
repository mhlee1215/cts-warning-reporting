package ac.kaist.sms.model;

import java.util.Map;

/**
 * @author user
 * Effectiveness analysis (B2) 결과
 */
public class SMSAnalysisResultEffectivenessAnalysis {
	/**
	 * Effectiveness of 안전보고
	 */
	private Map<String, Map<String, Double> > effectivenssOfSafetyReport;
	/**
	 * Effectiveness of internal audit
	 */
	private Map<String, Map<String, Double> > effectivenssOfInternalAudit;
	/**
	 * Effectiveness of external audit
	 */
	private Map<String, Map<String, Double> > effectivenssOfExternalAudit;
	/**
	 * Effectiveness of internal investigation
	 */
	private Map<String, Map<String, Double> > effectivenssOfInternalInvestigation;
	/**
	 * Effectiveness of external investigation
	 */
	private Map<String, Map<String, Double> > effectivenssOfExternalInvestigation;
	public Map<String, Map<String, Double>> getEffectivenssOfSafetyReport() {
		return effectivenssOfSafetyReport;
	}
	public void setEffectivenssOfSafetyReport(
			Map<String, Map<String, Double>> effectivenssOfSafetyReport) {
		this.effectivenssOfSafetyReport = effectivenssOfSafetyReport;
	}
	public Map<String, Map<String, Double>> getEffectivenssOfInternalAudit() {
		return effectivenssOfInternalAudit;
	}
	public void setEffectivenssOfInternalAudit(
			Map<String, Map<String, Double>> effectivenssOfInternalAudit) {
		this.effectivenssOfInternalAudit = effectivenssOfInternalAudit;
	}
	public Map<String, Map<String, Double>> getEffectivenssOfExternalAudit() {
		return effectivenssOfExternalAudit;
	}
	public void setEffectivenssOfExternalAudit(
			Map<String, Map<String, Double>> effectivenssOfExternalAudit) {
		this.effectivenssOfExternalAudit = effectivenssOfExternalAudit;
	}
	public Map<String, Map<String, Double>> getEffectivenssOfInternalInvestigation() {
		return effectivenssOfInternalInvestigation;
	}
	public void setEffectivenssOfInternalInvestigation(
			Map<String, Map<String, Double>> effectivenssOfInternalInvestigation) {
		this.effectivenssOfInternalInvestigation = effectivenssOfInternalInvestigation;
	}
	public Map<String, Map<String, Double>> getEffectivenssOfExternalInvestigation() {
		return effectivenssOfExternalInvestigation;
	}
	public void setEffectivenssOfExternalInvestigation(
			Map<String, Map<String, Double>> effectivenssOfExternalInvestigation) {
		this.effectivenssOfExternalInvestigation = effectivenssOfExternalInvestigation;
	}
	@Override
	public String toString() {
		return "<<SMSAnalysisResultEffectivenessAnalysis>>\n\teffectivenssOfSafetyReport="
				+ effectivenssOfSafetyReport
				+ "\n\teffectivenssOfInternalAudit="
				+ effectivenssOfInternalAudit
				+ "\n\teffectivenssOfExternalAudit="
				+ effectivenssOfExternalAudit
				+ "\n\teffectivenssOfInternalInvestigation="
				+ effectivenssOfInternalInvestigation
				+ "\n\teffectivenssOfExternalInvestigation="
				+ effectivenssOfExternalInvestigation + "\n";
	}
}

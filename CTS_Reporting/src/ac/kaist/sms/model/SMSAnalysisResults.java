package ac.kaist.sms.model;

/**
 * @author mh.lee
 * 
 * SMS분석을 위한 모든 결과값들을 담고있는 결과 오브젝트 입니다.
 * 결과값의 출력등에 관련된 메서드를 포함하고 있습니다.
 *
 */

public class SMSAnalysisResults {
	
	/**
	 * 안전보고 (A1) 결과
	 */
	private SMSAnalysisResultSafetyReport safetyReportResult;
	/**
	 * Hazard (A2) 결과
	 */
	private SMSAnalysisResultHazard hazardResult;
	/**
	 * Audit (A3) 결과
	 */
	private SMSAnalysisResultAudit auditResult;
	/**
	 * Investigation (A4) 결과
	 */
	private SMSAnalysisResultInvestigation investigationResult;
	/**
	 * Training (A5) 결과
	 */
	private SMSAnalysisResultTraining trainingResult;
	/**
	 * Classification (B3) 결과
	 */
	private SMSAnalysisResultClassification classificationResult;
	/**
	 * Monitoring and Trend analysis (B1) 결과
	 */
	private SMSAnalysisResultMonitoringAndTrendAnalysis monitoringAndTrendAnalysisResult;
	/**
	 * Effectiveness analysis (B2) 결과
	 */
	private SMSAnalysisResultEffectivenessAnalysis effectivenessAnalysisResult;
	public SMSAnalysisResultSafetyReport getSafetyReportResult() {
		return safetyReportResult;
	}
	public void setSafetyReportResult(
			SMSAnalysisResultSafetyReport safetyReportResult) {
		this.safetyReportResult = safetyReportResult;
	}
	public SMSAnalysisResultHazard getHazardResult() {
		return hazardResult;
	}
	public void setHazardResult(SMSAnalysisResultHazard hazardResult) {
		this.hazardResult = hazardResult;
	}
	public SMSAnalysisResultAudit getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(SMSAnalysisResultAudit auditResult) {
		this.auditResult = auditResult;
	}
	public SMSAnalysisResultInvestigation getInvestigationResult() {
		return investigationResult;
	}
	public void setInvestigationResult(
			SMSAnalysisResultInvestigation investigationResult) {
		this.investigationResult = investigationResult;
	}
	public SMSAnalysisResultTraining getTrainingResult() {
		return trainingResult;
	}
	public void setTrainingResult(SMSAnalysisResultTraining trainingResult) {
		this.trainingResult = trainingResult;
	}
	public SMSAnalysisResultClassification getClassificationResult() {
		return classificationResult;
	}
	public void setClassificationResult(
			SMSAnalysisResultClassification classificationResult) {
		this.classificationResult = classificationResult;
	}
	public SMSAnalysisResultMonitoringAndTrendAnalysis getMonitoringAndTrendAnalysisResult() {
		return monitoringAndTrendAnalysisResult;
	}
	public void setMonitoringAndTrendAnalysisResult(
			SMSAnalysisResultMonitoringAndTrendAnalysis monitoringAndTrendAnalysisResult) {
		this.monitoringAndTrendAnalysisResult = monitoringAndTrendAnalysisResult;
	}
	public SMSAnalysisResultEffectivenessAnalysis getEffectivenessAnalysisResult() {
		return effectivenessAnalysisResult;
	}
	public void setEffectivenessAnalysisResult(
			SMSAnalysisResultEffectivenessAnalysis effectivenessAnalysisResult) {
		this.effectivenessAnalysisResult = effectivenessAnalysisResult;
	}
	@Override
	public String toString() {
		return "<<SMSAnalysisResults>>\n\tsafetyReportResult="
				+ safetyReportResult + "\n\thazardResult=" + hazardResult
				+ "\n\tauditResult=" + auditResult + "\n\tinvestigationResult="
				+ investigationResult + "\n\ttrainingResult=" + trainingResult
				+ "\n\tclassificationResult=" + classificationResult
				+ "\n\tmonitoringAndTrendAnalysisResult="
				+ monitoringAndTrendAnalysisResult
				+ "\n\teffectivenessAnalysisResult="
				+ effectivenessAnalysisResult + "\n";
	}
}

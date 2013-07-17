package ac.kaist.sms.model;

/**
 * @author mh.lee
 * 
 * SMS�м��� ���� ��� ��������� ����ִ� ��� ������Ʈ �Դϴ�.
 * ������� ��µ ���õ� �޼��带 �����ϰ� �ֽ��ϴ�.
 *
 */

public class SMSAnalysisResults {
	
	/**
	 * �������� (A1) ���
	 */
	private SMSAnalysisResultSafetyReport safetyReportResult;
	/**
	 * Hazard (A2) ���
	 */
	private SMSAnalysisResultHazard hazardResult;
	/**
	 * Audit (A3) ���
	 */
	private SMSAnalysisResultAudit auditResult;
	/**
	 * Investigation (A4) ���
	 */
	private SMSAnalysisResultInvestigation investigationResult;
	/**
	 * Training (A5) ���
	 */
	private SMSAnalysisResultTraining trainingResult;
	/**
	 * Classification (B3) ���
	 */
	private SMSAnalysisResultClassification classificationResult;
	/**
	 * Monitoring and Trend analysis (B1) ���
	 */
	private SMSAnalysisResultMonitoringAndTrendAnalysis monitoringAndTrendAnalysisResult;
	/**
	 * Effectiveness analysis (B2) ���
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

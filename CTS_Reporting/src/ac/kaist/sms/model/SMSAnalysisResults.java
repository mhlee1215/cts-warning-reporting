package ac.kaist.sms.model;

/**
 * @author mh.lee
 * 
 * SMS분석을 위한 모든 결과값들을 담고있는 결과 오브젝트 입니다.
 * 결과값의 출력등에 관련된 함수를 포함하고 있습니다.
 *
 */

public class SMSAnalysisResults {
	
	private int volunteerReportCount;							//자율 보고 횟수
	private int serveyCount;									//Survey 횟수
	private int serveyVolunteerCount;							//Survey 참여자 수
	private int accidentFrequency;								//
	private int seriousIncidentFrequency;						//
	private int incidentFrequency;								//
	private int veryImportantIndicatorFrequency;				//
	private int averageImportantIndicatorFrequency;				//
	private int initialResidualRisk;							//
	private int correctiveAction;								//
	private int hazardCount;									//
	private int accidentTrend;									//
	private int seriousIncidentTrend;							//	
	private int incidentTrend;									//
	private int veryImportantIndicatorTrend;					//
	private int averageImporantIndicatorTrend;					//
	private int topBottomFiveOccurrence;						//
	
	private int internalAuditCount;								//
	private int internalAuditFindingCount;						//
	private int externalAuditCount;								//
	private int externalAuditFindingCount;						//
	
	private int intialResidualRisk;								//
	private int correctiveAction2;								//
	
	private int effectivenessOfSatetyReport;					//
	private int effectivenessOfInternalAudit;					//
	private int effectivenessOfExternalAudit;					//
	private int effectivenessOfInternalInvestigation;
	private int effectivenessOfExternalInvestigation;
	
	private int internalInvestigationCount;
	private int internalInvestigationFindingCount;
	private int externalInvestigationCount;
	private int externalInvestigationFindingCount;
	
	private int ContinuationCourese;
	private int RecurrentTraining;
	private double TrainingScore;
	

	public SMSAnalysisResults() {
		// TODO Auto-generated constructor stub
	}


	public int getVolunteerReportCount() {
		return volunteerReportCount;
	}


	public void setVolunteerReportCount(int volunteerReportCount) {
		this.volunteerReportCount = volunteerReportCount;
	}


	public int getServeyCount() {
		return serveyCount;
	}


	public void setServeyCount(int serveyCount) {
		this.serveyCount = serveyCount;
	}


	public int getServeyVolunteerCount() {
		return serveyVolunteerCount;
	}


	public void setServeyVolunteerCount(int serveyVolunteerCount) {
		this.serveyVolunteerCount = serveyVolunteerCount;
	}


	


	public int getVeryImportantIndicatorFrequency() {
		return veryImportantIndicatorFrequency;
	}


	public void setVeryImportantIndicatorFrequency(
			int veryImportantIndicatorFrequency) {
		this.veryImportantIndicatorFrequency = veryImportantIndicatorFrequency;
	}


	public int getAverageImportantIndicatorFrequency() {
		return averageImportantIndicatorFrequency;
	}


	public void setAverageImportantIndicatorFrequency(
			int averageImportantIndicatorFrequency) {
		this.averageImportantIndicatorFrequency = averageImportantIndicatorFrequency;
	}


	public int getInitialResidualRisk() {
		return initialResidualRisk;
	}


	public void setInitialResidualRisk(int initialResidualRisk) {
		this.initialResidualRisk = initialResidualRisk;
	}


	public int getCorrectiveAction() {
		return correctiveAction;
	}


	public void setCorrectiveAction(int correctiveAction) {
		this.correctiveAction = correctiveAction;
	}


	public int getHazardCount() {
		return hazardCount;
	}


	public void setHazardCount(int hazardCount) {
		this.hazardCount = hazardCount;
	}


	public int getAccidentTrend() {
		return accidentTrend;
	}


	public void setAccidentTrend(int accidentTrend) {
		this.accidentTrend = accidentTrend;
	}


	public int getSeriousIncidentTrend() {
		return seriousIncidentTrend;
	}


	public void setSeriousIncidentTrend(int seriousIncidentTrend) {
		this.seriousIncidentTrend = seriousIncidentTrend;
	}


	public int getIncidentTrend() {
		return incidentTrend;
	}


	public void setIncidentTrend(int incidentTrend) {
		this.incidentTrend = incidentTrend;
	}


	public int getVeryImportantIndicatorTrend() {
		return veryImportantIndicatorTrend;
	}


	public void setVeryImportantIndicatorTrend(int veryImportantIndicatorTrend) {
		this.veryImportantIndicatorTrend = veryImportantIndicatorTrend;
	}


	public int getAverageImporantIndicatorTrend() {
		return averageImporantIndicatorTrend;
	}


	public void setAverageImporantIndicatorTrend(int averageImporantIndicatorTrend) {
		this.averageImporantIndicatorTrend = averageImporantIndicatorTrend;
	}


	public int getTopBottomFiveOccurrence() {
		return topBottomFiveOccurrence;
	}


	public void setTopBottomFiveOccurrence(int topBottomFiveOccurrence) {
		this.topBottomFiveOccurrence = topBottomFiveOccurrence;
	}


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


	public int getIntialResidualRisk() {
		return intialResidualRisk;
	}


	public void setIntialResidualRisk(int intialResidualRisk) {
		this.intialResidualRisk = intialResidualRisk;
	}


	public int getCorrectiveAction2() {
		return correctiveAction2;
	}


	public void setCorrectiveAction2(int correctiveAction2) {
		this.correctiveAction2 = correctiveAction2;
	}


	public int getEffectivenessOfSatetyReport() {
		return effectivenessOfSatetyReport;
	}


	public void setEffectivenessOfSatetyReport(int effectivenessOfSatetyReport) {
		this.effectivenessOfSatetyReport = effectivenessOfSatetyReport;
	}


	public int getEffectivenessOfInternalAudit() {
		return effectivenessOfInternalAudit;
	}


	public void setEffectivenessOfInternalAudit(int effectivenessOfInternalAudit) {
		this.effectivenessOfInternalAudit = effectivenessOfInternalAudit;
	}


	public int getEffectivenessOfExternalAudit() {
		return effectivenessOfExternalAudit;
	}


	public void setEffectivenessOfExternalAudit(int effectivenessOfExternalAudit) {
		this.effectivenessOfExternalAudit = effectivenessOfExternalAudit;
	}


	public int getEffectivenessOfInternalInvestigation() {
		return effectivenessOfInternalInvestigation;
	}


	public void setEffectivenessOfInternalInvestigation(
			int effectivenessOfInternalInvestigation) {
		this.effectivenessOfInternalInvestigation = effectivenessOfInternalInvestigation;
	}


	public int getEffectivenessOfExternalInvestigation() {
		return effectivenessOfExternalInvestigation;
	}


	public void setEffectivenessOfExternalInvestigation(
			int effectivenessOfExternalInvestigation) {
		this.effectivenessOfExternalInvestigation = effectivenessOfExternalInvestigation;
	}


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


	public int getContinuationCourese() {
		return ContinuationCourese;
	}


	public void setContinuationCourese(int continuationCourese) {
		ContinuationCourese = continuationCourese;
	}


	public int getRecurrentTraining() {
		return RecurrentTraining;
	}


	public void setRecurrentTraining(int recurrentTraining) {
		RecurrentTraining = recurrentTraining;
	}


	public double getTrainingScore() {
		return TrainingScore;
	}


	public void setTrainingScore(double trainingScore) {
		TrainingScore = trainingScore;
	}


	public int getAccidentFrequency() {
		return accidentFrequency;
	}


	public void setAccidentFrequency(int accidentFrequency) {
		this.accidentFrequency = accidentFrequency;
	}


	public int getSeriousIncidentFrequency() {
		return seriousIncidentFrequency;
	}


	public void setSeriousIncidentFrequency(int seriousIncidentFrequency) {
		this.seriousIncidentFrequency = seriousIncidentFrequency;
	}


	public int getIncidentFrequency() {
		return incidentFrequency;
	}


	public void setIncidentFrequency(int incidentFrequency) {
		this.incidentFrequency = incidentFrequency;
	}


	@Override
	public String toString() {
		return "SMSAnalysisResults [volunteerReportCount="
				+ volunteerReportCount + ", serveyCount=" + serveyCount
				+ ", serveyVolunteerCount=" + serveyVolunteerCount
				+ ", accidentFrequency=" + accidentFrequency
				+ ", seriousIncidentFrequency=" + seriousIncidentFrequency
				+ ", incidentFrequency=" + incidentFrequency
				+ ", veryImportantIndicatorFrequency="
				+ veryImportantIndicatorFrequency
				+ ", averageImportantIndicatorFrequency="
				+ averageImportantIndicatorFrequency + ", initialResidualRisk="
				+ initialResidualRisk + ", correctiveAction="
				+ correctiveAction + ", hazardCount=" + hazardCount
				+ ", accidentTrend=" + accidentTrend
				+ ", seriousIncidentTrend=" + seriousIncidentTrend
				+ ", incidentTrend=" + incidentTrend
				+ ", veryImportantIndicatorTrend="
				+ veryImportantIndicatorTrend
				+ ", averageImporantIndicatorTrend="
				+ averageImporantIndicatorTrend + ", topBottomFiveOccurrence="
				+ topBottomFiveOccurrence + ", internalAuditCount="
				+ internalAuditCount + ", internalAuditFindingCount="
				+ internalAuditFindingCount + ", externalAuditCount="
				+ externalAuditCount + ", externalAuditFindingCount="
				+ externalAuditFindingCount + ", intialResidualRisk="
				+ intialResidualRisk + ", correctiveAction2="
				+ correctiveAction2 + ", effectivenessOfSatetyReport="
				+ effectivenessOfSatetyReport
				+ ", effectivenessOfInternalAudit="
				+ effectivenessOfInternalAudit
				+ ", effectivenessOfExternalAudit="
				+ effectivenessOfExternalAudit
				+ ", effectivenessOfInternalInvestigation="
				+ effectivenessOfInternalInvestigation
				+ ", effectivenessOfExternalInvestigation="
				+ effectivenessOfExternalInvestigation
				+ ", internalInvestigationCount=" + internalInvestigationCount
				+ ", internalInvestigationFindingCount="
				+ internalInvestigationFindingCount
				+ ", externalInvestigationCount=" + externalInvestigationCount
				+ ", externalInvestigationFindingCount="
				+ externalInvestigationFindingCount + ", ContinuationCourese="
				+ ContinuationCourese + ", RecurrentTraining="
				+ RecurrentTraining + ", TrainingScore=" + TrainingScore + "]";
	}



	
	
	
	

}

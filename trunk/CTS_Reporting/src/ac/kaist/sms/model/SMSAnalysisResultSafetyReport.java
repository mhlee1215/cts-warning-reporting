package ac.kaist.sms.model;


/**
 * @author user
 * 안전보고 (A1) 결과
 */
public class SMSAnalysisResultSafetyReport {
	//자율 보고 횟수
	private int volunteerReportCount;
	//Survey 횟수
	private int surveyCount;
	//Survey 참여자 수
	private int surveyParticipantCount;
	//Frequency of occurrenses
	private SMSAnalysisSheetModel nrSheet;
	private SMSAnalysisSheetModel vrSheet;
	private SMSAnalysisSheetModel surveySheet;
	
	public int getVolunteerReportCount() {
		return volunteerReportCount;
	}
	public void setVolunteerReportCount(int volunteerReportCount) {
		this.volunteerReportCount = volunteerReportCount;
	}
	public int getSurveyCount() {
		return surveyCount;
	}
	public void setSurveyCount(int surveyCount) {
		this.surveyCount = surveyCount;
	}
	public int getSurveyParticipantCount() {
		return surveyParticipantCount;
	}
	public void setSurveyParticipantCount(int surveyParticipantCount) {
		this.surveyParticipantCount = surveyParticipantCount;
	}
	public SMSAnalysisSheetModel getNrSheet() {
		return nrSheet;
	}
	public void setNrSheet(SMSAnalysisSheetModel nrSheet) {
		this.nrSheet = nrSheet;
	}
	public SMSAnalysisSheetModel getVrSheet() {
		return vrSheet;
	}
	public void setVrSheet(SMSAnalysisSheetModel vrSheet) {
		this.vrSheet = vrSheet;
	}
	public SMSAnalysisSheetModel getSurveySheet() {
		return surveySheet;
	}
	public void setSurveySheet(SMSAnalysisSheetModel surveySheet) {
		this.surveySheet = surveySheet;
	}
	@Override
	public String toString() {
		return "<<SMSAnalysisResultSafetyReport>>\n\tvolunteerReportCount="
				+ volunteerReportCount + "\n\tsurveyCount=" + surveyCount
				+ "\n\tsurveyParticipantCount=" + surveyParticipantCount + "\n";
	}
}

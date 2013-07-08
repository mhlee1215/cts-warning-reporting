package ac.kaist.cts.service;

import java.util.List;

import ac.kaist.cts.domain.Report;

public interface ReportService {
	public int 			createReport(Report report);
	public List<Report> readReportsAll();
	public List<Report> readReporstByUser(Report report);
	public Report 		readReport(Report report);
	public int 			editReport(Report report);
	public int 			deleteReport(Report report);
	
	public List<Report> readReportListReviewAll();
	public List<Report> readReportListReviewReview();
	public List<Report> readReportListReviewAccepted();
	public List<Report> readReportListReviewRejected();
	public List<Report> readReportListReviewInvestigation();
	public List<Report> readReportListReviewRegistered();
	
	public List<Report> readReportListHazardIdentificationReportsToIdentify();
	public List<Report> readReportListHazardIdentificationIdentifiedReports();
	
	public List<Report> readReportListRiskAnalysisHazardToBeAnalyzed();
	public List<Report> readReportListRiskAnalysisAnalyzedHazards();
	
	public List<Report> readReportListRiskAssessmentHazardsToBeAssessed();
	public List<Report> readReportListRiskAssessmentAssessedHazards();
	
	public List<Report> readReportListMitigationHazardsToBeMitigated();
	public List<Report> readReportListMitigationMitigatedHazards();
	
	
}

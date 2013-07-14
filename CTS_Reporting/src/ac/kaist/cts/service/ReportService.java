package ac.kaist.cts.service;

import java.util.List;

import ac.kaist.cts.domain.AircraftInfo;
import ac.kaist.cts.domain.FlightInfo;
import ac.kaist.cts.domain.Report;
import ac.kaist.cts.domain.ReportItem;

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
	
	
	
	//Aircraft Information
	public void createAircraftInformation(AircraftInfo aircraftInformation);
	public void updateAircraftInformation(AircraftInfo aircraftInformation);
	public void deleteAircraftInformation(AircraftInfo aircraftInformation);
	public List<AircraftInfo> readAircraftInformationList();
	public AircraftInfo readAIrcraftInformation(AircraftInfo aircraftInformation);
	
	//Flight Information
	public List<FlightInfo> readFLightInfoList();
	public FlightInfo readFlightInfo(FlightInfo userInfo);
	public void createFlightInfo(FlightInfo userInfo);
	public void deleteFlightInfo(FlightInfo userInfo);
	public void updateFlightInfo(FlightInfo userInfo);
	
	//Report
	public void updateReport(Report report);
	//Report Item
	public List<ReportItem> readReportItemList(ReportItem reportItem);
	public ReportItem readReportItem(ReportItem reportItem);
	public void createReportItem(ReportItem reportItem);
	public void deleteReportItem(ReportItem reportItem);
	public void updateReportItem(ReportItem reportItem);
	
}

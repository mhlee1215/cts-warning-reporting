package ac.kaist.cts.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ac.kaist.cts.domain.AircraftInfo;
import ac.kaist.cts.domain.AttachedItem;
import ac.kaist.cts.domain.Control;
import ac.kaist.cts.domain.FlightInfo;
import ac.kaist.cts.domain.Hazard;
import ac.kaist.cts.domain.Likelihood;
import ac.kaist.cts.domain.Report;
import ac.kaist.cts.domain.ReportItem;
import ac.kaist.cts.domain.ReportParent;
import ac.kaist.cts.domain.RiskOwner;
import ac.kaist.cts.domain.SelectItem;
import ac.kaist.cts.domain.Severity;

public interface ReportService {
	public int 			createReport(Report report);
	public List<Report> readReportsAll();
	public List<Report> readReporstByUser(Report report);
	public Report 		readReport(Report report);
	public int 			editReport(Report report);
	public int 			deleteReport(Report report);
	
	public List<Report> readReportList(Report report);
	public List<Report> readReportChildrenList(Report report);
	
	public List<Report> readReportListReviewAll();
	public List<Report> readReportListReviewReview();
	public List<Report> readReportListReviewAccepted();
	public List<Report> readReportListReviewRejected();
	public List<Report> readReportListReviewInvestigation();
	public List<Report> readReportListReviewRegistered();
	
	public List<Likelihood> readLikelihoodList(int year);
	public List<Severity> readSeverityList(int year);
	public List<Control> readExistingControlList(int year);
	public List<Control> readNewControlList(int year);
	
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
	public List<FlightInfo> readFLightInfoList(FlightInfo flightInfo);
	public FlightInfo readFlightInfo(FlightInfo userInfo);
	public void createFlightInfo(FlightInfo userInfo);
	public void deleteFlightInfo(FlightInfo userInfo);
	public void updateFlightInfo(FlightInfo userInfo);
	
	//Report
	public void updateReport(Report report);
	
	public void updateReportItemBasic(Report rp, HttpServletRequest request);
	public void updateReportItem(ReportItem ri, HttpServletRequest request, String type);
	public void updateReportItemReview(ReportItem ri, HttpServletRequest request, String type);
	
	//Report Item
	public List<ReportItem> readReportItemList(ReportItem reportItem);
	public List<ReportItem> readReportItemListAll(ReportItem reportItem);
	public ReportItem readReportItem(ReportItem reportItem);
	public void createReportItem(ReportItem reportItem);
	public void deleteReportItem(ReportItem reportItem);
	public void updateReportItem(ReportItem reportItem);
	
	public List<AttachedItem> readAttachedItemList(AttachedItem attachedItem);
	public AttachedItem readAttachedItem(AttachedItem attachedItem);
	public void createAttachedItem(AttachedItem attachedItem);
	public void deleteAttachedItem(AttachedItem attachedItem);
	public void updateAttachedItem(AttachedItem attachedItem);
	
	public List<Hazard> readHazardList(Hazard hazard);
	public List<Hazard> readHazardPureList(Hazard hazard);
	public Integer readHazardTotalCount(Hazard hazard);
	public Hazard readHazard(Hazard hazard);
	public void createHazard(Hazard hazard);
	public void deleteHazard(Hazard hazard);
	public void updateHazard(Hazard hazard);
	public void hazardViewContentUpdate(Hazard hazard, HttpServletRequest request);
	public void managementDetailRiskAnalysisUpdate(Hazard hazard, HttpServletRequest request, String type);	
	public void managementDetailRiskAssessmentUpdate(Hazard hazard, HttpServletRequest request);
	public void managementDetailMitigationUpdate(Hazard hazard, HttpServletRequest request);
	
	public List<ReportParent> readReportParentList(ReportParent reportParent);
	public ReportParent readReportParent(ReportParent reportParent);
	public void createReportParent(ReportParent reportParent);
	public void deleteReportParent(ReportParent reportParent);
	public void updateReportParent(ReportParent reportParent);
	
	List<SelectItem> readSelectItemList(SelectItem item);
	SelectItem readSelectItem(SelectItem item);
	
	public List<RiskOwner> readRiskOwnerList(RiskOwner riskOwner);
	public RiskOwner readRiskOwner(RiskOwner riskOwner);
	public void createRiskOwner(RiskOwner riskOwner);
	public void deleteRiskOwner(RiskOwner riskOwner);
	public void updateRiskOwner(RiskOwner riskOwner);
}

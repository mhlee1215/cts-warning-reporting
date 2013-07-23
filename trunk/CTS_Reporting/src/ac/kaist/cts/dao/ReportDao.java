package ac.kaist.cts.dao;

import java.util.List;

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
import ac.kaist.cts.domain.Severity;

public interface ReportDao {
	public List<Report> readReportListReview();
	public List<Report> readReportListHazardIdentification();
	public List<Report> readReportListRiskAnalysis();
	public List<Report> readReportListRiskAssessment();
	public List<Report> readReportListMitigation();
	
	public List<Likelihood> readLikelihoodList(int year);
	public List<Severity> readSeverityList(int year);
	public List<Control> readExistingControlList(int year);
	public List<Control> readNewControlList(int year);
	
	public void createAircraftInformation(AircraftInfo aircraftInformation);
	public void updateAircraftInformation(AircraftInfo aircraftInformation);
	public void deleteAircraftInformation(AircraftInfo aircraftInformation);
	public List<AircraftInfo> readAircraftInformationList();
	public AircraftInfo readAIrcraftInformation(AircraftInfo aircraftInformation);
	
	public List<FlightInfo> readFLightInfoList(FlightInfo flightInfo);
	public FlightInfo readFlightInfo(FlightInfo userInfo);
	public void createFlightInfo(FlightInfo userInfo);
	public void deleteFlightInfo(FlightInfo userInfo);
	public void updateFlightInfo(FlightInfo userInfo);
	
	public List<Report> readReportList(Report report);
	public List<Report> readReportChildrenList(Report report);
	public Report readReport(Report report);
	public Report readReportWorstSituation(Report report);
	public Report readReportHighestPriority(Report report);
	public void createReport(Report report);
	public void deleteReport(Report report);
	public void updateReport(Report report);
	
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
	
	public List<ReportParent> readReportParentList(ReportParent reportParent);
	public ReportParent readReportParent(ReportParent reportParent);
	public void createReportParent(ReportParent reportParent);
	public void deleteReportParent(ReportParent reportParent);
	public void updateReportParent(ReportParent reportParent);
	
	public List<RiskOwner> readRiskOwnerList(RiskOwner riskOwner);
	public RiskOwner readRiskOwner(RiskOwner riskOwner);
	public void createRiskOwner(RiskOwner riskOwner);
	public void deleteRiskOwner(RiskOwner riskOwner);
	public void updateRiskOwner(RiskOwner riskOwner);
}

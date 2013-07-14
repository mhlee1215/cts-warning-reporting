package ac.kaist.cts.dao;

import java.util.List;

import ac.kaist.cts.domain.AircraftInfo;
import ac.kaist.cts.domain.FlightInfo;
import ac.kaist.cts.domain.Report;
import ac.kaist.cts.domain.ReportItem;

public interface ReportDao {
	public List<Report> readReportListReview();
	public List<Report> readReportListHazardIdentification();
	public List<Report> readReportListRiskAnalysis();
	public List<Report> readReportListRiskAssessment();
	public List<Report> readReportListMitigation();
	
	public void createAircraftInformation(AircraftInfo aircraftInformation);
	public void updateAircraftInformation(AircraftInfo aircraftInformation);
	public void deleteAircraftInformation(AircraftInfo aircraftInformation);
	public List<AircraftInfo> readAircraftInformationList();
	public AircraftInfo readAIrcraftInformation(AircraftInfo aircraftInformation);
	
	public List<FlightInfo> readFLightInfoList();
	public FlightInfo readFlightInfo(FlightInfo userInfo);
	public void createFlightInfo(FlightInfo userInfo);
	public void deleteFlightInfo(FlightInfo userInfo);
	public void updateFlightInfo(FlightInfo userInfo);
	
	public Report readReport(Report report);
	public void createReport(Report report);
	public void deleteReport(Report report);
	public void updateReport(Report report);
	
	public List<ReportItem> readReportItemList(ReportItem reportItem);
	public ReportItem readReportItem(ReportItem reportItem);
	public void createReportItem(ReportItem reportItem);
	public void deleteReportItem(ReportItem reportItem);
	public void updateReportItem(ReportItem reportItem);
}

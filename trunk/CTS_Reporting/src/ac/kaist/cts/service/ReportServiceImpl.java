package ac.kaist.cts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kaist.cts.dao.ReportDao;
import ac.kaist.cts.domain.AircraftInfo;
import ac.kaist.cts.domain.FlightInfo;
import ac.kaist.cts.domain.Report;
import ac.kaist.cts.domain.ReportItem;

@Service
public class ReportServiceImpl implements ReportService {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ReportDao		reportDao;
	
	@Override
	public int createReport(Report report) {
		reportDao.createReport(report);
		return 0;
	}

	@Override
	public List<Report> readReportsAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReporstByUser(Report report) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report readReport(Report report) {
		// TODO Auto-generated method stub
		return reportDao.readReport(report);
	}

	@Override
	public int editReport(Report report) {
		reportDao.updateReport(report);
		return 0;
	}

	@Override
	public int deleteReport(Report report) {
		reportDao.deleteReport(report);
		return 0;
	}
	
	@Override
	public void updateReport(Report report) {
		reportDao.updateReport(report);
	}

	@Override
	public List<Report> readReportListReviewAll() {
		// TODO Auto-generated method stub
		return reportDao.readReportListReview();
	}

	@Override
	public List<Report> readReportListReviewReview() {
		// TODO Auto-generated method stub
		
		List<Report> listAll = reportDao.readReportListReview();
		List<Report> filteredList = new ArrayList<Report>();
		
		for (Report report : listAll){
			
		}
		
		
		return null;
	}

	@Override
	public List<Report> readReportListReviewAccepted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListReviewRejected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListReviewInvestigation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListReviewRegistered() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListHazardIdentificationReportsToIdentify() {
		// TODO Auto-generated method stub
		return reportDao.readReportListHazardIdentification();
	}

	@Override
	public List<Report> readReportListHazardIdentificationIdentifiedReports() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListRiskAnalysisHazardToBeAnalyzed() {
		// TODO Auto-generated method stub
		return reportDao.readReportListRiskAnalysis();
	}

	@Override
	public List<Report> readReportListRiskAnalysisAnalyzedHazards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListRiskAssessmentHazardsToBeAssessed() {
		// TODO Auto-generated method stub
		return reportDao.readReportListRiskAssessment();
	}

	@Override
	public List<Report> readReportListRiskAssessmentAssessedHazards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> readReportListMitigationHazardsToBeMitigated() {
		// TODO Auto-generated method stub
		return reportDao.readReportListMitigation();
	}

	@Override
	public List<Report> readReportListMitigationMitigatedHazards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createAircraftInformation(
			AircraftInfo aircraftInformation) {
		reportDao.createAircraftInformation(aircraftInformation);
	}

	@Override
	public void updateAircraftInformation(
			AircraftInfo aircraftInformation) {
		// TODO Auto-generated method stub
		reportDao.updateAircraftInformation(aircraftInformation);
	}

	@Override
	public void deleteAircraftInformation(
			AircraftInfo aircraftInformation) {
		reportDao.deleteAircraftInformation(aircraftInformation);
	}

	@Override
	public List<AircraftInfo> readAircraftInformationList() {
		return reportDao.readAircraftInformationList();
	}

	@Override
	public AircraftInfo readAIrcraftInformation(AircraftInfo aircraftInformation) {
		return reportDao.readAIrcraftInformation(aircraftInformation);
	}

	@Override
	public FlightInfo readFlightInfo(FlightInfo userInfo) {
		// TODO Auto-generated method stub
		return reportDao.readFlightInfo(userInfo);
	}

	@Override
	public void createFlightInfo(FlightInfo userInfo) {
		// TODO Auto-generated method stub
		reportDao.createFlightInfo(userInfo);
	}

	@Override
	public void deleteFlightInfo(FlightInfo userInfo) {
		// TODO Auto-generated method stub
		reportDao.deleteFlightInfo(userInfo);
	}

	@Override
	public void updateFlightInfo(FlightInfo userInfo) {
		// TODO Auto-generated method stub
		reportDao.updateFlightInfo(userInfo);
	}

	@Override
	public List<FlightInfo> readFLightInfoList() {
		// TODO Auto-generated method stub
		return reportDao.readFLightInfoList();
	}

	@Override
	public ReportItem readReportItem(ReportItem reportItem) {
		// TODO Auto-generated method stub
		return reportDao.readReportItem(reportItem);
	}

	@Override
	public void createReportItem(ReportItem reportItem) {
		// TODO Auto-generated method stub
		reportDao.createReportItem(reportItem);
	}

	@Override
	public void deleteReportItem(ReportItem reportItem) {
		// TODO Auto-generated method stub
		reportDao.deleteReportItem(reportItem);
	}

	@Override
	public void updateReportItem(ReportItem reportItem) {
		// TODO Auto-generated method stub
		reportDao.updateReportItem(reportItem);
	}

	@Override
	public List<ReportItem> readReportItemList(ReportItem reportItem) {
		return reportDao.readReportItemList(reportItem);
	}



}

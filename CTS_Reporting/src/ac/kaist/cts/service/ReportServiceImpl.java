package ac.kaist.cts.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import ac.kaist.cts.dao.ReportDao;
import ac.kaist.cts.domain.AircraftInfo;
import ac.kaist.cts.domain.AttachedItem;
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

	@Override
	public AttachedItem readAttachedItem(AttachedItem attachedItem) {
		// TODO Auto-generated method stub
		return reportDao.readAttachedItem(attachedItem);
	}

	@Override
	public void createAttachedItem(AttachedItem attachedItem) {
		// TODO Auto-generated method stub
		reportDao.createAttachedItem(attachedItem);
	}

	@Override
	public void deleteAttachedItem(AttachedItem attachedItem) {
		// TODO Auto-generated method stub
		reportDao.deleteAttachedItem(attachedItem);
	}

	@Override
	public void updateAttachedItem(AttachedItem attachedItem) {
		// TODO Auto-generated method stub
		reportDao.updateAttachedItem(attachedItem);
	}

	@Override
	public List<AttachedItem> readAttachedItemList(AttachedItem attachedItem) {
		return reportDao.readAttachedItemList(attachedItem);
	}

	@Override
	public void updateReportItemBasic(Report rp, HttpServletRequest request) {
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String reportingDate = ServletRequestUtils.getStringParameter(request, "reportingDate", "");
		String reportingNo = ServletRequestUtils.getStringParameter(request, "reportingNo", "");
		int crew_fatalities = ServletRequestUtils.getIntParameter(request, "crew_fatalities", 0);
		int crew_injuries = ServletRequestUtils.getIntParameter(request, "crew_injuries",  0);
		int cabin_fatalities = ServletRequestUtils.getIntParameter(request, "cabin_fatalities",  0);
		int cabin_injuries = ServletRequestUtils.getIntParameter(request, "cabin_injuries", 0);
		int passenger_fatalities = ServletRequestUtils.getIntParameter(request, "passenger_fatalities", 0);
		int passenger_injuries = ServletRequestUtils.getIntParameter(request, "passenger_injuries", 0);
		String aircraft_damage = ServletRequestUtils.getStringParameter(request, "aircraft_damage", "");
		String delay_time = ServletRequestUtils.getStringParameter(request, "delay_time", "");
		
		rp.setReport_no(report_no);
		rp.setReport_date(reportingDate);
		rp.setCrew_fatalities(crew_fatalities);
		rp.setCrew_injuries(crew_injuries);
		rp.setCabin_fatalities(cabin_fatalities);
		rp.setCabin_injuries(cabin_injuries);
		rp.setPassenger_fatalities(passenger_fatalities);
		rp.setPassenger_injuries(passenger_injuries);
		rp.setAircraft_damage(aircraft_damage);
		rp.setDelay_time(delay_time);
		
		reportDao.updateReport(rp);
		
	}

	@Override
	public void updateReportItem(ReportItem ri, HttpServletRequest request, String type){
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		String title = ServletRequestUtils.getStringParameter(request, "report_"+type+"_title", "");
		String time = ServletRequestUtils.getStringParameter(request, "report_"+type+"_time", "");
		String time_type = ServletRequestUtils.getStringParameter(request, "report_"+type+"_time_type", "");
		String narrative = ServletRequestUtils.getStringParameter(request, "report_"+type+"_narrative", "");
		String recommendation = ServletRequestUtils.getStringParameter(request, "report_"+type+"_recommendation", "");
		
		ri.setType(report_item_type);
		ri.setTitle(title);
		ri.setTime(time);
		ri.setTime_type(time_type);
		ri.setNarrative(narrative);
		ri.setRecommendation(recommendation);
		
		this.updateReportItem(ri);
	}
	
	
	
}

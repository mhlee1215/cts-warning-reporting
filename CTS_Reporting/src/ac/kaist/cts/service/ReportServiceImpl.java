package ac.kaist.cts.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import ac.kaist.cts.dao.HazardItemDao;
import ac.kaist.cts.dao.ReportDao;
import ac.kaist.cts.dao.SelectItemDao;
import ac.kaist.cts.dao.UserDao;
import ac.kaist.cts.domain.AircraftInfo;
import ac.kaist.cts.domain.AttachedItem;
import ac.kaist.cts.domain.FlightInfo;
import ac.kaist.cts.domain.Hazard;
import ac.kaist.cts.domain.HazardItem;
import ac.kaist.cts.domain.Report;
import ac.kaist.cts.domain.ReportItem;
import ac.kaist.cts.domain.ReportParent;
import ac.kaist.cts.domain.RiskOwner;
import ac.kaist.cts.domain.SelectItem;
import ac.kaist.cts.domain.UserInfo;

@Service
public class ReportServiceImpl implements ReportService {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ReportDao		reportDao;
	
	@Autowired
	private UserDao		userDao;
	
	@Autowired
	private SelectItemDao		selectItemDao;
	
	@Autowired
	private HazardItemDao		hazardItemDao;
	
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
	public List<FlightInfo> readFLightInfoList(FlightInfo flightInfo) {
		// TODO Auto-generated method stub
		return reportDao.readFLightInfoList(flightInfo);
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
		int crew_minor_injuries = ServletRequestUtils.getIntParameter(request, "crew_minor_injuries",  0);
		int crew_serious_injuries = ServletRequestUtils.getIntParameter(request, "crew_serious_injuries",  0);
		int cabin_fatalities = ServletRequestUtils.getIntParameter(request, "cabin_fatalities",  0);
		int cabin_minor_injuries = ServletRequestUtils.getIntParameter(request, "cabin_minor_injuries", 0);
		int cabin_serious_injuries = ServletRequestUtils.getIntParameter(request, "cabin_serious_injuries", 0);
		int passenger_fatalities = ServletRequestUtils.getIntParameter(request, "passenger_fatalities", 0);
		int passenger_minor_injuries = ServletRequestUtils.getIntParameter(request, "passenger_minor_injuries", 0);
		int passenger_serious_injuries = ServletRequestUtils.getIntParameter(request, "passenger_serious_injuries", 0);
		String aircraft_damage = ServletRequestUtils.getStringParameter(request, "aircraft_damage", "");
		
		String delay_time = ServletRequestUtils.getStringParameter(request, "delay_time", "");
		
		String manufacturer = ServletRequestUtils.getStringParameter(request, "aircraftinfo_manufacturer", "");
		String model = ServletRequestUtils.getStringParameter(request, "aircraftinfo_model", "");
		String serial_no = ServletRequestUtils.getStringParameter(request, "aircraftinfo_serial_no", "");
		String regi_no = ServletRequestUtils.getStringParameter(request, "aircraftinfo_regi_no", "");
		int no_seat_crew = ServletRequestUtils.getIntParameter(request, "aircraftinfo_no_seat_crew", 0);
		int no_seat_cabin = ServletRequestUtils.getIntParameter(request, "aircraftinfo_no_seat_cabin", 0);
		int no_seat_passenger = ServletRequestUtils.getIntParameter(request, "aircraftinfo_no_seat_passenger", 0);
		String last_inspection_type = ServletRequestUtils.getStringParameter(request, "aircraftinfo_last_inspection_type", "");
		String last_inspection_date = ServletRequestUtils.getStringParameter(request, "aircraftinfo_last_inspection_date", "");
		
		String flight_date = ServletRequestUtils.getStringParameter(request, "flight_date", "");
		String flight_no = ServletRequestUtils.getStringParameter(request, "flight_no", "");
		String airline = ServletRequestUtils.getStringParameter(request, "flight_airline", "");
		String route_from = ServletRequestUtils.getStringParameter(request, "flight_route_from", "");
		String route_to = ServletRequestUtils.getStringParameter(request, "flight_route_to", "");
		String route_diverted = ServletRequestUtils.getStringParameter(request, "flight_route_diverted", "");
		String flight_type = ServletRequestUtils.getStringParameter(request, "flight_flight_type", "");
		String dom_int_type = ServletRequestUtils.getStringParameter(request, "flight_dom_int_type", "");
		String cargo_operation = ServletRequestUtils.getStringParameter(request, "flight_cargo_operation", "");
		int no_crew = ServletRequestUtils.getIntParameter(request, "flight_no_crew", 0);
		int no_cabin = ServletRequestUtils.getIntParameter(request, "flight_no_cabin", 0);
		int no_passenger = ServletRequestUtils.getIntParameter(request, "flight_no_passenger", 0);

		String id_no = ServletRequestUtils.getStringParameter(request, "userinfo_id_no", "");
		String name = ServletRequestUtils.getStringParameter(request, "userinfo_name", "");
		String date_of_birth = ServletRequestUtils.getStringParameter(request, "userinfo_date_of_birth", "");
		String medical_certification = ServletRequestUtils.getStringParameter(request, "userinfo_medical_certification", "");
		String date_of_last_medical = ServletRequestUtils.getStringParameter(request, "userinfo_date_of_last_medical", "");
		int flight_time = ServletRequestUtils.getIntParameter(request, "userinfo_flight_time", 0);
		int this_make_model = ServletRequestUtils.getIntParameter(request, "userinfo_this_make_model", 0);
		
		rp.setReport_no(report_no);
		rp.setReport_date(reportingDate);
		rp.setCrew_fatalities(crew_fatalities);
		rp.setCrew_minor_injuries(crew_minor_injuries);
		rp.setCrew_serious_injuries(crew_serious_injuries);
		rp.setCabin_fatalities(cabin_fatalities);
		rp.setCabin_minor_injuries(cabin_minor_injuries);
		rp.setCabin_serious_injuries(cabin_serious_injuries);
		rp.setPassenger_fatalities(passenger_fatalities);
		rp.setPassenger_minor_injuries(passenger_minor_injuries);
		rp.setPassenger_serious_injuries(passenger_serious_injuries);
		rp.setAircraft_damage(aircraft_damage);
		
		
		Integer worst_minor_injury = Math.max(crew_minor_injuries, Math.max(cabin_minor_injuries, passenger_minor_injuries));
		Integer worst_serious_injury = Math.max(crew_serious_injuries, Math.max(cabin_serious_injuries, passenger_serious_injuries));
		Integer worst_fataility = Math.max(crew_fatalities, Math.max(cabin_fatalities, passenger_fatalities));
		
		if(worst_fataility > 1)
			rp.setInjury("5"); //Mutiple Fatality:
		else if(worst_fataility == 1)
			rp.setInjury("4"); //One Fatality
		else if(worst_serious_injury > 0)
			rp.setInjury("3"); //Serious Injury
		else if(worst_serious_injury == 0 && worst_minor_injury > 1)
			rp.setInjury("2"); //MINOR
		else //if(worst_minor_injury + worst_serious_injury + worst_fataility == 0)
			rp.setInjury("1"); //NONE 
		
		rp.setDelay_time(delay_time);
		
		reportDao.updateReport(rp);
		
		Report rrp = reportDao.readReport(rp);
		AircraftInfo ai = new AircraftInfo();
		ai.setReport_id(rrp.getId());
		ai.setManufacturer(manufacturer);
		ai.setModel(model);
		ai.setSerial_no(serial_no);
		ai.setRegi_no(regi_no);
		ai.setNo_seat_crew(no_seat_crew);
		ai.setNo_seat_cabin(no_seat_cabin);
		ai.setNo_seat_passenger(no_seat_passenger);
		ai.setLast_inspection_type(last_inspection_type);
		ai.setLast_inspection_date(last_inspection_date);
		reportDao.updateAircraftInformation(ai);
		
		FlightInfo fi = new FlightInfo();
		fi.setReport_id(rrp.getId());
		fi.setFlight_date(flight_date);
		fi.setFlight_no(flight_no);
		fi.setAirline(airline);
		fi.setRoute_from(route_from);
		fi.setRoute_to(route_to);
		fi.setRoute_diverted(route_diverted);
		fi.setFlight_type(flight_type);
		fi.setDom_int_type(dom_int_type);
		fi.setCargo_operation(cargo_operation);
		fi.setNo_crew(no_crew);
		fi.setNo_cabin(no_cabin);
		fi.setNo_passenger(no_passenger);
		reportDao.updateFlightInfo(fi);
		
		UserInfo ui = new UserInfo();
		ui.setReport_id(rrp.getId());
		ui.setId_no(id_no);
		ui.setName(name);
		ui.setDate_of_birth(date_of_birth);
		ui.setMedical_certification(medical_certification);
		ui.setDate_of_last_medical(date_of_last_medical);
		ui.setFlight_time(flight_time);
		ui.setThis_make_model(this_make_model);
		userDao.updateUserInfo(ui);
		
		
		
		
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
	
	@Override
	public void updateReportItemReview(ReportItem ri, HttpServletRequest request, String type){
		String reportItem_comments = ServletRequestUtils.getStringParameter(request, "reportItem_comments", "");
		String safety_officer_opinion = ServletRequestUtils.getStringParameter(request, "safety_officer_opinion", "");
		String priority = ServletRequestUtils.getStringParameter(request, "priority", "");
		
		ri.setComments(reportItem_comments);
		ri.setStatus_determine(safety_officer_opinion);
		ri.setPriority(priority);
		
		this.updateReportItem(ri);
	}

	@Override
	public List<Report> readReportList(Report report) {
		// TODO Auto-generated method stub
		return reportDao.readReportList(report);
	}
	
	@Override
	public List<Report> readReportChildrenList(Report report) {
		// TODO Auto-generated method stub
		return reportDao.readReportChildrenList(report);
	}

	@Override
	public Hazard readHazard(Hazard hazard) {
		// TODO Auto-generated method stub
		return reportDao.readHazard(hazard);
	}

	@Override
	public void createHazard(Hazard hazard) {
		// TODO Auto-generated method stub
		reportDao.createHazard(hazard);
	}

	@Override
	public void deleteHazard(Hazard hazard) {
		// TODO Auto-generated method stub
		reportDao.deleteHazard(hazard);
	}

	@Override
	public void updateHazard(Hazard hazard) {
		// TODO Auto-generated method stub
		reportDao.updateHazard(hazard);
	}

	@Override
	public List<Hazard> readHazardList(Hazard hazard) {
		return reportDao.readHazardList(hazard);
	}
	
	@Override
	public List<Hazard> readHazardPureList(Hazard hazard) {
		return reportDao.readHazardPureList(hazard);
	}
	
	@Override
	public ReportParent readReportParent(ReportParent reportParent) {
		// TODO Auto-generated method stub
		return reportDao.readReportParent(reportParent);
	}

	@Override
	public void createReportParent(ReportParent reportParent) {
		// TODO Auto-generated method stub
		reportDao.createReportParent(reportParent);
	}

	@Override
	public void deleteReportParent(ReportParent reportParent) {
		// TODO Auto-generated method stub
		reportDao.deleteReportParent(reportParent);
	}

	@Override
	public void updateReportParent(ReportParent reportParent) {
		// TODO Auto-generated method stub
		reportDao.updateReportParent(reportParent);
	}

	@Override
	public List<ReportParent> readReportParentList(ReportParent reportParent) {
		//public Report readReportWorstSituation(Report report);
		List<ReportParent> reportParentList = reportDao.readReportParentList(reportParent); 
		
		for(ReportParent rp : reportParentList){
			Report r = new Report();
			r.setReport_parent_id(rp.getId());
			
			r.setOrder_column("sia.value");
			Report ra = reportDao.readReportWorstSituation(r);
			rp.setAircraft_damage(ra.getAircraft_damage());
			
			r.setOrder_column("sii.value");
			Report ri = reportDao.readReportWorstSituation(r);
			rp.setInjury(ri.getInjury());
			
			r.setOrder_column("sid.value");
			Report rd = reportDao.readReportWorstSituation(r);
			rp.setDelay_time(rd.getDelay_time());
		}
		
		return reportParentList;
	}

	@Override
	public List<SelectItem> readSelectItemList(SelectItem item) {
		// TODO Auto-generated method stub
		return selectItemDao.readSelectItemList(item);
	}

	@Override
	public SelectItem readSelectItem(SelectItem item) {
		// TODO Auto-generated method stub
		return selectItemDao.readSelectItem(item);
	}

	@Override
	public void hazardViewContentUpdate(Hazard hazard,
			HttpServletRequest request) {
		String report_parent_no = ServletRequestUtils.getStringParameter(request, "report_parent_no", "");
		String report_no = ServletRequestUtils.getStringParameter(request, "report_no", "");
		String report_item_type = ServletRequestUtils.getStringParameter(request, "report_item_type", "");
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String hazard_title = ServletRequestUtils.getStringParameter(request, "hazard_title", "");
		String hazard_occurrence = ServletRequestUtils.getStringParameter(request, "hazard_occurrence", "");
		String hazard_injury = ServletRequestUtils.getStringParameter(request, "hazard_injury", "");
		String hazard_damage = ServletRequestUtils.getStringParameter(request, "hazard_damage", "");
		String hazard_delay = ServletRequestUtils.getStringParameter(request, "hazard_delay", "");
		String hazard_details = ServletRequestUtils.getStringParameter(request, "hazard_details", "");
		String hazard_new = ServletRequestUtils.getStringParameter(request, "hazard_new", "");
		String hazard_priority = ServletRequestUtils.getStringParameter(request, "hazard_priority", "");
		
		
		int hazard_item_lv1 = ServletRequestUtils.getIntParameter(request, "hazard_item_lv1", -1);
		int hazard_item_lv2 = ServletRequestUtils.getIntParameter(request, "hazard_item_lv2", -1);
		int hazard_item_lv3 = ServletRequestUtils.getIntParameter(request, "hazard_item_lv3", -1);
		int hazard_item_lv4 = ServletRequestUtils.getIntParameter(request, "hazard_item_lv4", -1);
		int hazard_item_lv5 = ServletRequestUtils.getIntParameter(request, "hazard_item_lv5", -1);
		
		hazard.setTitle(hazard_title);
		hazard.setOcurrence(hazard_occurrence);
		hazard.setInjury(hazard_injury);
		hazard.setDamage(hazard_damage);
		hazard.setDelay(hazard_delay);
		hazard.setDetails(hazard_details);
		hazard.setIsnew(hazard_new);
		hazard.setPriority(hazard_priority);
		
		if(hazard_no.isEmpty()){
			ReportItem ri = new ReportItem();
			ri.setReport_no(report_no);
			ri.setType(report_item_type);
			ri = reportDao.readReportItem(ri);
			
			Report r = new Report();
			r.setId(ri.getReport_id());
			r = reportDao.readReport(r);
			
			ReportParent rp = new ReportParent();
			rp.setReport_no(report_parent_no);
			rp = reportDao.readReportParent(rp);

			hazard.setReport_item_id(ri.getId());
			hazard.setHazard_no(hazard_no);
			hazard.setState_likelihood(Hazard.STATE_NOT_SUBMITTED);
			hazard.setState_severity(Hazard.STATE_NOT_SUBMITTED);
			hazard.setState_assessment(Hazard.STATE_NOT_SUBMITTED);
			hazard.setState_mitigation(Hazard.STATE_NOT_SUBMITTED);
			hazard.setPriority(rp.getPriority());
			
			DateTime curTime = new DateTime();
			hazard.setHazard_date(curTime.toString("yyyy-MM-dd"));
			
			Hazard hh = new Hazard();
			hh.setReport_parent_id(rp.getId());
			Integer hazardTotalCount = reportDao.readHazardTotalCount(hh);
			
			hazard_no = "H"+report_parent_no.substring(1)+"-"+Integer.toString(hazardTotalCount+1);
			hazard.setHazard_no(hazard_no);
			
			this.createHazard(hazard);
		}
		else{
			hazard.setHazard_no(hazard_no);
			this.updateHazard(hazard);
		}
		
		Hazard h = new Hazard();
		h.setHazard_no(hazard_no);
		h = readHazard(h);
		
		int hazard_id = h.getId();
		
		if(hazard_item_lv1!=-1){
			HazardItem hi = new HazardItem();
			hi.setHazard_id(hazard_id);
			hi.setItem_level(1);
			hi.setItem_id(hazard_item_lv1);
			if( hazardItemDao.updateHazardItem(hi) == 0){
				hazardItemDao.createHazardItem(hi);
			}
		}
		if(hazard_item_lv2!=-1){
			HazardItem hi = new HazardItem();
			hi.setHazard_id(hazard_id);
			hi.setItem_level(2);
			hi.setItem_id(hazard_item_lv2);
			if( hazardItemDao.updateHazardItem(hi) == 0){
				hazardItemDao.createHazardItem(hi);
			}
		}
		if(hazard_item_lv3!=-1){
			HazardItem hi = new HazardItem();
			hi.setHazard_id(hazard_id);
			hi.setItem_level(3);
			hi.setItem_id(hazard_item_lv3);
			if( hazardItemDao.updateHazardItem(hi) == 0){
				hazardItemDao.createHazardItem(hi);
			}
		}
		if(hazard_item_lv4!=-1){
			HazardItem hi = new HazardItem();
			hi.setHazard_id(hazard_id);
			hi.setItem_level(4);
			hi.setItem_id(hazard_item_lv4);
			if( hazardItemDao.updateHazardItem(hi) == 0){
				hazardItemDao.createHazardItem(hi);
			}
		}
		if(hazard_item_lv5!=-1){
			HazardItem hi = new HazardItem();
			hi.setHazard_id(hazard_id);
			hi.setItem_level(5);
			hi.setItem_id(hazard_item_lv5);
			if( hazardItemDao.updateHazardItem(hi) == 0){
				hazardItemDao.createHazardItem(hi);
			}
		}
		
		
		
		
		
			
		
	}

	@Override
	public Integer readHazardTotalCount(Hazard hazard) {
		// TODO Auto-generated method stub
		return reportDao.readHazardTotalCount(hazard);
	}

	@Override
	public List<RiskOwner> readRiskOwnerList(RiskOwner riskOwner) {
		// TODO Auto-generated method stub
		return reportDao.readRiskOwnerList(riskOwner);
	}

	@Override
	public RiskOwner readRiskOwner(RiskOwner riskOwner) {
		// TODO Auto-generated method stub
		return reportDao.readRiskOwner(riskOwner);
	}

	@Override
	public void createRiskOwner(RiskOwner riskOwner) {
		// TODO Auto-generated method stub
		reportDao.createRiskOwner(riskOwner);
	}

	@Override
	public void deleteRiskOwner(RiskOwner riskOwner) {
		// TODO Auto-generated method stub
		reportDao.deleteRiskOwner(riskOwner);
	}

	@Override
	public void updateRiskOwner(RiskOwner riskOwner) {
		// TODO Auto-generated method stub
		reportDao.updateRiskOwner(riskOwner);
	}

	@Override
	public void managementDetailRiskAnalysisUpdate(Hazard hazard, HttpServletRequest request, String type) {
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String initial_likelihood = ServletRequestUtils.getStringParameter(request, "risk_analysis_"+type+"_initial_likelihood", "");
		String residual_likelihood = ServletRequestUtils.getStringParameter(request, "risk_analysis_"+type+"_residual_likelihood", "");
		String comments = ServletRequestUtils.getStringParameter(request, "risk_analysis_"+type+"_comments", "");
		
		hazard.setHazard_no(hazard_no);
		if(type.equals(Hazard.TYPE_LIKELIHOOD)){			
			hazard.setLikelihood_initial_likelihood(initial_likelihood);
			hazard.setLikelihood_residual_likelihood(residual_likelihood);
			hazard.setLikelihood_comments(comments);
		}else if(type.equals(Hazard.TYPE_SEVERITY)){			
			hazard.setSeverity_initial_likelihood(initial_likelihood);
			hazard.setSeverity_residual_likelihood(residual_likelihood);
			hazard.setSeverity_comments(comments);
		} 
		
		this.updateHazard(hazard);
	}

	@Override
	public void managementDetailRiskAssessmentUpdate(Hazard hazard,
			HttpServletRequest request) {
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String likelihood_comments = ServletRequestUtils.getStringParameter(request, "management_risk_assessment_likelihood_comments", "");
		String severity_comments = ServletRequestUtils.getStringParameter(request, "management_risk_assessment_severity_comments", "");
		
		hazard.setHazard_no(hazard_no);
		hazard.setLikelihood_comments(likelihood_comments);
		hazard.setSeverity_comments(severity_comments);
		this.updateHazard(hazard);
	}

	@Override
	public void managementDetailMitigationUpdate(Hazard hazard,
			HttpServletRequest request) {
		String hazard_no = ServletRequestUtils.getStringParameter(request, "hazard_no", "");
		String likelihood_comments = ServletRequestUtils.getStringParameter(request, "management_mitigation_likelihood_comments", "");
		String severity_comments = ServletRequestUtils.getStringParameter(request, "management_mitigation_severity_comments", "");
		
		String control_no = ServletRequestUtils.getStringParameter(request, "management_mitigation_control_no", "");
		String tracked_by = ServletRequestUtils.getStringParameter(request, "management_mitigation_tracked_by", "");
		
		hazard.setHazard_no(hazard_no);
		hazard.setLikelihood_comments(likelihood_comments);
		hazard.setSeverity_comments(severity_comments);
		hazard.setControl_no(control_no);
		hazard.setTracked_by(tracked_by);
		this.updateHazard(hazard);
		
		hazard = reportDao.readHazard(hazard);
		RiskOwner ro = new RiskOwner();
		ro.setHazard_id(hazard.getId());
		List<RiskOwner> riskOwnerList = reportDao.readRiskOwnerList(ro);
		
		for(int i = 1 ; i <= 2 ; i++){
			String division = ServletRequestUtils.getStringParameter(request, "management_mitigation_division_"+Integer.toString(i), "");
			String assigned_to = ServletRequestUtils.getStringParameter(request, "management_mitigation_assigned_to_"+Integer.toString(i), "");
			String requested_date = ServletRequestUtils.getStringParameter(request, "management_mitigation_requested_date_"+Integer.toString(i), "");
			String due_date = ServletRequestUtils.getStringParameter(request, "management_mitigation_due_date_"+Integer.toString(i), "");
			String comments = ServletRequestUtils.getStringParameter(request, "management_mitigation_comments_"+Integer.toString(i), "");
			
			RiskOwner riskOwner = new RiskOwner();
			riskOwner.setId(riskOwnerList.get(i-1).getId());
			riskOwner.setDivision(division);
			riskOwner.setAssigned_to(assigned_to);
			riskOwner.setRequested_date(requested_date);
			riskOwner.setDue_date(due_date);
			riskOwner.setComments(comments);
			
			updateRiskOwner(riskOwner);
		}
	}

	
	
	
}

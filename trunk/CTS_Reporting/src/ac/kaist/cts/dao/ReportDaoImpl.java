package ac.kaist.cts.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import ac.kaist.cts.domain.AircraftInfo;
import ac.kaist.cts.domain.AttachedItem;
import ac.kaist.cts.domain.FlightInfo;
import ac.kaist.cts.domain.Hazard;
import ac.kaist.cts.domain.Report;
import ac.kaist.cts.domain.ReportItem;
import ac.kaist.cts.domain.ReportParent;
import ac.kaist.cts.domain.RiskOwner;
import ac.kaist.cts.domain.User;
import ac.kaist.cts.domain.UserInfo;

@Repository
public class ReportDaoImpl extends SqlMapClientDaoSupport implements ReportDao {

	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> readReportListReview() {
		// TODO Auto-generated method stub
		
		List<Report> list = new ArrayList<Report>();
		Report a = new Report();
		
		a.setReport_no		("R2604137C1234");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Accepted");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2604137C6234");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Accepted");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2204137C9434");
		a.setReport_date	("April 22 2013");
		a.setAircraft_damage("Substantial");
		a.setInjury			("Minor");
		a.setDelay_time		("A/C Change");
		a.setPriority		("High");
		a.setReport_state			("In Investigation");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2304137C9834");
		a.setReport_date	("April 23 2013");
		a.setAircraft_damage("Minor");
		a.setInjury			("Minor");
		a.setDelay_time		("More than 1 hour");
		a.setPriority		("Medium");
		a.setReport_state			("Registered");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2504137C3534");
		a.setReport_date	("April 25 2013");
		a.setAircraft_damage("None");
		a.setInjury			("Minor");
		a.setDelay_time		("Within 30 min");
		a.setPriority		("-");
		a.setReport_state			("Review");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2104137C9664");
		a.setReport_date	("April 21 2013");
		a.setAircraft_damage("None");
		a.setInjury			("None");
		a.setDelay_time		("30 min ~ 1 hour");
		a.setPriority		("-");
		a.setReport_state			("Review");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R013137C1234");
		a.setReport_date	("March 01 2013");
		a.setAircraft_damage("None");
		a.setInjury			("None");
		a.setDelay_time		("None");
		a.setPriority		("Low");
		a.setReport_state			("Registered");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R013137C1234");
		a.setReport_date	("March 01 2013");
		a.setAircraft_damage("Unknown");
		a.setInjury			("None");
		a.setDelay_time		("Unknown");
		a.setPriority		("Low");
		a.setReport_state			("Rejected");
		list.add(a);
		
		return list;
	}

	@Override
	public List<Report> readReportListHazardIdentification() {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public List<Report> readReportListRiskAnalysis() {
		List<Report> list = new ArrayList<Report>();
		Report a = null;
		
		a = new Report();
		a.setTitle			("Pax door impated airbridge while");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Identified");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The passenger's lack of ");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Identified");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's misidentification");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Identified");
		list.add(a);
		
		a = new Report();
		a.setTitle			("Collision with a truck");
		a.setReport_date	("April 25 2013");
		a.setAircraft_damage("None");
		a.setInjury			("Minor");
		a.setDelay_time		("Within 30 min");
		a.setPriority		("Medium");
		a.setReport_state			("Identified");
		list.add(a);
		
		return list;
	}

	@Override
	public List<Report> readReportListRiskAssessment() {
		List<Report> list = new ArrayList<Report>();
		Report a = null;
		
		a = new Report();
		a.setTitle			("Pax door impated airbridge while opening");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Analyzed");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The passenger's lack of compiliance");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Analyzed");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's misidentification of the airport's");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Analyzed");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's selection of unsuitable terrain for");
		a.setReport_date	("April 25 2013");
		a.setAircraft_damage("None");
		a.setInjury			("Minor");
		a.setDelay_time		("Within 30 min");
		a.setPriority		("Medium");
		a.setReport_state			("Analyzed");
		list.add(a);
		
		return list;
	}

	@Override
	public List<Report> readReportListMitigation() {
		List<Report> list = new ArrayList<Report>();
		Report a = null;
		
		a = new Report();
		a.setTitle			("Pax door impated airbridge while opening");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Risk");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The passenger's lack of compiliance");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Risk");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's misidentification of the airport's");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setReport_state			("Risk");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's selection of unsuitable terrain for");
		a.setReport_date	("April 25 2013");
		a.setAircraft_damage("None");
		a.setInjury			("Minor");
		a.setDelay_time		("Within 30 min");
		a.setPriority		("Low");
		a.setReport_state			("Risk");
		list.add(a);
		
		return list;
	}

	@Override
	public void createAircraftInformation(
			AircraftInfo aircraftInformation) {
		getSqlMapClientTemplate().insert("AircraftInfoSql.createAircraftInfo", aircraftInformation);
		
	}

	@Override
	public List<AircraftInfo> readAircraftInformationList() {
		// TODO Auto-generated method stub
		List<AircraftInfo> array = getSqlMapClientTemplate().queryForList("AircraftInfoSql.readAircraftInfoList");
		return array;
	}

	@Override
	public AircraftInfo readAIrcraftInformation(AircraftInfo aircraftInformation) {
		AircraftInfo result = (AircraftInfo)getSqlMapClientTemplate().queryForObject("AircraftInfoSql.readAircraftInfo", aircraftInformation);
		return result;
	}

	@Override
	public void updateAircraftInformation(
			AircraftInfo aircraftInformation) {
		getSqlMapClientTemplate().update("AircraftInfoSql.updateAircraftInfo", aircraftInformation);
	}

	@Override
	public void deleteAircraftInformation(
			AircraftInfo aircraftInformation) {
		getSqlMapClientTemplate().delete("AircraftInfoSql.deleteAircraftInfo", aircraftInformation);
	}

	@Override
	public FlightInfo readFlightInfo(FlightInfo flightInfo) {
		FlightInfo result = (FlightInfo)getSqlMapClientTemplate().queryForObject("FlightInfoSql.readFlightInfo", flightInfo);
		return result;
	}


	@Override
	public void createFlightInfo(FlightInfo userInfo) {
		getSqlMapClientTemplate().insert("FlightInfoSql.createFlightInfo", userInfo);
	}


	@Override
	public void deleteFlightInfo(FlightInfo userInfo) {
		getSqlMapClientTemplate().delete("FlightInfoSql.deleteFlightInfo", userInfo);
		
	}


	@Override
	public void updateFlightInfo(FlightInfo userInfo) {
		getSqlMapClientTemplate().update("FlightInfoSql.updateFlightInfo", userInfo);
	}

	@Override
	public Report readReport(Report report) {
		Report result = (Report)getSqlMapClientTemplate().queryForObject("ReportSql.readReport", report);
		return result;
	}


	@Override
	public void createReport(Report report) {
		getSqlMapClientTemplate().insert("ReportSql.createReport", report);
	}


	@Override
	public void deleteReport(Report report) {
		getSqlMapClientTemplate().delete("ReportSql.deleteReport", report);
		
	}


	@Override
	public void updateReport(Report report) {
		getSqlMapClientTemplate().update("ReportSql.updateReport", report);
	}

	@Override
	public List<FlightInfo> readFLightInfoList(FlightInfo flightInfo) {
		List<FlightInfo> array = getSqlMapClientTemplate().queryForList("FlightInfoSql.readFlightInfoList", flightInfo);
		return array;
	}

	@Override
	public ReportItem readReportItem(ReportItem reportItem) {
		ReportItem result = (ReportItem)getSqlMapClientTemplate().queryForObject("ReportItemSql.readReportItem", reportItem);
		return result;
	}


	@Override
	public void createReportItem(ReportItem reportItem) {
		getSqlMapClientTemplate().insert("ReportItemSql.createReportItem", reportItem);
	}


	@Override
	public void deleteReportItem(ReportItem reportItem) {
		getSqlMapClientTemplate().delete("ReportItemSql.deleteReportItem", reportItem);
		
	}


	@Override
	public void updateReportItem(ReportItem reportItem) {
		getSqlMapClientTemplate().update("ReportItemSql.updateReportItem", reportItem);
	}

	@Override
	public List<ReportItem> readReportItemList(ReportItem reportItem) {
		List<ReportItem> array = getSqlMapClientTemplate().queryForList("ReportItemSql.readReportItemList", reportItem);
		return array;
	}

	@Override
	public AttachedItem readAttachedItem(AttachedItem attachedItem) {
		AttachedItem result = (AttachedItem)getSqlMapClientTemplate().queryForObject("AttachedItemSql.readAttachedItem", attachedItem);
		return result;
	}


	@Override
	public void createAttachedItem(AttachedItem attachedItem) {
		getSqlMapClientTemplate().insert("AttachedItemSql.createAttachedItem", attachedItem);
	}


	@Override
	public void deleteAttachedItem(AttachedItem attachedItem) {
		getSqlMapClientTemplate().delete("AttachedItemSql.deleteAttachedItem", attachedItem);
		
	}


	@Override
	public void updateAttachedItem(AttachedItem attachedItem) {
		getSqlMapClientTemplate().update("AttachedItemSql.updateAttachedItem", attachedItem);
	}

	@Override
	public List<AttachedItem> readAttachedItemList(AttachedItem attachedItem) {
		List<AttachedItem> array = getSqlMapClientTemplate().queryForList("AttachedItemSql.readAttachedItemList", attachedItem);
		return array;
	}

	@Override
	public List<Report> readReportList(Report report) {
		List<Report> array = getSqlMapClientTemplate().queryForList("ReportSql.readReportList", report);
		return array;
	}
	
	@Override
	public List<Report> readReportChildrenList(Report report) {
		List<Report> array = getSqlMapClientTemplate().queryForList("ReportSql.readReportChildrenList", report);
		return array;
	}

	@Override
	public Hazard readHazard(Hazard hazard) {
		Hazard result = (Hazard)getSqlMapClientTemplate().queryForObject("HazardSql.readHazard", hazard);
		return result;
	}


	@Override
	public void createHazard(Hazard hazard) {
		getSqlMapClientTemplate().insert("HazardSql.createHazard", hazard);
	}


	@Override
	public void deleteHazard(Hazard hazard) {
		getSqlMapClientTemplate().delete("HazardSql.deleteHazard", hazard);
		
	}


	@Override
	public void updateHazard(Hazard hazard) {
		getSqlMapClientTemplate().update("HazardSql.updateHazard", hazard);
	}

	@Override
	public List<Hazard> readHazardList(Hazard hazard) {
		List<Hazard> array = getSqlMapClientTemplate().queryForList("HazardSql.readHazardList", hazard);
		return array;
	}
	
	@Override
	public List<Hazard> readHazardPureList(Hazard hazard) {
		List<Hazard> array = getSqlMapClientTemplate().queryForList("HazardSql.readHazardList2", hazard);
		return array;
	}
	
	@Override
	public ReportParent readReportParent(ReportParent reportParent) {
		ReportParent result = (ReportParent)getSqlMapClientTemplate().queryForObject("ReportParentSql.readReportParent", reportParent);
		return result;
	}


	@Override
	public void createReportParent(ReportParent reportParent) {
		getSqlMapClientTemplate().insert("ReportParentSql.createReportParent", reportParent);
	}


	@Override
	public void deleteReportParent(ReportParent reportParent) {
		getSqlMapClientTemplate().delete("ReportParentSql.deleteReportParent", reportParent);
		
	}


	@Override
	public void updateReportParent(ReportParent reportParent) {
		getSqlMapClientTemplate().update("ReportParentSql.updateReportParent", reportParent);
	}

	@Override
	public List<ReportParent> readReportParentList(ReportParent reportParent) {
		List<ReportParent> array = getSqlMapClientTemplate().queryForList("ReportParentSql.readReportParentList", reportParent);
		return array;
	}

	@Override
	public Report readReportWorstSituation(Report report) {
		Report result = (Report)getSqlMapClientTemplate().queryForObject("ReportSql.readReportWorstSituation", report);
		return result;
	}

	@Override
	public Integer readHazardTotalCount(Hazard hazard) {
		Integer result = (Integer)getSqlMapClientTemplate().queryForObject("HazardSql.readHazardTotalCount", hazard);
		return result;
	}

	@Override
	public RiskOwner readRiskOwner(RiskOwner riskOwner) {
		RiskOwner result = (RiskOwner)getSqlMapClientTemplate().queryForObject("RiskOwnerSql.readRiskOwner", riskOwner);
		return result;
	}


	@Override
	public void createRiskOwner(RiskOwner riskOwner) {
		getSqlMapClientTemplate().insert("RiskOwnerSql.createRiskOwner", riskOwner);
	}


	@Override
	public void deleteRiskOwner(RiskOwner riskOwner) {
		getSqlMapClientTemplate().delete("RiskOwnerSql.deleteRiskOwner", riskOwner);
		
	}


	@Override
	public void updateRiskOwner(RiskOwner riskOwner) {
		getSqlMapClientTemplate().update("RiskOwnerSql.updateRiskOwner", riskOwner);
	}

	@Override
	public List<RiskOwner> readRiskOwnerList(RiskOwner riskOwner) {
		List<RiskOwner> array = getSqlMapClientTemplate().queryForList("RiskOwnerSql.readRiskOwnerList", riskOwner);
		return array;
	}
}

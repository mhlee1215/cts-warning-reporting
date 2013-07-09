package ac.kaist.cts.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import ac.kaist.cts.domain.Report;

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
		a.setState			("Accepted");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2604137C6234");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setState			("Accepted");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2204137C9434");
		a.setReport_date	("April 22 2013");
		a.setAircraft_damage("Substantial");
		a.setInjury			("Minor");
		a.setDelay_time		("A/C Change");
		a.setPriority		("High");
		a.setState			("In Investigation");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2304137C9834");
		a.setReport_date	("April 23 2013");
		a.setAircraft_damage("Minor");
		a.setInjury			("Minor");
		a.setDelay_time		("More than 1 hour");
		a.setPriority		("Medium");
		a.setState			("Registered");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2504137C3534");
		a.setReport_date	("April 25 2013");
		a.setAircraft_damage("None");
		a.setInjury			("Minor");
		a.setDelay_time		("Within 30 min");
		a.setPriority		("-");
		a.setState			("Review");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R2104137C9664");
		a.setReport_date	("April 21 2013");
		a.setAircraft_damage("None");
		a.setInjury			("None");
		a.setDelay_time		("30 min ~ 1 hour");
		a.setPriority		("-");
		a.setState			("Review");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R013137C1234");
		a.setReport_date	("March 01 2013");
		a.setAircraft_damage("None");
		a.setInjury			("None");
		a.setDelay_time		("None");
		a.setPriority		("Low");
		a.setState			("Registered");
		list.add(a);
		
		a = new Report();
		a.setReport_no		("R013137C1234");
		a.setReport_date	("March 01 2013");
		a.setAircraft_damage("Unknown");
		a.setInjury			("None");
		a.setDelay_time		("Unknown");
		a.setPriority		("Low");
		a.setState			("Rejected");
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
		a.setState			("Identified");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The passenger's lack of ");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setState			("Identified");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's misidentification");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setState			("Identified");
		list.add(a);
		
		a = new Report();
		a.setTitle			("Collision with a truck");
		a.setReport_date	("April 25 2013");
		a.setAircraft_damage("None");
		a.setInjury			("Minor");
		a.setDelay_time		("Within 30 min");
		a.setPriority		("Medium");
		a.setState			("Identified");
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
		a.setState			("Analyzed");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The passenger's lack of compiliance");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setState			("Analyzed");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's misidentification of the airport's");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setState			("Analyzed");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's selection of unsuitable terrain for");
		a.setReport_date	("April 25 2013");
		a.setAircraft_damage("None");
		a.setInjury			("Minor");
		a.setDelay_time		("Within 30 min");
		a.setPriority		("Medium");
		a.setState			("Analyzed");
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
		a.setState			("Risk");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The passenger's lack of compiliance");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setState			("Risk");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's misidentification of the airport's");
		a.setReport_date	("April 26 2013");
		a.setAircraft_damage("Destoryed");
		a.setInjury			("Fatal");
		a.setDelay_time		("Flight Cancel");
		a.setPriority		("High");
		a.setState			("Risk");
		list.add(a);
		
		a = new Report();
		a.setTitle			("The pilot's selection of unsuitable terrain for");
		a.setReport_date	("April 25 2013");
		a.setAircraft_damage("None");
		a.setInjury			("Minor");
		a.setDelay_time		("Within 30 min");
		a.setPriority		("Low");
		a.setState			("Risk");
		list.add(a);
		
		return list;
	}

}

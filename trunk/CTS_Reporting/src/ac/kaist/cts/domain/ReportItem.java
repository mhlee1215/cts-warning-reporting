package ac.kaist.cts.domain;

import java.util.ArrayList;
import java.util.List;

//CREATE  TABLE IF NOT EXISTS `Report_DB`.`report_item` (
//		  `id` INT NOT NULL AUTO_INCREMENT ,
//		  `type` VARCHAR(45) NULL ,
//		  `title` VARCHAR(300) NULL ,
//		  `time` VARCHAR(45) NULL ,
//		  `time_type` VARCHAR(45) NULL ,
//		  `narrative` TEXT NULL ,
//		  `recommendation` TEXT NULL ,
//		  `status` VARCHAR(45) NULL ,
//		  `priority` VARCHAR(45) NULL ,
//		  `Report_id` INT NOT NULL ,
//		  PRIMARY KEY (`id`) ,
//		  INDEX `fk_report_item_Report1_idx` (`Report_id` ASC) ,
//		  CONSTRAINT `fk_report_item_Report1`
//		    FOREIGN KEY (`Report_id` )
//		    REFERENCES `Report_DB`.`Report` (`id` )
//		    ON DELETE NO ACTION
//		    ON UPDATE NO ACTION)
//		ENGINE = InnoDB;

public class ReportItem {

	public ReportItem() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<String> getReportItemNameList(){
		List<String> list = new ArrayList<String>();
		list.add(TYPE_TAXI_OUT);
		list.add(TYPE_TAKE_OFF);
		list.add(TYPE_CLIMB);
		list.add(TYPE_EN_ROUTE);
		list.add(TYPE_DECENT);
		list.add(TYPE_APPROACH);
		list.add(TYPE_LANDING);
		list.add(TYPE_TAXI_IN);
		return list;
	}
	
	public static String STATE_SUBMITTED = "SUBMITTED";
	public static String STATE_NOT_SUBMITTED = "NOTSUBMITTED";
	public static String STATUS_REVIEW_ACCEPTED = "Accepted";
	public static String STATUS_REVIEW_REJECTED = "Rejected";
	public static String STATUS_REVIEW_NEED_INVESTIGATION = "Need an Investigation";
	
	public static String getSTATUS_REVIEW_ACCEPTED() {
		return STATUS_REVIEW_ACCEPTED;
	}

	public static void setSTATUS_REVIEW_ACCEPTED(String sTATUS_REVIEW_ACCEPTED) {
		STATUS_REVIEW_ACCEPTED = sTATUS_REVIEW_ACCEPTED;
	}

	public static String getSTATUS_REVIEW_REJECTED() {
		return STATUS_REVIEW_REJECTED;
	}

	public static void setSTATUS_REVIEW_REJECTED(String sTATUS_REVIEW_REJECTED) {
		STATUS_REVIEW_REJECTED = sTATUS_REVIEW_REJECTED;
	}

	public static String getSTATUS_REVIEW_NEED_INVESTIGATION() {
		return STATUS_REVIEW_NEED_INVESTIGATION;
	}

	public static void setSTATUS_REVIEW_NEED_INVESTIGATION(
			String sTATUS_REVIEW_NEED_INVESTIGATION) {
		STATUS_REVIEW_NEED_INVESTIGATION = sTATUS_REVIEW_NEED_INVESTIGATION;
	}

	public static String TYPE_BASIC 	= "BASIC";
	

	public static String TYPE_TAXI_OUT = "TAXI-OUT";
	public static String TYPE_TAKE_OFF = "TAKE-OFF";
	public static String TYPE_CLIMB 	= "CLIMB";
	public static String TYPE_EN_ROUTE = "EN-ROUTE";
	public static String TYPE_DECENT 	= "DECENT";
	public static String TYPE_APPROACH = "APPROACH";
	public static String TYPE_LANDING 	= "LANDING";
	public static String TYPE_TAXI_IN 	= "TAXI-IN";
	
	private int id 				= 0;
	private String type 		= "";
	private String type_index	= "";
	private String title 		= "";
	private String time 		= "";
	private String time_type 	= "";
	private String narrative 	= "";
	private String recommendation = "";
	private String comments = "";
	private String status 		= "";
	private String status_review = "";
	private String status_determine = "";
	private String status_hazard_id = "";
	private String priority 	= "";
	private int report_id		= 0;
	private String report_no	=	"";
	
	
	

	public String getStatus_determine() {
		return status_determine;
	}

	public void setStatus_determine(String status_determine) {
		this.status_determine = status_determine;
	}

	public String getStatus_hazard_id() {
		return status_hazard_id;
	}

	public void setStatus_hazard_id(String status_hazard_id) {
		this.status_hazard_id = status_hazard_id;
	}

	public int getId() {
		return id;
	}
	public String getStatus_review() {
		return status_review;
	}

	public void setStatus_review(String status_review) {
		this.status_review = status_review;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public String getType_index() {
		return type_index;
	}

	public void setType_index(String type_index) {
		this.type_index = type_index;
	}

	public void setTime(String time) {
		this.time = time;
	}
	public String getTime_type() {
		return time_type;
	}
	public void setTime_type(String time_type) {
		this.time_type = time_type;
	}
	public String getNarrative() {
		return narrative;
	}
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public String getReport_no() {
		return report_no;
	}
	public void setReport_no(String report_no) {
		this.report_no = report_no;
	}
	public static String getSTATE_SUBMITTED() {
		return STATE_SUBMITTED;
	}
	public static void setSTATE_SUBMITTED(String sTATE_SUBMITTED) {
		STATE_SUBMITTED = sTATE_SUBMITTED;
	}
	public static String getSTATE_NOT_SUBMITTED() {
		return STATE_NOT_SUBMITTED;
	}
	public static void setSTATE_NOT_SUBMITTED(String sTATE_NOT_SUBMITTED) {
		STATE_NOT_SUBMITTED = sTATE_NOT_SUBMITTED;
	}
	public static String getTYPE_BASIC() {
		return TYPE_BASIC;
	}
	public static void setTYPE_BASIC(String tYPE_BASIC) {
		TYPE_BASIC = tYPE_BASIC;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"type\":\"" + type
				+ "\", \"type_index\":\"" + type_index + "\", \"title\":\""
				+ title + "\", \"time\":\"" + time + "\", \"time_type\":\""
				+ time_type + "\", \"narrative\":\"" + narrative
				+ "\", \"recommendation\":\"" + recommendation
				+ "\", \"comments\":\"" + comments + "\", \"status\":\""
				+ status + "\", \"status_review\":\"" + status_review
				+ "\", \"status_determine\":\"" + status_determine
				+ "\", \"status_hazard_id\":\"" + status_hazard_id
				+ "\", \"priority\":\"" + priority + "\", \"report_id\":\""
				+ report_id + "\", \"report_no\":\"" + report_no + "\"}";
	}
}

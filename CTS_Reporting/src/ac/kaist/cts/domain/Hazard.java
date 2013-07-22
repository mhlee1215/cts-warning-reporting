package ac.kaist.cts.domain;

//CREATE  TABLE IF NOT EXISTS `Report_DB`.`hazard` (
//		  `id` INT NOT NULL AUTO_INCREMENT ,
//		  `hazard_no` VARCHAR(45) NULL ,
//		  `title` TEXT NULL ,
//		  `date` VARCHAR(45) NULL ,
//		  `details` TEXT NULL ,
//		  `state` VARCHAR(45) NULL ,
//		  `isnew` VARCHAR(45) NULL ,
//		  `ocurrence` VARCHAR(200) NULL ,
//		  `injury` VARCHAR(45) NULL ,
//		  `damage` VARCHAR(45) NULL ,
//		  `delay` VARCHAR(45) NULL ,
//		  `likelihood_initial_likelihood` VARCHAR(45) NULL ,
//		  `likelihood_residual_likelihood` VARCHAR(45) NULL ,
//		  `likelihood_comment` TEXT NULL ,
//		  `severity_initial_likelihood` VARCHAR(45) NULL ,
//		  `severity_residual_likelihood` VARCHAR(45) NULL ,
//		  `severity_comment` TEXT NULL ,
//		  `report_item_id` INT NOT NULL ,
//		  PRIMARY KEY (`id`) ,
//		  INDEX `fk_hazard_report_item1_idx` (`report_item_id` ASC) ,
//		  CONSTRAINT `fk_hazard_report_item1`
//		    FOREIGN KEY (`report_item_id` )
//		    REFERENCES `Report_DB`.`report_item` (`id` )
//		    ON DELETE NO ACTION
//		    ON UPDATE NO ACTION)
//		ENGINE = InnoDB;

public class Hazard {

	public Hazard() {
		// TODO Auto-generated constructor stub
	}
	
	public static String 	STATUS_NOT_IDENTIFIED		= "Not Identified";
	public static String 	STATUS_IDENTIFIED			= "Identified";
	public static String 	STATUS_ANALYZED				= "Analyzed";
	public static String 	STATUS_RISK					= "Risk";
	public static String 	STATUS_MITIGATED			= "Mitigated";
	
	public static String	STATE_NOT_SUBMITTED			= "NOT SUBMITTED";
	public static String	STATE_SUBMITTED				= "SUBMITTED";
	
	public static String 	TYPE_LIKELIHOOD				= "likelihood";
	public static String 	TYPE_SEVERITY				= "severity";
	
	private int id = 0;
	private String hazard_no = "";
	private String title = "";
	private String hazard_date = "";
	private String details = "";
	private String state = "";
	private String isnew = "";
	private String ocurrence = "";
	private String injury = "";
	private String damage = "";
	private String delay = "";
	private String priority = "";
	private String state_likelihood = "";
	private String likelihood_initial_likelihood = "";
	private String likelihood_residual_likelihood = "";
	private String likelihood_comments = "";
	private String state_severity = "";
	private String severity_initial_likelihood = "";
	private String severity_residual_likelihood = "";
	private String severity_comments = "";
	private String control_no = "";
	private String tracked_by = "";
	private String state_assessment = "";
	private String state_mitigation = "";
	
	
	private int	report_item_id = 0;
	private int report_parent_id = 0;
	
	
	public String getState_assessment() {
		return state_assessment;
	}
	public void setState_assessment(String state_assessment) {
		this.state_assessment = state_assessment;
	}
	public String getState_mitigation() {
		return state_mitigation;
	}
	public void setState_mitigation(String state_mitigation) {
		this.state_mitigation = state_mitigation;
	}
	public String getState_likelihood() {
		return state_likelihood;
	}
	public void setState_likelihood(String state_likelihood) {
		this.state_likelihood = state_likelihood;
	}
	public String getState_severity() {
		return state_severity;
	}
	public void setState_severity(String state_severity) {
		this.state_severity = state_severity;
	}
	public String getControl_no() {
		return control_no;
	}
	public void setControl_no(String control_no) {
		this.control_no = control_no;
	}
	public String getTracked_by() {
		return tracked_by;
	}
	public void setTracked_by(String tracked_by) {
		this.tracked_by = tracked_by;
	}
	public int getReport_parent_id() {
		return report_parent_id;
	}
	public void setReport_parent_id(int report_parent_id) {
		this.report_parent_id = report_parent_id;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSeverity_comments() {
		return severity_comments;
	}
	public void setSeverity_comments(String severity_comments) {
		this.severity_comments = severity_comments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHazard_no() {
		return hazard_no;
	}
	public void setHazard_no(String hazard_no) {
		this.hazard_no = hazard_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getHazard_date() {
		return hazard_date;
	}
	public void setHazard_date(String hazard_date) {
		this.hazard_date = hazard_date;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsnew() {
		return isnew;
	}
	public void setIsnew(String isnew) {
		this.isnew = isnew;
	}
	public String getOcurrence() {
		return ocurrence;
	}
	public void setOcurrence(String ocurrence) {
		this.ocurrence = ocurrence;
	}
	public String getInjury() {
		return injury;
	}
	public void setInjury(String injury) {
		this.injury = injury;
	}
	public String getDamage() {
		return damage;
	}
	public void setDamage(String damage) {
		this.damage = damage;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public String getLikelihood_initial_likelihood() {
		return likelihood_initial_likelihood;
	}
	public void setLikelihood_initial_likelihood(
			String likelihood_initial_likelihood) {
		this.likelihood_initial_likelihood = likelihood_initial_likelihood;
	}
	public String getLikelihood_residual_likelihood() {
		return likelihood_residual_likelihood;
	}
	public void setLikelihood_residual_likelihood(
			String likelihood_residual_likelihood) {
		this.likelihood_residual_likelihood = likelihood_residual_likelihood;
	}
	public String getSeverity_initial_likelihood() {
		return severity_initial_likelihood;
	}
	public void setSeverity_initial_likelihood(String severity_initial_likelihood) {
		this.severity_initial_likelihood = severity_initial_likelihood;
	}
	public String getSeverity_residual_likelihood() {
		return severity_residual_likelihood;
	}
	public void setSeverity_residual_likelihood(String severity_residual_likelihood) {
		this.severity_residual_likelihood = severity_residual_likelihood;
	}
	public int getReport_item_id() {
		return report_item_id;
	}
	public void setReport_item_id(int report_item_id) {
		this.report_item_id = report_item_id;
	}
	
	public String getLikelihood_comments() {
		return likelihood_comments;
	}
	public void setLikelihood_comments(String likelihood_comments) {
		this.likelihood_comments = likelihood_comments;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"hazard_no\":\"" + hazard_no
				+ "\", \"title\":\"" + title + "\", \"hazard_date\":\""
				+ hazard_date + "\", \"details\":\"" + details
				+ "\", \"state\":\"" + state + "\", \"isnew\":\"" + isnew
				+ "\", \"ocurrence\":\"" + ocurrence + "\", \"injury\":\""
				+ injury + "\", \"damage\":\"" + damage + "\", \"delay\":\""
				+ delay + "\", \"priority\":\"" + priority
				+ "\", \"state_likelihood\":\"" + state_likelihood
				+ "\", \"likelihood_initial_likelihood\":\""
				+ likelihood_initial_likelihood
				+ "\", \"likelihood_residual_likelihood\":\""
				+ likelihood_residual_likelihood
				+ "\", \"likelihood_comments\":\"" + likelihood_comments
				+ "\", \"state_severity\":\"" + state_severity
				+ "\", \"severity_initial_likelihood\":\""
				+ severity_initial_likelihood
				+ "\", \"severity_residual_likelihood\":\""
				+ severity_residual_likelihood + "\", \"severity_comments\":\""
				+ severity_comments + "\", \"control_no\":\"" + control_no
				+ "\", \"tracked_by\":\"" + tracked_by
				+ "\", \"state_assessment\":\"" + state_assessment
				+ "\", \"state_mitigation\":\"" + state_mitigation
				+ "\", \"report_item_id\":\"" + report_item_id
				+ "\", \"report_parent_id\":\"" + report_parent_id + "\"}";
	}
	
	
}

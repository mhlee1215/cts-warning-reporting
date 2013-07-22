package ac.kaist.cts.domain;

//CREATE  TABLE IF NOT EXISTS `Report_DB`.`report_parent` (
//		  `id` INT NOT NULL AUTO_INCREMENT ,
//		  `report_no` VARCHAR(45) NULL ,
//		  `report_date` VARCHAR(45) NULL ,
//		  `report_state` VARCHAR(45) NULL ,
//		  PRIMARY KEY (`id`) )
//		ENGINE = InnoDB;

public class ReportParent {

	public ReportParent() {
		// TODO Auto-generated constructor stub
	}
	
	
	private int id = 0;
	private String report_no = "";
	private String report_date = "";
	private String report_state = "";
	private String management_state = "";
	private String aircraft_damage = "";
	private String injury = "";
	private String delay_time = "";
	private String priority = "";
	
	public String getManagement_state() {
		return management_state;
	}
	public void setManagement_state(String management_state) {
		this.management_state = management_state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReport_no() {
		return report_no;
	}
	public void setReport_no(String report_no) {
		this.report_no = report_no;
	}
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	public String getReport_state() {
		return report_state;
	}
	public void setReport_state(String report_state) {
		this.report_state = report_state;
	}
	public String getAircraft_damage() {
		return aircraft_damage;
	}
	public void setAircraft_damage(String aircraft_damage) {
		this.aircraft_damage = aircraft_damage;
	}
	public String getInjury() {
		return injury;
	}
	public void setInjury(String injury) {
		this.injury = injury;
	}
	public String getDelay_time() {
		return delay_time;
	}
	public void setDelay_time(String delay_time) {
		this.delay_time = delay_time;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "ReportParent [id=" + id + ", report_no=" + report_no
				+ ", report_date=" + report_date + ", report_state="
				+ report_state + ", management_state=" + management_state
				+ ", aircraft_damage=" + aircraft_damage + ", injury=" + injury
				+ ", delay_time=" + delay_time + ", priority=" + priority + "]";
	}
	
	

}

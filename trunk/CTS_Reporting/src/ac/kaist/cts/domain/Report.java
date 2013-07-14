package ac.kaist.cts.domain;

//CREATE  TABLE IF NOT EXISTS `Report_DB`.`Report` (
//		  `id` INT NOT NULL AUTO_INCREMENT ,
//		  `report_date` DATETIME NULL ,
//		  `status` VARCHAR(45) NULL ,
//		  `report_no` VARCHAR(45) NULL ,
//		  `crew_fatalities` INT NULL ,
//		  `crew_injuries` INT NULL ,
//		  `cabin_fatalities` INT NULL ,
//		  `cabin_injuries` INT NULL ,
//		  `passenger_fatalities` INT NULL ,
//		  `passenger_injuries` INT NULL ,
//		  `aircraft_damage` VARCHAR(45) NULL ,
//		  `delay_time` VARCHAR(45) NULL ,
//		  `flight_info_id` INT NOT NULL ,
//		  PRIMARY KEY (`id`) ,
//		  UNIQUE INDEX `idIReports_UNIQUE` (`id` ASC) ,
//		  INDEX `fk_Report_flight_info1_idx` (`flight_info_id` ASC) ,
//		  CONSTRAINT `fk_Report_flight_info1`
//		    FOREIGN KEY (`flight_info_id` )
//		    REFERENCES `Report_DB`.`flight_info` (`id` )
//		    ON DELETE NO ACTION
//		    ON UPDATE NO ACTION)
//		ENGINE = InnoDB;


public class Report {
	public static int	STATUS_REPORTED			= 1;
	public static int	STATUS_NOT_REPORTED			= 2;
	
	private int id 						= 0;
	private String report_date 			= "";
	private String state 				= "";
	private String report_no 			= "";
	private int crew_fatalities 		= 0;
	private int crew_injuries 			= 0;
	private int cabin_fatalities 		= 0;
	private int cabin_injuries 			= 0;
	private int passenger_fatalities 	= 0;
	private int passenger_injuries 		= 0;
	private String aircraft_damage 	= "";
	private String delay_time 			= "";
	private int flight_info_id 			= 0;
	
	//Temporal
	private String injury;
	private String priority;
	private String title;
	
	public String getInjury() {
		return injury;
	}
	public void setInjury(String injury) {
		this.injury = injury;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReport_no() {
		return report_no;
	}
	public void setReport_no(String report_no) {
		this.report_no = report_no;
	}
	public int getCrew_fatalities() {
		return crew_fatalities;
	}
	public void setCrew_fatalities(int crew_fatalities) {
		this.crew_fatalities = crew_fatalities;
	}
	public int getCrew_injuries() {
		return crew_injuries;
	}
	public void setCrew_injuries(int crew_injuries) {
		this.crew_injuries = crew_injuries;
	}
	public int getCabin_fatalities() {
		return cabin_fatalities;
	}
	public void setCabin_fatalities(int cabin_fatalities) {
		this.cabin_fatalities = cabin_fatalities;
	}
	public int getCabin_injuries() {
		return cabin_injuries;
	}
	public void setCabin_injuries(int cabin_injuries) {
		this.cabin_injuries = cabin_injuries;
	}
	public int getPassenger_fatalities() {
		return passenger_fatalities;
	}
	public void setPassenger_fatalities(int passenger_fatalities) {
		this.passenger_fatalities = passenger_fatalities;
	}
	public int getPassenger_injuries() {
		return passenger_injuries;
	}
	public void setPassenger_injuries(int passenger_injuries) {
		this.passenger_injuries = passenger_injuries;
	}
	public String getAircraft_damage() {
		return aircraft_damage;
	}
	public void setAircraft_damage(String aircraft_damage) {
		this.aircraft_damage = aircraft_damage;
	}
	public String getDelay_time() {
		return delay_time;
	}
	public void setDelay_time(String delay_time) {
		this.delay_time = delay_time;
	}
	public int getFlight_info_id() {
		return flight_info_id;
	}
	public void setFlight_info_id(int flight_info_id) {
		this.flight_info_id = flight_info_id;
	}
	
	@Override
	public String toString() {
		return "Report [id=" + id + ", report_date=" + report_date
				+ ", state=" + state + ", report_no=" + report_no
				+ ", crew_fatalities=" + crew_fatalities + ", crew_injuries="
				+ crew_injuries + ", cabin_fatalities=" + cabin_fatalities
				+ ", cabin_injuries=" + cabin_injuries
				+ ", passenger_fatalities=" + passenger_fatalities
				+ ", passenger_injuries=" + passenger_injuries
				+ ", aircraft_damage=" + aircraft_damage + ", delay_time="
				+ delay_time + ", flight_info_id=" + flight_info_id + "]";
	}
}

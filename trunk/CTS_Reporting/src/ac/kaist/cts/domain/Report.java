package ac.kaist.cts.domain;

//CREATE  TABLE IF NOT EXISTS `Report_DB`.`Report` (
//		  `id` Integer NOT NULL AUTO_INCREMENT ,
//		  `report_date` DATETIME NULL ,
//		  `status` VARCHAR(45) NULL ,
//		  `report_no` VARCHAR(45) NULL ,
//		  `crew_fatalities` Integer NULL ,
//		  `crew_injuries` Integer NULL ,
//		  `cabin_fatalities` Integer NULL ,
//		  `cabin_injuries` Integer NULL ,
//		  `passenger_fatalities` Integer NULL ,
//		  `passenger_injuries` Integer NULL ,
//		  `aircraft_damage` VARCHAR(45) NULL ,
//		  `delay_time` VARCHAR(45) NULL ,
//		  `flight_info_id` Integer NOT NULL ,
//		  PRIMARY KEY (`id`) ,
//		  UNIQUE INDEX `idIReports_UNIQUE` (`id` ASC) ,
//		  INDEX `fk_Report_flight_info1_idx` (`flight_info_id` ASC) ,
//		  CONSTRAInteger `fk_Report_flight_info1`
//		    FOREIGN KEY (`flight_info_id` )
//		    REFERENCES `Report_DB`.`flight_info` (`id` )
//		    ON DELETE NO ACTION
//		    ON UPDATE NO ACTION)
//		ENGINE = InnoDB;


public class Report {
	public static String	STATUS_REPORTED				= "Reported";
	public static String	STATUS_NOT_REPORTED			= "Not Reported";
	
	public static String 	STATUS_IDENTIFIED			= "Identified";
	public static String 	STATUS_ACCEPTED				= "Accepted";
	public static String 	STATUS_REVIEW				= "Review";
	public static String 	STATUS_REJECTED				= "Rejected";
	public static String 	STATUS_IN_INVESTIGATION		= "In Investigation";
	public static String 	STATUS_REGISTERED			= "Registered";
	
	private Integer id 						= 0;
	private String report_date 			= "";
	private String report_state 		= "";
	private String type					="";
	private String management_state 	= "";
	private String report_no 			= "";
	private Integer crew_fatalities 		= 0;
	private Integer crew_minor_injuries 	= 0;
	private Integer crew_serious_injuries 	= 0;
	private Integer cabin_fatalities 		= 0;
	private Integer cabin_minor_injuries 			= 0;
	private Integer cabin_serious_injuries 			= 0;
	private Integer passenger_fatalities 	= 0;
	private Integer passenger_minor_injuries 		= 0;
	private Integer passenger_serious_injuries 		= 0;
	private String aircraft_damage 	= "";
	private String delay_time 			= "";
	private Integer report_parent_id		= 0;
	
	//Temporal
	private String injury;
	private String priority;
	private String title;
	
	private String order_column			= "";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManagement_state() {
		return management_state;
	}

	public void setManagement_state(String management_state) {
		this.management_state = management_state;
	}

	public String getReport_no() {
		return report_no;
	}

	public void setReport_no(String report_no) {
		this.report_no = report_no;
	}

	public Integer getCrew_fatalities() {
		return crew_fatalities;
	}

	public void setCrew_fatalities(Integer crew_fatalities) {
		this.crew_fatalities = crew_fatalities;
	}

	public Integer getCrew_minor_injuries() {
		return crew_minor_injuries;
	}

	public void setCrew_minor_injuries(Integer crew_minor_injuries) {
		this.crew_minor_injuries = crew_minor_injuries;
	}

	public Integer getCrew_serious_injuries() {
		return crew_serious_injuries;
	}

	public void setCrew_serious_injuries(Integer crew_serious_injuries) {
		this.crew_serious_injuries = crew_serious_injuries;
	}

	public Integer getCabin_fatalities() {
		return cabin_fatalities;
	}

	public void setCabin_fatalities(Integer cabin_fatalities) {
		this.cabin_fatalities = cabin_fatalities;
	}

	public Integer getCabin_minor_injuries() {
		return cabin_minor_injuries;
	}

	public void setCabin_minor_injuries(Integer cabin_minor_injuries) {
		this.cabin_minor_injuries = cabin_minor_injuries;
	}

	public Integer getCabin_serious_injuries() {
		return cabin_serious_injuries;
	}

	public void setCabin_serious_injuries(Integer cabin_serious_injuries) {
		this.cabin_serious_injuries = cabin_serious_injuries;
	}

	public Integer getPassenger_fatalities() {
		return passenger_fatalities;
	}

	public void setPassenger_fatalities(Integer passenger_fatalities) {
		this.passenger_fatalities = passenger_fatalities;
	}

	public Integer getPassenger_minor_injuries() {
		return passenger_minor_injuries;
	}

	public void setPassenger_minor_injuries(Integer passenger_minor_injuries) {
		this.passenger_minor_injuries = passenger_minor_injuries;
	}

	public Integer getPassenger_serious_injuries() {
		return passenger_serious_injuries;
	}

	public void setPassenger_serious_injuries(Integer passenger_serious_injuries) {
		this.passenger_serious_injuries = passenger_serious_injuries;
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

	public Integer getReport_parent_id() {
		return report_parent_id;
	}

	public void setReport_parent_id(Integer report_parent_id) {
		this.report_parent_id = report_parent_id;
	}

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

	public String getOrder_column() {
		return order_column;
	}

	public void setOrder_column(String order_column) {
		this.order_column = order_column;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"report_date\":\"" + report_date
				+ "\", \"report_state\":\"" + report_state + "\", \"type\":\""
				+ type + "\", \"management_state\":\"" + management_state
				+ "\", \"report_no\":\"" + report_no
				+ "\", \"crew_fatalities\":\"" + crew_fatalities
				+ "\", \"crew_minor_injuries\":\"" + crew_minor_injuries
				+ "\", \"crew_serious_injuries\":\"" + crew_serious_injuries
				+ "\", \"cabin_fatalities\":\"" + cabin_fatalities
				+ "\", \"cabin_minor_injuries\":\"" + cabin_minor_injuries
				+ "\", \"cabin_serious_injuries\":\"" + cabin_serious_injuries
				+ "\", \"passenger_fatalities\":\"" + passenger_fatalities
				+ "\", \"passenger_minor_injuries\":\""
				+ passenger_minor_injuries
				+ "\", \"passenger_serious_injuries\":\""
				+ passenger_serious_injuries + "\", \"aircraft_damage\":\""
				+ aircraft_damage + "\", \"delay_time\":\"" + delay_time
				+ "\", \"report_parent_id\":\"" + report_parent_id
				+ "\", \"injury\":\"" + injury + "\", \"priority\":\""
				+ priority + "\", \"title\":\"" + title
				+ "\", \"order_column\":\"" + order_column + "\"}";
	}
	

	
	
}

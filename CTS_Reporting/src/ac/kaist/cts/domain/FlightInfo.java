package ac.kaist.cts.domain;

//CREATE  TABLE IF NOT EXISTS `Report_DB`.`flight_info` (
//		  `id` INT NOT NULL ,
//		  `reportId` VARCHAR(45) NULL ,
//		  `flight_date` VARCHAR(45) NULL ,
//		  `flight_no` VARCHAR(45) NULL ,
//		  `airline` VARCHAR(45) NULL ,
//		  `route_from` VARCHAR(45) NULL ,
//		  `route_to` VARCHAR(45) NULL ,
//		  `route_diverted` VARCHAR(45) NULL ,
//		  `flight_type` VARCHAR(45) NULL ,
//		  `dom_int_type` VARCHAR(45) NULL ,
//		  `cargo_operation` VARCHAR(45) NULL ,
//		  `no_crew` INT NULL ,
//		  `no_cabin` INT NULL ,
//		  `no_passenger` INT NULL ,
//		  `aircraft_information_id` INT NOT NULL ,
//		  PRIMARY KEY (`id`) ,
//		  INDEX `fk_flight_info_aircraft_information1_idx` (`aircraft_information_id` ASC) ,
//		  CONSTRAINT `fk_flight_info_aircraft_information1`
//		    FOREIGN KEY (`aircraft_information_id` )
//		    REFERENCES `Report_DB`.`aircraft_information` (`id` )
//		    ON DELETE NO ACTION
//		    ON UPDATE NO ACTION)
//		ENGINE = InnoDB;

public class FlightInfo {

	public FlightInfo() {
		// TODO Auto-generated constructor stub
	}
	
	private int id 						= 0;
	private String report_no			= "";
	private String report_state			= "";
	private String report_type			= "";
	private String ac_model				= "";
	private String reportId 			= "";
	private String flight_date 			= "";
	private String flight_no 			= "";
	private String airline 				= "";
	private String route_from 			= "";
	private String route_to 			= "";
	private String route_diverted 		= "";
	private String flight_type 			= "";
	private String dom_int_type 		= "";
	private String cargo_operation 		= "";
	private int no_crew 				= 0;
	private int no_cabin 				= 0;
	private int no_passenger 			= 0;
	private int Report_id					= 0;
	
	
	
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public String getReport_state() {
		return report_state;
	}
	public void setReport_state(String report_state) {
		this.report_state = report_state;
	}
	public String getAc_model() {
		return ac_model;
	}
	public void setAc_model(String ac_model) {
		this.ac_model = ac_model;
	}
	public String getReport_no() {
		return report_no;
	}
	public void setReport_no(String report_no) {
		this.report_no = report_no;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getFlight_date() {
		return flight_date;
	}
	public void setFlight_date(String flight_date) {
		this.flight_date = flight_date;
	}
	public String getFlight_no() {
		return flight_no;
	}
	public void setFlight_no(String flight_no) {
		this.flight_no = flight_no;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getRoute_from() {
		return route_from;
	}
	public void setRoute_from(String route_from) {
		this.route_from = route_from;
	}
	public String getRoute_to() {
		return route_to;
	}
	public void setRoute_to(String route_to) {
		this.route_to = route_to;
	}
	public String getRoute_diverted() {
		return route_diverted;
	}
	public void setRoute_diverted(String route_diverted) {
		this.route_diverted = route_diverted;
	}
	public String getFlight_type() {
		return flight_type;
	}
	public void setFlight_type(String flight_type) {
		this.flight_type = flight_type;
	}
	public String getDom_int_type() {
		return dom_int_type;
	}
	public void setDom_int_type(String dom_int_type) {
		this.dom_int_type = dom_int_type;
	}
	public String getCargo_operation() {
		return cargo_operation;
	}
	public void setCargo_operation(String cargo_operation) {
		this.cargo_operation = cargo_operation;
	}
	public int getNo_crew() {
		return no_crew;
	}
	public void setNo_crew(int no_crew) {
		this.no_crew = no_crew;
	}
	public int getNo_cabin() {
		return no_cabin;
	}
	public void setNo_cabin(int no_cabin) {
		this.no_cabin = no_cabin;
	}
	public int getNo_passenger() {
		return no_passenger;
	}
	public void setNo_passenger(int no_passenger) {
		this.no_passenger = no_passenger;
	}
	
	public int getReport_id() {
		return Report_id;
	}
	public void setReport_id(int report_id) {
		Report_id = report_id;
	}
	@Override
	public String toString() {
		return "FlightInfo [id=" + id + ", report_no=" + report_no
				+ ", report_state=" + report_state + ", report_type="
				+ report_type + ", ac_model=" + ac_model + ", reportId="
				+ reportId + ", flight_date=" + flight_date + ", flight_no="
				+ flight_no + ", airline=" + airline + ", route_from="
				+ route_from + ", route_to=" + route_to + ", route_diverted="
				+ route_diverted + ", flight_type=" + flight_type
				+ ", dom_int_type=" + dom_int_type + ", cargo_operation="
				+ cargo_operation + ", no_crew=" + no_crew + ", no_cabin="
				+ no_cabin + ", no_passenger=" + no_passenger + ", Report_id="
				+ Report_id + "]";
	}
}

package ac.kaist.cts.domain;


//CREATE  TABLE IF NOT EXISTS `Report_DB`.`aircraft_information` (
//		  `id` INT NOT NULL ,
//		  `manufacturer` VARCHAR(45) NULL ,
//		  `model` VARCHAR(45) NULL ,
//		  `serial_no` VARCHAR(45) NULL ,
//		  `regi_no` VARCHAR(45) NULL ,
//		  `no_seat_crew` INT NULL ,
//		  `no_seat_cabin` INT NULL ,
//		  `no_seat_passenger` INT NULL ,
//		  `last_inspection_type` INT NULL ,
//		  `last_inspection_date` VARCHAR(45) NULL ,
//		  PRIMARY KEY (`id`) )
//		ENGINE = InnoDB;

public class AircraftInfo {

	public AircraftInfo() {
		// TODO Auto-generated constructor stub
	}
	
	private int id 							= 0;
	private String manufacturer 			= "";
	private String model 					= "";
	private String serial_no 				= "";
	private String regi_no 					= "";
	private int no_seat_crew 				= 0;
	private int no_seat_cabin 				= 0;
	private int no_seat_passenger 			= 0;
	private String last_inspection_type 	= "";
	private String last_inspection_date 	= "";
	private int Report_id					= 0;
	
	public int getReport_id() {
		return Report_id;
	}
	public void setReport_id(int report_id) {
		Report_id = report_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}
	public String getRegi_no() {
		return regi_no;
	}
	public void setRegi_no(String regi_no) {
		this.regi_no = regi_no;
	}
	public int getNo_seat_crew() {
		return no_seat_crew;
	}
	public void setNo_seat_crew(int no_seat_crew) {
		this.no_seat_crew = no_seat_crew;
	}
	public int getNo_seat_cabin() {
		return no_seat_cabin;
	}
	public void setNo_seat_cabin(int no_seat_cabin) {
		this.no_seat_cabin = no_seat_cabin;
	}
	public int getNo_seat_passenger() {
		return no_seat_passenger;
	}
	public void setNo_seat_passenger(int no_seat_passenger) {
		this.no_seat_passenger = no_seat_passenger;
	}
	public String getLast_inspection_type() {
		return last_inspection_type;
	}
	public void setLast_inspection_type(String last_inspection_type) {
		this.last_inspection_type = last_inspection_type;
	}
	public String getLast_inspection_date() {
		return last_inspection_date;
	}
	public void setLast_inspection_date(String last_inspection_date) {
		this.last_inspection_date = last_inspection_date;
	}
	
	@Override
	public String toString() {
		return "AircraftInfo [id=" + id + ", manufacturer=" + manufacturer
				+ ", model=" + model + ", serial_no=" + serial_no
				+ ", regi_no=" + regi_no + ", no_seat_crew=" + no_seat_crew
				+ ", no_seat_cabin=" + no_seat_cabin + ", no_seat_passenger="
				+ no_seat_passenger + ", last_inspection_type="
				+ last_inspection_type + ", last_inspection_date="
				+ last_inspection_date + ", Report_id=" + Report_id + "]";
	}
	
}

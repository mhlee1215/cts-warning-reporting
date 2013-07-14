package ac.kaist.cts.domain;


//CREATE  TABLE IF NOT EXISTS `Report_DB`.`user_info` (
//  `id` INT NOT NULL AUTO_INCREMENT ,
//  `User_id` INT NOT NULL ,
//  `id_no` VARCHAR(45) NOT NULL ,
//  `name` VARCHAR(45) NULL ,
//  `date_of_birth` VARCHAR(45) NULL ,
//  `medical_certification` INT NULL ,
//  `date_of_last_medical` VARCHAR(45) NULL ,
//  `flight_time` INT NULL ,
//  `this_make_model` INT NULL ,
//  PRIMARY KEY (`id`, `id_no`) ,
//  INDEX `fk_user_info_User1_idx` (`User_id` ASC) ,
//  CONSTRAINT `fk_user_info_User1`
//    FOREIGN KEY (`User_id` )
//    REFERENCES `Report_DB`.`User` (`id` )
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION)
//ENGINE = InnoDB;

public class UserInfo {

	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	
	private int id 							= 0;
	private int User_id 					= 0;
	private String id_no 					= "";
	private String name 					= "";
	private String date_of_birth 			= "";
	private String medical_certification 	= "";
	private String date_of_last_medical 	= "";
	private int flight_time 				= 0;
	private int this_make_model 			= 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getMedical_certification() {
		return medical_certification;
	}
	public void setMedical_certification(String medical_certification) {
		this.medical_certification = medical_certification;
	}
	public String getDate_of_last_medical() {
		return date_of_last_medical;
	}
	public void setDate_of_last_medical(String date_of_last_medical) {
		this.date_of_last_medical = date_of_last_medical;
	}
	public int getFlight_time() {
		return flight_time;
	}
	public void setFlight_time(int flight_time) {
		this.flight_time = flight_time;
	}
	public int getThis_make_model() {
		return this_make_model;
	}
	public void setThis_make_model(int this_make_model) {
		this.this_make_model = this_make_model;
	}
	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", User_id=" + User_id + ", id_no="
				+ id_no + ", name=" + name + ", date_of_birth=" + date_of_birth
				+ ", medical_certification=" + medical_certification
				+ ", date_of_last_medical=" + date_of_last_medical
				+ ", flight_time=" + flight_time + ", this_make_model="
				+ this_make_model + "]";
	}
}

package ac.kaist.cts.domain;

//CREATE  TABLE IF NOT EXISTS `Report_DB`.`risk_owner` (
//		  `id` INT NOT NULL AUTO_INCREMENT ,
//		  `division` VARCHAR(45) NULL ,
//		  `assigned_to` VARCHAR(45) NULL ,
//		  `requested_date` VARCHAR(45) NULL ,
//		  `due_date` VARCHAR(45) NULL ,
//		  `comments` VARCHAR(45) NULL ,
//		  `hazard_id` INT NOT NULL ,
//		  PRIMARY KEY (`id`) ,
//		  INDEX `fk_risk_owner_hazard1_idx` (`hazard_id` ASC) ,
//		  CONSTRAINT `fk_risk_owner_hazard1`
//		    FOREIGN KEY (`hazard_id` )
//		    REFERENCES `Report_DB`.`hazard` (`id` )
//		    ON DELETE NO ACTION
//		    ON UPDATE NO ACTION)
//		ENGINE = InnoDB;

public class RiskOwner {

	public RiskOwner() {
		// TODO Auto-generated constructor stub
	}
	
	private int id = 0;
	private String division = "";
	private String assigned_to = "";
	private String requested_date = "";
	private String due_date = "";
	private String comments = "";
	private int hazard_id = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getRequested_date() {
		return requested_date;
	}
	public void setRequested_date(String requested_date) {
		this.requested_date = requested_date;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getHazard_id() {
		return hazard_id;
	}
	public void setHazard_id(int hazard_id) {
		this.hazard_id = hazard_id;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"division\":\"" + division
				+ "\", \"assigned_to\":\"" + assigned_to
				+ "\", \"requested_date\":\"" + requested_date
				+ "\", \"due_date\":\"" + due_date + "\", \"comments\":\""
				+ comments + "\", \"hazard_id\":\"" + hazard_id + "\"}";
	}

	
}


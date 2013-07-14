package ac.kaist.cts.domain;

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
	
	public static String STATE_SUBMITTED = "SUBMITTED";
	public static String STATE_NOT_SUBMITTED = "NOTSUBMITTED";
	
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
	private String title 		= "";
	private String time 		= "";
	private String time_type 	= "";
	private String narrative 	= "";
	private String recommendation = "";
	private String status 		= "";
	private String priority 	= "";
	private int report_id		= 0;
	private String report_no	=	"";
	public int getId() {
		return id;
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
		return "ReportItem [id=" + id + ", type=" + type + ", title=" + title
				+ ", time=" + time + ", time_type=" + time_type
				+ ", narrative=" + narrative + ", recommendation="
				+ recommendation + ", status=" + status + ", priority="
				+ priority + ", report_id=" + report_id + ", report_no="
				+ report_no + "]";
	}
}

package ac.kaist.cts.domain;

public class Report {
	public static int	STATUS_REGISTERED			= 1;
	public static int	STATUS_INCOMPLETE			= 1;
	public static int	STATUS_COMPLETE				= 1;
	
	private int idReport = 0;
	private int User_idUser = 0;
	private int status = 0;
	
	private String title = "";
	private String report_no = "";
	private String report_date = "";
	private String aircraft_damage = "";
	private String injury = "";
	private String delay_time = "";
	private String priority = "";
	private String state = "";
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReport_no() {
		return report_no;
	}
	public void setReport_no(String report_no) {
		this.report_no = report_no;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getIdReport() {
		return idReport;
	}
	public void setIdReport(int idReport) {
		this.idReport = idReport;
	}
	public int getUser_idUser() {
		return User_idUser;
	}
	public void setUser_idUser(int user_idUser) {
		User_idUser = user_idUser;
	}
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Report [idReport=" + idReport + ", User_idUser=" + User_idUser
				+ ", report_date=" + report_date + ", status=" + status + "]";
	}
	
	
	 
}

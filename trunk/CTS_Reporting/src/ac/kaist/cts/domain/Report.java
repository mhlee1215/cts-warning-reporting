package ac.kaist.cts.domain;

public class Report {
	public static int	STATUS_REGISTERED			= 1;
	public static int	STATUS_INCOMPLETE			= 1;
	public static int	STATUS_COMPLETE				= 1;
	
	private int idReport = 0;
	private int User_idUser = 0;
	private String report_date = "";
	private int status = 0;
	
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

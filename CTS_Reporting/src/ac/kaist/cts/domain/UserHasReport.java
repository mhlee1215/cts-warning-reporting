package ac.kaist.cts.domain;

public class UserHasReport {

	public UserHasReport() {
		// TODO Auto-generated constructor stub
	}

	private int User_id = 0;
	private int Report_id = 0;
	
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public int getReport_id() {
		return Report_id;
	}
	public void setReport_id(int report_id) {
		Report_id = report_id;
	}
	
	@Override
	public String toString() {
		return "UserHasReport [User_id=" + User_id + ", Report_id=" + Report_id
				+ "]";
	}
	
	
}

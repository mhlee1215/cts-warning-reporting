package ac.kaist.cts.domain;

public class Control {

	public Control() {
		// TODO Auto-generated constructor stub
	}

	private int id = 0;
	private String control_no = "";
	private String title = "";
	private String state = "";
	private String start_date = "";
	private String end_date = "";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getControl_no() {
		return control_no;
	}
	public void setControl_no(String control_no) {
		this.control_no = control_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"control_no\":\"" + control_no
				+ "\", \"title\":\"" + title + "\", \"state\":\"" + state
				+ "\", \"start_date\":\"" + start_date + "\", \"end_date\":\""
				+ end_date + "\"}";
	}
	
	
}

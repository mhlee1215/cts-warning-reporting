package ac.kaist.cts.domain;

public class Severity {

	public Severity() {
		// TODO Auto-generated constructor stub
	}
	
	private int id = 0;
	private String occurrence_no = "";
	private String date = "";
	private String occurrence = "";
	private String severity = "";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOccurrence_no() {
		return occurrence_no;
	}
	public void setOccurrence_no(String occurrence_no) {
		this.occurrence_no = occurrence_no;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOccurrence() {
		return occurrence;
	}
	public void setOccurrence(String occurrence) {
		this.occurrence = occurrence;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"occurrence_no\":\"" + occurrence_no
				+ "\", \"date\":\"" + date + "\", \"occurrence\":\""
				+ occurrence + "\", \"severity\":\"" + severity + "\"}";
	}
	
	
}

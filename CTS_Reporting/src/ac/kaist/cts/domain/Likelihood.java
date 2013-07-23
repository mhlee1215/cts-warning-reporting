package ac.kaist.cts.domain;

public class Likelihood {

	public Likelihood() {
		// TODO Auto-generated constructor stub
	}
	
	private int id = 0;
	private String title = "";
	private String date = "";
	private String occurrence = "";
	private String likelihood = "";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getLikelihood() {
		return likelihood;
	}
	public void setLikelihood(String likelihood) {
		this.likelihood = likelihood;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"title\":\"" + title
				+ "\", \"date\":\"" + date + "\", \"occurrence\":\""
				+ occurrence + "\", \"likelihood\":\"" + likelihood + "\"}";
	}
	
	
}

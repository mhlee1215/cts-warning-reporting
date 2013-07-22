package ac.kaist.cts.domain;

public class HazardItem {
	private int id;
	private int seq_num;
	private int item_id;
	private String item_name;
	private int item_level;
	private int hazard_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeq_num() {
		return seq_num;
	}
	public void setSeq_num(int seq_num) {
		this.seq_num = seq_num;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getItem_level() {
		return item_level;
	}
	public void setItem_level(int item_level) {
		this.item_level = item_level;
	}
	
	public int getHazard_id() {
		return hazard_id;
	}
	public void setHazard_id(int hazard_id) {
		this.hazard_id = hazard_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"seq_num\":\"" + seq_num
				+ "\", \"item_id\":\"" + item_id + "\", \"item_name\":\""
				+ item_name + "\", \"item_level\":\"" + item_level
				+ "\", \"hazard_id\":\"" + hazard_id + "\"}";
	}
	
	
	
	
	
	
}

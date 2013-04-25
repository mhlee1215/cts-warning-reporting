package ac.kaist.cts.domain;

public class HazardItem {
	private int id;
	private int seq_num;
	private int item_id;
	private String item_name;
	private int item_level;
	private int report_id;
	
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
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	@Override
	public String toString() {
		return "HazardItem [id=" + id + ", seq_num=" + seq_num + ", item_id="
				+ item_id + ", item_name=" + item_name + ", item_level="
				+ item_level + ", report_id=" + report_id + "]";
	}
	
	
	
	
	
	
}

package ac.kaist.cts.domain;

public class SelectItem {
	
	private int id;
	private String name;
	private int value;
	private String category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "select_Item [id=" + id + ", name=" + name + ", value=" + value
				+ ", category=" + category + "]";
	}

	
	
	
}

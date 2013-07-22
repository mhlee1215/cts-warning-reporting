package ac.kaist.cts.domain;

import java.util.Map;
import java.util.TreeMap;

public class SelectItem {
	
	public static Map<String, String> getPriorityColorMap(){
		Map<String, String> priorityColorMap = new TreeMap<String, String>();
		priorityColorMap.put("High", "red");
		priorityColorMap.put("Medium", "yellow");
		priorityColorMap.put("Low", "green");
		return priorityColorMap;
	}
	
	private int id;
	private String name;
	private String value;
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
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

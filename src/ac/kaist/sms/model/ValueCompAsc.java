package ac.kaist.sms.model;

import java.util.Comparator;
import java.util.Map;

public class ValueCompAsc implements Comparator<Map.Entry<String, Double>> {
	public int compare(Map.Entry<String, Double> e1,
			Map.Entry<String, Double> e2) {
		if (e1.getValue() < e2.getValue()) {
			return 1;
		} else if (e1.getValue() == e2.getValue()) {
			return 0;
		} else {
			return -1;
		}
	}
}
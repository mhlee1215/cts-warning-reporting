package ac.kaist.cts.dao;

import java.util.List;

import ac.kaist.cts.domain.HazardItem;
import ac.kaist.cts.domain.SelectItem;

public interface SelectItemDao {
	List<SelectItem> readSelectItemList(SelectItem item);
	SelectItem readSelectItem(SelectItem item);
}

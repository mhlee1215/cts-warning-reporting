package ac.kaist.cts.dao;

import java.util.List;

import ac.kaist.cts.domain.HazardItem;

public interface HazardItemDao {
	Integer readHazardItemNum(HazardItem item);
	List<HazardItem> readHazardItem(HazardItem item);
}

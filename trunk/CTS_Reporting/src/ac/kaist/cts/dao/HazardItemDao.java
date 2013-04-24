package ac.kaist.cts.dao;

import java.util.List;

import ac.kaist.cts.domain.HazardItem;

public interface HazardItemDao {
	List<HazardItem> readHazardItems(HazardItem item);
}

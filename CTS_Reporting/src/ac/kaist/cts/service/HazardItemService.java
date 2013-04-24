package ac.kaist.cts.service;

import java.util.List;

import ac.kaist.cts.domain.HazardItem;

public interface HazardItemService {
	List<HazardItem> readHazardItems(HazardItem item);
}

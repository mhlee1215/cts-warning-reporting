package ac.kaist.cts.service;

import java.util.List;

import ac.kaist.cts.domain.HazardItemList;

public interface HazardItemListService {
	List<HazardItemList> readTopList();
	List<HazardItemList> readChildList(HazardItemList item);
}

package ac.kaist.cts.dao;

import java.util.List;

import ac.kaist.cts.domain.HazardItemList;

public interface HazardItemListDao {
	List<HazardItemList> readTopList();
	List<HazardItemList> readChildList(HazardItemList item);
	HazardItemList readItem(HazardItemList item);
}

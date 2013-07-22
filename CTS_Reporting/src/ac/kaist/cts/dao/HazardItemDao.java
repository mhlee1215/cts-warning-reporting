package ac.kaist.cts.dao;

import java.util.List;

import ac.kaist.cts.domain.HazardItem;

public interface HazardItemDao {
	Integer readHazardItemNum(HazardItem item);
	List<HazardItem> readHazardItemList(HazardItem item);
	
	public HazardItem readHazardItem(HazardItem hazardItem);
	public void createHazardItem(HazardItem hazardItem);
	public void deleteHazardItem(HazardItem hazardItem);
	public int updateHazardItem(HazardItem hazardItem);
}

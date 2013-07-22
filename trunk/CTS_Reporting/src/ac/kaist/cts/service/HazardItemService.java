package ac.kaist.cts.service;

import java.util.List;
import java.util.Vector;

import ac.kaist.cts.domain.Hazard;
import ac.kaist.cts.domain.HazardItem;

public interface HazardItemService {
	Vector<List<HazardItem> > readHazardItems(HazardItem item);
	
	
	public List<HazardItem> readHazardItemList(HazardItem hazardItem);
	public HazardItem readHazardItem(HazardItem hazardItem);
	public void createHazardItem(HazardItem hazardItem);
	public void deleteHazardItem(HazardItem hazardItem);
	public void updateHazardItem(HazardItem hazardItem);
}

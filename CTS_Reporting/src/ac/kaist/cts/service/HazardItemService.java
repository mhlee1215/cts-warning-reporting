package ac.kaist.cts.service;

import java.util.List;
import java.util.Vector;

import ac.kaist.cts.domain.HazardItem;

public interface HazardItemService {
	Vector<List<HazardItem> > readHazardItems(HazardItem item);
}

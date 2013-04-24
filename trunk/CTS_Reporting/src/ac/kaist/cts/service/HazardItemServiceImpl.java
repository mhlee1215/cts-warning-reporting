package ac.kaist.cts.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kaist.cts.dao.HazardItemDao;
import ac.kaist.cts.dao.HazardItemListDao;
import ac.kaist.cts.domain.HazardItem;

@Service
public class HazardItemServiceImpl implements HazardItemService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private HazardItemDao		hazardItemDao;
	
	@Autowired
	private HazardItemListDao		hazardItemListDao;
	

	@Override
	public List<HazardItem> readHazardItems(HazardItem item) {
		// TODO Auto-generated method stub
		List<HazardItem> item_list = hazardItemDao.readHazardItems(item);
		
		return item_list;
	}
	
	

}

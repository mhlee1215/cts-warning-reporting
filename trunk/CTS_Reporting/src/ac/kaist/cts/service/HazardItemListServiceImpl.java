package ac.kaist.cts.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kaist.cts.dao.HazardItemListDao;
import ac.kaist.cts.domain.HazardItemList;

@Service
public class HazardItemListServiceImpl implements HazardItemListService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private HazardItemListDao		hazardItemListDao;
	
	@Override
	public List<HazardItemList> readTopList() {
		// TODO Auto-generated method stub
		return hazardItemListDao.readTopList();
	}

	@Override
	public List<HazardItemList> readChildList(HazardItemList item) {
		// TODO Auto-generated method stub
		return hazardItemListDao.readChildList(item);
	}

}

package ac.kaist.cts.service;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ac.kaist.cts.dao.HazardItemDao;
import ac.kaist.cts.dao.HazardItemListDao;
import ac.kaist.cts.domain.HazardItem;
import ac.kaist.cts.domain.HazardItemList;

@Service
public class HazardItemServiceImpl implements HazardItemService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private HazardItemDao		hazardItemDao;
	
	@Autowired
	private HazardItemListDao		hazardItemListDao;
	

	@Override
	public Vector<List<HazardItem>> readHazardItems(HazardItem item) {
		// TODO Auto-generated method stub
		Vector<List<HazardItem> > item_list = new Vector<List<HazardItem> >();
		Integer item_num = hazardItemDao.readHazardItemNum(item);
		for (int i = 0 ; i < item_num ; i++){
			item.setSeq_num(i+1);
			List<HazardItem> read_item = hazardItemDao.readHazardItemList(item);
			for (int j = 0 ; j < read_item.size() ; j++){
				HazardItemList q_item = new HazardItemList();			
				q_item.setId(read_item.get(j).getItem_id());
				q_item.setLevel(read_item.get(j).getItem_level());
				HazardItemList r_item = hazardItemListDao.readItem(q_item);
				read_item.get(j).setItem_name(r_item.getName());
			}
			item_list.add(i, read_item);
		}
		return item_list;
	}


	@Override
	public HazardItem readHazardItem(HazardItem hazardItem) {
		// TODO Auto-generated method stub
		return hazardItemDao.readHazardItem(hazardItem);
	}


	@Override
	public void createHazardItem(HazardItem hazardItem) {
		// TODO Auto-generated method stub
		hazardItemDao.createHazardItem(hazardItem);
	}


	@Override
	public void deleteHazardItem(HazardItem hazardItem) {
		// TODO Auto-generated method stub
		hazardItemDao.deleteHazardItem(hazardItem);
	}


	@Override
	public void updateHazardItem(HazardItem hazardItem) {
		// TODO Auto-generated method stub
		hazardItemDao.updateHazardItem(hazardItem);
	}


	@Override
	public List<HazardItem> readHazardItemList(HazardItem hazardItem) {
		// TODO Auto-generated method stub
		return hazardItemDao.readHazardItemList(hazardItem);
	}
	
	

}

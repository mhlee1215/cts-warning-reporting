package ac.kaist.cts.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import ac.kaist.cts.domain.HazardItem;
import ac.kaist.cts.domain.ReportParent;

@Repository
public class HazardItemDaoImpl extends SqlMapClientDaoSupport implements HazardItemDao {

	@Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	} 
	
	@Override
	public List<HazardItem> readHazardItemList(HazardItem item) {
		List<HazardItem> read_item = getSqlMapClientTemplate().queryForList("HazardItemSql.readHazardItem", item);
		return read_item;
	}

	@Override
	public Integer readHazardItemNum(HazardItem item) {
		Integer item_num = (Integer) getSqlMapClientTemplate().queryForObject("HazardItemSql.readHazardItemNum", item);
		return item_num;
	}

	@Override
	public HazardItem readHazardItem(HazardItem hazardItem) {
		HazardItem result = (HazardItem)getSqlMapClientTemplate().queryForObject("HazardItemSql.readHazardItem", hazardItem);
		return result;
	}


	@Override
	public void createHazardItem(HazardItem hazardItem) {
		getSqlMapClientTemplate().insert("HazardItemSql.createHazardItem", hazardItem);
	}


	@Override
	public void deleteHazardItem(HazardItem hazardItem) {
		getSqlMapClientTemplate().delete("HazardItemSql.deleteHazardItem", hazardItem);
		
	}


	@Override
	public int updateHazardItem(HazardItem hazardItem) {
		int affected_row_num = (int)getSqlMapClientTemplate().update("HazardItemSql.updateHazardItem", hazardItem);
		return affected_row_num;
	}

}
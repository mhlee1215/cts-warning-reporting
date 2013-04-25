package ac.kaist.cts.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import ac.kaist.cts.domain.HazardItem;

@Repository
public class HazardItemDaoImpl extends SqlMapClientDaoSupport implements HazardItemDao {

	@Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	} 
	
	@Override
	public List<HazardItem> readHazardItem(HazardItem item) {
		List<HazardItem> read_item = getSqlMapClientTemplate().queryForList("HazardItemSql.readHazardItem", item);
		return read_item;
	}

	@Override
	public Integer readHazardItemNum(HazardItem item) {
		Integer item_num = (Integer) getSqlMapClientTemplate().queryForObject("HazardItemSql.readHazardItemNum", item);
		return item_num;
	}

}
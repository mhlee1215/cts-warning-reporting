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
	@SuppressWarnings("unchecked")
	public List<HazardItem> readHazardItems(HazardItem item) {
		List<HazardItem> array = getSqlMapClientTemplate().queryForList("HazardItemSql.readHazardItems", item);
		return array;
	}

}
package ac.kaist.cts.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import ac.kaist.cts.domain.HazardItemList;
import ac.kaist.cts.domain.User;

@Repository
public class HazardItemListDaoImpl extends SqlMapClientDaoSupport implements HazardItemListDao {

	@Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	} 
	
	@Override
	@SuppressWarnings("unchecked")
	public List<HazardItemList> readTopList() {
		List<HazardItemList> array = getSqlMapClientTemplate().queryForList("HazardItemListSql.readTopList");
		return array;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HazardItemList> readChildList(HazardItemList item) {
		List<HazardItemList> array = getSqlMapClientTemplate().queryForList("HazardItemListSql.readChildList", item);
		return array;
	}

	@Override
	public HazardItemList readItem(HazardItemList item) {
		HazardItemList read_item = (HazardItemList) getSqlMapClientTemplate().queryForObject("HazardItemListSql.readItem", item);
		return read_item;
	}

}

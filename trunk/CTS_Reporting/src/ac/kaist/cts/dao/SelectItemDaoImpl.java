package ac.kaist.cts.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import ac.kaist.cts.domain.SelectItem;

@Repository
public class SelectItemDaoImpl extends SqlMapClientDaoSupport implements SelectItemDao {

	@Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@Override
	@SuppressWarnings("unchecked")
	public List<SelectItem> readSelectItemList(SelectItem item) {
	
		List<SelectItem> array = getSqlMapClientTemplate().queryForList("SelectItemSql.readSelectItemList");
		return array;
	}

	@Override
	public SelectItem readSelectItem(SelectItem item) {
		SelectItem result = (SelectItem)getSqlMapClientTemplate().queryForObject("SelectItemSql.readSelectItem", item);
		return result;
	}

}

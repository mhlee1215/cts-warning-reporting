package ac.kaist.cts.dao;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import ac.kaist.cts.domain.User;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {	
		List<User> array = getSqlMapClientTemplate().queryForList("UserSql.readUserList");
		return array;
	}


	public User readUser(User user) {
		User result = (User)getSqlMapClientTemplate().queryForObject("UserSql.readUser", user);
		return result;
	}


	public void createUser(User user) {
		getSqlMapClientTemplate().insert("UserSql.createUser", user);
	}


	public void deleteUser(User user) {
		getSqlMapClientTemplate().delete("UserSql.deleteUser", user);
		
	}


	public void updateUser(User user) {
		getSqlMapClientTemplate().update("UserSql.updateUser", user);
	}
}

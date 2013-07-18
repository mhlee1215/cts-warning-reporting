package ac.kaist.cts.dao;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import ac.kaist.cts.domain.User;
import ac.kaist.cts.domain.UserHasReport;
import ac.kaist.cts.domain.UserInfo;

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


	@Override
	public UserInfo readUserInfo(UserInfo userInfo) {
		UserInfo result = (UserInfo)getSqlMapClientTemplate().queryForObject("UserInfoSql.readUserInfo", userInfo);
		return result;
	}


	@Override
	public void createUserInfo(UserInfo userInfo) {
		getSqlMapClientTemplate().insert("UserInfoSql.createUserInfo", userInfo);
	}


	@Override
	public void deleteUserInfo(UserInfo userInfo) {
		getSqlMapClientTemplate().delete("UserInfoSql.deleteUserInfo", userInfo);
		
	}


	@Override
	public void updateUserInfo(UserInfo userInfo) {
		getSqlMapClientTemplate().update("UserInfoSql.updateUserInfo", userInfo);
	}


	@Override
	public UserHasReport readUserHasReport(UserHasReport userHasReport) {
		UserHasReport result = (UserHasReport)getSqlMapClientTemplate().queryForObject("UserHasReportSql.readUserHasReport", userHasReport);
		return result;
	}


	@Override
	public void createUserHasReport(UserHasReport userHasReport) {
		getSqlMapClientTemplate().insert("UserHasReportSql.createUserHasReport", userHasReport);
	}


	@Override
	public void deleteUserHasReport(UserHasReport userHasReport) {
		getSqlMapClientTemplate().delete("UserHasReportSql.deleteUserHasReport", userHasReport);
		
	}


	@Override
	public void updateUserHasReport(UserHasReport userHasReport) {
		getSqlMapClientTemplate().update("UserHasReportSql.updateUserHasReport", userHasReport);
	}
}

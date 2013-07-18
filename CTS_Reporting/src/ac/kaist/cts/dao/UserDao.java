package ac.kaist.cts.dao;

import java.util.List;


import ac.kaist.cts.domain.User;
import ac.kaist.cts.domain.UserHasReport;
import ac.kaist.cts.domain.UserInfo;

public interface UserDao {
	public User readUser(User user);
	public void createUser(User user);
	public void deleteUser(User user);
	public void updateUser(User user);
	public List<User> findAll();
	
	public UserInfo readUserInfo(UserInfo userInfo);
	public void createUserInfo(UserInfo userInfo);
	public void deleteUserInfo(UserInfo userInfo);
	public void updateUserInfo(UserInfo userInfo);
	
	public UserHasReport readUserHasReport(UserHasReport userHasReport);
	public void createUserHasReport(UserHasReport userHasReport);
	public void deleteUserHasReport(UserHasReport userHasReport);
	public void updateUserHasReport(UserHasReport userHasReport);
}

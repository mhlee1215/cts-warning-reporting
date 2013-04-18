package ac.kaist.cts.dao;

import java.util.List;


import ac.kaist.cts.domain.User;
import ac.kaist.cts.domain.UserIdMap;

public interface UserDao {
	public User readUser(User user);
	public void createUser(User user);
	public void deleteUser(User user);
	public void updateUser(User user);
	public List<User> findAll();
	public UserIdMap getUserIdMap(UserIdMap userIdMap);
	public void createUserIdMap(UserIdMap userIdMap);
	public int getNextUserIdMap();
}

package ac.kaist.cts.service;

import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.mail.EmailException;

import ac.kaist.cts.domain.User;

public interface UserService {
	public int readUser(User user) throws Exception;
	public User readUserData(User user) throws Exception;
	public int createUser(User user) throws Exception;
	//public void deleteUser(User user);
	public void updateUser(User user);
	
	public List<User> findAll();
	public int activateUser(String id);
	public int deleteUser(String id);
	public int deleteUser(String id, boolean isDeleteRow);
	public int findPassword(String id) throws EmailException, MalformedURLException;
}

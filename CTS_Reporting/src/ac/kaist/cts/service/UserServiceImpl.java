package ac.kaist.cts.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import ac.kaist.cts.dao.UserDao;
import ac.kaist.cts.domain.User;
import ac.kaist.cts.domain.UserIdMap;
import ac.kaist.cts.utils.Crypto;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserDao		userDao;
	
	boolean isEncrypt = false;

	public List<User> findAll() {
		return userDao.findAll();
	}

	public int createUser(User user) throws Exception {
		
		
		//Encrypt
        String passwordEncrypted = user.getPassword();
        if(isEncrypt)
            passwordEncrypted = Crypto.encrypt(passwordEncrypted);
        user.setPassword(passwordEncrypted);
		//
        
		User paramUser = new User();
		paramUser.setId(user.getId());
		User foundUser = readUserData(paramUser);
		logger.debug("create User");
		logger.debug("==[S]============================");
		
		if(foundUser != null){
			logger.debug("User is already registered. Cancel Register.");
			logger.debug("==[E]============================");
			return User.STATUS_ALREADY_REGISTEREDED;
		}
		else{
			logger.debug("User doesn't find. Go Register.");
			userDao.createUser(user);
			logger.debug("==[E]============================");
			return User.STATUS_SUCCESS_REGISTER;
		}
		
		
	}

	

	public int readUser(User user) throws Exception {
		
		String password = user.getPassword();
		if(isEncrypt)
		    password = Crypto.encrypt(password);
        //
        
		User paramUser = new User();
		paramUser.setId(user.getId());
		paramUser.setPassword(password);
		User readed = userDao.readUser(paramUser);
		
		if(readed == null){
			return User.STATUS_NOT_FOUNDED;
		}else{
			return User.STATUS_FOUNDED;
		}
	}
	
	public User readUserData(User user) throws Exception {
		return userDao.readUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public int activateUser(String id) {

		User paramUser = new User();
		paramUser.setId(id);
		User user = userDao.readUser(paramUser);
		if (user != null) {
			if (user.getIsactivated().equals("Y")) {
				//alread activated.
				return User.STATUS_ALREADY_ACTIVATED;
			} else {
				paramUser.setIsactivated("Y");
				userDao.updateUser(paramUser);
				// Activate successfully.
				return User.STATUS_SUCCESS_ACTIVATED;
			}
		}
		// Activate fail.
		return User.STATUS_NOT_FOUNDED;
	}

	public int deleteUser(String id) {
		return deleteUser(id, false);
	}
	public int deleteUser(String id, boolean isDeleteRow) {
		User paramUser = new User();
		paramUser.setId(id);
		User user = userDao.readUser(paramUser);
		if (user != null) {
			if (user.getIsdeleted().equals("Y")) {
				//alread deleted.
				return User.STATUS_ALREADY_DELETED;
			} else {
				if(isDeleteRow){
					userDao.deleteUser(paramUser);
				}else{
					paramUser.setIsactivated("Y");
					userDao.updateUser(paramUser);
					//delete successfully.
				}
				return User.STATUS_SUCCESS_DELETED;
			}
		}
		// Delete fail.
		return User.STATUS_NOT_FOUNDED;
		
	}

	public int findPassword(String id) throws EmailException, MalformedURLException {
		
		User paramUser = new User();
		paramUser.setId(id);
		User user = userDao.readUser(paramUser);
		if (user != null) {

			return User.STATUS_SUCCESS_SEND_FINDPASSWORD;
		}
		// Delete fail.
		return User.STATUS_NOT_FOUNDED;
	}

	public void createUserIdMap(UserIdMap userIdMap) {
		userDao.createUserIdMap(userIdMap);
		
	}

	public int getNextUserIdMap() {
		return userDao.getNextUserIdMap();
	}

	public UserIdMap getUserIdMap(String externalId) {
		UserIdMap userIdMap = new UserIdMap();
		userIdMap.setExternalId(externalId);
		return userDao.getUserIdMap(userIdMap);
	}
}

package ac.kaist.cts.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * CTS Reporting System at KAIST
 * 
 * @author minhaeng lee at KAIST
 * minhaeng.leee@gmail.com
 * 
 */

public class User {
	
	public static List<String> getUserTypeList(){
		List<String> userTypeList = new ArrayList<String>();
		userTypeList.add(User.TYPE_PILOT);
		userTypeList.add(User.TYPE_CABIN);
		userTypeList.add(User.TYPE_GROUND);
		userTypeList.add(User.TYPE_MAINTENANCE);
		userTypeList.add(User.TYPE_DISPATCHER);
		return userTypeList;
		
	}
	
	public static Map<String, String> getUserTypeNameMap(){
		Map<String, String> userTypeNameMap = new TreeMap<String, String>();
		userTypeNameMap.put(User.TYPE_PILOT, "Pilot");
		userTypeNameMap.put(User.TYPE_CABIN, "Cabin");
		userTypeNameMap.put(User.TYPE_GROUND, "Ground");
		userTypeNameMap.put(User.TYPE_MAINTENANCE, "Maintenance");
		userTypeNameMap.put(User.TYPE_DISPATCHER, "Dispatcher");
		return userTypeNameMap;
	}
	
	public static int	STATUS_NOT_FOUNDED			= 1;
	public static int	STATUS_FOUNDED				= 2;
	public static int	STATUS_SUCCESS_REGISTER		= 3;
	public static int	STATUS_SUCCESS_ACTIVATED	= 4;
	public static int	STATUS_SUCCESS_DELETED		= 5;
	public static int	STATUS_ALREADY_REGISTEREDED	= 6;
	public static int	STATUS_ALREADY_ACTIVATED	= 7;
	public static int	STATUS_ALREADY_DELETED		= 8;
	public static int	STATUS_SUCCESS_SEND_FINDPASSWORD = 9;
	public static int	STATUS_FAIL_SEND_FINDPASSWORD = 10;
	
	public static String TYPE_PILOT = "P";
	public static String TYPE_CABIN = "C";
	public static String TYPE_GROUND = "G";
	public static String TYPE_MAINTENANCE = "M";
	public static String TYPE_DISPATCHER = "D";
	public static String TYPE_SAFETYMANAGER = "SM";
	
	private int		id				=0;
	private String 	userId			="";
	private String	email			="";
	private String 	name			="";
	private String 	password		="";
	private int 	type			=0 ;
	
	private String	isactivated		= "";	// WHETHER THE ACCOUNT IS ACTIVATED OR NOT
	private String	isdeleted		= "";	// WHETHER THE ACCOUNT IS DELETEDED OR NOT
	private String 	registeredtime 	= "";
	private String 	deletedtime 	= "";
	private String 	activatedtime 	= "";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIsactivated() {
		return isactivated;
	}
	public void setIsactivated(String isactivated) {
		this.isactivated = isactivated;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getRegisteredtime() {
		return registeredtime;
	}
	public void setRegisteredtime(String registeredtime) {
		this.registeredtime = registeredtime;
	}
	public String getDeletedtime() {
		return deletedtime;
	}
	public void setDeletedtime(String deletedtime) {
		this.deletedtime = deletedtime;
	}
	public String getActivatedtime() {
		return activatedtime;
	}
	public void setActivatedtime(String activatedtime) {
		this.activatedtime = activatedtime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", email=" + email
				+ ", name=" + name + ", password=" + password + ", type="
				+ type + ", isactivated=" + isactivated + ", isdeleted="
				+ isdeleted + ", registeredtime=" + registeredtime
				+ ", deletedtime=" + deletedtime + ", activatedtime="
				+ activatedtime + "]";
	}
	
}

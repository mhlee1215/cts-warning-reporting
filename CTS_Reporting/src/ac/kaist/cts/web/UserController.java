package ac.kaist.cts.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import language.LanguagePack;
import language.LanguagePackEng;
import language.LanguageServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ac.kaist.cts.domain.User;
import ac.kaist.cts.service.UserService;


@Controller
public class UserController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final UserService userService = null;
		
	@RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String submittedUserId = ServletRequestUtils.getStringParameter(request, "submittedUserId", "");
		String loginComplete = ServletRequestUtils.getStringParameter(request, "loginComplete", "false");
		String loginFail = ServletRequestUtils.getStringParameter(request, "loginFail", "false");
		String logoutComplete = ServletRequestUtils.getStringParameter(request, "logoutComplete", "false");
		String registerComplete = ServletRequestUtils.getStringParameter(request, "registerComplete", "false");
		String registerFail = ServletRequestUtils.getStringParameter(request, "registerFail", "false");
	    String userid = (String)request.getSession().getAttribute("userid");
	    
	    if(userid == null){
    		String remoteHost = request.getRemoteHost();
    		//UserIdMap userIdMap = new UserIdMap();
    		//UserIdMap result = userService.getUserIdMap(remoteHost);
    		
//    		if(result == null){
//    			int nextId = userService.getNextUserIdMap();
//    			userIdMap.setExternalId(remoteHost);
//    			userIdMap.setInternalId(nextId);
//    			userService.createUserIdMap(userIdMap);
//    			User user = new User();
//    			user.setId(Integer.toString(userIdMap.getInternalId()));
//    			user.setInternalid((userIdMap.getInternalId()));
//    			user.setPassword("N/A");
//    			userService.createUser(user);
//    			
//    			result = userService.getUserIdMap(remoteHost);
//    		}
    		
    		//request.getSession().setAttribute("userid", Integer.toString(result.getInternalId()));
    		request.getSession().setAttribute("externalid", remoteHost);
    		
    		
	    }
	    String language = (String)request.getSession().getAttribute("lang");
		LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		
		ModelAndView model = new ModelAndView("login");
		model.addObject("page_title", lang.getStringHazardReportingSystem());
		model.addObject("loginComplete", loginComplete);
		model.addObject("loginFail", loginFail);
		model.addObject("logoutComplete", logoutComplete);
		model.addObject("registerComplete", registerComplete);
		model.addObject("registerFail", registerFail);
		model.addObject("submittedUserId", submittedUserId);
		model.addObject("isUseController", "true");
		
		
		
		model.addObject("lang", lang);
		
		
		//model.addObject("userId", id);
		
		return model;
    }
	
	@RequestMapping("/changeLang.do")
    public ModelAndView changeLang(HttpServletRequest request, HttpServletResponse response) {
		String language = ServletRequestUtils.getStringParameter(request, "language", "");
		request.getSession().setAttribute("lang", language);
		ModelAndView model = new ModelAndView("redirect:index.do");
		return model;
    }
	
	@RequestMapping("/login.do")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = ServletRequestUtils.getStringParameter(request, "id", "");
		String password = ServletRequestUtils.getStringParameter(request, "password", "");
		String work_type = ServletRequestUtils.getStringParameter(request, "name_work_type_radio", "");
		String user_type_reporting  = ServletRequestUtils.getStringParameter(request, "user_type_reporting", "");
		String user_type_management = ServletRequestUtils.getStringParameter(request, "user_type_management", "");
		String language = ServletRequestUtils.getStringParameter(request, "language", "");
			
		logger.debug("public ModelAndView login");
		logger.debug("===[S]======================");
		logger.debug("id : "+id);
		logger.debug("password : "+password);
		logger.debug("work_type : "+work_type);
		logger.debug("user_type_reporting : "+user_type_reporting);
		logger.debug("user_type_management : "+user_type_management);
		
		System.out.println("work_type : "+work_type);
		System.out.println("user_type_reporting : "+user_type_reporting);
		System.out.println("user_type_management : "+user_type_management);
		
		logger.debug("language : "+language);
		User user = new User();
		user.setUserId(id);
		user.setPassword(password);
		
		int result = 0;//userService.readUser(user);
		
		User readed = userService.readUserData(user);
		
		if(readed == null){
			result =  User.STATUS_NOT_FOUNDED;
		}else{
			result = User.STATUS_FOUNDED;
		}
		System.out.println(readed);
		ModelAndView model = null;//new ModelAndView("redirect:index.do");
		
		if(result == User.STATUS_NOT_FOUNDED){
			System.out.println("User does not exist! or password is wrong.");			
			model = new ModelAndView("redirect:index.do");
			
			model.addObject("loginFail", "true");
		}
		else if(result == User.STATUS_FOUNDED){
			System.out.println("User is founded!");
			//UserIdMap userIdMap = userService.getUserIdMap(id);
			//request.getSession().setAttribute("userid", Integer.toString(userIdMap.getInternalId()));
			//request.getSession().setAttribute("externalid", userIdMap.getExternalId());
			request.getSession().setAttribute("islogin", "true");
			request.getSession().setAttribute("user_id_no", readed.getId());
			request.getSession().setAttribute("user_id", readed.getUserId());
			request.getSession().setAttribute("user_name", readed.getName());
			request.getSession().setAttribute("work_type", work_type);
			if("report".equals(work_type))
				request.getSession().setAttribute("user_type", user_type_reporting);
			else if("management".equals(work_type))
				request.getSession().setAttribute("user_type", user_type_management);
			request.getSession().setAttribute("lang", language);
			
			if(work_type.equals("report")){
				model = new ModelAndView("redirect:index.do");
			}else if(work_type.equals("management")){
				model = new ModelAndView("redirect:management.do");
			}
			
			//model.addObject("loginComplete", "true");
				
		}
		//model.addObject("userId", id);
		logger.debug("===[S]======================");
		return model;
    }
	
	@RequestMapping("/register.do")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		

		//int nextId = userService.getNextUserIdMap();
		User user = new User();
		user.setUserId(id);
		//user.setInternalid(nextId);
		user.setPassword(password);
		int result = userService.createUser(user);
		
		ModelAndView model = new ModelAndView("redirect:index.do");
		if(result == User.STATUS_ALREADY_REGISTEREDED){
			System.out.println("The ID requested to register is already exists!");
			model.addObject("registerFail", "true");
			model.addObject("submittedUserId", id);
		}else if(result == User.STATUS_SUCCESS_REGISTER){
			
			//UserIdMap userIdMap = new UserIdMap();
			//userIdMap.setExternalId(id);
			//userIdMap.setInternalId(nextId);
			//userService.createUserIdMap(userIdMap);
			model.addObject("registerComplete", "true");
			model.addObject("submittedUserId", id);
		}
		return model;
    }
	
	@RequestMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().removeAttribute("userid");
		request.getSession().removeAttribute("externalid");
		request.getSession().removeAttribute("islogin");
		
		ModelAndView model = new ModelAndView("redirect:index.do");
		model.addObject("logoutComplete", "true");
		return model;
    }
	
	@RequestMapping("/findPassword.do")
    public ModelAndView findPassword(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().removeAttribute("userid");
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("logoutComplete", "true");
		return model;
    }
	
	
	@RequestMapping("/changePassword.do")
    public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().removeAttribute("userid");
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("logoutComplete", "true");
		return model;
    }
	
	@RequestMapping("/updateDesc.do")
    public ModelAndView updateWebDesc(HttpServletRequest request, HttpServletResponse response) {
		
		String desc = ServletRequestUtils.getStringParameter(request, "desc", "");
		String category = ServletRequestUtils.getStringParameter(request, "category", "");
		String password = ServletRequestUtils.getStringParameter(request, "password", "");
		logger.debug("desc : "+desc);
		logger.debug("category : "+category);
		logger.debug("password : "+password);
		String message = "";
		if("ml.ssu.ac.kr".equals(password)){

		}else{
			message = "Update fail. password mismatch";
		}
		ModelAndView model = new ModelAndView("redirect:"+category+".do");
		model.addObject("message", message);
		return model;
    }
	
	

}

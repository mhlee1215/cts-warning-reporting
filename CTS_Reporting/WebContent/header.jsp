<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
// 	String userid = (String)session.getAttribute("userid");
// 	System.out.println(request.getServerName());
// 	System.out.println(request.getServerPort());
// 	System.out.println(request.getServletPath());
// 	System.out.println(request.getScheme());
// 	String address = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	
// 	String remoteHost = request.getRemoteHost();
// 	String remoteAttr = request.getRemoteAddr(); 
// 	String proxyClientIp = request.getHeader("Proxy-Client-IP");
	
// 	String islogin = (String)session.getAttribute("islogin");
// 	String externalId = remoteHost;
// 	if("true".equals(islogin)){
// 	    externalId = (String)session.getAttribute("externalid");
// 	}
	
// 	String isUseController = ServletRequestUtils.getStringParameter(request, "isUseController", "false");
// 	String isAdmin = ServletRequestUtils.getStringParameter(request, "isAdmin", "false");
// 	if(isAdmin.equals("true")) session.setAttribute("isAdmin", "true");
	
// 	String sessionId = (String)session.getAttribute("userid");
// 	String flag = (String)session.getAttribute("isAdmin");
// 	System.out.println("aaa"+sessionId);
// 	System.out.println("1idUseController : "+isUseController);
%>

<div id="templatemo_header">

    <div id="site_title"><h1>CTS Warning Reporting System</h1></div>
    
    <div id="search_box">
        <!-- <form action="#" method="get">
            <input type="text" value="Search" name="q" size="10" id="searchfield" title="searchfield" onfocus="clearText(this)" onblur="clearText(this)" />
        </form>
         -->
    </div>
    
</div> <!-- end of templatemo header -->

<div id="templatemo_menu">
    <ul>
        <li><a href="index.jsp" class="current">Home</a></li>
        <li><a href="login.jsp">Login</a></li>
        <li><a href="report/reportMain.jsp" onclick="window.open(this.href,'targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=SomeSize,height=SomeSize')">New reports <br>(For user)</a></li>
        <li><a href="about.html">Report List (For admin)</a></li>
        <li><a href="contact.html" class="last">Contact</a></li>
    </ul>    	
</div> <!-- end of templatemo_menu -->
    
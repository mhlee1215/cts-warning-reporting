<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
    
	String user_name = (String)session.getAttribute("user_name");
	String user_type = (String)session.getAttribute("user_type");

	
 	String islogin = (String)session.getAttribute("islogin");

%>
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.lightbox-0.5.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.lightbox-0.5.css" media="screen" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.lightbox-0.5.js"></script>
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
  <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script>
	$(function() {
		$("#id_header_home_btn")
		.button()
		.click(function( event ) {
		 event.preventDefault();
		 document.location = '${pageContext.request.contextPath}/index.do';
		});
		$("#id_header_logout_btn")
		.button()
		.click(function( event ) {
		 event.preventDefault();
		 document.location = '${pageContext.request.contextPath}/logout.do';
		});
		
	});
</script>
<div id="templatemo_header">
	<table style="width:100%">
	<tr>
	<td width="15%" align="left">
		<a id="id_header_home_btn" href="#">Home</a>
	</td>
	<td width="70%" align="center">
    <h1>${page_title}</h1>
    </td>
    <td width="15%" align="right">
    
	    <%if("true".equals(islogin)){ %>
			[<%=user_name %>(<%=user_type %>)] <a id="id_header_logout_btn" href="#">Log out</a>
			<%}else{ %>
	
			<%} %>
	
         </td>
    </tr>
    </table>
    
    
</div> <!-- end of templatemo header -->

<div id="templatemo_menu" style="display:none">
    <ul>
        <li></li>
        <li><a href="login.jsp">Login</a></li>
        <li><a href="report/reportMain.jsp" onclick="window.open(this.href,'targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=SomeSize,height=SomeSize')">New reports <br>(For user)</a></li>
        <li><a href="about.html">Report List (For admin)</a></li>
        <li><a href="contact.html" class="last">Contact</a></li>
    </ul>    	
</div> <!-- end of templatemo_menu -->
    
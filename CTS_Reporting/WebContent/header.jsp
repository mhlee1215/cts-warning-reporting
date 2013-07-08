<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
  
	String work_type = (String)session.getAttribute("work_type");
	String user_name = (String)session.getAttribute("user_name");
	String user_type = (String)session.getAttribute("user_type");

	
 	String islogin = (String)session.getAttribute("islogin");

%>
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.lightbox-0.5.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.lightbox-0.5.css" media="screen" />
<style>
 .header_user_name {
	  padding: 0.2em 0.5em;
	  background-color:white;
	  color:rgb(30, 30, 30);
	  font-weight:bold;
	  font-size:120%;
	  text-align:left;
  }
  .header_title {
	  padding: 0.2em 0.5em;
	  color:rgb(30, 30, 30);
	  font-weight:bold;
	  font-size:250%;
	  text-align:center;
  }
</style>
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
		
		//$('#id_header_language_selector')
		
	});
	
	function changeLanguage(value){
		$('#id_header_language_form').attr('action', '${pageContext.request.contextPath}/changeLang.do');
		$('#id_header_language_input').attr('value', value);
		$('#id_header_language_form').submit();
	}
</script>
<form id="id_header_language_form" >
	<input id="id_header_language_input" name="language" type="hidden"/>
</form>
<div>
	<table style="width:100%">
	<tr>
		<td align="center" class="header_title">${page_title}</td>
	</tr>
	</table>
	<div style="height:10px;"></div>
	<table style="width:100%; height:30px;">
	 <%if("true".equals(islogin)){ %>
	
	<tr>
	<td width="100" align="left">
		<% if(!"management".equals(work_type)){ %>
		<a id="id_header_home_btn" href="#">${lang.getStringHome()}</a>
		<%} %>
	</td>
	<td  align="center" width="500">
    	
    </td>
    
    <td align="right">
    
	   
	    <select id="id_header_language_selector" onchange="changeLanguage(this.value);" name="language"
			class="form_selector">
				<option value="Eng" ${lang.getLanguage() == "Eng" ? "selected=\"selected\"" : ""}>English</option>
				<option value="Kor" ${lang.getLanguage() == "Kor" ? "selected=\"selected\"" : ""}>Korean</option>
		</select>
		
		<span class="header_user_name">[<%=user_name %> (<%=user_type %>)]</span>
		
		<a id="id_header_logout_btn" href="#">${lang.getStringLogout()}</a>
    </td>
    
    </tr>
    
    <%}%>

		
	</table>
    
</div> <!-- end of templatemo header -->
    
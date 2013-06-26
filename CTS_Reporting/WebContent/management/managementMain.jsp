<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>CTS Warning Reporint Stystem</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <style>
  .leftmost_label{
  	width:100px;
  	text-align:right;
  }
  .leftmost_label2{
  	width:100px;
  	text-align:right;
  	font-weight:bold;
  }
  .leftmost_header1{
    font-size:12px;
  	font-weight:bold;
  	margin-left:5px;
  	width:100px;
  	text-align:left;
  }
  .form_input_text{
  	width:120px;
  }
  .form_selector{
  	margin-left:2px;
  	margin-right:2px;
  	width:120px;
  }
  body {
  
    margin: 10px auto 0 auto;
    font-family: Arial, Helvetica;
    font-size: small;
    background-color: #eee;
    /*background-image: url(data:image/gif;base64,R0lGODlhCAAIAJEAAMzMzP///////wAAACH5BAEHAAIALAAAAAAIAAgAAAINhG4nudroGJBRsYcxKAA7);*/
  }

  /* ------------------------------------------------- */

  #tabs {
    overflow: hidden;
    width: 100%;
    margin: 0;
    padding: 0;
    list-style: none;
  }

  #tabs li {
    float: left;
    margin: 0 -15px 0 0;
  }

  #tabs a {
    float: left;
    position: relative;
    padding: 0 20px;
    height: 0;
    line-height: 30px;
    text-transform: uppercase;
    text-decoration: none;
    color: #fff;      
    border-right: 30px solid transparent;
    border-bottom: 30px solid #3D3D3D;
    border-bottom-color: #777\9;
    opacity: .3;
    filter: alpha(opacity=30);      
  }

  #tabs a:hover,
  #tabs a:focus {
    border-bottom-color: #2ac7e1;
    opacity: 1;
    filter: alpha(opacity=100);
  }

  #tabs a:focus {
    outline: 0;
  }

  #tabs .current {
    z-index: 3;
    border-bottom-color: #3d3d3d;
    opacity: 1;
    filter: alpha(opacity=100);      
  }

  /* ----------- */
  #content {
      background: #fff;
      border-top: 2px solid #3d3d3d;
      padding: 1em;
      /*height: 800px;*/
  }

  .report_title {
    font-size: 2.5em;
  	margin: 0 0 5px 0;
  }
  
  #content h2,
    #content h3,
    #content p {
      margin: 0 0 0 0;
  }  

  /* Demo page only */
  #about {
      color: #999;
      text-align: center;
      font: 0.9em Arial, Helvetica;
  }

  #about a {
      color: #777;
  }   
  </style>  
  <script>
  $(function() {
	  
  });
  </script>
</head>
<body>
<%@include file="/header.jsp"%>

  <ul id="tabs">
      <li><a href="#" id="id_tab1" name="#tab1">Review</a></li>
      <li><a href="#" id="id_tab2" name="#tab2">Hazard Identification</a></li>
      <li><a href="#" id="id_tab3" name="#tab3">Risk Analysis</a></li>
      <li><a href="#" id="id_tab4" name="#tab4">Risk Assessment</a></li>    
      <li><a href="#" id="id_tab5" name="#tab5">Mitigation</a></li>
      
  </ul>

  <div id="content">
      <!-- <h1 align="center" class="report_title">PILOT REPORT</h1> -->
      <div id="tab1"></div>
      <div id="tab2"></div>
      <div id="tab3"></div>
      <div id="tab4"></div>
      <div id="tab5"></div>
  </div>
  

  <script>
    function resetTabs(){
        $("#content > div").hide(); //Hide all content
        $("#tabs a").attr("class",""); //Reset id's      
    }

    var myUrl = window.location.href; //get URL
    var myUrlTab = myUrl.substring(myUrl.indexOf("#")); // For localhost/tabs.html#tab2, myUrlTab = #tab2     
    var myUrlTabName = myUrlTab.substring(0,4); // For the above example, myUrlTabName = #tab

    (function(){

        $("#content > div").hide(); // Initially hide all content
        $("#tabs li:first a").attr("class","current"); // Activate first tab
        $("#content > div:first").fadeIn(); // Show first tab content
        
        $("#tabs a").on("click",function(e) {
            e.preventDefault();
            if ($(this).attr("class") == "current"){ //detection for current tab
            	return;       
            }
            else {
            	resetTabs();
            	$(this).attr("class","current"); // Activate this
            	$($(this).attr('name')).fadeIn(); // Show content for current tab
            }
        });
        
        $("#tab1").load("${pageContext.request.contextPath}/management/managementReview.jsp");
    	$("#tab2").load("${pageContext.request.contextPath}/management/managementHazardIdentification.jsp");
    	$("#tab3").load("${pageContext.request.contextPath}/management/managementRiskAnalysis.jsp");
    	$("#tab4").load("${pageContext.request.contextPath}/management/managementRiskAssessment.jsp");
    	$("#tab5").load("${pageContext.request.contextPath}/management/managementMitigation.jsp");

    })()
    
    function changeTab(num){
    	resetTabs();
    	$("#id_tab"+num).attr("class","current"); // Activate this
    	$($("#id_tab"+num).attr('name')).fadeIn(); // Show content for current tab
    	$('html,body').scrollTop(0);
    }
  </script>
</body>
</html>
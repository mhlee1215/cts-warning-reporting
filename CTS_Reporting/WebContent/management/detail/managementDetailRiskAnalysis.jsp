<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Risk Analysis</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/themes/base/jquery-ui.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/jquery-1.9.1.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/ui/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <script>
  $(function() {
	  $("#id_management_risk_analysis_view_report_btn")
	  .button({icons: {secondary: "ui-icon-forder-open" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   window.open('${pageContext.request.contextPath}/managementDetailHazardIdentificationReport.do?report_no=RP200713KE1203-1&category=&type=TAXI-OUT&readonly=Y','viewReportWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=950,height=900');
	   
	  });
	  
	  
	  function resetTabs(){
	        $("#id_management_risk_analysis_content > div").hide(); //Hide all content
	        $("#id_management_risk_analysis_tabs a").attr("class",""); //Reset id's      
	    }

	    var myUrl = window.location.href; //get URL
	    var myUrlTab = myUrl.substring(myUrl.indexOf("#")); // For localhost/tabs.html#tab2, myUrlTab = #tab2     
	    var myUrlTabName = myUrlTab.substring(0,4); // For the above example, myUrlTabName = #tab

	    (function(){

	        $("#id_management_risk_analysis_content > div").hide(); // Initially hide all content
	        $("#id_management_risk_analysis_tabs li:first a").attr("class","current"); // Activate first tab
	        $("#id_management_risk_analysis_content > div:first").fadeIn(); // Show first tab content
	        
	        $("#id_management_risk_analysis_tabs a").on("click",function(e) {
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
	        
	        $("#management_risk_analysis_tab1").load("${pageContext.request.contextPath}/managementDetailRiskAnalysisLikelihood.do?hazard_no=${hazard_no}");
	    	$("#management_risk_analysis_tab2").load("${pageContext.request.contextPath}/managementDetailRiskAnalysisSeverity.do?hazard_no=${hazard_no}");
	    })();
  });
  </script>
  
</head>
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
  .leftmost_label_widt{
  	width:250px;
  	text-align:right;
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

  #id_management_risk_analysis_tabs {
    overflow: hidden;
    width: 100%;
    margin: 0;
    padding: 0;
    list-style: none;
  }

  #id_management_risk_analysis_tabs li {
    float: left;
    margin: 0 -15px 0 0;
  }

  #id_management_risk_analysis_tabs a {
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

  #id_management_risk_analysis_tabs a:hover,
  #id_management_risk_analysis_tabs a:focus {
    border-bottom-color: #2ac7e1;
    opacity: 1;
    filter: alpha(opacity=100);
  }

  #id_management_risk_analysis_tabs a:focus {
    outline: 0;
  }

  #id_management_risk_analysis_tabs .current {
    z-index: 3;
    border-bottom-color: #3d3d3d;
    opacity: 1;
    filter: alpha(opacity=100);      
  }

  /* ----------- */
  #id_management_risk_analysis_content {
      background: #fff;
      border-top: 2px solid #3d3d3d;
      padding: 1em;
      /*height: 800px;*/
  }

  .report_title {
    font-size: 2.5em;
  	margin: 0 0 5px 0;
  }
  
  #id_management_risk_analysis_content h2,
    #id_management_risk_analysis_content h3,
    #id_management_risk_analysis_content p {
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
<body>
 
<table width="100%"> 
<tr>
<td>
<div class="ui-widget-header">${lang.getStringHazardNo()} : ${hazard_no}</div>
</td>
<td align="right"><a id="id_management_risk_analysis_view_report_btn" href="#">${lang.getStringViewReport()}</a></td>
</tr>
</table>
 
<ul id="id_management_risk_analysis_tabs">
      <li><a href="#" id="id_management_risk_analysis_tab1" name="#management_risk_analysis_tab1">${lang.getStringLikelihood()}</a></li>
      <li><a href="#" id="id_management_risk_analysis_tab2" name="#management_risk_analysis_tab2">${lang.getStringSeverity()}</a></li>
  </ul>

  <div id="id_management_risk_analysis_content" style="border: 1px solid gray;">
      <!-- <h1 align="center" class="report_title">PILOT REPORT</h1> -->
      <div id="management_risk_analysis_tab1" ></div>
      <div id="management_risk_analysis_tab2"></div>
  </div>
 
</body>
</html>
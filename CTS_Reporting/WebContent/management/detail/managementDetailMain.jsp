<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>jQuery UI Tabs - Tabs at bottom</title>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <script>
  $(function() {
	  $( "#tabs_management_detail_main" ).tabs({
	        beforeLoad: function( event, ui ) {
	          ui.jqXHR.error(function() {
	            ui.panel.html(
	              "Couldn't load this tab. We'll try to fix this as soon as possible. " +
	              "If this wouldn't be a demo." );
	          });
	        }
	      });
 
    // fix the classes
    $( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
      .removeClass( "ui-corner-all ui-corner-top" )
      .addClass( "ui-corner-bottom" );
 
    // move the nav to the bottom
    $( ".tabs-bottom .ui-tabs-nav" ).appendTo( ".tabs-bottom" );
    
   
  });
  </script>
  <style>
  /* force a height so the tabs don't jump as content height changes */
  #tabs_management_detail_main .ui-tabs-panel {
    height: 720px;
    overflow: auto;
  }
  #tabs_management_detail_main .tabs-spacer { float: left; height: 500px; }
  .tabs-bottom .ui-tabs-nav { clear: left; padding: 0 .2em .2em .2em; }
  .tabs-bottom .ui-tabs-nav li { top: auto; bottom: 0; margin: 0 .2em 1px 0; border-bottom: auto; border-top: 0; }
  .tabs-bottom .ui-tabs-nav li.ui-tabs-active { margin-top: -1px; padding-top: 1px; }
  </style>
</head>
<body>
 
<div id="tabs_management_detail_main" class="tabs-bottom">
  <ul>
    <li><a href="${pageContext.request.contextPath}/managementDetailReview.do">Review</a></li>
    <li><a href="${pageContext.request.contextPath}/managementDetailHazardIdentification.do">Hazard ID</a></li>
    <li><a href="${pageContext.request.contextPath}/managementDetailRiskAnalysis.do">Risk Analysis</a></li>
    <li><a href="${pageContext.request.contextPath}/managementDetailRiskAssessment.do">Risk Assessment</a></li>
    <li><a href="${pageContext.request.contextPath}/managementDetailMitigation.do">Mitigation</a></li>
    <li><a href="${pageContext.request.contextPath}/managementDetailRegistered.do">Registered</a></li>
  </ul> 
</div>
</body>
</html>
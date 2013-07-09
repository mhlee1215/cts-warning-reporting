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
		//  disabled: [0, 1, 2, 3, 4, 5],
	        beforeLoad: function( event, ui ) {
	          ui.jqXHR.error(function() {
	            ui.panel.html(
	              "Couldn't load this tab. We'll try to fix this as soon as possible. " +
	              "If this wouldn't be a demo." );
	          });
	        },
	        ajaxOptions: {
	            error: function(xhr, status, index, anchor) {
	                $(anchor.hash).html();
	            },
	            beforeSend: function() {
	                $('#loader').show();
	            },
	            complete: function() {
	                $("#loader").hide();
	            }
	        },
	        activate: function(event ,ui){
                //console.log(event);
                //alert(ui.newTab.index());
            }
	      });
	 $( "#tabs_management_detail_main" ).tabs("option", "active", ${activateTab});
	 var disabledList = new Array(4);
	 var length = 0;
	 for(var i = 0 ; i < 6 ; i++){
		 if( i != ${activateTab}){
			 disabledList[length] = i;
			 length++;	 
		 }
		 
	 }
	 //alert(disabledList);
	 $( "#tabs_management_detail_main" ).tabs({ disabled: disabledList });
    // fix the classes
    $( ".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *" )
      .removeClass( "ui-corner-all ui-corner-top" )
      .addClass( "ui-corner-bottom" );
    
    // move the nav to the bottom
    $( ".tabs-bottom .ui-tabs-nav" ).appendTo( ".tabs-bottom" );
    
    
    $("#tab1").load("${pageContext.request.contextPath}/managementDetailReview.do");
	$("#tab2").load("${pageContext.request.contextPath}/managementDetailHazardIdentification.do");
	$("#tab3").load("${pageContext.request.contextPath}/managementDetailRiskAnalysis.do");
	$("#tab4").load("${pageContext.request.contextPath}/managementDetailRiskAssessment.do");
	$("#tab5").load("${pageContext.request.contextPath}/managementDetailMitigation.do");
	$("#tab6").load("${pageContext.request.contextPath}/managementDetailRegistered.do");
  });
  </script>
  <style>
  /* force a height so the tabs don't jump as content height changes */
  #tabs_management_detail_main .ui-tabs-panel {
    height: 820px;
    overflow: auto;
  }
  #tabs_management_detail_main .tabs-spacer { float: left; height: 100px; }
  .tabs-bottom .ui-tabs-nav { clear: left; padding: 0 .2em .2em .2em; }
  .tabs-bottom .ui-tabs-nav li { top: auto; bottom: 0; margin: 0 .2em 1px 0; border-bottom: auto; border-top: 0; }
  .tabs-bottom .ui-tabs-nav li.ui-tabs-active { margin-top: -1px; padding-top: 1px; }
  
  .ui-menu { width: 150px; border : none;}
  .ui-menu .ui-widget-content { border : none;}
  </style>
</head>
<body>

<div id="loader" style="display:none">Loading...</div>

<div id="tabs_management_detail_main" class="tabs-bottom">
  <ul>
    <li><a href="#tab1" id="id_tab1">${lang.getStringReview()}</a></li>
    <li><a href="#tab2" id="id_tab2">${lang.getStringHazardIdentification()}</a></li>
    <li><a href="#tab3" id="id_tab3">${lang.getStringRiskAnalysis()}</a></li>
    <li><a href="#tab4" id="id_tab4">${lang.getStringRiskAssessment()}</a></li>
    <li><a href="#tab5" id="id_tab5">${lang.getStringMitigation()}</a></li>
    <li><a href="#tab6" id="id_tab6">${lang.getStringRegistered()}</a></li>
  </ul>
  <div id="content">
      <!-- <h1 align="center" class="report_title">PILOT REPORT</h1> -->
      <div id="tab1"></div>
      <div id="tab2"></div>
      <div id="tab3"></div>
      <div id="tab4"></div>
      <div id="tab5"></div>
      <div id="tab6"></div>
  </div> 
</div>
</body>
</html>
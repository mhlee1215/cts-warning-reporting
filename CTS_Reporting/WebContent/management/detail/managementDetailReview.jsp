<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Hazard Management System - Report Review</title>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <script>
  $(function() {
	  $( "#management_review_accordion" ).accordion({
		  	header: "h3",
		    collapsible: true,
		    autoHeight: false,
		    navigation: true,
		    heightStyle: "content"
	  });
	  
	
	
	 $( "#management_review_basic_menu" ).menu();
	 $( "#management_review_taxi_out_menu" ).menu();
	 $( "#management_review_take_off_menu" ).menu();
	 $( "#management_review_climb_menu" ).menu();
	 $( "#management_review_en_route_menu" ).menu();
	 $( "#management_review_decent_menu" ).menu();
	 $( "#management_review_approach_menu" ).menu();
	 $( "#management_review_landing_menu" ).menu();
	 $( "#management_review_taxi_in_menu" ).menu();
	  
	  management_review_load_hazard_item();
	  $("#id_management_detail_main_right_panel").load("${pageContext.request.contextPath}/reportBasic.do?report_no=RP2604137C1234");
	  //alert($('#id_taxi_out_hazardListTable_parentDiv').width());
  });
   
  function management_review_load_hazard_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_taxi_out_hazardListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/attachedFileList.do', 
	  	height: 80,
	  	width:649,
	  	//autowidth:true,
	  	datatype: "xml", 
	     	colNames:['${lang.getStringFileName()}','${lang.getStringSize()}', '${lang.getStringModified()}'],
	     	colModel:[
	     	 			{name:'file_name'		,index:'file_name'		,width:50	,align:"left"	,sortable: false},
	     	    		{name:'file_size'		,index:'file_size'		,width:90	,align:"center"	,sortable: false},
	     	    		{name:'modified_date'	,index:'modified_date'	,width:70	,align:"center"	,sortable: false}
	     	    	],
	     	shrinkToFit:true,
	     	//altRows:true,
	     	hoverrows:false,
	     	rownumbers: true, 
	     	rowNum:10, 
	     	loadtext:'&nbsp;Loading report items..',
	     	//loadtext:'<img src="/images/icons/icon_processing1.gif" width="16" height="16" title="Processing"></img>&nbsp;Loading task data..',
	     	rowList:[10,20,30], 
	     	//pager: '#pager1', 
	     	//pagerpos:'center',
	     	sortname: 'id', 
	     	sortorder: 'desc',
	     	imgpath: gridimgpath,
	     	//multiselect: true,
	     	//viewrecords: true, 
	     	emptyrecords:'no report data',
	     	//caption: "Task List",
	     	toolbar: [false,"top"],
	     	loadError : function(xhr,st,err) { 
	  	   	jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText+". Please reload running status table."); 
	  	},
	  	loadComplete: function(){ 
	  		//$("#id_taxi_out_hazardListTable").jqGrid('setGridWidth', $('#id_taxi_out_hazardListTable_parentDiv').width()-5, true);
	  	},
	  	onSelectRow: function(id){ 
	  		//var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
	  	    //alert(localRowData);
	  		//window.open('${pageContext.request.contextPath}/managementDetailMain.do','targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=900,height=800');
	    }
	  }).navGrid('#pager1',{edit:false,add:false,del:false}); 
  }
  
  function loadBasicInfo(){
	  $("#id_management_detail_main_right_panel").load("${pageContext.request.contextPath}/reportBasic.do?report_no=RP2604137C1234");
	  //alert('hi');
  }
  
  function management_review_loadReport(category, type){
	  $("#id_management_detail_main_right_panel").load("${pageContext.request.contextPath}/managementDetailReviewReport.do?report_no=RP2604137C1234&category="+category+"&type="+type);
  }
  </script>
  <style>
  body {
	font-family: "Trebuchet MS", "Helvetica", "Arial",  "Verdana", "sans-serif";
	font-size: 70%;
}
.ui-menu { width: 150px; }
  </style>
</head>
<body>
 
<div class="ui-widget-header">${lang.getStringReportNo()} : RP2604137C1234</div>
<div class="ui-widget-content" style="padding: 5px;">
<table width="100%">
<tr>
<td id="id_management_detail_main_left_panel" width="180" valign="top">
<div id="management_review_accordion">
  <h3>${lang.getStringBasicInformation()}</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_basic_menu" style="border:none">
		  <li><a href="javascript:loadBasicInfo();"><span class="ui-icon ui-icon-bullet"></span>${lang.getStringShowBasicInfo()}</a></li>
		</ul>
	</div>
  <h3>Taxi-Out (2)</h3>
	<div style="padding:0 0 0 15px;">
		<ul id="management_review_taxi_out_menu" style="border:none">
		  <li><a href="javascript:management_review_loadReport('texi-out', 'pilot');"><span id="id_menu_item_texi-out_pilot" class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (1)</a></li>
		  <li><a href="javascript:management_review_loadReport('texi-out', 'cabin');"><span id="id_menu_item_texi-out_cabin" class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (1)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
  <h3>Take-Off (0)</h3>
  	<div style="padding:0 0 0 15px;">
		<ul id="management_review_take_off_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
  <h3>Climb (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_climb_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>En-Route (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_en_route_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>Decent (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_decent_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>Approach (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_approach_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>Landing (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_landing_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>Taxi-In (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_taxi_in_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
</div>

</td>
<td id="id_management_detail_main_right_panel" >
	
</td>
</tr>
</table>
</div>
 
 
</body>
</html>
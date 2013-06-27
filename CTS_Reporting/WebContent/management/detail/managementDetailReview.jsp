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
	  
	$("#id_management_review_edit_btn")
	  .button({icons: {secondary: "ui-icon-document" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  $("#id_management_review_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_review_delete_btn")
	  .button({icons: {secondary: "ui-icon-trash" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_review_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
	   event.preventDefault();
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
  });
  
  function management_review_load_hazard_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_taxi_out_hazardListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/attachedFileList.do', 
	  	height: 80,
	  	width:600,
	  	datatype: "xml", 
	     	colNames:['File Name','Size', 'Modified'],
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
	  		
	  	},
	  	onSelectRow: function(id){ 
	  		//var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
	  	    //alert(localRowData);
	  		//window.open('${pageContext.request.contextPath}/managementDetailMain.do','targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=900,height=800');
	    }
	  }).navGrid('#pager1',{edit:false,add:false,del:false}); 
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
 
<div class="ui-widget-header">Report No. : RP2604137C1234</div>
<div class="ui-widget-content" style="padding: 5px;">
<table width="100%">
<tr>
<td id="id_management_detail_main_left_panel" width="180" valign="top">
<div id="management_review_accordion">
  <h3>Basic Information</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_basic_menu" style="border:none">
		  <li><a href="#"><span class="ui-icon ui-icon-bullet"></span>Show basic info.</a></li>
		</ul>
	</div>
  <h3>Taxi-Out (2)</h3>
	<div style="padding:0 0 0 15px;">
		<ul id="management_review_taxi_out_menu" style="border:none">
		  <li><a href="#"><span class="ui-icon ui-icon-bullet"></span>Pilot (1)</a></li>
		  <li><a href="#"><span class="ui-icon ui-icon-bullet"></span>Cabin (1)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Ground (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Maintenance (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Dispatcher (0)</a></li>
		</ul>
	</div>
  <h3>Take-Off (0)</h3>
  	<div style="padding:0 0 0 15px;">
		<ul id="management_review_take_off_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Pilot (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Cabin (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Ground (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Maintenance (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Dispatcher (0)</a></li>
		</ul>
	</div>
  <h3>Climb (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_climb_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Pilot (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Cabin (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Ground (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Maintenance (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Dispatcher (0)</a></li>
		</ul>
	</div>
   <h3>En-Route (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_en_route_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Pilot (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Cabin (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Ground (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Maintenance (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Dispatcher (0)</a></li>
		</ul>
	</div>
   <h3>Decent (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_decent_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Pilot (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Cabin (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Ground (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Maintenance (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Dispatcher (0)</a></li>
		</ul>
	</div>
   <h3>Approach (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_approach_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Pilot (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Cabin (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Ground (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Maintenance (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Dispatcher (0)</a></li>
		</ul>
	</div>
   <h3>Landing (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_landing_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Pilot (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Cabin (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Ground (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Maintenance (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Dispatcher (0)</a></li>
		</ul>
	</div>
   <h3>Taxi-In (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_review_taxi_in_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Pilot (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Cabin (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Ground (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Maintenance (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>Dispatcher (0)</a></li>
		</ul>
	</div>
</div>

</td>
<td id="id_management_detail_main_right_panel" >
	<fieldset >
	    <legend>Title : Pax door impated airbridge while opening</legend>
	    <div class="ui-widget-header">Pilot</div>
	    <div class="ui-widget-content" style="padding: 5px;">
	    <table width="100%">
	    <tr>
	    <td>
	    <fieldset>
	    <legend>Narrative</legend>
	    <textarea rows="6" style="width:100%" id="id_management_review_narrative"></textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>Recommendation</legend>
	    <textarea rows="6" style="width:100%" id="id_management_review_recommendation"></textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <table id="id_taxi_out_hazardListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
		<div id="pager1" class="scroll"></div>    
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>Safety Officer Only</legend>
	    <fieldset>
	    <legend>Comments</legend>
	    <textarea rows="6" style="width:100%" id="id_management_review_comments"></textarea>
	    </fieldset>
	    <table width="100%">
	    	<tr>
	    	
	    	
	    	<tr>
											
										
	    	
	    		<td width="30%" align="left"><input type="radio" name="safety_officer_opinion"
												id="id_management_review_accept" value="accept"
												checked="checked" /> Accept</td>
	    		<td width="30%" align="center"><input type="radio" name="safety_officer_opinion"
												id="id_management_review_reject" value="reject"
												checked="checked" /> Reject</td>
	    		<td width="40%" align="right"><input type="radio" name="safety_officer_opinion"
												id="id_management_review_need_an_investigation" value="need_more_investigation"
												checked="checked" /> Need an Investigation</td>
	    	</tr>
	    	<tr>
	    		<td></td>
	    		<td></td>
	    		<td align="right">Priority <select id="id_management_review_priority_selector" name="method" class="form_selector">
				<option value="1">High</option>	
				<option value="2">Medium</option>
				<option value="3">Low</option>
			</select></td>
	    	</tr>
	    </table>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
			<td align="center">
			<a id="id_management_review_edit_btn" href="#">Edit</a> <a id="id_management_review_save_btn" href="#">Save</a> 
			<a id="id_management_review_delete_btn" href="#">Delete</a> <a id="id_management_review_submit_btn" href="#">Submit</a>
			</td>
		</tr>
				
	    </table>
	    
	    
	    
		
	    
	    
	    
	    
	    </div>
	</fieldset>
</td>
</tr>
</table>
</div>
 
 
</body>
</html>
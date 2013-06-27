<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Hazard Management System - Report hazard_identification</title>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <script>
  $(function() {
	 $("#management_hazard_identification_accordion" ).accordion({
		 heightStyle: "content"
	 });
	  
	$("#id_management_hazard_identification_edit_btn")
	  .button({icons: {secondary: "ui-icon-document" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  $("#id_management_hazard_identification_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_hazard_identification_delete_btn")
	  .button({icons: {secondary: "ui-icon-trash" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_hazard_identification_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  
	  $("#id_management_hazard_identification_previous_btn")
	  .button({icons: {primary: "ui-icon-circle-triangle-w" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_hazard_identification_next_btn")
	  .button({icons: {secondary: "ui-icon-circle-triangle-e" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  
	  
	$( "#management_hazard_identification_basic_menu" ).menu();
	$( "#management_hazard_identification_taxi_out_menu" ).menu();
	$( "#management_hazard_identification_take_off_menu" ).menu();
	$( "#management_hazard_identification_climb_menu" ).menu();
	$( "#management_hazard_identification_en_route_menu" ).menu();
	$( "#management_hazard_identification_decent_menu" ).menu();
	$( "#management_hazard_identification_approach_menu" ).menu();
	$( "#management_hazard_identification_landing_menu" ).menu();
	$( "#management_hazard_identification_taxi_in_menu" ).menu();
		 
	  fn_management_hazard_identification_read_hazard_item_list_top();
	   fn_management_hazard_identification_disable_hazard_selector_from_level(1);
	   
	   //fn_management_hazard_identification_load_hazard_item();
	  
	  management_hazard_identification_load_attached_file_item();
  });
  
  function fn_management_hazard_identification_reset_hazard_identification(){
	  fn_management_hazard_identification_read_hazard_item_list_top();
	  fn_management_hazard_identification_disable_hazard_selector_from_level(1);
  }
  
  function fn_management_hazard_identification_disable_hazard_selector_from_level(level){
	  //alert(level);
	  for(var cur_level=level+1 ; cur_level <= 5 ; cur_level++){
		  $('#id_management_hazard_identification_level_'+cur_level+'_selector').attr("disabled", true);
		  var selectItem = document.getElementById('id_management_hazard_identification_level_'+cur_level+'_selector');
		  for(var count = 0 ; count < selectItem.options.length ; count++)
			{
				selectItem.options[count] = null;
				count=count-1;
			}
		  selectItem.options[0] = new Option('[SELECT LEVEL '+cur_level+' HAZARD]', '');
		  
	  }
  }
  function fn_management_hazard_identification_enable_hazard_selector(level){
	  $('#id_management_hazard_identification_level_'+level+'_selector').removeAttr("disabled");
  }	
  
  function fn_management_hazard_identification_enable_description_of_new_hazard(val){
	  if (val == 'y'){
		  $('#id_management_hazard_identification_description_of_new_hazard').removeAttr("disabled");
	  }else if( val == 'n'){
		  $('#id_management_hazard_identification_description_of_new_hazard').attr("disabled", true);
	  }
	  
	 
  }
  
  
  
  function fn_management_hazard_identification_read_hazard_item_list_top(callback){	
		$.ajax({
			type: "POST",
			url: "<c:url value='/getHazardItemListTop.do' />",
			success: function(msg){
				var Result = msg;
				Result = jQuery.trim(Result);
				var platforms = Result.split(",");
				var selectItem = document.getElementById('id_management_hazard_identification_level_1_selector');
				//Remove all Items
				for(var count = 0 ; count < selectItem.options.length ; count++)
				{
					selectItem.options[count] = null;
					count=count-1;
				}
				
				selectItem.options[0] = new Option('[SELECT LEVEL 1 HAZARD]', '');
				
				for(var count = 0 ; count < platforms.length ; count++)
				{
					var item = platforms[count].split("_/");
					selectItem.options[selectItem.options.length] = new Option(item[1], item[0]);
				}
				if(callback != undefined && callback != null)
					callback();	
			}
		});
  }
  
  function fn_management_hazard_identification_read_hazard_item_list_children(id, level, callback){ 
		$.ajax({
			type: "POST",
			url: "<c:url value='/getHazardItemListChildren.do' />",
			data: 'level='+level+'&parent_id='+id,
			success: function(msg){
				var Result = msg;
				Result = jQuery.trim(Result);
				var platforms = Result.split(",");
				var selectItem = document.getElementById('id_management_hazard_identification_level_'+(parseInt(level, 10)+1)+'_selector');
				//Remove all Items
				for(var count = 0 ; count < selectItem.options.length ; count++)
				{
					selectItem.options[count] = null;
					count=count-1;
				}
				
				selectItem.options[0] = new Option('[SELECT LEVEL '+(parseInt(level, 10)+1)+' HAZARD]', '');
				
				for(var count = 0 ; count < platforms.length ; count++)
				{
					var item = platforms[count].split("_/");
					selectItem.options[selectItem.options.length] = new Option(item[1], item[0]);
				}
				if(callback != undefined && callback != null)
					callback();	
				
				fn_management_hazard_identification_disable_hazard_selector_from_level(parseInt(level, 10)+1);
				fn_management_hazard_identification_enable_hazard_selector(parseInt(level, 10)+1);
			}
		});
  }
  
  function management_hazard_identification_load_attached_file_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_hazard_identification_attached_file_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/attachedFileList.do', 
	  	height: 80,
	  	width:650,
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
	     	hoverrows:true,
	     	rownumbers: false, 
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
	     	//toolbar: [false,"top"],
	     	//loadError : function(xhr,st,err) { 
	  	   	//jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText+". Please reload running status table."); 
	  	//},
	  	loadComplete: function(){ 
	  		
	  	},
	  	onSelectRow: function(id){ 
	  		//var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
	  	    //alert(localRowData);
	  		//window.open('${pageContext.request.contextPath}/managementDetailMain.do','targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=900,height=800');
	    }
	  }).navGrid('#management_hazard_identification_pager1',{edit:false,add:false,del:false}); 
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

<table width="100%"> 
<tr>
<td>
<div class="ui-widget-header">${lang.getStringReportNo()} : RP2604137C1234</div>
</td>
<td align="right">Priority : <span style="background-color: red; padding:3px;">High</span></td>
</tr>
</table>
<div class="ui-widget-content" style="padding: 5px;">
<table width="100%">
<tr>
<td id="id_management_detail_main_left_panel" width="30%" valign="top">
<div id="management_hazard_identification_accordion">
  <h3>${lang.getStringBasicInformation()}</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_hazard_identification_basic_menu" style="border:none">
		  <li><a href="#"><span class="ui-icon ui-icon-bullet"></span>${lang.getStringShowBasicInfo()}</a></li>
		</ul>
	</div>
  <h3>Taxi-Out (2)</h3>
	<div style="padding:0 0 0 15px;">
		<ul id="management_hazard_identification_taxi_out_menu" style="border:none">
		  <li><a href="#"><span class="ui-icon ui-icon-bullet"></span>${lang.getStringPilot()} (1)</a></li>
		  <li><a href="#"><span class="ui-icon ui-icon-bullet"></span>${lang.getStringCabin()} (1)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
  <h3>Take-Off (0)</h3>
  	<div style="padding:0 0 0 15px;">
		<ul id="management_hazard_identification_take_off_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
  <h3>Climb (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_hazard_identification_climb_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>En-Route (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_hazard_identification_en_route_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>Decent (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_hazard_identification_decent_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>Approach (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_hazard_identification_approach_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>Landing (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_hazard_identification_landing_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
   <h3>Taxi-In (0)</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="management_hazard_identification_taxi_in_menu" style="border:none">
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringPilot()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringCabin()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringGround()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringMaintenance()} (0)</a></li>
		  <li class="ui-state-disabled"><a href="#"><span class="ui-icon ui-icon-radio-off"></span>${lang.getStringDispatcher()} (0)</a></li>
		</ul>
	</div>
</div>

</td>
<td id="id_management_detail_main_right_panel" width="70%">
	<fieldset >
	    <legend>${lang.getStringTitle()} : Pax door impated airbridge while opening</legend>
	    <div class="ui-widget-header">${lang.getStringPilot()}</div>
	    <div class="ui-widget-content" style="padding: 5px;">
	    <table width="100%">
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringNarrative()}</legend>
	    <textarea rows="3" style="width:100%" id="id_management_hazard_identification_narrative"></textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringRecommendation()}</legend>
	    <textarea rows="3" style="width:100%" id="id_management_hazard_identification_recommendation"></textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <table id="id_management_hazard_identification_attached_file_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
		<div id="management_hazard_identification_pager1" class="scroll"></div>    
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>Comments</legend>
	    <textarea rows="3" style="width:100%" id="id_management_hazard_identification_comments"></textarea>
	    </fieldset>
	    
	    <fieldset>
	    <legend>${lang.getStringHazardIdentification()}</legend>
	    
	    <table cellspacing="0" width="100%" style="padding-right:5px;">
		<tbody >
		<tr>
			<td width="50%"></td>
			<td align="right" width="20%">${lang.getStringHazardNo()}: </td>
			<td width="30%"><input style="width:100%" type="text" id="id_management_hazard_identification_hazard_no_text" class="form_input_text" disabled="disabled" value="H260413KE1234-01"/></td>
		</tr>
		</tbody>
		</table>
	    
	    <fieldset>
	    <legend>${lang.getStringHazard()}</legend>
	    
	    <table cellspacing="0" width="100%">
		<tbody>
		
		<tr>
			<td class="leftmost_label">Level1: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_level_1_selector" onchange="fn_management_hazard_identification_read_hazard_item_list_children(this.value,1);" name="method" class="hazard_item_selector">		
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">Level2: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_level_2_selector" onchange="fn_management_hazard_identification_read_hazard_item_list_children(this.value,2);" name="method" class="hazard_item_selector">
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">Level3: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_level_3_selector" onchange="fn_management_hazard_identification_read_hazard_item_list_children(this.value,3);" name="method" class="hazard_item_selector">
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">Level4: </td>
			<td><select style="width:100%"  id="id_management_hazard_identification_level_4_selector" onchange="fn_management_hazard_identification_read_hazard_item_list_children(this.value,4);" name="method" class="hazard_item_selector">
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">Level5: </td>
			<td><select style="width:100%"  id="id_management_hazard_identification_level_5_selector" name="method" class="hazard_item_selector">
			</select> </td>
		</tr>
		</tbody>
		</table>
		 <table cellspacing="0" width="100%">
		<tbody>
		<tr>
			<td class="leftmost_label">Details: </td>
			<td><input style="width:100%" type="text" id="id_management_hazard_identification_details_text" class="form_input_text"/></td>
			<td align="right"><input type="checkbox" name="name_management_hazard_identification_detail_new" id="id_management_hazard_identification_detail_new" value="1"/> New</td>
		</tr>
		</tbody>
		</table>
	    
	    </fieldset>
	    <fieldset>
	    <legend>Occurrences &amp; Concequences</legend>
	    <table cellspacing="0" width="100%">
		<tbody>
		<tr>
			<td class="leftmost_label">Occurrence: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_occurrence_selector" name="method" class="hazard_item_selector">
				<option value="0">ex) ABNORMAL RUNWAY CONTACT</option>	
				<option value="1">PASSENGER/CARGO</option>
				<option value="2">PASSENGER</option>
				<option value="3">CARGO</option>
				<option value="4">MAIL</option>		
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">${lang.getStringInjury()}: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_injury_selector"  name="method" class="hazard_item_selector">
			<option value="0">ex) Fatal</option>	
				<option value="1">PASSENGER/CARGO</option>
				<option value="2">PASSENGER</option>
				<option value="3">CARGO</option>
				<option value="4">MAIL</option>
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">${lang.getStringDamage()}: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_damage_selector"  name="method" class="hazard_item_selector">
				<option value="0">ex) Substantial</option>	
				<option value="1">NONE</option>
				<option value="2">MINOR</option>
				<option value="3">SUBSTANTIAL</option>
				<option value="4">DESTROYED</option>
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">${lang.getStringDelay()}: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_delay_selector"  name="method" class="hazard_item_selector">
			<option value="0">ex) Flight Cancel</option>	
				<option value="1">No Delay</option>
				<option value="2">Within 30 min</option>
				<option value="3">30 min ~ 1 hour</option>
				<option value="4">Flight Cancel</option>
				<option value="5">A/C Change</option>
				<option value="6">None</option>
			</select> </td>
		</tr>
		</tbody>
		</table>
	    </fieldset>
	    
	    <table width="100%">
		<tbody>
		<tr>
			<td align="left"><a id="id_management_hazard_identification_previous_btn" href="#">${lang.getStringPrevious()}</a></td>
			<td align="right"><a id="id_management_hazard_identification_next_btn" href="#">${lang.getStringNext()}</a></td>
		</tr>
		</tbody>
		</table>
	    
	    </fieldset>
	    
	    
	    </td>
	    </tr>
	    
	    <tr>
			<td align="center">
			<a id="id_management_hazard_identification_edit_btn" href="#">${lang.getStringEdit()}</a> <a id="id_management_hazard_identification_save_btn" href="#">${lang.getStringSave()}</a> 
			<a id="id_management_hazard_identification_delete_btn" href="#">${lang.getStringDelete()}</a> <a id="id_management_hazard_identification_submit_btn" href="#">${lang.getStringSubmit()}</a>
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
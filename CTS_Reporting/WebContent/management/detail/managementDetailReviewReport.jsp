<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Hazard Management System - Report Review</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/themes/base/jquery-ui.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/jquery-1.9.1.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/ui/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <script>
  $(function() {
	  
	  $("#id_management_review_${category}_${type}_edit_btn")
	  .button({icons: {secondary: "ui-icon-document" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  $("#id_management_review_${category}_${type}_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_review_${category}_${type}_delete_btn")
	  .button({icons: {secondary: "ui-icon-trash" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_review_${category}_${type}_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
		  //alert('id_menu_item_${category}_${type}');
		  $("#id_menu_item_${category}_${type}").attr("class", "ui-icon ui-icon-bullet");
		  //${category}_${type}_
	   event.preventDefault();
	   
	  
	});
	  
	  management_review_load_hazard_item();
  });
  
  function management_review_load_hazard_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_taxi_out_${category}_${type}_hazardListTable").jqGrid({
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
<td id="id_management_detail_main_right_panel" >
	<fieldset >
	    <legend>${lang.getStringTitle()} : Pax door impated airbridge while opening</legend>
	    <div class="ui-widget-header">${type == "pilot" ? lang.getStringPilot() : lang.getStringCabin()}</div>
	    <div class="ui-widget-content" style="padding: 5px;">
	    <table width="100%">
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringNarrative()}</legend>
	    <textarea readonly="readonly" rows="6" style="width:100%" id="id_management_review_${category}_${type}_narrative"></textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringRecommendation()}</legend>
	    <textarea readonly="readonly" rows="6" style="width:100%" id="id_management_review_${category}_${type}_recommendation"></textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <div id="id_taxi_out_hazardListTable_parentDiv">
	    <table id="id_taxi_out_${category}_${type}_hazardListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
	    </div>
		<div id="pager1" class="scroll"></div>    
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringSafetyOfficer()} Only</legend>
	    <fieldset>
	    <legend>Comments</legend>
	    <textarea rows="6" style="width:100%" id="id_management_review_${category}_${type}_comments"></textarea>
	    </fieldset>
	    <table width="100%">
	    	<tr>
	    	
	    	
	    	<tr>
											
										
	    	
	    		<td width="30%" align="left"><input type="radio" name="safety_officer_opinion"
												id="id_management_review_accept" value="accept"
												checked="checked" /> ${lang.getStringAccept()}</td>
	    		<td width="30%" align="center"><input type="radio" name="safety_officer_opinion"
												id="id_management_review_reject" value="reject"
												checked="checked" /> ${lang.getStringReject()}</td>
	    		<td width="40%" align="right"><input type="radio" name="safety_officer_opinion"
												id="id_management_review_need_an_investigation" value="need_more_investigation"
												checked="checked" /> ${lang.getStringNeedAnInvestigation()}</td>
	    	</tr>
	    	<tr>
	    		<td></td>
	    		<td></td>
	    		<td align="right">${lang.getStringPriority()} <select id="id_management_review_${category}_${type}_priority_selector" name="method" class="form_selector">
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
			<a id="id_management_review_${category}_${type}_edit_btn" href="#">${lang.getStringEdit()}</a> <a id="id_management_review_${category}_${type}_save_btn" href="#">${lang.getStringSave()}</a> 
			<a id="id_management_review_${category}_${type}_delete_btn" href="#">${lang.getStringDelete()}</a> <a id="id_management_review_${category}_${type}_submit_btn" href="#">${lang.getStringSubmit()}</a>
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
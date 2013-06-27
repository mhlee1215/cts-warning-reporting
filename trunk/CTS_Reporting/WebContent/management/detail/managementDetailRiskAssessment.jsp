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
  <style>
  .ui-button-text-icon-secondary .ui-button-text, .ui-button-text-icons .ui-button-text {
	padding: .1em 2.1em .1em 1em !important;
  }
  </style>
  <script>
  $(function() {
	  $("#id_management_risk_assessment_likelihood_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  $("#id_management_risk_assessment_existing_controls_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  $("#id_management_risk_assessment_edit_btn")
	  .button({icons: {secondary: "ui-icon-document" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  $("#id_management_risk_assessment_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_risk_assessment_delete_btn")
	  .button({icons: {secondary: "ui-icon-trash" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_risk_assessment_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  
	  $("#id_management_risk_assessment_view_report_btn")
	  .button({icons: {secondary: "ui-icon-forder-open" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  $("#id_management_risk_assessment_view_standard_btn")
	  .button({icons: {secondary: "ui-icon-forder-open" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	 
	  management_risk_assessment_new_controls_item();
	  management_risk_assessment_existing_controls_item();
  });
  function printObject(o) {
	  var out = '';
	  for (var p in o) {
	    out += p + ': ' + o[p] + '\n';
	  }
	  alert(out);
	}
  
  function fn_climb_fnFormatter( cellvalue, options, rowObject )
  {
	  //alert(rowObject[0]);
	  //printObject(rowObject.attributes);
	  var return_str = '<select style="width:100%" id="id_management_risk_assessment_initial_likelihood_selector" name="method" class="hazard_item_selector">'+
		'<option value="1">Frequent</option>'+
		'<option value="2">Occasional</option>'+
		'<option value="3">Remote</option>'+
		'<option value="4">Improbable</option>'+		
		'<option value="5">Extremely</option>'+
		'</select>';
	  	return return_str;
  }
  
  function fn_severity_fnFormatter( cellvalue, options, rowObject )
  {
	  //alert(rowObject[0]);
	  //printObject(rowObject.attributes);
	  var return_str = '<select style="width:100%" id="id_management_risk_analysis_likelihood_initial_likelihood_selector" name="method" class="hazard_item_selector">'+
		'<option value="1">Catastrophic</option>'+
		'<option value="2">Hazardous</option>'+
		'<option value="3">Major</option>'+
		'<option value="4">Minor</option>'+		
		'<option value="5">Negligible</option>'+
		'</select>';
	  	return return_str;
  }
  
  
  function management_risk_assessment_new_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_risk_assessment_new_controls_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisSeverityNewControlsList.do', 
	  	height: 100, 
	  	width:800,
	  	datatype: "xml", 
	  		colNames:['${lang.getStringControlNo()}','${lang.getStringTitle()}', '${lang.getStringState()}', '${lang.getStringStartDate()}','${lang.getStringEndDate()}'],
	     	colModel:[
	     	 			{name:'rp_no'		,index:'rp_no'		,width:80	,align:"left"	,sortable: true},
	     	    		{name:'date'		,index:'date'		,width:80	,align:"center"	,sortable: true},
	     	    		{name:'flight_no'	,index:'flight_no'	,width:60	,align:"center"	,sortable: true},
	     	    		{name:'ac_type'		,index:'ac_type'	,width:80	,align:"center"	,sortable: true},
	     	    		{name:'state'		,index:'state'		,width:75	,align:"center" ,sortable: true}		
	     	    	],
	     	shrinkToFit:true,
	     	//altRows:true,
	     	hoverrows:false,
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
	     	toolbar: [false,"top"],
	     	loadError : function(xhr,st,err) { 
	  	   	jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText+". Please reload running status table."); 
	  	},
	  	loadComplete: function(){ 
	  		
	  	},
	  	onSelectRow: function(id){ 
	  		//var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
	  	    //alert(localRowData);
	    }
	  }).navGrid('#management_risk_assessment_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  
  function management_risk_assessment_existing_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_risk_assessment_existing_controls_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisSeverityExistingControlsList.do', 
	  	height: 100, 
	  	width:800,
	  	datatype: "xml", 
		  	colNames:['${lang.getStringControlNo()}','${lang.getStringTitle()}', '${lang.getStringState()}', '${lang.getStringStartDate()}','${lang.getStringEndDate()}'],
	     	colModel:[
	     	 			{name:'rp_no'		,index:'rp_no'		,width:80	,align:"left"	,sortable: true},
	     	    		{name:'date'		,index:'date'		,width:80	,align:"center"	,sortable: true},
	     	    		{name:'flight_no'	,index:'flight_no'	,width:60	,align:"center"	,sortable: true},
	     	    		{name:'ac_type'		,index:'ac_type'	,width:80	,align:"center"	,sortable: true},
	     	    		{name:'state'		,index:'state'		,width:75	,align:"center" ,sortable: true}		
	     	    	],
	     	shrinkToFit:true,
	     	//altRows:true,
	     	hoverrows:false,
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
	     	toolbar: [false,"top"],
	     	loadError : function(xhr,st,err) { 
	  	   	jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText+". Please reload running status table."); 
	  	},
	  	loadComplete: function(){ 
	  		
	  	},
	  	onSelectRow: function(id){ 
	  		//var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
	  	    //alert(localRowData);
	    }
	  }).navGrid('#management_risk_assessment_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  </script>
  
</head>
<body>
 	<table width="100%" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				
				<td align="right" ><a id="id_management_risk_assessment_view_report_btn" href="#">${lang.getStringViewReport()}</a>
				
				<a id="id_management_risk_assessment_view_standard_btn" href="#">View Standard</a>
				</td>
			
			</tr>
		</tbody>
	</table>
 	
	<fieldset>
	<legend>${lang.getStringRiskAssessment()}</legend>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td width="49%">
					<fieldset>
					<legend>Initial ${lang.getStringRisk()}</legend>
						<table width="100%" cellpadding="2" cellspacing="10">
							<tbody>
								<tr>
									<td align="left">${lang.getStringLikelihood()}</td>
									<td><b>${lang.getStringFrequency()}</b></td>
									<td width="10%"></td>
								</tr>
								<tr>
									<td align="left">${lang.getStringSeverity()}</td>
									<td><b>Major</b></td>
								</tr>
								<tr>
									<td align="left">Tolerability</td>
									<td bgcolor="red"><font color="white"><b>High Risk : Unacceptable</b></font></td>
								</tr>
							</tbody>
						</table>
					</fieldset>
				</td>
				<td width="2%"></td>
				<td width="49%">
					<fieldset>
					<legend>Residual ${lang.getStringRisk()}</legend>
						<table width="100%" cellpadding="2" cellspacing="10">
							<tbody>
								<tr>
									<td align="left">${lang.getStringLikelihood()}</td>
									<td><b>Remote</b></td>
									<td width="10%"></td>
								</tr>
								<tr>
									<td align="left">${lang.getStringSeverity()}</td>
									<td><b>Minor</b></td>
								</tr>
								<tr>
									<td align="left">Tolerability</td>
									<td bgcolor="green"><font color="white"><b>Medium Risk : Unacceptable</b></font></td>
								</tr>
							</tbody>
						</table>
					</fieldset>
				</td>
			</tr>
		</tbody>
	</table>
    <div style="height: 8px;"></div>
    <div class="ui-widget-header">${lang.getStringExistingControls()}</div>
    <div class="ui-widget-content" style="padding: 5px;">
    <table width="100%">
    <tbody>
    	<tr>
    		<td><select style="width:100%" id="id_management_risk_assessment_existing_controls_year_selector" name="method" class="">
				<option value="1">${lang.getStringPast()} 1 ${lang.getStringYear()}</option>
				<option value="2">${lang.getStringPast()} 2 ${lang.getStringYears()}</option>
				<option value="3" selected="selected">${lang.getStringPast()} 3 ${lang.getStringYears()}</option>
				<option value="4">${lang.getStringPast()} 4 ${lang.getStringYears()}</option>
				<option value="5">${lang.getStringPast()} 5 ${lang.getStringYears()}</option>
				<option value="6">${lang.getStringPast()} 6 ${lang.getStringYears()}</option>
				<option value="7">${lang.getStringPast()} 7 ${lang.getStringYears()}</option>
				<option value="8">${lang.getStringPast()} 8 ${lang.getStringYears()}</option>
				<option value="9">${lang.getStringPast()} 9 ${lang.getStringYears()}</option>
				<option value="10">${lang.getStringPast()} 10 ${lang.getStringYears()}</option>
				<option value="999">${lang.getStringAll()}</option>
			</select></td>
    		<td align="left"><a id="id_management_risk_assessment_existing_controls_search_btn" href="#">${lang.getStringSearch()}</a> </td>
    		<td align="right" width="70%"></td>
    	</tr>
    </tbody>
    </table>
    <table>
    	<tbody>
    		<tr>
    			<td>
    			<table id="id_management_risk_assessment_existing_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_risk_assessment_existing_controls_pager1" class="scroll"></div>  
    			</td>
    		</tr>
    	</tbody>
    </table>
    </div>	
    <div style="height: 4px;"></div>
    <div class="ui-widget-header">${lang.getStringNewControls()}</div>
    <div class="ui-widget-content" style="padding: 5px;">
    <table>
    	<tbody>
    		<tr>
    			<td>
    			<table id="id_management_risk_assessment_new_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_risk_assessment_new_controls_pager1" class="scroll"></div>  
    			</td>
    		</tr>
    	</tbody>
    </table>
    </div>	
	</fieldset>
	
	<fieldset>
	<legend>Comments</legend>
	<textarea rows="15" style="width:100%" id="id_management_risk_assessment_comments"></textarea>
	</fieldset>
	
	<table width="100%">
	
	<tr>
		<td align="center">
		<a id="id_management_risk_assessment_edit_btn" href="#">${lang.getStringEdit()}</a> <a id="id_management_risk_assessment_save_btn" href="#">${lang.getStringSave()}</a> 
		<a id="id_management_risk_assessment_delete_btn" href="#">${lang.getStringDelete()}</a> <a id="id_management_risk_assessment_submit_btn" href="#">${lang.getStringSubmit()}</a>
		</td>
	</tr>
			
    </table>
	
 
 
</body>
</html>
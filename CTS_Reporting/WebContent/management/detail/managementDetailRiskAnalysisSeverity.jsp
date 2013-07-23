<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>jQuery UI Tabs - Tabs at bottom</title>

  <style>
  .ui-button-text-icon-secondary .ui-button-text, .ui-button-text-icons .ui-button-text {
	padding: .1em 2.1em .1em 1em !important;
  }
  </style>
  <script>
  $(function() {
	  $("#id_management_risk_analysis_severity_likelihood_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  }).hide();
	  
	  $("#id_management_risk_analysis_severity_existing_controls_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  }).hide();
	  
	  $("#id_management_risk_analysis_severity_edit_btn")
	  .button({icons: {secondary: "ui-icon-document" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  $("#id_management_risk_analysis_severity_edit_btn").hide();
	  $("#id_management_risk_analysis_severity_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	   updateAnalysisSeverity('n');
	});
	  $("#id_management_risk_analysis_severity_delete_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_risk_analysis_severity_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
	   event.preventDefault();
	   updateAnalysisSeverity('y');
	});
	  
	  $("#id_management_risk_analysis_severity_submitted_btn")
	  .button({icons: {secondary: "ui-icon-locked" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  $("#id_management_risk_analysis_severity_submitted_btn").hide();
	  
	  management_risk_analysis_severity_likelihood_item();
	  management_risk_analysis_severity_new_controls_item();
	  management_risk_analysis_severity_existing_controls_item();
	  
	  
	  
	  <c:if test="${hazard.state_severity == 'SUBMITTED'}" >
	  $("#id_management_risk_analysis_severity_submitted_btn").show();
	   $("#id_management_risk_analysis_severity_submit_btn").hide();   
	   
	   $('input.severity').attr('disabled', 'disabled');
		$('select.severity').attr('disabled', 'disabled');
		$('textarea.severity').attr('disabled', 'disabled');
	</c:if>
  });
  
  function fn_change_severity(value){
	  //alert('hi');
	  //management_risk_assessment_existing_controls_item();
	  jQuery("#id_management_risk_analysis_severity_likelihood_ListTable").jqGrid()
	  .setGridParam({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisSeveritySeverityList.do?year='+value
	  }).trigger("reloadGrid");
	  
	  
  }
  
  function fn_change_severity_existing_controls(value){
	  //management_risk_assessment_existing_controls_item();
	  jQuery("#id_management_risk_analysis_severity_existing_controls_ListTable").jqGrid()
	  .setGridParam({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisSeverityExistingControlsList.do?year='+value
	  }).trigger("reloadGrid");
  }
  
  
  function updateAnalysisSeverity (issubmit){
		 var str = $("#management_risk_analysis_severity_form").serialize();
		   //alert(str);
		   
		   $.ajax({
			    type:"post",
			    data:str+'&hazard_no=${hazard_no}&type=severity&issubmit='+issubmit,
			    url:"managementDetailRiskAnalysisLikelihoodUpdate.do",
			    async: false,
			    success: function(msg){
			       alert(msg);
			       if(issubmit == 'y'){
			    	   $("#id_management_risk_analysis_severity_submitted_btn").show();
				 	   $("#id_management_risk_analysis_severity_submit_btn").hide();   
				 	   
				 	  $('input.severity').attr('disabled', 'disabled');
						$('select.severity').attr('disabled', 'disabled');
						$('textarea.severity').attr('disabled', 'disabled');
			       }
			       
			    }
			});
	}
  
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
	  var return_str = '<select style="width:100%" id="id_management_risk_analysis_severity_initial_likelihood_selector" name="method" class="hazard_item_selector">'+
		'<option value="1">Frequent</option>'+
		'<option value="2">Occasional</option>'+
		'<option value="3">Remote</option>'+
		'<option value="4">Improbable</option>'+		
		'<option value="5">Extremely</option>'+
		'</select>';
	  	return return_str;
  }
  
  var severityNameMap = new Array();
  var severityNameMapR = new Array();
  <c:forEach items="${severityList}" var="selectItem" varStatus="status">
  severityNameMap['${selectItem.value}'] = '${selectItem.name}';
  severityNameMapR['${selectItem.name}'] = '${selectItem.value}';
  </c:forEach>
  
  function fn_severity_Formatter( cellvalue, options, rowObject )
  {
	  	var return_str = severityNameMap[cellvalue];
	  	return return_str;
  }
  
  function change_worst_severity(value){
	  var length = $("#id_management_risk_analysis_likelihood_likelihood_frequency_text").attr("value");
	  var max_val = eval(-1);
	  for(var i = 0 ; i < length ; i++){
		  if( max_val < eval($("#id_management_risk_analysis_severity_initial_likelihood_selector"+i+"").val()))
			  max_val = eval($("#id_management_risk_analysis_severity_initial_likelihood_selector"+i+"").val());
		}
	  $("#id_management_risk_analysis_severity_likelihood_worst_residual_text").attr("value", severityNameMap[max_val]);
  }
  
  function fn_severity_selector_Formatter( cellvalue, options, rowObject )
  {
	  //alert(rowObject[0]);
	  //printObject(rowObject.attributes);
	   var return_str = '<select style="width:100%" id="id_management_risk_analysis_severity_initial_likelihood_selector'+options.rowId+'" onchange="change_worst_severity(this.value);" name="method" class="hazard_item_selector">'+
	   <c:forEach items="${severityList}" var="selectItem" varStatus="list_status">
	  '<option value="${selectItem.value }"';
		if(cellvalue == '${selectItem.value}')
			return_str = return_str+ ' selected="selected"';
		return_str = return_str + '>${selectItem.name}</option>'+
		</c:forEach>
		'</select>';
	  	return return_str;
  }
  
  function management_risk_analysis_severity_likelihood_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_risk_analysis_severity_likelihood_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisSeveritySeverityList.do', 
	  	height: 100, 
	  	width:800,
	  	datatype: "xml", 
	     	colNames:['id', 'Ocurrence No.','${lang.getStringDate()} (UTC)', 'Occurrence', '${lang.getStringSeverity()}','Residual ${lang.getStringSeverity()}'],
	     	colModel:[
						{name:'id'		,index:'id'		,width:80	,align:"left"	,hidden: true},
	     	 			{name:'rp_no'		,index:'rp_no'		,width:80	,align:"left"	,sortable: true},
	     	    		{name:'date'		,index:'date'		,width:80	,align:"center"	,sortable: true},
	     	    		{name:'flight_no'	,index:'flight_no'	,width:60	,align:"left"	,sortable: true},
	     	    		{name:'ac_type'		,index:'ac_type'	,width:50	,align:"left"	,sortable: true, formatter:fn_severity_Formatter},
	     	    		{name:'state'		,index:'state'		,width:75	,align:"center" , formatter:fn_severity_selector_Formatter}		
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
	  		var fullData = jQuery("#id_management_risk_analysis_severity_likelihood_ListTable").jqGrid('getRowData');
	  		$("#id_management_risk_analysis_severity_likelihood_frequency_text").attr("value", fullData.length);
	  		
	  		var max_severity = -1;
	  		
	  		for(var i = 0 ; i < fullData.length ; i++)
	  			if(max_severity < severityNameMapR[fullData[i]['ac_type']])
	  				max_severity = severityNameMapR[fullData[i]['ac_type']];

	  		$("#id_management_risk_analysis_severity_likelihood_worst_residual_text").attr("value", severityNameMap[max_severity]);
	  		
	  	},
	  	onSelectRow: function(id){ 
	  		//var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
	  	    //alert(localRowData);
	    }
	  }).navGrid('#management_risk_analysis_severity_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  
  function management_risk_analysis_severity_new_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_risk_analysis_severity_new_controls_ListTable").jqGrid({
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
	  }).navGrid('#management_risk_analysis_severity_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  
  function management_risk_analysis_severity_existing_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_risk_analysis_severity_existing_controls_ListTable").jqGrid({
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
	  }).navGrid('#management_risk_analysis_severity_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  </script>
  
</head>
<body>
 <form id="management_risk_analysis_severity_form">
 
	<fieldset>
	<legend>${lang.getStringRiskAnalysis()}</legend>
	<div class="ui-widget-header">${lang.getStringSeverity()}</div>
    <div class="ui-widget-content" style="padding: 5px;">
    
    <table width="100%">
    <tbody>
    	<tr>
    		<td><select style="width:100%" id="id_management_risk_analysis_severity_likelihood_year_selector" onchange="fn_change_severity(this.value);" name="method" class="">
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
    		<td align="left"><a id="id_management_risk_analysis_severity_likelihood_search_btn" href="#">${lang.getStringSearch()}</a> </td>
    		<td align="right">${lang.getStringFrequency()}: <input style="width:30px;" type="text" id="id_management_risk_analysis_severity_likelihood_frequency_text" class="form_input_text" disabled="disabled" value=""/></td>
    		<td align="right">Worst Residual Severity: <input style="width:100px;" type="text" id="id_management_risk_analysis_severity_likelihood_worst_residual_text" class="form_input_text" disabled="disabled" value=""/></td>
    	</tr>
    </tbody>
    </table>
    <table>
    	<tbody>
    		<tr>
    			<td>
    			<table id="id_management_risk_analysis_severity_likelihood_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_risk_analysis_severity_likelihood_pager1" class="scroll"></div>  
    			</td>
    		</tr>
    	</tbody>
    </table>
    </div>	
    <div style="height: 4px;"></div>
    
    <div class="ui-widget-header">${lang.getStringExistingControls()}</div>
    <div class="ui-widget-content" style="padding: 5px;">
    <table width="100%">
    <tbody>
    	<tr>
    		<td><select style="width:100%" id="id_management_risk_analysis_severity_existing_controls_year_selector" onchange="fn_change_severity_existing_controls(this.value);" name="method" class="">
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
    		<td align="left"><a id="id_management_risk_analysis_severity_existing_controls_search_btn" href="#">${lang.getStringSearch()}</a> </td>
    		<td align="right" width="70%"></td>
    	</tr>
    </tbody>
    </table>
    <table>
    	<tbody>
    		<tr>
    			<td>
    			<table id="id_management_risk_analysis_severity_existing_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_risk_analysis_severity_existing_controls_pager1" class="scroll"></div>  
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
    			<table id="id_management_risk_analysis_severity_new_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_risk_analysis_severity_new_controls_pager1" class="scroll"></div>  
    			</td>
    		</tr>
    	</tbody>
    </table>
    </div>	
	</fieldset>
	
	<fieldset>
	<legend>Determine Severity</legend>
	<table width="100%">
	<tbody>
		<tr>
			<td>Initial ${lang.getStringSeverity()}: <select class="severity" style="width:50%" id="id_management_risk_analysis_severity_initial_likelihood_selector" name="risk_analysis_severity_initial_likelihood" class="hazard_item_selector">
				<option value="0">Select</option>
			    <c:forEach items="${severityList}" var="selectItem" varStatus="list_status">
	 			<option value="${selectItem.value }" ${hazard.severity_initial_likelihood == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option>
	 			</c:forEach>
			</select></td>
			<td></td>
			<td>Residual ${lang.getStringSeverity()}: <select class="severity" style="width:50%" id="id_management_risk_analysis_severity_residual_likelihood_selector" name="risk_analysis_severity_residual_likelihood" class="hazard_item_selector">
				<option value="0">Select</option>
			    <c:forEach items="${severityList}" var="selectItem" varStatus="list_status">
	 			<option value="${selectItem.value }" ${hazard.severity_residual_likelihood == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option>
	 			</c:forEach>
			</select> </td>
		</tr>
	</tbody>
	</table>
	</fieldset>
	
	<fieldset>
	<legend>Comments</legend>
	<textarea class="severity" rows="3" style="width:100%" name="risk_analysis_severity_comments" id="id_management_risk_analysis_severity_comments">${hazard.severity_comments}</textarea>
	</fieldset>
	
	<table width="100%">
	
	<tr>
		<td align="center">
		<a id="id_management_risk_analysis_severity_edit_btn" href="#">${lang.getStringEdit()}</a> <a id="id_management_risk_analysis_severity_save_btn" href="#">${lang.getStringSave()}</a> 
		<a id="id_management_risk_analysis_severity_delete_btn" href="#">${lang.getStringDelete()}</a> <a id="id_management_risk_analysis_severity_submit_btn" href="#">${lang.getStringSubmit()}</a> <a id="id_management_risk_analysis_severity_submitted_btn" href="#">${lang.getStringSubmitted()}</a>
		</td>
	</tr>
			
    </table>
	
 
 </form>
</body>
</html>
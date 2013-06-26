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
	  $("#id_management_risk_analysis_likelihood_likelihood_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  $("#id_management_risk_analysis_likelihood_existing_controls_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  management_risk_analysis_likelihood_likelihood_item();
	  management_risk_analysis_likelihood_new_controls_item();
	  management_risk_analysis_likelihood_existing_controls_item();
  });
  
  function management_risk_analysis_likelihood_likelihood_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_riskanalysis_likelihood_likelihood_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisLikelihoodLikelihoodList.do', 
	  	height: 100, 
	  	width:800,
	  	datatype: "xml", 
	     	colNames:['${lang.getStringReportNo()}','${lang.getStringFlightDate()} (UTC)', '${lang.getStringFlightNo()}', '${lang.getStringACType()}','${lang.getStringState()}'],
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
	  }).navGrid('#management_riskanalysis_likelihood_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  
  function management_risk_analysis_likelihood_new_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_riskanalysis_likelihood_new_controls_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisLikelihoodNewControlsList.do', 
	  	height: 100, 
	  	width:800,
	  	datatype: "xml", 
	     	colNames:['${lang.getStringReportNo()}','${lang.getStringFlightDate()} (UTC)', '${lang.getStringFlightNo()}', '${lang.getStringACType()}','${lang.getStringState()}'],
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
	  }).navGrid('#management_riskanalysis_likelihood_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  
  function management_risk_analysis_likelihood_existing_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_riskanalysis_likelihood_existing_controls_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisLikelihoodExistingControlsList.do', 
	  	height: 100, 
	  	width:800,
	  	datatype: "xml", 
	     	colNames:['${lang.getStringReportNo()}','${lang.getStringFlightDate()} (UTC)', '${lang.getStringFlightNo()}', '${lang.getStringACType()}','${lang.getStringState()}'],
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
	  }).navGrid('#management_riskanalysis_likelihood_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  </script>
  
</head>
<body>
 
 
	<fieldset>
	<legend>Risk Analysis</legend>
	<div class="ui-widget-header">Likelihood</div>
    <div class="ui-widget-content" style="padding: 5px;">
    
    <table width="100%">
    <tbody>
    	<tr>
    		<td><select style="width:100%" id="id_management_risk_analysis_likelihood_likelihood_year_selector" name="method" class="">
				<option value="1">Past 1 Year</option>
				<option value="2">Past 2 Years</option>
				<option value="3">Past 3 Years</option>
				<option value="4">Past 4 Years</option>
				<option value="5">Past 5 Years</option>
				<option value="6">Past 6 Years</option>
				<option value="7">Past 7 Years</option>
				<option value="8">Past 8 Years</option>
				<option value="9">Past 9 Years</option>
				<option value="10">Past 10 Years</option>
				<option value="999">All</option>
			</select></td>
    		<td align="left"><a id="id_management_risk_analysis_likelihood_likelihood_search_btn" href="#">Search</a> </td>
    		<td align="right">Frequency: <input style="width:30px;" type="text" id="id_management_risk_analysis_likelihood_likelihood_frequency_text" class="form_input_text" disabled="disabled" value="4"/></td>
    		<td align="right">Worst Residual Likelihood: <input style="width:100px;" type="text" id="id_management_risk_analysis_likelihood_likelihood_worst_residual_text" class="form_input_text" disabled="disabled" value="Remote"/></td>
    	</tr>
    </tbody>
    </table>
    <table>
    	<tbody>
    		<tr>
    			<td>
    			<table id="id_management_riskanalysis_likelihood_likelihood_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_riskanalysis_likelihood_likelihood_pager1" class="scroll"></div>  
    			</td>
    		</tr>
    	</tbody>
    </table>
    </div>	
    
    <div class="ui-widget-header">Existing Controls</div>
    <div class="ui-widget-content" style="padding: 5px;">
    
    <table width="100%">
    <tbody>
    	<tr>
    		<td><select style="width:100%" id="id_management_risk_analysis_likelihood_existing_controls_year_selector" name="method" class="">
				<option value="1">Past 1 Year</option>
				<option value="2">Past 2 Years</option>
				<option value="3">Past 3 Years</option>
				<option value="4">Past 4 Years</option>
				<option value="5">Past 5 Years</option>
				<option value="6">Past 6 Years</option>
				<option value="7">Past 7 Years</option>
				<option value="8">Past 8 Years</option>
				<option value="9">Past 9 Years</option>
				<option value="10">Past 10 Years</option>
				<option value="999">All</option>
			</select></td>
    		<td align="left"><a id="id_management_risk_analysis_likelihood_existing_controls_search_btn" href="#">Search</a> </td>
    		<td align="right" width="70%"></td>
    	</tr>
    </tbody>
    </table>
    <table>
    	<tbody>
    		<tr>
    			<td>
    			<table id="id_management_riskanalysis_likelihood_existing_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_riskanalysis_likelihood_existing_controls_pager1" class="scroll"></div>  
    			</td>
    		</tr>
    	</tbody>
    </table>
    </div>	
    
    <div class="ui-widget-header">New Controls</div>
    <div class="ui-widget-content" style="padding: 5px;">
    <table>
    	<tbody>
    		<tr>
    			<td>
    			<table id="id_management_riskanalysis_likelihood_new_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_riskanalysis_likelihood_new_controls_pager1" class="scroll"></div>  
    			</td>
    		</tr>
    	</tbody>
    </table>
    </div>	
	</fieldset>
	
	<fieldset>
	<legend>Determine Likelihood</legend>
	</fieldset>
	
	<fieldset>
	<legend>Comments</legend>
	</fieldset>
	
	
 
 
</body>
</html>
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
	  $("#id_management_risk_analysis_likelihood_likelihood_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  }).hide();
	  
	  $("#id_management_risk_analysis_likelihood_existing_controls_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  }).hide();
	  
	  $("#id_management_risk_analysis_likelihood_edit_btn")
	  .button({icons: {secondary: "ui-icon-document" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  $("#id_management_risk_analysis_likelihood_edit_btn").hide();
	  $("#id_management_risk_analysis_likelihood_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   updateAnalysisLikelihood('n');
	});
	  $("#id_management_risk_analysis_likelihood_delete_btn")
	  .button({icons: {secondary: "ui-icon-trash" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_risk_analysis_likelihood_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   updateAnalysisLikelihood('y');
	   
	});
	  
	  $("#id_management_risk_analysis_likelihood_submitted_btn")
	  .button({icons: {secondary: "ui-icon-locked" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  $("#id_management_risk_analysis_likelihood_submitted_btn").hide();
	  
	  management_risk_analysis_likelihood_likelihood_item();
	  management_risk_analysis_likelihood_new_controls_item();
	  management_risk_analysis_likelihood_existing_controls_item();
	  
	  //timeout = setInterval('resize_trigger()', 500);
	  //alert($('#id_management_risk_analysis_likelihood_likelihood_ListTable_parentDiv').width());
	  <c:if test="${hazard.state_likelihood == 'SUBMITTED'}" >
	   $("#id_management_risk_analysis_likelihood_submitted_btn").show();
	   $("#id_management_risk_analysis_likelihood_submit_btn").hide();   
	   
	   $("#id_management_risk_analysis_likelihood_save_btn").hide();
	   $("#id_management_risk_analysis_likelihood_delete_btn").hide();   
	   
	    $('input.likelihood').attr('disabled', 'disabled');
		$('select.likelihood').attr('disabled', 'disabled');
		$('textarea.likelihood').attr('disabled', 'disabled');
	</c:if>

  });
  
  function fn_change_likelihood(value){
	  //alert('hi');
	  //management_risk_assessment_existing_controls_item();
	  jQuery("#id_management_risk_analysis_likelihood_likelihood_ListTable").jqGrid()
	  .setGridParam({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisLikelihoodLikelihoodList.do?year='+value
	  }).trigger("reloadGrid");
  }
  
  function fn_change_likelihood_existing_controls(value){
	  //management_risk_assessment_existing_controls_item();
	  jQuery("#id_management_risk_analysis_likelihood_existing_controls_ListTable").jqGrid()
	  .setGridParam({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisSeverityExistingControlsList.do?year='+value
	  }).trigger("reloadGrid");
  }
  

function updateAnalysisLikelihood (issubmit){
	 var str = $("#management_risk_analysis_likelihood_form").serialize();
	  // alert(str);
	   
	   $.ajax({
		    type:"post",
		    data:str+'&hazard_no=${hazard_no}&type=likelihood&issubmit='+issubmit,
		    url:"managementDetailRiskAnalysisLikelihoodUpdate.do",
		    async: false,
		    success: function(msg){
		       alert(msg);
		       if(issubmit == 'y'){
		    	   $("#id_management_risk_analysis_likelihood_submitted_btn").show();
			 	   $("#id_management_risk_analysis_likelihood_submit_btn").hide();   
			 	   
			 	  $('input.likelihood').attr('disabled', 'disabled');
					$('select.likelihood').attr('disabled', 'disabled');
					$('textarea.likelihood').attr('disabled', 'disabled');
		       }
		       
		    }
		});
}
  
/*
  function resize_trigger(){
	  //alert('hi');
	  
	  var parentDivWidth = $('#id_management_risk_analysis_likelihood_likelihood_ListTable_parentDiv').width();
	  if ( parentDivWidth > 100){
		  clearInterval(timeout);		  
	  }
	  else {
		 // alert('still not loaded..');
	  }
	*/  

  
  //};
  
  
  function printObject(o) {
	  var out = '';
	  for (var p in o) {
	    out += p + ': ' + o[p] + '\n';
	  }
	  alert(out);
	}
  
  var likelihoodNameMap = new Array();
  var likelihoodNameMapR = new Array();
  <c:forEach items="${likelihoodList}" var="selectItem" varStatus="status">
  likelihoodNameMap['${selectItem.value}'] = '${selectItem.name}';
  likelihoodNameMapR['${selectItem.name}'] = '${selectItem.value}';
  </c:forEach>
  
  function fn_likelihood_Formatter( cellvalue, options, rowObject )
  {
	  	var return_str = likelihoodNameMap[cellvalue];
	  	return return_str;
  }
  
  function change_worst_likelihood(value){
	  var length = $("#id_management_risk_analysis_likelihood_likelihood_frequency_text").attr("value");
	  var min_val = eval(999);
	  for(var i = 0 ; i < length ; i++){
		  if( min_val > eval($("#id_management_risk_analysis_likelihood_residual_likelihood_selector_"+i+"").val()))
			  min_val = eval($("#id_management_risk_analysis_likelihood_residual_likelihood_selector_"+i+"").val());
		}
	  $("#id_management_risk_analysis_likelihood_likelihood_worst_residual_text").attr("value", likelihoodNameMap[min_val]);
  }
    
  function fn_likelihood_selector_Formatter( cellvalue, options, rowObject, aaa)
  {
	  	var return_str = '<select style="width:100%" id="id_management_risk_analysis_likelihood_residual_likelihood_selector_'+options.rowId+'" onchange="change_worst_likelihood(this.value);" name="method" class="hazard_item_selector">'+
	    <c:forEach items="${likelihoodList}" var="selectItem" varStatus="list_status">
		'<option value="${selectItem.value }"';
		if(cellvalue == '${selectItem.value}')
			return_str = return_str+ ' selected="selected"';
		return_str = return_str + '>${selectItem.name}</option>'+
		</c:forEach>
		'</select>';
	  	return return_str;
  }
  
  function management_risk_analysis_likelihood_likelihood_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_risk_analysis_likelihood_likelihood_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisLikelihoodLikelihoodList.do', 
	  	height: 100, 
	  	width:800,
	  	datatype: "xml", 
	     	colNames:['id', '${lang.getStringTitle()}','${lang.getStringDate()}', 'Occurrence', '${lang.getStringLikelihood()}','Residual'],
	     	colModel:[
						{name:'id'		,index:'id'				,width:150	,align:"left"	,hidden: true},
	     	 			{name:'rp_no'		,index:'rp_no'		,width:150	,align:"left"	,sortable: true},
	     	    		{name:'date'		,index:'date'		,width:80	,align:"center"	,sortable: true},
	     	    		{name:'flight_no'	,index:'flight_no'	,width:60	,align:"left"	,sortable: true},
	     	    		{name:'ac_type'		,index:'ac_type'	,width:80	,align:"left"	,sortable: true, formatter:fn_likelihood_Formatter},
	     	    		{name:'state'		,index:'state'		,width:75	,align:"center" , formatter:fn_likelihood_selector_Formatter}		
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
	  		var fullData = jQuery("#id_management_risk_analysis_likelihood_likelihood_ListTable").jqGrid('getRowData');
	  		$("#id_management_risk_analysis_likelihood_likelihood_frequency_text").attr("value", fullData.length);
	  		
			var min_likelihood = 999;
	  		
	  		for(var i = 0 ; i < fullData.length ; i++)
	  			if(min_likelihood > likelihoodNameMapR[fullData[i]['ac_type']])
	  				min_likelihood = likelihoodNameMapR[fullData[i]['ac_type']];

	  		$("#id_management_risk_analysis_likelihood_likelihood_worst_residual_text").attr("value", likelihoodNameMap[min_likelihood]);
	  		
	  		//alert(fullData[0].id);
	  		//$("#id_management_risk_analysis_likelihood_likelihood_ListTable").jqGrid('setGridWidth', $('#id_management_risk_analysis_likelihood_likelihood_ListTable_parentDiv').width(), true); 
	  	},
	  	onSelectRow: function(id){ 
	  		
	  		//var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
	  	    //alert(localRowData);
	    }
	  }).navGrid('#management_risk_analysis_likelihood_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  
  function management_risk_analysis_likelihood_new_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_risk_analysis_likelihood_new_controls_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisLikelihoodNewControlsList.do', 
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
	  }).navGrid('#management_risk_analysis_likelihood_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  
  function management_risk_analysis_likelihood_existing_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_risk_analysis_likelihood_existing_controls_ListTable").jqGrid({
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
	  }).navGrid('#management_risk_analysis_likelihood_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  </script>
  
</head>
<body>
<form id="management_risk_analysis_likelihood_form">
 
	<fieldset>
	<legend>${lang.getStringRiskAnalysis()}</legend>
	<div class="ui-widget-header">${lang.getStringLikelihood()}</div>
    <div class="ui-widget-content" style="padding: 5px;">
    
    <table width="100%">
    <tbody>
    	<tr>
    		<td><select style="width:100%" id="id_management_risk_analysis_likelihood_likelihood_year_selector" onchange="fn_change_likelihood(this.value);" name="method" class="">
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
    		<td align="left"><a id="id_management_risk_analysis_likelihood_likelihood_search_btn" href="#">${lang.getStringSearch()}</a> </td>
    		<td align="right">${lang.getStringFrequency()}: <input style="width:30px;" type="text" id="id_management_risk_analysis_likelihood_likelihood_frequency_text" class="form_input_text" disabled="disabled" value="4"/></td>
    		<td align="right">Worst Residual Likelihood: <input style="width:100px;" type="text" id="id_management_risk_analysis_likelihood_likelihood_worst_residual_text" class="form_input_text" disabled="disabled" value=""/></td>
    	</tr>
    </tbody>
    </table>
    <table width="100%">
    	<tbody>
    		<tr>
    			<td>
    			<div style="width:100%;" id="id_management_risk_analysis_likelihood_likelihood_ListTable_parentDiv">
    			<table id="id_management_risk_analysis_likelihood_likelihood_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
    			</div>
				<div id="management_risk_analysis_likelihood_likelihood_pager1" class="scroll"></div>  
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
    		<td><select style="width:100%" id="id_management_risk_analysis_likelihood_existing_controls_year_selector" onchange="fn_change_likelihood_existing_controls(this.value);" name="method" class="">
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
    		<td align="left"><a id="id_management_risk_analysis_likelihood_existing_controls_search_btn" href="#">${lang.getStringSearch()}</a> </td>
    		<td align="right" width="70%"></td>
    	</tr>
    </tbody>
    </table>
    <table>
    	<tbody>
    		<tr>
    			<td>
    			<table id="id_management_risk_analysis_likelihood_existing_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_risk_analysis_likelihood_existing_controls_pager1" class="scroll"></div>  
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
    			<table id="id_management_risk_analysis_likelihood_new_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_risk_analysis_likelihood_new_controls_pager1" class="scroll"></div>  
    			</td>
    		</tr>
    	</tbody>
    </table>
    </div>	
	</fieldset>
	
	<fieldset>
	<legend>Determine Likelihood</legend>
	<table width="100%">
	<tbody>
		<tr>
			<td>Initial ${lang.getStringLikelihood()}: <select class="likelihood" style="width:50%" id="id_management_risk_analysis_likelihood_initial_likelihood_selector" name="risk_analysis_likelihood_initial_likelihood" class="hazard_item_selector">
			    <option value="0">Select</option>
			    <c:forEach items="${likelihoodList}" var="selectItem" varStatus="list_status">
	 			<option value="${selectItem.value }" ${hazard.likelihood_initial_likelihood == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option>
	 			</c:forEach>
			</select></td>
			<td></td>
			<td>Residual ${lang.getStringLikelihood()}: <select class="likelihood" style="width:50%" id="id_management_risk_analysis_likelihood_residual_likelihood_selector" name="risk_analysis_likelihood_residual_likelihood" class="hazard_item_selector">
				<option value="0">Select</option>
			    <c:forEach items="${likelihoodList}" var="selectItem" varStatus="list_status">
	 			<option value="${selectItem.value }" ${hazard.likelihood_residual_likelihood == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option>
	 			</c:forEach>
			</select> </td>
		</tr>
	</tbody>
	</table>
	</fieldset>
	
	<fieldset>
	<legend>Comments</legend>
	<textarea class="likelihood" rows="3" style="width:100%" name="risk_analysis_likelihood_comments" id="id_management_risk_analysis_likelihood_comments">${hazard.likelihood_comments}</textarea>
	</fieldset>
	
	<table width="100%">
	
	<tr>
		<td align="center">
		<a id="id_management_risk_analysis_likelihood_edit_btn" href="#">${lang.getStringEdit()}</a> <a id="id_management_risk_analysis_likelihood_save_btn" href="#">${lang.getStringSave()}</a> 
		<a id="id_management_risk_analysis_likelihood_delete_btn" href="#">${lang.getStringDelete()}</a> <a id="id_management_risk_analysis_likelihood_submit_btn" href="#">${lang.getStringSubmit()}</a> <a id="id_management_risk_analysis_likelihood_submitted_btn" href="#">${lang.getStringSubmitted()}</a>
		</td>
	</tr>
			
    </table>
	
 
 </form>
</body>
</html>
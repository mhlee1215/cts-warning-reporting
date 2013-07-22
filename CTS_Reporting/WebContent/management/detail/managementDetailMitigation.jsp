<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Mitigation</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/themes/base/jquery-ui.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/jquery-1.9.1.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/ui/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <style>
  .ui-button-text-icon-secondary .ui-button-text, .ui-button-text-icons .ui-button-text {
	padding: .1em 2.1em .1em 1em !important;
  }
  </style>
  <script>
  $(function() {
	  $("#id_management_mitigation_likelihood_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  $("#id_management_mitigation_existing_controls_search_btn")
	  .button({icons: {secondary: "ui-icon-search" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  
	  $("#id_management_mitigation_edit_btn")
	  .button({icons: {secondary: "ui-icon-document" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	  $("#id_management_mitigation_edit_btn").hide();
	  $("#id_management_mitigation_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   updateMitigation('n');
	});
	  $("#id_management_mitigation_delete_btn")
	  .button({icons: {secondary: "ui-icon-trash" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_mitigation_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   updateMitigation('y');
	});
	  
	  $("#id_management_mitigation_submitted_btn")
	  .button({icons: {secondary: "ui-icon-rocked" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  
	  $("#id_management_mitigation_submitted_btn").hide();
	  
	  $("#id_management_mitigation_view_report_btn")
	  .button({icons: {secondary: "ui-icon-forder-open" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   window.open('${pageContext.request.contextPath}/managementDetailHazardIdentificationReport.do?report_no=RP200713KE1203-1&category=&type=TAXI-OUT','viewReportWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=950,height=900');
	   
	  });
	  
	  $("#id_management_mitigation_view_standard_btn")
	  .button({icons: {secondary: "ui-icon-forder-open" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   window.open('${pageContext.request.contextPath}/managementDetailViewStandardt.do','viewStandardWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=950,height=650');
	   
	  });
	  
	  $("#id_management_risk_mitigation_owner1_requested_date").datepicker({
	      showOn: "both",
	      buttonImage: "${pageContext.request.contextPath}/images/calendar.gif",
	      buttonImageOnly: true
	    });
	  $("#id_management_risk_mitigation_owner1_due_date").datepicker({
	      showOn: "both",
	      buttonImage: "${pageContext.request.contextPath}/images/calendar.gif",
	      buttonImageOnly: true
	    });
	  
	  $("#id_management_risk_mitigation_owner2_requested_date").datepicker({
	      showOn: "both",
	      buttonImage: "${pageContext.request.contextPath}/images/calendar.gif",
	      buttonImageOnly: true
	    });
	  $("#id_management_risk_mitigation_owner2_due_date").datepicker({
	      showOn: "both",
	      buttonImage: "${pageContext.request.contextPath}/images/calendar.gif",
	      buttonImageOnly: true
	    });
	  
	  management_mitigation_new_controls_item();
	  management_mitigation_existing_controls_item();
	  
	 
	  <c:if test="${hazard.state_mitigation == 'SUBMITTED'}" >
	    $("#id_management_mitigation_submitted_btn").show();
	    $("#id_management_mitigation_submit_btn").hide();   
	   
	   	$('input').attr('disabled', 'disabled');
		$('select').attr('disabled', 'disabled');
		$('textarea').attr('disabled', 'disabled');
	</c:if>
  });
  
  function updateMitigation (issubmit){
		 var str = $("#management_detail_mitigation_form").serialize();
		  //alert(str);
		   
		   $.ajax({
			    type:"post",
			    data:str+'&hazard_no=${hazard_no}&issubmit='+issubmit,
			    url:"managementDetailMitigationUpdate.do",
			    async: false,
			    success: function(msg){
			       alert(msg);
			       if(issubmit == 'y'){
			    	   $("#id_management_mitigation_submitted_btn").show();
				 	   $("#id_management_mitigation_submit_btn").hide();   
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
	  var return_str = '<select style="width:100%" id="id_management_mitigation_initial_likelihood_selector" name="method" class="hazard_item_selector">'+
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
  
  
  function management_mitigation_new_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_mitigation_new_controls_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisSeverityNewControlsList.do', 
	  	height: 80, 
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
	  }).navGrid('#management_mitigation_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  
  function management_mitigation_existing_controls_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jquery/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_management_mitigation_existing_controls_ListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/managementDetailRiskAnalysisSeverityExistingControlsList.do', 
	  	height: 80, 
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
	  }).navGrid('#management_mitigation_likelihood_pager1',{edit:false,add:false,del:false}); 
  }
  </script>
  
</head>
<body>
<form id="management_detail_mitigation_form">
 	<table width="100%" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>
				<div class="ui-widget-header">${lang.getStringHazardNo()} : ${hazard_no}</div>
				</td>
				<td align="right" ><a id="id_management_mitigation_view_report_btn" href="#">View Report</a>
				
				<a id="id_management_mitigation_view_standard_btn" href="#">View Standard</a>
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
						<table width="100%" cellpadding="2" cellspacing="5">
							<tbody>
								<tr>
									<td align="left">${lang.getStringLikelihood()}</td>
									<td><b>${likelihoodNameMap[hazard.likelihood_initial_likelihood]}</b></td>
									<td width="10%"></td>
								</tr>
								<tr>
									<td align="left">${lang.getStringSeverity()}</td>
									<td><b>${severityNameMap[hazard.severity_initial_likelihood]}</b></td>
								</tr>
								<tr>
									<td align="left">Tolerability</td>
									<td bgcolor="${initial_acceptable_color}"><font color="${initial_text_color}"><b>${initial_risk_text} : ${initial_acceptable_text}</b></font></td>
								</tr>
							</tbody>
						</table>
					</fieldset>
				</td>
				<td width="2%"></td>
				<td width="49%">
					<fieldset>
					<legend>Residual ${lang.getStringRisk()}</legend>
						<table width="100%" cellpadding="2" cellspacing="5">
							<tbody>
								<tr>
									<td align="left">${lang.getStringLikelihood()}</td>
									<td><b>${likelihoodNameMap[hazard.likelihood_residual_likelihood]}</b></td>
									<td width="10%"></td>
								</tr>
								<tr>
									<td align="left">${lang.getStringSeverity()}</td>
									<td><b>${severityNameMap[hazard.severity_residual_likelihood]}</b></td>
								</tr>
								<tr>
									<td align="left">Tolerability</td>
									<td bgcolor="${residual_acceptable_color}"><font color="${residual_text_color}"><b>${residual_risk_text} : ${residual_acceptable_text}</b></font></td>
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
    		<td><select style="width:100%" id="id_management_mitigation_existing_controls_year_selector" name="method" class="">
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
    		<td align="left"><a id="id_management_mitigation_existing_controls_search_btn" href="#">${lang.getStringSearch()}</a> </td>
    		<td align="right" width="70%"></td>
    	</tr>
    </tbody>
    </table>
    <table>
    	<tbody>
    		<tr>
    			<td>
    			<table id="id_management_mitigation_existing_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_mitigation_existing_controls_pager1" class="scroll"></div>  
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
    			<table id="id_management_mitigation_new_controls_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="management_mitigation_new_controls_pager1" class="scroll"></div>  
    			</td>
    		</tr>
    	</tbody>
    </table>
    </div>	
    
    <fieldset>
    <legend>Comments</legend>
    <table width="100%" cellpadding="0px;">
		<tr>
			<th>${lang.getStringLikelihood()}</th>
			<th width="10px;"></th>
			<th>${lang.getStringSeverity()}</th>
		</tr>
		<tr>
			<td><textarea rows="3" style="width:100%" name="management_mitigation_likelihood_comments" id="id_management_mitigation_likelihood_comments">${hazard.likelihood_comments}</textarea></td>
			<td></td>
			<td><textarea rows="3" style="width:100%" name="management_mitigation_severity_comments" id="id_management_mitigation_severity_comments">${hazard.severity_comments}</textarea></td>
		</tr>
	</table>
    </fieldset>
	</fieldset>
	
	<fieldset>
	<legend>${lang.getStringRisk()} Owner Assignment</legend>
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td colspan="3">
				<table width="100%" cellpadding="2" cellspacing="0">
					<tr>
						<td width="80px;" align="left">Control No.</td>
						<td align="left"><input type="text" name="management_mitigation_contorol_no" id="id_managment_risk_mitigation" disabled="disabled" value="C2604117C1234-03"/></td>
						<td width="130px;" align="left">To be tracked by</td>
						<td align="left"><select style="width:100%" id="id_management_risk_mitigation_tracked_by_selector" name="management_mitigation_tracked_by" class="">
							<option value="Ohhoon Kwon" ${hazard.tracked_by == "Ohhoon Kwon" ? "selected=\"selected\"" : ""}>Ohhoon Kwon</option>
							<option value="Yoonjin Yoon" ${hazard.tracked_by == "Yoonjin Yoon" ? "selected=\"selected\"" : ""}>Yoonjin Yoon</option>
							<option value="Hwasu Yeo" ${hazard.tracked_by == "Hwasu Yeo" ? "selected=\"selected\"" : ""}>Hwasu Yeo</option>
							<option value="Sahee Lee" ${hazard.tracked_by == "Sahee Lee" ? "selected=\"selected\"" : ""}>Sahee Lee</option>
							<option value="Taeho Yoon" ${hazard.tracked_by == "Taeho Yoon" ? "selected=\"selected\"" : ""}>Taeho Yoon</option>
							</select>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
			    <c:forEach items="${riskOwnerList}" var="riskOwner" varStatus="list_status">
				<td width="49%">
					<fieldset>
					<legend>${lang.getStringOwner()} ${list_status.index+1}</legend>
						<table width="100%" cellpadding="2" cellspacing="2">
							<tbody>
								<tr>
									<td align="left">${lang.getStringDivision()}</td>
									<td><select style="width:100%" id="id_management_risk_mitigation_owner1_division_selector" name="management_mitigation_division_${list_status.index+1}" class="">
									    <c:forEach items="${divisionList}" var="selectItem" varStatus="division_list_status">
	 									<option value="${selectItem.value }" ${riskOwner.division == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option> -->
	 									</c:forEach>
										</select>
									</td>
									<td width="10%"></td>
								</tr>
								<tr>
									<td align="left">${lang.getStringAssignedTo()}</td>
									<td><select style="width:100%" id="id_management_risk_mitigation_owner1_assigned_to_selector" name="management_mitigation_assigned_to_${list_status.index+1}" class="">
									<option value="Yuna Noh" ${riskOwner.assigned_to == "Yuna Noh" ? "selected=\"selected\"" : ""}>Yuna Noh</option>
									<option value="Jiseon Lee" ${riskOwner.assigned_to == "Jiseon Lee" ? "selected=\"selected\"" : ""}>Jiseon Lee</option>
									<option value="Sangeon Seo" ${riskOwner.assigned_to == "Sangeon Seo" ? "selected=\"selected\"" : ""}>Sangeon Seo</option>
									<option value="Eunhye Kim" ${riskOwner.assigned_to == "Eunhye Kim" ? "selected=\"selected\"" : ""}>Eunhye Kim</option>
									</select></td>
								</tr>
								<tr>
									<td align="left">${lang.getStringRequestedDate()}</td>
									<td><input type="text" style="width:90%" name="management_mitigation_requested_date_${list_status.index+1}" id="id_management_risk_mitigation_owner${list_status.index+1}_requested_date" value="${riskOwner.requested_date}"/></td>
								</tr>
								<tr>
									<td align="left">${lang.getStringDueDate2()}</td>
									<td><input type="text" style="width:90%" name="management_mitigation_due_date_${list_status.index+1}" id="id_management_risk_mitigation_owner${list_status.index+1}_due_date" value="${riskOwner.due_date}"/></td>
								</tr>
								<tr>
									<td colspan="3">
									<fieldset>
									<legend>Comments</legend>
										<textarea rows="3" style="width:100%" name="management_mitigation_comments_${list_status.index+1}" id="id_management_mitigation_owner${list_status.index+1}_comments">${riskOwner.comments}</textarea>
									</fieldset>
									</td>
									
								</tr>
							</tbody>
						</table>
					</fieldset>
				</td>
				</c:forEach>

			</tr>
		</tbody>
	</table>
	
	
	</fieldset>
	
	<table width="100%">
	
	<tr>
		<td align="center">
		<a id="id_management_mitigation_edit_btn" href="#">${lang.getStringEdit()}</a> <a id="id_management_mitigation_save_btn" href="#">${lang.getStringSave()}</a> 
		<a id="id_management_mitigation_delete_btn" href="#">${lang.getStringDelete()}</a> <a id="id_management_mitigation_submit_btn" href="#">${lang.getStringSubmit()}</a> <a id="id_management_mitigation_submitted_btn" href="#">${lang.getStringSubmitted()}</a>
		</td>
	</tr>
			
    </table>
	
 
</form> 
</body>
</html>
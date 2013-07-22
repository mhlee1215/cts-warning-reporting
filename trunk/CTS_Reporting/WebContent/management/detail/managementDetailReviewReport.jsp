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
	   
	   var str = $("#management_detail_review_report_form").serialize();
	  // alert(str);
	   
	   $.ajax({
		    type:"post",
		    data:str+'&report_no=${report_no}&type=${type}',
		    url:"managementDetailReviewReportUpdate.do",
		    async: false,
		    success: function(msg){
		       alert(msg);
		    }
		});
	   
	});
	  $("#id_management_review_${category}_${type}_delete_btn")
	  .button({icons: {secondary: "ui-icon-trash" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  
	  $("#id_management_review_${category}_${type}_submitted_btn")
	  .button({icons: {secondary: "ui-icon-rocked" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_review_${category}_${type}_submitted_btn").hide();
	  
	  $("#id_management_review_${category}_${type}_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
		  //alert('id_menu_${category}_${type}');
		  
		   var str = $("#management_detail_review_report_form").serialize();
	  // alert(str);
	   
	   $.ajax({
		    type:"post",
		    data:str+'&report_no=${report_no}&type=${type}',
		    url:"managementDetailReviewReportSubmit.do",
		    async: false,
		    success: function(msg){
		       alert(msg);
		       $("#id_management_review_menu_${type}_${category}").attr("class", "ui-icon ui-icon-bullet");
		       $("#id_management_review_${category}_${type}_submitted_btn").show();
		 	   $("#id_management_review_${category}_${type}_submit_btn").hide();
		    }
		});
		  
		  
		  //${category}_${type}_
	   event.preventDefault();
	   
	  
	});
	  
	  management_review_load_hazard_item();
	  
	  <c:if test="${reportItem.status_review == 'SUBMITTED'}" >
	  $("#id_management_review_${category}_${type}_submitted_btn").show();
	  $("#id_management_review_${category}_${type}_submit_btn").hide();
	  </c:if>
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
<form id="management_detail_review_report_form">
<div class="ui-widget-header">${lang.getStringReportNo()} : ${reportItem.report_no }</div>
<div class="ui-widget-content" style="padding: 5px;">
<table width="100%">
<tr>
<td id="id_management_detail_main_right_panel" >
	<fieldset >
	    <legend>${lang.getStringTitle()} : ${reportItem.title }</legend>
	    <div class="ui-widget-header">${userTypeNameList[report.type]}</div>
	    <div class="ui-widget-content" style="padding: 5px;">
	    <table width="100%">
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringNarrative()}</legend>
	    <textarea readonly="readonly" rows="6" style="width:100%" id="id_management_review_${category}_${type}_narrative">${reportItem.narrative }</textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringRecommendation()}</legend>
	    <textarea readonly="readonly" rows="6" style="width:100%" id="id_management_review_${category}_${type}_recommendation">${reportItem.recommendation}</textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <iframe id="${report_item_type}_attached_file_table" src="${pageContext.request.contextPath}/fileUploadForm.do?report_no=${reportItem.report_no}&report_item_type=${reportItem.type}&isReadOnly=Y" width="100%" style="height:190px;padding:0px;border:0px;" >
	    </iframe>    
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringSafetyOfficer()} Only</legend>
	    <fieldset>
	    <legend>Comments</legend>
	    <textarea rows="6" style="width:100%" id="id_management_review_${category}_${type}_comments" name="reportItem_comments">${reportItem.comments}</textarea>
	    </fieldset>
	    <table width="100%">
	    	<tr>
	    	
	    	
	    	<tr>
											
										
	    	
	    		<td width="30%" align="left"><input type="radio" name="safety_officer_opinion"
												id="id_management_review_accept" value="${statusReviewMap['ACCEPTED']}"
												${reportItem.status_determine == statusReviewMap['ACCEPTED'] ? "checked=\"checked\"" : ""} /> ${lang.getStringAccept()}</td>
	    		<td width="30%" align="center"><input type="radio" name="safety_officer_opinion"
												id="id_management_review_reject" value="${statusReviewMap['REJECTED']}"
												${reportItem.status_determine == statusReviewMap['REJECTED'] ? "checked=\"checked\"" : ""} /> ${lang.getStringReject()}</td>
	    		<td width="40%" align="right"><input type="radio" name="safety_officer_opinion"
												id="id_management_review_need_an_investigation" value="${statusReviewMap['NEEDINVESTIGATION']}"
												${reportItem.status_determine == statusReviewMap['NEEDINVESTIGATION'] ? "checked=\"checked\"" : ""} /> ${lang.getStringNeedAnInvestigation()}</td>
	    	</tr>
	    	<tr>
	    		<td></td>
	    		<td></td>
	    		<td align="right">${lang.getStringPriority()} <select id="id_management_review_${category}_${type}_priority_selector" name="priority" class="form_selector">
				<option value="0">Select</option>	
				<c:forEach items="${priorityList}" var="priority" varStatus="list_status">
					<option value="${priority.value}" ${reportItem.priority == priority.value ? "selected=\"selected\"" : ""}>${priority.name}</option>
				</c:forEach>
			</select></td>
	    	</tr>
	    </table>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
			<td align="center">
			<a id="id_management_review_${category}_${type}_edit_btn" href="#">${lang.getStringEdit()}</a> <a id="id_management_review_${category}_${type}_save_btn" href="#">${lang.getStringSave()}</a> 
			<a id="id_management_review_${category}_${type}_delete_btn" href="#">${lang.getStringDelete()}</a> <a id="id_management_review_${category}_${type}_submit_btn" href="#">${lang.getStringSubmit()}</a> <a id="id_management_review_${category}_${type}_submitted_btn" href="#">${lang.getStringSubmitted()}</a> 
			</td>
		</tr>
				
	    </table>
	    
	    
	    
		
	    
	    
	    
	    
	    </div>
	</fieldset>
</td>
</tr>
</table>
</div>
 
 </form>
</body>
</html>
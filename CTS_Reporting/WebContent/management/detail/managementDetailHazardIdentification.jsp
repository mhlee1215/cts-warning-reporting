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
  
  <script src="js/jquery.treeview.js" type="text/javascript"></script>
  <script src="js/jquery.cookie.js" type="text/javascript"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.treeview.css" />
  
  <script>
  $(function() {
	  $( "#management_hazard_identification_accordion" ).accordion({
		  	header: "h3",
		    collapsible: true,
		    autoHeight: false,
		    navigation: true,
		    heightStyle: "content"
	  });
	  
	
	
	 $( "#management_hazard_identification_basic_menu" ).menu();
	 $( "#management_hazard_identification_taxi_out_menu" ).menu();
	 $( "#management_hazard_identification_take_off_menu" ).menu();
	 $( "#management_hazard_identification_climb_menu" ).menu();
	 $( "#management_hazard_identification_en_route_menu" ).menu();
	 $( "#management_hazard_identification_decent_menu" ).menu();
	 $( "#management_hazard_identification_approach_menu" ).menu();
	 $( "#management_hazard_identification_landing_menu" ).menu();
	 
	 <c:forEach items="${reportItemNameList}" var="reportItemName" varStatus="list_status">
	 $( "#management_hazard_identification_${reportItemName}_menu" ).menu();
	 </c:forEach>
	 
	 <c:forEach items="${userTypeList}" var="userType" varStatus="list_status">
			<c:if test="${classifiedRpList[userType].size() > 0}" >
			$("#id_management_hazard_identification_basic_${userType}_sub_menu").menu();
			</c:if>
	 </c:forEach>
	  
	  //management_hazard_identification_load_hazard_item();
	  //loadBasicInfo();
	  //alert($('#id_taxi_out_hazardListTable_parentDiv').width());
	  
	  $("#id_management_hazard_identification_basicinfo_tree").treeview({
			animated: "fast",
			collapsed: true,
			unique: true
		});
	  <c:forEach items="${reportItemNameList}" var="reportItemName" varStatus="list_status">
	  $("#id_management_hazard_identification_${reportItemName}_info_tree").treeview({
			animated: "fast",
			collapsed: true,
			unique: true
	  });
	  
	  <c:forEach items="${userTypeList}" var="userType" varStatus="list_status">
		<c:if test="${classifiedRpList[userType].size() > 0}" >
		$("#id_management_hazard_identification_${reportItemName}_${userType}_sub_menu").menu();
		</c:if>
	  </c:forEach>
	 </c:forEach>
	 
		<c:forEach items="${userTypeList}" var="userType" varStatus="list_status">
		
			<c:if test="${classifiedRpList[userType].size() > 0}" >
			
				<c:forEach items="${classifiedRpList[userType]}" var="report" varStatus="list_status_sub">
					<c:if test="${list_status_sub.index == 0}" >
						loadBasicInfo('${report.report_no}');
					</c:if>
				</c:forEach>
		
			</c:if>
			
	</c:forEach>
	 
  });
   
  function management_hazard_identification_load_hazard_item(){
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
  
  function loadBasicInfo(report_no){
	  $("#id_management_detail_main_right_panel").load("${pageContext.request.contextPath}/reportBASIC.do?isReadOnly=Y&report_no="+report_no);
	  //alert('hi');
  }
  
  function management_hazard_identification_loadReport(report_no, category, type){
	  $("#id_management_detail_main_right_panel").load("${pageContext.request.contextPath}/managementDetailHazardIdentificationReport.do?report_no="+report_no+"&category="+category+"&type="+type);
  }
  
  
  </script>
  <style>
  body {
	font-family: "Trebuchet MS", "Helvetica", "Arial",  "Verdana", "sans-serif";
	font-size: 70%;
}
  </style>
</head>
<body>
 
<div class="ui-widget-header">${lang.getStringReportNo()} : ${report_no }</div>
<div class="ui-widget-content" style="padding: 5px;">
<table width="100%">
<tr>
<td id="id_management_detail_main_left_panel" width="180" valign="top">


<div id="management_hazard_identification_accordion">
  <h3>${lang.getStringBasicInformation()}</h3>
  <div style="padding:0 0 0 15px;">
		<ul id="id_management_hazard_identification_basicinfo_tree" class="">
			<c:forEach items="${userTypeList}" var="userType" varStatus="list_status">
				<li><span class="folder">${userTypeNameList[userType]} (${classifiedRpList[userType].size()})</span>
					<c:if test="${classifiedRpList[userType].size() > 0}" >
					<ul id="id_management_hazard_identification_basic_${userType}_sub_menu" style="width:120px;">
						<c:forEach items="${classifiedRpList[userType]}" var="report" varStatus="list_status">
							<li class=""><a href="javascript:loadBasicInfo('${report.report_no}')"><span class="ui-icon ui-icon-radio-off"></span>${report.type}</a></li>
						</c:forEach>
					</ul>
					</c:if>
				</li>	
			</c:forEach>
		</ul>
	</div>
  <c:forEach items="${reportItemNameList}" var="reportItemName" varStatus="list_status">
  <h3>${reportItemName} (${reportItemSize[reportItemName]})</h3>
	<div style="padding:0 0 0 15px;">
	
		<ul id="id_management_hazard_identification_${reportItemName}_info_tree" class="">
			<c:forEach items="${userTypeList}" var="userType" varStatus="list_status">
			
				<li><span class="folder">${userTypeNameList[userType]} (${reportItemList[reportItemName][userType].size()})</span>
					<c:if test="${reportItemList[reportItemName][userType].size() > 0}" >
					
					<ul id="id_management_hazard_identification_${reportItemName}_${userType}_sub_menu" style="width:120px;">
						<c:forEach items="${reportItemList[reportItemName][userType]}" var="reportItem" varStatus="list_status">
							<li class=""><a href="javascript:management_hazard_identification_loadReport('${reportItem.report_no}', '${userType}', '${reportItem.type}')"><span id="id_management_hazard_identification_menu_${reportItem.type}_${userType}" class="ui-icon ${reportItem.status_hazard_id == "SUBMITTED" ? "ui-icon-bullet" : "ui-icon-radio-off"}"></span>${userType}${reportItem.type_index}</a></li>
						</c:forEach>
					</ul>
					
					</c:if>
				</li>	
				
			</c:forEach>
		</ul>
		
	</div>
	
  </c:forEach>
</div>

</td>
<td id="id_management_detail_main_right_panel" >
	
</td>
</tr>
</table>
</div>
 
 
</body>
</html>
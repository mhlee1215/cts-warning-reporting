<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
  <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>
  <style>
  .hazard_item_selector{
  	margin-left:2px;
  	margin-right:2px;
  	width:719px;
  }
  .hazard_item_text_input{
    height:14px;
  	width:717px;
  }
  </style>
</head>
<script>
  $(function() {
	  $( "#tabs_review" ).tabs();
	   management_review_load_report("id_management_review_all_ListTable", "reportToIdentifyList.do");
	   management_review_load_report("id_management_review_review_ListTable", "reportToIdentifyList.do");
	   management_review_load_report("id_management_review_accepted_ListTable", "reportToIdentifyList.do");
	   management_review_load_report("id_management_review_rejected_ListTable", "reportToIdentifyList.do");
	   management_review_load_report("id_management_review_investigation_ListTable", "reportToIdentifyList.do");
	   management_review_load_report("id_management_review_registered_ListTable", "reportToIdentifyList.do");
  });
  
   
  function management_review_load_report(id, url){
	  var gridimgpath = '${pageContext.request.contextPath}/jqueryui-1.10.2/themes/base/images';
	  jQuery("#"+id).jqGrid({
	  	url:'${pageContext.request.contextPath}/'+url, 
	  	height: 120,
	  	width:800,
	  	datatype: "xml", 
	     	colNames:['Report No.','Date (UTC)', 'Aircraft Damage11', 'Injury','Delay Time','Priority','State'],
	     	colModel:[
	     	 			{name:'rp_no'		,index:'rp_no'		,width:120	,align:"left"	,sortable: true},
	     	    		{name:'date'		,index:'date'		,width:90	,align:"center"	,sortable: true},
	     	    		{name:'ac_damage'	,index:'ac_damage'	,width:70	,align:"center"	,sortable: true},
	     	    		{name:'injury'		,index:'injury'		,width:90	,align:"center"	,sortable: true},
	     	    		{name:'delay_time'	,index:'delay_time'	,width:90	,align:"center"	,sortable: true},
	     	    		{name:'priority'	,index:'priority'	,width:80	,align:"center"	,sortable: true},		
	     	    		{name:'state'		,index:'state'		,width:85	,align:"center" ,sortable: true}		
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
	     	//toolbar: [false,"top"],
	     	//loadError : function(xhr,st,err) { 
	  	   	//jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText+". Please reload running status table."); 
	  	//},
	  	loadComplete: function(){ 
	  		
	  	},
	  	onSelectRow: function(id){ 
	  		//var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
	  	    //alert(localRowData);
	  		window.open('${pageContext.request.contextPath}/managementDetailMain.do','targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=900,height=800');
	    }
	  }).navGrid('#pager1',{edit:false,add:false,del:false}); 
	  
  }
  

  </script>
<body>

<div id="tabs_review">
  <ul>
    <li><a href="#tabs-1">All</a></li>
    <li><a href="#tabs-2">Review</a></li>
    <li><a href="#tabs-3">Accepted</a></li>
    <li><a href="#tabs-4">Rejected</a></li>
    <li><a href="#tabs-5">Investigation</a></li>
    <li><a href="#tabs-6">Registered</a></li>
  </ul>
  <div id="tabs-1">
    <table>
	<tbody>
		<tr>
			<td align="center" width="723px;" ><table id="id_management_review_all_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
			<div id="id_management_review_all_pager" class="scroll"></div>
			</td>
		</tr>
	</tbody>
	</table>
  </div>
  <div id="tabs-2">
    <table>
	<tbody>
		<tr>
			<td align="center" width="723px;" ><table id="id_management_review_review_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
			<div id="id_management_review_review_pager" class="scroll"></div>
			</td>
		</tr>
	</tbody>
	</table>
  </div>
  <div id="tabs-3">
    <table>
	<tbody>
		<tr>
			<td align="center" width="723px;" ><table id="id_management_review_accepted_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
			<div id="id_management_review_accepted_pager" class="scroll"></div>
			</td>
		</tr>
	</tbody>
	</table>
  </div>
  <div id="tabs-4">
    <table>
	<tbody>
		<tr>
			<td align="center" width="723px;" ><table id="id_management_review_rejected_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
			<div id="id_management_review_rejected_pager" class="scroll"></div>
			</td>
		</tr>
	</tbody>
	</table>
  </div>
  <div id="tabs-5">
    <table>
	<tbody>
		<tr>
			<td align="center" width="723px;" ><table id="id_management_review_investigation_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
			<div id="id_management_review_investigation_pager" class="scroll"></div>
			</td>
		</tr>
	</tbody>
	</table>
  </div>
  <div id="tabs-6">
    <table>
	<tbody>
		<tr>
			<td align="center" width="723px;" ><table id="id_management_review_registered_ListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
			<div id="id_management_review_registered_pager" class="scroll"></div>
			</td>
		</tr>
	</tbody>
	</table>
  </div>
</div>
</body>
</html>
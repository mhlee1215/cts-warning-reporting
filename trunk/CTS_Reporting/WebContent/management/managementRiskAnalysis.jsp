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
	  $( "#tabs_risk_analysis" ).tabs();
	   texi_out_load_hazard_item();
  });
  
  
  
  function texi_out_dateFormatter( cellvalue, options, rowObject )
  {
  	if(cellvalue != undefined && cellvalue != ''){
  		var year = cellvalue.substring(0, 4);
  		var month = cellvalue.substring(4, 6);
  		var date = cellvalue.substring(6, 8);
  		var hour = cellvalue.substring(8, 10);
  		var min = cellvalue.substring(10, 12);
  		var sec = cellvalue.substring(12, 14);
  		return year+'-'+month+'-'+date+' '+hour+':'+min+':'+sec;
  	}
  	return '-';
  }
  
  function texi_out_fnFormatter( cellvalue, options, rowObject )
  {
	var return_str = '<a id="id_taxi_out_seq_'+cellvalue+'_edit_hazard" href="#">Edit</a><a id="id_taxi_out_seq_'+cellvalue+'_delete_hazard" href="#">Delete</a>';
	return_str += '<script>';
	return_str += '$("#id_taxi_out_seq_'+cellvalue+'_edit_hazard").button().click(function( event ) {'
	return_str += '    	event.preventDefault();';
	return_str += '});';
	return_str += '$("#id_taxi_out_seq_'+cellvalue+'_delete_hazard").button().click(function( event ) {'
	return_str += '    	event.preventDefault();';
	return_str += '});';
	return_str += '</scr'+'ipt>';
  	return return_str;
  }
  
  
  
  function texi_out_load_hazard_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_taxi_out_hazardListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/reportToIdentifyList.do', 
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
	     	toolbar: [false,"top"],
	     	loadError : function(xhr,st,err) { 
	  	   	jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText+". Please reload running status table."); 
	  	},
	  	loadComplete: function(){ 
	  		
	  	},
	  	onSelectRow: function(id){ 
	  		var localRowData = $(this).jqGrid('getGridParam', "rp_no" );  
	  	    alert(localRowData);
	    }
	  }).navGrid('#pager1',{edit:false,add:false,del:false}); 
	  
  }
  

  </script>
<body>

<div id="tabs_risk_analysis">
  <ul>
    <li><a href="#tabs-1">${lang.getStringHazardsToBeAnalyzed()}</a></li>
    <li><a href="#tabs-2">${lang.getStringAnalyzedHazards()}</a></li>
  </ul>
  <div id="tabs-1">
    <table>
<tbody>
<tr>
	<td align="center" width="723px;" ><table id="id_taxi_out_hazardListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
	<div id="pager1" class="scroll"></div>
	</td>
</tr>
</tbody>
</table>
  </div>
  <div id="tabs-2">
    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
  </div>

</div>

</body>
</html>
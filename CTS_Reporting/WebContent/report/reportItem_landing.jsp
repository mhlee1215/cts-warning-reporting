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
   $("#id_landingmore_hazards")
   .button()
   .click(function( event ) {
	 fn_landing_reset_hazard_identification();
     event.preventDefault();
   });
   $("#id_landingsave_hazard")
   .button()
   .click(function( event ) {
     event.preventDefault();
   });
   
   $("#id_landing_save_btn")
   .button()
   .click(function( event ) {
     event.preventDefault();
   });
   $("#id_landing_previous_btn")
   .button()
   .click(function( event ) {
     changeTab(7);
     event.preventDefault();
   });
   $("#id_landing_next_btn")
   .button()
   .click(function( event ) {
	 changeTab(9);
     event.preventDefault();
   });
   
   fn_landing_read_hazard_item_list_top();
   fn_landing_disable_hazard_selector_from_level(1);
   
   fn_landing_load_hazard_item();
  });
  
  function fn_landing_reset_hazard_identification(){
	  fn_landing_read_hazard_item_list_top();
	  fn_landing_disable_hazard_selector_from_level(1);
  }
  
  function fn_landing_disable_hazard_selector_from_level(level){
	  //alert(level);
	  for(var cur_level=level+1 ; cur_level <= 5 ; cur_level++){
		  $('#id_landing_level_'+cur_level+'_selector').attr("disabled", true);
		  var selectItem = document.getElementById('id_landing_level_'+cur_level+'_selector');
		  for(var count = 0 ; count < selectItem.options.length ; count++)
			{
				selectItem.options[count] = null;
				count=count-1;
			}
		  selectItem.options[0] = new Option('[SELECT LEVEL '+cur_level+' HAZARD]', '');
		  
	  }
  }
  function fn_landing_enable_hazard_selector(level){
	  $('#id_landing_level_'+level+'_selector').removeAttr("disabled");
  }	
  
  function fn_landing_landing_enable_description_of_new_hazard(val){
	  if (val == 'y'){
		  $('#id_landing_description_of_new_hazard').removeAttr("disabled");
	  }else if( val == 'n'){
		  $('#id_landing_description_of_new_hazard').attr("disabled", true);
	  }
	  
	 
  }
  
  
  
  function fn_landing_read_hazard_item_list_top(callback){	
		$.ajax({
			type: "POST",
			url: "<c:url value='/getHazardItemListTop.do' />",
			success: function(msg){
				var Result = msg;
				Result = jQuery.trim(Result);
				var platforms = Result.split(",");
				var selectItem = document.getElementById('id_landing_level_1_selector');
				//Remove all Items
				for(var count = 0 ; count < selectItem.options.length ; count++)
				{
					selectItem.options[count] = null;
					count=count-1;
				}
				
				selectItem.options[0] = new Option('[SELECT LEVEL 1 HAZARD]', '');
				
				for(var count = 0 ; count < platforms.length ; count++)
				{
					var item = platforms[count].split("_/");
					selectItem.options[selectItem.options.length] = new Option(item[1], item[0]);
				}
				if(callback != undefined && callback != null)
					callback();	
			}
		});
  }
  
  function fn_landing_read_hazard_item_list_children(id, level, callback){ 
		$.ajax({
			type: "POST",
			url: "<c:url value='/getHazardItemListChildren.do' />",
			data: 'level='+level+'&parent_id='+id,
			success: function(msg){
				var Result = msg;
				Result = jQuery.trim(Result);
				var platforms = Result.split(",");
				var selectItem = document.getElementById('id_landing_level_'+(parseInt(level, 10)+1)+'_selector');
				//Remove all Items
				for(var count = 0 ; count < selectItem.options.length ; count++)
				{
					selectItem.options[count] = null;
					count=count-1;
				}
				
				selectItem.options[0] = new Option('[SELECT LEVEL '+(parseInt(level, 10)+1)+' HAZARD]', '');
				
				for(var count = 0 ; count < platforms.length ; count++)
				{
					var item = platforms[count].split("_/");
					selectItem.options[selectItem.options.length] = new Option(item[1], item[0]);
				}
				if(callback != undefined && callback != null)
					callback();	
				
				fn_landing_disable_hazard_selector_from_level(parseInt(level, 10)+1);
				fn_landing_enable_hazard_selector(parseInt(level, 10)+1);
			}
		});
  }
  
  function fn_landing_dateFormatter( cellvalue, options, rowObject )
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
  
  function fn_landing_fnFormatter( cellvalue, options, rowObject )
  {
	  var return_str = '<a id="id_landing_seq_'+cellvalue+'_edit_hazard" href="#">Edit</a><a id="id_landing_seq_'+cellvalue+'_delete_hazard" href="#">Delete</a>';
		return_str += '<script>';
		return_str += '$("#id_landing_seq_'+cellvalue+'_edit_hazard").button().click(function( event ) {'
		return_str += '    	event.preventDefault();';
		return_str += '});';
		return_str += '$("#id_landing_seq_'+cellvalue+'_delete_hazard").button().click(function( event ) {'
		return_str += '    	event.preventDefault();';
		return_str += '});';
		return_str += '</scr'+'ipt>';
	  	return return_str;
  }
  
  
  
  function fn_landing_load_hazard_item(){
	  var gridimgpath = '${pageContext.request.contextPath}/jqueryui-1.10.2/themes/base/images';
	  jQuery("#id_landing_hazardListTable").jqGrid({
	  	url:'${pageContext.request.contextPath}/getHazardItems.do', 
	  	height: 120,
	  	width:720,
	  	datatype: "xml", 
	     	colNames:['No.','HAZARD Lv1', 'HAZARD Lv2', 'HAZARD Lv3','HAZARD Lv4','HAZARD Lv5','FN'],
	     	colModel:[
	     	 			{name:'seq_num'		,index:'seq_num'		,width:30	,align:"center", hidden:true, sortable:false},
	     	    		{name:'item_lv1'	,index:'item_lv1'		,width:90	,align:"center"	,sorttype:"text"},
	     	    		{name:'item_lv2'	,index:'item_lv2'		,width:90	,align:"center"	},
	     	    		{name:'item_lv3'	,index:'item_lv3'		,width:90	,align:"center"	},
	     	    		{name:'item_lv4'	,index:'item_lv4'		,width:90	,align:"center"	},
	     	    		{name:'item_lv5'	,index:'item_lv5'		,width:90	,align:"center"	},	
	     	    		{name:'fn'			,index:'fn'				,width:95	,align:"center", formatter:fn_landing_fnFormatter	}		
	     	    	],
	     	//shrinkToFit:true,
	     	//altRows:true,
	     	hoverrows:false,
	     	rownumbers: true, 
	     	rowNum:10, 
	     	//autowidth: true, 
	     	loadtext:'&nbsp;Loading hazard items..',
	     	//loadtext:'<img src="/images/icons/icon_processing1.gif" width="16" height="16" title="Processing"></img>&nbsp;Loading task data..',
	     	rowList:[10,20,30], 
	     	//pager: jQuery('#pager1'), 
	     	pagerpos:'center',
	     	sortname: 'id', 
	     	sortorder: 'desc',
	     	imgpath: gridimgpath,
	     	//multiselect: true,
	     	viewrecords: true, 
	     	emptyrecords:'no task data',
	     	//caption: "Task List",
	     	toolbar: [false,"top"],
	     	loadError : function(xhr,st,err) { 
	  	   	jQuery("#rsperror").html("Type: "+st+"; Response: "+ xhr.status + " "+xhr.statusText+". Please reload running status table."); 
	  	},
	  	loadComplete: function(){ 
	  	    
	  		
	  			//jQuery("#id_landing_hazardListTable").setRowData(ids[i],{detail:detailHtml});				
	  			//$("#detail_button_"+recordArry['id']).click(function() {fncDetailTask(recordArry['id']);});
	 			//$("#step4_next_button_"+recordArry['id']).click(function() {fncShowCoord(recordArry['id']);});
	  			//$("#step4_stop_button_"+recordArry['id']).click(function() {stopTask(recordArry['id']); });
	  			
	  		
	  	}  
	  }).navGrid('#pager1',{edit:false,add:false,del:false}); 
	  
	  /*jQuery("#id_landing_hazardListTable").jqGrid({
		   	url:'${pageContext.request.contextPath}/getHazardItems.do', 
			datatype: "xml",
		   	colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
		   	colModel:[
		   		{name:'id',index:'id', width:75},
		   		{name:'invdate',index:'invdate', width:90},
		   		{name:'name',index:'name', width:100},
		   		{name:'amount',index:'amount', width:80, align:"right"},
		   		{name:'tax',index:'tax', width:80, align:"right"},		
		   		{name:'total',index:'total', width:80,align:"right"},		
		   		{name:'note',index:'note', width:150, sortable:false}		
		   	],
		   	rowNum:10,
		   	autowidth: true,
		   	rowList:[10,20,30],
		   	pager: jQuery('#pager1'),
		   	sortname: 'id',
		    viewrecords: true,
		    sortorder: "desc",
		    caption:"XML Example"
		}).navGrid('#pager1',{edit:false,add:false,del:false});		*/
  }
  

  </script>
<body>
<h2 class="ui-widget-header">LANDING INFORMATION</h2>
<div class="ui-widget-content">
<table>
<tbody>
<tr>
	<td colspan="2" align="left" class="leftmost_header1"><span style="margin-left:10px;">Hazard Identification</span></td>
</tr>
<tr>
	<td class="leftmost_label">Level1: </td>
	<td><select id="id_landing_level_1_selector" onchange="fn_landing_read_hazard_item_list_children(this.value,1);" name="method" class="hazard_item_selector">		
	</select> </td>
</tr>
<tr>
	<td class="leftmost_label">Level2: </td>
	<td><select id="id_landing_level_2_selector" onchange="fn_landing_read_hazard_item_list_children(this.value,2);" name="method" class="hazard_item_selector">
	</select> </td>
</tr>
<tr>
	<td class="leftmost_label">Level3: </td>
	<td><select id="id_landing_level_3_selector" onchange="fn_landing_read_hazard_item_list_children(this.value,3);" name="method" class="hazard_item_selector">
	</select> </td>
</tr>
<tr>
	<td class="leftmost_label">Level4: </td>
	<td><select id="id_landing_level_4_selector" onchange="fn_landing_read_hazard_item_list_children(this.value,4);" name="method" class="hazard_item_selector">
	</select> </td>
</tr>
<tr>
	<td class="leftmost_label">Level5: </td>
	<td><select id="id_landing_level_5_selector" name="method" class="hazard_item_selector">
	</select> </td>
</tr>
</tbody>
</table>
<table >
<tbody>
<tr>
	<td align="right" class="leftmost_label">Specific Items: </td>
	<td> <input type="text" id="id_landingspecific_items" class="hazard_item_text_input" /> </td>
</tr>
</tbody>
</table>
<table>
<tbody>
<tr>
	<td class="leftmost_label"></td><td align="right" width="723px;"><a id="id_landingsave_hazard" href="#">Done</a> <a id="id_landingmore_hazards" href="#">More Hazards?</a></td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
	<td class="leftmost_label"></td>
	<td align="center" width="723px;" ><table id="id_landing_hazardListTable" class="scroll" cellpadding="0" cellspacing="0"></table>
	<div id="pager1" class="scroll"></div>
	</td>
</tr>
</tbody>
</table>
<table>
<tbody>
<tr>
	<td class="leftmost_label">New Hazard:</td>
	<td style="width:30px;text-align:right;">No</td>
	<td> <input type="radio" name="new_hazard" id="landing_new_hazard_yes" onchange="fn_landing_landing_enable_description_of_new_hazard('n');" value="n" checked="checked"/></td>
	<td style="width:30px;text-align:right;">Yes</td>
	<td> <input type="radio"name="new_hazard" id="landing_new_hazard_no" onchange="fn_landing_landing_enable_description_of_new_hazard('y');" value="y"/></td>
</tr>
</tbody>
</table>
<table>
<tbody>
<tr>
	<td class="leftmost_label">Description of New Hazard</td>
	<td><textarea rows="4" cols="100" id="id_landing_description_of_new_hazard" disabled="disabled"></textarea></td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
	<td class="leftmost_label">Narrative</td>
	<td><textarea rows="4" cols="100" id="id_landing_narrative"></textarea></td>
</tr>
<tr>
	<td class="leftmost_label">Recommendation</td>
	<td><textarea rows="4" cols="100" id="id_landing_recommendation"></textarea></td>
</tr>
</tbody>
</table>
<table width="100%">
<tbody>
<tr>
	<td align="right"><a id="id_landing_save_btn" href="#">Save</a><a id="id_landing_previous_btn" href="#">Previous</a><a id="id_landing_next_btn" href="#">Next</a></td>
</tr>
</tbody>
</table>
</div>
</body>
</html>
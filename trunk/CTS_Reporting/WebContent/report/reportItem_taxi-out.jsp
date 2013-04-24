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
</head>
<script>
  $(function() {
   $("#id_more_hazards")
   .button()
   .click(function( event ) {
     event.preventDefault();
   });
   $("#id_save_hazard")
   .button()
   .click(function( event ) {
     event.preventDefault();
   });
   $("#id_taxi_out_save_btn")
   .button()
   .click(function( event ) {
     event.preventDefault();
   });
   $("#id_taxi_out_previous_btn")
   .button()
   .click(function( event ) {
     changeTab(1);
     event.preventDefault();
   });
   $("#id_taxi_out_next_btn")
   .button()
   .click(function( event ) {
	 changeTab(3);
     event.preventDefault();
   });
   
   read_hazard_item_list_top();
   disable_hazard_selector_from_level(1);
   
  });
  
  function disable_hazard_selector_from_level(level){
	  //alert(level);
	  for(var cur_level=level+1 ; cur_level <= 5 ; cur_level++){
		  $('#id_taxi_out_level_'+cur_level+'_selector').attr("disabled", true);
		  var selectItem = document.getElementById('id_taxi_out_level_'+cur_level+'_selector');
		  for(var count = 0 ; count < selectItem.options.length ; count++)
			{
				selectItem.options[count] = null;
				count=count-1;
			}
		  selectItem.options[0] = new Option('[SELECT LEVEL '+cur_level+' HAZARD]', '');
		  
	  }
  }
  function enable_hazard_selector(level){
	  $('#id_taxi_out_level_'+level+'_selector').removeAttr("disabled");
  }	
  
  function read_hazard_item_list_top(callback){	
		$.ajax({
			type: "POST",
			url: "<c:url value='/getHazardItemListTop.do' />",
			success: function(msg){
				var Result = msg;
				Result = jQuery.trim(Result);
				var platforms = Result.split(",");
				var selectItem = document.getElementById('id_taxi_out_level_1_selector');
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
  
  function read_hazard_item_list_children(id, level, callback){ 
		$.ajax({
			type: "POST",
			url: "<c:url value='/getHazardItemListChildren.do' />",
			data: 'level='+level+'&parent_id='+id,
			success: function(msg){
				var Result = msg;
				Result = jQuery.trim(Result);
				var platforms = Result.split(",");
				var selectItem = document.getElementById('id_taxi_out_level_'+(parseInt(level, 10)+1)+'_selector');
				//Remove all Items
				for(var count = 0 ; count < selectItem.options.length ; count++)
				{
					selectItem.options[count] = null;
					count=count-1;
				}
				
				selectItem.options[0] = new Option('[SELECT LEVEL 2 HAZARD]', '');
				
				for(var count = 0 ; count < platforms.length ; count++)
				{
					var item = platforms[count].split("_/");
					selectItem.options[selectItem.options.length] = new Option(item[1], item[0]);
				}
				if(callback != undefined && callback != null)
					callback();	
				
				disable_hazard_selector_from_level(parseInt(level, 10)+1);
				enable_hazard_selector(parseInt(level, 10)+1);
			}
		});
  }
  

  </script>
<body>
<h2 class="ui-widget-header">TAXI-OUT INFORMATION</h2>
<div class="ui-widget-content" align="center">
<table width="80%" style="margin:10px;">
<tbody>
<tr>
	<td colspan="2" align="left"><h2>Hazard Identification</h2></td>
</tr>
<tr>
	<td align="right" width="80px;">Level1: </td>
	<td><select id="id_taxi_out_level_1_selector" onchange="read_hazard_item_list_children(this.value,1);" name="method" style="width:100%">		
	</select> </td>
</tr>
<tr>
	<td align="right">Level2: </td>
	<td><select id="id_taxi_out_level_2_selector" onchange="read_hazard_item_list_children(this.value,2);" name="method" style="width:100%">
	</select> </td>
</tr>
<tr>
	<td align="right">Level3: </td>
	<td><select id="id_taxi_out_level_3_selector" onchange="read_hazard_item_list_children(this.value,3);" name="method" style="width:100%;">
	</select> </td>
</tr>
<tr>
	<td align="right">Level4: </td>
	<td><select id="id_taxi_out_level_4_selector" name="method" style="width:100%;">
	</select> </td>
</tr>
<tr>
	<td align="right">Level5: </td>
	<td><select id="id_taxi_out_level_5_selector" name="method" style="width:100%;">
	</select> </td>
</tr>
</tbody>
</table>
<table width="80%">
<tbody>
<tr>
	<td align="right" width="80px;">Specific Items: </td>
	<td> <input type="text" id="id_specific_items" style="width:100%;" /> </td>
</tr>
</tbody>
</table>
<table width="80%">
<tbody>
<tr>
	<td align="right" width="80px;"><a id="id_save_hazard" href="#">Done</a> <a id="id_more_hazards" href="#">More Hazards?</a></td>
</tr>
</tbody>
</table>

<table width="80%">
<tbody>
<tr>
	<td align="center"><textarea rows="4" cols="100%" ></textarea></td>
</tr>
</tbody>
</table>
<table width="80%">
<tbody>
<tr>
	<td align="center">New Hazard:</td>
	<td>No <input type="radio" name="new_hazard" value="n"/></td>
	<td>Yes <input type="radio"name="new_hazard" value="y"/></td>
</tr>
</tbody>
</table>
<table width="80%">
<tbody>
<tr>
	<td width="100px;">Description of NEw Hazard</td>
	<td><textarea rows="4" cols="50" disabled="disabled"></textarea></td>
</tr>
</tbody>
</table>
<hr>
<table width="80%">
<tbody>
<tr>
	<td>Narrative:</td>
	<td><textarea rows="4" cols="100%"></textarea></td>
</tr>
<tr>
	<td>Recommendation::</td>
	<td><textarea rows="4" cols="100%"></textarea></td>
</tr>
</tbody>
</table>
<table width="100%">
<tbody>
<tr>
	<td align="right"><a id="id_taxi_out_save_btn" href="#">Save</a><a id="id_taxi_out_previous_btn" href="#">Previous</a><a id="id_taxi_out_next_btn" href="#">Next</a></td>
</tr>
</tbody>
</table>
</div>
</body>
</html>
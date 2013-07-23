<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Hazard Management System - Report hazard_identification</title>
  <script>
  
  var itemListJson = eval(${hazardItemList});
  //alert(itemListJson[0].item_id);
  $(function() {
//$("#id_management_detail_main_right_panel").load("${pageContext.request.contextPath}/reportBASIC.do?isReadOnly=Y&report_no="+report_no);	 	    
	  $("#id_management_hazard_identification_previous_btn")
	  .button({icons: {primary: "ui-icon-circle-triangle-w" } })
	  .click(function( event ) {
	   event.preventDefault();
	

	});
	  $("#id_management_hazard_identification_next_btn")
	  .button({icons: {secondary: "ui-icon-circle-triangle-e" } })
	  .click(function( event ) {
	   event.preventDefault();

	});
	  
	 
		 
	  fn_management_hazard_identification_read_hazard_item_list_top();
      //fn_management_hazard_identification_disable_hazard_selector_from_level(1);
	   
	   //fn_management_hazard_identification_load_hazard_item();
	  
	  //management_hazard_identification_load_attached_file_item();
	  
	  <c:if test="${isreadonly == 'Y'}" >
   
	  
		
		
	   </c:if>
  });
  
  //function fn_management_hazard_identification_reset_hazard_identification(){
	  //fn_management_hazard_identification_read_hazard_item_list_top();
	  //fn_management_hazard_identification_disable_hazard_selector_from_level(1);
  //}
  
  function fn_management_hazard_identification_disable_hazard_selector_from_level(level){
	  //alert(level);
	  for(var cur_level=level+1 ; cur_level <= 5 ; cur_level++){
		  $('#id_management_hazard_identification_level_'+cur_level+'_selector').attr("disabled", true);
		  var selectItem = document.getElementById('id_management_hazard_identification_level_'+cur_level+'_selector');
		  for(var count = 0 ; count < selectItem.options.length ; count++)
			{
				selectItem.options[count] = null;
				count=count-1;
			}
		  selectItem.options[0] = new Option('[SELECT LEVEL '+cur_level+' HAZARD]', '');
		  
	  }
  }
  function fn_management_hazard_identification_enable_hazard_selector(level){
	  $('#id_management_hazard_identification_level_'+level+'_selector').removeAttr("disabled");
  }	
  
  
  
  
  
  function fn_management_hazard_identification_read_hazard_item_list_top(callback){	
		$.ajax({
			type: "POST",
			url: "<c:url value='/getHazardItemListTop.do' />",
			success: function(msg){
				var Result = msg;
				Result = jQuery.trim(Result);
				var platforms = Result.split(",");
				var selectItem = document.getElementById('id_management_hazard_identification_level_1_selector');
				//Remove all Items
				for(var count = 0 ; count < selectItem.options.length ; count++)
				{
					selectItem.options[count] = null;
					count=count-1;
				}
				
				selectItem.options[0] = new Option('[SELECT LEVEL 1 HAZARD]', 0);
				
				for(var count = 0 ; count < platforms.length ; count++)
				{
					var item = platforms[count].split("_/");
					selectItem.options[selectItem.options.length] = new Option(item[1], item[0]);
				}
				//selectItem.value = 1;
				
				if(itemListJson.length > 0)
					selectItem.value = itemListJson[0].item_id;

				
				if(itemListJson.length > 1){
					fn_management_hazard_identification_read_hazard_item_list_children(itemListJson[0].item_id,1, 'load');
				}
				
				if(callback != undefined && callback != null)
					callback();	
			}
		});
  }
  
  function fn_management_hazard_identification_read_hazard_item_list_children(id, level, mode, callback){ 
	  //alert(level);
		$.ajax({
			type: "POST",
			url: "<c:url value='/getHazardItemListChildren.do' />",
			data: 'level='+level+'&parent_id='+id,
			success: function(msg){
				var Result = msg;
				Result = jQuery.trim(Result);
				var platforms = Result.split(",");
				var selectItem = document.getElementById('id_management_hazard_identification_level_'+(parseInt(level, 10)+1)+'_selector');
				//Remove all Items
				for(var count = 0 ; count < selectItem.options.length ; count++)
				{
					selectItem.options[count] = null;
					count=count-1;
				}
				
				selectItem.options[0] = new Option('[SELECT LEVEL '+(parseInt(level, 10)+1)+' HAZARD]', 0);
				
				for(var count = 0 ; count < platforms.length ; count++)
				{
					var item = platforms[count].split("_/");
					selectItem.options[selectItem.options.length] = new Option(item[1], item[0]);
				}
				
				if(callback != undefined && callback != null)
					callback();	
				
				fn_management_hazard_identification_disable_hazard_selector_from_level(parseInt(level, 10)+1);
				fn_management_hazard_identification_enable_hazard_selector(parseInt(level, 10)+1);
				
				if(mode == 'load' && level < 4){
					if(itemListJson.length > level)
						selectItem.value = itemListJson[level].item_id;
					else
						selectItem.value = 0;
					
					if(itemListJson.length > level){
						fn_management_hazard_identification_read_hazard_item_list_children(itemListJson[level].item_id, level+1, mode);
					}	
				}else{
					
					  <c:if test="${isreadonly == 'Y'}" >
					  $('input').attr('disabled', 'disabled');
						$('select').attr('disabled', 'disabled');
						$('textarea').attr('disabled', 'disabled');  
					  
						
						
					   </c:if>
					 
					
				}
				
			}
		});
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
<form id="management_hazard_identification_hazard_item_form">
	<input type="hidden" name="hazard_no" value="${hazard.hazard_no}" />
	<input type="hidden" name="report_no" value="${report_no}" />
	<input type="hidden" name="report_parent_no" value="${report_parent_no}" />
	<input type="hidden" name="report_item_type" value="${report_item_type}" />
	
<div class="ui-widget-content" style="padding: 5px;">
<table width="100%">
<tr>
<td id="id_management_detail_main_right_panel" width="70%">
	    
	    
	    
	    
	    <table cellspacing="0" width="100%" style="padding-right:5px;">
		<tbody >
		<tr>
			<td align="left" width="15%">${lang.getStringHazard()} ${lang.getStringTitle()}: </td>
			<td align="left" width="30%"><input style="width:100%" type="text" id="id_management_hazard_identification_hazard_title" name="hazard_title" class="form_input_text" value="${hazard.title }"/></td>
			<td align="right" width="20%">${lang.getStringHazardNo()}: </td>
			<td width="30%"><input style="width:100%" type="text" id="id_management_hazard_identification_hazard_no" class="form_input_text" disabled="disabled" value="${hazard.hazard_no}"/></td>
		</tr>
		</tbody>
		</table>
	    
	    <fieldset>
	    <legend>${lang.getStringHazard()}</legend>
	    
	    <table cellspacing="0" width="100%">
		<tbody>
		
		<tr>
			<td class="leftmost_label">Level1: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_level_1_selector" onchange="fn_management_hazard_identification_read_hazard_item_list_children(this.value,1, 'change');" name="hazard_item_lv1" class="hazard_item_selector">		
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">Level2: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_level_2_selector" onchange="fn_management_hazard_identification_read_hazard_item_list_children(this.value,2, 'change');" name="hazard_item_lv2" class="hazard_item_selector">
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">Level3: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_level_3_selector" onchange="fn_management_hazard_identification_read_hazard_item_list_children(this.value,3, 'change');" name="hazard_item_lv3" class="hazard_item_selector">
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">Level4: </td>
			<td><select style="width:100%"  id="id_management_hazard_identification_level_4_selector" onchange="fn_management_hazard_identification_read_hazard_item_list_children(this.value,4, 'change');" name="hazard_item_lv4" class="hazard_item_selector">
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">Level5: </td>
			<td><select style="width:100%"  id="id_management_hazard_identification_level_5_selector" name="hazard_item_lv5" class="hazard_item_selector">
			</select> </td>
		</tr>
		</tbody>
		</table>
		 <table cellspacing="0" width="100%">
		<tbody>
		<tr>
			<td class="leftmost_label">Details: </td>
			<td><input style="width:100%" type="text" id="id_management_hazard_identification_details_text" name="hazard_details" class="form_input_text" value="${hazard.details}"/> </td>
			<td align="right"><input type="checkbox" name="hazard_new" id="id_management_hazard_identification_detail_new" ${hazard.isnew == "New" ? "checked=\"checked\"" : ""} value="New"/> New</td>
		</tr>
		</tbody>
		</table>
	    
	    </fieldset>
	    <fieldset>
	    <legend>Occurrences &amp; Concequences</legend>
	    <table cellspacing="0" width="100%">
		<tbody>
		<tr>
			<td class="leftmost_label">Occurrence: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_occurrence_selector" name="hazard_occurrence" class="hazard_item_selector">
				<option value="0">select</option>	
				<c:forEach items="${occurrenceList}" var="selectItem" varStatus="list_status">
	 			<option value="${selectItem.value }" ${hazard.ocurrence == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option>
	 			</c:forEach>
			</select> 
			</td>
		</tr>
		<tr>
			<td class="leftmost_label">${lang.getStringInjury()}: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_injury_selector"  name="hazard_injury" class="hazard_item_selector">
			<option value="0">select</option>	
				<c:forEach items="${injuryList}" var="selectItem" varStatus="list_status">
	 			<option value="${selectItem.value }" ${hazard.injury == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option>
	 			</c:forEach>
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">${lang.getStringDamage()}: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_damage_selector"  name="hazard_damage" class="hazard_item_selector">
				<option value="0">select</option>	
				<c:forEach items="${damageList}" var="selectItem" varStatus="list_status">
	 			<option value="${selectItem.value }" ${hazard.damage == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option>
	 			</c:forEach>
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">${lang.getStringDelay()}: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_delay_selector"  name="hazard_delay" class="hazard_item_selector">
			<option value="0">select</option>	
				<c:forEach items="${delayList}" var="selectItem" varStatus="list_status">
	 			<option value="${selectItem.value }" ${hazard.delay == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option>
	 			</c:forEach>
			</select> </td>
		</tr>
		<tr>
			<td class="leftmost_label">${lang.getStringPriority()}: </td>
			<td><select style="width:100%" id="id_management_hazard_identification_priority_selector"  name="hazard_priority" class="hazard_item_selector">
			<option value="0">select</option>	
				<c:forEach items="${priorityList}" var="selectItem" varStatus="list_status">
	 			<option value="${selectItem.value }" ${hazard.priority == selectItem.value ? "selected=\"selected\"" : ""}>${selectItem.name}</option>
	 			</c:forEach>
			</select> </td>
		</tr>
		</tbody>
		</table>
	    </fieldset>
	    
	  
	    
	  
	    
	    
	    </td>
	    </tr>
	    
				
	    </table>
	    
	    
	    
		
	    
	    
	    
	    
	    </div>

 </form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" %>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Hazard Management System - Report hazard_identification</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/themes/base/jquery-ui.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/css/ui.jqgrid.css" />
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/jquery-1.9.1.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/ui/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery.jqGrid-4.4.5/js/jquery.jqGrid.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <script>
  $(function() {
	 	  
	$("#id_management_hazard_identification_${category}_${type}_edit_btn")
	  .button({icons: {secondary: "ui-icon-document" } })
	  .click(function( event ) {
	   event.preventDefault();
	  });
	$("#id_management_hazard_identification_${category}_${type}_edit_btn").hide();
	  $("#id_management_hazard_identification_${category}_${type}_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_hazard_identification_${category}_${type}_delete_btn")
	  .button({icons: {secondary: "ui-icon-trash" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_hazard_identification_${category}_${type}_submit_btn")
	  .button({icons: {secondary: "ui-icon-check" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   var str = $("#management_hazard_identification_report_form").serialize();
		  // alert(str);
		   
		   $.ajax({
			    type:"post",
			    data:str+'&report_no=${report_no}&type=${type}',
			    url:"managementDetailHazardIdentificationSubmit.do",
			    async: false,
			    success: function(msg){
			       alert(msg);
			       $("#id_management_hazard_identification_menu_${type}_${category}").attr("class", "ui-icon ui-icon-bullet");
			       $("#id_management_hazard_identification_${category}_${type}_submitted_btn").show();
			 	   $("#id_management_hazard_identification_${category}_${type}_submit_btn").hide();
			    }
			});
	});
	  
	  $("#id_management_hazard_identification_${category}_${type}_submitted_btn")
	  .button({icons: {secondary: "ui-icon-locked" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_hazard_identification_${category}_${type}_submitted_btn").hide();
	  
	  $("#id_management_hazard_identification_${category}_${type}_previous_btn")
	  .button({icons: {primary: "ui-icon-circle-triangle-w" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  $("#id_management_hazard_identification_${category}_${type}_next_btn")
	  .button({icons: {secondary: "ui-icon-circle-triangle-e" } })
	  .click(function( event ) {
	   event.preventDefault();
	});
	  
	 
	  <c:if test="${reportItem.status_hazard_id == 'SUBMITTED' || isreadonly == 'Y'}" >
	  $("#id_management_hazard_identification_${category}_${type}_submitted_btn").show();
	  $("#id_management_hazard_identification_${category}_${type}_submit_btn").hide();
	  $("#id_management_hazard_identification_${category}_${type}_save_btn").hide();
	  $("#id_management_hazard_identification_${category}_${type}_delete_btn").hide();
	  
	  $('input').attr('disabled', 'disabled');
	  $('select').attr('disabled', 'disabled');
	  $('textarea').attr('disabled', 'disabled');
	</c:if>
	
	<c:if test="${isreadonly == 'Y'}" >
	  $("#id_management_hazard_identification_${category}_${type}_submitted_btn").hide();
	  $("#id_management_hazard_identification_${category}_${type}_submit_btn").hide();
	  $("#id_management_hazard_identification_${category}_${type}_delete_btn").hide();
	  $("#id_management_hazard_identification_${category}_${type}_save_btn").hide();
	 </c:if>
	  //fn_management_hazard_identification_read_hazard_item_list_top();
	   //fn_management_hazard_identification_disable_hazard_selector_from_level(1);
	   
	   //fn_management_hazard_identification_load_hazard_item();
	  
	  //management_hazard_identification_load_attached_file_item();
	  
	  
	  
	 
  });
  
  function fn_management_hazard_identification_reset_hazard_identification(){
	  fn_management_hazard_identification_read_hazard_item_list_top();
	  fn_management_hazard_identification_disable_hazard_selector_from_level(1);
  }
  
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
  
  function fn_management_hazard_identification_enable_description_of_new_hazard(val){
	  if (val == 'y'){
		  $('#id_management_hazard_identification_description_of_new_hazard').removeAttr("disabled");
	  }else if( val == 'n'){
		  $('#id_management_hazard_identification_description_of_new_hazard').attr("disabled", true);
	  }
	  
	 
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
  
  function fn_management_hazard_identification_read_hazard_item_list_children(id, level, callback){ 
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
				
				selectItem.options[0] = new Option('[SELECT LEVEL '+(parseInt(level, 10)+1)+' HAZARD]', '');
				
				for(var count = 0 ; count < platforms.length ; count++)
				{
					var item = platforms[count].split("_/");
					selectItem.options[selectItem.options.length] = new Option(item[1], item[0]);
				}
				if(callback != undefined && callback != null)
					callback();	
				
				fn_management_hazard_identification_disable_hazard_selector_from_level(parseInt(level, 10)+1);
				fn_management_hazard_identification_enable_hazard_selector(parseInt(level, 10)+1);
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
<form id="management_hazard_identification_report_form">
<table width="100%"> 
<tr>
<td>
<div class="ui-widget-header">${lang.getStringReportNo()} : ${report_no }</div>
</td>
<td align="right">Priority : <span style="background-color: ${priorityColorMap[priorityNameMap[reportItem.priority]]}; padding:3px;">${priorityNameMap[reportItem.priority]}</span></td>
</tr>
</table>
<div class="ui-widget-content" style="padding: 5px;">
<table width="100%">
<tr>
<td id="id_management_detail_main_right_panel" width="70%">
	<fieldset >
	    <legend>${lang.getStringTitle()} : ${reportItem.title }</legend>
	    <div class="ui-widget-header">${lang.getStringPilot()}</div>
	    <div class="ui-widget-content" style="padding: 5px;">
	    <table width="100%">
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringNarrative()}</legend>
	    <textarea  readonly="readonly" rows="3" style="width:100%" id="id_management_hazard_identification_narrative">${reportItem.narrative}</textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>${lang.getStringRecommendation()}</legend>
	    <textarea readonly="readonly" rows="3" style="width:100%" id="id_management_hazard_identification_recommendation">${reportItem.recommendation}</textarea>
	    </fieldset>
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <iframe id="${report_item_type}_attached_file_table" src="${pageContext.request.contextPath}/fileUploadForm.do?report_no=${reportItem.report_no}&report_item_type=${reportItem.type}&isReadOnly=Y" width="100%" style="height:180px;padding:0px;border:0px;" >
	    </iframe>    
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <fieldset>
	    <legend>Comments</legend>
	    <textarea rows="3" style="width:100%" id="id_management_hazard_identification_comments">${reportItem.comments}</textarea>
	    </fieldset>
	    
	    </td>
	    </tr>
	    
	    <tr>
	    <td>
	    <iframe id="${report_item_type}_hazard_item_table" src="${pageContext.request.contextPath}/hazardView.do?report_no=${report_no}&report_parent_no=${report_parent_no}&hazard_index=1&report_item_type=${type}&hazard_no=${hazard_no}&isreadonly=${isreadonly}&state_hazard_id=${reportItem.status_hazard_id}" width="100%" style="height:460px;padding:0px;border:0px;" >
	    </iframe>
	     
	    
	    
	    </td>
	    </tr>
	    
	    <tr>
			<td align="center">
			<a id="id_management_hazard_identification_${category}_${type}_edit_btn" href="#">${lang.getStringEdit()}</a> <a id="id_management_hazard_identification_${category}_${type}_save_btn" href="#">${lang.getStringSave()}</a> 
			<a id="id_management_hazard_identification_${category}_${type}_delete_btn" href="#">${lang.getStringDelete()}</a> <a id="id_management_hazard_identification_${category}_${type}_submit_btn" href="#">${lang.getStringSubmit()}</a> <a id="id_management_hazard_identification_${category}_${type}_submitted_btn" href="#">${lang.getStringSubmitted()}</a>
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
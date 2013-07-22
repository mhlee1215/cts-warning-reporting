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
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/jquery-1.9.1.js"></script>
  <script src="${pageContext.request.contextPath}/jquery/jquery-ui-1.10.2/ui/jquery-ui.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <script>
  
  var cur_tab = eval('${hazard_index}');
  var cur_max = eval('${hazardNum}');

  var max_tab = 10;
  $(function() {
	  
	  
	  $("#div_1").show();
	  $("#div_1").load("${pageContext.request.contextPath}/hazardViewContent.do?report_no=${report_no}&report_parent_no=${report_parent_no}&hazard_index="+(cur_tab)+"&report_item_type=${report_item_type}");
//$("#id_management_detail_main_right_panel").load("${pageContext.request.contextPath}/reportBASIC.do?isReadOnly=Y&report_no="+report_no);

	  $("#id_management_hazard_identification_save_btn")
	  .button({icons: {secondary: "ui-icon-disk" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   var str = $("#management_hazard_identification_hazard_item_form").serialize();
	    //alert(str);
	   
	    $.ajax({
		    type:"post",
		    data:str,
		    url:"hazardViewContentUpdate.do",
		    async: false,
		    success: function(msg){
		       alert(msg);
		    	
		    }
		});
	   
	  });

	  $("#id_management_hazard_identification_previous_btn")
	  .button({icons: {primary: "ui-icon-circle-triangle-w" } })
	  .click(function( event ) {
	   event.preventDefault();
	   
	   $("#div_1").load("${pageContext.request.contextPath}/hazardViewContent.do?report_no=${report_no}&report_parent_no=${report_parent_no}&hazard_index="+(cur_tab-1)+"&report_item_type=${report_item_type}");
	    
	  // $("#div_"+(cur_tab)).hide();
	   //$("#div_"+(cur_tab)).empty();
	   
	   //$("#div_"+(cur_tab-1)).show();
	   
	   /*
	   $("#div_"+(cur_tab-1)).show(effect_type, {
		   direction:"left",
		   duration: 200,
		   complete : function(){
			   //alert('hi');
			   //$("#div_"+(cur_tab-1)).show();//.load("${pageContext.request.contextPath}/hazardViewContent.do?report_no=RP200713KE1203-2&report_parent_no=R200713KE1203&hazard_index=1&report_item_type=EN-ROUTE");	   
		   }
	   });
	   */
	   
	   cur_tab -= 1;
	   $("#legend_harazrd_identification_title").text('${lang.getStringHazardIdentification()} ('+cur_tab+"/"+cur_max+')');
	   adjustBtn(cur_tab, cur_max);
	});
	  $("#id_management_hazard_identification_next_btn")
	  .button({icons: {secondary: "ui-icon-circle-triangle-e" } })
	  .click(function( event ) {
	   	   event.preventDefault();
	   
	   	$("#div_1").load("${pageContext.request.contextPath}/hazardViewContent.do?report_no=${report_no}&report_parent_no=${report_parent_no}&hazard_index="+(cur_tab+1)+"&report_item_type=${report_item_type}");
	   	   
		   //$("#div_"+(cur_tab)).hide();
		   //$("#div_"+(cur_tab)).empty();
		   
		   //$("#div_"+(cur_tab+1)).show();
	  
	   if(cur_tab == cur_max){
		   cur_max = cur_max + 1;	
	   }
	   
	   
	   cur_tab += 1;
	   $("#legend_harazrd_identification_title").text('${lang.getStringHazardIdentification()} ('+cur_tab+"/"+cur_max+')');
	   adjustBtn(cur_tab, cur_max);
	   
	   
	});
	  
	 
	 
	  
	  
	  adjustBtn(cur_tab, cur_max);
  });
  
 
  function adjustBtn(tab_idx, current_max){
	  if(tab_idx == current_max){
		  $("#id_management_hazard_identification_next_btn").button({label:"${lang.getStringMore()}", icons: {secondary: "ui-icon-circle-plus" }});  
	  }else{
		  $("#id_management_hazard_identification_next_btn").button({label:"${lang.getStringNext()}", icons: {secondary: "ui-icon-circle-triangle-e" }});
	  }
	  
	  if(tab_idx == 1){
		   $("#id_management_hazard_identification_previous_btn").hide();
	   }
	   else if(tab_idx == max_tab){
		   $("#id_management_hazard_identification_next_btn").hide();
	   }
	   else{
		   $("#id_management_hazard_identification_next_btn").show();
		   $("#id_management_hazard_identification_previous_btn").show();
	   }
	   
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
<body style="margin: 0px;">



<table width="100%" cellpadding="0" cellspacing="0">
<tr>
<td id="id_management_detail_main_right_panel" width="70%">
	    
	    
	    <fieldset>
	    <legend id="legend_harazrd_identification_title">${lang.getStringHazardIdentification()} (${hazard_index}/${hazardNum})</legend>
	    
	    
	    <table cellpadding="0" cellspacing="0" width="100%" height="350px;">
		<tr>
		<td><div id="div_1" style="display:none">
			
			</div>
		</td>
		
		</tr>
		</table>
	    
	    <table width="100%">
		<tbody>
		<tr>
			<td align="left"><a id="id_management_hazard_identification_previous_btn" href="#">${lang.getStringPrevious()}</a></td>
			<td align="right"><a id="id_management_hazard_identification_save_btn" href="#">${lang.getStringSave()}</a></td>
			<td align="right"><a id="id_management_hazard_identification_next_btn" href="#">${lang.getStringNext()}</a></td>
		</tr>
		</tbody>
		</table>
	    
	    </fieldset>
	    
	    
	    </td>
	    </tr>
	    
				
	    </table>
	    
	    
	    
		
	    
	    
	    

 
</body>
</html>
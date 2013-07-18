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
  <script src="${pageContext.request.contextPath}/js/jquery.example.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/js/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor_4.1.2_standard/ckeditor.js"></script>
  
  <script src="${pageContext.request.contextPath}/js/fileupload/jquery.fileupload.js"></script>
  <script src="${pageContext.request.contextPath}/js/fileupload/jquery.fileupload-fp.js"></script>
  <script src="${pageContext.request.contextPath}/js/fileupload/jquery.fileupload-ui.js"></script>
  <style>
    
  .l1_fieldset { border:2px solid rgb(40, 40, 40) }
  .l1_fieldset_legend {
	  padding: 0.2em 0.5em;
	  
	  color:rgb(30, 30, 30);
	  font-weight:bold;
	  font-size:150%;
	  text-align:left;
  }
  .l2_fieldset { border:1px solid rgb(60, 60, 60) }
  .l2_fieldset_legend {
	  padding: 0.2em 0.5em;
	  color:rgb(30, 30, 30);
	  font-weight:bold;
	  font-size:120%;
	  text-align:left;
  }
  
  .example{color:#666;}
  
  .attachedTable {
	max-width: 100%;
	background-color: transparent;
	border-collapse: collapse;
	border-spacing: 0;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: 14px;
	line-height: 20px;
	color: #333333;
  }
  </style>
</head>
<script>
  $(function() {
	   
   $("#id_report_${report_item_type}_title").example('ex) Pax door impated airbridege while opening');
   $("#id_report_${report_item_type}_time").timepicker();
  
   CKEDITOR.replace( 'report_${report_item_type}_narrative' );
   CKEDITOR.replace( 'report_${report_item_type}_recommendation' );
   
  });
  
 
 
  
  

  </script>
<body>
<form id="report_${report_item_type}_form" >
<fieldset class="l1_fieldset">
<legend class="l1_fieldset_legend">${report_item_type} ${lang.getStringInformation()}</legend>

<table width="100%" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td width="90">${lang.getStringHazardTitle()}</td>
			<td><input type="text" style="width:100%" name="report_${report_item_type}_title" id="id_report_${report_item_type}_title" value="${reportItem.title}"/></td>
		</tr>
		<tr>
			<td>${lang.getStringTime()}</td>
			<td>
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tbody>
					<tr>
						<td width="10%"><input type="text" style="width:100%; height:12px;" name="report_${report_item_type}_time" id="id_report_${report_item_type}_time" value="${reportItem.time}"/><td>
						<td width="5%" align="right"> <input type="radio" name="report_${report_item_type}_time_type" id="${report_item_type}_new_hazard_no" value="LOCAL" ${reportItem.time_type == "LOCAL" ? "checked=\"checked\"" : ""} /></td>
						<td width="5%" align="left">Local</td>
						<td width="5%" align="right"> <input type="radio" name="report_${report_item_type}_time_type" id="${report_item_type}_new_hazard_yes" value="UTC"  ${reportItem.time_type == "UTC" ? "checked=\"checked\"" : ""}/></td>
						<td width="5%" align="left">UTC</td>
						<td width="80%"></td>
					</tr>
				</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<fieldset class="l2_fieldset">
				<legend class="l2_fieldset_legend">${lang.getStringNarrative()}</legend>
				<textarea id="id_report_${report_item_type}_narrative" name="report_${report_item_type}_narrative" rows="3" style="width:100%">${reportItem.narrative}</textarea>
			</fieldset>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<fieldset class="l2_fieldset">
				<legend class="l2_fieldset_legend">${lang.getStringRecommendation()}</legend>
				<textarea id="id_report_${report_item_type}_recommendation" name="report_${report_item_type}_recommendation" rows="3" style="width:100%">${reportItem.recommendation}</textarea>
			</fieldset>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<fieldset class="l2_fieldset">
				<legend class="l2_fieldset_legend">${lang.getStringAttachment()}</legend>
					
				<iframe id="${report_item_type}_attached_file_table" src="${pageContext.request.contextPath}/fileUploadForm.do?report_no=${report_no}&report_item_type=${report_item_type}" width="100%" style="height:200px;padding:0px;border:0px;" >
				
				</iframe>
				
			</fieldset>
			</td>
		</tr>
	</tbody>
</table>


</fieldset>
</form>
</body>
</html>
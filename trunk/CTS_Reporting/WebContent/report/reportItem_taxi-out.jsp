<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<script>
  $(function() {
   $("#id_more_hazards")
   .button()
   .click(function( event ) {
     event.preventDefault();
   });
   $("#id_save_btn")
   .button()
   .click(function( event ) {
     event.preventDefault();
   });
   $("#id_previous_btn")
   .button()
   .click(function( event ) {
     event.preventDefault();
   });
   $("#id_next_btn")
   .button()
   .click(function( event ) {
     event.preventDefault();
   });
  });
  
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
	<td><select id="id_level_1_selector" name="method" style="width:100%">
				<option value="1">Ex) ENVIRONMENTAL ISSUES</option>
				<option value="0">222</option>
	</select> </td>
</tr>
<tr>
	<td align="right">Level2: </td>
	<td><select id="id_level_1_selector" name="method" style="width:100%">
				<option value="1">Ex) ENVIRONMENTAL ISSUES</option>
				<option value="0">222</option>
	</select> </td>
</tr>
<tr>
	<td align="right">Level3: </td>
	<td><select id="id_level_1_selector" name="method" style="width:100%;">
				<option value="1">Ex) ENVIRONMENTAL ISSUES</option>
				<option value="0">222</option>
	</select> </td>
</tr>
<tr>
	<td align="right">Level4: </td>
	<td><select id="id_level_1_selector" name="method" style="width:100%;">
				<option value="1">Ex) ENVIRONMENTAL ISSUES</option>
				<option value="0">222</option>
	</select> </td>
</tr>
<tr>
	<td align="right">Level5: </td>
	<td><select id="id_level_1_selector" name="method" style="width:100%;">
				<option value="1">Ex) ENVIRONMENTAL ISSUES</option>
				<option value="0">222</option>
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
	<td align="right" width="80px;"><a id="id_more_hazards" href="#">More Hazards?</a></td>
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
<table width="80%">
<tbody>
<tr>
	<td align="right"><a id="id_save_btn" href="#">Save</a><a id="id_previous_btn" href="#">Previous</a><a id="id_next_btn" href="#">Next</a></td>
</tr>
</tbody>
</table>
</div>
</body>
</html>
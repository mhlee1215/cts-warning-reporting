<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  
  <script>
  $(function() {
    $("#id_basic_report_date_datepicker").datepicker();
    $("#id_basic_flight_date_datepicker").datepicker();
   
    $("#id_basic_date_of_birth_datapicker").datepicker();
    $("#id_basic_date_of_last_medical_datapicker").datepicker();
    $("#id_basic_date_last_inspection_datepicker").datepicker();
    
        
    $("#id_basic_auto_fill_btn")
          .button()
          .click(function( event ) {
            event.preventDefault();
          });
    $("#id_basic_edit_btn")
    .button()
    .click(function( event ) {
     event.preventDefault();
    });
    $("#id_basic_cancel_btn")
    .button()
    .click(function( event ) {
     event.preventDefault();
    });
    $("#id_basic_print_btn")
    .button()
    .click(function( event ) {
     event.preventDefault();
    });
    $("#id_basic_submit_btn")
    .button()
    .click(function( event ) {
     event.preventDefault();
    });
    $("#id_basic_save_btn")
    .button()
    .click(function( event ) {
     event.preventDefault();
    });
    $("#id_basic_next_btn")
    .button()
    .click(function( event ) {
	 changeTab(2);
     event.preventDefault();
  });
   
   
  });
  
  function auto_fill(){
	    $("#id_basic_report_date_datepicker").attr("value", "04/26/2013");
	    $("#id_basic_flight_date_datepicker").attr("value", "04/25/2013");
	    $("#id_basic_flight_no").attr("value", "KE1234");
	  	$("#id_basic_air_line").attr("value", "7C");
	  	$("#id_basic_route_from").attr("value", "GMP");
	  	$("#id_basic_route_to").attr("value", "KIX");
	  	$("#id_basic_route_diverted").attr("value", "ITM");
	  	$("#id_basic_no_of_flight_crew").attr("value", "2");
	  	$("#id_basic_no_of_cabin_crew").attr("value", "7");
	  	$("#id_basic_no_of_passengers").attr("value", "112");
	  	
	  	
	  	$("#id_basic_id_basic_number").attr("value", "P12345");
	  	$("#id_basic_name").attr("value", "Gil-Dong Hong");
	  	$("#id_basic_date_of_birth_datapicker").attr("value", "06/11/1965");
	  	$("#id_basic_date_of_last_medical_datapicker").attr("value", "05/12/13");
	  	$("#id_basic_total_time").attr("value", "1540");
	  	$("#id_basic_this_mask_model").attr("value", "890");
	  	
	  	
	  	$("#id_basic_serial_number").attr("value", "51-11012");
	  	$("#id_basic_registration_number").attr("value", "HL7229");
	  	$("#id_basic_flight_crew").attr("value", "2");
	  	$("#id_basic_cabin_crew").attr("value", "8");
	  	$("#id_basic_passengers").attr("value", "150");
	  	$("#id_basic_date_last_inspection_datepicker").attr("value", "06/12/2012");
	  	
	  	$("#id_basic_flight_crew_no_of_fatalities").attr("value", "0");
	  	$("#id_basic_flight_crew_no_of_injuries").attr("value", "0");
	  	$("#id_basic_cabin_crew_no_of_fatalities").attr("value", "0");
	  	$("#id_basic_cabin_crew_no_of_injuries").attr("value", "0");
	  	$("#id_basic_passengers_no_of_fatalities").attr("value", "0");
	  	$("#id_basic_passengers_no_of_injuries").attr("value", "0");
	  	
	  	
	  	$("#id_basic_flight_type_selector").val("1");
	  	$("#id_basic_dom_or_int_selector").val("1");
	  	$("#id_basic_cargo_oper_selector").val("1");
	  	$("#id_basic_medical_certification_selector").val("1");
	  	$("#id_basic_manufacturer_selector").val("1");
	  	$("#id_basic_model_selector").val("12");
	  	$("#id_basic_last_inspection_type_selector").val("1");
	  	$("#id_basic_damage_selector").val("1");
	  	$("#id_basic_fire_selector").val("1");
	  	$("#id_basic_explosion_selector").val("1");
  }
  </script>
</head>
<body>

<h2 class="ui-widget-header">BASIC INFORMATION</h2>
<div class="ui-widget-content">
<table width="100%">
<tbody>
<tr>
<td class="leftmost_label">
Reporting Date: </td><td><input type="text" id="id_basic_report_date_datepicker" class="form_input_text"/>
</td>
<td align="right"><a id="id_basic_auto_fill_btn" href="#" onclick="auto_fill();">Auto Fill</a>
</td>
</tr>
</tbody>
</table>
<h3 class="ui-widget-header">FLIGHT INFORMATION</h3>
<table>
<tbody>
<tr>
	<td class="leftmost_label">Flight Date:</td>
	<td><input type="text" id="id_basic_flight_date_datepicker" class="form_input_text" /></td>
	<td width="100px;" align="right">Flight No.:</td>
	<td><input type="text" id="id_basic_flight_no" class="form_input_text"/></td>
</tr>
<tr>
	<td class="leftmost_label">Airline:</td>
	<td><input type="text" id="id_basic_air_line" class="form_input_text" /></td>
	<td></td>
	<td></td>
</tr>

</tbody>
</table>
<table>
<tbody>
<tr>
	<td class="leftmost_label">Route:</td>
	<td><input type="text" id="id_basic_route_from" class="form_input_text" /></td>
	<td>to</td>
	<td><input type="text" id="id_basic_route_to" class="form_input_text"/></td>
	<td>/ Diverted:</td>
	<td><input type="text" id="id_basic_route_diverted" class="form_input_text"/></td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
	<td class="leftmost_label">Flight Type:</td>
	<td><select id="id_basic_flight_type_selector" name="method" class="form_selector">
				<option value="0">select</option>	
				<option value="1">SCHEDULED</option>
				<option value="2">NON-SCHEDULED</option>
			</select></td>
</tr>
</tbody>
</table>  

<table>
<tbody>
<tr>
	<td class="leftmost_label">Domestic or International:</td>
	<td><select id="id_basic_dom_or_int_selector" name="method" class="form_selector">
				<option value="0">select</option>	
				<option value="1">INTERNATIONAL</option>
				<option value="2">DOMESTIC</option>
			</select></td>
	<td style="width:100px;text-align:right;">Cargo Operation:</td>
	<td><select id="id_basic_cargo_oper_selector" name="method" class="form_selector">
				<option value="0">select</option>	
				<option value="1">PASSENGER/CARGO</option>
				<option value="2">PASSENGER</option>
				<option value="3">CARGO</option>
				<option value="4">MAIL</option>
			</select></td>
</tr>
</tbody>
</table> 

<table>
<tbody>
<tr>
	<td class="leftmost_label">No. of Flight Crew:</td>
	<td><input type="text" id="id_basic_no_of_flight_crew" class="form_input_text"/></td>
	<td style="width:100px;text-align:right;">No. of Cabin Crew:</td>
	<td><input type="text" id="id_basic_no_of_cabin_crew" class="form_input_text"/></td>
	<td style="width:100px;text-align:right;">No. of Passengers:</td>
	<td><input type="text" id="id_basic_no_of_passengers" class="form_input_text"/></td>
</tr>
</tbody>
</table> 
  
<h3 class="ui-widget-header">PERSONAL INFORMATION</h3>
<table>
<tbody>
<tr>
	<td class="leftmost_label">ID Number:</td>
	<td><input type="text" id="id_basic_id_basic_number" class="form_input_text" /></td>
	<td style="width:100px;text-align:right;">Name:</td>
	<td><input type="text" id="id_basic_name" class="form_input_text"/></td>
	<td style="width:100px;text-align:right;">Date of Birth:</td>
	<td><input type="text" id="id_basic_date_of_birth_datapicker" class="form_input_text"/></td>
</tr>
</tbody>
</table> 
<table>
<tbody>
<tr>
	<td class="leftmost_header1" colspan="4"><span style="margin-left:10px;">Medical Condition</span></td>
</tr>
<tr>
	<td class="leftmost_label">Medical Certification:</td>
	<td><select id="id_basic_medical_certification_selector" name="method" class="form_selector">
				<option value="0">select</option>	
				<option value="1">NONE</option>
				<option value="2">CLASS1</option>
				<option value="3">CLASS2</option>
				<option value="4">CLASS3</option>
				<option value="5">UNKNOWN</option>
			</select></td>
	<td style="width:135px;text-align:right;">Date of Last Medical:</td>
	<td><input type="text" id="id_basic_date_of_last_medical_datapicker" class="form_input_text"/></td>
</tr>
<tr>
	<td class="leftmost_header1" colspan="4"><span style="margin-left:10px;">Flight Time (hours)</span></td>
</tr>
<tr>
	<td class="leftmost_label">Total Time:</td>
	<td><input type="text" id="id_basic_total_time" class="form_input_text"/></td>
	<td style="width:135px;text-align:right;">This Mask/Model:</td>
	<td><input type="text" id="id_basic_this_mask_model" class="form_input_text"/></td>
</tr>
</tbody>
</table> 
			
<h3 class="ui-widget-header">AIRCRAFT INFORMATION</h3>
<table>
<tbody>
<tr>
	<td class="leftmost_label">Manufacturer:</td>
	<td><select id="id_basic_manufacturer_selector" name="method" class="form_selector">
				<option value="0">select</option>	
				<option value="1">BOEING</option>
				<option value="2">AIRBUS</option>
			</select></td>
	<td style="width:135px;text-align:right;">Model:</td>
	<td><select id="id_basic_model_selector" name="method" class="form_selector">
				<option value="0">select</option>	
				<option value="1">A300-600</option>
				<option value="2">A320-200</option>
				<option value="3">A321-100</option>
				<option value="4">A321-200</option>
				<option value="5">A330-200</option>
				<option value="6">A330-300</option>
				<option value="7">A380-800</option>
				<option value="8">B737-400</option>
				<option value="9">B737-500</option>
				<option value="10">B737-600</option>
				<option value="11">B737-700</option>
				<option value="12">B737-800</option>
				<option value="13">B737-900</option>
				<option value="14">B737-900ER</option>
				<option value="15">B747-400</option>
				<option value="16">B767-300</option>
				<option value="17">B777-200ER</option>
				<option value="18">B777-300</option>
				<option value="19">B777-300ER</option>
				<option value="2">B747-400 Combi</option>
				<option value="21">B747-400SF</option>
				<option value="22">B747-400F</option>
				<option value="23">B747-8F</option>
				<option value="24">B767-300F</option>
				<option value="25">B777F</option>
			</select></td>
</tr>
<tr>
	<td class="leftmost_label">Serial Number:</td>
	<td><input type="text" id="id_basic_serial_number" class="form_input_text"/></td>
	<td style="width:135px;text-align:right;">Registration Number:</td>
	<td><input type="text" id="id_basic_registration_number" class="form_input_text"/></td>
</tr>
</tbody>
</table> 
 
<table>
<tbody>
<tr>
	<td class="leftmost_header1" colspan="6"><span style="margin-left:10px;">No. of Seats</span></td>
</tr>
<tr>
	<td class="leftmost_label">Flight Crew:</td>
	<td><input type="text" id="id_basic_flight_crew" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">Cabin Crew:</td>
	<td><input type="text" id="id_basic_cabin_crew" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">Passengers:</td>
	<td><input type="text" id="id_basic_passengers" class="form_input_text"/></td>
</tr>
</tbody>
</table> 

<table>
<tbody>
<tr>
	<td class="leftmost_label">Last Inspection Type:</td>
	<td><select id="id_basic_last_inspection_type_selector" name="method" class="form_selector">
				<option value="0">select</option>	
				<option value="1">100HOUR</option>
				<option value="2">AAIP</option>
				<option value="3">ANNUAL</option>
				<option value="4">CONTINUOUS AIRWORTHINESS</option>
				<option value="5">CONDITIONAL INSPECTION</option>
				<option value="6">UNKNOWN</option>
			</select></td>
	<td style="width:130px;text-align:right;">Date Last Inspection:</td>
	<td><input type="text" id="id_basic_date_last_inspection_datepicker" class="form_input_text"/></td>
</tr>
</tbody>
</table> 	
					
<h3 class="ui-widget-header">Injury/Damage Report</h3>
<table>
<tbody>
<tr>
	<td class="leftmost_header1" colspan="5"><span style="margin-left:10px;">Degree of Injury</span></td>
</tr>
<tr>
	<td class="leftmost_label2">Flight Crew</td>
	<td style="width:130px;text-align:right;">No. of Fatalities:</td>
	<td><input type="text" id="id_basic_flight_crew_no_of_fatalities" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">No. of Injuries:</td>
	<td><input type="text" id="id_basic_flight_crew_no_of_injuries" class="form_input_text"/></td>
</tr>
<tr>
	<td class="leftmost_label2">Cabin Crew</td>
	<td style="width:130px;text-align:right;">No. of Fatalities:</td>
	<td><input type="text" id="id_basic_cabin_crew_no_of_fatalities" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">No. of Injuries:</td>
	<td><input type="text" id="id_basic_cabin_crew_no_of_injuries" class="form_input_text"/></td>
</tr>
<tr>
	<td class="leftmost_label2">Passengers</td>
	<td style="width:130px;text-align:right;">No. of Fatalities:</td>
	<td><input type="text" id="id_basic_passengers_no_of_fatalities" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">No. of Injuries:</td>
	<td><input type="text" id="id_basic_passengers_no_of_injuries" class="form_input_text"/></td>
</tr>
</tbody>
</table> 

<table>
<tbody>
<tr>
	<td class="leftmost_header1" colspan="6"><span style="margin-left:10px;">Aircraft Damages</span></td>
</tr>
<tr>
	<td class="leftmost_label">Damage:</td>
	<td><select id="id_basic_damage_selector" name="method" class="form_selector">
				<option value="0">select</option>	
				<option value="1">NONE</option>
				<option value="2">MINOR</option>
				<option value="3">SUBSTANTIAL</option>
				<option value="4">DESTROYED</option>
			</select></td>
	<td style="width:70px;text-align:right;">Fire:</td>
	<td><select id="id_basic_fire_selector" name="method" class="form_selector">
				<option value="0">select</option>	
				<option value="1">NONE</option>
				<option value="2">IN-FLIGHT</option>
				<option value="3">ON-GROUND</option>
				<option value="4">BOTH GROUND AND IN-FLIGHT</option>
				<option value="5">UNKNOWN ORIGIN</option>
			</select></td>
	<td style="width:70px;text-align:right;">Explosion:</td>
	<td><select id="id_basic_explosion_selector" name="method" class="form_selector">
				<option value="0">select</option>					
				<option value="1">NONE</option>
				<option value="2">IN-FLIGHT</option>
				<option value="3">ON-GROUND</option>
				<option value="4">BOTH GROUND AND IN-FLIGHT</option>
				<option value="5">UNKNOWN ORIGIN</option>
			</select></td>
</tr>
</tbody>
</table>
<br>
			 
			
			 
<table width="100%">
	<tbody>
		<tr>
			<td align="right"><a id="id_basic_edit_btn" href="#">Edit</a><a id="id_basic_cancel_btn" href="#">Cancel</a><a id="id_basic_print_btn" href="#">Print</a><a id="id_basic_submit_btn" href="#">Submit</a><a id="id_basic_save_btn" href="#">Save</a> <a id="id_basic_next_btn" href="#">Next</a></td>
		</tr>
	</tbody>
</table>
</div>
</body>
</html>
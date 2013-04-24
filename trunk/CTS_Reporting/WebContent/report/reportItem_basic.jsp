<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <style>

  </style>
  <script>
  $(function() {
    $("#id_report_date_datepicker").datepicker();
    $("#id_flight_date_datepicker").datepicker();
   
    $("#id_date_of_birth_datapicker").datepicker();
    $("#id_date_of_last_medical_datapicker").datepicker();
    $("#id_date_last_inspection_datepicker").datepicker();
    
        
    $("#id_auto_fill_btn")
          .button()
          .click(function( event ) {
            event.preventDefault();
          });
    $("#id_save_btn")
    .button()
    .click(function( event ) {
     event.preventDefault();
    });
    $("#id_next_btn")
    .button()
    .click(function( event ) {
	 changeTab(2);
     event.preventDefault();
  });
   
   
  });
  
  function auto_fill(){
	    $("#id_report_date_datepicker").attr("value", "04/26/2013");
	    $("#id_flight_date_datepicker").attr("value", "04/25/2013");
	    $("#id_flight_no").attr("value", "KE1234");
	  	$("#id_air_line").attr("value", "7C");
	  	$("#id_route_from").attr("value", "GMP");
	  	$("#id_route_to").attr("value", "KIX");
	  	$("#id_route_diverted").attr("value", "ITM");
	  	$("#id_no_of_flight_crew").attr("value", "2");
	  	$("#id_no_of_cabin_crew").attr("value", "7");
	  	$("#id_no_of_passengers").attr("value", "112");
	  	
	  	
	  	$("#id_id_number").attr("value", "P12345");
	  	$("#id_name").attr("value", "Gil-Dong Hong");
	  	$("#id_date_of_birth_datapicker").attr("value", "06/11/1965");
	  	$("#id_date_of_last_medical_datapicker").attr("value", "05/12/13");
	  	$("#id_total_time").attr("value", "1540");
	  	$("#id_this_mask_model").attr("value", "890");
	  	
	  	
	  	$("#id_serial_number").attr("value", "51-11012");
	  	$("#id_registration_number").attr("value", "HL7229");
	  	$("#id_flight_crew").attr("value", "2");
	  	$("#id_cabin_crew").attr("value", "8");
	  	$("#id_passengers").attr("value", "150");
	  	$("#id_date_last_inspection_datepicker").attr("value", "06/12/2012");
	  	
	  	$("#id_flight_crew_no_of_fatalities").attr("value", "0");
	  	$("#id_flight_crew_no_of_injuries").attr("value", "0");
	  	$("#id_cabin_crew_no_of_fatalities").attr("value", "0");
	  	$("#id_cabin_crew_no_of_injuries").attr("value", "0");
	  	$("#id_passengers_no_of_fatalities").attr("value", "0");
	  	$("#id_passengers_no_of_injuries").attr("value", "0");
  }
  </script>
</head>
<body>

<h2 class="ui-widget-header">BASIC INFORMATION</h2>
<div class="ui-widget-content">
<table width="100%">
<tbody>
<tr>
<td>
Reporting Date: <input type="text" id="id_report_date_datepicker" />
</td>
<td align="right"><a id="id_auto_fill_btn" href="#" onclick="auto_fill();">Auto Fill</a>
</td>
</tr>
</tbody>
</table>
<h3 class="ui-widget-header">FLIGHT INFORMATION</h3>
Flight Date: <input type="text" id="id_flight_date_datepicker" /> Flight No.: <input type="text" id="id_flight_no" /><br>
Airline: <input type="text" id="id_air_line" /><br>
Route: <input type="text" id="id_route_from" /> to <input type="text" id="id_route_to" /> / Diverted: <input type="text" id="id_route_diverted" /><br>
Flight Type: <select id="id_flight_type_selector" name="method" style="width:120px;">
				<option value="1">SCHEDULED</option>
				<option value="0">NON-SCHEDULED</option>
			</select>
			<br>
Domestic or International: <select id="id_dom_or_int_selector" name="method" style="width:197px;">
				<option value="1">INTERNATIONAL</option>
				<option value="0">DOMESTIC</option>
			</select>
			
			Cargo Operation: <select id="id_cargo_oper_selector" name="method" style="width:197px;">
				<option value="0">PASSENGER/CARGO</option>
				<option value="1">PASSENGER</option>
				<option value="2">CARGO</option>
				<option value="3">MAIL</option>
			</select>
			<br>			
No. of Flight Crew: <input type="text" id="id_no_of_flight_crew" /> No. of Cabin Crew: <input type="text" id="id_no_of_cabin_crew" /> No. of Passengers: <input type="text" id="id_no_of_passengers" /><br>
<h3 class="ui-widget-header">PERSONAL INFORMATION</h3>
ID Number: <input type="text" id="id_id_number" /> Name: <input type="text" id="id_name" /> Date of Birth: <input type="text" id="id_date_of_birth_datapicker" /><br>
Medical Condition<br>
Medical Certification: <select id="id_medical_certification_selector" name="method" style="width:120px;">
				<option value="0">NONE</option>
				<option value="1">CLASS1</option>
				<option value="2">CLASS2</option>
				<option value="3">CLASS3</option>
				<option value="4">UNKNOWN</option>
			</select>
Date of Last Medical: <input type="text" id="id_date_of_last_medical_datapicker" /><br>
Flight Time (hours)<br>
Total Time: <input type="text" id="id_total_time" /> This Mask/Model: <input type="text" id="id_this_mask_model" /><br>
			
<h3 class="ui-widget-header">AIRCRAFT INFORMATION</h3>
Manufacturer: <select id="id_manufacturer_selector" name="method" style="width:197px;">
				<option value="0">BOEING</option>
				<option value="1">AIRBUS</option>
			</select>
			
			Model: <select id="id_model_selector" name="method" style="width:197px;">
				<option value="0">A321</option>
				<option value="1">A330</option>
				<option value="2">A380</option>
				<option value="3">B737-XXX</option>
				<option value="4">B767-XXX</option>
				<option value="5">B777-XXX</option>
				<option value="6">B747-XXX</option>
			</select>
			<br>
Serial Number: <input type="text" id="id_serial_number" /> Registration Number: <input type="text" id="id_registration_number" /><br>
No. of Seats<br>
Flight Crew: <input type="text" id="id_flight_crew" /> Cabin Crew: <input type="text" id="id_cabin_crew" /> Passengers: <input type="text" id="id_passengers" /><br>
Last Inspection Type: <select id="id_manufacturer_selector" name="method" style="width:197px;">
				<option value="0">100HOUR</option>
				<option value="1">AAIP</option>
				<option value="2">ANNUAL</option>
				<option value="3">CONTINUOUS AIRWORTHINESS</option>
				<option value="4">CONDITIONAL INSPECTION</option>
				<option value="5">UNKNOWN</option>
			</select>
Date Last Inspection: <input type="text" id="id_date_last_inspection_datepicker" /><br>					
<h3 class="ui-widget-header">Injury/Damage Report</h3>
Degree of Injury<br>
<table width="100%">
	<tbody>
		<tr>
			<td>Flight Crew</td>
			<td>No. of Fatalities: <input type="text" id="id_flight_crew_no_of_fatalities" /></td>
			<td>No. of Injuries: <input type="text" id="id_flight_crew_no_of_injuries" /></td>
		</tr>
		<tr>
			<td>Cabin Crew</td>
			<td>No. of Fatalities: <input type="text" id="id_cabin_crew_no_of_fatalities" /></td>
			<td>No. of Injuries: <input type="text" id="id_cabin_crew_no_of_injuries" /></td>
		</tr>
		<tr>
			<td>Passengers</td>
			<td>No. of Fatalities: <input type="text" id="id_passengers_no_of_fatalities" /></td>
			<td>No. of Injuries: <input type="text" id="id_passengers_no_of_injuries" /></td>
		</tr>
		<tr>
		<td><hr></td>
		<td><hr></td>
		<td><hr></td>
	</tbody>
</table>
Aircraft Damages<br>
			Damage: <select id="id_damage_selector" name="method" style="width:197px;">
				<option value="0">NONE</option>
				<option value="1">MINOR</option>
				<option value="2">SUBSTANTIAL</option>
				<option value="3">DESTROYED</option>
			</select>
			Fire: <select id="id_fire_selector" name="method" style="width:197px;">
				<option value="0">NONE</option>
				<option value="1">IN-FLIGHT</option>
				<option value="2">ON-GROUND</option>
				<option value="3">BOTH GROUND AND IN-FLIGHT</option>
				<option value="4">UNKNOWN ORIGIN</option>
			</select>
			Explosion: <select id="id_explosion_selector" name="method" style="width:197px;">
				<option value="0">NONE</option>
				<option value="1">IN-FLIGHT</option>
				<option value="2">ON-GROUND</option>
				<option value="3">BOTH GROUND AND IN-FLIGHT</option>
				<option value="4">UNKNOWN ORIGIN</option>
			</select>
<table width="100%">
	<tbody>
		<tr>
			<td align="right"><a id="id_save_btn" href="#">Save</a> <a id="id_next_btn" href="#">Next</a></td>
		</tr>
	</tbody>
</table>
</div>
</body>
</html>
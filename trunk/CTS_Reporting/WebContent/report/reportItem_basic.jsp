<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
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
  </style>
  <script>
  var d = new Date();
  var curr_date = d.getDate()+'';
  var curr_month = (d.getMonth() + 1); //Months are zero based
  if(curr_month < 10) curr_month = '0'+curr_month;
  else curr_month = curr_month + '';
  var curr_year = d.getFullYear()+'';
  curr_year = curr_year.substring(2, 4);
  var report_prefix = 'RP'+curr_date+''+curr_month+''+curr_year;
  
  function update_rp_no(){
	  var f_no = $('#id_basic_flight_no').val();
	  $('#id_basic_report_No').attr("value", report_prefix+f_no);
  }
  
  $(function() {
	$('#id_basic_report_No').attr("value", report_prefix);
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
	    $("#id_basic_report_date_datepicker").datepicker('setDate', new Date()).datepicker('hide');
	    //$("#id_basic_report_date_datepicker").attr("value", "04/26/2013");
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
	  	$("#id_basic_delay_time_selector").val("1");
  }
  </script>
</head>
<body>

<fieldset class="l1_fieldset">
<legend class="l1_fieldset_legend">${lang.getStringBasicInformation()}</legend>
<table width="100%">
<tbody>
<tr>
<td>
<table>
<tbody>
<tr>
<td class="leftmost_label">
${lang.getStringReportingDate()}: </td>
<td><input type="text" id="id_basic_report_date_datepicker" class="form_input_text"/>
</td>
<td width="100px;" align="right">
${lang.getStringReportingNo()} </td>
<td><input readonly="readonly" type="text" id="id_basic_report_No" class="form_input_text"/>
</td>
</tr>
</tbody>
</table>
</td>
<td align="right"><a id="id_basic_auto_fill_btn" href="#" onclick="auto_fill();">${lang.getStringAutoFill()}</a>
</td>

</tr>
</tbody>
</table>

<fieldset class="l2_fieldset">
<legend class="l2_fieldset_legend">${lang.getStringFlightInformation()}</legend>
<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringFlightDate()}:</td>
	<td><input type="text" id="id_basic_flight_date_datepicker" class="form_input_text" /></td>
	<td width="100px;"  align="right">${lang.getStringFlightNo()}:</td>
	<td><input type="text" onchange="update_rp_no();" id="id_basic_flight_no" class="form_input_text"/></td>
	<td width="100px;" align="right">${lang.getStringAirline()}:</td>
	<td><input type="text" id="id_basic_air_line" class="form_input_text" /></td>
</tr>

</tbody>
</table>
<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringRoute()}:</td>
	<td><input type="text" id="id_basic_route_from" class="form_input_text" /></td>
	<td width="100px;" align="right">${lang.getStringTo()}</td>
	<td><input type="text" id="id_basic_route_to" class="form_input_text"/></td>
	<td width="100px;" align="right">${lang.getStringDiverted()}:</td>
	<td><input type="text" id="id_basic_route_diverted" class="form_input_text"/></td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringFlightType()}:</td>
	<td><select id="id_basic_flight_type_selector" name="method" class="form_selector">
				<option value="0">${lang.getStringSelect()}</option>	
				<option value="1">SCHEDULED</option>
				<option value="2">NON-SCHEDULED</option>
			</select></td>
</tr>
</tbody>
</table>  

<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringDomesticInternational()}:</td>
	<td><select id="id_basic_dom_or_int_selector" name="method" class="form_selector">
				<option value="0">${lang.getStringSelect()}</option>	
				<option value="1">INTERNATIONAL</option>
				<option value="2">DOMESTIC</option>
			</select></td>
	<td style="width:100px;text-align:right;">${lang.getStringCargoOperation()}:</td>
	<td><select id="id_basic_cargo_oper_selector" name="method" class="form_selector">
				<option value="0">${lang.getStringSelect()}</option>	
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
	<td class="leftmost_label">${lang.getStringNoOfFlightCrew()}:</td>
	<td><input type="text" id="id_basic_no_of_flight_crew" class="form_input_text"/></td>
	<td style="width:100px;text-align:right;">${lang.getStringNoOfCabinCrew()}:</td>
	<td><input type="text" id="id_basic_no_of_cabin_crew" class="form_input_text"/></td>
	<td style="width:100px;text-align:right;">${lang.getStringNoOfPassengers()}:</td>
	<td><input type="text" id="id_basic_no_of_passengers" class="form_input_text"/></td>
</tr>
</tbody>
</table> 
</fieldset>

<fieldset class="l2_fieldset">
<legend class="l2_fieldset_legend">${lang.getStringPersonalInformation()}</legend>
<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringIDNo()}:</td>
	<td><input type="text" id="id_basic_id_basic_number" class="form_input_text" /></td>
	<td style="width:100px;text-align:right;">${lang.getStringName()}:</td>
	<td><input type="text" id="id_basic_name" class="form_input_text"/></td>
	<td style="width:100px;text-align:right;">${lang.getStringDateOfBirth()}:</td>
	<td><input type="text" id="id_basic_date_of_birth_datapicker" class="form_input_text"/></td>
</tr>
</tbody>
</table> 
<table>
<tbody>
<tr>
	<td class="leftmost_label_wide">${lang.getStringMedicalCertification()}:</td>
	<td><select id="id_basic_medical_certification_selector" name="method" class="form_selector">
				<option value="0">${lang.getStringSelect()}</option>	
				<option value="1">NONE</option>
				<option value="2">CLASS1</option>
				<option value="3">CLASS2</option>
				<option value="4">CLASS3</option>
				<option value="5">UNKNOWN</option>
			</select></td>
	<td style="width:135px;text-align:right;">${lang.getStringDateOfLastMedical()}:</td>
	<td><input type="text" id="id_basic_date_of_last_medical_datapicker" class="form_input_text"/></td>
</tr>
</tbody>
</table> 
<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringFlightTime()}:</td>
	<td><input type="text" id="id_basic_total_time" class="form_input_text"/></td>
	<td style="width:135px;text-align:right;">${lang.getStringThisMakeModel()}:</td>
	<td><input type="text" id="id_basic_this_mask_model" class="form_input_text"/></td>
</tr>
</tbody>
</table> 
</fieldset>
<fieldset class="l2_fieldset">
<legend class="l2_fieldset_legend">${lang.getStringAircraftInformation()}</legend>
<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringManufacturer()}:</td>
	<td><select id="id_basic_manufacturer_selector" name="method" class="form_selector">
				<option value="0">${lang.getStringSelect()}</option>	
				<option value="1">BOEING</option>
				<option value="2">AIRBUS</option>
			</select></td>
	<td style="width:135px;text-align:right;">${lang.getStringModel()}:</td>
	<td><select id="id_basic_model_selector" name="method" class="form_selector">
				<option value="0">${lang.getStringSelect()}</option>	
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
				<option value="20">B747-400 Combi</option>
				<option value="21">B747-400SF</option>
				<option value="22">B747-400F</option>
				<option value="23">B747-8F</option>
				<option value="24">B767-300F</option>
				<option value="25">B777F</option>
			</select></td>
</tr>
<tr>
	<td class="leftmost_label">${lang.getStringSerialNumber()}:</td>
	<td><input type="text" id="id_basic_serial_number" class="form_input_text"/></td>
	<td style="width:135px;text-align:right;">${lang.getStringRegistrationNumber()}:</td>
	<td><input type="text" id="id_basic_registration_number" class="form_input_text"/></td>
</tr>
</tbody>
</table> 
 
<table>
<tbody>
<tr>
	<td>${lang.getStringNoOfSeats()}: </td>
	<td class="leftmost_label">${lang.getStringFlightCrews()}:</td>
	<td><input type="text" id="id_basic_flight_crew" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringCabinCrews()}:</td>
	<td><input type="text" id="id_basic_cabin_crew" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringPassengers()}:</td>
	<td><input type="text" id="id_basic_passengers" class="form_input_text"/></td>
</tr>
</tbody>
</table> 

<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringLastInspectionType()}:</td>
	<td><select id="id_basic_last_inspection_type_selector" name="method" class="form_selector">
				<option value="0">${lang.getStringSelect()}</option>	
				<option value="1">100HOUR</option>
				<option value="2">AAIP</option>
				<option value="3">ANNUAL</option>
				<option value="4">CONTINUOUS AIRWORTHINESS</option>
				<option value="5">CONDITIONAL INSPECTION</option>
				<option value="6">UNKNOWN</option>
			</select></td>
	<td style="width:130px;text-align:right;">${lang.getStringDateLastInspection()}:</td>
	<td><input type="text" id="id_basic_date_last_inspection_datepicker" class="form_input_text"/></td>
</tr>
</tbody>
</table> 	
</fieldset>
</fieldset>			

<fieldset class="l2_fieldset">
<legend class="l2_fieldset_legend">${lang.getStringInjury()}/${lang.getStringDamage()}/${lang.getStringDelay()}</legend>
<table>
<tbody>
<tr>
	<td class="leftmost_label2">${lang.getStringFlightCrew()}</td>
	<td style="width:130px;text-align:right;">${lang.getStringFatalities()}:</td>
	<td><input type="text" id="id_basic_flight_crew_no_of_fatalities" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringInjuries()}:</td>
	<td><input type="text" id="id_basic_flight_crew_no_of_injuries" class="form_input_text"/></td>
</tr>
<tr>
	<td class="leftmost_label2">${lang.getStringCabinCrew()}</td>
	<td style="width:130px;text-align:right;">${lang.getStringFatalities()}:</td>
	<td><input type="text" id="id_basic_cabin_crew_no_of_fatalities" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringInjuries()}:</td>
	<td><input type="text" id="id_basic_cabin_crew_no_of_injuries" class="form_input_text"/></td>
</tr>
<tr>
	<td class="leftmost_label2">${lang.getStringPassenger()}</td>
	<td style="width:130px;text-align:right;">${lang.getStringFatalities()}:</td>
	<td><input type="text" id="id_basic_passengers_no_of_fatalities" class="form_input_text"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringInjuries()}:</td>
	<td><input type="text" id="id_basic_passengers_no_of_injuries" class="form_input_text"/></td>
</tr>
</tbody>
</table> 

<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringAircraftDamages()}:</td>
	<td><select id="id_basic_damage_selector" name="method" class="form_selector">
				<option value="0">${lang.getStringSelect()}</option>	
				<option value="1">NONE</option>
				<option value="2">MINOR</option>
				<option value="3">SUBSTANTIAL</option>
				<option value="4">DESTROYED</option>
			</select></td>
		
	<td style="width:70px;text-align:right;">${lang.getStringDelay()}${lang.getStringTime()}:</td>
	<td><select id="id_basic_delay_time_selector" name="method" class="form_selector">
				<option value="0">${lang.getStringSelect()}</option>					
				<option value="1">${lang.getStringNone()}</option>
				<option value="2">${lang.getStringNoDelay()}</option>
				<option value="3">${lang.getStringWithin30Min()}</option>
				<option value="4">${lang.getStringMoreThan1Hour()}</option>
				<option value="5">${lang.getStringFlightCancel()}</option>
				<option value="6">${lang.getStringACChange()}</option>
			</select></td>
</tr>
</tbody>
</table>
</fieldset>
			 

</body>
</html>
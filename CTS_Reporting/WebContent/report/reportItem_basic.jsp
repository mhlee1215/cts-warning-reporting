<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<%@include file="/include_js.jsp"%>
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
	<c:if test="${isReadOnly != 'N'}">
	$('input').attr('disabled', 'disabled');
	$('select').attr('disabled', 'disabled');
	</c:if>
	//$('#id_basic_report_No').attr("value", report_prefix);
	$('#id_basic_report_No').attr("value", '${report_no}');
    $("#id_basic_report_date_datepicker").datepicker( {dateFormat:"yy-mm-dd"} );
    $("#id_basic_flight_date_datepicker").datepicker( {dateFormat:"yy-mm-dd"} );
   
    $("#id_basic_date_of_birth_datapicker").datepicker( {dateFormat:"yy-mm-dd"} );
    $("#id_basic_date_of_last_medical_datapicker").datepicker( {dateFormat:"yy-mm-dd"} );
    $("#id_basic_date_last_inspection_datepicker").datepicker( {dateFormat:"yy-mm-dd"} );
    
        
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
   
    $("#id_basic_report_date_datepicker").datepicker('setDate', new Date()).datepicker('hide');
   
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

<form id="report_item_basic_form" >



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
<td><input type="text" id="id_basic_report_date_datepicker" name="reportingDate" class="form_input_text"/>
</td>
<td width="100px;" align="right">
${lang.getStringReportingNo()} </td>
<td><input readonly="readonly" type="text" id="id_basic_report_No" name="reportingNo" class="form_input_text_long"/>
</td>
</tr>
</tbody>
</table>
</td>
<c:if test="${isReadOnly == 'N'}">
<td align="right"><a id="id_basic_auto_fill_btn" href="#" onclick="auto_fill();">${lang.getStringAutoFill()}</a>
</c:if>

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
	<td><input type="text" id="id_basic_flight_date_datepicker" name="flight_date" class="form_input_text" value="${flight_info.getFlight_date() }" /></td>
	<td width="100px;"  align="right">${lang.getStringFlightNo()}:</td>
	<td><input type="text" onchange="update_rp_no();" id="id_basic_flight_no" name="flight_no" class="form_input_text" value="${flight_info.getFlight_no() }"/></td>
	<td width="100px;" align="right">${lang.getStringAirline()}:</td>
	<td><input type="text" id="id_basic_air_line" name="flight_airline" class="form_input_text" value="${flight_info.getAirline() }"/></td>
</tr>

</tbody>
</table>
<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringRoute()}:</td>
	<td><input type="text" id="id_basic_route_from" name="flight_route_from" class="form_input_text" value="${flight_info.getRoute_from() }"/></td>
	<td width="100px;" align="right">${lang.getStringTo()}</td>
	<td><input type="text" id="id_basic_route_to" name="flight_route_to" class="form_input_text" value="${flight_info.getRoute_to() }"/></td>
	<td width="100px;" align="right">${lang.getStringDiverted()}:</td>
	<td><input type="text" id="id_basic_route_diverted" name="flight_route_diverted" class="form_input_text" value="${flight_info.getRoute_diverted() }"/></td>
</tr>
</tbody>
</table>

<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringFlightType()}:</td>
	<td><select id="id_basic_flight_type_selector" name="flight_type" class="form_selector">
					
				<option value="SCHEDULED" ${flight_info.getFlight_type() == "SCHEDULED" ? "selected=\"selected\"" : ""}>SCHEDULED</option>
				<option value="NON-SCHEDULED" ${flight_info.getFlight_type() == "NON-SCHEDULED" ? "selected=\"selected\"" : ""}>NON-SCHEDULED</option>
			</select></td>
</tr>
</tbody>
</table>  

<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringDomesticInternational()}:</td>
	<td><select id="id_basic_dom_or_int_selector" name="flight_dom_int_type" class="form_selector">
					
				<option value="INTERNATIONAL" ${flight_info.getDom_int_type() == "INTERNATIONAL" ? "selected=\"selected\"" : ""}>INTERNATIONAL</option>
				<option value="DOMESTIC" ${flight_info.getDom_int_type() == "DOMESTIC" ? "selected=\"selected\"" : ""}>DOMESTIC</option>
			</select></td>
	<td style="width:100px;text-align:right;">${lang.getStringCargoOperation()}:</td>
	<td><select id="id_basic_cargo_oper_selector" name="flight_cargo_operation" class="form_selector">
					
				<option value="PASSENGER/CARGO" ${flight_info.getCargo_operation() == "PASSENGER/CARGO" ? "selected=\"selected\"" : ""}>PASSENGER/CARGO</option>
				<option value="PASSENGER" ${flight_info.getCargo_operation() == "PASSENGER" ? "selected=\"selected\"" : ""}>PASSENGER</option>
				<option value="CARGO" ${flight_info.getCargo_operation() == "CARGO" ? "selected=\"selected\"" : ""}>CARGO</option>
				<option value="MAIL" ${flight_info.getCargo_operation() == "MAIL" ? "selected=\"selected\"" : ""}>MAIL</option>
			</select></td>
</tr>
</tbody>
</table> 

<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringNoOfFlightCrew()}:</td>
	<td><input type="text" id="id_basic_no_of_flight_crew" name="flight_no_crew" class="form_input_text" value="${flight_info.getNo_crew() }"/></td>
	<td style="width:100px;text-align:right;">${lang.getStringNoOfCabinCrew()}:</td>
	<td><input type="text" id="id_basic_no_of_cabin_crew" name="flight_no_cabin" class="form_input_text" value="${flight_info.getNo_cabin() }"/></td>
	<td style="width:100px;text-align:right;">${lang.getStringNoOfPassengers()}:</td>
	<td><input type="text" id="id_basic_no_of_passengers" name="flight_no_cabin" class="form_input_text" value="${flight_info.getNo_passenger() }"/></td>
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
	<td><input type="text" id="id_basic_id_basic_number" name="userinfo_id_no" class="form_input_text" value="${user_info.userid}"/></td>
	<td style="width:100px;text-align:right;">${lang.getStringName()}:</td>
	<td><input type="text" id="id_basic_name" name="userinfo_name"  class="form_input_text" value="${user_info.name}"/></td>
	<td style="width:100px;text-align:right;">${lang.getStringDateOfBirth()}:</td>
	<td><input type="text" id="id_basic_date_of_birth_datapicker" name="userinfo_date_of_birth"  class="form_input_text" value="${user_info.date_of_birth}"/></td>
</tr>
</tbody>
</table> 
<table>
<tbody>
<tr>
	<td class="leftmost_label_wide">${lang.getStringMedicalCertification()}:</td>
	<td><select id="id_basic_medical_certification_selector" name="userinfo_medical_certification" class="form_selector">
					
				<option value="NONE" ${user_info.medical_certification == "NONE" ? "selected=\"selected\"" : ""}>NONE</option>
				<option value="CLASS1" ${user_info.medical_certification == "CLASS1" ? "selected=\"selected\"" : ""}>CLASS1</option>
				<option value="CLASS2" ${user_info.medical_certification == "CLASS2" ? "selected=\"selected\"" : ""}>CLASS2</option>
				<option value="CLASS3" ${user_info.medical_certification == "CLASS3" ? "selected=\"selected\"" : ""}>CLASS3</option>
				<option value="UNKNOWN" ${user_info.medical_certification == "UNKNOWN" ? "selected=\"selected\"" : ""}>UNKNOWN</option>
			</select></td>
	<td style="width:135px;text-align:right;">${lang.getStringDateOfLastMedical()}:</td>
	<td><input type="text" id="id_basic_date_of_last_medical_datapicker" name="userinfo_date_of_last_medical" class="form_input_text" value="${user_info.date_of_last_medical}"/></td>
</tr>
</tbody>
</table> 
<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringFlightTime()}:</td>
	<td><input type="text" id="id_basic_total_time" name="userinfo_flight_time" class="form_input_text" value="${user_info.flight_time}"/></td>
	<td style="width:135px;text-align:right;">${lang.getStringThisMakeModel()}:</td>
	<td><input type="text" id="id_basic_this_mask_model" name="userinfo_this_make_model" class="form_input_text" value="${user_info.this_make_model}"/></td>
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
	<td><select id="id_basic_manufacturer_selector" name="aircraftinfo_manufacturer" class="form_selector">
				
				<c:forEach items="${manufacturerList}" var="manufacturer" varStatus="list_status">
					<option value="${manufacturer.value}" ${aircraft_info.getManufacturer() == manufacturer.value ? "selected=\"selected\"" : ""}>${manufacturer.name}</option>
				</c:forEach>	
			</select></td>
	<td style="width:135px;text-align:right;">${lang.getStringModel()}:</td>
	<td><select id="id_basic_model_selector" name="aircraftinfo_model" class="form_selector">
					
		<c:forEach items="${acModelList}" var="acModel" varStatus="list_status">
				<option value="${acModel.value}" ${aircraft_info.getModel() == acModel.value ? "selected=\"selected\"" : ""}>${acModel.name}</option>
		</c:forEach>
				
			</select></td>
</tr>
<tr>
	<td class="leftmost_label">${lang.getStringSerialNumber()}:</td>
	<td><input type="text" id="id_basic_serial_number" name="aircraftinfo_serial_no" class="form_input_text" value="${aircraft_info.getSerial_no()}"/></td>
	<td style="width:135px;text-align:right;">${lang.getStringRegistrationNumber()}:</td>
	<td><input type="text" id="id_basic_registration_number" name="aircraftinfo_regi_no" class="form_input_text" value="${aircraft_info.getRegi_no()}"/></td>
</tr>
</tbody>
</table> 
 
<table>
<tbody>
<tr>
	<td>${lang.getStringNoOfSeats()}: </td>
	<td class="leftmost_label">${lang.getStringFlightCrews()}:</td>
	<td><input type="text" id="id_basic_flight_crew" name="aircraftinfo_no_seat_crew" class="form_input_text" value="${aircraft_info.getNo_seat_crew()}"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringCabinCrews()}:</td>
	<td><input type="text" id="id_basic_cabin_crew" name="aircraftinfo_no_seat_cabin" class="form_input_text" value="${aircraft_info.getNo_seat_cabin()}"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringPassengers()}:</td>
	<td><input type="text" id="id_basic_passengers" name="aircraftinfo_no_seat_passenger" class="form_input_text" value="${aircraft_info.getNo_seat_passenger()}"/></td>
</tr>
</tbody>
</table> 

<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringLastInspectionType()}:</td>
	<td><select id="id_basic_last_inspection_type_selector" name="aircraftinfo_last_inspection_type" class="form_selector">
					
				<option value="100HOUR" ${aircraft_info.getLast_inspection_type() == "100HOUR" ? "selected=\"selected\"" : ""}>100HOUR</option>
				<option value="AAIP"	${aircraft_info.getLast_inspection_type() == "AAIP" ? "selected=\"selected\"" : ""}>AAIP</option>
				<option value="ANNUAL"	${aircraft_info.getLast_inspection_type() == "ANNUAL" ? "selected=\"selected\"" : ""}>ANNUAL</option>
				<option value="CONTINUOUS AIRWORTHINESS" ${aircraft_info.getLast_inspection_type() == "CONTINUOUS AIRWORTHINESS" ? "selected=\"selected\"" : ""}>CONTINUOUS AIRWORTHINESS</option>
				<option value="CONDITIONAL INSPECTION" ${aircraft_info.getLast_inspection_type() == "CONDITIONAL INSPECTION" ? "selected=\"selected\"" : ""}>CONDITIONAL INSPECTION</option>
				<option value="UNKNOWN" ${aircraft_info.getLast_inspection_type() == "UNKNOWN" ? "selected=\"selected\"" : ""}>UNKNOWN</option>
			</select></td>
	<td style="width:130px;text-align:right;">${lang.getStringDateLastInspection()}:</td>
	<td><input type="text" id="id_basic_date_last_inspection_datepicker" name="aircraftinfo_last_inspection_date" class="form_input_text" value="${aircraft_info.getLast_inspection_date()}"/></td>
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
	<td><input type="text" id="id_basic_flight_crew_no_of_fatalities" name="crew_fatalities" class="form_input_text" value="${report.getCrew_fatalities()}"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringSeriousInjuries()}:</td>
	<td><input type="text" id="id_basic_flight_crew_no_of_injuries" name="crew_serious_injuries" class="form_input_text" value="${report.getCrew_serious_injuries()}"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringMinorInjuries()}:</td>
	<td><input type="text" id="id_basic_flight_crew_no_of_injuries" name="crew_minor_injuries" class="form_input_text" value="${report.getCrew_minor_injuries()}"/></td>
	
</tr>
<tr>
	<td class="leftmost_label2">${lang.getStringCabinCrew()}</td>
	<td style="width:130px;text-align:right;">${lang.getStringFatalities()}:</td>
	<td><input type="text" id="id_basic_cabin_crew_no_of_fatalities" name="cabin_fatalities" class="form_input_text" value="${report.getCabin_fatalities()}"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringSeriousInjuries()}:</td>
	<td><input type="text" id="id_basic_cabin_crew_no_of_injuries" name="cabin_serious_injuries" class="form_input_text" value="${report.getCabin_serious_injuries()}"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringMinorInjuries()}:</td>
	<td><input type="text" id="id_basic_cabin_crew_no_of_injuries" name="cabin_minor_injuries" class="form_input_text" value="${report.getCabin_minor_injuries()}"/></td>
	
</tr>
<tr>
	<td class="leftmost_label2">${lang.getStringPassenger()}</td>
	<td style="width:130px;text-align:right;">${lang.getStringFatalities()}:</td>
	<td><input type="text" id="id_basic_passengers_no_of_fatalities" name="passenger_fatalities" class="form_input_text" value="${report.getPassenger_fatalities()}"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringSeriousInjuries()}:</td>
	<td><input type="text" id="id_basic_passengers_no_of_injuries" name="passenger_serious_injuries" class="form_input_text" value="${report.getPassenger_serious_injuries()}"/></td>
	<td style="width:130px;text-align:right;">${lang.getStringMinorInjuries()}:</td>
	<td><input type="text" id="id_basic_passengers_no_of_injuries" name="passenger_minor_injuries" class="form_input_text" value="${report.getPassenger_minor_injuries()}"/></td>
	
</tr>
</tbody>
</table> 

<table>
<tbody>
<tr>
	<td class="leftmost_label">${lang.getStringAircraftDamages()}:</td>
	<td><select id="id_basic_damage_selector" name="aircraft_damage" class="form_selector">
					
				<c:forEach items="${aircraftDamageList}" var="aircraftDamage" varStatus="list_status">
					<option value="${aircraftDamage.value}" ${report.getAircraft_damage() == aircraftDamage.value ? "selected=\"selected\"" : ""}>${aircraftDamage.name}</option>
				</c:forEach>
			</select></td>
	
	<!-- 
	<td class="leftmost_label">${lang.getStringInjury()}:</td>
	<td><select id="id_basic_damage_selector" name="injury" class="form_selector">
				
				<c:forEach items="${injuryList}" var="injury" varStatus="list_status">
					<option value="${injury.value}" ${report.getInjury() == injury.value ? "selected=\"selected\"" : ""}>${injury.name}</option>
				</c:forEach>	
			</select></td>
    -->
	<td style="width:70px;text-align:right;">${lang.getStringDelay()}${lang.getStringTime()}:</td>
	<td><select id="id_basic_delay_time_selector" name="delay_time" class="form_selector">
				
				<c:forEach items="${delayTimeList}" var="delayTime" varStatus="list_status">
					<option value="${delayTime.value}" ${report.getDelay_time() == delayTime.value ? "selected=\"selected\"" : ""}>${delayTime.name}</option>
				</c:forEach>					
			</select></td>
</tr>
</tbody>
</table>
</fieldset>
			 
</form>
</body>
</html>
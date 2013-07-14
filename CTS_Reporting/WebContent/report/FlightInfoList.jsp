<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<rows>
	<page>${page}</page>
	<total>${total_pages}</total>
	<records>${total_count}</records>

<c:forEach items="${flightInfoList}" var="flight_info" varStatus="list_status">
	<row id='${flight_info.report_no}'>
      <cell>${flight_info.report_no}</cell>
      <cell>${flight_info.flight_date}</cell>
      <cell>${flight_info.flight_no}</cell>
      <cell>${flight_info.ac_model}</cell>
      <cell>${flight_info.report_state}</cell>
</row>
</c:forEach>
</rows>

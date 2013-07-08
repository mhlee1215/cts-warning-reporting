<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<rows>
  <page>1</page>
  <total>1</total>
  <records>10</records>
<c:forEach items="${reports}" var="report" varStatus="status">
<row id='${report.report_no}'>
      <cell>${report.title}</cell>
      <cell>${report.report_date}</cell>
      <cell>${report.aircraft_damage}</cell>
      <cell>${report.injury}</cell>
      <cell>${report.delay_time}</cell>
      <cell>${report.priority}</cell>
      <cell>${report.state}</cell>
</row>
</c:forEach>
</rows>
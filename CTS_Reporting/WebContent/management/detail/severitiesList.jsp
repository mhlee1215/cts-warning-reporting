<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<rows>
  <page>1</page>
  <total>1</total>
  <records>10</records>
<c:forEach items="${severityList}" var="severity" varStatus="status">
<row id='${status.index}'>
	  <cell>${status.index}</cell>
      <cell>${severity.occurrence_no}</cell>
      <cell>${severity.date}</cell>
      <cell>${severity.occurrence}</cell>
      <cell>${severity.severity}</cell>
      <cell>${severity.severity}</cell>
</row>
</c:forEach>
</rows>
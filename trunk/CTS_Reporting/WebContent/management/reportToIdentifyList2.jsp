<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<rows>
  <page>1</page>
  <total>1</total>
  <records>10</records>
<c:forEach items="${hazards}" var="hazard" varStatus="status">
<row id='${hazard.hazard_no}'>
      <cell>${hazard.title}</cell>
      <cell>${hazard.hazard_date}</cell>
      <cell>${hazard.damage}</cell>
      <cell>${hazard.injury}</cell>
      <cell>${hazard.delay}</cell>
      <cell>${hazard.priority}</cell>
      <cell>${hazard.state}</cell>
</row>
</c:forEach>
</rows>
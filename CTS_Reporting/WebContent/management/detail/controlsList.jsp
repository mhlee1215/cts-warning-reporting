<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<rows>
  <page>1</page>
  <total>1</total>
  <records>10</records>
<c:forEach items="${controlList}" var="control" varStatus="status">
<row id='${status.index}'>
      <cell>${control.control_no}</cell>
      <cell>${control.title}</cell>
      <cell>${control.state}</cell>
      <cell>${control.start_date}</cell>
      <cell>${control.end_date}</cell>
</row>
</c:forEach>
</rows>
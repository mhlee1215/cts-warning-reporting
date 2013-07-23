<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<rows>
  <page>1</page>
  <total>1</total>
  <records>10</records>
<c:forEach items="${likelihoodList}" var="likelihood" varStatus="status">
<row id='${status.index}'>
	  <cell>${status.index}</cell>
      <cell>${likelihood.title}</cell>
      <cell>${likelihood.date}</cell>
      <cell>${likelihood.occurrence}</cell>
      <cell>${likelihood.likelihood}</cell>
      <cell>${likelihood.likelihood}</cell>
</row>
</c:forEach>
</rows>
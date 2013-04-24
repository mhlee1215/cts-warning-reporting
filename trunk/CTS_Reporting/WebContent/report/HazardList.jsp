<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<rows>
	<page>${page}</page>
	<total>${total_pages}</total>
	<records>${total_count}</records>
<c:forEach items="${HazardItemList}" var="HazardItem" varStatus="status">
	<row> 
		<cell>${HazardItem.id}</cell>
		<cell>${HazardItem.item_lv1_name}</cell>
		<cell>${HazardItem.item_lv2_name}</cell>
		<cell>${HazardItem.item_lv3_name}</cell>
		<cell>${HazardItem.item_lv4_name}</cell>
		<cell>${HazardItem.item_lv5_name}</cell>			
		<cell/>
	</row>
</c:forEach>
</rows>
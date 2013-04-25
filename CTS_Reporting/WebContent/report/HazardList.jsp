<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<rows>
	<page>${page}</page>
	<total>${total_pages}</total>
	<records>${total_count}</records>


<c:forEach items="${hazard_list}" var="hazard_items" varStatus="list_status">
	<row> 
		<c:forEach items="${hazard_items}" var="hazard_item" varStatus="item_status">
			<c:if test="${item_status.first }">
				<cell>${hazard_item.seq_num}</cell>
				<c:set var="seq_num" value="${hazard_item.seq_num}"></c:set>
			</c:if>
			<cell>${hazard_item.item_name}</cell>
		</c:forEach>
		<c:forEach begin="${fn:length(hazard_items)+1}" end="5" varStatus="loop">
    		<cell> N/A </cell>
		</c:forEach>
		<cell>${seq_num}</cell>
	</row>
</c:forEach>
</rows>
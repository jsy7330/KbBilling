<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils"%>

<%
	String useLanguage = StringEscapeUtils.escapeHtml((String)request.getAttribute("useLanguage"));
	String lastPagePath = (String)session.getAttribute("lastPagePath");
	String mainPath = (String)session.getAttribute("mainPath");
%>

<script type="text/javascript">
		var useLanguage = "<%=useLanguage%>";
		var lastPagePath = "<%=lastPagePath%>";
		var mainPath = "<%=mainPath%>";
		var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
</script>
<fmt:setLocale value="${sessionLanguage}_${sessionCountry}" scope="request" />

<!-- 국제화 날짜 포멧 관련 변수 선언 -->
<c:if test="${sessionLanguage == 'ko'}">
	<c:set var="dateToStrFormat1" value="yyyy-MM-dd HH:mm:ss" scope="request"/>
	<c:set var="dateToStrFormat2" value="yyyy-MM-dd HH:mm" scope="request"/>
	<c:set var="dateToStrFormat3" value="yyyy-MM-dd HH" scope="request"/>
	<c:set var="dateToStrFormat4" value="yyyy-MM-dd" scope="request"/>
	<c:set var="dateToStrFormat5" value="yyyy-MM" scope="request"/>
	<c:set var="dateToStrFormat6" value="yyyy" scope="request"/>
</c:if>


<c:if test="${sessionLanguage == 'en'}">
	<c:set var="dateToStrFormat1" value="MM/dd/yyyy HH:mm:ss" scope="request"/>
	<c:set var="dateToStrFormat2" value="MM/dd/yyyy HH:mm" scope="request"/>
	<c:set var="dateToStrFormat3" value="MM/dd/yyyy HH" scope="request"/>
	<c:set var="dateToStrFormat4" value="MM/dd/yyyy" scope="request"/>
	<c:set var="dateToStrFormat5" value="MM/yyyy" scope="request"/>
	<c:set var="dateToStrFormat6" value="yyyy" scope="request"/>
</c:if>

<c:set var="dateFormat1" value="yyyyMMddHHmmss" scope="request" />
<c:set var="dateFormat2" value="yyyyMMddHHmm" scope="request"/>
<c:set var="dateFormat3" value="yyyyMMddHH" scope="request"/>
<c:set var="dateFormat4" value="yyyyMMdd" scope="request"/>
<c:set var="dateFormat5" value="yyyyMM" scope="request"/>
<c:set var="dateFormat6" value="yyyy" scope="request"/>


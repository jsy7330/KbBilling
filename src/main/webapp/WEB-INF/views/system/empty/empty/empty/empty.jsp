<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Navigator -->
<div class="h3_bg">
	<h3>${menuName}</h3>
		<div class="nav">                                        
   			<a href="#" class="home">home</a>
			<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
				<span>&gt;</span>${mu.menuName}
			</c:forEach>
		</div>                               
</div>

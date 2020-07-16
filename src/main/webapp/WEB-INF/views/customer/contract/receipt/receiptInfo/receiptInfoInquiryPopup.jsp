<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

$(document).ready(function() {
	
	//상세계약정보확인 버튼 이벤트
	$('#popPymBtn').on('click',function (e) {
		if($("#popPymBtn").hasClass('not-active')){
			return;
		}
		
		$('#mask, .Layer_wrap').hide();
		btnOrderProcessEvent();
	});
	
	$('#popCtrtBtn').on('click',function (e) {
		if($("#popCtrtBtn").hasClass('not-active')){
			return;
		}
		$('#mask, .Layer_wrap').hide();
		getMenuNo();
	});
});


</script>
<div class='layer_top'>
	<div class='title'><spring:message code="LAB.M08.LAB00194"/>
	</div>
	<a href='#' class='close'></a>
</div>
				
<div class='layer_box'>
	<div class='dbl_box'>
		<p class='btn_guide'><spring:message code="MSG.M15.MSG00041"/></p>
		<div class='btn_area w500'>
			<a id="popPymBtn" href='#' class='grey-btn'><spring:message code="LAB.M07.LAB00313"/></a>
			<a id="popCtrtBtn" href='#' class='grey-btn'><spring:message code="LAB.M01.LAB00245"/></a>
		</div>
	</div>
</div>

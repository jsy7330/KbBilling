<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	$(".close").click(function() {
		window.open("about:blank", "_self").close();
	});
	
	$('#btn_ok').click(function() {
		if($("#inputUserIdPop").val() == ''|| ($("#inputUserIdPop").val().trim()).length==0){
			$("#inputUserIdPop").focus();
			alert('<spring:message code="MSG.M07.MSG00042"/>');
			return;
		}
		
		if($("#inputEmlPop").val() == null || ($("#inputEmlPop").val().trim()).length==0){
				$('#inputEmlPop').focus();
				alert('<spring:message code="MSG.M08.MSG00048"/>');
				return;
		}
		var pwInfo = new Object();
		pwInfo.userId = $("#inputUserIdPop").val();
		pwInfo.email = $("#inputEmlPop").val();
		
		$.ajax({
	        url:'/system/login/getUserInfoConfirm.json',
	        type : 'POST',
			data : JSON.stringify( pwInfo ),
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			success : function( data ){
				$("#inputUserIdPop").val("");
				$("#inputEmlPop").val("");
				alert('<spring:message code="MSG.M15.MSG00049"/>');
				window.open("about:blank", "_self").close();
			},
			beforeSend: function( data ){
			},
			error : function( err ){
				ajaxErrorCallback( err );
			}
    	});
	});
	
});

function viewLayer(data) {
	// 남아있는 elements 제거
	$("#layer_pop").children().remove();
	// 생성된 elements 추가
	$("#layer_pop").append(data);
	// 모달로 열기
	$("#layer_pop").modal();					
}
</script>

<div class="layer_top">
	<div class="title">
		<spring:message code="LAB.M06.LAB00140" />
	</div>
	<a href="#" class="close"></a>
</div>

<div class="layer_box">
	<table class="writeview">
				<colgroup>
					<col style="width: 25%;">
					<col style="width: 75%;">
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="LAB.M07.LAB00067"/><span class="dot">*</span></th>
						<td><input type="text" id="inputUserIdPop" name="userIdPop" class="w100p"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M08.LAB00119"/><span class="dot">*</span></th>
						<td ><input type="text" id="inputEmlPop" name="emlPop" class="w100p"></td>
					</tr>
					
				</tbody>
			</table>
	<div class="btn_box">
		<a class="grey-btn" id="btn_ok" href="#"title="ok"><span class="write_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
		<a class="grey-btn close" href="#" id="btn_cancel" title="<spring:message code="LAB.M03.LAB00027"/>">
			<span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span>
		</a>
	</div>
</div>
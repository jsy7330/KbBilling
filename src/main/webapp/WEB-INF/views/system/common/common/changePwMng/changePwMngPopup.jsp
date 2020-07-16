<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function(){
	$(".close").click(function() {
		window.open("about:blank", "_self").close();
	});
	
	$('#btn_ok').click(function() {
		var userID = '${session_user.userId}';
        var password = $("#password").val();
        var newPassword1 = $("#newPassword1").val();
        var newPassword2 = $("#newPassword2").val();

        
        // 재입력 일치 여부
        if (newPassword1 != newPassword2) {
        	//입력한 두 개의 비밀번호가 서로  일치하지 않습니다.
        	alert('<spring:message code="MSG.M15.MSG00044"/>');
            return false;
        }
         
        // 길이
		if($("#newPassword1").val().length <6 || $("#newPassword1").val().length >8){
			alert('<spring:message code="MSG.M15.MSG00045"/>');
			return false;
        }
        /* if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{6,8}$/.test(newPassword1))
        {
            //alert("비밀번호는 숫자, 영문, 특수문자 조합으로 6~8자리를 사용해야 합니다.");
            alert('<spring:message code="MSG.M15.MSG00045"/>');
            return false;
        } */
        
        
        // 영문, 숫자, 특수문자 3종 이상 혼용
        var chk = 0;
        if(newPassword1.search(/[0-9]/g) != -1 ) chk ++;
        if(newPassword1.search(/[a-z]/ig)  != -1 ) chk ++;
        if(newPassword1.search(/[!@#$%^&*()?_~]/g)  != -1  ) chk ++;
        if(chk < 3)
        {
        	alert('<spring:message code="MSG.M15.MSG00046"/>');
            return false;
        }
         
       /*  // 동일한 문자/숫자 4이상, 연속된 문자
        if(/(\w)\1\1\1/.test(newPassword1) || isContinuedValue(newPassword1))
        {
            alert("비밀번호에 4자 이상의 연속 또는 반복 문자 및 숫자를 사용하실 수 없습니다.");
            return false;
        } */
        // 아이디 포함 여부
        if(newPassword1.search(userID)>-1)
        {
        	alert('<spring:message code="MSG.M15.MSG00047"/>');
            return false;
        }
         
        // 기존 비밀번호와 새 비밀번호 일치 여부
        if (password == newPassword2) {
        	alert('<spring:message code="MSG.M15.MSG00048"/>');
            return false;
        }
		
        
        var updatePwInfo = new Object();
        updatePwInfo.userId = userID;
        updatePwInfo.password = $("#password").val();
        updatePwInfo.newPassword1 = $("#newPassword1").val();
        updatePwInfo.newPassword2 = $("#newPassword2").val();
		$.ajax({
	        url:'/system/common/common/changePwMng/updatePassword.json',
	        type : 'POST',
			data : JSON.stringify( updatePwInfo ),
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			success : function( data ){
				$("#password").val("");
				$("#newPassword1").val("");
				$("#newPassword2").val("");
				alert('<spring:message code="MSG.M06.MSG00036"/>');
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
</script>
<div class="layer_top">
	<div class="title">
		<spring:message code="LAB.M06.LAB00103" />
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
						<th><spring:message code="LAB.M14.LAB00056"/><span class="dot">*</span></th>
						<td><input type="password" id="password" name="password" class="w100p"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00159"/><span class="dot">*</span></th>
						<td ><input type="password" id="newPassword1" name="newPassword1" class="w100p"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00160"/><span class="dot">*</span></th>
						<td ><input type="password" id="newPassword2" name="newPassword2" class="w100p"></td>
					</tr>
				</tbody>
			</table>
	<div class="btn_box">
		<a class="grey-btn" id="btn_ok" href="#"title="ok"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252" /></span></a>
		<a class="grey-btn close" href="#" id="btn_cancel" title="<spring:message code="LAB.M03.LAB00027"/>">
			<span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span>
		</a>
	</div>
</div>

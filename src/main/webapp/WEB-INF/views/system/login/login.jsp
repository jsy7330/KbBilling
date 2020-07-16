<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>VADS_login</title>

<link href="/styles/nccbs/styles.css" rel="stylesheet" type="text/css" />
<link href="/styles/nccbs/custom_login.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/scripts/nccbs/custom_login.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$('#loginUserId').keypress(function(event) {
		if(event.keyCode == 13){
			login();
			}
    	}
    );

    $('#loginUserId').keypress(function(event) {
		if(event.keyCode == 13){
			login();
			}
    	}
    );
    
    $('#findPw_btn').click(function() {
		findPw();

	});
});

function login(){
	
	//var f = document.formLogin;

	if($("#loginUserId").val() == "") {
		alert("<spring:message code="MSG.M08.MSG00004"/>");
		$("#loginUserId").focus();
		return;
	}
	
	if($("#loginUserPw").val() == "") {
		alert("<spring:message code="MSG.M06.MSG00039"/>");
		$("#loginUserPw").focus();
		return;
	}
	
	//f.submit();
	var loginCheck = "${loginCheck}";
	
	var param = new Object();
	param.text_id = $("#loginUserId").val();
	param.text_nm = $("#loginUserPw").val();
	param.useLanguage =  'KR-ko';
	
	$.ajax({
	    url:  '/system/login/loginAction',
	    type: 'POST',
	    data: param,
	    //timeout: 1000,
	    success: function(data){
	    	if(data.RESULT == "GO_MAIN") {
	    		goMenuPage(data.MAIN_URL);
	    	} if(data == "GO_CHANGE_PASSWORD") {
	    		var url = "/system/authorization/passwd/changePasswordPopUp.ajax";
	    		openModal(url);
	    	} else if(data.RESULT == "ERROR_INPUT_NULL") {
	    		alert('<spring:message code="MSG.M08.MSG00002"/>');
	    	} else if(data.RESULT == "LOGIN_FAIL") {
	    		alert('<spring:message code="MSG.M08.MSG00001"/>');
	    	} else if(data.RESULT == "FAIL_PASS_IP_BANDWIDTH") {
    	 		alert('<spring:message code="MSG.M09.MSG00018"/>');
	    	} else if(data.RESULT == "OVER_LOGIN_FAIL_COUNT") {
	    		alert('<spring:message code="MSG.M04.MSG00003"/>');
	    	} else if(data.RESULT == "LOCK_ACCOUNT") {
    	 		alert('<spring:message code="MSG.M04.MSG00002"/>');
	    	}
	    },
	    error: function(e){
	    	console.log(e.responseText.trim());
	    	viewLayer(e.responseText.trim());
	    },
	    complete: function() {
	    }
	});
}

//팝업창 modal로 열기
function openModal(url) {
	$.ajax({
		type : "post",
		url : url,
		success : function(data) {
			viewLayer(data);					
		},
	    error: function(e){
	        alert(e.responseText);
	    },
	    complete: function() {
	    }
	});
}

//Modal(layer_pop) 보이기
function viewLayer(data) {
	// 남아있는 elements 제거
	$("#layer_pop").children().remove();
	// 생성된 elements 추가
	$("#layer_pop").append(data);
	// 모달로 열기
	$("#layer_pop").modal();					
}

//Modal 닫기
function closeModal() {
	$.modal.close();
}

function findPw(){
	var url="/system/login/findPasswordPopup.ajax";
	  
	 var width = 450;
	 var height = 200;
	 var LeftPosition=(screen.width-width)/2;
	 var TopPosition=(screen.height-height)/2;
	  
	 window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no");
}
</script>
</head>

<body>
<form name="frmMenuHandle"></form>
<%-- <form method="post" action="/system/login/login" name="formLogin">
     <div id="contents">
         <div class="login_box">
              <ul>
              	 <li><img src="/images/common/login_ci.gif" alt="ci" /></li>
                 <li>
					<select class="w360" id="useLanguage" name="useLanguage">
						<c:forEach items="${listCountryLanguage}" var="countryLanguage" varStatus="status">
						<option value="${countryLanguage.countryCode}-${countryLanguage.languageCode}">${countryLanguage.languageName}</option>
						</c:forEach>
					</select>
                 </li>
                 <li><input class="id" name="text_id" id="text_id" type="text" title="User ID" onkeydown="javascript:if(event.keyCode == 13){login();}" value="admin" /></li>
                 <li><input class="pw" name="text_nm" id="text_nm" type="password" title="password"  onkeydown="javascript:if(event.keyCode == 13){login();}" value="1111" /></li>
                 <li> <a href="#" class="login-btn" onclick="javascript:login();">Login</a></li>
                  <li><a href="#" class="pw-btn">Find Password</a></li>
              </ul>
        </div>
        <div class="copy">COPYRIGHT ⓒ2016 ntels. All Right Reserved.</div>
    </div>
</form> --%>
	<!-- <form method="post" action="/system/login/login" name="formLogin"> -->

	<div class='wrapAll'>
			<div id="contents">
				<div class="login_box">
					<ul>
						<li class='login_box_title'>
							<h1>
								<img src='/images/login/logo.png' alt='logo'/>
							</h1>
						</li>
						<li>
							<label for='loginUserId'>
								<input name="loginUserId" id="loginUserId" type="text" value="admin"/>
							</label>
						</li>
						<li>
							<label for='loginUserPw'>
								<input name="loginUserPw" id="loginUserPw" type="password" value="1111"/>
								<!--// login page에서 input에 title은 사용금지-->
							</label>
						</li>
						<li>
							<a href="#" class="find-pw" id="findPw_btn">Forgot Password?</a>
						</li>
						<li>
							<a href="#" class="a-btn login" onclick="javascript:login();">Login</a>
						</li>

					</ul>
				</div>
				<!--//login_box -->
			</div>
		<!--//contents -->
			<div class='login_back'>
				<div class='title'>
					<h2>Billing System</h2>
				</div>
			<!--//title-->
				<div class='left_img_box'>
					<img src='/images/login/login_left.png' alt='main_left_image'/>
				</div>
			<!--//left -->
				<div class='right_img_box'>
					<img src='/images/login/login_right.png' alt='main_right_image'/>
				</div>
			<!--//right -->
			</div>
		</div>
		<!--//wrapAll -->
		
	<!-- </form> -->
	
</body>
</html>

<div id="popup_dialog" class="Layer_wrap" style="display:none;" >
</div>



<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/function.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		$("#searchSoId").val("${session_user.soId}");
		$('#searchSoId').selectmenu("refresh");
		
		var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
		

		if($(".datepicker").length > 0){
			$( ".datepicker" ).datepicker({
				  changeMonth: true,
				  changeYear: true,
				  regional:lng
				}).datepicker("setDate", "-30"); //시스템 날짜 출력
		}
		$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
		$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
		

		if($(".datepicker1").length > 0){
			$( ".datepicker1" ).datepicker({
				  changeMonth: true,
				  changeYear: true,
				  regional:lng
				}).datepicker("setDate", "0"); //시스템 날짜 출력
		}
		$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
		$( ".datepicker1.disabled" ).datepicker( "option", "disabled", true );

	});
</script>
<!-- title -->
<div class="layer_top">
   <div class="title">작업관리</div>
   <a href="#" class="close">Close</a>
</div>
<!--// title -->   
<div class="main_btn_box">
	<h4 class="sub_title">작업기본정보</h4>
</div>
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:20%;">
		<col style="width:10%;">
		<col style="width:30%;">
		<col style="width:10%;">
		<col style="width:20%;">
	</colgroup>
	<thead> 
		<tr>
			<th title="latest" >Order ID</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>고객 ID</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>고객명</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
		</tr>
		<tr>
			<th>요청고객</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>Order 유형</th>
			<td>
				<select id="wrkStat" name="wrkStat" class="w150">

				</select>
			</td>
			<th>전화번호</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>고객관계</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>작업요청일</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
		</tr>
		<tr>
			<th>개통희망일</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>작업 ID</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>작업명</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
		</tr>
		<tr>
			<th>상품</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>작업상태</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>계약ID</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
		</tr>
		<tr>
			<th>해지희망일</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>설치주소</th>
			<td colspan="3">
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w400" />
				</div>
			</td>
		</tr>
	</thead>
</table> 
<div class="main_btn_box">
	<div class="fr">
		<a class="grey-btn" id="btnIssueBulk" title="<spring:message code='LAB.M06.LAB00045'/>" href='#'>고객정보조회</a>
	</div>
</div>
<div class="main_btn_box">
	<h4 class="sub_title">요청사항</h4>
</div>
<table class="writeview">
	<thead> 
		<tr>
			<td>
				<textarea id="remark" name="remark" class="w100p h100"></textarea>
			</td>
		</tr>
	</thead>
</table> 
<div class="main_btn_box">
	<div class="fr">
		<a class="grey-btn" id="btnIssueBulk" title="<spring:message code='LAB.M06.LAB00045'/>" href='#'>작업진행</a>
		<a class="grey-btn" id="btnTransfer" title="<spring:message code='LAB.M08.LAB00118'/>" href='#'>작업내역조회</a>
	</div>
</div>
<div class="main_btn_box">
	<h4 class="sub_title">작업결과등록</h4>
	과금개시일 설정 : <input id="svcTelNo" type="text" class="ㅉ80" />
</div>
<table class="writeview">
	<thead> 
		<tr>
			<td>
				<textarea id="remark" name="remark" class="w100p h100"></textarea>
			</td>
		</tr>
	</thead>
</table> 
<div class="main_btn_box">
	<div class="fr">
		<a class="grey-btn" id="btnIssueBulk" title="<spring:message code='LAB.M06.LAB00045'/>" href='#'>작업완료</a>
		<a class="grey-btn" id="btnTransfer" title="<spring:message code='LAB.M08.LAB00118'/>" href='#'>작업취소</a>
		<a class="grey-btn" id="btnAddOrder" title="<spring:message code='LAB.M06.LAB00029'/>" href='#'>ORDER 취소</a>
		<a class="grey-btn" id="btnConfirmOrder" title="<spring:message code='LAB.M06.LAB00043'/>" href='#'>Order 변경</a>
		<a class="grey-btn" id="btnCancelOrder" title="<spring:message code='LAB.M06.LAB00041'/>" href='#'>계약정보조회</a>
	</div>
</div>
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:20%;">
		<col style="width:10%;">
		<col style="width:30%;">
		<col style="width:10%;">
		<col style="width:20%;">
	</colgroup>
	<thead> 
		<tr>
			<th title="latest" >USER ID</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>PassWord</th>
			<td colspan="3">
				<div class="inp_date w420">
					<input id="svcTelNo" type="text" class="w200" />&nbsp;<a class="grey-btn" id="btnCancelOrder" title="<spring:message code='LAB.M06.LAB00041'/>" href='#'>연동</a>
				</div>
			</td>
		</tr>
	</thead>
</table> 
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>


<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(function(){
	$('#btnApprovalPopup').click(function() {
		var url="approvalReportPopup.ajax";
		var param = new Object();
		param.aprvId = $("#parvId").val();
		
		openModal(url, param); 	
	});	
	$('#btnApprovalPopup2').click(function() {
		var url="approvalOkPopup.ajax";
		var param = new Object();
		param.aprvId = $("#parvId").val();
		
		openModal(url, param); 	
	});	
});


function openModal(url, param) {
	
	$.ajax({
		type : "post",
		url : url,
		data : param,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
		}
	}); 
}
</script>

<!--NaviGator-->
<div class="h3_bg">
	<h3>${menuName}</h3>
	<!-- Navigator -->
		<div class="nav">                                        
		   <a href="#" class="home"></a>
			<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
			<span>&gt;</span>${mu.menuName}
		</c:forEach>
		</div>                               
   <!--// Navigator -->
</div>

	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">가망고객 견적서 정보</h4>
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:5%;">
			<col style="width:20%;">
			<col style="width:5%;">
			<col style="width:20%;">
			<col style="width:5%;">
			<col style="width:20%;">
			<col style="width:5%;">
			<col style="width:20%;">
		</colgroup>
		 <thead> 
			<tr>
				<th>견적서 ID</th>
				<td>CT00000001</td>
				<th>견적서명</th>
				<td colspan="7">OOOO병원 보안 설치에 관한 건</td>
			</tr>
			<tr>
				<th>가망고객 ID</th>
				<td>CU00000000</td>
				<th>가망고객명</th>
				<td>조동훈</td>
				<th>가망고객 연락처</th>
				<td colspan="3">	010-6202-8458</td>
			</tr>
			<tr>
				<th>가망고객 주소</th>
				<td colspan="7">					
					[120092] &nbsp;<input id="searchProdCd" name="searchProdCd" type="text" value="서울시 서대문구 홍제2동 모내래길" class="w200" readonly>&nbsp;<input id="searchProdCd" name="searchProdCd" type="text" value="금호빌딩 5층 406호" class="w300" readonly>
				</td>
			</tr>
			<tr>
				<th>견적상품</th>
				<td>NSOK Special 상품</td>
				<th>견적금액</th>
				<td>120,000원</td>
				<th>견적일자</th>
				<td>2017-05-08</td>
				<th>견적담당자</th>
				<td>윤은국(ekyun)</td>
			</tr>
			<tr>
				<th>비고</th>
				<td colspan="7"><textarea id="searchProdCd" name="searchProdCd"  class="w100p h200" readonly>기본15%할인 및 추가 5% 할인이 들어감</textarea></td>
			</tr>
		</thead>
	</table>
	<br>
	
	업무아이디 : 
	<input type="text" id="parvId" name="aprvId" value="0000000000" />

<div class="main_btn_box">
	<div class="fr">	
		<!--결재상신-->
		<ntels:auth auth="${menuAuthC}">
		<a class="grey-btn" href="#" id="btnApprovalPopup" title='결재상신' ><span class="write_icon">결재상신</span></a>
		</ntels:auth>		
		<!--결재승인-->
		<ntels:auth auth="${menuAuthU}">
		<a class="grey-btn" href="#" id="btnApprovalPopup2" title='결재승인' ><span class="edit_icon">결재승인</span></a>
		</ntels:auth>	
	</div>
  </div>


<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:800px;" >
</div>
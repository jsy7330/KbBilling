<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {

	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$( ".date" ).datepicker();
	var date1 = new Date();
	date1.setDate( date1.getDate() + 1 );
	result_y = date1.getFullYear();
	result_m = eval(date1.getMonth()+1);
	result_d = date1.getDate();
	
	if( date1.getHours() < 10 ) {
		result_h = "0" + date1.getHours();
	} else {
		result_h = date1.getHours();
	}
	if( date1.getMinutes() < 10 ) {
		result_mi = "0" + date1.getMinutes();
	} else {
		result_mi = date1.getMinutes();
	}
	if( date1.getSeconds() < 10 ) {
		result_s = "0" + date1.getSeconds();
	}
	else {
		result_s = date1.getSeconds();
	}
	
	var date2 = null;
	var date3 = null;

	if( '${lngTyp}' == 'ko' ) {
		date2 = result_y + "-" + result_m + "-" + result_d;
		date3 = "" + result_y + result_m + result_d + result_h + result_mi + result_s;
	} else {
		date2 = result_m + "-" + result_d + "-" + result_y;
		date3 = "" + result_m + result_d + result_y + result_h + result_mi + result_s; 
	}
	$("#insertEffDt").val(date2);

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goInsert1() {
	if($("#insertRateFacUnit").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00027"/>");
		$("#insertRateFacUnit").focus();
		return;
	}
	if($("#insertRateFacUnitNm").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00035"/>");
		$("#insertRateFacUnitNm").focus();
		return;
	}
	if($("#insertRateFac").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00036"/>");
		$("#insertRateFac").focus();
		return;
	}

	$("#ratingFactorUnitInsertAction").attr("action", "/product/refInfo/ratingRefInfo/ratingFactorUnit/ratingFactorUnitInsertAction");
	$("#ratingFactorUnitInsertAction").attr("method", "post");
	$("#ratingFactorUnitInsertAction").submit();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>


<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M01.LAB00119"/>
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="ratingFactorUnitInsertAction" name="ratingFactorUnitInsertAction" method="post">
  	<input type="hidden"  id="insertEffDt" name="insertEffDt"/>
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00119"/>
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width:11%;">
				<col style="width:12%;">
				<col style="width:11%;">
				<col style="width:12%;">
				<col style="width:11%;">
				<col style="width:13%;">

			</colgroup>
			<tbody>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00122"/>
					</th>
					<td>
						<input type="text" id="insertRateFacUnit" name="insertRateFacUnit" class="w180"/>
					</td>
		
					<th>
						<spring:message code="LAB.M01.LAB00124"/>
					</th>
					<td>
						<input type="text" id="insertRateFacUnitNm" name="insertRateFacUnitNm" class="w180"/>
					</td>
				
					<th>
						<spring:message code="LAB.M01.LAB00118"/>
					</th>
					<td>
						<select id="insertRateFac" name="insertRateFac" class="w180">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${ratingFacList}" var="ratingFacList" varStatus="status">
								<option value="${ratingFacList.commonCd}">
									${ratingFacList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goInsert1();" id="btn_insert1">
			<span class="confirm_icon">
				<spring:message code="LAB.M09.LAB00048" />
			</span>
		</a>
		<a class="grey-btn close" href="#" id="btnClose">
			<span class="cancel_icon">
				<spring:message code="LAB.M03.LAB00027" />
			</span>
		</a>
	</div>
</form:form>
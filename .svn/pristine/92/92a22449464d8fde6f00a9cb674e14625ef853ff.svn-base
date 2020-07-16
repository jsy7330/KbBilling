<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$( ".date" ).datepicker();

	var date1 = new Date();
	
	result_y = date1.getFullYear();

	if( date1.getMonth()+1 < 10 ) {
		result_m = "0" + (date1.getMonth() + 1);
	} else {
		result_m = date1.getMonth()+1;
	}
	if( date1.getDate()+1 < 10 ) {
		result_d = "0" + (date1.getDate() + 1);
	} else {
		result_d = date1.getDate() + 1;
	}
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

	if( '${lngTyp}' == 'ko' ) {
		date2 = result_y + "-" + result_m + "-" + result_d;
	} else {
		date2 = result_m + "-" + result_d + "-" + result_y;
	}
	$("#effDt").val(date2);
	
	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
	
});

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

function goUpdate(){
	if( $("#effDt").val() == "" ) {
		alert("<spring:message code="MSG.M09.MSG00011"/>");
		return;
	}
	if( $("#description").val == "" ) {
		alert("<spring:message code="MSG.M01.MSG00033"/>");
		return;
	}
	
	$("#modUsgTyp").attr("disabled", false);
	
	$("#manageRatingDefUpdateAction").attr("action", "/product/refInfo/ratingRefInfo/manageRatingDef/manageRatingDefUpdateAction");
	$("#manageRatingDefUpdateAction").attr("method", "post");
	$("#manageRatingDefUpdateAction").submit();
}

</Script>

<!-- title -->
<div class="layer_top">
	<div class="title">
		<spring:message code="LAB.M01.LAB00138" />
	</div>
	<a href="#" class="close">Close</a>
</div>

<form id="manageRatingDefUpdateAction" name="manageRatingDefUpdateAction" method="post">			
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00138" />
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 30%;">
				<col style="width: 70%;">
			</colgroup>
			<thead>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00141" />&nbsp;<font color="red">*</font>
					</th>
					<td>
						<input type="text" id="chrgCd" name="chrgCd" class="w180" value="${manageRatingDef.chrgCd }" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00029" />&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modUsgTyp" id="modUsgTyp" class="w180" disabled="disabled">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${usgTypList}" var="usgTypList" varStatus="status">
								<option value="${usgTypList.commonCd}"
									<c:if test="${manageRatingDef.usgTyp eq usgTypList.commonCd}">
										selected="selected"
									</c:if>>
									${usgTypList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M09.LAB00052" />&nbsp;<font color="red">*</font>
					</th>
					<td>
					   <div class="date_box">
						   <div class="inp_date w135">
							  <input type="text" id="effDt" name="effDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00115" />
					</th>
					<td>
						<input type="text" id="description" name="description" value="${manageRatingDef.description }" class="w180"/>
					</td>
				</tr>
			</thead>
		</table>
	</div>			
	
	<div class="btn_box">
		<!--확인-->
		<a class="blue-btn" href="javascript:goUpdate();" id="btn_update_action"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
		<!--취소-->
		<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>
</form>
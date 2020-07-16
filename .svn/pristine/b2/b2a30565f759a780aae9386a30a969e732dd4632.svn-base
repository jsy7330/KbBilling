<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--��¥ �޷� ����� ��� �ʿ�-->
<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	$("#btn_update1").click( function () {
		goUpdate();
	});
	
	$("#modEffDt").val( $("#effDt").val().substring(0, 4) + "-" + $("#effDt").val().substring(4, 6) + "-" + $("#effDt").val().substring(6, 8) );
	$("#modExpDt").val( $("#expDt").val().substring(0, 4) + "-" + $("#expDt").val().substring(4, 6) + "-" + $("#expDt").val().substring(6, 8) );
});

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

function goUpdate(){
	if( $("#modUsgTyp").val() == '' ) {
		alert("<spring:message code="LAB.M07.LAB00052"/>");
		return;
	}
	if( $("#modEffDt").val() == '' ) {
		alert("<spring:message code="LAB.M07.LAB00032"/>");
		return;
	}
	if( $("#modExpDt").val() == '' ) {
		alert("<spring:message code="LAB.M07.LAB00046"/>");
		return;
	}
	if( $("#modCdrIndicator").val() == '' ) {
		alert("<spring:message code="LAB.M14.LAB00058"/>");
		return;
	}
	if( $("#modDefaultNoOfUnits").val() == '' ) {
		alert("<spring:message code="LAB.M01.LAB00208"/>");
		return;
	}
	if( $("#modDefaultUsgRoundingOffset").val() == '' ) {
		alert("<spring:message code="LAB.M01.LAB00207"/>");
		return;
	}
	if( $("#modDefaultUsgFeeRounding").val() == '' ) {
		alert("<spring:message code="LAB.M01.LAB00206"/>");
		return;
	}
	if( $("#modCallAuthThreshold").val() == '' ) {
		alert("<spring:message code="LAB.M14.LAB00059"/>");
		return;
	}
	
	$("#usageTypeMngUpdate").attr("action", "/product/refInfo/ratingRefInfo/usageType/usageTypeMngUpdateAction");
	$("#usageTypeMngUpdate").attr("method", "post");
	$("#usageTypeMngUpdate").submit();
}

</Script>
<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M07.LAB00033"/>
   	</div>
   <a href="#" class="close">Close</a>
</div>
<!--// title -->   

<form id="usageTypeMngUpdate" name="usageTypeMngUpdate" method="post">			
	<input type="hidden" id="usgTyp" name="usgTyp" value="${usageTypeMng.usgTyp }"/>
	<input type="hidden" id="expDt" name="expDt" value="${usageTypeMng.expDt }"/>
	<input type="hidden" id="effDt" name="effDt" value="${usageTypeMng.effDt }"/>
	
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M07.LAB00041"/>
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
						<spring:message code="LAB.M07.LAB00052"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<input type="text" id="modUsgTyp" name="modUsgTyp" value="${usageTypeMng.usgTyp}"class="w180"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00050"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<input type="text" id="modUsgTypNm" name="modUsgTypNm" value="${usageTypeMng.usgTypNm }" class="w180"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00078"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modUsgOfferTyp" id="modUsgOfferTyp" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${usgOfferTypList}" var="usgOfferTypList" varStatus="status">
								<option value="${usgOfferTypList.commonCd}"
									<c:if test="${usageTypeMng.usgOfferTyp eq usgOfferTypList.commonCd}">
										selected="selected"
									</c:if>>
									${usgOfferTypList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M14.LAB00058"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modCdrIndicator" id="modCdrIndicator" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${cdrIndicatorList}" var="cdrIndicatorList" varStatus="status">
								<option value="${cdrIndicatorList.commonCd}"
									<c:if test="${usageTypeMng.cdrIndicator eq cdrIndicatorList.commonCd}">
										selected="selected"
									</c:if>>
									${cdrIndicatorList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M09.LAB00194"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modMultipleDiscMethod" id="modMultipleDiscMethod" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${multipleDiscMethodList}" var="multipleDiscMethodList" varStatus="status">
								<option value="${multipleDiscMethodList.commonCd}"
									<c:if test="${usageTypeMng.multipleDiscMethod eq multipleDiscMethodList.commonCd}">
										selected="selected"
									</c:if>>
									${multipleDiscMethodList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00214"/>
					</th>
					<td>
						<select name="modPeriodApplMethod" id="modPeriodApplMethod" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${periodApplMethodList}" var="periodApplMethodList" varStatus="status">
								<option value="${periodApplMethodList.commonCd}"
									<c:if test="${usageTypeMng.periodApplMethod eq periodApplMethodList.commonCd}">
										selected="selected"
									</c:if>>
									${periodApplMethodList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00208"/>
					</th>
					<td>
						<input type="text" id="modDefaultNoOfUnits" name="modDefaultNoOfUnits" value="${usageTypeMng.defaultNoOfUnits}" class="w180"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00209"/>
					</th>
					<td>
						<select name="modDefaultUsgRounding" id="modDefaultUsgRounding" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${roundingList}" var="roundingList" varStatus="status">
								<option value="${roundingList.commonCd}"
									<c:if test="${usageTypeMng.defaultUsgRounding eq roundingList.commonCd}">
										selected="selected"
									</c:if>>
									${roundingList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00207"/>
					</th>
					<td>
						<input type="text" id="modDefaultUsgRoundingOffset" name="modDefaultUsgRoundingOffset" value="${usageTypeMng.defaultUsgRoundingOffset}" class="w180"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00206"/>
					</th>
					<td>
						<select name="modDefaultUsgFeeRounding" id="modDefaultUsgFeeRounding" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${roundingList}" var="roundingList" varStatus="status">
								<option value="${roundingList.commonCd}"
									<c:if test="${usageTypeMng.defaultUsgFeeRounding eq roundingList.commonCd}">
										selected="selected"
									</c:if>>
									${roundingList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00032"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
					   <div class="date_box">
						   <div class="inp_date w135">
							  <input type="text"  id="modEffDt" name="modEffDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00046"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
					   <div class="date_box">
						   <div class="inp_date w135">
							  <input type="text"  id="modExpDt" name="modExpDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00106"/>
					</th>
					<td>
						<select name="modParentUsgTyp" id="modParentUsgTyp" class="w180">
							<option>
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${cdrIndicatorList}" var="cdrIndicatorList" varStatus="status">
								<option value="${cdrIndicatorList.commonCd}"
									<c:if test="${usageTypeMng.parentUsgTyp eq cdrIndicatorList.commonCd}">
										selected="selected"
									</c:if>>
									${cdrIndicatorList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M14.LAB00059"/>
					</th>
					<td>
						<input type="text" id="modCallAuthThreshold" name="modCallAuthThreshold" value="${usageTypeMng.callAuthThreshold }" class="w180"/>
					</td>
				</tr>
			</thead>
		</table>
		<div id='gridDiv'>
		<table id="attributePop" class="w100p"></table>
			<!--<table id="attributePop" style="width: 100%"></table> -->
			<div id="pager3"></div>
		</div>			
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="#" id="btn_update1"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
		<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>
</form>
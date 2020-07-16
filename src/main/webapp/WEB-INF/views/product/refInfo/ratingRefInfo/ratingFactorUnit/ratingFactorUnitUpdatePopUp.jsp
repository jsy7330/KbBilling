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

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goUpdate() {
	if($("#modRateFacUnitNm").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00035"/>");
		$("#modRateFacUnitNm").focus();
		return;
	}
	if($("#modRateFac").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00036"/>");
		$("#modRateFac").focus();
		return;
	}

	$("#ratingFactorUnitUpdateAction").attr("action", "/product/refInfo/ratingRefInfo/ratingFactorUnit/ratingFactorUnitUpdateAction");
	$("#ratingFactorUnitUpdateAction").attr("method", "post");
	$("#ratingFactorUnitUpdateAction").submit();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>


<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M01.LAB00125"/>
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="ratingFactorUnitUpdateAction" name="ratingFactorUnitUpdateAction" method="post">
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00125"/>
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
						<input type="text" id="modRateFacUnit" name="modRateFacUnit" value="${ratingFactorUnit.modRateFacUnit }" class="w180" readonly="readonly"/>
					</td>
		
					<th>
						<spring:message code="LAB.M01.LAB00124"/>
					</th>
					<td>
						<input type="text" id="modRateFacUnitNm" name="modRateFacUnitNm" value="${ratingFactorUnit.modRateFacUnitNm }" class="w180"/>
					</td>
				
					<th>
						<spring:message code="LAB.M01.LAB00118"/>
					</th>
					<td>
						<select id="modRateFac" name="modRateFac" class="w180">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${ratingFacList}" var="ratingFacList" varStatus="status">
									<option value="${ratingFacList.commonCd}"
										<c:if test="${ratingFactorUnit.modRateFac eq ratingFacList.commonCd}">
											selected="selected"
										</c:if>>
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
		<a class="blue-btn" href="javascript:goUpdate();" id="btn_update">
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
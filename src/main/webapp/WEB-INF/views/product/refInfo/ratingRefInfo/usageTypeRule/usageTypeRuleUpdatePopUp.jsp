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
	if($("#modFldNm").val() == "" ) {
		alert("<spring:message code="MSG.M13.MSG00018"/>");
		$("#modFldNm").focus();
		return;
	}
	
	$("#modDataNm").attr("disabled", false);
	$("#modFldSeq").attr("disabled", false);
	
	$("#usageTypeRuleUpdateAction").attr("action", "/product/refInfo/ratingRefInfo/usageTypeRule/usageTypeRuleUpdateAction");
	$("#usageTypeRuleUpdateAction").attr("method", "post");
	$("#usageTypeRuleUpdateAction").submit();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>

<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M07.LAB00045"/>
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="usageTypeRuleUpdateAction" name="usageTypeRuleUpdateAction" method="post">
   	<input type="hidden"  id="modEffDt" name="modEffDt" value="${usageTypeRule.effDt }"/>

	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M07.LAB00045"/>
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width:12%;">
				<col style="width:13%;">
				<col style="width:12%;">
				<col style="width:13%;">
				<col style="width:12%;">
				<col style="width:13%;">
				<col style="width:12%;">
				<col style="width:13%;">
			</colgroup>
			<tbody>
				<tr>
					<th>
						<spring:message code="LAB.M03.LAB00068"/>
					</th>
					<td>
						<select id="modDataNm" name="modDataNm" class="w180p" disabled="disabled">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${dataNmList}" var="dataNmList" varStatus="status">
									<option value="${dataNmList.commonCd}"
										<c:if test="${usageTypeRule.dataNm eq dataNmList.commonCd}">
											selected="selected"
										</c:if>>
										${dataNmList.commonCdNm}
									</option>
							</c:forEach>
					</select>
					</td>
		
					<th>
						<spring:message code="LAB.M08.LAB00139"/>
					</th>
					<td>
						<input type="text" id="modSeqNo" name="modSeqNo" value="${usageTypeRule.seqNo }" readonly="readonly"/>
						
					</td>
					<th>
						<spring:message code="LAB.M13.LAB00029"/>
					</th>
					<td>
						<select id="modFldSeq" name="modFldSeq" class="w180p" disabled="disabled">
							<option value="${usageTypeRule.fldSeq }">0</option>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00181"/>
					</th>
					<td>
						<input type="text" id="modFldNm" name="modFldNm" value="${usageTypeRule.fldNm }" class="w180p"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goUpdate();" id="btn_update1">
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
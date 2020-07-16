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
	if($("#modBranchNm").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00012"/>");
		$("#modBranchNm").focus();
		return;
	}
	if($("#modFuncCd").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00021"/>");
		$("#modFuncCd").focus();
		return;
	}
	
	$("#modUsgTyp").attr("disabled", false);
	$("#modChrgCdSeq").attr("disabled", false);
	$("#modSeqNo").attr("disabled", false);

	$("#ratingCodeRuleUpdateAction").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeRule/ratingCodeRuleUpdateAction");
	$("#ratingCodeRuleUpdateAction").attr("method", "post");
	$("#ratingCodeRuleUpdateAction").submit();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>


<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M01.LAB00142"/>
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="ratingCodeRuleUpdateAction" name="ratingCodeRuleUpdateAction" method="post">
  	<input type="hidden"  id="modEffDt" name="modEffDt" value="${ratingCodeRule.effDt }"/>
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00142"/>
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width:8%;">
				<col style="width:11%;">
				<col style="width:8%;">
				<col style="width:6%;">
				<col style="width:8%;">
				<col style="width:7%;">
				<col style="width:8%;">
				<col style="width:7%;">
				<col style="width:8%;">
				<col style="width:11%;">
				<col style="width:9%;">
				<col style="width:5%;">
			</colgroup>
			<tbody>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00029"/>
					</th>
					<td>
						<select id="modUsgTyp" name="modUsgTyp" class="w300p" disabled="disabled">
						<option value="">
							<spring:message code="LAB.M15.LAB00002"/>
						</option>
						<c:forEach items="${usgTypList}" var="usgTypList" varStatus="status">
								<option value="${usgTypList.commonCd}"
									<c:if test="${ratingCodeRule.usgTyp eq usgTypList.commonCd}">
										selected="selected"
									</c:if>>
									${usgTypList.commonCdNm}
								</option>
						</c:forEach>
					</select>
					</td>
		
					<th>
						<spring:message code="LAB.M15.LAB00023"/>
					</th>
					<td>
						<select id="modChrgCdSeq" name="modChrgCdSeq" class="w180p" disabled="disabled">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${chrgCdseqList}" var="chrgCdseqList" varStatus="status">
								<option value="${chrgCdseqList.commonCd}"
									<c:if test="${ratingCodeRule.chrgCdSeq eq chrgCdseqList.commonCd}">
										selected="selected"
									</c:if>>
									${chrgCdseqList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				
					<th>
						<spring:message code="LAB.M07.LAB00288"/>
					</th>
					<td>
						<select id="modSeqNo" name="modSeqNo" class="w180p" disabled="disabled">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${seqList}" var="seqList" varStatus="status">
								<option value="${seqList.commonCd}"
									<c:if test="${ratingCodeRule.seqNo eq seqList.commonCd}">
										selected="selected"
									</c:if>>
									${seqList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				
					<th>
						<spring:message code="LAB.M15.LAB00022"/>
					</th>
					<td>
						<input type="text" id="modBranchSeq" name="modBranchSeq" value="${ratingCodeRule.branchSeq }" class="w180p" readonly="readonly"/>
					</td>
		
					<th>
						<spring:message code="LAB.M15.LAB00021"/>
					</th>
					<td>
						<input type="text" id="modBranchNm" name="modBranchNm" value="${ratingCodeRule.branchNm }" class="w180p"/>
					</td>
				
					<th>
						<spring:message code="LAB.M15.LAB00037"/>
					</th>
					<td>
						<input type="text" id="modFuncCd" name="modFuncCd" value="${ratingCodeRule.funcCd }" class="w180p"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goUpdate();" id="btn_insert1">
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
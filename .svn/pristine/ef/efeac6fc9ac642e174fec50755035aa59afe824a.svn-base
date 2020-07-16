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
	
	$("#modEffDt").val( $("#effDt").val());
	$("#modExpDt").val( $("#expDt").val());

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goUpdate() {
	if($("#modExpDt").val() == "" ) {
		alert("<spring:message code="MSG.M08.MSG00040"/>");
		$("#modExpDt").focus();
		return;
	}
	if($("#modUsgTyp").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00027"/>");
		$("#modUsgTyp").focus();
		return;
	}

	$("#modDataNm").attr("disabled", false);
	
	$("#usageTypeMapUpdateAction").attr("action", "/product/refInfo/ratingRefInfo/usageTypeMap/usageTypeMapUpdateAction");
	$("#usageTypeMapUpdateAction").attr("method", "post");
	$("#usageTypeMapUpdateAction").submit();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>

<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M07.LAB00049"/>
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="usageTypeMapUpdateAction" name="usageTypeMapUpdateAction" method="post">
	<input type="hidden" id="effDt" name="effDt" value="${usageTypeMap.modEffDt }" />
	<input type="hidden" id="expDt" name="expDt" value="${usageTypeMap.modExpDt }" />

	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M07.LAB00049"/>
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
										<c:if test="${usageTypeMap.modDataNm eq dataNmList.commonCd}">
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
						<input type="text" id="modSeqNo" name="modSeqNo" value="${usageTypeMap.modSeqNo }" class="w105" readonly="readonly"/>
					</td>
					<th>
						<spring:message code="LAB.M13.LAB00024"/>
					</th>
					<td>
						<input type="text" id="modFld1" name="modFld1" value="${usageTypeMap.modFld1 }" class="w105" readonly="readonly"/>
					</td>
					<th>
						<spring:message code="LAB.M13.LAB00025"/>
					</th>
					<td>
						<input type="text" id="modFld2" name="modFld2" value="${usageTypeMap.modFld2 }" class="w105" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M13.LAB00026"/>
					</th>
					<td>
						<input type="text" id="modFld3" name="modFld3" value="${usageTypeMap.modFld3 }" class="w105" readonly="readonly"/>
					</td>
					<th>
						<spring:message code="LAB.M08.LAB00108"/>
					</th>
					<td>
						<div class="date_box">
							<div class="inp_date w135">
						  		<input type="text" id="modEffDt" name="modEffDt" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
					<th>
						<spring:message code="LAB.M08.LAB00110"/>
					</th>
					<td>
					   <div class="date_box">
						   <div class="inp_date w135">
							  <input type="text" id="modExpDt" name="modExpDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00029"/>
					</th>
					<td>
						<select name="modUsgTyp" id="modUsgTyp" class="w135">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${usgTypList}" var="usgTypList" varStatus="status">
									<option value="${usgTypList.commonCd}"
										<c:if test="${usageTypeMap.modUsgTyp eq usgTypList.commonCd}">
											selected="selected"
										</c:if>>
										${usgTypList.commonCdNm}
									</option>
							</c:forEach>
						</select>
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
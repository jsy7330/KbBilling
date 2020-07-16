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

function goSearchChrgCdPopUp() {
	var param = new Object();
	var url = "ratingCodeMapUpdateChrgCdPopUp.ajax";
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goUpdate1() {
	if($("#modChrgCd").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00040" />");
		$("#insertUsgTyp").focus();
		return;
	}
	if($("#modBillFlag").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00034" />");
		$("#insertChrgCdSeq").focus();
		return;
	}

	$("#modUsgTyp").removeAttr("disabled");
	$("#modChrgCdSeq").removeAttr("disabled");
	$("#modSeqNo").removeAttr("disabled");

	$("#ratingCodeMapUpdateAction").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeMap/ratingCodeMapUpdateAction");
	$("#ratingCodeMapUpdateAction").attr("method", "post");
	$("#ratingCodeMapUpdateAction").submit();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>

<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M01.LAB00147" />
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="ratingCodeMapUpdateAction" name="ratingCodeMapUpdateAction" method="post">
	<input type="hidden" id="effDt" name="effDt" value="${ratingCodeMap.effDt }"/>
	<input type="hidden" id="expDt" name="expDt" value="${ratingCodeMap.expDt }"/>

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
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
			</colgroup>
			<tbody>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00029" />
					</th>
					<td>
						<select id="modUsgTyp" name="modUsgTyp" class="w300p" disabled>
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${usgTypList}" var="usgTypList" varStatus="status">
								<option value="${usgTypList.commonCd}"
									<c:if test="${ratingCodeMap.usgTyp eq usgTypList.commonCd}">
										selected="selected"
									</c:if>>
									${usgTypList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
		
					<th>
						<spring:message code="LAB.M15.LAB00023" />
					</th>
					<td>
						<select id="modChrgCdSeq" name="modChrgCdSeq" class="w180p" disabled>
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${seqList}" var="seqList" varStatus="status">
								<option value="${seqList.commonCd}"
									<c:if test="${ratingCodeMap.chrgCdSeq eq seqList.commonCd}">
										selected="selected"
									</c:if>>
									${seqList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				
					<th>
						<spring:message code="LAB.M07.LAB00288" />
					</th>
					<td>
						<select id="modSeqNo" name="modSeqNo" class="w180p" disabled>
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${seqList}" var="seqList" varStatus="status">
								<option value="${seqList.commonCd}"
									<c:if test="${ratingCodeMap.seqNo eq seqList.commonCd}">
										selected="selected"
									</c:if>>
									${seqList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M15.LAB00016" />
					</th>
					<td>
						<input type="text" id="modBranch1" name="modBranch1" value="${ratingCodeMap.branch1 }" class="w180p" readonly/>
					</td>
		
					<th>
						<spring:message code="LAB.M15.LAB00017" />
					</th>
					<td>
						<input type="text" id="modBranch2" name="modBranch2" value="${ratingCodeMap.branch2 }" class="w180p" readonly/>
					</td>
				
					<th>
						<spring:message code="LAB.M15.LAB00018" />
					</th>
					<td>
						<input type="text" id="modBranch3" name="modBranch3" value="${ratingCodeMap.branch3 }" class="w180p" readonly/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M15.LAB00019" />
					</th>
					<td>
						<input type="text" id="modBranch4" name="modBranch4" value="${ratingCodeMap.branch4 }" class="w180p" readonly/>
					</td>
					<th>
						<spring:message code="LAB.M15.LAB00020" />
					</th>
					<td>
						<input type="text" id="modBranch5" name="modBranch5" value="${ratingCodeMap.branch5 }" class="w180p" readonly/>
					</td>
					<th>
						<spring:message code="LAB.M08.LAB00108" />
					</th>
					<td>
						<div class="date_box">
							<div class="inp_date w135p">
					  			<input type="text"  id="modEffDt" name="modEffDt" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
					<th>
						<spring:message code="LAB.M08.LAB00110" />
					</th>
					<td>
						<div class="date_box">
							<div class="inp_date w135p">
					  			<input type="text"  id="modExpDt" name="modExpDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00141" />
					</th>
					<td>
						<select id="modChrgCd" name="modChrgCd" class="w180p">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${chrgCdList}" var="chrgCdList" varStatus="status">
								<option value="${chrgCdList.commonCd}"
									<c:if test="${ratingCodeMap.chrgCd eq chrgCdList.commonCd}">
										selected="selected"
									</c:if>>
									${chrgCdList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00117" />
					</th>
					<td>
						<select id="modBillFlag" name="modBillFlag" class="w180p">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${flagList}" var="flagList" varStatus="status">
								<option value="${flagList.commonCd}"
									<c:if test="${ratingCodeMap.billFlag eq flagList.commonCd}">
										selected="selected"
									</c:if>>
									${flagList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goUpdate1();" id="btn_insert1">
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
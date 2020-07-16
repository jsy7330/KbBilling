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
	date1.setDate(date1.getDate() + 1);
	result_y = date1.getFullYear();
	
	if( (date1.getMonth() + 1) < 10 ) {
		result_m = "0" + (date1.getMonth() + 1);
	} else {
		result_m = date1.getMonth() + 1;
	}
	if( (date1.getDate()+1) < 10 ) {
		result_d = "0" + (date1.getDate());
	} else {
		result_d = date1.getDate();
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
	var date3 = null;

	if( '${lngTyp}' == 'ko' ) {
		date2 = result_y + "-" + result_m + "-" + result_d;
		date3 = "" + result_y + result_m + result_d + result_h + result_mi + result_s;
	} else {
		date2 = result_m + "-" + result_d + "-" + result_y;
		date3 = "" + result_m + result_d + result_y + result_h + result_mi + result_s; 
	}
	
	$("#insertEffDt").val(date3);

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goInsert1() {
	if($("#insertUsgTyp").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00027"/>");
		$("#insertUsgTyp").focus();
		return;
	}
	if($("#insertChrgCdSeq").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00015"/>");
		$("#insertChrgCdSeq").focus();
		return;
	}
	if($("#insertSeq").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00124"/>");
		$("#insertSeq").focus();
		return;
	}
	if($("#insertBranchSeq").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00013"/>");
		$("#insertBranchSeq").focus();
		return;
	}
	if($("#insertBranchNm").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00012"/>");
		$("#insertBranchNm").focus();
		return;
	}
	if($("#insertFuncCd").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00021"/>");
		$("#insertFuncCd").focus();
		return;
	}

	$("#ratingCodeRuleInsertAction").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeRule/ratingCodeRuleInsertAction");
	$("#ratingCodeRuleInsertAction").attr("method", "post");
	$("#ratingCodeRuleInsertAction").submit();
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
<form:form commandName="ratingCodeRuleInsertAction" name="ratingCodeRuleInsertAction" method="post">
  	<input type="hidden"  id="insertExpDt" name="insertExpDt" value="99991231235959"/>
  	<input type="hidden"  id="insertEffDt" name="insertEffDt"/>
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
						<select id="insertUsgTyp" name="insertUsgTyp" class="w300p">
						<option value="${param.usgTyp }">
							<spring:message code="LAB.M15.LAB00002"/>
						</option>
						<c:forEach items="${usgTypList}" var="usgTypList" varStatus="status">
							<option value="${usgTypList.commonCd}">
								${usgTypList.commonCdNm}								
							</option>
						</c:forEach>
					</select>
					</td>
		
					<th>
						<spring:message code="LAB.M15.LAB00023"/>
					</th>
					<td>
						<select id="insertChrgCdSeq" name="insertChrgCdSeq" class="w180p">
							<option value="${param.chrgCdSeq }">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${chrgCdseqList}" var="chrgCdseqList" varStatus="status">
								<option value="${chrgCdseqList.commonCd}">
									${chrgCdseqList.commonCdNm}								
								</option>
							</c:forEach>
						</select>
					</td>
				
					<th>
						<spring:message code="LAB.M07.LAB00288"/>
					</th>
					<td>
						<select id="insertSeqNo" name="insertSeqNo" class="w180p">
							<option value="${param.seqNo }">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${seqList}" var="seqList" varStatus="status">
								<option value="${seqList.commonCd}">
									${seqList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				
					<th>
						<spring:message code="LAB.M15.LAB00022"/>
					</th>
					<td>
						<input type="text" id="insertBranchSeq" name="insertBranchSeq" class="w180p"/>
					</td>
		
					<th>
						<spring:message code="LAB.M15.LAB00021"/>
					</th>
					<td>
						<input type="text" id="insertBranchNm" name="insertBranchNm" class="w180p"/>
					</td>
				
					<th>
						<spring:message code="LAB.M15.LAB00037"/>
					</th>
					<td>
						<input type="text" id="insertFuncCd" name="insertFuncCd" class="w180p"/>
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
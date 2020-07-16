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
	var date2 = null;
	
	date1.setDate(date1.getDate() + 1);
	
	result_y = date1.getFullYear();
	if( (date1.getMonth()+1) < 10 ) {
		result_m = "0" + (date1.getMonth() + 1);
	} else {
		result_m = date1.getMonth() + 1;
	}
	if( date1.getDate() < 10 ) {
		result_d = "0" + date1.getDate();
	} else {
		result_d = date1.getDate();
	}
	
	if( '${lngTyp}' == 'ko' ) {
		date2 = result_y + "-" + result_m + "-" + result_d;
	} else {
		date2 = result_m + "-" + result_d + "-" + result_y;
	}
	
	$("#insertEffDt").val(date2);
	
	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goInsert() {
	if($("#insertDataNm").val() == "" ) {
		alert("<spring:message code="MSG.M03.MSG00021"/>");
		$("#insertDataNm").focus();
		return;
	}
	if($("#insertSeqNo").val() == "" ) {
		alert("<spring:message code="MSG.M08.MSG00057"/>");
		$("#insertSeqNo").focus();
		return;
	}
	if($("#insertFldSeq").val() == "" ) {
		alert("<spring:message code="MSG.M13.MSG00020"/>");
		$("#insertFldSeq").focus();
		return;
	}
	if($("#insertFldNm").val() == "" ) {
		alert("<spring:message code="MSG.M13.MSG00018"/>");
		$("#insertFldNm").focus();
		return;
	}

	$("#usageTypeRuleInsertAction").attr("action", "/product/refInfo/ratingRefInfo/usageTypeRule/usageTypeRuleInsertAction");
	$("#usageTypeRuleInsertAction").attr("method", "post");
	$("#usageTypeRuleInsertAction").submit();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>


<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M07.LAB00044"/>
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="usageTypeRuleInsertAction" name="usageTypeRuleInsertAction" method="post">
  	<input type="hidden"  id="insertExpDt" name="insertExpDt"/>
  	<input type="hidden"  id="insertEffDt" name="insertEffDt"/>
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M07.LAB00044"/>
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
						<select id="insertDataNm" name="insertDataNm" class="w180p">
						<option value="">
							<spring:message code="LAB.M15.LAB00002"/>
						</option>
						<c:forEach items="${dataNmList}" var="dataNmList" varStatus="status">
							<option value="${dataNmList.commonCd}">
								${dataNmList.commonCdNm}								
							</option>
						</c:forEach>
					</select>
					</td>
		
					<th>
						<spring:message code="LAB.M08.LAB00139"/>
					</th>
					<td>
						<input type="text" id="insertSeqNo" name="insertSeqNo" />
					</td>
					<th>
						<spring:message code="LAB.M13.LAB00029"/>
					</th>
					<td>
						<select id="insertFldSeq" name="insertFldSeq" class="w180p">
							<option>0</option>
							<option>1</option>
							<option>2</option>
							<option>3</option>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00181"/>
					</th>
					<td>
						<input type="text" id="insertFldNm" name="insertFldNm" class="w180p"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goInsert();" id="btn_insert1">
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
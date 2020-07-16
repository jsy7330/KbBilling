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
	reault_y = date1.getFullYear();
	reault_m = eval(date1.getMonth()+1);
	reault_d = date1.getDate();
	
	if( parseInt(reault_m) < 10 ) {
		reault_m = "0" + reault_m; 
	}
	if( parseInt(reault_d) < 10 ) {
		reault_d = "0" + reault_d;
	}

	var date2 = null;	

	if('${lngTyp}'=='ko'){
		date2 = reault_y + "-" + reault_m + "-" + reault_d;	
	}else {
		date2 =  reault_m + "-" + reault_d + "-" +reault_y; 
	}

	$("#insertEffDt").val(date2);
	$("#insertExpDt").val("9999-12-31");
	
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
	if($("#insertFld1").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00018"/>");
		$("#insertFld1").focus();
		return;
	}
	if($("#insertFld2").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00019"/>");
		$("#insertFld2").focus();
		return;
	}
	if($("#insertFld3").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00020"/>");
		$("#insertFld3").focus();
		return;
	}
	if($("#insertEffDt").val() == "" ) {
		alert("<spring:message code="MSG.M08.MSG00038"/>");
		$("#insertEffDt").focus();
		return;
	}
	if($("#insertDataNm").val() == "" ) {
		alert("<spring:message code="MSG.M08.MSG00040"/>");
		$("#insertDataNm").focus();
		return;
	}
	if($("#insertUsgTyp").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00027"/>");
		$("#insertUsgTyp").focus();
		return;
	}

	$("#usageTypeMapInsertAction").attr("action", "/product/refInfo/ratingRefInfo/usageTypeMap/usageTypeMapInsertAction");
	$("#usageTypeMapInsertAction").attr("method", "post");
	$("#usageTypeMapInsertAction").submit();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>


<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M07.LAB00048"/>
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="usageTypeMapInsertAction" name="usageTypeMapInsertAction" method="post">
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M07.LAB00048"/>
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
						<input type="text" id="insertSeqNo" name="insertSeqNo" class="w105"/>
					</td>
					<th>
						<spring:message code="LAB.M13.LAB00024"/>
					</th>
					<td>
						<input type="text" id="insertFld1" name="insertFld1" class="w105"/>
					</td>
					<th>
						<spring:message code="LAB.M13.LAB00025"/>
					</th>
					<td>
						<input type="text" id="insertFld2" name="insertFld2" class="w105"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M13.LAB00026"/>
					</th>
					<td>
						<input type="text" id="insertFld3" name="insertFld3" class="w105"/>
					</td>
					<th>
						<spring:message code="LAB.M08.LAB00108"/>
					</th>
					<td>
						<div class="date_box">
							<div class="inp_date w135">
						  		<input type="text"  id="insertEffDt" name="insertEffDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
					<th>
						<spring:message code="LAB.M08.LAB00110"/>
					</th>
					<td>
					   <div class="date_box">
						   <div class="inp_date w135">
							  <input type="text"  id="insertExpDt" name="insertExpDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00029"/>
					</th>
					<td>
						<select name="insertUsgTyp" id="insertUsgTyp" class="w135">
						<option value="">
							<spring:message code="LAB.M07.LAB00195" />
						</option>
						<c:forEach items="${usgTypList}" var="usgTypList" varStatus="status">
							<option value="${usgTypList.commonCd}">
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
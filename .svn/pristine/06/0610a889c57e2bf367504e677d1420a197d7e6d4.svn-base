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
	result_y = date1.getFullYear();
	result_m = eval(date1.getMonth()+1);
	result_d = date1.getDate();
	
	if( parseInt(result_m) < 10 ) {
		reault_m = "0" + reault_m; 
	}
	if( parseInt(result_d) < 10 ) {
		result_d = "0" + result_d;
	}

	var date2 = null;	

	if('${lngTyp}'=='ko'){
		date2 = result_y + "-" + result_m + "-" + result_d;	
	}else {
		date2 =  result_m + "-" + result_d + "-" +result_y; 
	}
	
	$("#effDt").val(date2);

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goInsert(){
	if($("#insertChrgCd").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00040"/>");
		$("#insertChrgCd").focus();
		return;
	}
	if($("#description").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00033"/>");
		$("#description").focus();
		return;
	}
	if($("#insertUsgTyp").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00027"/>");
		$("#insertUsgTyp").focus();
		return;
	}
	if($("#insertUsgTyp").val() == "" ) {
		alert("<spring:message code="MSG.M09.MSG00011"/>");
		$("#insertUsgTyp").focus();
		return;
	}
	if($("#expDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00013"/>");
		return;
	}
	
	$("#manageRatingDefInsertAction").attr("action", "/product/refInfo/ratingRefInfo/manageRatingDef/manageRatingDefInsertAction");
	$("#manageRatingDefInsertAction").attr("method", "post");
	$("#manageRatingDefInsertAction").submit();
}

</Script>


<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M01.LAB00136" />
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="manageRatingDefInsertAction" name="manageRatingDefInsertAction" method="post">
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00136" />
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width:30%;">
				<col style="width:69%;">

			</colgroup>
			<tbody>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00141" />&nbsp;<font color="red">*</font>
					</th>
					<td>
						<input type="text" id="chrgCd" name="chrgCd" class="w180"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00115" />
					</th>
					<td>
						<input type="text" id="description" name="description" class="w180"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00029" />&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="usgTyp" id="usgTyp" class="w180">
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
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M09.LAB00052" />&nbsp;<font color="red">*</font>
					</th>
					<td>
					    <div class="date_box">
					  		<div class="inp_date w135">	
						  		<input type="text"  id="effDt" name="effDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goInsert();" id="btn_insert">
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
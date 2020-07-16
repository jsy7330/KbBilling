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
	
	$("#btn_insert1").click( function () {
		goInsert();
	});
			
	
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
});

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

function goInsert(){
	if( $("#insertUsgTyp").val() == '' ) {
		alert("<spring:message code="MSG.M07.MSG00028"/>");
		return;
	}
	if( $("#insertUsgTypNm").val() == '' ) {
		alert("<spring:message code="MSG.M07.MSG00025"/>");
		return;
	}
	if( $("#insertUsgOfferTyp").val() == '' ) {
		alert("<spring:message code="MSG.M07.MSG00047"/>");
		return;
	}
	if( $("#insertCdrIndicator").val() == '' ) {
		alert("<spring:message code="MSG.M14.MSG00023"/>");
		return;
	}
	if( $("#insertMultipleDiscMethod").val() == '' ) {
		alert("<spring:message code="MSG.M09.MSG00057"/>");
		return;
	}
	if( $("#insertEffDt").val() == '' ) {
		alert("<spring:message code="LAB.M07.LAB00032"/>");
		return;
	}
	if( $("#insertExpDt").val() == '' ) {
		alert("<spring:message code="LAB.M07.LAB00046"/>");
		return;
	}
	if( $("#insertDefaultNoOfUnits").val() == '' ) {
		alert("<spring:message code="LAB.M01.LAB00208"/>");
		return;
	}
	if( $("#insertDefaultUsgRoundingOffset").val() == '' ) {
		alert("<spring:message code="LAB.M01.LAB00207"/>");
		return;
	}
	if( $("#insertDefaultUsgFeeRounding").val() == '' ) {
		alert("<spring:message code="LAB.M01.LAB00206"/>");
		return;
	}
	if( $("#insertCallAuthThreshold").val() == '' ) {
		alert("<spring:message code="LAB.M14.LAB00059"/>");
		return;
	}
	
	$("#usageTypeMngInsert").attr("action", "/product/refInfo/ratingRefInfo/usageType/usageTypeMngInsertAction");
	$("#usageTypeMngInsert").attr("method", "post");
	$("#usageTypeMngInsert").submit();
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

<form id="usageTypeMngInsert" name="usageTypeMngInsert" method="post">			

<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">
				<spring:message code="LAB.M07.LAB00040"/>
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
					<input type="text" id="insertUsgTyp" name="insertUsgTyp" class="w180"/>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00050"/>&nbsp;<font color="red">*</font>
				</th>
				<td>
					<input type="text" id="insertUsgTypNm" name="insertUsgTypNm" class="w180"/>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00078"/>&nbsp;<font color="red">*</font>
				</th>
				<td>
					<select name="insertUsgOfferTyp" id="insertUsgOfferTyp" class="w180">
						<option>
							<spring:message code="LAB.M07.LAB00195" />
						</option>
						<c:forEach items="${usgOfferTypList}" var="usgOfferTypList" varStatus="status">
							<option value="${usgOfferTypList.commonCd}">
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
					<select name="insertCdrIndicator" id="insertCdrIndicator" class="w180">
						<option>
							<spring:message code="LAB.M07.LAB00195" />
						</option>
						<c:forEach items="${cdrIndicatorList}" var="cdrIndicatorList" varStatus="status">
							<option value="${cdrIndicatorList.commonCd}">
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
					<select name="insertMultipleDiscMethod" id="insertMultipleDiscMethod" class="w180">
						<option>
							<spring:message code="LAB.M07.LAB00195" />
						</option>
						<c:forEach items="${multipleDiscMethodList}" var="multipleDiscMethodList" varStatus="status">
							<option value="${multipleDiscMethodList.commonCd}">
								${multipleDiscMethodList .commonCdNm}
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
					<select name="insertPeriodApplMethod" id="insertPeriodApplMethod" class="w180">
						<option>
							<spring:message code="LAB.M07.LAB00195" />
						</option>
						<c:forEach items="${periodApplMethodList}" var="periodApplMethodList" varStatus="status">
							<option value="${periodApplMethodList.commonCd}">
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
					<input type="text" id="insertDefaultNoOfUnits" name="insertDefaultNoOfUnits" class="w180"/>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00209"/>
				</th>
				<td>
					<select name="insertDefaultUsgRounding" id="insertDefaultUsgRounding" class="w180">
						<option>
							<spring:message code="LAB.M07.LAB00195" />
						</option>
						<c:forEach items="${roundingList}" var="roundingList" varStatus="status">
							<option value="${roundingList.commonCd}">
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
					<input type="text" id="insertDefaultUsgRoundingOffset" name="insertDefaultUsgRoundingOffset" class="w180"/>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00206"/>
				</th>
				<td>
					<select name="insertDefaultUsgFeeRounding" id="insertDefaultUsgFeeRounding" class="w180">
						<option>
							<spring:message code="LAB.M07.LAB00195" />
						</option>
						<c:forEach items="${roundingList}" var="roundingList" varStatus="status">
							<option value="${roundingList.commonCd}">
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
						  <input type="text"  id="insertEffDt" name="insertEffDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
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
						  <input type="text"  id="insertExpDt" name="insertExpDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00106"/>
				</th>
				<td>
					<select name="insertParentUsgTyp" id="insertParentUsgTyp" class="w180">
						<option>
							<spring:message code="LAB.M07.LAB00195" />
						</option>
						<c:forEach items="${cdrIndicatorList}" var="cdrIndicatorList" varStatus="status">
							<option value="${cdrIndicatorList.commonCd}">
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
					<input type="text" id="insertCallAuthThreshold" name="insertCallAuthThreshold" class="w180"/>
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
<!--Reload-->
<a class="blue-btn" href="#" id="btn_insert1"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>


	
</form>
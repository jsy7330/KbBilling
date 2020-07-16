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

	$('#insertUsgTypLvl').selectmenu({
        change: function() {
        	if( $("#insertUsgTypLvl").val() == 0 ) {
        		$("#insertUsgTypGrpCd").empty().html("<option value='0000000000'>0000000000</option>");
        		$('#insertUsgTypGrpCd').selectmenu("refresh");
        	}
        	else {
        		$("#insertUsgTypGrpCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${listCommon14}' var='listCommon14' varStatus='status'><option value='${listCommon14.commonCd}'>${listCommon14.commonCdNm}</option></c:forEach>");
         		$('#insertUsgTypGrpCd').selectmenu("refresh");
        	}
        }
	});
	
	$('#insertUsgItmTyp').selectmenu({
        change: function() {
        	if( $("#insertUsgItmTyp").val() == 0 ) {
        		$("#insertUsgItmCd").empty().html("<option value='0000000000'>0000000000</option>");
        		$('#insertUsgItmCd').selectmenu("refresh");
        	}
        	else if( $("#insertUsgItmTyp").val() == 1 ) {
        		$("#insertUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
         		$('#insertUsgItmCd').selectmenu("refresh");
        	}
        	else {
        		$("#insertUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
         		$('#insertUsgItmCd').selectmenu("refresh");
        	}
        }
	});
	
	$('#insertCondRatingFactor1').selectmenu({
        change: function() {
        	if( $("#insertCondRatingFactor1").val() == "0" ) {
        		$("#insertCondOp1").val("");
        		$("#insertCondOp1").attr("disabled", true);
        		$("#insertCondVal1").val("");
        		$("#insertCondVal1").attr("readonly", true);
        		$('#insertCondOp1').selectmenu("refresh");
        	}
        	else {
        		$("#insertCondOp1").attr("disabled", false);
     			$("#insertCondVal1").attr("readonly", false);
        		$('#insertCondOp1').selectmenu("refresh");
        		
        	}
        }
	});
	
	$('#insertCondRatingFactor2').selectmenu({
        change: function() {
        	if( $("#insertCondRatingFactor2").val() == "0" ) {
        		$("#insertCondOp2").val("");
        		$("#insertCondOp2").attr("disabled", true);
        		$("#insertCondVal2").val("");
        		$("#insertCondVal2").attr("readonly", true);
        		$("#insertCondOp2").selectmenu("refresh");
        	}
        	else {
        		$("#insertCondOp2").attr("disabled", false);
        		$("#insertCondVal2").attr("readonly", false);
        		$("#insertCondOp2").selectmenu("refresh");
        	}
        }
	});
	
	$('#insertAmtFlag').selectmenu({
        change: function() {
        	if( $("#insertAmtFlag").val() == 1 ) {
        		$("#insertAmtUnit").empty().html('<option><spring:message code="LAB.M07.LAB00195" /></option><c:forEach items="${listItem}" var="listItem" varStatus="status"><option value="${listItem.commonCd}">${listItem.commonCdNm}</option></c:forEach>');
         		$('#insertAmtUnit').selectmenu("refresh");
        	}
        	else {
        		$("#insertAmtUnit").empty().html('<option><spring:message code="LAB.M07.LAB00195" /></option><c:forEach items="${listCommon13}" var="listCommon13" varStatus="status"><option value="${listCommon13.commonCd}">${listCommon13.commonCdNm}</option></c:forEach>');
         		$('#insertAmtUnit').selectmenu("refresh");
        	}
        }
	});
	
	$('#insertAmtFlag').selectmenu({
        change: function() {
        	$("#insertCurrOrNextCycl").val($("#insertAmtFlag").val());
        }
	});

	
	$('#checkSubscProrateFlag').change(function () {
	    if ($(this).is(':checked')) {
	    	$("#insertSubscProrateFlag").val('1');
	    } else {
	    	$("#insertSubscProrateFlag").val('0');
	    }
	});
	
	$('#checkTermProrateFlag').change(function () {
	    if ($(this).is(':checked')) {
	    	$("#insertTermProrateFlag").val('1');
	    } else {
	    	$("#insertTermProrateFlag").val('0');
	    }
	});
	
	$("#insertReplenishCycl").selectmenu({
		change: function() {
			if( $("#insertReplenishCycl").val() == 'R' ) {
				$("#th1").attr("style", "visibility:visible");
				$("#td1").attr("style", "visibility:visible");
				$("#th2").attr("style", "visibility:visible");
				$("#td2").attr("style", "visibility:visible");
				$("#insertDuration").val("");
			}
			else {
				$("#th1").attr("style", "visibility:hidden");
				$("#td1").attr("style", "visibility:hidden");
				$("#th2").attr("style", "visibility:hidden");
				$("#td2").attr("style", "visibility:hidden");
				$("#insertDuration").val("1");
			}
		}
	});
	
	initGrid();
	
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$( ".date" ).datepicker();
	var date1 = new Date();
	date1.setDate( date1.getDate() + 1 );
	result_y = date1.getFullYear();
	result_m = eval(date1.getMonth()+1);
	result_d = date1.getDate();
	
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

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

function goInsert() {
	if( $("#insertDiscDedtCd").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00029"/>");
		$("#insertDiscDedtCd").focus();
		return;		
	}
	if( $("#insertDiscDedtNm").val() == "") {
		alert("<spring:message code="MSG.M01.MSG00028"/>");
		$("#insertDiscDedtNm").focus();
		return;
	}
	if( $("#insertDedtDiscFlag").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00020"/>");
		$("#insertDedtDiscFlag").focus();
		return;		
	}
	if( $("#insertFreeTyp").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00027"/>");
		$("#insertFreeTyp").focus();
		return;		
	}
	if( $("#insertUsgTypLvl").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00023"/>");
		$("#insertUsgTypLvl").focus();
		return;		
	}
	if( $("#insertUsgTypGrpCd").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00021"/>");
		$("#insertUsgTypGrpCd").focus();
		return;		
	}
	if( $("#insertUsgItmTyp").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00049"/>");
		$("#insertUsgItmTyp").focus();
		return;		
	}
	if( $("#insertUsgItmCd").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00050"/>");
		alert($("#insertUsgItmCd").val());
		$("#insertUsgItmCd").focus();
		return;		
	}
	if( $("#insertQuantity").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00022"/>");
		$("#insertQuantity").focus();
		return;		
	}
	if( $("#insertMaxAccumBal").val() == "" ) {
		$("#insertMaxAccumBal").val("0");
	}
	$("#insertCondOp1").attr("disabled", false);
	$("#insertCondOp2").attr("disabled", false);
	
	$("#allowanceDiscountInsertAction").attr("action", "/product/refInfo/ratingRefInfo/allowanceDiscount/allowanceDiscountInsertAction");
	$("#allowanceDiscountInsertAction").attr("method", "post");
	$("#allowanceDiscountInsertAction").submit();
}

</Script>
<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M01.LAB00065"/>
	</div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->   

<form id="allowanceDiscountInsertAction" name="allowanceDiscountInsertAction" method="post">			
	<input type="hidden" id="insertSmsNotiCd" name = "insertSmsNotiCd" value=""/>
	<input type="hidden" id="insertSmsNotiFlag" name = "insertSmsNotiFlag" value="0"/>
	<input type="hidden" id="insertCurrOrNextCycl" name = "insertCurrOrNextCycl" />
	<input type="hidden" id="insertMultipleDiscMethod" name = "insertMultipleDiscMethod" value=""/>
	<input type="hidden" id="insertVoiceCallDedtFlag" name = "insertVoiceCallDedtFlag" value="0"/>
	<input type="hidden" id="insertApplDiscFlag" name = "insertApplDiscFlag" value="0"/>
	<input type="hidden" id="insertBalCrOvrMethod" name = "insertBalCrOvrMethod" value="0"/>
	<input type="hidden" id="insertBalCrOvrPeriod" name = "insertBalCrOvrPeriod" value="0"/>
	<input type="hidden" id="insertEffDt" name="insertEffDt"/>
	<input type="hidden" id="insertTmDivisionFlag" name = "insertTmDivisionFlag" value="0"/>
	<input type="hidden" id="insertSegmentFlag" name = "insertSegmentFlag" value="0"/>
	<input type="hidden" id="insertFreeOfChrgFlag" name = "insertFreeOfChrgFlag" value="0"/>
	<input type="hidden" id="expDt" name = "expDt" value="99991231235959"/>
	<input type="hidden" id="insertSubscProrateFlag" name = "insertSubscProrateFlag" value="0"/>
	<input type="hidden" id="insertTermProrateFlag" name = "insertTermProrateFlag" value="0"/>

	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00066"/>
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 10%;">
				<col style="width: 15%;">
				<col style="width: 10%;">
				<col style="width: 15%;">
				<col style="width: 10%;">
				<col style="width: 15%;">
				<col style="width: 10%;">
				<col style="width: 15%;">
			</colgroup>
			<thead>
				<tr>
					<td colspan="8">
						<h4>
							<spring:message code="LAB.M01.LAB00226"/>
						</h4>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M11.LAB00006"/><font color="red">*</font>
					</th>
					<td>
						<input type="text" id="insertDiscDedtCd" name="insertDiscDedtCd" class="w100p"/>
					</td>
					<th>
						<spring:message code="LAB.M05.LAB00037"/><font color="red">*</font>
					</th>
					<td>
						<input type="text" id="insertDiscDedtNm" name="insertDiscDedtNm" class="w100p"/>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00063"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="insertDedtDiscFlag" id="insertDedtDiscFlag" class="w100p">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon1}" var="listCommon1" varStatus="status">
								<option value="${listCommon1.commonCd}">
									${listCommon1.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00079"/><font color="red">*</font>
					</th>
					<td>
						<select name="insertFreeTyp" id="insertFreeTyp" class="w100p">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon2}" var="listCommon2" varStatus="status">
								<option value="${listCommon2.commonCd}">
									${listCommon2.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</thead>
		</table>
		<table class="writeview">
			<colgroup>
				<col style="width: 10%;">
				<col style="width: 40%;">
				<col style="width: 10%;">
				<col style="width: 40%;">
			</colgroup>
			<thead>
				<tr>
					<td colspan="8">
						<h4>
							<spring:message code="LAB.M09.LAB00051"/>
						</h4>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00042"/><font color="red">*</font>
					</th>
					<td>
						<select name="insertUsgTypLvl" id="insertUsgTypLvl" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon3}" var="listCommon3" varStatus="status">
								<option value="${listCommon3.commonCd}">
									${listCommon3.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00037"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="insertUsgTypGrpCd" id="insertUsgTypGrpCd" class="w180">
						<option value=""></option>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00079"/><font color="red">*</font>
					</th>
					<td>
						<select name="insertUsgItmTyp" id="insertUsgItmTyp" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon4}" var="listCommon4" varStatus="status">
								<option value="${listCommon4.commonCd}">
									${listCommon4.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00080"/><font color="red">*</font>
					</th>
					<td>
						<select name="insertUsgItmCd" id="insertUsgItmCd" class="w180">
							<option value=""></option>
						</select>
					</td>
				</tr>
			</thead>
		</table>
		<table class="writeview">
			<colgroup>
				<col style="width: 10%;">
				<col style="width: 23%;">
				<col style="width: 10%;">
				<col style="width: 23%;">
				<col style="width: 10%;">
				<col style="width: 23%;">
			</colgroup>
			<thead>
				<tr>
					<td colspan="8">
						<h4>
							<spring:message code="LAB.M09.LAB00126"/>
						</h4>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M09.LAB00129"/>
					</th>
					<td>
						<select name="insertCondRatingFactor1" id="insertCondRatingFactor1" class="w180" >
							<option value="0">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon5}" var="listCommon5" varStatus="status">
								<option value="${listCommon5.commonCd}">
									${listCommon5.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00132"/>
					</th>
					<td>
						<select name="insertCondOp1" id="insertCondOp1" class="w180" disabled="disabled">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon12}" var="listCommon12" varStatus="status">
								<option value="${listCommon12.commonCd}">
									${listCommon12.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00127"/>
					</th>
					<td>
						<input type="text" id="insertCondVal1" name="insertCondVal1" value="" class="w100" readonly/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00063"/>
					</th>
					<td>
						<select name="insertLogicalOperator12" id="insertLogicalOperator12" class="w180">
							<option value="0">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon6}" var="listCommon6" varStatus="status">
								<option value="${listCommon6.commonCd}">
									${listCommon6.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<td colspan='4'>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M09.LAB00130"/>
					</th>
					<td>
						<select name="insertCondRatingFactor2" id="insertCondRatingFactor2" class="w180">
							<option value="0">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon5}" var="listCommon5" varStatus="status">
								<option value="${listCommon5.commonCd}">
									${listCommon5.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00133"/>
					</th>
					<td>
						<select name="insertCondOp2" id="insertCondOp2" class="w180" disabled="disabled">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon12}" var="listCommon12" varStatus="status">
								<option value="${listCommon12.commonCd}">
									${listCommon12.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00128"/>
					</th>
					<td>
						<input type="text" id="insertCondVal2" name="insertCondVal2" class="w100" readonly="readonly"/>
					</td>
				</tr>
			</thead>
		</table>
		<table class="writeview">
			<colgroup>
				<col style="width: 10%;">
				<col style="width: 23%;">
				<col style="width: 10%;">
				<col style="width: 23%;">
				<col style="width: 10%;">
				<col style="width: 23%;">
			</colgroup>
			<thead>
				<tr>
					<td colspan="8">
						<h4>
							<spring:message code="LAB.M09.LAB00055"/>
						</h4>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" id="checkSubscProrateFlag" name="checkSubscProrateFlag"><spring:message code="LAB.M01.LAB00008"/>
					</td>
					<td>
						<input type="checkbox" id="checkTermProrateFlag" name="checkTermProrateFlag"><spring:message code="LAB.M14.LAB00047"/>
					</td>
				</tr>
			</thead>
		</table>
		<table class="writeview"  id="allowanceTab">
			<thead>
				<tr>
					<td colspan="6">
						<h4>
							<spring:message code="LAB.M01.LAB00071"/>
						</h4>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00080"/>
					</th>
					<td>
						<select name="insertApplLvl" id="insertApplLvl" class="w180">
							<c:forEach items="${listCommon7}" var="listCommon7" varStatus="status">
								<option value="${listCommon7.commonCd}">
									${listCommon7.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00077"/>
					</th>
					<td>
						<select name="insertReplenishCycl" id="insertReplenishCycl" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon8}" var="listCommon8" varStatus="status">
								<option value="${listCommon8.commonCd}">
									${listCommon8.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00086"/>
					</th>
					<td>
						<select name="insertDedtTyp" id="insertDedtTyp" class="w180">
							<option value="0">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon9}" var="listCommon9" varStatus="status">
								<option value="${listCommon9.commonCd}">
									${listCommon9.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00074"/>
					</th>
					<td>
						<select name="insertAmtFlag" id="insertAmtFlag" class="w180">
							<c:forEach items="${listCommon10}" var="listCommon10" varStatus="status">
								<option value="${listCommon10.commonCd}">
									${listCommon10.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00075"/>
					</th>
					<td>
						<select name="insertAmtUnit" id="insertAmtUnit" class="w180">
							<c:forEach items="${listItem}" var="listItem" varStatus="status">
								<option value="${listItem.commonCd}">
									${listItem.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00073"/>
					</th>
					<td>
						<input type="text" id="insertQuantity" name="insertQuantity" value=""/>
					</td>
				</tr>
				<tr id="replenishCycl">
					<th id="th1" style="visibility:hidden" >
						<spring:message code="LAB.M06.LAB00020"/>
					</th>
					<td id="td1" style="visibility:hidden">
						<select id="insertDurationUnit" name="insertDurationUnit" class="w180">
							<c:forEach items="${listCommon15}" var="listCommon15" varStatus="status">
								<option value="${listCommon15.commonCd}">
									${listCommon15.commonCdNm}
								</option>
							</c:forEach>
						</select>
						
					</td>
					<th id="th2" style="visibility:hidden">
						<spring:message code="LAB.M06.LAB00020"/>
					</th>
					<td id="td2" style="visibility:hidden">
						<input type="text" id="insertDuration" name="insertDuration" value="1" class="w180"/>
					</td>
					<th>
						<spring:message code="LAB.M10.LAB00063"/>
					</th>
					<td>
						<input type="text" id="insertMaxAccumBal" name="insertMaxAccumBal"/>
					</td>
				</tr>
			</thead>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goInsert();" id="btn_update"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
		<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>
</form>
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
	
	initGrid();
	
	$('#modUsgTypLvl').selectmenu({
        change: function() {
        	if( $("#modUsgTypLvl").val() == 0 ) {
        		$("#modUsgTypGrpCd").empty().html("<option value='0000000000'>0000000000</option>");
        		$('#modUsgTypGrpCd').selectmenu("refresh");
        	}
        	else {
        		$("#modUsgTypGrpCd").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${listCommon14}' var='listCommon14' varStatus='status'><option value='${listCommon14.commonCd}'>${listCommon14.commonCdNm}</option></c:forEach>");
         		$('#modUsgTypGrpCd').selectmenu("refresh");
        	}
        }
	});
	
	$('#modUsgItmTyp').selectmenu({
        change: function() {
        	if( $("#modUsgItmTyp").val() == 0 ) {
        		$("#modUsgItmCd").empty().html("<option value='0000000000'>0000000000</option>");
        		$('#modUsgItmCd').selectmenu("refresh");
        	}
        	else if( $("#modUsgItmTyp").val() == 1 ) {
        		$("#modUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
         		$('#modUsgItmCd').selectmenu("refresh");
        	}
        	else {
        		$("#modUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
         		$('#modUsgItmCd').selectmenu("refresh");
        	}
        }
	});
	
	if( $("#modCondRateFac1").val() == "0" ) {
		$("#modCondOp1").val("");
		$("#modCondOp1").attr("disabled", true);
		$("#modCondVal1").attr("readonly", true);
		$('#modCondOp1').selectmenu("refresh");
	}
	else {
		$("#modCondOp1").attr("disabled", false);
		$("#modCondVal1").attr("readonly", false);
		$('#modCondOp1').selectmenu("refresh");
	}
	
	if( $("#modCondRateFac2").val() == "0" ) {
		$("#modCondOp2").val("");
		$("#modCondOp2").attr("disabled", true);
		$("#modCondVal2").attr("readonly", true);
		$('#modCondOp2').selectmenu("refresh");
	}
	else {
		$("#modCondOp2").attr("disabled", false);
		$("#modCondVal2").attr("readonly", false);
		$('#modCondOp2').selectmenu("refresh");
	}

	$('#modCondRateFac1').selectmenu({
        change: function() {
        	if( $("#modCondRateFac1").val() == "0" ) {
        		$("#modCondOp1").val("");
        		$("#modCondOp1").attr("disabled", true);
        		$("#modCondVal1").val("");
        		$("#modCondVal1").attr("readonly", true);
        		$('#modCondOp1').selectmenu("refresh");
        	}
        	else {
        		$("#modCondOp1").attr("disabled", false);
     			$("#modCondVal1").attr("readonly", false);
        		$('#modCondOp1').selectmenu("refresh");
        	}
        }
	});
	
	$('#modCondRateFac2').selectmenu({
        change: function() {
        	if( $("#modCondRateFac2").val() == "0" ) {
        		$("#modCondOp2").val("");
        		$("#modCondOp2").attr("disabled", true);
        		$("#modCondVal2").val("");
        		$("#modCondVal2").attr("readonly", true);
        		$("#modCondOp2").selectmenu("refresh");
        	}
        	else {
        		$("#modCondOp2").attr("disabled", false);
        		$("#modCondVal2").attr("readonly", false);
        		$("#modCondOp2").selectmenu("refresh");
        	}
        }
	});
	
	$('#modAmtFlag').selectmenu({
        change: function() {
        	if( $("#modAmtFlag").val() == 1 ) {
        		$("#modAmtUnit").empty().html('<option><spring:message code="LAB.M07.LAB00195" /></option><c:forEach items="${listItem}" var="listItem" varStatus="status"><option value="${listItem.commonCd}">${listItem.commonCdNm}</option></c:forEach>');
         		$('#modAmtUnit').selectmenu("refresh");
        	}
        	else {
        		$("#modAmtUnit").empty().html('<option><spring:message code="LAB.M07.LAB00195" /></option><c:forEach items="${listCommon13}" var="listCommon13" varStatus="status"><option value="${listCommon13.commonCd}">${listCommon13.commonCdNm}</option></c:forEach>');
         		$('#modAmtUnit').selectmenu("refresh");
        	}
        }
	});
	
	$('#modAmtFlag').selectmenu({
        change: function() {
        	$("#modCurrOrNextCycl").val($("#modAmtFlag").val());
        	alert($("#modAmtFlag").val());
        }
	});

	
	$('#checkSubscProrateFlag').change(function () {
	    if ($(this).is(':checked')) {
	    	$("#modSubscProrateFlag").val('1');
	    } else {
	    	$("#modSubscProrateFlag").val('0');
	    }
	});
	
	$('#checkTermProrateFlag').change(function () {
	    if ($(this).is(':checked')) {
	    	$("#modTermProrateFlag").val('1');
	    } else {
	    	$("#modTermProrateFlag").val('0');
	    }
	});
	
	if($("#modSubscProrateFlag").val() == '0' ) {
		$("#checkSubscProrateFlag").attr("checked", false);
	} 
	else if($("#modSubscProrateFlag").val() == '1' ) {
		$("#checkSubscProrateFlag").attr("checked", true);
	}
	if($("#modTermProrateFlag").val() == '0' ) {
		$("#checkTermProrateFlag").attr("checked", false);
	} 
	else if($("#modTermProrateFlag").val() == '1' ) {
		$("#checkTermProrateFlag").attr("checked", true);
	}
	
	if( $("#modReplenishCycl").val() == 'R' ) {
		$("#th1").attr("style", "visibility:visible");
		$("#td1").attr("style", "visibility:visible");
		$("#th2").attr("style", "visibility:visible");
		$("#td2").attr("style", "visibility:visible");
	}
	
	$("#modReplenishCycl").selectmenu({
		change: function() {
			if( $("#modReplenishCycl").val() == 'R' ) {
				$("#th1").attr("style", "visibility:visible");
				$("#td1").attr("style", "visibility:visible");
				$("#th2").attr("style", "visibility:visible");
				$("#td2").attr("style", "visibility:visible");
				$("#modDuration").val("");
			}
			else {
				$("#th1").attr("style", "visibility:hidden");
				$("#td1").attr("style", "visibility:hidden");
				$("#th2").attr("style", "visibility:hidden");
				$("#td2").attr("style", "visibility:hidden");
				$("#modDuration").val("1");
			}
		}
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goUpdate() {
	if( $("#modDiscDedtCd").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00029"/>");
		$("#modDiscDedtCd").focus();
		return;		
	}
	if( $("#modDiscDedtNm").val() == "") {
		alert("<spring:message code="MSG.M01.MSG00028"/>");
		$("#modDiscDedtNm").focus();
		return;
	}
	if( $("#modDedtDiscFlag").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00020"/>");
		$("#modDedtDiscFlag").focus();
		return;		
	}
	if( $("#modFreeTyp").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00027"/>");
		$("#modFreeTyp").focus();
		return;		
	}
	if( $("#modUsgTypLvl").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00023"/>");
		$("#modUsgTypLvl").focus();
		return;		
	}
	if( $("#modUsgTypGrpCd").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00021"/>");
		$("#modUsgTypGrpCd").focus();
		return;		
	}
	if( $("#modUsgItmTyp").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00049"/>");
		$("#modUsgItmTyp").focus();
		return;		
	}
	if( $("#modUsgItmCd").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00050"/>");
		$("#modUsgItmCd").focus();
		return;		
	}
	if( $("#modQuantity").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00022"/>");
		$("#modQuantity").focus();
		return;		
	}
	if( $("#modMaxAccumBal").val() == "" ) {
		$("#modMaxAccumBal").val("0");
	}
	if( $("#modDuration").val() > 999 ) {
		alert("<spring:message code="MSG.M06.MSG00007"/>");
		$("#modDuration").focus();
		return;
	}
	
	$("#modCondOp1").attr("disabled", false);
	$("#modCondOp2").attr("disabled", false);
	
	$("#allowanceDiscountUpdateAction").attr("action", "/product/refInfo/ratingRefInfo/allowanceDiscount/allowanceDiscountUpdateAction");
	$("#allowanceDiscountUpdateAction").attr("method", "post");
	$("#allowanceDiscountUpdateAction").submit();
}

</Script>
<!-- title -->
<div class="layer_top">
	<div class="title">
		<spring:message code="LAB.M01.LAB00067"/>
	</div>
   <a href="#" class="close">Close</a>
</div>
<!--// title -->   

<form id="allowanceDiscountUpdateAction" name="allowanceDiscountUpdateAction" method="post">			
	<input type="hidden" id="modSmsNotiCd" name = "modSmsNotiCd" value=""/>
	<input type="hidden" id="modSmsNotiFlag" name = "modSmsNotiFlag" value="0"/>
	<input type="hidden" id="modCurrOrNextCycl" name = "modCurrOrNextCycl" />
	<input type="hidden" id="modMultipleDiscMethod" name = "modMultipleDiscMethod" value=""/>
	<input type="hidden" id="modVoiceCallDedtFlag" name = "modVoiceCallDedtFlag" value="0"/>
	<input type="hidden" id="modApplDiscFlag" name = "modApplDiscFlag" value="0"/>
	<input type="hidden" id="modBalCrOvrMethod" name = "modBalCrOvrMethod" value="0"/>
	<input type="hidden" id="modBalCrOvrPeriod" name = "modBalCrOvrPeriod" value="0"/>
	<input type="hidden" id="modEffDt" name="modEffDt" value="${allowanceDiscount.modEffDt }"/>
	<input type="hidden" id="modTmDivisionFlag" name = "modTmDivisionFlag" value="0"/>
	<input type="hidden" id="modSegmentFlag" name = "modSegmentFlag" value="0"/>
	<input type="hidden" id="modFreeOfChrgFlag" name = "modFreeOfChrgFlag" value="0"/>
	<input type="hidden" id="modSubscProrateFlag" name = "modSubscProrateFlag" value="${allowanceDiscount.modSubscProrateFlag }"/>
	<input type="hidden" id="modTermProrateFlag" name = "modTermProrateFlag" value="${allowanceDiscount.modTermProrateFlag }"/>
	<input type="hidden" id="modBalCrOvrPeriod" name = "modBalCrOvrPeroid" value="${allowanceDiscount.modTermProrateFlag }"/>
	<input type="hidden" id="modExpDt" name = "modExpDt" value="99991231235959"/>
	
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00067"/>
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
						<spring:message code="LAB.M11.LAB00006"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<input type="text" id="modDiscDedtCd" name="modDiscDedtCd" value="${allowanceDiscount.modDiscDedtCd }" class="w100p" readonly="readonly"/>
					</td>
					<th>
						<spring:message code="LAB.M05.LAB00037"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<input type="text" id="modDiscDedtNm" name="modDiscDedtNm" value="${allowanceDiscount.modDiscDedtNm }" class="w100p"/>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00063"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modDedtDiscFlag" id="modDedtDiscFlag" class="w100p">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon1}" var="listCommon1" varStatus="status">
								<option value="${listCommon1.commonCd}"
									<c:if test="${allowanceDiscount.modDedtDiscFlag eq listCommon1.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon1.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00148"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modFreeTyp" id="modFreeTyp" class="w100p">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon2}" var="listCommon2" varStatus="status">
								<option value="${listCommon2.commonCd}"
									<c:if test="${allowanceDiscount.modFreeTyp eq listCommon2.commonCd}">
										selected="selected"
									</c:if>>
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
						<spring:message code="LAB.M07.LAB00042"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modUsgTypLvl" id="modUsgTypLvl" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon3}" var="listCommon3" varStatus="status">
								<option value="${listCommon3.commonCd}"
									<c:if test="${allowanceDiscount.modUsgTypLvl eq listCommon3.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon3.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00037"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modUsgTypGrpCd" id="modUsgTypGrpCd" class="w180">
							<c:if test="${allowanceDiscount.modUsgTypLvl eq '0'}">
								<option value='0000000000' selected="selected">0000000000</option>
							</c:if>	
							<c:if test="${allowanceDiscount.modUsgTypLvl eq '1'}">
								<option value="">
									<spring:message code='LAB.M07.LAB00195' />
								</option>
								<c:forEach items='${listCommon14}' var='listCommon14' varStatus='status'>
									<option value="${listCommon14.commonCd}"
										<c:if test="${allowanceDiscount.modUsgTypGrpCd eq listCommon14.commonCd}">
											selected="selected"
										</c:if>>
										${listCommon14.commonCdNm}
									</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00079"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modUsgItmTyp" id="modUsgItmTyp" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon4}" var="listCommon4" varStatus="status">
								<option value="${listCommon4.commonCd}"
									<c:if test="${allowanceDiscount.modUsgItmTyp eq listCommon4.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon4.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00080"/>&nbsp;<font color="red">*</font>
					</th>
					<td>
						<select name="modUsgItmCd" id="modUsgItmCd" class="w180">
							<c:if test="${allowanceDiscount.modUsgItmTyp eq '0'}">
								<option value='0000000000' selected="selected">0000000000</option>
							</c:if>	
							<c:if test="${allowanceDiscount.modUsgItmTyp eq '1'}">
								<option value=''>
									<spring:message code='LAB.M07.LAB00195' />
								</option>
								<c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'>
									<option value='${chrgCdList.commonCd}'
										<c:if test="${allowanceDiscount.modUsgItmCd eq chrgCdList.commonCd }">
											selected="selected"
										</c:if>>
										${chrgCdList.commonCdNm}
									</option>
								</c:forEach>
							</c:if>
							<c:if test="${allowanceDiscount.modUsgItmTyp eq '6'}">
								<option value=''>
									<spring:message code='LAB.M07.LAB00195' />
								</option>
								<c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'>
									<option value='${chrgCdList.commonCd}'
										<c:if test="${allowanceDiscount.modUsgItmCd eq chrgCdList.commonCd }">
											selected="selected"
										</c:if>>
										${chrgCdList.commonCdNm}
									</option>
								</c:forEach>
							</c:if>
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
						<select name="modCondRateFac1" id="modCondRateFac1" class="w180" >
							<option value="0">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon5}" var="listCommon5" varStatus="status">
								<option value="${listCommon5.commonCd}"
									<c:if test="${allowanceDiscount.modCondRateFac1 eq listCommon5.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon5.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00132"/>
					</th>
					<td>
						<select name="modCondOp1" id="modCondOp1" class="w180" disabled="disabled">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon12}" var="listCommon12" varStatus="status">
								<option value="${listCommon12.commonCd}"
									<c:if test="${allowanceDiscount.modCondOp1 eq listCommon12.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon12.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00127"/>
					</th>
					<td>
						<input type="text" id="modCondVal1" name="modCondVal1" value="${allowanceDiscount.modCondVal1 }" class="w100"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00063"/>
					</th>
					<td>
						<select name="modLogicalOperator12" id="modLogicalOperator12" class="w180">
							<option value="0">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon6}" var="listCommon6" varStatus="status">
								<option value="${listCommon6.commonCd}"
									<c:if test="${allowanceDiscount.modLogicalOperator12 eq listCommon6.commonCd}">
										selected="selected"
									</c:if>>
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
						<select name="modCondRateFac2" id="modCondRateFac2" class="w180">
							<option value="0">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon5}" var="listCommon5" varStatus="status">
								<option value="${listCommon5.commonCd}"
									<c:if test="${allowanceDiscount.modCondRateFac2 eq listCommon5.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon5.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00133"/>
					</th>
					<td>
						<select name="modCondOp2" id="modCondOp2" class="w180" disabled="disabled">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon12}" var="listCommon12" varStatus="status">
								<option value="${listCommon12.commonCd}"
									<c:if test="${allowanceDiscount.modCondOp2 eq listCommon12.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon12.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00128"/>
					</th>
					<td>
						<input type="text" id="modCondVal2" name="modCondVal2" value="${allowanceDiscount.modCondVal2 }" class="w100"/>
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
						<input type="checkbox" id="checkSubscProrateFlag" name="checkSubscProrateFlag" ><spring:message code="LAB.M01.LAB00008"/>
					</td>
					<td>
						<input type="checkbox" id="checkTermProrateFlag" name="checkTermProrateFlag"><spring:message code="LAB.M14.LAB00047"/>
					</td>
				</tr>
			</thead>
		</table>
		<table class="writeview">
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
						<select name="modApplLvl" id="modApplLvl" class="w180">
							<option value="0">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon7}" var="listCommon7" varStatus="status">
								<option value="${listCommon7.commonCd}"
									<c:if test="${allowanceDiscount.modApplLvl eq listCommon7.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon7.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00077"/>
					</th>
					<td>
						<select name="modReplenishCycl" id="modReplenishCycl" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon8}" var="listCommon8" varStatus="status">
								<option value="${listCommon8.commonCd}"
									<c:if test="${allowanceDiscount.modReplenishCycl eq listCommon8.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon8.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00086"/>
					</th>
					<td>
						<select name="modDedtTyp" id="modDedtTyp" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listCommon9}" var="listCommon9" varStatus="status">
								<option value="${listCommon9.commonCd}"
									<c:if test="${allowanceDiscount.modDedtTyp eq listCommon9.commonCd}">
										selected="selected"
									</c:if>>
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
						<select name="modAmtFlag" id="modAmtFlag" class="w180">
							<c:forEach items="${listCommon10}" var="listCommon10" varStatus="status">
								<option value="${listCommon10.commonCd}"
									<c:if test="${allowanceDiscount.modAmtFlag eq listCommon10.commonCd}">
										selected="selected"
									</c:if>>
									${listCommon10.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00075"/>
					</th>
					<td>
						<select name="modAmtUnit" id="modAmtUnit" class="w180">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${listItem}" var="listItem" varStatus="status">
								<option value="${listItem.commonCd}"
									<c:if test="${allowanceDiscount.modAmtUnit eq listItem.commonCd}">
										selected="selected"
									</c:if>>
									${listItem.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00073"/>
					</th>
					<td>
						<input type="text" id="modQuantity" value="${allowanceDiscount.modQuantity }" name="modQuantity"/>
					</td>
				</tr>
			 	<tr id="replenishCycl">
					<th id="th1" style="visibility:hidden" >
						<spring:message code="LAB.M06.LAB00020"/>
					</th>
					<td id="td1" style="visibility:hidden">
						<select id="modDurationUnit" name="modDurationUnit" class="w180">
							
							<c:forEach items="${listCommon15}" var="listCommon15" varStatus="status">
								<option value="${listCommon15.commonCd}"
									<c:if test="${allowanceDiscount.modDurationUnit eq listCommon15.commonCd }">
										selected="selected"
									</c:if>>
									${listCommon15.commonCdNm}
								</option>
							</c:forEach>
						</select>
						
					</td>
					<th id="th2" style="visibility:hidden">
						<spring:message code="LAB.M06.LAB00020"/>
					</th>
					<td id="td2" style="visibility:hidden">
						<input type="text" id="modDuration" name="modDuration" value="${allowanceDiscount.modDuration }" class="w180"/>
					</td>
					<th>
						<spring:message code="LAB.M10.LAB00063"/>
					</th>
					<td>
						<input type="text" id="modMaxAccumBal" name="modMaxAccumBal" value="${allowanceDiscount.modMaxAccumBal }"/>
					</td>
				</tr>
			</thead>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goUpdate();" id="btn_update"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
		<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>
</form>
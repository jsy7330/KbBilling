<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--��¥ �޷� ����� ��� �ʿ�-->
<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	initGrid();	//jqgrid ȣ��	
	
	//��ư Ŭ����
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	$("#searchSoNm").selectmenu({
		change: function() {
			$("#searchSoId").val($("#searchSoNm").val());
		}
	});
	$("#searchSoNm").val($("#searchSoId").val());
	$("#searchSoNm").selectmenu("refresh");

	var resultMsg = "${resultMsg}";
	if( resultMsg != null && resultMsg != "" ) {
		alert("${resultMsg}");
	}
});

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

function goSearch() {
	var param = new Object();
	param.soId = $("#searchSoId").val();
	param.prodId = $("#searchProdCd").val();
	param.prodNm = $("#searchProdNm").val();

	$("#searchNewProductDtlGrid").clearGridData();  // 이전 데이터 삭제
	$("#searchNewProductDtlGrid").setGridParam({ postData: param }).trigger("reloadGrid"); 
}

function buttonEvent() {
	var rowId = $("#searchNewProductDtlGrid").jqGrid('getGridParam', 'selrow');

	selectProdInfo(rowId);
}

function selectProdInfo(rowId) {
	var data = $("#searchNewProductDtlGrid").getRowData(rowId);
	
	$("#prodCd").val(data.prodId);
	$("#prodNm").val(data.prodNm);
	$("#attrNm").val(data.attrNm);
	$("#prodFamily").val(data.prodFamily);
	$("#prodOfferTyp").val(data.prodOfferTyp);
	$("#prodOfferTyp").selectmenu("refresh");
	$("#description").val(data.description);
	$("#prodState").val(data.prodState);
	$("#prodState").selectmenu("refresh");
	$("#prodPriority").val(data.prodPriority);
	if( $("#effDt").val() != '' ) {
		$("#effDt").val('' + (data.effDt).substring(0,4) + '-' + (data.effDt).substring(4,6) + '-' + (data.effDt).substring(6,8));
		$("#expDt").val('' + (data.expDt).substring(0,4) + '-' + (data.expDt).substring(4,6) + '-' + (data.expDt).substring(6,8));
	}
	$("#prodNm2").val(data.prodNm);
	$("#prodNm3").val(data.prodNm);
	$("#prodNm4").val(data.prodNm);
	$("#prodNm5").val(data.prodNm);
	$("#prodNm6").val(data.prodNm);
	$("#prodNm7").val(data.prodNm);
	$("#prodNm8").val(data.prodNm);
	$("#prodNm9").val(data.prodNm);
	
	initGrid2();
	
	$("#btnClose").trigger('click');
}

function setSelectedData3(rowId) {
	var effDt = $("#usgTypGrid1").getRowData(rowId).effDt;
	var expDt = $("#usgTypGrid1").getRowData(rowId).expDt;

	$("#calendarDefId").val($("#usgTypGrid1").getRowData(rowId).calendarDefId);
	$("#calendarDefId").selectmenu("refresh");
	$("#periodApplMethod").val($("#usgTypGrid1").getRowData(rowId).periodApplMethod);
	$("#periodApplMethod").selectmenu("refresh");
	$("#maxReserveAmt").val($("#usgTypGrid1").getRowData(rowId).maxReserveAmt);
	$("#tierSetId").val($("#usgTypGrid1").getRowData(rowId).tierSetId);
	$("#tierSetId").selectmenu("refresh");
	$("#tierStepFlag").val($("#usgTypGrid1").getRowData(rowId).tierStepFlag);
	$("#tierStepFlag").selectmenu("refresh");
	$("#chrgCd").val($("#usgTypGrid1").getRowData(rowId).chrgCd);
	$("#chrgCd").selectmenu("refresh");
	$("#unitSvcCd").val($("#usgTypGrid1").getRowData(rowId).unitSvcCd);
	$("#usgMeasureUnit").val($("#usgTypGrid1").getRowData(rowId).usgMeasureUnit);
	$("#usgMeasureUnit").selectmenu("refresh");
	$("#currencyCd").val($("#usgTypGrid1").getRowData(rowId).currencyCd);
	$("#currencyCd").selectmenu("refresh");
	$("#usgFeeRoundingMethod").val($("#usgTypGrid1").getRowData(rowId).usgFeeRoundingMethod);
	$("#usgFeeRoundingMethod").selectmenu("refresh");
	$("#usgFeeRoundingOffset").val($("#usgTypGrid1").getRowData(rowId).usgFeeRoundingOffset);
	$("#usgRoundingMethod").val($("#usgTypGrid1").getRowData(rowId).usgRoundingMethod);
	$("#usgRoundingMethod").selectmenu("refresh");
	$("#maxReserveAmt").val($("#usgTypGrid1").getRowData(rowId).maxReserveAmt);
	
	if( effDt != '' && expDt != '' ) {
		$("#effDt1").val('' + effDt.substring(0,4) + '-' + effDt.substring(4,6) + '-' + effDt.substring(6,8));
		$("#expDt1").val('' + expDt.substring(0,4) + '-' + expDt.substring(4,6) + '-' + expDt.substring(6,8));
	}
	if( $("#usgTypGrid1").getRowData(rowId).commRateFlag == '0' ) {
		$("#checkCommRateFlag").attr('checked', false);
		$("#checkCommRateFlag").trigger('change');
	}
	else {
		$("#checkCommRateFlag").attr('checked', true);
	}
	if( $("#usgTypGrid1").getRowData(rowId).discFlag == '0' ) {
		$("#checkDiscFlag").attr('checked', false);
	}
	else {
		$("#checkDiscFlag").attr('checked', true);
	}
	if( $("#usgTypGrid1").getRowData(rowId).dedtFlag == '0' ) {
 		$("#checkDedtFlag").prop('checked', false);
	}
	else {
 		$("#checkDedtFlag").prop('checked', true);
	}
	if( $("#usgTypGrid1").getRowData(rowId).tmDivisionFlag == '0' ) {
		$("#checkTmDivisionFlag").attr('checked', false);
	}
	else {
		$("#checkTmDivisionFlag").attr('checked', true);
	}
	if( $("#usgTypGrid1").getRowData(rowId).lmtFlag == '0' ) {
		$("#checkLmtFlag").attr('checked', false);
	}
	else {
		$("#checkLmtFlag").attr('checked', true);
	}
	
	$("#tab_2").unbind('click');
	$("#tab_3").unbind('click');
	$("#tab_4").unbind('click');
	$("#tab_5").unbind('click');
	$("#tab_6").unbind('click');
	$("#tab_7").unbind('click');
	$("#tab_8").unbind('click');
	$("#tab_9").unbind('click');
	
	$("#tab_2").bind('click', function() {
		openTab(event, 'tab2');
	});
	$("#tab_3").bind('click', function() {
		openTab(event, 'tab3');
	});
	$("#tab_4").bind('click', function() {
		openTab(event, 'tab4');
	});
	$("#tab_5").bind('click', function() {
		openTab(event, 'tab5');
	});
	$("#tab_6").bind('click', function() {
		openTab(event, 'tab6');
	});
	
	if( $("#checkDiscFlag").is(':checked') ) {
		$("#tab_7").bind('click', function() {
			openTab(event, 'tab7');
		});
	};
	
	if( $("#checkDedtFlag").is(':checked') ) {
		$("#tab_8").bind('click', function() {
			openTab(event, 'tab8');
		});
	};
	
	if( $("#checkLmtFlag").is(':checked') ) {
		$("#tab_9").bind('click', function() {
			openTab(event, 'tab9');
		});
	};
}

function setSelectedData6(rowId) {
	$("#periodCd").attr("disabled", true);
	$("#tierSegmentId").attr("disabled", true);
	$("#corrId").attr("disabled", true);
	$("#chrgCd6").attr("disabled", true);
	$("#rateId").val($("#tariffGrid").getRowData(rowId).rateId);
	$("#periodCd").val($("#tariffGrid").getRowData(rowId).periodCd);
	$("#periodCd").selectmenu("refresh");
	$("#tierSegmentId").val($("#tariffGrid").getRowData(rowId).tierSegmentId);
	$("#tierSegmentId").selectmenu("refresh");
	$("#corrId").val($("#tariffGrid").getRowData(rowId).corrId);
	$("#corrId").selectmenu("refresh");
	$("#chrgCd6").val($("#tariffGrid").getRowData(rowId).chrgCd);
	$("#chrgCd6").selectmenu("refresh");
	$("#effDt6").val($("#tariffGrid").getRowData(rowId).effDt.substring(0, 10));
	$("#expDt6").val($("#tariffGrid").getRowData(rowId).expDt.substring(0, 10));
	$("#usgBillingIncrement").val($("#tariffGrid").getRowData(rowId).usgBillingIncrement);
	$("#ratePerUnit").val($("#tariffGrid").getRowData(rowId).ratePerUnit);
}

// 할인
function setSelectedData7(rowId) {
	$("#discMapCd").val($("#discountGrid").getRowData(rowId).mapCd);
	$("#mapDiscCd").val($("#discountGrid").getRowData(rowId).mapDiscDedtCd);
	$("#discNm").val($("#discountGrid").getRowData(rowId).discDedtNm);
	$("#mapDiscPrty").val($("#discountGrid").getRowData(rowId).mapDiscDedtPrty);
	$("#multipleDiscMethod").val($("#discountGrid").getRowData(rowId).multipleDiscMethod);
	$("#discMapEffDt").val($("#discountGrid").getRowData(rowId).mapEffDt.substring(0, 10));
	$("#discMapExpDt").val($("#discountGrid").getRowData(rowId).mapExpDt.substring(0, 10));
	$("#discUsgTypLvl").val($("#discountGrid").getRowData(rowId).usgTypLvl);
	$("#discUsgTypLvl").selectmenu("refresh");
	$("#discUsgTypGrpCd").val($("#discountGrid").getRowData(rowId).usgTypGrpCd);
	$("#discUsgItmTyp").val($("#discountGrid").getRowData(rowId).usgItmTyp);
	$("#discUsgItmTyp").selectmenu("refresh");
	$("#discUsgItmCd").val($("#discountGrid").getRowData(rowId).usgItmCd);
	$("#discCondRateFac1").val($("#discountGrid").getRowData(rowId).condRateFac1);
	$("#discCondOperator1").val($("#discountGrid").getRowData(rowId).condOperator1);
	$("#discCondVal1").val($("#discountGrid").getRowData(rowId).condVal1);
	$("discLogicalOperator12").val($("#discountGrid").getRowData(rowId).logicalOperator12);
	$("#discCondRateFac2").val($("#discountGrid").getRowData(rowId).condRateFac2);
	$("#discCondOperator2").val($("#discountGrid").getRowData(rowId).condOperator2);
	$("#discCondVal2").val($("#discountGrid").getRowData(rowId).condVal2);
	$("#discQuantity").val($("#discountGrid").getRowData(rowId).quantity);
	$("#discTmDivisionFlag").val($("#discountGrid").getRowData(rowId).tmDivisionFlag);
	$("#discSegmentFlag").val($("#discountGrid").getRowData(rowId).segmentFlag);
	
	if( $("#discUsgTypLvl").val() == 0 ) {
		$("#discUsgTypGrpCd").empty().html("<option value='0000000000'>0000000000</option>");
		$('#discUsgTypGrpCd').selectmenu("refresh");
	}
	else if( $("#discUsgTypLvl").val() == 2 ) {
		$("#discUsgTypGrpCd").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgItmCdList1}' var='usgItmCdList1' varStatus='status'><option value='${usgItmCdList1.commonCd}'>${usgItmCdList1.commonCdNm}</option></c:forEach>");
 		$('#discUsgTypGrpCd').selectmenu("refresh");
	}
	else if( $("#discUsgTypLvl").val() == 1 ) {
		$("#discUsgTypGrpCd").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgTypGrpCdList2}' var='usgTypGrpCdList2' varStatus='status'><option value='${usgTypGrpCdList2.commonCd}'>${usgTypGrpCdList2.commonCdNm}</option></c:forEach>");
 		$('#discUsgTypGrpCd').selectmenu("refresh");
	}
	
	$("#discUsgTypGrpCd").val($("#discountGrid").getRowData(rowId).usgTypGrpCd);
	$("#discUsgTypGrpCd").selectmenu("refresh");
	
	if( $("#discUsgItmTyp").val() == 0 ) {
		$("#discUsgItmCd").empty().html("<option value='0000000000'>0000000000</option>");
		$('#discUsgItmCd').selectmenu("refresh");
	}
	else if( $("#discUsgItmTyp").val() == 1 ) {
		$("#discUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
 		$('#discUsgItmCd').selectmenu("refresh");
	}
	else {
		$("#discUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
 		$('#discUsgItmCd').selectmenu("refresh");
	}
	
	$("#discUsgItmCd").val($("#discountGrid").getRowData(rowId).usgItmCd);
	$("#discUsgItmCd").selectmenu("refresh");
	
	$("#discCondRateFac1").val($("#discountGrid").getRowData(rowId).condRateFac1);
	$("#discCondRateFac1").selectmenu("refresh");
	
	if( $("#discCondRateFac1").val() == "0" ) {
		$("#discCondOperator1").val("");
		$("#discCondOperator1").attr("disabled", true);
		$("#discCondVal1").attr("readonly", true);
		$('#discCondOperator1').selectmenu("refresh");
	}
	else {
		$("#discCondOperator1").attr("disabled", false);
		$("#discCondVal1").attr("readonly", false);
		$('#discCondOperator1').selectmenu("refresh");
	}
	
	$("#discCondOperator1").val($("#discountGrid").getRowData(rowId).condOperator1);
	$("#discCondOperator1").selectmenu("refresh");
	$("#discLogicalOperator12").val($("#discountGrid").getRowData(rowId).logicalOperator12);
	$("#discLogicalOperator12").selectmenu("refresh");
	$("#discCondRateFac2").val($("#discountGrid").getRowData(rowId).condRateFac2);
	$("#discCondRateFac2").selectmenu("refresh");
	
	if( $("#discCondRateFac2").val() == "0" ) {
		$("#discCondOperator2").val("");
		$("#discCondOperator2").attr("disabled", true);
		$("#discCondVal2").attr("readonly", true);
		$('#discCondOperator2').selectmenu("refresh");
	}
	else {
		$("#discCondOperator2").attr("disabled", false);
		$("#discCondVal2").attr("readonly", false);
		$('#discCondOperator2').selectmenu("refresh");
	}
	
	$("#discCondOperator2").val($("#discountGrid").getRowData(rowId).condOperator2);
	$("#discCondOperator2").selectmenu("refresh");
	$("#discCondVal2").val($("#discountGrid").getRowData(rowId).condVal2);
	
	if( $("#discTmDivisionFlag").val() == 0 ) {
		$("#checkDiscTmDivisionFlag").attr('checked', false);
	}
	else {
		$("#checkDiscTmDivisionFlag").attr('checked', true);
	}
	
	if( $("#discSegmentFlag").val() == 0 ) {
		$("#checkDiscSegmentFlag").attr('checked', false);
	}
	else {
		$("#checkDiscSegmentFlag").attr('checked', true);
	}
}

// 공제
function setSelectedData8(rowId) {
	$("#mapCd").val($("#allowanceGrid").getRowData(rowId).mapCd);
	$("#mapDedtCd").val($("#allowanceGrid").getRowData(rowId).mapDiscDedtCd);
	$("#discDedtNm").val($("#allowanceGrid").getRowData(rowId).discDedtNm);
	$("#dedtMapEffDt").val($("#allowanceGrid").getRowData(rowId).mapEffDt.substring(0, 10));
	$("#dedtMapExpDt").val($("#allowanceGrid").getRowData(rowId).mapExpDt.substring(0, 10));
	$("#mapDedtPrty").val($("#allowanceGrid").getRowData(rowId).mapDiscDedtPrty);
	$("#dedtUsgTypLvl").val($("#allowanceGrid").getRowData(rowId).usgTypLvl);
	$("#dedtUsgTypLvl").selectmenu("refresh");
	
	if( $("#dedtUsgTypLvl").val() == 0 ) {
		$("#dedtUsgTypGrpCd").empty().html("<option value='0000000000'>0000000000</option>");
		$('#dedtUsgTypGrpCd').selectmenu("refresh");
	}
	else if( $("#dedtUsgTypLvl").val() == 2 ) {
		$("#dedtUsgTypGrpCd").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgItmCdList1}' var='usgItmCdList1' varStatus='status'><option value='${usgItmCdList1.commonCd}'>${usgItmCdList1.commonCdNm}</option></c:forEach>");
 		$('#dedtUsgTypGrpCd').selectmenu("refresh");
	}
	else if( $("#dedtUsgTypLvl").val() == 1 ) {
		$("#dedtUsgTypGrpCd").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgTypGrpCdList2}' var='usgTypGrpCdList2' varStatus='status'><option value='${usgTypGrpCdList2.commonCd}'>${usgTypGrpCdList2.commonCdNm}</option></c:forEach>");
 		$('#dedtUsgTypGrpCd').selectmenu("refresh");
	}
	
	$("#dedtUsgTypGrpCd").val($("#allowanceGrid").getRowData(rowId).usgTypGrpCd);
	$("#dedtUsgTypGrpCd").selectmenu("refresh");
	$("#dedtUsgItmTyp").val($("#allowanceGrid").getRowData(rowId).usgItmTyp);
	$("#dedtUsgItmTyp").selectmenu("refresh");
	
	if( $("#dedtUsgItmTyp").val() == 0 ) {
		$("#dedtUsgItmCd").empty().html("<option value='0000000000'>0000000000</option>");
		$('#dedtUsgItmCd').selectmenu("refresh");
	}
	else if( $("#dedtUsgItmTyp").val() == 1 ) {
		$("#dedtUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
 		$('#dedtUsgItmCd').selectmenu("refresh");
	}
	else {
		$("#dedtUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
 		$('#dedtUsgItmCd').selectmenu("refresh");
	}
	
	$("#dedtUsgItmCd").val($("#allowanceGrid").getRowData(rowId).usgItmCd);
	$("#dedtUsgItmCd").selectmenu("refresh");
	$("#dedtCondRateFac1").val($("#allowanceGrid").getRowData(rowId).condRateFac1);
	$("#dedtCondRateFac1").selectmenu("refresh");
	
	if( $("#dedtCondRateFac1").val() == "0" ) {
		$("#dedtCondOperator1").val("");
		$("#dedtCondOperator1").attr("disabled", true);
		$("#dedtCondVal1").attr("readonly", true);
		$('#dedtCondOperator1').selectmenu("refresh");
	}
	else {
		$("#dedtCondOperator1").attr("disabled", false);
		$("#dedtCondVal1").attr("readonly", false);
		$('#dedtCondOperator1').selectmenu("refresh");
	}
	
	$("#dedtCondOperator1").val($("#allowanceGrid").getRowData(rowId).condOperator1);
	$("#dedtCondOperator1").selectmenu("refresh");
	$("#dedtCondVal1").val($("#allowanceGrid").getRowData(rowId).condVal1);
	$("#dedtLogicalOperator12").val($("#allowanceGrid").getRowData(rowId).logicalOperator12);
	$("#dedtLogicalOperator12").selectmenu("refresh");
	$("#dedtCondRateFac2").val($("#allowanceGrid").getRowData(rowId).condRateFac2);
	$("#dedtCondRateFac2").selectmenu("refresh");
	
	if( $("#dedtCondRateFac2").val() == "0" ) {
		$("#dedtCondOperator2").val("");
		$("#dedtCondOperator2").attr("disabled", true);
		$("#dedtCondVal2").attr("readonly", true);
		$('#dedtCondOperator2').selectmenu("refresh");
	}
	else {
		$("#dedtCondOperator2").attr("disabled", false);
		$("#dedtCondVal2").attr("readonly", false);
		$('#dedtCondOperator2').selectmenu("refresh");
	}
	
	$("#dedtCondOperator2").val($("#allowanceGrid").getRowData(rowId).condOperator2);
	$("#dedtCondOperator2").selectmenu("refresh");
	$("#dedtCondVal2").val($("#allowanceGrid").getRowData(rowId).condVal2);
	$("#subscProrateFlag").val($("#allowanceGrid").getRowData(rowId).subscProrateFlag);
	$("#termProrateFlag").val($("#allowanceGrid").getRowData(rowId).termProrateFlag);
	
	if( $("#subscProrateFlag").val() == 1 ) {
		$("#checkSubscProrateFlag").attr('checked', true);
	}
	else {
		$("#checkSubscProrateFlag").attr('checked', false);
	}
	if( $("#termProrateFlag").val() == 1 ) {
		$("#checkTermProrateFlag").attr('checked', true);
	}
	else {
		$("#checkTermProrateFlag").attr('checked', false);
	}
	
	$("#amtFlag").val($("#allowanceGrid").getRowData(rowId).amtFlag);
	$("#amtFlag").selectmenu("refresh");
	
	if( $("#amtFlag").val() == 1 ) {
		$("#amtUnit").empty().html('<option><spring:message code="LAB.M07.LAB00195" /></option><c:forEach items="${amtUnitList1}" var="amtUnitList1" varStatus="status"><option value="${amtUnitList1.commonCd}">${amtUnitList1.commonCdNm}</option></c:forEach>');
 		$('#amtUnit').selectmenu("refresh");
	}
	else {
		$("#amtUnit").empty().html('<option><spring:message code="LAB.M07.LAB00195" /></option><c:forEach items="${amtUnitList2}" var="amtUnitList2" varStatus="status"><option value="${amtUnitList2.commonCd}">${amtUnitList2.commonCdNm}</option></c:forEach>');
 		$('#amtUnit').selectmenu("refresh");
	}
	
	$("#amtUnit").val($("#allowanceGrid").getRowData(rowId).amtUnit);
	$("#amtUnit").selectmenu("refresh");
	$("#quantity").val($("#allowanceGrid").getRowData(rowId).quantity);
	$("#replenishCycl").val($("#allowanceGrid").getRowData(rowId).replenishCycl);
	$("#replenishCycl").selectmenu("refresh");
}

function setSelectedData9(rowId) {
	$("#lmtCd").val($("#limitGrid").getRowData(rowId).lmtCd);
	$("#effDt9").val($("#limitGrid").getRowData(rowId).effDt.substring(0, 10));
	$("#expDt9").val($("#limitGrid").getRowData(rowId).expDt.substring(0, 10));
	$("#usgTypLvl9").val($("#limitGrid").getRowData(rowId).usgTypLvl);
	$("#usgTypLvl9").selectmenu("refresh");

	if( $("#usgTypLvl9").val() == 0 ) {
		$("#usgTypGrpCd9").empty().html("<option value='0000000000'>0000000000</option>");
		$('#usgTypGrpCd9').selectmenu("refresh");
	}
	else if( $("#usgTypLvl9").val() == 2 ) {
		$("#usgTypGrpCd9").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgItmCdList1}' var='usgItmCdList1' varStatus='status'><option value='${usgItmCdList1.commonCd}'>${usgItmCdList1.commonCdNm}</option></c:forEach>");
 		$('#usgTypGrpCd9').selectmenu("refresh");
	}
	else if( $("#usgTypLvl9").val() == 1 ) {
		$("#usgTypGrpCd9").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgTypGrpCdList2}' var='usgTypGrpCdList2' varStatus='status'><option value='${usgTypGrpCdList2.commonCd}'>${usgTypGrpCdList2.commonCdNm}</option></c:forEach>");
 		$('#usgTypGrpCd9').selectmenu("refresh");
	}
	
	$("#usgTypGrpCd9").val($("#limitGrid").getRowData(rowId).usgTypGrpCd);
	$("#usgTypGrpCd9").selectmenu("refresh");
	$("#usgItmTyp9").val($("#limitGrid").getRowData(rowId).usgItmTyp);
	$("#usgItmTyp9").selectmenu("refresh");
	
	if( $("#usgItmTyp9").val() == 0 ) {
		$("#usgItmCd9").empty().html("<option value='0000000000'>0000000000</option>");
		$('#usgItmCd9').selectmenu("refresh");
	}
	else if( $("#usgItmTyp9").val() == 1 ) {
		$("#usgItmCd9").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgItmCdList1}' var='usgItmCdList1' varStatus='status'><option value='${usgItmCdList1.commonCd}'>${usgItmCdList1.commonCdNm}</option></c:forEach>");
 		$('#usgItmCd9').selectmenu("refresh");
	}
	else {
		$("#usgItmCd9").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
 		$('#usgItmCd9').selectmenu("refresh");
	}
	$("#usgItmCd9").val($("#limitGrid").getRowData(rowId).usgItmCd);
	$("#usgItmCd9").selectmenu("refresh");
	$("#usgTypCtrlLvl9").val($("#limitGrid").getRowData(rowId).usgTypCtrlLvl);
	$("#usgTypCtrlLvl9").selectmenu("refresh");
	
	if( $("#usgTypCtrlLvl9").val() == 0 ) {
		$("#ctrlUsgTypGrpCd9").empty().html("<option value=''></option>");
		$('#ctrlUsgTypGrpCd9').selectmenu("refresh");
	}
	else if( $("#usgTypCtrlLvl9").val() == 1 ) {
		$("#ctrlUsgTypGrpCd9").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgTypGrpCdList2}' var='usgTypGrpCdList2' varStatus='status'><option value='${usgTypGrpCdList2.commonCd}'>${usgTypGrpCdList2.commonCdNm}</option></c:forEach>");
 		$('#ctrlUsgTypGrpCd9').selectmenu("refresh");
	}
	else if( $("#usgTypCtrlLvl9").val() == 2 ) {
		$("#ctrlUsgTypGrpCd9").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgItmCdList1}' var='usgItmCdList1' varStatus='status'><option value='${usgItmCdList1.commonCd}'>${usgItmCdList1.commonCdNm}</option></c:forEach>");
 		$('#ctrlUsgTypGrpCd9').selectmenu("refresh");
	}
	else {
		$("#ctrlUsgTypGrpCd9").empty().html("<option value='0000000000'>0000000000</option>");
 		$('#ctrlUsgTypGrpCd9').selectmenu("refresh");
	}
	$("#ctrlUsgTypGrpCd9").val($("#limitGrid").getRowData(rowId).ctrlUsgTypGrpCd);
	$("#ctrlUsgTypGrpCd9").selectmenu("refresh");
	$("#amtFlag9").val($("#limitGrid").getRowData(rowId).amtFlag);
	$("#amtFlag9").selectmenu("refresh");
	$("#quantity9").val($("#limitGrid").getRowData(rowId).quantity);
	$("#rechrgLmtTypCd").val($("#limitGrid").getRowData(rowId).rechrgLmtTypCd);
	$("#rechrgLmtTypCd").selectmenu("refresh");
}

function initGrid() {
	
	var param = new Object();
	param.soId = $("#searchSoId").val();
	param.prodId = $("#searchProdCd").val();
	param.prodNm = $("#searchProdNm").val();
	
	$("#searchNewProductDtlGrid").jqGrid({
		
	   	url:'newProductDtlListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData: param,
	   	colModel: [ 
   			{ label: '상품구분' , name: 'attrNm', hidden:true, width : 0},
   			{ label: '상품군명' , name: 'prodFamily', hidden:true, width : 0},
   			{ label: '상품제공형태' , name: 'prodOfferTyp', hidden:true, width : 0},
   			{ label: '상품설명' , name: 'description', hidden:true, width : 0},
   			{ label: '상품형태' , name: 'prodState', hidden:true, width : 0},
   			{ label: '상품간우선순위' , name: 'prodPriority', hidden:true, width : 0},
   			{ label: '상품유효일자' , name: 'effDt', hidden:true, width : 0},
   			{ label: '상품종료일시' , name: 'expDt', hidden:true, width : 0},
   			{ label: 'ifSys' , name: 'ifSys', hidden:true, width : 0},
   			{ label: 'soId' , name: 'soId', hidden:true, width : 0},
   		 	{ label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'soNm', width: 50},			//속성 코드
   			{ label: '<spring:message code="LAB.M07.LAB00156"/>', name: 'prodId', width: 125},				//속성 코드
   			{ label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'prodNm', width: 125}			//속성 코드
   		],
		ondblClickRow: function(rowId) {
			selectProdInfo(rowId);
		},
	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager3',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	
        },
        loadComplete: function(obj){
        	$("#searchNewProductDtlGrid").setGridWidth($("#GridDiv").width(), false); 
        }
	});

	$("#searchNewProductDtlGrid").setGridWidth(990, false); 
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#searchNewProductDtlGrid").setGridWidth($("#GridDiv").width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

function initGrid2() {

	var param = new Object();
	param.prodId =  $("#prodCd").val();

	$("#allUsgTypGrid").jqGrid({
		
	   	url:'getAllUsgTypListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   			{ label: 'ifSys' , name: 'ifSys', hidden:true,width : 0},
   			{ label: 'remark' , name: 'remark', hidden:true,width : 0},
   		    { label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTyp', width: 100, sortable:false, align: "center"},			//속성 코드
   		 	{ label: '<spring:message code="LAB.M07.LAB00050"/>', name: 'usgTypNm', sortable:false, width: 100},			//속성 코드
   		],
	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2_1',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	
        },
        loadComplete: function(obj){
        	$("#allUsgTypGrid").setGridWidth($('#allUsgTypGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#allUsgTypGrid").setGridWidth($('#allUsgTypGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#usgTypGrid").jqGrid({
		
	   	url:'getUsgTypListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   			{ label: 'ifSys' , name: 'ifSys', hidden:true, width : 0},
   			{ label: 'remark' , name: 'remark', hidden:true, width : 0},
   		 	{ label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTyp', width: 100, sortable:false, align:"center"},			//속성 코드
   		 	{ label: '<spring:message code="LAB.M07.LAB00050"/>', name: 'usgTypNm', sortable:false, width: 100},			//속성 코드
   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2_2',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	
        },
        loadComplete: function(obj){
        	$('#usgTypGrid').setGridWidth($('#usgTypGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#usgTypGrid').setGridWidth($('#usgTypGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#usgTypGrid1").jqGrid({
		
	   	url:'getProdUsgTypListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   		 	{ label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTypNm', width: 100, align: "center"},	
   			{ label: '<spring:message code="LAB.M03.LAB00075"/>', name: 'regYn', width: 50, align: "center"},			
   			{ label: 'prodId', name: 'prodId', width: 50, align: "center",  hidden: true },
   			{ label: 'usgTyp', name: 'usgTyp', width: 100, align: "center",  hidden: true },
   			{ label: 'chrgCd', name: 'chrgCd', width: 50,  hidden: true },			
   			{ label: 'chrgCdNm', name: 'chrgCdNm', width: 50,  hidden: true },		
   			{ label: 'effDt', name: 'effDt', width: 50,  hidden: true },			
   			{ label: 'expDt', name: 'expDt', width: 50,  hidden: true },			
   			{ label: 'calendarDefId', name: 'calendarDefId', width: 50,  hidden: true },		
   			{ label: 'multipleRateInd', name: 'multipleRateInd', width: 50,  hidden: true },	
   			{ label: 'tierSetId', name: 'tierSetId', width: 50,  hidden: true },			
   			{ label: 'tierStepFlag', name: 'tierStepFlag', width: 50,  hidden: true },	
   			{ label: 'usgMeasureUnit', name: 'usgMeasureUnit', width: 50,  hidden: true },
   			{ label: 'currencyCd', name: 'currencyCd', width: 50,  hidden: true },		
   			{ label: 'tmDivisionFlag', name: 'tmDivisionFlag', width: 50,  hidden: true },
   			{ label: 'periodApplMethod', name: 'periodApplMethod', width: 50,  hidden: true },
   			{ label: 'usgRoundingMethod', name: 'usgRoundingMethod', width: 50,  hidden: true },
   			{ label: 'commRateFlag', name: 'commRateFlag', width: 50,  hidden: true },		
   			{ label: 'prodPriority', name: 'prodPriority', width: 50,  hidden: true },	
   			{ label: 'usgFeeRoundingMethod', name: 'usgFeeRoundingMethod', width: 50,  hidden: true },
   			{ label: 'discFlag', name: 'discFlag', width: 50,  hidden: true },
   			{ label: 'multipleDiscFlag', name: 'multipleDiscFlag', width: 50,  hidden: true },
   			{ label: 'multipleDiscCriteria', name: 'multipleDiscCriteria', width: 50,  hidden: true },
   			{ label: 'usgFeeRoundingOffset', name: 'usgFeeRoundingOffset', width: 50,  hidden: true },
   			{ label: 'dedtFlag', name: 'dedtFlag', width: 50,  hidden: true },	
   			{ label: 'prorateApplGrp', name: 'prorateApplGrp', width: 50,  hidden: true },
   			{ label: 'dedtRateId', name: 'dedtRateId', width: 50,  hidden: true },	
   			{ label: 'lmtFlag', name: 'lmtFlag', width: 50,  hidden: true },		
   			{ label: 'crossDiscFlag', name: 'crossDiscFlag', width: 50,  hidden: true },	
   			{ label: 'realTmAccFlag', name: 'realTmAccFlag', width: 50,  hidden: true },	
   			{ label: 'maxReserveAmt', name: 'maxReserveAmt', width: 50,  hidden: true },	
   			{ label: 'unitSvcCd', name: 'unitSvcCd', width: 50,  hidden: true },		
   			{ label: 'vatRate', name: 'vatRate', width: 50, hidden: true } 	
   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager3',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
			$("#usgTyp3").val($("#usgTypGrid1").getRowData(rowId).usgTypNm);
			setSelectedData3(rowId);
        },
        loadComplete: function(obj){
        	$('#usgTypGrid1').setGridWidth($('#usgTypGridDiv1').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	$("#usgTypGrid1").setSelection($("#usgTypGrid1").getDataIDs()[0]);
        	$("#usgTyp3").val($("#usgTypGrid1").getRowData(1).usgTypNm);
        	setSelectedData3(1);
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#usgTypGrid1').setGridWidth($('#usgTypGridDiv1').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#usgTypGrid2").jqGrid({
		
	   	url:'getProdUsgRatingFacListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   			{ label: 'ifSys' , name: 'ifSys', hidden:true, width : 0},
   			{ label: 'remark' , name: 'remark', hidden:true, width : 0},
   		 	{ label: '사용유형', name: 'usgTyp', hidden: true, width: 100, align: "center" },			//속성 코드
   		 	{ label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTypNm', width: 100 },			//속성 코드
   			{ label: '<spring:message code="LAB.M03.LAB00075"/>', name: 'count', width: 100, align: "center" },			//속성 코드
   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager4_1',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
			$("#usgTyp4").val($("#usgTypGrid2").getRowData(rowId).usgTypNm);
        },
        loadComplete: function(obj){
        	$('#usgTypGrid2').setGridWidth($('#usgTypGridDiv2').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	$("#usgTypGrid2").setSelection($("#usgTypGrid2").getDataIDs()[0]);
        	$("#usgTyp4").val($("#usgTypGrid2").getRowData(1).usgTypNm);
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#usgTypGrid2').setGridWidth($('#usgTypGridDiv2').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#ratingFactorGrid").jqGrid({
		
	   	url: 'getProdRatingFacListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [
			{ label: 'changedTag', name: 'changedTag',  width: 100, hidden: true },
			{ label: 'prodId', name: 'prodId',  width: 100, hidden: true },		
			{ label: 'usgTyp', name: 'usgTyp',  width: 100, hidden: true },		
			{ label: 'effDt', name: 'effDt',  width: 100, hidden: true },			
			{ label: 'expDt', name: 'expDt', width: 100, hidden: true },			
   		 	{ label: 'rateFac', name: 'rateFac', width: 100, hidden: true },		
   			{ label: 'rateFacFlag', name: 'rateFacFlag', width: 100, hidden: true },
   			{ label: '<spring:message code="LAB.M01.LAB00118"/>', name: 'rateFacNm', width: 100},			
   			{ label: '<spring:message code="LAB.M08.LAB00070"/>', name: 'rateFacFlagNm', width: 100}		
   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager4_2',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 323,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
			$("#delRateFac").val($("#ratingFactorGrid").getRowData(rowId).rateFac);
		},
        loadComplete: function(obj){
        	$('#ratingFactorGrid').setGridWidth($('#ratingFactorGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#ratingFactorGrid').setGridWidth($('#ratingFactorGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#usgTypGrid3").jqGrid({
		
	   	url:'getPeriodUsgTypListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   			{ label: 'usgTyp', name: 'usgTyp', width: 100, hidden:true },			//속성 코드
   		 	{ label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTypNm', width: 100},			//속성 코드
   			{ label: '<spring:message code="LAB.M03.LAB00075"/>', name: 'count', width: 100, align:"center"},			//속성 코드
   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager5_1',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
			$("#usgTyp5").val($("#usgTypGrid3").getRowData(rowId).usgTypNm);
        },
        loadComplete: function(obj){
        	$('#usgTypGrid3').setGridWidth($('#usgTypGridDiv3').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	$("#usgTypGrid3").setSelection($("#usgTypGrid3").getDataIDs()[0]);
        	$("#usgTyp5").val($("#usgTypGrid3").getRowData(1).usgTypNm);
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#usgTypGrid3').setGridWidth($('#usgTypGridDiv3').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#timePeriodGrid").jqGrid({
		
	   	url:'getProdUsgTypPeriodListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
			{ label: 'changedTag', name: 'changedTag', width:20, hidden: true },
			{ label: 'prodId', name: 'prodId', width: 100, align:"center" , hidden: true },			
			{ label: 'usgTyp', name: 'usgTyp', width: 100, align:"center" , hidden: true },			
			{ label: 'effDt', name: 'effDt', width: 100, align:"center" , hidden: true },			
			{ label: 'expDt', name: 'expDt', width: 100, align:"center" , hidden: true },
   		 	{ label: '<spring:message code="LAB.M07.LAB00275"/>', name: 'actDay', width: 100, align:"center" },			
   			{ label: '<spring:message code="LAB.M07.LAB00278"/>', name: 'deactDay', width: 100, align:"center" },		
   			{ label: '<spring:message code="LAB.M07.LAB00277"/>', name: 'periodDefId', width: 100 }			
   		],
	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager5_2',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 323,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
			$("#actDay").val($("#timePeriodGrid").getRowData(rowId).actDay);
        },
        loadComplete: function(obj){
        	$('#timePeriodGrid').setGridWidth($('#timePeriodGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#timePeriodGrid').setGridWidth($('#timePeriodGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#usgTypGrid4").jqGrid({
		
	   	url:'getRateInfoUsgTypListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   			{ label: 'ifSys' , name: 'ifSys', hidden:true, width : 0},
   			{ label: 'remark' , name: 'remark', hidden:true, width : 0},
   		 	{ label: 'usgTyp', name: 'usgTyp', hidden:true, width: 100},			//속성 코드
   			{ label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTypNm', width: 100},			//속성 코드
   			{ label: '<spring:message code="LAB.M03.LAB00075"/>', name: 'count', align:"center", width: 100},			//속성 코드
   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager6_1',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
			$("#usgTyp6").val($("#usgTypGrid4").getRowData(rowId).usgTypNm);
        },
        loadComplete: function(obj){
        	$('#usgTypGrid4').setGridWidth($('#usgTypGridDiv4').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	$("#usgTypGrid4").setSelection($("#usgTypGrid4").getDataIDs()[0]);
        	$("#usgTyp6").val($("#usgTypGrid4").getRowData(1).usgTypNm);
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#usgTypGrid4').setGridWidth($('#usgTypGridDiv4').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#tariffGrid").jqGrid({
		
	   	url:'getRateInfoListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
			{ label: 'prodId', name: 'prodId', width: 100, hidden: true },			
			{ label: 'usgTyp', name: 'usgTyp', width: 100, hidden: true },			
			{ label: 'changedTag', name: 'changedTag', width: 100, hidden: true },			
   		 	{ label: '<spring:message code="LAB.M08.LAB00071"/>', name: 'rateId', width: 100},			
   			{ label: '<spring:message code="LAB.M07.LAB00279"/>', name: 'periodCd', width: 100},			
   		 	{ label: '<spring:message code="LAB.M01.LAB00162"/>', name: 'tierSegmentId', width: 100},			
   			{ label: '<spring:message code="LAB.M09.LAB00203"/>', name: 'corrId', width: 100},			
   		 	{ label: '<spring:message code="LAB.M01.LAB00141"/>', name: 'chrgCd', width: 100},			
   			{ label: '<spring:message code="LAB.M08.LAB00109"/>', name: 'effDt', width: 100, align:"center"},			
   		 	{ label: '<spring:message code="LAB.M05.LAB00003"/>', name: 'expDt', width: 100, align:"center"},			
   			{ label: '<spring:message code="LAB.M08.LAB00074"/>', name: 'usgBillingIncrement', width: 100, align:"right"},			
   		 	{ label: '<spring:message code="LAB.M03.LAB00026"/>', name: 'ratePerUnit', width: 100, align:"right"},			
   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager6_2',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
			setSelectedData6(rowId);
        },
        loadComplete: function(obj){
        	$('#tariffGrid').setGridWidth($('#tariffGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	$("#tariffGrid").setSelection($("#tariffGrid").getDataIDs()[0]);
        	if( $("#tariffGrid").getGridParam("reccount") > 0 ) {
        		setSelectedData6(1);
        	}
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#tariffGrid').setGridWidth($('#tariffGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	param.searchProdId = $("#searchProdId").val();
	
	$("#discountGrid").jqGrid({
		
	   	url: 'getDcDedtDefListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
			{ label: 'changedTag', name: 'changedTag', width:20, hidden:true },
			{ label: 'mapProdId', name: 'mapProdId', width: 70, hidden:true },
   			{ label: '중복할인적용방식', name: 'multipleDiscMethod', hidden:true, width: 90 },
   			{ label: '할인항목유형', name: 'usgTypLvl', width: 90, hidden:true },			
   		 	{ label: '사용유형(그룹)', name: 'usgTypGrpCd', width: 90, hidden:true },			
   			{ label: '사용항목유형', name: 'usgItmTyp', width: 90, hidden:true },			
   		 	{ label: '사용항목코드', name: 'usgItmCd', width: 90, hidden:true },			
	   		{ label: '조건과금요소1', name: 'condRateFac1', width: 90, hidden:true },			
	   		{ label: '조건연산자1', name: 'condOperator1', width: 90, hidden:true },			
	   		{ label: '조건연결자1-2', name: 'logicalOperator12', width: 90, hidden:true },			
	   		{ label: '조건과금요소2', name: 'condRateFac2', width: 90, hidden:true },			
	   		{ label: '조건연산자2', name: 'condOperator2', width: 90, hidden:true },			
   		 	{ label: '<spring:message code="LAB.M14.LAB00037"/>', name: 'mapCd', align:"center", width: 80 },			
   			{ label: '<spring:message code="LAB.M14.LAB00029"/>', name: 'mapDiscDedtCd', width: 70 },			
   			{ label: '<spring:message code="LAB.M14.LAB00029"/>', name: 'discDedtNm', align:"center", width: 130 },	
   		 	{ label: '<spring:message code="LAB.M14.LAB00032"/>', name: 'mapDiscDedtPrty', align:"right", width: 90 },			
   			{ label: '<spring:message code="LAB.M08.LAB00109"/>', name: 'mapEffDt', width: 90, align:"center" },			
   		 	{ label: '<spring:message code="LAB.M05.LAB00003"/>', name: 'mapExpDt', width: 90, align:"center" },			
   			{ label: '<spring:message code="LAB.M14.LAB00038"/>', name: 'usgTypLvlNm', width: 90 },			
   		 	{ label: '<spring:message code="LAB.M07.LAB00031"/>', name: 'usgTypGrpCdNm', width: 90 },			
   			{ label: '<spring:message code="LAB.M07.LAB00079"/>', name: 'usgItmTypNm', width: 90 },			
   		 	{ label: '<spring:message code="LAB.M07.LAB00080"/>', name: 'usgItmCdNm', width: 90 },			
	   		{ label: '<spring:message code="LAB.M09.LAB00129"/>', name: 'condRateFacNm1', width: 90 },			
	   		{ label: '<spring:message code="LAB.M09.LAB00132"/>', name: 'condOperatorNm1', width: 90 },			
	   		{ label: '<spring:message code="LAB.M09.LAB00127"/>', name: 'condVal1', width: 90 },			
	   		{ label: '<spring:message code="LAB.M09.LAB00131"/>', name: 'logicalOperatorNm12', width: 90 },			
	   		{ label: '<spring:message code="LAB.M09.LAB00130"/>', name: 'condRateFacNm2', width: 90 },			
	   		{ label: '<spring:message code="LAB.M09.LAB00133"/>', name: 'condOperatorNm2', width: 90 },			
	   		{ label: '<spring:message code="LAB.M09.LAB00128"/>', name: 'condVal2', width: 90 },			
	   		{ label: '<spring:message code="LAB.M07.LAB00280"/>', name: 'tmDivisionFlag', width: 65, align:"center" },			
	   		{ label: '<spring:message code="LAB.M06.LAB00066"/>', name: 'segmentFlag', width: 65, align:"center" },			
	   		{ label: '<spring:message code="LAB.M14.LAB00033"/>', name: 'quantity', width: 70, align:"right" },			
   		],
	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager7',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
			setSelectedData7(rowId);
        },
        loadComplete: function(obj){
        	$('#discountGrid').setGridWidth($('#discountGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	setSelectedData7(1);
        	$("#discountGrid").setSelection($("#discountGrid").getDataIDs()[0]);
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#discountGrid').setGridWidth($('#discountGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#allowanceGrid").jqGrid({
		
	   	url:'getDcDedtDefListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
	   	    { label: 'changedTag', name: 'changedTag', width:20, hidden:true },
			{ label: 'mapProdId', name: 'mapProdId', width: 70, hidden:true },
			{ label: '공제량단위', name: 'amtUnit', width: 70, hidden:true },
			{ label: 'replenishCycl', name: 'replenishCycl', width: 70, hidden:true },
			{ label: '사용유형(그룹)', name: 'usgTypGrpCd', width: 90, hidden:true },
			{ label: '공제사용유형레벨', name: 'usgTypLvl', width: 90, hidden:true },
			{ label: '사용항목유형', name: 'usgItmTyp', width: 90, hidden:true },
			{ label: '사용항목코드', name: 'usgItmCd', width: 90, hidden:true },
			{ label: '반복주기', name: 'durationUnit', width: 70, hidden:true },
			{ label: '공제량FLAG', name: 'amtFlag', width: 65, hidden:true },		
			{ label: '조건과금요소1', name: 'condRateFac1', width: 90, hidden:true },			
	   		{ label: '조건연산자1', name: 'condOperator1', width: 70, hidden:true },			
	   		{ label: '조건연결자1-2', name: 'logicalOperator12', width: 70, hidden:true },			
	   		{ label: '조건과금요소2', name: 'condRateFac2', width: 70, hidden:true },			
	   		{ label: '조건연산자2', name: 'condOperator2', width: 70, hidden:true },			
   		 	{ label: '<spring:message code="LAB.M01.LAB00085"/>', name: 'mapCd', width: 70},			
   			{ label: '<spring:message code="LAB.M01.LAB00085"/>', name: 'mapDiscDedtCd', width: 70},	
   			{ label: '<spring:message code="LAB.M01.LAB00076"/>', name: 'discDedtNm', width: 70},		
   		 	{ label: '<spring:message code="LAB.M01.LAB00077"/>', name: 'replenishCyclNm', width: 70},			
   		 	{ label: '<spring:message code="LAB.M06.LAB00019"/>', name: 'durationUnitNm', width: 70},			
		 	{ label: '<spring:message code="LAB.M06.LAB00020"/>', name: 'duration', width: 70},			
   		 	{ label: '<spring:message code="LAB.M08.LAB00109"/>', name: 'mapEffDt', width: 70, align:"center" },			
   		 	{ label: '<spring:message code="LAB.M05.LAB00003"/>', name: 'mapExpDt', width: 70, align:"center" },			
   			{ label: '<spring:message code="LAB.M01.LAB00069"/>', name: 'mapDiscDedtPrty', width: 90, align:"right"},			
   		 	{ label: '<spring:message code="LAB.M01.LAB00078"/>', name: 'usgTypLvlNm', width: 90},			
   			{ label: '<spring:message code="LAB.M07.LAB00031"/>', name: 'usgTypGrpCdNm', width: 90},			
   		 	{ label: '<spring:message code="LAB.M07.LAB00079"/>', name: 'usgItmTypNm', width: 90},			
	   		{ label: '<spring:message code="LAB.M07.LAB00080"/>', name: 'usgItmCdNm', width: 90},			
	   		{ label: '<spring:message code="LAB.M09.LAB00129"/>', name: 'condRateFacNm1', width: 90},			
	   		{ label: '<spring:message code="LAB.M09.LAB00132"/>', name: 'condOperatorNm1', width: 70},			
	   		{ label: '<spring:message code="LAB.M09.LAB00127"/>', name: 'condVal1', width: 70},			
	   		{ label: '<spring:message code="LAB.M09.LAB00131"/>', name: 'logicalOperatorNm12', width: 70},			
	   		{ label: '<spring:message code="LAB.M09.LAB00130"/>', name: 'condRateFacNm2', width: 70},			
	   		{ label: '<spring:message code="LAB.M09.LAB00133"/>', name: 'condOperatorNm2', width: 70},			
	   		{ label: '<spring:message code="LAB.M09.LAB00128"/>', name: 'condVal2', width: 70},			
	   		{ label: '<spring:message code="LAB.M01.LAB00008"/>', name: 'subscProrateFlag', width: 65, align:"center"},	
	   		{ label: '<spring:message code="LAB.M14.LAB00047"/>', name: 'termProrateFlag', width: 65, align:"center"},			
	   		{ label: '<spring:message code="LAB.M01.LAB00074"/>', name: 'amtFlagNm', width: 65},			
	   		{ label: '<spring:message code="LAB.M01.LAB00075"/>', name: 'amtUnitNm', width: 70},			
	   		{ label: '<spring:message code="LAB.M01.LAB00071"/>', name: 'quantity', width: 65, align:"right"},			
   		],
   		autowidth: true,
		shrinkToFit: true,
	   	rowNum: 10,					//한번에 노출되는 row 수
	   	rowList:  [5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager8',
		sortable : false,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: false,	
		height: 200,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	setSelectedData8(rowId);
        },
        loadComplete: function(obj){
        	$('#allowanceGrid').setGridWidth($('#allowanceGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	$("#allowanceGrid").setSelection($("#allowanceGrid").getDataIDs()[0]);
        	setSelectedData8(1);
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#allowanceGrid').setGridWidth($('#allowanceGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#limitGrid").jqGrid({
		
	   	url:'getLimitDefListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [
	   	    { label: 'prodId', name: 'prodId', width:70, hidden: true },
			{ label: '제한사용유형', name: 'ctrlUsgTypGrpCd', width: 90, hidden: true },			//속성 코드
			{ label: '한도사용유형', name: 'usgTypGrpCd', width: 70, hidden: true },			//속성 코드
			{ label: '사용항목코드', name: 'usgItmCd', width: 70, hidden: true },			//속성 코드
			{ label: '한도사용유형레벨', name: 'usgTypLvl', width: 70,  hidden: true },			//속성 코드
			{ label: '사용항목유형', name: 'usgItmTyp', width: 70,  hidden: true },			//속성 코드
			{ label: '제한사용유형레벨', name: 'usgTypCtrlLvl', width: 90,  hidden: true },			//속성 코드
			{ label: '한도랑FLAG', name: 'amtFlag', width: 90,  hidden: true },			//속성 코드
			{ label: '충전제한유형', name: 'rechrgLmtTypCd', width: 90,  hidden: true },			//속성 코드
			{ label: '<spring:message code="LAB.M14.LAB00012"/>', name: 'lmtCd', width: 70, align:"center"},			//속성 코드
   			{ label: '<spring:message code="LAB.M08.LAB00109"/>', name: 'effDt', width: 70, align:"center"},			//속성 코드
   		 	{ label: '<spring:message code="LAB.M05.LAB00003"/>', name: 'expDt', width: 70, align:"center"},			//속성 코드
   		 	{ label: '<spring:message code="LAB.M14.LAB00008"/>', name: 'usgTypLvlNm', width: 70},			//속성 코드
		 	{ label: '<spring:message code="LAB.M14.LAB00007"/>', name: 'usgTypGrpCdNm', width: 70},			//속성 코드
   		 	{ label: '<spring:message code="LAB.M07.LAB00079"/>', name: 'usgItmTypNm', width: 70},			//속성 코드
   		 	{ label: '<spring:message code="LAB.M07.LAB00080"/>', name: 'usgItmCdNm', width: 70},			//속성 코드
   			{ label: '<spring:message code="LAB.M09.LAB00125"/>', name: 'usgTypCtrlLvlNm', width: 90},			//속성 코드
   		 	{ label: '<spring:message code="LAB.M09.LAB00124"/>', name: 'ctrlUsgTypGrpCdNm', width: 90},			//속성 코드
   			{ label: '<spring:message code="LAB.M14.LAB00006"/>', name: 'amtFlagNm', width: 90},			//속성 코드
   		 	{ label: '<spring:message code="LAB.M14.LAB00011"/>', name: 'quantity', width: 90, align:"right"},			//속성 코드
   		],
   		autowidth: true,
		shrinkToFit: true,
	   	rowNum: 10,					//한번에 노출되는 row 수
	   	rowList:  [5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager9',
		sortable : false,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: false,	
		height: 200,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
			setSelectedData9(rowId);
        },
        loadComplete: function(obj){
        	$('#limitGrid').setGridWidth($('#limitGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	$("#limitGrid").setSelection($("#limitGrid").getDataIDs()[0]);
        	if( $("#limitGrid").getGridParam("reccount") > 0 ) {
        		setSelectedData9(1);
        	}
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$('#limitGrid').setGridWidth($('#limitGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#allUsgTypGrid").setGridWidth($('#allUsgTypGridDiv').width(), false); 
	$("#usgTypGrid").setGridWidth($('#usgTypGridDiv').width(), false);
	$('#usgTypGrid1').setGridWidth($('#usgTypGridDiv1').width(),false);
	$('#usgTypGrid2').setGridWidth($('#usgTypGridDiv2').width(),false); 
	$('#ratingFactorGrid').setGridWidth($('#ratingFactorGridDiv').width(),false);
	$('#usgTypGrid3').setGridWidth($('#usgTypGridDiv3').width(),false);
	$('#timePeriodGrid').setGridWidth($('#timePeriodGridDiv').width(),false);
	$('#usgTypGrid4').setGridWidth($('#usgTypGridDiv4').width(),false);
	$('#tariffGrid').setGridWidth($('#tariffGridDiv').width(),false);
	$('#discountGrid').setGridWidth($('#discountGridDiv').width(),false);
	$('#allowanceGrid').setGridWidth($('#allowanceGridDiv').width(),false);
	$('#limitGrid').setGridWidth($('#limitGridDiv').width(),false);
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#allUsgTypGrid").setGridWidth($('#allUsgTypGridDiv').width(), false); 
		$("#usgTypGrid").setGridWidth($('#usgTypGridDiv').width(), false);
		$('#usgTypGrid1').setGridWidth($('#usgTypGridDiv1').width(),false);
		$('#ratingFactorGrid').setGridWidth($('#ratingFactorGridDiv').width(),false);
		$('#usgTypGrid3').setGridWidth($('#usgTypGridDiv3').width(),false);
		$('#timePeriodGrid').setGridWidth($('#timePeriodGridDiv').width(),false);
		$('#usgTypGrid4').setGridWidth($('#usgTypGridDiv4').width(),false);
		$('#tariffGrid').setGridWidth($('#tariffGridDiv').width(),false);
		$('#discountGrid').setGridWidth($('#discountGridDiv').width(),false);
		$('#allowanceGrid').setGridWidth($('#allowanceGridDiv').width(),false);
		$('#limitGrid').setGridWidth($('#limitGridDiv').width(),false);
	});
	
	//Tab 처리
	$("ul.tabs li").click(function () {
		$("ul.tabs li").removeClass("active");
		$("ul.tabs li").css("color", "#b2b2b2");
		$(this).addClass("active");
		$(this).addClass("active").css("color", "#8ab700");
		$(".tab_content").hide();
	    var activeTab = $(this).attr("rel");
	    $("#" + activeTab).show();
		var index = $("ul.tabs li").index(this);
		$("#allUsgTypGrid").setGridWidth($('#allUsgTypGridDiv').width(), false); 
		$("#usgTypGrid").setGridWidth($('#usgTypGridDiv').width(), false);
		$('#usgTypGrid1').setGridWidth($('#usgTypGridDiv1').width(),false); //컬럼 위치 변경 후 재조정
		$('#usgTypGrid2').setGridWidth($('#usgTypGridDiv2').width(),false); //컬럼 위치 변경 후 재조정
		$('#ratingFactorGrid').setGridWidth($('#ratingFactorGridDiv').width(),false); //컬럼 위치 변경 후 재조정
		$('#usgTypGrid3').setGridWidth($('#usgTypGridDiv3').width(),false);
		$('#timePeriodGrid').setGridWidth($('#timePeriodGridDiv').width(),false);
		$('#usgTypGrid4').setGridWidth($('#usgTypGridDiv4').width(),false);
		$('#tariffGrid').setGridWidth($('#tariffGridDiv').width(),false);
		$('#discountGrid').setGridWidth($('#discountGridDiv').width(),false);
		$('#allowanceGrid').setGridWidth($('#allowanceGridDiv').width(),false);
		$('#limitGrid').setGridWidth($('#limitGridDiv').width(),false);
	});
}

</script>
<!-- title -->
<div class="layer_top">
	<div class="title">
		<spring:message code="LAB.M07.LAB00128" />
	</div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->

<form id="searchNewProductDtl" name="searchNewProductDtl" method="post">
	<input type="hidden" id="searchSoId" name="searchSoId" value="" /> 
	<input type="hidden" id="popupOutputSoId" name="popupOutputSoId" value="${OUTPUT_SO_ID }" />
	<input type="hidden" id="popupOutputProdCd" name="popupOutputProdCd" value="${OUTPUT_PROD_CD }" />
	<input type="hidden" id="popupOutputProdNm" name="popupOutputProdNm" value="${OUTPUT_PROD_NM }" />

	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message
						code="LAB.M01.LAB00225" />
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 20%;">
				<col style="width: 30%;">
				<col style="width: 20%;">
				<col style="width: 30%;">
			</colgroup>
			<thead>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00003" />
					</th>
					<td colspan='3'>
						<select id="searchSoNm" name="searchSoNm" class="w100">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${soInfoList}" var="soInfoList" varStatus="status">
								<option value="${soInfoList.commonCd}">
									${soInfoList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message	code="LAB.M07.LAB00156" />
					</th>
					<td>
						<input type="text" id="searchProdCd" name="searchProdCd" value="${param.searchProdCd}" class="w180" /></td>
					<th>
						<spring:message	code="LAB.M07.LAB00130" />
					</th>
					<td>
						<input type="text" id="searchProdNm" name="searchProdNm" value="${param.searchProdNm}" class="w180" /> 
						<a class="grey-btn"	href="javascript:goSearch();" id="btn_search">
							<span class="search_icon">
								<spring:message	code="LAB.M09.LAB00158" />
							</span>
						</a>
					</td>
				</tr>
			</thead>
		</table>
	</div>

	<div id='GridDiv'>
		<table id="searchNewProductDtlGrid" class="w100p"></table>
		<div id="pager3"></div>
	</div>

	<div class="btn_box">
		<a class="blue-btn" href="javascript:buttonEvent();" id="btn_update"><span
			class="confirm_icon"><spring:message
					code="LAB.M09.LAB00048" /></span></a>
		<!--�ݱ�-->
		<a class="grey-btn close" href="#" id="btnClose"><span
			class="cancel_icon"><spring:message
					code="LAB.M03.LAB00027" /></span></a>
	</div>

</form>

<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	
	openTab(event, 'tab1');
	
	$("#tab_2").bind('click', function() {
		confirmData();
	});
	$("#tab_3").bind('click', function() {
		confirmData();
	});
	$("#tab_4").bind('click', function() {
		confirmData();
	});
	$("#tab_5").bind('click', function() {
		confirmData();
	});
	$("#tab_6").bind('click', function() {
		confirmData();
	});
	$("#tab_7").bind('click', function() {
		confirmData();
	});
	$("#tab_8").bind('click', function() {
		confirmData();
	});
	$("#tab_9").bind('click', function() {
		confirmData();
	});
	
	updateProdUsgTypGrid();
	
	$('#dedtUsgTypLvl').selectmenu({
        change: function() {
        	if( $("#dedtUsgTypLvl").val() == 0 ) {
        		$("#dedtUsgTypGrpCd").empty().html("<option value='0000000000'>0000000000</option>");
        		$('#dedtUsgTypGrpCd').selectmenu("refresh");
        	}
        	else if( $("#dedtUsgTypLvl").val() == 2 ) {
        		$("#dedtUsgTypGrpCd").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgTypGrpCdList1}' var='usgTypGrpCdList1' varStatus='status'><option value='${usgTypGrpCdList1.commonCd}'>${usgTypGrpCdList1.commonCdNm}</option></c:forEach>");
         		$('#dedtUsgTypGrpCd').selectmenu("refresh");
        	}
        	else if( $("#dedtUsgTypLvl").val() == 1 ) {
        		$("#dedtUsgTypGrpCd").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgTypGrpCdList2}' var='usgTypGrpCdList2' varStatus='status'><option value='${usgTypGrpCdList2.commonCd}'>${usgTypGrpCdList2.commonCdNm}</option></c:forEach>");
         		$('#dedtUsgTypGrpCd').selectmenu("refresh");
        	}
        }
	});
	
	$('#discUsgTypLvl').selectmenu({
        change: function() {
        	if( $("#discUsgTypLvl").val() == 0 ) {
        		$("#discUsgTypGrpCd").empty().html("<option value='0000000000'>0000000000</option>");
        		$('#discUsgTypGrpCd').selectmenu("refresh");
        	}
        	else if( $("#discUsgTypLvl").val() == 2 ) {
        		$("#discUsgTypGrpCd").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgTypGrpCdList1}' var='usgTypGrpCdList1' varStatus='status'><option value='${usgTypGrpCdList1.commonCd}'>${usgTypGrpCdList1.commonCdNm}</option></c:forEach>");
         		$('#discUsgTypGrpCd').selectmenu("refresh");
        	}
        	else if( $("#discUsgTypLvl").val() == 1 ) {
        		$("#discUsgTypGrpCd").empty().html("<option><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgTypGrpCdList2}' var='usgTypGrpCdList2' varStatus='status'><option value='${usgTypGrpCdList2.commonCd}'>${usgTypGrpCdList2.commonCdNm}</option></c:forEach>");
         		$('#discUsgTypGrpCd').selectmenu("refresh");
        	}
        	$("#discUsgTypLvl").selectmenu("refresh");
        }
	});
	
	$('#usgTypLvl9').selectmenu({
        change: function() {
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
        }
	});
	
	$('#dedtUsgItmTyp').selectmenu({
        change: function() {
        	if( $("#dedtUsgItmTyp").val() == 0 ) {
        		$("#dedtUsgItmCd").empty().html("<option value='0000000000'>0000000000</option>");
        		$('#dedtUsgItmCd').selectmenu("refresh");
        	}
        	else if( $("#dedtUsgItmTyp").val() == 1 ) {
        		$("#dedtUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgItmCdList1}' var='usgItmCdList1' varStatus='status'><option value='${usgItmCdList1.commonCd}'>${usgItmCdList1.commonCdNm}</option></c:forEach>");
         		$('#dedtUsgItmCd').selectmenu("refresh");
        	}
        	else {
        		$("#dedtUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
         		$('#dedtUsgItmCd').selectmenu("refresh");
        	}
        }
	});
	
	$('#discUsgItmTyp').selectmenu({
        change: function() {
        	if( $("#discUsgItmTyp").val() == 0 ) {
        		$("#discUsgItmCd").empty().html("<option value='0000000000'>0000000000</option>");
        		$('#discUsgItmCd').selectmenu("refresh");
        	}
        	else if( $("#discUsgItmTyp").val() == 1 ) {
        		$("#discUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${usgItmCdList1}' var='usgItmCdList1' varStatus='status'><option value='${usgItmCdList1.commonCd}'>${usgItmCdList1.commonCdNm}</option></c:forEach>");
         		$('#discUsgItmCd').selectmenu("refresh");
        	}
        	else {
        		$("#discUsgItmCd").empty().html("<option value=''><spring:message code='LAB.M07.LAB00195' /></option><c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'><option value='${chrgCdList.commonCd}'>${chrgCdList.commonCdNm}</option></c:forEach>");
         		$('#discUsgItmCd').selectmenu("refresh");
        	}
        }
	});
	
	$('#usgItmTyp9').selectmenu({
        change: function() {
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
        }
	});
	
	$('#usgTypCtrlLvl9').selectmenu({
        change: function() {
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
        }
	});
	
	if( $("#dedtCondRateFac1").val() == "0" ) {
		$("#dedtCondOperator1").val("");
		$('#dedtCondOperator1').selectmenu("refresh");
		$("#dedtCondOperator1").attr("disabled", true);
		$("#dedtCondVal1").attr("readonly", true);
	}
	else {
		$("#dedtCondOperator1").attr("disabled", false);
		$("#dedtCondVal1").attr("readonly", false);
		$('#dedtCondOperator1').selectmenu("refresh");
	}
	
	if( $("#dedtCondRateFac2").val() == "0" ) {
		$("#dedtCondOperator2").val("");
		$('#dedtCondOperator2').selectmenu("refresh");
		$("#dedtCondOperator2").attr("disabled", true);
		$("#dedtCondVal2").attr("readonly", true);
	}
	else {
		$("#dedtCondOperator2").attr("disabled", false);
		$("#dedtCondVal2").attr("readonly", false);
		$('#dedtCondOperator2').selectmenu("refresh");
	}

	$('#dedtCondRateFac1').selectmenu({
        change: function() {
        	if( $("#dedtCondRateFac1").val() == "0" ) {
        		$("#dedtCondOperator1").val("");
        		$("#dedtCondOperator1").attr("disabled", true);
        		$('#dedtCondOperator1').selectmenu("refresh");
        		$("#dedtCondVal1").val("");
        		$("#dedtCondVal1").attr("readonly", true);
        	}
        	else {
        		$("#dedtCondOperator1").attr("disabled", false);
     			$("#dedtCondVal1").attr("readonly", false);
        		$('#dedtCondOperator1').selectmenu("refresh");
        	}
        }
	});
	
	$('#dedtCondRateFac2').selectmenu({
        change: function() {
        	if( $("#dedtCondRateFac2").val() == "0" ) {
        		$("#dedtCondOperator2").val("");
        		$("#dedtCondOperator2").attr("disabled", true);
        		$("#dedtCondOperator2").selectmenu("refresh");
        		$("#dedtCondVal2").val("");
        		$("#dedtCondVal2").attr("readonly", true);
        	}
        	else {
        		$("#dedtCondOperator2").attr("disabled", false);
        		$("#dedtCondOperator2").selectmenu("refresh");
        		$("#dedtCondVal2").attr("readonly", false);
        	}
        }
	});
	
	if( $("#discCondRateFac1").val() == "0" ) {
		$("#discCondOperator1").val("");
		$("#discCondOperator1").attr("disabled", true);
		$('#discCondOperator1').selectmenu("refresh");
		$("#discCondVal1").attr("readonly", true);
	}
	else {
		$("#discCondOperator1").attr("disabled", false);
		$("#discCondVal1").attr("readonly", false);
		$('#discCondOperator1').selectmenu("refresh");
	}
	
	if( $("#discCondRateFac2").val() == "0" ) {
		$("#discCondOperator2").val("");
		$("#discCondOperator2").attr("disabled", true);
		$('#discCondOperator2').selectmenu("refresh");
		$("#discCondVal2").attr("readonly", true);
	}
	else {
		$("#discCondOperator2").attr("disabled", false);
		$("#discCondVal2").attr("readonly", false);
		$('#discCondOperator2').selectmenu("refresh");
	}

	$('#discCondRateFac1').selectmenu({
        change: function() {
        	if( $("#discCondRateFac1").val() == "0" ) {
        		$("#discCondOperator1").val("");
        		$("#discCondOperator1").attr("disabled", true);
        		$('#discCondOperator1').selectmenu("refresh");
        		$("#discCondVal1").val("");
        		$("#discCondVal1").attr("readonly", true);
        	}
        	else {
        		$("#discCondOperator1").attr("disabled", false);
     			$("#discCondVal1").attr("readonly", false);
        		$('#discCondOperator1').selectmenu("refresh");
        	}
        }
	});
	
	$('#discCondRateFac2').selectmenu({
        change: function() {
        	if( $("#discCondRateFac2").val() == "0" ) {
        		$("#discCondOperator2").val("");
        		$("#discCondOperator2").attr("disabled", true);
        		$("#discCondOperator2").selectmenu("refresh");
        		$("#discCondVal2").val("");
        		$("#discCondVal2").attr("readonly", true);
        	}
        	else {
        		$("#discCondOperator2").attr("disabled", false);
        		$("#discCondVal2").attr("readonly", false);
        		$("#discCondOperator2").selectmenu("refresh");
        	}
        }
	});
	
	$('#amtFlag').selectmenu({
        change: function() {
        	if( $("#amtFlag").val() == 1 ) {
        		$("#amtUnit").empty().html('<option><spring:message code="LAB.M07.LAB00195" /></option><c:forEach items="${amtUnitList1}" var="amtUnitList1" varStatus="status"><option value="${amtUnitList1.commonCd}">${amtUnitList1.commonCdNm}</option></c:forEach>');
         		$('#amtUnit').selectmenu("refresh");
        	}
        	else {
        		$("#amtUnit").empty().html('<option><spring:message code="LAB.M07.LAB00195" /></option><c:forEach items="${amtUnitList2}" var="amtUnitList2" varStatus="status"><option value="${amtUnitList2.commonCd}">${amtUnitList2.commonCdNm}</option></c:forEach>');
         		$('#amtUnit').selectmenu("refresh");
        	}
        }
	});
	
	$("#checkDiscTmDivisionFlag").change(function() {
		if( $(this).is(':checked') ) {
			$("#discTmDivisionFlag").val('1');
		}
		else {
			$("#discTmDivisionFlag").val('0');
		}
	});
	
	$("#checkDiscSegmentFlag").change(function() {
		if( $(this).is(':checked') ) {
			$("#discSegmentFlag").val('1');
		}
		else {
			$("#discSegmentFlag").val('0');
		}
	});
	
	$('#checkSubscProrateFlag').change(function () {
	    if( $(this).is(':checked') ) {
	    	$("#subscProrateFlag").val('1');
	    } 
	    else {
	    	$("#subscProrateFlag").val('0');
	    }
	});
	
	$('#checkTermProrateFlag').change(function () {
	    if( $(this).is(':checked') ) {
	    	$("#termProrateFlag").val('1');
	    } 
	    else {
	    	$("#termProrateFlag").val('0');
	    }
	});
	
	if($("#subscProrateFlag").val() == '0' ) {
		$("#checkSubscProrateFlag").attr("checked", false);
	} 
	else if($("#subscProrateFlag").val() == '1' ) {
		$("#checkSubscProrateFlag").attr("checked", true);
	}
	if($("#termProrateFlag").val() == '0' ) {
		$("#checkTermProrateFlag").attr("checked", false);
	} 
	else if($("#termProrateFlag").val() == '1' ) {
		$("#checkTermProrateFlag").attr("checked", true);
	}
	
	if( $("#replenishCycl").val() == 'R' ) {
		$("#th1").attr("style", "visibility:visible");
		$("#td1").attr("style", "visibility:visible");
		$("#th2").attr("style", "visibility:visible");
		$("#td2").attr("style", "visibility:visible");
	}
	
	$("#replenishCycl").selectmenu({
		change: function() {
			if( $("#replenishCycl").val() == 'R' ) {
				$("#th1").attr("style", "visibility:visible");
				$("#td1").attr("style", "visibility:visible");
				$("#th2").attr("style", "visibility:visible");
				$("#td2").attr("style", "visibility:visible");
				$("#duration").val("");
			}
			else {
				$("#th1").attr("style", "visibility:hidden");
				$("#td1").attr("style", "visibility:hidden");
				$("#th2").attr("style", "visibility:hidden");
				$("#td2").attr("style", "visibility:hidden");
				$("#duration").val("1");
			}
		}
	});
	
	//등록 버튼 클릭 시
	$("#btn_search").click(function() {
		var param = new Object();
		var url = "newProductDtlBasicLoadPopUp.ajax";
		$("#popup_dialog").css("width", 1000);
		openModalPopup(url, param);
	});
	
	$("#btn_load").click(function() {
		
		var param = new Object();
		var url = "newProductDtlRatingCdLoadPopUp.ajax";
		$("#popup_dialog").css("width", 1000);
		openModalPopup(url, param);
		
	});
	
	$("#btn_update_rate_fac").click(function() {
		var param = new Object;
		var url = "newProductDtlRateFacUpdatePopUp.ajax";
		
		document.getElementById('tab4').style.display = "block";
		$("#popup_dialog").css("width", 1000);
		openModalPopup(url, param);
	});

	$("#checkDiscMappedInfo").click(function() {
		var param = new Object();
		if( $("#checkDiscMappedInfo").is(":checked") ) {
			param.mappedInfo = '1';
		}
		else {
			param.mappedInfo = '0';
		}
		$("#discountGrid").clearGridData();  // 이전 데이터 삭제
		$("#discountGrid").setGridParam({ postData: param }).trigger("reloadGrid");
	});
	$("#checkDedtMappedInfo").click(function() {
		var param = new Object();
		if( $("#checkDedtMappedInfo").is(":checked") ) {
			param.mappedInfo = '1';
		}
		else {
			param.mappedInfo = '0';
		}
		$("#allowanceGrid").clearGridData();  // 이전 데이터 삭제
		$("#allowanceGrid").setGridParam({ postData: param }).trigger("reloadGrid");
	});
	
	$(window).resize(function() {
		$("#allUsgTypGrid").setGridWidth($('#allUsgTypGridDiv').width(), false); 
		$("#usgTypGrid").setGridWidth($('#usgTypGridDiv').width(), false);
	});
	
	var resultMsg = "${resultMsg}";
	if( resultMsg != null && resultMsg != "" ) {
		alert("${resultMsg}");
	}
});

function goInsertProdUsgTyp() {

	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$(".date").datepicker();
	var date1 = new Date();
	date1.setDate(date1.getDate() + 1);
	result_y = date1.getFullYear();

	if ((date1.getMonth() + 1) < 10) {
		result_m = "0" + (date1.getMonth() + 1);
	} else {
		result_m = date1.getMonth() + 1;
	}
	if ((date1.getDate() + 1) < 10) {
		result_d = "0" + (date1.getDate());
	} else {
		result_d = date1.getDate();
	}

	var date2 = null;

	if ('${lngTyp}' == 'ko') {
		date2 = "" + result_y + result_m + result_d;
	} else {
		date2 = "" + result_m + result_d + result_y;
	}
	
	var param = new Object();
	var param2 = new Object();
	var rowId = jQuery("#allUsgTypGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00096"/>");
		return;
	}
	
	param.usgTyp = $("#allUsgTypGrid").getRowData(rowId).usgTyp;
	param.usgTypNm = $("#allUsgTypGrid").getRowData(rowId).usgTypNm;
	param2.prodId = $("#prodCd").val();
	param2.usgTyp = $("#allUsgTypGrid").getRowData(rowId).usgTyp;
	param2.usgTypNm = $("#allUsgTypGrid").getRowData(rowId).usgTypNm;
	param2.regYn = 'N';
	param2.chrgCd = '';
	param2.chrgCdNm = '';
	param2.effDt = date2;
	param2.expDt = '99991231';
	param2.calendarDefId = '1';
	param2.multipleRateInd = '0';
	param2.tierSetId = '';
	param2.tierStepFlag = '';
	param2.usgMeasureUnit = '';
	param2.commRateFlag = '0';
	param2.currencyCd = '110';
	param2.tmDivisionFlag = '0';
	param2.periodApplMethod = '';
	param2.usgRoundingMethod = '1';
	param2.discFlag = '0';
	param2.multipleDiscFlag = '0';
	param2.multipleDiscCriteria = '';
	param2.usgFeeRoundingOffset = '-1';
	param2.usgFeeRoundingMethod = '1';
	param2.dedtFlag = '0';
	param2.prorateApplGrp = '';
	param2.dedtRateId = '';
	param2.lmtFlag = '0';
	param2.crossDiscFlag = '0';
	param2.realTmAccFlag = '1';
	param2.maxReserveAmt = '';
	param2.unitSvcCd = $("#usgTypGrid1").getRowData(1).unitSvcCd;
	param2.vatRate = '';
	
	$("#allUsgTypGrid").jqGrid('delRowData',rowId);
	$("#usgTypGrid").jqGrid('addRowData', $("#usgTypGrid").getDataIDs().length+1, param);
	$("#usgTypGrid1").jqGrid('addRowData', $("#usgTypGrid1").getDataIDs().length+1, param2);
}

function goDeleteProdUsgTyp() {
	var param = new Object();
	var rowId = $("#usgTypGrid").jqGrid('getGridParam', 'selrow');
	var length = $("#usgTypGrid1").getGridParam("reccount");
	
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056"/>");
		return;
	}
	
	param.usgTyp = $("#usgTypGrid").getRowData(rowId).usgTyp;
	param.usgTypNm = $("#usgTypGrid").getRowData(rowId).usgTypNm;
	
	if( confirm('<spring:message code="MSG.M07.MSG00054"/>') == true ) {
		$("#allUsgTypGrid").jqGrid('addRowData', $("#allUsgTypGrid").getDataIDs().length+1, param);
		for( var i=1; i<=length; i++ ) {
			if( param.usgTyp == $("#usgTypGrid1").getRowData(i).usgTyp ) {
				param.prodId = $("#usgTypGrid1").getRowData(i).prodId;
				param.chrgCd = $("#usgTypGrid1").getRowData(i).chrgCd;
				
				$("#usgTypGrid1").jqGrid('delRowData', i);
				
				$.ajax({
					url : 'newProductDtlProdUsgTypDeleteAction.json',
					type : 'POST',
					data : JSON.stringify(param),
					dataType : 'json',
					contentType : "application/json; charset=UTF-8",
					success : function(data) {
						if( data.result != "0" ) {
							$("#ratingFactorGrid").jqGrid('delRowData', rowId);
							alert('<spring:message code="MSG.M07.MSG00053" />');	//저장되었습니다.
						}
									
					},
					beforeSend : function(data) {
						
					},
					error : function(err) { 
						if( err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != '' ) {
							alert(err.responseJSON.exceptionMsg);
			     		}
						else {
			     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
			     		}		
					}
				});
				$("#usgTypGrid").jqGrid('delRowData', rowId);
				
				return;
			}
		}
	}
}

function goInsertRatePopUp() {
	var param = new Object;
	param.prodId = $("#prodCd").val();
	param.usgTyp = $("#usgTyp4").val();
	
	var url = "newProductDtlRateFacInsertPopUp.ajax";
	
	document.getElementById('tab4').style.display = "block";
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goUpdateRatePopUp() {
	var rowId = $("#ratingFactorGrid").jqGrid('getGridParam', 'selrow');
	
	if( rowId == null ) { 
		alert("<spring:message code="MSG.M07.MSG00096"/>");
		return;
	}
	
	var param = new Object;
	param.prodId = $("#prodCd").val();
	param.usgTyp = $("#usgTyp4").val();
	param.rateFac = $("#ratingFactorGrid").getRowData(rowId).rateFac;
	param.rateFacFlag = $("#ratingFactorGrid").getRowData(rowId).rateFacFlag;
	
	var url = "newProductDtlRateFacUpdatePopUp.ajax";
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goDeleteRateFac() {
	var rowId = $("#ratingFactorGrid").jqGrid('getGridParam', 'selrow');
	var rowId1 = $("#usgTypGrid2").jqGrid('getGridParam', 'selrow');
	var param = new Object();
	
	if( rowId == null ) { 
		alert('<spring:message code="MSG.M07.MSG00056"/>');
		return;
	}
	param.prodId = $("#ratingFactorGrid").getRowData(rowId).prodId;
	param.usgTyp = $("#ratingFactorGrid").getRowData(rowId).usgTyp;
	param.rateFac = $("#ratingFactorGrid").getRowData(rowId).rateFac;
	
	if( confirm('<spring:message code="MSG.M07.MSG00054"/>') == true ) {
		$.ajax({
			url : 'newProductDtlRateFacDeleteAction.json',
			type : 'POST',
			data : JSON.stringify(param),
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				if( data.result != "0" ) {
					$("#ratingFactorGrid").jqGrid('delRowData', rowId);
					$("#usgTypGrid2").jqGrid('setCell', rowId1, 'count', Number($("#usgTypGrid2").getRowData(rowId1).count)-1);
					alert('<spring:message code="MSG.M07.MSG00053" />');	//저장되었습니다.
				}
			},
			beforeSend : function(data) {
				
			},
			error : function(err) { 
				if( err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != '' ) {
					alert(err.responseJSON.exceptionMsg);
	     		}
				else {
	     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
	     		}		
			}
		});
	}
}

function goPeriodInsertPopUp() {
	var param = new Object;
	
	param.prodId = $("#prodCd").val();
	param.usgTyp = $("#usgTyp5").val();
	
	var url = "newProductDtlPeriodInsertPopUp.ajax";
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goPeriodUpdatePopUp() {
	var rowId = $("#timePeriodGrid").jqGrid('getGridParam', 'selrow');
	
	if( rowId == null ) {
		alert('<spring:message code="MSG.M07.MSG00096"/>');
		return;
	}
	var param = new Object;
	
	param.prodId = $("#prodCd").val();
	param.usgTyp = $("#usgTyp5").val();
	param.actDay = $("#timePeriodGrid").getRowData(rowId).actDay;
	param.deactDay = $("#timePeriodGrid").getRowData(rowId).deactDay;
	param.periodDefId = $("#timePeriodGrid").getRowData(rowId).periodDefId;
	
	var url = "newProductDtlPeriodUpdatePopUp.ajax";
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goDeletePeriod() {
	var rowId = $("#timePeriodGrid").jqGrid('getGridParam', 'selrow');
	var rowId1 = $("#usgTypGrid3").jqGrid('getGridParam', 'selrow');
	var param = new Object();
	
	if( rowId == null ) {
		alert('<spring:message code="MSG.M07.MSG00056"/>');
		return;
	}
	
	if( confirm('<spring:message code="MSG.M07.MSG00054"/>') == true ) {
		param.prodId = $("#timePeriodGrid").getRowData(rowId).prodId;
		param.usgTyp = $("#timePeriodGrid").getRowData(rowId).usgTyp;
		param.actDay = $("#timePeriodGrid").getRowData(rowId).actDay;
		
		$.ajax({
			url : 'newProductDtlPeriodDeleteAction.json',
			type : 'POST',
			data : JSON.stringify(param),
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				if( data.result != "0" ) {
					$("#timePeriodGrid").jqGrid('delRowData', rowId);
					$("#usgTypGrid3").jqGrid('setCell', rowId1, 'count', Number($("#usgTypGrid3").getRowData(rowId1).count)-1);
					alert('<spring:message code="MSG.M07.MSG00053" />');	//저장되었습니다.
				}
			},
			beforeSend : function(data) {
				
			},
			error : function(err) { 
				if( err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != '' ) {
					alert(err.responseJSON.exceptionMsg);
	     		}
				else {
	     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
	     		}		
			}
		});
	}
}

function initRateInfo() {
	$("#periodCd").attr("disabled", false);
	$("#tierSegmentId").attr("disabled", false);
	$("#corrId").attr("disabled", false);
	$("#chrgCd6").attr("disabled", false);
	
	$("#rateId").val('');
	$("#periodCd").val('');
	$("#periodCd").selectmenu("refresh");
	$("#tierSegmentId").val('');
	$("#tierSegmentId").selectmenu("refresh");
	$("#corrId").val('');
	$("#corrId").selectmenu("refresh");
	$("#chrgCd6").val('');
	$("#chrgCd6").selectmenu("refresh");
	$("#effDt6").val('');
	$("#expDt6").val('');
	$("#usgBillingIncrement").val('');
	$("#ratePerUnit").val('');
}

function goInsertRateInfo() {
	var param = new Object();
	var rowId = $("#usgTypGrid4").jqGrid('getGridParam', 'selrow');
	
	if( $("#rateId").val() != '' ) {
		alert('<spring:message code="MSG.M09.MSG00049"/>');
		return;
	}
	if( $("#periodCd").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00118"/>');
		return;
	}
	if( $("#tierSegmentId").val() == '' ) {
		alert('<spring:message code="MSG.M01.MSG00044"/>');
		return;
	}
	if( $("#corrId").val() == '' ) {
		alert('<spring:message code="MSG.M09.MSG00058"/>');
		return;
	}
	if( $("#chrgCd6").val() == '' ) {
		alert('<spring:message code="MSG.M01.MSG00040"/>');
		return;
	}
	if( $("#effDt6").val() == '' ) {
		alert('<spring:message code="MSG.M08.MSG00039"/>');
		return;
	}
	if( $("#expDt6").val() == '' ) { 
		alert('<spring:message code="MSG.M05.MSG00002"/>');
		return;
	}
	if( $("#usgBillingIncrement").val() == '' ) {
		alert('<spring:message code="MSG.M08.MSG00029"/>');
		return;
	}
	if( $("#ratePerUnit").val() == '' ) {
		alert('<spring:message code="MSG.M03.MSG00006"/>');
		return;
	}
	
	param.prodId = $("#prodCd").val();
	param.usgTyp = $("#usgTyp6").val();
	param.rateId = '';
	param.periodCd = $("#periodCd").val();
	param.tierSegmentId = $("#tierSegmentId").val();
	param.corrId = $("#corrId").val();
	param.chrgCd = $("#chrgCd6").val();
	param.effDt = $("#effDt6").val() + " 00:00:00";
	param.expDt = $("#expDt6").val() + " 23:59:59";
	param.usgBillingIncrement = $("#usgBillingIncrement").val();
	param.ratePerUnit = $("#ratePerUnit").val();
	param.changedTag = '1';
	
	$("#tariffGrid").jqGrid('addRowData', $("#tariffGrid").getDataIDs().length+1, param);
	$("#usgTypGrid4").jqGrid('setCell', rowId, 'count', Number($("#usgTypGrid4").getRowData(rowId).count)+1);
}

function goUpdateRateInfo() {
	if( $("#rateId").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00098"/>');
		return;
	}
	
	var rowId = $("#tariffGrid").jqGrid('getGridParam', 'selrow');
	
	if( rowId == null ) {
		alert('<spring:message code="MSG.M07.MSG00096"/>');
		return;
	}
	if( $("#usgBillingIncrement").val() == '' ) {
		alert('<spring:message code="MSG.M08.MSG00029"/>');
		return;
	}
	if( $("#ratePerUnit").val() == '' ) {
		alert('<spring:message code="MSG.M03.MSG00006"/>');
		return;
	}
	if( $("#expDt6").val() == '' ) {
		alert('<spring:message code="MSG.M05.MSG00002"/>');
		return;
	}
	$("#tariffGrid").jqGrid('setCell', rowId, 'changedTag', '1');
	$("#tariffGrid").jqGrid('setCell', rowId, 'expDt', $("#expDt6").val() + " 23:59:59");	
	$("#tariffGrid").jqGrid('setCell', rowId, 'usgBillingIncrement', $("#usgBillingIncrement").val());
	$("#tariffGrid").jqGrid('setCell', rowId, 'ratePerUnit', $("#ratePerUnit").val());
}

function goDeleteRateInfo() {
	var rowId = $("#tariffGrid").jqGrid('getGridParam', 'selrow');
	var rowId1 = $("#usgTypGrid4").jqGrid('getGridParam', 'selrow');
	var param = new Object();
	
	if( rowId == null ) {
		alert('<spring:message code="MSG.M07.MSG00056"/>');
		return;
	}
	if( confirm('<spring:message code="MSG.M07.MSG00054"/>') == true ) {
		param.prodId = $("#tariffGrid").getRowData(rowId).prodId;
		param.usgTyp = $("#tariffGrid").getRowData(rowId).usgTyp;
		param.periodCd = $("#tariffGrid").getRowData(rowId).periodCd;
		param.tierSegmentId = $("#tariffGrid").getRowData(rowId).tierSegmentId;
		param.corrId = $("#tariffGrid").getRowData(rowId).corrId;
		param.chrgCd = $("#tariffGrid").getRowData(rowId).chrgCd;
		param.effDt = $("#tariffGrid").getRowData(rowId).effDt;
		param.expDt = $("#tariffGrid").getRowData(rowId).expDt;
		param.rateId = $("#tariffGrid").getRowData(rowId).rateId;

		$("#tariffGrid").jqGrid('delRowData', rowId);
		$("#usgTypGrid4").jqGrid('setCell', rowId1, 'count', Number($("#usgTypGrid4").getRowData(rowId1).count)-1);

		if( param.rateId != '' ) {
			$.ajax({
				url : 'newProductDtlRateInfoDeleteAction.json',
				type : 'POST',
				data : JSON.stringify(param),
				dataType : 'json',
				contentType : "application/json; charset=UTF-8",
				success : function(data) {
					if( data.result != "0" ) {
						alert('<spring:message code="MSG.M07.MSG00053" />');	//저장되었습니다.
					}
				},
				beforeSend : function(data) {
					
				},
				error : function(err) { 
					if( err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != '' ) {
						alert(err.responseJSON.exceptionMsg);
		     		}
					else {
		     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
		     		}		
				}
			});
		}
	}
}
function goUpdateDisc() {
	var rowId = $("#discountGrid").jqGrid('getGridParam', 'selrow');
	var op1=null;
	
}

function goUpdateDisc() {
	var rowId = $("#discountGrid").jqGrid('getGridParam', 'selrow');
	var op1=null, op2=null, val1=null, val2=null;
	
	if( $("#mapDiscPrty").val() == '' ) {
		alert('<spring:message code="MSG.M14.MSG00009"/>');
		return;
	}
	if( $("#discMapEffDt").val() == '' ) {
		alert('<spring:message code="MSG.M08.MSG00039"/>');
		return;
	}
	if( $("#discMapExpDt").val() == '' ) {
		alert('<spring:message code="MSG.M05.MSG00002"/>');
		return;
	}
	if( $("#discUsgTypLvl").val() == '' ) {
		alert('<spring:message code="MSG.M14.MSG00011"/>');
		return;
	}	
	if( $("#discUsgTypGrpCd").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00019"/>');
		return;
	}
	if( $("#discUsgItmTyp").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00049"/>');
		return;
	}
	if( $("#discUsgItmCd").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00050"/>');
		return;
	}
	if( $("#discQuantity").val() == '' ) { 
		alert('<spring:message code="MSG.M14.MSG00010"/>');
		return;
	}
	if( $("#discQuantity").val().replace(/\./, '').length > 13 ) {
		alert("할인율이 너무 큽니다.");
		return;
	}
	if( $("#discCondRateFac1").val() != '0' ) {
		if( $("#discCondOperator1").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00033"/>');
			return;
		}
		op1 = $("#discCondOperator1").val();
		if( $("#discCondVal1").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00030"/>');
			return;
		}
		val1 = $("#discCondVal1").val();
	}
	if( $("#discCondRateFac2").val() != '0' ) {
		if( $("#discLogicalOperator12").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00032"/>');
			return;
		}
		if( $("#discCondOperator2").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00034"/>');
			return;
		}
		op2 = $("#discCondOperator2").val();
		if( $("#discCondVal2").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00031"/>');
			return;
		}
		val2 = $("#discCondVal2").val();
	}
	
	$("#discountGrid").jqGrid('setCell', rowId, 'mapDiscDedtPrty', $("#mapDiscPrty").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'multipleDiscMethod', $("#multipleDiscMethod").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'mapExpDt', $("#discMapExpDt").val() + ' 23:59:59');
	$("#discountGrid").jqGrid('setCell', rowId, 'usgTypLvl', $("#discUsgTypLvl").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'usgTypLvlNm', $("#discUsgTypLvl option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#discountGrid").jqGrid('setCell', rowId, 'usgTypGrpCd', $("#discUsgTypGrpCd").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'usgTypGrpCdNm', $("#discUsgTypGrpCd option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#discountGrid").jqGrid('setCell', rowId, 'usgItmTyp', $("#discUsgItmTyp").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'usgItmTypNm', $("#discUsgItmTyp option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#discountGrid").jqGrid('setCell', rowId, 'usgItmCd', $("#discUsgItmCd").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'usgItmCdNm', $("#discUsgItmCd option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#discountGrid").jqGrid('setCell', rowId, 'condRateFac1', $("#discCondRateFac1").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'condRateFacNm1', $("#discCondRateFac1 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#discountGrid").jqGrid('setCell', rowId, 'condOperator1', op1);
	$("#discountGrid").jqGrid('setCell', rowId, 'condOperatorNm1', $("#discCondOperator1 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#discountGrid").jqGrid('setCell', rowId, 'condVal1', val1);
	$("#discountGrid").jqGrid('setCell', rowId, 'logicalOperator12', $("#discLogicalOperator12").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'logicalOperatorNm12', $("#discLogicalOperator12 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#discountGrid").jqGrid('setCell', rowId, 'condRateFac2', $("#discCondRateFac2").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'condRateFacNm2', $("#discCondRateFac2 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#discountGrid").jqGrid('setCell', rowId, 'condOperator2', op2);
	$("#discountGrid").jqGrid('setCell', rowId, 'condOperatorNm2', $("#discCondOperator2 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#discountGrid").jqGrid('setCell', rowId, 'condVal2', val2);
	$("#discountGrid").jqGrid('setCell', rowId, 'tmDivisionFlag', $("#discTmDivisionFlag").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'segmentFlag', $("#discSegmentFlag").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'quantity', $("#discQuantity").val());
	$("#discountGrid").jqGrid('setCell', rowId, 'discDedtAddTag', '1');
	$("#discountGrid").jqGrid('setCell', rowId, 'mapProdId', $("#prodCd").val());
}

function goDeleteDisc() {
	var rowId = $("#discountGrid").jqGrid('getGridParam', 'selrow');
	var param = new Object();
	
	if( rowId == null ) {
		alert('<spring:message code="MSG.M07.MSG00056"/>');
		return;
	}
	
	param.mapCd = $("#discountGrid").getRowData(rowId).mapCd;
	$("#discountGrid").jqGrid('delRowData', rowId);
	
	if( param.mapCd != '' ) {
		$.ajax({
			url : 'newProductDtlDiscDedtDefDeleteAction.json',
			type : 'POST',
			data : JSON.stringify(param),
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				if( data.result != "0" ) {
					alert('<spring:message code="MSG.M07.MSG00053" />');	//저장되었습니다.
				}
			},
			beforeSend : function(data) {
				
			},
			error : function(err) { 
				if( err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != '' ) {
					alert(err.responseJSON.exceptionMsg);
	     		}
				else {
	     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
	     		}		
			}
		});
	}
}

function goSearchDiscGrid() {
	var param = new Object();
	
	param.searchDiscDedtCd = $("#searchDiscCd").val();
	param.searchDiscDedtCdNm = $("#searchDiscCdNm").val();
	
	$("#discountGrid").clearGridData();  
	$("#discountGrid").setGridParam({ postData: param }).trigger("reloadGrid");
}

function goUpdateDedt() {
	var rowId = $("#allowanceGrid").jqGrid('getGridParam', 'selrow');
	var op1=null, op2=null, val1=null, val2=null, logic=null;
	
	if( $("#mapDedtPrty").val() == '' ) {
		alert('<spring:message code="MSG.M01.MSG00021"/>');
		return;
	}
	if( $("#dedtUsgTypLvl").val() == '' ) {
		alert('<spring:message code="MSG.M01.MSG00026"/>');
		return;
	}
	if( $("#dedtUsgTypGrpCd").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00019"/>');
		return;
	}
	if( $("#dedtUsgItmTyp").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00049"/>');
		return;
	}
	if( $("#dedtUsgItmCd").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00050"/>');
		return;
	}
	if( $("#amtFlag").val() == '' ) {
		alert('<spring:message code="MSG.M01.MSG00023"/>');
		return;
	}
	if( $("#amtUnit").val() == '' ) {
		alert('<spring:message code="MSG.M01.MSG00024"/>');
		return;
	}
	if( $("#quantity").val() == '' ) { 
		alert('<spring:message code="MSG.M01.MSG00025"/>');
		return;
	}
	if( $("#quantity").val().replace(/\./g, '').length > 13 ) {
		alert("공제량이 너무 큽니다.");
		return;		
	}	
	if( $("#dedtCondRateFac1").val() != '0' ) {
		if( $("#dedtCondOperator1").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00033"/>');
			return;
		}
		op1 = $("#dedtCondOperator1").val();
		if( $("#dedtCondVal1").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00030"/>');
			return;
		}
		val1 = $("#dedtCondVal1").val();
	}
	if( $("#dedtCondRateFac2").val() != '0' ) {
		if( $("#dedtLogicalOperator12").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00032"/>');
			return;
		}
		logic = $("#dedtLogicalOperator12").val();
		if( $("#dedtCondOperator2").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00034"/>');
			return;
		}
		op2 = $("#dedtCondOperator2").val();
		if( $("#dedtCondVal2").val() == '' ) {
			alert('<spring:message code="MSG.M09.MSG00031"/>');
			return;
		}
		val2 = $("#dedtCondVal2").val();
	}
	
	$("#allowanceGrid").jqGrid('setCell', rowId, 'mapDiscDedtPrty', $("#mapDedtPrty").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'usgTypLvl', $("#dedtUsgTypLvl").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'usgTypLvlNm', $("#dedtUsgTypLvl option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'usgTypGrpCd', $("#dedtUsgTypGrpCd").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'usgTypGrpCdNm', $("#dedtUsgTypGrpCd option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'usgItmTyp', $("#dedtUsgItmTyp").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'usgItmTypNm', $("#dedtUsgItmTyp option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'usgItmCd', $("#dedtUsgItmCd").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'usgItmCdNm', $("#dedtUsgItmCd option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condRateFac1', $("#dedtCondRateFac1").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condRateFacNm1', $("#dedtCondRateFac1 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condOperator1', op1);
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condOperatorNm1', $("#dedtCondOperator1 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condVal1', val1);
	$("#allowanceGrid").jqGrid('setCell', rowId, 'logicalOperator12', $("#dedtLogicalOperator12").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'logicalOperatorNm12', $("#dedtLogicalOperator12 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condRateFac2', $("#dedtCondRateFac2").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condRateFacNm2', $("#dedtCondRateFac2 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condOperator2', op2);
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condOperatorNm2', $("#dedtCondOperator2 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'condVal2', val2);
	$("#allowanceGrid").jqGrid('setCell', rowId, 'subscProrateFlag', $("#subscProrateFlag").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'termProrateFlag', $("#termProrateFlag").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'amtFlag', $("#amtFlag").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'amtFlagNm', $("#amtFlag option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'amtUnit', $("#amtUnit").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'amtUnitNm', $("#amtUnit option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'quantity', $("#quantity").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'replenishCycl', $("#replenishCycl").val());
	$("#allowanceGrid").jqGrid('setCell', rowId, 'replenishCyclNm', $("#replenishCycl option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
	$("#allowanceGrid").jqGrid('setCell', rowId, 'mapExpDt', $("#dedtMapExpDt").val() + ' 23:59:59');
	$("#allowanceGrid").jqGrid('setCell', rowId, 'discDedtAddTag', '1');
	$("#allowanceGrid").jqGrid('setCell', rowId, 'mapProdId', $("#prodCd").val());
}

function goDeleteDedt() {
	var rowId = $("#allowanceGrid").jqGrid('getGridParam', 'selrow');
	var param = new Object();
	
	if( rowId == null ) {
		alert('<spring:message code="MSG.M07.MSG00056"/>');
		return;
	}
	
	param.mapCd = $("#allowanceGrid").getRowData(rowId).mapCd;
	$("#allowanceGrid").jqGrid('delRowData', rowId);
	
	if( param.mapCd != '' ) {
		$.ajax({
			url : 'newProductDtlDiscDedtDefDeleteAction.json',
			type : 'POST',
			data : JSON.stringify(param),
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				if( data.result != "0" ) {
					alert('<spring:message code="MSG.M07.MSG00053" />');	//저장되었습니다.
				}
			},
			beforeSend : function(data) {
				
			},
			error : function(err) { 
				if( err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != '' ) {
					alert(err.responseJSON.exceptionMsg);
	     		}
				else {
	     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
	     		}		
			}
		});
	}
}

function goSearchDedtGrid() {
	var param = new Object();
	
	param.searchDiscDedtCd = $("#searchDedtCd").val();
	param.searchDiscDedtCdNm = $("#searchDedtCdNm").val();
	
	$("#allowanceGrid").clearGridData();  
	$("#allowanceGrid").setGridParam({ postData: param }).trigger("reloadGrid");
}

function initLmt() {
	$("#lmtCd").val('');
	$("#effDt9").val('');
	$("#expDt9").val('');
	$("#quantity9").val('');
}

function goInsertLmt() {
	if( $("#lmtCd").val() != '' ) {
		alert('<spring:message code="MSG.M07.MSG00090"/>');	
		return;
	}
	else {
		if( $("#effDt9").val() == '' ) {
			alert('<spring:message code="MSG.M08.MSG00039"/>');
			return;
		}
		if( $("#expDt9").val() == '' ) {
			alert('<spring:message code="MSG.M05.MSG00002"/>');
			return;
		}
		if( $("#usgTypLvl9").val() == '' ) {
			alert('<spring:message code="MSG.M14.MSG00006"/>');
			return;
		}
		if( $("#usgTypGrpCd9").val() == '' ) {
			alert('<spring:message code="MSG.M14.MSG00005"/>');
			return;
		}
		if( $("#usgItmTyp9").val() == '' ) {
			alert('<spring:message code="MSG.M07.MSG00049"/>');
			return;
		}
		if($("#usgItmCd9").val() == '' ) {
			alert('<spring:message code="MSG.M07.MSG00050"/>');
			return;		
		}
		if( $("#usgTypCtrlLvl9").val() != '0' ) {
			if( $("#ctrlUsgTypGrpCd9").val() == '' ) {
				alert('<spring:message code="MSG.M09.MSG00029"/>');
				return;
			}
		}
		if( $("#amtFlag9").val() == '' ) {
			alert('<spring:message code="MSG.M14.MSG00004"/>');
			return;
		}
		if( $("#quantity9").val() == '' ) {
 			alert('<spring:message code="MSG.M14.MSG00007"/>');
			return;
		}
		if( $("#quantity9").val().replace(/\./g, '').length > 13 ) {
			alert("한도치가 너무 큽니다.")
			return;
		}
		if( $("#rechrgLmtTypCd").val() == '' ) {
			alert('<spring:message code="MSG.M10.MSG00028"/>');
			return;
		}
		
		var param = new Object();
		param.prodId = $("#prodCd").val();
		param.effDt = $("#effDt9").val() + ' 00:00:00';
		param.expDt = $("#expDt9").val() + ' 23:59:59';
		param.usgTypLvl = $("#usgTypLvl9").val();
		param.usgTypLvlNm = $("#usgTypLvl9 option:selected").text().replace(/	/g, "").replace(/\n/g, "");
		param.usgTypGrpCd = $("#usgTypGrpCd9").val();
		param.usgTypGrpCdNm = $("#usgTypGrpCd9 option:selected").text().replace(/	/g, "").replace(/\n/g, "");
		param.usgItmTyp = $("#usgItmTyp9").val();
		param.usgItmTypNm = $("#usgItmTyp9 option:selected").text().replace(/	/g, "").replace(/\n/g, "");
		param.usgItmCd = $("#usgItmCd9").val();
		param.usgItmCdNm = $("#usgItmCd9 option:selected").text().replace(/	/g, "").replace(/\n/g, "");
		param.usgTypCtrlLvl = $("#usgTypCtrlLvl9").val();
		param.usgTypCtrlLvlNm = $("#usgTypCtrlLvl9 option:selected").text().replace(/	/g, "").replace(/\n/g, "");
		param.ctrlUsgTypGrpCd = $("#ctrlUsgTypGrpCd").val();
		param.ctrlUsgTypGrpCdNm = $("#ctrlUsgTypGrpCd option:selected").text().replace(/	/g, "").replace(/\n/g, "");
		param.amtFlag = $("#amtFlag9").val();
		param.amtFlagNm = $("#amtFlag9 option:selected").text().replace(/	/g, "").replace(/\n/g, "");
		param.quantity = $("#quantity9").val();
		param.rechrgLmtTypCd = $("#rechrgLmtTypCd").val();
		param.rechrgLmtTypCdNm = $("#rechrgLmtTypCd option:selected").text().replace(/	/g, "").replace(/\n/g, "");
		
		$("#limitGrid").jqGrid('addRowData', $("#limitGrid").getDataIDs().length+1, param);
	}
}

function goUpdateLmt() {
	if( $("#lmtCd").val() == '' ) {
		alert('<spring:message code="MSG.M03.MSG00026"/>');
		return;
	}
	else {
		var rowId = $("#limitGrid").jqGrid('getGridParam', 'selrow');
		
		if( $("#effDt9").val() == '' ) {
			alert('<spring:message code="MSG.M08.MSG00039"/>');
			return;
		}
		if( $("#expDt9").val() == '' ) {
			alert('<spring:message code="MSG.M05.MSG00002"/>');
			return;
		}
		if( $("#usgTypLvl9").val() == '' ) {
			alert('<spring:message code="MSG.M14.MSG00006"/>');
			return;
		}
		if( $("#usgTypGrpCd9").val() == '' ) {
			alert('<spring:message code="MSG.M14.MSG00005"/>');
			return;
		}
		if( $("#usgItmTyp9").val() == '' ) {
			alert('<spring:message code="MSG.M07.MSG00049"/>');
			return;
		}
		if($("#usgItmCd9").val() == '' ) {
			alert('<spring:message code="MSG.M07.MSG00050"/>');
			return;		
		}
		if( $("#usgTypCtrlLvl9").val() != '0' ) {
			if( $("#ctrlUsgTypGrpCd9").val() == '' ) {
 				alert('<spring:message code="MSG.M09.MSG00029"/>');
				return;
			}
		}
		if( $("#amtFlag9").val() == '' ) {
			alert('<spring:message code="MSG.M14.MSG00004"/>');
			return;
		}
		if( $("#quantity9").val() == '' ) {
			alert('<spring:message code="MSG.M14.MSG00007"/>');
			return;
		}
		if( $("#quantity9").val().replace(/\./g, '').length > 13 ) {
			alert("한도치가 너무 큽니다.")
			return;
		}
		if( $("#rechrgLmtTypCd").val() == '' ) {
			alert('<spring:message code="MSG.M10.MSG00028"/>');
			return;
		}
		
		$("#limitGrid").jqGrid('setCell', rowId, 'expDt', $("#expDt9").val() + ' 23:59:59');
		$("#limitGrid").jqGrid('setCell', rowId, 'usgTypLvl', $("#usgTypLvl9").val());
		$("#limitGrid").jqGrid('setCell', rowId, 'usgTypLvlNm', $("#usgTypLvl9 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
		$("#limitGrid").jqGrid('setCell', rowId, 'usgTypGrpCd', $("#usgTypGrpCd9").val());
		$("#limitGrid").jqGrid('setCell', rowId, 'usgTypGrpCdNm', $("#usgTypGrpCd9 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
		$("#limitGrid").jqGrid('setCell', rowId, 'usgItmTyp', $("#usgItmTyp9").val());
		$("#limitGrid").jqGrid('setCell', rowId, 'usgItmTypNm', $("#usgItmTyp9 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
		$("#limitGrid").jqGrid('setCell', rowId, 'usgItmCd', $("#usgItmCd9").val());
		$("#limitGrid").jqGrid('setCell', rowId, 'usgItmCdNm', $("#usgItmCd9 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
		$("#limitGrid").jqGrid('setCell', rowId, 'usgTypCtrlLvl', $("#usgTypCtrlLvl9").val());
		$("#limitGrid").jqGrid('setCell', rowId, 'usgTypCtrlLvlNm', $("#usgTypCtrlLvl9 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
		$("#limitGrid").jqGrid('setCell', rowId, 'ctrlUsgTypGrpCd', $("#ctrlUsgTypGrpCd9").val());
		$("#limitGrid").jqGrid('setCell', rowId, 'ctrlUsgTypGrpCdNm', $("#ctrlUsgTypGrpCd9 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
		$("#limitGrid").jqGrid('setCell', rowId, 'amtFlag', $("#amtFlag9").val());
		$("#limitGrid").jqGrid('setCell', rowId, 'amtFlagNm', $("#amtFlag9 option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
		$("#limitGrid").jqGrid('setCell', rowId, 'quantity', $("#quantity9").val());
		$("#limitGrid").jqGrid('setCell', rowId, 'rechrgLmtTypCd', $("#rechrgLmtTypCd").val());
	}
}

function goDeleteLmt() {
	var rowId = $("#limitGrid").jqGrid('getGridParam', 'selrow');
	var param = new Object();
	
	if( rowId == null ) {
		alert('<spring:message code="MSG.M07.MSG00056"/>');
		return;
	}
	
	if( confirm('<spring:message code="MSG.M07.MSG00054"/>') == true ) {
		param.lmtCd = $("#limitGrid").getRowData(rowId).lmtCd;
		param.prodId = $("#limitGrid").getRowData(rowId).prodId;
		
		$("#limitGrid").jqGrid('delRowData', rowId);
		
		if( param.lmtCd != '' ) {
			$.ajax({
				url : 'newProductDtlLimitDeleteAction.json',
				type : 'POST',
				data : JSON.stringify(param),
				dataType : 'json',
				contentType : "application/json; charset=UTF-8",
				success : function(data) {
					if( data.result != "0" ) {
						alert('<spring:message code="MSG.M07.MSG00053" />');	//저장되었습니다.
					}
				},
				beforeSend : function(data) {
					
				},
				error : function(err) { 
					if( err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != '' ) {
						alert(err.responseJSON.exceptionMsg);
		     		}
					else {
		     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
		     		}		
				}
			});
		}
	}
}

function updateProdUsgTypGrid() {

	$("#calendarDefId").selectmenu({
		change: function() {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'calendarDefId', $("#calendarDefId").val());
			$("#calendarDefId").selectmenu("refresh");
		}
	});
	
	$("#periodApplMethod").selectmenu({
		change: function() {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'periodApplMethod', '1');
			$("#periodApplMethod").selectmenu("refresh");
		}
	});
	
	$("#tierSetId").selectmenu({
		change: function() {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'tierSetId', $("#tierSetId").val());
			$("#tierSetId").selectmenu("refresh");
		}
	});
	
	$("#tierStepFlag").selectmenu({
		change: function() {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'tierStepFlag', $("#tierStepFlag").val());
			$("#tierStepFlag").selectmenu("refresh");
		}
	});

	$("#checkCommRateFlag").click(function() {
		if( $("#checkCommRateFlag").is(":checked") ) {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'commRateFlag', '1');
		}
		else {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'commRateFlag', '0');
		}
	});
	
	$("#chrgCd").selectmenu({
		change: function() {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'chrgCd', $("#chrgCd").val());
			$("#chrgCd").selectmenu("refresh");
		}
	});
	
	$("#usgMeasureUnit").selectmenu({
		change: function() {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'usgMeasureUnit', $("#usgMeasureUnit").val());
			$("#usgMeasureUnit").selectmenu("refresh");
		}
	});
	
	$("#currencyCd").selectmenu({
		change: function() {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'currencyCd', $("#currencyCd").val());
			$("#currencyCd").selectmenu("refresh");
		}
	});
	$("#usgFeeRoundingMethod").selectmenu({
		change: function() {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'usgFeeRoundingMethod', $("#usgFeeRoundingMethod").val());
			$("#usgFeeRoundingMethod").selectmenu("refresh");
		}
	});
	
	$("#usgFeeRoundingOffset").change(function() {
		$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'usgFeeRoundingOffset', $("#usgFeeRoundingOffset").val());
	});
	
	$("#usgRoundingMethod").selectmenu({
		change: function() {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'usgRoundingMethod', $("#usgRoundingMethod").val());
			$("#usgRoundingMethod").selectmenu("refresh");
		}
	});
	
	$("#checkDiscFlag").change(function() {
		if( $(this).is(':checked') ) {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'discFlag', '1');
			$("#tab_7").bind('click', function() {
				openTab(event, 'tab7');
			});
		}
		else {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'discFlag', '0');
			$("#tab_7").unbind('click');
		}
	});
	
	$("#checkDedtFlag").change(function() {
		if( $(this).is(':checked') ) {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'dedtFlag', '1');
			$("#tab_8").bind('click', function() {
				openTab(event, 'tab8');
			});
		}
		else {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'dedtFlag', '0');
			$("#tab_8").unbind('click');
		}
	});
	
	$("#checkTmDivisionFlag").click(function() {
		if( $("#checkTmDivisionFlag").is(":checked") ) {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'tmDivisionFlag', '1');
		}
		else {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'tmDivisionFlag', '0');
		}
	});
	
	$("#checkLmtFlag").change(function() {
		if( $(this).is(':checked') ) {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'lmtFlag', '1');
			$("#tab_9").bind('click', function() {
				openTab(event, 'tab9');
			});
		}
		else {
			$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'lmtFlag', '0');
			$("#tab_9").unbind('click');
		}
	});
	
	$("#maxReserveAmt").change(function() {
		$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'maxReserveAmt', $("#maxReserveAmt").val());
	});

	$("#effDt1").change(function() {
		$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'effDt', $("#effDt1").val());
	});
	
	$("#expDt1").change(function() {
		$("#usgTypGrid1").jqGrid('setCell', $("#usgTypGrid1").jqGrid('getGridParam', 'selrow'), 'expDt', $("#expDt1").val());
	});
}

function goReloadGrids() {
	var param = new Object();
	
	$("#allUsgTypGrid").clearGridData();  // 이전 데이터 삭제
	$("#allUsgTypGrid").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#usgTypGrid").clearGridData();  // 이전 데이터 삭제
	$("#usgTypGrid").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#usgTypGrid1").clearGridData();  // 이전 데이터 삭제
	$("#usgTypGrid1").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#usgTypGrid2").clearGridData();  // 이전 데이터 삭제
	$("#usgTypGrid2").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#ratingFactorGrid").clearGridData();  // 이전 데이터 삭제
	$("#ratingFactorGrid").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#usgTypGrid3").clearGridData();  // 이전 데이터 삭제
	$("#usgTypGrid3").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#timePeriodGrid").clearGridData();  // 이전 데이터 삭제
	$("#timePeriodGrid").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#usgTypGrid4").clearGridData();  // 이전 데이터 삭제
	$("#usgTypGrid4").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#tariffGrid").clearGridData();  // 이전 데이터 삭제
	$("#tariffGrid").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#discountGrid").clearGridData();  // 이전 데이터 삭제
	$("#discountGrid").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#allowanceGrid").clearGridData();  // 이전 데이터 삭제
	$("#allowanceGrid").setGridParam({ postData: param }).trigger("reloadGrid");
	$("#limitGrid").clearGridData();  // 이전 데이터 삭제
	$("#limitGrid").setGridParam({ postData: param }).trigger("reloadGrid");
}

function goSearchUnitSvcCdPopUp() {
	var param = new Object();
	var url = "newProductDtlRatingCdLoadPopUp.ajax";

	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function valChanged() {
	
}

function goSave() {
	var param = new Object();
	var param1 = new Object();
	
	if( $("#prodCd").val() == '' ) {
		alert('<spring:message code="MSG.M03.MSG00020"/>');
		return;
	}
	if( $("#prodOfferTyp").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00067"/>');
		return;
	}
	if( $("#prodPriority").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00063"/>');
		return;
	}
	if( $("#maxReserveAmt").val() == '' ) {
		alert('<spring:message code="MSG.M08.MSG00018"/>');
		return;
	}

	if( $("#periodApplMethod").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00115"/>');
		return;
	}
	
	if( $("#tierStepFlag").val() == '' ) {
		alert('<spring:message code="MSG.M15.MSG00031"/>');
		return;
	}
	
	if( $("#chrgCd").val() == '' ) {
		alert('<spring:message code="MSG.M01.MSG00040"/>');
		return;
	}
	
	if( $("#maxReserveAmt").val() == '' ) {
		alert('<spring:message code="MSG.M08.MSG00018"/>');
		return;
	}
	
	if( $("#usgMeasureUnit").val() == '' ) {
		alert('<spring:message code="MSG.M07.MSG00016"/>');
		return;
	}
	
	param.prodId = $("#prodCd").val();
	param.prodFamily = $("#prodFamily").val();
	param.prodOfferTyp = $("#prodOfferTyp").val();
	param.description = $("#description").val();
	param.prodPriority = $("#prodPriority").val();
	param.effDt = $("#effDt").val().replace(/-/g, '') + '000000';
	param.expDt = $("#expDt").val().replace(/-/g, '') + '235959';
	param.updateUsgTypValList = $("#usgTypGrid1").getRowData();			// tab3
	param.updateRateFacValList = $("#ratingFactorGrid").getRowData();	// tab4
	param.updatePeriodValList = $("#timePeriodGrid").getRowData();		// tab5
	param.updateRateInfoValList = $("#tariffGrid").getRowData();		// tab6
	param.updateDiscDefValList = $("#discountGrid").getRowData();		// tab7
	param.updateDedtDefValList = $("#allowanceGrid").getRowData();		// tab8
	param.updateLmtValList = $("#limitGrid").getRowData();				// tab9

	$.ajax({
		url : 'newProductDtlSaveAction.json',
		type : 'POST',
		data : JSON.stringify(param),
		dataType : 'json',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			if( data.result != "0" ) {
				goReloadGrids();
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
			}
		},
		beforeSend : function(data) {
			
		},
		error : function(err) { 
			if( err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != '' ) {
				alert(err.responseJSON.exceptionMsg);
     		}
			else {
     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
     		}		
		}
	});
}

function btnActive(id) {
	$('#'+id).addClass(grey-btn);
	$('#'+id).removeClass('white-btn');
	$('#'+id).removeClass('non-active');
	$('#'+id).removeClass('disabled');
}

function checkValidation() {
	var param = new Object();
	param.chrg_cd = $("#chrg_cd").val();
	param.description = $("#description").val();
	param.usg_typ = $("#usg_typ").val();
	param.usg_typ_nm = $("#usg_typ_nm").val();
	
	return param;
}

function openTab(evt, tabName) {
	var i, tabcontent, tablinks;
	tabcontent = document.getElementsByClassName("tabcontent");
	
	for( i=0; i<tabcontent.length; i++ ) {
		tabcontent[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablinks");
	for( i=0; i<tablinks.length; i++ ) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	document.getElementById(tabName).style.display = "block";
	evt.currentTarget.className += " active";
}

function confirmData() {
	alert("데이터를 확인하세요.");
}

</script>

<style>

ul.tab {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}


<style>
ul.tab li a {
    display: inline-block;
    color: black;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    transition: 0.3s;
    font-size: 17px;
}

ul.tab li .active {
    background-color: #ccc;
}

.tabcontent {
    display: none;
    padding: 5px 5px;
    border: 1px solid #ccc;
    border-top: none;
}
</style>


<!-- NaviGator -->
<div class="h3_bg">
	<h3>${menuName}</h3>
	<!-- Navigator -->
		<div class="nav">                                        
		   <a href="#" class="home"></a>
			<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
			<span>&gt;</span>${mu.menuName}
		</c:forEach>
		</div>                               
   <!--// Navigator -->
</div>

<form id="newProductDtl" name="newProductDtl" method="post">
	<input type="hidden" id="searchProdId" name="searchProdId" value=""/>
	
	<div id="container">
	    <ul id="tab" class="tabs mb0 mt20">
	        <li>
	        	<a id="tab_1" href="javascript:void(0)" class="tablinks active" onclick='openTab(event, "tab1")'>
	        		<spring:message code="LAB.M07.LAB00149"/>
	        	</a>
	        </li>
	        <li>
	        	<a id="tab_2" href="javascript:void(0)" class="tablinks" >
	        		<spring:message code="LAB.M07.LAB00035"/>
	        	</a>
	        </li>
	        <li>
	        	<a id="tab_3" href="javascript:void(0)" class="tablinks" >
	        		<spring:message code="LAB.M07.LAB00029"/>
	        	</a>
	        </li>
	        <li>
	        	<a id="tab_4" href="javascript:void(0)" class="tablinks" >
	        		<spring:message code="LAB.M01.LAB00118"/>
	        	</a>
	        </li>
	        <li>
	        	<a id="tab_5" href="javascript:void(0)" class="tablinks" >
	        		<spring:message code="LAB.M08.LAB00106"/>
	        	</a>
	        </li>
	        <li>
	        	<a id="tab_6" href="javascript:void(0)" class="tablinks" >
 	        		<spring:message code="LAB.M08.LAB00075"/>
	        	</a>
	        </li>
	        <li>
	        	<a id="tab_7" href="javascript:void(0)" class="tablinks" >
	        		<spring:message code="LAB.M14.LAB00034"/>
	        	</a>
	        </li>
	        <li>
	        	<a id="tab_8" href="javascript:void(0)" class="tablinks" >
	        		<spring:message code="LAB.M01.LAB00081"/>
	        	</a>
	        </li>
	        <li>
	        	<a id="tab_9" href="javascript:void(0)" class="tablinks" >
	        		<spring:message code="LAB.M14.LAB00009"/>
	        	</a>
	        </li>
	    </ul>

		<!-- #tab1 -->
		<div id="tab1" class="tabcontent">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M07.LAB00150"/>
				</h4><br>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:15%;">
					<col style="width:35%;">
					<col style="width:15%;">
					<col style="width:35%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00156"/><font color="red">*</font>
						</th>
						<td>
							<input id="prodCd" name="prodCd" value="" type="text" class="w270" readonly="readonly">
						</td>
						<td>
							<a href="#" class="grey-btn" id="btn_search"><spring:message code="LAB.M01.LAB00225"/></a> 
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00130"/><font color="red">*</font>
						</th>
						<td>
							<input id="prodNm" name="prodNm" value="" type="text" class="w270" readonly="readonly">
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00124"/>
						</th> 
						<td>
							<input id="attrNm" name="attrNm" value="" type="text" class="w270" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00127"/>
						</th>
						<td>
							<input id="prodFamily" name="prodFamily" value="" type="text" class="w270">
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00152"/>
						</th> 
						<td>
							<select id="prodOfferTyp" name="prodOfferTyp" class="w270">
								<option value="">
									<spring:message code="LAB.M07.LAB00195" />
								</option>
								<c:forEach items="${prodOfferTypList}" var="prodOfferTypList" varStatus="status">
									<option value="${prodOfferTypList.commonCd}">
										${prodOfferTypList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00143"/>
						</th>
						<td colspan='3'>
							<input id="description" name="description" type="number" class="w270p">
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M06.LAB00089"/></h4><br>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:15%;">
					<col style="width:35%;">
					<col style="width:15%;">
					<col style="width:35%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00157"/>
						</th>
						<td>
							<select id="prodState" name="prodState" class="w270">
								<option value="">
									<spring:message code="LAB.M07.LAB00195" />
								</option>
								<c:forEach items="${prodStateList}" var="prodStateList" varStatus="status">
									<option value="${prodStateList.commonCd}">
										${prodStateList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00117"/>
						</th>
						<td>
							<input id="prodPriority" name="prodPriority" type="text" class="w270">
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00147"/>
						</th>
						<td>
							<div class="date_box">
					   			<div class="inp_date w220">
						 			<input type="text"  id="effDt" name="effDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
								</div>
							</div>
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00155"/>
						</th> 
						<td>
							<div class="date_box">
					   			<div class="inp_date w220">
						 			<input type="text"  id="expDt" name="expDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
								</div>
							</div>
						</td>
					</tr>
				</thead>
			</table>
		</div>
		
	    <!-- #tab2 -->
		<div id="tab2" class="tabcontent">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M07.LAB00139"/></h4><br>
			</div>
			
			<table class="writeview">
				<colgroup>
					<col style="width:10%;">
					<col style="width:90%;">
				</colgroup>
				<thead> 
					   <tr>
							<th><spring:message code="LAB.M07.LAB00130"/></th>
							<td colspan='3'>
								<input type="text" id="prodNm2" name="prodNm2" class="w270" readonly="readonly"/>
							</td>
						</tr>
				</thead>
			</table> 
			<br>
			<!--사용자부 -->
			<div class="lay_box2">
				<div class="lay_left2">
					<div id="allUsgTypGridDiv">
						<table id="allUsgTypGrid" class="w100p"></table>
						<div id="pager2_1"></div>
					</div>
				</div>
				
				<img id='lay_prev_btn' class='lay_prev_icon' src='/images/icon/ico_cal_prev.png' onclick='javascript:goDeleteProdUsgTyp();' width="25" height="20">
				<img id='lay_next_btn' class='lay_next_icon' src='/images/icon/ico_cal_next.png' onclick='javascript:goInsertProdUsgTyp();' width="25" height="20">
				
				<div class="lay_right2">
					<div id="usgTypGridDiv">
						<table id="usgTypGrid" class="w100p"></table>
						<div id="pager2_2"></div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- #tab3 -->
		<div id="tab3" class="tabcontent">
			<h4 class="sub_title">
				<spring:message code="LAB.M07.LAB00140"/>
			</h4>
			<div>
				<div class="lay_box">	
					<div class="lay_left">
						<div>
							<h4>&nbsp;</h4>
							<br>
							<table class="writeview">
							<colgroup>
								<col style="width:10%;">
								<col style="width:90%;">
							</colgroup>
								<thead>
									<tr>
										<th>
											<spring:message code="LAB.M07.LAB00130"/>
										</th>
										<td>
											<input type="text" id="prodNm3" name="prodNm3" class="w270" readonly="readonly"/>
										</td>
									</tr>
									<tr>
										<th>
											<spring:message code="LAB.M07.LAB00146"/>
										</th>
										<td>
											<input type="text" id="usgTyp3" name="usgTyp3" class="w270" readonly="readonly"/>
										</td>
									</tr>
								</thead>
							</table>
							<br>
						</div>
						<div id="usgTypGridDiv1">
							<table id="usgTypGrid1" class="w100p">
							</table>
							<div id="pager3"></div>
						</div>
					</div>		
					<div class="lay_right">
						<div id="usgTyp">
							<h5>
								<spring:message code="LAB.M07.LAB00034"/>
							</h5>
							<br>
							<table class="writeview">
								<colgroup>
									<col style="width:15%;">
									<col style="width:35%;">
									<col style="width:15%;">
									<col style="width:35%;">
								</colgroup>
								<tr>
									<th>
										<spring:message code="LAB.M11.LAB00002"/>
									</th>
									<td>
										<select id="calendarDefId" name="calendarDefId" class="w240">
											<c:forEach items="${calendarList}" var="calendarList" varStatus="status">
												<option value="${calendarList.commonCd}">
													${calendarList.commonCdNm}
												</option>
											</c:forEach>
										</select>
									</td>
									<th>
										<spring:message code="LAB.M07.LAB00276"/>
									</th>
									<td>
										<select id="periodApplMethod" name="periodApplMethod" class="w240">
											<option value="">
												<spring:message code='LAB.M07.LAB00195' />
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
										<spring:message code="LAB.M01.LAB00163"/>
									</th>
									<td>
										<select id="tierSetId" name="tierSetId" class="w240">
											<option value="">
												<spring:message code='LAB.M07.LAB00195' />
											</option>
										</select>
									</td>
									<th>
										<spring:message code="LAB.M15.LAB00087"/>
									</th>
									<td>
										<select id="tierStepFlag" name="tierStepFlag" class="w240">
											<option value="">
												<spring:message code='LAB.M07.LAB00195' />
											</option>
											<c:forEach items="${tierList}" var="tierList" varStatus="status">
												<option value="${tierList.commonCd}">
													${tierList.commonCdNm}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M01.LAB00104"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input style="text-align:right" type="checkbox" name="checkCommRateFlag" id="checkCommRateFlag"/>
									</th>
									<td colspan='3'>
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M01.LAB00141"/>
									</th>
									<td>
										<select id="chrgCd" name="chrgCd" class="w240">
											<option value="">
												<spring:message code='LAB.M07.LAB00195' />
											</option>
											<c:forEach items="${chrgCdList}" var="chrgCdList" varStatus="status">
												<option value="${chrgCdList.commonCd}">
													${chrgCdList.commonCdNm}
												</option>
											</c:forEach>
										</select>
									</td>
									<th>
										<spring:message code="LAB.M06.LAB00106"/>
									</th>
									<td>
										<div class="date_box">
											<div class="inp_date w270">
												<input type="text" id="unitSvcCd" name="unitSvcCd" class="w240" readonly="readonly"/>
												<a href="javascript:goSearchUnitSvcCdPopUp();" id="btn_load" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>	
											</div>	
										</div>
									</td>
								</tr>
							</table>
							<br>
							<h5>
								<spring:message code="LAB.M01.LAB00122"/>
							</h5>
							<br>
							<table class="writeview">
								<colgroup>
									<col style="width:15%;">
									<col style="width:35%;">
									<col style="width:15%;">
									<col style="width:35%;">
								</colgroup>
								<tr>
									<th>
										<spring:message code="LAB.M07.LAB00023"/>
									</th>
									<td>
										<select id="usgMeasureUnit" name="usgMeasureUnit" class="w240">
											<option value="">
												<spring:message code='LAB.M07.LAB00195' />
											</option>
											<c:forEach items="${usgMeasureUnitList}" var="usgMeasureUnitList" varStatus="status">
												<option value="${usgMeasureUnitList.commonCd}">
													${usgMeasureUnitList.commonCdNm}
												</option>
											</c:forEach>
										</select>
									</td>
									<th>
										<spring:message code="LAB.M12.LAB00005"/>
									</th>
									<td>
										<select id="currencyCd" name="currencyCd" class="w240">
											<c:forEach items="${currencyCdList}" var="currencyCdList" varStatus="status">
												<option value="${currencyCdList.commonCd}">
													${currencyCdList.commonCdNm}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</table>
							<br>
							<h5>
								<spring:message code="LAB.M07.LAB00024"/>
							</h5>
							<br>
							<table class="writeview">
								<colgroup>
									<col style="width:15%;">
									<col style="width:35%;">
									<col style="width:15%;">
									<col style="width:35%;">
								</colgroup>
								<tr>
									<th>
										<spring:message code="LAB.M07.LAB00019"/>
									</th>
									<td>
										<select id="usgFeeRoundingMethod" name="usgFeeRoundingMethod" class="w240">
											<c:forEach items="${roundingList}" var="roundingList" varStatus="status">
												<option value="${roundingList.commonCd}">
													${roundingList.commonCdNm}
												</option>
											</c:forEach>
										</select>
									</td>
									<th>
										<spring:message code="LAB.M07.LAB00020"/>
									</th>
									<td>
										<input type="text" id="usgFeeRoundingOffset" name="usgFeeRoundingOffset" class="w240" style="text-align: right" />
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M07.LAB00025"/>
									</th>
									<td colspan='3'>
										<select id="usgRoundingMethod" name="usgRoundingMethod" class="w240">
											<c:forEach items="${roundingList}" var="roundingList" varStatus="status">
												<option value="${roundingList.commonCd}">
													${roundingList.commonCdNm}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</table>
							<br>
							<h5>
								<spring:message code="LAB.M14.LAB00013"/>
							</h5>
							<br>
							<table class="writeview">
								<colgroup>
									<col style="width:15%;">
									<col style="width:35%;">
									<col style="width:15%;">
									<col style="width:35%;">
								</colgroup>
								<tr>
									<td colspan='4'>
										<input type="checkbox" name="checkDiscFlag" id="checkDiscFlag"/>
										<spring:message code="LAB.M14.LAB00024"/>
										&nbsp;&nbsp;&nbsp;&nbsp;		
										<input type="checkbox" name="checkDedtFlag" id="checkDedtFlag"/>
										<spring:message code="LAB.M01.LAB00062"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="checkTmDivisionFlag" id="checkTmDivisionFlag"/>
										<spring:message code="LAB.M07.LAB00280"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="checkLmtFlag" id="checkLmtFlag"/>
										<spring:message code="LAB.M14.LAB00004"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M08.LAB00032"/>
									</th>
									<td colspan='3'>
										<input type="text" id="maxReserveAmt" name="maxReserveAmt" class="w240" style="text-align:right"/>
									</td>
								</tr>
							</table>
							<br>
							<h5>
								<spring:message code="LAB.M08.LAB00109"/>
							</h5>
							<br>
							<table class="writeview">
								<colgroup>
									<col style="width:15%;">
									<col style="width:35%;">
									<col style="width:15%;">
									<col style="width:35%;">
								</colgroup>
								<tr>
									<th>
										<spring:message code="LAB.M08.LAB00109"/>
									</th>
									<td	colspan='3'>
										<div class="date_box">
						   					<div class="inp_date w220">
							 					<input type="text"  id="effDt1" name="effDt1" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M05.LAB00003"/>
									</th>
									<td colspan='3'>
										<div class="date_box">
						   					<div class="inp_date w220">
							 					<input type="text" id="expDt1" name="expDt1" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	  	
  		<!-- #tab4 -->
  		<div id="tab4" class="tabcontent">
  		<h4 class="sub_title">
  			<spring:message code="LAB.M07.LAB00138"/>
  		</h4>
			<div>
				<input type="hidden" id="delRateFac" name="delRateFac" />
				<div class="lay_box">	
					<div class="lay_left">
						<div id="usgTypGridDiv2">
							<h4>&nbsp;</h4>
							<br>
							<table class="writeview">
								<tr>
									<th>
										<spring:message code="LAB.M07.LAB00130"/>
									</th>
									<td>
										<input type="text" id="prodNm4" name="prodNm4" class="w270" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M07.LAB00146"/>
									</th>
									<td>
										<input type="text" id="usgTyp4" name="usgTyp4" class="w270" readonly="readonly"/>
									</td>
								</tr>
							</table>
							<br>
							<table id="usgTypGrid2" class="w100p"></table>
							<div id="pager4_1"></div>
						</div>
					</div>		
					<div class="lay_right">
						<div id="usgTyp">
							<div id="ratingFactorGridDiv">
								<h4>&nbsp;</h4>
								<br>
								<table id="ratingFactorGrid" class="w100p"></table>
								<div id="pager4_2"></div>
							</div>
						</div>
						<div class="main_btn_box">
							<div class="fr">		
								<!--등록-->
								<ntels:auth auth="${menuAuthC}">
								<a class="grey-btn" href="javascript:goInsertRatePopUp();" id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
								</ntels:auth>
								<!--수정-->
								<ntels:auth auth="${menuAuthU}">
								<a class="grey-btn" href="javascript:goUpdateRatePopUp();" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
								</ntels:auth>		
								<!--삭제-->
								<ntels:auth auth="${menuAuthD}">
								<a class="grey-btn" href="javascript:goDeleteRateFac();" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
								</ntels:auth>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
  		
  		<!-- #tab5 -->
  		<div id="tab5" class="tabcontent">
  		<h4 class="sub_title">
  			<spring:message code="LAB.M07.LAB00136"/>
  		</h4>
			<div>
				<div class="lay_box">	
					<div class="lay_left">
						<div id="usgTypGridDiv3">
							<h4>&nbsp;</h4>
							<br>
							<table class="writeview">
								<tr>
									<th>
										<spring:message code="LAB.M07.LAB00130"/>
									</th>
									<td>
										<input type="text" id="prodNm5" name="prodNm5" class="w270" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M07.LAB00146"/>
									</th>
									<td>
										<input type="text" id="usgTyp5" name="usgTyp5" class="w270" readonly="readonly"/>
									</td>
								</tr>
							</table>
							<br>
							<table id="usgTypGrid3" class="w100p"></table>
							<div id="pager5_1"></div>
						</div>
					</div>		
					<div class="lay_right">
						<div id="usgTyp">
							<div id="timePeriodGridDiv">
								<h4>&nbsp;</h4>
								<br>
								<table id="timePeriodGrid" class="w100p"></table>
								<div id="pager5_2"></div>
							</div>
						</div>
						<div style="text-align: right">
							<!--등록-->
							<ntels:auth auth="${menuAuthC}">
							<a class="grey-btn" href="javascript:goPeriodInsertPopUp();"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
							</ntels:auth>		
							<!--수정-->
							<ntels:auth auth="${menuAuthU}">
							<a class="grey-btn" href="javascript:goPeriodUpdatePopUp();" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
							</ntels:auth>	
							<!--삭제-->
							<ntels:auth auth="${menuAuthD}">
							<a class="grey-btn" href="javascript:goDeletePeriod();" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
							</ntels:auth>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!-- #tab6 -->
		<div id="tab6" class="tabcontent">
			<h4 class="sub_title">
				<spring:message code="LAB.M08.LAB00076"/>
			</h4>
			<div>
				<div class="lay_box">	
					<div class="lay_left">
						<div id="usgTypGridDiv4">
							<h4>&nbsp;</h4>
							<br>
							<table class="writeview">
								<tr>
									<th>
										<spring:message code="LAB.M07.LAB00130"/>
									</th>
									<td>
										<input type="text" id="prodNm6" name="prodNm6" class="w270" readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M07.LAB00146"/>
									</th>
									<td>
										<input type="text" id="usgTyp6" name="usgTyp6" class="w270" readonly="readonly"/>
									</td>
								</tr>
							</table>
							<br>
							<table id="usgTypGrid4" class="w100p"></table>
							<div id="pager6_1"></div>
						</div>
					</div>		
					<div class="lay_right">
						<div id="usgTyp">
							<h5>
								<spring:message code="LAB.M01.LAB00165"/>
							</h5>
							<br>
							<table class="writeview">
								<colgroup>
									<col style="width:15%;">
									<col style="width:35%;">
									<col style="width:15%;">
									<col style="width:35%;">
								</colgroup>
								<tr>
									<th>
										<spring:message code="LAB.M08.LAB00071"/>	
									</th>
									<td>
										<input type="text" id="rateId" name="rateId" class="w220" readonly="readonly"/>
									</td>
									<th>
										<spring:message code="LAB.M07.LAB00279"/>
									</th>
									<td>
										<select id="periodCd" name="periodCd" class="w220" disabled="disabled">
											<option value="">
												<spring:message code='LAB.M07.LAB00195' />
											</option>
											<option value="0000000000">
												0000000000
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M01.LAB00162"/>
									</th>
									<td>
										<select id="tierSegmentId" name="tierSegmentId" class="w220" disabled="disabled">
											<option value="">
												<spring:message code='LAB.M07.LAB00195' />
											</option>
											<option value="0000000000">
												0000000000
											</option>
										</select>
									</td>
									<th>
										<spring:message code="LAB.M09.LAB00203"/>
									</th>
									<td>
										<select id="corrId" name="corrId" class="w220" disabled="disabled">
											<option value="">
												<spring:message code='LAB.M07.LAB00195' />
											</option>
											<option value="0000000000">
												0000000000
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M01.LAB00141"/>
									</th>
									<td colspan='3'>
										<select id="chrgCd6" name="chrgCd6" class="w220" disabled="disabled">
											<option value=''>
												<spring:message code='LAB.M07.LAB00195' />
											</option>
											<c:forEach items='${chrgCdList}' var='chrgCdList' varStatus='status'>
												<option value='${chrgCdList.commonCd}'>
													${chrgCdList.commonCdNm}
												</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</table>
							<br>
							<h5>
								<spring:message code="LAB.M08.LAB00109"/>
							</h5>
							<br>
							<table class="writeview">
								<colgroup>
									<col style="width:15%;">
									<col style="width:35%;">
									<col style="width:15%;">
									<col style="width:35%;">
								</colgroup>
								<tr>
									<th>
										<spring:message code="LAB.M08.LAB00109"/>
									</th>
									<td	colspan='3'>
										<div class="date_box">
						   					<div class="inp_date w170">
							 					<input type="text"  id="effDt6" name="effDt6" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<th>
										<spring:message code="LAB.M05.LAB00003"/>
									</th>
									<td colspan='3'>
										<div class="date_box">
						   					<div class="inp_date w170">
							 					<input type="text" id="expDt6" name="expDt6" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
											</div>
										</div>
									</td>
								</tr>
							</table>
							<br>
							<h5>
								<spring:message code="LAB.M09.LAB00050"/>
							</h5>
							<br>
							<table class="writeview">
								<colgroup>
									<col style="width:15%;">
									<col style="width:35%;">
									<col style="width:15%;">
									<col style="width:35%;">
								</colgroup>
								<tr>
									<th>
										<spring:message code="LAB.M08.LAB00074"/>
									</th>
									<td>
										<input type="text" id="usgBillingIncrement" name="usgBillingIncrement" class="w220" style="text-align:right"/>
									</td>
									<th>
										<spring:message code="LAB.M03.LAB00026"/>
									</th>
									<td>
										<input type="text" id="ratePerUnit" name="ratePerUnit" class="w220" style="text-align:right"/>
									</td>
								</tr>
							</table>
							<br>
						</div>
					</div>
				</div>
				<br>
				<div id="tariffGridDiv">
					<table id="tariffGrid" class="w100p"></table>
					<div id="pager6_2"></div>
				</div>
				<div style="text-align: right">
					<!--초기화-->		
					<a class="grey-btn"  href="javascript:initRateInfo();"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
					<!--등록-->
					<ntels:auth auth="${menuAuthC}">
					<a class="grey-btn" href="javascript:goInsertRateInfo();" id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
					</ntels:auth>
					<!--수정-->
					<ntels:auth auth="${menuAuthU}">
					<a class="grey-btn" href="javascript:goUpdateRateInfo();" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
					</ntels:auth>		
					<!--삭제-->
					<ntels:auth auth="${menuAuthD}">
					<a class="grey-btn" href="javascript:goDeleteRateInfo();" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
					</ntels:auth>
				</div>
			</div>
		</div>
		
		<!-- #tab7 -->
		<div id="tab7" class="tabcontent">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M14.LAB00035"/>
				</h4>
				<br>
			</div>
			<h5>
				<spring:message code="LAB.M01.LAB00226"/>
			</h5>
			<table class="writeview">
				<colgroup>
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:21%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M14.LAB00037"/>
						</th>
						<td>
							<input id="discMapCd" name="discMapCd" value="" type="text" class="w270" readonly="readonly">
						</td>
						<th>
							<spring:message code="LAB.M14.LAB00029"/>
						</th>
						<td colspan='3'>
							<input id="mapDiscCd" name="mapDiscCd" value="" type="text" class="w270" readonly="readonly">
							<input id="discNm" name="discNm" value="" type="text" class="w530" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00130"/>
						</th>
						<td>
							<input id="prodNm7" name="prodNm7" value="" type="text" class="w270" readonly="readonly">
						</td>
						<th>
							<spring:message code="LAB.M14.LAB00032"/>
						</th> 
						<td>
							<input id="mapDiscPrty" name="mapDiscPrty" value="" type="text" class="w270" >
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00195"/>
						</th>
						<td>
							<select id="multipleDiscMethod" name="multipleDiscMethod" class="w270" >
								<option value=""><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${multipleDiscMethodList}" var="multipleDiscMethodList" varStatus="status">
									<option value="${multipleDiscMethodList.commonCd}">
										${multipleDiscMethodList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						</tr>
						<tr>
							<th>
								<spring:message code="LAB.M08.LAB00109"/>
							</th>
							<td colspan='3'>
								<div class="date_box">
				   					<div class="inp_date w220">
					 					<input type="text"  id="discMapEffDt" name="discMapEffDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
									</div>
								</div>
							</td>
							<th>
								<spring:message code="LAB.M05.LAB00003"/>
							</th>
							<td>
								<div class="date_box">
				   					<div class="inp_date w220">
					 					<input type="text"  id="discMapExpDt" name="discMapExpDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
									</div>
								</div>
							</td>
						</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h5 class="sub_title">
					<spring:message code="LAB.M09.LAB00051"/>
				</h5>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:21%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M14.LAB00038"/>
						</th>
						<td>
							<select id="discUsgTypLvl" name="discUsgTypLvl" class="w270" >
								<c:forEach items="${usgTypLvlList}" var="usgTypLvlList" varStatus="status">
									<option value="${usgTypLvlList.commonCd}">
										${usgTypLvlList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00031"/>
						</th>
						<td colspan='3'>
							<select id="discUsgTypGrpCd" name="discUsgTypGrpCd" class="w270" >
							</select>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00079"/>
						</th>
						<td>
							<select id="discUsgItmTyp" name="discUsgItmTyp" class="w270" >
								<c:forEach items="${usgItmTypList}" var="usgItmTypList" varStatus="status">
									<option value="${usgItmTypList.commonCd}">
										${usgItmTypList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00080"/>
						</th>
						<td colspan='3'>
							<select id="discUsgItmCd" name="discUsgItmCd" class="w270" >
							</select>
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h5 class="sub_title">
					<spring:message code="LAB.M14.LAB00036"/>
				</h5>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:21%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M09.LAB00129"/>
						</th>
						<td>
							<select id="discCondRateFac1" name="discCondRateFac1" class="w270" >
								<option value="0"><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${condRateFacList}" var="condRateFacList" varStatus="status">
									<option value="${condRateFacList.commonCd}">
										${condRateFacList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00132"/>
						</th>
						<td>
							<select id="discCondOperator1" name="discCondOperator1" class="w270" >
								<option value=""><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${condOperatorList}" var="condOperatorList" varStatus="status">
									<option value="${condOperatorList.commonCd}">
										${condOperatorList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00127"/>
						</th>
						<td>
							<input id="discCondVal1" name="discCondVal1" value="" type="text" class="w270" >
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M09.LAB00131"/>
						</th>
						<td colspan='5'>
							<select id="discLogicalOperator12" name="discLogicalOperator12" class="w270" >
								<option value="0"><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${logicalOperatorList}" var="logicalOperatorList" varStatus="status">
									<option value="${logicalOperatorList.commonCd}">
										${logicalOperatorList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M09.LAB00130"/>
						</th>
						<td>
							<select id="discCondRateFac2" name="discCondRateFac2" class="w270" >
								<option value="0"><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${condRateFacList}" var="condRateFacList" varStatus="status">
									<option value="${condRateFacList.commonCd}">
										${condRateFacList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00133"/>
						</th>
						<td>
							<select id="discCondOperator2" name="discCondOperator2" class="w270" >
								<option value=""><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${condOperatorList}" var="condOperatorList" varStatus="status">
									<option value="${condOperatorList.commonCd}">
										${condOperatorList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00128"/>
						</th>
						<td>
							<input id="discCondVal2" name="discCondVal2" value="" type="text" class="w270" >
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h5 class="sub_title">
					<spring:message code="LAB.M08.LAB00147"/>
				</h5>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:25%;">
					<col style="width:25%;">
					<col style="width:15%;">
					<col style="width:35%;">
				</colgroup>
				<thead>
					<tr>
						<td colspan='2'>
							<input type="hidden" name="discTmDivisionFlag" id="discTmDivisionFlag"/>
							<input type="checkbox" name="checkDiscTmDivisionFlag" id="checkDiscTmDivisionFlag"/><spring:message code="LAB.M07.LAB00280"/>
							&nbsp;&nbsp;&nbsp;	
							<input type="hidden" name="discSegmentFlag" id="discSegmentFlag"/>
							<input type="checkbox" name="checkDiscSegmentFlag" id="checkDiscSegmentFlag"/><spring:message code="LAB.M06.LAB00066"/>
						</td>
						<th>
							<spring:message code="LAB.M14.LAB00033"/>
						</th>
						<td>
							<input id="discQuantity" name="discQuantity" value="" type="number" class="w270" style="text-align:right">
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<table class="writeview">
				<colgroup>
					<col style="width:10%;">
					<col style="width:20%;">
					<col style="width:10%;">
					<col style="width:25%;">
					<col style="width:30%;">
					<col style="width:15%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M01.LAB00085"/>
						</th>
						<td>
							<input id="searchDiscCd" name="searchDiscCd" value="" type="text" class="w270">
						</td>
						<th>
							<spring:message code="LAB.M01.LAB00076"/>
						</th>
						<td>
							<div class="date_box">
								<div class="inp_date w300">
									<input id="searchDiscCdNm" name="searchDiscCdNm" value="" type="text" class="w270">
									<a href="javascript:goSearchDiscGrid();" id="btn_search" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>
								</div>	
							</div>
						</td>
						<td>
							<div class="w350">
								<span style="float:left">
									<input type="checkbox" name="checkDiscMappedInfo" id="checkDiscMappedInfo"/> <spring:message code="LAB.M05.LAB00016"/>
								</span>
								<span style="float:right"><a class="grey-btn" href="javascript:goDeleteDisc();	"id="btn_delete"><span class="trashcan_icon"></span></a></span>
							</div>
						</td>
					</tr>
				</thead>
			</table>
			<div id="discountGridDiv">
				<table id="discountGrid" class="w100p"></table>
				<div id="pager7"></div>
			</div>
			<div style="text-align: right">
				<!--수정-->
				<ntels:auth auth="${menuAuthU}">
				<a class="grey-btn" href="javascript:goUpdateDisc();" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
				</ntels:auth>		
			</div>
		</div>
		
		<!-- #tab8 -->
		<div id="tab8" class="tabcontent">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M01.LAB00082"/>
				</h4>
				<br>
			</div>
			<h5>
				<spring:message code="LAB.M01.LAB00083"/>
			</h5>
			<table class="writeview">
				<colgroup>
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:21%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M01.LAB00085"/>
						</th>
						<td>
							<input id="mapCd" name="mapCd" value="" type="text" class="w270" readonly="readonly">
						</td>
						<th>
							<spring:message code="LAB.M01.LAB00085"/>
						</th>
						<td>
							<input id="mapDedtCd" name="mapDedtCd" value="" type="text" class="w110" readonly="readonly">
							<input id="discDedtNm" name="discDedtNm" value="" type="text" class="w200" readonly="readonly">
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00130"/>
						</th>
						<td>
							<input id="prodNm8" name="prodNm8" value="" type="text" class="w270" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M08.LAB00109"/>
						</th>
						<td>
							<div class="date_box">
			   					<div class="inp_date w220">
				 					<input type="text"  id="dedtMapEffDt" name="dedtMapEffDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#"></a>
								</div>
							</div>
						</td>
						<th>
							<spring:message code="LAB.M05.LAB00003"/>
						</th> 
						<td>
							<div class="date_box">
			   					<div class="inp_date w220">
				 					<input type="text"  id="dedtMapExpDt" name="dedtMapExpDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
								</div>
							</div>
						</td>
						<th>
							<spring:message code="LAB.M01.LAB00069"/>
						</th>
						<td>
							<input id="mapDedtPrty" name="mapDedtPrty" type="text" class="w270" style="text-align:right">
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h5 class="sub_title">
					<spring:message code="LAB.M09.LAB00051"/>
				</h5>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:21%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M01.LAB00078"/>
						</th>
						<td>
	 							<select id="dedtUsgTypLvl" name="dedtUsgTypLvl" class="w270"> 
								<c:forEach items="${usgTypLvlList}" var="usgTypLvlList" varStatus="status">
									<option value="${usgTypLvlList.commonCd}">
										${usgTypLvlList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00031"/>
						</th>
						<td colspan='3'>
							<select id="dedtUsgTypGrpCd" name="dedtUsgTypGrpCd" class="w270">
								
							</select>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00079"/>
						</th>
						<td>
							<select id="dedtUsgItmTyp" name="dedtUsgItmTyp" class="w270">
								<c:forEach items="${usgItmTypList}" var="usgItmTypList" varStatus="status">
									<option value="${usgItmTypList.commonCd}">
										${usgItmTypList.commonCdNm}
									</option>
								</c:forEach>	
							</select>
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00080"/>
						</th>
						<td colspan='3'>
							<select id="dedtUsgItmCd" name="dedtUsgItmCd" class="w270">
							
							</select>
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h5 class="sub_title">
					<spring:message code="LAB.M01.LAB00084"/>
				</h5>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:20%;">
					<col style="width:13%;">
					<col style="width:21%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M09.LAB00129"/>
						</th>
						<td>
							<select id="dedtCondRateFac1" name="dedtCondRateFac1" class="w270">
								<option value="0"><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${condRateFacList}" var="condRateFacList" varStatus="status">
									<option value="${condRateFacList.commonCd}">
										${condRateFacList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00132"/>
						</th>
						<td>
							<select id="dedtCondOperator1" name="dedtCondOperator1" class="w270">
								<option value=""><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${condOperatorList}" var="condOperatorList" varStatus="status">
									<option value="${condOperatorList.commonCd}">
										${condOperatorList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00127"/>
						</th>
						<td>
							<input id="dedtCondVal1" name="dedtCondVal1" value="" type="text" class="w270">
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M09.LAB00131"/>
						</th>
						<td colspan='5'>
							<select id="dedtLogicalOperator12" name="dedtLogicalOperator12" class="w270">
								<option value="0"><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${logicalOperatorList}" var="logicalOperatorList" varStatus="status">
									<option value="${logicalOperatorList.commonCd}">
										${logicalOperatorList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M09.LAB00130"/>
						</th>
						<td>
							<select id="dedtCondRateFac2" name="dedtCondRateFac2" class="w270">
								<option value="0"><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${condRateFacList}" var="condRateFacList" varStatus="status">
									<option value="${condRateFacList.commonCd}">
										${condRateFacList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00133"/>
						</th>
						<td>
							<select id="dedtCondOperator2" name="dedtCondOperator2" class="w270">
								<option value=""><spring:message code='LAB.M07.LAB00195' /></option>
								<c:forEach items="${condOperatorList}" var="condOperatorList" varStatus="status">
									<option value="${condOperatorList.commonCd}">
										${condOperatorList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00128"/>
						</th>
						<td>
							<input id="dedtCondVal2" name="dedtCondVal2" value="" type="text" class="w270">
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h5 class="sub_title">
					<spring:message code="LAB.M01.LAB00070"/>
				</h5>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:25%;">
					<col style="width:25%;">
					<col style="width:15%;">
					<col style="width:35%;">
				</colgroup>
				<thead>
					<tr>
						<td colspan='2'>
							<input type="hidden" id="subscProrateFlag" name="subscProrateFlag"/>
							<input type="checkbox" name="checkSubscProrateFlag" id="checkSubscProrateFlag" />
							<spring:message code="LAB.M01.LAB00008"/>
							&nbsp;&nbsp;&nbsp;	
							<input type="hidden" id="termProrateFlag" name="termProrateFlag"/>
							<input type="checkbox" name="checkTermProrateFlag" id="checkTermProrateFlag" />
							<spring:message code="LAB.M14.LAB00047"/>
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h5 class="sub_title">
					<spring:message code="LAB.M01.LAB00072"/>
				</h5>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:13%;">
					<col style="width:12%;">
					<col style="width:13%;">
					<col style="width:12%;">
					<col style="width:13%;">
					<col style="width:12%;">
					<col style="width:13%;">
					<col style="width:12%;">
				
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M01.LAB00074"/>
						</th>
						<td>
							<select id="amtFlag" name="amtFlag" class="w170">
								<c:forEach items="${amgFlagList}" var="amgFlagList" varStatus="status">
									<option value="${amgFlagList.commonCd}">
										${amgFlagList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M01.LAB00075"/>
						</th>
						<td>
							<select id="amtUnit" name="amtUnit" class="w170">
							
							</select>
						</td>
						<th>
							<spring:message code="LAB.M01.LAB00071"/>
						</th>
						<td>
							<input id="quantity" name="quantity" type="number" class="w170" style="text-align:right">
						</td>
						<th>
							<spring:message code="LAB.M01.LAB00077"/>
						</th>
						<td>
							<select id="replenishCycl" name="replenishCycl" class="w170">
								<c:forEach items="${replenishCyclList}" var="replenishCyclList" varStatus="status">
									<option value="${replenishCyclList.commonCd}">
										${replenishCyclList.commonCdNm}
									</option>
								</c:forEach>								
							</select>
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<table class="writeview">
				<colgroup>
					<col style="width:10%;">
					<col style="width:20%;">
					<col style="width:10%;">
					<col style="width:25%;">
					<col style="width:30%;">
					<col style="width:15%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M01.LAB00085"/>
						</th>
						<td>
							<input id="searchDedtCd" name="searchDedtCd" value="" type="text" class="w270">
						</td>
						<th>
							<spring:message code="LAB.M01.LAB00076"/>
						</th>
						<td>
							<div class="date_box">
								<div class="inp_date w300">
									<input id="searchDedtCdNm" name="searchDedtCdNm" value="" type="text" class="w270">
									<a href="javascript:goSearchDedtGrid();" id="btn_popUp" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>
								</div>
							</div>
						</td>
						<td>
							<div class="w350">
								<span style="float:left">
									<input type="checkbox" name="checkDedtMappedInfo" id="checkDedtMappedInfo"/> <spring:message code="LAB.M05.LAB00016"/>
								</span>
								<span style="float:right"><a class="grey-btn" href="javascript:goDeleteDedt();" id="btn_delete"><span class="trashcan_icon"></span></a></span>
							</div>
						</td>
	
					</tr>
				</thead>
			</table>
			<div id="allowanceGridDiv">
				<table id="allowanceGrid" class="w100p"></table>
				<div id="pager8"></div>
			</div>
			<div style="text-align: right">
				<!--수정-->
				<ntels:auth auth="${menuAuthU}">
				<a class="grey-btn" href="javascript:goUpdateDedt();" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
				</ntels:auth>		
			</div>
		</div>
		
		<!-- #tab9 -->
		<div id="tab9" class="tabcontent">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M14.LAB00010"/>
				</h4>
				<br>
			</div>
			<h5>
				<spring:message code="LAB.M01.LAB00226"/>
			</h5>
			<table class="writeview">
				<colgroup>
					<col style="width:15%;">
					<col style="width:35%;">
					<col style="width:15%;">
					<col style="width:35%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M14.LAB00012"/>
						</th>
						<td>
							<input id="lmtCd" name="lmtCd" value="" type="text" class="w270" readonly="readonly">
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00130"/>
						</th>
						<td>
							<input id="prodNm9" name="prodNm9" value="" type="text" class="w270" readonly="readonly">
							
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M08.LAB00109"/>
						</th>
						<td>
							<div class="date_box">
			   					<div class="inp_date w220">
									<input type="text"  id="effDt9" name="effDt9" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>				 					
								</div>
							</div>
						</td>
						<th>
							<spring:message code="LAB.M05.LAB00003"/>
						</th>
						<td>
							<div class="date_box">
			   					<div class="inp_date w220">
				 					<input type="text"  id="expDt9" name="expDt9" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
								</div>
							</div>
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h5 class="sub_title">
					<spring:message code="LAB.M09.LAB00051"/>
				</h5>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:15%;">
					<col style="width:35%;">
					<col style="width:15%;">
					<col style="width:35%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M14.LAB00008"/>
						</th>
						<td>
							<select id="usgTypLvl9" name="usgTypLvl9" class="w270">
								<c:forEach items="${usgTypLvlList}" var="usgTypLvlList" varStatus="status">
									<option value="${usgTypLvlList.commonCd}">
										${usgTypLvlList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M14.LAB00007"/>
						</th>
						<td colspan='3'>
							<select id="usgTypGrpCd9" name="usgTypGrpCd9" class="w270"></select>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M07.LAB00079"/>
						</th>
						<td>
							<select id="usgItmTyp9" name="usgItmTyp9" class="w270">
								<c:forEach items="${usgItmTypList}" var="usgItmTypList" varStatus="status">
									<option value="${usgItmTypList.commonCd}">
										${usgItmTypList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M07.LAB00080"/>
						</th>
						<td colspan='3'>
							<select id="usgItmCd9" name="usgItmCd9" class="w270"></select>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M09.LAB00125"/>
						</th>
						<td>
							<select id="usgTypCtrlLvl9" name="usgTypCtrlLvl9" class="w270">
								<c:forEach items="${usgTypCtrlLvlList}" var="usgTypCtrlLvlList" varStatus="status">
									<option value="${usgTypCtrlLvlList.commonCd}">
										${usgTypCtrlLvlList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00124"/>
						</th>
						<td colspan='3'>
							<select id="ctrlUsgTypGrpCd9" name="ctrlUsgTypGrpCd9" class="w270">
								<c:forEach items="${ctrlUsgTypGrpCdList}" var="ctrlUsgTypGrpCdList" varStatus="status">
									<option value="${ctrlUsgTypGrpCdList.commonCd}">
										${ctrlUsgTypGrpCdList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div class="fl">
				<h5 class="sub_title">
					<spring:message code="LAB.M14.LAB00005"/>
				</h5>
			</div>
			<table class="writeview">
				<colgroup>
					<col style="width:15%;">
					<col style="width:35%;">
					<col style="width:15%;">
					<col style="width:35%;">
				</colgroup>
				<thead>
					<tr>
						<th>
							<spring:message code="LAB.M14.LAB00006"/>
						</th>
						<td>
							<select id="amtFlag9" name="amtFlag9" class="w270">
								<c:forEach items="${amgFlagList}" var="amgFlagList" varStatus="status">
									<option value="${amgFlagList.commonCd}">
										${amgFlagList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
						<th>
							<spring:message code="LAB.M14.LAB00011"/>
						</th>
						<td>
							<input type="number" id="quantity9" name="quantity9" class="w270" style="text-align:right"/>
						</td>
					</tr>
					<tr>
						<th>
							<spring:message code="LAB.M10.LAB00088"/>
						</th>
						<td colspan='3'>
							<select id="rechrgLmtTypCd" name="rechrgLmtTypCd" class="w270">
								<c:forEach items="${rechrgLmtTypCdList}" var="rechrgLmtTypCdList" varStatus="status">
									<option value="${rechrgLmtTypCdList.commonCd}">
										${rechrgLmtTypCdList.commonCdNm}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</thead>
			</table>
			<br>
			<div id="limitGridDiv">
				<table id="limitGrid" class="w100p"></table>
				<div id="pager9"></div>
			</div>
			<div style="text-align: right">
				<!--초기화-->		
				<a class="grey-btn"  href="javascript:initLmt();"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
				<!--등록-->
				<ntels:auth auth="${menuAuthC}">
				<a class="grey-btn" href="javascript:goInsertLmt();" id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
				</ntels:auth>
				<!--수정-->
				<ntels:auth auth="${menuAuthU}">
				<a class="grey-btn" href="javascript:goUpdateLmt();" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
				</ntels:auth>		
				<!--삭제-->
				<ntels:auth auth="${menuAuthD}">
				<a class="grey-btn" href="javascript:goDeleteLmt();" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
				</ntels:auth>
			</div>
		</div>
	</div>
    <!--버튼-->	
	<div class="main_btn_box">
		<div class="fr">
		<!--저장-->
		<a class="grey-btn" href="javascript:goSave();" id="btn_save" title='<spring:message code="LAB.M09.LAB00048"/>'><span class="save_icon"><spring:message code="LAB.M09.LAB00048"/></span></a>
		</div>
	</div>
	<!-- #container -->
</form>
<div id="popup_dialog" class="Layer_wrap" style="display:none; width:400px;" >
</div>     
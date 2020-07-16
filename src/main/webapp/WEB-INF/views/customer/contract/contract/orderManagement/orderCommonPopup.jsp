<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {

	//버튼제어
	orderPopup_commonBtnSet();

	//처리자 정보 세팅
	orderPopup_setProcessUserInfo();

	//오더 페이지 로드
	orderPopup_orderProcessPageLoad();
	var orderInfo = orderPopup_getOrderCommonInfoAsJson();

	//저장
    $('#popupRcptBtn_save').on('click',function (e) {
		  	if($("#popupRcptBtn_save").hasClass('not-active')){
				return;
			}
			var result = precheckOrderSave();
			if(result == false){
			  		return;
			}

			if(confirm('<spring:message code="MSG.M09.MSG00008"/>')==true){
				
			  	popupOrderProcessSave();
			}
		}
    );

    //완료
    $('#popupRcptBtn_complete').on('click',function (e) {
    	//alert("popupRcptBtn_complete");
		  	if($("#popupRcptBtn_complete").hasClass('not-active')){
				return;
			}
			if(confirm('<spring:message code="MSG.M08.MSG00079"/>')==true){
		
		  		popupOrderProcessCmpl();
		  	}
		}
    );

    //취소
    $('#popupRcptBtn_cancel').on('click',function (e) {
		  	if($("#popupRcptBtn_cancel").hasClass('not-active')){
				return;
			}
			if(confirm('<spring:message code="MSG.M10.MSG00045"/>')==true){
			  	popupOrderProcessCancel();
			}
		}
    );

    //
    //닫기 버튼을 눌렀을 때
	$('.Layer_wrap ').on('click','.close',function (e) {
		//오더 상태 변경이 일어 났을 경우 callback호출
	  	if($("#common_orderStat_org").val() != $("#common_orderStat").val()){
	  		if (typeof orderPopupCallback == 'function') {
	  			orderPopupCallback($("#common_soId").val(), $("#common_custId").val(), $("#common_ctrtId").val(), $("#common_rcptId").val());
	  		}
	  	}
	});

});


/**
 * 오더 기본 정보 세팅
 */
function orderPopup_getOrderCommonInfoAsJson(){
	
	/* alert("orderTp=="+'${orderCommonInfo.orderTp}'); */
	var orderInfo = new Object();
	orderInfo.rcptId = '${orderCommonInfo.rcptId}';
	orderInfo.soId = '${orderCommonInfo.soId}';
	orderInfo.ctrtId = '${orderCommonInfo.ctrtId}';
	orderInfo.custId = '${orderCommonInfo.custId}';
	orderInfo.custNm = '${orderCommonInfo.custNm}';
	orderInfo.svcTelNoAsMask = '${orderCommonInfo.svcTelNoAsMask}';
	orderInfo.orderId = '${orderCommonInfo.orderId}';
	orderInfo.orderCd = '${orderCommonInfo.orderCd}';
	orderInfo.orderTp = '${orderCommonInfo.orderTp}';
	orderInfo.orderTpNm = '${orderCommonInfo.orderTpNm}';
	orderInfo.orderStat = '${orderCommonInfo.orderStat}';
	orderInfo.orderStatNm = '${orderCommonInfo.orderStatNm}';
	orderInfo.custTp = '${orderCommonInfo.custTp}';
	orderInfo.custTpNm = '${orderCommonInfo.custTpNm}';
	orderInfo.cnslMstCl = '${orderCommonInfo.cnslMstCl}';
	orderInfo.cnslMstClNm = '${orderCommonInfo.cnslMstClNm}';
	orderInfo.cnslMidCl = '${orderCommonInfo.cnslMidCl}';
	orderInfo.cnslMstClNm = '${orderCommonInfo.cnslMstClNm}';
	orderInfo.cnslSlvCl = '${orderCommonInfo.cnslSlvCl}';
	orderInfo.cnslSlvClNm = '${orderCommonInfo.cnslSlvClNm}';
	orderInfo.basicSvcCd = '${orderCommonInfo.basicSvcCd}';
	orderInfo.url = '${orderCommonInfo.url}';
	return JSON.stringify(orderInfo);
}


/**
 * 각 오더의 페이지 로드
 */
function orderPopup_orderProcessPageLoad(){
	var url = $("#common_url").val();

	$.ajax({
		type : "post",
		url : '/customer/contract/contract/orderManagement/openOrderPage.ajax',
		data : {
			orderPageUrl : url
		},
		async: true,
		success : function(data) {
			var html = data;
			$("#popupOrderProcessDiv").html(html);
			$('#popup_dialog').show();
			commonPupupResize();
			layerW();
			popupMaxheight();
		},		
		complete : function(){
			wrapWindowByMaskOrder(); // 팝업 오픈	
		},
		error : function(err){
     		alert('<spring:message code="MSG.M10.MSG00005"/>');	
     	}
	}); 	
}

/**
 * 각 오더의 버튼처리
 */
function orderPopup_commonBtnSet(){
	var orderStat = $('#common_orderStat').val();

	if(orderStat == ''){
		btnEnable('popupRcptBtn_save');
		btnDisable('popupRcptBtn_cancel');
		btnDisable('popupRcptBtn_complete');
		btnEnable('popupRcptBtn_close');
	}else if(orderStat == '01'){
		btnDisable('popupRcptBtn_save');
		btnEnable('popupRcptBtn_cancel');
		btnEnable('popupRcptBtn_complete');
		btnEnable('popupRcptBtn_close');
	}else if(orderStat == '02'){
		btnDisable('popupRcptBtn_save');
		btnDisable('popupRcptBtn_cancel');
		btnDisable('popupRcptBtn_complete');
		btnEnable('popupRcptBtn_close');
	}else if(orderStat == '03'){
		btnDisable('popupRcptBtn_save');
		btnDisable('popupRcptBtn_cancel');
		btnDisable('popupRcptBtn_complete');
		btnEnable('popupRcptBtn_close');
	}else if(orderStat == '04'){
		btnDisable('popupRcptBtn_save');
		btnDisable('popupRcptBtn_cancel');
		btnDisable('popupRcptBtn_complete');
		btnEnable('popupRcptBtn_close');
	}
}

/*
 * 처리자 정보 세팅
 */
function orderPopup_setProcessUserInfo(){
	$("#common_procUsrInfo").append(getNameAndId('${session_user.userId}', '${session_user.userName}')); 
	$("#common_procOrgInfo").append(getNameAndId('${session_user.orgId}', '${session_user.orgNm}')); 
}

function wrapWindowByMaskOrder(){
	//화면의 높이와 너비를 구한다.
	var maskHeight = $(document).height();  
	var maskWidth = $(window).width();  
	$('#mask').fadeIn(100);      
	$('#mask').fadeTo("slow",0.5); 
}


/*
 * 명칭 포맷팅
 */
function getNameAndId(id, name){
	if(name == '' || name == null){
		return id;
	}else{
		return name + '(' + id + ')'; 
	}
}

/*
 * 장비 왼쪽 그리드에서 오른쪽으로 이동
 */
function moveDeviceRight(from, to){
	var selectedRowId = $('#' + from).jqGrid('getGridParam','selrow');
	
	if(selectedRowId == null){
		return;
	}

	var addRow = $('#' + from).getRowData(selectedRowId);

	var ids = $('#' + to).getDataIDs();
	//중복구매 불가 상품의 경우 체크
	if(addRow.is_dup_yn == 'N'){
		var isDup = false;
		$.each(ids, function(index, value){
			var rowData = $('#' + to).getRowData(value);
			if(addRow.device_prod_cd == rowData.device_prod_cd){
				isDup = true;
				return false;
			}
		});

		if(isDup){
			alert('<spring:message code="MSG.M08.MSG00080"/>');
			return false;
		}
	}else if(addRow.is_dup_yn == 'Y'){
		if(addRow.dup_cnt != null && addRow.dup_cnt != '' && addRow.dup_cnt != '0'){ //미설정인 경우 무제한
			var checkCnt = 0;
			$.each(ids, function(index, value){
				var rowData = $('#' + to).getRowData(value);
				if(addRow.device_prod_cd == rowData.device_prod_cd){
					checkCnt = checkCnt + 1;
				}
			});

			//허용초과
			if(addRow.dup_cnt <= checkCnt){
				alert('<spring:message code="MSG.M08.MSG00085" arguments="' + addRow.dup_cnt + '"/>'); 
				return false;
			}
		}
	}

	var selOrgListCnt = $('#' + to).getGridParam("records") + 1;
	$('#' + to).setGridParam({
 		rowNum: selOrgListCnt
	});

	addRow.org_monthly_fee = addRow.monthly_fee;
	addRow.org_onetime_fee = addRow.onetime_fee;
	$('#' + to).addRowData(undefined,addRow);
	$('#' + to).trigger("reloadGrid");
}


/*
 * 부가 왼쪽 그리드에서 오른쪽으로 이동
 */
function moveAddRight(from, to){
	var selectedRowId = $('#' + from).jqGrid('getGridParam','selrow');
	
	if(selectedRowId == null){
		return;
	}
	var addRow = $('#' + from).getRowData(selectedRowId);
	var ids = $('#' + to).getDataIDs();
	//중복구매 불가 상품의 경우 체크
	if(addRow.is_dup_yn == 'N'){
		var isDup = false;
		$.each(ids, function(index, value){
			var rowData = $('#' + to).getRowData(value);
			if(addRow.add_prod_cd == rowData.add_prod_cd){
				isDup = true;
				return false;
			}
		});

		if(isDup){
			alert('<spring:message code="MSG.M08.MSG00080"/>');
			return false;
		}
	}else if(addRow.is_dup_yn == 'Y'){
		if(addRow.dup_cnt != null && addRow.dup_cnt != '' && addRow.dup_cnt != '0'){ //미설정인 경우 무제한
			var checkCnt = 0;
			$.each(ids, function(index, value){
				var rowData = $('#' + to).getRowData(value);
				if(addRow.add_prod_cd == rowData.add_prod_cd){
					checkCnt = checkCnt + 1;
				}
			});

			//허용초과
			if(addRow.dup_cnt <= checkCnt){
				alert('<spring:message code="MSG.M08.MSG00085" arguments="' + addRow.dup_cnt + '"/>'); 
				return false;
			}
		}
	}

	var selOrgListCnt = $('#' + to).getGridParam("records") + 1;
	$('#' + to).setGridParam({
 		rowNum: selOrgListCnt
	});


	addRow.org_monthly_fee = addRow.monthly_fee;
	addRow.org_onetime_fee = addRow.onetime_fee;
	$('#' + to).addRowData(undefined,addRow);
	$('#' + to).trigger("reloadGrid");
}

/*
 * 부가 왼쪽 그리드에서 오른쪽으로 이동
 */
function moveAddRightWithoutCheck(from, to){
	var selectedRowId = $('#' + from).jqGrid('getGridParam','selrow');
	
	if(selectedRowId == null){
		return;
	}
	var addRow = $('#' + from).getRowData(selectedRowId);
	var selOrgListCnt = $('#' + to).getGridParam("records") + 1;
	$('#' + to).setGridParam({
 		rowNum: selOrgListCnt
	});


	addRow.org_monthly_fee = addRow.monthly_fee;
	addRow.org_onetime_fee = addRow.onetime_fee;
	$('#' + to).addRowData(undefined,addRow);
	$('#' + to).trigger("reloadGrid");
}

/*
 * 장비 왼쪽 그리드에서 오른쪽으로 이동
 */
function moveDeviceRightWithoutCheck(from, to){
	var selectedRowId = $('#' + from).jqGrid('getGridParam','selrow');
	
	if(selectedRowId == null){
		return;
	}
	var addRow = $('#' + from).getRowData(selectedRowId);
	var selOrgListCnt = $('#' + to).getGridParam("records") + 1;
	$('#' + to).setGridParam({
 		rowNum: selOrgListCnt
	});


	addRow.org_monthly_fee = addRow.monthly_fee;
	addRow.org_onetime_fee = addRow.onetime_fee;
	$('#' + to).addRowData(undefined,addRow);
	$('#' + to).trigger("reloadGrid");
}
/*
 * 장비 필수항목 이동
 */
function moveDeviceMandatory(from, to, updateColIndex){

	var orderStat = $('#common_orderStat').val();

	if(orderStat != ''){
		return false;
	}

	//필수 장비 선택 여부 체크
	var ids = $('#' + from).getDataIDs();
	$.each(ids, function(index, value){
		var rowData = $('#' + from).getRowData(value);
		if(rowData.is_mandatory_yn != 'Y'){
			return true;
		}

		var targetId = findRow($("#"  + to), "device_prod_cd", rowData.device_prod_cd, 0);
		if(targetId != undefined) return true;

		var selOrgListCnt = $('#' + to).getGridParam("records") + 1;
		$('#' + to).setGridParam({
	 		rowNum: selOrgListCnt
		});

		rowData.org_monthly_fee = rowData.monthly_fee;
		rowData.org_onetime_fee = rowData.onetime_fee;
		$('#' + to).addRowData(undefined,rowData);
		$('#' + to).trigger("reloadGrid");
	});
}


/*
 * 부가서비스 필수항목 이동
 */
function moveAddMandatory(from, to, updateColIndex){

	var orderStat = $('#common_orderStat').val();

	if(orderStat != ''){
		return false;
	}

	//필수 장비 선택 여부 체크
	var ids = $('#' + from).getDataIDs();
	$.each(ids, function(index, value){
		var rowData = $('#' + from).getRowData(value);
		if(rowData.add_prod_mandatory_yn != 'Y'){
			return true;
		}

		var targetId = findRow($("#"  + to), "add_prod_cd", rowData.add_prod_cd, 0);
		if(targetId != undefined) return true;


		var selOrgListCnt = $('#' + to).getGridParam("records") + 1;
		$('#' + to).setGridParam({
	 		rowNum: selOrgListCnt
		});

		rowData.org_monthly_fee = rowData.monthly_fee;
		rowData.org_onetime_fee = rowData.onetime_fee;
		$('#' + to).addRowData(undefined,rowData);
		$('#' + to).trigger("reloadGrid");
	});
}

/**
 * Edit처리
 */
function gridEditCell(gridId, updateColIndex){
	//Grid Update
	// var data = $('#' + gridId).getRowData();

	// $.each(data, function(index, value){
	// 	$('#' + gridId).editCell(index, updateColIndex ,false); //대상 Edit Cel의 Index지정
	// });

	// $('#' + gridId).jqGrid("resetSelection");
}
/*
 * 그리드에서 삭제
 */
function deleteRow(from, rowId){
	
	if(rowId == null){
		return;
	}

	var preRow = $('#' + from).getInd(rowId);
	if(preRow == false){
		return;
	}

	var selOrgListCnt = $('#' + from).getGridParam("records")  - 1;

	$('#' + from).setGridParam({
 		rowNum: selOrgListCnt
	});
	$('#' + from).delRowData(rowId);


	$('#' + from).trigger("reloadGrid");
}

/**
 * 그리드 Diable 처리
 */
function disable_popup_grid(id){
	$("#" + id).unbind("contextmenu");
	$("#" + id).unbind("mouseover");
	$("#" + id).unbind("mouseout");
	$("#" + id).setGridParam({
		hoverrows : false,
		beforeSelectRow: function(rowid, e) {
			return false;
		}, 
		onSelectRow: function( rowid, status, event ) {
		    return false;
		}
	});
}

</script>


<div class='layer_top layer-minw1100'>
	<div class='title'>${orderCommonInfo.orderTpNm}</div>
	<a href='#' class='close'></a>
</div>

<div class='layer_box'>
	<div class='main_btn_box'>
		<div class='fl'>
			<h4 class='sub_title'><spring:message code="LAB.M01.LAB00040"/></h4> <!-- 계약기본정보 -->
		</div>
	</div>
	<table id='popupCommonInfoTable' class='writeview tdB column_5 row_1'>
		<tbody>
			<tr class='col5'>
				<th><spring:message code="LAB.M01.LAB00046"/></th> <!-- 고객ID -->
				<td>
					<div id='popupCommonCustId'>
						${orderCommonInfo.custId}
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00050"/></th> <!-- 고객명 -->
				<td>
					<div id='popupCommonCustNm'>
						${orderCommonInfo.custNm}
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00032"/></th> <!-- 계약ID -->
				<td>
					<div id='popupCommonCtrtId'>
						${orderCommonInfo.ctrtId}
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00071"/></th> <!-- 접수ID -->
				<td>
					<div id='popupCommonRcptId'>
						${orderCommonInfo.rcptId}
					</div>
				</td>
				<th><spring:message code="LAB.M08.LAB00035"/></th> <!-- 오더상태 -->
				<td>
					<div id='popupCommonOrderStatNm'>
						${orderCommonInfo.orderStatNm}
					</div>
				</td>
			</tr>
		</tbody>
	</table>

	<div id="popupOrderProcessDiv" class="w100p"></div>

	<div class='main_btn_box'>
		<div class='fl'>
			<h4 class='sub_title'><spring:message code="LAB.M10.LAB00026"/></h4> <!-- 처리자정보 -->
		</div>
	</div>
	<table class='writeview tdB column_2 row_1'>
		<tbody>
			<tr class='col2'>
				<th><spring:message code="LAB.M10.LAB00024"/></th> <!-- 처리자 -->
				<td>
					<div id='common_procUsrInfo'></div>
				</td>
				<th><spring:message code="LAB.M10.LAB00027"/></th> <!-- 처리조직 -->
				<td>
					<div id='common_procOrgInfo'></div>
				</td>
			</tr>
		</tbody>
	</table>
	<div class='btn_box'>
		<a class="grey-btn" id="popupRcptBtn_save" href="#">
			<span><spring:message code="LAB.M09.LAB00048"/></span> <!-- 저장 -->
		</a>
		<a class="grey-btn" id="popupRcptBtn_cancel" href="#">
			<span><spring:message code="LAB.M10.LAB00091"/></span> <!-- 취소 -->
		</a>
		<a class="grey-btn" id="popupRcptBtn_complete" href="#">
			<span><spring:message code="LAB.M08.LAB00044"/></span> <!-- 완료 -->
		</a>
		<a class="grey-btn close" id="popupRcptBtn_close" href="#"> <!-- 닫기 -->
			<span><spring:message code="LAB.M03.LAB00027"/></span>
		</a>
	</div>
</div>

<input id="common_soId" value="${orderCommonInfo.soId}" type="text" hidden />	
<input id="common_custId" value="${orderCommonInfo.custId}" type="text" hidden />	
<input id="common_ctrtId" value="${orderCommonInfo.ctrtId}" type="text" hidden />	
<input id="common_orderId" value="${orderCommonInfo.orderId}" type="text" hidden />	
<input id="common_rcptId" value="${orderCommonInfo.rcptId}" type="text" hidden />	
<input id="common_orderCd" value="${orderCommonInfo.orderCd}" type="text" hidden />	
<input id="common_orderTp" value="${orderCommonInfo.orderTp}" type="text" hidden />	
<input id="common_orderStat" value="${orderCommonInfo.orderStat}" type="text" hidden />	
<input id="common_orderStat_org" value="${orderCommonInfo.orderStat}" type="text" hidden />	
<input id="common_cnslMstCl" value="${orderCommonInfo.cnslMstCl}" type="text" hidden />	
<input id="common_cnslMidCl" value="${orderCommonInfo.cnslMidCl}" type="text" hidden />	
<input id="common_cnslSlvCl" value="${orderCommonInfo.cnslSlvCl}" type="text" hidden />	
<input id="common_url" value="${orderCommonInfo.url}" type="text" hidden />	
<input id="common_basicSvcCd" value="${orderCommonInfo.basicSvcCd}" type="text" hidden />	
<input id="common_custTp" value="${orderCommonInfo.custTp}" type="text" hidden />	
<input id="common_custTpNm" value="${orderCommonInfo.custTpNm}" type="text" hidden />	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<style type="text/css">
table.ui-datepicker-calendar { display:none; }
</style>

<script type="text/javascript">

var billSetupItemComboList1 = '<option value="">' + '[New]' + '</option>';
var billSetupItemComboList2 = '<option value="">' + '[New]' + '</option>';
var maxDate = new Date('9999/12/31');

$(document).ready(function() {
	
	$("#searchSoId").val("${session_user.soId}");
	$('#searchSoId').selectmenu("refresh");
	
	//설정항목 코드
	$.ajax({
		type : "post",
          url : '/charge/billing/billing/newBillingInfoItem/getBillSetupItemComboList.json', 
          data : '',
          async: false,
          success : function(data) {
        	  
        	  var billSetupItemComboList = data.comboList;
        	  for(var i=0; i<billSetupItemComboList.length; i++){
        		  if(billSetupItemComboList[i].setLoc == '1'){
        			  billSetupItemComboList1 += '<option value="' + billSetupItemComboList[i].setItmCd + '">' + billSetupItemComboList[i].newSetItmNm + '</option>';
        		  }else if(billSetupItemComboList[i].setLoc == '2'){
        			  billSetupItemComboList2 += '<option value="' + billSetupItemComboList[i].setItmCd + '">' + billSetupItemComboList[i].newSetItmNm + '</option>';
        		  }
        	  }
          }
	});
	
	// 설정위치 선택에 따른 설정항목코드 분리처리
	$('#referenceType').selectmenu({change: function() {
		labelActive("setItmCd");
		var referenceType = $("#referenceType").val();
		var requiredFlag = "";
		
		if(referenceType != ''){
		
			if(referenceType == '1'){
				$('#setItmCd').html(billSetupItemComboList1);
				requiredFlag = 'Y';
				labelActive("setVal");
				labelNonActive("required");
				labelNonActive("notRequired");
				
			}else if(referenceType == '2'){
				$('#setItmCd').html(billSetupItemComboList2);
				requiredFlag = 'Y';
				labelActive("required");
				labelActive("notRequired");
				labelNonActive("setVal");
			}
			console.log(requiredFlag);
			$('input:radio[name=required]:input[value='+requiredFlag+']').prop("checked", true);
			
			$('#setItmCd').val('');
			$('#setItmCd').selectmenu("refresh");
			
		}
	}});
	
	// 시작
	init();
	
	// 비활성화처리
	nonActiveSetting();

	// JQGrid 호출
	initGrid();	
	
	// 필드성격에 따른 필드구조 분기처리
	$('#fieldNature').selectmenu({
        change: function() {
        	if($('#fieldNature').val() == '3') { //Date
        		labelActive("fieldStructure");
        	}
        	else {
        		labelNonActive("fieldStructure");
        	}
     }
	});
	
	// 항목명 처리
	$('#setItmCd').selectmenu({
        change: function() {
        	var setItmCdText = $('#setItmCd option:selected').text();
        	var setItmCdTextStartIdx = setItmCdText.indexOf("]");
        	var setItmCdTextEndIdx = setItmCdText.length;
        	var setItmResetNm = setItmCdText.substring(setItmCdTextStartIdx + 1, setItmCdTextEndIdx);
        	
        	$('#setItmNm').val(setItmResetNm);
        	if(setItmResetNm == ''){
        		labelActive("setItmNm");
        	}else{
        		labelNonActive("setItmNm");	
        	}
        	
        }
	});
	 
	// 조회
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	// 초기화
	$("#btn_init").click(function() {
		reset();
	});
	
	// 등록 처리
	$("#btn_insert").click(function() {
		goProcess("I");
	});

	// 수정 처리
	$("#btn_update").click(function() {
		goProcess("U");
	});

	// 삭제처리
	$("#btn_delete").click(function() {
		goProcess("D");
	});

	// 리사이징
	$(window).resize(function() {
		$("#newBillingInfoItemTable").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	// 달력(월) 처리
	//달력처리
	if($(".month-picker").length > 0){
		if(lng == 'ko'){
			format = 'yy-mm';
		}else if (lng == 'en'){
			format = 'mm/yy';
		}
		 $('.month-picker').datepicker( {
		        changeMonth: true,
		        changeYear: true,
		        showButtonPanel: true,
		        dateFormat: format,
		        onClose: function(dateText, inst) {
		            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		            $(this).datepicker('setDate', new Date(year, month, 1));
		        }
		 		
		 		,
		        beforeShow : function (dateText, inst) {
		        	
		            var selectDate = $(this).val().split("-");
		            var year = Number(selectDate[0]);
		            var month = Number(selectDate[1]) - 1;
		            $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
		            $(this).datepicker( 'setDate', new Date(year, month, 1) );
		            
		        }
		 		 
		    }
		 ); 
	}
	
	$('#searchYymm').datepicker('setDate', new Date());
	$('#eftBgnYymm').datepicker('setDate', new Date());
	$('#eftEndYymm').datepicker('setDate', maxDate);

});

function initGrid() {
	var param = new Object();
	param.searchSoId =  $("#searchSoId").val();	
	param.searchYymm =  dateFormatToStringYYYYMM($("#searchYymm").val());	
	param.searchSetItmNm =  $("#searchSetItmNm").val();
	param.searchReferenceType = $("#searchReferenceType").val();
	
	$("#newBillingInfoItemTable").jqGrid({
	   	url:'newBillingInfoItemListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	
	   	colModel: [ 
					{ label: '<spring:message code="LAB.M07.LAB00003" />',  name: 'soNm', width : 30, align:"center"},
					{ label: '<spring:message code="LAB.M14.LAB00044" />', name: 'setItmCd', width : 30, align:"center"},
					{ label: '<spring:message code="LAB.M07.LAB00205" />', name: 'setItmNm', width : 90},
					{ label: '<spring:message code="LAB.M14.LAB00072" />', name: 'eftBgnYymm', width : 40, align:"center", formatter:stringTypeFormatterYYYYMM},
					{ label: '<spring:message code="LAB.M14.LAB00074" />', name: 'eftEndYymm', width : 40, align:"center", formatter:stringTypeFormatterYYYYMM},
					{ label: '<spring:message code="LAB.M07.LAB00203" />', name: 'referenceType', width : 50, align:"center"},
					{ label: '<spring:message code="LAB.M13.LAB00034" />', name: 'required', width : 35, align:"center"},
					{ label: '<spring:message code="LAB.M13.LAB00028" />', name: 'fieldNature', width : 35, align:"center"},
					{ label: '<spring:message code="LAB.M13.LAB00027" />', name: 'fieldStructure', width : 50},
					{ label: '<spring:message code="LAB.M13.LAB00030" />', name: 'fieldDigit', width : 40, align:"right"},
					{ label: '<spring:message code="LAB.M07.LAB00201" />', name: 'setVal', width : 140},
					{ label: '<spring:message code="LAB.M03.LAB00082" />', name: 'regId', width : 30, align:"center"},
					{ label: '<spring:message code="LAB.M03.LAB00081" />', name: 'regDate', formatter:dateTypeFormatterYYYYMMDDHH24MISS , width : 45, align:"center"},
					{ label: '<spring:message code="LAB.M06.LAB00062" />', name: 'chgId', width : 30, align:"center"},
					{ label: '<spring:message code="LAB.M06.LAB00061" />', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 45, align:"center"},
					{ label: 'soId' , name: 'soId', hidden:true,width : 0},
					{ label: 'newSetItmNm' , name: 'newSetItmNm', hidden:true,width : 0},
					{ label: 'referenceTypeCd' , name: 'referenceTypeCd', hidden:true,width : 0},
					{ label: 'fieldNatureCd' , name: 'fieldNatureCd', hidden:true,width : 0},
					{ label: 'requiredCd' , name: 'requiredCd', hidden:true,width : 0}
		],
	   	//shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2',
		autowidth:true,
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 350,					//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        	btnActive("btn_init");
			btnActive("btn_update");
			btnActive("btn_delete");
			btnNonActive("btn_insert");
        },
	    loadComplete: function(obj){
	    	$("#newBillingInfoItemTable").setGridWidth($("#gridDiv").width(),false);
	    }
	});
	$("#newBillingInfoItemTable").setGridWidth($("#gridDiv").width(),false);
}

function init() {
	//화면 처음 들어 왔을 때 입력부분 비활성화 처리
	$("#setItmCd").val("");
	$("#setItmCd").selectmenu("refresh");
	$("#eftBgnYymm").val("");
	$('#eftEndYymm').datepicker('setDate', maxDate);
	$("#setItmNm").val("");
	$("#soId").val("");
	$("#soId").selectmenu("refresh");
	$("#fieldNature").val("");
	$("#fieldNature").selectmenu("refresh");
	$("#fieldStructure").val("");
	$("#fieldDigit").val("");
	$("#setVal").val("");
}

/*
 * 데이터 선택 이벤트 처리
 */
function setSelectedData(rowId) {
	
	init();
	nonActiveSetting();
	
	var data = $("#newBillingInfoItemTable").getRowData(rowId);
	
	$("#referenceType").val(data.referenceTypeCd);
	$("#referenceType").selectmenu("refresh");
	
	if(data.referenceTypeCd == '1'){
		$('#setItmCd').html(billSetupItemComboList1);
		labelActive("setVal");
		labelNonActive("required");
		labelNonActive("notRequired");
		
	}else if(data.referenceTypeCd == '2'){
		$('#setItmCd').html(billSetupItemComboList2);
		labelActive("required");
		labelActive("notRequired");
		labelNonActive("setVal");
	}
	
	$("#setItmCd").val(data.setItmCd);
	$("#setItmCd").selectmenu("refresh");
	
	$("#eftBgnYymm").val(data.eftBgnYymm);
	$("#eftEndYymm").val(data.eftEndYymm);
	
	$("#setItmNm").val(data.setItmNm);
	$("#soId").val(data.soId);
	$("#soId").selectmenu("refresh");
	
	$('input:radio[name=referenceType]:input[value='+data.referenceTypeCd+']').prop("checked", true); 
	$('input:radio[name=required]:input[value='+data.requiredCd+']').prop("checked", true);
	$("#fieldNature").val(data.fieldNatureCd);
	$("#fieldNature").selectmenu("refresh");
	
	$("#fieldStructure").val(data.fieldStructure);
	$("#fieldDigit").val(data.fieldDigit);
	$("#setVal").val(data.setVal);
	
	//활성화
	labelActive("eftEndYymm");
	labelActive("setItmNm");
	labelActive("fieldDigit");
	
	// 필드성격에 따른 필드구조 분기처리
	if($('#fieldNature').val() == '3') { //Date
		labelActive("fieldStructure");
	}else {
		labelNonActive("fieldStructure");
	}
	
}

function reset(){
	
	initforReset();  // 값 처리
	initActiveforReset(); //버튼 활성화 처리
}

function initforReset() {
	initializeSetItmCd(); //코드 초기화
	
	$("#setItmNm").val("");
	$('#eftBgnYymm').datepicker('setDate', new Date());
	$("#fieldStructure").val("");
	$("#fieldDigit").val("");
	$("#setVal").val("");
}

function initActiveforReset(){
	btnActive("btn_init");
	btnActive("btn_insert");
	btnNonActive("btn_update");
	btnNonActive("btn_delete");
	
	labelActive("referenceType");
	$("#referenceType").val("");
	$("#referenceType").selectmenu("refresh");
	
	labelActive("setItmCd");	
	labelActive("eftBgnYymm");
	labelActive("eftEndYymm");
	labelActive("setItmNm");
	labelActive("soId");
	$("#soId").val("");
	$("#soId").selectmenu("refresh");
	labelActive("fieldNature");
	$("#fieldNature").val("");
	$("#fieldNature").selectmenu("refresh");
	labelActive("fieldDigit");
}

function nonActiveSetting(){
	btnActive("btn_init");
	btnNonActive("btn_insert");
	btnNonActive("btn_update");
	btnNonActive("btn_delete");
	
	labelNonActive("referenceType");	
	labelNonActive("setItmCd");
	labelNonActive("eftBgnYymm");
	labelNonActive("eftEndYymm");
	labelNonActive("setItmNm");
	labelNonActive("soId");
	$("input:radio[name='required']").attr('disabled', true);
	labelNonActive("fieldDigit");
	labelNonActive("fieldStructure");
	labelNonActive("setVal");
	
}

function btnActive(id){
	$('#'+id).addClass('grey-btn');
	$('#'+id).removeClass('white-btn');
	$('#'+id).removeClass('not-active');
	$('#'+id).removeAttr('disabled');
}

function btnNonActive(id){
	$('#'+id).addClass('white-btn');
	$('#'+id).removeClass('grey-btn');
	$('#'+id).addClass('not-active');
	$('#'+id).attr('disabled',true);
}

function labelActive(id){
	$('#'+id).addClass('white-btn');
	$('#'+id).removeClass('grey-btn');
	$('#'+id).removeClass('not-active');
	$('#'+id).removeAttr('disabled', false);
	
	$('#'+id+'-button').removeClass('not-active');
	$('#'+id+'-button').removeAttr('disabled');
}

function labelNonActive(id){
	$('#'+id).addClass('grey-btn');
	$('#'+id).removeClass('white-btn');
	$('#'+id).addClass('not-active');
	$('#'+id).attr('disabled',true);
}

function initializeSetItmCd() {
	
	$('#setItmCd').empty();
	$('#setItmCd').prepend('<option value="">' + '[New]' + '</option>');
	$('#setItmCd').selectmenu("refresh");
}

function goSearch() {
	init();
	nonActiveSetting();
	initializeSetItmCd(); //코드 초기화
	
	var param = new Object();
	param.page =  $("#page").val();
	param.perPage =  $("#perPage").val();
	param.searchSoId =  $("#searchSoId").val();	
	param.searchYymm =  dateFormatToStringYYYYMM($("#searchYymm").val());	
	param.searchSetItmNm =  $("#searchSetItmNm").val();	
	param.searchReferenceType = $("#searchReferenceType").val();
	
	$("#newBillingInfoItemTable").clearGridData();  // 이전 데이터 삭제
	$("#newBillingInfoItemTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
}

function goProcess(type) {	
	var param = checkValidation(type);
	
	if(!param) {
		return;
	}
	
	var url = "";
	if(type == 'I'){		
		url = "insertNewBillingInfoItemInfo.json";
	}else if(type == 'U'){		
		url = "updateNewBillingInfoItemInfo.json";
	}else if(type == 'D'){
		url = "deleteNewBillingInfoItemInfo.json";
	}
	
	if(type == 'I') {
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');   //I
	}
	else if(type == 'U') {
		var check = confirm('<spring:message code="MSG.M07.MSG00093" />'); //U
	}
	else if(type == 'D') {
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />'); //D
	}
	
	if(!check){
		return;
	}
	
	if(param != false){
		$.ajax({
			url : url,
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				if(data.result != "0"){
					if(type == 'I'){		
						alert('<spring:message code="MSG.M09.MSG00005" />');
					}else if(type == 'U'){		
						alert('<spring:message code="MSG.M09.MSG00005" />');
					}else if(type == 'D'){
						alert('<spring:message code="MSG.M07.MSG00053" />');
					}
					goSearch();
					init();
					nonActiveSetting();

					btnActive("btn_init");
					btnNonActive("btn_insert");
					btnNonActive("btn_update");
					btnNonActive("btn_delete");
				}
			},
		    error: function(err){
		    	if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
	     			alert(err.responseJSON.exceptionMsg);
	     		}else{
	     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
	     		}
		    },
			complete : function() {
			}
		});
	}
}
	
//유효성 체크
function checkValidation(type){
		var param = new Object();
		var typeCd = $("#referenceType").val();
		
		if(typeCd == '1') { //설정위치
			param.referenceType = '1';
		}
		else if(typeCd == '2') {
			param.referenceType = '2';
		}
		else {
			alert('<spring:message code="MSG.M07.MSG00082"/>');
			return false;
		}
		param.setItmCd = $("#setItmCd").val();		//설정항목코드
		if(param.setItmCd == '' && type != 'I'){
			alert('<spring:message code="MSG.M07.MSG00083"/>'); //설정항목코드를 입력해 주세요
			return false;
		}
		
		param.eftBgnYymm = dateFormatToStringYYYYMM($("#eftBgnYymm").val());		//효력발생월
		if(param.eftBgnYymm == ''){
			alert('<spring:message code="MSG.M08.MSG00037"/>'); //효력발생월을 입력해 주세요
			return false;
		}
		
		param.eftEndYymm = dateFormatToStringYYYYMM($("#eftEndYymm").val());		//효력종료월
		if(param.eftEndYymm == ''){
			alert('<spring:message code="MSG.M08.MSG00037"/>'); //효력종료월을 입력해 주세요
			return false;
		}
		
		param.setItmNm = $("#setItmNm").val();		//설정항목명
		if(param.setItmNm == ''){
			alert('<spring:message code="MSG.M07.MSG00081"/>'); //설정항목명을 입력해 주세요
			return false;
		}
		
		param.soId = $("#soId").val();	//사업
		if(param.soId == ''){
			alert('<spring:message code="MSG.M07.MSG00006" />');	  //사업을 선택하세요
			return false;
		}
		
		if($("#required").val() != "" && $("#required").val() != null) {	//필수항목
			param.required = $("#required").val();
		}
		else if($("#notRequired").val() != "" && $("#notRequired").val() != null) {
			param.required = $("#notRequired").val();
		}
		else if($("#required").val() == "" && $("#notRequired").val() == "") {
			alert('<spring:message code="MSG.M13.MSG00026"/>'); //필수항목을 선택하세요
			return false;
		}
		
		param.fieldNature = $("#fieldNature").val();		//필드성격
		if(param.fieldNature == ''){ //필드성격을 선택해 주세요
			alert('<spring:message code="MSG.M13.MSG00019"/>');
			return false;
		}
		
		if($("#fieldNature").val() != null && $("#fieldNature").val() == '3') {   //Date일 경우만 필드구조 입력
			if(param.fieldStructure == ''){   //필드구조를 입력해 주세요
				alert('<spring:message code="MSG.M13.MSG00017"/>');
				return false;
			}
			
			if($("#fieldStructure").val().match(/[^a-zA-Z]/) != null ){
				alert('<spring:message code="MSG.M08.MSG00041"/>');
				return false;
			}else{
				param.fieldStructure = $("#fieldStructure").val();		//필드구조
			}
			
		}else{
			param.fieldStructure = "";		//필드구조 
		}
		
		param.fieldDigit = $("#fieldDigit").val();		//필드자릿수
		if(param.fieldDigit == ''){	//필드자릿수를 입력해 주세요
			alert('<spring:message code="MSG.M13.MSG00021"/>');
			return false;
		}
		if($.isNumeric($("#fieldDigit").val()) == true) {
			param.fieldDigit = $("#fieldDigit").val();		//필드구조
		}
		else {
			alert('<spring:message code="MSG.M07.MSG00134"/>');//숫자만 입력해 주세요.
			return false;
		}
		
		//설정위치가 빌링설정정보이면 설정값 필수
		if(typeCd == '1' && $("#setVal").val() == '') { //설정위치
			alert('<spring:message code="MSG.M07.MSG00080"/>');		//MSG.M07.MSG00080=설정값을 입력하세요.
			return false;
		}else if(typeCd == '1' && $("#setVal").val() != ''){
			
			if($("#fieldNature").val() == '1') {  //필드성격이 숫자일 경우 숫자만 입력 체크
				if($.isNumeric($("#setVal").val()) == true) {
					param.setVal = $("#setVal").val();		//설정값
				} 
				else {
					alert('<spring:message code="MSG.M13.MSG00030"/>');  //필드성격에 맞는 값을 입력해주세요.
					return false;
				}
				param.setVal = $("#setVal").val();		//설정값
			}
			
		}
		
		param.searchYymm = dateFormatToStringYYYYMM($("#searchYymm").val()); //기준년월
		return param;
}

$(window).resize(function() {
	$("#newBillingInfoItemTable").setGridWidth($("#gridDiv").width(),false);
});


</script>

<!--NaviGator-->
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

<!--검색-->	
<form id="attribute" name="attribute" method="post">
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>

	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M06.LAB00110"/></h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		 <thead> 
		<tr>
			<th><spring:message code="LAB.M07.LAB00003"/><span class="dot">*</span><!-- 사업 --></th>
			<td>
				<select class="w150" id="searchSoId" name="searchSoId">
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>   
			</td>  
			<th><spring:message code="LAB.M01.LAB00220"/><span class="dot">*</span><!-- 기준년월 --></th>
			<td>
				<div class="date_box">
					<div class="inp_date w135">
							<input  type="text"  id="searchYymm" name="searchYymm" class="month-picker" readonly="readonly" >
							<a href="#" class="btn_cal"></a>
						</div>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00203"/><!-- 설정위치 --></th>
			<td>
					<select id="searchReferenceType" name="searchReferenceType" class="w150">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${searchReferenceTypeList}" var="searchReferenceTypeCode" varStatus="status">
						<option value="${searchReferenceTypeCode.commonCd}">${searchReferenceTypeCode.commonCdNm}</option>
					</c:forEach>
				</select>
		    </td>
		    <th><spring:message code="LAB.M07.LAB00205"/><!-- 설정항목명 --></th>
			<td>
					<div class="inp_date w135">
						<input type="text" id="searchSetItmNm" name="searchSetItmNm" class="w200">
				   </div> 
		   </td>
		</tr>
	</thead>
	</table> 
</form>	

<!--JQ Grid 리스트-->	
<div class="main_btn_box">
	<div class="fl">	
	   <h4 class="sub_title"><spring:message code="LAB.M06.LAB00107"/></h4>
	</div>
</div>
<div id='gridDiv'>
<table id="newBillingInfoItemTable" class="w100p"></table>
	<div id="pager2"></div>
</div>

<!--등록 수정-->	
<form:form method="post" name="attributeAction" commandName="attributeAction">
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>
	<input type="hidden" name = "setItmResetNm" value=""/>
        
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M06.LAB00110"/></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<tbody> 
			<tr>
				<!--설정위치-->
				<th><spring:message code="LAB.M07.LAB00203"/><span class="dot">*</span></th>
				<td colspan="3">
					<select id="referenceType" name="referenceType" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${searchReferenceTypeList}" var="searchReferenceTypeCode" varStatus="status">
							<option value="${searchReferenceTypeCode.commonCd}">${searchReferenceTypeCode.commonCdNm}</option>
						</c:forEach>
					</select>
				
				
				<%-- 
					<input type="radio" id="referenceType_billingSettingInfo" name="referenceType" value="1" checked="checked"/> 
					<label for="referenceType_billingSettingInfo"><spring:message code="LAB.M06.LAB00107" /></label>
					<input type="radio" id="referenceType_billingCycleInfo" name="referenceType" value="2"/> 
					<label for="referenceType_billingCycleInfo"><spring:message code="LAB.M06.LAB00111" /></label>
					 --%>
				</td>
			</tr>
			<tr>
				<!--설정항목코드-->
			   <th><spring:message code="LAB.M07.LAB00206" /><span class="dot">*</span></th>
			   <td colspan="3">
					<select id="setItmCd" name="setItmCd" class="w270">
						<option value="">[New]</option>
					</select>
			   </td>
			</tr>
			<tr>
			   <!--효력발생월-->
			   <th><spring:message code="LAB.M14.LAB00072" /><span class="dot">*</span></th>
			   <td>
				  <div class="date_box">
					<div class="inp_date w270">
							<input  type="text"  id="eftBgnYymm" name="eftBgnYymm" class="month-picker"readonly="readonly"  >
							<a href="#" class="btn_cal"></a>
						</div>
				</div>
			   </td>
			   <!--효력종료월-->
			   <th><spring:message code="LAB.M14.LAB00074" /><span class="dot">*</span></th>
			   <td>
				  <div class="date_box">
					<div class="inp_date w270">
							<input type="text"  id="eftEndYymm" name="eftEndYymm" class="month-picker" readonly="readonly" >
							<a href="#" class="btn_cal"></a>
						</div>
				</div>
			   </td>
			</tr>
			<tr>
			   <!--설정항목명-->
			   <th><spring:message code="LAB.M07.LAB00205" /><span class="dot">*</span></th>
			   <td>
				  <input id="setItmNm" name="setItmNm" type="text"  class="w270">
			   </td>
			   <!--사업-->
			   <th><spring:message code="LAB.M07.LAB00003" /><span class="dot">*</span></th>
			   <td>
			   	    <select class="w150" id="soId" name="soId">
			   	    <option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>   
			   </td>
			</tr>
			<tr>
			   <!--필수여부-->
			   <th><spring:message code="LAB.M13.LAB00034" /><span class="dot">*</span></th>
				  <td>
					<input type="radio" id="required" name="required" value="Y" checked="checked"/> 
					<label for="required"><spring:message code="LAB.M13.LAB00034" /></label>
					<input type="radio" id="notRequired" name="required" value="N" /> 
					<label for="notRequired"><spring:message code="LAB.M13.LAB00032" /></label>
				</td>
			   <!--필드성격-->
			   <th><spring:message code="LAB.M13.LAB00028" /><span class="dot">*</span></th>
			   <td>
				  <select id="fieldNature" name="fieldNature" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/>
						<c:forEach items="${fieldNatureList}" var="fieldNatureList" varStatus="status">
						<option value="${fieldNatureList.commonCd}">${fieldNatureList.commonCdNm}</option>
						</c:forEach>
					</select>
			   </td>
			</tr>
			<tr>
			   <!--필드구조-->
			   <th><spring:message code="LAB.M13.LAB00027" /></th>
			   <td>
				  <input id="fieldStructure" name="fieldStructure" type="text" class="w100p" maxlength='20'>
			   </td>
			   <!--필드자릿수-->
			   <th><spring:message code="LAB.M13.LAB00030" /><span class="dot">*</span></th>
			   <td>
				  <input id="fieldDigit" name="fieldDigit" type="text"  class="w270" maxlength='3'>
			   </td>
			</tr>
			<tr>
				<!--설정값-->
				<th><spring:message code="LAB.M07.LAB00201"/><span class="dot">*</span></th>
				<td colspan="3">
					<input id="setVal" name="setVal" type="text" class="w270">
				</td>
			</tr>
		</tbody>
	</table> 
</form:form>


<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">
		<!--초기화-->		
		<a class="grey-btn" href="#" id="btn_init"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
		<!--등록-->
		<ntels:auth auth="${menuAuthC}">
			<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>		
		<!--수정-->
		<ntels:auth auth="${menuAuthU}">
			<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		<!--삭제-->
		<ntels:auth auth="${menuAuthD}">
			<a class="grey-btn" href="#" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
		<!--출력-->
		<!-- 
		<ntels:auth auth="${menuAuthP}">
		<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth> -->
	
  </div>
</div>
<div id="pop_layer" style="height:600px;width:500px;overflow-x:none;overflow-y:auto;display:none;"></div>       




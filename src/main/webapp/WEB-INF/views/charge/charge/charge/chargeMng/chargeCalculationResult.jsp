<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
table.ui-datepicker-calendar { display:none; }
#dimMask {
position:absolute;
z-index:9000;
background-color:#000;
display:none;
left:0;
top:0;
}
.window{
display: none;
position:absolute;
left:100px;
top:100px;
z-index:10000;
}
</style>

<script type="text/javascript">
$(document).ready(function() {

	pageInit();

	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
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
			},
			beforeShow : function (dateText, inst) {
		        	
				var selectDate = $(this).val().split("-");
				var year = Number(selectDate[0]);
				var month = Number(selectDate[1]) - 1;
				$(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
				$(this).datepicker('setDate', new Date(year, month, 1));
		            
			}
		}).datepicker("setDate", new Date());  
	}
	
	$('#condBillYymm').datepicker('setDate', new Date());
	
	/*if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "-7"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
	

	if($(".datepicker1").length > 0){
		$( ".datepicker1" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "0"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker1.disabled" ).datepicker( "option", "disabled", true );*/

	//그리드 처리
	$("#workGrpGrid").jqGrid({
		url : '/charge/charge/charge/chargeMng/getChargeListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'billYymm', name: 'BILL_YYMM', width : 100, align:"center", hidden:true},
		    { label: 'prodCd', name: 'PROD_CD', width : 100, align:"center", hidden:true},
		    { label: 'ctrtId', name: 'CTRT_ID', width : 100, align:"center", hidden:true},
		    { label: 'pymAcntId', name: 'PYM_ACNT_ID', width : 100, align:"center", hidden:true},
		    { label: '<spring:message code="LAB.M09.LAB00240"/>', name: 'CLC_WRK_NO', width : 100, align:"center", sortable:false},	//작업번호
		    { label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'CUST_ID', width : 150, align:"center", sortable:false},	//고객ID
			{ label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'CUST_NM', width : 150, sortable:false},	//고객명
			{ label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'CTRT_ID', width : 150, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'PROD_NM', width : 150, sortable:false},	//상품명
		    { label: '<spring:message code="LAB.M07.LAB00342"/>', name: 'TOT_USE_AMT', width : 150, sortable:false,formatter:numberAutoFormatter ,align:"right"} //사용액합계
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "chargeList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#workGrpGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	$("#workGrpGrid2").jqGrid({
		url : '/charge/charge/charge/chargeMng/getChargeDetailList.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
		    { label: '<spring:message code="LAB.M07.LAB00185"/>', name: 'SVC_NM', width : 100, sortable:false},	//서비스명
		    { label: '<spring:message code="LAB.M01.LAB00152"/>', name: 'RATE_ITM_NM', width : 100, sortable:false},	//과금항목명
		    { label: '<spring:message code="LAB.M08.LAB00221"/>', name: 'USE_QTY', width : 150, sortable:false,align:"right"},	//이용건수
			{ label: '<spring:message code="LAB.M08.LAB00220"/>', name: 'USE_CNT', width : 150, sortable:false,align:"right"}, //USE_CNT
			{ label: '<spring:message code="LAB.M07.LAB00361"/>', name: 'USE_AMT', width : 150, sortable:false,formatter:numberAutoFormatter,align:"right"}, //사용금액
		    { label: '<spring:message code="LAB.M07.LAB00021"/>', name: 'USE_YYMM', width : 200, align:"left", sortable:false,align:"center", formatter:stringTypeFormatterYYYYMM}	//사용년월
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "chargeDetailList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#workGrpGridPager2",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#workGrpGrid2").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#workGrpGrid2").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	});

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
	    	if($('#condSo').val() == 'SEL'){
				alert('<spring:message code="MSG.M07.MSG00006" />');  //사업을 선택하세요
				return false;
			}
			if($('#condBillYymm').val() == ''){
				alert("<spring:message code="MSG.M10.MSG00006" />");  //청구년월을 입력해 주세요
				return false;
			}
			if($('#condPymAcntId').val() == ''){
				alert('<spring:message code="MSG.M02.MSG00001"/>');   //납부계정 정보가 없습니다.
				return false;
			}
	    	if($("#searchBtn").hasClass('not-active')){
				return;
			}
    		searchWorkGrpList();
		}
    );

	//납부계정 조회
	$('#btnSearchPym').on('click',function (e) {
		var url="/system/common/common/pymAcntSearch/pymAcntPopup.ajax";
		var param = new Object();
		param.popType = "m";            //팝업타입 m:모달 o:일반
		param.returnId1 = "searchAcntNm";
		param.returnId2 = "condPymAcntId";
		
		$.ajax({
			type : "post",
			url : url,
			data : param,
			async: true,
			success : function(data) {
				var html = data;
				$("#popup_dialog").html(html);
			},      
			complete : function(){
				wrapWindowByMask(); // 팝업 오픈
			}
		}); 
	});
	
	// 고객조회
	$('#btnCustSearch').on('click',function (e) {
			if($("#btnCustSearch").hasClass('not-active')){
				return;
			}
			openCustSearchPopup();	
		}
	);
});

/*
 * 페이지 초기화
 */
function pageInit(){

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#workGrpGrid").clearGridData();
	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

	$('#workGrpIdTxt').val('');
	$('#workGrpIdTxt').addClass('not-active');
    $('#workGrpIdTxt').attr('disabled', true);
    $('#workGrpNmTxt').val('');
	$('#workGrpNmTxt').addClass('not-active');
    $('#workGrpNmTxt').attr('disabled', true);
    $("#workGrpSoSel").val('SEL');
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('disable');
    $("#workGrpUseYnSel").val('SEL');
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('disable');
}

/*
 * 작업그룹조회
 */
function searchWorkGrpList(){

	pageInit();
	
	$("#workGrpGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			soId : $('#condSo').val(),
  			condBillYymm : $('#condBillYymm').val().replace("-",""),
  			condPymAcntId : $('#condPymAcntId').val(),
  			condCustId : $('#condCustId').val()
		}
	});	
	      
   	$("#workGrpGrid").trigger("reloadGrid");
  	
}

/*
 * 요금내역 선택 이벤트
 */

function setSelectedData(rowId){
	
	var data = $("#workGrpGrid").getRowData(rowId);
	
   	//서비스 상세 내역 
   	$("#workGrpGrid2").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  	    	soId : data.SO_ID,
  	    	condWorkGrpId : data.CLC_WRK_NO,
  			condPymAcntId : data.PYM_ACNT_ID,
  			condCustId : data.CUST_ID,	
  			condCtrtId : data.CTRT_ID,
  			condProdCd : data.PROD_CD,
  			condBillYymm : data.BILL_YYMM
		}
	});
   	
   	$("#workGrpGrid2").trigger("reloadGrid");
   	
}

function reloadUserDtl(workGrpId){
	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnEnable('updateBtn');
	btnEnable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#userGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : workGrpId
		}
	});
   	$("#userGrid").trigger("reloadGrid");	

   	$("#workGrpUserGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : workGrpId
		}
	});
   	$("#workGrpUserGrid").trigger("reloadGrid");	

}

/*
 * 초기화 버튼
 */
function initBtn(){

	btnEnable('initBtn');
	btnEnable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();


	$('#workGrpIdTxt').val('');
	$('#workGrpIdTxt').addClass('not-active');
    $('#workGrpIdTxt').attr('disabled', true);
    $('#workGrpNmTxt').val('');
	$('#workGrpNmTxt').removeClass('not-active');
    $('#workGrpNmTxt').removeAttr('disabled');
    $("#workGrpSoSel").val('SEL');
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('enable');
    $("#workGrpUseYnSel").val('SEL');
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('enable');
	$('#workGrpSoSel-button').focus();
}

/*
 * 사용자추가
 */
function addUserBtn(){
	var rowIds = $("#userGrid").jqGrid('getGridParam', 'selarrrow');

	var addUserList = [];
	for(var i = 0; i < rowIds.length;i++){
		var userData = $("#userGrid").getRowData(rowIds[i]);
		var addUserInfo = new Object();
		addUserInfo.WORK_GRP_ID = $('#workGrpIdTxt').val();
		addUserInfo.ADD_USER_ID = userData.USER_ID;
		addUserList[i] = addUserInfo;
	}

	var url = '/product/service/serviceMgt/workGrpMng/inserWorkGrpUserAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : JSON.stringify(addUserList),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	reloadUserDtl($('#workGrpIdTxt').val());
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });


}

/*
 * 사용자수정
 */
function updateWorkUserBtn(){
	var data = $("#workGrpUserGrid").getRowData();
	$.each(data, function(index, value){
		$("#workGrpUserGrid").editCell(index,3,false);
		$("#workGrpUserGrid").editCell(index,4,false);
	});
	var selectedRowIds = $("#workGrpUserGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
	var updateDataList = [];
	$.each(selectedRowIds, function(index, value){
		var rowData = $("#workGrpUserGrid").jqGrid('getRowData', value);
		var updateData = new Object();
		updateData.WORK_GRP_ID = $('#workGrpIdTxt').val();
		updateData.USER_ID = rowData.USER_ID;
		updateData.USE_YN = rowData.USE_YN;
		updateData.SMS_YN = rowData.SMS_YN;
		updateDataList[index] = updateData;
	});

	

	if(updateDataList.length == 0){
		alert('<spring:message code="MSG.M07.MSG00073"/>');
		return;
	}
	var url = '/product/service/serviceMgt/workGrpMng/updateWorkGrpUserAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : JSON.stringify(updateDataList),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	reloadUserDtl($('#workGrpIdTxt').val());

        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}


/*
 * 사용자삭제
 */
function deleteWorkUserBtn(){
	var selectedRowIds = $("#workGrpUserGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
	var deleteDataList = [];
	$.each(selectedRowIds, function(index, value){
		var rowData = $("#workGrpUserGrid").jqGrid('getRowData', value);
		var deleteData = new Object();
		deleteData.WORK_GRP_ID = $('#workGrpIdTxt').val();
		deleteData.USER_ID = rowData.USER_ID;
		deleteDataList[index] = deleteData;
	});

	if(deleteDataList.length == 0){
		alert('<spring:message code="MSG.M07.MSG00073"/>');
		return;
	}
	var url = '/product/service/serviceMgt/workGrpMng/deleteWorkGrpUserAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : JSON.stringify(deleteDataList),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	reloadUserDtl($('#workGrpIdTxt').val());

        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}

/*
 * 신규등록처리
 */
function insertBtn(){

	var soId = $("#workGrpSoSel").val();
	if($('#workGrpSoSel').val()== 'SEL'){
		
		var item = '<spring:message code="LAB.M07.LAB00003" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
    }

	//작업그룹명 필수
	var workGrpNm = $("#workGrpNmTxt").val();
	if(workGrpNm == null || workGrpNm.length == 0){
		$("#workGrpNmTxt").focus();
		var item = '<spring:message code="LAB.M09.LAB00013" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
	}


	//사용유무
	var useYn = $("#workGrpUseYnSel").val();
	if($('#workGrpUseYnSel').val()== 'SEL'){
		$('#workGrpUseYnSel-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
    }

    var url = '/product/service/serviceMgt/workGrpMng/insertWorkGrpAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
		  workGrpNm : workGrpNm
		 ,soId : soId
		 ,useYn : useYn 
			},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}


/*
 * 수정처리
 */
function updateBtn(){

	//작업그룹명 필수
	var workGrpNm = $("#workGrpNmTxt").val();
	if(workGrpNm == null || workGrpNm.length == 0){
		$("#workGrpNmTxt").focus();
		var item = '<spring:message code="LAB.M09.LAB00013" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
	}


	//사용유무
	var useYn = $("#workGrpUseYnSel").val();
	if($('#workGrpUseYnSel').val()== 'SEL'){
		$('#workGrpUseYnSel-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
    }

    var url = '/product/service/serviceMgt/workGrpMng/updateWorkGrpAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
		  workGrpId : $('#workGrpIdTxt').val()
		 ,workGrpNm : workGrpNm 
		 ,useYn : useYn 
			},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}

/*
 * 삭제처리
 */
function deleteBtn(){
    var url = '/product/service/serviceMgt/workGrpMng/deleteWorkGrpAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
		  workGrpId : $('#workGrpIdTxt').val()
		},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}
/*
 * 버튼 비활성화 처리
 */
function btnDisable(id){
	$('#' + id ).addClass('white-btn');
	$('#' + id ).removeClass('grey-btn');
	$('#' + id ).addClass('not-active');
	$('#' + id ).attr('disabled',true);
	
}

/*
 * 버튼 활성화 처리
 */
function btnEnable(id){
	$('#' + id ).addClass('grey-btn');
	$('#' + id ).removeClass('white-btn');
	$('#' + id ).removeClass('not-active');
	$('#' + id ).removeAttr('disabled');
}
/*
 * 고객조회팝업
 */
function openCustSearchPopup(){
	
	$.ajax({
		type : "post",
		url : '/system/common/common/customerSearch/customerSearchPopup.ajax',
		data : {
			 inputSoId : $('#condSo').val()   //input SO Id
			,inputCustNm : $('#condCustNm').val()   //input Customer Name
			,inputIsUnmaskYn : $('#isUnmaskYn').val() //마스크 처리 해제 Y
			//,outputSoId : 'condSo'            //output SO ID Select
			,outputCustNm : 'condCustNm'            //output Customer Name Text
			,outputCustId : 'condCustId'            //output Customer ID Text

		},
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
			$("#txtCustSearchCustNm").focus(); //오픈 후 focus위치
		}
	}); 
}

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


<!-- 검색 버튼 -->
<div class="main_btn_box">
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='searchBtn' href="#" class="grey-btn" title='<spring:message code="LAB.M09.LAB00158"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</ntels:auth>
</div>


<!-- 검색부 -->
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:40%;">
		<col style="width:10%;">
		<col style="width:40%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M07.LAB00003" /><span class="dot">*</span></th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>	<!-- 사업 -->
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M10.LAB00033" /><span class="dot">*</span></th> <!-- 청구년월 -->
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="condBillYymm" name="condBillYymm"  class="month-picker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M02.LAB00005" /><span class="dot">*</span></th> <!-- 납부계정 -->
			<td>
				<div class="inp_date w280">
					<input id="searchAcntNm" name="searchAcntNm" type="text" class="w120" disabled="disabled"/>
					<input id="condPymAcntId" name="condPymAcntId" type="text" class="w120" disabled="disabled"/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnSearchPym"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
			<th><spring:message code="LAB.M01.LAB00050" /></th> <!-- 고객명 -->
			<td colspan="3">
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w120" />
					<input id="condCustId" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch" href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<!--작업그룹표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M08.LAB00201" /></h4> <!-- 요금내역 -->
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00364" /></h4> <!-- 서비스상세내역 -->
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid2" class="w100p"></table>
	<div id="workGrpGridPager2"></div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>


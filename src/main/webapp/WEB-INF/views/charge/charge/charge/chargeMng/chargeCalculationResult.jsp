<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

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
		}); 
		 
		// 년월 레이어 focus
	    $(".month-picker").focus(function () {
	        $(".ui-datepicker-calendar").hide();
	        $("#ui-datepicker-div").position({
	            my: "center top",
	            at: "center bottom",
	            of: $(this)
	        });
	    });
		
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
		    { label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
		    { label: '작업일시', name: 'PROC_DTTM', width : 100, align:"left", sortable:false},
		    { label: '작업번호', name: 'CLC_WRK_NO', width : 100, align:"center", sortable:false},
		    { label: '고객ID', name: 'CUST_ID', width : 150, sortable:false},
			{ label: '고객명', name: 'CUST_NM', width : 150, sortable:false},
			{ label: '계약ID', name: 'CTRT_ID', width : 150, sortable:false},
		   // { label: '서비스번호', name: 'SVC_WRK_GRP_NM', width : 200, align:"left", sortable:false},
		    { label: '계산작업구분', name: 'CLC_WRK_CL', width : 100, align:"center", sortable:false},
		    { label: '상품명', name: 'PROD_NM', width : 150, sortable:false},
		    { label: '사용액합계', name: 'TOT_USE_AMT', width : 150, sortable:false},
			{ label: '처리상태', name: 'CLC_PROC_STAT', width : 150, sortable:false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "workGrpList",
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
		url : '/product/service/serviceMgt/workGrpMng/getWorkGrpListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
		    { label: '서비스명', name: 'SO_NM', width : 100, align:"left", sortable:false},
		    { label: '과금항목명', name: 'SVC_WRK_GRP_ID', width : 100, align:"center", sortable:false},
		    { label: '이용건수', name: 'CHGR_NM', width : 150, sortable:false},
			{ label: '이용일수', name: 'CHGR_NM', width : 150, sortable:false},
			{ label: '사용금액', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '사용년월', name: 'SVC_WRK_GRP_NM', width : 200, align:"left", sortable:false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "workGrpList",
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

	//작업명 조회 이벤트
	$( "#condWorkGrpNm" ).keypress(function(event) {
		if($("#searchBtn").hasClass('not-active')){
			return;
		}
      if(event.keyCode == 13){
        searchWorkGrpList();
      }
    });

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
	    	if($("#searchBtn").hasClass('not-active')){
				return;
			}
    		searchWorkGrpList();
		}
    );


    //초기화 버튼 이벤트
   	$('#initBtn').on('click',function (e) {
	   		if($("#initBtn").hasClass('not-active')){
				return;
			}
    		initBtn();
		}
    );

    //신규등록 버튼 이벤트
    $('#newBtn').on('click',function (e) {
      	if($("#newBtn").hasClass('not-active')){
          return;
  		  }
    		insertBtn();
		  }
    );

    //수정 버튼 이벤트
    $('#updateBtn').on('click',function (e) {
      	if($("#updateBtn").hasClass('not-active')){
          return;
  		  }
    		updateBtn();
		  }
    );

    //삭제 버튼 이벤트
    $('#deleteBtn').on('click',function (e) {
      	if($("#deleteBtn").hasClass('not-active')){
          return;
  		  }
    		deleteBtn();
		  }
    );

    //사용자 추가 버튼
    $('#addUserBtn').on('click',function (e) {
      	if($("#addUserBtn").hasClass('not-active')){
          return;
  		  }
    		addUserBtn();
		  }
    );

    //사용자수정버튼
    $('#updateWorkUserBtn').on('click',function (e) {
      	if($("#updateWorkUserBtn").hasClass('not-active')){
          return;
  		  }
    		updateWorkUserBtn();
		  }
    );

    //사용자삭제버튼
    $('#deleteWorkUserBtn').on('click',function (e) {
      	if($("#deleteWorkUserBtn").hasClass('not-active')){
          return;
  		  }
    		deleteWorkUserBtn();
		  }
    );

	$('#workGrpNmTxt').keyup(function(){
	  		var str = getMaxStr($('#workGrpNmTxt').val(), 30);
	  		if(str != $('#workGrpNmTxt').val()){
	  			$('#workGrpNmTxt').val(str);
	  		}
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
  			condClc : $('#condClc').val(),
  			condPymAcntId : $('#condPymAcntId').val(),
  			condCustId : $('#condCustId').val()
		}
	});
	      
   	$("#workGrpGrid").trigger("reloadGrid");	
}

/*
 * 작업그룹 선택 이벤트
 */

function setSelectedData(rowId){
	var data = $("#workGrpGrid").getRowData(rowId);

	$('#workGrpIdTxt').val(data.SVC_WRK_GRP_ID);
	$('#workGrpNmTxt').val(data.SVC_WRK_GRP_NM);
	$('#workGrpNmTxt').removeClass('not-active');
	$("#workGrpNmTxt").removeAttr('disabled');
    $("#workGrpUseYnSel").val(data.USE_YN);
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('enable');
	$("#workGrpSoSel").val(data.SO_ID);
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('disable');

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
  			svcWrkGrpId : data.SVC_WRK_GRP_ID
		}
	});
   	$("#userGrid").trigger("reloadGrid");	

   	$("#workGrpUserGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : data.SVC_WRK_GRP_ID
		}
	});
   	$("#workGrpUserGrid").trigger("reloadGrid");	
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
			<th><spring:message code="LAB.M07.LAB00003" /></th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th>청구년월</th>
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
			<th>계산처리상태</th>
			<td>
				<select id="condClc" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${chargeTrtTp}" var="chargeTrtTp" varStatus="status">
						<option value="${chargeTrtTp.commonCd}">${chargeTrtTp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th>납부계정</th>
			<td>
				<div class="inp_date w280">
					<input id="searchAcntNm" name="searchAcntNm" type="text" class="w120" disabled="disabled"/>
					<input id="condPymAcntId" name="condPymAcntId" type="text" class="w120" disabled="disabled"/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnSearchPym"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
		<tr>
			<th>고객명</th>
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
		<h4 class="sub_title">요금내역</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">서비스상세내역</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid2" class="w100p"></table>
	<div id="workGrpGridPager2"></div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>


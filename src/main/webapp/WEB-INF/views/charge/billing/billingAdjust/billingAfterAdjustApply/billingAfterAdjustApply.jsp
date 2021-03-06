<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

var intervalVal = 0;
var pgmExeSeqNo = '';
var batchCount = 0;

//현재년월
var globalDt = new Date();
var globalYyyy = globalDt.getFullYear();
var globalMm = globalDt.getMonth() + 1;
if(globalMm < 10){
    globalMm = "0"+globalMm;
}
var globalYyyyMm = ""+globalYyyy+globalMm;

//요금조정 파라미터
var packageName ="com.ntels.ccbs.batch.iv.launcher";
var className ="NBlivd51m00JobLauncher";
var batGrpId ="99999";
var batPgmId ="BLIVD51M00";

var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';

$(document).ready(function() {
	
	if($(".datepicker").length > 0){
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
	$( ".datepicker1.disabled" ).datepicker( "option", "disabled", true );

	//그리드 처리
	$("#afterAdjApplyTable").jqGrid({
		url : 'afterAdjApplyTgtList.json?',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
            { label: 'pymAcntId', name: 'pymAcntId', hidden:true, width:0},
            { label: 'dcsnProcStat', name: 'dcsnProcStat', hidden:true, width:0},
            { label: 'applSoId', name: 'applSoId', hidden:true, width:0},
		    { label: '<spring:message code="LAB.M14.LAB00086" />', name: 'hopeAplyYymm', formatter:stringTypeFormatterYYYYMM, width : 150, align:"center"},
		    { label: '<spring:message code="LAB.M07.LAB00350" />', name: 'applDttm', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},
		    { label: '<spring:message code="LAB.M02.LAB00014" />', name: 'pymAcntNm', width : 200, align:"left"},
		    { label: '조정번호', name: 'adjNo', width : 120, align:"left"},
			{ label: '<spring:message code="LAB.M09.LAB00215" />', name: 'dcsnProcStatNm', width : 150, align:"left"},
		    { label: '<spring:message code="LAB.M10.LAB00058" />', name: 'adjPrvBillAmt', formatter:'integer', width : 200, align:"right"},
		    { label: '<spring:message code="LAB.M09.LAB00242" />', name: 'adjAmt', formatter:'integer', width : 200, align:"right"},
		    { label: '<spring:message code="LAB.M10.LAB00031" />', name: 'billAmt', formatter:'integer', width : 120, align:"right"},
		    { label: '<spring:message code="LAB.M09.LAB00137" />', name: 'adjPrvBillAmtA', formatter:'integer', width : 200, align:"right"},
			{ label: '<spring:message code="LAB.M07.LAB00345" />', name: 'adjApplAmt', formatter:'integer', width : 120, align:"right"},
		    { label: '<spring:message code="LAB.M09.LAB00134" />', name: 'adjAmtA', formatter:'integer', width : 120, align:"right"},
		    { label: '<spring:message code="LAB.M07.LAB00237" />', name: 'rcptAmt', formatter:'integer', width : 150, align:"right"},
		    { label: '<spring:message code="LAB.M10.LAB00033" />', name: 'billYymm', formatter:stringTypeFormatterYYYYMM, width : 150, align:"center"},
		    { label: '<spring:message code="LAB.M10.LAB00107" />', name: 'billAplyDt', formatter:stringTypeFormatterYYYYMMDD, width : 150, align:"center"},
		    { label: '<spring:message code="LAB.M10.LAB00040" />', name: 'billDt', formatter:stringTypeFormatterYYYYMMDD, width : 150, align:"center"},
		    { label: '<spring:message code="LAB.M02.LAB00003" />', name: 'payDueDt', formatter:stringTypeFormatterYYYYMMDD, width : 150, align:"center"},
		    { label: '<spring:message code="LAB.M09.LAB00077" />', name: 'rcptPsnNm', width : 150, align:"left"},
		    { label: '<spring:message code="LAB.M07.LAB00256" />', name: 'chgrIdNm', width : 150, align:"left"},
		    { label: '<spring:message code="LAB.M07.LAB00254" />', name: 'chgDttm', formatter:stringTypeFormatterYYYYMMDDHH24MISS, width : 150, align:"center"}
		],
		multiselect : false,
		viewrecords: true,
		shrinkToFit:false,
		height: 500,
		sortable : true,
		jsonReader: {
            root : "afterAdjTgtList"
        },
        onCellSelect : function(rowid, index, contents, event){
        	btnCheck(rowid);
        },
       	loadComplete : function (data) {
  	      	$("#afterAdjApplyTable").setGridWidth($('#afterAdjApplyGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#afterAdjApplyTable").setGridWidth($('#afterAdjApplyGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#afterAdjApplyTable").setGridWidth($('#afterAdjApplyGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
    
    //납부자 조회
    $('#btnSearchPym').on('click',function (e) {
        openPymSearchPopup();
    });
    
    //납부자 키 이벤트
    $( "#condPymAcntNm" ).keypress(function(event) {
    	//
       if(event.keyCode == 13){
    	   openPymSearchPopup();
      }
    });

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
    	getAfterAdjApplyList();
    });
    
    //적용버튼 클릭
    $('#btnApply').on('click',function (e) {
    	callAdjBatch();
    });


    
});

function getAfterAdjApplyList(){
	var startDate = dateFormatToStringYYYYMMDD($("#searchStatDt").val());
    var endDate   = dateFormatToStringYYYYMMDD($("#searchEndDt").val());
    
    if(startDate == '' || endDate == ''){
        alert('<spring:message code="MSG.M01.MSG00007"/>');
           return;
    }
    if(startDate > endDate){
        alert('<spring:message code="MSG.M01.MSG00005"/>');
              return;
    }
    
    $("#afterAdjApplyTable").clearGridData();
    $("#afterAdjApplyTable").setGridParam({
        datatype: "json",
        postData : {
        	soId : $('#searchSoId').val(),
        	pymAcntId : $("#condPymAcntId").val(),
        	startDt : startDate,
        	endDt : endDate,
        	billAplyYn : $("input[type=radio][name='mstrFl']:checked").val()
        }
    });
    $("#afterAdjApplyTable").trigger("reloadGrid");
}
/*
 * 납부조회팝업
 */
function openPymSearchPopup(){
	
	//납부계정 조회
	var url="/system/common/common/pymAcntSearch/pymAcntPopup.ajax";
	var param = new Object();
	param.popType = "m";            //팝업타입 m:모달 o:일반
	param.returnId1 = "condPymAcntNm";
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
	
}

function btnCheck(rowid){
	var data = $("#afterAdjApplyTable").getRowData(rowid);
	
	if(data.dcsnProcStat == "05"){
		btnDisable("btnApply");
	}else{
		btnEnable("btnApply");
	}
}

function callAdjBatch(){
	var chkid = '';
    var param = '';
    var chkid = $("#afterAdjApplyTable").jqGrid('getGridParam', 'selrow'); //선택된 행의 ID를 가져온다
    var param = $("#afterAdjApplyTable").jqGrid('getRowData',chkid);  //선택된 행의 Data를 가져온다
    
    if(chkid==null){
        alert('<spring:message code="MSG.M03.MSG00015"/>');
        return;
    }
    
    //프로그램 실행 시퀀스 생성  
    $.ajax({
          type : "post",
          url : '/charge/payment/payment/newSinglePayment/getPgmExeSeqNo.json', 
          async: false,
          success : function(data) {
             pgmExeSeqNo = data.pgmExeSeqNo;
       }
    });
    
    //배치 Input Setting
    var batchParam = new Object();
    batchParam.packageName = packageName;
    batchParam.className = className;
    
    var args = "";
    args += batPgmId + "\|";                  //0.프로그램Id
    args += "0" + "\|";                       //1. Default
    args += batGrpId + "\|";                  //2.그룹아이디
    args += pgmExeSeqNo + "\|";               //3.배치시퀀스번호
    args += globalYyyyMm + "\|";              //4.작업년월(현재년월)
    args += "01" + "\|";                      //5.청구주기(Bill cycle)
    args += "${session_user.userId}" + "\|";  //6사용자ID
    args += param.applSoId + "\|";        //7.so_id
    args += param.adjNo + "\|";           //8.조정번호
    args += "N";                     //9.수납여부
    
    batchParam.args = args;
    

    console.log('batchParam.args : ' + batchParam.args);
    
    alert("배치를 호출 해 주세요 ")
/*     
    pageDisable();
    
    $.post('http://localhost:18080/executeJob', batchParam, function (response) {
        console.log('success : ' + response.commonResult.success);
        console.log('message : ' + response.commonResult.message);
    });
    
    //로그 호출
    getPaymentBatchLog(); */
}

//페이지 로딩
function pageDisable(){
    
    //화면의 높이와 너비를 구한다.
    var maskHeight = $(window).height();  
    var maskWidth = $(window).width();  

    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
    $('#dimMask').css({'width': maskWidth, 'height':maskHeight});
    $('#dimMask').fadeTo(0,0.1);  
    $('#loadingImage2').removeAttr('style');
    //e.preventDefault();
}

function getPaymentBatchLog(){
    
    clearInterval(intervalVal);
    
    //선택된 그리드 row
    var rowId = $("#afterAdjApplyTable").getGridParam("selrow");
    var data = $("#afterAdjApplyTable").getRowData(rowId);
    
    var param = new Object();
    param.soId = data.applSoId;
    param.bsYymm = globalYyyyMm;
    param.batPgmId = batPgmId;
    param.batGrpId = batGrpId;
    param.pgmExeSeqNo = pgmExeSeqNo;
    
    $.ajax({
        url : '/charge/charge/charge/chargeMng/getBatchLogAction.json',
        type : 'POST',
        data : param,
        dataType: 'json',
        success : function(data) {
            console.log(data.batchLogList);
            var batchLogList = data.batchLogList;
            batchCount++;
            
            //데이터 체크
            if(data.batchLogList != null && data.batchLogList.length > 0){
                //배치작업 완료되면 딤처리 해지, 그리드 다시 검색
                if(batchLogList[0].batProcStat == "1" || batchLogList[0].batProcStat == "9"){
                    
                    clearInterval(intervalVal);
                    batchCount = 0; //배치 카운트 초기화
                    getAnonyReceiptTable();
                    btnNonActive("btn_insert");
                    pageEnavle();
                    
                    
                }else{      //진행중일경우
                    clearInterval(intervalVal);
                    intervalVal = setTimeout(getPaymentBatchLog, 3000);
                }
                
            }else{  //진행중일경우
                clearInterval(intervalVal);
                intervalVal = setTimeout(getPaymentBatchLog, 3000);
            
                //임시로 종료 시키기 위한작업 삭제 할것.
                if(batchCount > 10){    //루프 3번 돌동안 로그 안싸였을 경우 초기화
                    alert('<spring:message code="MSG.M15.MSG00028"/>'); //MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
                    clearInterval(intervalVal);
                    pageEnavle();
                    batchCount = 0; //배치 카운트 초기화
                }
                
            }
            
        },
        error : function(err){
            alert('<spring:message code="MSG.M15.MSG00028"/>'); //MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
            pageEnavle();
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
			<th><spring:message code="LAB.M07.LAB00003"/><span class="dot">*</span><!-- 사업 --></th>
            <td>
               <select class="w150" id="searchSoId" name="searchSoId">
                   <spring:message code="LAB.M15.LAB00002"/>
                   <c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
                       <option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
                   </c:forEach>
               </select>                                           
            </td>
			<th><spring:message code="LAB.M07.LAB00350"/><span class="dot">*</span><!-- 신청일자 --></th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
					<span class="dash">~</span>
					<div class="inp_date w130">
						<input type="text" id="searchEndDt" name="searchEndDt" class="datepicker1" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M02.LAB00005"/><!-- 납부계정 --></th>
            <td>
                <div class="inp_date w280">
                   <input type="text" id="condPymAcntNm" name="condPymAcntNm" class="w250" />
                   <input type="hidden" id="condPymAcntId" name="condPymAcntId" />
                   <a href="#" id="btnSearchPym" name="btnSearchPym" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
               </div> 
            </td>
			<th><spring:message code="LAB.M10.LAB00102"/></th>
            <td>
                <div class="inp_date w280">
                    <div class="date_box">
                        <input type="radio" id="mstrFl1" name="mstrFl" value=""checked="checked" />
                        <label for="mstrFl"> <spring:message code="LAB.M09.LAB00063"/></label>
                        <input type="radio" id="mstrFl2" name="mstrFl" value="Y" /> 
                        <label for="mstrFl2"> <spring:message code="LAB.M06.LAB00132"/></label>
                        <input type="radio" id="mstrFl3" name="mstrFl" value="N" /> 
                        <label for="mstrFl2"> <spring:message code="LAB.M05.LAB00058"/></label>
                    </div>
                </div>
            </td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M10.LAB00101"/></h4>
	</div>
    <div class="fr mt10">
        <a href="#" class="grey-btn" id="btnApply" title='<spring:message code="LAB.M09.LAB00049"/>'><spring:message code="LAB.M09.LAB00049"/></a>
    </div>
</div>
<div id='afterAdjApplyGrid'>
    <table id="afterAdjApplyTable" class="w100p"></table>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>


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
	$("#workGrpGrid").jqGrid({
		url : '/charge/billing/billingAdjust/billingBeforeAdjustSearch/getBillChargeAdjBeforeReportList.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'soId', width : 100, align:"center", hidden:true},
		    { label: 'ctrtId', name: 'ctrtId', width : 100, align:"center", hidden:true},
		    { label: 'billSeqNo', name: 'billSeqNo', width : 100, align:"center", hidden:true},
		    { label: '신청일자',name: 'applDttmDt', width : 100, align:"center", formatter:stringToDateformatYYYYMMDD},
		    { label: '희망적용년월', name: 'hopeAplyYymm', width : 100, align:"center", sortable:false, formatter:stringToDateformatYYYYMM},
		    { label: '납부계정ID', name: 'pymAcntId', width : 100, align:"center", hidden:true},
		    { label: '납부자명', name: 'pymAcntNm', width : 100, align:"left", sortable:false},
		    { label: '서비스번호', name: 'svcTelNo', width : 100, align:"center", sortable:false},
		    { label: '조정구분', name: 'adjPt', width : 100, align:"center", sortable:false, hidden:true},
		    { label: '조정구분', name: 'adjPtNm', width : 100, align:"center", sortable:false},
		    { label: '조정사유', name: 'adjResnNm', width : 200, align:"left", sortable:false},
		    { label: '청구금액', name: 'billAmt', width : 100, align:"right", sortable:false,formatter:numberAutoFormatter},
		    { label: '신청금액', name: 'adjApplAmt', width : 150, align:"right", sortable:false,formatter:numberAutoFormatter},
			{ label: '청구반영일자', name: 'billAplyDt', width : 150, align:"center",sortable:false, formatter:stringToDateformatYYYYMMDD},
		    { label: '진행상태', name: 'dcsnProcStatNm', width : 150, sortable:false},
		    { label: '신청자명', name: 'rcptPsnNm', width : 150, sortable:false},
		    { label: '결재자명', name: 'apprrIdNm', width : 150, sortable:false},
		    { label: '청구년월', name: 'billYymm', width : 150, sortable:false,align:"center",formatter:stringToDateformatYYYYMM},
		    { label: '청구일자', name: 'billDt', width : 150, sortable:false, align:"center",formatter:stringToDateformatYYYYMMDD},
		    { label: '납기마감일자', name: 'payDueDt', width : 150, align:"center",sortable:false, formatter:stringToDateformatYYYYMMDD}, //확인!!
		    { label: '신청번호', name: 'adjNo', width : 150, sortable:false},
		    { label: '신청사유', name: 'adjApplConts', width : 150, sortable:false},
		    { label: '수정자', name: 'chgrIdNm', width : 150, sortable:false},
		    { label: '수정일시', name: 'chgDttm', width : 150, align:"center", sortable:false, formatter:dateTypeFormatterYYYYMMDDHH24MISS}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 300,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "billChargeAdjustReportList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#workGrpGridPager",
        /* onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        }, */
        ondblClickRow : function(rowid){
        	var rowData = $("#workGrpGrid").jqGrid('getRowData', rowid);
        	setSelectedDataPopup(rowData);
        },
       	loadComplete : function () {
  	      	$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
	    	if($("#searchBtn").hasClass('not-active')){
				return;
			}
    		searchWorkGrpList();
		}
    );
    
 	// 납부계정조회
	$('#btnCustPymSearch').on('click',function (e) {
			if($("#btnCustSearch").hasClass('not-active')){
				return;
			}
			openCustPymSearchPopup();	
		}
	);
 	
	//엑셀 다운로드 이벤트
    $('#btn_print').on('click',function (e) {
	    	if($("#btn_print").hasClass('not-active')){
				return;
			}
    		printExcel();
		}
    );
 	
 	//사용자조회
	$('#btnRcptSearch').on('click', function(e){
 		if($("#btnRcptSearch").hasClass('not-active')){
			return;
		}
 		openRcptSearchPopup();
 	});

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

});

//조회
function searchWorkGrpList(){
	
	var condDate = caldate(31, $('#searchEndDt').val());

	if($('#condSo').val() == 'SEL'){
		alert('<spring:message code="MSG.M07.MSG00002"/>');
		return;
	}
	
	if(chkDate($('#searchStatDt').val(),$('#searchEndDt').val())){
		alert("신청일자의 시작일이 종료일보다 큽니다.");
		return;
	}
	
	if(chkDate(condDate, $('#searchStatDt').val())){
		alert("신청기간을 1개월 이내로 입력해 주세요.");
		return;
	}
	
	$("#workGrpGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  	    	condSoId: $('#condSo').val() ,
  	    	applDttmFrom : dateFormatToStringYYYYMMDD($('#searchStatDt').val()),
  	    	applDttmTo : dateFormatToStringYYYYMMDD($('#searchEndDt').val()),
  	    	condApplDttmTyp : $('#condApplDttmTyp').val(),
  	    	condPymAcntId : $('#condPymAcntId').val(),
  	    	condRcptPsnId : $('#condUserId').val(),
  	    	condDcsnProcStat : $('#condDcsnProcStat').val(),
  	    	condApprrId : $('#condApprrId').val()
		},loadComplete : function (data) {

			if(data.totalCount == 0){
				$('#btn_print').addClass('white-btn');
				$('#btn_print').removeClass('grey-btn');
				$('#btn_print').addClass('not-active');
				$('#btn_print').attr('disabled',true);
			}else{
				$('#btn_print').addClass('grey-btn');
				$('#btn_print').removeClass('white-btn');
				$('#btn_print').removeClass('not-active');
				$('#btn_print').removeAttr('disabled');
			}
		}
	});
	
   	$("#workGrpGrid").trigger("reloadGrid");
}

/**
 * 그리드 더블 클릭
 */
function setSelectedDataPopup(data){
	var param = new Object();
	param.pymAcntId = data.pymAcntId;
	//param.pymAcntNm = data.pymAcntNm;
	param.adjPt= '1';
	param.billSeqNo = data.billSeqNo;
	param.ctrtId = data.ctrtId;
	param.adjNo = data.adjNo;
	param.billYymm = data.billYymm;
	param.soId = $('#condSo').val();
	
	console.info(JSON.stringify(param));
	
	$.ajax({
        type : "post",
        url : '/charge/billing/billingAdjust/billingBeforeAdjustSearch/openBeforeAdjSearhReqPopup.ajax',
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

/*
 * 납부조회팝업
 */
function openCustPymSearchPopup(){
	$.ajax({
	    type : "post",
	    url : '/system/common/common/pymAcntSearch/pymAcntSearchPopup.ajax',
	    data : {
	         inputSoId : $("#condSo").val()   //input SO Id
	        ,inputCustNm : $('#condPymAcntNm').val()   //input Customer Name
	        ,inputPymAcntId : $('#condPymAcntId').val()
	        ,inputIsUnmaskYn : $('#isUnmaskYn').val() //마스크 처리 해제 Y
	        ,outputSoId : 'condSo'            //output SO ID Select
	        ,outputCustNm : 'condCustNm'            //output Customer Name Text
	        ,outputCustId : 'condCustId'            //output Customer ID Text
	        ,outputPymAcntId : 'condPymAcntId'      //output Payment ID Text
	        ,outputPymAcntNm : 'condPymAcntNm'      //output Payment Nm Text
        	,outputSoId : 'condSo'      		//output Payment Nm Text
	    },
	    async: true,
	    success : function(data) {
	        var html = data;
	        $("#popup_dialog").html(html);
	    },      
	    complete : function(){
	        wrapWindowByMask(); // 팝업 오픈
	        $("#txtPymSearchCustNm").focus(); //오픈 후 focus위치
	    }
	});
}

/**
 * 신정자조회팝업
 */
function openRcptSearchPopup(){

	$("#condUserNm").val('');  //돋보기 클릭시 초기화
	$("#condUserId").val('');
	var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
	var param = new Object();
	param.popType = "m";            //팝업타입 m:모달 o:일반
	param.returnId1 = "condUserNm";
	param.returnId2 = "condUserId";
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

/**
 * 엑셀다운로드
 */
function printExcel(){
	
	var param = new Object();
	
	param.soId = $('#condSo').val() ;
  	param.applDttmFrom = dateFormatToStringYYYYMMDD($('#searchStatDt').val());
  	param.applDttmTo =  dateFormatToStringYYYYMMDD($('#searchEndDt').val());
  	param.condApplDttmTyp = $('#condApplDttmTyp').val();
  	param.condPymAcntId = $('#condPymAcntId').val();
  	param.condRcptPsnId = $('#condUserId').val();
  	param.condDcsnProcStat = $('#condDcsnProcStat').val();
  	param.condApprrId = $('#condApprrId').val();
		
  	$.download('getBillChargeAdjBeforeReportExcel.xlsx',param,'post');
}


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
	
	//엑셀버튼 비활성화
	$('#btn_print').addClass('white-btn');
	$('#btn_print').removeClass('grey-btn');
	$('#btn_print').addClass('not-active');
	$('#btn_print').attr('disabled',true);

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

/**
 * 검색조건 날짜 비교
 */
function chkDate(date1, date2){
	
	var chk = false;
	
	var d1 = new Date(date1);
	var d2 = new Date(date2);
	
	if(d1 > d2){
		chk = true;
	}
	
	return chk;
}

/**
 * conDate의 day만큼 전날의 날짜 return
 */
function caldate(day, condDate){
	 
	 var caledmonth, caledday, caledYear;
	 var loadDt = new Date(condDate);
	 var v = new Date(Date.parse(loadDt) - day*1000*60*60*24);
	 
	 caledYear = v.getFullYear();
	 
	 if( v.getMonth() < 9 ){
	  caledmonth = '0'+(v.getMonth()+1);
	 }else{
	  caledmonth = v.getMonth()+1;
	 }
	 if( v.getDate() < 9 ){
	  caledday = '0'+v.getDate();
	 }else{
	  caledday = v.getDate();
	 }
	 return caledYear+'-'+caledmonth+'-'+caledday;
	}


</script>

<input type="text" id="condApprrId" name="condApprrId" hidden />

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
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M02.LAB00005" /></th><!-- 납부계정 -->
			<td colspan="3">
				<div class="inp_date w280">
					<input id="condPymAcntNm" type="text" class="w120" />
					<input id="condPymAcntId" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustPymSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
		<tr>
			<th>일자<span class="dot">*</span></th>
			<td colspan="3">
				<div class="date_box">
					<select id="condApplDttmTyp" class="w10p">
						<option value="02">신청일자</option>
						<option value="01">처리일자</option>
					</select>
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
			<th>진행상태</th>
			<td>
				<select id="condDcsnProcStat" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${dcsnProcStatList}" var="item" varStatus="status">
							<option value="${item.commonCd}">${item.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th>신청자</th>
			<td>
				<div class="inp_date w280">
					<input id="condUserNm" type="text" class="w120" />
					<input id="condUserId" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnRcptSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">요금조정내역</h4>
	</div>
	<div class="fr mt10">
		<a class="grey-btn" id="btn_print" href="javascript:init();"><span class="print_icon"><spring:message code="LAB.M08.LAB00015" /></span></a>
		<%-- <a id='disableMaskBtn' class="grey-btn" href="#" title='액셀저장'><spring:message code="LAB.M08.LAB00015" /></a><!-- 엑셀다운로드 --> --%>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>


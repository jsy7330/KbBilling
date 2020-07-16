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

var intervalVal = 0;
var selGridData;
var batchCount = 0;
var userId = '${session_user.userName}';
var batGrpId = '00011';		//프로그램 그룹 아이디 
var batchType = "O";	//배치 타입 O: 건별작업 B:일괄작업

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
		            searchBatchList();	//달력 변경시 그리드 조회
		        }
			 	,
		        beforeShow : function (dateText, inst) {
		            var selectDate = $(this).val().split("-");
		            var year = Number(selectDate[0]);
		            var month = Number(selectDate[1]) - 1;
		            $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
		            $(this).datepicker('setDate', new Date(year, month, 1));
		            
		        }
		    }
		 ).datepicker("setDate", new Date()); 
	}
	
	//그리드 처리
	$("#batchGrid").jqGrid({
		url : '/charge/charge/charge/chargeMng/regularChargeJobAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: '<spring:message code="LAB.M09.LAB00017"/>', name: 'pgmNm', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00258"/>', name: 'wrkProcOrd', width : 50, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M10.LAB00096"/>', name: 'batProcStatNm', width : 100, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M04.LAB00016"/>', name: 'logFileNm', width : 250, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00197"/>', name: 'readCnt', width : 100, align:"right", formatter:'integer', sortable:false},
			{ label: '<spring:message code="LAB.M10.LAB00097"/>', name: 'procCnt', width : 100, align:"right", formatter:'integer', sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'pgmStrtDttm', formatter:stringTypeFormatterYYYYMMDDHH24MISS, align:"center",width : 150, sortable:false},
			{ label: '<spring:message code="LAB.M09.LAB00168"/>', name: 'pgmEndDttm', formatter:stringTypeFormatterYYYYMMDDHH24MISS, align:"center",width : 150, sortable:false},
			{ label: '<spring:message code="LAB.M09.LAB00231"/>', name: 'startEndTime', width : 150, align:"center",sortable:false},
			{ label: 'soId', name: 'soId', hidden:true},
			{ label: 'bsYymm', name: 'bsYymm', hidden:true},
			{ label: 'batPgmId', name: 'batPgmId', hidden:true},			
			{ label: 'pgmExeSeqNo', name: 'pgmExeSeqNo', hidden:true},
			{ label: 'pkgNm', name: 'pkgNm', hidden:true},
			{ label: 'execObj', name: 'execObj', hidden:true},
			{ label: 'batProcStat', name: 'batProcStat', hidden:true}
			
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 230,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "regularChargeJobList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 20,
        pager: "#batchGridPager",
        onCellSelect : function(rowid, index, contents, event){
        },
       	loadComplete : function () {
  	      	$("#batchGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	
  	      	gridLoad();
  	      	
	  	    //일괄 작업 일 경우 다시 호출
			if(batchType == "B"){
				clearInterval(intervalVal);
			   	oneWork("B");
			}
  	      	
  	      	
  	      	
        },
    	sortable: { update: function(permutation) {
    		$("#batchGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#batchGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	
	//작업명 조회 이벤트
	$( "#searchForm" ).keypress(function(event) {
		if($("#searchBtn").hasClass('not-active')){
			return;
		}
      if(event.keyCode == 13){
        searchBatchList();
      }
    });

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
		if($("#searchBtn").hasClass('not-active')){
			return;
		}
		searchBatchList();
	});
    
    //건별작업
    $('#oneWorkBtn').on('click',function (e) {
    	oneWork("O");
    });
    
  	//일괄작업
    $('#batchWorkBtn').on('click',function (e) {
    	oneWork("B");
    });
    
    //리스트 조회
    searchBatchList();
    
    //사업아이디 변경시 리스트 조회
    $( "#soId" ).selectmenu({
		change: function( event, ui ) {
			searchBatchList();
		}
	});
    
});

//배치 프로그램 리스트 조회
function searchBatchList() {
	
	batchType = "";	//작업 타입 초기화
	
	$("#batchGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  	    	soId: $('#soId').val()
  			, bsYymm: dateFormatToStringYYYYMM($('#bsYymm').val() )
  			, batGrpId : batGrpId
		}
	});

   	$("#batchGrid").trigger("reloadGrid");	

}

//리스트 조회후 작업
function gridLoad(){
	
	//작업버튼 비활성화
	btnDisable("oneWorkBtn");
	btnDisable("batchWorkBtn");
	
	var gridData = $("#batchGrid").getRowData();
	//그리드 데이터 전부 가져와서 처리 상태 값 확인
	for(var i=0; i<gridData.length; i++){
		
		//배치 데이터의 작업상태 코드가 1, 0 가 아닐경우 버튼 활성화
		if(gridData[i].batProcStat != "1" && gridData[i].batProcStat != "0"){
			
			//작업버튼 비활성화
			btnEnable("oneWorkBtn");
			btnEnable("batchWorkBtn");
			
			break;	//for문 종료
		}
		
	}
	
}//gridLoad()


// 배치 실행   배치 타입 O: 건별작업 B:일괄작업
function oneWork(type){
	
	//일괄작업 체크
	batchType = type;
	var checkBatch = "N";
	
	
	var gridData = $("#batchGrid").getRowData();
	
	//그리드 데이터 전부 가져와서 처리 상태 값 확인
	for(var i=0; i<gridData.length; i++){
		
		//배치 데이터의 작업상태 코드가 1 가 아닐경우 실행
		if(gridData[i].batProcStat != "1"){
			
			checkBatch = "Y";
			
			
			//작업버튼 비활성화
			btnDisable("oneWorkBtn");
			btnDisable("batchWorkBtn");
			
			pageDisable();
			
			var pgmExeSeqNo = gridData[i].pgmExeSeqNo;	//로그시퀀스 채번
			if(pgmExeSeqNo == null || pgmExeSeqNo == ''){	//로그시퀀스가 없을 경우 
				$.ajax({
					url : '/charge/charge/charge/chargeMng/pgmExeSeqNoAction.json',
					type : 'POST',
					data : "",
					dataType: 'json',
					async:false,	//싱크방식
					success : function(data) {
						pgmExeSeqNo = data.pgmExeSeqNo;
					},
			     	error : function(err){
			     		alert('<spring:message code="MSG.M15.MSG00028"/>');	//MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
			     		pageEnavle();
			     		return false;
			     	}
				});
			}			
			
			var clcWrkNo = "";
			var clcWrkNoParam = new Object();
			clcWrkNoParam.billYymm = dateFormatToStringYYYYMM($('#bsYymm').val() );
			clcWrkNoParam.soId = $("#soId").val();
			clcWrkNoParam.billCycl = '01';
			
			//clcWrkNo 채번
			$.ajax({
				url : '/charge/charge/charge/chargeMng/getClcWrkNo.json',
				type : 'POST',
				data : clcWrkNoParam,
				dataType: 'json',
				async:false,	//싱크방식
				success : function(data) {
					clcWrkNo = data.clcWrkNo;
				},
		     	error : function(err){
		     		alert('<spring:message code="MSG.M15.MSG00028"/>');	//MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
		     		pageEnavle();
		     		return false;
		     	}
			});
			
			//오류로 종료된걸 다시 실행 시킬 경우 상태값 5로 없데이트 후 배치 호출
			if(gridData[i].batProcStat == "9"){
				
				var updParam = new Object();
				updParam.batProcStat = "5";
				updParam.pgmExeSeqNo = gridData[i].pgmExeSeqNo;
				updParam.batGrpId = batGrpId;
				updParam.bsYymm = dateFormatToStringYYYYMM($('#bsYymm').val() );
				updParam.batPgmId = gridData[i].batPgmId;
				updParam.soId = $("#soId").val();
				
				$.ajax({
					url : '/charge/charge/charge/chargeMng/setBatPgmLogAction.json',
					type : 'POST',
					data : updParam,
					dataType: 'json',
					async:false,	//싱크방식
					success : function(data) {
						console.log(data);
					},
			     	error : function(err){
			     		alert('<spring:message code="MSG.M15.MSG00028"/>');	//MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
			     		pageEnavle();
			     		return false;
			     	}
				});
				
			}
			
			//배치 실행 
			var batchParam = new Object();
			batchParam.packageName = gridData[i].pkgNm;
			batchParam.className = gridData[i].execObj;
			
			var args = "";
			args += clcWrkNo + "\|";		//1. clcWrkNo
			args += $("#soId").val() + "\|";		//2. soId
			args += dateFormatToStringYYYYMM($('#bsYymm').val() ) + "\|";		//3. billYymm
			args += "01" + "\|";		//4. billCycl
			args += gridData[i].batPgmId + "\|";		//5. pgmId -
			args += "1" + "\|";		//6. pgmSeq
			args += batGrpId + "\|";		//7. grpId
			args += pgmExeSeqNo + "\|";		//8. pgmExeSeqNo
			args += userId;		//9. user
			
			batchParam.args = args;
			
			console.log(batchParam);
			
			$.post('http://192.168.10.214:18080/executeJob', batchParam, function (response) {
				console.log('success : ' + response.commonResult.success);
				console.log('message : ' + response.commonResult.message);
			});
			
			//배치 실행 
			
			//배치완료 될때까지 루프
			 
			selGridData = gridData[i];
			getBatchLog();
			/* 
			setTimeout(function(){
				intervalVal = setInterval(getBatchLog, 3000);
			}, 50); 
			 */
 
			//setTimeout(pageEnavle, 3000);
			//배치 종료후 버튼 활성화
			
			break;	//for문 종료
		}
		
		
	}
	
	//배치 종료
	if(checkBatch == "N"){
		pageEnavle();
	}
	
}//oneWork()

//로그테이블에서 데이터를 가지고와서 작업완료 될때까지 계속 호출
function getBatchLog(){
	
	clearInterval(intervalVal);
	
	var gridData = selGridData;
	var param = new Object();
	param.soId = $('#soId').val();
	param.bsYymm = dateFormatToStringYYYYMM($('#bsYymm').val() );
	param.batPgmId = gridData.batPgmId;
	param.batGrpId =batGrpId;
	
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
					//clearInterval(intervalVal);
					
					batchCount = 0; //배치 카운트 초기화
					
					
					//일괄 작업 일 경우 다시 호출
					if(batchLogList[0].batProcStat == "1" && batchType == "B"){
						
						$("#batchGrid").setGridParam({
							mtype: 'POST',
							datatype : 'json',
							async:false,	//싱크방식
					  	    postData : {
					  	    	soId: $('#soId').val()
					  			, bsYymm: dateFormatToStringYYYYMM($('#bsYymm').val() )
					  			, batGrpId : batGrpId
							}
						});

					   	$("#batchGrid").trigger("reloadGrid");
					}else{
						//건별 작업일 경우 종료
						pageEnavle();
						searchBatchList();
					}
					
				}else{		//진행중일경우
					clearInterval(intervalVal);
					intervalVal = setTimeout(getBatchLog, 3000);
				}
				
			}else{	//진행중일경우
				clearInterval(intervalVal);
				intervalVal = setTimeout(getBatchLog, 3000);
			
				//임시로 종료 시키기 위한작업 삭제 할것.
				if(batchCount > 10){	//루프 3번 돌동안 로그 안싸였을 경우 초기화
					alert('<spring:message code="MSG.M15.MSG00028"/>');	//MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
					clearInterval(intervalVal);
					pageEnavle();
					searchBatchList();
					batchCount = 0; //배치 카운트 초기화
				}
				
			}
			
		},
     	error : function(err){
     		alert('<spring:message code="MSG.M15.MSG00028"/>');	//MSG.M15.MSG00028=JDBC 또는 SQL 오류가 발생하였습니다.  시퀀스 채번 오류
     		pageEnavle();
     		searchBatchList();
     	}
	});
	
}

function pageInit(){
	btnDisable("oneWorkBtn");
	btnDisable("batchWorkBtn");
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

//페이지 로딩 삭제
function pageEnavle(){
	
	$('#dimMask').hide();  
	$('#loadingImage2').css({"display":"none"});
	
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
<form id="searchForm">
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
					<select id="soId" name="soId" class="w100p">
						<%-- <option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option> --%>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
								<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M10.LAB00033" /></th> <!-- 청구년월 -->
				<td>
					<div class="date_box">
						<div class="inp_date w130">
							<input type="text" id="bsYymm" name="bsYymm"  class="month-picker" readonly="readonly" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
		</thead>
	</table> 
</form>

<!--작업그룹표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M06.LAB00130" /></h4> <!-- 배치작업내역 -->
	</div>
	<div class="fr mt10">
		<a id='oneWorkBtn' class="grey-btn" href="#" title='<spring:message code="LAB.M01.LAB00246" />'><spring:message code="LAB.M01.LAB00246" /></a><!-- 건별작업 -->
		<a id='batchWorkBtn' class="grey-btn" href="#" title='<spring:message code="LAB.M08.LAB00198" />'><spring:message code="LAB.M08.LAB00198" /></a><!-- 일괄작업 -->
	</div>
</div>
<div id='gridDiv'>
	<table id="batchGrid" class="w100p"></table>
	<div id="batchGridPager"></div>
</div>

<!--작업그룹상세-->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">View</h4>
	</div>
</div>
<table class="writeview">
	<colgroup>
		<col style="width:100%;">
	</colgroup>
	<thead>
		<tr>
			<td>
				<textarea id="rsn" name="rsn" class="w100p h150" maxlength = "1500"></textarea>
			</td>
		</tr>
	</thead>
</table> 
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>
<!-- <div id="dimMask"></div> -->

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	//화면 초기화 처리
	
	pageInit();
	//그리드 처리
	$("#accssLogGrid").jqGrid({
		url : '/system/log/logMng/accessLogHist/mainListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			sdate : $("#sdate").val(),
			edate : $("#edate").val(),
			condUserId:$("#condUserId").val(),
			condWorkType:$("#condWorkType").val()
		},
		colModel: [ 
			    { label: '<spring:message code="LAB.M07.LAB00067"/>', name: 'userId', width : 100},
			    { label: '<spring:message code="LAB.M07.LAB00075"/>', name: 'userName', width : 100,sortable:false},
			    { label: '<spring:message code="LAB.M09.LAB00139"/>', name: 'orgId', width : 100,sortable:false},
			    { label: '<spring:message code="LAB.M09.LAB00154"/>', name: 'orgNm', width : 100,sortable:false},
			    { label: '<spring:message code="LAB.M07.LAB00069"/>', name: 'remoteAddr', width : 100,sortable:false}, 
			    { label: '<spring:message code="LAB.M07.LAB00210"/>', name: 'sessionId', width : 200,sortable:false},
			    { label: '<spring:message code="LAB.M04.LAB00010"/>' , name: 'fullLogin',width : 100,align:"center",formatter:stringTypeFormatterYYYYMMDDHH24MISS},
			    { label: '<spring:message code="LAB.M04.LAB00005"/>' , name: 'fullLogout',width : 100,align:"center",formatter:stringTypeFormatterYYYYMMDDHH24MISS},
			    { label: '<spring:message code="LAB.M04.LAB00004"/>', name: 'logoutStatus', width : 100,sortable:false},
			  			    
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 480,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "accssList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	    rowNum: 20,
	    pager: "#jqGridPager",
	    onCellSelect : function(rowid, index, contents, event){
	    	
	    },
	   	loadComplete : function (data) {
	   		$("#accssLogGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		},
    	sortable: { update: function(permutation) {
    		$("#accssLogGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	$("#accssLogGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#accssLogGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}); 
	
	//검색 버튼 이벤트
	$('#btn_srch').click(function() {
		fnSearch();
	});
	//검색 enter 이벤트
	$( "#condUserId" ).keypress(function(event) {
		var checkR = "<c:out value='${menuAuthR}'/>"; 
		if(checkR == 'N') return;
		
   		if(event.keyCode == 13){
   			fnSearch();
   		}
	});
	
	//엑셀출력
   	$('#printBtn').on('click',function (e) {
	   		if($("#printBtn").hasClass('not-active')){
				return;
			}
    		printLog();
		}
    );
});
/*
 * 화면 초기화 함수
 */
function pageInit() {
	$('#sdate').datepicker();
    $('#sdate').datepicker("option", "maxDate", $("#edate").val());
    $('#sdate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#edate").datepicker( "option", "minDate", selectedDate );
    });
 
    $('#edate').datepicker();
    $('#edate').datepicker("option", "minDate", $("#sdate").val());
    $('#edate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#sdate").datepicker( "option", "maxDate", selectedDate );
    });
    
	$("#prcssLogGrid").clearGridData();

	//입력부 초기화
	inputInit('N');

}
/*
 * 입력부 초기화 함수
 */
function inputInit(srch) {
	
	//검색정보 초기화
	if (srch=='Y') {	//검색조건유지
		$('#condUserId').val($('#condUserId').val());
		
	}else{
		$('#condUserId').val('');
		$('#condUserId').focus();
		
	}
}
function fnSearch(){
	$("#accssLogGrid").clearGridData();
	//입력부 초기화
	inputInit('Y');
	var input1=dateFormatToStringYYYYMMDD($("#sdate").val());
	var input2=dateFormatToStringYYYYMMDD($("#edate").val());

	var date1 = new Date(input1.substr(0,4),input1.substr(4,2)-1,input1.substr(6,2));
	var date2 = new Date(input2.substr(0,4),input2.substr(4,2)-1,input2.substr(6,2));

	var interval = date2 - date1;
	var day = 1000*60*60*24;

	if(parseInt(interval/day)>30){
		alert('<spring:message code="MSG.M01.MSG00053"/>');
		return;
	}
	
	$("#accssLogGrid").setGridParam({
		postData : {
			sdate : dateFormatToStringYYYYMMDD($("#sdate").val()),
			edate : dateFormatToStringYYYYMMDD($("#edate").val()),
			condUserId:$("#condUserId").val(),
			condWorkType:$("#condWorkType").val(),
			srchYn:"Y"
		}
	});

	$("#accssLogGrid").trigger("reloadGrid");	
}

function printLog(){
	var input1=dateFormatToStringYYYYMMDD($("#sdate").val());
	var input2=dateFormatToStringYYYYMMDD($("#edate").val());

	var date1 = new Date(input1.substr(0,4),input1.substr(4,2)-1,input1.substr(6,2));
	var date2 = new Date(input2.substr(0,4),input2.substr(4,2)-1,input2.substr(6,2));

	var interval = date2 - date1;
	var day = 1000*60*60*24;

	if(parseInt(interval/day)>30){
		alert('<spring:message code="MSG.M01.MSG00053"/>');
		return;
	}

	var param = 'sdate=' + dateFormatToStringYYYYMMDD($("#sdate").val());
	param = param + '&edate=' + dateFormatToStringYYYYMMDD($("#edate").val());
	param = param + '&condUserId=' + $("#condUserId").val();
	param = param + '&condWorkType=' + $("#condWorkType").val();
	
	$.download('getAccessLogExcelAction.xlsx',param,'post');
}
</script>
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
<div class="main_btn_box">
	<div class="fr mt10">
		<ntels:auth auth="${menuAuthR}">
			<a href="#" id="btn_srch" class="grey-btn" title="<spring:message code="LAB.M09.LAB00158"/>"><span
				class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
		</ntels:auth>
	</div>
</div>
<table class="writeview">
		<colgroup>
		<col style="width: 10%;">
		<col style="width: 23%;">
		<col style="width: 10%;">
		<col style="width: 23%;">
		
	</colgroup>
	<thead>
		<tr id="cond">
			<th title="<spring:message code="LAB.M01.LAB00196"/>"><spring:message code="LAB.M01.LAB00196"/></th>
			<td>
				<div class="date_box">
					<div class="inp_date w135">
						<fmt:parseDate value="${sdate}" var="sdate" pattern="${dateFormat1}"/> 
			 			<input  type="text" id="sdate" name="sdate" value="<fmt:formatDate value="${sdate}"  pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a> 
					</div>
					<span class="dash">~</span>
					<div class="inp_date w135">
					  <input  type="text"  id="edate" name="edate" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th title="<spring:message code="LAB.M07.LAB00067"/>"><spring:message code="LAB.M07.LAB00067"/></th>
			<td>
				<input name="condUserId" id="condUserId" type="text" class="w270" />
			</td>
			
		</tr>
	</thead>
</table>
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00068"/></h4>
	</div>
	<div class="fr mt10">
			<ntels:auth auth="${menuAuthP}">		
				<a id="printBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
			</ntels:auth>
	</div>
</div>
<div id='gridDiv' class="w100p">
	<table id="accssLogGrid" class="w100p"></table>
	<div id="jqGridPager"></div>
</div> 

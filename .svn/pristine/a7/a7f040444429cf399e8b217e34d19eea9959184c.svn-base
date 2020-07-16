<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

var checkInit ="N"; //초기화 버튼을 제어하기 위한 전역 변수

$(document).ready(function() {
	
	initGrid();	
	btnDisable('btn_update');
	btnDisable('btn_insert');
	btnDisable('btn_delete');
	
	$("#searchStsCd").selectmenu({
	    change: function() {
			var param = new Object();
			param.searchStsCd =  $("#searchStsCd").val();
			param.searchStsCd = $(this).val();
			if($("#searchStsCd").val() == null || $("#searchStsCd").val() == ""){
				location.reload();
				return;
			}
			$.ajax({
				url : 'statisticsMgtListSearch.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					$("#actDt").val(data.getStatisticeDtl.actDt);
					$("#inactDt").val(data.getStatisticeDtl.inactDt);				
					$("#stmtTp").val(data.getStatisticeDtl.stmtTp);
					$("#stmtTp").selectmenu("refresh");
					$("#soId").val(data.getStatisticeDtl.soId);
					$("#soId").selectmenu("refresh");				
					$("#stmtNm").val(data.getStatisticeDtl.stmtNm);
					$("#stmtCd").val(data.getStatisticeDtl.stmtCd);
					$("#tmout").val(data.getStatisticeDtl.tmout);
					$("#chgrId").val(data.getStatisticeDtl.chgrId);
					$("#chgrNm").val(data.getStatisticeDtl.chgrNm);
					$("#regrId").val(data.getStatisticeDtl.regrId);
					$("#regrNm").val(data.getStatisticeDtl.regrNm);
					$("#stmt").val(data.getStatisticeDtl.stmt);
					$("#stmt").val(data.getStatisticeDtl.stmt);
					$("#rmrkDesc").val(data.getStatisticeDtl.rmrkDesc);
					$("#chgDate").val(data.getStatisticeDtl.chgDate);		
					$("#regDate").val(data.getStatisticeDtl.regDate);						
					$("#useAbleGrid").setGridParam({
						postData : {
							varDefn : data.getStatisticeDtl.varDefn
						}
					});										
					btnEnable('btn_update');
					btnEnable('btn_delete');					
					$("#useAbleGrid").trigger("reloadGrid"); 					
					$("#selectedGrid").setGridParam({
						postData : {
							varDefn : data.getStatisticeDtl.varDefn
						}
					});				  
					$("#selectedGrid").trigger("reloadGrid"); 
				},
			    error: function(request,status,error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    },
				complete : function() {
				}
			});	
	    }			
	});
	
	$("#searchStsCd").val($("#searchStsCd").val());
	$("#searchStsCd").selectmenu("refresh");
	
	$("#lay_next_btn").click(function() {
		goInsert();
	});
	$("#lay_prev_btn").click(function() {
		goDel();
	});	
	
	$(window).resize(function() {
		$("#userGrid").setGridWidth($('#userGridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function insertEvent() {

	if($("#stmtTp").val() == "") {
		alert('<spring:message code="MSG.M15.MSG00051"/>'); 
		$("#stmtTp").focus();
		return;
	}
	if($("#soId").val() == "") {
		alert('<spring:message code="MSG.M07.MSG00001"/>'); 
		$("#soId").focus();
		return;
	}	
	if($("#stmtNm").val() == "") {
		alert('<spring:message code="MSG.M13.MSG00031"/>'); 
		$("#stmtNm").focus();
		return;
	}	
	if($("#actDt").val() == "") {
		alert('<spring:message code="MSG.M07.MSG00135"/>'); 
		$("#actDt").focus();
		return;
	}	
	if($("#inactDt").val() == "") {
		alert('<spring:message code="MSG.M09.MSG00068"/>'); 
		$("#inactDt").focus();
		return;
	}		
	if($("#tmout").val() == "") {
		alert('<spring:message code="MSG.M09.MSG00069"/>'); 
		$("#tmout").focus();
		return;
	}		
	if($("#stmt").val() == "") {
		alert('<spring:message code="MSG.M15.MSG00052"/>'); 
		$("#stmt").focus();
		return;
	}		
	if($("#stmt").val().trim().toUpperCase().indexOf("SELECT")=="-1") {
		 alert('<spring:message code="MSG.M09.MSG00070"/>');
		 return;
	}

	var statisticsMgtVO = new Object();
	var actDt = dateFormatToStringYYYYMMDD($('#actDt').val());
	var inactDt = dateFormatToStringYYYYMMDD($('#inactDt').val());
	statisticsMgtVO.actDt = actDt;	
	statisticsMgtVO.inactDt = inactDt;		

	var value = "";
	for(var i=1; i< $("#selectedGrid").getRowData().length+1; i++){
		 value = value + $("#selectedGrid").getRowData(i).commonCd;
		 if(i != $("#selectedGrid").getRowData().length){
			value = value+",";
		 }
	}

	statisticsMgtVO.varDefn = value;
	statisticsMgtVO.stmtTp = $("#stmtTp").val();
	statisticsMgtVO.soId = $("#soId").val();
	statisticsMgtVO.stmtNm = $("#stmtNm").val();
	statisticsMgtVO.tmout = $("#tmout").val();	
	statisticsMgtVO.rmrkDesc = $("#rmrkDesc").val();
	statisticsMgtVO.stmt = $("#stmt").val();
	//param.stmt = encodeURIComponent($("#stmt").val());
	console.log(statisticsMgtVO);
	var url = '/customer/statistics/statisticsMgt/statisticsMgtInsertAction.json';
	$.ajax({
		type : "POST",
		url : url,
		data : JSON.stringify(statisticsMgtVO),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			if(data.result != "0"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				window.location.reload();
			}

		},		
		beforeSend: function(data){
   		},
		error : function(err){
     		ajaxErrorCallback(err);
     	}
	}); 	
	
}


//수정 버튼 클릭시 수정 처리
function updateEvent(){	

	if($("#stmtTp").val() == "") {
		alert('<spring:message code="MSG.M15.MSG00051"/>'); 
		$("#stmtTp").focus();
		return;
	}
	if($("#soId").val() == "") {
		alert('<spring:message code="MSG.M07.MSG00001"/>'); 
		$("#soId").focus();
		return;
	}	
	if($("#stmtNm").val() == "") {
		alert('<spring:message code="MSG.M13.MSG00031"/>'); 
		$("#stmtNm").focus();
		return;
	}	
	if($("#actDt").val() == "") {
		alert('<spring:message code="MSG.M07.MSG00135"/>'); 
		$("#actDt").focus();
		return;
	}	
	if($("#inactDt").val() == "") {
		alert('<spring:message code="MSG.M09.MSG00068"/>'); 
		$("#inactDt").focus();
		return;
	}		
	if($("#tmout").val() == "") {
		alert('<spring:message code="MSG.M09.MSG00069"/>'); 
		$("#tmout").focus();
		return;
	}		
	if($("#stmt").val() == "") {
		alert('<spring:message code="MSG.M15.MSG00052"/>'); 
		$("#stmt").focus();
		return;
	}	
	
	if($("#stmt").val().trim().toUpperCase().indexOf("SELECT")=="-1") {
		 alert('<spring:message code="MSG.M09.MSG00070"/>');
		 return;
	}

	var statisticsMgtVO = new Object();
	var actDt = dateFormatToStringYYYYMMDD($('#actDt').val());
	var inactDt = dateFormatToStringYYYYMMDD($('#inactDt').val());
	statisticsMgtVO.actDt = actDt;	
	statisticsMgtVO.inactDt = inactDt;		

	var value = "";
	for(var i=1; i< $("#selectedGrid").getRowData().length+1; i++){
		 value = value + $("#selectedGrid").getRowData(i).commonCd;
		 if(i != $("#selectedGrid").getRowData().length){
			value = value+",";
		 }
	}

	statisticsMgtVO.stmtCd =$("#stmtCd").val();
	statisticsMgtVO.varDefn = value;
	statisticsMgtVO.stmtTp = $("#stmtTp").val();
	statisticsMgtVO.soId = $("#soId").val();
	statisticsMgtVO.stmtNm = $("#stmtNm").val();
	statisticsMgtVO.tmout = $("#tmout").val();	
	statisticsMgtVO.rmrkDesc = $("#rmrkDesc").val();
	statisticsMgtVO.stmt = $("#stmt").val();
	//param.stmt = encodeURIComponent($("#stmt").val());
	console.log(statisticsMgtVO);

	var url = '/customer/statistics/statisticsMgt/statisticsMgtUpdateAction.json';
	$.ajax({
		type : "POST",
		url : url,
		data : JSON.stringify(statisticsMgtVO),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			if(data.result != "0"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				window.location.reload();
			}

		},		
		beforeSend: function(data){
   		},
		error : function(err){
     		ajaxErrorCallback(err);
     	}
	}); 

}

//삭제 버튼 클릭시 삭제 처리
function deleteEvent(){	

	if (!confirm('<spring:message code="MSG.M07.MSG00052" />')){
		return;
	}

	var statisticsMgtVO = new Object();
	statisticsMgtVO.stmtCd =$("#stmtCd").val();

	var url = '/customer/statistics/statisticsMgt/statisticsMgtDeleteAction.json';
	$.ajax({
		type : "POST",
		url : url,
		data : JSON.stringify(statisticsMgtVO),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			if(data.result != "0"){
				alert('<spring:message code="MSG.M07.MSG00053" />');	//저장되었습니다.
				 window.location.reload();
			}

		},		
		beforeSend: function(data){
   		},
		error : function(err){
     		ajaxErrorCallback(err);
     	}
	}); 

}

function initGrid() {
	
	setClear2();
	var param = new Object();
	param.varDefn = $("#varDefn").val();

	$("#useAbleGrid").jqGrid({
		
	   	url:'statisticsMgtListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   		 	{ label: '<spring:message code="LAB.M07.LAB00355"/>' , name: 'commonCd', width : 120},
		   	{ label: '<spring:message code="LAB.M09.LAB00247"/>' , name: 'commonCdNm', width : 120}
   		],

	   	rowNum:20,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
	   	pager: '#pager2',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 180,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	setSelectedDate(rowId);
        },
        loadComplete: function(obj){
        	$("#useAbleGrid").setGridWidth($('#useAbleGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#useAbleGrid").setGridWidth($('#useAbleGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#selectedGrid").jqGrid({
		
	   	url:'statisticsMgtListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   		 	{ label: '<spring:message code="LAB.M07.LAB00356"/>' , name: 'commonCd', width : 120},
		   	{ label: '<spring:message code="LAB.M09.LAB00247"/>' , name: 'commonCdNm', width : 120}
   		],
		cellEdit:true,
		cellsubmit:'clientArray',
   	   	rowNum:20,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		jsonReader: {
			repeatitems : true,
			root : "selList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
	   	pager: '#pager3',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 180,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	setSelectedDate(rowId);
        },
        loadComplete: function(obj){
        	$("#selectedGrid").setGridWidth($('#selectedGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) { 
			$("#selectedGrid").setGridWidth($('#selectedGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#useAbleGridDiv").setGridWidth($('#useAbleGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#selectedGrid").setGridWidth($('#selectedGridDiv').width(),false);
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#useAbleGrid").setGridWidth($('#useAbleGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#selectedGrid").setGridWidth($('#selectedGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

//상세정보 
function setSelectedDate(rowId){		
}
function setClear(){

	if(checkInit=="N"){
		$("#searchStsCd").val("");
		$('#searchStsCd').selectmenu("refresh");
		$('#searchStsCd').selectmenu("disable");	
		$("#stmtTp").val("");
		$('#stmtTp').selectmenu("refresh");
		$('#soId').val("");
		$('#soId').selectmenu("refresh");	
		$("#stmtNm").val("");	
		$("#stmtCd").val("");
		$('#stmtCd').addClass('not-active');
		$('#stmtCd').attr('disabled', true);
		$("#actDt").val("");
		$("#inactDt").val("");
		$("#tmout").val("");	
		$("#chgrNm").val("");
		$('#chgrNm').addClass('not-active');
		$('#chgrNm').attr('disabled', true);	
		$("#chgDate").val("");
		$('#chgDate').addClass('not-active');
		$('#chgDate').attr('disabled', true);

		$("#regrNm").val("");
		$('#regrNm').addClass('not-active');
		$('#regrNm').attr('disabled', true);	
		$("#regDate").val("");
		$('#regDate').addClass('not-active');
		$('#regDate').attr('disabled', true);

		$("#rmrkDesc").val("");
		$("#stmt").val("");
		btnEnable('btn_insert');
		checkInit = "Y";
		
	}else if(checkInit=="Y"){

		/*
		$("#searchStsCd").val("");
		$('#searchStsCd').selectmenu("refresh");
		$('#searchStsCd').selectmenu("enable");	
		$("#stmtTp").val("");
		$('#stmtTp').selectmenu("refresh");
		$('#soId').val("");
		$('#soId').selectmenu("refresh");	
		$("#stmtNm").val("");	
		$("#stmtCd").val("");
		$('#stmtCd').addClass('not-active');
		$('#stmtCd').attr('enable', true);
		$("#actDt").val("");
		$("#inactDt").val("");
		$("#tmout").val("");	
		$("#chgrNm").val("");
		$('#chgrNm').addClass('not-active');
		$('#chgrNm').attr('enable', true);	
		$("#chgDate").val("");
		$('#chgDate').addClass('not-active');
		$('#chgDate').attr('enable', true);

		$("#regrNm").val("");
		$('#regrNm').addClass('not-active');
		$('#regrNm').attr('disabled', true);	
		$("#regDate").val("");
		$('#regDate').addClass('not-active');
		$('#regDate').attr('disabled', true);

		$("#rmrkDesc").val("");
		$("#stmt").val("");
		
		btnDisable('btn_update');
		btnDisable('btn_insert');
		btnDisable('btn_delete');
		*/
		checkInit = "N";
		location.reload();
		/*
		$( '#useAbleGrid').empty();
		$( '#selectedGrid').empty();

		$.ajax({
			url : 'statisticsMgtList.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {

			},
			error: function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			},
			complete : function() {
			}
		});	
		*/
	}
}

function setClear2(){
	$("#searchStsCd").val("");
	$('#searchStsCd').selectmenu("refresh");	
	$("#stmtTp").val("");
	$('#stmtTp').selectmenu("refresh");
	$('#soId').val("");
	$('#soId').selectmenu("refresh");	
	$("#stmtNm").val("");	
	$("#stmtCd").val("");
	$('#stmtCd').addClass('not-active');

	$("#actDt").val("");
	$("#inactDt").val("");
	$("#tmout").val("");	
	$("#chgrNm").val("");
	$('#chgrNm').addClass('not-active');
    $('#chgrNm').attr('disabled', true);	
	$("#chgDate").val("");
	$('#chgDate').addClass('not-active');
    $('#chgDate').attr('disabled', true);

	$("#regrNm").val("");
	$('#regrNm').addClass('not-active');
	$('#regrNm').attr('disabled', true);	
	$("#regDate").val("");
	$('#regDate').addClass('not-active');
	$('#regDate').attr('disabled', true);

	$("#rmrkDesc").val("");
	$("#stmt").val("");
}

function goInsert() {
	var param = new Object();
	var rowId = jQuery("#useAbleGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert('<spring:message code="MSG.M07.MSG00096"/>');
		return;
	}
	param.commonCd = $("#useAbleGrid").getRowData(rowId).commonCd;
	param.commonCdNm = $("#useAbleGrid").getRowData(rowId).commonCdNm;	
	$("#selectedGrid").jqGrid('addRowData', $("#selectedGrid").getDataIDs().length+1, param);
}

function goDel() {

	var param = new Object();
	var rowId = jQuery("#selectedGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00096"/>");
		return;
	}
	$("#selectedGrid").jqGrid('delRowData', rowId);
}

function test(){
	
	if($("#tmout").val() == "") {
		alert('<spring:message code="MSG.M09.MSG00069"/>'); 
		$("#tmout").focus();
		return;
	}		

	if($("#stmt").val().trim().toUpperCase().indexOf("SELECT")=="-1") {
		 alert('<spring:message code="MSG.M09.MSG00070"/>');
		 return;
	}

	var param = new Object();
	param.stmt = encodeURIComponent($("#stmt").val());
	param.tmout = $("#tmout").val();

	var url = '/customer/statistics/statisticsMgt/testSql.json';

	$.ajax({
        url:url,
        type:'POST',
        data : param,
        dataType: 'json',
        success: function(data){
			//alert(data.colNm[0].name);
        	alert('<spring:message code="MSG.M08.MSG00086"/>');
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
function openExamplePopup(){
	
	$.ajax({
		type : "post",
		url : '/customer/statistics/statisticsMgt/statisticsExamplePopup.ajax',
		data : {

		},
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
<form id="statisticsMgtList" name="statisticsMgtList" method="post">
	<input type="hidden" id="varDefn" name="varDefn" />	
	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M12.LAB00012" />			
				</h4>
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:5%;">
			<col style="width:20%;">
			<col style="width:5%;">
			<col style="width:20%;">
			<col style="width:5%;">
			<col style="width:20%;">		
			<col style="width:5%;">
			<col style="width:20%;">					
		</colgroup>
		 <thead> 
			   <tr>
					<th>
						<spring:message code="LAB.M12.LAB00013" />		
					</th>
					<td>
						<select name="searchStsCd" id="searchStsCd" class="w270">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${getStatisticeList}" var="getStatisticeList" varStatus="status">
								<option value="${getStatisticeList.stmtCd}">
									${getStatisticeList.stmtNm }
								</option>
							</c:forEach>							
						</select>
					</td>
					<th>
						<spring:message code="LAB.M15.LAB00105" />	
					</th>
					<td>
						<select name="stmtTp" id="stmtTp" class="w270">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${sqlTpList}" var="sqlTpList" varStatus="status">
								<option value="${sqlTpList.commonCd}">
									${sqlTpList.commonCdNm }
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00003" />	
					</th>
					<td>
						<select class="w150" id="soId" name="soId">
							<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
								<option value="${soAuthList.so_id}" <c:if test="${serviceMgt.soId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
							</c:forEach>
						</select>             
					</td>
					<th>
						<spring:message code="LAB.M12.LAB00014" />	
					</th>
					<td>
						<input type="text" id="stmtNm" name="stmtNm" class="w250" value="${stmtNm}">    
					</td>	
				</tr>		 
			   <tr>
					<th>
						<spring:message code="LAB.M12.LAB00015" />	
					</th>
					<td>
						<input type="text" id="stmtCd" name="stmtCd" class="w250" value="${stmtCd}"> 
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00354" />	
					</th>
					<td>
						<div class="date_box">
							<div class="inp_date w150">
								<input type="text" id="actDt" name="actDt" class="datepicker" readonly="readonly" />
								<a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00169" />	
					</th>
					<td>
						<div class="date_box">
							<div class="inp_date w150">
								<input type="text" id="inactDt" name="inactDt" class="datepicker" readonly="readonly" />
								<a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>		
					<th>
						<spring:message code="LAB.M09.LAB00246" />	
					</th>
					<td>
						<input type="text" id="tmout" name="tmout" class="w250" value="${tmout}"> 
					</td>					
				</tr>
			   <tr>
					<th>
						<spring:message code="LAB.M09.LAB00255" />
					</th>
					<td>
						<input type="hidden" id="regrId" name="regrId" class="w250" value="${regrId}">
						<input type="text" id="regrNm" name="regrNm" class="w250" value="${regrNm}">						
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00256" />						
					</th>
					<td>
						<input type="text" id="regDate" name="regDate" class="w250" value="${regDate}">	
					</td>										
					<th>
						<spring:message code="LAB.M06.LAB00062" />	
					</th>
					<td>
						<input type="hidden" id="chgrId" name="chgrId" class="w250" value="${chgrId}">
						<input type="text" id="chgrNm" name="chgrNm" class="w250" value="${chgrNm}">						
					</td>
					<th>
						<spring:message code="MSG.M06.MSG00041" />						
					</th>
					<td>
						<input type="text" id="chgDate" name="chgDate" class="w250" value="${chgDate}">	
					</td>										
				</tr>	
			   <tr>
					<th>
						<spring:message code="LAB.M06.LAB00093" />
					</th>
					<td colspan='7'>
						<input type="text" id="rmrkDesc" name="rmrkDesc" class="w800" value="${rmrkDesc}">         
					</td>
				</tr>
			   <tr>
					<th>
						<spring:message code="LAB.M15.LAB00106" />&nbsp;<a href="javascript:openExamplePopup();">[<spring:message code="LAB.M08.LAB00204" />]</a>
					</th>
					<td colspan='7'>
						<textarea id="stmt" name="stmt" class="w100p h230"></textarea>             
					</td>
				</tr>												
		</thead>
	</table> 
	<div class="main_btn_box">
		<div class="fr">
			<!--테스트-->
			<a class="grey-btn"  href="javascript:test();" title='<spring:message code="LAB.M10.LAB00108"/>'><span class="re_icon"><spring:message code="LAB.M10.LAB00108"/></span></a>
		</div>
	</div>
<!--사용자부 -->
<div class="lay_box2">
	<div class="lay_left2">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title">
		            	<spring:message code="LAB.M06.LAB00141" />
		            </h4>
			</div>
		</div>
		<div id='useAbleGridDiv'>
			<table id="useAbleGrid" class="w100p"></table>
		</div>
	</div>
	
	<img id='lay_prev_btn' class='lay_prev_icon' src='/images/icon/ico_cal_prev.png' width="25" height="20">
	<img id='lay_next_btn' class='lay_next_icon' src='/images/icon/ico_cal_next.png' width="25" height="20">

	<div class="lay_right2">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title">
		            	
		            </h4>
			</div>
		</div>
		<div id='selectedGridDiv'>
			<table id="selectedGrid" class="w100p"></table>
		</div>
		<!--버튼-->	
		<div class="main_btn_box">
			<div class="fr">
				<!--초기화-->
				<a class="grey-btn"  href="javascript:setClear();" title='<spring:message code="LAB.M10.LAB00050"/>'><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
				<!--등록-->
				<a id="btn_insert" class="white-btn" href="javascript:insertEvent();"  title='<spring:message code="LAB.M03.LAB00075"/>'><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
				<!--수정-->
				<a id="btn_update" class="white-btn" href="javascript:updateEvent();" title='<spring:message code="LAB.M07.LAB00252"/>'><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
				<!--삭제-->
				<ntels:auth auth="${menuAuthD}">
				<a class="white-btn" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="javascript:deleteEvent();"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
				</ntels:auth>
				<!--출력-->
				<ntels:auth auth="${menuAuthP}">
				<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
				</ntels:auth>
			</div>
		  </div>
	</div>
</div>
</form>	
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>



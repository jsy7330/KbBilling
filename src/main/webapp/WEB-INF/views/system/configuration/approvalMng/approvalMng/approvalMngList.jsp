<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

var lastsel;
var regrId = '${session_user.userId}';

$(document).ready(function() {
	
	initGrid();	
	aprvMngDtlGrid();
	
	//조회
	$("#btnSearch").click(function(){
		searchAprvList();
	});
	
	//초기화
	$("#btnClear").click(function(){
		clearDetailInfo();
	});
	
	//결제자삭제
	$("#btnDeleteData").click(function(){
		deleteGridData();
	});
	
	
	//저장
	$("#btnSave").click(function(){
		saveDtlAprv();
	});
	
	$("#btnAddApproval").click(function(){
		var url="addApprovalPopup.ajax";
		var param = new Object();
		openModalPopup(url, param); 	//모달팝업 호출
	});
	
});


//결제정보 조회 리스트 그리드
function initGrid() {
	var param = new Object();
	param.searchAprvNm =  $("#searchAprvNm").val();
	param.searchAprvId =  $("#searchAprvId").val();

	$("#aprvTable").jqGrid({			
	   	url:'approvalMngListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
			{label:'REGR_NM',name:'regrNm', hidden:true},
			{label:'USE_YN',name:'useYn', hidden:true},
   		    {label:'업무ID', name: 'aprvId', width : 100},	//결재업무ID
   		 	{label:'업무명', name: 'aprvNm', width : 100},		//결재업무명
   			{label:'등록자', name: 'regrId', width : 100},		//등록자
			{label:'등록일', name: 'regDate',align:"center", width : 100, formatter: dateTypeFormatterYYYYMMDDHH24MISS}//등록일
   		],
	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 240,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	setSelectedDate(rowId);
        },
        loadComplete: function(obj){
        	$("#aprvTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);				
		},
		sortable: { update: function(permutation) {
			$("#aprvTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	$("#aprvTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리		
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#aprvTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

//결재정보 조회
function searchAprvList() {

	clearDetailInfo();
	
	$("#aprvTable").clearGridData();  // 이전 데이터 삭제
	var param = new Object();
	param.searchAprvNm =  $("#searchAprvNm").val();
	param.searchAprvId =  $("#searchAprvId").val();
	
    $("#aprvTable").setGridParam({ postData: param }).trigger("reloadGrid");
}

//결제상세 정보 초기화
function clearDetailInfo(){
	$("#aprvId").val("");
	$("#aprvNm").val("");
	$("#regrId").val("");
	$("#regrNm").val("");
	$("#regDate").val("");
	$('#useYn').val("");
	$('#useYn').selectmenu("refresh");
	
	$("#aprvDtlTable").clearGridData();  // 이전 데이터 삭제
	
	$("#aprvTable").jqGrid("resetSelection");	//결재정보 선택된 포커스 제거
}


//결제 상세정보 
function setSelectedDate(rowId){		
	var data = $("#aprvTable").getRowData(rowId);
	$("#aprvId").val(data.aprvId);
	$("#aprvNm").val(data.aprvNm);
	$("#regrId").val(data.regrId);
	$("#regrNm").val(data.regrNm);
	$("#regDate").val(data.regDate);
	$('#useYn').val(data.useYn);
	$('#useYn').selectmenu("refresh");
	
	searchDtlAprvList(data.aprvId) ;

}

//결재정보 조회
function searchDtlAprvList(aprvId) {
	
	$("#aprvDtlTable").clearGridData();  // 이전 데이터 삭제
	var param = new Object();
	param.aprvId = aprvId;
	
    $("#aprvDtlTable").setGridParam({ postData: param, datatype: "json" }).trigger("reloadGrid");
}


//결재대상자 정보 그리드
function aprvMngDtlGrid() {

	$("#aprvDtlTable").jqGrid({
		url:'approvalListAction.json?',
	    mtype:"POST",
	   	//datatype: "json",
	   	datatype: "local",
	   	postData : {},
	   	colModel:[
			{label:'ORG_ID',name:'orgId', hidden:true},
			{label:'USER_ID',name:'userId', hidden:true},
			//{label:'결재순서',name:'aprvStep', width:40, align:"center"}, 
			{label:'결재순서',name:'aprvStep', width:100, align:"center", editable:true, edittype:'select', formatoptions: {disabled : false}, editoptions:{
				value:gridSelectBox
				}
			}, 
			{label:'결재자조직',name:'orgNm', width:100, align:"center"}, 
			{label:'결재자명',name:'userName', width:100, align:"center"},			
			{label:'등록일',name:'regDate', width:100, align:"center", formatter: dateTypeFormatterYYYYMMDDHH24MISS}
	   	],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager3',
		sortable : false,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 240,				//화면 상태에 따라 크기 조절 할 것
		//multiselect: true,
		//cellurl : "/",
		onSelectRow: function(id){
			jQuery('#aprvDtlTable').jqGrid('saveRow',lastsel);	//저장하고 닫기 saveRow 저장 안하고 닫기 restoreRow
			jQuery('#aprvDtlTable').jqGrid('editRow',id,true);
			lastsel=id;
		},
		
		onCellSelect : function(rowId, index, contents, event){
        },
        loadComplete: function(obj){
        	$("#aprvDtlTable").setGridWidth($('#gridDiv3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$("#aprvDtlTable").setGridWidth($('#gridDiv3').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#aprvDtlTable").setGridWidth($('#gridDiv3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#aprvDtlTable").setGridWidth($('#gridDiv3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

}

function gridSelectBox(){
	
	var size = $("#aprvDtlTable").getGridParam("records") + 1;
	var value = "({";
	for (var i=1; i<size; i++){
		value += i+":"+i+","
	}
	value = value.substring(0, value.length-1)
	value +="})";
	return eval(value);
	
} 


function deleteGridData(){
	var rowId = $("#aprvDtlTable").jqGrid('getGridParam', 'selrow');
	$('#aprvDtlTable').jqGrid('delRowData', rowId);
	
}

function saveDtlAprv(){
	
	var params = getParams();
	
	if(params != false){
		
		$.ajax({
			url : 'saveAprvDtlList.json',
			type : 'POST',
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			async: false,
			data : params,
			success : function(data) {
				console.log(data);
				if(data.result != "0"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					searchAprvList();
					clearDetailInfo();
				}
			},
		    error: function(e){
		        alert("Failed to save.");
		    },
			complete : function() {
			}
		});
	}
	
}


function getParams(){
	
	var aprvId = $("#aprvId").val();
	var aprvNm = $("#aprvNm").val();
	var useYn = $("#useYn").val();
	
	if(aprvNm == null || aprvNm ==""){
		alert("업무명을 입력해 주세요.");
		return false;
	}
	
	if(useYn == null || useYn ==""){
		alert("사용여부를 선택해 주세요.");
		return false;
	}
	
	var params = new Array();
	
	var ids = $("#aprvDtlTable").jqGrid('getDataIDs');		//전체 rowId
	
	for(var i=0; i<ids.length; i++){
		$('#aprvDtlTable').jqGrid('saveRow',ids[i]); //셀렉트 박스 저장하고 닫는 작업 restoreRow saveRow
		
		var gridData = $("#aprvDtlTable").getRowData(ids[i]);	//rowId로 데이터 셀렉트
		
		var param = new Object();
		param.aprvId = aprvId;
		param.aprvNm = aprvNm;
		param.aprvStep = gridData.aprvStep;
		param.orgId = gridData.orgId;
		param.userId = gridData.userId;
		param.useYn = useYn;
		param.regrId = regrId;
		
		params.push(param);
	}
	
	var paramsLength = params.length; 
	if(paramsLength < 1){
		alert("결제대상자를 등록해 주세요.");
		return false;
	}
	
	//중복 체크 관련 : 리스티 1번째 부터 시작
	for(var i=0; i<paramsLength; i++){
		
		//결제순서 체크
		if(params[i].aprvStep < 1 || params[i].aprvStep > paramsLength){
			alert("결제순서가 잘못되었습니다.");
			return false;
		}
		
		for(var j=i+1; j<paramsLength; j++){
			
			//결제자 중복 체크
			if(params[i].userId == params[j].userId){
				alert("결제자가 중복 등록 되었습니다.");
				return false;
			}
			
			//결제순서 중복 체크
			if(params[i].aprvStep == params[j].aprvStep){
				alert("결제순서가 중복 등록 되었습니다.");
				return false;
			}
			
		}
		
	}
	
	console.log(params);
	params = JSON.stringify(params);
	
	return params;
	
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

	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">결재정보 조회</h4>
			</div>
			<div class="fr mt10">
				<a href="javascript:;" id="btnSearch" class="grey-btn" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
				<!--업무ID-->
				<th>업무ID</th>
				<td>					
					<input id="searchAprvId" name="searchAprvId" type="text" class="w200">
				</td>
				<!--업무명-->
				<th>업무명</th>
				<td>					
					<input id="searchAprvNm" name="searchAprvNm" type="text" class="w200">
				</td>
			</tr>
		</thead>
	</table>
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">결재정보 List</h4>
		</div>
	</div>
	<div id='gridDiv'>
		<table id="aprvTable" class="w100p"></table>
		<div id="pager2"></div>
	</div>

	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">결재 상세정보</h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:5%;">
			<col style="width:10%;">
			<col style="width:5%;">
			<col style="width:20%;">
			<col style="width:5%;">
			<col style="width:20%;">
			<col style="width:5%;">
			<col style="width:10%;">
			<col style="width:5%;">
			<col style="width:10%;">
		</colgroup>
		 <thead> 
			<tr>
				<!--업무ID-->
				<th>업무ID</th>
				<td><input id="aprvId" name="aprvId"  type="text" class="w180 not-active" disable></td>
				<!--업무명-->
				<th>업무명</th>
				<td><input id="aprvNm" name="aprvNm" type="text" class="w200"></td>
				<!--등록자-->
				<th>등록자</th>
				<td><input id="regrNm" name="regrNm" type="text" class="w180 not-active" disable></td>
				<!--등록일-->
				<th>등록일</th>
				<td><input id="regDate" name="regDate" type="text" class="w180 not-active" disable></td>
				<!--사용여부-->
				<th>사용여부</th>
				<td>
					<select id="useYn" name="useYn" class="w60">
						<option value=""selected="selected"><spring:message code="LAB.M09.LAB00063"/></option>
						<option value="Y">사용중</option>
						<option value="N">사용안함</option>
					</select>
				</td>
			</tr>
		</thead>
	</table> 
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">결재대상자정보 List</h4>
		</div>
	</div>
	<div id='gridDiv3'>
		<table id="aprvDtlTable" class="w100p"></table>
		<div id="pager3"></div>
	</div>


<div class="main_btn_box">
	<div class="fl">	
		<!--결재자추가-->
		<ntels:auth auth="${menuAuthU}">
		<a class="grey-btn" href="#" id="btnAddApproval" title='결재자추가' ><span class="edit_icon">결재자추가</span></a>
		</ntels:auth>	
		<!--결재자삭제-->
		<ntels:auth auth="${menuAuthU}">
		<a class="grey-btn" href="#" id="btnDeleteData" title='결재자삭제' ><span class="edit_icon">결재자삭제</span></a>
		</ntels:auth>	
	</div>
	<div class="fr">	
		<!--초기화-->
		<ntels:auth auth="${menuAuthC}">
		<a id="btnClear" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
		</ntels:auth>
		<!--저장-->
		<ntels:auth auth="${menuAuthC}">
		<a class="grey-btn" href="#"  id="btnSave" ><span class="write_icon">저장</span></a>
		</ntels:auth>		
	</div>
  </div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:800px;" >
</div>
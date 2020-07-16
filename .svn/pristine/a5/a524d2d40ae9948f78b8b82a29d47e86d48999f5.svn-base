<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	var aprvId = "${approvalMng.aprvId}";
	var regrId = '${session_user.userId}';
	//var regrId = 'ntels_ST';		//ntels_ST, ntels_SD
	var rowId = '';		//로그인한 사용자의 해당 rowId
	
	$(document).ready(function() {
		
		$("#aprvIdPop").val(aprvId);
			
		initGrid();
		
		$("#btnOkSave").click(function(){	//승인
			setApproval('02');
		});
		
		$("#btnNoSave").click(function(){	//반려
			setApproval('03');
		});

		$("#btnPopHist").click(function() {
			
			var url="/system/configuration/approvalMng/approvalMng/approvalHistPopup.ajax";
			url = url + "?&popType=o&aprvId=" + aprvId;
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
	});
	
	
	function initGrid() {

		var param = new Object();
		param.aprvId = aprvId;

		$("#aprvReportTable").jqGrid({
			
			url:'approvalOkListAction.json?',
			postData : param,
			mtype:"POST",
			datatype: "json",
			colModel: [ 
				{label:'결재자조직',name:'orgNm', width:100, align:"center"}, 
				{label:'결재자ID',name:'userId', width:100, align:"center"},
				{label:'결재자명',name:'userName', width:100, align:"center"},
				{label:'결재순서',name:'aprvStep', width:40, align:"center"},
				{label:'결재상태',name:'aprvStatNm', width:40, align:"center"},
				{label:'결재상태',name:'aprvStat', hidden:true },
				{label:'ORG_ID',name:'orgId', hidden:true},
				{label:'aprvNm',name:'aprvNm', hidden:true},
				{label:'aprvId',name:'aprvId', hidden:true},
				{label:'aprvMastId',name:'aprvMastId', hidden:true},
				{label:'regrId',name:'regrId', hidden:true},
			],

			rowNum:100,					//한번에 노출되는 row 수
			rowList:[10,20,30,50],	//선택시 노출되는 row 수
			//pager: '#aprvReportPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
			viewrecords: true, 
			height: 240,				//화면 상태에 따라 크기 조절 할 것
			ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    }, 
			loadComplete: function(obj){
				$("#aprvReportTable").setGridWidth($('#aprvReportDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				
				var data = $("#aprvReportTable").jqGrid("getRowData", 1);
				$("#aprvNmPop").val(data.aprvNm);
				
				//결재 승인, 반려 버튼 처리
				checkApproval();
				
			},
			loadError: function (jqXHR, textStatus, errorThrown) {
				ajaxErrorCallback(jqXHR);
			},
			sortable: { update: function(permutation) {
				$("#aprvReportTable").setGridWidth($('#aprvReportDiv').width(),false); //컬럼 위치 변경 후 재조정
				}
			}
		});

		$("#aprvReportTable").setGridWidth($('#aprvReportDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#aprvReportTable").setGridWidth($('#aprvReportDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});

	}
	
	//결재 승인, 반려 버튼 
	function checkApproval(){
		
		//버튼 숨김
		$("#btnOkSave").hide();
		$("#btnNoSave").hide();
		
		var ids = $("#aprvReportTable").jqGrid('getDataIDs');		//전체 rowId
		var statChk = true;
		
		for(var i=0; i<ids.length; i++){
			
			var gridData = $("#aprvReportTable").getRowData(ids[i]);	//rowId로 데이터 셀렉트
			
			console.log(gridData);
			
			//첫번째 데이터의 상태값이 01이고 로그인 아이디와 결제자 ID가 같은 결우 
			if(statChk && gridData.userId == regrId && ( gridData.aprvStat == '01' || gridData.aprvStat == '03' ) ){
				
				$("#btnOkSave").show();
				$("#btnNoSave").show();
				
				rowId = ids[i];	//로그인한 사용자의 해당 rowId
				
				break;
			}else{
				
				if(gridData.aprvStat == '02'){
					statChk == true;
				}else{
					statChk == false;
				}
				
			}
			
		}
		
	}
	
	function reloadGrid(){
		
		$("#aprvReportTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.aprvId = aprvId;
		
	    $("#aprvReportTable").setGridParam({ postData: param, datatype: "json" }).trigger("reloadGrid");
	    
	}
	
	function setApproval(type){
		
		var param = $("#aprvReportTable").getRowData(rowId);	//rowId로 데이터 셀렉트
		param.aprvStat = type;
		param.regrId = regrId;
		
		param = JSON.stringify(param);
		$.ajax({
			url : 'saveApprovalAction.json',
			type : 'POST',
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				
				reloadGrid();
				
				if(data.result > 0){
					alert('처리되었습니다.');	//처리되었습니다.
					//$("#btnClose").trigger('click');
				}
			},
		    error: function(e){
		        alert("Failed to save.");
		    },
			complete : function() {
			}
		});
		
		
	}

</Script>

<!-- title -->
<div class="layer_top">
   <div class="title">결제승인</div>
   <a href="javascript:closeModal();" class="close">Close</a>
</div>
<!--// title -->   

<form id="searchProd" name="searchProd" method="post">			

<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">결제승인</h4>
		</div>
		<div class="fr">
			<a href="javascript:;" id="btnPopHist" class="grey-btn" ><span class="search_icon">이력조회</span></a> 
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
			 <thead> 
			   <tr>						
					<th>업무ID</th>
					<td><input id="aprvIdPop" name="aprvIdPop" type="text" class="w200" readonly="readonly" /></td>
					<th>업무명</th>
					<td><input id="aprvNmPop" name="aprvNmPop" type="text" class="w300" readonly="readonly" /></td>
				</tr>
			</thead>
	</table>
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">결재대상자</h4>
		</div>
	</div>
	<div id='aprvReportDiv'>
		<table id="aprvReportTable" class="w100p"></table>
		<div id="aprvReportPager"></div>
	</div>
	<div class="btn_box">
		<a class="grey-btn" id="btnOkSave" href="#" style="display:none;" ><span class="confirm_icon">승인</span></a>
		<a class="grey-btn" id="btnNoSave" href="#" style="display:none;" ><span class="confirm_icon">반려</span></a>
		<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	 </div>
</form>	
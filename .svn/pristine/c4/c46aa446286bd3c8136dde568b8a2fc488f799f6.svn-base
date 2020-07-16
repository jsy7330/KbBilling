<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	var aprvId = "${approvalMng.aprvId}";
	var regrId = '${session_user.userId}';
	
	$(document).ready(function() {
		
		$("#aprvIdPop").val(aprvId);
			
		initGrid();
		
		$("#btnReportSave").click(function(){
			saveAprvReport();
		});
			
	});
	
	
	function initGrid() {

		var param = new Object();
		param.aprvId = aprvId;

		$("#aprvReportTable").jqGrid({
			
			url:'approvalListAction.json?',
			postData : param,
			mtype:"POST",
			datatype: "json",
			colModel: [ 
				{label:'ORG_ID',name:'orgId', hidden:true},
				{label:'aprvNm',name:'aprvNm', hidden:true},
				{label:'결재순서',name:'aprvStep', width:40, align:"center"}, 
				{label:'결재자조직',name:'orgNm', width:100, align:"center"}, 
				{label:'결재자ID',name:'userId', width:100, align:"center"},
				{label:'결재자명',name:'userName', width:100, align:"center"}			
			],

			rowNum:100,					//한번에 노출되는 row 수
			rowList:[10,20,30,50],	//선택시 노출되는 row 수
			pager: '#aprvReportPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
			viewrecords: true,	
			height: 240,				//화면 상태에 따라 크기 조절 할 것
			ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    }, 
			loadComplete: function(obj){
				$("#aprvReportTable").setGridWidth($('#aprvReportDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				
				var data = $("#aprvReportTable").jqGrid("getRowData", 1);
				$("#aprvNmPop").val(data.aprvNm);
				
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
	
	//결제상신 저장
	function saveAprvReport(){
		
		var ids = $("#aprvReportTable").jqGrid('getDataIDs');		//전체 rowId
		
		if(ids.length > 0){	//결재대상자가 있을경우
			
			var param = new Object();
			param.aprvId = aprvId;
			param.regrId = regrId;
			
			param = JSON.stringify(param);
			$.ajax({
				url : 'saveAprvReport.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result > 0){
						alert('결재상신 되었습니다.');	//결재상신 되었습니다.
						$("#btnClose").trigger('click');
					}else if(data.result == 0){
						alert('결재상신 대상자가 없습니다.');	//결재상신 대상자가 없습니다.
					}else if(data.result < 0){
						alert('결재상신된 데이터가 있습니다.');	//결재상신된 데이터가 있습니다.
					}
				},
			    error: function(e){
			        alert("Failed to save.");
			    },
				complete : function() {
				}
			});
		
		}else{
			alert("결재정보가 없습니다.");
		}

	}

</Script>

<!-- title -->
<div class="layer_top">
   <div class="title">결제상신</div>
   <a href="javascript:closeModal();" class="close">Close</a>
</div>
<!--// title -->   

<form id="searchProd" name="searchProd" method="post">			

<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">결제상신</h4>
		</div>
		<div class="fr">
			<%-- <a href="javascript:;" id="btnPopSearch" class="grey-btn" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> --%> 
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
		<a class="grey-btn" id="btnReportSave" href="#" ><span class="confirm_icon">상신</span></a>
		<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	 </div>
</form>	
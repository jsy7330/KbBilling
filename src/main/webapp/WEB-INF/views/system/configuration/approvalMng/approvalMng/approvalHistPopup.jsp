<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	var popType = "${approvalMng.popType}";	//팝업타입
	var aprvId = "${approvalMng.aprvId}";
	
	$(document).ready(function() {
		
		$("#aprvIdPop").val(aprvId);
		
		if(popType == "o"){	//일반팝업일 경우 버튼 닫기 처리
					
			$("#btnClosePop").click(function() {
				window.open("about:blank", "_self").close();
			});
			
			$("#btnClosePop1").click(function() {
				window.open("about:blank", "_self").close();
			});
			
		}
		
		initGrid();
		
	});
	
	
	function initGrid() {

		var param = new Object();
		param.aprvId = aprvId;

		$("#approvalHistTable").jqGrid({
			
			url:'approvalHistListAction.json?',
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
				$("#approvalHistTable").setGridWidth($('#approvalHistDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				
				var data = $("#approvalHistTable").jqGrid("getRowData", 1);
				$("#aprvNmPop").val(data.aprvNm);
				
			},
			loadError: function (jqXHR, textStatus, errorThrown) {
				ajaxErrorCallback(jqXHR);
			},
			sortable: { update: function(permutation) {
				$("#approvalHistTable").setGridWidth($('#approvalHistDiv').width(),false); //컬럼 위치 변경 후 재조정
				}
			}
		});

		$("#approvalHistTable").setGridWidth($('#approvalHistDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#approvalHistTable").setGridWidth($('#approvalHistDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});

	}
	
	
	
</script>	
<%-- 
<c:if test="${approvalMng.popType eq 'o'}"> 
	<div class="Layer_wrap" style="width:800px;" >
</c:if>
 --%>
<!-- title -->
<div class="layer_top">
   <div class="title">결재이력조회</div>
   <a href="javascript:;" id="btnClosePop"  class="close">Close</a>
</div>
<!--// title -->   

<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">결재이력조회</h4>
		</div>
		<div class="fr">
			<!-- <a href="javascript:;" id="btnPopHist" class="grey-btn" ><span class="search_icon">결재</span></a> -->
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
			<h4 class="ly_title">결재이력</h4>
		</div>
	</div>
	
	<c:if test="${approvalMng.popType eq 'm'}"> 
	<div class="layer_box">
		<table id="approvalHistTable" class="w100p"></table>
		<div id="approvalHistPager"></div>
	</div>
	</c:if>
	
	<c:if test="${approvalMng.popType eq 'o'}"> 
		<div id='approvalHistDiv'>
			<table id="approvalHistTable" class="w100p"></table>
			<div id="approvalHistPager"></div>
		</div>
	</c:if>
	
	
	
	<div class="btn_box">
		<a class="grey-btn close" href="#" id="btnClosePop1"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	 </div>
<%-- 
<c:if test="${approvalMng.popType eq 'o'}"> 
</div>
</c:if>
 --%>
</div>
	 
	 
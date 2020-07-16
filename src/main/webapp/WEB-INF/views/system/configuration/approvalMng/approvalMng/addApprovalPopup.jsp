<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	/* Modal */
	$(document).ready(function() {
		
		initGrid();	
		
		$("#btnPopSearch").click(function(){
			searchApproval();
		});
		
		$("#btnSelectId").click(function(){
			selectId();
		});
		
	});

	function closeModal() {
		$("#popup_dialog").remove();
	}
	
	function initGrid() {

		var param = new Object();
		param.searchPopOrgId =  $("#searchPopOrgId").val();
		param.searchPopOrgNm =  $("#searchPopOrgNm").val();
		param.searchPopUserId =  $("#searchPopUserId").val();
		param.searchPopUserName =  $("#searchPopUserName").val();

		$("#aprvAddTable").jqGrid({
			
			url:'addApprovalPopupAction.json?',
			postData : param,
			mtype:"POST",
			datatype: "json",
			colModel: [ 
				{ label: '조직코드', name: 'orgId', width : 100},			//조직코드
				{ label: '조직명', name: 'orgNm', width : 100},		//조직명
				{ label: '사원ID', name: 'userId', width : 100},		//사원ID
				{ label: '사원명', name: 'userName', align:"center", width : 100}//사원명
			],

			rowNum:10,					//한번에 노출되는 row 수
			rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
			pager: '#pager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
			viewrecords: true,	
			height: 240,				//화면 상태에 따라 크기 조절 할 것
			ondblClickRow : function(id){ //ROW 클릭시 이벤트
				selectId();
		    }, 
			loadComplete: function(obj){
				$("#aprvAddTable").setGridWidth($('#gridDiv2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			},
			loadError: function (jqXHR, textStatus, errorThrown) {
				ajaxErrorCallback(jqXHR);
				
			},
			sortable: { update: function(permutation) {
				$("#aprvAddTable").setGridWidth($('#gridDiv2').width(),false); //컬럼 위치 변경 후 재조정
				}
			}
		});

		$("#aprvAddTable").setGridWidth($('#gridDiv2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#aprvAddTable").setGridWidth($('#gridDiv2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});

	}
	
	function searchApproval() {
	
		$("#aprvAddTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.searchPopOrgId =  $("#searchPopOrgId").val();
		param.searchPopOrgNm =  $("#searchPopOrgNm").val();
		param.searchPopUserId =  $("#searchPopUserId").val();
		param.searchPopUserName =  $("#searchPopUserName").val();
		
	    $("#aprvAddTable").setGridParam({ postData: param }).trigger("reloadGrid");
	    
	}
	
	//결제대상자 선택
	function selectId(){
		
		var rowId  = $("#aprvAddTable").jqGrid("getGridParam", "selrow" );
		var data = $("#aprvAddTable").jqGrid("getRowData", rowId );
		
		var size = $("#aprvDtlTable").getGridParam("records")+1;
		
		
		var param = new Object();
		param.aprvStep = size;
		param.orgNm = data.orgNm;
		param.orgId = data.orgId;
		param.userName = data.userName;
		param.userId = data.userId;
		param.regDate = dateFormatterUsingDateYYYYMMDDHH24MISS(new Date());
		
		$("#aprvDtlTable").jqGrid('addRowData', $("#aprvDtlTable").getDataIDs().length+1, param);

		$("#btnClose").trigger('click');
		
	}
	
</Script>

<!-- title -->
<div class="layer_top">
   <div class="title">결재대상추가</div>
   <a href="javascript:closeModal();" class="close">Close</a>
</div>
<!--// title -->   

<form id="searchProd" name="searchProd" method="post">			

<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">결재대상자조회</h4>
		</div>
		<div class="fr">
			<a href="javascript:;"class="grey-btn" id="btnPopSearch" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
		
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:5%;">
			<col style="width:25%;">
			<col style="width:5%;">
			<col style="width:25%;">
			<col style="width:5%;">
			<col style="width:25%;">
			<col style="width:5%;">
			<col style="width:25%;">
		</colgroup>
			 <thead> 
			   <tr>						
					<th>조직코드</th>
					<td><input id="searchPopOrgId" name="searchPopOrgId" type="text" class="w100"></td>
					<th>조직명</th>
					<td><input id="searchPopOrgNm" name="searchPopOrgNm" type="text" class="w100"></td>
					<th>사원ID</th>
					<td><input id="searchPopUserId" name="searchPopUserId" type="text" class="w100"></td>
					<th>사원명</th>
					<td><input id="searchPopUserName" name="searchPopUserName" type="text" class="w100"></td>
				</tr>
			</thead>
	</table>
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">결재대상자</h4>
		</div>
	</div>
	<div id='gridDiv2'>
	<table id="aprvAddTable" class="w100p"></table>
		<div id="pager2"></div>
	</div>
	<div class="btn_box">
		<a class="grey-btn" id="btnSelectId" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
		<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	 </div>
</form>	
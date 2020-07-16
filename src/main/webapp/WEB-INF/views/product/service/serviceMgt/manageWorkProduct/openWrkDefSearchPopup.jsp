<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
<!--
$(document).ready(function() {

	initGrid();	//jqgrid 호출
	
	//버튼 클릭시
	$("#btn_search2").click(function() {
		$("#page").val("1");
		goSearch2();
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function goSearch2() {
	var param = new Object();

	param.soId =  $("#soId").val();		
	openPop( $("#soId").val());
	$("#popup_dialog").remove();
}

function getParam(){
	var param = new Object();	
	param.soId =  $("#soId").val();
	return param;
}

function goInsertValue(svcWrkGrpId,svcWrkGrpNm){
	$("#wrkDftWrker", parent.document.body).val(svcWrkGrpId);
	$("#wrkDftWrkerNm", parent.document.body).val(svcWrkGrpNm);
	$("#btnClose").trigger('click');
}

function initGrid(lng) {
	
	$("#attributePop").jqGrid({

		url : 'openWrkDefSearchAction.json?',
		mtype : "POST",
		datatype : "json",
		postData : {},
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		colModel : [ 
		            {label: '작업그룹ID',name : 'svcWrkGrpId',width : 50,key: true},
		 		    {label: '작업그룹',name : 'svcWrkGrpNm',width : 50}
		],
		rowNum:25,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager3',
		autowidth:true,
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		sortorder: "desc",
	    viewrecords: true,	
		height: 250,					//화면 상태에 따라 크기 조절 할 것
		ondblClickRow : function(rowid) { //ROW 클릭시 이벤트
			goInsertValue($("#attributePop").getRowData(rowid).svcWrkGrpId, $("#attributePop").getRowData(rowid).svcWrkGrpNm);
		}
	});

	//$("#attributePop").setGridWidth($('#gridDiv').width(),false);

		$("#attributePop").setGridWidth(380,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

}
//-->
</Script>

<!-- title -->
<div class="layer_top">
   <div class="title">상품별작업관리</div>
  <a href="#" class="close">Close</a>
</div>
<!--// title -->   

<form id="frmCommList" name="frmCommList" method="post">			
	<table class="writeview">
		<colgroup>
			<col style="width:30%;">
			<col style="width:70%;">
		</colgroup>
		 <thead> 
			   <tr>
					<th>사업</th>
					<td>
						<select class="w150" id="searchSoId" name="searchSoId">
							<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
								<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
		</thead>
	</table> 
</form>

<!--JQ Grid 리스트-->	
<div class="main_btn_box">
	<h4 class="sub_title">상품별작업관리</h4>
</div>
<div id='gridDiv' class="w100p">
<table id="attributePop" class="w100p"></table>
<!--<table id="attributePop" style="width: 100%"></table> -->
	<div id="pager3"></div>
</div>

<!--// center -->
<div class="btn_box">
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>


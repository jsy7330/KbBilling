<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--��¥ �޷� ����� ��� �ʿ�-->
<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	initGrid();	//jqgrid ȣ��	

	//��ư Ŭ����
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

function goSearch() {
	var param = new Object();
	
	param.unitSvcCd = $("#unitSvcCd").val();
	
	$("#unitSvcCdGrid").clearGridData();  // 이전 데이터 삭제
	$("#unitSvcCdGrid").setGridParam({ postData: param }).trigger("reloadGrid");
}

function initGrid() {
	
	var param = new Object();
	param.unitSvcCd = $("#unitSvcCd").val();
	
	$("#unitSvcCdGrid").jqGrid({
		url:'newProductDtlRatingCdListAction.json?',
		mtype:"POST",
		postData : param,
	   	datatype: "json",
	   	colModel: [ 
   			{ label: 'ifSys' , name: 'ifSys', hidden:true, width : 0},
   			{ label: 'remark' , name: 'remark', hidden:true, width : 0},
   		 	{ label: '과금코드', name: 'rateItmCd', width: 100, align:"center"},			//속성 코드
   			{ label: '과금설명', name: 'rateItmNm', width: 100},			//속성 코드
   		],
   		ondblClickRow: function(rowId) {
			$("#unitSvcCd").val($("#unitSvcCdGrid").getRowData(rowId).rateItmCd);
			$("#btnClose").trigger('click');
		},
	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager3',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	setSelectedDate(rowId);
        },
        loadComplete: function(obj){
        	$("#unitSvcCdGrid").setGridWidth($("#unitSvcCdGridDiv").width(), false); 
        }
	});

	$("#unitSvcCdGrid").setGridWidth(990, false); 
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#unitSvcCdGrid").setGridWidth($("#unitSvcCdGridDiv").width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

</script>
<!-- title -->
<div class="layer_top">
   <div class="title">과금코드불러오기</div>
   <a href="#" class="close">Close</a>
</div>
<!--// title -->   

<form id="unitSvcCd" name="unitSvcCd" method="post">			
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">과금코드불러오기</h4>
			</div>
			<div class="fr" >
	<a class="grey-btn" href="javascript:goSearch();" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158" /></span></a>
	</div>
		</div>
	</div>
	
	<div id='unitSvcCdGridDiv'>
		<table id="unitSvcCdGrid" class="w100p"></table>
		<div id="pager3">
		</div>
	</div>			
		
	<div class="btn_box">
		<a class="blue-btn" href="javascript:buttonEvent();" id="btn_update"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
			<!--�ݱ�-->
		<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>

</form>

<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {

var soId = '${soId}';
var orderId = '${orderId}';


initGrid(soId,orderId);	

function initGrid(soId,orderId) {
		var param = new Object();
		param.soId = soId;
		param.orderId = orderId;

		$("#popupBasicProdListGrid").jqGrid({
		   	url:'/system/sample/sample/example/getEstimate.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
				colModel: [
							{ label: '상품명', name: 'prodNm', width : 100, align:"left"},
							{ label: '과금항목명', name: 'rateItmNm', width : 150, align:"left"},
							{ label: '단가', name: 'unitPrice', width : 70, align : "right", align:"right"},
							{ label: '수량', name: 'cnt', width : 50, align : "right", formatter : 'integer', sorttype:'number'},
							{ label: '금액', name: 'billAmt', width : 130, align : "right", formatter : 'integer', sorttype:'number'}
				],
			viewrecords: true,
			shrinkToFit:false,
			height: 200,
			sortable : true,
			rowNum : 10000,
			jsonReader: {
				repeatitems : true,
				root : "estimateList2"
			},

			onCellSelect : function(rowid, index, contents, event){
				var data = $("#popupBasicProdListGrid").getRowData(rowid);
				selectBasicProd(rowid);
			},
			loadComplete : function () {
				$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfo_basicProdListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				$("#popupBasicProdListGrid").trigger("reloadGrid");
			},
			loadError: function (jqXHR, textStatus, errorThrown) {
				ajaxErrorCallback(jqXHR);
				
			},
			sortable: { 
				update: function(permutation) {
					$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfo_basicProdListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
			}
		});
}


	$('#btnClose2').click(function() {
		modalClose();
	});
	$('#btnClose3').click(function() {
		modalClose();
	});

});

function modalClose(){
	$("#popup_dialog2").html("");
	$("#popup_dialog2").hide();
}


/* Modal */
function closeModal() {
	$("#popup_dialog2").remove();
}

</Script>
<!-- title -->
<div class="layer_top">
   <div class="title">견적서보기</div>
   <a class="grey-btn close" href="#" id="btnClose2">Close</a>
</div>
<!--// title -->   
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">견적서보기</h4>
		</div>
	</div>
	<div id='popupProdInfo_basicProdInfo_basicProdListDiv'>
		<table id="popupBasicProdListGrid" class="w600p"></table>
	</div>
</div>			





<div class="btn_box">
<a class="grey-btn close" href="#" id="btnClose3"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

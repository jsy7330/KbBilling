<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {

		$('#btnSarchVissuePoInfo').click(function() {
			searchVissuePoInfo();
		});

		$('#btnConfirmOrder_confirmCancelOrderPopup').click(function() {

			var id = $("#vissuePoInfoTable").jqGrid("getGridParam", "selrow" );

			if(id == null){
				// 바우처를 선택해주세요.
				alert('<spring:message code="MSG.M06.MSG00001" />');
				return;
			}

			// 발행을 하시겠습니까?
			var check = confirm('<spring:message code="MSG.M06.MSG00015" />');
			if (check == false) {
				return;
			}

			var row = $("#vissuePoInfoTable").getRowData(id);

			var poNo = row.poNo;
			var poDt = '${vissueVO.poDt}';
			var soId = '${vissueVO.soId}';
			var vissueSeqNo = '${vissueVO.vissueSeqNo}';

			var param = {
				poNo : poNo
				, poDt : poDt
				, soId : soId
				, vissueSeqNo : vissueSeqNo
			};

			console.log('poNo : ' + poNo);
			console.log('poDt : ' + poDt);
			console.log('soId : ' + soId);
			console.log('vissueSeqNo : ' + vissueSeqNo);

			$.ajax({
				url : '/distributor/logistics/voucherMng/voucherGenerate/confirmOrder.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					$("#btnClose").trigger('click');
					$("#btnClose1Sep").trigger('click');
					refreshGrid();
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		});

		$('#btnCancelOrder_confirmCancelOrderPopup').click(function() {
			var id = $("#vissuePoInfoTable").jqGrid("getGridParam", "selrow" );

			if(id == null){
				// 바우처를 선택해주세요.
				alert('<spring:message code="MSG.M06.MSG00001" />');
				return;
			}

			// 발행을 하시겠습니까?
			var check = confirm('<spring:message code="MSG.M06.MSG00012" />');
			if (check == false) {
				return;
			}

			var row = $("#vissuePoInfoTable").getRowData(id);

			var poNo = row.poNo;
			var poDt = '${vissueVO.poDt}';
			var soId = '${vissueVO.soId}';
			var vissueSeqNo = '${vissueVO.vissueSeqNo}';

			var param = {
				poNo : poNo
				, poDt : poDt
				, soId : soId
				, vissueSeqNo : vissueSeqNo
			};

			console.log('poNo : ' + poNo);
			console.log('poDt : ' + poDt);
			console.log('soId : ' + soId);
			console.log('vissueSeqNo : ' + vissueSeqNo);

			$.ajax({
				url : '/distributor/logistics/voucherMng/voucherGenerate/cancelOrder.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					$("#btnColse1SeP").trigger('click');
					refreshGrid();
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		});

		initGrid();
	});
	
	function initGrid() {

		var param = {
			soId : '${vissueVO.soId}'
			, poNo : '${vissueVO.poNo}'
			, itemId : '${vissueVO.itemId}'
			, mncoId : '${vissueVO.mncoId}'
			, poStat : '${vissueVO.poStat}'
		};
		
		$("#vissuePoInfoTable").jqGrid({
	
		   	url:'/distributor/logistics/voucherMng/voucherGenerate/getVpoInfoList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[

				 { label : '<spring:message code="LAB.M06.LAB00031" />', name : 'poNo', width : 100 }
				 , { label : '<spring:message code="LAB.M09.LAB00106" />', name : 'itemId', width : 100 }
				 , { label : '<spring:message code="LAB.M09.LAB00111" />', name : 'itemNm', width : 100 }
				 , { label : '<spring:message code="LAB.M09.LAB00099" />', name : 'mncoId', width : 100 }
				 , { label : '<spring:message code="LAB.M09.LAB00098" />', name : 'mncoNm', width : 100 }

		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#vissuePoInfoPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    }, 
		    loadComplete: function(){
		    	$("#vissuePoInfoTable").setGridWidth(680,false);
		    },
	    	sortable: { update: function(permutation) {
	    		$("#vissuePoInfoTable").setGridWidth(680,false);
	    		}
	    	}
		});
	}

	function searchVoucher_confirmCancelOrderPopup() {
		var url="/system/common/common/productMng/productSearchPopup.ajax";
		url = url + "?soId=${soId}&popType=o&returnId1=itemId_confirmCancelOrderPopup&returnId2=itemNm_confirmCancelOrderPopup&presetItemTpCd=V";
		
		var width = 815;
		var height = 510;
		var LeftPosition=(screen.width-width)/2;
		var TopPosition=(screen.height-height)/2;
		
		window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
	}

	function searchVissuePoInfo() {
		$("#vissuePoInfoTable").clearGridData();  // 이전 데이터 삭제

		var param = {
			soId : '${vissueVO.soId}'
			, poNo : '${vissueVO.poNo}'
			, itemId : $('#itemId_confirmCancelOrderPopup').val()
			, mncoId : '${vissueVO.mncoId}'
			, poStat : '${vissueVO.poStat}'
		};
		
        $("#vissuePoInfoTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
	}
	
</script>


<div class="layer_top">
     <div class="title"><spring:message code="LAB.M06.LAB00037"/><!-- 발주정보선택 --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">

	<div class="ly_btn_box">
		<div class="fr">
			<a href="#"class="grey-btn" id="btnSarchVissuePoInfo" name="btnSarchVissuePoInfo" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
				<th><spring:message code="LAB.M09.LAB00105"/><!-- 제품 --></th>
				<td colspan="">
					<div class="inp_date w280">
						<input type="hidden" id="itemId_confirmCancelOrderPopup" name="itemId" />
						<input type="text" id="itemNm_confirmCancelOrderPopup" name="itemNm" class="w250" disabled="disabled" value="${vissueVO.itemId}" />
						<a href="#" id="btnSearchVoucher_confirmCancelOrderPopup" onclick="searchVoucher_confirmCancelOrderPopup()" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --></th>
				<td colspan="">
					${vissueVO.mncoNm}
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M06.LAB00030"/><!-- 발주목 --></h4>
		</div>
	</div>
	
	<div class="layer_box">
		<table id="vissuePoInfoTable" class="w100p"></table>
		<div id="vissuePoInfoPager"></div>
	</div>

</div>
<!--// center -->
<div class="btn_box">
	<c:if test="${popupType == '1'}">
      	<a class="grey-btn" id="btnConfirmOrder_confirmCancelOrderPopup" href="#" ><spring:message code="LAB.M06.LAB00043" /><!-- 발주확정 --></a>
	</c:if>
	<c:if test="${popupType == '2'}">
		<a class="grey-btn" id="btnCancelOrder_confirmCancelOrderPopup" href="#" ><spring:message code="LAB.M06.LAB00041" /><!-- 발주취소 --></a>
	</c:if>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog_confirmCancelOrderPopup" class="Layer_wrap" style="display: none;" >
</div>
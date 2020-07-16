<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {

		//선택버튼
		$("#btnSelectItem").click(function() {
			selectItem();
		});

		initGrid();
	});
	
	function initGrid() {

		var param = {
			soId : '${soId}'
		};
		
		$("#voucherItemSearchTable").jqGrid({
	
		   	url:'/distributor/logistics/voucherMng/voucherGenerate/getVoucherItemListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[

				 { label : '<spring:message code="LAB.M07.LAB00003" />', name : 'soNm', width : 100 }
				 , { label : '<spring:message code="LAB.M07.LAB00156" />', name : 'prodCd', width : 100 }
				 , { label : '<spring:message code="LAB.M07.LAB00130" />', name : 'prodNm', width : 100 }
				 , { label : '<spring:message code="LAB.M01.LAB00114" />', name : 'prodAmt', width : 100 }
				 , { label : '<spring:message code="LAB.M11.LAB00015" />', name : 'qosAmt', width : 100 }
				 , { label : '<spring:message code="LAB.M08.LAB00107" />', name : 'effPeriod', width : 100 }
				 , { label : '<spring:message code="LAB.M15.LAB00069" />', name : 'pcrfYn', width : 100 }

		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#voucherItemSearchPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectItem();
		    }, 
		    loadComplete: function(){
		    	$("#voucherItemSearchTable").setGridWidth(680,false);
		    },
	    	sortable: { update: function(permutation) {
	    		$("#voucherItemSearchTable").setGridWidth(680,false);
	    		}
	    	}
		});
	}

	function selectItem() {
		var index  = $("#voucherItemSearchTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}

		var data = $("#voucherItemSearchTable").getRowData(index)

		var returnId = "${returnId}";
		var returnNm = "${returnNm}";
		var returnAmt = "${returnAmt}";

		if (returnId != null || returnId != '') {
			$('#' + returnId).val(data.prodCd);
		}

		if (returnNm != null || returnNm != '') {
			$('#' + returnNm).val(data.prodNm);			
		}

		if (returnAmt != null || returnAmt != '') {
			$('#' + returnAmt).val(data.prodAmt);	
		}

		$("#btnClose").trigger('click');
	}
	
</script>


<div class="layer_top">
     <div class="title"><spring:message code="LAB.M06.LAB00013"/><!-- 물류센터 검색 --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">

	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td colspan="3">
					${soNm}
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M05.LAB00042"/><!-- 제품목록 --></h4>
		</div>
	</div>
	
	<div class="layer_box">
		<table id="voucherItemSearchTable" class="w100p"></table>
		<div id="voucherItemSearchPager"></div>
	</div>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSelectItem" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
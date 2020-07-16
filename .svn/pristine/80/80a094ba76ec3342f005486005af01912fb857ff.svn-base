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
			soId : '${chargeMngVO.soId}'
			, ctrtId : '${chargeMngVO.ctrtId}'
			, userId : '${chargeMngVO.userId}'
			, prodCd : '${chargeMngVO.prodCd}'
			, discYn : '${chargeMngVO.discYn}'
			, rchrgTermDt : '${chargeMngVO.rchrgTermDt}'
			, pcrfId : '${chargeMngVO.pcrfId}'
		};

		var displayColumnCount = 8;
		var gridWidth = 780;
		
		$("#chrgProdTable").jqGrid({
	
		   	url:'/distributor/logistics/voucherMng/chargeMng/chrgProdListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[

		   		{ label : "effPeriod", name : "effPeriod", width : 0, hidden : true }
		   		, { label : "qosAmt", name : "qosAmt", width : 0, hidden : true }
				, { label : 'soId', name : 'soId', width : 0, hidden : true }
				, { label : 'dhType', name : 'dhType', width : 0, hidden : true }
				, { label : 'pcrfId', name : 'pcrfId', width : 0, hidden : true }
				, { label : 'liteYn', name : 'liteYn', width : 0, hidden : true }
				, { label : 'pdvcYn', name : 'pdvcYn', width : 0, hidden : true }
				, { label : 'discYn', name : 'discYn', width : 0, hidden : true }
				, { label : 'holiYn', name : 'holiYn', width : 0, hidden : true }
				, { label : 'rgProdTp', name : 'rgProdTp', width : 0, hidden : true }
				, { label : 'dedtAmt', name : 'dedtAmt', width : 0, hidden : true }
				, { label : 'dnType', name : 'dnType', width : 0, hidden : true }

				, { label : '<spring:message code="LAB.M07.LAB00003"/>', name : 'soNm', width : gridWidth / displayColumnCount }
				, { label : '<spring:message code="LAB.M07.LAB00156"/>', name : 'prodCd', width : gridWidth / displayColumnCount }
				, { label : '<spring:message code="LAB.M07.LAB00130"/>', name : 'prodNm', width : gridWidth / displayColumnCount }
				, { label : '<spring:message code="LAB.M01.LAB00114"/>', name : 'prodAmt', width : gridWidth / displayColumnCount }
				, { label : '<spring:message code="LAB.M11.LAB00015"/>', name : 'qosAmt', width : gridWidth / displayColumnCount }
				, { label : '<spring:message code="LAB.M08.LAB00107"/>', name : 'effPeriod', width : gridWidth / displayColumnCount }
				, { label : '<spring:message code="LAB.M15.LAB00069"/>', name : 'pcrfYn', width : gridWidth / displayColumnCount }
				, { label : '<spring:message code="LAB.M15.LAB00100"/>', name : 'rcmdYn', width : gridWidth / displayColumnCount }

		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#chrgProdPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectItem();
		    }, 
		    loadComplete: function(){
		    	$("#chrgProdTable").setGridWidth(gridWidth,false);
		    },
	    	sortable: { update: function(permutation) {
	    		$("#chrgProdTable").setGridWidth(gridWidth,false);
	    		}
	    	}
		});
	}

	function selectItem() {
		var index  = $("#chrgProdTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}

		var data = $("#chrgProdTable").getRowData(index)

		var popupType = '${popupType}';

		if (popupType == 'm') {
			setProdData(data);
			$("#btnColse1SeP").trigger('click');
		} else if (popupType = 'o') {
			window.opener.setProdData(data);
			window.open("about:blank", "_self").close();
		}
	}
	
</script>


<div class="layer_top">
     <div class="title"><spring:message code="LAB.M07.LAB00142"/><!-- 상품선택 --></div>
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
					${chargeMngVO.soNm}
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00131"/><!-- 상품목록 --></h4>
		</div>
	</div>
	
	<div class="layer_box">
		<table id="chrgProdTable" class="w100p"></table>
		<div id="chrgProdPager"></div>
	</div>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSelectItem" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
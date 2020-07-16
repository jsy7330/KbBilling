<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {

		initGrid();
		
	});
	
	
	function initGrid() {

		var soId = '${chargeMngVO.soId}';
		var custId = '${chargeMngVO.custId}';

		var param = {
			soId : soId
			, custId : custId
		};
		
		$("#voucherSearchTable").jqGrid({
	
		   	url:'/distributor/logistics/voucherMng/chargeMng/chrgCtrtListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[
		   		
				{ label : '<spring:message code="LAB.M07.LAB00003"/>', name : 'soNm', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00032"/>', name : 'ctrtId', width : 100 }
				, { label : '<spring:message code="LAB.M07.LAB00186"/>', name : 'svcTelNo', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00037"/>', name : 'ctrtStatNm', width : 100 }

		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#voucherSearchPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectItemSep();
		    }, 
		    loadComplete: function(){
		    	var popType = "${productMngVO.popType}";	//팝업타입
				
				if(popType == "o"){
					$("#voucherSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#voucherSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
		    },
	    	sortable: { update: function(permutation) {
	    		$("#voucherSearchTable").setGridWidth(780,false);
	    		}
	    	}
		});
		
		$("#voucherSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		 
	}
	
	function searchSep(){
		
		$("#voucherSearchTable").clearGridData();  // 이전 데이터 삭제

		var soId = $('#soIdSeP').val();
		var itemId = $('#itemIdSeP').val();

		var param = {
			soId : soId
			, itemId : itemId
		};
		
        $("#voucherSearchTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function selectItemSep(){
		
		var index  = $("#voucherSearchTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}

		var data = $("#voucherSearchTable").getRowData(index);

	}
	
</script>

<div class="layer_top">
     <div class="title"><spring:message code="LAB.M03.LAB00048"/><!-- 대상계약정보 --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M03.LAB00048"/><!-- 바우처발행선택 --></h4>
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
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td>${chargeMngVO.soNm}</td>
				<th><spring:message code="LAB.M01.LAB00046"/><!-- 고객ID --></th>
				<td>${chargeMngVO.custId}</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00050"/><!-- 고객명 --></th>
				<td colspan="3">${chargeMngVO.custNm}</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M03.LAB00046"/><!-- 대상계약리스트 --></h4>
		</div>
	</div>
	
	<div class="layer_box">
		<table id="voucherSearchTable" class="w100p"></table>
		<div id="voucherSearchPager"></div>
	</div>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSelectItem" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
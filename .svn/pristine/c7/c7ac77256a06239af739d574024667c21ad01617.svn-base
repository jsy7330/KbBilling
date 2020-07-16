<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		initGridP1();
		
		initGridP2();
		//조직검색 팝업
		$("#btnOrgPopP").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=orgIdP&returnId2=orgNmP";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
	});
	
	
	function initGridP1() {
		
		var param = new Object();
		param.soId = "${session_user.soId}";
		
		$("#orderDistributorHistTable").jqGrid({
			
		   	url:'ordList2.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'ordOrgId' , name: 'ordOrgId', hidden:true,width : 0},
				{ label: 'ordOrgNm' , name: 'ordOrgNm', hidden:true,width : 0},
				{ label: 'lgstCentId' , name: 'lgstCentId', hidden:true,width : 0},
				{ label: 'ordMngOrgId' , name: 'ordMngOrgId', hidden:true,width : 0},
				{ label: 'ordMngInchrgId' , name: 'ordMngInchrgId', hidden:true,width : 0},
				{ label: 'ordPrgrStatCd' , name: 'ordPrgrStatCd', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'itemNm' , name: 'itemNm', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'itemTpNm' , name: 'itemTpNm', hidden:true,width : 0},
				{ label: 'itemKndCd' , name: 'itemKndCd', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},
				{ label: 'eqtUseNm' , name: 'eqtUseNm', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'colorNm' , name: 'colorNm', hidden:true,width : 0},
				{ label: 'ordQty' , name: 'ordQty', hidden:true,width : 0},
				{ label: 'ordUtPrc' , name: 'ordUtPrc', hidden:true,width : 0},
				{ label: 'ordAmt' , name: 'ordAmt', hidden:true,width : 0},
				{ label: 'ordAddTx' , name: 'ordAddTx', hidden:true,width : 0},
				{ label: 'ordTotAmt' , name: 'ordTotAmt', hidden:true,width : 0},
				{ label: 'ordConfQty' , name: 'ordConfQty', hidden:true,width : 0},
				{ label: 'statProcId' , name: 'statProcId', hidden:true,width : 0},
				{ label: 'statProcDttm' , name: 'statProcDttm', hidden:true,width : 0},
				{ label: 'dlvZipCd' , name: 'dlvZipCd', hidden:true,width : 0},
				{ label: 'dlvBssAddr' , name: 'dlvBssAddr', hidden:true,width : 0},
				{ label: 'dlvDtlAddr' , name: 'dlvDtlAddr', hidden:true,width : 0},
				{ label: 'dlvAddr' , name: 'dlvAddr', hidden:true,width : 0},
				{ label: 'ordNote' , name: 'ordNote', hidden:true,width : 0},
				{ label: 'asgnOrgId' , name: 'asgnOrgId', hidden:true,width : 0},
				{ label: 'asgnQty' , name: 'asgnQty', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M14.LAB00017" />', name: 'asgnNo', width : 100},				//할당번호
				{ label: '<spring:message code="LAB.M14.LAB00021" />', name: 'asgnOrgNm', width : 100},				//할당조직
				{ label: '<spring:message code="LAB.M09.LAB00176" />', name: 'ordNo', width : 100},				//주문번호
				{ label: '<spring:message code="LAB.M08.LAB00100" />', name: 'lgstCentNm', width : 100},				//유통점주문조직
				{ label: '<spring:message code="LAB.M09.LAB00181" />', name: 'regDate', formatter: dateTypeFormatterYYYYMMDD, align:'center', width : 100},				//주문일자
				{ label: '<spring:message code="LAB.M09.LAB00174" />', name: 'regrId', width : 100},				//주문등록자
				{ label: '<spring:message code="LAB.M09.LAB00184" />', name: 'ordPrgrStatNm', width : 100},				//주문진행상태
				{ label: '<spring:message code="LAB.M09.LAB00171" />', name: 'ordMngOrgNm', width : 100},				//주문관리조직
	   			
			    
			    
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#orderDistributorHistPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 140,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
				searchDataP2(rowId)
	        },
	        loadComplete: function(obj){
	        	$("#orderDistributorHistTable").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    sortable: { update: function(permutation) {
		    	$("#orderDistributorHistTable").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#orderDistributorHistTable").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}
	
	//그리드 재검색 
	function searchDataP1(){
		$("#orderDistributorHistTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = "${session_user.soId}";
		
        $("#orderDistributorHistTable").setGridParam({ postData: param }).trigger("reloadGrid");
        
        $("#orderDistributorHistTable").clearGridData();  // 이전 데이터 삭제
        
	}
	
	
	function initGridP2() {
		
		var param = new Object();
		param.soId = "99999";
		
		$("#orderDistributorHistTable2").jqGrid({
			
		   	url:'ordList2.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [
				{ label: '<spring:message code="LAB.M03.LAB00016" />', name: 'eqtUseNm', width : 100},	//단말기용도
				{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},	//제품유형
				{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},	//제품명
				{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', width : 100},	//색상
				{ label: '<spring:message code="LAB.M14.LAB00019" />', name: 'asgnQty', formatter: 'integer', align:'right',width : 100},	//할당수량
				{ label: '<spring:message code="LAB.M09.LAB00179" />', name: 'ordQty', formatter: 'integer', align:'right',width : 100},	//주문수량
				{ label: '<spring:message code="LAB.M09.LAB00187" />', name: 'ordConfQty', formatter: 'integer', align:'right',width : 100},	//주문확정수량
				{ label: '<spring:message code="LAB.M09.LAB00172" />', name: 'ordUtPrc', formatter: 'integer', align:'right',width : 100},	//주문단가
				{ label: '<spring:message code="LAB.M09.LAB00170" />', name: 'ordAmt', formatter: 'integer', align:'right',width : 100},	//주문공급가액
				{ label: '<spring:message code="LAB.M09.LAB00177" />', name: 'ordAddTx', formatter: 'integer', align:'right',width : 100},	//주문부가세
				{ label: '<spring:message code="LAB.M09.LAB00185" />', name: 'ordTotAmt', formatter: 'integer', align:'right',width : 100},	//주문총금액
				{ label: '<spring:message code="LAB.M06.LAB00049" />', name: 'dlvAddr', width : 100},	//배송지주소
				{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'ordNote', width : 100},	//주문비고
	   			
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	//rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	//pager: '#orderDistributorHistPager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 80,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        },
	        loadComplete: function(obj){
	        	$("#orderDistributorHistTable2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#orderDistributorHistTable2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#orderDistributorHistTable2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}
	
	//그리드 재검색 
	function searchDataP2(rowId){
		
		//var rowId  = $("#orderDistributorHistTable").jqGrid("getGridParam", "selrow" );
		var data = $("#orderDistributorHistTable").getRowData(rowId);
		
		$("#orderDistributorHistTable2").clearGridData();  // 이전 데이터 삭제
		
		var data2 = {
				'eqtUseNm' : data.eqtUseNm
				, 'itemTpNm' : data.itemTpNm
				, 'itemNm' : data.itemNm
				, 'colorNm' : data.colorNm
				, 'asgnQty' : data.asgnQty
				, 'ordQty' : data.ordQty
				, 'ordConfQty' : data.ordConfQty
				, 'ordUtPrc' : data.ordUtPrc
				, 'ordAmt' : data.ordAmt
				, 'ordAddTx' : data.ordAddTx
				, 'ordTotAmt' : data.ordTotAmt
				, 'dlvAddr' : data.dlvAddr
				, 'ordNote' : data.ordNote
		};
        
		console.log(data2);
		$("#orderDistributorHistTable2").jqGrid('addRowData', '1', data2);
		
        
	}
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00180"/><!-- 주문이력 --></div>
	<a href="#" id="btnColse1SeP" class="close">Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00180"/><!-- 주문이력 --></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="searchSeP" name="searchSeP" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
				<th><spring:message code="LAB.M09.LAB00171"/><!-- 주문관리조직 --></th>
				<td>
					<input type="text" id="searchOrderMngOrgNmP" name="searchOrderMngOrgNmP" class="w300" disabled="disabled" />
					<input type="hidden" id="searchOrderMngOrgIdP" name="searchOrderMngOrgIdP" />
				</td>
				<th><spring:message code="LAB.M08.LAB00100"/><!-- 유통점주문조직 --></th>
				<td>
					<div class="inp_date w290">
						<input type="text" id="orgNmP" name="orgNmP" class="w260" disabled="disabled" />
						<input type="hidden" id="orgIdP" name="orgIdP" />
                        <a href="#" id="btnOrgPopP" name="btnOrgPopP" class="search">search</a>
					</div>
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00175"/><!-- 주문목록 --></h4>
		</div>
	</div>
	
	<div class="layer_box" id="typeP1" name="typeP1" >
		<table id="orderDistributorHistTable" class="w100p"></table>
		<div id="orderDistributorHistPager"></div>
	</div>
<%-- 
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00175"/><!-- 단가변경이력 --></h4>
		</div>
	</div>
	 --%>
	 
	<div class="layer_box" id="typeP2" name="typeP2">
		<table id="orderDistributorHistTable2" class="w100p"></table>
		<div id="orderDistributorHistPager2"></div>
	</div>

	
</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>	
	
	
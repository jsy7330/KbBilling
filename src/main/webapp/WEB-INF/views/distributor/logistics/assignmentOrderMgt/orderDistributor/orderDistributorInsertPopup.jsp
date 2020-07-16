<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	

	$(document).ready(function() {
		
		setDataInP();
		
		initGridInP();
		
		
		$("#btnApplyInP").click(function() {
			changeValueInP();            
		});
		
		$("#btnInsertInP").click(function() {
			insertDataInP();            
		});
		
	});


	function setDataInP(){
		
		var rowId  = $("#orderDistributorTable").jqGrid("getGridParam", "selrow" );
		var data = $("#orderDistributorTable").getRowData(rowId);

		$("#lgstCentNmInP").val(data.lgstCentNm);
		$("#soIdInP").val(data.soId);
		$("#asgnNoInP").val(data.asgnNo);
		$("#asgnOrgIdInP").val(data.asgnOrgId);
		$("#itemTpCdInP").val(data.itemTpCd);
		$("#itemIdInP").val(data.itemId);
		$("#eqtUseCdInP").val(data.eqtUseCd);
		$("#eqtUseNmInP").val(data.eqtUseNm);
		$("#clorCdInP").val(data.clorCd);
		$("#asgnMngOrgIdInP").val(data.asgnMngOrgId);
		$("#asgnMngInchrgIdInP").val(data.asgnMngInchrgId);
		$("#lgstCentIdInP").val(data.lgstCentId);
		$("#asgnOrgNmInP").val(data.asgnOrgNm);
		$("#itemTpNmInP").val(data.itemTpNm);
		$("#asgnMngInchrgNmInP").val(data.asgnMngInchrgNm);
		$("#itemNmInP").val(data.itemNm);
		$("#colorNmInP").val(data.colorNm);
		$("#asgnQtyInP").val(data.stckOwnQty);
		$("#noasgnQtyInP").val(data.noOrdQty0);
		$("#noteInP").val(data.note);
		
		$("#ordMngOrgIdInP").val(data.ordMngOrgId);
		$("#ordMngInchrgIdInP").val(data.ordMngInchrgId);
	
	}
	
	
	function initGridInP() {
		
		var param = new Object();
		param.lgstCentId = $("#lgstCentIdInP").val();
		param.ordMngOrgId = $("#ordMngOrgIdInP").val();
		param.ordMngInchrgId = $("#ordMngInchrgIdInP").val();
		param.soId = $("#searchSoId").val();
		param.itemId = $("#itemIdInP").val();
		param.itemNm = $("#itemNmInP").val();
		param.itemTpCd = $("#itemTpCdInP").val();
		param.itemTpNm = $("#itemTpNmInP").val();
		param.eqtUseCd = $("#eqtUseCdInP").val();
		param.eqtUseNm = $("#eqtUseNmInP").val();
		param.clorCd = $("#clorCdInP").val();
		param.colorNm = $("#colorNmInP").val();
		param.asgnNo = $("#asgnNoInP").val();
		param.asgnOrgId = $("#asgnOrgIdInP").val();
		param.sysDate = $.datepicker.formatDate('yymmdd', new Date());
		param.asgnOrgId = $("#asgnOrgIdInP").val();
		
		console.log(param);
		
		$("#orderDistributorTableInP").jqGrid({
			
		   	url:'ordPreList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'ordNo' , name: 'ordNo', hidden:true,width : 0},
				{ label: 'ordOrgId' , name: 'ordOrgId', hidden:true,width : 0},
				{ label: 'lgstCentId' , name: 'lgstCentId', hidden:true,width : 0},
				{ label: 'ordMngOrgId' , name: 'ordMngOrgId', hidden:true,width : 0},
				{ label: 'ordMngInchrgId' , name: 'ordMngInchrgId', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'oldOrdQty' , name: 'oldOrdQty', hidden:true,width : 0},
				{ label: 'asgnOrgId' , name: 'asgnOrgId', hidden:true,width : 0},
				{ label: 'asgnNo' , name: 'asgnNo', hidden:true,width : 0},
				{ label: 'ordPrgrStatCd' , name: 'ordPrgrStatCd', hidden:true,width : 0},
	
	   		 	{ label: '<spring:message code="LAB.M09.LAB00183" />', name: 'ordOrgNm', width : 100},	//주문조직
	   		 	{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},	//제품유형
	   		 	{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},	//제품명
	   		 	{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', width : 100},	//색상
	   		 	/* { label: '<spring:message code="LAB.M09.LAB00179" />', name: 'ordConfQty', formatter: 'integer', align:'right', width : 100, editable:true,
	   		 	editoptions : {dataEvents:[{type:'keypress',fn:function(e){console.log(e); changeValue(); }}]	}   		 	
	   		 	},		//주문수량 */
	   		 	{ label: '<spring:message code="LAB.M09.LAB00179" />', name: 'ordQty', formatter: 'integer', align:'right', width : 100, editable:true},		//주문수량
		   		 	
		   		 	
	   		 	{ label: '<spring:message code="LAB.M09.LAB00172" />', name: 'ordUtPrc', formatter: 'integer', align:'right', width : 100},		//주문단가
	   		 	{ label: '<spring:message code="LAB.M09.LAB00170" />', name: 'ordAmt', formatter: 'integer', align:'right', width : 100},		//주문공급가액
	   		 	{ label: '<spring:message code="LAB.M09.LAB00177" />', name: 'ordAddTx', formatter: 'integer', align:'right', width : 100},	//주문부가세
	   			{ label: '<spring:message code="LAB.M09.LAB00185" />', name: 'ordTotAmt', formatter: 'integer', align:'right', width : 100},	//주문총금액
	   			{ label: '<spring:message code="LAB.M09.LAB00181" />', name: 'sysDate', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},	//주문일자
	   			/* { label: '<spring:message code="LAB.M07.LAB00110" />', name: 'mncoDt', align:'center', width : 100},		//상태 */
	   			{ label: '<spring:message code="LAB.M08.LAB00087" />', name: 'dlvZipCd', align:'center', width : 100, editable:true},		//우편번호
	   			{ label: '<spring:message code="LAB.M06.LAB00049" />', name: 'dlvBssAddr', align:'center', width : 100, editable:true},	//배송지주소
	   			{ label: '<spring:message code="LAB.M07.LAB00102" />', name: 'dlvDtlAddr', align:'center', width : 100, editable:true},	//상세주소
	   			{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'ordNote', align:'center', width : 100, editable:true},		//비고
	   			{ label: '<spring:message code="LAB.M08.LAB00165" />', name: 'ioChk', align:'center', width : 100},	//입고처리구분
	   			
	   			
	   				   			
	   			
	   			
	   		],

		   	rowNum:99999999,					//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 250,				//화면 상태에 따라 크기 조절 할 것
			//multiselect : true,
			cellEdit: true,                         // 셀 수정 기능을 사용하려면 true!
			cellsubmit: 'clientArray',
			onCellSelect : function(id, cellidx, cellvalue) { // use this event to capture edited cellID
	            //selectedCellId = cellidx; // save the cellId to a variable
	            //changeValueInP();
	            
	        },
	        loadComplete: function(obj){
	        	$("#orderDistributorTableInP").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#orderDistributorTableInP").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#orderDistributorTableInP").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function searchDataInP(){
		
		$("#orderDistributorTableInP").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
        $("#orderDistributorTableInP").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	function changeValueInP(){
		
		var ids = jQuery("#orderDistributorTableInP").jqGrid('getDataIDs');
		$('#orderDistributorTableInP').editCell(ids.length, 4, false);
		
		for(var i=0;i < ids.length;i++){
			var cl = ids[i];
			
			$('#orderDistributorTableInP').jqGrid('saveCell', cl, 4);
			var data = $("#orderDistributorTableInP").getRowData(cl);
			
			var ordAmt = data.ordQty * data.ordUtPrc;
			var ordAddTx = ordAmt * 0;
			var ordTotAmt = ordAmt+ordAddTx;
			
			$("#orderDistributorTableInP").jqGrid('setCell', cl, 'ordAmt', ordAmt);
			$("#orderDistributorTableInP").jqGrid('setCell', cl, 'ordAddTx', ordAddTx);
			$("#orderDistributorTableInP").jqGrid('setCell', cl, 'ordTotAmt', ordTotAmt);
			
		}
		 
		
		var data2 = jQuery("#orderDistributorTableInP").getRowData();
		
		console.log(data2);
	}
	
	
	function insertDataInP(){
		
		changeValueInP();
		
		var param = new Array();
		var ids = jQuery("#orderDistributorTableInP").jqGrid('getDataIDs');
		for(var i=0;i < ids.length;i++){
			var data = $("#orderDistributorTableInP").getRowData(ids[i]);
			data.soId = "${session_user.soId}";
			data.ordConfQty = "0";
			param.push(data);
		}
			
		param = JSON.stringify(param)
		
		$.ajax({
			url : 'insertOrd.json',
			type : 'POST',
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				if(data.result != "0"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					
					searchData();
					searchData2();
					
					$("#btnClose").trigger('click');
					
				}else{
					alert('<spring:message code="MSG.M09.MSG00004" />');	//저장 내용이 없습니다.
				}
			},
		    error: function(e){
		        alert("Failed to save.");
		    },
			complete : function() {
			}
		});
		
		
	}
	
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M08.LAB00099"/><!-- 유통점주문등록 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->


<!-- center -->
<div class="layer_box">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M14.LAB00015"/><!-- 할당내역 --></h4>
		</div>
	</div>
	
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M05.LAB00042"/><!-- 물류센터 --></th>
				<td>
					<input type="text" id="lgstCentNmInP" name="lgstCentNmInP" class="w200" disabled="disalbed" />
					
					<input type="hidden" id="soIdInP" name="soIdInP" />
					<input type="hidden" id="asgnNoInP" name="asgnNoInP" />
					<input type="hidden" id="asgnOrgIdInP" name="asgnOrgIdInP" />
					<input type="hidden" id="itemTpCdInP" name="itemTpCdInP" />
					<input type="hidden" id="itemIdInP" name="itemIdInP" />
					<input type="hidden" id="eqtUseCdInP" name="eqtUseCdInP" />
					<input type="hidden" id="eqtUseNmInP" name="eqtUseNmInP" />
					<input type="hidden" id="clorCdInP" name="clorCdInP" />
					<input type="hidden" id="asgnMngOrgIdInP" name="asgnMngOrgIdInP" />
					<input type="hidden" id="asgnMngInchrgIdInP" name="asgnMngInchrgIdInP" />
					<input type="hidden" id="lgstCentIdInP" name="lgstCentIdInP" />
					<input type="hidden" id="ordMngOrgIdInP" name="ordMngOrgIdInP" />
					<input type="hidden" id="ordMngInchrgIdInP" name="ordMngInchrgIdInP" />
					
				</td>
				<th><spring:message code="LAB.M14.LAB00021"/><!-- 할당조직 --></th>
				<td>
					<input type="text" id="asgnOrgNmInP" name="asgnOrgNmInP" class="w200" disabled="disalbed" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
				<td>
					<input type="text" id="itemTpNmInP" name="itemTpNmInP" class="w200" disabled="disalbed" />
				</td>
				<th><spring:message code="LAB.M14.LAB00014"/><!-- 할당관리담당자 --></th>
				<td>
					<input type="text" id="asgnMngInchrgNmInP" name="asgnMngInchrgNmInP" class="w200" disabled="disalbed" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
				<td>
					<input type="text" id="itemNmInP" name="itemNmInP" class="w200" disabled="disalbed" />
				</td>
				<th><spring:message code="LAB.M07.LAB00162"/><!-- 색상 --></th>
				<td>
					<input type="text" id="colorNmInP" name="colorNmInP" class="w200" disabled="disalbed" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00019"/><!-- 할당수량 --></th>
				<td>
					<input type="text" id="asgnQtyInP" name="asgnQtyInP" class="w200" disabled="disalbed" />
				</td>
				<th><spring:message code="LAB.M05.LAB00051"/><!-- 미주문수량 --></th>
				<td>
					<input type="text" id="noasgnQtyInP" name="noasgnQtyInP" class="w200" disabled="disalbed" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00018"/><!-- 할당비고 --></th>
				<td colspan="3" >
					<input type="text" id="noteInP" name="noteInP" class="w600" disabled="disalbed" />
				</td>
			</tr>
		</thead>
	</table>
	 
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M09.LAB00175"/><!-- 주문목록 --></h4>
		</div>
	</div>

	<div class="layer_box" id="orderDistributorInPGrid" >
		<table id="orderDistributorTableInP" class="w100p"></table>
		<div id="orderDistributorPagerInP"></div>
	</div>
	
	
</div>

<div class="btn_box">
	<a class="grey-btn" href="#" id="btnApplyInP"  ><span class="">적용</span></a>
	<a class="grey-btn" href="#" id="btnInsertInP" ><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<%-- <a class="grey-btn" href="#" id="btn_deleteP1" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a> --%>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
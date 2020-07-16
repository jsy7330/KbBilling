<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	var itemTpCd = "";

	$(document).ready(function() {
		
		$("#btnInsertInP").show();
		
		setDataInP();
		
		if(itemTpCd == "C"){
			$("#logisticsCenterIssueInspGridP1").show();
			
			initGridInP1();
			
		}else if(itemTpCd == "U"){
			$("#logisticsCenterIssueInspGridP2").show();
			
			initGridInP2();
			
		}else if(itemTpCd == "V"){
			$("#logisticsCenterIssueInspGridP3").show();
			
			initGridInP3();
		}
		
		$("#btnSearchInP").click(function() {
			
			if(itemTpCd == "C"){
				searchDataInP1();
			}else if(itemTpCd == "U"){
				searchDataInP2();
			}else if(itemTpCd == "V"){
				searchDataInP3();
			}
			
		});
		
		$("#btnInsertInP").click(function() {
			
			var rowId1  = $("#logisticsCenterIssueInspTable").jqGrid("getGridParam", "selrow" );
			var data1 = $("#logisticsCenterIssueInspTable").getRowData(rowId1);
			var totQty = data1.ordQty;
			
			var datas1 = jQuery("#logisticsCenterIssueInspTable2").getRowData();
			var selQty = 0;
			
			for(var i=0; i<datas1.length; i++){
				selQty = selQty + Number(datas1[i].inoutQty);
			}
			
			var qty = Number($("#qtyInP").val());
			
			if(totQty < selQty + qty){
				var qty1 = totQty - selQty;
				alert('<spring:message code="MSG.M10.MSG00018" />' + qty1);		//출고가능 수량보다 출고 수량이 많습니다. 출고가능수량 :
					return;
			}
			
			var ids;
			
			if(itemTpCd == "C"){
				ids = $("#logisticsCenterIssueInspTableP1").jqGrid("getGridParam", "selarrrow" );
			}else if(itemTpCd == "U"){
				ids = $("#logisticsCenterIssueInspTableP2").jqGrid("getGridParam", "selarrrow" );
			}else if(itemTpCd == "V"){
				ids = $("#logisticsCenterIssueInspTableP3").jqGrid("getGridParam", "selarrrow" );
			}
			
			
			if(ids.length < 1){
				alert('<spring:message code="MSG.M10.MSG00023" />');		//출고대상제품을 선택해 주세요.
				return;
			}
			
			if(ids.length != qty ){
				alert('<spring:message code="MSG.M10.MSG00022" />');		//출고대상제품 수량이 일치 하지 않습니다.
				return;
			}
			
			var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
			var params = new Array();
			for(var i=0; i<ids.length; i++){
				
				var param;
				if(itemTpCd == "C"){
					param = $("#logisticsCenterIssueInspTableP1").getRowData(ids[i]);
				}else if(itemTpCd == "U"){
					param = $("#logisticsCenterIssueInspTableP2").getRowData(ids[i]);
					param.eqtUseCd = "";	//단말기만
					param.eqtRsrcClCd = "";	//단말기만
					param.clorCd = "";		//단말기만
				}else if(itemTpCd == "V"){
					param = $("#logisticsCenterIssueInspTableP3").getRowData(ids[i]);
					param.eqtUseCd = "";	//단말기만
					param.eqtRsrcClCd = "";	//단말기만
					param.clorCd = "";		//단말기만
				}
				
				param.inoutClCd = '2';
				param.inoutDt = yyyymmdd;
				param.inoutPrgrStatCd = '40';
				param.ownOrgId = $("#ordOrgIdInP").val();
				param.inoutOrgId = $("#lgstCentIdInP").val();
				param.relInoutId = '';
				param.dlvId = '';
				param.ordUtPrc = $("#ordUtPrcInP").val();
				param.inoutResnCd = '40';
				param.itemTpCd = $("#itemTpCdInP").val();
				
				param.inoutQty = qty;
				param.inoutUtPrc = $("#ordUtPrcInP").val();
				param.note = $("#noteInP").val();
				param.poNo = "";
				param.actncNo = "";
				param.ordNo = $("#ordNoInP").val();
				param.apprYn = "Y";
				
				params.push(param);
			}
			
			console.log(params);
			
			insertInoutList(params);
			
			
		});
		
		
	});

	function setDataInP(){
		var rowId  = $("#logisticsCenterIssueInspTable").jqGrid("getGridParam", "selrow" );
		var data = $("#logisticsCenterIssueInspTable").getRowData(rowId);
		
		$("#ordOrgNmInP").val(data.ordOrgNm);
		$("#eqtUseNmInP").val(data.eqtUseNm);
		$("#itemTpNmInP").val(data.itemTpNm);
		$("#itemNmInP").val(data.itemNm);
		$("#eqtUseNm2InP").val(data.itemTpNm);
		
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#outDtInP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		
		itemTpCd = data.itemTpCd;
		$("#soIdInP").val(data.soId);
		$("#itemIdInP").val(data.itemId);
		$("#ordUtPrcInP").val(data.ordUtPrc);
		$("#ordOrgIdInP").val(data.ordOrgId);
		$("#lgstCentIdInP").val(data.lgstCentId);
		$("#itemTpCdInP").val(data.itemTpCd);
		$("#eqtUseCdInP").val(data.eqtUseCd);
		$("#ordNoInP").val(data.ordNo);
		
	}
	
	function initGridInP1() {
		
		var param = new Object();
		//param.soId = $("#soIdInP").val();
		param.soId = "99999";
		param.itemId = $("#itemIdInP").val();
		param.eqtUseCd = "10";
		
		$("#logisticsCenterIssueInspTableP1").jqGrid({
			
		   	url:'lgstCntrOutEqtList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'colorNm' , name: 'colorNm', hidden:true,width : 0},
				{ label: 'eqtRsrcClCd' , name: 'eqtRsrcClCd', hidden:true,width : 0},
				{ label: 'eqtRsrcClNm' , name: 'eqtRsrcClNm', hidden:true,width : 0},
				{ label: 'eqtStatCd' , name: 'eqtStatCd', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},
				{ label: 'eqtStatNm' , name: 'eqtStatNm', hidden:true,width : 0},
	
	   		 	{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},	//제품명
	   		 	{ label: '<spring:message code="LAB.M08.LAB00139" />', name: 'eqtSeq', width : 100},	//일련번호
	   		 	{ label: '<spring:message code="LAB.M09.LAB00113" />', name: 'eqtStatNm', width : 100},		//제품상태
	   		 	{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', width : 100},		//바코드
	   		 	
		   		
		   		
		   		
		   		
	   		],

		   	rowNum:99999999,			//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 250,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#logisticsCenterIssueInspTableP1").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#logisticsCenterIssueInspTableP1").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#logisticsCenterIssueInspTableP1").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function searchDataInP1(){
		
		$("#logisticsCenterIssueInspTableP1").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#soIdInP").val();
		param.itemId = $("#itemIdInP").val();
		param.eqtUseCd = "10";
		param.searchSeq = $("#qtyInP").val();
		
		$("#logisticsCenterIssueInspTableP1").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	function initGridInP2() {
		
		var param = new Object();
		//param.soId = $("#soIdInP").val();
		param.soId = "99999";
		param.itemId = $("#itemIdInP").val();
		param.eqtUseCd = "10";
		
		$("#logisticsCenterIssueInspTableP2").jqGrid({
			
		   	url:'lgstCntrOutUsimList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'eqtRsrcClCd' , name: 'eqtRsrcClCd', hidden:true,width : 0},
				{ label: 'eqtRsrcClNm' , name: 'eqtRsrcClNm', hidden:true,width : 0},
				{ label: 'eqtStatCd' , name: 'eqtStatCd', hidden:true,width : 0},
				{ label: 'eqtStatNm' , name: 'eqtStatNm', hidden:true,width : 0},
				{ label: 'idlStatCd' , name: 'idlStatCd', hidden:true,width : 0},
	
	   		 	{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},	//제품명
	   		 	{ label: '<spring:message code="LAB.M08.LAB00139" />', name: 'eqtSeq', width : 100},	//일련번호
	   		 	{ label: '<spring:message code="LAB.M09.LAB00113" />', name: 'eqtStatNm', width : 100},		//제품상태
	   		 	{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', width : 100},		//바코드
	   		 	{ label: '<spring:message code="LAB.M15.LAB00047" />', name: 'imsiNo', width : 100},		//IMSI_NO
	   		 	
		   		
		   		
	   		],

		   	rowNum:99999999,			//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 250,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#logisticsCenterIssueInspTableP2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    sortable: { update: function(permutation) {
		    	$("#logisticsCenterIssueInspTableP2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#logisticsCenterIssueInspTableP2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function searchDataInP2(){
		
		$("#logisticsCenterIssueInspTableP2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#soIdInP").val();
		param.itemId = $("#itemIdInP").val();
		param.eqtUseCd = "10";
		param.searchSeq = $("#qtyInP").val();
		
		$("#logisticsCenterIssueInspTableP2").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	function initGridInP3() {
		
		var param = new Object();
		//param.soId = $("#soIdInP").val();
		param.soId = "9999";
		param.itemId = $("#itemIdInP").val();
		param.eqtUseCd = "10";
		
		$("#logisticsCenterIssueInspTableP3").jqGrid({
			
		   	url:'lgstCntrOutVeqtList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'eqtRsrcClCd' , name: 'eqtRsrcClCd', hidden:true,width : 0},
				{ label: 'eqtRsrcClNm' , name: 'eqtRsrcClNm', hidden:true,width : 0},
				{ label: 'eqtStatCd' , name: 'eqtStatCd', hidden:true,width : 0},
				{ label: 'eqtStatNm' , name: 'eqtStatNm', hidden:true,width : 0},
				{ label: 'idlStatCd' , name: 'idlStatCd', hidden:true,width : 0},
	
	   		 	{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},	//제품명
	   		 	{ label: '<spring:message code="LAB.M08.LAB00139" />', name: 'eqtSeq', width : 100},	//일련번호
	   		 	{ label: '<spring:message code="LAB.M09.LAB00113" />', name: 'eqtStatNm', width : 100},		//제품상태
	   		 	{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', width : 100},		//바코드
	   		 	{ label: '<spring:message code="LAB.M15.LAB00047" />', name: 'imsiNo', width : 100},		//IMSI_NO
	   		 	
		   		
		   		
	   		],

		   	rowNum:99999999,			//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 250,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#logisticsCenterIssueInspTableP3").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#logisticsCenterIssueInspTableP3").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#logisticsCenterIssueInspTableP3").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function searchDataInP3(){
		
		$("#logisticsCenterIssueInspTableP3").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#soIdInP").val();
		param.itemId = $("#itemIdInP").val();
		param.eqtUseCd = "10";
		param.searchSeq = $("#qtyInP").val();
		
		$("#logisticsCenterIssueInspTableP3").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	function insertInoutList(param){
		
		if(param != false){
			
			param = JSON.stringify(param)
			
			$.ajax({
				url : 'insertInoutList.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData2();
						
						$("#btnClose").trigger('click');
						
					}
				},
			    error: function(e){
			        alert("Failed to save.");
			    },
				complete : function() {
				}
			});
		}
	}
	
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M05.LAB00044"/><!-- 물류센터출고등록 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->


<!-- center -->
<div class="layer_box">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M05.LAB00044"/><!-- 물류센터출고등록 --></h4>
		</div>
		<div class="fr mt10">
			<%-- <a href="#" class="grey-btn" id="btnSearchP2"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> --%>
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
				<th><spring:message code="LAB.M10.LAB00072"/><!-- 출고대상조직 --></th>
				<td>
					<input type="text" id="ordOrgNmInP" name="ordOrgNmInP" class="w200" disabled="disalbed" />
					<input type="hidden" id="soIdInP" name="soIdInP" />
					<input type="hidden" id="itemIdInP" name="itemIdInP" />
					<input type="hidden" id="ordUtPrcInP" name="ordUtPrcInP" />
					<input type="hidden" id="ordOrgIdInP" name="ordOrgIdInP" />
					<input type="hidden" id="lgstCentIdInP" name="lgstCentIdInP" />
					<input type="hidden" id="itemTpCdInP" name="itemTpCdInP" />
					<input type="hidden" id="eqtUseCdInP" name="eqtUseCdInP" />
					<input type="hidden" id="ordNoInP" name="ordNoInP" />
					
				</td>
				<th><spring:message code="LAB.M03.LAB00016"/><!-- 단말기용도 --></th>
				<td>
					<input type="text" id="eqtUseNmInP" name="eqtUseNmInP" class="w200" disabled="disalbed" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형--></th>
				<td>
					<input type="text" id="itemTpNmInP" name="itemTpNmInP" class="w200" disabled="disalbed" />
				</td>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
				<td>
					<input type="text" id="itemNmInP" name="itemNmInP" class="w200" disabled="disalbed" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M10.LAB00078"/><!-- 출고일자 --></th>
				<td>
					<input type="text" id="outDtInP" name="outDtInP" class="w200" disabled="disalbed" />
				</td>
				<th><spring:message code="LAB.M07.LAB00247"/><!-- 수량 --></th>
				<td>
					<input type="text" id="qtyInP" name="qtyInP" class="w200" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --></th>
				<td>
					<input type="text" id="noteInP" name="noteInP" class="w200" />
				</td>
				<th><spring:message code="LAB.M03.LAB00024"/><!-- 단말기자원구분 --></th>
				<td>
					<input type="text" id="eqtUseNm2InP" name="eqtUseNm2InP" class="w200" disabled="disalbed" />
				</td>
			</tr>
		</thead>
	</table>
	 
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M10.LAB00071"/><!-- 출고대상제품 --></h4>
		</div>
		<div class="fr">
			<a href="#" class="grey-btn" id="btnSearchInP"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>

	<div class="layer_box" id="logisticsCenterIssueInspGridP1" style="display:none;" >
		<table id="logisticsCenterIssueInspTableP1" class="w100p"></table>
		<div id="logisticsCenterIssueInspPagerP1"></div>
	</div>
	
	<div class="layer_box" id="logisticsCenterIssueInspGridP2" style="display:none;" >
		<table id="logisticsCenterIssueInspTableP2" class="w100p"></table>
		<div id="logisticsCenterIssueInspPagerP2"></div>
	</div>
	
	<div class="layer_box" id="logisticsCenterIssueInspGridP3" style="display:none;" >
		<table id="logisticsCenterIssueInspTableP3" class="w100p"></table>
		<div id="logisticsCenterIssueInspPagerP3"></div>
	</div>
	
</div>

<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInspection" style="display:none;" ><span class=""><spring:message code="MSG.M01.MSG00009" /></span></a><!-- 검수 -->
	<a class="grey-btn" href="#" id="btnExcel" style="display:none;" ><span class="">Excel Upload</span></a>
	<a class="grey-btn" href="#" id="btnInsertInP" style="display:none;" ><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<%-- <a class="grey-btn" href="#" id="btn_deleteP1" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a> --%>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<!--// center -->
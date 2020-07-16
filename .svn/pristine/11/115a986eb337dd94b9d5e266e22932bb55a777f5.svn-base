<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		console.log("${session_user}");
		
		initGrid();
		
		initGrid2();
		
		initGrid3();
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();            
		});
		
		$("#btnInspSave").click(function() {
			
			var ids = $("#distributorReceiptInspTable2").jqGrid("getGridParam", "selarrrow" );
			
			if(ids < 1){
				alert('<spring:message code="MSG.M08.MSG00062" />');	//입고내역을 선택해 주세요.
				return;
			}
			
			var data = $("#distributorReceiptInspTable2").getRowData(ids[0]);
			/* 
			if(data.inoutPrgrStatCd != '10' && data.inoutPrgrStatCd != '30'){
				alert('입고대기, 입고확정 상태에서만 진행 할 수 있습니다.');	//입고대기, 입고확정 상태에서만 진행 할 수 있습니다.
				return;
			}
			 */
			data.inoutClCd = '1';		//입출고구분코드
			data.inoutDt = $.datepicker.formatDate('yymmdd', new Date());
			
			var params = new Array();
			
			var ids2 = $("#distributorReceiptInspTable3").jqGrid("getGridParam", "selarrrow" );
			var allData = $("#distributorReceiptInspTable3").getRowData();
			
			for(var i=0; i<allData.length; i++){
				
				var data2 = $("#distributorReceiptInspTable3").getRowData(i+1);
				
				data2.apprYn = 'N';
				
				for(var j=0; j<ids2.length; j++){
					if(i == j){
						data2.apprYn = 'Y';
					}
				}
				
				params.push(data2);
			}
			
			data.voList = params;
			console.log(data);
			
			insertInsp(data);
		});
		
		
		//입고승인
		$("#btnInConf").click(function() {
			
			var ids = $("#distributorReceiptInspTable2").jqGrid("getGridParam", "selarrrow" );
			
			if(ids < 1){
				alert('<spring:message code="MSG.M08.MSG00062" />');	//입고내역을 선택해 주세요.
				return;
			}
			
			var data = $("#distributorReceiptInspTable2").getRowData(ids[0]);
			if(data.inoutPrgrStatCd != '40'){
				alert('<spring:message code="MSG.M08.MSG00061" />');	//입고검수 상태에서만 진행 할 수 있습니다.
				return;
			}
			
			data.inoutClCd = '1';		//입출고구분코드
			
			var params = new Array();
			
			var allData = $("#distributorReceiptInspTable3").getRowData();
			
			for(var i=0; i<allData.length; i++){
				
				var data2 = $("#distributorReceiptInspTable3").getRowData(i+1);
				
				if(data2.apprNm != "Y"){
					alert('<spring:message code="MSG.M01.MSG00013" />');		//검수하지 않은 건이 존재할 경우 입고 확정할 수 없습니다.
					return;
				}
				
				params.push(data2);
			}
			
			data.voList = params;
			console.log(data);
			
			insertInConf(data);
			
		});
		
		
		//입고거절
		$("#btnInRefuse").click(function() {
			
			var ids = $("#distributorReceiptInspTable2").jqGrid("getGridParam", "selarrrow" );
			
			if(ids < 1){
				alert('<spring:message code="MSG.M08.MSG00062" />');	//입고내역을 선택해 주세요.
				return;
			}
			
			var data = $("#distributorReceiptInspTable2").getRowData(ids[0]);
			data.inoutClCd = '1';		//입출고구분코드
			data.inoutDt = dateFormatToStringYYYYMMDD(data.inoutDt);
			
			var params = new Array();
			
			var allData = $("#distributorReceiptInspTable3").getRowData();
			
			for(var i=0; i<allData.length; i++){
				
				var data2 = $("#distributorReceiptInspTable3").getRowData(i+1);
				
				params.push(data2);
			}
			
			data.voList = params;
			console.log(data);
			
			insertInRefuse(data);
			
		});
		
		//제조사검색 팝업
		$("#btnSearchMnco").click(function() {
			var url="/system/common/common/manufacturerMng/manufacturerSearchPopup.ajax";
			var param = new Object();
			param.soId = $("#searchSoId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchMncoId";	//리턴받는 Id 의 태그아이디값
			param.returnId2 = "searchMncoNm";	//리턴받는 Nm 의 태그아이디값
			
			$("#popup_dialog").css("width", 500);
			openModalPopup(url, param);
		});
		
		//조직검색 팝업
		$("#searchOrgPopup").click(function() {
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			var param = new Object();
			param.orgId = $("#orgId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchOrgId";	//리턴받는 orgId 의 태그아이디값
			param.returnId2 = "searchOrgNm";	//리턴받는 orgNm 의 태그아이디값
			
			$("#popup_dialog").css("width", 1000);
			openModalPopup(url, param);
		});
		
		//제품검색 팝업
		$("#btnSearchItem").click(function() {
			var url="/system/common/common/productMng/productSearchPopup.ajax";
			var param = new Object();
			param.soId = $("#searchSoId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchItemId";	//리턴받는 Id 의 태그아이디값
			param.returnId2 = "searchItemNm";	//리턴받는 Nm 의 태그아이디값
			
			$("#popup_dialog").css("width", 800);
			openModalPopup(url, param);
		});
		
	});
	
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.ownOrgId = "${session_user.orgId}";
		
		$("#distributorReceiptInspTable").jqGrid({
			
		   	url:'inoutDtlList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'inoutResnCd' , name: 'inoutResnCd', hidden:true,width : 0},
				{ label: 'inoutClCd' , name: 'inoutClCd', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'inoutAmt' , name: 'inoutAmt', hidden:true,width : 0},
				{ label: 'inoutAddTx' , name: 'inoutAddTx', hidden:true,width : 0},
				{ label: 'inoutTotAmt' , name: 'inoutTotAmt', hidden:true,width : 0},
				{ label: 'poNo' , name: 'poNo', hidden:true,width : 0},
				{ label: 'ordNo' , name: 'ordNo', hidden:true,width : 0},
				{ label: 'inoutPrgrStatCd' , name: 'inoutPrgrStatCd', hidden:true,width : 0},
				{ label: 'statProcId' , name: 'statProcId', hidden:true,width : 0},
				{ label: 'statProcDttm' , name: 'statProcDttm', hidden:true,width : 0},
				{ label: 'ownOrgId' , name: 'ownOrgId', hidden:true,width : 0},
				{ label: 'inoutOrgId' , name: 'inoutOrgId', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M10.LAB00065" />', name: 'inoutId', width : 100},	//출고ID
				{ label: '<spring:message code="LAB.M10.LAB00072" />', name: 'inoutOrgNm', width : 100},	//출고조직
				{ label: '<spring:message code="LAB.M10.LAB00078" />', name: 'inoutDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//출고일자
				{ label: '<spring:message code="LAB.M06.LAB00048" />', name: 'dlvDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//배송일자
				{ label: '<spring:message code="LAB.M07.LAB00233" />', name: 'invoNo', width : 100},		//송장번호
				{ label: '<spring:message code="LAB.M10.LAB00074" />', name: 'inoutResnNm', width : 100},		//출고사유
				{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},		//제품유형
				{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
				{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', align:'center', width : 100},		//색상
				{ label: '<spring:message code="LAB.M10.LAB00076" />', name: 'inoutQty', formatter: 'integer', align:'right', width : 100},	//출고수량
				{ label: '<spring:message code="LAB.M10.LAB00069" />', name: 'inoutUtPrc', formatter: 'integer', align:'right', width : 100},	//출고단가
				{ label: '<spring:message code="LAB.M10.LAB00073" />', name: 'note', width : 100},			//출고비고
	   		 	
				
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#distributorReceiptInspPager3',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 130,				//화면 상태에 따라 크기 조절 할 것
			//multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				searchData2(rowId);
				
				
	        },
	        loadComplete: function(obj){
	        	$("#distributorReceiptInspTable").setGridWidth($('#distributorReceiptInspGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#distributorReceiptInspTable").setGridWidth($('#distributorReceiptInspGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#distributorReceiptInspTable").setGridWidth($('#distributorReceiptInspGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#distributorReceiptInspTable").setGridWidth($('#distributorReceiptInspGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData(){
		
		$("#distributorReceiptInspTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		
		param.ownOrgId = "${session_user.orgId}";
		param.soId = $("#searchSoId").val();
		
		param.searchStatDt = dateFormatToStringYYYYMMDD( $("#searchStatDt").val() );
		param.searchEndDt = dateFormatToStringYYYYMMDD( $("#searchEndDt").val() );
		param.mncoId = $("#searchMncoId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		param.itemId = $("#searchItemId").val();
		param.inoutOrgId = $("#searchOrgId").val();
		
        $("#distributorReceiptInspTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	function initGrid2() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.inoutId = "999999999999999";
		param.inoutDt = '99';
		
		$("#distributorReceiptInspTable2").jqGrid({
			
		   	url:'inoutDtlList2.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'inoutResnCd' , name: 'inoutResnCd', hidden:true,width : 0},
				{ label: 'inoutClCd' , name: 'inoutClCd', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},
				{ label: 'eqtRsrcClCd' , name: 'eqtRsrcClCd', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'inoutAmt' , name: 'inoutAmt', hidden:true,width : 0},
				{ label: 'inoutUtPrc' , name: 'inoutUtPrc', hidden:true,width : 0},
				{ label: 'inoutAddTx' , name: 'inoutAddTx', hidden:true,width : 0},
				{ label: 'inoutTotAmt' , name: 'inoutTotAmt', hidden:true,width : 0},
				{ label: 'poNo' , name: 'poNo', hidden:true,width : 0},
				{ label: 'actncNo' , name: 'actncNo', hidden:true,width : 0},
				{ label: 'ordNo' , name: 'ordNo', hidden:true,width : 0},
				{ label: 'inoutPrgrStatCd' , name: 'inoutPrgrStatCd', hidden:true,width : 0},
				{ label: 'statProcId' , name: 'statProcId', hidden:true,width : 0},
				{ label: 'statProcDttm' , name: 'statProcDttm', hidden:true,width : 0},
				{ label: 'dlvId' , name: 'dlvId', hidden:true,width : 0},
				{ label: 'ownOrgId' , name: 'ownOrgId', hidden:true,width : 0},
				{ label: 'inoutOrgId' , name: 'inoutOrgId', hidden:true,width : 0},
				{ label: 'note' , name: 'note', hidden:true,width : 0},
				{ label: 'relInoutId' , name: 'relInoutId', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M08.LAB00148" />', name: 'inoutId', width : 100},	//입고ID
				{ label: '<spring:message code="LAB.M08.LAB00164" />', name: 'inoutPrgrStatNm', width : 100},	//입고진행상태
				{ label: '<spring:message code="LAB.M05.LAB00042" />', name: 'ownOrgNm', width : 100},	//물류센터
				{ label: '<spring:message code="LAB.M08.LAB00163" />', name: 'inoutDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//입고일자
				{ label: '<spring:message code="LAB.M08.LAB00158" />', name: 'inoutResnNm', width : 100},		//입고사유
				{ label: '<spring:message code="LAB.M03.LAB00016" />', name: 'eqtUseNm', width : 100},		//단말기용도
				{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},		//제품유형
				{ label: '<spring:message code="LAB.M07.LAB00247" />', name: 'inoutQty', formatter: 'integer', align:'right', width : 100},	//수량
	   		 	
				
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	//rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	//pager: '#distributorReceiptInspPager3',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 22,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	
	        	searchData3(1);
	        	
	        	$("#distributorReceiptInspTable2").setGridWidth($('#distributorReceiptInspGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#distributorReceiptInspTable2").setGridWidth($('#distributorReceiptInspGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#distributorReceiptInspTable2").setGridWidth($('#distributorReceiptInspGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#distributorReceiptInspTable2").setGridWidth($('#distributorReceiptInspGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData2(rowId){
		
		var data = $("#distributorReceiptInspTable").getRowData(rowId);
		
		$("#distributorReceiptInspTable2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		
		param.soId = data.soId;
		param.inoutId = data.inoutId;
		param.inoutDt = $.datepicker.formatDate('yymmdd', new Date());
		
        $("#distributorReceiptInspTable2").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	function initGrid3() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.inoutId = "999999999999999";
		param.itemId = "999999999999999";
		param.itemTpCd = "C";
		param.statDt = '';
		
		$("#distributorReceiptInspTable3").jqGrid({
			
		   	url:'inoutDtlList3.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'eqtStatCd' , name: 'eqtStatCd', hidden:true,width : 0},
				{ label: 'inoutId' , name: 'inoutId', hidden:true,width : 0},
				{ label: 'apprYn' , name: 'apprYn', hidden:true,width : 0},
				{ label: 'apprYn2' , name: 'apprYn2', hidden:true,width : 0},
				{ label: 'barCd' , name: 'barCd', hidden:true,width : 0},
				{ label: 'lgstProcStatCd' , name: 'lgstProcStatCd', hidden:true,width : 0},
				{ label: 'ownLocCd' , name: 'ownLocCd', hidden:true,width : 0},
				{ label: 'statDt' , name: 'statDt', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'eqtStatCd' , name: 'eqtStatCd', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M01.LAB00027" />', name: 'apprNm', width : 100},		//검수여부
				{ label: '<spring:message code="LAB.M09.LAB00122" />', name: 'eqtSeq', width : 100},		//제품일련번호
				{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
				{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', width : 100},		//색상
				{ label: '<spring:message code="LAB.M09.LAB00113" />', name: 'eqtStatNm', width : 100},	//제품상태
				{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', width : 100},		//바코드
				{ label: '<spring:message code="LAB.M08.LAB00154" />', name: 'inoutUtPrc', formatter: 'integer', align:'right', width : 100},	//입고단가
	   		 	
				
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	//rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	//pager: '#distributorReceiptInspPager3',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 150,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	var data = $("#distributorReceiptInspTable2").getRowData(1);
	        	if(data.inoutId == null || data.inoutId == ''){
	        		
	        		var data = $("#distributorReceiptInspTable3").getRowData();
	        		for(var i=0; i<data.length; i++){
	        			$("#distributorReceiptInspTable3").jqGrid('setCell', i+1, 'apprNm', '미등록');
	        		}
	        		
	        	}
	        	
	        	$("#distributorReceiptInspTable3").setGridWidth($('#distributorReceiptInspGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#distributorReceiptInspTable3").setGridWidth($('#distributorReceiptInspGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#distributorReceiptInspTable3").setGridWidth($('#distributorReceiptInspGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#distributorReceiptInspTable3").setGridWidth($('#distributorReceiptInspGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData3(rowId){
		
		var data = $("#distributorReceiptInspTable2").getRowData(rowId);
		if(data.inoutId == null || data.inoutId == ''){
			
			var rowid1  = $("#distributorReceiptInspTable").jqGrid("getGridParam", "selrow" );			// 선택한 열의 아이디값
			var inoutId1 = $("#distributorReceiptInspTable").jqGrid("getRowData", rowid1);		// 선택한 열중에서 사업자번호정보를 가져온다.
			
			data.inoutId = inoutId1.inoutId;
		}
		
		$("#distributorReceiptInspTable3").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		
		param.soId = data.soId;
		param.inoutId = data.inoutId;
		param.itemId = data.itemId;
		param.itemTpCd = data.itemTpCd;
		param.statDt = $.datepicker.formatDate('yymmdd', new Date());
		
        $("#distributorReceiptInspTable3").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function insertInsp(param){
		
		
		var check = confirm('<spring:message code="MSG.M01.MSG00012" />');		//검수저장 하시겠습니까?

		if(check){
			
			param = JSON.stringify(param);
			
			$.ajax({
				url : 'insertInsp.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						searchData2();
						
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		}
	}
	
	
	function insertInConf(param){
		
		var check = confirm('<spring:message code="MSG.M08.MSG00069" />');		//입고승인 하시겠습니까?

		if(check){
			
			param = JSON.stringify(param);
			
			$.ajax({
				url : 'insertInConf.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						searchData();
						
						$("#distributorReceiptInspTable2").clearGridData();  // 이전 데이터 삭제
						$("#distributorReceiptInspTable3").clearGridData();  // 이전 데이터 삭제
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		}
	}
	
	
	
	function insertInRefuse(param){
		
		var check = confirm('<spring:message code="MSG.M08.MSG00059" />');		//입고거절 하시겠습니까?

		if(check){
			
			param = JSON.stringify(param);
			
			$.ajax({
				url : 'insertInRefuse.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						searchData();
						
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		}
	}
	
	
</script>


	
<!--NaviGator-->
<div class="h3_bg">
	<h3>${menuName}</h3>
	<!-- Navigator -->
		<div class="nav">                                        
		   <a href="#" class="home"></a>
			<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
			<span>&gt;</span>${mu.menuName}
		</c:forEach>
		</div>                               
   <!--// Navigator -->
</div>

<div class="main_btn_box">
	<div class="fr mt10">
		<a href="#" id="btnSearch" class="grey-btn" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
			<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
			<td>
				<select class="w150" id="searchSoId" name="searchSoId">
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchMncoNm" name="searchMncoNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchMncoId" name="searchMncoId" />
					<a href="#" id="btnSearchMnco" name="btnSearchMnco" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M10.LAB00067"/><!-- 출고기간 --></th>
			<td>
				<div class="date_box">
					<div class="inp_date w135">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
					<span class="dash">~</span>
					<div class="inp_date w135">
						<input type="text" id="searchEndDt" name="searchEndDt" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th><spring:message code="LAB.M09.LAB00138"/><!-- 조직 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchOrgNm" name="searchOrgNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchOrgId" name="searchOrgId" />
					<a href="#" id="searchOrgPopup" name="searchOrgPopup" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
			</td>
		</tr>
		
		<tr>
			<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
			<td>
				<select id="searchItemTpCd" name="searchItemTpCd" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
						<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchItemNm" name="searchItemNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchItemId" name="searchItemId" />
					<a href="#" id="btnSearchItem" name="btnSearchItem" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M08.LAB00155"/><!-- 입고대기내역 --></h4>
	</div>
</div>

<div id="distributorReceiptInspGrid">
	<table id="distributorReceiptInspTable" class="w100p"></table>
	<div id="distributorReceiptInspPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M08.LAB00151"/><!-- 입고내역 --></h4>
	</div>
</div>

<div id="distributorReceiptInspGrid2">
	<table id="distributorReceiptInspTable2" class="w100p"></table>
	<div id="distributorReceiptInspPager2"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00100"/><!-- 상세내역 --></h4>
	</div>
</div>

<div id="distributorReceiptInspGrid3">
	<table id="distributorReceiptInspTable3" class="w100p"></table>
	<div id="distributorReceiptInspPager3"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnInspSave" title="<spring:message code='LAB.M01.LAB00028'/>" href="#" ><spring:message code="LAB.M01.LAB00028"/><!-- 검수저장 --></a>
		<a class="grey-btn" id="btnInConf" title="<spring:message code='LAB.M08.LAB00161'/>" href="#" ><spring:message code="LAB.M08.LAB00161"/><!-- 입고승인 --></a>
		<a class="grey-btn" id="btnInRefuse" title="<spring:message code='LAB.M08.LAB00149'/>" href="#" ><spring:message code="LAB.M08.LAB00149"/><!-- 입고거절 --></a>
	</div>
	<div class="fr">
		<!--초기화-->		
		<a class="white-btn not-active" href="#" id="btn_init" disabled="disabled" ><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
		<!--등록-->
		<%-- 
		<ntels:auth auth="${menuAuthC}">
			<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>		
		<!--수정-->
		<ntels:auth auth="${menuAuthU}">
			<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		<!--삭제-->
		<ntels:auth auth="${menuAuthD}">
			<a class="grey-btn" href="#" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
		 --%>
		<%-- 
		<!--출력-->
		<ntels:auth auth="${menuAuthP}">
			<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
		 --%>
	</div>
</div>
                           
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>	
	
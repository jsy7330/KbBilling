<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	var itemTpCd = "";
	var inoutPrgrStatCd = "";
	var soIdP2 = "";
	var eqtRsrcClCdP2 = "";
	var eqtUseCdP2 = "";
	var prchsUtPrcP2 = "";
	var dstrbUtPrcP2 = "";
	var itemIdP2 = "";

	$(document).ready(function() {
		
		setDataP2();
		
		if(inoutPrgrStatCd == "10"){
			$("#btnExcel").show();
			$("#btnInsert").show();
		}else if(inoutPrgrStatCd == "30"){
			$("#btnInspection").show();
		}
		
		if(itemTpCd == "C" || itemTpCd == "I"){
			$("#deliveryGrid1").show();
			
			initGridP21();
			
		}else if(itemTpCd == "U"){
			$("#deliveryGrid2").show();
			
			initGridP22();
			
		}else if(itemTpCd == "V"){
			$("#deliveryGrid3").show();
			
			initGridP23();
		}
		
		$("#btnInspection").click(function() {
			
			if(itemTpCd == "C"  || itemTpCd == "I"){

				var ids = $("#deliveryTable1").jqGrid("getGridParam", "selarrrow" );
				var allData = $("#deliveryTable1").getRowData();
				
				var yDataLength = 0;
				for(var i=0; i < allData.length; i++){
					if(allData[i].apprYn == "Y"){
						yDataLength++;
					}
				}
				
				console.log(allData);
				console.log(allData.length);
				console.log(yDataLength);
				console.log(ids.length);
				
				if(ids.length < 1){
					alert('<spring:message code="MSG.M01.MSG00011" />');		//검수대상제품을 선택해 주세요.
					return;
				}
				
				var params = new Array();
				for(var i=0; i<ids.length; i++){
					var param = $("#deliveryTable1").getRowData(ids[i]);
					
					if(param.apprYn == "Y"){
						alert('<spring:message code="MSG.M01.MSG00010" />');	//검수 완료된건은 검수 할 수 없습니다.
						return;
					}
					
					param.soId = soIdP2;
					param.eqtRsrcClCd = eqtRsrcClCdP2;
					param.eqtUseCd = eqtUseCdP2;
					param.apprYn = "Y";
					
					param.inoutId = $("#inoutIdP2").val();		//입출고ID
					param.itemTpCd = itemTpCd;
					param.inoutPrgrStatCd = "40"; // 입고진행상태코드 (입고검수)
					
					if(i == 0 && allData.length == yDataLength + ids.length){
						param.inoutPrgrStatCheck = "Y"; // 입고진행상태 체크 
					}else{
						param.inoutPrgrStatCheck = "N"; // 입고진행상태 체크 
					}
					
					params.push(param);
				}
				
				insertInspection(params);
				
			}else if(itemTpCd == "U"){

				var ids = $("#deliveryTable2").jqGrid("getGridParam", "selarrrow" );
				var allData = $("#deliveryTable2").getRowData();
				
				var yDataLength = 0;
				for(var i=0; i < allData.length; i++){
					if(allData[i].apprYn == "Y"){
						yDataLength++;
					}
				}
				
				if(ids.length < 1){
					alert('<spring:message code="MSG.M01.MSG00011" />');		//검수대상제품을 선택해 주세요.
					return;
				}
				
				var params = new Array();
				for(var i=0; i<ids.length; i++){
					var param = $("#deliveryTable2").getRowData(ids[i]);
					
					if(param.apprYn == "Y"){
						alert('<spring:message code="MSG.M01.MSG00010" />');	//검수 완료된건은 검수 할 수 없습니다.
						return;
					}
					
					param.soId = soIdP2;
					param.eqtRsrcClCd = eqtRsrcClCdP2;
					param.eqtUseCd = eqtUseCdP2;
					param.apprYn = "Y";
					
					param.inoutId = $("#inoutIdP2").val();		//입출고ID
					param.itemTpCd = itemTpCd;
					param.inoutPrgrStatCd = "40"; // 입고진행상태코드 (입고검수)
					
					if(i == 0 && allData.length == yDataLength + ids.length){
						param.inoutPrgrStatCheck = "Y"; // 입고진행상태 체크 
					}else{
						param.inoutPrgrStatCheck = "N"; // 입고진행상태 체크 
					}
					
					params.push(param);
				}
				
				insertInspection(params);
				
			}else if(itemTpCd == "V"){

				var ids = $("#deliveryTable3").jqGrid("getGridParam", "selarrrow" );
				var allData = $("#deliveryTable3").getRowData();
				
				var yDataLength = 0;
				for(var i=0; i < allData.length; i++){
					if(allData[i].apprYn == "Y"){
						yDataLength++;
					}
				}
				
				if(ids.length < 1){
					alert('<spring:message code="MSG.M01.MSG00011" />');		//검수대상제품을 선택해 주세요.
					return;
				}
				
				var params = new Array();
				for(var i=0; i<ids.length; i++){
					var param = $("#deliveryTable3").getRowData(ids[i]);
					
					if(param.apprYn == "Y"){
						alert('<spring:message code="MSG.M01.MSG00010" />');	//검수 완료된건은 검수 할 수 없습니다.
						return;
					}
					
					param.soId = soIdP2;
					param.eqtRsrcClCd = eqtRsrcClCdP2;
					param.eqtUseCd = eqtUseCdP2;
					param.apprYn = "Y";
					
					param.inoutId = $("#inoutIdP2").val();		//입출고ID
					param.itemTpCd = itemTpCd;
					param.inoutPrgrStatCd = "40"; // 입고진행상태코드 (입고검수)
					
					if(i == 0 && allData.length == yDataLength + ids.length){
						param.inoutPrgrStatCheck = "Y"; // 입고진행상태 체크 
					}else{
						param.inoutPrgrStatCheck = "N"; // 입고진행상태 체크 
					}
					
					params.push(param);
				}
				
				insertInspection(params);
				
				
			}
			
		});
		
		
		$("#btnExcel").click(function() {
			
			var url="excelUploadPopUp.ajax";
			url = url + "?itemTpCd=" + itemTpCd;
			
			var width = 910;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			 
		});
		
		$("#btnInsert").click(function() {
			
			if(itemTpCd == "C"  || itemTpCd == "I"){
				
				var ids = $("#deliveryTable1").jqGrid("getGridParam", "selarrrow" );
				
				if(ids.length < 1){
					alert('<spring:message code="MSG.M08.MSG00064" />');		//입고대상제품을 선택해 주세요.
					return;
				}
				
				if(ids.length != $("#inoutQtyP2").val() ){
					alert('<spring:message code="MSG.M08.MSG00067" />');		//입고수량과 업로드된 입고수량이 일치하지 않습니다.
					return;
				}
				
				
				var params = new Array();
				for(var i=0; i<ids.length; i++){
					var param = $("#deliveryTable1").getRowData(ids[i]);
					
					if(itemIdP2 != param.itemId){
						alert('<spring:message code="MSG.M08.MSG00077" />');		//입력된 제품ID가 일치하지 않습니다. 제품ID를 확인하여 주십시오.
						return;
					}
					
					param.soId = soIdP2;
					param.eqtRsrcClCd = eqtRsrcClCdP2;
					param.eqtUseCd = eqtUseCdP2;
					param.eqtStatCd = "001"; 	// 제품상태코드 (단말기-가용)
					param.lgstProcStatCd = "";	//물류처리상태코드
					param.lgstProcDttm = "";	//물류처리일시
					param.ownLocCd = "";		//소유위치코드
					param.frstInDt = "";		//최초물류센터입고일자
					param.agcInDt = "";			//대리점입고일자
					param.prchsUtPrc = prchsUtPrcP2;	//매입단가
					param.dstrbUtPrc = dstrbUtPrcP2;	//유통단가
					param.ctOrgId = "";	//접점조직
					param.ctChgId = "";	//접점변경자
					param.ctChgDt = "";		//접점변경일
					
					param.verNo = "";		//버전번호
					param.eqtMgmtStatCd = "";	//단말기관리상태코드
					param.nmClCd = "";		//네이밍구분코드
					param.pscMinNo = "";	//물리MIN번호
					param.pscImsiNo = "";	//물리IMSI번호
					param.vImsiNo = "";		//가변IMSI번호
					param.macAddrVal = "";		//MAC주소
					param.mncoDt = "";		//제조일
					param.resultCd = "";		//처리결과코드
					
					param.inoutId = $("#inoutIdP2").val();		//입출고ID
					param.itemTpCd = itemTpCd;
					param.inoutPrgrStatCd = "30"; // 입고진행상태코드 (입고확정)입고승인
					
					params.push(param);
				}
				
				
				insertInoutList(params);
				
			}else if(itemTpCd == "U"){
				
				var ids = $("#deliveryTable2").jqGrid("getGridParam", "selarrrow" );
				
				if(ids.length < 1){
					alert('<spring:message code="MSG.M08.MSG00064" />');		//입고대상제품을 선택해 주세요.
					return;
				}
				
				if(ids.length != $("#inoutQtyP2").val() ){
					alert('<spring:message code="MSG.M08.MSG00067" />');		//입고수량과 업로드된 입고수량이 일치하지 않습니다.
					return;
				}
				
				
				var params = new Array();
				for(var i=0; i<ids.length; i++){
					var param = $("#deliveryTable2").getRowData(ids[i]);
					
					if(itemIdP2 != param.itemId){
						alert('<spring:message code="MSG.M08.MSG00077" />');		//입력된 제품ID가 일치하지 않습니다. 제품ID를 확인하여 주십시오.
						return;
					}
					
					param.soId = soIdP2;
					param.usimBarCd = param.eqtBarCd;
					param.eqtStatCd = "001"; 	// 제품상태코드 (단말기-가용)
					param.usimStatCd = "001"; 	// 제품상태코드 (단말기-가용)
					param.prchsUtPrc = prchsUtPrcP2;	//매입단가
					
					param.eqtRsrcClCd = eqtRsrcClCdP2;
					param.eqtUseCd = eqtUseCdP2;
					param.inoutId = $("#inoutIdP2").val();		//입출고ID
					param.itemTpCd = itemTpCd;
					param.inoutPrgrStatCd = "30"; // 입고진행상태코드 (입고확정)입고승인
					
					params.push(param);
				}
				
				console.log(params);
				
				insertInoutList(params);
				
			}else if(itemTpCd == "V"){
				
				var ids = $("#deliveryTable3").jqGrid("getGridParam", "selarrrow" );
				
				if(ids.length < 1){
					alert('<spring:message code="MSG.M08.MSG00064" />');		//입고대상제품을 선택해 주세요.
					return;
				}
				
				if(ids.length != $("#inoutQtyP2").val() ){
					alert('<spring:message code="MSG.M08.MSG00067" />');		//입고수량과 업로드된 입고수량이 일치하지 않습니다.
					return;
				}
				
				
				var params = new Array();
				for(var i=0; i<ids.length; i++){
					var param = $("#deliveryTable3").getRowData(ids[i]);
					
					if(itemIdP2 != param.itemId){
						alert('<spring:message code="MSG.M08.MSG00077" />');		//입력된 제품ID가 일치하지 않습니다. 제품ID를 확인하여 주십시오.
						return;
					}
					
					param.soId = soIdP2;
					paramactncNo = $("#actncNoP2").val();
					param.inoutId = $("#inoutIdP2").val();		//입출고ID
					
					
					
					param.usimBarCd = param.eqtBarCd;
					param.eqtStatCd = "001"; 	// 제품상태코드 (단말기-가용)
					param.usimStatCd = "001"; 	// 제품상태코드 (단말기-가용)
					param.prchsUtPrc = prchsUtPrcP2;	//매입단가
					
					param.eqtRsrcClCd = eqtRsrcClCdP2;
					param.eqtUseCd = eqtUseCdP2;
					
					param.itemTpCd = itemTpCd;
					param.inoutPrgrStatCd = "30"; // 입고진행상태코드 (입고확정)입고승인
					
					params.push(param);
				}
				
				console.log(params);
				
				insertInoutList(params);
				
			}
			
		});
		
		
	});
	
	function initGridP21() {
		
		var param = new Object();
		param.soId = soIdP2;
		param.inoutId = $("#inoutIdP2").val();
		
		$("#deliveryTable1").jqGrid({
			
		   	url:'selectInOutEqtListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
	   		 	{ label: '<spring:message code="LAB.M01.LAB00027" />', name: 'apprYn', width : 100},	//검수여부
	   		 	{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},	//제품ID
	   		 	{ label: '<spring:message code="LAB.M08.LAB00139" />', name: 'eqtSeq', width : 100},		//일련번호
	   		 	{ label: '<spring:message code="LAB.M15.LAB00045" />', name: 'imeiNo', width : 100},		//IMEI
	   		 	{ label: '<spring:message code="LAB.M07.LAB00163" />', name: 'clorCd', width : 100},			//색상코드
	   		 	{ label: '<spring:message code="LAB.M15.LAB00101" />', name: 'macAddrVal', width : 100},		//WIFIMACADDR
	   		 	{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', align:'center', width : 100},		//바코드
	   		 	{ label: '<spring:message code="LAB.M15.LAB00012" />', name: 'algoid', align:'center', width : 100},		//ALGOID
	   			{ label: '<spring:message code="LAB.M15.LAB00051" />', name: 'kdbid', align:'center', width : 100},		//KDBID
	   			{ label: '<spring:message code="LAB.M15.LAB00008" />', name: 'acsub', align:'center', width : 100},		//ACSUB
	   			{ label: '<spring:message code="LAB.M09.LAB00104" />', name: 'mncoDt', align:'center', width : 100}		//제조일자
	   		 	
	   		],

		   	rowNum:99999999,					//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 250,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#deliveryTable1").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#deliveryTable1").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#deliveryTable1").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function searchDataP21(){
		
		$("#deliveryTable1").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		/* 
		param.actncOrgId = "0000121212";
		param.soId = $("#searchSoId").val();
		param.searchStatDt = dateFormatToStringYYYYMMDD( $("#searchStatDt").val() );
		 */
        $("#deliveryTable1").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}

	function initGridP22() {
		
		var param = new Object();
		param.soId = soIdP2;
		param.inoutId = $("#inoutIdP2").val();
		
		$("#deliveryTable2").jqGrid({
			
		   	url:'selectInOutUsimListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'eqtSeq' , name: 'eqtSeq', hidden:true,width : 0},

	   		 	{ label: '<spring:message code="LAB.M01.LAB00027" />', name: 'apprYn', width : 100},	//검수여부
	   		 	{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},	//제품ID
	   		 	{ label: '<spring:message code="LAB.M15.LAB00047" />', name: 'imsiNo', width : 100},	//IMSI_NO
	   		 	{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', width : 100},	//바코드
	   		 	{ label: '<spring:message code="LAB.M15.LAB00071" />', name: 'pin1', width : 100},		//PIN1
	   			{ label: '<spring:message code="LAB.M15.LAB00073" />', name: 'puk1', width : 100},		//PUK1
	   			{ label: '<spring:message code="LAB.M15.LAB00072" />', name: 'pin2', width : 100},		//PIN2
	   			{ label: '<spring:message code="LAB.M15.LAB00074" />', name: 'puk2', width : 100},		//PUK2
	   			{ label: '<spring:message code="LAB.M15.LAB00010" />', name: 'adm', width : 100},		//ADM
	   			{ label: '<spring:message code="LAB.M15.LAB00053" />', name: 'ki', width : 100},		//KI
	   			{ label: '<spring:message code="LAB.M15.LAB00007" />', name: 'acc', width : 100},		//ACC
	   			{ label: '<spring:message code="LAB.M15.LAB00012" />', name: 'algoid', width : 100},	//ALGOID
	   			{ label: '<spring:message code="LAB.M15.LAB00051" />', name: 'kdbid', width : 100},		//KDBID
	   			{ label: '<spring:message code="LAB.M15.LAB00008" />', name: 'acsub', width : 100},		//ACSUB
	   			{ label: '<spring:message code="LAB.M09.LAB00104" />', name: 'mncoDt', align:'center', width : 100},		//제조일자
	   			
	   			

	   		],

		   	rowNum:99999999,					//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 250,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#deliveryTable2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#deliveryTable2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#deliveryTable2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function searchDataP22(){
		
		$("#deliveryTable2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
        $("#deliveryTable2").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function initGridP23() {
		
		var param = new Object();
		param.soId = soIdP2;
		param.inoutId = $("#inoutIdP2").val();
		
		$("#deliveryTable3").jqGrid({
			
		   	url:'selectInOutVEqtListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
	   		 	{ label: '<spring:message code="LAB.M01.LAB00027" />', name: 'apprYn', width : 100},	//검수여부
	   		 	{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},	//제품ID
	   		 	{ label: '<spring:message code="LAB.M08.LAB00139" />', name: 'eqtSeq', width : 100},		//일련번호
	   		 	{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', align:'center', width : 100},	//바코드
	   			{ label: '<spring:message code="LAB.M15.LAB00012" />', name: 'algoid', align:'center', width : 100},		//ALGOID
	   			{ label: '<spring:message code="LAB.M15.LAB00051" />', name: 'kdbid', align:'center', width : 100},		//KDBID
	   			{ label: '<spring:message code="LAB.M15.LAB00008" />', name: 'acsub', align:'center', width : 100},		//ACSUB
	   			{ label: '<spring:message code="LAB.M09.LAB00104" />', name: 'mncoDt', align:'center', width : 100}		//제조일자

	   		],

		   	rowNum:99999999,					//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 250,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#deliveryTable3").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#deliveryTable3").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#deliveryTable3").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function searchDataP23(){
		
		$("#deliveryTable3").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
        $("#deliveryTable3").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function setDataP2(){
		
		var rowId  = $("#deliveryInspectionTable2").jqGrid("getGridParam", "selrow" );
		var data = $("#deliveryInspectionTable2").getRowData(rowId);
		
		itemTpCd = data.itemTpCd;
		inoutPrgrStatCd = data.inoutPrgrStatCd;
		soIdP2 = data.soId;
		eqtRsrcClCdP2 = data.eqtRsrcClCd;
		eqtUseCdP2 = data.eqtUseCd;
		prchsUtPrcP2 = data.prchsUtPrc;
		dstrbUtPrcP2 = data.dstrbUtPrc;
		itemIdP2 = data.itemId;
		
		$("#itemTpNmP2").val(data.itemTpNm);
		$("#itemIdP2").val(data.itemId);
		$("#itemNmP2").val(data.itemNm);
		$("#clorCdP2").val(data.clorCd);
		$("#colorNmP2").val(data.colorNm);
		$("#actncNoP2").val(data.actncNo);
		$("#inoutIdP2").val(data.inoutId);
		$("#inoutQtyP2").val(data.inoutQty);
		
	}
	
	
	function setGridListC(param){
		
		$("#deliveryTable1").clearGridData();  // 이전 데이터 삭제

		for(var i=0 ;i<param.length; i++){
			
			var addData = {
					"itemId" : param[i].itemId
					, "eqtSeq" : param[i].eqtSeq
					, "imeiNo" : param[i].imeiNo
					, "clorCd" : param[i].clorCd
					, "macAddrVal" : param[i].macAddrVal
					, "eqtBarCd" : param[i].eqtBarCd
					, "mncoDt" : param[i].mncoDt
			}
			$("#deliveryTable1").jqGrid('addRowData',i+1,addData);
			
		}
		$("#deliveryTable1").setGridWidth($('#deliveryGrid1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function setGridListU(param){
		
		$("#deliveryTable2").clearGridData();  // 이전 데이터 삭제

		for(var i=0 ;i<param.length; i++){
			
			var addData = {
					"itemId" : param[i].itemId
					, "imsiNo" : param[i].imsiNo
					, "eqtBarCd" : param[i].eqtBarCd
					, "pin1" : param[i].pin1
					, "puk1" : param[i].puk1
					, "pin2" : param[i].pin2
					, "puk2" : param[i].puk2
					, "adm" : param[i].adm
					, "ki" : param[i].ki
					, "acc" : param[i].acc
					, "algoid" : param[i].algoid
					, "kdbid" : param[i].kdbid
					, "acsub" : param[i].acsub
					, "mncoDt" : param[i].mncoDt
			}
			$("#deliveryTable2").jqGrid('addRowData',i+1,addData);
			
		}
		$("#deliveryTable2").setGridWidth($('#deliveryGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	
	function setGridListV(param){
		
		$("#deliveryTable3").clearGridData();  // 이전 데이터 삭제

		for(var i=0 ;i<param.length; i++){
			
			var addData = {
					"itemId" : param[i].itemId
					, "eqtSeq" : param[i].eqtSeq
					, "eqtBarCd" : param[i].eqtBarCd
					, "mncoDt" : param[i].mncoDt
			}
			$("#deliveryTable3").jqGrid('addRowData',i+1,addData);
			
		}
		$("#deliveryTable3").setGridWidth($('#deliveryGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
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
	
	
	function insertInspection(param){
		
		if(param != false){
			
			param = JSON.stringify(param)
			
			$.ajax({
				url : 'insertInspection.json',
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
	<div class="title"><spring:message code="LAB.M09.LAB00103"/><!-- 제조사입고확정 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->


<!-- center -->
<div class="layer_box">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M09.LAB00103"/><!-- 제조사입고확정 --></h4>
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
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
				<td>
					<input type="text" id="itemTpNmP2" name="itemTpNmP2" class="w200" disabled="disalbed" />
				</td>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
				<td>
					<input type="text" id="itemIdP2" name="itemIdP2" class="w100" disabled="disalbed" />
					<input type="text" id="itemNmP2" name="itemNmP2" class="w100" disabled="disalbed" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00162"/><!-- 색상 --></th>
				<td>
					<input type="text" id="clorCdP2" name="clorCdP2" class="w100" disabled="disalbed" />
					<input type="text" id="colorNmP2" name="colorNmP2" class="w100" disabled="disalbed" />
				</td>
				<th><spring:message code="LAB.M02.LAB00025"/><!-- 납품번호 --></th>
				<td>
					<input type="text" id="actncNoP2" name="actncNoP2" class="w200" disabled="disalbed" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00148"/><!-- 입고ID --></th>
				<td>
					<input type="text" id="inoutIdP2" name="inoutIdP2" class="w200" disabled="disalbed" />
				</td>
				<th><spring:message code="LAB.M08.LAB00160"/><!-- 입고수량 --></th>
				<td>
					<input type="text" id="inoutQtyP2" name="inoutQtyP2" class="w200" disabled="disalbed" />
				</td>
			</tr>
		</thead>
	</table>
	 
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M08.LAB00156"/><!-- 입고대상제품 --></h4>
		</div>
	</div>

	<div class="layer_box" id="deliveryGrid1" style="display:none;" >
		<table id="deliveryTable1" class="w100p"></table>
		<div id="deliveryPager1"></div>
	</div>
	
	<div class="layer_box" id="deliveryGrid2" style="display:none;" >
		<table id="deliveryTable2" class="w100p"></table>
		<div id="deliveryPager2"></div>
	</div>
	
	<div class="layer_box" id="deliveryGrid3" style="display:none;" >
		<table id="deliveryTable3" class="w100p"></table>
		<div id="deliveryPager3"></div>
	</div>
	
</div>

<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInspection" style="display:none;" ><span class=""><spring:message code="MSG.M01.MSG00009" /></span></a><!-- 검수 -->
	<a class="grey-btn" href="#" id="btnExcel" style="display:none;" ><span class="">Excel Upload</span></a>
	<a class="grey-btn" href="#" id="btnInsert" style="display:none;" ><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<%-- <a class="grey-btn" href="#" id="btn_deleteP1" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a> --%>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<!--// center -->
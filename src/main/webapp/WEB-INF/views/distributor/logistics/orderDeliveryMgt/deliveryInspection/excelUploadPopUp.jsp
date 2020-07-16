<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	var itemTpCd = "${deliveryInspectionVO.itemTpCd}";

	$(document).ready(function() {
		
		$("#btnColse1").click(function() {
			window.open("about:blank", "_self").close();
		});
		
		$("#btnClose").click(function() {
			window.open("about:blank", "_self").close();
		});
		
		changeColumnType();
		$("input[name='columnType']").change(function() {
			changeColumnType();
		});
		
		
		
		if(itemTpCd == "C" || itemTpCd == "I"){
			$("#excelUploadGrid1").show();
			$("#columnTableC").show();
			
			initGridP21();
			
		}else if(itemTpCd == "U"){
			$("#excelUploadGrid2").show();
			$("#columnTableU").show();
			
			initGridP22();
			
		}else if(itemTpCd == "V"){
			$("#excelUploadGrid3").show();
			$("#columnTableV").show();
			
			initGridP23();
		}
		
		//
		$("#btnExcelUploadColumn").click(function() {
			
			
			if(itemTpCd == "C"|| itemTpCd == "I"){
				fileUploadColumnC("ex_filename01");
			}else if(itemTpCd == "U"){
				fileUploadColumnU("ex_filename01");
			}else if(itemTpCd == "V"){
				fileUploadColumnV("ex_filename01");
			}
			
		});
		
		$("#btnShowExcel").click(function() {
			
			if(itemTpCd == "C"|| itemTpCd == "I"){
				fileUploadExcelC("ex_filename01");
			}else if(itemTpCd == "U"){
				fileUploadExcelU("ex_filename01");
			}else if(itemTpCd == "V"){
				fileUploadExcelV("ex_filename01");
			}
		});
		
		
		$("#btnInsert").click(function() {
			
			if(itemTpCd == "C" || itemTpCd == "I"){
				
				var ids = $("#excelUploadTable1").jqGrid("getGridParam", "selarrrow" );
				
				if(ids.length < 1){
					alert('<spring:message code="MSG.M08.MSG00008" />');		//업로드 내역을 선택해 주세요.
					return;
				}
				
				var params = new Array();
				for(var i=0; i<ids.length; i++){
					var param = $("#excelUploadTable1").getRowData(ids[i]);
					params.push(param);
				}
				
				opener.parent.setGridListC(params);
				
				window.open("about:blank", "_self").close();

			}else if(itemTpCd == "U"){

				var ids = $("#excelUploadTable2").jqGrid("getGridParam", "selarrrow" );
				
				if(ids.length < 1){
					alert('<spring:message code="MSG.M08.MSG00008" />');		//업로드 내역을 선택해 주세요.
					return;
				}
				
				var params = new Array();
				for(var i=0; i<ids.length; i++){
					var param = $("#excelUploadTable2").getRowData(ids[i]);
					params.push(param);
				}
				
				opener.parent.setGridListU(params);
				
				window.open("about:blank", "_self").close();
				
			}else if(itemTpCd == "V"){

			var ids = $("#excelUploadTable3").jqGrid("getGridParam", "selarrrow" );
				
				if(ids.length < 1){
					alert('<spring:message code="MSG.M08.MSG00008" />');		//업로드 내역을 선택해 주세요.
					return;
				}
				
				var params = new Array();
				for(var i=0; i<ids.length; i++){
					var param = $("#excelUploadTable3").getRowData(ids[i]);
					params.push(param);
				}
				
				opener.parent.setGridListV(params);
				
				window.open("about:blank", "_self").close();
				
			}
		});
		
	});
	
	
	function fileUploadColumnC(fileId){
		
		var formData = new FormData();
		formData.append("fileName",$("input[name=" + fileId + "]")[0].files[0]);

		$.ajax({
			async : false,
			method : "post",
			url : 'excelUploadColumnAction.json',
			processData : false, //true : data의 파일형태가 query String으로 전송. false : non-processed data 
			data : formData,
			contentType : false, // false : multipart/form-data 형태로 전송되기 위한 옵션값
			success : function(data){
				
				if(data.returnList != null){
					var selectHtml = "<option value='' ></option>";
					var listLength = data.returnList.length;
					var columnType = $(':radio[name="columnType"]:checked').val();
					
					for(var i=0; i<listLength; i++){
						selectHtml += "<option value='" + data.returnList[i] + "' >" + data.returnList[i] + "</option>";
					}
					
					for(var i=0; i<7; i++){		//컬럼 갯수 고정
						$("#columnC"+i).html("");
						$("#columnC"+i).html(selectHtml);
						$("#columnC"+i).selectmenu( "refresh" );
						
						if(columnType == "01"){
							$("#columnC"+i).val(data.returnList[i]);
							$("#columnC"+i).selectmenu( "refresh" );
						}
					}
					
				}else{
					alert('<spring:message code="MSG.M09.MSG00022" />');	//정확한 엑셀파일을 업로드 해 주세요.
				}
			}
		});
		
	}
	
	function fileUploadColumnU(fileId){
		
		var formData = new FormData();
		formData.append("fileName",$("input[name=" + fileId + "]")[0].files[0]);

		$.ajax({
			async : false,
			method : "post",
			url : 'excelUploadColumnAction.json',
			processData : false, //true : data의 파일형태가 query String으로 전송. false : non-processed data 
			data : formData,
			contentType : false, // false : multipart/form-data 형태로 전송되기 위한 옵션값
			success : function(data){
				
				if(data.returnList != null){
					var selectHtml = "<option value='' ></option>";
					var listLength = data.returnList.length;
					var columnType = $(':radio[name="columnType"]:checked').val();
					
					for(var i=0; i<listLength; i++){
						selectHtml += "<option value='" + data.returnList[i] + "' >" + data.returnList[i] + "</option>";
					}
					
					for(var i=0; i<14; i++){		//컬럼 갯수 고정
						$("#columnU"+i).html("");
						$("#columnU"+i).html(selectHtml);
						$("#columnU"+i).selectmenu( "refresh" );
						
						if(columnType == "01"){
							$("#columnU"+i).val(data.returnList[i]);
							$("#columnU"+i).selectmenu( "refresh" );
						}
					}
					
				}else{
					alert('<spring:message code="MSG.M09.MSG00022" />');	//정확한 엑셀파일을 업로드 해 주세요.
				}
			}
		});
		
	}
	
	
	function fileUploadColumnV(fileId){
		
		var formData = new FormData();
		formData.append("fileName",$("input[name=" + fileId + "]")[0].files[0]);

		$.ajax({
			async : false,
			method : "post",
			url : 'excelUploadColumnAction.json',
			processData : false, //true : data의 파일형태가 query String으로 전송. false : non-processed data 
			data : formData,
			contentType : false, // false : multipart/form-data 형태로 전송되기 위한 옵션값
			success : function(data){
				
				if(data.returnList != null){
					var selectHtml = "<option value='' ></option>";
					var listLength = data.returnList.length;
					var columnType = $(':radio[name="columnType"]:checked').val();
					
					for(var i=0; i<listLength; i++){
						selectHtml += "<option value='" + data.returnList[i] + "' >" + data.returnList[i] + "</option>";
					}
					
					for(var i=0; i<4; i++){		//컬럼 갯수 고정
						$("#columnV"+i).html("");
						$("#columnV"+i).html(selectHtml);
						$("#columnV"+i).selectmenu( "refresh" );
						
						if(columnType == "01"){
							$("#columnV"+i).val(data.returnList[i]);
							$("#columnV"+i).selectmenu( "refresh" );
						}
					}
					
				}else{
					alert('<spring:message code="MSG.M09.MSG00022" />');	//정확한 엑셀파일을 업로드 해 주세요.
				}
			}
		});
		
	}
	
	function fileUploadExcelC(fileId){
		
		var formData = new FormData();
		formData.append("fileName", $("input[name=" + fileId + "]")[0].files[0]);
		formData.append("column0" , $("#columnC0").val());
		formData.append("column1" , $("#columnC1").val());
		formData.append("column2" , $("#columnC2").val());
		formData.append("column3" , $("#columnC3").val());
		formData.append("column4" , $("#columnC4").val());
		formData.append("column5" , $("#columnC5").val());
		formData.append("column6" , $("#columnC6").val());
		
		$.ajax({
			async : false,
			method : "post",
			url : 'excelUploadCAction.json',
			processData : false, //true : data의 파일형태가 query String으로 전송. false : non-processed data 
			data : formData,
			contentType : false, // false : multipart/form-data 형태로 전송되기 위한 옵션값
			success : function(data){
				                 
				$("#excelUploadTable1").clearGridData();  // 이전 데이터 삭제

				for(var i=0 ;i<data.returnList.length; i++){
					
					console.log(data.returnList[i]);
					
					var addData = {
							"itemId" : data.returnList[i].itemId
							, "eqtSeq" : data.returnList[i].eqtSeq
							, "imeiNo" : data.returnList[i].imeiNo
							, "clorCd" : data.returnList[i].clorCd
							, "macAddrVal" : data.returnList[i].macAddrVal
							, "eqtBarCd" : data.returnList[i].eqtBarCd
							, "mncoDt" : data.returnList[i].mncoDt
					}
					$("#excelUploadTable1").jqGrid('addRowData',i+1,addData);
					
				}
				$("#excelUploadTable1").setGridWidth($('#excelUploadGrid1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			}
		});
		
	}
	
	
	function fileUploadExcelU(fileId){
		
		var formData = new FormData();
		formData.append("fileName", $("input[name=" + fileId + "]")[0].files[0]);
		formData.append("column0" , $("#columnU0").val());
		formData.append("column1" , $("#columnU1").val());
		formData.append("column2" , $("#columnU2").val());
		formData.append("column3" , $("#columnU3").val());
		formData.append("column4" , $("#columnU4").val());
		formData.append("column5" , $("#columnU5").val());
		formData.append("column6" , $("#columnU6").val());
		formData.append("column7" , $("#columnU7").val());
		formData.append("column8" , $("#columnU8").val());
		formData.append("column9" , $("#columnU9").val());
		formData.append("column10" , $("#columnU10").val());
		formData.append("column11" , $("#columnU11").val());
		formData.append("column12" , $("#columnU12").val());
		formData.append("column13" , $("#columnU13").val());
		
		$.ajax({
			async : false,
			method : "post",
			url : 'excelUploadUAction.json',
			processData : false, //true : data의 파일형태가 query String으로 전송. false : non-processed data 
			data : formData,
			contentType : false, // false : multipart/form-data 형태로 전송되기 위한 옵션값
			success : function(data){
				                 
				$("#excelUploadTable2").clearGridData();  // 이전 데이터 삭제

				for(var i=0 ;i<data.returnList.length; i++){
					
					var addData = {
							"itemId" : data.returnList[i].itemId
							, "imsiNo" : data.returnList[i].imsiNo
							, "eqtBarCd" : data.returnList[i].eqtBarCd
							, "pin1" : data.returnList[i].pin1
							, "puk1" : data.returnList[i].puk1
							, "pin2" : data.returnList[i].pin2
							, "puk2" : data.returnList[i].puk2
							, "adm" : data.returnList[i].adm
							, "ki" : data.returnList[i].ki
							, "acc" : data.returnList[i].acc
							, "algoid" : data.returnList[i].algoid
							, "kdbid" : data.returnList[i].kdbid
							, "acsub" : data.returnList[i].acsub
							, "mncoDt" : data.returnList[i].mncoDt
					}
					$("#excelUploadTable2").jqGrid('addRowData',i+1,addData);
				}
				$("#excelUploadTable2").setGridWidth($('#excelUploadGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			}
		});
		
	}
	
	
	function fileUploadExcelV(fileId){
		
		var formData = new FormData();
		formData.append("fileName", $("input[name=" + fileId + "]")[0].files[0]);
		formData.append("column0" , $("#columnV0").val());
		formData.append("column1" , $("#columnV1").val());
		formData.append("column2" , $("#columnV2").val());
		formData.append("column3" , $("#columnV3").val());
		
		$.ajax({
			async : false,
			method : "post",
			url : 'excelUploadVAction.json',
			processData : false, //true : data의 파일형태가 query String으로 전송. false : non-processed data 
			data : formData,
			contentType : false, // false : multipart/form-data 형태로 전송되기 위한 옵션값
			success : function(data){
				                 
				$("#excelUploadTable3").clearGridData();  // 이전 데이터 삭제

				for(var i=0 ;i<data.returnList.length; i++){
					
					console.log(data.returnList[i]);
					
					var addData = {
							"itemId" : data.returnList[i].itemId
							, "eqtSeq" : data.returnList[i].eqtSeq
							, "eqtBarCd" : data.returnList[i].eqtBarCd
							, "mncoDt" : data.returnList[i].mncoDt
					}
					$("#excelUploadTable3").jqGrid('addRowData',i+1,addData);
					
				}
				$("#excelUploadTable3").setGridWidth($('#excelUploadGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			}
		});
		
	}
	
	function initGridP21() {
		
		var param = new Object();
		param.soId = "00";
		param.inoutId = "1";
		
		$("#excelUploadTable1").jqGrid({
			
		   	url:'selectInOutEqtListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
	   		 	{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},	//제품ID
	   		 	{ label: '<spring:message code="LAB.M08.LAB00139" />', name: 'eqtSeq', width : 100},		//일련번호
	   		 	{ label: '<spring:message code="LAB.M15.LAB00045" />', name: 'imeiNo', width : 100},		//IMEI
	   		 	{ label: '<spring:message code="LAB.M07.LAB00163" />', name: 'clorCd', width : 100},			//색상코드
	   		 	{ label: '<spring:message code="LAB.M15.LAB00101" />', name: 'macAddrVal', width : 100},		//WIFIMACADDR
	   		 	{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', align:'center', width : 100},		//바코드
	   			{ label: '<spring:message code="LAB.M09.LAB00104" />', name: 'mncoDt', align:'center', width : 100}		//제조일자
	   		],

		   	rowNum:99999999,					//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 270,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#excelUploadTable1").setGridWidth($('#excelUploadGrid1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#excelUploadTable1").setGridWidth($('#excelUploadGrid1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#excelUploadTable1").setGridWidth($('#excelUploadGrid1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#excelUploadTable1").setGridWidth($('#excelUploadGrid1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
		
	}
	
	function searchDataP21(){
		
		$("#excelUploadTable1").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
        $("#excelUploadTable1").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	function initGridP22() {
		
		var param = new Object();
		param.soId = "00";
		param.inoutId = "1";
		
		$("#excelUploadTable2").jqGrid({
			
		   	url:'selectInOutEqtListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
	   		 	{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},	//제품ID
	   		 	{ label: '<spring:message code="LAB.M15.LAB00047" />', name: 'imsiNo', width : 100},	//IMSI_NO
	   		 	{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', align:'center', width : 100},		//바코드
	   			{ label: '<spring:message code="LAB.M15.LAB00071" />', name: 'pin1', width : 100},		//PIN1
		   		{ label: '<spring:message code="LAB.M15.LAB00073" />', name: 'puk1', width : 100},		//PUK1
		   		{ label: '<spring:message code="LAB.M15.LAB00072" />', name: 'pin2', width : 100},		//PIN2
		   		{ label: '<spring:message code="LAB.M15.LAB00074" />', name: 'puk2', width : 100},		//PUK2
		   		{ label: '<spring:message code="LAB.M15.LAB00010" />', name: 'adm', width : 100},			//ADM
		   		{ label: '<spring:message code="LAB.M15.LAB00053" />', name: 'ki', width : 100},			//KI
		   		{ label: '<spring:message code="LAB.M15.LAB00007" />', name: 'acc', width : 100},			//ACC
		   		{ label: '<spring:message code="LAB.M15.LAB00012" />', name: 'algoid', width : 100},	//ALGOID
		   		{ label: '<spring:message code="LAB.M15.LAB00051" />', name: 'kdbid', width : 100},		//KDBID
		   		{ label: '<spring:message code="LAB.M15.LAB00008" />', name: 'acsub', width : 100},		//ACSUB
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
	        	$("#excelUploadTable2").setGridWidth($('#excelUploadGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#excelUploadTable2").setGridWidth($('#excelUploadGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#excelUploadTable2").setGridWidth($('#excelUploadGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#excelUploadTable2").setGridWidth($('#excelUploadGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
		
	}
	
	function searchDataP22(){
		
		$("#excelUploadTable2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
        $("#excelUploadTable2").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	function initGridP23() {
		
		var param = new Object();
		param.soId = "00";
		param.inoutId = "1";
		
		$("#excelUploadTable3").jqGrid({
			
		   	url:'selectInOutEqtListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
	   		 	{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},	//제품ID
	   		 	{ label: '<spring:message code="LAB.M08.LAB00139" />', name: 'eqtSeq', width : 100},		//일련번호
	   		 	{ label: '<spring:message code="LAB.M06.LAB00017" />', name: 'eqtBarCd', align:'center', width : 100},		//바코드
	   			{ label: '<spring:message code="LAB.M09.LAB00104" />', name: 'mncoDt', align:'center', width : 100}		//제조일자
	   		],

		   	rowNum:99999999,					//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 270,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#excelUploadTable3").setGridWidth($('#excelUploadGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#excelUploadTable3").setGridWidth($('#excelUploadGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#excelUploadTable3").setGridWidth($('#excelUploadGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#excelUploadTable3").setGridWidth($('#excelUploadGrid3').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
		
	}
	
	function searchDataP23(){
		
		$("#excelUploadTable3").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
        $("#excelUploadTable3").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function changeColumnType(){
		
		var columnType = $(':radio[name="columnType"]:checked').val();
		
		if(columnType == "01"){
			$("#columnTableC select").selectmenu("disable");
			$("#columnTableU select").selectmenu("disable");
			$("#columnTableV select").selectmenu("disable");
		}else{
			$("#columnTableC select").selectmenu("enable");
			$("#columnTableU select").selectmenu("enable");
			$("#columnTableV select").selectmenu("enable");
		}
	}
	
</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M08.LAB00157"/><!-- 입고대상제품 엑셀 업로드 --></div>
	<a href="#" class="close" id="btnColse1" >Close</a>
</div>
<!--// title -->

<form id="uploadForm" name="uploadForm" method="post" enctype="multipart/form-data">

	<div class="layer_box">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M08.LAB00157"/><!-- 입고대상제품 엑셀 업로드 --></h4>
			</div>
			<%-- 
			<div class="fr mt10">
				<a href="#" class="grey-btn" id="btnSearchP2"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
			</div>
			 --%>
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
					<th><spring:message code="LAB.M08.LAB00017"/><!-- 엑셀파일 --></th>
					<td>
						<span class="filebox bs3-primary">
							<input class="upload-name w210" id="fileNm" name="fileNm" placeholder="<spring:message code='LAB.M13.LAB00002'/>" />
							<!-- <input type="hidden" id="imgUuid" name="imgUuid" /> -->
							<label for="ex_filename01"><spring:message code="LAB.M08.LAB00009"/><!-- 업로드 --></label> 
							<input type="file" id="ex_filename01" name="ex_filename01" class="upload-hidden"> 
						</span>                                            	
						<a href="#" id="btnExcelUploadColumn" name="btnExcelUploadColumn" class="td-btn"><spring:message code="LAB.M09.LAB00049"/><!-- 적용 --></a>
					</td>
					<th><spring:message code="LAB.M11.LAB00003"/><!-- 컬럼맵핑 --></th>
					<td>
						<input type="radio" id="columnType1" name="columnType" value="01" checked="checked" />
						<label for="columnType1" ><spring:message code="LAB.M09.LAB00064"/><!-- 전체동일 --></label>
						<input type="radio" id="columnType2" name="columnType" value="02" />
						<label for="columnType2" ><spring:message code="LAB.M09.LAB00213"/><!-- 직접선택 --></label>
					</td>
				</tr>
			</thead>
		</table>
		
		

		<table class="writelist" id="columnTableC" style="display:none;" >
			<colgroup>
				<col style="width:14%;">
				<col style="width:14%;">
				<col style="width:14%;">
				<col style="width:14%;">
				<col style="width:14%;">
				<col style="width:14%;">
				<col style="width:16%;">
			</colgroup>
     		<thead> 
           		<tr>
	                <th><spring:message code="LAB.M09.LAB00106" /><!-- 제품ID --></th>
	                <th><spring:message code="LAB.M08.LAB00139" /><!-- 일련번호 --></th>
	                <th><spring:message code="LAB.M15.LAB00045" /><!-- IMEI --></th>
	                <th><spring:message code="LAB.M07.LAB00163" /><!-- 색상코드 --></th>
	                <th><spring:message code="LAB.M15.LAB00101" /><!-- WIFIMACADDR --></th>
	                <th><spring:message code="LAB.M06.LAB00017" /><!-- 바코드 --></th>
	                <th><spring:message code="LAB.M09.LAB00104" /><!-- 제조일자 --></th>
       			</tr>
    		</thead>
    		<tbody>
	           	<tr>
	           		<td>
						<select class="sel" id="columnC0" name="columnC0" >
							<option value="" ></option>
						</select> 
					</td>
					<td>
						<select class="sel" id="columnC1" name="columnC1" >
							<option value="" ></option>
						</select> 
					</td>
					<td>
						<select class="sel" id="columnC2" name="columnC2" >
							<option value="" ></option>
						</select> 
					</td>
					<td>
						<select class="sel" id="columnC3" name="columnC3" >
							<option value="" ></option>
						</select> 
					</td>
					<td>
						<select class="sel" id="columnC4" name="columnC4" >
							<option value="" ></option>
						</select> 
					</td>
					<td>
						<select class="sel" id="columnC5" name="columnC5" >
							<option value="" ></option>
						</select> 
					</td>
					<td>
						<select class="sel" id="columnC6" name="columnC6" >
							<option value="" ></option>
						</select> 
					</td>
					
				</tr>
			</tbody>
		</table>

		<div style="overflow:auto">
			<table class="writelist" id="columnTableU" style="width:2000px; display:none;" >
				<colgroup>
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:7%;">
					<col style="width:9%;">
				</colgroup>
	     		<thead> 
	           		<tr>
		                <th><spring:message code="LAB.M09.LAB00106" /><!-- 제품ID --></th>
		                <th><spring:message code="LAB.M15.LAB00047" /><!-- IMSI_NO --></th>
		                <th><spring:message code="LAB.M06.LAB00017" /><!-- 바코드 --></th>
		                <th><spring:message code="LAB.M15.LAB00071" /><!-- PIN1 --></th>
		                <th><spring:message code="LAB.M15.LAB00073" /><!-- PUK1 --></th>
		                <th><spring:message code="LAB.M15.LAB00072" /><!-- PIN2 --></th>
		                <th><spring:message code="LAB.M15.LAB00074" /><!-- PUK2 --></th>
		                <th><spring:message code="LAB.M15.LAB00010" /><!-- ADM --></th>
		                <th><spring:message code="LAB.M15.LAB00053" /><!-- KI --></th>
		                <th><spring:message code="LAB.M15.LAB00007" /><!-- ACC --></th>
		                <th><spring:message code="LAB.M15.LAB00012" /><!-- ALGOID --></th>
		                <th><spring:message code="LAB.M15.LAB00051" /><!-- KDBID --></th>
		                <th><spring:message code="LAB.M15.LAB00008" /><!-- ACSUB --></th>
		                <th><spring:message code="LAB.M09.LAB00104" /><!-- 제조일자 --></th>
	       			</tr>
	    		</thead>
	    		<tbody>
		           	<tr>
		           		<td>
							<select class="sel" id="columnU0" name="columnU0" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU1" name="columnU1" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU2" name="columnU2" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU3" name="columnU3" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU4" name="columnU4" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU5" name="columnU5" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU6" name="columnU6" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU7" name="columnU7" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU8" name="columnU8" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU9" name="columnU9" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU10" name="columnU10" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU11" name="columnU11" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU12" name="columnU12" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnU13" name="columnU13" >
								<option value="" ></option>
							</select> 
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		
		<div style="overflow:auto">
			<table class="writelist" id="columnTableV" style="display:none;" >
				<colgroup>
					<col style="width:25%;">
					<col style="width:25%;">
					<col style="width:25%;">
					<col style="width:25%;">
				</colgroup>
	     		<thead> 
	           		<tr>
		                <th><spring:message code="LAB.M09.LAB00106" /><!-- 제품ID --></th>
		                <th><spring:message code="LAB.M08.LAB00139" /><!-- 일련번호 --></th>
		                <th><spring:message code="LAB.M06.LAB00017" /><!-- 바코드 --></th>
		                <th><spring:message code="LAB.M09.LAB00104" /><!-- 제조일자 --></th>
	       			</tr>
	    		</thead>
	    		<tbody>
		           	<tr>
		           		<td>
							<select class="sel" id="columnV0" name="columnV0" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnV1" name="columnV1" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnV2" name="columnV2" >
								<option value="" ></option>
							</select> 
						</td>
						<td>
							<select class="sel" id="columnV3" name="columnV3" >
								<option value="" ></option>
							</select> 
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		 
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M04.LAB00013"/><!-- 리스트 --></h4>
			</div>
			<div class="fr mt10">
				<a href="#" class="grey-btn" id="btnShowExcel"><spring:message code="LAB.M05.LAB00049"/><!-- 미리보기 --></a>
			</div>
		</div>
	
		<div class="layer_box" id="excelUploadGrid1" style="display:none;">
			<table id="excelUploadTable1" class="w100p"></table>
			<div id="excelUploadPager1"></div>
		</div>
		
		<div class="layer_box" id="excelUploadGrid2" style="display:none;" >
			<table id="excelUploadTable2" class="w100p"></table>
			<div id="excelUploadPager2"></div>
		</div>
		
		<div class="layer_box" id="excelUploadGrid3" style="display:none;" >
			<table id="excelUploadTable3" class="w100p"></table>
			<div id="excelUploadPager3"></div>
		</div>
		
	</div>
	
	<div class="btn_box">
		
		<a class="grey-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00049"/><!-- 적용 --></span></a>
		<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>

</form>
<!--// center -->
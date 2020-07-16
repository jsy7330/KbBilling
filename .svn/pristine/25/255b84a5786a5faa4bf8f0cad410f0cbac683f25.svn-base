<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	var checkId = "N";		//ID중복체크
	var checkIdValue = ""	//체크한 ID값

	$(document).ready(function() {
		
		byteCheckKeyEvent();	//바이트체크
		
		$("#searchSoId").val("${session_user.soId}");
		$('#searchSoId').selectmenu("refresh");
		
		$('#mncoOtptUtPrc').number( true );
		
		tdHide();
		
		nonActiveSetting();
		nonActiveSetting2();
		nonActiveSetting3();
		
		initGrid();
		initGrid2();
		initGrid3();
		
		//사업 셀렉트 박스 체인지
		$('#searchSoId').selectmenu({
		    change: function() {
		    	$("#areaSearch input").val("");
		    }
		});
		
		//제품유형 셀렉트 박스 체인지 이벤트1
		$('#searchItemTpCd').selectmenu({
		    change: function() {
		    	changeItemTpCd("searchItemTpCd", "searchItemKndCd");
		    }
		});
		
		//제품유형 셀렉트 박스 체인지 이벤트2
		$('#itemTpCd').selectmenu({
		    change: function() {
		    	changeItemTpCd("itemTpCd", "itemKndCd");
		    }
		});
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();
		});
		
		//초기화
		$("#btn_init").click(function() {
			reset();
		});
		
		//등록
		$("#btn_insert").click(function() {
			insertData();
		});
		
		//수정
		$("#btn_update").click(function() {
			updateData();
		});
		
		//삭제
		$("#btn_delete").click(function() {
			deleteData();
		});
		
		//아이디 중복 체크
		$("#btnCheckId").click(function() {
			checkItemId();
		});
		
		//초기화
		$("#btn_init2").click(function() {
			reset2();
		});
		
		//등록
		$("#btn_insert2").click(function() {
			insertData2();
		});
		
		//수정
		$("#btn_update2").click(function() {
			updateData2();
		});
		
		//삭제
		$("#btn_delete2").click(function() {
			deleteData2();
		});
		
		//초기화
		$("#btn_init3").click(function() {
			reset3();
		});
		
		//등록
		$("#btn_insert3").click(function() {
			insertData3();
		});
		
		//수정
		$("#btn_update3").click(function() {
			
			var rowId  = $("#productInfoTable3").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M07.MSG00105" />');		//수정할 정보를 선택해 주세요.
				return;
			}

			updateData3();
		});
		
		//삭제
		$("#btn_delete3").click(function() {
			deleteData3();
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
			openModal(url, param);
		});
		
		//제조사검색 팝업
		$("#btnMnco").click(function() {
			var url="/system/common/common/manufacturerMng/manufacturerSearchPopup.ajax";
			var param = new Object();
			param.soId = $("#soId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "mncoId";	//리턴받는 Id 의 태그아이디값
			param.returnId2 = "mncoNm";	//리턴받는 Nm 의 태그아이디값
			
			$("#popup_dialog").css("width", 500);
			openModal(url, param);
		});
		
		
		//속성관리 팝업
		$("#btnPropertyMng").click(function() {
			var url="proPertyMngPopUp.ajax";
			var param = new Object();
			param.soId = $("#soId").val();
			
			$("#popup_dialog").css("width", 800);
			openModal(url, param);
		});
		
		//파일다운로드버튼
		$("#btnDownImgFileNm").click(function() {
			fileDownload("imgUuid", "imgFileNm");
		});
		
		$("#btnDownAttchFile").click(function() {
			fileDownload("attchUuid", "attchFileNm");
		});
		
	});
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		
		$("#productInfoTable").jqGrid({
			
		   	url:'productInfoListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'itemKndCd' , name: 'itemKndCd', hidden:true,width : 0},
				{ label: 'ctrtTpCd' , name: 'ctrtTpCd', hidden:true,width : 0},
				{ label: 'faceAmt' , name: 'faceAmt', hidden:true,width : 0},
				{ label: 'imgUuid' , name: 'imgUuid', hidden:true,width : 0},
				{ label: 'imgFileNm' , name: 'imgFileNm', hidden:true,width : 0},
				{ label: 'mncoId' , name: 'mncoId', hidden:true,width : 0},
				{ label: 'note' , name: 'note', hidden:true,width : 0},
				

	   		    { label: '<spring:message code="LAB.M07.LAB00003" />', name: 'soNm', width : 100},				//사업
	   		 	{ label: '<spring:message code="LAB.M09.LAB00098" />', name: 'mncoNm', width : 100},				//제조사
	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			//{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', width : 100},			//색상
	   			{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},		//제품유형
	   			{ label: '<spring:message code="LAB.M09.LAB00123" />', name: 'itemKndNm', width : 100},		//제품종류
	   			{ label: '<spring:message code="LAB.M01.LAB00039" />', name: 'ctrtTpNm', width : 100},			//계약유형
	   			{ label: '<spring:message code="LAB.M05.LAB00005" />', name: 'mncoOtptUtPrc', width : 100},	//매입단가
	   			{ label: '<spring:message code="LAB.M03.LAB00065" />', name: 'repItemId', width : 100},		//대표제품ID
	   			
			    
			    
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#productInfoPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 240,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        	setSelectedDate(rowId);
	        	
	        	var data = $("#productInfoTable").getRowData(rowId);
	        	setItemAttr(data.itemTpCd);
	        },
	        loadComplete: function(obj){
	        	$("#productInfoTable").setGridWidth($('#productInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        },
	        
	        sortable: { update: function(permutation) {
	        	$("#productInfoTable").setGridWidth($('#productInfoGrid').width(),false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
	        
		});
		
		$("#productInfoTable").setGridWidth($('#productInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#productInfoTable").setGridWidth($('#productInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function initGrid2() {
		
		var param = new Object();
		param.itemId = "0000000000000000000000";
		
		$("#productInfoTable2").jqGrid({
			
		   	url:'itemAttrMappListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'attrCd' , name: 'attrCd', hidden:true,width : 0},
				{ label: 'attrValCd' , name: 'attrValCd', hidden:true,width : 0},
				

	   		    { label: '<spring:message code="LAB.M06.LAB00050" />', name: 'mappOrd', width : 100},				//번호
	   		 	{ label: '<spring:message code="LAB.M07.LAB00214" />', name: 'attrNm', width : 100},			//속성
	   			{ label: '<spring:message code="LAB.M07.LAB00215" />', name: 'attrValNm', width : 100},		//속성값
	   		 	
	   			
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#productInfoPager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 100,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        	setSelectedDate2(rowId);
	        },
	        loadComplete: function(obj){
	        	$("#productInfoTable2").setGridWidth($('#productInfoGrid').width() * 0.47 ,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        },
	        
	        sortable: { update: function(permutation) {
	        	$("#productInfoTable2").setGridWidth($('#productInfoGrid').width() * 0.47, false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
	        
		});
		
		$("#productInfoTable2").setGridWidth($('#productInfoGrid').width() * 0.47 ,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#productInfoTable2").setGridWidth($('#productInfoGrid').width() * 0.47 ,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function initGrid3() {
		
		var param = new Object();
		param.itemId = "0000000000000000000000";
		
		$("#productInfoTable3").jqGrid({
			
		   	url:'itemDtlListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'attchUuid' , name: 'attchUuid', hidden:true,width : 0},
				{ label: 'attchFileNm' , name: 'attchFileNm', hidden:true,width : 0},
				

	   		    { label: '<spring:message code="LAB.M06.LAB00050" />', name: 'dtlInfoOrd', width : 100},				//번호
	   		 	{ label: '<spring:message code="LAB.M14.LAB00041" />', name: 'clNm', width : 100},			//항목
	   			{ label: '<spring:message code="LAB.M02.LAB00034" />', name: 'ttl', width : 100},		//내용
	   			{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'attchCt', width : 100},		//비고
	   		 	
	   			
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#productInfoPager3',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 100,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        	setSelectedDate3(rowId);
	        },
	        loadComplete: function(obj){
	        	$("#productInfoTable3").setGridWidth($('#productInfoGrid').width() * 0.47 ,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        },
	        
	        sortable: { update: function(permutation) {
	        	$("#productInfoTable3").setGridWidth($('#productInfoGrid').width() * 0.47 ,false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
		});
		
		$("#productInfoTable3").setGridWidth($('#productInfoGrid').width() * 0.47 ,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#productInfoTable3").setGridWidth($('#productInfoGrid').width() * 0.47 ,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	//그리드 재검색 
	function searchData(){
		
		$("#productInfoTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.searchType = $("#searchType").val();
		param.searchText = $("#searchText").val();
		param.mncoId = $("#searchMncoId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		param.itemKndCd = $("#searchItemKndCd").val();
		
        $("#productInfoTable").setGridParam({ postData: param }).trigger("reloadGrid");
        
        reset();
        $("#btnCheckId").hide();	//중복확인 버튼 
	}
	
	//그리드 재검색 
	function searchData2(){
		
		$("#productInfoTable2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.itemId = $("#itemId").val();
		
        $("#productInfoTable2").setGridParam({ postData: param }).trigger("reloadGrid");
	}
	
	//그리드 재검색 
	function searchData3(){
		
		$("#productInfoTable3").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.itemId = $("#itemId").val();
		
        $("#productInfoTable3").setGridParam({ postData: param }).trigger("reloadGrid");
	}
	
	//상세정보 
	function setSelectedDate(rowId){
		
		var data = $("#productInfoTable").getRowData(rowId);
		$("#soId").val(data.soId);
		$('#soId').selectmenu("refresh");
		
		$("#mncoId").val(data.mncoId);		
		$("#mncoNm").val(data.mncoNm);
		$("#itemId").val(data.itemId);		
		$("#itemNm").val(data.itemNm);
		$("#mncoOtptUtPrc").val(data.mncoOtptUtPrc);
		$("#mncoOtptUtPrcB").val(data.mncoOtptUtPrc);
		
		$("#itemTpCd").val(data.itemTpCd);
		$('#itemTpCd').selectmenu("refresh");
		
		
		changeItemTpCd("itemTpCd", "itemKndCd");
		
		$("#itemKndCd").val(data.itemKndCd);
		$('#itemKndCd').selectmenu("refresh");
		
		$("#ctrtTpCd").val(data.ctrtTpCd);
		$('#ctrtTpCd').selectmenu("refresh");
		
		$("#faceAmt").val(data.faceAmt);
		$("#repItemId").val(data.repItemId);
		
		$("#note").val(data.note);
		$("#imgUuid").val(data.imgUuid);
		$("#imgFileNm").val(data.imgFileNm);
		
		activeSetting();
		
		$("#soId").selectmenu("disable");
		
		$("#itemId").addClass('not-active');
		$("#itemId").attr('disabled', true);
		
		btnActive("btn_init");
		btnNonActive("btn_insert");
		btnActive("btn_update");
		btnActive("btn_delete");
		
		$("#btnCheckId").hide();	//중복확인 버튼 
		
		searchData2();
		searchData3();
		
	}
	
	//상세정보 
	function setSelectedDate2(rowId){
		
		var data = $("#productInfoTable2").getRowData(rowId);
		$("#attrCdTab2").val(data.attrCd);
		$('#attrCdTab2').selectmenu("refresh");
		
		$("#attrValCdTab2").val(data.attrValCd);
		$('#attrValCdTab2').selectmenu("refresh");
		
		$("#mappOrd").val(data.mappOrd);		
		
		
		btnActive("btn_init2");
		btnNonActive("btn_insert2");
		btnActive("btn_update2");
		btnActive("btn_delete2");
		
		activeSetting2();
		
	}
	
	//상세정보 
	function setSelectedDate3(rowId){
		
		var data = $("#productInfoTable3").getRowData(rowId);
		
		$("#dtlInfoOrd").val(data.dtlInfoOrd);
		$("#clNm").val(data.clNm);
		$("#ttl").val(data.ttl);
		$("#attchCt").val(data.attchCt);
		$("#attchUuid").val(data.attchUuid);
		$("#attchFileNm").val(data.attchFileNm);
		
		btnActive("btn_init3");
		btnNonActive("btn_insert3");
		btnActive("btn_update3");
		btnActive("btn_delete3");
		
		activeSetting3();
		
	}
	
	//등록
	function insertData(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidation("I");
		
		if(param != false){
			
			//파일업로드 
			if($("#ex_filename01").val() != ""){
				var uuid = fileUpload("ex_filename01");
				param.imgUuid = uuid;
			}
			
			$.ajax({
				url : 'insertProductInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();
						
						activeSetting();
						
						$("#itemId").addClass('not-active');
						$("#itemId").attr('disabled', true);
						
						$("#soId").selectmenu("disable");
						
						btnActive("btn_init");
						btnNonActive("btn_insert");
						btnActive("btn_update");
						btnActive("btn_delete");
					}
				},
			    error: function(e){
			        
			        ajaxErrorCallback(e);
			    },
				complete : function() {
				}
			});
		}
	}
	
	//수정
	function updateData(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidation("U");
		
		if(param != false){
			
			//파일업로드 
			if($("#ex_filename01").val() != ""){
				var uuid = fileUpload("ex_filename01");
				param.imgUuid = uuid;
			}
			
			$.ajax({
				url : 'updateProductInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();
						
						activeSetting();
						
						$("#itemId").addClass('not-active');
						$("#itemId").attr('disabled', true);
						
						$("#soId").selectmenu("disable");
						
						btnActive("btn_init");
						btnNonActive("btn_insert");
						btnActive("btn_update");
						btnActive("btn_delete");
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
	
	//삭제
	function deleteData(){
		
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');

		if(check){
			
			var param = new Object();
			param.itemId = $("#itemId").val();		//제품ID
			param.soId = $("#soId").val();			//사업ID
			
			$.ajax({
				url : 'deleteProductInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00053" />');	//삭제되었습니다.
						searchData();
						nonActiveSetting();
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
	
	//밸리데이션 체크
	function checkValidation(type){
		var param = new Object();
		
		param.soId = $("#soId").val();	//사업
		if(param.soId == ''){
			alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}
		param.mncoId = $("#mncoId").val();	//제조사
		if(param.mncoId == ''){
			alert('<spring:message code="LAB.M09.LAB00098" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}
		param.itemId = $("#itemId").val();	//제품ID
		if(param.itemId == ''){
			//제품ID를 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		if(type == "I"){
			if(checkId == 'N' || param.itemId != checkIdValue){
				alert('아이디 중복 체크를 해주세요.');
				return false;
			}
		}else{
			if($("#mncoOtptUtPrc").val() == $("#mncoOtptUtPrcB").val()){
				param.checkMncoOtptUtPrc = "Y";
			}else{
				param.checkMncoOtptUtPrc = "N";
			}
		}

		param.itemNm = $("#itemNm").val();	//제품명
		if(param.itemNm == ''){
			//제품명을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00111" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		param.mncoOtptUtPrc = $("#mncoOtptUtPrc").val();	//매입단가
		if(param.mncoOtptUtPrc == ''){
			//매입단가를 입력해 주세요
			alert('<spring:message code="LAB.M05.LAB00005" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		param.itemTpCd = $("#itemTpCd").val();	//제품유형
		if(param.itemTpCd == ''){
			//제품유형을 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00121" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}
		param.faceAmt = $("#faceAmt").val();	//바우쳐액면가
		param.itemKndCd = $("#itemKndCd").val();	//제품종류
		if(param.itemKndCd == ''){
			//제품종류를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00123" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}
		param.ctrtTpCd = $("#ctrtTpCd").val();	//계약유형
		if(param.ctrtTpCd == ''){
			//계약유형를 선택해 주세요
			alert('<spring:message code="LAB.M01.LAB00039" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}
		param.repItemId = $("#repItemId").val();	//대표제품ID
		if(param.repItemId == ''){
			//대표제품ID를 입력해 주세요
			alert('<spring:message code="LAB.M03.LAB00065" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.faceAmt = $("#faceAmt").val();	//바우쳐액면가
		if(param.faceAmt == ''){
			param.faceAmt = "0";
		}
		param.note = $("#note").val();	//비고
		param.imgFileNm = $("#imgFileNm").val();	//대표 이미지파일
		param.imgUuid = $("#imgUuid").val();	//대표 이미지파일
		
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		param.eftStrtDt = yyyymmdd;		//효력시작일자
		param.eftEndDt = "99991231";	//효력종료일자
		
		return param;
	}
	
	//아이디 중복체크
	function checkItemId(){
		
		var param = new Object();
		param.itemId = $.trim( $("#itemId").val() );
		var idCount = ""; 
		
		if(param.itemId.length < 1){
			alert("ID를  입력해 주세요");
			return;
		}
		
		$.ajax({
			url : 'checkItemId.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				
				idCount = data.idCount;
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
		if(idCount != "0"){	//중복
			checkId = "N";
			alert('<spring:message code="MSG.M15.MSG00024" />');	//ID가 중복 되었습니다.
		}else{
			checkId = "Y";
			checkIdValue = $.trim( $("#itemId").val() );
			alert('<spring:message code="MSG.M07.MSG00010" />');	//사용가능한 ID 입니다.
		}
	}
	
	
	//등록
	function insertData2(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidation2("I");
		
		if(param != false){
			
			$.ajax({
				url : 'insertItemAttrMapp.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData2();

						activeSetting2();
						
						btnActive("btn_init2");
						btnNonActive("btn_insert2");
						btnActive("btn_update2");
						btnActive("btn_delete2");
						
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
	
	//수정
	function updateData2(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidation2("U");
		
		if(param != false){
			
			$.ajax({
				url : 'updateItemAttrMapp.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData2();
						
						activeSetting2();
						
						
						btnActive("btn_init2");
						btnNonActive("btn_insert2");
						btnActive("btn_update2");
						btnActive("btn_delete2");
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
	
	//삭제
	function deleteData2(){
		
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');

		if(check){
			
			var param = new Object();
			param.itemId = $("#itemId").val();		//제조사ID
			param.mappOrd = $("#mappOrd").val();	//매핑순번
			
			$.ajax({
				url : 'deleteProductInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00053" />');	//삭제되었습니다.
						searchData2();
						nonActiveSetting2();
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
	
	//밸리데이션 체크
	function checkValidation2(type){
		var param = new Object();
		
		param.soId = $("#soId").val();			//사업
		param.itemId = $("#itemId").val();		//제품ID
		
		if(param.itemId == ''){
			//제품ID를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.mappOrd = $("#mappOrd").val();	//매핑순번
		param.attrCd = $("#attrCdTab2").val();	//속성코드
		if(param.attrCd == ''){
			//제품ID를 선택해 주세요
			alert('<spring:message code="LAB.M07.LAB00232" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		param.attrValCd = $("#attrValCdTab2").val();	//속성값코드
		if(param.attrValCd == ''){
			//제품ID를 선택해 주세요
			alert('<spring:message code="LAB.M07.LAB00217" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		return param;
	}
	
	
	//등록
	function insertData3(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidation3("I");
		
		if(param != false){
			
			//파일업로드 
			if($("#ex_filename02").val() != ""){
				var uuid = fileUpload("ex_filename02");
				param.attchUuid = uuid;
			}
			
			$.ajax({
				url : 'insertItemDtl.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData3();

						activeSetting3();
						
						btnActive("btn_init3");
						btnNonActive("btn_insert3");
						btnActive("btn_update3");
						btnActive("btn_delete3");
						
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
	
	//수정
	function updateData3(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		//$("#dtlInfoOrd").val();	//상세정보순번
		if($("#dtlInfoOrd").val() == ''){
			alert(1111);
			return;
		}
		
		var param = checkValidation3("U");
		
		if(param != false){
			
			//파일업로드 
			if($("#ex_filename02").val() != ""){
				var uuid = fileUpload("ex_filename02");
				param.attchUuid = uuid;
			}
			
			$.ajax({
				url : 'updateItemDtl.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData3();
						
						activeSetting3();
						
						
						btnActive("btn_init3");
						btnNonActive("btn_insert3");
						btnActive("btn_update3");
						btnActive("btn_delete3");
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
	
	//삭제
	function deleteData3(){
		
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');

		if(check){
			
			var param = new Object();
			param.itemId = $("#itemId").val();		//제조사ID
			param.dtlInfoOrd = $("#dtlInfoOrd").val();	//매핑순번
			
			$.ajax({
				url : 'deleteItemDtl.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00053" />');	//삭제되었습니다.
						searchData3();
						nonActiveSetting3();
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
	
	//밸리데이션 체크
	function checkValidation3(type){
		var param = new Object();
		
		param.soId = $("#soId").val();			//사업
		param.itemId = $("#itemId").val();		//제품ID
		
		if(param.itemId == ''){
			//제품ID를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.dtlInfoOrd = $("#dtlInfoOrd").val();	//상세정보순번
		param.clNm = $("#clNm").val();	//항목
		if(param.clNm == ''){
			//itemA을 입력해 주세요
			alert('<spring:message code="LAB.M14.LAB00041" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		param.ttl = $("#ttl").val();	//내용
		if(param.ttl == ''){
			//내용을 입력해 주세요
			alert('<spring:message code="LAB.M02.LAB00034" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}

		param.attchCt = $("#attchCt").val();	//비고
		param.attchFileNm = $("#attchFileNm").val();	//첨부파일명
		param.attchUuid = $("#attchUuid").val();	//첨부파일아이디
		
		return param;
	}
	
	function nonActiveSetting(){
		
		$("#tab1 input").addClass('not-active');
		$("#tab1 input").attr('disabled', true);
		
		$("#tab1 select").selectmenu("disable");
		
		btnActive("btn_init");
		btnNonActive("btn_insert");
		btnNonActive("btn_update");
		btnNonActive("btn_delete");
		
		$('#btnMnco').addClass('not-active');
		$('#btnMnco').attr('disabled',true);
		
		btnTdNonActive("btnCheckId");	//비 활성화
		btnTdNonActive("btnDownImgFileNm");	//비 활성화
		
		
		$("#btnCheckId").hide();	//중복확인 버튼 
	}
	
	function activeSetting(){
		$("#tab1 input").removeClass('not-active');
		$("#tab1 input").removeAttr('disabled');
		
		$("#tab1 select").selectmenu("enable");
		
		$("#mncoNm").addClass('not-active');
		$("#mncoNm").attr('disabled', true);
		
		$("#imgFileNm").addClass('not-active');
		$("#imgFileNm").attr('disabled', true);
		
		$('#btnMnco').removeClass('not-active');
		$('#btnMnco').removeAttr('disabled',true);
		
		btnTdActive("btnCheckId");	//활성화
		
		btnTdActive("btnDownImgFileNm");	//활성화
		
	}
	
	function activeSetting2(){
		
		$("#areaTab2 select").selectmenu({});
		$("#areaTab2 select").selectmenu("enable");
		
		$('#attrCdTab2').selectmenu({
		    change: function() {
		    	setItemAttrVal();
		    }
		});
		
	}
	
	function activeSetting3(){
		
		$("#areaTab3 input").removeClass('not-active');
		$("#areaTab3 input").removeAttr('disabled');
		
		$("#attchCt").removeClass('not-active');
		$("#attchCt").removeAttr('disabled');
		
		$("#attchFileNm").addClass('not-active');
		$("#attchFileNm").attr('disabled', true);
		
	}
	
	function reset(){
		
		activeSetting();
		
		$("#tab1 input").val("");
		$("#tab1 select").val("");
		$("#tab1 select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		btnActive("btn_insert");	//등록버튼 활성화
		btnNonActive("btn_update");	//수정버튼 비 활성화
		btnNonActive("btn_delete");	//삭제버튼 비 활성화
		
		$("#btnCheckId").show();	//중복확인 버튼 
	}
	
	function nonActiveSetting2(){
		
		$("#areaTab2 select").selectmenu("disable");
		
		btnActive("btn_init2");
		btnNonActive("btn_insert2");
		btnNonActive("btn_update2");
		btnNonActive("btn_delete2");
		
	}
	
	function reset2(){
		
		$("#areaTab2 select").selectmenu({});
		$("#areaTab2 select").selectmenu("enable");
		
		$('#attrCdTab2').selectmenu({
		    change: function() {
		    	setItemAttrVal();
		    }
		});
		
		btnActive("btn_insert2");	//등록버튼 활성화
		btnNonActive("btn_update2");	//수정버튼 비 활성화
		btnNonActive("btn_delete2");	//삭제버튼 비 활성화
		
	}
	
	function nonActiveSetting3(){
		
		$("#areaTab3 input").addClass('not-active');
		$("#areaTab3 input").attr('disabled', true);
		
		$("#attchCt").addClass('not-active');
		$("#attchCt").attr('disabled', true);
		
		btnActive("btn_init3");
		btnNonActive("btn_insert3");
		btnNonActive("btn_update3");
		btnNonActive("btn_delete3");
		
	}
	
	function reset3(){
		
		$("#areaTab3 input").val("");
		$("#attchCt").val("");
		
		$("#areaTab3 input").removeClass('not-active');
		$("#areaTab3 input").removeAttr('disabled');
		
		$("#attchCt").removeClass('not-active');
		$("#attchCt").removeAttr('disabled');
		
		$("#attchFileNm").addClass('not-active');
		$("#attchFileNm").attr('disabled', true);
		
		btnActive("btn_insert3");	//등록버튼 활성화
		btnNonActive("btn_update3");	//수정버튼 비 활성화
		btnNonActive("btn_delete3");	//삭제버튼 비 활성화
		
	}
	
	function btnActive(id){
		$('#'+id).addClass('grey-btn');
		$('#'+id).removeClass('white-btn');
		$('#'+id).removeClass('not-active');
		$('#'+id).removeAttr('disabled');
	}
	
	function btnNonActive(id){
		$('#'+id).addClass('white-btn');
		$('#'+id).removeClass('grey-btn');
		$('#'+id).addClass('not-active');
		$('#'+id).attr('disabled',true);
	}
	
	function btnTdActive(id){
		$('#'+id).addClass('td-grey-btn');
		$('#'+id).removeClass('td-btn');
		$('#'+id).removeClass('not-active');
		$('#'+id).removeAttr('disabled');
	}
	
	function btnTdNonActive(id){
		$('#'+id).addClass('td-grey-btn');
		$('#'+id).removeClass('td-btn');
		$('#'+id).addClass('not-active');
		$('#'+id).attr('disabled',true);
	}
	
	function tdShow(){
		$("#th01").show();
		$("#td01").show();
		$("#td02").attr("colspan", "1");
	}
	
	function tdHide(){
		$("#th01").hide();
		$("#td01").hide();
		$("#td02").attr("colspan", "3");
	}
	
	//유형 셀렉트 박스 체인지
	function changeItemTpCd(id1, id2){
		
		
		var param = new Object();
		var grpCd = "DN00064";
		var ref1 = $("#"+id1).val();
		
		if(id1 == "searchItemTpCd" && ref1 == ""){
			
			param.code = grpCd;
			$.ajax({
				url : 'getCommonCodeListAction.json',
				type : 'POST',
				data : param,
				async: false,
				success : function(data) {
					
					var sHtml = '';
					sHtml = sHtml + '<option value=""><spring:message code="LAB.M15.LAB00002"/></option>';
					
					$.each(data.itemKndCd, function(index,item){
						sHtml = sHtml + '<option value="'+item.commonCd+'">'+item.commonCdNm+'</option>';						
					}); 
					
					$("#"+id2).html("");
					$("#"+id2).html(sHtml);
					
					$("#"+id2 ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
			
			return;
		}
		
		
		
		if(id1 == "itemTpCd" && ref1 == "V"){
			tdShow();
		}else{
			tdHide();
		}
		$("#faceAmt").val("");
		
		
		param.grpCd = grpCd;
		param.ref1 = ref1;
		
		var sHtml = '';
		sHtml = sHtml + '<option value=""><spring:message code="LAB.M15.LAB00002"/></option>';
		
		if(ref1 == ''){
			$("#"+id2).html("");
			$("#"+id2).html(sHtml);
			
			$("#"+id2).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			
		}else{
			
			$.ajax({
				url : '/system/common/common/organizationMng/getCommonCodeListByRef1ListAction.json',
				type : 'POST',
				data : param,
				async: false,
				success : function(data) {
					
					$.each(data.codeList, function(index,item){
						sHtml = sHtml + '<option value="'+item.commonCd+'">'+item.commonCdNm+'</option>';						
					}); 
					
					$("#"+id2).html("");
					$("#"+id2).html(sHtml);
					
					$("#"+id2 ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
			
		}
		
	}
	
	
	function setItemAttr(attrClCd){
		
		var param = new Object();
		param.attrClCd = attrClCd;
		
		$.ajax({
			url : 'itemAttrSelectListAction.json',
			type : 'POST',
			data : param,
			success : function(data) {
				
				var sHtml = "";
				sHtml = sHtml + '<option value=""><spring:message code="LAB.M15.LAB00002"/></option>';
				$.each(data.itemAttrList, function(index,item){
					sHtml = sHtml + '<option value="'+item.attrCd+'">'+item.attrNm+'</option>';						
				}); 
				
				$("#attrCdTab2").html("");
				$("#attrCdTab2").html(sHtml);
				
				$("#attrCdTab2").selectmenu("refresh");	//셀렉트 박스 수정후 리플레쉬
				
				setItemAttrVal();
			
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
	}
	
	function setItemAttrVal(){
		
		var param = new Object();
		param.attrCd = $("#attrCdTab2").val();
		
		$.ajax({
			url : 'itemAttrValSelectListAction.json',
			type : 'POST',
			data : param,
			success : function(data) {
				var sHtml = "";
				sHtml = sHtml + '<option value=""><spring:message code="LAB.M15.LAB00002"/></option>';
				$.each(data.itemAttrValList, function(index,item){
					sHtml = sHtml + '<option value="'+item.attrValCd+'">'+item.attrValNm+'</option>';						
				}); 
				
				$("#attrValCdTab2").html("");
				$("#attrValCdTab2").html(sHtml);
				
				$("#attrValCdTab2").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
	}
	
	function fileUpload(fileId){
		
		var formData = new FormData();
		formData.append("fileName",$("input[name=" + fileId + "]")[0].files[0]);
		var uuid = "";
		$.ajax({
			async : false,
			method : "post",
			url : '/system/common/common/fileMng/fileInsertAction.json',
			processData : false, //true : data의 파일형태가 query String으로 전송. false : non-processed data 
			data : formData,
			contentType : false, // false : multipart/form-data 형태로 전송되기 위한 옵션값
			success : function(data){
				
				uuid = data.uuid;
			}
		});
		
		return uuid;
		
	}
	
	function fileDownload(uuidId, fileNmId){
		
		var uuid = $("#"+uuidId).val();
		var fileNm = $("#"+fileNmId).val();
		
		if(uuid == '' || fileNm == ''){
			alert('<spring:message code="MSG.M03.MSG00004" />');
			return;
		}
		
		//location.href = '/system/common/common/fileMng/getFileAction.json?uuid='+uuid+'&fileNm='+fileNm;
		location.href = '/system/common/common/fileMng/getFileAction.json?uuid='+uuid+'&fileNm='+encodeURI(encodeURIComponent(fileNm));
	}
	
	//팝업창 modal로 열기
	function openModal(url, param) {
		
		//popType m : 모달팝어 o : 일반팝업
		$.ajax({
			type : "post",
			url : url,
			data : param,
			async: true,
			success : function(data) {
				var html = data;
				$("#popup_dialog").html(html);
			},		
			complete : function(){
				wrapWindowByMask(); // 팝업 오픈
			}
		}); 
		
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
	
<form id="manufactureForm" name="manufactureForm" method="post" enctype="multipart/form-data">

	<table class="writeview" id="areaSearch" >
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<thead> 
			<tr>
				<th  title="latest" ><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td colspan="3">
					<select class="w150" id="searchSoId" name="searchSoId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --></th>
				<td>
					<div class="inp_date w280">
						<input type="text" id="searchMncoNm" name="searchMncoNm" class="w250" disabled="disabled" />
						<input type="hidden" id="searchMncoId" name="searchMncoId" />
						<a href="#" id="btnSearchMnco" name="btnSearchMnco" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div> 
				</td>
				<th><spring:message code="LAB.M09.LAB00159"/><!-- 조회구분 --></th>
				<td>
					<div class="date_box">
						<div class="select_box w150">
							<select class=" sel" id="searchType" name="searchType" >
								<option value="01" >Product ID</option>
								<option value="02" >Product Name</option>
							</select>                                                
						</div>
						<div class="inp_date w200">
							<input type="text" id="searchText" name="searchText" class="w160" />
						</div> 
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
				<th><spring:message code="LAB.M09.LAB00123"/><!-- 제품종류 --></th>
				<td>
					<select id="searchItemKndCd" name="searchItemKndCd" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${itemKndCd}" var="itemKndCd" varStatus="status">
							<option value="${itemKndCd.commonCd}">${itemKndCd.commonCdNm}</option>
						</c:forEach>
					</select>                                          
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M09.LAB00112"/><!-- 제품목록 --></h4>
		</div>
	</div>
	
	<div id="productInfoGrid">
		<table id="productInfoTable" class="w100p"></table>
		<div id="productInfoPager"></div>
	</div>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00101"/><!-- 상세정보 --></h4>
		</div>
	</div>
	
	<!-- tab -->
	<ul class="tabs">
		<li rel="tab1" class="active" title="<spring:message code='LAB.M01.LAB00217'/>"><spring:message code="LAB.M01.LAB00217"/><!-- 기본정보 --></li>
		<li rel="tab2" title="<spring:message code='LAB.M07.LAB00002'/>"><spring:message code="LAB.M07.LAB00002"/><!-- 사양정보 --></li>
		<li rel="tab3" title="<spring:message code='LAB.M07.LAB00101'/>"><spring:message code="LAB.M07.LAB00101"/><!-- 상세정보 --></li>
	</ul>
	<!--// tab -->

	<div class="tab_box h280">
	<!-- tab con1 -->
		<div id="tab1" class="tab_content">
			<table class="writeview">
				<colgroup>
					<col style="width:10%;">
					<col style="width:25%;">
					<col style="width:10%;">
					<%-- <col style="width:40%;"> --%>
					<col style="width:26%;">
					<col style="width:10%;">
					<col style="width:19%;">
				</colgroup>
				<tbody> 
					<tr>
						<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span></th>
						<td>
							<select class="w150" id="soId" name="soId">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
									<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
								</c:forEach>
							</select>
						</td>
						<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --><span class="dot">*</span></th>
						<td colspan="3" >                                                
							<div class="inp_date w280">
								<input type="text" id="mncoNm" name="mncoNm" class="w250" />
								<input type="hidden" id="mncoId" name="mncoId"  />
								<a href="#" id="btnMnco" name="btnMnco" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
							</div> 
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M09.LAB00106"/><!-- 제품ID --><span class="dot">*</span></th>
						<td>
							<input type="text" id="itemId" name="itemId" class="w200" checkbyte="20" />
							<a href="#" id="btnCheckId" name="btnCheckId" class="td-grey-btn"><spring:message code="LAB.M09.LAB00193"/><!-- 중복체크 --></a>
						</td>
						<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --><span class="dot">*</span></th>
						<td colspan="3">
							<input type="text" id="itemNm" name="itemNm" class="w270" checkbyte="120" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M05.LAB00005"/><!-- 매입단가 --><span class="dot">*</span></th>
						<td>
							<input type="text" id="mncoOtptUtPrc" name="mncoOtptUtPrc" class="w270 txt-r" />
							<input type="hidden" id="mncoOtptUtPrcB" name="mncoOtptUtPrcB"  />
						</td>
						<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --><span class="dot">*</span></th>
						<td id="td02">
							<select name="select" id="itemTpCd" name="itemTpCd" class="w270">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
									<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
								</c:forEach>
							</select>
						</td>
						<th id="th01"><spring:message code="LAB.M06.LAB00014"/><!-- 바우쳐액면가 --></th>
						<td id="td01">
							<input type="text" id="faceAmt" name="faceAmt" class="w190" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M09.LAB00123"/><!-- 제품종류 --><span class="dot">*</span></th>
						<td>
							<select name="select" id="itemKndCd" name="itemKndCd" class="w270">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<c:forEach items="${itemKndCd}" var="itemKndCd" varStatus="status">
									<option value="${itemKndCd.commonCd}">${itemKndCd.commonCdNm}</option>
								</c:forEach>
							</select>
						</td>
						<th><spring:message code="LAB.M01.LAB00039"/><!-- 계약유형 --><span class="dot">*</span></th>
						<td colspan="3">
							<select name="select" id="ctrtTpCd" name="ctrtTpCd" class="w270">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<c:forEach items="${ctrtTpCd}" var="ctrtTpCd" varStatus="status">
									<option value="${ctrtTpCd.commonCd}">${ctrtTpCd.commonCdNm}</option>
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<th><spring:message code="LAB.M03.LAB00065"/><!-- 대표제품ID --><span class="dot">*</span></th>
						<td>
							<input type="text" id="repItemId" name="repItemId" class="w270" checkbyte="20" />
						</td>
						<%-- 
						<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --><span class="dot"></span></th>
						<td colspan="3">
							<input type="text" id="note" name="note" class="w270" checkbyte="4000" />
						</td>
						 --%>
						<th><spring:message code="LAB.M03.LAB00059"/><!-- 대표 이미지파일 --><span class="dot"></span></th>
						<td colspan="3">
							<span class="filebox bs3-primary">
								<input class="upload-name w210" id="imgFileNm" name="imgFileNm" placeholder="<spring:message code='LAB.M13.LAB00002'/>" />
								<input type="hidden" id="imgUuid" name="imgUuid" />
								<label for="ex_filename01"><spring:message code="LAB.M08.LAB00009"/><!-- 업로드 --></label> 
								<input type="file" id="ex_filename01" name="ex_filename01" class="upload-hidden"> 
							</span>                                            	
							<a href="#" id="btnDownImgFileNm" name="btnDownImgFileNm" class="td-btn"><spring:message code="LAB.M03.LAB00004"/><!-- 다운로드 --></a>
						</td> 
						 
					</tr>
					<tr>
						<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --><span class="dot"></span></th>
						<td colspan="5">
							<textarea id="note" name="note" class="w100p h50" checkbyte="4000"></textarea>
						</td>
					<%-- 
						<th><spring:message code="LAB.M03.LAB00059"/><!-- 대표 이미지파일 --><span class="dot"></span></th>
						<td colspan="5">
							<span class="filebox bs3-primary">
								<input class="upload-name w210" id="imgFileNm" name="imgFileNm" placeholder="<spring:message code='LAB.M13.LAB00002'/>" />
								<input type="hidden" id="imgUuid" name="imgUuid" />
								<label for="ex_filename01"><spring:message code="LAB.M08.LAB00009"/><!-- 업로드 --></label> 
								<input type="file" id="ex_filename01" name="ex_filename01" class="upload-hidden"> 
							</span>                                            	
							<a href="#" id="btnDownImgFileNm" name="btnDownImgFileNm" class="td-btn"><spring:message code="LAB.M03.LAB00004"/><!-- 다운로드 --></a>
						</td>
						 --%>
					</tr>
				</tbody>
			</table>
			<div class="main_btn_box">
				<div class="fr">
					<!--초기화-->		
					<a class="grey-btn" href="#" id="btn_init"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
					<!--등록-->
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
					<%-- 
					<!--출력-->
					<ntels:auth auth="${menuAuthP}">
						<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
					</ntels:auth>
					 --%>
				</div>
			</div>
		</div>
		<!--// tab con1 -->
		<!-- tab con2 -->
		<div id="tab2" class="tab_content">
			<div class="wite_box">
				
				<div class="lu_left">
				
					<div id="productInfoGrid2"  class="w100p">
						<table id="productInfoTable2" class="w100p"></table>
						<div id="productInfoPager2"></div>
					</div>
					
				</div>
				
				<div class="lu_right" id="areaTab2">
					<table class="writeview">
						<colgroup>
							<col style="width:10%;">
							<col style="width:90%;">
						</colgroup>
						<tbody> 
							<tr>
								<th><spring:message code="LAB.M07.LAB00214"/><!-- 속성 --><span class="dot">*</span></th>
								<td>
									<input type="hidden" id="mappOrd" name="mappOrd" />
									<select  id="attrCdTab2" name="attrCdTab2" class="w270">
										<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
									</select>
								</td>
							</tr>
							<tr>
								<th><spring:message code="LAB.M07.LAB00215"/><!-- 속성값 --><span class="dot">*</span></th>
								<td>
									<select  id="attrValCdTab2" name="attrValCdTab2" class="w270">
										<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="main_btn_box">
				<div class="fl">
					<a class="common-btn" title="<spring:message code="LAB.M07.LAB00218"/>" href="#" id="btnPropertyMng" name="btnPropertyMng"  ><spring:message code="LAB.M07.LAB00218"/><!-- 속성관리 --></a>
				</div>
				<div class="fr">
					<!--초기화-->		
					<a class="grey-btn" href="#" id="btn_init2"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
					<!--등록-->
					<ntels:auth auth="${menuAuthC}">
						<a class="grey-btn" href="#"  id="btn_insert2" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
					</ntels:auth>		
					<!--수정-->
					<ntels:auth auth="${menuAuthU}">
						<a class="grey-btn" href="#" id="btn_update2" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
					</ntels:auth>
					<!--삭제-->
					<ntels:auth auth="${menuAuthD}">
						<a class="grey-btn" href="#" id="btn_delete2" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
					</ntels:auth>
					<%-- 
					<!--출력-->
					<ntels:auth auth="${menuAuthP}">
						<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
					</ntels:auth>
					 --%>
				</div>
			</div>
		</div>
		<!--// tab con2 -->
		<!-- tab con3 -->
		<div id="tab3" class="tab_content">`
			<div class="wite_box">
				<div class="lu_left">
				
					<div id="productInfoGrid3">
						<table id="productInfoTable3" class="w100p"></table>
						<div id="productInfoPager3"></div>
					</div>
					
				</div>
				<div class="lu_right" id="areaTab3">
					<table class="writeview">
						<colgroup>
							<col style="width:10%;">
							<col style="width:90%;">
						</colgroup>
						<tbody> 
							<tr>
								<th><spring:message code="LAB.M14.LAB00041"/><!-- 항목 --></th>
								<td>
									<input type="text" id="clNm" name="clNm" class="w270" checkbyte="20" />
									<input type="hidden" id="dtlInfoOrd" name="dtlInfoOrd"  />
								</td>
							</tr>
							<tr>
								<th><spring:message code="LAB.M02.LAB00034"/><!-- 내용 --></th>
								<td>
									<input type="text" id="ttl" name="ttl" class="w270" checkbyte="450" />
								</td>
							</tr>
							<tr>
								<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --></th>
								<td>
									<textarea id="attchCt" name="attchCt"  class="w100p h50" checkbyte="4000" ></textarea>
								</td>
							</tr>
							<tr>
								<th><spring:message code="LAB.M10.LAB00029"/><!-- 첨부파일 --></th>
								<td>
									<span class="filebox bs3-primary">
										<input class="upload-name w210" value="" id="attchFileNm" name="attchFileNm"  placeholder="<spring:message code='LAB.M13.LAB00002'/>" >
										<input type="hidden" id="attchUuid" name="attchUuid"  />
										<label for="ex_filename02"><spring:message code="LAB.M08.LAB00009"/><!-- 업로드 --></label> 
										<input type="file" id="ex_filename02" name="ex_filename02" class="upload-hidden"> 
									</span>                                            	
									<a href="#" id="btnDownAttchFile" name="btnDownAttchFile" class="td-btn"><spring:message code="LAB.M03.LAB00004"/><!-- 다운로드 --></a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="main_btn_box">
				<div class="fr">
					<!--초기화-->		
					<a class="grey-btn" href="#" id="btn_init3"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
					<!--등록-->
					<ntels:auth auth="${menuAuthC}">
						<a class="grey-btn" href="#"  id="btn_insert3" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
					</ntels:auth>		
					<!--수정-->
					<ntels:auth auth="${menuAuthU}">
						<a class="grey-btn" href="#" id="btn_update3" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
					</ntels:auth>
					<!--삭제-->
					<ntels:auth auth="${menuAuthD}">
						<a class="grey-btn" href="#" id="btn_delete3" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
					</ntels:auth>
					<%-- 
					<!--출력-->
					<ntels:auth auth="${menuAuthP}">
						<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
					</ntels:auth>
					 --%>
				</div>
			</div>
		</div>
		<!--// tab con3 -->
	</div>

</form>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>

	
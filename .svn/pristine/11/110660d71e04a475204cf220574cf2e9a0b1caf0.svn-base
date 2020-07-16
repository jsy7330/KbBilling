<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	var checkAttrCd = "N";			//제품속성코드 체크
	var checkAttrCdValue = ""		//베품속성코드 체크한 ID값
	var checkAttrValCd = "N";		//제품속성값코드 체크
	var checkAttrValCdValue = ""	//제품속성값코드 체크한 ID값
	var selectTab = "1";		//선택된 탭 1, 2
	
	$(document).ready(function() {
		
		byteCheckKeyEvent();	//바이트체크
		
		$('#priNoLeft').number( true );
		$('#priNoRight').number( true );
		
		initGrid();
		
		//셀렉트 박스 세팅
		$('#attrClCdLeft').selectmenu({});
		$('#useYnLeft').selectmenu({});
		$('#commCdRefLeft').selectmenu({});
		$('#useYnRight').selectmenu({});
		
		//초기화면 버튼
		leftBtnNonActive();
		rigthBtnNonActive();
		leftBtnActive();
		
		//초기화면 인풋
		leftInputNonActive();
		rightInputNonActive();
		
		//제품속성값관리 버튼
		$("#btnAttrP1").click(function() {
			selectTab = "2";
			leftBtnNonActive();
			leftInputNonActive()
			rigthBtnActive();
			$("#btnAttrCd").hide();	//중복확인버튼
			$("#btnAttrValCd").hide();	//중복확인버튼
		});
		
		//제품속성관리 버튼
		$("#btnAttrP2").click(function() {
			selectTab = "1";
			rigthBtnNonActive();
			rightInputNonActive()
			leftBtnActive();
			$("#btnAttrCd").hide();	//중복확인버튼
			$("#btnAttrValCd").hide();	//중복확인버튼
		});
		
		//왼쪽화면 초기화
		$("#btn_initP1").click(function() {
			leftReset();
			
		});
		
		//속성등록
		$("#btn_insertP1").click(function() {
			insertDataP1();
		});
		
		//속성수정
		$("#btn_updateP1").click(function() {
			updateDataP1();
		});
		
		//속성삭제
		$("#btn_deleteP1").click(function() {
			deleteDataP1();
		});
		
		
		//오른쪽화면 초기화
		$("#btn_initP2").click(function() {
			rightReset()
			
		});
		
		//속성값등록
		$("#btn_insertP2").click(function() {
			insertDataP2();
		});
		
		//속성값수정
		$("#btn_updateP2").click(function() {
			updateDataP2();
		});
		
		//속성값삭제
		$("#btn_deleteP2").click(function() {
			deleteDataP2();
		});
		
		//제품속성 중복 체크
		$("#btnAttrCd").click(function() {
			fnCheckAttrCd();
		});
	
		//제품속성값 중복 체크
		$("#btnAttrValCd").click(function() {
			fnCheckAttrValCd();
		});
		
		
	});
	
	
	function initGrid() {
		
		var param = new Object();
		
		$("#proPertyMngTable").jqGrid({
	
		   	url:'itemAttrListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
		   	
		   	colModel: [ 
		   	           
				{ label: 'attrClCd' , name: 'attrClCd', hidden:true,width : 0},
				{ label: 'priNo' , name: 'priNo', hidden:true,width : 0},
				{ label: 'useYn' , name: 'useYn', hidden:true,width : 0},
				{ label: 'commCdRef' , name: 'commCdRef', hidden:true,width : 0},	
				

	   		    { label: '<spring:message code="LAB.M07.LAB00232" />', name: 'attrCd', width : 100},		//속성코드
	   		 	{ label: '<spring:message code="LAB.M07.LAB00220" />', name: 'attrNm', width : 100},		//속성명
	   			{ label: '<spring:message code="LAB.M07.LAB00219" />', name: 'attrClNm', width : 100},		//속성구분
	   			
			    				    
	   		],
			   		
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#proPertyMngPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
		    	selectSepP1(rowId);
		    	searchDataP2($("#proPertyMngTable").getRowData(rowId).attrCd);
		    }, 
		    loadComplete: function(){
		    	$("#proPertyMngTable").setGridWidth(368,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    	initGrid2("0000");
		    },
		    
		    sortable: { update: function(permutation) {
	        	$("#proPertyMngTable").setGridWidth(368,false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
		    
		});
		
		
		$("#proPertyMngTable").setGridWidth(368,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	//그리드 재검색 
	function searchDataP1(){
		
		$("#proPertyMngTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		
        $("#proPertyMngTable").setGridParam({ postData: param }).trigger("reloadGrid");
	}
	
	function selectSepP1(rowId){
		var data = $("#proPertyMngTable").getRowData(rowId);
		
		$("#attrCdLeft").val(data.attrCd);
		$("#attrCdRight").val(data.attrCd);
		$("#attrNmLeft").val(data.attrNm);
		
		$("#attrClCdLeft").val(data.attrClCd);
		$('#attrClCdLeft').selectmenu("refresh");
		
		$("#priNoLeft").val(data.priNo);
		
		$("#useYnLeft").val(data.useYn);
		$('#useYnLeft').selectmenu("refresh");
		
		$("#commCdRefLeft").val(data.commCdRef);
		$('#commCdRefLeft').selectmenu("refresh");
		
		//수정가능하도록
		if(selectTab == "1"){
			$("#areaPopLeft input").removeClass('not-active');
			$("#areaPopLeft input").removeAttr('disabled');
			
			$("#areaPopLeft select").selectmenu("enable");
			
			$("#attrCdLeft").addClass('not-active'); 
			$("#attrCdLeft").attr('disabled', true);
		
			btnActive("btn_initP1");
			btnNonActive("btn_insertP1");
			btnActive("btn_updateP1");
			btnActive("btn_deleteP1");
		}
		
		$("#btnAttrCd").hide();	//중복확인버튼
	}
	
	function initGrid2(attrCd) {
		
		var param = new Object();
		param.attrCd = attrCd;
		
		$("#proPertyMngTable2").jqGrid({
	
		   	url:'itemAttrValListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'priNo' , name: 'priNo', hidden:true,width : 0},
				{ label: 'useYn' , name: 'useYn', hidden:true,width : 0},

	   		    { label: '<spring:message code="LAB.M07.LAB00232" />', name: 'attrCd', width : 100},		//속성코드
	   		 	{ label: '<spring:message code="LAB.M07.LAB00217" />', name: 'attrValCd', width : 100},		//속성값코드
	   			{ label: '<spring:message code="LAB.M07.LAB00216" />', name: 'attrValNm', width : 100},		//속성값명
	   			
			    
	   		],
	   		
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#proPertyMngPager2',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
		    	selectSepP2(rowId);
		    }, 
		    loadComplete: function(){
		    	$("#proPertyMngTable2").setGridWidth(368,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#proPertyMngTable2").setGridWidth(368,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#proPertyMngTable2").setGridWidth(368,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}
	
	//그리드 재검색 
	function searchDataP2(attrCd){
		
		$("#proPertyMngTable2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.attrCd = attrCd;
		
        $("#proPertyMngTable2").setGridParam({ postData: param }).trigger("reloadGrid");
	}
	
	function selectSepP2(rowId){
		var data = $("#proPertyMngTable2").getRowData(rowId);
		
		$("#attrValCdRight").val(data.attrValCd);
		$("#attrValNmRight").val(data.attrValNm);
		$("#priNoRight").val(data.priNo);
		
		$("#useYnRight").val(data.useYn);
		$('#useYnRight').selectmenu("refresh");
		
		
		//수정가능하도록
		if(selectTab == "2"){
			$("#areaPopRight input").removeClass('not-active');
			$("#areaPopRight input").removeAttr('disabled');
			
			$("#areaPopRight select").selectmenu("enable");
			
			$("#attrCdRight").addClass('not-active'); 
			$("#attrCdRight").attr('disabled', true);
			
			$("#attrValCdRight").addClass('not-active'); 
			$("#attrValCdRight").attr('disabled', true);
		
			btnActive("btn_initP2");
			btnNonActive("btn_insertP2");
			btnActive("btn_updateP2");
			btnActive("btn_deleteP2");
		}
		
		$("#btnAttrValCd").hide();	//중복확인버튼
	}
	
	//제품속성 코드 중복체크
	function fnCheckAttrCd(){
		
		var param = new Object();
		param.attrCd = $.trim( $("#attrCdLeft").val() );
		var idCount = ""; 
		
		if(param.attrCd.length < 1){
			alert('<spring:message code="MSG.M11.MSG00007" />');	//코드를 입력해 주세요.
			return;
		}
		
		$.ajax({
			url : 'checkAttrCd.json',
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
			checkAttrCd = "N";
			alert('<spring:message code="MSG.M09.MSG00028" />');	//제품코드가 중복 되었습니다.
		}else{
			checkAttrCd = "Y";
			checkAttrCdValue = $.trim( $("#attrCdLeft").val() );	//사용가능한 코드 입니다.
			alert('<spring:message code="MSG.M07.MSG00015" />');
		}
	}
	
	//제품속성값 코드 중복체크
	function fnCheckAttrValCd(){
		
		var param = new Object();
		param.attrValCd = $.trim( $("#attrValCdRight").val() );
		param.attrCd = $.trim( $("#attrCdRight").val() );
		var idCount = ""; 
		
		if(param.attrValCd.length < 1){
			alert('<spring:message code="MSG.M11.MSG00007" />');	//코드를 입력해 주세요.
			return;
		}
		
		$.ajax({
			url : 'checkAttrValCd.json',
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
			checkAttrValCd = "N";
			alert('<spring:message code="MSG.M09.MSG00026" />');	//제품값코드가 중복 되었습니다.
		}else{
			checkAttrValCd = "Y";
			checkAttrValCdValue = $.trim( $("#attrValCdRight").val() );
			alert('<spring:message code="MSG.M07.MSG00014" />');	//사용가능한 제품값코드 입니다.
		}
	}
	
	//등록
	function insertDataP1(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidationP1("I");
		
		if(param != false){
			
			$.ajax({
				url : 'insertItemAttr.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchDataP1();
						
						//수정가능하도록
						$("#areaPopLeft input").removeClass('not-active');
						$("#areaPopLeft input").removeAttr('disabled');
						
						$("#areaPopLeft select").selectmenu("enable");
						
						$("#attrCdLeft").addClass('not-active'); 
						$("#attrCdLeft").attr('disabled', true);
						
						btnActive("btn_initP1");
						btnNonActive("btn_insertP1");
						btnActive("btn_updateP1");
						btnActive("btn_deleteP1");
						$("#btnAttrCd").hide();	//중복확인버튼
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
	function updateDataP1(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidationP1("U");
		
		if(param != false){
			
			$.ajax({
				url : 'updateItemAttr.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchDataP1();
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
	function deleteDataP1(){
		
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');

		if(check){
			
			var param = new Object();
			param.attrCd = $("#attrCdLeft").val();		//제조사ID
			
			$.ajax({
				url : 'deleteItemAttr.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00053" />');	//삭제되었습니다.
						
						searchDataP1();
						
						$("#areaPopLeft input").val("");
						
						$("#areaPopLeft select").val("");
						$("#areaPopLeft select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
						
						//초기화면 버튼
						leftBtnNonActive();
						leftBtnActive();
						
						//초기화면 인풋
						leftInputNonActive();
						
						searchDataP2("0000");	//오른쪽 그리드 초기화
						
						$("#areaPopRight input").val("");
						
						$("#areaPopRight select").val("");
						$("#areaPopRight select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
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
	function checkValidationP1(type){
		var param = new Object();
		
		param.attrCd = $("#attrCdLeft").val();	//속성코드
		if(param.attrCd == ''){
			//속성코드를 입력해 주세요
			alert('<spring:message code="LAB.M07.LAB00232" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		if(type == "I"){
			if(checkAttrCd == 'N' || param.attrCd != checkAttrCdValue){
				alert('중복 체크를 해주세요.');
				return false;
			}
		}
		
		param.attrNm = $("#attrNmLeft").val();	//속성명
		if(param.attrNm == ''){
			//속성명을 입력해 주세요
			alert('<spring:message code="LAB.M07.LAB00220" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.attrClCd = $("#attrClCdLeft").val();	//속성구분
		//속성구분을 선택해 주세요
		if(param.attrClCd == ''){
			alert('<spring:message code="LAB.M07.LAB00219" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}
		
		param.priNo = $("#priNoLeft").val();			//우선순위
		param.useYn = $("#useYnLeft").val();			//사용여부
		//속성구분을 선택해 주세요
		if(param.useYn == ''){
			alert('<spring:message code="LAB.M07.LAB00026" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}
		param.commCdRef = $("#commCdRefLeft").val();	//공통코드 참조값
		
		return param;
	}
	
	
	//등록
	function insertDataP2(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidationP2("I");
		
		if(param != false){
			
			$.ajax({
				url : 'insertItemAttrVal.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchDataP2();
						
						//수정가능하도록
						$("#areaPopRight input").removeClass('not-active');
						$("#areaPopRight input").removeAttr('disabled');
						
						$("#areaPopLeft select").selectmenu("enable");
						
						$("#attrCdRight").addClass('not-active'); 
						$("#attrCdRight").attr('disabled', true);
						
						$("#attrValCdRight").addClass('not-active'); 
						$("#attrValCdRight").attr('disabled', true);
						
						btnActive("btn_initP2");
						btnNonActive("btn_insertP2");
						btnActive("btn_updateP2");
						btnActive("btn_deleteP2");
						$("#btnAttrValCd").hide();	//중복확인버튼
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
	function updateDataP2(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidationP2("U");
		
		if(param != false){
			
			$.ajax({
				url : 'updateItemAttrVal.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchDataP2();
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
	function deleteDataP2(){
		
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');

		if(check){
			
			var param = new Object();
			param.attrCd = $("#attrCdRight").val();			//속성코드
			param.attrValCd = $("#attrValCdRight").val();	//속성값코드
			
			$.ajax({
				url : 'deleteItemAttrVal.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00053" />');	//삭제되었습니다.
						
						searchDataP2();
						/* 
						$("#areaPopLeft input").val("");
						
						$("#areaPopLeft select").val("");
						$("#areaPopLeft select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
						
						//초기화면 버튼
						leftBtnNonActive();
						leftBtnActive();
						
						//초기화면 인풋
						leftInputNonActive();
						 */
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
	function checkValidationP2(type){
		var param = new Object();
		
		param.attrCd = $("#attrCdRight").val();	//속성코드
		if(param.attrCd == ''){
			//속성코드를 입력해 주세요
			alert('<spring:message code="LAB.M07.LAB00232" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.attrValCd = $("#attrValCdRight").val();	//속성값코드
		if(param.attrValCd == ''){
			//속성값코드를 입력해 주세요
			alert('<spring:message code="LAB.M07.LAB00217" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		if(type == "I"){
			if(checkAttrValCd == 'N' || param.attrValCd != checkAttrValCdValue){
				alert('중복 체크를 해주세요.');
				return false;
			}
		}
		
		param.attrValNm = $("#attrValNmRight").val();	//속성값명
		if(param.attrValNm == ''){
			//속성명을 입력해 주세요
			alert('<spring:message code="LAB.M07.LAB00216" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.priNo = $("#priNoRight").val();			//우선순위
		param.useYn = $("#useYnRight").val();			//사용여부
		if(param.useYn == ''){
			alert('<spring:message code="LAB.M07.LAB00026" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}
		
		return param;
	}
	
	
	function leftReset(){
		
		$("#areaPopLeft input").val("");
		
		$("#areaPopLeft select").val("");
		$("#areaPopLeft select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#areaPopLeft input").removeClass('not-active');
		$("#areaPopLeft input").removeAttr('disabled');
		
		$("#areaPopLeft select").selectmenu("enable");
		
		btnActive("btn_insertP1");	//등록버튼 활성화
		btnNonActive("btn_updateP1");	//수정버튼 비 활성화
		btnNonActive("btn_deleteP1");	//삭제버튼 비 활성화
		
		$("#btnAttrCd").show();	//중복확인버튼
	}
	
	function rightReset(){
		
		//$("#areaPopRight input").val("");
		$("#attrValCdRight").val("");
		$("#attrValNmRight").val("");
		$("#priNoRight").val("");
		
		$("#areaPopRight select").val("");
		$("#areaPopRight select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#areaPopRight input").removeClass('not-active');
		$("#areaPopRight input").removeAttr('disabled');
		
		$("#attrCdRight").addClass('not-active');
		$("#attrCdRight").attr('disabled', true);
		
		$("#areaPopRight select").selectmenu("enable");
		
		btnActive("btn_insertP2");	//등록버튼 활성화
		btnNonActive("btn_updateP2");	//수정버튼 비 활성화
		btnNonActive("btn_deleteP2");	//삭제버튼 비 활성화
		
		$("#btnAttrValCd").show();	//중복확인버튼
	}
	
	function leftInputNonActive(){
		
		$("#areaPopLeft input").addClass('not-active');
		$("#areaPopLeft input").attr('disabled', true);
		
		$("#areaPopLeft select").selectmenu("disable");
		
		btnNonActive("btn_insertP1");	//등록버튼 활성화
		btnNonActive("btn_updateP1");	//수정버튼 비 활성화
		btnNonActive("btn_deleteP1");	//삭제버튼 비 활성화
	}
	
	function rightInputNonActive(){
		
		$("#areaPopRight input").addClass('not-active');
		$("#areaPopRight input").attr('disabled', true);
		
		$("#areaPopRight select").selectmenu("disable");
		
		btnNonActive("btn_insertP2");	//등록버튼 활성화
		btnNonActive("btn_updateP2");	//수정버튼 비 활성화
		btnNonActive("btn_deleteP2");	//삭제버튼 비 활성화
	}
	
	//왼쪽화면 버튼 활성화
	function leftBtnActive(){
		btnActive("btnAttrP1");
		btnActive("btn_initP1");
	}
	//왼쪽화면 버튼 비활성화
	function leftBtnNonActive(){
		btnNonActive("btnAttrP1");
		btnNonActive("btn_initP1");
		btnNonActive("btn_insertP1");
		btnNonActive("btn_updateP1");
		btnNonActive("btn_deleteP1");
	}
	//오른쪽화면 버튼 활성화
	function rigthBtnActive(){
		btnActive("btnAttrP2");
		btnActive("btn_initP2");
	}
	//오른쪽화면 버튼 비활성화
	function rigthBtnNonActive(){
		btnNonActive("btnAttrP2");
		btnNonActive("btn_initP2");
		btnNonActive("btn_insertP2");
		btnNonActive("btn_updateP2");
		btnNonActive("btn_deleteP2");
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
	
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00108"/><!-- 제품관리 --></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->


<!-- center -->
<div class="layer_box">
	<div class="wite_box">
		<div class="lu_left" >
			<div class="main_btn_box">
				<div class="fl">
					<h4 class="sub_title"><spring:message code="LAB.M09.LAB00117"/><!-- 제품속성 --></h4>
				</div>
			</div>

			<div class="layer_box">
				<table id="proPertyMngTable" class="w100p"></table>
				<div id="proPertyMngPager"></div>
			</div>
			
			
			
			<table class="writeview mt10" id="areaPopLeft" name="areaPopLeft">
				<colgroup>
					<col style="width:30%;">
					<col style="width:70%;">
				</colgroup>
				<tbody> 
					<tr>
						<th><spring:message code="LAB.M07.LAB00232"/><!-- 속성코드 --><span class="dot">*</span></th>
						<td>
							<input type="text" id="attrCdLeft" name="attrCdLeft" class="w100" checkbyte="3" />
							<a href="#" id="btnAttrCd" name="btnAttrCd" class="grey-btn" style="display:none;"><spring:message code="LAB.M09.LAB00193"/><!-- 중복체크 --></a>
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00220"/><!-- 속성명 --><span class="dot">*</span></th>
						<td>
							<input type="text" id="attrNmLeft" name="attrNmLeft" class="w200" checkbyte="75" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00219"/><!-- 속성구분 --><span class="dot">*</span></th>
						<td>
							<select id="attrClCdLeft" name="attrClCdLeft" class="w200">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
									<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M08.LAB00086"/><!-- 우선순위 --></th>
						<td>
							<input type="text" id="priNoLeft" name="priNoLeft" class="w200" checkbyte="5" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00026"/><!-- 사용여부 --><span class="dot">*</span></th>
						<td>
							<select id="useYnLeft" name="useYnLeft" class="w200">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<option value="Y">Y</option>
								<option value="N">N</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M01.LAB00111"/><!-- 공통코드참조값 --></th>
						<td>
							<select id="commCdRefLeft" name="commCdRefLeft" class="w200">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<option value="DNDT019">COLOR</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="main_btn_box" id="areaPopLeft2" name="areaPopLeft2">
				<div class="fr">
					<a class="grey-btn" href="#" id="btnAttrP1" name="btnAttrP1" ><spring:message code="LAB.M07.LAB00360"/><!-- 제품속성값관리 --></a>
					
					<!--초기화-->		
					<a class="grey-btn" href="#" id="btn_initP1"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
					<!--등록-->
					<ntels:auth auth="${menuAuthC}">
						<a class="grey-btn" href="#"  id="btn_insertP1" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
					</ntels:auth>		
					<!--수정-->
					<ntels:auth auth="${menuAuthU}">
						<a class="grey-btn" href="#" id="btn_updateP1" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
					</ntels:auth>
					<!--삭제-->
					<ntels:auth auth="${menuAuthD}">
						<a class="grey-btn" href="#" id="btn_deleteP1" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
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
		
		<div class="lu_right" >
			<div class="main_btn_box">
				<div class="fl">
					<h4 class="sub_title"><spring:message code="LAB.M09.LAB00118"/><!-- 제품속성값 --></h4>
				</div>
			</div>
			
			<div class="layer_box">
				<table id="proPertyMngTable2" class="w100p"></table>
				<div id="proPertyMngPager2"></div>
			</div>
			
			
			<table class="writeview mt10" id="areaPopRight" name="areaPopRight" >
				<colgroup>
					<col style="width:30%;">
					<col style="width:70%;">
				</colgroup>
				<tbody> 
					<tr>
						<th><spring:message code="LAB.M07.LAB00232"/><!-- 속성코드 --><span class="dot">*</span></th>
						<td>
							<input type="text" id="attrCdRight" name="attrCdRight" class="w100" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00217"/><!-- 속성값코드 --><span class="dot">*</span></th>
						<td>
							<input type="text" id="attrValCdRight" name="attrValCdRight" class="w100" checkbyte="3" />
							<a href="#" id="btnAttrValCd" name="btnAttrValCd" class="grey-btn" style="display:none;"><spring:message code="LAB.M09.LAB00193"/><!-- 중복체크 --></a>
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00216"/><!-- 속성값명 --><span class="dot">*</span></th>
						<td>
							<input type="text" id="attrValNmRight" name="attrValNmRight" class="w200" checkbyte="75" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M08.LAB00086"/><!-- 우선순위 --></th>
						<td>
							<input type="text" id="priNoRight" name="priNoRight" class="w200" checkbyte="5" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00026"/><!-- 사용여부 --><span class="dot">*</span></th>
						<td>
							<select id="useYnRight" name="useYnRight" class="w200">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<option value="Y">Y</option>
								<option value="N">N</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="main_btn_box mt30" id="areaPopRight2" name="areaPopRight2" >
				<div class="fr">
					<a class="grey-btn" href="#" id="btnAttrP2" name="btnAttrP2" ><spring:message code="LAB.M07.LAB00218"/><!-- 제품속성관리 --></a>
					
					<!--초기화-->		
					<a class="grey-btn" href="#" id="btn_initP2"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
					<!--등록-->
					<ntels:auth auth="${menuAuthC}">
						<a class="grey-btn" href="#"  id="btn_insertP2" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
					</ntels:auth>		
					<!--수정-->
					<ntels:auth auth="${menuAuthU}">
						<a class="grey-btn" href="#" id="btn_updateP2" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
					</ntels:auth>
					<!--삭제-->
					<ntels:auth auth="${menuAuthD}">
						<a class="grey-btn" href="#" id="btn_deleteP2" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
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
	</div>
</div>

<div class="btn_box">
	<a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<!--// center -->
	
	
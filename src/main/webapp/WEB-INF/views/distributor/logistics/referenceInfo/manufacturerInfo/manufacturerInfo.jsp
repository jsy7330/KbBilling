<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		byteCheckKeyEvent();	//바이트체크
		
		$("#searchSoId").val("${session_user.soId}");
		$('#searchSoId').selectmenu("refresh");
		
		nonActiveSetting();
		
		initGrid();
		
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
		
		//주소검색
		$('#searchAddrBtn').click(function() {
			if($("#searchAddrBtn").hasClass('not-active')){
				return;
			}
			searchAddrBtn();
		});
		
		//파일다운로드버튼
		$("#btnDown01").click(function() {
			fileDownload("ctrtUuid", "ctrtAttchFileNm");
		});
		
		$("#btnDown02").click(function() {
			fileDownload("sttlUuid", "sttlAttchFileNm");
		});
		
		//셀렉트 박스 체인지 이벤트
		$('#searchSoId').selectmenu({
		    change: function() {
		    	searchData();
		    }
		});
		
		//키이벤트
		$( "#searchText" ).keypress(function(event) {
	   		if(event.keyCode == 13){
	   			searchData();
	   		}
		});
		
	});
	
	
	function fileUpload(fileId){
		
		var formData = new FormData();
		//var file = this.files[0];                      //multiple 속성이 있으면 files 에는 다수의 객체가 할당됨
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
				console.log(data);
				uuid = data.uuid;
			}
		});
		
		return uuid;
		
	}
	
	function fileDownload(uuidId, fileNmId){
		
		var uuid = $("#"+uuidId).val();
		var fileNm = $("#"+fileNmId).val();
		
		if(uuid == '' || fileNm == ''){
			alert('<spring:message code="MSG.M03.MSG00004" />');	//다운받을 파일이 없습니다.
			return;
		}
		
		//location.href = '/system/common/common/fileMng/getFileAction.json?uuid='+uuid+'&fileNm='+fileNm;
		location.href = '/system/common/common/fileMng/getFileAction.json?uuid='+uuid+'&fileNm='+encodeURI(encodeURIComponent(fileNm));
	}
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		
		$("#maunfacturerInfoTable").jqGrid({
			
		   	url:'manufacturerInfoListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'repFaxNo' , name: 'repFaxNo', hidden:true,width : 0},
				{ label: 'busiCndt' , name: 'busiCndt', hidden:true,width : 0},
				{ label: 'busiTp' , name: 'busiTp', hidden:true,width : 0},
				{ label: 'poInchrgFaxNo' , name: 'poInchrgFaxNo', hidden:true,width : 0},
				{ label: 'poInchrgEml' , name: 'poInchrgEml', hidden:true,width : 0},
				{ label: 'rtngdInchrgNm' , name: 'rtngdInchrgNm', hidden:true,width : 0},
				{ label: 'rtngdInchrgTelNo' , name: 'rtngdInchrgTelNo', hidden:true,width : 0},
				{ label: 'rtngdInchrgFaxNo' , name: 'rtngdInchrgFaxNo', hidden:true,width : 0},
				{ label: 'rtngdInchrgEml' , name: 'rtngdInchrgEml', hidden:true,width : 0},
				{ label: 'zipCd' , name: 'zipCd', hidden:true,width : 0},
				{ label: 'bssAddr' , name: 'bssAddr', hidden:true,width : 0},
				{ label: 'dtlAddr' , name: 'dtlAddr', hidden:true,width : 0},
				{ label: 'ctrtInchrgNm' , name: 'ctrtInchrgNm', hidden:true,width : 0},
				{ label: 'ctrtInchrgTelNo' , name: 'ctrtInchrgTelNo', hidden:true,width : 0},
				{ label: 'ctrtInchrgFaxNo' , name: 'ctrtInchrgFaxNo', hidden:true,width : 0},
				{ label: 'ctrtInchrgEml' , name: 'ctrtInchrgEml', hidden:true,width : 0},
				{ label: 'ctrtStrtDt' , name: 'ctrtStrtDt', hidden:true,width : 0},
				{ label: 'ctrtEndDt' , name: 'ctrtEndDt', hidden:true,width : 0},
				{ label: 'ctrtUuid' , name: 'ctrtUuid', hidden:true,width : 0},
				{ label: 'ctrtAttchFileNm' , name: 'ctrtAttchFileNm', hidden:true,width : 0},
				{ label: 'ctrtNote' , name: 'ctrtNote', hidden:true,width : 0},
				{ label: 'bnkCd' , name: 'bnkCd', hidden:true,width : 0},
				{ label: 'acnt' , name: 'acnt', hidden:true,width : 0},
				{ label: 'acntHldNm' , name: 'acntHldNm', hidden:true,width : 0},
				{ label: 'pymMthdCd' , name: 'pymMthdCd', hidden:true,width : 0},
				{ label: 'sttlInchrgNm' , name: 'sttlInchrgNm', hidden:true,width : 0},
				{ label: 'sttlInchrgTelNo' , name: 'sttlInchrgTelNo', hidden:true,width : 0},
				{ label: 'sttlInchrgFaxNo' , name: 'sttlInchrgFaxNo', hidden:true,width : 0},
				{ label: 'sttlInchrgEml' , name: 'sttlInchrgEml', hidden:true,width : 0},
				{ label: 'sttlUuid' , name: 'sttlUuid', hidden:true,width : 0},
				{ label: 'sttlAttchFileNm' , name: 'sttlAttchFileNm', hidden:true,width : 0},
				{ label: 'sttlInchrgNote' , name: 'sttlInchrgNote', hidden:true,width : 0},

	   		    { label: '<spring:message code="LAB.M07.LAB00003" />', name: 'soNm', width : 100},			//사업
	   		 	{ label: '<spring:message code="LAB.M09.LAB00099" />', name: 'mncoId', width : 100},		//제조사ID
	   			{ label: '<spring:message code="LAB.M09.LAB00101" />', name: 'mncoNm', width : 100},		//제조사명
	   			{ label: '<spring:message code="LAB.M07.LAB00014" />', name: 'borNo', width : 100},			//사업자번호
	   			{ label: '<spring:message code="LAB.M03.LAB00063" />', name: 'repNm', width : 100},			//대표자명
	   			{ label: '<spring:message code="LAB.M03.LAB00061" />', name: 'repTelNo', width : 100},	//대표연락처
	   			{ label: '<spring:message code="LAB.M06.LAB00027" />', name: 'poInchrgNm', width : 100},	//발주담당자명
	   			{ label: '<spring:message code="LAB.M06.LAB00028" />', name: 'poInchrgTelNo', width : 100},	//발주담당자연락처
	   			
	   			
	   		],
		   		
	   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#maunfacturerInfoPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 240,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        	setSelectedDate(rowId);
	        },
	        loadComplete: function(obj){
	        	$("#maunfacturerInfoTable").setGridWidth($('#manufacturerInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        }
		});
		
		$("#maunfacturerInfoTable").setGridWidth($('#manufacturerInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#maunfacturerInfoTable").setGridWidth($('#manufacturerInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	//그리드 재검색 
	function searchData(){
		
		$("#maunfacturerInfoTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.searchType = $("#searchType").val();
		param.searchText = $("#searchText").val();
		
        $("#maunfacturerInfoTable").setGridParam({ postData: param }).trigger("reloadGrid");
        
        reset();
	}
	
	//상세정보 
	function setSelectedDate(rowId){
		
		var data = $("#maunfacturerInfoTable").getRowData(rowId);
		$("#soId").val(data.soId);
		$('#soId').selectmenu("refresh");
		
		$("#mncoId").val(data.mncoId);		
		$("#mncoNm").val(data.mncoNm);
		$("#repNm").val(data.repNm);
		$("#repTelNo").val(data.repTelNo);
		$("#borNo").val(data.borNo);
		$("#repFaxNo").val(data.repFaxNo);
		$("#zipCd").val(data.zipCd);
		$("#bssAddr").val(data.bssAddr);
		$("#dtlAddr").val(data.dtlAddr);
		$("#busiCndt").val(data.busiCndt);
		$("#busiTp").val(data.busiTp);
		$("#poInchrgNm").val(data.poInchrgNm);
		$("#poInchrgTelNo").val(data.poInchrgTelNo);
		$("#poInchrgFaxNo").val(data.poInchrgFaxNo);
		$("#poInchrgEml").val(data.poInchrgEml);
		$("#rtngdInchrgNm").val(data.rtngdInchrgNm);
		$("#rtngdInchrgTelNo").val(data.rtngdInchrgTelNo);
		$("#rtngdInchrgFaxNo").val(data.rtngdInchrgFaxNo);
		$("#rtngdInchrgEml").val(data.rtngdInchrgEml);
		$("#ctrtInchrgNm").val(data.ctrtInchrgNm);
		$("#ctrtInchrgTelNo").val(data.ctrtInchrgTelNo);
		$("#ctrtInchrgFaxNo").val(data.ctrtInchrgFaxNo);
		$("#ctrtInchrgEml").val(data.ctrtInchrgEml);
		$("#ctrtStrtDt").val(stringToDateformatYYYYMMDD(data.ctrtStrtDt));
		$("#ctrtEndDt").val(stringToDateformatYYYYMMDD(data.ctrtEndDt));
		$("#ctrtUuid").val(data.ctrtUuid);
		$("#ctrtAttchFileNm").val(data.ctrtAttchFileNm);
		$("#ctrtNote").val(data.ctrtNote);
		$("#sttlInchrgNm").val(data.sttlInchrgNm);
		$("#sttlInchrgTelNo").val(data.sttlInchrgTelNo);
		$("#sttlInchrgFaxNo").val(data.sttlInchrgFaxNo);
		$("#sttlInchrgEml").val(data.sttlInchrgEml);
		$("#sttlUuid").val(data.sttlUuid);
		$("#sttlAttchFileNm").val(data.sttlAttchFileNm);
		$("#sttlInchrgNote").val(data.sttlInchrgNote);
		$("#bnkCd").val(data.bnkCd);
		$('#bnkCd').selectmenu("refresh");
		$("#acntHldNm").val(data.acntHldNm);
		$("#pymMthdCd").val(data.pymMthdCd);
		$('#pymMthdCd').selectmenu("refresh");
		$("#acnt").val(data.acnt);
		
		activeSetting();
		
		$("#mncoId").addClass('not-active');
		$("#mncoId").attr('disabled', true);
		
		$("#soId").selectmenu("disable");
		
		btnActive("btn_init");
		btnNonActive("btn_insert");
		btnActive("btn_update");
		btnActive("btn_delete");
		
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
				param.ctrtUuid = uuid;
			}
			
			if($("#ex_filename02").val() != ""){
				var uuid = fileUpload("ex_filename02");
				param.sttlUuid = uuid;
			}
			
			$.ajax({
				url : 'insertManufacturerInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();
						
						activeSetting();
						
						$("#mncoId").addClass('not-active');
						$("#mncoId").attr('disabled', true);
						
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
				param.ctrtUuid = uuid;
			}
			
			if($("#ex_filename02").val() != ""){
				var uuid = fileUpload("ex_filename02");
				param.sttlUuid = uuid;
			}
			
			$.ajax({
				url : 'updateManufacturerInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();
						
						activeSetting();
						
						$("#mncoId").addClass('not-active');
						$("#mncoId").attr('disabled', true);
						
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
			param.mncoId = $("#mncoId").val();		//제조사ID
			
			$.ajax({
				url : 'deleteManufacturerInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
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
		
		param.mncoId = $("#mncoId").val();		//제조사ID
		if(param.mncoId == ''){
			//제조사ID를 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00099" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		if(type == "I"){
			if(!checkId()){
				alert('<spring:message code="MSG.M09.MSG00024" />');
				return false;
			}
		}
		
		param.mncoNm = $("#mncoNm").val();		//제조사명
		if(param.mncoNm == ''){
			//제조사명을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00101" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.repNm = $("#repNm").val();		//대표자명
		if(param.repNm == ''){
			//대표자명을 입력해 주세요
			alert('<spring:message code="LAB.M03.LAB00063" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.borNo = $("#borNo").val();		//사업자번호
		if(param.borNo == ''){
			//사업자번호를 입력해 주세요
			alert('<spring:message code="LAB.M07.LAB00014" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.zipCd = $("#zipCd").val();		//기본주소
		if(param.zipCd == ''){
			//기본주소를 입력해 주세요
			alert('<spring:message code="LAB.M01.LAB00218" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.repTelNo = $("#repTelNo").val();					//대표연락처
		param.repFaxNo = $("#repFaxNo").val();					//대표FAX
		param.bssAddr = $("#bssAddr").val();					//기본주소
		param.dtlAddr = $("#dtlAddr").val();					//상세주소
		param.busiCndt = $("#busiCndt").val();					//업태
		param.busiTp = $("#busiTp").val();						//업종
		param.poInchrgNm = $("#poInchrgNm").val();				//발주담당 담당자명
		param.poInchrgTelNo = $("#poInchrgTelNo").val();		//발주담당 담당자연락처
		param.poInchrgFaxNo = $("#poInchrgFaxNo").val();		//발주담당 담당자FAX
		param.poInchrgEml = $("#poInchrgEml").val();			//발주담당 담당자이메일
		param.rtngdInchrgNm = $("#rtngdInchrgNm").val();		//반품담당 담당자명
		param.rtngdInchrgTelNo = $("#rtngdInchrgTelNo").val();	//반품담당 담당자연락처
		param.rtngdInchrgFaxNo = $("#rtngdInchrgFaxNo").val();	//반품담당 담당자FAX
		param.rtngdInchrgEml = $("#rtngdInchrgEml").val();		//반품담당 담당자이메일
		param.ctrtStrtDt = dateFormatToStringYYYYMMDD( $("#ctrtStrtDt").val() );		//계약시작일자
		param.ctrtEndDt = dateFormatToStringYYYYMMDD( $("#ctrtEndDt").val() );		//계약만료일자
		param.ctrtInchrgNm = $("#ctrtInchrgNm").val();			//계약담당 담당자명
		param.ctrtInchrgTelNo = $("#ctrtInchrgTelNo").val();	//계약담당 담당자연락처
		param.ctrtInchrgFaxNo = $("#ctrtInchrgFaxNo").val();	//계약담당 담당자FAX
		param.ctrtInchrgEml = $("#ctrtInchrgEml").val();		//계약담당 담당자이메일
		param.ctrtAttchFileNm = $("#ctrtAttchFileNm").val();	//계약첨부파일명
		param.ctrtUuid = $("#ctrtUuid").val();					//계약첨부파일ID
		param.ctrtNote = $("#ctrtNote").val();					//계약비고
		param.sttlInchrgNm = $("#sttlInchrgNm").val();			//정산담당 담당자명
		param.sttlInchrgTelNo = $("#sttlInchrgTelNo").val();	//정산담당 담당자연락처
		param.sttlInchrgFaxNo = $("#sttlInchrgFaxNo").val();	//정산담당 담당자FAX
		param.sttlInchrgEml = $("#sttlInchrgEml").val();		//정산담당 담당자이메일
		param.sttlAttchFileNm = $("#sttlAttchFileNm").val();	//정산첨부파일명
		param.sttlUuid = $("#sttlUuid").val();					//정산첨부파일ID
		param.sttlInchrgNote = $("#sttlInchrgNote").val();		//정산비고
		param.bnkCd = $("#bnkCd").val();						//은행명
		param.acntHldNm = $("#acntHldNm").val();				//예금주
		param.pymMthdCd = $("#pymMthdCd").val();				//대금지급방법
		param.acnt = $("#acnt").val();							//계좌번호
		
		return param;
	}
	
	//제조사아이디 중복체크
	function checkId(){
		
		var param = new Object();
		param.mncoId = $.trim( $("#mncoId").val() );
		var idCount = ""; 
		
		$.ajax({
			url : 'getExistManufacturerInfo.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				idCount = data.count;
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
		if(idCount != "0"){	//중복
			return false;
		}else{
			return true;
		}
	}
	
	
	function nonActiveSetting(){
		
		$("#areaDtl input").addClass('not-active');
		$("#areaDtl input").attr('disabled', true);
		
		$("#areaDtl select").selectmenu("disable");
		
		$('#searchAddrBtn').addClass('not-active');
		$('#searchAddrBtn').attr('disabled',true);
		
		$("#areaPo input").addClass('not-active');
		$("#areaPo input").attr('disabled', true);
		
		$("#areaRtngd input").addClass('not-active');
		$("#areaRtngd input").attr('disabled', true);
		
		$("#areaCtrt input").addClass('not-active');
		$("#areaCtrt input").attr('disabled', true);
		
		$("#areaSttl input").addClass('not-active');
		$("#areaSttl input").attr('disabled', true);
		
		$("#areaSttl select").selectmenu("disable");
		
		btnActive("btn_init");
		btnNonActive("btn_insert");
		btnNonActive("btn_update");
		btnNonActive("btn_delete");
	}
	
	function activeSetting(){
		$("#areaDtl input").removeClass('not-active');
		$("#areaDtl input").removeAttr('disabled');
		
		$("#areaDtl select").selectmenu("enable");
		
		$('#searchAddrBtn').removeClass('not-active');
		$('#searchAddrBtn').removeAttr('disabled');
		
		$("#areaPo input").removeClass('not-active');
		$("#areaPo input").removeAttr('disabled');
		
		$("#areaRtngd input").removeClass('not-active');
		$("#areaRtngd input").removeAttr('disabled');
		
		$("#areaCtrt input").removeClass('not-active');
		$("#areaCtrt input").removeAttr('disabled');
		
		$("#areaSttl input").removeClass('not-active');
		$("#areaSttl input").removeAttr('disabled');
		
		$("#areaSttl select").selectmenu("enable");
		
		//비활성화 
		$("#zipCd").addClass('not-active');
		$("#zipCd").attr('disabled', true);
		
		$("#bssAddr").addClass('not-active');
		$("#bssAddr").attr('disabled', true);
		
		$("#ctrtAttchFileNm").addClass('not-active');
		$("#ctrtAttchFileNm").attr('disabled', true);
		
		$("#sttlAttchFileNm").addClass('not-active');
		$("#sttlAttchFileNm").attr('disabled', true);
	}
	
	function reset(){
		
		activeSetting();
		
		$("#areaDtl input").val("");
		$("#areaDtl select").val("");
		$("#areaDtl select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#areaPo input").val("");
		
		$("#areaRtngd input").val("");
		
		$("#areaCtrt input").val("");
		
		$("#areaSttl input").val("");
		$("#areaSttl select").val("");
		$("#areaSttl select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		btnActive("btn_insert");	//등록버튼 활성화
		btnNonActive("btn_update");	//수정버튼 비 활성화
		btnNonActive("btn_delete");	//삭제버튼 비 활성화
		
		$("textarea").val("");
		
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
	
	function searchAddrBtn(){
		
		var parameter = new Object();
    	parameter.zipCd = 'zipCd';
    	parameter.baseAddr = 'bssAddr';
    	parameter.addrDtl = 'dtlAddr';
    	
		$.ajax({
			type : "post",
			url : '/system/common/common/postMng/postPopup.ajax',
			data : parameter,
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


<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:40%;">
		<col style="width:10%;">
		<col style="width:40%;">
	</colgroup>
	<thead> 
		<tr>
			<th title="latest" ><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
			<td>
				<select class="w150" id="searchSoId" name="searchSoId">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M09.LAB00159"/><!-- 조회구분 --></th>
			<td>
				<div class="date_box">
					<div class="select_box w150">
						<select class=" sel" id="searchType" name="searchType" >
							<option value="01" >Manufacturer ID</option>
							<option value="02" >Manufacturer Name</option>
						</select>                                                
					</div>
					<div class="inp_date w200">
						<input type="text" id="searchText" name="searchText" class="w160">
					</div> 
				</div>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00102"/><!-- 제조사목록 --></h4>
	</div>
</div>

<div id="manufacturerInfoGrid">
	<table id="maunfacturerInfoTable" class="w100p"></table>
	<div id="maunfacturerInfoPager"></div>
</div>

<form id="manufactureForm" name="manufactureForm" method="post" enctype="multipart/form-data">

	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00101"/><!-- 상세정보 --></h4>
		</div>
	</div>
	
	<table class="writeview" id="areaDtl">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<tbody> 
			<tr>
				<th>사업<span class="dot">*</span></th>
				<td>
					<select class="w150" id="soId" name="soId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M09.LAB00099"/><!-- 제조사ID --><span class="dot">*</span></th>
				<td>                                                
					<input type="text" id="mncoId" name="mncoId" class="w100" checkbyte="30" />
					<input type="text" id="mncoNm" name="mncoNm" class="w165" checkbyte="150" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00063"/><!-- 대표자명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="repNm" name="repNm" class="w270" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M03.LAB00061"/><!-- 대표연락처 --></th>
				<td colspan="3">
					<input type="text" id="repTelNo" name="repTelNo" class="w270" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00014"/><!-- 사업자번호 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="borNo" name="borNo" class="w270" checkbyte="30" /></td>
				<th><spring:message code="LAB.M03.LAB00060"/><!-- 대표FAX --></th>
				<td>
					<input type="text" id="repFaxNo" name="repFaxNo" class="w270" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00218"/><!-- 기본주소 --><span class="dot">*</span></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="zipCd" name="zipCd" class="p100" />
							 <a id="searchAddrBtn" href="#" class="search"><spring:message code="LAB.M09.LAB00191" /></a>
						</div>
						<div class="inp_date w85p">
							<input type="text" id="bssAddr" name="bssAddr" class="w40p" /> 
							<input type="text" id="dtlAddr" name="dtlAddr" class="w55p" checkbyte="450" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00012"/><!-- 업태 --></th>
				<td>
					<input type="text" id="busiCndt" name="busiCndt" class="w270" checkbyte="75" />
				</td>
				<th><spring:message code="LAB.M08.LAB00011"/><!-- 업종 --><span class="dot"></span></th>
				<td>
					<input type="text" id="busiTp" name="busiTp" class="w270" checkbyte="75" />
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M06.LAB00026"/><!-- 발주담당 --></h4>
		</div>
	</div>
	
	<table class="writeview" id="areaPo">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M03.LAB00031"/><!-- 담당자명 --></th>
				<td>
					<input type="text" id="poInchrgNm" name="poInchrgNm" class="w270" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M03.LAB00033"/><!-- 담당자연락처 --></th>
				<td>                                                
					<input type="text" id="poInchrgTelNo" name="poInchrgTelNo" class="w270" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00030"/><!-- 담당자FAX --></th>
				<td>
					<input type="text" id="poInchrgFaxNo" name="poInchrgFaxNo" class="w270" checkbyte="75" />
				</td>
				<th><spring:message code="LAB.M03.LAB00034"/><!-- 담당자이메일 --></th>
				<td>
					<input type="text" id="poInchrgEml" name="poInchrgEml" class="w270" checkbyte="75" />
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M06.LAB00021"/><!-- 반품담당 --></h4>
		</div>
	</div>
	
	<table class="writeview" id="areaRtngd">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M03.LAB00031"/><!-- 담당자명 --></th>
				<td>
					<input type="text" id="rtngdInchrgNm" name="rtngdInchrgNm" class="w270" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M03.LAB00033"/><!-- 담당자연락처 --></th>
				<td>                                                
					<input type="text" id="rtngdInchrgTelNo" name="rtngdInchrgTelNo" class="w270" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00030"/><!-- 담당자FAX --></th>
				<td>
					<input type="text" id="rtngdInchrgFaxNo" name="rtngdInchrgFaxNo" class="w270" checkbyte="75" />
				</td>
				<th><spring:message code="LAB.M03.LAB00034"/><!-- 담당자이메일 --></th>
				<td>
					<input type="text" id="rtngdInchrgEml" name="rtngdInchrgEml" class="w270" checkbyte="75" />
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M01.LAB00033"/><!-- 계약담당 --></h4>
		</div>
	</div>
	
	<table class="writeview" id="areaCtrt">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M01.LAB00038"/><!-- 계약시작일자 --></th>
				<td>
					<div class="inp_date w270">
						<input type="text" id="ctrtStrtDt" name="ctrtStrtDt" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00034"/><!-- 계약만료일자 --></th>
				<td>                                                
					<div class="inp_date w270">
						<input type="text" id="ctrtEndDt" name="ctrtEndDt" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00031"/><!-- 담당자명 --></th>
				<td>
					<input type="text" id="ctrtInchrgNm" name="ctrtInchrgNm" class="w270" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M03.LAB00033"/><!-- 담당자연락처 --></th>
				<td>
					<input type="text" id="ctrtInchrgTelNo" name="ctrtInchrgTelNo" class="w270" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00030"/><!-- 담당자FAX --></th>
				<td>
					<input type="text" id="ctrtInchrgFaxNo" name="ctrtInchrgFaxNo" class="w270" checkbyte="75" />
				</td>
				<th><spring:message code="LAB.M03.LAB00034"/><!-- 담당자이메일 --></th>
				<td>
					<input type="text" id="ctrtInchrgEml" name="ctrtInchrgEml" class="w270" checkbyte="75" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M10.LAB00029"/><!-- 첨부파일 --></th>
				<td colspan="3" >
					<span class="filebox bs3-primary">
						<input class="upload-name w100" id="ctrtAttchFileNm" name="ctrtAttchFileNm" value="" placeholder="<spring:message code='LAB.M13.LAB00002'/>" >
						<input type="hidden" id="ctrtUuid" name="ctrtUuid" >
						<label for="ex_filename01"><spring:message code="LAB.M08.LAB00009"/><!-- 업로드 --></label> 
						<input type="file" id="ex_filename01" name="ex_filename01"  class="upload-hidden"> 
					</span>                                            	
					<a href="#" id="btnDown01" name="btnDown01" class="td-btn"><spring:message code="LAB.M03.LAB00004"/><!-- 다운로드 --></a>
				</td>
				<%-- 
				<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --></th>
				<td>
					<input type="text" id="ctrtNote" name="ctrtNote" class="w270" checkbyte="4500" />
				</td>
				 --%>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --></th>
				<td colspan="3" >
					<textarea id="ctrtNote" name="ctrtNote" checkbyte="4000" class="w100p h50"></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M09.LAB00090"/><!-- 정산담당 --></h4>
		</div>
	</div>
	
	<table class="writeview" id="areaSttl" >
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M03.LAB00031"/><!-- 담당자명 --></th>
				<td>
					<input type="text" id="sttlInchrgNm" name="sttlInchrgNm" class="w270" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M03.LAB00033"/><!-- 담당자연락처 --></th>
				<td>                                                
					<input type="text" id="sttlInchrgTelNo" name="sttlInchrgTelNo" class="w270" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00030"/><!-- 담당자FAX --></th>
				<td>
					<input type="text" id="sttlInchrgFaxNo" name="sttlInchrgFaxNo" class="w270" checkbyte="75" />
				</td>
				<th><spring:message code="LAB.M03.LAB00034"/><!-- 담당자이메일 --></th>
				<td>
					<input type="text" id="sttlInchrgEml" name="sttlInchrgEml" class="w270" checkbyte="75" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M10.LAB00029"/><!-- 첨부파일 --></th>
				<td colspan="3">
					<span class="filebox bs3-primary">
						<input class="upload-name w100" id="sttlAttchFileNm" name="sttlAttchFileNm" value="" placeholder="<spring:message code='LAB.M13.LAB00002'/>" >
						<input type="hidden" id="sttlUuid" name="sttlUuid" />
						<label for="ex_filename02"><spring:message code="LAB.M08.LAB00009"/><!-- 업로드 --></label> 
						<input type="file" id="ex_filename02" name="ex_filename02" class="upload-hidden"> 
					</span>                                            	
					<a href="#" id="btnDown02" name="btnDown02" class="td-btn"><spring:message code="LAB.M03.LAB00004"/><!-- 다운로드 --></a>
				</td>
				<%-- 
				<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --></th>
				<td>
					<input type="text" id="sttlInchrgNote" name="sttlInchrgNote" class="w270" checkbyte="4500" />
				</td>
				  --%>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --></th>
				<td colspan="3" >
					<textarea id="sttlInchrgNote" name="sttlInchrgNote" checkbyte="4000" class="w100p h50"></textarea>
				</td>
			</tr>
			
			
			
			<tr>
				<th><spring:message code="LAB.M08.LAB00112"/><!-- 은행명 --></th>
				<td>
					<select name="select" id="bnkCd" name="bnkCd" class="w270">
						<option value=''><spring:message code="LAB.M07.LAB00195"/></option>
						<c:forEach items="${bnkCd}" var="bnkCd" varStatus="status">
						 	<option value="${bnkCd.commonCd}">${bnkCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M08.LAB00028"/><!-- 예금주 --></th>
				<td colspan="3">
					<input type="text" id="acntHldNm" name="acntHldNm" class="w270" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00041"/><!-- 대금지급방법 --></th>
				<td>
					<select name="select" id="pymMthdCd" name="pymMthdCd" class="w270">
						<option value=''><spring:message code="LAB.M07.LAB00195"/></option>
						<c:forEach items="${pymMthdCd}" var="pymMthdCd" varStatus="status">
						 	<option value="${pymMthdCd.commonCd}">${pymMthdCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M01.LAB00044"/><!-- 계좌번호 --></th>
				<td colspan="3">
					<input type="text" id="acnt" name="acnt" class="w270" checkbyte="20" />
				</td>
			</tr>
		</tbody>
	</table>

</form>

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
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>


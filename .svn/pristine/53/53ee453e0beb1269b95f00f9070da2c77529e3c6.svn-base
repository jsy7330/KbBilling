<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {

		reset();
		initGrid();
		initGrid2('');
		
		$('#btnSearch').click(function() {
			searchVissue();
		});

		$('#btnSearchMnco').click(function() {
			var url="/system/common/common/manufacturerMng/manufacturerSearchPopup.ajax";
			var param = new Object();
			param.soId = $("#searchSoId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchMncoId";	//리턴받는 Id 의 태그아이디값
			param.returnId2 = "searchMncoNm";	//리턴받는 Nm 의 태그아이디값
			
			$("#popup_dialog").css("width", 500);
			openModal('popup_dialog', url, param);
		})

		//제조사검색 팝업
		$("#btnSearchLgstCent").click(function() {
 			var url="/distributor/logistics/voucherMng/voucherGenerate/logisticsCenterSearchPopup.ajax";
			var param = {
				returnId : 'lgstCentId'
				, returnNm : 'lgstCentNm'
			};
			
			$("#popup_dialog").css("width", 900);
			openModal('popup_dialog', url, param);
		});

		//제품검색 팝업
		$("#btnSearchVoucher").click(function() {
			var url="productSearchPopup.ajax";
			var param = new Object();
			param.soId = $("#soId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.presetItemTpCd = "V";
			param.returnId1 = "itemId";	//리턴받는 Id 의 태그아이디값
			param.returnId2 = "itemNm";	//리턴받는 Nm 의 태그아이디값
			param.returnId3 = "mncoId";
			param.returnId4 = "mncoNm";
			
			$("#popup_dialog").css("width", 800);
			openModal('popup_dialog', url, param);
		});

		// 바우처상품 검색 팝업
		$('#btnSearchVoucherItem').click(function() {
			var url="/distributor/logistics/voucherMng/voucherGenerate/voucherItemSearchPopup.ajax";

			var param = {
				soId : $('#soId').val()
				, soNm : $('#soId option:selected').text()
				, returnId : 'voProdCd'
				, returnNm : 'voProdNm'
				, returnAmt : 'voIssueAmt'
			}

			$("#popup_dialog").css("width", 700);
			openModal('popup_dialog', url, param);
		});

		// 주소검색
		$('#btnSearchAddr').click(function() {
			searchAddr();
		})

		// 발행 클릭
		$('#btnIssueBulk').click(function() {

			var id = $("#voucherGenerateTable").jqGrid("getGridParam", "selrow" );

			if(id == null){
				// 바우처를 선택해주세요.
				alert('<spring:message code="MSG.M06.MSG00001" />');
				return;
			}

			// 발행을 하시겠습니까?
			var check = confirm('<spring:message code="MSG.M06.MSG00017" />');
			if (check == false) {
				return;
			}

			var row = $("#voucherGenerateTable").getRowData(id);

			var soId = row.soId;
			var itemId = row.itemId;
			var vissueSeqNo = row.vissueSeqNo;
			var voIssueAmt = row.voIssueAmt;
			var voProdCd = row.voProdCd;
			var voIssueCnt = row.voIssueCnt;
			var voIssueDt = row.voIssueDt;
			var voExpiredDt = row.voExpiredDt;

			var param = {
				soId : soId
				, itemId : itemId
				, vissueSeqNo : vissueSeqNo
				, voIssueAmt : voIssueAmt
				, voProdCd : voProdCd
				, voIssueCnt : voIssueCnt
				, voIssueDt : voIssueDt
				, voExpiredDt : voExpiredDt
			};

			console.log('itemId : ' + itemId);
			console.log('vissueSeqNo : ' + vissueSeqNo);
			console.log('voIssueAmt : ' + voIssueAmt);
			console.log('voProdCd : ' + voProdCd);
			console.log('voIssueCnt : ' + voIssueCnt);
			console.log('voIssueDt : ' + voIssueDt);
			console.log('voExpiredDt : ' + voExpiredDt);

			$.ajax({
				url : 'addIssueVoucherBulkInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					var result = data.result;

					if (result == 0) {
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						searchVissueDtl(soId, vissueSeqNo);
						refreshGrid();
					} else {
						var errorMsg = data.errorMsg;
						alert(errorMsg);
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});

		});

		// 이관 클릭
		$('#btnTransfer').click(function() {
			var url="/distributor/logistics/voucherMng/voucherGenerate/voucherTransferPopup.ajax";

			var param = {};

			$("#popup_dialog").css("width", 700);
			openModal('popup_dialog', url, param);
		});

		// 발주등록 클릭
		$('#btnAddOrder').click(function() {

			var id = $("#voucherGenerateTable").jqGrid("getGridParam", "selrow" );

			if(id == null){
				// 바우처를 선택해주세요.
				alert('<spring:message code="MSG.M06.MSG00001" />');
				return;
			}

			// 발행을 하시겠습니까?
			var check = confirm('<spring:message code="MSG.M06.MSG00010" />');
			if (check == false) {
				return;
			}

			var row = $("#voucherGenerateTable").getRowData(id);

			var poDt = $('#poDt').val();
			var dlgdAgreeDt = $('#dlgdAgreeDt').val();
			var lgstCentId = $('#lgstCentId').val();

			if (isEmpty(poDt) == true) {
				alert('<spring:message code="MSG.M06.MSG00011" />');
				return;
			}

			if (isEmpty(dlgdAgreeDt) == true) {
				alert('<spring:message code="MSG.M02.MSG00006" />');
				return;
			}

			if (isEmpty(lgstCentId) == true) {
				alert('<spring:message code="MSG.M05.MSG00012" />');
				return;
			}

			var mncoOtptUtPrc = row.mncoOtptUtPrc;
			var voIssueCnt = row.voIssueCnt
			var poAmt = mncoOtptUtPrc * voIssueCnt

			var dlvZipCd = $('#zipCd').val();
			var dlvBssAddr = $('#bssAddr').val();
			var dlvDtlAddr = $('#dtlAddr').val();

			var param = {
				soId : row.soId
				, vissueSeqNo : row.vissueSeqNo
				, poDt : poDt
				, itemId : row.itemId
				, mncoId : row.mncoId
				, itemTpCd : 'V'
				, voIssueCnt : voIssueCnt
				, mncoOtptUtPrc : mncoOtptUtPrc
				, poAmt : poAmt
				, lgstCentId : lgstCentId
				, dlgdAgreeDt : dlgdAgreeDt
				, dlvZipCd : dlvZipCd
				, dlvBssAddr : dlvBssAddr
				, dlvDtlAddr : dlvDtlAddr
			}

			console.log('param.soId : ' + param.soId);
			console.log('param.vissueSeqNo : ' +  param.vissueSeqNo);
			console.log('param.poDt : ' +  param.poDt);
			console.log('param.itemId : ' +  param.itemId);
			console.log('param.mncoId : ' +  param.mncoId);
			console.log('param.itemTpCd : ' +  param.itemTpCd);
			console.log('param.voIssueCnt : ' +  param.voIssueCnt);
			console.log('param.mncoOtptUtPrc : ' +  param.mncoOtptUtPrc);
			console.log('param.poAmt : ' +  param.poAmt);
			console.log('param.lgstCentId : ' +  param.lgstCentId);
			console.log('param.dlgdAgreeDt : ' +  param.dlgdAgreeDt);
			console.log('param.dlvZipCd : ' +  param.dlvZipCd);
			console.log('param.dlvBssAddr : ' +  param.dlvBssAddr);
			console.log('param.dlvDtlAddr : ' +  param.dlvDtlAddr);

			$.ajax({
				url : 'addOrder.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.resule != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						refreshGrid();
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});

		});

		$('#btnConfirmOrder').click(function() {
			openConfirmCancelOrderPopup('1');
		});

		$('#btnCancelOrder').click(function() {
			openConfirmCancelOrderPopup('2');
		});

		// 초기화 클릭
		$('#btn_init').click(function() {
			reset();
		});

		// 등록 클릭
		$('#btn_insert').click(function() {

			var soId = $('#soId').val();
			var itemId = $('#itemId').val();
			var mncoId = $('#mncoId').val();
			var voTp = $('#voTp').val();
			var vissueCnt = $('#voIssueCnt').val();
			var voIssueDt = $('#voIssueDt').val();
			var voProdCd = $('#voProdCd').val();

			if (isEmpty(soId) == true) {
				alert('<spring:message code="MSG.M07.MSG00003" />');
				return;
			}

			if (isEmpty(itemId) == true) {
				alert('<spring:message code="MSG.M06.MSG00001" />');
				return;
			}

			if (isEmpty(voTp) == true) {
				alert('<spring:message code="MSG.M06.MSG00004" />');
				return;
			}

			if (voTp == '02' && isEmpty(voProdCd) == true) {
				alert('<spring:message code="MSG.M06.MSG00003" />');
				return;
			}

			if (isEmpty(vissueCnt) == true) {
				alert('<spring:message code="MSG.M06.MSG00002" />');
				return;
			}

			// var param = {
			// 	soId : soId
			// 	, itemId : itemId
			// 	, mncoId : mncoId
			// 	, voTp : voTp
			// 	, voProdCd : voProdCd
			// 	, voIssueCnt : vissueCnt
			// 	, voIssueDt : voIssueDt
			// };

			var param = new Object();

			param.soId = soId;
			param.itemId = itemId;
			param.mncoId = mncoId;
			param.voTp = voTp;
			param.voProdCd = voProdCd;
			param.voIssueCnt = vissueCnt;
			param.voIssueDt = voIssueDt;

			console.log('param.soId : ' + param.soId);
			console.log('param.itemId : ' + param.itemId);
			console.log('param.mncoId : ' + param.mncoId);
			console.log('param.voTp : ' + param.voTp);
			console.log('param.voProdCd : ' + param.voProdCd);
			console.log('param.voIssueCnt : ' + param.voIssueCnt);
			console.log('param.voIssueDt : ' + param.voIssueDt);

			$.ajax({
				url : 'saveVissueInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					var result = data.result;

					if (result == 0) {
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						refreshGrid();
					} else {
						var errorMsg = data.errorMsg;
						alert(errorMsg);
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});

		});

		$('#voTp').change(function() {
			console.log('voTp change');

			var voTp = $('#voTp').val();

			if (voTp == '01') {
				disableATag('btnSearchVoucherItem');
			} else if (voTp == '02') {
				enableATag('btnSearchVoucherItem');
			}
		});

	});
	
	function initGrid() {
		
		var param = new Object();
		
		$('#voucherGenerateTable').jqGrid({
			url : 'getVissueListAction.json?'
			, postData : param
			, mtype : 'POST'
			, datatype : 'json'

			, colModel : [

				// hidden data
				{ label : 'poNo', name : 'poNo', width : 0, hidden : true }
				, { label : 'lgstCentNm', name : 'lgstCentNm', width : 0, hidden : true }
				, { label : 'dlgdAgreeDt', name : 'dlgdAgreeDt', width : 0, hidden : true }
				, { label : 'soId', name : 'soId', width : 0, hidden : true }
				, { label : 'itemId', name : 'itemId', width : 0, hidden : true }
				, { label : 'mncoId', name : 'mncoId', width : 0, hidden : true }
				, { label : 'issueChkCnt', name : 'issueChkCnt', width : 0, hidden : true }
				, { label : 'poStat', name : 'poStat', width : 0, hidden : true }
				, { label : 'voTp', name : 'voTp', width : 0, hidden : true }
				, { label : 'vpProdCd', name : 'voProdCd', width : 0, hidden : true }
				, { label : 'mncoOtptUtPrc', name : 'mncoOtptUtPrc', width : 0, hidden : true }

				, { label : '<spring:message code="LAB.M07.LAB00003"/>', name : 'soNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00046"/>', name : 'vissueSeqNo', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00001"/>', name : 'itemNm', width : 100 }
				, { label : '<spring:message code="LAB.M09.LAB00098"/>', name : 'mncoNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00016"/>', name : 'voTpNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00007"/>', name : 'voIssueAmt', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00012"/>', name : 'voProdNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00009"/>', name : 'voIssueCnt', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00010"/>', name : 'voIssueDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00005"/>', name : 'voExpiredDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00035"/>', name : 'poDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00032"/>', name : 'poStatNm', width : 100 }
			]

		   	, rowNum:10					//한번에 노출되는 row 수
		   	, rowList:[5,10,20,30,50]	//선택시 노출되는 row 수
		   	, pager: '#voucherGeneratePager'
			, sortable : true			//드래그로 컬럼간의 위치 변경 가능 여부
		    , viewrecords: true	
			, height: 180				//화면 상태에 따라 크기 조절 할 것
			, multiselect : false
			, onCellSelect : function(rowId, index, contents, event){
				console.log('grid cell select');
				var data = $("#voucherGenerateTable").getRowData(rowId);

				if (data != null) {
					selectItemVissueInfo(data);
				}
				
	        },
	        loadComplete: function(obj){
	        	$("#voucherGenerateTable").setGridWidth($('#voucherGenerateGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        	var firstId = $("#voucherGenerateTable").getDataIDs()[0];

	        	if (firstId != null) {
	        		$("#voucherGenerateTable").jqGrid("setSelection", firstId);
					var data = $("#voucherGenerateTable").getRowData(firstId);
					selectItemVissueInfo(data);
	        	}
		    },
  			sortable: { update: function(permutation) {
        		$("#voucherGenerateTable").setGridWidth($('#voucherGenerateGrid').width(),false); //컬럼 위치 변경 후 재조정
    			}
    		}
		});
		
		$("#voucherGenerateTable").setGridWidth($('#voucherGenerateGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#voucherGenerateTable").setGridWidth($('#voucherGenerateGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function initGrid2(vissueSeqNo) {
		
		var param = new Object();
		param.vissueSeqNo = vissueSeqNo
		
		console.log('initGrid2 vissueSeqNo : ' + vissueSeqNo);
		
		$('#voucherDtlTable').jqGrid({
			url : 'getVissueDtlListAction.json?'
			, postData : param
			, mtype : 'POST'
			, datatype : 'json'

			, colModel : [
				{ label : '<spring:message code="LAB.M06.LAB00001" />', name : 'itemNm', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00161" />', name : 'voBarCd', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00015" />', name : 'voSeqNo', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00007" />', name : 'voIssueAmt', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00012" />', name : 'voProdNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00010" />', name : 'voIssueDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00005" />', name : 'voExpiredDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00011" />', name : 'voStatCd', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00047" />', name : 'orgNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00034" />', name : 'voPoDttm', formatter: stringTypeFormatterYYYYMMDDHH24MISS, align:'center', width : 100 }
				, { label : '<spring:message code="LAB.M08.LAB00162" />', name : 'voInDttm', formatter: stringTypeFormatterYYYYMMDDHH24MISS, align:'center', width : 100 }
			]

		   	, rowNum:10					//한번에 노출되는 row 수
		   	, rowList:[5,10,20,30,50]	//선택시 노출되는 row 수
		   	, pager: '#voucherDtlPager'
			, sortable : true			//드래그로 컬럼간의 위치 변경 가능 여부
		    , viewrecords: true	
			, height: 180				//화면 상태에 따라 크기 조절 할 것
			, multiselect : false
			, onCellSelect : function(rowId, index, contents, event){

				
	        },
	        loadComplete: function(obj){
	        	console.log(obj);
	        	$("#voucherDtlTable").setGridWidth($('#voucherDtlGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
  			sortable: { update: function(permutation) {
        		$("#voucherDtlTable").setGridWidth($('#voucherDtlGrid').width(),false); //컬럼 위치 변경 후 재조정
    			}
    		}
		});
		
		$("#voucherDtlTable").setGridWidth($('#voucherDtlGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#voucherDtlTable").setGridWidth($('#voucherDtlGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
		
	}

	function setVoucherSearchData(data) {

		$('#itemId').val(data.itemId);
		$('#itemNm').val(data.itemNm);
		$('#mncoId').val(data.mncoId);
		$('#mncoNm').val(data.mncoNm);

		if (data.itemKndCd == '301') {
			// 금액기준
			$('#voTp').val('01');
			$('#voTp').selectmenu("refresh");
			// $('#voTp').selectmenu('enable');
			$('#voIssueAmt').val(data.mncoOtptUtPrc);
			disableATag('btnSearchVoucherItem');
		} else if (data.itemKndCd == '302') {
			// 상품기준
			$('#voTp').val('02');
			$('#voTp').selectmenu("refresh");
			// $('#voTp').selectmenu('disable');
			enableATag('btnSearchVoucherItem');
		}

	}

	function reset() {
		$("#areaDtl input").val("");
		$("#areaDtl select").val("");
		$("#areaDtl select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬

		disableButtons();
		disableInputFields();
		enableInputField('voIssueCnt');
		enableInputField('voIssueDt');

		disableATag('btnSearchVoucherItem');
		enableATag('btnSearchVoucher');

		enableButton('btn_init');
		enableButton('btn_insert');
		enableButton('btnTransfer');
	}

	function searchVissue() {
		var soId = $('#searchSoId').val();
		var mncoId = $('#searchMncoId').val();
		var voTp = $('#searchVoTpCd').val();
		var poStat = $('#searchPoPrgrStatCd').val();

		var param = {
			soId : soId
			, mncoId : mncoId
			, voTp : voTp
			, poStat : poStat
		};

		$("#voucherGenerateTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
	}
	
	function searchVissueDtl(soId, vissueSeqNo) {
		
		$("#voucherDtlTable").clearGridData();  // 이전 데이터 삭제
		var param = {
			soId : soId
			, vissueSeqNo : vissueSeqNo	
		}
		
        $("#voucherDtlTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
	}
	
	function selectItemVissueInfo(data) {
		var soId = data.soId;
		var vissueSeqNo = data.vissueSeqNo;

		console.log('before searchVissueDtl');

		searchVissueDtl(soId, vissueSeqNo);

		console.log('after searchVissueDtl');
		
		var soId = data.soId;
		var vissueSeqNo = data.vissueSeqNo;
		var itemNm = data.itemNm;
		var mncoNm = data.mncoNm;
		var voTp = data.voTp;
		var voIssueAmt = data.voIssueAmt;
		var voProdCd = data.voProdCd;
		var voProdNm = data.voProdNm;
		var voIssueCnt = data.voIssueCnt;
		var voIssueDt = data.voIssueDt;
		var voExpiredDt = data.voExpiredDt;
		var poDt = data.poDt;
		var poStatNm = data.poStatNm;
		var lgstCentNm = data.lgstCentNm;
		var dlgdAgreeDt = data.dlgdAgreeDt;
		var issueChkCnt = data.issueChkCnt;
		var poStat = data.poStat;

		$("#soId").val(data.soId);
		$('#soId').selectmenu("refresh");
 		$('#vissueSeqNo').val(vissueSeqNo);
		$('#itemNm').val(itemNm);
		$('#mncoNm').val(mncoNm);
		$('#voTp').val(voTp);
		$('#voIssueAmt').val(voIssueAmt);
		$('#voProdCd').val(voProdCd);
		$('#voProdNm').val(voProdNm);
		$('#voIssueCnt').val(voIssueCnt);
		$('#voIssueDt').val(voIssueDt);
		$('#voExpiredDt').val(voExpiredDt);
		$('#poDt').val(poDt);
		$('#poStatNm').val(poStatNm);
		$('#lgstCentNm').val(lgstCentNm);
		$('#dlgdAgreeDt').val(dlgdAgreeDt);

		$("#areaDtl select" ).selectmenu( "refresh" );

		disableInputFields();
		disableButtons();
		disableSearchButtons();
		enableButton('btn_init');
		enableButton('btnTransfer');
		// 발행 이전
		if (issueChkCnt == 0) {
			enableButton('btnIssueBulk');
		} else {

			if (poStat == '00') {
	        	enableButton('btnAddOrder');
	        	enableInputField('voIssueCnt');
	        	enableInputField('voTp');
	        	enableInputField('voIssueDt');
	        	enableInputField('poDt');
	        	enableInputField('dlgdAgreeDt');
	        	enableATag('btnSearchVoucherItem');
	        	enableATag('btnSearchLgstCent');
	        	enableATag('btnSearchAddr');
	        	enableInputField('dtlAddr');
	        	// enableATag('voIssueDtSel');
	        	// enableATag('voExpiredDtSel');
	        	// enableATag('poDt');
	        	// enableATag('btnSearchLgstCent');
	        	// enableATag('dlgdAgreeDtSel');
			} else if (poStat == '10') {
				enableButton('btnConfirmOrder');
			} else if (poStat == '20') {
				enableButton('btnCancelOrder');
			}
		}

		disableButton('btn_insert');
		disableButton('btn_update');
		disableButton('btn_delete');
		
	}
	
	function disableButton(id) {
		$('#' + id).addClass('white-btn');
		$('#' + id).removeClass('grey-btn');
		$('#' + id).addClass('not-active');
		$('#' + id).attr('disabled',true);
	}

	function enableButton(id) {
		$('#' + id).addClass('grey-btn');
		$('#' + id).removeClass('white-btn');
		$('#' + id).removeClass('not-active');
		$('#' + id).removeAttr('disabled');
	}

	function disableInputField(id) {
		$("#" + id).addClass('not-active'); 
		$("#" + id).attr('disabled', true);
	}

	function enableInputField(id) {
		$("#" + id).removeClass('not-active');
		$("#" + id).removeAttr('disabled');
	}

	function disableATag(id) {
		$('#' + id).addClass('not-active');
		$('#' + id).attr('disabled',true);
	}

	function enableATag(id) {
		$('#' + id).removeClass('not-active');
		$('#' + id).removeAttr('disabled');
	}

	function disableInputFields() {
		disableInputField('vissueSeqNo');
		disableInputField('itemNm');
		disableInputField('mncoNm');
		disableInputField('voTpNm');
		disableInputField('voIssueAmt');
		disableInputField('voProdCd');
		disableInputField('voIssueCnt');
		disableInputField('voIssueDt');
		disableInputField('voExpiredDt');
		disableInputField('poDt');
		disableInputField('poStatNm');
		disableInputField('lgstCentNm');
		disableInputField('dlgdAgreeDt');
		disableInputField('zipCd');
		disableInputField('bssAddr');
		disableInputField('dtlAddr');
	}

	function disableButtons() {
		disableButton('btnIssueBulk');
		disableButton('btnTransfer');
		disableButton('btnAddOrder');
		disableButton('btnConfirmOrder');
		disableButton('btnCancelOrder');

		disableButton('btn_init');
		disableButton('btn_insert');
		disableButton('btn_update');
		disableButton('btn_delete');

		// disableATag('voIssueDtSel');
		// disableATag('voExpiredDtSel');
		// disableATag('poDtSel');
		// disableATag('dlgdAgreeDtSel');
		// disableATag('btnSearchLgstCent');
		// disableATag('btnSearchVoucher');
	}

	function disableSearchButtons() {
		disableATag('btnSearchVoucherItem');
		disableATag('btnSearchVoucher');
		disableATag('btnSearchLgstCent');
		disableATag('btnSearchAddr');
	}
	
	//팝업창 modal로 열기
	function openModal(id, url, param) {
		
		//popType m : 모달팝어 o : 일반팝업
		$.ajax({
			type : "post",
			url : url,
			data : param,
			async: true,
			success : function(data) {
				var html = data;
				$("#" + id).html(html);
			},		
			complete : function(){
				wrapWindowByMask(); // 팝업 오픈
			}
		}); 
		
	}

	function isEmpty(str) {
		if (str == null || str == '') {
			return true;
		}

		return false;
	}

	function searchAddr(){
		
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

	function refreshGrid() {
		$('#voucherGenerateTable').trigger( 'reloadGrid' );
		$('#voucherDtlTable').trigger( 'reloadGrid' );
	}

	function openConfirmCancelOrderPopup(popupType) {
		var id = $("#voucherGenerateTable").jqGrid("getGridParam", "selrow" );

		if(id == null){
			// 바우처를 선택해주세요.
			alert('<spring:message code="MSG.M06.MSG00001" />');
			return;
		}

		var row = $("#voucherGenerateTable").getRowData(id);
	
		var soId = row.soId;
		var vissueSeqNo = row.vissueSeqNo
		var poNo = row.poNo;
		var itemId = row.itemId;
		var itemNm = row.itemNm;
		var mncoId = row.mncoId;
		var mncoNm = row.mncoNm;
		var poStat = row.poStat;
		var poDt = row.poDt;

		var param = {
			soId : soId
			, vissueSeqNo : vissueSeqNo
			, poNo : poNo
			, itemId : itemId
			, itemNm : itemNm
			, mncoId : mncoId
			, mncoNm : mncoNm
			, poStat : poStat
			, poDt : poDt
			, popupType : popupType
		};

		console.log('soId : ' + soId);
		console.log('vissueSeqNo : ' + vissueSeqNo);
		console.log('poNo : ' + poNo);
		console.log('itemId : ' + itemId);
		console.log('itemNm : ' + itemNm);
		console.log('mncoId : ' + mncoId);
		console.log('mncoNm : ' + mncoNm);
		console.log('poStat : ' + poStat);
		console.log('poDt : ' + poDt);

		var url = "/distributor/logistics/voucherMng/voucherGenerate/confirmCancelOrderPopup.ajax";

		$("#popup_dialog").css("width", 700);
		openModal('popup_dialog', url, param);
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
			<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
			<td>
				<select id="searchVoTpCd" name="searchVoTpCd" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${voTpCdList}" var="voTpCd" varStatus="status">
						<option value="${voTpCd.commonCd}">${voTpCd.commonCdNm}</option>
					</c:forEach>
				</select>                                        
			</td>
			<th><spring:message code="LAB.M06.LAB00039"/><!-- 발주진행상태 --></th>
			<td>
				<select id="searchPoPrgrStatCd" name="searchPoPrgrStatCd" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${poPrgrStatCd}" var="poPrgrStatCd" varStatus="status">
						<option value="${poPrgrStatCd.commonCd}">${poPrgrStatCd.commonCdNm}</option>
					</c:forEach>
				</select>                
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M06.LAB00002"/><!-- 발주내역 --></h4>
	</div>
</div>

<div id="voucherGenerateGrid">
	<table id="voucherGenerateTable" class="w100p"></table>
	<div id="voucherGeneratePager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M06.LAB00003"/><!-- 발주내역 --></h4>
	</div>
</div>

<div id="voucherDtlGrid">
	<table id="voucherDtlTable" class="w100p"></table>
	<div id="voucherDtlPager"></div>
</div>

<form id="manufactureForm" name="manufactureForm" method="post" enctype="multipart/form-data">

	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00101"/><!-- 상세정보 --></h4>
		</div>
	</div>
	
	<table class="writeview" id="areaDtl">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
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
				<th><spring:message code="LAB.M06.LAB00046"/><!-- 발행일련번호 --></th>
				<td>                                                
					<input type="text" id="vissueSeqNo" class="w270 txt_r">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00001"/><!-- 바우처 --><span class="dot">*</span></th>
				<td> 
					<div class="inp_date w280">
						<input type="hidden" id="itemId" name="itemId" />
						<input type="text" id="itemNm" name="itemNm" class="w250" disabled="disabled" />
						<a href="#" id="btnSearchVoucher" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사명 --></th>
				<td>                                                
					<input type="text" id="mncoNm" class="w270 txt_r">
					<input type="hidden" id="mncoId" class="w270 txt_r">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00016"/><!-- 바우처종류 --><span class="dot">*</span></th>
				<td>
					<select class="w150" id="voTp" name="voTpCd">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${voTpCdList}" var="voTpCd" varStatus="status">
							<option value="${voTpCd.commonCd}">${voTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M06.LAB00007"/><!-- 바우처발행금액 --></th>
				<td>                                                
					<input type="text" id="voIssueAmt" class="w270 txt_r">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00012"/><!-- 바우처상품 --></th>
				<td>
					<div class="inp_date w280">
						<input type="hidden" id="voProdCd" name="voProdCd" />
						<input type="text" id="voProdNm" name="voProdNm" class="w250" disabled="disabled" />
						<a href="#" id="btnSearchVoucherItem" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M06.LAB00009"/><!-- 바우처발행수 --><span class="dot">*</span></th>
				<td>                                                
					<input type="text" id="voIssueCnt" class="w270 txt_r">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00010"/><!-- 바우처발행일자 --></th>
				<td>
					<div class="inp_date w270">
						<input type="text" id="voIssueDt" name="voIssueDt" class="datepicker" readonly="readonly" />
						<a href="#" id="voIssueDtSel" class="btn_cal"></a>
					</div>
				</td>
				<th><spring:message code="LAB.M06.LAB00005"/><!-- 바우처만료일자 --></th>
				<td>
					<div class="inp_date w270">
						<input type="text" id="voExpiredDt" name="voExpiredDt" class="datepicker" readonly="readonly" />
						<a href="#" id="voExpiredDtSel" class="btn_cal"></a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00032"/><!-- 발주상태 --></th>
				<td>                                                
					<input type="text" id="poStatNm" class="w270 txt_r">
				</td>
				<th><spring:message code="LAB.M06.LAB00035"/><!-- 발주일자 --></th>
				<td>                                                
					<div class="inp_date w270">
						<input type="text" id="poDt" name="poDt" class="datepicker" readonly="readonly" />
						<a href="#" id="poDtSel" class="btn_cal"></a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M05.LAB00042"/><!-- 물류센터 --></th>
				<td>
					<div class="inp_date w280">
						<input type="hidden" id="lgstCentId" name="lgstCentId" />
						<input type="text" id="lgstCentNm" name="lgstCentNm" class="w250" disabled="disabled" />
						<a href="#" id="btnSearchLgstCent" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M06.LAB00042"/><!-- 납품합의일자 --></th>
				<td>                                                
					<div class="inp_date w270">
						<input type="text" id="dlgdAgreeDt" name="dlgdAgreeDt" class="datepicker" readonly="readonly" />
						<a href="#" id="dlgdAgreeDtSel" class="btn_cal"></a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00049"/><!-- 배송지주소 --><span class="dot">*</span></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="zipCd" name="zipCd" class="p100" />
							 <a id="btnSearchAddr" href="#" class="search"><spring:message code="LAB.M09.LAB00191" /></a>
						</div>
						<div class="inp_date w85p">
							<input type="text" id="bssAddr" name="bssAddr" class="w40p" /> 
							<input type="text" id="dtlAddr" name="dtlAddr" class="w55p" checkbyte="450" />
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	
</form>

<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnIssueBulk" title="<spring:message code='LAB.M06.LAB00045'/>" href='#'><spring:message code="LAB.M06.LAB00045" /><!-- 발행 --></a>
		<a class="grey-btn" id="btnTransfer" title="<spring:message code='LAB.M08.LAB00118'/>" href='#'><spring:message code="LAB.M08.LAB00118" /><!-- 이관 --></a>
		<a class="grey-btn" id="btnAddOrder" title="<spring:message code='LAB.M06.LAB00029'/>" href='#'><spring:message code="LAB.M06.LAB00029" /><!-- 발주등록 --></a>
		<a class="grey-btn" id="btnConfirmOrder" title="<spring:message code='LAB.M06.LAB00043'/>" href='#'><spring:message code="LAB.M06.LAB00043" /><!-- 발주확정 --></a>
		<a class="grey-btn" id="btnCancelOrder" title="<spring:message code='LAB.M06.LAB00041'/>" href='#'><spring:message code="LAB.M06.LAB00041" /><!-- 발주취소 --></a>
	</div>
	
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
	</div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>

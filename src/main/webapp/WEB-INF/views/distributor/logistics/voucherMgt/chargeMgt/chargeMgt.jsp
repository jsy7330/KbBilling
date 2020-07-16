<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		initGrid();
		initGrid2('');
		
		$('#btnSearch').click(function() {
			searchCtrtInfo();
		});

		$('#btnSearchCust').click(function() {
			var url="customerSearchPopup.ajax";
			var param = new Object();
			param.popupType = 'm';

			$("#popup_dialog").css("width", 800);
			openModal('popup_dialog', url, param);
		})

		// 발행 클릭
		$('#btnCharge').click(function() {

			var index  = $("#tgtCtrtInfoTable").jqGrid("getGridParam", "selrow" );

			if(index == null || index == ''){
				alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
				return;
			}

			var data = $("#tgtCtrtInfoTable").getRowData(index);

			var param = {
				soId : data.soId
				, soNm : data.soNm
				, crttId : data.ctrtId
				, custId : data.custId
				, svcTelNo : data.svcTelNo
				, basicProdGrp : data.basicProdGrp
				, prodCd : data.prodCd
				, qosAmt : data.qosAmt
			};

			// var param = {};

			var url = "chargePopup.ajax";
			$("#popup_dialog").css("width", 870);
			openModal('popup_dialog', url, param);


		});

		$('#btnCancelCharge').click(function() {
			var index  = $("#chargeInfoTable").jqGrid("getGridParam", "selrow" );

			if(index == null || index == ''){
				alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
				return;
			}

			var data = $("#chargeInfoTable").getRowData(index);

			// (수정)선물하기는 취소 불가
			if (data.rchrgTp == '400') {
				// 충전취소 대상이 아닙니다.
				alert('<spring:message code="MSG.M10.MSG00029" />');
				return;
			}

			if (data.rchrgStat == '30') {
				// 충전취소된 대상입니다.
				alert('<spring:message code="MSG.M10.MSG00031" />');
				return;
			}

			if (data.cancleCnt == '1') {
				// 이미 충전취소되었습니다.
				alert('<spring:message code="MSG.M08.MSG00052" />');
				return;
			}
	
			var today = new Date();
			// 바우처만료일자
			var expDt = new Date(data.voExpiredDt);
			// 종료일자
			var chgDt = new Date(data.rchrgChgDt);
			// RCHRG_DT 6개월
			var rchrgDt6m = new Date(data.rchrgDt6m);

			if (data.rchrgTp == '300') {
				if (expDt.getTime() < today.getTime() && chgDt.getTime() < today.getTime()) {
					// 만료된 바우쳐는 충전할 수 없습니다
					alert('<spring:message code="MSG.M05.MSG00001" />');
					return;
				}
			}

			if (data.ifSucYn == 'N') {
				// Provision 인터페이스 진행 중입니다. 충전취소를 할 수 없습니다.
				alert('<spring:message code="MSG.M15.MSG00029" />');
				return;
			}

			if ((data.rchrgTp == '100' && data.apprTp == '02') || (data.rchrgTp == '100' && data.apprTp == '03')) {
				if (rchrgDt6m.getTime() < today.getTime()) {
					// 카드결제는 결제일 6개월 이전인 경우 취소가능합니다.
					alert('<spring:message code="MSG.M11.MSG00001" />');
					return;
				}
			}

			var param = data;

			var url = "cancelPopup.ajax";
			$("#popup_dialog").css("width", 870);
			openModal('popup_dialog', url, param);


		});

	});
	
	function initGrid() {
		
		var param = new Object();
		
		$('#tgtCtrtInfoTable').jqGrid({
			url : 'ctrtInfoListAction.json?'
			, postData : param
			, mtype : 'POST'
			, datatype : 'json'

			, colModel : [

				{ label : 'custId', name : 'custId', width : 0, hidden : true }
				, { label : 'prodCd', name : 'prodCd', width : 0, hidden : true }
				, { label : 'basicProdGrp', name : 'basicProdGrp', width : 0, hidden : true }
				, { label : 'soId', name : 'soId', width : 0, hidden : true }
				, { label : 'ctrtStat', name : 'ctrtStat', width : 0, hidden : true }
				, { label : 'ctrtStatNm', name : 'ctrtStatNm', width : 0, hidden : true }
				, { label : 'qosAmt', name : 'qosAmt', width : 0, hidden : true }

				, { label : '<spring:message code="LAB.M07.LAB00003" />', name : 'soNm', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00032" />', name : 'ctrtId', width : 100 }
				, { label : '<spring:message code="LAB.M07.LAB00186" />', name : 'svcTelNo', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00037" />', name : 'ctrtStatNm', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00020" />', name : 'openDd', width : 100 }
				, { label : '<spring:message code="LAB.M07.LAB00130" />', name : 'prodNm', width : 100 }
			]

		   	, rowNum:10					//한번에 노출되는 row 수
		   	, rowList:[5,10,20,30,50]	//선택시 노출되는 row 수
		   	, pager: '#tgtCtrtInfoPager'
			, sortable : true			//드래그로 컬럼간의 위치 변경 가능 여부
		    , viewrecords: true	
			, height: 180				//화면 상태에 따라 크기 조절 할 것
			, multiselect : false
			, onCellSelect : function(rowId, index, contents, event){
				var data = $("#tgtCtrtInfoTable").getRowData(rowId);
				selectItemVissueInfo(data);
				
	        },
	        loadComplete: function(obj){
	        	$("#tgtCtrtInfoTable").setGridWidth($('#tgtCtrtInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        	var firstId = $("#tgtCtrtInfoTable").getDataIDs()[0];

	        	if (firstId != null) {
	        		$("#tgtCtrtInfoTable").jqGrid("setSelection", firstId);
					var data = $("#tgtCtrtInfoTable").getRowData(firstId);
					selectItemVissueInfo(data);
	        	}
		    },
  			sortable: { update: function(permutation) {
        		$("#tgtCtrtInfoTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
    			}
    		}
		});
		
		$("#tgtCtrtInfoTable").setGridWidth($('#tgtCtrtInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#tgtCtrtInfoTable").setGridWidth($('#tgtCtrtInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function initGrid2(vissueSeqNo) {
		
		var param = new Object();

		var gridWidth = $('#chargeInfoGrid').width();
		var displayColumnCount = 25;

		var columnWidth = gridWidth / displayColumnCount;
		
		$('#chargeInfoTable').jqGrid({
			url : 'chrgHistListAction.json?'
			, postData : param
			, mtype : 'POST'
			, datatype : 'json'

			, colModel : [

				{ label : 'cancleCnt', name : 'cancleCnt', width : 0, hidden : true }
				, { label : 'voExpiredDt', name : 'voExpiredDt', width : 0, hidden : true }
				, { label : 'rchrgDt6m', name : 'rchrgDt6m', width : 0, hidden : true }
				, { label : 'apprTp', name : 'apprTp', width : 0, hidden : true }
				, { label : 'prodId', name : 'prodId', width : 0, hidden : true }
				, { label : 'custId', name : 'custId', width : 0, hidden : true }
				, { label : 'ctrtId', name : 'ctrtId', width : 0, hidden : true }

				, { label : '<spring:message code="LAB.M10.LAB00084" />', name : 'rchrgSeqNo', width : columnWidth }
				, { label : '<spring:message code="LAB.M10.LAB00085" />', name : 'rchrgTp', width : columnWidth }
				, { label : '<spring:message code="LAB.M07.LAB00273" />', name : 'apprTpNm', width : columnWidth }
				, { label : '<spring:message code="LAB.M07.LAB00110" />', name : 'rchrgStat', width : columnWidth }
				, { label : '<spring:message code="LAB.M10.LAB00086" />', name : 'rchrgDt', width : columnWidth }
				, { label : '<spring:message code="LAB.M09.LAB00169" />', name : 'rchrgTermDt', width : columnWidth }
				, { label : '<spring:message code="LAB.M10.LAB00092" />', name : 'rchrgChgDt', width : columnWidth }
				, { label : '<spring:message code="LAB.M10.LAB00090" />', name : 'rchrgCnt', width : columnWidth }
				, { label : '<spring:message code="LAB.M10.LAB00083" />', name : 'chrgAmt', width : columnWidth }
				, { label : '<spring:message code="LAB.M07.LAB00083" />', name : 'delFlg', width : columnWidth }
				, { label : '<spring:message code="LAB.M08.LAB00145" />', name : 'thrwyChrgId', width : columnWidth }
				, { label : '<spring:message code="LAB.M07.LAB00130" />', name : 'prodNm', width : columnWidth }
				, { label : '<spring:message code="LAB.M03.LAB00049" />', name : 'trgtCustNm', width : columnWidth }
				, { label : '<spring:message code="LAB.M03.LAB00047" />', name : 'trgtCtrtId', width : columnWidth }
				, { label : '<spring:message code="LAB.M03.LAB00054" />', name : 'trgtSvcTelNo', width : columnWidth }
				, { label : '<spring:message code="LAB.M06.LAB00015" />', name : 'voSeqNo', width : columnWidth }
				, { label : '<spring:message code="LAB.M06.LAB00004" />', name : 'voBarCd', width : columnWidth }
				, { label : '<spring:message code="LAB.M07.LAB00266" />', name : 'apprNo', width : columnWidth }
				, { label : '<spring:message code="LAB.M11.LAB00015" />', name : 'dataQuota', width : columnWidth }
				, { label : '<spring:message code="LAB.M08.LAB00136" />', name : 'ifSucYn', width : columnWidth }
				, { label : '<spring:message code="LAB.M08.LAB00137" />', name : 'ifRsnCd', width : columnWidth }
				, { label : '<spring:message code="LAB.M03.LAB00082" />', name : 'regrId', width : columnWidth }
				, { label : '<spring:message code="LAB.M03.LAB00080" />', name : 'regDate', width : columnWidth }
				, { label : '<spring:message code="LAB.M07.LAB00256" />', name : 'chgrId', width : columnWidth }
				, { label : '<spring:message code="LAB.M07.LAB00254" />', name : 'chgDate', width : columnWidth }
			]

		   	, rowNum:10					//한번에 노출되는 row 수
		   	, rowList:[5,10,20,30,50]	//선택시 노출되는 row 수
		   	, pager: '#chargeInfoPager'
			, sortable : true			//드래그로 컬럼간의 위치 변경 가능 여부
		    , viewrecords: true	
			, height: 180				//화면 상태에 따라 크기 조절 할 것
			, multiselect : false
			, onCellSelect : function(rowId, index, contents, event){

				
	        },
	        loadComplete: function(obj){
	        	$("#chargeInfoTable").setGridWidth($('#chargeInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    }
		});
		
		$("#chargeInfoTable").setGridWidth($('#chargeInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#chargeInfoTable").setGridWidth($('#chargeInfoGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
		
	}

	function searchCtrtInfo() {

		var soId = $('#searchSoId').val();
		var custId = $('#searchCustId').val();

		var param = {
			soId : soId
			, custId : custId
		};

		$("#tgtCtrtInfoTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
	}
	
	function searchChrgHistList(data) {
		var param = data;

		$("#chargeInfoTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
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

	function refreshGrid() {
		$('#tgtCtrtInfoTable').trigger( 'reloadGrid' );
		$('#chargeInfoTable').trigger( 'reloadGrid' );
	}

	function setCustomerInfo(data) {
		$('#searchCustNm').val(data.custNm);
		$('#searchCustId').val(data.custId);
		$('#searchCustTp').text(data.custTpNm);
		$('#searchMtelNo').text(data.mtelNo);
		$('#searchCorpRegNo').text(data.corpRegNo);
		$('#searchBizRegNo').text(data.bizRegNo);
		$('#searchSoId').val(data.soId);
		$('#searchSoId').selectmenu("refresh");

		searchCtrtInfo();
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
				<select class="w150" id="searchSoId" name="searchSoId">형
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>   
			</td>
			<th><spring:message code="LAB.M01.LAB00050"/><!-- 고객명 --></th>
			<td>
				<div class="inp_date w250">
					<span>
						<input type="text" id="searchCustNm" name="searchItemNm" class="w200" disabled="disabled" />
						<input type="hidden" id="searchCustId" name="searchItemId" />
					</span>
					<a href="#" id="btnSearchCust" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M01.LAB00053"/><!-- 고객유형 --></th>
			<td><span id="searchCustTp"></span></td>
			<th><spring:message code="LAB.M14.LAB00076"/><!-- 휴대폰번호 --></th>
			<td><span id="searchMtelNo"></span></td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00188"/><!-- 주민/법인번호 --></th>
			<td><span id="searchCorpRegNo"></span></td>
			<th><spring:message code="LAB.M07.LAB00014"/><!-- 발주진행상태 --></th>
			<td><span id="searchBizRegNo"></span></td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M03.LAB00048"/><!-- 대상계약정보 --></h4>
	</div>
</div>

<div id="tgtCtrtInfoGrid">
	<table id="tgtCtrtInfoTable" class="w100p"></table>
	<div id="tgtCtrtInfoPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M10.LAB00087"/><!-- 충전정보 --></h4>
	</div>
</div>

<div id="chargeInfoGrid">
	<table id="chargeInfoTable" class="w100p"></table>
	<div id="chargeInfoPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnCharge" title="<spring:message code='LAB.M10.LAB00082'/>" href='#'><spring:message code="LAB.M10.LAB00082" /><!-- 충전 --></a>
		<a class="grey-btn" id="btnCancelCharge" title="<spring:message code='LAB.M10.LAB00089'/>" href='#'><spring:message code="LAB.M10.LAB00089" /><!-- 충전취소 --></a>
	</div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>

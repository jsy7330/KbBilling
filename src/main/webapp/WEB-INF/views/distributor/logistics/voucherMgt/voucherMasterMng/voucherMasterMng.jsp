<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {

		$('#btnSearch').click(function() {
			searchVoucherMasterInfo();
		});

		$('#btnSearchProduct').click(function() {
			var url="/distributor/logistics/voucherMng/voucherMasterMng/voucherSearchPopup.ajax";
			var param = {
				soId : $("#searchSoId").val()
				, returnVissueSeqNo : 'searchVissueSeqNo'
				, returnItemId : 'searchItemId'
				, returnItemNm : 'searchItemNm'
				, itemId : $('#searchItemId').val()
				, itemNm : $('#searchItemNm').val()
			};
			
			$("#popup_dialog").css("width", 800);
			openModal('popup_dialog', url, param);
		});

		$('#btnSale').click(function() {
			// var id = $("#voucherMasterTable").jqGrid("getGridParam", "selrow" );

			// if(id == null){
			// 	// 바우처를 선택해주세요.
			// 	alert('<spring:message code="MSG.M06.MSG00001" />');
			// 	return;
			// }

			// var row = $("#voucherMasterTable").getRowData(id);

			// var soId = row.soId;
			// var soNm = row.soNm;
			// var vissueSeqNo = row.vissueSeqNo;
			// var ownLocCd = row.ownLocCd;
			// var ownLocNm = row.ownLocNm;
			// 테스트

			var soId = '00';
			var soNm = 'nTels';
			var vissueSeqNo = '1234567890';
			var ownLocCd = '1234';
			var ownLocNm = '테스트';

			var param = {
				soId : soId
				, soNm : soNm
				, vissueSeqNo : vissueSeqNo
				, ownLocCd : ownLocCd
				, ownLocNm : ownLocNm
			};

			console.log('soId : ' + soId);
			console.log('soNm : ' + soNm);
			console.log('vissueSeqNo : ' + vissueSeqNo);
			console.log('ownLocCd : ' + ownLocCd);
			console.log('ownLocNm : ' + ownLocNm);

			var url="/distributor/logistics/voucherMng/voucherMasterMng/saleProcessVeqtSearchPopup.ajax";

			$("#popup_dialog").css("width", 800);
			openModal('popup_dialog', url, param);

		});

		initGrid();

	});

	function initGrid() {

		var param = {};

		$('#voucherMasterTable').jqGrid({
			url : 'voucherMasterInfoAction.json?'
			, postData : param
			, mtype : 'POST'
			, datatype : 'json'

			, colModel : [

				{ label : 'soId', name : 'soId', width : 0, hidden : true }
				, { label : 'vissueSeqNo', name : 'vissueSeqNo', width : 0, hidden : true }
				, { label : 'ownLocCd', name : 'ownLocCd', width : 0, hidden : true }

				, { label : '<spring:message code="LAB.M07.LAB00003"/>', name : 'soNm', width : 100 }
				, { label : '<spring:message code="LAB.M09.LAB00098"/>', name : 'mncoNm', width : 100 }
				, { label : '<spring:message code="LAB.M09.LAB00105"/>', name : 'itemNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00016"/>', name : 'voTpNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00015"/>', name : 'voSeqNo', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00161"/>', name : 'voBarCd', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00011"/>', name : 'voStatCd', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00010"/>', name : 'voIssueDt', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00005"/>', name : 'voExpiredDt', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00007"/>', name : 'voIssueAmt', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00012"/>', name : 'voProdNm', width : 100 }
				, { label : '<spring:message code="LAB.M05.LAB00046"/>', name : 'lgstProcStatNm', width : 100 }
				, { label : '<spring:message code="LAB.M05.LAB00047"/>', name : 'lgstProcDttm', width : 100 }
				, { label : '<spring:message code="LAB.M07.LAB00213"/>', name : 'ownLocNm', width : 100 }
				, { label : '<spring:message code="LAB.M05.LAB00045"/>', name : 'frstInDt', width : 100 }
				, { label : '<spring:message code="LAB.M03.LAB00044"/>', name : 'agcInDt', width : 100 }
				, { label : '<spring:message code="LAB.M05.LAB00005"/>', name : 'prchsUtPrc', width : 100 }
				, { label : '<spring:message code="LAB.M08.LAB00095"/>', name : 'dstrbUtPrc', width : 100 }
				, { label : '<spring:message code="LAB.M15.LAB00026"/>', name : 'remark', width : 100 }
				, { label : '<spring:message code="LAB.M03.LAB00082"/>', name : 'regrId', width : 100 }
				, { label : '<spring:message code="LAB.M03.LAB00080"/>', name : 'regDate', width : 100 }
				, { label : '<spring:message code="LAB.M07.LAB00256"/>', name : 'chgrId', width : 100 }
				, { label : '<spring:message code="LAB.M07.LAB00254"/>', name : 'chgDate', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00008"/>', name : 'selectVoucher', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00006"/>', name : 'listVoucher', width : 100 }
			]

		   	, rowNum:10					//한번에 노출되는 row 수
		   	, rowList:[5,10,20,30,50]	//선택시 노출되는 row 수
		   	, pager: '#voucherMasterPager'
			, sortable : true			//드래그로 컬럼간의 위치 변경 가능 여부
		    , viewrecords: true	
			, height: 360				//화면 상태에 따라 크기 조절 할 것
			, multiselect : false
			, onCellSelect : function(rowId, index, contents, event){
				var data = $("#voucherMasterTable").getRowData(rowId);
				selectItemVissueInfo(data);
				
	        },
	        loadComplete: function(obj){
	        	$("#voucherMasterTable").setGridWidth($('#voucherMasterGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        	var firstId = $("#voucherMasterTable").getDataIDs()[0];

	        	if (firstId != null) {
	        		$("#voucherMasterTable").jqGrid("setSelection", firstId);
					var data = $("#voucherMasterTable").getRowData(firstId);
					selectItemVissueInfo(data);
	        	}
		    },
  			sortable: { update: function(permutation) {
        		$("#voucherMasterTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
    			}
    		}
		});
		
		$("#voucherMasterTable").setGridWidth($('#voucherMasterGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#voucherMasterTable").setGridWidth($('#voucherMasterGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}

	function searchVoucherMasterInfo() {
		var soId = $('#searchSoId').val();
		var itemId = $('#searchItemId').val();
		var vissueSeqNo = $('#searchVissueSeqNo').val();
		var poStat = $('#searchPoStat').val();
		var voBarCd = $('#searchVoBarCd').val();

		if (itemId == null || itemId == '') {
			alert('<spring:message code="MSG.M06.MSG00005"/>');
			return;
		}

		var param = {
			soId : soId
			, itemId : itemId
			, vissueSeqNo : vissueSeqNo
			, poStat : poStat
			, voBarCd : voBarCd
		};

		$("#voucherMasterTable").clearGridData();  // 이전 데이터 삭제		
        $("#voucherMasterTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
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
			<th><spring:message code="LAB.M09.LAB00105"/><!-- 제품 --><span class="dot">*</span></th>
			<td> 
				<div class="inp_date w380">
					<span>
						<input type="text" id="searchItemNm" name="searchItemNm" class="w170" disabled="disabled">
						<input type="text" id="searchItemId" name="searchItemId" class="w170" disabled="disabled">
					</span>
					<a href="#" id="btnSearchProduct" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="MSG.M06.MSG00018"/><!-- 발행일련번호 --></th>
			<td>
				<input type="text" id="searchVissueSeqNo" name="searchVissueSeqNo" class="w250" />
			</td>
			<th><spring:message code="LAB.M06.LAB00011"/><!-- 바우처상태 --></th>
			<td>
				<select id="searchPoStat" name="searchPoStat" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${poStatList}" var="poStat" varStatus="status">
						<option value="${poStat.commonCd}">${poStat.commonCdNm}</option>
					</c:forEach>
				</select>                
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M01.LAB00161"/><!-- 관리번호 --></th>
			<td colspan="3">
				<input type="text" id="searchVoBarCd" name="searchVoBarCd" class="w250" />
			</td>
		</tr>
	</thead>
</table> 

<div class="ly_btn_box">
	<div class="fl">
		<h4 class="ly_title"><spring:message code="LAB.M06.LAB00006"/><!-- 바우처목록 --></h4>
	</div>
</div>

<div id="voucherMasterGrid">
	<table id="voucherMasterTable" class="w100p"></table>
	<div id="voucherMasterPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnSale" title="<spring:message code='LAB.M13.LAB00011'/>" href='#'><spring:message code="LAB.M13.LAB00011" /><!-- 판매처리 --></a>
	</div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>
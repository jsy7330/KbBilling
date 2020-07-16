<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		$('#btnSaleProcessVeqt').click(function() {
			searchSaleProcessVeqt();
		});
		
		$('#btnSalePop').click(function() {
			sale();
		});
		
		initGrid();
	});
	
	function initGrid() {
		
		var soId = '${soId}';
		var vissueSeqNo = '${vissueSeqNo}';
		var ownLocCd = '${ownLocCd}';

		console.log('soId : ' + soId);
		console.log('vissueSeqNo : ' + vissueSeqNo);
		console.log('ownLocCd : ' + ownLocCd);

		var param = {
			soId : soId
			, vissueSeqNo : vissueSeqNo
			, ownLocCd : ownLocCd
		};
		
		$("#saleProcessVeqtTable").jqGrid({
	
		   	url:'/distributor/logistics/voucherMng/voucherMasterMng/salesProcessPopVeqtListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[
				  { label : 'soId', name : 'soId', width : 0, hidden : true }
				, { label : 'soNm', name : 'soNm', width : 0, hidden : true }
				, { label : 'vissueSeqNo', name : 'vissueSeqNo', width : 0, hidden : true }
				, { label : 'itemId', name : 'itemId', width : 0, hidden : true }
				, { label : 'itemNm', name : 'itemNm', width : 0, hidden : true }
				, { label : 'mncoId', name : 'mncoId', width : 0, hidden : true }
				, { label : 'mncoNm', name : 'mncoNm', width : 0, hidden : true }
				, { label : 'voTp', name : 'voTp', width : 0, hidden : true }
				, { label : 'voTpNm', name : 'voTpNm', width : 0, hidden : true }
				, { label : 'voStatNm', name : 'voStatNm', width : 0, hidden : true }
				, { label : 'voExpiredDt', name : 'voExpiredDt', width : 0, hidden : true }
				, { label : 'voProdCd', name : 'voProdCd', width : 0, hidden : true }
				, { label : 'voProdNm', name : 'voProdNm', width : 0, hidden : true }
				, { label : 'lgstProcStatCd', name : 'lgstProcStatCd', width : 0, hidden : true }
				, { label : 'lgstProcStatNm', name : 'lgstProcStatNm', width : 0, hidden : true }
				, { label : 'lgstProcDttm', name : 'lgstProcDttm', width : 0, hidden : true }
				, { label : 'ownLocCd', name : 'ownLocCd', width : 0, hidden : true }
				, { label : 'ownLocNm', name : 'ownLocNm', width : 0, hidden : true }
				, { label : 'frstInDt', name : 'frstInDt', width : 0, hidden : true }
				, { label : 'agcInDt', name : 'agcInDt', width : 0, hidden : true }
				, { label : 'prchsUtPrc', name : 'prchsUtPrc', width : 0, hidden : true }
				, { label : 'dstrbUtPrc', name : 'dstrbUtPrc', width : 0, hidden : true }
				, { label : 'ctOrgId', name : 'ctOrgId', width : 0, hidden : true }
				, { label : 'ctChgId', name : 'ctChgId', width : 0, hidden : true }
				, { label : 'ctChgDt', name : 'ctChgDt', width : 0, hidden : true }
				, { label : 'regrId', name : 'regrId', width : 0, hidden : true }
				, { label : 'regDate', name : 'regDate', width : 0, hidden : true }
				, { label : 'chgrId', name : 'chgrId', width : 0, hidden : true }
				, { label : 'chgDate', name : 'chgDate', width : 0, hidden : true }
				, { label : 'remark', name : 'remark', width : 0, hidden : true }
				, { label : '<spring:message code="LAB.M01.LAB00161"/>', name : 'voBarCd', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00015"/>', name : 'voSeqNo', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00011"/>', name : 'voStatCd', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00010"/>', name : 'voIssueDt', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00007"/>', name : 'voIssueAmt', width : 100 }
		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#saleProcessVeqtPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectItem();
		    }, 
		    loadComplete: function(){
		    	$("#saleProcessVeqtTable").setGridWidth(880,false);
		    },
	    	sortable: { update: function(permutation) {
	    		$("#saleProcessVeqtTable").setGridWidth(880,false);
	    		}
	    	}
		});
	}
	
	function searchSaleProcessVeqt() {
		
		var usrNm = $('#usrNm').val();
		var orgId = $('#orgId').val();
		var orgNm = $('#orgNm').val();
		var tpDtlCd = $('#tpDtlCd').val();
		var arClCd = $('#arClCd').val();
		var arTpCd = $('#arTpCd').val();
		
		var param = {
				usrNm : usrNm
				, orgId : orgId
				, orgNm : orgNm
				, tpDtlCd : tpDtlCd
				, arClCd : arClCd
				, arTpCd : arTpCd
		}
		
		$("#saleProcessVeqtTable").setGridParam({ postData: param }).trigger("reloadGrid");
	}
	
	function sale() {
		var params = new Array();
		var message = "";
	    var id = $("#saleProcessVeqtTable").getGridParam('selarrrow');
	    var ids = $("#saleProcessVeqtTable").jqGrid('getDataIDs');
	    
		if (id == ''){
			alert('<spring:message code="MSG.M06.MSG00001" />');
			return;
		}

		var confirm = confirm('<spring:message code="MSG.M13.MSG00011" />');

		if (confirm == false) {
			return;
		}

	    for (var i = 0; i < ids.length; i++) {
	        var check = false;
	        $.each(id, function (index, value) {
	            if (value == ids[i])
	                check = true;
	        });

	        if (check) {
	            var rowdata = $("#saleProcessVeqtTable").getRowData(ids[i]);
	            rowData.aftrUpdCd = '80';
	            rowData.lgstProcStatCd = '902';
	            params.push(rowdata);
	        }
	    }

	    var tempParam = JSON.stringify(params);
	    console.log("tempParam : " + tempParam);

		$.ajax({
			url : '/distributor/logistics/voucherMng/voucherMasterMng/saveSalesProcessVeqtInfo.json',
			type : 'POST',
			async: false,
			data :  tempParam,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {

				var result = data.result;

				if (result == 0) {
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					$("#saleProcessVeqtTable").trigger("reloadGrid");
				} else if (result == 1) {
					var errorMsg = data.errorMsg;
					alert(errorMsg);
				}

			},
		    error: function(request,status,error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    },
			complete : function() {
			}
		});
	}
	
</script>


<div class="layer_top">
     <div class="title"><spring:message code="LAB.M05.LAB00043"/><!-- 물류센터 검색 --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00151"/><!-- 조직선택 --></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="btnSaleProcessVeqt"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>

	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td>${soNm}</td>
				<th><spring:message code="MSG.M06.MSG00018"/><!-- 발행일련번호 --></th>
				<td>${vissueSeqNo}</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00161"/><!-- 관리번호 --></th>
				<td>
					<input type="text" id="searchVoBarCd" name="searchVoBarCd" class="w200" />
				</td>
				<th><spring:message code="LAB.M06.LAB00015"/><!-- 바우처일련번호 --></th>
				<td>
					<input type="text" id="searchVoSeqNo" name="searchVoSeqNo" class="w200"/>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00138"/><!-- 조직 --></th>
				<td colspan="3">${ownLocNm}</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M06.LAB00006"/><!-- 바우처목록 --></h4>
		</div>
	</div>
	
	<div class="layer_box">
		<table id="saleProcessVeqtTable" class="w100p"></table>
		<div id="saleProcessVeqtPager"></div>
	</div>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSalePop" href="#" ><span class="confirm_icon"><spring:message code="LAB.M13.LAB00011" /></span></a>
</div>
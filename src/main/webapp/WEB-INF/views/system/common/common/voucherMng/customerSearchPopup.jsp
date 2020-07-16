<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		$('#searchCust').click(function() {
			searchCust();
		});
		
		$('#btnSelectItem').click(function() {
			selectItem();
		});
		
		initGrid();
	});
	
	function initGrid() {
		
		var param = {};
		
		$("#customerSearchTable").jqGrid({
	
		   	url:'/distributor/logistics/voucherMng/chargeMng/customerSearchAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[

				{ label : 'soId', name : 'soId', width : 0, hidden : true }

				, { label : '<spring:message code="LAB.M01.LAB00048" />', name : 'custTpNm', width : 100 }
				, { label : '<spring:message code="LAB.M09.LAB00188" />', name : 'corpRegNo', width : 100 }
				, { label : '<spring:message code="LAB.M07.LAB00012" />', name : 'bizRegNo', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00050" />', name : 'custNm', width : 100 }
				, { label : '<spring:message code="LAB.M14.LAB00069" />', name : 'usrId', width : 100 }
				, { label : '<spring:message code="LAB.M07.LAB00186" />', name : 'svcTelNo', width : 100 }
				, { label : '<spring:message code="LAB.M07.LAB00003" />', name : 'soNm', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00046" />', name : 'custId', width : 100 }
				, { label : '<spring:message code="LAB.M08.LAB00021" />', name : 'mtelNo', width : 100 }
				, { label : '<spring:message code="LAB.M09.LAB00065" />', name : 'telNo', width : 100 }
				, { label : '<spring:message code="LAB.M02.LAB00005" />', name : 'pymAcntId', width : 100 }
				, { label : '<spring:message code="LAB.M09.LAB00190" />', name : 'addr', width : 100 }

		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#customerSearchPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectItem();
		    }, 
		    loadComplete: function(){
		    	$("#customerSearchTable").setGridWidth(880,false);
		    },
  			sortable: { update: function(permutation) {
        		$("#customerSearchTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
    			}
    		}
		});
	}
	
	function searchCust() {
		
		var soId = $('#soIdPop').val();
		var custNm = $('#custNmPop').val();
		var custId = $('#custIdPop').val();
		var svcTelNo = $('#svcTelNoPop').val();
		var acntNo = $('#acntNoPop').val();
		var corpRegNo = $('#corpRegNoPop').val();
		var repNm = $('#repNmPop').val();
		var custTp = $('#custTpPop').val();
		var pymAcntId = $('#pymAcntIdPop').val();
		var ctrtId = $('#ctrtIdPop').val();

		if ((custNm == null || custNm == '') && (custId == null || custId == '') 
			&& (svcTelNo == null || svcTelNo == '') && (acntNo == null || acntNo == '') 
			&& (corpRegNo == null || corpRegNo == '') && (repNm == null || repNm == '') 
			&& (custTp == null || custTp == '') && (pymAcntId == null || pymAcntId == '') 
			&& (ctrtId == null || ctrtId == '')) {
			alert('<spring:message code="MSG.M09.MSG00040" />');
			return;
		}

		if ((custNm != null && custNm != '') && custNm.length < 2) {
			alert('<spring:message code="MSG.M01.MSG00018" />');
			return;
		}

		var param = {
			soId : soId
			, custNm : custNm
			, custId : custId
			, svcTelNo : svcTelNo
			, acntNo : acntNo
			, corpRegNo : corpRegNo
			, repNm : repNm
			, custTp : custTp
			, pymAcntId : pymAcntId
			, ctrtId : ctrtId
		};
		
		$("#customerSearchTable").setGridParam({ postData: param }).trigger("reloadGrid");
	}
	
	function selectItem() {
		var index  = $("#customerSearchTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}

		var data = $("#customerSearchTable").getRowData(index);
		
		var popupType = '${popupType}';
		var callbackFunction;

		if (popupType == 'm') {
			setCustomerInfo(data);
			$("#btnColse1SeP").trigger('click');
		} else if (popupType = 'o') {
			window.opener.setCustomerInfo(data);
			window.open("about:blank", "_self").close();
		}

	}
	
</script>


<div class="layer_top">
     <div class="title"><spring:message code="LAB.M01.LAB00052"/><!-- 고객상세조회 --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00151"/><!-- 조직선택 --></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="searchCust"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>

	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td>
				
					<select class="w200" id="soIdPop" name="soIdPop">
						<option value=""selected="selected"><spring:message code="LAB.M09.LAB00063"/><!-- 전체 --></option>
 						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
				<th><spring:message code="LAB.M01.LAB00050"/><!-- 고객명 --></th>
				<td colspan="3">
					<input type="text" id="custNmPop" name="custNmPop" class="w300" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00046"/><!-- 고객ID --></th>
				<td><input type="text" id="custIdPop" name="custIdPop" class="w150" /></td>
				<th><spring:message code="LAB.M07.LAB00186"/><!-- 서비스번호 --></th>
				<td><input type="text" id="svcTelNoPop" name="svcTelNoPop" class="w150" /></td>
				<th><spring:message code="LAB.M01.LAB00044"/><!-- 계계좌번호 --></th>
				<td><input type="text" id="acntNoPop" name="acntNoPop" class="w150" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00188"/><!-- 주민/법인번호 --></th>
				<td><input type="text" id="corpRegNoPop" name="corpRegNoPop" class="w150" /></td>
				<th><spring:message code="LAB.M03.LAB00063"/><!-- 대표자명 --></th>
				<td><input type="text" id="repNmPop" name="repNmPop" class="w150" /></td>
				<th><spring:message code="LAB.M01.LAB00053" /><!-- 고객유형 --></th>
				<td>
					<select class="w200" id="custTpPop" name="custTpPop">
						<option value=""selected="selected"><spring:message code="LAB.M09.LAB00063"/><!-- 전체 --></option>
						<c:forEach items="${custTpCdList}" var="custTp" varStatus="status">
							<option value="${custTp.commonCd}">${custTp.commonCdNm}</option>
						</c:forEach>
					</select>  
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M02.LAB00006"/><!-- 납부계정ID --></th>
				<td><input type="text" id="pymAcntIdPop" name="pymAcntIdPop" class="w150" /></td>
				<th><spring:message code="LAB.M01.LAB00032"/><!-- 계약ID --></th>
				<td><input type="text" id="ctrtIdPop" name="ctrtIdPop" class="w150" /></td>
			</tr>
		</thead>
	</table> 
	
	<div class="layer_box">
		<table id="customerSearchTable" class="w100p"></table>
		<div id="customerSearchPager"></div>
	</div>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSelectItem" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		$('#searchLogisticsCenter').click(function() {
			searchLogisticsCenter();
		});
		
		$('#btnSelectItem').click(function() {
			selectItem();
		});
		
		initGrid();
	});
	
	function initGrid() {
		
		var tpCd = $('#tpCd').val();
		
		var param = {
			tpCd : tpCd
		};
		param.soId = "${productMngVO.soId}";
		
		$("#logisticsSearchTable").jqGrid({
	
		   	url:'/distributor/logistics/voucherMng/voucherGenerate/logisticsCenterSearchAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[
				{ label : '<spring:message code="LAB.M09.LAB00139" />', name : 'orgId', width : 100} // 조직ID
				, { label : '<spring:message code="LAB.M09.LAB00147" />', name : 'orgNm', width : 100} // 조직명
				, { label : '<spring:message code="LAB.M09.LAB00205" />', name : 'empNo', width : 100} // 직원ID
				, { label : '<spring:message code="LAB.M09.LAB00207" />', name : 'empNm', width : 100} // 직원명
				, { label : '<spring:message code="LAB.M07.LAB00067" />', name : 'usrId', width : 100} // 사용자ID
				, { label : '<spring:message code="LAB.M07.LAB00071" />', name : 'usrNm', width : 100} // 사용자명
				, { label : '<spring:message code="LAB.M08.LAB00103" />', name : 'tpNm', width : 100} // 유형
				, { label : '<spring:message code="LAB.M08.LAB00105" />', name : 'tpDtlNm', width : 100} // 유형상세
				, { label : '<spring:message code="LAB.M09.LAB00201" />', name : 'arClNm', width : 100} // 지역분류
				, { label : '<spring:message code="LAB.M09.LAB00200" />', name : 'arTpNm', width : 100} // 지역구분
				, { label : '<spring:message code="LAB.M09.LAB00160" />', name : 'inqPermNm', width : 100} // 조회권한
				, { label : '<spring:message code="LAB.M09.LAB00146" />', name : 'orgLvNm', width : 100} // 조직레벨
				, { label : '<spring:message code="LAB.M09.LAB00212" />', name : 'cellNo', width : 100} // 직원휴대폰번호
				, { label : '<spring:message code="LAB.M09.LAB00211" />', name : 'telNo', width : 100} // 직원전화번호
		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#logisticsSearchPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectItem();
		    }, 
		    loadComplete: function(){
		    	$("#logisticsSearchTable").setGridWidth(880,false);
		    },
	    	sortable: { update: function(permutation) {
	    		$("#logisticsSearchTable").setGridWidth(880,false);
	    		}
	    	}
		});
	}
	
	function searchLogisticsCenter() {
		
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
		
		$("#logisticsSearchTable").setGridParam({ postData: param }).trigger("reloadGrid");
	}
	
	function selectItem() {
		var returnId = "${returnId}";
		var returnNm = "${returnNm}";
		
		var index  = $("#logisticsSearchTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}
		
		var returnItemId = $("#logisticsSearchTable").getRowData(index).orgId
		var returnItemNm = $("#logisticsSearchTable").getRowData(index).orgNm
		
		$("#" + returnId).val(returnItemId);
		$("#" + returnNm).val(returnItemNm);
		$("#btnClose").trigger('click');
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
			<a href="#"class="grey-btn" id="searchLogisticsCenter"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
				<td>
				
					<select class="w200" id="soIdSeP" name="soIdSeP">
 						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
				<th><spring:message code="LAB.M07.LAB00071"/><!-- 사용자명 --></th>
				<td>
					<input type="text" id="usrNm" name="usrNm" class="w150" />
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00139"/><!-- 조직ID -->
					/<spring:message code="LAB.M09.LAB00147"/><!-- 조직명 -->
				</th>
				<td colspan="3">
					<input type="text" id="orgId" name="orgId" class="w150" />
					<input type="text" id="orgNm" name="orgNm" class="w150" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00103"/><!-- 유형 --></th>
				<td>
					<spring:message code="LAB.M05.LAB00042"/><!-- 물류센터 -->
					<input type="hidden" id="tpCd" value="2000"/>
				</td>
				<th><spring:message code="LAB.M08.LAB00105"/><!-- 유형상세 --></th>
				<td>
					<select class="w200" id="tpDtlCd" name="tpDtlCd">
						<option value=""selected="selected"><spring:message code="LAB.M09.LAB00063"/><!-- 전체 --></option>
						<c:forEach items="${tpDtlList}" var="tpDtl" varStatus="status">
							<option value="${tpDtl.commonCd}">${tpDtl.commonCdNm}</option>
						</c:forEach>
					</select>  
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00201"/><!-- 지역분류 --></th>
				<td>
					<select class="w200" id="arClCd" name="arClCd">
						<option value=""selected="selected"><spring:message code="LAB.M09.LAB00063"/><!-- 전체 --></option>
						<c:forEach items="${alClList}" var="alCl" varStatus="status">
							<option value="${alCl.commonCd}">${alCl.commonCdNm}</option>
						</c:forEach>
					</select>  
				</td>
				<th><spring:message code="LAB.M09.LAB00200"/><!-- 지역구분 --></th>
				<td>
					<select class="w200" id="arTpCd" name="arTpCd">
						<option value=""selected="selected"><spring:message code="LAB.M09.LAB00063"/><!-- 전체 --></option>
						<c:forEach items="${alTpList}" var="alTp" varStatus="status">
							<option value="${alTp.commonCd}">${alTp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M05.LAB00042"/><!-- 제품목록 --></h4>
		</div>
	</div>
	
	<div class="layer_box">
		<table id="logisticsSearchTable" class="w100p"></table>
		<div id="logisticsSearchPager"></div>
	</div>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSelectItem" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
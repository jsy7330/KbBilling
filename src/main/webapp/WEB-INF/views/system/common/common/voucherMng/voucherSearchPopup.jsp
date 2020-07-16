<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		$('#soIdSeP').selectmenu({});
		$('#itemTpCdSeP').selectmenu({});
		$('#voTpCdSeP').selectmenu({});
		$('#searchType').selectmenu({});
		
		//조회버튼
		$("#searchItemSeP").click(function() {
			searchSep();            
		});
		
		//선택버튼
		$("#btnSelectItem").click(function() {
			selectItemSep();            
		});
		
		//제조사검색 팝업
		$("#btnSearchMncoSeP").click(function() {
			
			var url="/system/common/common/manufacturerMng/manufacturerSearchPopup.ajax";
			url = url + "?soId=" + $("#soIdSeP").val() + "&popType=o&returnId1=mncoIdSeP&returnId2=mncoNmSeP";
			
			var width = 510;
			var height = 430;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		initGrid();
		
	});
	
	
	function initGrid() {
		
		var param = new Object();
		
		$("#productSearchTable").jqGrid({
	
		   	url:'/distributor/logistics/voucherMng/voucherMasterMng/voucherListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[
		   		
				{ label : '<spring:message code="LAB.M07.LAB00003" />', name : 'soNm', width : 100 } // 사업
				, { label : '<spring:message code="LAB.M06.LAB00046" />', name : 'vissueSeqNo', width : 100 } // 발행일련번호
				, { label : '<spring:message code="LAB.M09.LAB00098" />', name : 'mncoNm', width : 100 } // 제조사
				, { label : '<spring:message code="LAB.M09.LAB00105" />', name : 'itemNm', width : 100 } // 제품
				, { label : '<spring:message code="LAB.M06.LAB00016" />', name : 'voTpNm', width : 100 } // 바우처종류
				, { label : '<spring:message code="LAB.M06.LAB00007" />', name : 'voIssueAmt', width : 100 } // 바우처발행금액
				, { label : '<spring:message code="LAB.M06.LAB00009" />', name : 'voIssueCnt', width : 100 } // 바우처발행수
				, { label : '<spring:message code="LAB.M06.LAB00010" />', name : 'voIssueDt', width : 100, formatter: stringTypeFormatterYYYYMMDD, align:'center' } // 바우처발행일자
				, { label : '<spring:message code="LAB.M06.LAB00005" />', name : 'voExpiredDt', width : 100, formatter: stringTypeFormatterYYYYMMDD, align:'center' } // 바우처만료일자

				, { label : 'itemId', name : 'itemId', width : 0, hidden : true }
		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#productSearchPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectItemSep();
		    }, 
		    loadComplete: function(){
		    	var popType = "${productMngVO.popType}";	//팝업타입
				
				if(popType == "o"){
					$("#productSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#productSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
		    },
	    	sortable: { update: function(permutation) {
				var popType = "${productMngVO.popType}";	//팝업타입
				
				if(popType == "o"){
					$("#productSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#productSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
	    	}
	    	}
		});
		
		$("#productSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		 
	}
	
	function searchSep(){
		
		$("#productSearchTable").clearGridData();  // 이전 데이터 삭제

		var searchType = $("#searchType").val();
		var searchText = $("#searchText").val();

		var param = new Object();
		param.soId = $("#soIdSeP").val();
		param.mncoId = $("#mncoIdSeP").val();

		if (searchText == '01') {
			param.itemId = searchText;
		} else {
			param.itemNm = searchText;
		}

		param.voTp = $("#voTpCdSeP").val();
		
        $("#productSearchTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function selectItemSep(){
		
		var index  = $("#productSearchTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}

		var data = $("#productSearchTable").getRowData(index);

		var vissueSeqNo = data.vissueSeqNo
		var itemId = data.itemId;
		var itemNm = data.itemNm;
		

		var returnVissueSeqNo = '${returnVissueSeqNo}';
		var returnItemId = '${returnItemId}';
		var returnItemNm = '${returnItemNm}';
		
		$("#"+returnVissueSeqNo).val(vissueSeqNo);
		$("#"+returnItemId).val(itemId);
		$("#"+returnItemNm).val(itemNm);
		$("#btnClose").trigger('click');
	}
	
</script>

<div class="layer_top">
     <div class="title"><spring:message code="LAB.M09.LAB00107"/><!-- 제품검색 --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M06.LAB00008"/><!-- 바우처발행선택 --></h4>
		</div>
		<div class="fr">
			<a href="#" class="grey-btn" id="searchItemSeP" name="searchItemSeP" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
				<td colspan="3">
				
					<select class="w200" id="soIdSeP" name="soIdSeP">
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --></th>
				<td>
					<div class="inp_date w190">
						<input type="text" id="mncoNmSeP" name="mncoNmSeP" class="w150" />
						<input type="hidden" id="mncoIdSeP" name="mncoIdSeP" />
                        <a href="#" id="btnSearchMncoSeP" name="btnSearchMncoSeP" class="search">search</a>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00159"/><!-- 조회구분 --></th>
				<td>
					<div class="date_box">
						<select class="w100" id="searchType" name="searchType" >
							<option value="01">Product ID</option>
							<option value="02" <c:if test="${itemNm != null && itemNm != ''}">selected="selected"</c:if>>Product Name</option>
						</select>                                                
						<input type="text" id="searchText" name="searchText" class="w100"
							<c:if test="${itemNm != null && itemNm != ''}">value="${itemNm}"</c:if>
						/>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
				<td>
					<input type="text" class="w200 not-active" disabled="disabled" />
				</td>
				<th><spring:message code="LAB.M09.LAB00123"/><!-- 제품종류 --></th>
				<td>
					<select id="voTpCdSeP" name="voTpCdSeP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${voTpCdList}" var="voTpCd" varStatus="status">
							<option value="${voTpCd.commonCd}">${voTpCd.commonCdNm}</option>
						</c:forEach>
					</select>  
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M06.LAB00006"/><!-- 바우처목록 --></h4>
		</div>
	</div>
	
	<div class="layer_box">
		<table id="productSearchTable" class="w100p"></table>
		<div id="productSearchPager"></div>
	</div>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSelectItem" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		$('#soIdSeP').selectmenu({});
		
		//조회버튼
		$("#btnSearchVoucherSeP").click(function() {
			searchSep();            
		});

		$('#btnSearchItemSeP').click(function() {

			var url="/system/common/common/productMng/productSearchPopup.ajax";
			url = url + "?soId=" + $('#soIdSeP').val() + "&popType=o&returnId1=itemIdSeP&returnId2=itemNmSeP&returnId3=mncoIdSeP&returnId4=mncoNmSeP&presetItemTpCd=V";
			
			var width = 815;
			var height = 510;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
		});
		
		//선택버튼
		$("#btnSelectItem").click(function() {
			selectItemSep();            
		});
		
		initGrid();
		
	});
	
	
	function initGrid() {

		var param = {};
		
		$("#voucherSearchTable").jqGrid({
	
		   	url:'chrgVeatListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[
		   		
		   		{ label : "effPeriod", name : "effPeriod", width : 0, hidden : true }
		   		, { label : "qosAmt", name : "qosAmt", width : 0, hidden : true }
		   		, { label : "dhType", name : "dhType", width : 0, hidden : true }

				, { label : '<spring:message code="LAB.M07.LAB00003"/>', name : 'soNm', width : 100 }
				, { label : '<spring:message code="LAB.M01.LAB00161"/>', name : 'voBarCd', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00015"/>', name : 'voSeqNo', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00001"/>', name : 'itemNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00007"/>', name : 'voIssueAmt', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00012"/>', name : 'voProdNm', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00010"/>', name : 'voIssueDt', width : 100 }
				, { label : '<spring:message code="LAB.M06.LAB00005"/>', name : 'voExpiredDt', width : 100 }

		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#voucherSearchPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectItemSep();
		    }, 
		    loadComplete: function(){
		    	var popType = "${productMngVO.popType}";	//팝업타입
				
				if(popType == "o"){
					$("#voucherSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#voucherSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
		    },
	    	sortable: { update: function(permutation) {
	    		$("#voucherSearchTable").setGridWidth(780,false);
	    		}
	    	}
		});
		
		$("#voucherSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		 
	}
	
	function searchSep(){
		
		$("#voucherSearchTable").clearGridData();  // 이전 데이터 삭제

		var soId = $('#soIdSeP').val();
		var itemId = $('#itemIdSeP').val();

		var param = {
			soId : soId
			, itemId : itemId
		};
		
        $("#voucherSearchTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function selectItemSep(){
		
		var index  = $("#voucherSearchTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}

		var data = $("#voucherSearchTable").getRowData(index);

		window.opener.setVoucherInfo(data);
		window.open("about:blank", "_self").close();

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
			<a href="#" class="grey-btn" id="btnSearchVoucherSeP" name="btnSearchVoucherSeP" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
				<th><spring:message code="LAB.M09.LAB00105"/><!-- 제품 --></th>
				<td>
					<div class="inp_date w190">
						<input type="text" id="itemNmSeP" name="itemNmSeP" class="w150" />
						<input type="hidden" id="itemIdSeP" name="itemIdSeP" />
                        <a href="#" id="btnSearchItemSeP" name="btnSearchItemSeP" class="search">search</a>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00159"/><!-- 제조사 --></th>
				<td>
					<div class="inp_date w190">
						<input type="text" id="mncoNmSeP" name="mncoNmSeP" class="w150 not-active" disabled="disabled" />
						<input type="hidden" id="mncoIdSeP" name="mncoIdSeP" />
					</div>
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
		<table id="voucherSearchTable" class="w100p"></table>
		<div id="voucherSearchPager"></div>
	</div>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSelectItem" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
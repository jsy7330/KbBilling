<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		initGrid();
		
		initGrid2();
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();            
		});
		
		//조직검색 팝업
		$("#searchOrgPopup").click(function() {
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			var param = new Object();
			param.orgId = $("#orgId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchOrdOrgId";	//리턴받는 orgId 의 태그아이디값
			param.returnId2 = "searchOrdOrgNm";	//리턴받는 orgNm 의 태그아이디값
			
			openModalPopup(url, param);
		});
		
		//제품검색 팝업
		$("#btnSearchItem").click(function() {
			var url="/system/common/common/productMng/productSearchPopup.ajax";
			var param = new Object();
			param.soId = $("#searchSoId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchItemId";	//리턴받는 Id 의 태그아이디값
			param.returnId2 = "searchItemNm";	//리턴받는 Nm 의 태그아이디값
			
			$("#popup_dialog").css("width", 800);
			openModalPopup(url, param);
		});
		
		//출고내역 등록
		$("#btn_insert").click(function() {
			
			var rowId  = $("#logisticsCenterIssueInspTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M10.MSG00021" />');	//출고대기 내역을 선택해 주세요.
				return;
			}
			
			var data = $("#logisticsCenterIssueInspTable").getRowData(rowId);
			console.log(data);
			
			var url="logisticsCenterIssueInspInsertPopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 1000);
			
			openModalPopup(url, param);         
		});
		
		
		$("#btn_delete").click(function() {
			var ids = $("#logisticsCenterIssueInspTable2").jqGrid("getGridParam", "selarrrow" );
			
			if(ids.length < 1){
				alert('<spring:message code="MSG.M07.MSG00059" />');		//삭제할 출고내역을 선택해 주세요.
				return;
			}
			
			var params = new Array();
			for(var i=0; i<ids.length; i++){
				var param = $("#logisticsCenterIssueInspTable2").getRowData(ids[i]);
				
				if(param.inoutPrgrStatCd != '40'){
					alert('<spring:message code="MSG.M10.MSG00019" />');		//출고검수 상태에서만 삭제할 수 있습니다.
					return;
				}
				params.push(param);
			}
			
			console.log(params);

			deleteOut(params);
		});
		
		//출고승인
		$("#btnOutConf").click(function() {
			var ids = $("#logisticsCenterIssueInspTable2").jqGrid("getGridParam", "selarrrow" );
			
			if(ids.length < 1){
				alert('<spring:message code="MSG.M10.MSG00024" />');		//출고승인할 내역을 선택해 주세요.
				return;
			}
			
			var params = new Array();
			for(var i=0; i<ids.length; i++){
				var param = $("#logisticsCenterIssueInspTable2").getRowData(ids[i]);
				
				if(param.inoutPrgrStatCd != '40'){
					alert('<spring:message code="MSG.M10.MSG00020" />');		//출고검수 상태에서만 승인할 수 있습니다.
					return;
				}
				params.push(param);
			}
			
			console.log(params);

			insertOutConf(params);
		});
	});
	
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		
		$("#logisticsCenterIssueInspTable").jqGrid({
			
		   	url:'lgstCntrOrdList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'ordOrgId' , name: 'ordOrgId', hidden:true,width : 0},
				{ label: 'lgstCentId' , name: 'lgstCentId', hidden:true,width : 0},
				{ label: 'ordMngOrgId' , name: 'ordMngOrgId', hidden:true,width : 0},
				{ label: 'ordMngInchrgId' , name: 'ordMngInchrgId', hidden:true,width : 0},
				{ label: 'ordPrgrStatCd' , name: 'ordPrgrStatCd', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'ordUtPrc' , name: 'ordUtPrc', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M09.LAB00176" />', name: 'ordNo', width : 100},	//주문번호
				{ label: '<spring:message code="LAB.M09.LAB00183" />', name: 'ordOrgNm', width : 100},	//주문조직
				{ label: '<spring:message code="LAB.M03.LAB00016" />', name: 'eqtUseNm', width : 100},	//단말기용도
				{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},	//제품유형
				{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},	//제품ID
				{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
				{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', align:'center', width : 100},		//색상
				{ label: '<spring:message code="LAB.M09.LAB00179" />', name: 'ordQty', formatter: 'integer', align:'right', width : 100},	//주문수량
				{ label: '<spring:message code="LAB.M09.LAB00181" />', name: 'statProcDttm', formatter: stringTypeFormatterYYYYMMDDHH24MISS, align:'center', width : 100},		//주문일자
				{ label: '<spring:message code="LAB.M09.LAB00182" />', name: 'statProcId', width : 100},	//주문자
				{ label: '<spring:message code="LAB.M06.LAB00049" />', name: 'dlvAddr', width : 100},	//배송지
				{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'ordNote', width : 100},	//주문비고
	   		 	
			    
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#logisticsCenterIssueInspPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 180,				//화면 상태에 따라 크기 조절 할 것
			//multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				searchData2(rowId);
				
	        },
	        loadComplete: function(obj){
	        	$("#logisticsCenterIssueInspTable").setGridWidth($('#logisticsCenterIssueInspGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#logisticsCenterIssueInspTable").setGridWidth($('#logisticsCenterIssueInspGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#logisticsCenterIssueInspTable").setGridWidth($('#logisticsCenterIssueInspGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#logisticsCenterIssueInspTable").setGridWidth($('#logisticsCenterIssueInspGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData(){
		
		$("#logisticsCenterIssueInspTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoId").val();
		
		param.searchStatDt = dateFormatToStringYYYYMMDD( $("#searchStatDt").val() ) + "000000";
		param.searchEndDt = dateFormatToStringYYYYMMDD( $("#searchEndDt").val() ) + "999999";
		param.ordOrgId = $("#searchOrdOrgId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		param.itemId = $("#searchItemId").val();
		
        $("#logisticsCenterIssueInspTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	
	function initGrid2() {
		
		var param = new Object();
		param.soId = "9999";
		param.ordNo = "999999999999"
		
		$("#logisticsCenterIssueInspTable2").jqGrid({
			
		   	url:'lgstCntrOutList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'ownOrgId' , name: 'ownOrgId', hidden:true,width : 0},
				{ label: 'ordOrgId' , name: 'ordOrgId', hidden:true,width : 0},
				{ label: 'lgstCentId' , name: 'lgstCentId', hidden:true,width : 0},
				{ label: 'ordMngOrgId' , name: 'ordMngOrgId', hidden:true,width : 0},
				{ label: 'ordMngInchrgId' , name: 'ordMngInchrgId', hidden:true,width : 0},
				{ label: 'ordPrgrStatCd' , name: 'ordPrgrStatCd', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'inoutPrgrStatCd' , name: 'inoutPrgrStatCd', hidden:true,width : 0},
				{ label: 'inoutOrgId' , name: 'inoutOrgId', hidden:true,width : 0},
				{ label: 'ordNo' , name: 'ordNo', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M10.LAB00065" />', name: 'inoutId', width : 100},	//출고ID
				{ label: '<spring:message code="LAB.M10.LAB00072" />', name: 'ownOrgNm', width : 100},	//출고대상조직
				{ label: '<spring:message code="LAB.M10.LAB00075" />', name: 'inoutPrgrStatNm', width : 100},	//출고상태
				{ label: '<spring:message code="LAB.M10.LAB00078" />', name: 'statProcDt', formatter: stringTypeFormatterYYYYMMDDHH24MISS, align:'center', width : 100},		//출고일자
				{ label: '<spring:message code="LAB.M10.LAB00074" />', name: 'inoutResnNm', width : 100},	//출고사유
				{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},	//제품유형
				{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},	//제품ID
				{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},	//제품명
				{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', align:'center', width : 100},		//색상
				{ label: '<spring:message code="LAB.M10.LAB00076" />', name: 'inoutQty', formatter: 'integer', align:'right', width : 100},	//출고수량
				{ label: '<spring:message code="LAB.M10.LAB00069" />', name: 'inoutUtPrc', formatter: 'integer', align:'right', width : 100},	//출고단가
				{ label: '<spring:message code="LAB.M03.LAB00016" />', name: 'eqtUseNm', width : 100},	//단말기용도
				{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'note', width : 100},	//비고
	   		 	
			    
	   		],

		   	rowNum:99999,					//한번에 노출되는 row 수
		   	//rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	//pager: '#logisticsCenterIssueInspPager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 180,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#logisticsCenterIssueInspTable2").setGridWidth($('#logisticsCenterIssueInspGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#logisticsCenterIssueInspTable2").setGridWidth($('#logisticsCenterIssueInspGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#logisticsCenterIssueInspTable2").setGridWidth($('#logisticsCenterIssueInspGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#logisticsCenterIssueInspTable2").setGridWidth($('#logisticsCenterIssueInspGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData2(rowId){
		
		var data = $("#logisticsCenterIssueInspTable").getRowData(rowId);
		
		$("#logisticsCenterIssueInspTable2").clearGridData();  //이전 데이터 삭제
		var param = new Object();
		param.soId = data.soId;
		param.ordNo = data.ordNo;
		
        $("#logisticsCenterIssueInspTable2").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function deleteOut(param){
		
		if(param != false){
			
			param = JSON.stringify(param);
			
			$.ajax({
				url : 'deleteOut.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00053" />');	//삭제되었습니다.
						searchData2();
					}
				},
			    error: function(e){
			        alert("Failed to save.");
			    },
				complete : function() {
				}
			});
		}
	}
	
	
	
	function insertOutConf(param){
		
		if(param != false){
			
			param = JSON.stringify(param)
			
			$.ajax({
				url : 'insertOutConf.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00110" />');	//승인되었습니다.
						searchData2();
					}
				},
			    error: function(e){
			        alert("Failed to save.");
			    },
				complete : function() {
				}
			});
		}
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
			<td colspan="3" >
				<select class="w150" id="searchSoId" name="searchSoId">
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00181"/><!-- 주문일자 --></th>
			<td>
				<div class="date_box">
					<div class="inp_date w135">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
					<span class="dash">~</span>
					<div class="inp_date w135">
						<input type="text" id="searchEndDt" name="searchEndDt" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th><spring:message code="LAB.M09.LAB00183"/><!-- 주문조직 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchOrdOrgNm" name="searchOrdOrgNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchOrdOrgId" name="searchOrdOrgId" />
					<a href="#" id="searchOrgPopup" name="searchOrgPopup" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
			<td>
				<select id="searchItemTpCd" name="searchItemTpCd" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
						<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchItemNm" name="searchItemNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchItemId" name="searchItemId" />
					<a href="#" id="btnSearchItem" name="btnSearchItem" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div> 
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M10.LAB00070"/><!-- 출고대기내역 --></h4>
	</div>
</div>

<div id="logisticsCenterIssueInspGrid">
	<table id="logisticsCenterIssueInspTable" class="w100p"></table>
	<div id="logisticsCenterIssueInspPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M10.LAB00068"/><!-- 출고내역 --></h4>
	</div>
</div>

<div id="logisticsCenterIssueInspGrid2">
	<table id="logisticsCenterIssueInspTable2" class="w100p"></table>
	<div id="logisticsCenterIssueInspPager2"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnOutConf" title="<spring:message code='LAB.M10.LAB00077'/>" href="#" ><spring:message code="LAB.M10.LAB00077"/><!-- 출고승인 --></a>
	</div>
	<div class="fr">
		<!--초기화-->		
		<a class="white-btn not-active" href="#" id="btn_init" disabled="disabled" ><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
		<!--등록-->
		<ntels:auth auth="${menuAuthC}">
			<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>	
		<%-- 	
		<!--수정-->
		<ntels:auth auth="${menuAuthU}">
			<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		 --%>
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
	
	
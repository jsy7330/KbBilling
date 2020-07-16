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
		
		initGrid2();
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();            
		});
		
		
		$("#btnOrderInsert").click(function() {
			
			var rowId  = $("#orderDistributorTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M14.MSG00008" />');	//할당내역을 선택해 주세요.
				return;
			}
			
			var data = $("#orderDistributorTable").getRowData(rowId);
			console.log(data);
			if(data.noOrdQty0 < 1){
				alert('<spring:message code="MSG.M09.MSG00043" />');	//주문가능 수량이 존재하지 않습니다.
				return;
			}
			
			var url="orderDistributorInsertPopup.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 1000);
			
			openModalPopup(url, param);         
		});
		
		
		$("#btnOrderDelete").click(function() {
			
			var rowId  = $("#orderDistributorTable2").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M09.MSG00044" />');	//주문내역을 선택해 주세요.
				return;
			}
			
			var data = $("#orderDistributorTable2").getRowData(rowId);
			if(data.ordPrgrStatCd != "10"){
				alert('<spring:message code="MSG.M09.MSG00045" />');	//주문등록 상태에서만 삭제할 수 있습니다.
				return;
			}
			
			deleteData();
			
		});
		
		
		$("#btnOrderConf").click(function() {
			
			var rowId  = $("#orderDistributorTable2").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M09.MSG00044" />');	//주문내역을 선택해 주세요.
				return;
			}
			
			var data = $("#orderDistributorTable2").getRowData(rowId);
			if(data.ordPrgrStatCd != "10"){
				alert('<spring:message code="MSG.M09.MSG00046" />');	//주문등록 상태에서만 확정할 수 있습니다.
				return;
			}
			
			updateData();
			
		});
		
		//주문이력 팝업
		$("#btnOrderHist").click(function() {
			var url="orderDistributorHistPopup.ajax";
			var param = new Object();
			
			$("#popup_dialog").css("width", 1000);
			
			openModalPopup(url, param);
		});
		
		//조직검색 팝업
		$("#searchOrgPopup").click(function() {
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			var param = new Object();
			param.orgId = $("#orgId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchOrgId";	//리턴받는 orgId 의 태그아이디값
			param.returnId2 = "searchOrgNm";	//리턴받는 orgNm 의 태그아이디값
			
			openModalPopup(url, param);
		});
		
		
		
		
		
		
	});
	
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.asgnOrgId = "${session_user.orgId}";
		//param.asgnOrgId = "6000000005";
		
		
		param.asgnEndYn = "N";
		
		$("#orderDistributorTable").jqGrid({
			
		   	url:'asgnList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'asgnNo' , name: 'asgnNo', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},
				{ label: 'asgnOrgId' , name: 'asgnOrgId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'stckOwnQty' , name: 'stckOwnQty', hidden:true,width : 0},
				{ label: 'asgnMngOrgId' , name: 'asgnMngOrgId', hidden:true,width : 0},
				{ label: 'asgnMngInchrgId' , name: 'asgnMngInchrgId', hidden:true,width : 0},
				{ label: 'asgnMngInchrgNm' , name: 'asgnMngInchrgNm', hidden:true,width : 0},
				{ label: 'lgstCentId' , name: 'lgstCentId', hidden:true,width : 0},
				{ label: 'ordMngOrgId' , name: 'ordMngOrgId', hidden:true,width : 0},
				{ label: 'ordMngInchrgId' , name: 'ordMngInchrgId', hidden:true,width : 0},
				{ label: 'uppLvAsgnNo' , name: 'uppLvAsgnNo', hidden:true,width : 0},
				{ label: 'uppLvAsgnOrgId' , name: 'uppLvAsgnOrgId', hidden:true,width : 0},
				{ label: 'asgnEndYn' , name: 'asgnEndYn', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M05.LAB00042" />', name: 'lgstCentNm', width : 100},	//물류센터      
				{ label: '<spring:message code="LAB.M14.LAB00021" />', name: 'asgnOrgNm', width : 100},		//할당조직      
				{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},		//제품유형
				{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
				{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', align:'center', width : 100},		//색상
				{ label: '<spring:message code="LAB.M08.LAB00085" />', name: 'eqtUseNm', width : 100},		//용도
				{ label: '<spring:message code="LAB.M14.LAB00019" />', name: 'stckOwnQty', formatter: 'integer', align:'right', width : 100},	//할당수량
				{ label: '<spring:message code="LAB.M05.LAB00051" />', name: 'noOrdQty0', formatter: 'integer', align:'right', width : 100},	//미주문수량
				{ label: '<spring:message code="LAB.M14.LAB00020" />', name: 'asgnDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//할당일자
				{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'note', width : 100},			//비고
	   		 	
			    
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#orderDistributorPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 180,				//화면 상태에 따라 크기 조절 할 것
			//multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				searchData2(rowId);
				
	        },
	        loadComplete: function(obj){
	        	$("#orderDistributorTable").setGridWidth($('#orderDistributorGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#orderDistributorTable").setGridWidth($('#orderDistributorGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#orderDistributorTable").setGridWidth($('#orderDistributorGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#orderDistributorTable").setGridWidth($('#orderDistributorGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData(){
		
		$("#orderDistributorTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.actncOrgId = "${session_user.orgId}";
		
		param.searchStatDt = dateFormatToStringYYYYMMDD( $("#searchStatDt").val() );
		param.searchEndDt = dateFormatToStringYYYYMMDD( $("#searchEndDt").val() );
		param.mncoId = $("#searchMncoId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		param.itemId = $("#searchItemId").val();
		
        $("#orderDistributorTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	
	function initGrid2() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.ordQty = "0";
		param.asgnOrgId = "${session_user.orgId}";
		param.asgnNo = "0000000000000000000";
		param.asgnEndYn = "N";
		
		$("#orderDistributorTable2").jqGrid({
			
		   	url:'ordList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'ordNo' , name: 'ordNo', hidden:true,width : 0},
				{ label: 'lgstCentId' , name: 'lgstCentId', hidden:true,width : 0},
				{ label: 'asgnOrgId' , name: 'asgnOrgId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'ordPrgrStatCd' , name: 'ordPrgrStatCd', hidden:true,width : 0},
				{ label: 'stckOwnQty' , name: 'stckOwnQty', hidden:true,width : 0},
				{ label: 'asgnMngOrgId' , name: 'asgnMngOrgId', hidden:true,width : 0},
				{ label: 'asgnMngInchrgId' , name: 'asgnMngInchrgId', hidden:true,width : 0},
				{ label: 'uppLvAsgnNo' , name: 'uppLvAsgnNo', hidden:true,width : 0},
				{ label: 'uppLvAsgnOrgId' , name: 'uppLvAsgnOrgId', hidden:true,width : 0},
				{ label: 'asgnEndYn' , name: 'asgnEndYn', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M09.LAB00176" />', name: 'ordNo', width : 100},	//주문번호
				{ label: '<spring:message code="LAB.M08.LAB00100" />', name: 'ordOrgNm', width : 100},	//유통점주문조직
				{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},		//제품유형
				{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
				{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', align:'center', width : 100},		//색상
				{ label: '<spring:message code="LAB.M08.LAB00085" />', name: 'eqtUseNm', width : 100},		//용도
				{ label: '<spring:message code="LAB.M09.LAB00181" />', name: 'regDate', formatter: dateTypeFormatterYYYYMMDD, align:'center', width : 100},		//주문일자
				{ label: '<spring:message code="LAB.M07.LAB00110" />', name: 'ordPrgrStatNm', width : 100},		//상태
				{ label: '<spring:message code="LAB.M09.LAB00179" />', name: 'ordQty', formatter: 'integer', align:'right', width : 100},	//주문수량
				{ label: '<spring:message code="LAB.M09.LAB00172" />', name: 'ordUtPrc', formatter: 'integer', align:'right', width : 100},	//주문단가
				{ label: '<spring:message code="LAB.M09.LAB00170" />', name: 'ordAmt', formatter: 'integer', align:'right', width : 100},	//주문공급가액
				{ label: '<spring:message code="LAB.M09.LAB00177" />', name: 'ordAddTx', formatter: 'integer', align:'right', width : 100},	//주문부가세
				{ label: '<spring:message code="LAB.M09.LAB00185" />', name: 'ordTotAmt', formatter: 'integer', align:'right', width : 100},	//주문총금액
				{ label: '<spring:message code="LAB.M09.LAB00174" />', name: 'statProcId', width : 100},	//주문등록자
				{ label: '<spring:message code="LAB.M14.LAB00017" />', name: 'asgnNo', width : 100},		//할당번호  
				{ label: '<spring:message code="LAB.M09.LAB00171" />', name: 'asgnOrgNm', width : 100},	//주문관리조직
				{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'note', width : 100},			//비고
	   		 	
			    
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#orderDistributorPager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 180,				//화면 상태에 따라 크기 조절 할 것
			//multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
				
	        },
	        loadComplete: function(obj){
	        	$("#orderDistributorTable2").setGridWidth($('#orderDistributorGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#orderDistributorTable2").setGridWidth($('#orderDistributorGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#orderDistributorTable2").setGridWidth($('#orderDistributorGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#orderDistributorTable2").setGridWidth($('#orderDistributorGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData2(rowId){
		
		//var rowId  = $("#orderDistributorTable").jqGrid("getGridParam", "selrow" );
		var data = $("#orderDistributorTable").getRowData(rowId);
		
		$("#orderDistributorTable2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.ordQty = "0";
		param.asgnOrgId = "${session_user.orgId}";
		param.asgnNo = data.asgnNo;
		
        $("#orderDistributorTable2").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	//삭제
	function deleteData(){
		
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');

		if(check){
			
			var rowId  = $("#orderDistributorTable2").jqGrid("getGridParam", "selrow" );
			var param = $("#orderDistributorTable2").getRowData(rowId);
			param.regDate = new Date();
			param.ordPrgrStatCd = "30";
			param.ordConfQty = "0";
			
			$.ajax({
				url : 'deleteOrd.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00053" />');	//삭제되었습니다.
						searchData();
						searchData2();
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
	
	//주문확정
	function updateData(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00048" />');	//주문확정 하시겠습니까?

		if(check){
			
			var rowId  = $("#orderDistributorTable2").jqGrid("getGridParam", "selrow" );
			var param = $("#orderDistributorTable2").getRowData(rowId);
			param.regDate = new Date();
			param.ordPrgrStatCd = "20";
			param.ordConfQty = param.ordQty;
			param.idlStatCd = "40";
			param.orgId = "${session_user.orgId}";
			param.loanKndCd = "10";
			
			$.ajax({
				url : 'updateOrd.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					
					if(data.result == 'E01'){
						alert('<spring:message code="MSG.M08.MSG00010" />');	//여신정보가 없습니다.
					}else if(data.result == 'E02'){
						alert('<spring:message code="MSG.M08.MSG00011" />');	//여신정보를 사용 할 수 없습니다.
					}else if(data.result == 'E03'){
						alert('<spring:message code="MSG.M08.MSG00009" />');	//여신가능 금액을 초과했습니다.
					}else if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00047" />');	//주문확정 되었습니다.
						searchData();
						searchData2();
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
			<th><spring:message code="LAB.M09.LAB00171"/><!-- 주문관리조직 --></th>
			<td>
				<select class="w150" id="searchOrderMngOrgId" name="searchOrderMngOrgId">
					<c:forEach items="${orderMngOrgList}" var="orderMngOrgList" varStatus="status">
						<option value="${orderMngOrgList.orgId}">${orderMngOrgList.orgNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00100"/><!-- 유통점주문조직 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchOrgNm" name="searchOrgNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchOrgId" name="searchOrgId" />
					<a href="#" id="searchOrgPopup" name="searchOrgPopup" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
			</td>
			<th><spring:message code="LAB.M14.LAB00020"/><!-- 할당일자 --></th>
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
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M14.LAB00015"/><!-- 할당내역 --></h4>
	</div>
</div>

<div id="orderDistributorGrid">
	<table id="orderDistributorTable" class="w100p"></table>
	<div id="orderDistributorPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00175"/><!-- 주문목록 --></h4>
	</div>
</div>

<div id="orderDistributorGrid2">
	<table id="orderDistributorTable2" class="w100p"></table>
	<div id="orderDistributorPager2"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnOrderInsert" title="<spring:message code='LAB.M09.LAB00173'/>" href="#" ><spring:message code="LAB.M09.LAB00173"/><!-- 주문등록 --></a>
		<a class="grey-btn" id="btnOrderDelete" title="<spring:message code='LAB.M09.LAB00178'/>" href="#" ><spring:message code="LAB.M09.LAB00178"/><!-- 주문삭제 --></a>
		<a class="grey-btn" id="btnOrderConf" title="<spring:message code='LAB.M09.LAB00186'/>" href="#" ><spring:message code="LAB.M09.LAB00186"/><!-- 주문확정 --></a>
		<a class="grey-btn" id="btnOrderHist" title="<spring:message code='LAB.M09.LAB00180'/>" href="#" ><spring:message code="LAB.M09.LAB00180"/><!-- 주문이력 --></a>
	</div>
	<div class="fr">
		<!--초기화-->		
		<a class="white-btn not-active" href="#" id="btn_init" disabled="disabled" ><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
		<!--등록-->
		<%-- 
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
		 --%>
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




	
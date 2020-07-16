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
		
		//사업 셀렉트 박스 체인지
		$('#searchSoId').selectmenu({
		    change: function() {
		    	$("#areaSearch input").val("");
		    }
		});
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();
		});
		
		//초기화
		//초기화버튼
		$('#btn_init').addClass('white-btn');
		$('#btn_init').removeClass('grey-btn');
		$('#btn_init').addClass('not-active');
		$('#btn_init').attr('disabled',true);
		/* 
		$("#btn_init").click(function() {
			reset();
		});
		 */
		 
		//등록
		$("#btn_insert").click(function() {
			
			var rowId  = $("#purchaseUnitPriceTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M03.MSG00030" />');		//등록할 제품을 선택해 주세요.
				return;
			}
			
			var url="mncoUtPrcDtlInsertPopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 700);
			
			openModalPopup(url, param);
		});
		
		//수정
		$("#btn_update").click(function() {

			var rowId  = $("#purchaseUnitPriceTable2").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M07.MSG00095" />');	//수정할 단가내역을 선택해 주세요.
				return;
			}
			
			var url="mncoUtPrcDtlUpdatePopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 700);
			
			openModalPopup(url, param);
		});
		
		//삭제
		$("#btn_delete").click(function() {
			
			var rowId  = $("#purchaseUnitPriceTable2").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M07.MSG00055" />');	//삭제할 단가내역을 선택해 주세요.
				return;
			}
			
			deleteData();
		});
		
		//제조사검색 팝업
		$("#btnSearchMnco").click(function() {
			var url="/system/common/common/manufacturerMng/manufacturerSearchPopup.ajax";
			var param = new Object();
			param.soId = $("#searchSoId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchMncoId";	//리턴받는 Id 의 태그아이디값
			param.returnId2 = "searchMncoNm";	//리턴받는 Nm 의 태그아이디값
			
			$("#popup_dialog").css("width", 500);
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
		
		
		//단가변경이력조회 팝업
		$("#btnPriceChangeHistPop").click(function() {
			var url="/distributor/logistics/referenceInfo/purchaseUnitPrice/priceChangeHisthPopUp.ajax";
			var param = new Object();
			
			$("#popup_dialog").css("width", 1000);
			openModalPopup(url, param);
		});
		
		
		
	});
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		
		$("#purchaseUnitPriceTable").jqGrid({
			
		   	url:'purchaseUnitPriceListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},

	   		 	{ label: '<spring:message code="LAB.M09.LAB00098" />', name: 'mncoNm', width : 100},				//제조사
	   			{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},			//제품유형
	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			{ label: '<spring:message code="LAB.M14.LAB00073" />', name: 'eftStrtDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력시작일자
	   			{ label: '<spring:message code="LAB.M14.LAB00075" />', name: 'eftEndDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력종료일자
	   			{ label: '<spring:message code="LAB.M05.LAB00005" />', name: 'mncoOtptUtPrc', formatter: 'integer', align:'right', width : 100},	//매입단가
	   			{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'note', width : 100},		//비고
	   			
			    
			    
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#purchaseUnitPricePager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 180,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
				searchData2(rowId)
	        },
	        loadComplete: function(obj){
	        	$("#purchaseUnitPriceTable").setGridWidth($('#purchaseUnitPriceGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#purchaseUnitPriceTable").setGridWidth($('#purchaseUnitPriceGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#purchaseUnitPriceTable").setGridWidth($('#purchaseUnitPriceGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#purchaseUnitPriceTable").setGridWidth($('#purchaseUnitPriceGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	//그리드 재검색 
	function searchData(){
		
		$("#purchaseUnitPriceTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.mncoId = $("#searchMncoId").val();
		param.itemId = $("#searchItemId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		
        $("#purchaseUnitPriceTable").setGridParam({ postData: param }).trigger("reloadGrid");
        
        $("#purchaseUnitPriceTable2").clearGridData();  // 이전 데이터 삭제
        
	}
	
	function initGrid2() {
		
		var param = new Object();
		param.itemId = "0000000000000000000000";
		
		$("#purchaseUnitPriceTable2").jqGrid({
			
		   	url:'mncoUtPrcDtlListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'prcDtlSeq' , name: 'prcDtlSeq', hidden:true,width : 0},

	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			{ label: '<spring:message code="LAB.M14.LAB00073" />', name: 'eftStrtDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력시작일자
	   			{ label: '<spring:message code="LAB.M14.LAB00075" />', name: 'eftEndDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력종료일자
	   			{ label: '<spring:message code="LAB.M05.LAB00005" />', name: 'mncoOtptUtPrc', formatter: 'integer', align:'right', width : 100},	//매입단가
	   			
	   			
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#purchaseUnitPricePager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 180,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        	//setSelectedDate(rowId);
	        },
	        loadComplete: function(obj){
	        	$("#purchaseUnitPriceTable").setGridWidth($('#purchaseUnitPriceGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#purchaseUnitPriceTable").setGridWidth($('#purchaseUnitPriceGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#purchaseUnitPriceTable2").setGridWidth($('#purchaseUnitPriceGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#purchaseUnitPriceTable2").setGridWidth($('#purchaseUnitPriceGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	//그리드 재검색 
	function searchData2(rowId){
		
		var data = $("#purchaseUnitPriceTable").getRowData(rowId);
		
		$("#purchaseUnitPriceTable2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		
		if(rowId =! 0){
			param.itemId = data.itemId;
		}else{
			param.itemId = "0000000000000000000000";
		}
		
        $("#purchaseUnitPriceTable2").setGridParam({ postData: param }).trigger("reloadGrid");
        
	}
	
	//삭제
	function deleteData(){
		
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');

		if(check){
			
			var param = new Object();
			
			var rowId  = $("#purchaseUnitPriceTable2").jqGrid("getGridParam", "selrow" );
			var data = $("#purchaseUnitPriceTable2").getRowData(rowId);
			
			param.prcDtlSeq = data.prcDtlSeq;
			
			
			$.ajax({
				url : 'deleteMncoUtPrcDtl.json',
				type : 'POST',
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
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		}
	}
	
	function reset(){
		
		$("#areaSearch input").val("");
		
		$("#areaSearch select").val("");
		$("#areaSearch select" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
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


<table class="writeview" id="areaSearch" >
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
					<%-- <option value=""><spring:message code="LAB.M15.LAB00002"/></option> --%>
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
			<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchItemNm" name="searchItemNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchItemId" name="searchItemId" />
					<a href="#" id="btnSearchItem" name="btnSearchItem" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div> 
			</td>
			<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
			<td>
				<select id="searchItemTpCd" name="searchItemTpCd" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
						<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
					</c:forEach>
				</select>                                        
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00112"/><!-- 제품목록 --></h4>
	</div>
</div>

<div id="purchaseUnitPriceGrid">
	<table id="purchaseUnitPriceTable" class="w100p"></table>
	<div id="purchaseUnitPricePager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M03.LAB00008"/><!-- 단가내역 --></h4>
	</div>
	<div class="fr mt10">
		<div class="ask"><spring:message code="MSG.M15.MSG00004"/><!-- ※ 매입단가는 부가세 제외입니다. --></div>
	</div>
</div>

<div id="purchaseUnitPriceGrid2">
	<table id="purchaseUnitPriceTable2" class="w100p"></table>
	<div id="purchaseUnitPricePager2"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnPriceChangeHistPop" name="btnPriceChangeHistPop" title="<spring:message code='LAB.M03.LAB00010'/>" href="#" ><spring:message code="LAB.M03.LAB00010"/><!-- 단가변경이력조회 --></a>
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
	
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		console.log("${session_user}");
		
		console.log("${session_user.orgId}"); 
		console.log("${session_user.soId}"); 
		
		initGrid();
		
		initGrid2();
		
		//초기화버튼
		$('#btn_init').addClass('white-btn');
		$('#btn_init').removeClass('grey-btn');
		$('#btn_init').addClass('not-active');
		$('#btn_init').attr('disabled',true);
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();            
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
		
		//등록
		$("#btn_insert").click(function() {
			
			var rowId  = $("#deliveryInspectionTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M03.MSG00028" />');	//등록할 납품내역을 선택해주세요.
				return;
			}
			
			var data = $("#deliveryInspectionTable").getRowData(rowId);
			
			var actncQty = data.actncQty;	//납품수량
			//전체 rowData 가져오기
			var data2 = $("#deliveryInspectionTable2").getRowData();
			var inoutQty = 0;
			for(var i=0; i < data2.length; i++){
				inoutQty = inoutQty + Number(data2[i].inoutQty);
			}
			
			if(actncQty <= inoutQty){
				alert('<spring:message code="MSG.M08.MSG00068" />');		//입고수량의 합계는 납품수량을 초과 할 수 없습니다.
				return ;
			}
			
			var url="inActncInsertPopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 800);
			
			openModalPopup(url, param);
		});
		
		
		//수정
		$("#btn_update").click(function() {
			
			var rowId  = $("#deliveryInspectionTable2").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M07.MSG00104" />');	//수정할 입고내역을 선택해주세요.
				return;
			}
			var data = $("#deliveryInspectionTable2").getRowData(rowId);
			if(data.inoutPrgrStatCd != "10"){
				alert('<spring:message code="MSG.M08.MSG00066" />');	//입고등록 상태에서만 수정할 수 있습니다. 
				return;
			}
			
			
			var url="inActncUpdatePopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 800);
			
			openModalPopup(url, param);
			
		});
		
		//삭제
		$("#btn_delete").click(function() {
			
			var rowId  = $("#deliveryInspectionTable2").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M07.MSG00058" />');	//삭제할 입고내역을 선택해주세요.
				return;
			}
			var data = $("#deliveryInspectionTable2").getRowData(rowId);
			if(data.inoutPrgrStatCd != "10"){
				alert('<spring:message code="MSG.M08.MSG00065" />');	//입고등록 상태에서만 삭제할 수 있습니다. 
				return;
			}
			
			deleteData();
			
		});
		
		
		
		//검수저장
		$("#btnInspSave").click(function() {
			
			var rowId  = $("#deliveryInspectionTable2").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M08.MSG00063" />');	//입고내역을 선택해주세요.
				return;
			}
			var data = $("#deliveryInspectionTable2").getRowData(rowId);
			var url="deliveryInspectionPopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 1000);
			
			openModalPopup(url, param);
			
		});
		
		
		
		//입고승인
		$("#btnWearingAc").click(function() {
			
			var rowId  = $("#deliveryInspectionTable2").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M08.MSG00063" />');	//입고내역을 선택해주세요.
				return;
			}
			
			var data = $("#deliveryInspectionTable2").getRowData(rowId);
			
			if(data.inoutPrgrStatCd != "40"){
				alert('<spring:message code="MSG.M08.MSG00060" />');	//입고검수 상태에서만 입고승인 할 수 있습니다.
				return;
			}
			
			
			var url="inAcPopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 800);
			param.soId = data.soId;
			
			openModalPopup(url, param);
			
			
			
			//입고승인-임시주석
			//insertWearingAc(data);
			
		});
		
	});
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.actncOrgId = "${session_user.orgId}";
		
		//param.soId = "00";
		//param.actncOrgId = "0000121212";
		
		$("#deliveryInspectionTable").jqGrid({
			
		   	url:'inOutActncList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'poNo' , name: 'poNo', hidden:true,width : 0},
				{ label: 'idlStatCd' , name: 'idlStatCd', hidden:true,width : 0},
				{ label: 'lgstCentId' , name: 'lgstCentId', hidden:true,width : 0},
				{ label: 'mncoId' , name: 'mncoId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'actncPrgrStatCd' , name: 'actncPrgrStatCd', hidden:true,width : 0},
				{ label: 'actncPrgrStatNm' , name: 'actncPrgrStatNm', hidden:true,width : 0},
				{ label: 'statProcId' , name: 'statProcId', hidden:true,width : 0},
				{ label: 'statProcDttm' , name: 'statProcDttm', hidden:true,width : 0},
				{ label: 'actncOrgId' , name: 'actncOrgId', hidden:true,width : 0},
				{ label: 'actncOrgNm' , name: 'actncOrgNm', hidden:true,width : 0},
				{ label: 'actncInchrgTelNo' , name: 'actncInchrgTelNo', hidden:true,width : 0},
				{ label: 'inoutResnCd' , name: 'inoutResnCd', hidden:true,width : 0},
				{ label: 'inoutResnNm' , name: 'inoutResnNm', hidden:true,width : 0},
				{ label: 'inoutClCd' , name: 'inoutClCd', hidden:true,width : 0},
				{ label: 'inoutClNm' , name: 'inoutClNm', hidden:true,width : 0},
				{ label: 'note' , name: 'note', hidden:true,width : 0},

	   		 	{ label: '<spring:message code="LAB.M02.LAB00025" />', name: 'actncNo', width : 100},		//납품번호
	   		 	{ label: '<spring:message code="LAB.M09.LAB00098" />', name: 'mncoNm', width : 100},			//제조사
	   		 	{ label: '<spring:message code="LAB.M08.LAB00159" />', name: 'idlStatNm', width : 100},	//입고상태
	   		 	{ label: '<spring:message code="LAB.M05.LAB00042" />', name: 'lgstCentNm', width : 100},	//물류센터
	   		 	{ label: '<spring:message code="LAB.M09.LAB00109" />', name: 'itemTpNm', width : 100},			//제품구분
	   		 	{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
	   		 	{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', align:'center', width : 100},		//색상
	   		 	{ label: '<spring:message code="LAB.M02.LAB00028" />', name: 'actncDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//납품일자
	   		 	{ label: '<spring:message code="LAB.M02.LAB00027" />', name: 'actncQty', formatter: 'integer', align:'right', width : 100},	//납품수량
	   		 	{ label: '<spring:message code="LAB.M09.LAB00110" />', name: 'actncUtPrc', formatter: 'integer', align:'right', width : 100},	//제품단가
	   		 	{ label: '<spring:message code="LAB.M01.LAB00061" />', name: 'actncAmt', formatter: 'integer', align:'right', width : 100},	//공급가
	   		 	{ label: '<spring:message code="LAB.M06.LAB00088" />', name: 'actncAddTx', formatter: 'integer', align:'right', width : 100},	//부가세
	   		 	{ label: '<spring:message code="LAB.M10.LAB00051" />', name: 'actncTotAmt', formatter: 'integer', align:'right', width : 100},	//총금액
	   		 	
			    
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#deliveryInspectionPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 180,				//화면 상태에 따라 크기 조절 할 것
			//multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				searchData2(rowId);
				
	        },
	        loadComplete: function(obj){
	        	$("#deliveryInspectionTable").setGridWidth($('#deliveryInspectionGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#deliveryInspectionTable").setGridWidth($('#deliveryInspectionGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#deliveryInspectionTable").setGridWidth($('#deliveryInspectionGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#deliveryInspectionTable").setGridWidth($('#deliveryInspectionGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData(){
		
		$("#deliveryInspectionTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.actncOrgId = "${session_user.orgId}";
		
		//param.actncOrgId = "0000121212";
		//param.soId = $("#searchSoId").val();
		
		param.searchStatDt = dateFormatToStringYYYYMMDD( $("#searchStatDt").val() );
		param.searchEndDt = dateFormatToStringYYYYMMDD( $("#searchEndDt").val() );
		param.mncoId = $("#searchMncoId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		param.itemId = $("#searchItemId").val();
		
        $("#deliveryInspectionTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function initGrid2() {
		
		var param = new Object();
		
		param.soId = "00";
		param.inoutClCd = "1111";
		
		$("#deliveryInspectionTable2").jqGrid({
			
		   	url:'inActncList.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'inoutPrgrStatCd' , name: 'inoutPrgrStatCd', hidden:true,width : 0},
				{ label: 'eqtRsrcClCd' , name: 'eqtRsrcClCd', hidden:true,width : 0},
				{ label: 'eqtUseCd' , name: 'eqtUseCd', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'itemTpNm' , name: 'itemTpNm', hidden:true,width : 0},
				{ label: 'actncNo' , name: 'actncNo', hidden:true,width : 0},
				{ label: 'clorCd' , name: 'clorCd', hidden:true,width : 0},
				{ label: 'colorNm' , name: 'colorNm', hidden:true,width : 0},
				{ label: 'prchsUtPrc' , name: 'prchsUtPrc', hidden:true,width : 0},
				{ label: 'dstrbUtPrc' , name: 'dstrbUtPrc', hidden:true,width : 0}, 
				{ label: 'ownOrgId' , name: 'ownOrgId', hidden:true,width : 0},
	
	
	   		 	{ label: '<spring:message code="LAB.M08.LAB00148" />', name: 'inoutId', width : 100},		//입고ID
	   		 	{ label: '<spring:message code="LAB.M09.LAB00215" />', name: 'inoutPrgrStatNm', width : 100},		//진행상태
	   		 	{ label: '<spring:message code="LAB.M05.LAB00042" />', name: 'inoutOrgNm', width : 100},	//물류센터
	   		 	{ label: '<spring:message code="LAB.M08.LAB00158" />', name: 'inoutResnNm', width : 100},	//입고사유
	   		 	{ label: '<spring:message code="LAB.M06.LAB00031" />', name: 'poNo', width : 100},				//발주번호
	   		 	{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
	   		 	{ label: '<spring:message code="LAB.M03.LAB00018" />', name: 'eqtRsrcClNm', width : 100},		//단말기자원
	   		 	{ label: '<spring:message code="LAB.M03.LAB00016" />', name: 'eqtUseNm', width : 100},		//단말기용도
	   		 	{ label: '<spring:message code="LAB.M08.LAB00163" />', name: 'inoutDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//입고일자
	   		 	{ label: '<spring:message code="LAB.M08.LAB00160" />', name: 'inoutQty', formatter: 'integer', align:'right', width : 100},	//입고수량
	   		 	{ label: '<spring:message code="LAB.M09.LAB00110" />', name: 'inoutUtPrc', formatter: 'integer', align:'right', width : 100},	//제품단가
	   		 	{ label: '<spring:message code="LAB.M01.LAB00061" />', name: 'inoutAmt', formatter: 'integer', align:'right', width : 100},	//공급가
	   		 	{ label: '<spring:message code="LAB.M06.LAB00088" />', name: 'inoutAddTx', formatter: 'integer', align:'right', width : 100},	//부가세
	   		 	{ label: '<spring:message code="LAB.M10.LAB00051" />', name: 'inoutTotAmt', formatter: 'integer', align:'right', width : 100},	//총금액
	   		 	
	   		 	
	   		 	
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#deliveryInspectionPager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 180,				//화면 상태에 따라 크기 조절 할 것
			//multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
				
	        },
	        loadComplete: function(obj){
	        	$("#deliveryInspectionTable2").setGridWidth($('#deliveryInspectionGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#deliveryInspectionTable2").setGridWidth($('#deliveryInspectionGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#deliveryInspectionTable2").setGridWidth($('#deliveryInspectionGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#deliveryInspectionTable2").setGridWidth($('#deliveryInspectionGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData2(rowId){
		
		$("#deliveryInspectionTable2").clearGridData();  // 이전 데이터 삭제
		
		var data = $("#deliveryInspectionTable").getRowData(rowId);
		var param = new Object();
		
		param.soId = "${session_user.soId}";
		param.inoutClCd = "1";
		param.inoutOrgId = "${session_user.orgId}";
		param.actncNo = data.actncNo;
		
        $("#deliveryInspectionTable2").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	//삭제
	function deleteData(){
		
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');

		if(check){
			
			var rowId  = $("#deliveryInspectionTable2").jqGrid("getGridParam", "selrow" );
			var data = $("#deliveryInspectionTable2").getRowData(rowId);
			
			var param = new Object();
			
			param.soId = data.soId;			//사업ID
			param.inoutId = data.inoutId;	//입고ID
			param.itemId = data.itemId;		//제품ID
			
			$.ajax({
				url : 'deleteInout.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
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
	
	function insertWearingAc(param){
		
		var check = confirm('<spring:message code="MSG.M08.MSG00069" />');		//입고승인 하시겠습니까?

		if(check){
			
			$.ajax({
				url : 'insertWearingAc.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00110" />');	//승인되었습니다.
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
			<td colspan="3">
				<select class="w150" id="searchSoId" name="searchSoId">
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M02.LAB00028"/><!-- 납품일자 --></th>
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
		<h4 class="sub_title"><spring:message code="LAB.M02.LAB00021"/><!-- 납품내역 --></h4>
	</div>
</div>

<div id="deliveryInspectionGrid">
	<table id="deliveryInspectionTable" class="w100p"></table>
	<div id="deliveryInspectionPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M08.LAB00151"/><!-- 입고내역 --></h4>
	</div>
	<div class="fr mt10">
		<!-- <a class="common-btn" title="tooltip" href="www.naver.com">등록</a>
		<a class="common-btn" title="tooltip" href="www.naver.com">삭제</a> -->
	</div>
</div>

<div id="deliveryInspectionGrid2">
	<table id="deliveryInspectionTable2" class="w100p"></table>
	<div id="deliveryInspectionPager2"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnInspSave" title="<spring:message code='LAB.M01.LAB00028'/>" href="#" ><spring:message code="LAB.M01.LAB00028"/><!-- 검수저장 --></a>
		<a class="grey-btn" id="btnWearingAc" title="<spring:message code='LAB.M08.LAB00161'/>" href="#" ><spring:message code="LAB.M08.LAB00161"/><!-- 입고승인 --></a>
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
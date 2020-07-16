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
		
		//초기화버튼
		$('#btn_init').addClass('white-btn');
		$('#btn_init').removeClass('grey-btn');
		$('#btn_init').addClass('not-active');
		$('#btn_init').attr('disabled',true);
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();            
		});
		
		//물류센터
		$("#btnOrgPop").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			var param = new Object();
			param.orgId = "";
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchLgstCentId";	//리턴받는 Id 의 태그아이디값
			param.returnId2 = "searchLgstCentNm";	//리턴받는 Nm 의 태그아이디값
			param.popType2 = "LGST";
			
			$("#popup_dialog").css("width", 1010);
			openModalPopup(url, param);
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
		
		
		
		//납품등록 팝업
		$("#btnDlgdInsert").click(function() {
			
			var rowId  = $("#deliveryConfirmationTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M03.MSG00029" />');	//등록할 발주내역을 선택해주세요.
				return;
			}
			
			var url="deliveryConfirmationInsertPopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 800);
			
			openModalPopup(url, param);
		});
		
		
		
		//납품확인
		$("#btnDlgdConf").click(function() {
			
			
			var ids = $("#deliveryConfirmationTable2").jqGrid("getGridParam", "selarrrow" );
			
			if(ids.length < 1){
				alert('<spring:message code="MSG.M02.MSG00003" />');		//납품내역을 선택해 주세요.
				return;
			}
			
			var check = confirm('<spring:message code="MSG.M02.MSG00007" />');		//납품확인 하시겠습니까?
			if(!check){
				return;
			}
			
			var params = new Array();
			var itemUsgAmt = Number($("#deliveryConfirmationTable2").getRowData(1).itemUsgAmt);
			
			for(var i=0; i<ids.length; i++){
				var param = $("#deliveryConfirmationTable2").getRowData(ids[i]);
				
				if(param.actncPrgrStatCd != "30"){
					alert('<spring:message code="MSG.M02.MSG00004" />');		//납품등록 상태에서만 진행할 수 있습니다.
					return;
				}
				
				itemUsgAmt = itemUsgAmt + Number(param.actncTotAmt);
				param.actncPrgrStatCd = "40";	//발주진행상태 40-납품확인
				param.itemUsgAmt = itemUsgAmt;		
				param.idlStatCd = "10";			//대기상태코드 10-입고대기
				param.statProcDttm = $.datepicker.formatDate('yymmdd', new Date());	//재정의
				
				params.push(param);
				
			}
			
			insertActncIdlDtl(params)
			
		});
		
		//사업 변경
		$('#searchSoId').selectmenu({
		    change: function() {
		    	
		    	$("#searchArea input").val("");
		    	
		    }
		});
		
		
		
	});
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.idlStatCd	= "30";
		
		$("#deliveryConfirmationTable").jqGrid({
			
		   	url:'orderPlacementListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'poOrgId' , name: 'poOrgId', hidden:true,width : 0},
				{ label: 'lgstCentId' , name: 'lgstCentId', hidden:true,width : 0},
				{ label: 'mncoId' , name: 'mncoId', hidden:true,width : 0},
				{ label: 'poPrgrStatCd' , name: 'poPrgrStatCd', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'colorCd' , name: 'colorCd', hidden:true,width : 0},
				{ label: 'dlvZipCd' , name: 'dlvZipCd', hidden:true,width : 0},
				{ label: 'dlvBssAddr' , name: 'dlvBssAddr', hidden:true,width : 0},
				{ label: 'dlvDtlAddr' , name: 'dlvDtlAddr', hidden:true,width : 0},
				{ label: 'actncInchrg' , name: 'actncInchrg', hidden:true,width : 0},
				{ label: 'actncInchrgTel' , name: 'actncInchrgTel', hidden:true,width : 0},
				{ label: 'poInchrgEml' , name: 'poInchrgEml', hidden:true,width : 0},
				{ label: 'poPrgrStatNm' , name: 'poPrgrStatNm', hidden:true,width : 0},

	   		 	{ label: '<spring:message code="LAB.M06.LAB00031" />', name: 'poNo', width : 100},				//발주번호
	   		 	{ label: '<spring:message code="LAB.M06.LAB00044" />', name: 'poConfDt', formatter: stringTypeFormatterYYYYMMDD, align:'center',width : 100},		//발주확정일자
	   		 	{ label: '<spring:message code="LAB.M05.LAB00042" />', name: 'lgstCentNm', width : 100},				//물류센터
	   		 	{ label: '<spring:message code="LAB.M06.LAB00038" />', name: 'poOrgNm', width : 100},				//발주조직
	   		 	{ label: '<spring:message code="LAB.M09.LAB00098" />', name: 'mncoNm', width : 100},			//제조사
	   		 	{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},			//제품유형
	   		 	{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},		//제품ID
	   		 	{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
	   		 	{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', align:'center', width : 100},		//색상
	   		 	{ label: '<spring:message code="LAB.M06.LAB00033" />', name: 'poQty', formatter: 'integer', align:'right', width : 100},	//발주수량
	   		 	{ label: '<spring:message code="LAB.M02.LAB00027" />', name: 'poActncQty', formatter: 'integer', align:'right', width : 100},	//납품수량
	   		 	{ label: '<spring:message code="LAB.M06.LAB00025" />', name: 'poUtPrc', formatter: 'integer', align:'right', width : 100},		//발주단가
	   		 	{ label: '<spring:message code="LAB.M06.LAB00023" />', name: 'poAmt', formatter: 'integer', align:'right', width : 100},		//발주급액
	   		 	{ label: '<spring:message code="LAB.M06.LAB00088" />', name: 'addTx', formatter: 'integer', align:'right', width : 100},		//부가세
	   			{ label: '<spring:message code="LAB.M06.LAB00040" />', name: 'totPrchsAmt', formatter: 'integer', align:'right', width : 100},		//발주총금액
	   			{ label: '<spring:message code="LAB.M02.LAB00031" />', name: 'dlgdAgreeDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//납품합의일자
	   			{ label: '<spring:message code="LAB.M07.LAB00113" />', name: 'chgrId', align:'center', width : 100},		//상태처리자
	   			{ label: '<spring:message code="LAB.M07.LAB00112" />', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDD, align:'center', width : 100},		//상태처리일시
	   			{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'note', width : 100},				//비고
	   			
	   			
	   			//{ label: '<spring:message code="LAB.M06.LAB00039" />', name: 'poPrgrStatNm', width : 100},	//발주진행상태
	   			//{ label: '<spring:message code="LAB.M06.LAB00035" />', name: 'poRegDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//발주일자
	   			//{ label: '<spring:message code="LAB.M06.LAB00049" />', name: 'dlvDtlAddr', width : 100},		//배송지주소
	   			
			    
			    
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#deliveryConfirmationPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 160,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
				
				var data = $("#deliveryConfirmationTable").getRowData(rowId);
				searchData2(data.poNo);
				
	        },
	        loadComplete: function(obj){
	        	$("#deliveryConfirmationTable").setGridWidth($('#deliveryConfirmationGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#deliveryConfirmationTable").setGridWidth($('#deliveryConfirmationGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#deliveryConfirmationTable").setGridWidth($('#deliveryConfirmationGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#deliveryConfirmationTable").setGridWidth($('#deliveryConfirmationGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData(){
		
		$("#deliveryConfirmationTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.searchStatDt = dateFormatToStringYYYYMMDD( $("#searchStatDt").val() );
		param.searchEndDt = dateFormatToStringYYYYMMDD( $("#searchEndDt").val() );
		param.mncoId = $("#searchMncoId").val();
		param.lgstCentId = $("#searchLgstCentId").val();
		param.idlStatCd	= "30";
		
        $("#deliveryConfirmationTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}

	
	function initGrid2() {
		
		var param = new Object();
		param.poNo	= "000000000000";
		
		$("#deliveryConfirmationTable2").jqGrid({
			
		   	url:'insertActncAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'actncNo' , name: 'actncNo', hidden:true,width : 0},
				{ label: 'actncPrgrStatCd' , name: 'actncPrgrStatCd', hidden:true,width : 0},
				{ label: 'itemUsgAmt' , name: 'itemUsgAmt', hidden:true,width : 0} ,

	   		 	{ label: '<spring:message code="LAB.M02.LAB00029" />', name: 'actncPrgrStatNm', width : 100},	//납품진행상태
	   		 	{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},				//제품유형
	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},		//제품ID
	   		 	{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
	   		 	{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', align:'center', width : 100},		//색상
	   		 	{ label: '<spring:message code="LAB.M02.LAB00028" />', name: 'actncDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//납품일자
	   		 	{ label: '<spring:message code="LAB.M02.LAB00027" />', name: 'actncQty', formatter: 'integer', align:'right', width : 100},	//납품수량
	   		 	{ label: '<spring:message code="LAB.M02.LAB00022" />', name: 'actncUtPrc', formatter: 'integer', align:'right', width : 100},		//납품단가
	   		 	{ label: '<spring:message code="LAB.M02.LAB00020" />', name: 'actncAmt', formatter: 'integer', align:'right', width : 100},		//납품금액
	   		 	{ label: '<spring:message code="LAB.M02.LAB00026" />', name: 'actncAddTx', formatter: 'integer', align:'right', width : 100},		//부가세
	   		 	{ label: '<spring:message code="LAB.M02.LAB00030" />', name: 'actncTotAmt', formatter: 'integer', align:'right', width : 100},		//발주총금액
	   		 	{ label: '<spring:message code="LAB.M02.LAB00023" />', name: 'actncInchrgId', width : 100},		//납품담당자
	   		 	{ label: '<spring:message code="LAB.M03.LAB00033" />', name: 'actncInchrgTelNo', width : 100},		//담당자연락처
	   		 	{ label: '<spring:message code="LAB.M10.LAB00021" />', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDD, align:'center', width : 100},		//처리일시
	   		 	{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'note', width : 100},				//비고
	   			
			    { label: 'soId' , name: 'soId', hidden:true,width : 0},
			    { label: 'actncNo' , name: 'actncNo', hidden:true,width : 0},
			    { label: 'actncPrgrStatCd' , name: 'actncPrgrStatCd', hidden:true,width : 0},
			    { label: 'itemUsgAmt' , name: 'itemUsgAmt', hidden:true,width : 0} 
			    
			    
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#deliveryConfirmationPager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 160,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				var data = $("#deliveryConfirmationTable2").getRowData(rowId);
				
	        },
	        loadComplete: function(obj){
	        	$("#deliveryConfirmationTable2").setGridWidth($('#deliveryConfirmationGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#deliveryConfirmationTable2").setGridWidth($('#deliveryConfirmationGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#deliveryConfirmationTable2").setGridWidth($('#deliveryConfirmationGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#deliveryConfirmationTable2").setGridWidth($('#deliveryConfirmationGrid2').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData2(poNo){
		
		$("#deliveryConfirmationTable2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.poNo = poNo;
		
        $("#deliveryConfirmationTable2").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	//납품확인
	function insertActncIdlDtl(param){
		
		if(param != false){
			
			param = JSON.stringify(param)
			
			$.ajax({
				url : 'insertActncIdlDtl.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						var rowId  = $("#deliveryConfirmationTable").jqGrid("getGridParam", "selrow" );
						var data = $("#deliveryConfirmationTable2").getRowData(rowId);
						searchData2(data.poNo); 
						
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
	
	function btnActive(id){
		$('#'+id).addClass('grey-btn');
		$('#'+id).removeClass('white-btn');
		$('#'+id).removeClass('not-active');
		$('#'+id).removeAttr('disabled');
	}
	
	function btnNonActive(id){
		$('#'+id).addClass('white-btn');
		$('#'+id).removeClass('grey-btn');
		$('#'+id).addClass('not-active');
		$('#'+id).attr('disabled',true);
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
	
	
	
	
<table class="writeview" id="searchArea" >
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
				<select class="w270" id="searchSoId" name="searchSoId">
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
				
			</td>
			<th><spring:message code="LAB.M05.LAB00042"/><!-- 물류센터 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchLgstCentNm" name="searchLgstCentNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchLgstCentId" name="searchLgstCentId" />
					<a href="#" id="btnOrgPop" name="btnOrgPop" class="search">search</a>
				</div> 
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchMncoNm" name="searchMncoNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchMncoId" name="searchMncoId" />
					<a href="#" id="btnSearchMnco" name="btnSearchMnco" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					
				</div> 
			</td>
			<th><spring:message code="LAB.M06.LAB00035"/><!-- 발주일자 --></th>
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
		<h4 class="sub_title"><spring:message code="LAB.M06.LAB00024"/><!-- 발주내역 --></h4>
	</div>
</div>

<div id="deliveryConfirmationGrid">
	<table id="deliveryConfirmationTable" class="w100p"></table>
	<div id="deliveryConfirmationPager"></div>
</div>


<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M02.LAB00021"/><!-- 납품내역 --></h4>
	</div>
</div>

<div id="deliveryConfirmationGrid2">
	<table id="deliveryConfirmationTable2" class="w100p"></table>
	<div id="deliveryConfirmationPager2"></div>
</div>


<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnDlgdInsert" name="btnDlgdInsert" title="<spring:message code='LAB.M02.LAB00024'/>" href="#" ><spring:message code="LAB.M02.LAB00024"/><!-- 납품등록 --></a>
		<a class="grey-btn" id="btnDlgdConf" name="btnDlgdConf" title="<spring:message code='LAB.M02.LAB00032'/>" href="#" ><spring:message code="LAB.M02.LAB00032"/><!-- 납품확인 --></a>
		<%-- <a class="grey-btn" id="btnWearingNum" name="btnWearingNum" title="<spring:message code='LAB.M08.LAB00150'/>" href="#" ><spring:message code="LAB.M08.LAB00150"/><!-- 입고검수 --></a> --%>
	</div>
	<div class="fr">
		<!--초기화-->		
		<a class="grey-btn" href="#" id="btn_init"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
		<%-- 
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
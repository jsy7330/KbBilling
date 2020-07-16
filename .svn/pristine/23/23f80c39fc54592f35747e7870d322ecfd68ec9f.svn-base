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
		
		//초기화버튼
		$('#btn_init').addClass('white-btn');
		$('#btn_init').removeClass('grey-btn');
		$('#btn_init').addClass('not-active');
		$('#btn_init').attr('disabled',true);
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();            
		});
		
		//등록
		$("#btn_insert").click(function() {
			
			var url="orderPlacementInsertPopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 800);
			
			openModalPopup(url, param);
		});
		
		//수정
		$("#btn_update").click(function() {
			
			var rowId  = $("#orderPlacementTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M07.MSG00099" />');	//수정할 발주내역을 선택해주세요.
				return;
			}
			
			//poPrgrStatCd
			var data = $("#orderPlacementTable").getRowData(rowId);
			console.log(data);
			if(data.poPrgrStatCd != '10'){
				alert('<spring:message code="MSG.M06.MSG00014" />');	//발주확정, 발주취소건은 수정할 수 없습니다.
				return;
			}
			
			var url="orderPlacementUpdatePopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 800);
			
			openModalPopup(url, param);
		});
		
		//btnNonActive("btnPoConf");
		//btnNonActive("btnPoCan");
		
		btnActive("btnPoConf");
		btnActive("btnPoCan");
		
		//발주확정
		$("#btnPoConf").click(function() {
			/* 
			var ids = $("#orderPlacementTable").jqGrid("getGridParam", "selarrrow" );
			
			if(ids.length < 1){
				alert('<spring:message code="MSG.M06.MSG00008" />');		//발주내역을 선택해 주세요.
				return;
			}
			
			var check = confirm('<spring:message code="MSG.M06.MSG00015" />');		//발주확정을 하시겠습니까?
			if(!check){
				return;
			}
			
			
			var params = new Array();
			for(var i=0; i<ids.length; i++){
				var param = $("#orderPlacementTable").getRowData(ids[i]);
				if(param.poPrgrStatCd != "10"){
					alert('<spring:message code="MSG.M06.MSG00009" />');		//발주등록 상태에서만 진행할 수 있습니다.
					return;
				}
				
				param.poPrgrStatCd = "20";
				param.idlStatCd = "30";
				param.paramType = "01";
				param.poConfDt = $.datepicker.formatDate('yymmdd', new Date());
				
				params.push(param);
				
			}
			 */
			 
			var rowId  = $("#orderPlacementTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M06.MSG00008" />');		//발주내역을 선택해 주세요.
				return;
			}
			
			var check = confirm('<spring:message code="MSG.M06.MSG00015" />');		//발주확정을 하시겠습니까?
			if(!check){
				return;
			}
			 
			var params = new Array();
			var param = $("#orderPlacementTable").getRowData(rowId);
			if(param.poPrgrStatCd != "10"){
				alert('<spring:message code="MSG.M06.MSG00009" />');		//발주등록 상태에서만 진행할 수 있습니다.
				return;
			}
			
			param.poPrgrStatCd = "20";
			param.idlStatCd = "30";
			param.paramType = "01";
			param.poConfDt = $.datepicker.formatDate('yymmdd', new Date());
			
			params.push(param);
			
			updatePoPrgrStatCd(params);
			
		});
		
		//발주취소
		$("#btnPoCan").click(function() {
			/* 
			var ids = $("#orderPlacementTable").jqGrid("getGridParam", "selarrrow" );
			
			if(ids.length < 1){
				alert('<spring:message code="MSG.M06.MSG00008" />');		//발주내역을 선택해 주세요.
				return;
			}
			
			var check = confirm('<spring:message code="MSG.M06.MSG00012" />');	//발주취소를 하시겠습니까?
			if(!check){
				return;
			}
			
			var params = new Array();
			for(var i=0; i<ids.length; i++){
				var param = $("#orderPlacementTable").getRowData(ids[i]);
				if(param.poPrgrStatCd != "20"){
					alert('<spring:message code="MSG.M06.MSG00013" />');		//발주확정 상태에서만 진행할 수 있습니다.
					return;
				}
				
				if(param.poActncQty > 0){
					alert('<spring:message code="MSG.M02.MSG00005" />');		//납품등록 상태입니다. 발주취소를 할 수 없습니다.
					return;
				}
				
				
				param.poPrgrStatCd = "25";
				param.paramType = "02";
				param.poConfDt = $.datepicker.formatDate('yymmdd', new Date());
				
				params.push(param);
				
			}
			 */
			 
			var rowId  = $("#orderPlacementTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M06.MSG00008" />');		//발주내역을 선택해 주세요.
				return;
			}
			
			var check = confirm('<spring:message code="MSG.M06.MSG00012" />');	//발주취소를 하시겠습니까?
			if(!check){
				return;
			}
			
			var params = new Array();
			var param = $("#orderPlacementTable").getRowData(rowId);
			if(param.poPrgrStatCd != "20"){
				alert('<spring:message code="MSG.M06.MSG00013" />');		//발주확정 상태에서만 진행할 수 있습니다.
				return;
			}
			
			if(param.poActncQty > 0){
				alert('<spring:message code="MSG.M02.MSG00005" />');		//납품등록 상태입니다. 발주취소를 할 수 없습니다.
				return;
			}
			
			param.poPrgrStatCd = "25";
			param.paramType = "02";
			param.poConfDt = $.datepicker.formatDate('yymmdd', new Date());
			
			params.push(param);
			
			updatePoPrgrStatCd(params);
			
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
		
	});
	
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		
		$("#orderPlacementTable").jqGrid({
			
		   	url:'orderPlacementListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'poOrgId' , name: 'poOrgId', hidden:true,width : 0},
				{ label: 'lgstCentId' , name: 'lgstCentId', hidden:true,width : 0},
				{ label: 'lgstCentNm' , name: 'lgstCentNm', hidden:true,width : 0},
				{ label: 'mncoId' , name: 'mncoId', hidden:true,width : 0},
				{ label: 'poPrgrStatCd' , name: 'poPrgrStatCd', hidden:true,width : 0},
				{ label: 'itemId' , name: 'itemId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'colorCd' , name: 'colorCd', hidden:true,width : 0},
				{ label: 'dlvZipCd' , name: 'dlvZipCd', hidden:true,width : 0},
				{ label: 'dlvBssAddr' , name: 'dlvBssAddr', hidden:true,width : 0},
				{ label: 'dlvDtlAddr' , name: 'dlvDtlAddr', hidden:true,width : 0},
				{ label: 'actncInchrg' , name: 'actncInchrg', hidden:true,width : 0},
				{ label: 'actncInchrgTel' , name: 'actncInchrgTel', hidden:true,width : 0},
				{ label: 'note' , name: 'note', hidden:true,width : 0},
				{ label: 'poInchrgEml' , name: 'poInchrgEml', hidden:true,width : 0},
				{ label: 'poPrgrStatNm' , name: 'poPrgrStatNm', hidden:true,width : 0},
				{ label: 'poActncQty' , name: 'poActncQty', hidden:true,width : 0},

	   		 	{ label: '<spring:message code="LAB.M06.LAB00031" />', name: 'poNo', width : 100},				//발주번호
	   			{ label: '<spring:message code="LAB.M06.LAB00039" />', name: 'poPrgrStatNm', width : 100},	//발주진행상태
	   			{ label: '<spring:message code="LAB.M09.LAB00098" />', name: 'mncoNm', width : 100},			//제조사
	   			{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},			//제품유형
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},		//제품명
	   			{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', align:'center', width : 100},		//색상
	   			{ label: '<spring:message code="LAB.M06.LAB00033" />', name: 'poQty', formatter: 'integer', align:'right', width : 100},	//발주수량
	   			{ label: '<spring:message code="LAB.M06.LAB00025" />', name: 'poUtPrc', formatter: 'integer', align:'right', width : 100},		//발주단가
	   			{ label: '<spring:message code="LAB.M06.LAB00022" />', name: 'poAmt', formatter: 'integer', align:'right', width : 100},		//발주공급액
	   			{ label: '<spring:message code="LAB.M06.LAB00088" />', name: 'addTx', formatter: 'integer', align:'right', width : 100},		//부가세
	   			{ label: '<spring:message code="LAB.M06.LAB00040" />', name: 'totPrchsAmt', formatter: 'integer', align:'right', width : 100},		//발주총금액
	   			{ label: '<spring:message code="LAB.M06.LAB00035" />', name: 'poRegDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//발주일자
	   			{ label: '<spring:message code="LAB.M02.LAB00031" />', name: 'dlgdAgreeDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//납품합의일자
	   			{ label: '<spring:message code="LAB.M06.LAB00049" />', name: 'dlvDtlAddr', width : 100, hidden:true},		//배송지주소
	   			
			    
			    
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#orderPlacementPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 260,				//화면 상태에 따라 크기 조절 할 것
			//multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
				/* 
				var data = $("#orderPlacementTable").getRowData(rowId);
				if(data.poPrgrStatCd == "10"){
					btnActive("btnPoConf");
					btnNonActive("btnPoCan");
				}else if(data.poPrgrStatCd == "20"){
					btnNonActive("btnPoConf");
					btnActive("btnPoCan");
				}else{
					btnNonActive("btnPoConf");
					btnNonActive("btnPoCan");
				}
				 */
				
	        },
	        loadComplete: function(obj){
	        	$("#orderPlacementTable").setGridWidth($('#orderPlacementGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#orderPlacementTable").setGridWidth($('#orderPlacementGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#orderPlacementTable").setGridWidth($('#orderPlacementGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#orderPlacementTable").setGridWidth($('#orderPlacementGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData(){
		
		$("#orderPlacementTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.searchStatDt = dateFormatToStringYYYYMMDD( $("#searchStatDt").val() );
		param.searchEndDt = dateFormatToStringYYYYMMDD( $("#searchEndDt").val() );
		param.mncoId = $("#searchMncoId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		param.poPrgrStatCd = $("#searchPoPrgrStatCd").val();
		
        $("#orderPlacementTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	//발주 등록 취소
	function updatePoPrgrStatCd(param){
		
		if(param != false){
			
			param = JSON.stringify(param)
			
			$.ajax({
				url : 'updatePoPrgrStatCd.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData(); 
						
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
			<th><spring:message code="LAB.M06.LAB00039"/><!-- 발주진행상태 --></th>
			<td>
				<select id="searchPoPrgrStatCd" name="searchPoPrgrStatCd" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${poPrgrStatCd}" var="poPrgrStatCd" varStatus="status">
						<option value="${poPrgrStatCd.commonCd}">${poPrgrStatCd.commonCdNm}</option>
					</c:forEach>
				</select>                
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M06.LAB00024"/><!-- 발주내역 --></h4>
	</div>
</div>

<div id="orderPlacementGrid">
	<table id="orderPlacementTable" class="w100p"></table>
	<div id="orderPlacementPager"></div>
</div>


<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" id="btnPoConf" name="btnPoConf" title="<spring:message code='LAB.M06.LAB00043'/>" href="#" ><spring:message code="LAB.M06.LAB00043"/><!-- 발주확정 --></a>
		<a class="grey-btn" id="btnPoCan" name="btnPoCan" title="<spring:message code='LAB.M06.LAB00041'/>" href="#" ><spring:message code="LAB.M06.LAB00041"/><!-- 발주취소 --></a>
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
		<%-- 
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
	
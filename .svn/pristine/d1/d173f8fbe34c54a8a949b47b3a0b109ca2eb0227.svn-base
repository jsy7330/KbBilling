<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	
	//jqgrid 호출
	initGrid();	
	
	// 셀렉트 박스 값 가져오기
	var ifSys = "${attribute.ifSys}";
	 $('#ifSys').val(ifSys);
	 $('#ifSys').selectmenu("refresh");

	//조회
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	//등록 페이지 이동
	$("#btn_add").click(function() {
		goInsertPage();
	});


	//속성 등록 처리
	$("#btn_insert").click(function() {
		goInsertPopUp();
	});

	//속성 수정 처리
	$("#btn_update").click(function() {
		goUpdatePopUp();
	});	

	//삭제처리
	$("#btn_delete").click(function() {
		goDelete();
	});

	$(window).resize(function() {
		$("#allowanceDiscountGrid").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function initGrid() {

	var param = new Object();
	param.discDedtNm = $("#discDedtNm").val();
	$("#allowanceDiscountGrid").jqGrid({
		
	   	url:'allowanceDiscountListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
	   	    { label: '조건과금요소1', name: 'condRateFac1', hidden:true, width : 100},			//1
			{ label: '조건연산자1', name: 'condOperator1', hidden:true, width : 100},			//2
			{ label: '조건값1', name: 'condVal1', hidden:true, width : 100},					//3
			{ label: '조건연결자1-2', name: 'logicalOperator12', hidden:true, width : 100},	//4
			{ label: '조건과금요소2', name: 'condRateFac2', hidden:true, width : 100},			//5
			{ label: '조건연산자2', name: 'condOperator2', hidden:true, width : 100},			//6
			{ label: '조건값2', name: 'condVal2', hidden:true, width : 100},					//7
			{ label: '공제/할인여부', name: 'dedtDiscFlag', hidden:true, width : 100},			//8
			{ label: '공제유형', name: 'usgTypLvl', hidden:true, width : 100},					//9
			{ label: '사용유형그룹', name: 'usgTypGrpCd', hidden:true,  width : 100},				//10
			{ label: '사용항목유형', name: 'usgItmTyp', hidden:true, width : 100},				//11
			{ label: '상품적용유형', name: 'freeTyp', hidden:true, width : 100},					//12
			{ label: '가입시일할계산', name: 'subscProrateFlag', hidden:true, width : 100},		//14
			{ label: '해지시일할계산', name: 'termProrateFlag', hidden:true, width : 100},		//15
			{ label: '공제적용수준', name: 'applLvl', hidden:true, width : 100},					//16
			{ label: '공제부여주기', name: 'replenishCycl', hidden:true, width : 100},			//17
			{ label: '공제코드유형', name: 'dedtTyp', hidden:true, width : 100},					//18
			{ label: '공제량FLAG', name: 'amtFlag', hidden:true, width : 100},					//19
			{ label: '공제량단위', name: 'amtUnit', hidden:true, width : 100},					//20
			{ label: '추가허용량', name: 'maxAccumBal', hidden:true, width : 100},				//21
			{ label: 'effDt', name: 'effDt', hidden:true, width : 100},
			{ label: 'usgItmCd', name: 'usgItmCd', hidden:true, width : 100},	
			{ label: 'duration', name: 'duration', hidden:true, width : 100},					
			{ label: 'durationUnit', name: 'durationUnit', hidden:true, width : 100},					
			{ label: '<spring:message code="LAB.M11.LAB00006"/>', name: 'discDedtCd', width : 90, align:"center"},								//24		
  	 		{ label: '<spring:message code="LAB.M01.LAB00087"/>', name: 'discDedtNm', width : 160, align:"center"},							
  	 		{ label: '<spring:message code="LAB.M06.LAB00019"/>', name: 'durationUnitNm', width : 80, align:"center"},							
  	 		{ label: '<spring:message code="LAB.M06.LAB00020"/>', name: 'duration', width : 70, align:"center"},							
  	 		{ label: '<spring:message code="LAB.M07.LAB00148"/>', name: 'freeTypNm', width : 80, align:"center"},							
  	 		{ label: '<spring:message code="LAB.M01.LAB00064"/>', name: 'dedtDiscFlagNm', width : 80, align:"center"},						
  	 		{ label: '<spring:message code="LAB.M07.LAB00042"/>', name: 'usgTypLvlNm', width : 90, align:"center"},							//22
  	 		{ label: '<spring:message code="LAB.M07.LAB00037"/>', name: 'usgTypGrpCdNm', width : 80, align:"center"},						
  	 		{ label: '<spring:message code="LAB.M07.LAB00079"/>', name: 'usgItmTypNm', width : 80, align:"center"},							
  	 		{ label: '<spring:message code="LAB.M07.LAB00080"/>', name: 'usgItmCd', width : 80, align:"center"},								
  	 		{ label: '<spring:message code="LAB.M01.LAB00080"/>', name: 'applLvlNm', width : 80, align:"center"},							
  	 		{ label: '<spring:message code="LAB.M01.LAB00077"/>', name: 'replenishCyclNm', width : 80, align:"center"},						
  	 		{ label: '<spring:message code="LAB.M01.LAB00086"/>', name: 'dedtTypNm', width : 90, align:"center"},							
  	 		{ label: '<spring:message code="LAB.M01.LAB00074"/>', name: 'amtFlagNm', width : 80, align:"center"},							
  	 		{ label: '<spring:message code="LAB.M01.LAB00075"/>', name: 'amtUnitNm', width : 100, align:"center"},								
  	 		{ label: '<spring:message code="LAB.M01.LAB00073"/>', name: 'quantity', width : 80, align:"center"},							//23
  	 		{ label: '<spring:message code="LAB.M09.LAB00039"/>', name: 'balCrOvrMethodNm', width : 130, align:"center"},						
  	 		{ label: '<spring:message code="LAB.M01.LAB00068"/>', name: 'smsNotiFlagNm', width : 150, align:"center"},						
   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 240,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	btnActive("btn_update");
        	btnActive("btn_delete");
        },
        loadComplete: function(obj){
        	$("#allowanceDiscountGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	btnNonActive("btn_update");
        	btnNonActive("btn_delete");
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#allowanceDiscountGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#allowanceDiscountGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#allowanceDiscountGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

}

function goInsertPopUp() {
	var param = new Object();
	var url = "allowanceDiscountInsertPopUp.ajax";
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goUpdatePopUp() {
	var param = new Object();
	var rowId = jQuery("#allowanceDiscountGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00096" />");
		return;
	}
		
	param.modCondRateFac1 = $("#allowanceDiscountGrid").getRowData(rowId).condRateFac1;
	param.modCondOp1 = $("#allowanceDiscountGrid").getRowData(rowId).condOperator1;
	param.modCondVal1 = $("#allowanceDiscountGrid").getRowData(rowId).condVal1;
	param.modLogicalOperator12 = $("#allowanceDiscountGrid").getRowData(rowId).logicalOperator12;
	param.modCondRateFac2 = $("#allowanceDiscountGrid").getRowData(rowId).condRateFac2;
	param.modCondOp2 = $("#allowanceDiscountGrid").getRowData(rowId).condOperator2;
	param.modCondVal2 = $("#allowanceDiscountGrid").getRowData(rowId).condVal2;
	param.modDedtDiscFlag = $("#allowanceDiscountGrid").getRowData(rowId).dedtDiscFlag;
	param.modUsgTypLvl = $("#allowanceDiscountGrid").getRowData(rowId).usgTypLvl;
	param.modUsgTypGrpCd = $("#allowanceDiscountGrid").getRowData(rowId).usgTypGrpCd;
	param.modUsgItmTyp = $("#allowanceDiscountGrid").getRowData(rowId).usgItmTyp;
	param.modFreeTyp = $("#allowanceDiscountGrid").getRowData(rowId).freeTyp;
	param.modDedtDiscFlag = $("#allowanceDiscountGrid").getRowData(rowId).dedtDiscFlag;
	param.modSubscProrateFlag = $("#allowanceDiscountGrid").getRowData(rowId).subscProrateFlag;
	param.modTermProrateFlag = $("#allowanceDiscountGrid").getRowData(rowId).termProrateFlag;
	param.modApplLvl = $("#allowanceDiscountGrid").getRowData(rowId).applLvl;
	param.modReplenishCycl = $("#allowanceDiscountGrid").getRowData(rowId).replenishCycl;
	param.modDedtTyp = $("#allowanceDiscountGrid").getRowData(rowId).dedtTyp;
	param.modAmtFlag = $("#allowanceDiscountGrid").getRowData(rowId).amtFlag;
	param.modAmtUnit = $("#allowanceDiscountGrid").getRowData(rowId).amtUnit;
	param.modMaxAccumBal = $("#allowanceDiscountGrid").getRowData(rowId).maxAccumBal;
	param.modUsgTypLvlNm = $("#allowanceDiscountGrid").getRowData(rowId).usgTypLvlNm;
	param.modQuantity = $("#allowanceDiscountGrid").getRowData(rowId).quantity;
	param.modDiscDedtCd = $("#allowanceDiscountGrid").getRowData(rowId).discDedtCd;
	param.modDiscDedtNm = $("#allowanceDiscountGrid").getRowData(rowId).discDedtNm;
	param.modEffDt = $("#allowanceDiscountGrid").getRowData(rowId).effDt;
	param.modDuration = $("#allowanceDiscountGrid").getRowData(rowId).duration;
	param.modDurationUnit = $("#allowanceDiscountGrid").getRowData(rowId).durationUnit;
	param.modUsgItmCd = $("#allowanceDiscountGrid").getRowData(rowId).usgItmCd;
	 
	var url = "allowanceDiscountUpdatePopUp.ajax";
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goDelete() {
	var param = new Object();
	var rowId = jQuery("#allowanceDiscountGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00056"/>");
		return;
	}
		
	$("#discDedtCd").val($("#allowanceDiscountGrid").getRowData(rowId).discDedtCd);
	$("#effDt").val($("#allowanceDiscountGrid").getRowData(rowId).effDt);
	
	if(confirm("삭제하시겠습니까?") == true) {
		$("#allowanceDiscount").attr("action", "/product/refInfo/ratingRefInfo/allowanceDiscount/allowanceDiscountDeleteAction");
		$("#allowanceDiscount").attr("method", "post");
		$("#allowanceDiscount").submit();
	}
	
}

function goSearch() {
	if( $("#searchItem").val() == "" ) {
		alert("조회항목을 선택하세요.");
		return;
	}	

	$("#allowanceDiscount").attr("action", "/product/refInfo/ratingRefInfo/allowanceDiscount/allowanceDiscountList");
	$("#allowanceDiscount").submit();
}

function btnActive(id) {
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

<!--검색-->	
<form id="allowanceDiscount" name="allowanceDiscount" method="post">

	<input type="hidden" id="discDedtCd" name="discDedtCd"/>
	<input type="hidden" id="effDt" name="effDt" />
	
	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M01.LAB00065"/>
				</h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table id="tab1" class="writeview">
		<colgroup>
			<col style="width:10%">
			<col style="width:45%">
			<col style="width:45%">
		</colgroup>
			<thead>			
				<tr>
					<th>
						<spring:message code="LAB.M09.LAB00164"/>
					</th>
					<td>
						<select name="searchItem" id="searchItem" class="w150">
							<option value="">SEL</option>
							<option value="1">Allowance/Discount</option>
						</select>
						&nbsp;&nbsp;
						<input id="discDedtNm" name="discDedtNm" value="${param.discDedtNm }" type="text" class="w300"/>
					</td>		
				</tr>
			</thead>
	</table> 
</form>	

<!--JQ Grid 리스트-->	
<br> 
<div id='gridDiv'>
	<table id="allowanceDiscountGrid" class="w100p"></table>
	<div id="pager2"></div>
</div>


<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">
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
		<a class="grey-btn" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
		<!--출력-->
		<ntels:auth auth="${menuAuthP}">
		<a class="grey-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
	</div>
</div>

<div id="popup_dialog" class="Layer_wrap" style="display:none; width:400px;" >
</div>                   




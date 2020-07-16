<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- amchart -->
<script src="/scripts/amcharts_2.4.2/amcharts.js" type="text/javascript"></script>
<script src="/scripts/amcharts_2.4.2/serial.js" type="text/javascript"></script> 
<script type="text/javascript">


$(document).ready(function() {
	
	var lng = '<%= session.getAttribute("sessionLanguage")%>';
	
	initGrid(lng);
	
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	$('#btn_insert').click(function() {
		goInsertPopUp();
	});
	
	$('#btn_update').click(function() {
		goUpdatePopUp();
	});
	
	$('#btn_delete').click(function() {
		goDelete();
	});
	
	$("#btn_print").click(function() {
		printExcel();	
	});
	
	$(window).resize(function() {
		$("#usageTypeMngGrid").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function initGrid(lng) {
	
	var param = new Object();
	param.searchUsgTypNm = $("#searchUsgTypNm").val();
	$("#usageTypeMngGrid").jqGrid({
		
		url: 'usageTypeMngListAction.json?',
		mtype: "POST",
		datatype: "json",
		postData: param,
		colModel: [
           { label: '사용유형게시일', name: 'effDt', hidden:true, width : 100},			
		   { label: '사용유형만료일', name: 'expDt', hidden:true, width : 100},	
		   { label: '사용제공유형', name: 'usgOfferTyp', hidden:true, width : 100},			
		   { label: '호발생여부', name: 'cdrIndicator', hidden:true, width : 100},			
		   { label: '중복할인방법', name: 'multipleDiscMethod', hidden:true, width : 100},	
		   { label: '기본시간대적용방식', name: 'periodApplMethod', hidden:true, width : 100},	
		   { label: '기본사용금액올림처리방식', name: 'defaultUsgFeeRounding', hidden:true, width : 100},
		   { label: '기본사용량올림처리방식', name: 'defaultUsgRounding', hidden:true, width : 100},
		   { label: '호인증기준값', name: 'callAuthThreshold', hidden:true, width : 100},
		   { label: '<spring:message code="LAB.M07.LAB00052"/>', name: 'usgTyp', align:"center", width : 100},			
   		   { label: '<spring:message code="LAB.M07.LAB00050"/>', name: 'usgTypNm', align:"center", width : 100},			
   		   { label: '<spring:message code="LAB.M07.LAB00078"/>', name: 'usgOfferTypNm', width : 100},		
   		   { label: '<spring:message code="LAB.M14.LAB00058"/>', name: 'cdrIndicatorNm', width : 100},		
   		   { label: '<spring:message code="LAB.M09.LAB00194"/>', name: 'multipleDiscMethodNm', width : 100},
   		   { label: '<spring:message code="LAB.M01.LAB00214"/>', name: 'periodApplMethodNm', width : 100},		
   		   { label: '<spring:message code="LAB.M01.LAB00208"/>', name: 'defaultNoOfUnits', width : 100},			
   		   { label: '<spring:message code="LAB.M01.LAB00209"/>', name: 'defaultUsgRoundingNm', width : 100},	
   		   { label: '<spring:message code="LAB.M01.LAB00207"/>', name: 'defaultUsgRoundingOffset', width : 100},
   		   { label: '<spring:message code="LAB.M01.LAB00206"/>', name: 'defaultUsgFeeRoundingNm', width : 100},
   		],
		rowNum: 10,
		rowList: [ 5, 10, 15, 20, 25 ],
		pager: '#pager2',
		sortable: true,
		sortorder: "desc",
		viewrecords: true,
		height: 240,
		onCellStart: function(rowid, index, contents, event) {
			btnNonActive("btn_update");
			btnNonActive("btn_delete");
		},
		loadComplete: function(obj) {
			$("#usageTypeMngGrid").setGridWidth($('#gridDiv').width(),false);
				btnNonActive("btn_update");
				btnNonActive("btn_delete");
		},
		onCellSelect: function(rowid, index, contents, event) {
			btnActive("btn_update");
			btnActive("btn_delete");
		}
	});

	$("#usageTypeMngGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	$(window).resize(function() {
		$("#usageTypeMngGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

function goSearch() {
	var param = new Object();
	param.page =  $("#page").val();
	param.perPage =  $("#perPage").val();
	$("#usageTypeMng").attr("action", "/product/refInfo/ratingRefInfo/usageType/usageTypeMngList");
	$("#usageTypeMng").submit();
}

function goInsertPopUp() {
	var param = new Object();
	var url="usageTypeMngInsertPopUp.ajax";
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goUpdatePopUp() {
	var url="usageTypeMngUpdatePopUp.ajax";
	var rowId = jQuery("#usageTypeMngGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00096" />");
		return;
	}
	
	var param = new Object();
	param.usgTyp = $("#usageTypeMngGrid").getRowData(rowId).usgTyp;
	param.usgTypNm = $("#usageTypeMngGrid").getRowData(rowId).usgTypNm;
	param.usgOfferTyp = $("#usageTypeMngGrid").getRowData(rowId).usgOfferTyp;
	param.cdrIndicator = $("#usageTypeMngGrid").getRowData(rowId).cdrIndicator;
	param.multipleDiscMethod = $("#usageTypeMngGrid").getRowData(rowId).multipleDiscMethod;
	param.periodApplMethod = $("#usageTypeMngGrid").getRowData(rowId).periodApplMethod;
	param.defaultNoOfUnits = $("#usageTypeMngGrid").getRowData(rowId).defaultNoOfUnits;
	param.defaultUsgRounding = $("#usageTypeMngGrid").getRowData(rowId).defaultUsgRounding;
	param.defaultUsgRoundingOffset = $("#usageTypeMngGrid").getRowData(rowId).defaultUsgRoundingOffset;
	param.defaultUsgFeeRounding = $("#usageTypeMngGrid").getRowData(rowId).defaultUsgFeeRounding;
	param.parentUsgTyp = $("#usageTypeMngGrid").getRowData(rowId).parentUsgTyp;
	param.callAuthThreshold = $("#usageTypeMngGrid").getRowData(rowId).callAuthThreshold;
	param.effDt = $("#usageTypeMngGrid").getRowData(rowId).effDt;
	param.expDt = $("#usageTypeMngGrid").getRowData(rowId).expDt;

		
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goDelete() {
	var rowId = jQuery("#usageTypeMngGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056" />");
		return;
	}
	
	$("#delUsgTyp").val($("#usageTypeMngGrid").getRowData(rowId).usgTyp);
	$("#delEffDt").val($("#usageTypeMngGrid").getRowData(rowId).effDt);
	
	if( confirm("<spring:message code="MSG.M07.MSG00054"/>") == true) {
		$("#usageTypeMng").attr("action", "/product/refInfo/ratingRefInfo/usageType/usageTypeMngDeleteAction");
		$("#usageTypeMng").submit();
	}
}

function printExcel(){
	var usgTypNm = $("#searchUsgTypNm").val();

	var param = 'usgTypNm=' + usgTypNm;

	$.download('usageTypeMngListExcelAction.xlsx', param, 'post');	
}

function setSelectedData(rowId) {
	var data = $("#usageTypeMngGrid").getRowData(rowId);
	
	$("#chrg_cd").val(data.chrg_cd);
	$("#chrg_cd").selectmenu("refresh");
	$("#description").val(data.description);
	$("#description").selectmenu("refresh");
	$("#usg_typ").val(data.usg_typ);
	$("#usg_typ").selectmenu("refresh");
	$("#usg_typ_nm").val(data.usg_typ_nm);
	$("#usg_typ_nm").selectmenu("refresh");
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


<!-- 검색 -->
<form id="usageTypeMng" name="usageTypeMng" method="post">
	<input type="hidden" id="delUsgTyp" name="delUsgTyp"/>
	<input type="hidden" id="delEffDt" name="delEffDt"/>
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M07.LAB00033"/>
			</h4>
		</div>
		<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:50%;">
			<col style="width:40%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00050"/>
				</th>
				<td colspan='2'>
					<input id="searchUsgTypNm" name="searchUsgTypNm" value="${param.searchUsgTypNm }" type="text" class="w270">
				</td>
			</tr>
		</thead>
	</table>
</form>	

<br>
<!--JQ Grid 리스트-->	

<div id='gridDiv'>
<table id="usageTypeMngGrid" class="w100p"></table>
	<div id="pager2">
	</div>
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
		<a class="grey-btn" id="btn_print" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
	
	</div>
</div>
<div id="popup_dialog" class="Layer_wrap" style="display:none; width:100px;">
</div>            

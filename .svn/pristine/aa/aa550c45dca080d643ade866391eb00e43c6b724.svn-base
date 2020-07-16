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
	
	$("#soNm").selectmenu({
		change: function() {
			$("#searchSoNm").val($("#soNm").val());
		}
	});
	$("#soNm").val($("#searchSoNm").val());
	$("#soNm").selectmenu("refresh");
	
	$("#typ").selectmenu({
		change: function() {
			$("#searchTyp").val($("#typ").val());
		}
	});
	$("#typ").val($("#searchTyp").val());
	$("#typ").selectmenu("refresh");
	
	$("#prodTyp").selectmenu({
		change: function() {
			$("#searchProdTyp").val($("#prodTyp").val());
		}
	});
	$("#prodTyp").val($("#searchProdTyp").val());
	$("#prodTyp").selectmenu("refresh");
	
	//검색 버튼 클릭 시
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	$("#btn_print").click(function() {
		printExcel();
	});

	$(window).resize(function() {
		$("#productDtlGrid").setGridWidth($('#gridDiv').width()); 
	});
	
	var resultMsg = "${resultMsg}";
	if( resultMsg != null && resultMsg != "" ) {
		alert("${resultMsg}");
	}
});

function initGrid(lng) {
	var param = new Object();
	param.searchSoNm = $("#searchSoNm").val();
	param.searchProdTyp = $("#searchProdTyp").val();
	param.searchTyp = $("#searchTyp").val();
	param.searchProd = $("#searchProd").val();
	
	$("#productDtlGrid").jqGrid({
		
		url: 'productDtlListAction.json?',
		mtype: "POST",
		datatype: "json",
		postData: param,
		colModel: [
   				{ label:'<spring:message code="LAB.M07.LAB00003"/>',name:'soNm', width:50 },
   				{ label:'<spring:message code="LAB.M07.LAB00156"/>',name:'prodCd',  width:50 },
   				{ label:'<spring:message code="LAB.M07.LAB00130"/>',name:'prodNm',  width:50 },
   		   		{ label:'<spring:message code="LAB.M07.LAB00127"/>',name:'prodGrp', width:50 },
   				{ label:'<spring:message code="LAB.M07.LAB00143"/>',name:'prodDesc', width:50 },
   				{ label:'<spring:message code="LAB.M07.LAB00141"/>',name:'basicProdFl',  width:50 },
   				{ label:'<spring:message code="LAB.M07.LAB00147"/>',name:'actDt',  width:50 },
   		   		{ label:'<spring:message code="LAB.M13.LAB00010"/>',name:'inactDt', width:50 }
   		],
		rowNum: 10,
		rowList: [ 5, 10, 15, 20, 25 ],
		pager: '#pager2',
		sortable: true,
		sortorder: "desc",
		viewrecords: true,
		height: 240,
		onCellStart: function(rowid, index, contents, event) {
			setSelectedData(rowId)
		},
		loadComplete: function(obj) {
			$("#productDtlGrid").setGridWidth($('#gridDiv').width(),false);
		}
	});
	$("#productDtlGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	$(window).resize(function() {
		$("#productDtlGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

function goSearch() {
	var param = new Object();
	param.page = $("#page").val();
	param.perPage = $("#perPage").val();
	param.searchSoNm = $("#searchsearchSoNm").val();
	param.searchProdTyp = $("#searchProdTyp").val();
	param.searchTyp = $("#searchTyp").val();
	param.searchProd = $("#searchProd").val();
	
	$("#productDtlList").attr("action", "/product/usageProduct/usageProductMgt/productDtlList/productDtlList");
	$("#productDtlList").submit();
}

function printExcel(){
	var param = 'soNm=' + "";
	param = param + '&prodCd=' + "";
	param = param + '&prodNm=' + "";
	param = param + '&prodGrp=' + "";
	param = param + '&prodDesc=' + "";
	param = param + '&basicProdTyp=' + "";
	param = param + '&actDt=' + "";
	param = param + '&inactDt=' + "";

	$.download('productDtlListExcelAction.xlsx', param, 'post');	
}

function setSelectedData(rowId) {
	var data = $("#productDtlGrid").getRowData(rowId);
	
	$("#ratingCd").val(data.ratingcd);
	$("#ratingCd").selectmenu("refresh");
	$("#ratingDesc").val(data.ratingDesc);
	$("#ratingDesc").selectmenu("refresh");
	$("#usgTyp").val(data.usgTyp);
	$("#usgTyp").selectmenu("refresh");
	$("#usgTypNm").val(data.usgTypNm);
	$("#usgTypNm").selectmenu("refresh");
}

function btnActive(id) {
	$('#'+id).addClass(grey-btn);
	$('#'+id).removeClass('white-btn');
	$('#'+id).removeClass('non-active');
	$('#'+id).removeClass('disabled');
}

function labelActive(id){
	$('#'+id).addClass('white-btn');
	$('#'+id).removeClass('grey-btn');
	$('#'+id).removeClass('not-active');
	$('#'+id).removeAttr('disabled');
}

function labelNonActive(id){
	$('#'+id).addClass('grey-btn');
	$('#'+id).removeClass('white-btn');
	$('#'+id).addClass('not-active');
	$('#'+id).attr('disabled',true);
}

function checkValidation() {
	var param = new Object();
	param.chrg_cd = $("#chrg_cd").val();
	param.description = $("#description").val();
	param.usg_typ = $("#usg_typ").val();
	param.usg_typ_nm = $("#usg_typ_nm").val();
	
	return param;
}

</script>


<!-- NaviGator -->
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


<!-- 검색  -->
<form id="productDtlList" name="productDtlList" method="post">

	<input type="hidden" id="searchSoNm" name="searchSoNm" value="${param.searchSoNm }" />
	<input type="hidden" id="searchTyp" name="searchTyp" value="${param.searchTyp }"/>
	<input type="hidden" id="searchProdTyp" name="searchProdTyp" value="${param.searchProdTyp }"/>

	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M07.LAB00129"/>
			</h4>
		</div>
		<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:24%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00003"/>
				</th>
				<td>
					<select id="soNm" name="soNm" class="w200">
						<option value="">
							<spring:message code="LAB.M15.LAB00002"/>
						</option>
						<c:forEach items="${soNmList}" var="soNmList" varStatus="status">
						<option value="${soNmList.commonCd}">
							${soNmList.commonCdNm}
						</option>
						</c:forEach>
					</select>
				</td>
				<th>
					<spring:message code="LAB.M07.LAB00152"/>
				</th>
				<td colspan='3'>
					<select id="prodTyp" name="prodTyp" class="w200">
						<option value="">
							ALL
						</option>
						<c:forEach items="${prodTypList}" var="prodTypList" varStatus="status">
						<option value="${prodTypList.commonCd}">
							${prodTypList.commonCdNm}
						</option>
						</c:forEach>
					</select>
					<select id="typ" name="typ" class="w270">
						<option value="1">
							PROD_CD
						</option>
						<option value="2">
							PROD_NAME
						</option>
					</select>
					<input type="text" id="searchProd" name="searchProd" value="${param.searchProd }" class="w200"/>
				</td>
			</tr>
		</thead>
	</table>
</form>	

<br>
<!--JQ Grid 리스트-->	

<div id='gridDiv'>
<table id="productDtlGrid" class="w100p"></table>
	<div id="pager2">
	</div>
</div>

<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">
		<!--초기화-->		
		<a class="grey-btn" id="btn_print" href="javascript:init();"><span class="print_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>		
	</div>
</div>
<div id="popup_dialog" class="Layer_wrap" style="display:none; width:400px;" >
</div>       

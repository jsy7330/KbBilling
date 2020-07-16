<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	searchProdGrid();
	

});

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

function searchProdGrid() {

	var param = new Object();

	if ($("#searchSoIdPop").val().length > 0) {
		param.searchSoIdPop = $("#searchSoIdPop").val();
	}	
	if ($("#searchProdNmPop").val().length > 0) {
		param.searchProdNmPop = $("#searchProdNmPop").val();
	}	

	$("#searchProdGrid").jqGrid("GridUnload"); 
	$("#searchProdGrid").jqGrid({
		url:'searchProdAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'상코드',name:'prodCd',  width:60, align:"left"},	
			{label:'상품명',name:'prodNm', width:150, align:"left"}		
	   	],

	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#searchProdGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 295,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		onCellSelect : function(rowId, index, contents, event){
			setSelectedData(rowId);
		},
	    loadComplete: function(obj){
	    	$("#searchProdGrid").setGridWidth($("#searchProdGridDiv").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#searchProdGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	$("#searchProdGrid").setGridWidth($("#searchProdGridDiv").width(),false);

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#searchProdGrid").setGridWidth($('#searchProdGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}
function setSelectedData(rowId){		
	var data = $("#searchProdGrid").getRowData(rowId);
	$("#searchProdNm", parent.document.body).val(data.prodNm);
	$("#searchProdCd", parent.document.body).val(data.prodCd);
	$("#btnClose").trigger('click');
}
</Script>

<!-- title -->
<div class="layer_top">
   <div class="title">상품조회</div>
   <a href="javascript:closeModal();" class="close">Close</a>
</div>
<!--// title -->   

<form id="searchProd" name="searchProd" method="post">			

<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">상품조회</h4>
		</div>
		<div class="fr mt10">
			<a href="javascript:searchProdGrid();" class="grey-btn" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
						<!--SO_ID-->
						<th><spring:message code="LAB.M07.LAB00003"/></th>
						<td>
							<select id="searchSoIdPop" name="searchSoIdPop" class="w300">
								<option value=""><spring:message code="LAB.M09.LAB00063"/></option>
								<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
									<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
								</c:forEach>					
							</select>
						</td>
						<th>상품명 </th>
						<td>
								<input id="searchProdNmPop" name="searchProdNmPop" type="text" class="w200">
						</td>
					</tr>
			</thead>
	</table> 	
</div>	
	
<div class="main_btn_box">
	
</div>
<div id='searchProdGridDiv'>
	<table id="searchProdGrid" class="w100p"></table>
	<div id="searchProdGridPager"></div>
</div>

<div class="btn_box">
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
 </div>

</form>	
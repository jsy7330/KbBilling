<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	
	var lng = '<%= session.getAttribute("sessionLanguage") %>';
	
	initGrid(lng);
	
	// 검색,등록,수정,삭제 버튼 이벤트
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	$("#btn_insert").click(function() {
		goInsertPopUp();
	});
	$("#btn_update").click(function() {
		goUpdatePopUp();
	})
	$("#btn_delete").click(function() {
		goDelete();
	});
	$("#btn_print").click(function() {
		printExcel();
	})
	
	// 사용유형 셀렉트 박스 선택 시 검색 처리
	$("#searchUsgTyp").selectmenu({
		change: function() {
			$("#usgTyp").val($("#searchUsgTyp").val());
		}
	});
	$("#searchUsgTyp").val($("#usgTyp").val());
	$("#searchUsgTyp").selectmenu("refresh");
	
	$(window).resize(function() {
		$("#manageRatingDefGrid").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	var resultMsg = "${resultMsg}";
	if( resultMsg != null && resultMsg != "" ) {
		alert("${resultMsg}");
	}
});


function initGrid(lng) {
	var param = new Object();
	param.searchChrgCd =  $("#searchChrgCd").val();	
	param.searchDescription = $("#searchDescription").val();
	param.searchUsgTyp =  $("#searchUsgTyp").val();
	
	$("#manageRatingDefGrid").jqGrid({	
		url: 'manageRatingDefListAction.json?',
		mtype: "POST",
		datatype: "json",
		
		postData: param,
		colModel: [
				{ label:'effDt', name:'effDt', hidden:true, width:50 },   
   				{ label:'<spring:message code="LAB.M01.LAB00141"/>',name:'chrgCd', align:"center", width:50 },
   				{ label:'<spring:message code="LAB.M01.LAB00115"/>',name:'description', align:"center", width:100 },
   				{ label:'<spring:message code="LAB.M07.LAB00029"/>',name:'usgTyp', align:"center", width:50 },
   		   		{ label:'<spring:message code="LAB.M07.LAB00050"/>',name:'usgTypNm', align:"center", width:50 }
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
			setSelectedData(rowId);
		},
		loadComplete: function(obj) {
			btnNonActive("btn_update");
			btnNonActive("btn_delete");
		},
		onCellSelect: function(rowid, index, contents, event) {
			btnActive("btn_update");
			btnActive("btn_delete");
		}
	});

	$("#manageRatingDefGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	$(window).resize(function() {
		$("#manageRatingDefGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

function goSearch() {
	var param = new Object();
	
	$("#manageRatingDef").attr("action", "/product/refInfo/ratingRefInfo/manageRatingDef/manageRatingDefList");
	$("#manageRatingDef").attr("method", "post");
	$("#manageRatingDef").submit();
}

function goInsertPopUp() {
	var param = new Object();
	var url = "manageRatingDefInsertPopUp.ajax";
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goUpdatePopUp() {
	var rowId = jQuery("#manageRatingDefGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00096" />");
		return;
	}
		
	var param = new Object();
	param.chrgCd = $("#manageRatingDefGrid").getRowData(rowId).chrgCd;
	param.usgTyp = $("#manageRatingDefGrid").getRowData(rowId).usgTyp;
	param.description = $("#manageRatingDefGrid").getRowData(rowId).description;
	
	var url = "manageRatingDefUpdatePopUp.ajax";
	$("#popup_dialog").css("width", 1000);
	
	openModalPopup(url, param);
}

function goDelete() {
	var rowId = jQuery("#manageRatingDefGrid").jqGrid('getGridParam', 'selrow');
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056" />");
		return;
	}
	$("#chrgCd").val($("#manageRatingDefGrid").getRowData(rowId).chrgCd);
	$("#usgTyp").val($("#manageRatingDefGrid").getRowData(rowId).usgTyp);
	$("#effDt").val($("#manageRatingDefGrid").getRowData(rowId).effDt);
	
	if( confirm("<spring:message code="MSG.M07.MSG00054" />") == true ) {
		$("#manageRatingDef").attr("action", "/product/refInfo/ratingRefInfo/manageRatingDef/manageRatingDefDeleteAction");
		$("#manageRatingDef").attr("method", "post");
		$("#manageRatingDef").submit();
	}
}

function printExcel(){
	var chrgCd = $("#searchChrgCd").val();
	var description = $("#searchDescription").val();
	var usgTyp = $("#searchUsgTyp").val();

	var param = 'chrgCd=' + chrgCd;
	param = param + '&description=' + description;
	param = param + '&usgTyp=' + usgTyp;
	
	$.download('manageRatingDefListExcelAction.xlsx', param, 'post');	
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

<!-- NaviGator -->
<div class="h3_bg">
	<h3>${menuName}</h3>
	<div class="nav">                                        
		<a href="#" class="home"></a>
		<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
		<span>&gt;</span>${mu.menuName}
	</c:forEach>
	</div>                               
</div>

<!-- 검색  -->
<form id="manageRatingDef" name="manageRatingDef" method="post">
	<input id="chrgCd" name="chrgCd" type="hidden" class="w100p">
	<input id="usgTyp" name="usgTyp" type="hidden" value="${param.searchUsgTyp }" class="w100p">
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M01.LAB00134" />
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
					<spring:message code="LAB.M01.LAB00141" />
				</th>
				<td>
					<input id="searchChrgCd" name="searchChrgCd" type="text" value="${param.searchChrgCd }" class="w100p">
				</td>
				<th>
					<spring:message code="LAB.M01.LAB00115" />
				</th>
				<td>
					<input id="searchDescription" name="searchDescription" value="${param.searchDescription }" type="text" class="w100p">
				</td>
				<th>
					<spring:message code="LAB.M07.LAB00029" />
				</th> 
				<td>
					<select id="searchUsgTyp" name="searchUsgTyp" class="w200">	
						<option value="">
							<spring:message code="LAB.M15.LAB00002"/>
						</option>
						<c:forEach items="${usgTypList}" var="usgTypList" varStatus="status">
							<option value="${usgTypList.commonCd}">
								${usgTypList.commonCdNm}								
							</option>
						</c:forEach>		
					</select>
				</td>
			</tr>
		</thead>
	</table>
</form>	

<br>

<!--JQ Grid 리스트-->	
<div id='gridDiv'>
<table id="manageRatingDefGrid" class="w100p"></table>
	<div id="pager2">
	</div>
</div>

<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">
		<!--등록-->
		<a class="grey-btn" href="#" id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		<!--수정-->
		<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>'><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		<!--삭제-->
		<a class="grey-btn" href="#" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>'><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		<!--출력-->
		<ntels:auth auth="${menuAuthP}">
		<a class="grey-btn" title='<spring:message code="LAB.M10.LAB00079"/>' id="btn_print" href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
	</div>
</div>
<div id="popup_dialog" class="Layer_wrap" style="display:none; width:400px;" >
</div>
</html>

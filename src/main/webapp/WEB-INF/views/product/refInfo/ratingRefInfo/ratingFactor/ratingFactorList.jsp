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

	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	$("#btn_insert").click(function() {
		goInsert();
	});

	$("#btn_update").click(function() {
		goUpdatePopUp();
	});
	
	$("#btn_delete").click(function() {
		goDelete();
	});
	
	$(window).resize(function() {
		$("#ratingFactorGrid").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function goInsert() {
	var param = new Object();
	var url = "ratingFactorInsertPopUp.ajax";
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goUpdatePopUp() {
	var param = new Object();
	var url = "ratingFactorUpdatePopUp.ajax";
	var rowId = jQuery("#ratingFactorGrid").jqGrid('getGridParam', 'selrow');
	
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00096" />");
		return;
	}
	
	param.modUsgTyp = $("#ratingFactorGrid").getRowData(rowId).usgTyp;
	param.modRatingFac = $("#ratingFactorGrid").getRowData(rowId).rateDeter;
	param.modRateDeter = $("#ratingFactorGrid").getRowData(rowId).rateDeter;
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goDelete() {
	var rowId = jQuery("#ratingFactorGrid").jqGrid('getGridParam', 'selrow');
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056" />");
		return;
	}	
	$("#usgTyp").val($("#ratingFactorGrid").getRowData(rowId).usgTyp);
	$("#ratingFac").val($("#ratingFactorGrid").getRowData(rowId).rateDeter);
	
	if( confirm("<spring:message code="MSG.M07.MSG00054" />") == true ) {
		$("#ratingFactor").attr("action", "/product/refInfo/ratingRefInfo/ratingFactor/ratingFactorDeleteAction");
		$("#ratingFactor").attr("method", "post");
		$("#ratingFactor").submit();
	}
}

function goSearch() {
	var param = new Object();
	param.page =  $("#page").val();
	param.perPage =  $("#perPage").val();

	$("#ratingFactorGrid").clearGridData();  // 이전 데이터 삭제
	$("#ratingFactorGrid").setGridParam({ postData: param }).trigger("reloadGrid");
}

function initGrid() {
		var param = new Object();
		param.searchUsgTyp =  $("#searchUsgTyp").val();
		
		$("#ratingFactorGrid").jqGrid({
		   	url: 'ratingFactorListAction.json?',
		   	postData: param,
		    mtype: "POST",
		   	datatype: "json",
		   	colModel: [ 
		   	    { label: 'usgTyp', name: 'usgTyp', hidden: true },
	   		    { label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTypNm', align:"center", width : 100},			//속성 코드
	  	 		{ label: '<spring:message code="LAB.M01.LAB00118"/>', name: 'ratingFac', align:"center", width : 100},			//속성 코드
	  	 		{ label: '<spring:message code="LAB.M08.LAB00070"/>', name: 'rateDeter', align:"center", width : 100},			//속성 코드
	   		],
		   	rowNum: 10,					//한번에 노출되는 row 수
		   	rowList: [5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#pager2',
			sortable: true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 240,				//화면 상태에 따라 크기 조절 할 것
			
	        loadComplete: function(obj){
	        	$("#ratingFactorGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        	btnNonActive("btn_update");
	        	btnNonActive("btn_delete");
	        },
			loadError: function (jqXHR, textStatus, errorThrown) {
				ajaxErrorCallback(jqXHR);
			},
			onCellSelect: function(rowId, index, contents, event){
	        	setSelectedDate(rowId);
	        	btnActive("btn_update");
	        	btnActive("btn_delete");
	        },
			sortable: { update: function(permutation) {
				$("#ratingFactorGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
				}
			}
	});
	$("#ratingFactorGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#ratingFactorGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
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

//상세정보 
function setSelectedDate(rowId){		
	var data = $("#ratingFactorGrid").getRowData(rowId);

	$("#oldActDt").val(data.actDt);
	$("#actDt").val(data.actDt);
	$("#inactDtt").val(data.inactDt);
	$("#attrCd").val(data.attrCd);
	$("#attrNm").val(data.attrNm);
	$("#ifSysNm").val(data.ifSysNm);
	$("#refCdNm").val(data.refCdNm);
	$("#attrStrtVal").val(data.attrStrtVal);
	$("#attrEndVal").val(data.attrEndVal);
	$("#ifSys").val(data.ifSys);
	$("#ifSys").selectmenu("refresh");
	$("#refCd").val(data.refCd);
	$("#remark").val(data.remark);		
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
<form id="ratingFactor" name="ratingFactor" method="post">
	<input id="usgTyp" name="usgTyp" type="hidden" class="w100p">
	<input id="ratingFac" name="ratingFac" type="hidden" class="w100p">
	
	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M07.LAB00051"/>
				</h4>
			</div>
			<div class="fr mt10">
				<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:85%;">

		</colgroup>
		<thead> 
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00029"/>
				</th>
				<td>
					<select id="searchUsgTyp" name="searchUsgTyp" class="w270">
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

<!--JQ Grid 리스트-->	
<br>
<div id='gridDiv'>
	<table id="ratingFactorGrid" class="w100p"></table>
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
	</div>
</div>

<div id="popup_dialog" class="Layer_wrap" style="display:none; width:400px;" >
</div>
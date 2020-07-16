<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
<!--
//Ajax로 첫 화면의 데이터 호출
//화면 먼저 보이고 데이터를 불러야 사용자가 덜 답답해 함
$(document).ready(function() {
	
	initGrid();	//jqgrid 호출

	//버튼 클릭시
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	//속성 등록 처리
	$("#btn_insert").click(function() {
		var param = new Object();
		var url="attrTypMapInsertPopUp.ajax";
		$("#popup_dialog").css("width", 1000);
		openModalPopup(url, param);
	});


	// 셀렉트 박스 값 가져오기
	 $('#searchAttrTyp').val('${attrTypMap.searchAttrTyp}');
	 $('#searchAttrTyp').selectmenu("refresh");
	 $('#searchAttrCd').val('${attrTypMap.searchAttrCd}');
	 $('#searchAttrCd').selectmenu("refresh");

	//속성유형 셀렉트 박스 선택시 검색 처리
	$('#searchAttrTyp').selectmenu({
	    change: function() {
			var param = new Object();
			param.searchAttrCd =  $("#searchAttrCd").val();
			param.searchAttrTyp =  $("#searchAttrTyp").val();
			goSearch();
	    }
	});
	//속성 셀렉트 박스 선택시 검색 처리
	$('#searchAttrCd').selectmenu({
	    change: function() {
			var param = new Object();
			param.searchAttrCd =  $("#searchAttrCd").val();
			param.searchAttrTyp =  $("#searchAttrTyp").val();
			goSearch();
	    }
	});

	$(window).resize(function() {
		$("#attriTypMapTable").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function getParam(){
	var param = new Object();	
	param.searchAttrTyp=  '${attrTypMap.searchAttrTyp}';
	param.searchAttrCd=  '${attrTypMap.searchAttrCd}';
	return param;
}

function initGrid() {

	var param = new Object();
	$("#attriTypMapTable").jqGrid({
	   	url:'attrTypMapListAction.json?'+ $.param(getParam()),
	    mtype:"POST",
	   	datatype: "json",
	   	colNames:[
			'<spring:message code="LAB.M07.LAB00227" />',
			'<spring:message code="LAB.M07.LAB00232" />',
			'<spring:message code="LAB.M08.LAB00090" />',
			'<spring:message code="LAB.M07.LAB00222" />',
			'<spring:message code="LAB.M07.LAB00214" />',
			'<spring:message code="LAB.M09.LAB00052" />',
			'<spring:message code="LAB.M09.LAB00058" />'
		],
	   	colModel:[
			{label:'attrTyp',name:'attrTyp', hidden:true, width:50},
			{label:'attrCd',name:'attrCd',hidden:true,  width:50},
			{label:'mstrFl',name:'mstrFl',hidden:true,  width:50},
	   		{label:'attrTypNm',name:'attrTypNm', width:140},
	   		{label:'attrNm',name:'attrNm'},
	   		{label:'actDt',name:'actDt', formatter:stringTypeFormatterYYYYMMDD, width:80,align:"center"},
	   		{label:'inactDt',name:'inactDt', formatter:stringTypeFormatterYYYYMMDD, width:80,align:"center"}
	   	],
	   	shrinkToFit:false,	   	
	   	rowNum:10,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2',
		autowidth:true,
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 235,					//화면 상태에 따라 크기 조절 할 것
		multiselect: true,    
	    ondblClickRow : function(id){ //ROW 클릭시 이벤트
	    	goView(
				  $("#attriTypMapTable").getRowData(id).attrCd
				, $("#attriTypMapTable").getRowData(id).attrTyp
			);
	    },
	    loadComplete: function(obj){
	    	$("#attriTypMapTable").setGridWidth($("#gridDiv").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#attriTypMapTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	$("#attriTypMapTable").setGridWidth($("#gridDiv").width(),false);
}

function goSearch() {
	var param = new Object();
	param.page =  $("#page").val();
	param.perPage =  $("#perPage").val();
	param.searchAttrCd =  $("#searchAttrCd").val();
	param.searchAttrTyp =  $("#searchAttrTyp").val();
	$("#attriTypMap").attr("action", "/product/refInfo/commonInfo/attrTypMap/attrTypMapList");
	$("#attriTypMap").submit();
}


//수정 버튼 클릭시 팝업 처리
function updateEvent(){
	var ids = jQuery("#attriTypMapTable").jqGrid('getGridParam','selarrrow');		
	if(ids.length <=0){
		alert("<spring:message code="MSG.M07.MSG00096"/>");
		return;
	}else if(ids.length >1){
		alert("<spring:message code="MSG.M07.MSG00097"/>");
		return;
	}	
	var attrCd = $("#attriTypMapTable").getRowData(ids[0]).attrCd;
	var attrTyp = $("#attriTypMapTable").getRowData(ids[0]).attrTyp;
	var param = new Object();
	param.attrCd = attrCd;
	param.attrTyp = attrTyp;	
	var url="attrTypMapUpdatePopUp.ajax";
		$("#popup_dialog").css("width", 1000);
		openModalPopup(url, param);
}

$(window).resize(function() {
	$("#attriTypMapTable").setGridWidth($("#gridDiv").width(),false);
});

//-->
</script>

<form id="attriTypMap" name="attriTypMap" method="post">
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>

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
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M07.LAB00225"/></h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:25%;">
			<col style="width:10%;">
			<col style="width:55%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00222"/></th>
				<td>
					<select name="searchAttrTyp" id="searchAttrTyp" class="w180">
							<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
						<c:forEach items="${listCommon}" var="listCommon" varStatus="status">
							<option value="${listCommon.commonCd}">${listCommon.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M07.LAB00214"/></th>
				<td>
					<select name="searchAttrCd" id="searchAttrCd"class="w180">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
						<c:forEach items="${listAttr}" var="listAttr" varStatus="status">
						<option value="${listAttr.ATTR_CD}">${listAttr.ATTR_NM}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</thead>
	</table> 
	<!--JQ Grid 리스트-->	
	<div class="main_btn_box">
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00225"/></h4>
	</div>
	<div id='gridDiv'>
		<table id="attriTypMapTable"  class="w100p"></table>
		<div id="pager2"></div>
	</div>
</form>
<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">
		<!--초기화-->
		<a class="white-btn not-active"  href="javascript:init();" title='<spring:message code="LAB.M10.LAB00050"/>'><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
		<!--등록-->
		<a class="grey-btn" href="#"  id="btn_insert" title='<spring:message code="LAB.M03.LAB00075"/>'><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		<!--수정-->
		<a class="grey-btn" href="javascript:updateEvent();" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>'><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		<!--삭제-->
		<ntels:auth auth="${menuAuthD}">
		<a class="white-btn not-active" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
		<!--출력-->
		<ntels:auth auth="${menuAuthP}">
		<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
	</div>
  </div>
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:400px;" >
</div>
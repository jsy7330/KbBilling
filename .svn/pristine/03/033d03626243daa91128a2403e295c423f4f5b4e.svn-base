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

	//조회
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	//속성 등록 처리
	$("#btn_insert").click(function() {
		goInsert();
	});
	
	$("#btn_update").click(function() {
		goUpdatePopUp();
	});

	//삭제처리
	$("#btn_delete").click(function() {
		goDelete();
	});
	
	$(window).resize(function() {
		$("#ratingFactorUnitGrid").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function goInsert() {
	var param = new Object();
	var url = "ratingFactorUnitInsertPopUp.ajax";
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goUpdatePopUp() {
	var param = new Object();
	var url = "ratingFactorUnitUpdatePopUp.ajax";
	var rowId = jQuery("#ratingFactorUnitGrid").jqGrid('getGridParam', 'selrow');
	
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00096" />");
		return;
	}

	param.modRateFac = $("#ratingFactorUnitGrid").getRowData(rowId).rateFac;
	param.modRateFacUnitNm = $("#ratingFactorUnitGrid").getRowData(rowId).rateFacUnitNm;
	param.modRateFacUnit = $("#ratingFactorUnitGrid").getRowData(rowId).rateFacUnit;
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goDelete() {
	var rowId = jQuery("#ratingFactorUnitGrid").jqGrid('getGridParam', 'selrow');
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056" />");
		return;
	}	
	$("#rateFacUnit").val($("#ratingFactorUnitGrid").getRowData(rowId).rateFacUnit);
 
	if( confirm("<spring:message code="MSG.M07.MSG00054" />") == true ) {
		$("#ratingFactorUnit").attr("action", "/product/refInfo/ratingRefInfo/ratingFactorUnit/ratingFactorUnitDeleteAction");
		$("#ratingFactorUnit").attr("method", "post");
		$("#ratingFactorUnit").submit();	
	}
}

function goSearch() {
	var param = new Object();
	param.searchRateFacUnitNm =  $("#searchRateFacUnitNm").val();
	
	$("#ratingFactorUnit").attr("action", "/product/refInfo/ratingRefInfo/ratingFactorUnit/ratingFactorUnitList");
	$("#ratingFactorUnit").attr("method", "post");
	$("#ratingFactorUnit").submit();
}


function initGrid() {
	var param = new Object();	
	param.searchRateFacUnitNm =  $("#searchRateFacUnitNm").val();	
	
	$("#ratingFactorUnitGrid").jqGrid({
		
	   	url: 'ratingFactorUnitListAction.json?', // + $.param(getParam()),		  
	    mtype: "POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel: [ 
	   	    { label: 'rateFac', name: 'rateFac', hidden:true },
   		    { label: '<spring:message code="LAB.M01.LAB00122"/>', name: 'rateFacUnit', align:"center", width : 100},			//속성 코드
  	 		{ label: '<spring:message code="LAB.M01.LAB00124"/>', name: 'rateFacUnitNm', width : 150},			//속성 코드
  	 		{ label: '<spring:message code="LAB.M01.LAB00118"/>', name: 'rateFacNm', width : 100},			//속성 코드
   		],
	   	rowNum: 10,					//한번에 노출되는 row 수
	   	rowList: [5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2',
		sortable: true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 240,				//화면 상태에 따라 크기 조절 할 것
		loadComplete: function(obj){
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
			$("#ratingFactorUnitGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	$("#ratingFactorUnitGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#ratingFactorUnitGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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

function setSelectedDate(rowId){		
	var data = $("#ratingFactorUnitGrid").getRowData(rowId);

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
<form id="ratingFactorUnit" name="ratingFactorUnit" method="post">
	<input type="hidden" id="rateFacUnit" name="rateFacUnit"/>
	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M01.LAB00123"/>
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
						<spring:message code="LAB.M01.LAB00124"/>
					</th>
					<td>
						 <input id="searchRateFacUnitNm" name="searchRateFacUnitNm" type="text" value="${param.searchRateFacUnitNm }" class="w270" >
					</td>
				</tr>
		</thead>
	</table> 
</form>	

<!--JQ Grid 리스트-->	
<br>
<div id='gridDiv'>
	<table id="ratingFactorUnitGrid" class="w100p"></table>
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<style type="text/css">
table.ui-datepicker-calendar { display:none; }
</style>

<script type="text/javascript">

$(document).ready(function() {
	
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	
	//달력처리
	if($(".month-picker").length > 0){
		if(lng == 'ko'){
			format = 'yy-mm';
		}else if (lng == 'en'){
			format = 'mm/yy';
		}
		 $('.month-picker').datepicker( {
		        changeMonth: true,
		        changeYear: true,
		        showButtonPanel: true,
		        dateFormat: format,
		        onClose: function(dateText, inst) {
		            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		            $(this).datepicker('setDate', new Date(year, month, 1));
		        }
		    }
		 ).datepicker("setDate", new Date()); 
	}
	
	$("#searchSoId").val("${session_user.soId}");
	$('#searchSoId').selectmenu("refresh");
	
	// JQgrid 호출
	initGrid();	
	 
	// 조회
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});

});

function initGrid() {

	var param = new Object();
	param.searchSoId =  $("#searchSoId").val();	
	param.searchYymm =  dateFormatToStringYYYYMM($("#searchYymm").val());	
	param.searchSetItmNm =  $("#searchSetItmNm").val();	
	
	$("#billingInfoItemTable").jqGrid({

	   	url:'billingInfoItemListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel:[
			{label: 'soId' , name: 'soId', hidden:true,width : 0},
	   		{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width: 40,align:"center"},
	   		{label:'<spring:message code="LAB.M14.LAB00044" />',name:'setItmCd', width:40, align:"center"},
	   		{label:'<spring:message code="LAB.M07.LAB00205" />',name:'setItmNm', width:70},
	   		{label:'<spring:message code="LAB.M14.LAB00072" />',name:'eftBgnYymm', width:50, align:"center", formatter:stringTypeFormatterYYYYMM},
	   		{label:'<spring:message code="LAB.M14.LAB00074" />',name:'eftEndYymm', width:50, align:"center", formatter:stringTypeFormatterYYYYMM},
	   		{label:'<spring:message code="LAB.M07.LAB00201" />',name:'setVal', width:270}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 450,					//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowid, index, contents, event){
        },
        loadComplete: function(obj){
        	$("#billingInfoItemTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
        
        sortable: { update: function(permutation) {
        	$("#billingInfoItemTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
    		}
    	}
	});
	
	$("#billingInfoItemTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#billingInfoItemTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

}

function goSearch() {
	var param = new Object();
	param.page =  $("#page").val();
	param.perPage =  $("#perPage").val();
	param.searchSoId =  $("#searchSoId").val();	
	param.searchYymm =  dateFormatToStringYYYYMM($("#searchYymm").val());	
	param.searchSetItmNm =  $("#searchSetItmNm").val();	
	
	$("#billingInfoItemTable").clearGridData();  // 이전 데이터 삭제
	$("#billingInfoItemTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
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
<form id="attribute" name="attribute" method="post">
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>

	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M06.LAB00109"/></h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
			<th><spring:message code="LAB.M01.LAB00220"/><!-- 기준년월 --></th>
			<td>
				<div class="date_box">
					<div class="inp_date w150">
						<input  type="text"  id="searchYymm" name="searchYymm" class="month-picker" readonly="readonly">
						<a href="#" id='btnCal' class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00205"/><!-- 설정항목명 --></th>
			<td>
					<input type="text" id="searchSetItmNm" name="searchSetItmNm" class="w150">
		    </td>
		</tr>
	</thead>
	</table> 
</form>	

<!--JQ Grid 리스트-->	
<div class="main_btn_box">
	<div class="fl">	
	<h4 class="sub_title"><spring:message code="LAB.M06.LAB00107"/></h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="billingInfoItemTable" class="w100p"></table>
	<div id="pager2"></div>
</div>


<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">
		<!--출력-->
		<!-- 
		<ntels:auth auth="${menuAuthP}">
		<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth> -->
	
  </div>
</div>
<div id="pop_layer" style="height:600px;width:500px;overflow-x:none;overflow-y:auto;display:none;"></div>       




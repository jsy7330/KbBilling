<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(function(){
	chargeTypeGrid();
	
	$( "#chrgCtgry" ).selectmenu({
		  change: function( event, ui ) {
			  chargeTypeGrid();
		  }
		});
	
	$('#btn_insert').click(function() {
		var url="productChargeTypeListInsertPopUp.ajax";
		var param = new Object();
		openModal(url, param); 		
	});	

	$('#btn_update').click(function() {
		var rowId = $("#productChargeTypeListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00102" />');
			return;
		}		
		
		var gridRateItmTypCd = $("#productChargeTypeListGrid").getRowData(rowId).rateItmTypCd;
		var gridActDt = $("#productChargeTypeListGrid").getRowData(rowId).actDt;
		var gridInactDt = $("#productChargeTypeListGrid").getRowData(rowId).inactDt;		
				
		var url="productChargeTypeListUpdatePopUp.ajax";
		
		var param = new Object();
		
		param.rateItmTypCd = gridRateItmTypCd;
		param.actDt = dateFormatToStringYYYYMMDD(gridActDt);
		param.inactDt = dateFormatToStringYYYYMMDD(gridInactDt);
		
		openModal(url, param); 		
	});		
	
});

function openModal(url, param) {
	
	$.ajax({
		type : "post",
		url : url,
		data : param,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
		}
	}); 
}

$(window).resize(function() {
	$("#productChargeTypeListGrid").setGridWidth($("#productChargeTypeListGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});

function chargeTypeGrid() {
	var param = new Object();
	if ($("#chrgCtgry").val().length > 0) {
		param.chrgCtgry = $("#chrgCtgry").val();
	}	

	$("#productChargeTypeListGrid").jqGrid("GridUnload"); 
	$("#productChargeTypeListGrid").jqGrid({
		url:'productChargeTypeListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M08.LAB00057" />',name:'rateItmTypCd', width:100, align:"center"},        
			{label:'<spring:message code="LAB.M08.LAB00052" />',name:'rateItmTypNm', width:200},	   	          
			{label:'<spring:message code="LAB.M08.LAB00051" />',name:'chrgCtgryNm', width:80, align:"center"},      
			{label:'<spring:message code="LAB.M09.LAB00089" />',name:'dispPriNo', width:80, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#productChargeTypeListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 495,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#productChargeTypeListGrid").setGridWidth($("#productChargeTypeListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#productChargeTypeListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#productChargeTypeListGrid").setGridWidth($("#productChargeTypeListGridDiv").width(),false);
	
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

	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M08.LAB00053"/></h4>
			</div>
			<div class="fr mt10">
			<!-- <a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>--> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:90%;">
		</colgroup>
		 <thead> 
			   <tr>
					<th><spring:message code="LAB.M08.LAB00051"/></th>
					<td>
						<select id="chrgCtgry" name="chrgCtgry">
							<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${chrgCtgry}" var="chrgCtgry" varStatus="status">
								<option value="${chrgCtgry.commonCd}">${chrgCtgry.commonCdNm}</option>
							</c:forEach>
						</select> 
					</td>
				</tr>
		</thead>
	</table> 
</form>	

<!--JQ Grid 리스트-->	
<div class="main_btn_box">
	
</div>
<div id='productChargeTypeListGridDiv'>
	<table id="productChargeTypeListGrid" class="w100p"></table>
	<div id="productChargeTypeListGridPager"></div>
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
	</div>
  </div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:400px;" >
</div>
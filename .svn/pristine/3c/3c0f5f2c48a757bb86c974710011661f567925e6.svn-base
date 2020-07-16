<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
/* Layer */
.Layer_wrap2 {  overflow-y:auto;
	  position:absolute;  
	  left: 45%;
	  top: 45%;
	-ms-transform: translate(-45%, -45%);
	-webkit-transform: translate(-45%, -45%);
	-moz-transform: translate(-45%, -45%);
    transform: translate(-45%, -45%);
	  z-index:8888;
	 margin:0; 
	 padding:0; 
	 border:2px solid #69822b;
	 background:#fff;
	 }
</style>
<script type="text/javascript">
$(function(){
	//닫기 버튼을 눌렀을 때
	$('.Layer_wrap2 ').on('click','.close',function (e) {
	    //링크 기본동작은 작동하지 않도록 한다.
	    e.preventDefault();  
	    $('#mask, .Layer_wrap2').hide();  
	});

	//검은 막을 눌렀을 때
	$('#mask').click(function () {  
	    $(this).hide();   
	    $('.Layer_wrap2').hide();  
	}); 	
	
	factorGrid();
	
	$('#btn_search').click(function() {
		factorGrid();	
	});	
	$('#btn_insert').click(function() {
		var url="factorListInsertPopUp.ajax";
		var param = new Object();
		openModal(url, param); 		
	});	

	$('#btn_update').click(function() {
		var rowId = $("#factorListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00103" />');
			return;
		}		
		
		var gridFctrCd = $("#factorListGrid").getRowData(rowId).fctrCd;		
		var gridActDt = $("#factorListGrid").getRowData(rowId).actDt;
		var gridInactDt = $("#factorListGrid").getRowData(rowId).inactDt;
		
		var url="factorListUpdatePopUp.ajax";
		
		var param = new Object();
		
		param.fctrCd = gridFctrCd;
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
			var maskHeight = $(document).height();  
			var maskWidth = $(window).width();  

			//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
			$('#mask').css({'height':maskHeight});  

			//애니메이션 효과 - 일단 1초동안 까맣게 됐다가 80% 불투명도로 간다.
			$('#mask').fadeIn(100);      
			$('#mask').fadeTo("slow",0.5);    

			//윈도우 같은 거 띄운다.
			$('#popup_dialog').show();
		}
	}); 
	
}

function openModal2(url) {
	
	$.ajax({
		type : "post",
		url : url,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog2").html(html);
		},		
		complete : function(){
			var maskHeight = $(document).height();  
			var maskWidth = $(window).width();  

			//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
			$('#mask').css({'height':maskHeight});  

			//애니메이션 효과 - 일단 1초동안 까맣게 됐다가 80% 불투명도로 간다.
			$('#mask').fadeIn(100);      
			$('#mask').fadeTo("slow",0.5);    

			//윈도우 같은 거 띄운다.
			$('#popup_dialog2').show();
		}
	}); 
	
}


$(window).resize(function() {
	$("#productBillingItemListGrid").setGridWidth($("#productBillingItemListGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});

function factorGrid() {
	var param = new Object();
	if ($("#fctrNm").val().length > 0) {
		param.fctrNm = $("#fctrNm").val();
	}	

	$("#factorListGrid").jqGrid("GridUnload"); 
	$("#factorListGrid").jqGrid({
		url:'getTotalFactorList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M08.LAB00068" />',name:'fctrCd', width:70, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00062" />',name:'fctrNm', width:200}, 
			{label:'<spring:message code="LAB.M10.LAB00010" />',name:'fctrRefTyp', width:50, align:"center"},      
			{label:'<spring:message code="LAB.M12.LAB00001" />',name:'tableId', width:150},	   	          
			{label:'<spring:message code="LAB.M11.LAB00004" />',name:'colmnId', width:150}, 	   	          
			{label:'<spring:message code="LAB.M03.LAB00069" />',name:'dataTyp', width:80, align:"center"}, 	
			{label:'<spring:message code="LAB.M10.LAB00002" />',name:'refCd', width:80, align:"center"},
			{label:'<spring:message code="LAB.M14.LAB00039" />',name:'refFunc', width:150, align:"center"},
			{label:'<spring:message code="LAB.M15.LAB00066" />',name:'chgNullFl', width:80, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M03.LAB00082" />',name:'regrId', width:50, align:"center"},
			{label:'<spring:message code="LAB.M03.LAB00079" />',name:'gridRegDate', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#factorListGridPager',
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
	    	$("#factorListGrid").setGridWidth($("#factorListGridDiv").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#factorListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	$("#factorListGrid").setGridWidth($("#factorListGridDiv").width(),false);
		//그리드 화면 재조정
	$(window).resize(function() {
		$("#factorListGrid").setGridWidth($('#factorListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

function test(){
	alert("test!");
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
<!--  -->
<!--검색-->	
<form id="attribute" name="attribute" method="post">
	<div class="main_btn_box">
			<div class="fl">
				<!-- <h4 class="sub_title"><spring:message code="LAB.M08.LAB00053"/></h4>-->
			</div>
			<div class="fr mt10">
				<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:90%;">
		</colgroup>
		 <thead> 
			<tr>
				<!--과금요소명-->
				<th><spring:message code="LAB.M01.LAB00127"/></th>
				<td>
					<input id="fctrNm" name="fctrNm" type="text" class="w100p">
				</td>
			</tr>
		</thead>
	</table> 
</form>	
<div class="main_btn_box">
	
</div>
<div id='factorListGridDiv'>
	<table id="factorListGrid" class="w100p"></table>
	<div id="factorListGridPager"></div>
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




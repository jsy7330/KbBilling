<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(function(){	
	
	productRel();
	
	$('#btn_search').click(function() {
		var url="productRelationshipListAllPopup.ajax";
		var param = new Object();
		
		openModal(url, param);
	});		
	
	$('#productRelDefresGridAddBtn').click(function() {
		var url="productdefultRelationsListPopup.ajax";
		var param = new Object();

		param.prodCase = 1;
		$("#productRelProdCase").val("1");
		param.prodCd = $("#productRelProdCd").val();
		param.basicProdFl =$("#productRelBasicProdFl").val(); 
	
		openModal(url, param);
	});			
	
	$('#productRelDefreqGridAddBtn').click(function() {
		var url="productdefultRelationsListPopup.ajax";
		var param = new Object();

		param.prodCase = 2;
		$("#productRelProdCase").val("2");
		param.prodCd = $("#productRelProdCd").val();
		param.basicProdFl =$("#productRelBasicProdFl").val(); 
	
		openModal(url, param);
	});	
	
	
	$('#productRelDefresGridDeleteBtn').click(function() {
		var url="productCancelPopup.ajax";
		var param = new Object();

		param.prodCase = 1;
		$("#productRelProdCase").val("1");
	
		openModal1(url, param);
	});		
	
	
	$('#productRelDefreqGridDeleteBtn').click(function() {
		var url="productCancelPopup.ajax";
		var param = new Object();

		param.prodCase = 2;
		$("#productRelProdCase").val("2");
	
		openModal1(url, param);
	});		
	
 
});

$(window).resize(function() {
	$("#productRelDefresGrid").setGridWidth($("#productRelDefresGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#productRelDefreqGrid").setGridWidth($("#productRelDefreqGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});

function getproductRelDefreList(prodCd){
	var param = new Object();
	param.prodCd = prodCd;
	param.soId = $("#soId").val();
	
 	$("#productRelDefresGrid").jqGrid("GridUnload"); 
  	$("#productRelDefresGrid").jqGrid({
		url:'getProductdefultRelationsList_res.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
	  		{name:'prodCd', hidden:true},
	  		{name:'prodRelTyp', hidden:true},
	  		{name:'relProdCd', hidden:true},
	  		{name:'prodCase', hidden:true},
	  		{name:'inactDtChg', hidden:true},
	  		{label:'<spring:message code="LAB.M01.LAB00160" />',name:'prodRelTypNm', width:150},	   	          
	  		{label:'<spring:message code="LAB.M03.LAB00050" />',name:'relProdNm', width:200, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00168" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
	  		{label:'<spring:message code="LAB.M01.LAB00204" />',name:'defYn', width:100, align:"center"},	
	  		{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 220,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#productRelDefresGrid").setGridWidth($("#productRelDefresGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#productRelDefresGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		},
		multiselect: true
		
	});   
	$("#productRelDefresGrid").setGridWidth($("#productRelDefresGridDiv").width(),false);	
	
 	$("#productRelDefreqGrid").jqGrid("GridUnload"); 
  	$("#productRelDefreqGrid").jqGrid({
		url:'getProductdefultRelationsList_req.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
	  		{label:'<spring:message code="LAB.M01.LAB00160" />',name:'prodRelTypNm', width:150},	   	          
	  		{label:'<spring:message code="LAB.M03.LAB00050" />',name:'prodNm', width:200, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00168" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
	  		{label:'<spring:message code="LAB.M01.LAB00204" />',name:'defYn', width:100, align:"center"},	
	  		{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100},
	  		{name:'prodCd', hidden:true},
	  		{name:'prodRelTyp', hidden:true},
	  		{name:'relProdCd', hidden:true},
	  		{name:'prodCase', hidden:true},
	  		{name:'inactDtChg', hidden:true}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 220,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		multiselect: true,
		
	});   
	$("#productRelDefreqGrid").setGridWidth($("#productRelDefreqGridDiv").width(),false);		
	
}

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

function productRel() {
/* 	var param = new Object();
	if ($("#soId").val() != "") {
		param.soId = $("#soId").val();
	} 	
	param.prodNm = $("#prodNm").val();

	if ($("#dvlpTyp").val() != "") {
		param.dvlpTyp = $("#prodNm").val();
	} 
	
	if ($("#dvlpStatus").val() != "") {
		param.dvlpStatus = $("#dvlpStatus").val();
	} 	 */
	
 	$("#productRelDefresGrid").jqGrid("GridUnload"); 
  	$("#productRelDefresGrid").jqGrid({
		//url:'getproductRel.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	//postData : param,
	   	colModel:[
	  		{label:'<spring:message code="LAB.M01.LAB00160" />',name:'prodRelTypNm', width:150},	   	          
	  		{label:'<spring:message code="LAB.M03.LAB00050" />',name:'relProdNm', width:200, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00168" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
	  		{label:'<spring:message code="LAB.M01.LAB00204" />',name:'defYn', width:100, align:"center"},	
	  		{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100}			
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 220,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		multiselect: true,
		
	});   
	$("#productRelDefresGrid").setGridWidth($("#productRelDefresGridDiv").width(),false);
	
 	$("#productRelDefreqGrid").jqGrid("GridUnload"); 
  	$("#productRelDefreqGrid").jqGrid({
		//url:'getproductRel.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	//postData : param,
	   	colModel:[
	  		{label:'<spring:message code="LAB.M01.LAB00160" />',name:'prodRelTypNm', width:150},	   	          
	  		{label:'<spring:message code="LAB.M03.LAB00050" />',name:'prodNm', width:200, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00168" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
	  		{label:'<spring:message code="LAB.M01.LAB00204" />',name:'defYn', width:100, align:"center"},	
	  		{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100}			
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 220,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		multiselect: true,
		
	});   
	$("#productRelDefreqGrid").setGridWidth($("#productRelDefreqGridDiv").width(),false);	
}

function openModal1(url) {
	
	$.ajax({
		type : "post",
		url : url,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog1").html(html);
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
			$('#popup_dialog1').show();
		}
	}); 
	
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
				<!-- <h4 class="sub_title"><spring:message code="LAB.M08.LAB00053"/></h4>-->
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
				<th><spring:message code="LAB.M07.LAB00003"/></th>
				<td>
					<select id="soId" name="soId" class="w300">
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
								<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>					
					</select>
				</td>
				<th><spring:message code="LAB.M07.LAB00130"/></th>
				<td>
					<input id="prodNm" name="prodNm" type="text" class="w300" disabled>
					<a id="btn_search" class="grey-btn" title='<spring:message code="LAB.M09.LAB00158" />' href="#"><span><spring:message code="LAB.M09.LAB00158" /></span></a>
				</td>
			</tr>			
		</thead>
	</table> 
</form>	
<!-- <div class="main_btn_box">
	
</div> -->
<div class="ly_btn_box">
	<div class="fl">
		<h4 class="ly_title"><spring:message code="LAB.M01.LAB00201"/></h4>
	</div>
</div>
<div id="productRelDefresGridDiv">
	<table id="productRelDefresGrid" class="w100p"></table>
</div>
<div class="main_btn_box">
	<div class="fr">
		<span id="commonBtn">
			<ntels:auth auth="${menuAuthC}">
			<a id="productRelDefresGridAddBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>				
			<ntels:auth auth="${menuAuthD}">
			<a id="productRelDefresGridDeleteBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</ntels:auth>																
		</span>				
	</div>
</div>
<div class="ly_btn_box">
	<div class="fl">
		<h4 class="ly_title"><spring:message code="LAB.M08.LAB00019"/></h4>
	</div>
</div>
<div id="productRelDefreqGridDiv">
	<table id="productRelDefreqGrid" class="w100p"></table>
</div>
<div class="main_btn_box">
	<div class="fr">
		<span id="commonBtn">
			<ntels:auth auth="${menuAuthC}">
			<a id="productRelDefreqGridAddBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>				
			<ntels:auth auth="${menuAuthD}">
			<a id="productRelDefreqGridDeleteBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</ntels:auth>																
		</span>				
	</div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:800px;" >
</div>

<div id="popup_dialog1" class="Layer_wrap" style="display: none;width:300px;" >
</div>


<input id="productRelProdCd" type='text' value=""  hidden />
<input id="productRelBasicProdFl" type='text' value=""  hidden />
<input id="productRelProdCase" type='text' value=""  hidden />

<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css">
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
	
	prodDvlpList();
	
 	$('#btn_search').click(function() {
		prodDvlpList();	
	});	
 	
	$('#btnConReq').click(function() {

		var rowId = $("#prodDvlpListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M01.MSG00001" />');
			return;
		}
		
		var gridDvlpStatus = $("#prodDvlpListGrid").getRowData(rowId).dvlpStatus;		
		
		var url="getProdConfReqDtlListPopup.ajax";
		var param = new Object();
		param.prodCd = $("#prodDvlpListGrid").getRowData(rowId).prodCd;
		param.prodDvlpDttm = $("#prodDvlpListGrid").getRowData(rowId).prodDvlpDttm;
		param.modTyp = $("#prodDvlpListGrid").getRowData(rowId).modTyp;
		param.dvlpTyp = $("#prodDvlpListGrid").getRowData(rowId).dvlpTyp;
		param.dvlpTypNm = $("#prodDvlpListGrid").getRowData(rowId).dvlpTypNm;
		openModal(url, param); 	
	});	
	 
	
	$('#btnReqCancel').click(function() {
		var rowId = $("#prodDvlpListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M01.MSG00001" />');
			return;
		}
		
		var param = new Object();
		param.prodCd = $("#prodDvlpListGrid").getRowData(rowId).prodCd;
		param.prodDvlpDttm = $("#prodDvlpListGrid").getRowData(rowId).prodDvlpDttm;
		param.confReqCd = $("#prodDvlpListGrid").getRowData(rowId).confReqCd;
		
		$.ajax({
			url : 'modProdConfReqCancelProc.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					
					$("#prodDvlpListGrid").trigger("reloadGrid");
				} else if (data.result == "-1") {
					alert('<spring:message code="MSG.M09.MSG00051" />');
					
				}
			},
		    error: function(request,status,error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    },
			complete : function() {
			}
		});		
		
		
	});	
	
});

$(window).resize(function() {
	$("#prodDvlpListGrid").setGridWidth($("#prodDvlpListGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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

function prodDvlpList() {
	var param = new Object();
	if ($("#soId").val() != "") {
		param.soId = $("#soId").val();
	} 	
	param.prodNm = $("#prodNm").val();

	if ($("#dvlpTyp").val() != "") {
		param.dvlpTyp = $("#dvlpTyp").val();
	} 
	
	if ($("#dvlpStatus").val() != "") {
		param.dvlpStatus = $("#dvlpStatus").val();
	} 	
	
 	$("#prodDvlpListGrid").jqGrid("GridUnload"); 
  	$("#prodDvlpListGrid").jqGrid({
		url:'getProdDvlpList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'prodCd', hidden:true},
			{name:'prodDvlpDttm', hidden:true},
			{name:'modTyp', hidden:true},
			{name:'confReqCd', hidden:true},
			{name:'dvlpTyp', hidden:true},
			{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:80, align:"center"},
			{label:'<spring:message code="LAB.M07.LAB00146" />',name:'prodTypNm', width:80, align:"center"}, 
			{label:'<spring:message code="LAB.M07.LAB00130" />',name:'prodNm', width:150}, 
			{label:'<spring:message code="LAB.M01.LAB00014" />',name:'dvlpTypNm', width:80, align:"center"}, 
			{label:'<spring:message code="LAB.M06.LAB00059" />',name:'modDesc', width:150}, 
			{label:'<spring:message code="LAB.M09.LAB00029" />',name:'usrNm', width:80, align:"center"}, 			
			{label:'<spring:message code="LAB.M07.LAB00110" />',name:'dvlpStatusNm', width:80, align:"center"}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#prodDvlpListGridPager',
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
	    	$("#prodDvlpListGrid").setGridWidth($("#prodDvlpListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#prodDvlpListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});   
	$("#prodDvlpListGrid").setGridWidth($("#prodDvlpListGridDiv").width(),false);
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
				<th><spring:message code="LAB.M07.LAB00003"/></th>
				<td>
					<select id="soId" name="soId" class="w300">
						<option value=""><spring:message code="LAB.M09.LAB00063"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>					
					</select>
				</td>
				<th><spring:message code="LAB.M07.LAB00130"/></th>
				<td>
					<input id="prodNm" name="prodNm" type="text" class="w300">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00014"/></th>
				<td>
					<select id="dvlpTyp" name="dvlpTyp" class="w300">
						<option value=""><spring:message code="LAB.M09.LAB00063"/></option>
						<c:forEach items="${dvlpTyp}" var="dvlpTyp" varStatus="status">
							<option value="${dvlpTyp.commonCd}">${dvlpTyp.commonCdNm}</option>
						</c:forEach>					
					</select>
				</td>
				<th><spring:message code="LAB.M01.LAB00013"/></th>
				<td>
					<select id="dvlpStatus" name="dvlpStatus" class="w300">
						<option value=""><spring:message code="LAB.M09.LAB00063"/></option>
						<c:forEach items="${dvlpStatus}" var="dvlpStatus" varStatus="status">
							<option value="${dvlpStatus.commonCd}">${dvlpStatus.commonCdNm}</option>
						</c:forEach>					
					</select>
				</td>
			</tr>			
		</thead>
	</table> 
</form>	
<div class="main_btn_box">
	
</div>
<div id="prodDvlpListGridDiv">
	<table id="prodDvlpListGrid" class="w100p"></table>
	<div id="prodDvlpListGridPager"></div>
</div>

<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">	
		<a id="btnConReq" class="grey-btn" title='<spring:message code="LAB.M07.LAB00272"/>' href="#"><span><spring:message code="LAB.M07.LAB00272"/></span></a>
		&nbsp;<a id="btnReqCancel" class="grey-btn" title='<spring:message code="LAB.M08.LAB00084"/>' href="#"><span><spring:message code="LAB.M08.LAB00084"/></span></a>
	</div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:800px;" >
</div>


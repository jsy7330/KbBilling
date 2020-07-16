<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(function(){
	confSbjList();
	
 	$('#btn_search').click(function() {
 		confSbjList();	
	});	
	 	
 		 
 	$('#btnAppro').click(function() {
 		var param = new Object();
		var rowId = $("#confSbjListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00109" />');
			return;
		}
				
		var url="confReturnPopup.ajax";
		openModal(url, param); 	
	});		
 	
});

$(window).resize(function() {
	$("#confSbjListGrid").setGridWidth($("#confSbjListGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});

function confSbjList() {
	var param = new Object();
	if ($("#soId").val() != "") {
		param.soId = $("#soId").val();
	} 	
	
	if ($("#confRslt").val() != "") {
		param.confRslt = $("#confRslt").val();
	} 
	
 	$("#confSbjListGrid").jqGrid("GridUnload"); 
  	$("#confSbjListGrid").jqGrid({
		url:'getConfSbjList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'modDesc', hidden:true},
			{name:'prodCd', hidden:true},
			{name:'prodDvlpDttm', hidden:true},
			{name:'confrCd', hidden:true},
			{name:'confReqCd', hidden:true},
			{name:'confRslt', hidden:true},
			{name:'confDesc', hidden:true},
	  		{label:'<spring:message code="LAB.M07.LAB00130" />',name:'prodNm', width:200}, 	   	          
	  		{label:'<spring:message code="LAB.M09.LAB00009" />',name:'progGbNm', width:100, align:"center"}, 		
	  		{label:'<spring:message code="LAB.M07.LAB00261" />',name:'confRsltNm', width:100, align:"center"}, 		  		
	  		{label:'<spring:message code="LAB.M07.LAB00267" />',name:'confDesc', width:300, align:"center"}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#confSbjListGridPager',
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
	    	$("#confSbjListGrid").setGridWidth($("#confSbjListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#confSbjListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}	
	});   
	$("#confSbjListGrid").setGridWidth($("#confSbjListGridDiv").width(),false);
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
				<th><spring:message code="LAB.M07.LAB00261"/></th>
				<td>
					<select id="confRslt" name="confRslt" class="w300">
						<option value=""><spring:message code="LAB.M09.LAB00063"/></option>
						<c:forEach items="${confRslt}" var="confRslt" varStatus="status">
							<option value="${confRslt.commonCd}">${confRslt.commonCdNm}</option>
						</c:forEach>					
					</select>
				</td>
			</tr>			
		</thead>
	</table> 
<div class="main_btn_box">
	
</div>
<div id="confSbjListGridDiv">
	<table id="confSbjListGrid" class="w100p"></table>
	<div id="confSbjListGridPager"></div>
</div>

<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">	
		<a id="btnAppro" class="grey-btn" title='<spring:message code="LAB.M07.LAB00263"/>' href="#"><span><spring:message code="LAB.M07.LAB00263"/></span></a>
	</div>
  </div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:600px;" >
</div>
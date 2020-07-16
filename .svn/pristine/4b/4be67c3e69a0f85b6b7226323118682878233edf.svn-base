<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	
	excuteProductServiceList();

	$('#productServiceListPopupBtnInsert').click(function() {
		var rowId = $("#productServiceListPopupGrid").getGridParam("selrow");
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00072" />');
			return;
		}
		
		var param = new Object();
		
		param.prodCd =  $("#productServiceListProcCd").val();
		param.svcCd =  $("#productServiceListPopupGrid").getRowData(rowId).svcCd;
		
		if(param != false){
			
			$.ajax({
				url : 'addProductService.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						$("#productServiceListPopupBtnClose").trigger('click');
						$("#productServiceListGrid").trigger("reloadGrid");
						//reloadProduct();
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
		}
		
		
	});
	
});

function excuteProductServiceList() {
	var param = new Object();
	param.prodCd = $("#productServiceListProcCd").val();
	
	$("#productServiceListPopupGrid").jqGrid({
		url:'getAddProductServiceInit.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
					{name:'svcCd', width:0,hidden:true},
				{label:'<spring:message code="LAB.M07.LAB00167" />',name:'svcNm', width:150, sortable:false}, 
	  			{label:'<spring:message code="LAB.M07.LAB00107" />',name:'uprSvcNm', width:150, sortable:false},  
				{label:'<spring:message code="LAB.M05.LAB00032" />',name:'mainSvcNm', width:150, sortable:false}

	   	],
	   	shrinkToFit:false,
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		sortable : false,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		loadComplete : function () {
  	      	$("#productServiceListPopupGrid").setGridWidth($('#productServiceListPopupGridDiv').width(),false);
        }

	});
	
	$("#productServiceListPopupGrid").setGridWidth($('#productServiceListPopupGridDiv').width(),false); 
}

$(window).resize(function() {
	$("#productServiceListPopupGrid").setGridWidth($('#productServiceListPopupGridDiv').width(),false);
});
</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00118"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00134"/></h4>
		</div>
	</div>             
	<div class="layer_box">
		<table id="productServiceListPopupGrid" class="w100p"></table>
		<div id="productServiceListGridPopupDiv"></div>
	</div>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="productServiceListPopupBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="productServiceListPopupBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<input id="productServiceListProcCd" value='${productDevMgt.prodCd}' type='text' hidden />

<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	$(".search").css("margin-top", "3px");
	getProductdefultRelationsList();

	$('#confUsrIdListBtnInsert').click(function() {
		var rowId = $("#productRelationshipListAllGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M01.MSG00001" />');
			return;
		}
		
		var prodCd = $("#productRelationshipListAllGrid").getRowData(rowId).prodCd;	
		
		$(parent.location).attr("href", "javascript:getproductRelDefreList('" + prodCd + "');");
		$("#confUsrIdListBtnClose").trigger('click');
		
		getProdect(prodCd);
	});	

	//조회
	$("#productRelationshipListAllSearch").click(function() {
		getProductdefultRelationsList();
	});
	
});	

$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});	

function getProdect(prodCd){
	var param = new Object();
	if ($("#productRelationshipListAllProdNm").val() != "") {
		param.prodNm = $("#productRelationshipListAllProdNm").val();
	} 	
	
	param.soId = $("#soId").val();
	param.prodCd = prodCd;
	
	$.ajax({
		url : 'getProdect.json',
		type : 'POST',
		async: false,
		data : param,
		success : function(data) {				
			$("#prodNm").val(data.result.prodNm);
			$("#prodNm").addClass('not-active'); 
			$("#prodNm").attr('disabled', true);	
			
			$("#productRelProdCd").val(data.result.prodCd);	
			
			$("#productRelBasicProdFl").val(data.result.basicProdFl);	
		},
	    error: function(request,status,error){
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    },
		complete : function() {
		}
	});	

}



function getProductdefultRelationsList(){
	var param = new Object();
	
	if ($("#productRelationshipListAllProdNm").val() != "") {
		param.prodNm = $("#productRelationshipListAllProdNm").val();
	} 	
	
	param.soId = $("#soId").val();
	
	$("#productRelationshipListAllGrid").jqGrid("GridUnload"); 
  	$("#productRelationshipListAllGrid").jqGrid({
		url:'getProductRelationshipListAll.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M07.LAB00156" />',name:'prodCd', width:100, align:"center"}, 
			{label:'<spring:message code="LAB.M07.LAB00146" />',name:'prodTypNm', width:100, align:"center"},      
			{label:'<spring:message code="LAB.M07.LAB00127" />',name:'prodGrpNm', width:150},			
			{label:'<spring:message code="LAB.M07.LAB00130" />',name:'prodNm', width:250}	
	   	],
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		loadComplete : function () {
  	      	$("#productRelationshipListAllGrid").setGridWidth($('#productRelationshipListAllGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        }        
	});
  	$("#productRelationshipListAllGrid").setGridWidth($('#productRelationshipListAllGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
}

$(window).resize(function() {
	$("#productRelationshipListAllGrid").setGridWidth($("#productRelationshipListAllGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});

</script>
	
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00123"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h3 class="ly_title"><spring:message code="LAB.M08.LAB00088"/></h3>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">			
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00130"/><!-- 상품명 -->
				</th>								                                        
	     		<td>  
					<input id="productRelationshipListAllProdNm" name="productRelationshipListAllProdNm" value="" class="w590" type="text" > 
					<a href="#" id="productRelationshipListAllSearch" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>			 		                                     				                                       
	     		</td>					
			</tr>														 		
		</thead>
	</table>	
	<div class="layer_box">
		<table id="productRelationshipListAllGrid" class="w100p"></table>
		<div id="productRelationshipListAllGridDiv"></div>
	</div>     
</div>

<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="confUsrIdListBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
	<a class="grey-btn close" href="#" id="confUsrIdListBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>


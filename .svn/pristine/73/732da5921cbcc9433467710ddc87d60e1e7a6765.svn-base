<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
.ui-jqgrid tr.jqgfirstrow td {padding: 0 2px 0 2px;border-right-width: 1px; border-right-style: solid;} 
  	 
</style>

<script type="text/javascript">
$(document).ready(function() {
	$("#productRelationshipListProdRelTyp").selectmenu()
	.selectmenu( "option", "width", "100%" );	
	
	var tmpProdTyp = $("#productRelationshipPopUpProdTyp").val();
 	if (tmpProdTyp == "11") {
		$("#productRelationshipListProdRelTyp").val("C").selectmenu('refresh');	
		$("#productRelationshipListProdRelTyp").selectmenu("disable");
	} else  if (tmpProdTyp == "80")  {
		$("#productRelationshipListProdRelTyp").val("S").selectmenu('refresh');	
		$("#productRelationshipListProdRelTyp").selectmenu("disable");
	} else {
		$("#productRelationshipListProdRelTyp").find("option").each(function(){
		    if(this.value != "P" && this.value != "X"){
		        $(this).remove();
		    }
		});
		
		$("#productRelationshipListProdRelTyp").val("P").selectmenu('refresh');	
	} 
	

	

	excuteGridAddProductRelationshipPopUp();
	
	$('#productRelationshipPopUpBtnInsert').click(function() {
		
		var rowId = $("#productRelationshipPopUpGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00065" />');
			return;
		}
				
		var prodCd = $("#productRelationshipPopUpGrid").getRowData(rowId).prodCd;
		
		
		var param = new Object();
		param.prodRelTyp = $("#productRelationshipListProdRelTyp").val();
		param.prodCd = $("#productRelationshipPopUpProdCd").val();
		param.relProdCd = prodCd;
		
		$.ajax({
			url : 'addProductRelationship.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					
					$("#productRelationshipPopUpBtnClose").trigger('click');
					$("#productRelationshipListGrid").trigger("reloadGrid");
					reloadProduct();
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
	
	$("#productRelationshipListProdRelTyp").selectmenu({
		change: function( event, ui ) {
			excuteGridAddProductRelationshipPopUp();
			
		}
	});
	
});

function excuteGridAddProductRelationshipPopUp() {
	var param = new Object();
	param.prodRelTyp = $("#productRelationshipListProdRelTyp").val();
	param.prodCd = $("#productRelationshipPopUpProdCd").val();
	param.soId = $("#productRelationshipPopUpSoId").val();
	
	$("#productRelationshipPopUpGrid").jqGrid("GridUnload"); 
	$("#productRelationshipPopUpGrid").jqGrid({
		url:'getProductRelComboList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M07.LAB00156" />',name:'prodCd', width:100, align:"center"},
			{label:'<spring:message code="LAB.M01.LAB00221" />',name:'prodNm', width:247}
	   	],
	   	shrinkToFit:false,
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
		}

	});
}


</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00180"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00122"/></h4>
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
					<spring:message code="LAB.M01.LAB00158"/><!-- 관계 -->
				</th>
				<td>
					<select class="w100p" id="productRelationshipListProdRelTyp" name="productRelationshipListProdRelTyp">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${prodRelTyp}" var="prodRelTyp" varStatus="status">
							<option value="${prodRelTyp.commonCd}">${prodRelTyp.commonCdNm}</option>
						</c:forEach>
					</select>   
				</td>	
			</tr>		
		</thead>
	</table>
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M03.LAB00051"/></h4>
		</div>
	</div>
	
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">
		</colgroup>
		<thead>
			<tr>
				<td colspan="2">
					<div id='productRelationshipPopUpGridDiv'>
						<table id="productRelationshipPopUpGrid" class="w100p"></table>
					</div>				
				</td>
			</tr> 		
		</thead>
	</table>	
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="productRelationshipPopUpBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="productRelationshipPopUpBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<input id="productRelationshipPopUpProdCd" type='text' value="${productDevMgt.prodCd}"  hidden />
<input id="productRelationshipPopUpSoId" type='text' value="${productDevMgt.soId}"  hidden />
<input id="productRelationshipPopUpProdTyp" type='text' value="${productDevMgt.prodTyp}"  hidden />
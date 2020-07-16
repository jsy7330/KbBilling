<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {

	$(".search").css("margin-top", "3px");
	
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng,
		      dateFormat:"yy-mm-dd"
		    }).datepicker("setDate", "1"); //시스템 날짜 출력
	}
	
	excuteProductRelComboList();
	
	$('#productRelComboListbtnInsert').click(function() {
		var rowId = $("#productRelComboListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M06.MSG00029" />');
			return;
		}		
		
		var soId = $("#productRelComboListGrid").getRowData(rowId).soId;
		var prodCd = $("#productRelComboListGrid").getRowData(rowId).prodCd;
		var prodTyp = $("#productRelComboListGrid").getRowData(rowId).prodTyp;
		var prodGrp = $("#productRelComboListGrid").getRowData(rowId).prodGrp;
		var prodNm = $("#productRelComboListGrid").getRowData(rowId).prodNm;
		var prodAbbrNm = $("#productRelComboListGrid").getRowData(rowId).prodAbbrNm;
		var subsFl = $("#productRelComboListGrid").getRowData(rowId).subsFl;
		var basicProdFl = $("#productRelComboListGrid").getRowData(rowId).basicProdFl;
		var svcGrp = $("#productRelComboListGrid").getRowData(rowId).svcGrp;
		var useMmTyp = $("#productRelComboListGrid").getRowData(rowId).useMmTyp;
		var prodLvl = $("#productRelComboListGrid").getRowData(rowId).prodLvl;
		var mnoProdCd = $("#productRelComboListGrid").getRowData(rowId).mnoProdCd;
		

		$('#productDevMgtCopySoId').val(soId).selectmenu('refresh');
		$('#productDevMgtCopyProdTyp').val(prodTyp).selectmenu('refresh');
		$('#productDevMgtCopyProdGrp').val(prodGrp).selectmenu('refresh');
		$('#productDevMgtCopySubsFl').val(subsFl).selectmenu('refresh');
		$('#productDevMgtCopyBasicProdFl').val(basicProdFl).selectmenu('refresh');
		$('#productDevMgtCopySvcGrp').val(svcGrp).selectmenu('refresh');
		$('#productDevMgtCopyUseMmTyp').val(useMmTyp).selectmenu('refresh');
		
		$("#oldProdCd").val(prodCd);
		$("#productDevMgtCopyProdNm").val(prodNm);
		$("#productDevMgtCopyProdAbbrNm").val(prodAbbrNm);
		$("#productDevMgtCopyProdLvl").val(prodLvl);
		$("#productDevMgtCopyMnoProdCd").val(mnoProdCd);
		
		$("#productRelComboListBtnClose").trigger('click');
		
	});	
	
});

function excuteProductRelComboList() {
	var param = new Object();
	
	$("#productRelComboListGrid").jqGrid({
		url:'getCopyProductList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
	  			{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100, align:"center", sortable:false},  
	  			{label:'<spring:message code="LAB.M07.LAB00146" />',name:'prodTypNm', width:80, sortable:false},
				{label:'<spring:message code="LAB.M07.LAB00156" />',name:'prodCd', width:100, align:"center", sortable:false},
				{label:'<spring:message code="LAB.M07.LAB00130" />',name:'prodNm', width:200, sortable:false},
				{name:'prodCd', hidden:true},
				{name:'prodTyp', hidden:true},
				{name:'prodGrp', hidden:true},
				{name:'prodAbbrNm', hidden:true},
				{name:'prodEngNm', hidden:true},
				{name:'prodEngAbbrNm', hidden:true},
				{name:'basicProdFl', hidden:true},
				{name:'subsFl', hidden:true},
				{name:'svcGrp', hidden:true},
				{name:'useMmTyp', hidden:true},
				{name:'prodLvl', hidden:true},
				{name:'kpiProdGrpCd', hidden:true},
				{name:'soId', hidden:true},
				{name:'mnoProdCd', hidden:true}
	   	],
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
  	      	$("#productRelComboListGrid").setGridWidth($('#productRelComboListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	//$("#productRelComboListGrid_cb").css("width", "29px");
        }

	});
	
	//$("#productRelComboListGrid").setGridWidth($('#productRelComboListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	//$("#productRelComboListGrid_cb").css("width", "29px");
}


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
			<h4 class="ly_title"><spring:message code="LAB.M08.LAB00088"/></h4>
		</div>
	</div>             
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">
		</colgroup>
		<thead>
			<tr>
				<div class="layer_box">
					<div id='productRelComboListGridDiv'>
						<table id="productRelComboListGrid" class="w100p"></table>
					</div>				
				</div>
			</tr> 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="productRelComboListbtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="productRelComboListBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
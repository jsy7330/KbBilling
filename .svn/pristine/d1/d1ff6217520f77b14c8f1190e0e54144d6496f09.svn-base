<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<style>
.layer_top a.close1{background:url('/images/icon/close_icon.png') no-repeat 4px 4px;}
</style>

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
	
	excuteCommonGrpPopupList($("#commonGrpPopupListNm").val());
	
	$('#commonGrpPopupListSearch').click(function() {	
		excuteCommonGrpPopupList($("#commonGrpPopupListNm").val());
	});
	
	$('#commonGrpPopupListbtnInsert').click(function() {
		var rowId = $("#commonGrpPopupListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00101" />');
			return;
		}		
		
		var commonGrp = $("#commonGrpPopupListGrid").getRowData(rowId).commonGrp;
		var commonGrpNm = $("#commonGrpPopupListGrid").getRowData(rowId).commonGrpNm;

		$("#factorListPopupCommonGrpNm").val(commonGrpNm);
		$("#factorListPopupCommonGrp").val(commonGrp);
		
		$("#commonGrpPopupListBtnClose").trigger('click');
		
	});	
	
	
	
	$('#commonGrpPopupListBtnClose').click(function() {	
		modalClose();
	});
	$('#popClose').click(function() {	
		modalClose();
	});
	
	
});

function modalClose(){
	$("#popup_dialog2").html("");
	$("#popup_dialog2").hide();
}

function excuteCommonGrpPopupList(commonGrpNm) {
	var param = new Object();
	
	if(fctrNm != ''){
		param.commonGrpNm = commonGrpNm;
	}
	
	$("#commonGrpPopupListGrid").jqGrid("GridUnload"); 
	$("#commonGrpPopupListGrid").jqGrid({
		url:'getCommonGrpPopupList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M01.LAB00102" />',name:'commonGrp', width:90, align:"center"},
			{label:'<spring:message code="LAB.M01.LAB00103" />',name:'commonGrpNm', width:220}
	   	],
	   	shrinkToFit:false,
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
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
  	      	$("#commonGrpPopupListGrid").setGridWidth($('#commonGrpPopupListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	$("#commonGrpPopupListGrid_cb").css("width", "29px");
        }

	});
	
	$("#commonGrpPopupListGrid").setGridWidth($('#commonGrpPopupListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#commonGrpPopupListGrid_cb").css("width", "29px");
}


</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M08.LAB00067"/></div>
	<a href="#" class="close1" id="popClose" >Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M10.LAB00015"/></h4>
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
					<spring:message code="LAB.M01.LAB00103"/><!-- 공통그룹명 -->
				</th>
				<td>
					<input type="text" id="commonGrpPopupListNm" name="commonGrpPopupListNm" class="w270">
					<a href="#" id="commonGrpPopupListSearch" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>
				</td>	
			</tr>
			<tr>
			<div class="layer_box">			
				<table id="commonGrpPopupListGrid" class="w100p"></table>
				<div id='commonGrpPopupListGridDiv'></div>
			</div>						
			</tr> 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="commonGrpPopupListbtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="commonGrpPopupListBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
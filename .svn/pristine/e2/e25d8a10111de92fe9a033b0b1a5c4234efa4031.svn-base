<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {

	$(".search").css("margin-top", "3px");
	
	excuteqtClCdComboList();

	$('#eqtClCdComboListSearch').click(function() {
		excuteqtClCdComboList();
	});	

	$('#eqtClCdComboListbtnInsert').click(function() {
		var rowId = $("#eqtClCdComboListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M09.MSG00003" />');
			return;
		}		
		
		var itemNm = $("#eqtClCdComboListGrid").getRowData(rowId).itemNm;
		$('#productDevMgtCopyMnoProdCd').val(itemNm);
		$('#productListInsertMnoProdCd').val(itemNm);
		$('#productListUpdateMnoProdCd').val(itemNm);
		
		$("#eqtClCdComboListBtnClose").trigger('click');
		
	});	
	
});

function excuteqtClCdComboList() {
	$("#eqtClCdComboListGrid").jqGrid("GridUnload"); 
	var param = new Object();
	
	param.soId = $("#eqtClCdComboListSoId").val();
	if ($("#eqtClCdComboListEqtClCd").val() != '') {
		param.eqtClCd = $("#eqtClCdComboListEqtClCd").val();
	}
	
	if ($("#eqtClCdComboListEqtClNm").val() != '') {
		param.eqtClNm = $("#eqtClCdComboListEqtClNm").val();
	}	
	
	
	$("#eqtClCdComboListGrid").jqGrid({
		url:'getEqtClCdComboList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
	  			{label:'<spring:message code="LAB.M09.LAB00045" />',name:'eqtClCd', width:100, align:"center", sortable:false},  
	  			{label:'<spring:message code="LAB.M09.LAB00044" />',name:'eqtClNm', width:260, sortable:false},
				{label:'<spring:message code="LAB.M14.LAB00042" />',name:'itemNm', width:100, sortable:false}
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
  	      	$("#eqtClCdComboListGrid").setGridWidth($('eqtClCdComboListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00225"/></h4>
		</div>
	</div>             
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:30%;">
			<col style="width:20%;">
			<col style="width:30%;">			
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span>
				</th>
	            <td colspan="3">
					<select class="w200" id="eqtClCdComboListSoId" name="eqtClCdComboListSoId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${userSoId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>			
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00045"/><!-- 장비유형 코드 -->
				</th>
				<td>
					<input type="text" id="eqtClCdComboListEqtClCd" name="eqtClCdComboListEqtClCd" class="w100p">                                                                               
				</td>			
				<th>
					<spring:message code="LAB.M09.LAB00044"/><!-- 장비유형명 -->
				</th>
				<td>
					<input type="text" id="eqtClCdComboListEqtClNm" name="eqtClCdComboListEqtClNm" class="w100">
					<a href="#" id="eqtClCdComboListSearch" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>                                                                               
				</td>
			</tr>		
			<tr>
			<div class="layer_box">			
				<table id="eqtClCdComboListGrid" class="w100p"></table>
				<div id='eqtClCdComboListGridDiv'></div>
			</div>	
			</tr> 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="eqtClCdComboListbtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="eqtClCdComboListBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
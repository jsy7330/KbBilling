<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">

	var popType = "${pymAcntVO.popType}";	//팝업타입
	
	$(document).ready(function() {
		
		if(popType == "o"){	//일반팝업일 경우 버튼 닫기 처리

			$("#btnPymSearchClose").click(function() {
				window.open("about:blank", "_self").close();
			});
			
			$("#btnClose").click(function() {
				window.open("about:blank", "_self").close();
			});
			
		}
		
		
		$("#pymPopupGrid").jqGrid({

		   	url:'/system/common/common/pymAcntSearch/pymAcntList.json?',
		    mtype:"POST",
		   	datatype: "local",
		   	colModel:[
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'soNm'},
				{ label: '<spring:message code="LAB.M02.LAB00005"/>', name: 'pymAcntId',sortable:false},
				{ label: '<spring:message code="LAB.M02.LAB00008"/>', name: 'acntNm',sortable:false},
				{ label: '<spring:message code="LAB.M02.LAB00016"/>', name: 'pymMthdNm',sortable:false},	//납부방법
				{ label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'custId'},
				{ label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'custNm'},
				{ label: '<spring:message code="LAB.M09.LAB00065"/>', name: 'telNo',sortable:false},
				{ label: '<spring:message code="LAB.M14.LAB00076"/>', name: 'mtelNo',sortable:false},
				{ label: '<spring:message code="LAB.M01.LAB00048"/>', name: 'custTpNm',sortable:false},
				{ label: '<spring:message code="LAB.M09.LAB00190"/>', name: 'addr',sortable:false}
				
		   	],
		  	viewrecords: true,
			height: 250,
			width: 900, 
			sortable : true,
			rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
			rowNum: 10,
			jsonReader: {
				repeatitems : true,
				root : "pymAcntList",
				records : "totalCount", //총 레코드 
				total : "totalPages",  //총페이지수
				page : "page"          //현재 페이지
			},
			pager: "#pymPopupGridPager",
			loadComplete: function(data){
				
				if(popType == "o"){
					$("#pymPopupGrid").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#pymPopupGrid").setGridWidth(898,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
			},
			ondblClickRow : function(id){
				selectPymAcnt();
			},
	    	sortable: { update: function(permutation) {
	    		if(popType == "o"){
					$("#pymPopupGrid").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#pymPopupGrid").setGridWidth(898,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
	    	}}
		});
		
		
		$('#btnSearchPymPopup').click(function() {
			getpymAcntGrid();
		});
		
		$('#btnPymSearchSelect').click(function() {
			selectPymAcnt();
		});
		
	});
	
	
	function getpymAcntGrid(){
		var param = new Object();
		param.soId = $("#popupSoId").val();
		param.custNm = $("#popupCustNm").val();
		param.custId = $("#popupCustId").val();
		param.pymAcntId = $("#popupPymAcntId").val();
		param.acntNm = $("#popupAcntNm").val();
		
		$("#pymPopupGrid").clearGridData();
  		 $("#pymPopupGrid").setGridParam({
  			datatype : 'json',
			postData : param
		});
		$("#pymPopupGrid").trigger("reloadGrid");
	}
	
	function selectPymAcnt(){
		var returnId1 = "${pymAcntVO.returnId1}";
		var returnId2 = "${pymAcntVO.returnId2}";
		var index  = $("#pymPopupGrid").jqGrid("getGridParam", "selrow" );
		if(index == null || index == ''){
			alert('<spring:message code="MSG.M02.MSG00013" />');
			return;
		}
		var returnUserNm = $("#pymPopupGrid").getRowData(index).acntNm;
		var returnUserId = $("#pymPopupGrid").getRowData(index).pymAcntId;
		if(popType == "m"){
			$("#"+returnId1).val(returnUserNm);
			$("#"+returnId2).val(returnUserId);
			$("#btnClose").trigger('click');
			
		}else if(popType == "o"){
			$("#"+returnId1,opener.document).val(returnUserNm);
			$("#"+returnId2,opener.document).val(returnUserId);
			window.open("about:blank", "_self").close();
		}
	}
	
</script>

<c:if test="${pymAcntVO.popType eq 'o'}"> 
	<div class="Layer_wrap" style="width:900px;" >
</c:if>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M02.LAB00011" /></div>
	<a href="#" id="btnPymSearchClose" class="close">Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M02.LAB00011"/></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="btnSearchPymPopup" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>            
	                
	<table class="writeview">
		<colgroup>
			<col style="width:13%;">
			<col style="width:20%;">
			<col style="width:13%;">
			<col style="width:20%;">
			<col style="width:13%;">
			<col style="width:21%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/></th>
				<td>
					<select class="w170" id="popupSoId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
				<th><spring:message code="LAB.M01.LAB00050"/></th>
				<td>
					<input type="text" id="popupCustNm" name="txtPymSearchCustNm" class="w100p">
				</td>
				<th><spring:message code="LAB.M01.LAB00046"/></th>
				<td>
					<input type="text" id="popupCustId" class="w100p">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M02.LAB00006"/></th><!-- 납부계정ID -->
				<td>
					<input type="text" id="popupPymAcntId" class="w100p">
				</td>
			
				<th><spring:message code="LAB.M02.LAB00008"/></th><!-- 납부계정명 -->
				<td colspan="3">
					<input type="text" id="popupAcntNm"  class="w100p">  
				</td>
				
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M02.LAB00007"/></h4>
		</div>
	</div>
	
<c:if test="${pymAcntVO.popType eq 'm'}"> 
	<div class="layer_box">
		<table id="pymPopupGrid" class="w100p"></table>
		<div id="pymPopupGridPager"></div>
	</div>
</c:if>

<c:if test="${pymAcntVO.popType eq 'o'}"> 
	<div id='gridDivPop01'>
		<table id="pymPopupGrid" class="w100p"></table>
		<div id="pymPopupGridPager"></div>
	</div>
</c:if>	
	
	
	
	
</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnPymSearchSelect" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
	

<c:if test="${pymAcntVO.popType eq 'o'}"> 
	</div>
</c:if>
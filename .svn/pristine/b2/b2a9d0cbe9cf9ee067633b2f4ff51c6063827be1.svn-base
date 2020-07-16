<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
var popType = "${userSearch.popType}";	//팝업타입

$(document).ready(function() {
	
	$('#condSoIdPop').val('SEL');
	
	$('#condSoIdPop').focus();
	if(popType == "o"){	//일반팝업일 경우 버튼 닫기 처리
		$("#condSoIdPop" ).selectmenu( "refresh" );
		$("#btnColse1SeP").click(function() {
			window.open("about:blank", "_self").close();
		});
		
		$("#btnClose").click(function() {
			window.open("about:blank", "_self").close();
		});
		
	}
	
	$("#userSearchPop").jqGrid({

	   	url:'/system/common/common/userSearchMng/userSearchListAction.json?',
	   	postData : {
	   		
	   	},
	    mtype:"POST",
	   	datatype: "json",
	   	colModel:[
	   		{ label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'soNm', width : 100, align:"center"},
		    { label: '<spring:message code="LAB.M07.LAB00067"/>' , name: 'userId',width : 100, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00071"/>', name: 'userName', width : 100},
			{ label: '<spring:message code="LAB.M09.LAB00139"/>' , name: 'orgId',width : 100},
		    { label: '<spring:message code="LAB.M09.LAB00147"/>' , name: 'orgNm',width : 100},
		    { label: '<spring:message code="LAB.M07.LAB00074"/>' , name: 'crrTpNm',width : 100},
		    { label: '<spring:message code="LAB.M14.LAB00076"/>' , name: 'mtelNo',width : 100,formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M08.LAB00119"/>' , name: 'eMail',width : 100}
	   	],
	  	viewrecords: true,
		height: 250,
		width: 800, 
		sortable : true,
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		rowNum: 10,
		jsonReader: {
			repeatitems : true,
			root : "userSearchList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		pager: "#jqGridPager",
		loadComplete: function(data){
			
			if(popType == "o"){
				$("#userSearchPop").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			}else{
				$("#userSearchPop").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			}
		},
		ondblClickRow : function(id){ //ROW Ŭ���� �̺�Ʈ
			selectUserSep();
		},
    	sortable: { update: function(permutation) {
    		if(popType == "o"){
				$("#userSearchPop").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			}else{
				$("#userSearchPop").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			}
    		}
    	}
	});
	
	if(popType == "o"){
		
		$("#userSearchPop").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#userSearchPop").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	
	}else{
		$("#userSearchPop").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}

	//검색 enter 이벤트
	$("#condUserNmPop").keypress(function(event) {
		if(event.keyCode == 13){
			 $("#userSearchPop").clearGridData();
	   		 $("#userSearchPop").setGridParam({
	 			postData : {
	 				condSoId : $("#condSoIdPop").val(),
	 				condUserNm : $("#condUserNmPop").val()
	 			}
	 		});
 			$("#userSearchPop").trigger("reloadGrid"); 
		}
	});
	
	//유형 셀렉트 박스 체인지 이벤트
	$('#condSoIdPop').selectmenu({
	    change: function() {
	    	$("#userSearchPop").clearGridData();
	   		 $("#userSearchPop").setGridParam({
	 			postData : {
	 				condSoId : $("#condSoIdPop").val()
	 			}
	 		});
			$("#userSearchPop").trigger("reloadGrid"); 
	    }
	});
	
	//초기화 버튼 이벤트
	$('#btnSelectOrg').click(function() {
		selectUser();
	});
});


function selectUserSep(){
	var returnId1 = "${userSearch.returnId1}";
	var returnId2 = "${userSearch.returnId2}";
	var index  = $("#userSearchPop").jqGrid("getGridParam", "selrow" );
	
	var returnUserNm = $("#userSearchPop").getRowData(index).userName;
	var returnUserId = $("#userSearchPop").getRowData(index).userId;
	
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
function selectUser(){
	var returnId1 = "${userSearch.returnId1}";
	var returnId2 = "${userSearch.returnId2}";
	var index  = $("#userSearchPop").jqGrid("getGridParam", "selrow" );
	if(index == null || index == ''){
		alert('<spring:message code="MSG.M07.MSG00044" />');
		return;
	}
	var returnUserNm = $("#userSearchPop").getRowData(index).userName;
	var returnUserId = $("#userSearchPop").getRowData(index).userId;
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
<!--custom tag정리 필요로 인한 수정사항입니다. 기존 page와 확인하여 수정 진행하시기 바랍니다.  -->
	<div class="layer_top">
		<div class="title">
			<spring:message code="LAB.M07.LAB00077" />
		</div>
		<a href="#" id="btnColse1SeP" class="close"><spring:message code="LAB.M03.LAB00027" /></a>
	</div>
	<div class="layer_box">
		<table class="writeview column_2 row_1"><!--classAdd-->
<!-- 			<colgroup> -->
<!-- 				<col style="width:15%;"> -->
<!-- 				<col style="width:35%;"> -->
<!-- 				<col style="width:15%;"> -->
<!-- 				<col style="width:35%;"> -->
<!-- 			</colgroup> -->
			<tbody><!--thead 삭제 및 tbody사용--> 
				<tr class='col2'><!--colgroup정의 class-->
					<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
					<td>
						<select class="w100p" id="condSoIdPop" name="condSoIdPop">
							<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option> 
							<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
								<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
							</c:forEach>
						</select>  
					</td>
					<th><spring:message code="LAB.M07.LAB00071"/><!-- 조직ID/명 --></th>
					<td>
						<input type="text" id="condUserNmPop" name="condUserNmPop" class="w100p"><!--해당 위치에 아이콘이 존재하지 않을 시, div박스는 필요없습니다. 또는 div class='np'를 추가-->
<!-- 						<div class="inp_date w100p"> -->
<!-- 							<input type="text" id="condUserNmPop" name="condUserNmPop" class="w100p"> -->
<!-- 						</div> -->
					</td>
				</tr>
			</tbody><!--/tbody end-->
		</table> 
		<div class="main_btn_box"><!--class지정변경-->
			<div class="fl">
				<h4 class="ly_title"><spring:message code="LAB.M07.LAB00072"/><!-- 사용자목록 --></h4>
			</div>
		</div>
		<c:if test="${userSearch.popType eq 'm'}"> 
		<div class="layer_box">
			<table id="userSearchPop" class="w100p"></table>
			<div id="jqGridPager"></div>
		</div>
		</c:if>
	
		<c:if test="${userSearch.popType eq 'o'}"> 
		<div id='gridDivPop01'>
			<table id="userSearchPop" class="w100p"></table>
			<div id="jqGridPager"></div>
		</div>
		</c:if>
		<c:if test="${userSearch.popType eq 'm'}"> 
			<div class="btn_box">
		</c:if>
		<c:if test="${userSearch.popType eq 'o'}"> 
			<div class="main_btn_box"><!--class지정변경-->
		</c:if>
			<a class="grey-btn" id="btnSelectOrg" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
			<a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
		</div>
	</div><!--// layer_box-->
<!--하기 태그는 기존  -->
<!-- 	<c:if test="${userSearch.popType eq 'm'}">  -->
<!-- 		<div class="btn_box"> -->
<!-- 	</c:if> -->
<!-- 	<c:if test="${userSearch.popType eq 'o'}">  -->
<!-- 		<div class="btn_box mr10"> -->
<!-- 	</c:if> -->
<!-- 		<a class="grey-btn" id="btnSelectOrg" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a> -->
<!-- 		<a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a> -->
<!-- 	</div> -->

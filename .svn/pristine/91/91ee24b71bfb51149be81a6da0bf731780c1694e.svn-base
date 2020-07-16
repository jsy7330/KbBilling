<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		var popType = "${manufacturerMngVO.popType}";	//팝업타입
		
		if(popType == "o"){	//일반팝업일 경우 버튼 닫기 처리
					
			$("#btnColse1SeP").click(function() {
				window.open("about:blank", "_self").close();
			});
			
			$("#btnClose").click(function() {
				window.open("about:blank", "_self").close();
			});
			
		}
		
		//선택버튼
		/* 
		$("#btnSelectOrg").click(function() {
			selectOrgSep();            
		});
		 */
		
		initGrid();
		
	});
	
	
	function initGrid() {
		
		var param = new Object();
		param.soId = "${manufacturerMngVO.soId}";
		
		$("#manufacturerSearchTable").jqGrid({
	
		   	url:'/system/common/common/manufacturerMng/manufacturerListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colNames:[
				'<spring:message code="LAB.M07.LAB00003" />',		//사업
				'<spring:message code="LAB.M09.LAB00099" />',	//제조사ID
				'<spring:message code="LAB.M09.LAB00101" />'	//제조사명
			],
		   	colModel:[
		   		
		   		{label:'soNm',name:'soNm', width:80},
		   		{label:'mncoId',name:'mncoId', width:80},
		   		{label:'mncoNm',name:'mncoNm', width:80}
		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#manufacturerSearchPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 300,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectMncoSep();
		    }, 
		    loadComplete: function(){
		    	
		    	var popType = "${manufacturerMngVO.popType}";	//팝업타입
				
				if(popType == "o"){
					$("#manufacturerSearchTable").setGridWidth($('#gridDivPop01').width()-2,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				
				}else{
					$("#manufacturerSearchTable").setGridWidth(480,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}

		    },
	    	sortable: { update: function(permutation) {
	    		var popType = "${manufacturerMngVO.popType}";	//팝업타입
	    		
	    		if(popType == "o"){
					$("#manufacturerSearchTable").setGridWidth($('#gridDivPop01').width()-2,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				
				}else{
					$("#manufacturerSearchTable").setGridWidth(480,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
	    		}
	    	}
		});
		
		var popType = "${manufacturerMngVO.popType}";	//팝업타입
		
		if(popType == "o"){
		
			$("#manufacturerSearchTable").setGridWidth($('#gridDivPop01').width()-2,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			
			//그리드 화면 재조정
			$(window).resize(function() {
				$("#manufacturerSearchTable").setGridWidth($('#gridDivPop01').width()-2,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			});
		
		}else{
			$("#manufacturerSearchTable").setGridWidth(480,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}
		
		
	}
	
	function selectMncoSep(){
		
		var popType = "${manufacturerMngVO.popType}";
		var returnId1 = "${manufacturerMngVO.returnId1}";
		var returnId2 = "${manufacturerMngVO.returnId2}";
		
		var index  = $("#manufacturerSearchTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00099" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}
		
		var returnMncoId = $("#manufacturerSearchTable").getRowData(index).mncoId
		var returnMncoNm = $("#manufacturerSearchTable").getRowData(index).mncoNm
		
		if(popType == "m"){
			$("#"+returnId1).val(returnMncoId);
			$("#"+returnId2).val(returnMncoNm);
			$("#btnClose").trigger('click');
			
		}else if(popType == "o"){
			$("#"+returnId1,opener.document).val(returnMncoId);
			$("#"+returnId2,opener.document).val(returnMncoNm);
			window.open("about:blank", "_self").close();
			
		}
	}
	
</script>


<c:if test="${manufacturerMngVO.popType eq 'o'}"> 
	<div class="Layer_wrap" style="width:500px;" >
</c:if>


<div class="layer_top">
     <div class="title"><spring:message code="LAB.M09.LAB00102"/></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<c:if test="${manufacturerMngVO.popType eq 'm'}"> 
	<div class="layer_box" >
		<table id="manufacturerSearchTable" class="w100p"></table>
		<div id="manufacturerSearchPager"></div>
	</div>
</c:if>
	
<c:if test="${manufacturerMngVO.popType eq 'o'}"> 
	<div id='gridDivPop01'>
		<table id="manufacturerSearchTable" class="w100p"></table>
		<div id="manufacturerSearchPager"></div>
	</div>
</c:if>

<div class="btn_box">
	<a class="grey-btn close" href="#" id="btnClose" name="btnClose" title="<spring:message code="LAB.M03.LAB00027"/>">
		<span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span>
	</a>
</div>

<c:if test="${manufacturerMngVO.popType eq 'o'}"> 
	</div>
</c:if>
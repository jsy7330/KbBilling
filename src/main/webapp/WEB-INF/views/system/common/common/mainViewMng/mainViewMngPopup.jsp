<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">

$(document).ready(function() {
	$("#mainViewPop").jqGrid({
		url : '/system/common/common/mainViewMng/mainViewListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
		},
		colModel: [ 
		     	{ label: '<spring:message code="LAB.M13.LAB00017"/>', name: 'commonCdNm', width : 100},
			    { label: '<spring:message code="LAB.M13.LAB00016"/>', name: 'refCode', width : 150}

		],
		viewrecords: true,
		shrinkToFit:false,
		height: 437,
		width : 400,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "mainViewList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowNum: -1,  
	    onCellSelect : function(rowid, index, contents, event){
			$("#mainView", parent.document.body).val($("#mainViewPop").getRowData(rowid).refCode);
			$('#mask, .Layer_wrap').hide();
	    },
	   	loadComplete : function (data) {
	   		
	   	}
		});
		$(window).resize(function() {
			$("#mainViewPop").setGridWidth($('#mainViewPop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
});
</script>
<div class="layer_top">
	<div class="title">
		<spring:message code="LAB.M15.LAB00057" />
	</div>
	<a href="#" class="close"></a>
</div>
<div class="layer_box">
	<table id="mainViewPop" class="w100p"></table>
	<div id="jqGridPager"></div>
	<div class="btn_box">
		<a class="grey-btn close" href="javascript:closeModal();" id="btn_cancel" title="<spring:message code="LAB.M03.LAB00027"/>">
			<span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span>
		</a>
	</div>
</div>
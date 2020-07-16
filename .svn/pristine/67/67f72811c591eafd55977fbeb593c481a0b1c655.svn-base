<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {

	$("#popupHome_Grid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
			{ label: '<spring:message code="LAB.M14.LAB00069"/>', name: 'MEM_ID', width : 80, align:"center"},
		    { label: '<spring:message code="LAB.M14.LAB00088"/>', name: 'MEM_NM', width : 120, align:"left"},
		    { label: '<spring:message code="LAB.M08.LAB00021"/>', name: 'TEL_NO', width : 120, align:"center", formatter:telNoFormatter}
		],
		viewrecords: true,
		shrinkToFit:false,
		loadonce : true,
		height: 300,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "homeServiceList"
	  },
      loadComplete : function () {
      	  $("#popupHome_Grid").setGridWidth($('#popupHome_GridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
          $("#popupHome_Grid").trigger("reloadGrid");
      },
      loadError: function (jqXHR, textStatus, errorThrown) {
       	ajaxErrorCallback(jqXHR);
      },
  	  sortable: { 
	   		update: function(permutation) {
		    		$("#popupHome_Grid").setGridWidth($('#popupHome_GridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	   		}
  		}
	});
	popup_getHomeServiceList();
 	
});

/**
 * 홈서비스ID 조회
 */ 
function popup_getHomeServiceList(){
	var soId = '${soId}';
	var custId = '${custId}';
	$("#popupHome_Grid").clearGridData();
	$("#popupHome_Grid").setGridParam({
		url : '/customer/contract/contract/homeService/getHomeServiceListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			soId : soId,
  			custId : custId
		}
	});
	      	
   	$("#popupHome_Grid").trigger("reloadGrid");
}

</script>

<div class='layer_top'>
	<div class='title'><spring:message code="LAB.M14.LAB00087"/>
	</div>
	<a href='#' class='close'></a>
</div>

<div class='layer_box'>
	<div id="popupHome_GridDiv" class="w500">
		<table id="popupHome_Grid" class="w100p"></table>
	</div>
	<div class='btn_box'>
		<a class="grey-btn close" href="#">
			<span class="cancel_icon"><spring:message code="LAB.M03.LAB00027"/></span>
		</a>
	</div><!--//btn_box-->
</div>
	
		

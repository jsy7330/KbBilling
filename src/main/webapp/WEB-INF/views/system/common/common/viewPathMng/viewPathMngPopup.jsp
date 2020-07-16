<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	$('#condCommonCd').selectmenu();
    $('#condCommonCd').val('SEL'); 
    $('#condCommonCd').selectmenu("refresh");
	
	$("#viwPathPop").jqGrid({
		url : '/system/common/common/viewPathMng/mainListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
		},
		colModel: [ 
		     	{ label: '<spring:message code="LAB.M07.LAB00285"/>', name: 'commonCdNm', width : 100},
		     	{ label: '<spring:message code="LAB.M13.LAB00022"/>', name: 'commonGrpNm', width : 100},
			    { label: '<spring:message code="LAB.M15.LAB00095"/>', name: 'refCode2', width : 200}

		],
		viewrecords: true,
		shrinkToFit:false,
		height: 300,
		width: 900,
		sortable: { update: function(permutation) {
    		$("#viwPathPop").setGridWidth($('#viewPathPopDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
		jsonReader: {
			repeatitems : true,
			root : "viewPathList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowNum: -1,  
	    onCellSelect : function(rowid, index, contents, event){
			$("#viewPath", parent.document.body).val($("#viwPathPop").getRowData(rowid).refCode2);
			$('#mask, .Layer_wrap').hide();
	    },
	   	loadComplete : function (data) {
	   		$("#viwPathPop").setGridWidth($('#viewPathPopDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
   		}
	});
	
	$(window).resize(function() {
		$("#viwPathPop").setGridWidth($('#viewPathPopDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	//시스템구분 셀렉트 박스 체인지 이벤트
	$('#condCommonCd').selectmenu({
	    change: function() {
	    	$("#viwPathPop").clearGridData();
			 $("#viwPathPop").setGridParam({
				postData : {
					condCommonCd : $("#condCommonCd").val()
				}
			});
			$("#viwPathPop").trigger("reloadGrid"); 
	    }
	});
	
	//검색 enter 이벤트
	$( "#condCommonCdNm" ).keypress(function(event) {
			
   		if(event.keyCode == 13){
   			 $("#viwPathPop").clearGridData();
	   		 $("#viwPathPop").setGridParam({
	 			postData : {
	 				condCommonCd : $("#condCommonCd").val(),
	 				condCommonCdNm : $("#condCommonCdNm").val()
	 			}
	 		});
	 		$("#viwPathPop").trigger("reloadGrid"); 
   		}
	});
});
</script>
<div class="layer_top">
	<div class="title">
		<spring:message code="LAB.M15.LAB00096" />
	</div>
	<a href="#" class="close"></a>
</div>
<table class="writeview">
	<colgroup>
		<col style="width: 20%;" />
		<col style="width: 20%;" />
		<col style="width: 20%;" />
		<col style="width: 40%;" />
	</colgroup>
	<thead>
		<tr>
			<th title="<spring:message code="LAB.M07.LAB00285"/>"><spring:message code="LAB.M07.LAB00285"/></th>
			<td>
				<select id="condCommonCd" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
				<c:forEach items="${commonCdList}" var="commonCdList" varStatus="status">
					<option value="${commonCdList.commonCd}">${commonCdList.commonCdNm}</option>
				</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M15.LAB00095"/></th>
			<td>
				<input id="condCommonCdNm" type="text" class="w100p" />
			</td>
		</tr>
	</thead>
</table>
<div class="layer_box">
	<div id="viewPathPopDiv">
		<table id="viwPathPop" class="w100p"></table>
	</div>
	<div id="jqGridPager"></div>
	<div class="btn_box">
		<a class="grey-btn close" href="javascript:closeModal();" id="btn_cancel" title="<spring:message code="LAB.M03.LAB00027"/>">
			<span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span>
		</a>
	</div>
</div>
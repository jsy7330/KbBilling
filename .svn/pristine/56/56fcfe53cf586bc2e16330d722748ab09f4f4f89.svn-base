<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
<!--
$(document).ready(function() {

	initGrid();	
	
	$("#btn_search2").click(function() {
		$("#page").val("1");
		goSearch2();
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function goSearch2() {
	var param = new Object();
	param.page =  $("#page").val();
	param.perPage =  $("#perPage").val();
	param.commonGrpNm =  $("#commonGrpNm").val();		
	openPop( $("#commonGrpNm").val());
	$("#popup_dialog").remove();
}

function getParam(){
	var param = new Object();	
	param.commonGrpNm =  $("#commonGrpNm").val();
	return param;
}

function goInsertValue(commonGrp,commonGrpNm){
	$("#refCd", parent.document.body).val(commonGrp);
	$("#refCdNm", parent.document.body).val(commonGrpNm);
	$("#popup_dialog").remove();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

function initGrid() {

	var param = new Object();
	param.commonGrpNm =  $("#commonGrpNm").val();
	$("#attributePop").jqGrid({
	   	url:'commListActionPopUp.json',
	    mtype:"POST",
	   	datatype: "json",
		postData : param,
	   	colNames:[
			'<spring:message code="LAB.M01.LAB00109" />',
			'<spring:message code="LAB.M01.LAB00106" />'
		],
	   	colModel:[
	   		{name:'commonGrp',index:'commonGrp', width:300},
	   		{name:'commonGrpNm',index:'commonGrpNm', width:250}
	   	],
	   	rowNum:10,		
	   	rowList:[5,10,20,30,50],	
	   	pager: '#pager3',
		autowidth:false,
		sortable : true,				
	    viewrecords: true,	
		height:235,		
	    ondblClickRow : function(id){ 
	    	goInsertValue($("#attributePop").getRowData(id).commonGrp,$("#attributePop").getRowData(id).commonGrpNm);
	    }
	});
}
//-->
</Script>

<!-- title -->
<div class="layer_top">
   <div class="title"><spring:message code="LAB.M01.LAB00110" /></div>
   <a href="javascript:closeModal();" class="close">Close</a>
</div>
<!--// title -->   

<form id="frmCommList" name="frmCommList" method="post">			
	<input type="hidden" id="system_id" name="system_id" value="${resconfig.system_id}"/>
	<input type="hidden" id="resource_type" name="resource_type" value="${resconfig.resource_type}"/>
 
	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M01.LAB00110" /></h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search2"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:30%;">
			<col style="width:70%;">
		</colgroup>
		 <thead> 
			   <tr>
					<th><spring:message code="LAB.M01.LAB00109"/></th>
					<td>
						 <input type="text" id="commonGrpNm" name="commonGrpNm" value = "${param.commonGrpNm}" class="w100p" >
					</td>
				</tr>
		</thead>
	</table> 


<div class="main_btn_box">
	<h4 class="sub_title"><spring:message code="LAB.M01.LAB00110" /></h4>
</div>
<div id='gridDiv' class="w100p">
<table id="attributePop" class="writelist w100p"></table>
	<div id="pager3"></div>
</div>

<div class="main_btn_box">
	<div class="fr">
		<a class="grey-btn" href="javascript:closeModal();" id="btn_cancel"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>
  </div>
</div>

</form>
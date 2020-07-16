<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	
	
	var multiFlag=$("#multiFlag").val();
	if(multiFlag=="N"){
		initGridN();
	}else{
		initGrid();	
	}

});


function getParam(){
	var param = new Object();	
	param.srchNm = $("#srchNm").val();  
	
	return param;
}

function goInsertValue(userGroupId){
	$("#tmp1", parent.document.body).val(userGroupId);
}


/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}
function insertOk(){
	
		var ids=$("#attributePop").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
		
		var tmp1="";
		var tmp2="";
		for(var i=0; i<ids.length; i++){
			var rowObject = $("#attributePop").getRowData(ids[i]);					
			var valueId = rowObject.userGroupId;														
			var valueNm=rowObject.userGroupName;
			tmp1 +=valueId+",";
			tmp2+=valueNm+",";
		}
	 
	  	tmp1=tmp1.slice(0,-1);
	  	tmp2=tmp2.slice(0,-1);
		if($("#userId").val()==""){
			$("#userGroupName", parent.document.body).val(tmp2);
			$("#userGroupId", parent.document.body).val(tmp1);
			$('#mask, .Layer_wrap').hide();
		}else{
		 	goInsertAuth(tmp1,tmp2);
		}
}

function initGrid() {
	
	var param = new Object();
	
	$("#attributePop").jqGrid({
	
	   	url:'/system/common/common/userGroupMng/userGroupListActionPopup.json?'+ $.param(getParam()),
	    mtype:"POST",
	   	datatype: "json",
	   	colModel:[
	   		{label:'<spring:message code="LAB.M01.LAB00178" />', name:'userGroupId',width : 50,key:true},
	   		{label:'<spring:message code="LAB.M01.LAB00179" />',name:'userGroupName',width : 100}
	   	],
	  	multiselect: true,
	  	viewrecords: true,
		height: 250,
		width: 550, 
		sortable : true,
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		rowNum: -1,
		jsonReader: {
			repeatitems : true,
			root : "userGroupList"
		},
		loadComplete: function(data){
			$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			var tmp=$("#tmp1").val();
			
			if(tmp ==''){
				return;
			}
			
			var res=tmp.split(",");
			
			for(var i=0;i<res.length;i++){
				var userGroupId = res[i];
				var index = $("#attributePop").getRowData(userGroupId);
				
				$('input:checkbox[id="jqg_attributePop_' +index.userGroupId +'"]').attr("checked", true);	
				$('#attributePop').jqGrid('setSelection', index.userGroupId).attr('checkbox', true);
			}
		
		},
    	sortable: { update: function(permutation) {
    		$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
		ondblClickRow : function(id){ 
			
	}});
	//$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	$(window).resize(function() {
		$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}
function initGridN() {
	
	var param = new Object();
	
	$("#attributePop").jqGrid({

	   	url:'/system/common/common/userGroupMng/userGroupListActionPopup.json?'+ $.param(getParam()),
	    mtype:"POST",
	   	datatype: "json",
	   	jsonReader: {
			repeatitems : true,
			root : "userGroupList"
		},
	   	colModel:[
	   		{label:'<spring:message code="LAB.M01.LAB00202" />', name:'userGroupId',width : 50},
	   		{label:'<spring:message code="LAB.M01.LAB00203" />',name:'userGroupName',width : 100}
	   	],
	  	viewrecords: true,
		height: 250,
		width: 550, 
		sortable : true,
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		rowNum: -1,
		loadComplete: function(data){
			$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		},
		ondblClickRow : function(id){ 
			 var userGroupId=$("#attributePop").getRowData(id).userGroupId; 
			 var userGroupName=$("#attributePop").getRowData(id).userGroupName; 
			 $("#"+'${userGroupId}').val(userGroupId);
			 $("#"+'${userGroupName}').val(userGroupName);
			 $('#mask, .Layer_wrap').hide();
		},
    	sortable: { update: function(permutation) {
    		$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
	});
	$(window).resize(function() {
		$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}
function goInsertAuth(tmp1,tmp2){
	var param = new Object();
	var check=tmp1.split(",");
	var nm=$("#attributePop").getRowData($("#baseId").val()).userGroupName;
	var flag="N";
	for(var i=0;i<check.length;i++){
		if(check[i]==$("#baseId").val()){
			flag="Y";
		}
	}
	
	if(flag=="N"){
		alert(nm+'<spring:message code="MSG.M02.MSG00010"/>');
		return;
	}
	
	param.userId=$("#userId").val();
	param.userGroupId = tmp1;
	param.userGroupName = tmp2;
	
	 var url = '/system/auth/authMng/userAuthMng/insertAction.json';
		$.ajax({
			type : "post",
			url : url,
			data : param,
			async : true,
			success : function(data) {
				$("#userPerAuthGrid").trigger("reloadGrid"); 
				alert('<spring:message code="MSG.M09.MSG00005"/>');
				$('#mask, .Layer_wrap').hide();
			},
			complete : function() {
			}
		}); 
}
</Script>

<div class="layer_top">
	<div class="title">
		<c:if test="${userGroup.multiFlag eq 'N'}"> 
		<spring:message code="LAB.M01.LAB00202"/>
		</c:if>
		<c:if test="${userGroup.multiFlag ne 'N'}"> 
		<spring:message code="LAB.M01.LAB00177"/>
		</c:if>
	</div>
	<a href="#" class="close"></a>
</div>

<form id="frmCommList" name="frmCommList" method="post">			

<input type="hidden" id="tmp1" name="tmp1" value="${userGroup.userGroupId}"/>
<input type="hidden" id="tmp2" name="tmp2" value="${userGroup.userGroupName}"/>
<input type="hidden" id="multiFlag" name="multiFlag" value="${userGroup.multiFlag}"/>
<input type="hidden" id="userId" name="userId" value="${userId}"/>
<input type="hidden" id="baseId" name="baseId" value="${baseId}"/>
	<div class="layer_box">
		<table id="attributePop"  class="w100p"></table>
		<div id="jqGridPager"></div>
		<div class="btn_box">
			<c:if test="${userGroup.multiFlag!='N'}">
			<a class="grey-btn" id="btn_ok" href="javascript:insertOk();"title="ok"><span class="write_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
			</c:if>
			<a class="grey-btn close" href="javascript:closeModal();" id="btn_cancel" title="<spring:message code="LAB.M03.LAB00027"/>">
				<span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span>
			</a>
		</div>
	 </div>

	
</form>
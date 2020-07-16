<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	initGrid();	//jqgrid ȣ��
});

function getParam(){
	var param = new Object();	
	param.srchNm = $("#srchNm").val();  
	return param;
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}
function insertOk(){
	
	  var ids=$("#attributePop").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
	  var tmp="";
	  var tmp2="";
	  
	  for(var i=0; i<ids.length; i++){
			var rowObject = $("#attributePop").getRowData(ids[i]);					
			var valueId = rowObject.soId;														
			
			tmp +=valueId+",";
		}
	  	tmp=tmp.slice(0,-1);
		goInsertAuth(tmp);
}

function initGrid() {
	
	var param = new Object();
	
	$("#attributePop").jqGrid({

	   	url:'/system/common/common/soSearchMng/soSearchListAction.json?'+ $.param(getParam()),
	    mtype:"POST",
	   	datatype: "json",
	   	colNames:[ 
			'<spring:message code="LAB.M07.LAB00010" />',
			'<spring:message code="LAB.M07.LAB00013" />'
		],
	   	colModel:[
	   		{name:'soId',index:'soId',width : 50, key:true},
	   		{name:'soNm',index:'soNm',width : 100}
	   	],
	  	multiselect: true,
	  	viewrecords: true,
		height: 250,
		width: 550, 
		sortable: { update: function(permutation) {
    		$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		rowNum: -1,
		jsonReader: {
			repeatitems : true,
			root : "soSearchList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		loadComplete: function(data){
			$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			var rowid=$("#tmpSoId").val().split(",");
			
			if($("#tmpSoId").val() == ''){
				return;
			}
			
			for(var i=0; i < rowid.length;i++){
				var soId = rowid[i];
				
				var index = $("#attributePop").getRowData(soId);
				$('input:checkbox[id="jqg_attributePop_' + index.soId +'"]').attr("checked", true);
				$('#attributePop').jqGrid('setSelection', index.soId).attr('checkbox', true);
			}
		
		},
		ondblClickRow : function(id){
		},
    	sortable: { update: function(permutation) {
    		$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	//$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$(window).resize(function() {
		$("#attributePop").setGridWidth($('#attributePop').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

function goInsertAuth(tmp){
	var param = new Object();
	var check=tmp.split(",");
	var flag="N";
	var nm=$("#attributePop").getRowData($("#baseId").val()).soNm;
	for(var i=0;i<check.length;i++){	//기본사업 존재 유무 확인
		if(check[i]==$("#baseId").val()){
			flag="Y";
		}
	}
	
	if(flag=="N"){
		alert(nm+'<spring:message code="MSG.M02.MSG00012"/>');
		return;
	}
	
	param.userId=$("#userId").val();
	param.soId = tmp;

	 var url = '/system/auth/authMng/soAuthMng/insertAction.json';
		$.ajax({
			type : "post",
			url : url,
			data : param,
			async : true,
			success : function(data) {
				$("#soAuthGrid").trigger("reloadGrid"); 
				alert('<spring:message code="MSG.M09.MSG00005"/>');
				$('#mask, .Layer_wrap').hide();
			},
			complete : function() {
			}
		}); 
}
</Script>
<input type="hidden" id="tmpSoId" name="tmpSoId" value="${soId}"/><!--해당 user_ID에 해당하는 so권한  -->
<input type="hidden" id="userId" name="userId" value="${userId}"/><!--USER_ID-->
<input type="hidden" id="baseId" name="baseId" value="${baseId}"/><!--해당 user에 해당하는 기본 SO_ID  -->
<div class="layer_top">
	<div class="title">
		<spring:message code="LAB.M07.LAB00005" />
	</div>
	<a href="#" class="close"></a>
</div>

<div class="layer_box">
	<table id="attributePop" class="w100p"></table>
	<div id="jqGridPager"></div>
	<div class="btn_box">
		<c:if test="${bulletin.multiFlag!='N'}">
		<a class="grey-btn" id="btn_ok" href="javascript:insertOk();"title="ok"><span class="write_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
		</c:if>
		<a class="grey-btn close" href="javascript:closeModal();" id="btn_cancel" title="<spring:message code="LAB.M03.LAB00027"/>">
			<span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span>
		</a>
	</div>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	
	initGrid();
	
	$('#addDiscExclInitPopupBtnInsert').click(function() {
		insertData();
	});		
	
});	

$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});	

function insertData(){
	var params = new Array();  
	var message = "";
    var id = $("#addDiscExclInitPopupGrid").getGridParam('selarrrow');
    var ids = $("#addDiscExclInitPopupGrid").jqGrid('getDataIDs');
    
	if (id == ''){
		alert('<spring:message code="MSG.M07.MSG00071" />');
		return;
	}        

    for (var i = 0; i < ids.length; i++) {
    	var rowdata = $("#addDiscExclInitPopupGrid").getRowData(ids[i]);
     	$("#addDiscExclInitPopupGrid").setCell(ids[i], "rateItmCd", $("#addDiscExclInitPopupRateItmCd").val());  
    }

    for (var i = 0; i < ids.length; i++) {
        var check = false;
        $.each(id, function (index, value) {
            if (value == ids[i])
                check = true;
        });

        if (check) {
            var rowdata = $("#addDiscExclInitPopupGrid").getRowData(ids[i]);        
            params.push(rowdata);
        }
    }
	
    var tempParam = JSON.stringify(params);   
    
	$.ajax({
		url : 'addDiscExcl.json',
		type : 'POST',
		async: false,
		data :  tempParam,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			
			if(data.result != "0" && data.result != "-1"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				$("#discExclListGrid").trigger("reloadGrid");
				$("#addDiscExclInitPopupBtnClose").trigger('click');
			} 
		},
	    error: function(request,status,error){
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    },
		complete : function() {
		}
	});	

}


function initGrid() {
	var param = new Object();
	
	param.rateItmCd = $("#addDiscExclInitPopupRateItmCd").val();
	
	$("#addDiscExclInitPopupGrid").jqGrid("GridUnload"); 
  	$("#addDiscExclInitPopupGrid").jqGrid({
		url:'getAddDiscExclInit.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M05.LAB00033" />',name:'mainSvcNm', width:80},
			{label:'<spring:message code="LAB.M07.LAB00185" />',name:'svcNm', width:80},
			{label:'<spring:message code="LAB.M07.LAB00177" />',name:'svcRateItmTypNm', width:150},
			{name:'svcRateItmTypCd', hidden:true},
			{name:'rateItmCd', hidden:true}
	   	],
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		multiselect: true,
		loadComplete : function () {
			$("#addDiscExclInitPopupGrid").setGridWidth($('#addDiscExclInitPopupGridDiv').width(),false);
		}
	});
  $("#addDiscExclInitPopupGrid").setGridWidth($('#addDiscExclInitPopupGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
}

$(window).resize(function() {
	$("#addDiscExclInitPopupGrid").setGridWidth($('#addDiscExclInitPopupGridDiv').width(),false);
});

</script>
	
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M14.LAB00030"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h3 class="ly_title"><spring:message code="LAB.M07.LAB00170"/></h3>
		</div>
	</div>

	<div class="layer_box">
		<table id="addDiscExclInitPopupGrid" class="w100p"></table>
		<div id="addDiscExclInitPopupGridDiv"></div>
	</div>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="addDiscExclInitPopupBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="addDiscExclInitPopupBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<input id="addDiscExclInitPopupRateItmCd" type='text' value="${productDevMgt.rateItmCd}"  hidden />

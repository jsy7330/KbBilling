<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
.ui-jqgrid tr.jqgfirstrow td {padding: 0 2px 0 2px;border-right-width: 1px; border-right-style: solid;} 
  	 
</style>
<script type="text/javascript">
$(document).ready(function() {
	$(".search").css("margin-top", "3px");
	
	 
	excuteGridRetrieveRateItemFactor($("#rateItmFctrListRetrieveRateItmCd").val(), $("#rateItmFctrListRetrieveFctrNm").val());
	
	$('#rateItmFctrListRetrieveFctrSearch').click(function() {	
		excuteGridRetrieveRateItemFactor($("#rateItmFctrListRetrieveRateItmCd").val(), $("#rateItmFctrListRetrieveFctrNm").val());
	});	

	
	$('#rateItmFctrListRetrieveBtnInsert').click(function() {
		var params = new Array();  
		var message = "";
        var id = $("#rateItmFctrListRetrieveGrid").getGridParam('selarrrow');
        var ids = $("#rateItmFctrListRetrieveGrid").jqGrid('getDataIDs');
        
		if (id == ''){
			alert('<spring:message code="MSG.M08.MSG00025" />');
			return;
		}        

        for (var i = 0; i < ids.length; i++) {
        	var rowdata = $("#rateItmFctrListRetrieveGrid").getRowData(ids[i]);
        	$("#rateItmFctrListRetrieveGrid").setCell(ids[i], "rateItmCd", $("#rateItmFctrListRetrieveRateItmCd").val() );  
        }

        for (var i = 0; i < ids.length; i++) {
            var check = false;
            $.each(id, function (index, value) {
                if (value == ids[i])
                    check = true;
            });

            if (check) {
                var rowdata = $("#rateItmFctrListRetrieveGrid").getRowData(ids[i]);
                params.push(rowdata);
            }
        }

        var tempParam = JSON.stringify(params);   
        
		$.ajax({
			url : 'addRetrieveRateItemFactor_2.json',
			type : 'POST',
			async: false,
			data :  tempParam,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					$("#rateItmFctrListGrid").trigger("reloadGrid");
					$("#rateItmFctrListRetrieveBtnClose").trigger('click');
				} else if (data.result == "-1") {
					alert('<spring:message code="MSG.M09.MSG00051" />');
					
				}  else if (data.result == "-2") {
					alert('<spring:message code="MSG.M14.MSG00014" />');
					
				}
			},
		    error: function(request,status,error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    },
			complete : function() {
			}
		});
        
       
	});	 
	
});

function excuteGridRetrieveRateItemFactor(rateItmCd, fctrNm) {
	var param = new Object();
	param.rateItmCd = rateItmCd;
	
	if(fctrNm != ''){
		param.fctrNm = fctrNm;
	}	
	
	$("#rateItmFctrListRetrieveGrid").jqGrid("GridUnload"); 
	$("#rateItmFctrListRetrieveGrid").jqGrid({
		url:'getRetrieveRateItemFactor.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[     
			{label:'<spring:message code="LAB.M08.LAB00068" />',name:'fctrCd', width:90, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00064" />',name:'fctrNm', width:230},
	  		{name:'rateItmCd',hidden:true}
	   	],
	   	shrinkToFit:false,
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
			$("#rateItmFctrListRetrieveGrid").setGridWidth($('#rateItmFctrListRetrieveGridDiv').width(),false);
		}
	});
	$("#rateItmFctrListRetrieveGrid").setGridWidth($('#rateItmFctrListRetrieveGridDiv').width(),false);
}

$(window).resize(function() {
	$("#rateItmFctrListRetrieveGrid").setGridWidth($('#rateItmFctrListRetrieveGridDiv').width(),false);
});
</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00180"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M08.LAB00066"/></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00064"/><!-- 변경/종료 구분 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="rateItmFctrListRetrieveFctrNm" name="rateItmFctrListRetrieveFctrNm" class="w270">
					<a href="#" id="rateItmFctrListRetrieveFctrSearch" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>
				</td>	
			</tr>
		</thead>
	</table>
	<div class="layer_box">
		<table id="rateItmFctrListRetrieveGrid" class="w270"></table>
		<div id="rateItmFctrListRetrieveGridDiv"></div>
	</div>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="rateItmFctrListRetrieveBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="rateItmFctrListRetrieveBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<input id="rateItmFctrListRetrieveRateItmCd" type='text' value="${productDevMgt.rateItmCd}"  hidden />
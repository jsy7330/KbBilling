<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "1"); //시스템 날짜 출력
	}
	
	excuteGridfctrListPopup($("#fctrListPopupSvcRateItmTypCd").val(), $("#fctrNm").val());
	
	$('#fctrSearch').click(function() {	
		excuteGridfctrListPopup($("#fctrListPopupSvcRateItmTypCd").val(), $("#fctrNm").val());
	});
	
	$('#btnInsert').click(function() {
		var params = new Array();  
		var message = "";
        var id = $("#fctrListPopupGrid").getGridParam('selarrrow');
        var ids = $("#fctrListPopupGrid").jqGrid('getDataIDs');
        
		if (id == null){
			alert('<spring:message code="MSG.M07.MSG00101" />');
			return;
		}        
        
        for (var i = 0; i < ids.length; i++) {
        	var rowdata = $("#fctrListPopupGrid").getRowData(ids[i]);
        	$("#fctrListPopupGrid").setCell(ids[i], "svcRateItmTypCd", $("#fctrListPopupSvcRateItmTypCd").val() );  
        	$("#fctrListPopupGrid").setCell(ids[i], "actDt", dateFormatToStringYYYYMMDD($("#actDt").val()) );  
        }
        
        for (var i = 0; i < ids.length; i++) {
            var check = false;
            $.each(id, function (index, value) {
                if (value == ids[i])
                    check = true;
            });

            if (check) {
                var rowdata = $("#fctrListPopupGrid").getRowData(ids[i]);
                params.push(rowdata);
                
            }
        }
        
        var tempParam = JSON.stringify(params);   
        
		$.ajax({
			url : 'addSvcRateItmTypFctr.json',
			type : 'POST',
			async: false,
			data :  tempParam,
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					$("#serviceMgtSvcRateItmTypFctrListGrid").trigger("reloadGrid");
					$("#btnClose").trigger('click');
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

function excuteGridfctrListPopup(targetCd, fctrNm) {
	var param = new Object();
	param.svcRateItmTypCd = targetCd;
	
	if(fctrNm != ''){
		param.fctrNm = fctrNm;
	}

	
	$("#fctrListPopupGrid").jqGrid("GridUnload"); 
	$("#fctrListPopupGrid").jqGrid({
		url:'getTotalFactorListMergedeSvc.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M08.LAB00068" />',name:'fctrCd', width:90, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00064" />',name:'fctrNm', width:220},
			{name:'fctrRefTyp',hidden:true},
			{name:'tableId',hidden:true},
			{name:'colmnId',hidden:true},
			{name:'dataTyp',hidden:true},
			{name:'refCd',hidden:true},
			{name:'refFunc',hidden:true},
			{name:'chgNullFl',hidden:true},
			{name:'chgNullVal',hidden:true},
			{name:'svcRateItmTypCd',hidden:true},
			{name:'actDt',hidden:true}
	   	],
	   	shrinkToFit:false,
	   	rowNum:50,		//한번에 노출되는 row 수
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
		loadComplete : function () {
  	      	$("#fctrListPopupGrid").setGridWidth($('#fctrListPopupGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	$("#fctrListPopupGrid_cb").css("width", "29px");
        },
        multiselect: true

	});
	
	$("#fctrListPopupGrid").setGridWidth($('#fctrListPopupGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#fctrListPopupGrid_cb").css("width", "29px");
}


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
					<input type="text" id="fctrNm" name="fctrNm" class="w270">
					<a href="#" id="fctrSearch" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>
				</td>	
			</tr>
			<tr>
				<td colspan="2">
					<div id='fctrListPopupGridDiv'>
						<table id="fctrListPopupGrid" class="w100p"></table>
					</div>				
				</td>
			</tr>


			<tr>	
				<th>
					<spring:message code="LAB.M09.LAB00052"/><!-- 적용일자 --><span class="dot">*</span>
				</th>
				<td>
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="actDt" name="actDt" class="datepicker" readonly="readonly" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>	 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<input id="fctrListPopupSvcRateItmTypCd" type='text' value="${serviceMgt.svcRateItmTypCd}"  hidden />
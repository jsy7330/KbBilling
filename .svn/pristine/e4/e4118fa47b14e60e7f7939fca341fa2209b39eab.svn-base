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

	getProdConfReqDtlList();
	
	$("#procGbNm").addClass('not-active'); 
	$("#procGbNm").attr('disabled', true);	
	
	$('#btnInsert').click(function() {
		var url="getConfUsrIdListPopUp.ajax";
		$(parent.location).attr("href", "javascript:openModal2('" + url + "');");
		
	});		
	
	
	$('#btnDelete').click(function() {
		var returnCon = confirm('<spring:message code="MSG.M07.MSG00054" />');
		
		if(returnCon){
			
	
			var params = new Array();  
			var message = "";
		    var id = $("#prodConfReqDtlListGrid").getGridParam('selarrrow');
		    var ids = $("#prodConfReqDtlListGrid").jqGrid('getDataIDs');
		    
			if (id == ''){
				alert('<spring:message code="MSG.M03.MSG00016" />');
				return;
			}        
	
		    for (var i = 0; i < ids.length; i++) {
		    	var rowdata = $("#prodConfReqDtlListGrid").getRowData(ids[i]);
		    	//$("#prodConfReqDtlListGrid").setCell(ids[i], "confReqCd", $("#confReqNo" + i).val() ); 
		    }
	
		    for (var i = 0; i < ids.length; i++) {
		        var check = false;
		        $.each(id, function (index, value) {
		            if (value == ids[i])
		                check = true;
		        });
	
		        if (check) {
		            var rowdata = $("#prodConfReqDtlListGrid").getRowData(ids[i]);
		            params.push(rowdata);
		        }
		    }
			
		    var tempParam = JSON.stringify(params);   	
			
			$.ajax({
				url : 'delProdConfDtl.json',
				type : 'POST',
				async: false,
				data :  tempParam,
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success : function(data) {
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M07.MSG00053" />');
						$("#prodConfReqDtlListGrid").trigger("reloadGrid");
						$("#prodDvlpListGrid").trigger("reloadGrid");
						//$("#btnClose").trigger('click');
					} else if (data.result == "-1") {
						alert('<spring:message code="MSG.M09.MSG00051" />');
					}  
				},
			    error: function(request,status,error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    },
				complete : function() {
				}
			});	
		
		}	
	});		
	
	$("#prodConfReqDtlListBtnConfReq").click(function() {
		var checkedVal = false;
	    $('input:radio').each(function () {
		     if($(this).prop('checked')){
		    	 checkedVal = true;
		      }
		 });
	    
	    if (!checkedVal) {
			alert('<spring:message code="LAB.M06.LAB00056" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
	    }
	    
		var returnCon = confirm('<spring:message code="MSG.M09.MSG00008" />');
		
		if(returnCon){
			updateData();
		}
	});
	
	
});	

$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});	

function getProdConfReqDtlList() {
	var param = new Object();
	param.prodCd = $("#prodConfReqDtlListProdCd").val();
	param.prodDvlpDttm = $("#prodConfReqDtlListProdDvlpDttm").val();
	
  	$("#prodConfReqDtlListGrid").jqGrid({
		url:'getProdConfReqDtlList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M06.LAB00090" />',name:'orgNm', width:430},      
			{label:'<spring:message code="LAB.M07.LAB00207" />',name:'userName', width:300, align:"center"},
			{name:'prodCd', hidden:true},
			{name:'prodDvlpDttm', hidden:true},
			{name:'modDesc', hidden:true},
			{name:'modTyp', hidden:true},
			{name:'confReqCd', hidden:true},
			{name:'confrCd', hidden:true}
	   	],
	   	shrinkToFit:false,
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
  	      	$("#prodConfReqDtlListGrid").setGridWidth($('#prodConfReqDtlListGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	//$("#productRelComboListGrid_cb").css("width", "29px");
        } 
	});   	
}


function updateData(){
		
	var params = new Array();  
	var message = "";
    var id = $("#prodConfReqDtlListGrid").getGridParam('selarrrow');
    var ids = $("#prodConfReqDtlListGrid").jqGrid('getDataIDs');
    
	if (id == ''){
		alert('<spring:message code="MSG.M03.MSG00016" />');
		return;
	}        

    for (var i = 0; i < ids.length; i++) {
    	var rowdata = $("#prodConfReqDtlListGrid").getRowData(ids[i]);
    	$("#prodConfReqDtlListGrid").setCell(ids[i], "prodCd", $("#prodConfReqDtlListProdCd").val() );  
    	$("#prodConfReqDtlListGrid").setCell(ids[i], "prodDvlpDttm", $("#prodConfReqDtlListProdDvlpDttm").val() ); 
    	$("#prodConfReqDtlListGrid").setCell(ids[i], "modDesc", $("#modDesc").val() ); 
    	$("#prodConfReqDtlListGrid").setCell(ids[i], "modTyp", $("#prodConfReqDtlListModTyp").val() ); 
    	//$("#prodConfReqDtlListGrid").setCell(ids[i], "confReqCd", $("#confReqNo" + i).val() ); 
    }

    for (var i = 0; i < ids.length; i++) {
        var check = false;
        $.each(id, function (index, value) {
            if (value == ids[i])
                check = true;
        });

        if (check) {
            var rowdata = $("#prodConfReqDtlListGrid").getRowData(ids[i]);
            params.push(rowdata);
        }
    }
	
    var tempParam = JSON.stringify(params);   	
	
	$.ajax({
		url : 'modProdConfReqProc.json',
		type : 'POST',
		async: false,
		data :  tempParam,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			
			if(data.result != "0" && data.result != "-1"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				$("#prodDvlpListGrid").trigger("reloadGrid");
				$("#btnClose").trigger('click');
			} else if (data.result == "-1") {
				alert('<spring:message code="MSG.M09.MSG00051" />');
			}  
		},
	    error: function(request,status,error){
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    },
		complete : function() {
		}
	});	
}
</script>
	
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00269"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h3 class="ly_title"><spring:message code="LAB.M06.LAB00057"/><!-- 등록 --></h3>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:100%;">
		</colgroup>
		<thead>
			<tr>
				<td>
	       			<textarea id="modDesc" name="modDesc" class="w100p h100"></textarea>                                           
	     		</td>			
			</tr>						 		
		</thead>
	</table>
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00012"/><!-- 개발구분 -->
				</th>			
				<td>
	       			<input type="text" id="procGbNm" name="procGbNm" value="${productDevMgt.dvlpTypNm}" class="w200">                                          
	     		</td>			
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M06.LAB00056"/><!-- 변경구분 --><span class="dot">*</span>
				</th>			
				<td>
					<div class="date_box">
						<c:forEach items="${radioGubun}" var="radioGubun" varStatus="status">
								<input type="radio" id="rdoGubun${status.index}" name="rdoGubun" value="${radioGubun.commonCd}" <c:if test="${productDevMgt.dvlpTyp eq radioGubun.commonCd}"> checked="checked" </c:if>/>
								<label for="rdoGubun${status.index}">${radioGubun.commonCdNm}</label>
						</c:forEach>
					</div>				                                        
	     		</td>			
			</tr>									 		
		</thead>
	</table>
	<div class="ly_btn_box">
		<div class="fl">
			<h3 class="ly_title"><spring:message code="LAB.M07.LAB00270"/></h3>
		</div>
	<div class="fr">	
		<a id="prodConfReqDtlListBtnConfReq" class="grey-btn" title='<spring:message code="LAB.M07.LAB00272"/>' href="#"><span><spring:message code="LAB.M07.LAB00272"/></span></a>
	</div>			
	</div>

	<div class="layer_box">
		<table id="prodConfReqDtlListGrid" class="w100p"></table>
		<div id="prodConfReqDtlListGridDiv"></div>
	</div>
</div>
<!--// center -->
<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M10.LAB00059" /></span></a>
	<a class="grey-btn" href="#" id="btnDelete"><span class="cancel_icon"><spring:message code="LAB.M07.LAB00082" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<c:forEach items="${confReqCdList}" var="confReqCdList" varStatus="status">
		<input id="confReqNo${status.index}" type='text' value="${confReqCdList.confReqCd}"  hidden />
</c:forEach>

<input id="prodConfReqDtlListProdCd" type='text' value="${productDevMgt.prodCd}"  hidden />
<input id="prodConfReqDtlListProdDvlpDttm" type='text' value="${productDevMgt.prodDvlpDttm}"  hidden />
<input id="prodConfReqDtlListModTyp" type='text' value="${productDevMgt.modTyp}"  hidden />

<div id="popup_dialog2" class="Layer_wrap2" style="display: none;width:600px;" >
</div>

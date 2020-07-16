<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
table.ui-datepicker-calendar { display:none; } 
</style>
<script type="text/javascript">

$(document).ready(function() {
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng,
		      dateFormat:"yy-mm",
		      showButtonPanel: true,
		      closeText: "선택",
		      currentText: "이번달",
		      onClose : function (dateText, inst) {
		          var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		          var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		          $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
		          $(this).datepicker('setDate', new Date(year, month, 1));
		      }
		      
		    }).datepicker("setDate", "1"); //시스템 날짜 출력
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );

	$('#productCancelBtnInsert').click(function() {
		var inactDtTmp = $.trim( $("#inactDt").val() );
		var inactDttmp1;
		inactDtTmp = inactDtTmp + "-31";
		var stDate = new Date();
		inactDttmp1 = new Date(inactDtTmp);
		inactDtTmp = inactDtTmp.replace(/\-/g,'');
		if (stDate > inactDttmp1){
			alert('<spring:message code="MSG.M09.MSG00012" />');
			return;
		}

		if ($("#productRelProdCase").val() == 1) {
			insertData1(inactDtTmp);
		} else if ($("#productRelProdCase").val() == 2) {
			insertData2(inactDtTmp);
		}

	});		
		
});	


function insertData1(inactDtTmp){
	var params = new Array();  
	var message = "";
    var id = $("#productRelDefresGrid").getGridParam('selarrrow');
    var ids = $("#productRelDefresGrid").jqGrid('getDataIDs');
    
	if (id == ''){
		alert('<spring:message code="MSG.M08.MSG00036" />');
		return;
	}        

    for (var i = 0; i < ids.length; i++) {
    	var rowdata = $("#productRelDefresGrid").getRowData(ids[i]);
    	$("#productRelDefresGrid").setCell(ids[i], "prodCase", $("#productRelProdCase").val());
    	$("#productRelDefresGrid").setCell(ids[i], "inactDtChg", inactDtTmp);
    }

    for (var i = 0; i < ids.length; i++) {
        var check = false;
        $.each(id, function (index, value) {
            if (value == ids[i])
                check = true;
        });

        if (check) {
            var rowdata = $("#productRelDefresGrid").getRowData(ids[i]);
            params.push(rowdata);
        }
    }
	
    var tempParam = JSON.stringify(params);   
    
	$.ajax({
		url : 'setProductCancel.json',
		type : 'POST',
		async: false,
		data :  tempParam,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			
			if(data.result != "0" && data.result != "-1"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				$("#productRelDefresGrid").trigger("reloadGrid");
				$("#productCancelBtnClose").trigger('click');
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

function insertData2(inactDtTmp){
	var params = new Array();  
	var message = "";
    var id = $("#productRelDefreqGrid").getGridParam('selarrrow');
    var ids = $("#productRelDefreqGrid").jqGrid('getDataIDs');
    
	if (id == ''){
		alert('<spring:message code="MSG.M08.MSG00036" />');
		return;
	}        

    for (var i = 0; i < ids.length; i++) {
    	var rowdata = $("#productRelDefreqGrid").getRowData(ids[i]);
    	$("#productRelDefreqGrid").setCell(ids[i], "prodCase", $("#productRelProdCase").val());
    	$("#productRelDefreqGrid").setCell(ids[i], "inactDtChg", inactDtTmp);
    }

    for (var i = 0; i < ids.length; i++) {
        var check = false;
        $.each(id, function (index, value) {
            if (value == ids[i])
                check = true;
        });

        if (check) {
            var rowdata = $("#productRelDefreqGrid").getRowData(ids[i]);
            params.push(rowdata);
        }
    }
	
    var tempParam = JSON.stringify(params);   
    
	$.ajax({
		url : 'setProductCancel.json',
		type : 'POST',
		async: false,
		data :  tempParam,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			
			if(data.result != "0" && data.result != "-1"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				$("#productRelDefreqGrid").trigger("reloadGrid");
				$("#productCancelBtnClose").trigger('click');
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
	<div class="title"><spring:message code="LAB.M07.LAB00123"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h3 class="ly_title"><spring:message code="LAB.M08.LAB00088"/></h3>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:50%;">
			<col style="width:50%;">
		</colgroup>
		<thead>
			<tr>	
				<th>
					<spring:message code="LAB.M09.LAB00057"/><!-- 적용일자 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="inactDt" name="inactDt" class="datepicker" readonly="readonly" />
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
	<a class="blue-btn" href="#" id="productCancelBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="productCancelBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>


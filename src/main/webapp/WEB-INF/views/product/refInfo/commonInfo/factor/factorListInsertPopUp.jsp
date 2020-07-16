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
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );

	$(".search").css("margin-top", "3px");
	
	$("#factorListPopupFctrRefTyp, #factorListPopupTableId, #factorListPopupColmnId, #factorListPopupDataTyp").selectmenu()
	.selectmenu( "option", "width", "100%" )
	.selectmenu("disable");
	
	$("#factorListPopupNullFm, #factorListPopupCommonGrpNm, #factorListPopupCommonGrp, #factorListPopupRefFunc").addClass('not-active');
	$("#factorListPopupNullFm, #factorListPopupCommonGrpNm, #factorListPopupCommonGrp, #factorListPopupRefFunc").attr('disabled', true);	
	
	$("input[name=radioChgNullFl]").attr("disabled", true);  
		
	$("#btnInsert").click(function() {
		insertData();            
	});		
	
	$('#fctrSearch').click(function() {	
		var fctrNm = $.trim($('#factorListPopupFctrNm').val());
		if(fctrNm == ''){
			alert('<spring:message code="LAB.M08.LAB00063" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}

		$.ajax({
			url : 'fctrNameCheck.json',
			type : 'POST',
			async: false,
			data :  {"fctrNm" :fctrNm},
			success : function(data) {
				if (data.result > 0) {
					alert('<spring:message code="MSG.M09.MSG00056" />');
					$('#factorListPopupFctrNm').val("");
				} else if (data.result == 0) {
					alert('<spring:message code="MSG.M09.MSG00053" />');
					$("input[name=radioChgNullFl]").attr("disabled", false);
					$("#factorListPopupNullFm").removeClass('not-active');
					$("#factorListPopupNullFm").removeAttr('disabled');
					$("#factorListPopupFctrRefTyp").selectmenu("enable");
					
					getCommonSelbox("#factorListPopupFctrRefTyp", "PP00012");
					
				}
			},
		    error: function(request,status,error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    },
			complete : function() {
				
			}
		});		
		$("#factorListPopupFctrRefTyp").val("").selectmenu('refresh');	
		//$('#factorListPopupTableId').val("").selectmenu('refresh');	
		$("#factorListPopupTableId").selectmenu("disable");
		//$('#factorListPopupColmnId').val("").selectmenu('refresh');	
		$("#factorListPopupColmnId").selectmenu("disable");
		//$('#factorListPopupDataTyp').val("").selectmenu('refresh');	
		$("#factorListPopupDataTyp").selectmenu("disable");		
		$("#factorListPopupRefFunc").val('');
		$("#factorListPopupRefFunc").addClass('not-active');
		$("#factorListPopupRefFunc").attr('disabled', true);
	});	

	$('#factorListPopupCommonGrpSearch').click(function() {
		if ($("#factorListPopupDataTyp").val() == "4" ) {
			var url="getCommonGrpPopupListPopUp.ajax";
			$(parent.location).attr("href", "javascript:openModal2('" + url + "');");
		}
		
	});
	
	$("#factorListPopupDataTyp").selectmenu({
		change: function( event, ui ) {
			if ($(this).val() == "6" || $(this).val() == "4" ) {
					$("#factorListPopupRefFunc").removeClass('not-active');
					$("#factorListPopupRefFunc").removeAttr('disabled');
			}
		}
	});

	$("#factorListPopupFctrRefTyp").selectmenu({
		change: function( event, ui ) {
			if ($(this).val() == "T" ) {
				$("#factorListPopupTableId").selectmenu("enable");
				$("#factorListPopupDataTyp").selectmenu("enable");
				
				getCommonSelbox("#factorListPopupTableId", "PP00013");
				getCommonSelbox("#factorListPopupDataTyp", "PP00024");
				
			} else if ($(this).val() == "F" ) {	
				
				$("#factorListPopupDataTyp").selectmenu("enable");				
				getCommonSelbox("#factorListPopupDataTyp", "PP00024");
				
				$('#factorListPopupTableId').html("<option value=''></option>");
				$('#factorListPopupTableId').val("").selectmenu('refresh');	
				$("#factorListPopupTableId").selectmenu("disable");

				$('#factorListPopupColmnId').html("<option value=''></option>");
				$('#factorListPopupColmnId').val("").selectmenu('refresh');	
				$("#factorListPopupColmnId").selectmenu("disable");				
				


			} else {
				$('#factorListPopupTableId').val("").selectmenu('refresh');	
				$("#factorListPopupTableId").selectmenu("disable");
				
				$('#factorListPopupDataTyp').val("").selectmenu('refresh');	
				$("#factorListPopupDataTyp").selectmenu("disable");
				
				$('#factorListPopupColmnId').val("").selectmenu('refresh');	
				$("#factorListPopupColmnId").selectmenu("disable");
				
				$("#factorListPopupRefFunc").addClass('not-active'); 
				$("#factorListPopupRefFunc").attr('disabled', true);
			}
		}
	});
	
	$("#factorListPopupTableId").selectmenu({
		change: function( event, ui ) {
			$("#factorListPopupColmnId").selectmenu("enable");
			var param = new Object();

			param.tableName = $(this).val();
			$.ajax({
				url : 'getRefColmnIdComboList.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					var options = [];
					
					$('#factorListPopupColmnId').find('option').remove(); 
					
					$.each(data.refComboList, function(i, item) {
						options.push('<option value="' + data.refComboList[i].columnName + '">' + data.refComboList[i].columnName + '</option>');
					});
					
				    $('#factorListPopupColmnId')
			        .append(options.join(""))
			        .selectmenu();
				   
				    $('#factorListPopupColmnId').val("[]").selectmenu('refresh');		
				},
			    error: function(request,status,error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    },
				complete : function() {
				}
			});			
			
		}
	});
});

function getCommonSelbox(selboxId, commonCd){
	$.ajax({
		url : 'getCommonSelbox.json',
		type : 'POST',
		async: false,
		data : {"commonCd" : commonCd},
		success : function(data) {
			var options = [];
			
			$(selboxId).find('option').remove(); 
			$.each(data.commonData, function(i, item) {

				if(i == 0) {
					options.push('<option value="">선택</option>');
				}
				options.push('<option value="' + data.commonData[i].commonCd + '">' + data.commonData[i].commonCdNm + '</option>');
			});
			
		    $(selboxId)
	        .append(options.join(""))
	        .selectmenu();
		   
		    $(selboxId).val("").selectmenu('refresh');		
			
		},
	    error: function(request,status,error){
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    },
		complete : function() {
		}
	});		
}

function insertData(){
	
	var param = checkValidation();
	
	if(param != false){
		
		$.ajax({
			url : 'addTotalFactor.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					
					$("#btnClose").trigger('click');
				} else if (data.result == "-1") {
					alert('<spring:message code="MSG.M09.MSG00051" />');
					
				}
			},
		    error: function(request,status,error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    },
			complete : function() {
				$(parent.location).attr("href", "javascript:factorGrid();");
			}
		});
	}
}

function checkValidation(){
	var param = new Object();
	
	var fctrNm = $("#factorListPopupFctrNm").val();	//요소명
	if(fctrNm == ''){
		alert('<spring:message code="LAB.M01.LAB00127" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}	
	
	var fctrRefTyp = $.trim( $("#factorListPopupFctrRefTyp").val() );	//참조유형
	if(fctrRefTyp == ''){
		alert('<spring:message code="LAB.M10.LAB00010" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var actDt = $("#factorListPopupActDt").val();	//적용시작일
	
	if(actDt == ''){
		alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}	
	
	if ($("#factorListPopupFctrRefTyp").val()  == "T" ) {
		var tableId = $.trim( $("#factorListPopupTableId").val() );	//테이블명
		if(tableId == ''){
			alert('<spring:message code="LAB.M12.LAB00001" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}	
		
		var colmnId = $.trim( $("#factorListPopupColmnId").val() );	//컬럼명
		if(colmnId == '[]'){
			alert('<spring:message code="LAB.M11.LAB00004" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}			
		
		var dataTyp = $.trim( $("#factorListPopupDataTyp").val() );	//컬럼명
		if(dataTyp == ''){
			alert('<spring:message code="LAB.M03.LAB00069" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}			
	}
	
	if ($("#factorListPopupFctrRefTyp").val()  == "F" ) {	
/*		
		var refFunc = $("#factorListPopupRefFunc").val();	//함수참조
		if(refFunc == ''){
			alert('<spring:message code="LAB.M14.LAB00039" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
	*/	
		var dataTyp = $.trim( $("#factorListPopupDataTyp").val() );	//컬럼명
		if(dataTyp == ''){
			alert('<spring:message code="LAB.M03.LAB00069" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}		
		
	}
	
	var fctrNm = $("#factorListPopupFctrNm").val();
	var refCd = $("#factorListPopupCommonGrp").val();	
	var refFunc = $("#factorListPopupRefFunc").val();
	var chgNullFl = $(':radio[name="radioChgNullFl"]:checked').val();
	var chgNullVal = $('#factorListPopupNullFm').val();
	
	param.fctrRefTyp = fctrRefTyp;
	param.actDt = dateFormatToStringYYYYMMDD(actDt);
	param.fctrNm = fctrNm;
	param.chgNullFl = chgNullFl;
	param.chgNullVal = chgNullVal;	

	if ($("#factorListPopupFctrRefTyp").val()  == "T" ) {
		param.tableId = tableId;
		param.colmnId = colmnId;
		param.dataTyp = dataTyp;
		param.refCd = refCd;
		param.refFunc = refFunc;
	}
	
	if ($("#factorListPopupFctrRefTyp").val()  == "F" ) {
		param.refFunc = refFunc;
		param.dataTyp = dataTyp;
		param.refCd = refCd;
	}

	return param;
	
}


function openModal1(url, param) {
	
	$.ajax({
		type : "post",
		url : url,
		data : param,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog_Sub").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
		}
	}); 
}



</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M08.LAB00067"/></div>
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
					<spring:message code="LAB.M01.LAB00127"/><!-- 요소등록 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="factorListPopupFctrNm" name="factorListPopupFctrNm" class="w270">
					<a href="#" id="fctrSearch" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>
				</td>	
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00010"/><!-- 참조유형 --><span class="dot">*</span>
				</th>
				<td>
					<select id ="factorListPopupFctrRefTyp" name="factorListPopupFctrRefTyp" class="w100p">
					</select>                                                                                   
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M12.LAB00001"/><!-- 테이블명 --><span class="dot">*</span>
				</th>
				<td>
					<select id ="factorListPopupTableId" name="factorListPopupTableId" class="w100p">
						<option value=""></option>
					</select>                                                                                   
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M11.LAB00004"/><!-- 컬럼명 --><span class="dot">*</span>
				</th>
				<td>
					<select id ="factorListPopupColmnId" name="factorListPopupColmnId" class="w100p">
						<option value=""></option>
					</select>                                                                                   
				</td>
			</tr>						
			<tr>
				<th>
					<spring:message code="LAB.M03.LAB00069"/><!-- 데이터유형 --><span class="dot">*</span>
				</th>
				<td>
					<select id ="factorListPopupDataTyp" name="factorListPopupDataTyp" class="w100p">
					</select>                                                                                   
				</td>
			</tr>	 
			<tr>
				<th>
					<spring:message code="LAB.M14.LAB00039"/><!-- 함수참조 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="factorListPopupRefFunc" name="factorListPopupRefFunc" class="w100p">                                                                                 
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00013"/><!-- 참조 코드 -->
				</th>
				<td>
					<input type="text" id="factorListPopupCommonGrpNm" name="factorListPopupCommonGrpNm" class="w100">
					<a href="#" id="factorListPopupCommonGrpSearch" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>
					<input type="text" id="factorListPopupCommonGrp" name="factorListPopupCommonGrp" class="w70">                                                                                  
				</td>
			</tr>								
			<tr>	
				<th>
					<spring:message code="LAB.M09.LAB00052"/><!-- 적용일자 --><span class="dot">*</span>
				</th>
				<td>
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="factorListPopupActDt" name="factorListPopupActDt" class="datepicker" readonly="readonly" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M15.LAB00066"/><!-- 변경/종료 구분 -->
				</th>
				<td colspan="3">
					<div class="date_box">
						<input type="radio" id="radioChgNullFl" name="radioChgNullFl" value="1"checked="checked" />
						<label for="mstrFl"><spring:message code="LAB.M15.LAB00027" /></label>
						<input type="radio" id="radioChgNullFl" name="radioChgNullFl" value="0" /> 
						<label for="mstrFl2"> <spring:message code="LAB.M15.LAB00064" /></label>
					</div>	
				</td>	
			</tr>				 		
			<tr>
				<th>
					<spring:message code="LAB.M15.LAB00065"/><!-- Null변환 -->
				</th>
				<td>
					<input type="text" id="factorListPopupNullFm" name="factorListPopupNullFm" class="w100p">
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

<div id="popup_dialog2" class="Layer_wrap2" style="display: none;width:380px;" >
</div>
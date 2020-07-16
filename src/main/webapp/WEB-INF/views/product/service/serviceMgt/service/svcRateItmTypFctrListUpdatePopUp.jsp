<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	
	$("#fctrNm").addClass('not-active');	
	$("#fctrNm").attr('disabled', true);
	
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "1"); //시스템 날짜 출력
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
	
	$("input:radio").change(
		function(){
			if ($("input[name=serviceMgtSvcRateItmTypFctrRadioInActYn]:checked").val() == 1){
				$( ".datepicker" ).datepicker("setDate", "1");
			} else {
				$( ".datepicker" ).datepicker("setDate", "0");
			}
		}	
	);	
	
	$('#btnUpdate').click(function() {

		var param = checkValidation();

		if(param != false){
			
			$.ajax({
				url : 'modSvcRateItmTypFctr.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result == "2"){
						alert('<spring:message code="MSG.M06.MSG00025" />');	//변경 내역이 없습니다.
						$("#serviceMgtSvcRateItmTypFctrListGrid").trigger("reloadGrid");
						$("#btnClose").trigger('click');
					}else{
						if(data.result != "0" && data.result != "-1"){
							alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
							$("#serviceMgtSvcRateItmTypFctrListGrid").trigger("reloadGrid");
							$("#btnClose").trigger('click');
						}
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
	
});

function checkValidation(){
	var param = new Object();
	var fctrCd = $("#fctrCd").val();	//출력우선순위
	if(fctrCd == ''){
		alert('<spring:message code="LAB.M01.LAB00127" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}	

	var actDt = $("#actDt").val();	//적용시작일
	
	if(actDt == ''){
		alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}	

	var inActYn = $(':radio[name="serviceMgtSvcRateItmTypFctrRadioInActYn"]:checked').val();
	var baseActDt = $("#serviceMgtSvcRateItmTypFctrListUpdateBaseActDt").val();
	var svcRateItmTypCd = $("#fctrListPopupSvcRateItmTypCd").val();
	
	var fctrRefTyp = $("#fctrListPopupFctrRefTyp").val();
	var tableId = $("#fctrListPopupTableId").val();
	var colmnId = $("#fctrListPopupCoulmnId").val();
	var dataTyp = $("#fctrListPopupDataTyp").val();
	var refColmnId = $("#fctrListPopupRefColmnId").val();
	var refTableId = $("#fctrListPopupRefTableId").val();
	var refColmnNm = $("#fctrListPopupSvcRateItmTypCd").val();
	var refTableCond = $("#fctrListPopupRefTableCond").val();
	var refCd = $("#fctrListPopupRefCd").val();
	var inactDt = $("#fctrListPopupInactDt").val();
	
	param.inActYn = inActYn;
	param.fctrCd = fctrCd;
	param.actDt = dateFormatToStringYYYYMMDD(actDt);
	param.baseActDt = baseActDt;
	param.svcRateItmTypCd = svcRateItmTypCd;
	param.fctrRefTyp = fctrRefTyp;
	param.tableId = tableId;
	param.colmnId = colmnId;
	param.dataTyp = dataTyp;
	param.refColmnId = refColmnId;
	param.refTableId = refTableId;
	param.refColmnNm = refColmnNm;
	param.refTableCond = refTableCond;
	param.refCd = refCd;
	param.inactDt = inactDt;
	
	
	return param;
	
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
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00174"/></h4>
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
					<spring:message code="LAB.M06.LAB00055"/><!-- 변경/종료 구분 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<div class="date_box">
						<input type="radio" id="serviceMgtSvcRateItmTypFctrRadioInActYn" name="serviceMgtSvcRateItmTypFctrRadioInActYn" value="1"checked="checked" />
						<label for="mstrFl"><spring:message code="LAB.M06.LAB00054" /></label>
						<input type="radio" id="serviceMgtSvcRateItmTypFctrRadioInActYn" name="serviceMgtSvcRateItmTypFctrRadioInActYn" value="2" /> 
						<label for="mstrFl2"> <spring:message code="LAB.M09.LAB00165" /></label>
					</div>	
				</td>	
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00127"/><!-- 과금요소명 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="fctrNm" name="fctrNm" value="${svcRateItmTypFctr.fctrNm} " class="w100p">
					<input hidden id="fctrCd" name="fctrCd" value="${svcRateItmTypFctr.fctrCd}" class="w100p">                     
				</td>	
			</tr>
			<tr>	
				<th>
					<spring:message code="LAB.M09.LAB00052"/><!-- 적용시작일 --><span class="dot">*</span>
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
	<a class="blue-btn" href="#" id="btnUpdate"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<input id="serviceMgtSvcRateItmTypFctrListUpdateBaseActDt" type='text' value="${svcRateItmTypFctr.baseActDt}"  hidden />
<input id="fctrListPopupSvcRateItmTypCd" type='text' value="${svcRateItmTypFctr.svcRateItmTypCd}"  hidden />

<input id="fctrListPopupFctrRefTyp" type='text' value="${svcRateItmTypFctr.fctrRefTyp}"  hidden />
<input id="fctrListPopupTableId" type='text' value="${svcRateItmTypFctr.tableId}"  hidden />
<input id="fctrListPopupCoulmnId" type='text' value="${svcRateItmTypFctr.colmnId}"  hidden />
<input id="fctrListPopupDataTyp" type='text' value="${svcRateItmTypFctr.dataTyp}"  hidden />
<input id="fctrListPopupRefColmnId" type='text' value="${svcRateItmTypFctr.refColmnId}"  hidden />
<input id="fctrListPopupRefTableId" type='text' value="${svcRateItmTypFctr.refTableId}"  hidden />
<input id="fctrListPopupRefColmnNm" type='text' value="${svcRateItmTypFctr.refColmnNm}"  hidden />
<input id="fctrListPopupRefTableCond" type='text' value="${svcRateItmTypFctr.refTableCond}"  hidden />
<input id="fctrListPopupRefCd" type='text' value="${svcRateItmTypFctr.refCd}"  hidden />
<input id="fctrListPopupInactDt" type='text' value="${svcRateItmTypFctr.inactDt}"  hidden />


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

	$("#btnUpdate").click(function() {
		updateData();            
	});	
	
	$("#svcType, #subscripCmpsFl").selectmenu();
	
	$( "#soId" ).selectmenu()
	 			.selectmenu("disable");
	
	$("input:radio").change(
		function(){
			if ($("input[name=serviceMgtUpdateRadioInActYn]:checked").val() == 1){
				$(":text").removeClass('not-active'); 
				$(":text").removeAttr('disabled');
				
				$("#svcType, #subscripCmpsFl").selectmenu("enable");
				$( ".datepicker" ).datepicker("setDate", "1");
				
				$("#serviceMgtUpdateDateLabel").text('<spring:message code="LAB.M09.LAB00052" />');
				
			} else {
				$(":text").addClass('not-active'); 
				$(":text").attr('disabled', true);
				
				$("#svcType, #subscripCmpsFl").selectmenu("disable");
				$( ".datepicker" ).datepicker("setDate", "0");
				
				$("#serviceMgtUpdateDateLabel").text('<spring:message code="LAB.M09.LAB00058" />');
			}
		}	
		
	);
	
});	


function updateData(){
	
	var param = checkValidation();
	
	if(param != false){
		
		$.ajax({
			url : 'updateServiceMgt.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1" && data.result != "-2"){
					alert('<spring:message code="MSG.M07.MSG00089" />');	//저장되었습니다.
					//$("#serviceMgtComListGrid").trigger("reloadGrid");
					reloadProduct();
					$("#btnClose").trigger('click');
				} else if (data.result == "-1") {
					alert('<spring:message code="MSG.M09.MSG00051" />');
					return;
				} else if (data.result == "-2") {
					alert('<spring:message code="MSG.M14.MSG00015" />');
					return;
				}
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
	}
}

function checkValidation(){
	var param = new Object();
	
	var soId = $.trim( $("#soId").val() );	//사업
	if(soId == ''){
		alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
		return false;
	}

	var svcNm = $.trim( $("#svcNm").val() );	//서비스명
	if(svcNm == ''){
		alert('<spring:message code="LAB.M07.LAB00185" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}	

	var svcType = $.trim( $("#svcType").val() );	//서비스유형
	if(svcType == ''){
		alert('<spring:message code="LAB.M07.LAB00188" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	

	var subscripCmpsFl = $.trim( $("#subscripCmpsFl").val() );	//가입계약구성
	if(subscripCmpsFl == ''){
		alert('<spring:message code="LAB.M01.LAB00006" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var dispPriNo = $.trim( $("#dispPriNo").val() );	//츨력우선순위
	if(dispPriNo == ''){
		alert('<spring:message code="LAB.M10.LAB00081" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}
	
	var actDt = $.trim( $("#actDt").val() );
	if(actDt == ''){
		alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
	
	var svcAbbrNm = $("#svcAbbrNm").val();
	var baseActDt = $("#baseActDt").val();
	var inActYn = $(':radio[name="serviceMgtUpdateRadioInActYn"]:checked').val();
	var targetMainSvcCd = $("#serviceMgtUpdateMainSvcCd").val();
	var svcCd = $("#serviceMgtUpdateSvcCd").val();
	var inactDt = $("#serviceMgtUpdateInactDt").val();
	var targetUpperCd = $("#serviceMgtUpdateTargetUpperCd").val();
	var mainSvcFl = $("#serviceMgtUpdateMainSvcFl").val();
	var mstrFl = $("#serviceMgtUpdateMstrFl").val();
	
	param.soId = soId;
	param.svcCd = svcCd;
	param.svcNm = svcNm;
	param.svcTyp = svcType;
	param.subscripCmpsFl = subscripCmpsFl;
	param.dispPriNo = dispPriNo;
	
	param.actDt = dateFormatToStringYYYYMMDD(actDt);
	param.svcAbbrNm = svcAbbrNm;
	param.baseActDt = baseActDt;
	param.targetMainSvcCd = targetMainSvcCd;
	param.inActYn = inActYn;
	param.inactDt = inactDt;
	param.targetUpperCd = soId;	
	param.mainSvcFl = mainSvcFl;
	param.mstrFl = mstrFl;
	
	
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
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00184"/><!-- 서비스등록 --></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:25%;">
			<col style="width:25%;">
			<col style="width:25%;">
			<col style="width:25%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M06.LAB00055"/><!-- 변경/종료 구분 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<div class="date_box">
						<input type="radio" id="serviceMgtUpdateRadioInActYn" name="serviceMgtUpdateRadioInActYn" value="1"checked="checked" />
						<label for="mstrFl"><spring:message code="LAB.M06.LAB00054" /></label>
						<input type="radio" id="serviceMgtUpdateRadioInActYn" name="serviceMgtUpdateRadioInActYn" value="2" /> 
						<label for="mstrFl2"> <spring:message code="LAB.M09.LAB00165" /></label>
					</div>	
				</td>	
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span>
				</th>
	            <td colspan="3">
					<select class="w150" id="soId" name="soId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${getServiceMgt.soId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>		 
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00185"/><!-- 서비스명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<input type="text" id="svcNm" name="svcNm" value="${getServiceMgt.svcNm}" class="w200">                                            
	     		</td>
	 		</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00187"/><!-- 서비스약칭명 -->
				</th>
				<td colspan="3">
	       			<input type="text" id="svcAbbrNm" name="svcAbbrNm" value="${getServiceMgt.svcAbbrNm}" class="w200">                                            
	     		</td>
	 		</tr>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00188"/><!-- 서비스유형 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id="svcType" name="svcType" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${svcType}" var="svcType" varStatus="status">
							<option value="${svcType.commonCd}" <c:if test="${getServiceMgt.svcTyp eq svcType.commonCd}"> selected="selected" </c:if> >${svcType.commonCdNm}</option>
						</c:forEach>
					</select>                                                                                   
				</td>
			</tr> 	 			 		
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00006"/><!-- 가입계약구성 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id=subscripCmpsFl name="subscripCmpsFl" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${subscripCmpsFl}" var="subscripCmpsFl" varStatus="status">
							<option value="${subscripCmpsFl.commonCd}" <c:if test="${getServiceMgt.subscripCmpsFl eq subscripCmpsFl.commonCd}"> selected="selected" </c:if> >${subscripCmpsFl.commonCdNm}</option>
						</c:forEach>
					</select>                                                                                   
				</td>
				
			</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M10.LAB00081"/><!-- 출력우선순위 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<input type="text" id="dispPriNo" name="dispPriNo" class="w150" value="${getServiceMgt.dispPriNo}">                                            
	     		</td>
	 		</tr>
			<tr>	
				<th id ="serviceMgtUpdateDateLabel">
					<spring:message code="LAB.M09.LAB00052"/><!-- 적용일자 --><span class="dot">*</span>
				</th>
				<td colspan="3">
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

<input id="serviceMgtUpdateSvcCd" type='text' value="${svcCd}"  hidden />
<input id="baseActDt" type='text' value="${getServiceMgt.baseActDt}"  hidden />
<input id="serviceMgtUpdateMainSvcCd" type='text' value="${targetMainSvcCd}"  hidden />
<input id="serviceMgtUpdateInactDt" type='text' value="${getServiceMgt.inactDt}"  hidden />
<input id="serviceMgtUpdateTargetUpperCd" type='text' value="${targetUpperCd}"  hidden />
<input id="serviceMgtUpdateMainSvcFl" type='text' value="${getServiceMgt.mainSvcFl}"  hidden />
<input id="serviceMgtUpdateMstrFl" type='text' value="${getServiceMgt.mstrFl}"  hidden />		
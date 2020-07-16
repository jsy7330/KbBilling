<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	
	$("select").selectmenu()
	.selectmenu( "option", "width", "100%" );	
	
	
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "1"); //시스템 날짜 출력
	}
	
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );	
	
	$('#svcRateItmTypAttrSearchBtnUpdate').click(function() {
		$("#actDt").val(dateFormatToStringYYYYMMDD($("#actDt").val()));
		var formData = $("#attrListMap").serialize();
		
		$.ajax({
			url : 'treatSvcRateItmTypAttr.json',
			type 
			: 'POST',
			async: false,
			data : formData,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					
					$("#svcRateItmTypAttrSearchBtnClose").trigger('click');
					$("#serviceMgtSvcRateItmTypAttrListGrid").trigger("reloadGrid");
				}
			},
		    error: function(request,status,error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    },
			complete : function() {
			}
		});		
		
	});	
	
	$("input:radio").change(
			function(){
				if ($("input[name=svcRateItmTypAttrSearchRadioInActYn]:checked").val() == 0){
										
/* 					$("input:text").removeClass('not-active'); 
					$("input:text").removeAttr('disabled'); */
					
					//$("select").selectmenu("enable");
					
					$( ".datepicker" ).datepicker("setDate", "1");
					
				} else {
/* 					$("input:text").addClass('not-active'); 
					$("input:text").attr('disabled', true); */
					
					//$("select").selectmenu("disable");
					
					$( ".datepicker" ).datepicker("setDate", "0");
					
				}
			}	
			
		);		
	
	
});		
</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00172"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<form id="attrListMap" name="attrListMap" method="post">
<input id="svcRateItmTypAttrSearchSvcRateItmTypCd" name="svcRateItmTypAttrSearchSvcRateItmTypCd" type='text' value="${svcRateItmTypCd}"  hidden />
<input id="svcRateItmTypAttrSearchSoId" name="svcRateItmTypAttrSearchSoId" type='text' value="${soId}"  hidden />
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00171"/></h4>
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
					<spring:message code="LAB.M03.LAB00076"/><!-- 변경/종료 구분 --><span class="dot">*</span>
				</th>
				<td>
					<div class="date_box">
						<input type="radio" id="svcRateItmTypAttrSearchRadioInActYn" name="svcRateItmTypAttrSearchRadioInActYn" value="0"checked="checked" />
						<label for="mstrFl"><spring:message code="LAB.M10.LAB00060" /></label>
						<input type="radio" id="svcRateItmTypAttrSearchRadioInActYn" name="svcRateItmTypAttrSearchRadioInActYn" value="1" /> 
						<label for="mstrFl2"> <spring:message code="LAB.M09.LAB00165" /></label>
					</div>	
				</td>	
			</tr>
			
			<c:forEach items="${resultList}" var="resultList" varStatus="status">
				<c:set var="dataTyp" value="0" /> 
				<c:forEach items="${commonGrpList}" var="commonGrpList" varStatus="status">
					<c:if test="${resultList.refCd eq commonGrpList.commonGrp }">
						<c:set var="dataTyp" value="1" /> 
					</c:if>
				</c:forEach>
				
				<c:if test="${dataTyp eq '0'}">
					<tr>
						<th>
							${resultList.attrNm} 					
						</th>
						<td>
							<input type="text" id="${resultList.attrCd}" name="${resultList.attrCd}" class="w100p" value="${resultList.attrValNm}">
							<input id="InacT${resultList.attrCd}" name="InacT${resultList.attrCd}" type='text' value="${resultList.inactDt}"  hidden />
							<input id="BasE${resultList.attrCd}" name="BasE${resultList.attrCd}" type='text' value="${resultList.baseActDt}"  hidden />
						</td>
					</tr>
				</c:if>
				
				<c:if test="${dataTyp eq '1'}">
					<tr>
						<th>
							${resultList.attrNm} 					
						</th>
						<td>
							<select class="w100p" id="${resultList.attrCd}" name="${resultList.attrCd}">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<c:forEach items="${commonGrpList}" var="commonGrpList" varStatus="status">
									<c:if test="${resultList.refCd eq commonGrpList.commonGrp }">
										<option value="${commonGrpList.commonCd}" <c:if test="${resultList.attrVal eq commonGrpList.commonCd}"> selected="selected" </c:if> >${commonGrpList.commonCdNm}</option>
									</c:if>
								</c:forEach>							
							</select>
							<input id="InacT${resultList.attrCd}" name="InacT${resultList.attrCd}" type='text' value="${resultList.inactDt}"  hidden />
							<input id="BasE${resultList.attrCd}" name="BasE${resultList.attrCd}" type='text' value="${resultList.baseActDt}"  hidden />
						</td>
					</tr>
				</c:if>				
			</c:forEach>
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
</form>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="svcRateItmTypAttrSearchBtnUpdate"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="svcRateItmTypAttrSearchBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
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
	
	
	$("#btnInsert").click(function() {
		updateData();            
	});	
	
	
	$("#productBillingItemListUpdateSoId, #productBillingItemListUpdateInvItmGrp1, #productBillingItemListUpdateInvItmGrp2, #productBillingItemListInsertInvPrtGrpStd" ).selectmenu()
	.selectmenu( "option", "width", "100%" );
	
	$("#productBillingItemListUpdateSoId").selectmenu("disable");
	
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
	
	$("input:radio").change(
			function(){
				if ($("input[name=productBillingItemListUpdateRadioInActYn]:checked").val() == 1){
					
					$("#productBillingItemListUpdateInvItmNm, #productBillingItemListUpdateInvAbbrNm, #productBillingItemListUpdatePrintPriNo").removeClass('not-active'); 
					$("#productBillingItemListUpdateInvItmNm, #productBillingItemListUpdateInvAbbrNm, #productBillingItemListUpdatePrintPriNo").removeAttr('disabled');
					
					$("#productBillingItemListUpdateInvItmGrp1, #productBillingItemListUpdateInvItmGrp2, #productBillingItemListInsertInvPrtGrpStd").selectmenu("enable");
					
					$( ".datepicker" ).datepicker("setDate", "1");
					$("#dateLabel").text('<spring:message code="LAB.M09.LAB00056" />');
					
				} else {
					$("#productBillingItemListUpdateInvItmNm, #productBillingItemListUpdateInvAbbrNm, #productBillingItemListUpdatePrintPriNo").addClass('not-active'); 
					$("#productBillingItemListUpdateInvItmNm, #productBillingItemListUpdateInvAbbrNm, #productBillingItemListUpdatePrintPriNo").attr('disabled', true);
					
					$("#productBillingItemListUpdateInvItmGrp1, #productBillingItemListUpdateInvItmGrp2, #productBillingItemListInsertInvPrtGrpStd").selectmenu("disable");
					
					$( ".datepicker" ).datepicker("setDate", "0");
					$("#dateLabel").text('<spring:message code="LAB.M09.LAB00058" />');
					
				}
			}	
			
		);		
	
});	

function updateData(){
	
	var param = checkValidation();
	
	if(param != false){
		
		$.ajax({
			url : 'modInvoiceItem.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					$("#productBillingItemListGrid").trigger("reloadGrid");					
					$("#btnClose").trigger('click');
				} else if (data.result == "-1") {
					alert('<spring:message code="MSG.M09.MSG00051" />');
					
				} else if (data.result == "0") {
					alert('<spring:message code="MSG.M07.MSG00098" />');
					
				} 
			},
		    error: function(request,status,error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    },
			complete : function() {
			}
		});
	}
}

function checkValidation(){
	var param = new Object();

	var soId = $.trim( $("#productBillingItemListUpdateSoId").val() );
	if(soId == ''){
		alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');
		return false;
	}		
	
	var invItmNm = $.trim( $("#productBillingItemListUpdateInvItmNm").val() );
	if(invItmNm == ''){
		alert('<spring:message code="LAB.M10.LAB00045" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	} 
	
	var printPriNo = $.trim( $("#productBillingItemListUpdatePrintPriNo").val() );
	if(printPriNo == ''){
		alert('<spring:message code="LAB.M08.LAB00130" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
			
		
	var actDt = $("#productBillingItemListUpdateActDt").val();

	var invItmAbbrNm =  $("#productBillingItemListUpdateInvAbbrNm").val();
	var invPrtGrpStdNm = $.trim( $("#productBillingItemListUpdateInvPrtGrpStd").val());
	var invItmGrp1 = $.trim( $("#productBillingItemListUpdateInvItmGrp1").val());	
	var invItmGrp2 = $.trim( $("#productBillingItemListUpdateInvItmGrp2").val());		
	var inActYn = $(':radio[name="productBillingItemListUpdateRadioInActYn"]:checked').val();
	var invItmCd = $.trim( $("#productBillingItemListUpdateInvItmCd").val());
	var baseActDt = $("#productBillingItemListUpdateBaseActDt").val();
	var inactDt = $("#productBillingItemListUpdateInActDt").val();
	var mstrFl = $("#productBillingItemListUpdateMstrFl").val();
	var invPrtGrpStd = $.trim( $("#productBillingItemListInsertInvPrtGrpStd").val());
	
	param.soId = soId;
	param.invItmNm = invItmNm;
	param.invItmAbbrNm = invItmAbbrNm;
	param.printPriNo = printPriNo;
	param.invPrtGrpStd = invPrtGrpStdNm;
	param.actDt = dateFormatToStringYYYYMMDD(actDt);

	param.invItmEngNm = invItmNm;
	param.invItmGrp1 = invItmGrp1;
	param.invItmGrp2 = invItmGrp2;
	param.inActYn = inActYn;
	param.invItmCd = invItmCd;
	param.baseActDt = baseActDt;
	param.inactDt = inactDt.replace(/\-/g,'');
	param.mstrFl = mstrFl;
	param.invPrtGrpStd = invPrtGrpStd;
	
	return param;
}

</script>
	
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M10.LAB00043"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M10.LAB00044"/><!-- 청구항목등록 --></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:30%;">
			
			<col style="width:70%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="MSG.M06.MSG00024"/><!-- 변경/종료 구분 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<div class="date_box">
						<input type="radio" id="productBillingItemListUpdateRadioInActYn" name="productBillingItemListUpdateRadioInActYn" value="1"checked="checked" />
						<label for="mstrFl"><spring:message code="MSG.M06.MSG00023" /></label>
						<input type="radio" id="productBillingItemListUpdateRadioInActYn" name="productBillingItemListUpdateRadioInActYn" value="2" /> 
						<label for="mstrFl2"><spring:message code="MSG.M09.MSG00041" /></label>
					</div>	
				</td>	
			</tr>			
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span>
				</th>
	            <td colspan="3">
					<select class="w150" id="productBillingItemListUpdateSoId" name="productBillingItemListUpdateSoId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${tmpBillingItem.soId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00045"/><!-- 청구항목명 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="productBillingItemListUpdateInvItmNm" name="productBillingItemListUpdateInvItmNm" class="w100p" value="${tmpBillingItem.invItmNm}">                                            
	     		</td>			
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00003"/><!-- 약칭명 -->
				</th>
				<td>
	       			<input type="text" id="productBillingItemListUpdateInvAbbrNm" name="productBillingItemListUpdateInvAbbrNm" class="w100p" value="${tmpBillingItem.invItmAbbrNm}">                                            
	     		</td>			
			</tr>			
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00130"/><!-- 인쇄순서 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="productBillingItemListUpdatePrintPriNo" name="productBillingItemListUpdatePrintPriNo" class="w150" value="${tmpBillingItem.printPriNo}" numberonly="true">                                            
	     		</td>			
			</tr>			
			<tr>	
				<th id="dateLabel">
					<spring:message code="LAB.M09.LAB00056"/><!-- 적용일자 --><span class="dot">*</span>
				</th>
				<td>
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="productBillingItemListUpdateActDt" name="productBillingItemListUpdateActDt" class="datepicker" readonly="readonly" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00132"/><!-- 인쇄항목그룹1 -->
				</th>				
	            <td>
					<select class="w100p" id="productBillingItemListUpdateInvItmGrp1" name="productBillingItemListUpdateInvItmGrp1">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${invItmGrp1}" var="invItmGrp1" varStatus="status">
							<option value="${invItmGrp1.commonCd}" <c:if test="${tmpBillingItem.invItmGrp1 eq invItmGrp1.commonCd}"> selected="selected" </c:if> >${invItmGrp1.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>			
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00133"/><!-- 인쇄항목그룹2 -->
				</th>				
	            <td>
					<select class="w100p" id="productBillingItemListUpdateInvItmGrp2" name="productBillingItemListUpdateinvItmGrp2">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${invItmGrp2}" var="invItmGrp2" varStatus="status">
							<option value="${invItmGrp2.commonCd}" <c:if test="${tmpBillingItem.invItmGrp2 eq invItmGrp2.commonCd}"> selected="selected" </c:if>>${invItmGrp2.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>			
			</tr>									
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00131"/><!-- 인쇄출력그룹기준 -->
				</th>				
	            <td>
					<select class="w100p" id="productBillingItemListInsertInvPrtGrpStd" name="productBillingItemListInsertInvPrtGrpStd">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${invPrtGrpStd}" var="invPrtGrpStd" varStatus="status">
							<option value="${invPrtGrpStd.commonCd}" <c:if test="${tmpBillingItem.invPrtGrpStd eq invPrtGrpStd.commonCd}"> selected="selected" </c:if> >${invPrtGrpStd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
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
<input id="productBillingItemListUpdateInvItmCd" type='text' value="${tmpBillingItem.invItmCd}"  hidden />
<input id="productBillingItemListUpdateBaseActDt" type='text' value="${tmpBillingItem.baseActDt}"  hidden />
<input id="productBillingItemListUpdateInActDt" type='text' value="${tmpBillingItem.inactDt}"  hidden />
<input id="productBillingItemListUpdateMstrFl" type='text' value="${tmpBillingItem.mstrFl}"  hidden />

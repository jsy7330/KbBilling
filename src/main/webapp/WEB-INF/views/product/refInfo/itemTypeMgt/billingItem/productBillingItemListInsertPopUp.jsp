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
	
	
	$("#btnInsert").click(function() {
		insertData();            
	});	
	
	
	$("#productBillingItemListInsertInvPrtGrpStdNm").selectmenu()
	.selectmenu( "option", "width", "100%" );
	
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
	
	
	/*
	$("#productChargeTypeListInsertChrgCtgry").selectmenu({
		change: function( event, ui ) {
			var tmpParam = new Object();
			var commonCd = $(this).val();
			console.log("common : " + commonCd);
			if (commonCd == "Z") {
				alert("계산대상 요금항목이 아니고 청구에서만 사용하는 기타 항목입니다.");
				return;
			}
			
			tmpParam.commonCd = commonCd;
			
			$.ajax({
				url : 'getRateTypeMaxPriNo.json',
				type : 'POST',
				async: false,
				data : tmpParam,
				success : function(data) {
					$("#productChargeTypeListInsertDispPriNo").val(data.productChargeTypeListChargeType.maxPriNo);
				},
			    error: function(request,status,error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    },
				complete : function() {
				}
			});
		}
		
	});
	*/
});	

function insertData(){
	
	var param = checkValidation();
	
	if(param != false){
		
		$.ajax({
			url : 'addInvoiceItem.json',
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

	var soId = $.trim( $("#productBillingItemListInsertSoId").val() );
	if(soId == ''){
		alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');
		return false;
	}		
	
	var invItmNm = $.trim( $("#productBillingItemListInsertInvItmNm").val() );
	if(invItmNm == ''){
		alert('<spring:message code="LAB.M10.LAB00045" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	} 
	
	var printPriNo = $.trim( $("#productBillingItemListInsertPrintPriNo").val() );
	if(printPriNo == ''){
		alert('<spring:message code="LAB.M08.LAB00130" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
			
		
	var actDt = $("#productBillingItemListInsertActDt").val();
	var invItmGrp1 = $.trim($("#productBillingItemListInsertInvItmGrp1").val());
	var invItmGrp2 = $.trim($("#productBillingItemListInsertInvItmGrp2").val());
	var invItmAbbrNm = $.trim( $("#productBillingItemListInsertInvAbbrNm").val());
	var invPrtGrpStdNm = $.trim( $("#productBillingItemListInsertInvPrtGrpStdNm").val());
	
	param.soId = soId;
	param.invItmNm = invItmNm;
	param.invItmAbbrNm = invItmAbbrNm;
	param.printPriNo = printPriNo;
	param.invPrtGrpStd = invPrtGrpStdNm;
	param.actDt = dateFormatToStringYYYYMMDD(actDt);
	param.invItmEngNm = invItmNm;
	param.invItmGrp1 = invItmGrp1;
	param.invItmGrp2 = invItmGrp2;
	
	

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
					<spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span>
				</th>
	            <td colspan="3">
					<select class="w150" id="productBillingItemListInsertSoId" name="productBillingItemListInsertSoId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00045"/><!-- 청구항목명 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="productBillingItemListInsertInvItmNm" name="productBillingItemListInsertInvItmNm" class="w100p">                                            
	     		</td>			
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00003"/><!-- 약칭명 -->
				</th>
				<td>
	       			<input type="text" id="productBillingItemListInsertInvAbbrNm" name="productBillingItemListInsertInvAbbrNm" class="w100p">                                            
	     		</td>			
			</tr>			
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00130"/><!-- 인쇄순서 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="productBillingItemListInsertPrintPriNo" name="productBillingItemListInsertPrintPriNo" class="w150" value="${printPriNo}" numberonly="true">                                            
	     		</td>			
			</tr>			
			<tr>	
				<th>
					<spring:message code="LAB.M09.LAB00056"/><!-- 적용일자 --><span class="dot">*</span>
				</th>
				<td>
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="productBillingItemListInsertActDt" name="productBillingItemListInsertActDt" class="datepicker" readonly="readonly" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>			
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00131"/><!-- 인쇄출력그룹기준 -->
				</th>				
	            <td>
					<select class="w100p" id="productBillingItemListInsertInvPrtGrpStdNm" name="productBillingItemListInsertInvPrtGrpStdNm">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${invPrtGrpStd}" var="invPrtGrpStd" varStatus="status">
							<option value="${invPrtGrpStd.commonCd}">${invPrtGrpStd.commonCdNm}</option>
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
<input id="productBillingItemListInsertInvItmGrp1" type='text' value="${invItmGrp1[0].commonCd}"  hidden />
<input id="productBillingItemListInsertInvItmGrp2" type='text' value="${invItmGrp2[0].commonCd}"  hidden />

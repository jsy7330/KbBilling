<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
var ratePriNo = "";
var dispPriNo = "";
var chrgCtgry = "";

$(document).ready(function() {
	
	$("#modRateItmPopupRateLocat, #modRateItmPopupRateRefTyp, #modRateItmPopupDefltRateItm").selectmenu()
	.selectmenu( "option", "width", "100%" );	
	//$("#modRateItmPopupDefltRateItm").selectmenu("disable");
	$("#modRateItmPopupChrgCtgry, #modRateItmPopupSvcRateItmTypNm").addClass('not-active'); 
	$("#modRateItmPopupChrgCtgry, #modRateItmPopupSvcRateItmTypNm").attr('disabled', true);
	
	$("#modRateItmPopupRateRefTyp").selectmenu({
		change: function( event, ui ) {	
			if ($(this).val() == "1") {
				$("#modRateItmPopupDefltRateItm").selectmenu("enable");
			} else {
				$("#modRateItmPopupDefltRateItm").selectmenu("disable");
			}
		}
	});		
		
	$('#modRateItmPopupBtnUpdate').click(function() {

		var param = checkValidation();        
       
		if(param != false){
			
			$.ajax({
				url : 'modRateItm.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						$("#modRateItmPopupPopupBtnClose").trigger('click');
						//$("#rateItmListGrid").trigger("reloadGrid");
						reloadProduct();
					} else if (data.result == "-1") {
						alert('<spring:message code="MSG.M09.MSG00051" />');
						
					}
				},
			    error: function(request,status,error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    },
				complete : function() {
					reloadProduct();
				}
			});
		}		
		
	});	 

});

function checkValidation(){
	var param = new Object();
	
	
	var rateItmNm = $("#modRateItmPopupRateItmNm").val();	//요소명
	if(rateItmNm == ''){
		alert('<spring:message code="LAB.M01.LAB00152" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
	
	var rateLocat = $.trim( $("#modRateItmPopupRateLocat").val() );	
	if(rateLocat == ''){
		alert('<spring:message code="LAB.M01.LAB00148" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}
	
	var rateRefTyp = $.trim( $("#modRateItmPopupRateRefTyp").val() );	
	if(rateRefTyp == ''){
		alert('<spring:message code="LAB.M08.LAB00077" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	param.rateItmCd = $("#modRateItmPopupRateItmCd").val();
	param.rateItmNm = rateItmNm;
	param.prodCd = $("#modRateItmPopupProdCd").val();
	param.svcCd = $("#modRateItmPopupSvcCd").val();
	param.rateLocat = rateLocat;
	param.rateRefTyp = rateRefTyp;
	param.chrgCtgry = chrgCtgry;
	//param.ratePriNo = $("#modRateItmPopupRatePriNo").val();

	if($("#modRateItmPopupRatePriNo").val().length==null){
		param.dispPriNo = "0";

	}else{
		param.dispPriNo = dispPriNo;
		
	}

	param.dispPriNo = $("#modRateItmPopupDispPriNo").val();
	param.defltRateItmCd = $("#modRateItmPopupDefltRateItm").val();
	param.mnoRateItmCd = $("#modRateItmPopupMnoRateItmCd").val();
	
	return param;	
}

</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00118"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00153"/></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:35%;">
			<col style="width:65%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00175"/><!-- 서비스과금항목유형 --><span class="dot">*</span>
				</th>				
	            <td>
	            	<input type="text" id="modRateItmPopupSvcRateItmTypNm" name="modRateItmPopupSvcRateItmTypNm" value="${rateItm.svcRateItmTypNm}" class="w100p">                                       
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00051"/><!-- 요금영역 -->
				</th>
				<td>
					<input type="text" id="modRateItmPopupChrgCtgry" name="modRateItmPopupChrgCtgry" value="${rateItm.chrgCtgryNm}"  class="w100p">
				</td>	
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00152"/><!-- 과금항목명 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="modRateItmPopupRateItmNm" name="modRateItmPopupRateItmNm" value="${rateItm.rateItmNm}" class="w100p">
				</td>	
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00112"/><!-- 과금/편성위치 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="modRateItmPopupRateLocat" name="modRateItmPopupRateLocat">
						<c:forEach items="${rateLocat}" var="rateLocat" varStatus="status">
							<option value="${rateLocat.commonCd}" <c:if test="${rateItm.rateLocat eq rateLocat.commonCd}"> selected="selected" </c:if> >${rateLocat.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00077"/><!-- 요율참조유형 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="modRateItmPopupRateRefTyp" name="modRateItmPopupRateRefTyp">
						<c:forEach items="${rateRefTyp}" var="rateRefTyp" varStatus="status">
							<option value="${rateRefTyp.commonCd}" <c:if test="${rateItm.rateRefTyp eq rateRefTyp.commonCd}"> selected="selected" </c:if> >${rateRefTyp.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00100"/><!-- 공통과금항목 -->
				</th>				
	            <td>
					<select class="w100p" id="modRateItmPopupDefltRateItm" name="modRateItmPopupDefltRateItm">
						<c:forEach items="${defltRateItmList}" var="defltRateItmList" varStatus="status">
							<option value="${defltRateItmList.rateItmCd}" <c:if test="${rateItm.defltRateItmCd eq defltRateItmList.rateItmCd}"> selected="selected" </c:if> >${defltRateItmList.rateItmNm}</option>
						</c:forEach>					
					</select>                                            
				</td>
			</tr>						
			<tr>
				<th>
					<spring:message code="LAB.M15.LAB00060"/><!-- MNO과금항목코드 -->
				</th>
				<td>
					<input type="text" id="modRateItmPopupMnoRateItmCd" name="modRateItmPopupMnoRateItmCd" value="${rateItm.mnoRateItmCd}" class="w100p">
				</td>	
			</tr>																	 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="modRateItmPopupBtnUpdate"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="modRateItmPopupPopupBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<input id="modRateItmPopupProdCd" type='text' value="${productDevMgt.prodCd}"  hidden />
<input id="modRateItmPopupSvcCd" type='text' value="${productDevMgt.svcCd}"  hidden />
<input id="modRateItmPopupSvcRateItmTypCd" type='text' value="${rateItm.svcRateItmTypCd}"  hidden />
<input id="modRateItmPopupRateItmCd" type='text' value="${rateItm.rateItmCd}"  hidden />
<input id="modRateItmPopupRatePriNo" type='text' value="${rateItm.ratePriNo}"  hidden />
<input id="modRateItmPopupDispPriNo" type='text' value="${rateItm.dispPriNo}"  hidden />
<input id="modRateItmPopupChrgCtgry" type='text' value="${rateItm.chrgCtgry}"  hidden />
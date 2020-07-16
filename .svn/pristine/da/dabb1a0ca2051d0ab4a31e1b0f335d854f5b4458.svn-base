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
	
	$("#saleTyp").selectmenu();
	
	$( "#soId, #rateItmTyp" ).selectmenu()
	 			.selectmenu("disable");
	
	$("input:radio").change(
		function(){
			if ($("input[name=serviceMgtSaleItmUpdateRadioInActYn]:checked").val() == 1){
				
				$("#saleItmNm, #saleItmAbbrNm").removeClass('not-active'); 
				$("#saleItmNm, #saleItmAbbrNm").removeAttr('disabled');
				
				$( ".datepicker" ).datepicker("setDate", "1");
				
				$("#dateLabel").text('<spring:message code="LAB.M09.LAB00052" />');
				
			} else {
				$("#saleItmNm, #saleItmAbbrNm").addClass('not-active'); 
				$("#saleItmNm, #saleItmAbbrNm").attr('disabled', true);
				
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
			url : 'updateServiceMgtSaleItmListUpdate.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M07.MSG00089" />');	//저장되었습니다.
					$("#serviceMgtSaleItmListGrid").trigger("reloadGrid");
					reloadProduct();	
					$("#btnClose").trigger('click');
				} else if (data.result == "-1") {
					
					alert('<spring:message code="MSG.M09.MSG00051" />');
					return;
				}  else if (data.result == "-2") {
					alert('<spring:message code="MSG.M14.MSG00015" />' + data.returnMsg);
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
	
	var rateItmTyp = $.trim( $("#rateItmTyp").val() );	//요금유형
	if(rateItmTyp == ''){
		alert('<spring:message code="LAB.M08.LAB00048" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}
	
	var saleItmNm = $.trim( $("#saleItmNm").val() );	//매출항목명
	if(saleItmNm == ''){
		alert('<spring:message code="LAB.M05.LAB00012" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}	

	var actDt = $.trim( $("#actDt").val() );	//적용시작일
	
	if(actDt == ''){
		alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}			
	
	var svcCd = $("#serviceMgtSaleItmListUpdateSvcCd").val();
	var saleItmAbbrNm = $("#saleItmAbbrNm").val();
	var saleTyp = $("#saleTyp").val();
	var inActYn = $(':radio[name="serviceMgtSaleItmUpdateRadioInActYn"]:checked').val();
	var baseActDt = $("#baseActDt").val();
	var saleItmCd = $("#saleItmCd").val();
	var inactDt = $("#serviceMgtSaleItmUpdateInactDt").val();	
	
	param.soId = soId;
	param.svcCd = svcCd;
	param.saleItmNm = saleItmNm;
	param.actDt = dateFormatToStringYYYYMMDD(actDt);
	param.saleItmAbbrNm = saleItmAbbrNm;
	param.rateItmTypCd = rateItmTyp;
	param.saleTyp = saleTyp;
	param.inActYn = inActYn;
	param.baseActDt = baseActDt;
	param.saleItmCd = saleItmCd;
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
			<h4 class="ly_title"><spring:message code="LAB.M05.LAB00013"/><!-- 서비스등록 --></h4>
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
						<input type="radio" id="serviceMgtSaleItmUpdateRadioInActYn" name="serviceMgtSaleItmUpdateRadioInActYn" value="1"checked="checked" />
						<label for="mstrFl"><spring:message code="LAB.M06.LAB00054" /></label>
						<input type="radio" id="serviceMgtSaleItmUpdateRadioInActYn" name="serviceMgtSaleItmUpdateRadioInActYn" value="2" /> 
						<label for="mstrFl2"><spring:message code="LAB.M09.LAB00165" /></label>
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
							<option value="${soAuthList.so_id}" <c:if test="${getSaleItm.soId eq soAuthList.so_id}"> selected="selected" </c:if>>${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>			
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00048"/><!-- 요금유형 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id=rateItmTyp name="rateItmTyp" class="w200">
				        <c:forEach items="${rateItmTyp}" var="rateItmTyp" varStatus="status">
							<option value="${rateItmTyp.rateItmTypCd}" <c:if test="${getSaleItm.rateItmTypCd eq rateItmTyp.rateItmTypCd}"> selected="selected" </c:if> >${rateItmTyp.rateItmTypNm}</option>
						</c:forEach>
					</select>                                                                                   
				</td>
				
			</tr>		
			<tr>		
				<th>
					<spring:message code="LAB.M05.LAB00012"/><!-- 매출항목명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<input type="text" id="saleItmNm" name="saleItmNm" value="${getSaleItm.saleItmNm}" class="w200">                                            
	     		</td>
	 		</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M05.LAB00014"/><!-- 매출항목약칭명 -->
				</th>
				<td colspan="3">
	       			<input type="text" id="saleItmAbbrNm" name="saleItmAbbrNm" value="${getSaleItm.saleItmAbbrNm}" class="w200">                                            
	     		</td>
	 		</tr>		 		
			<tr>
				<th>
					<spring:message code="LAB.M05.LAB00009"/><!-- 매출유형 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id=saleTyp name="saleTyp" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${saleTyp}" var="saleTyp" varStatus="status">
							<option value="${saleTyp.commonCd}" <c:if test="${getSaleItm.saleTyp eq saleTyp.commonCd}"> selected="selected" </c:if>>${saleTyp.commonCdNm}</option>
						</c:forEach>
					</select>                                                                                   
				</td>
			</tr>
			<tr>	
				<th id ="dateLabel">
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
<input id="baseActDt" type='text' value="${getSaleItm.baseActDt}"  hidden />
<input id="saleItmCd" type='text' value="${saleItmCd}"  hidden />
<input id="serviceMgtSaleItmUpdateInactDt" type='text' value="${getSaleItm.inactDt}"  hidden />	
<input id="serviceMgtSaleItmListUpdateSvcCd" type='text' value="${svcCd}"  hidden />
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

	$("#serviceMgtSvcRateItmTypListBtnUpdate").click(function() {
		updateData();            
	});	
	
	$("#soId, #rateLocat, #invItm, #saleItm").selectmenu()
		.selectmenu( "option", "width", "100%" );
	
	$("#chrgCtgryNm, #rateItmTypNm, #saleItm").addClass('not-active');
	$("#chrgCtgryNm, #rateItmTypNm, #saleItm").attr('disabled', true);
	$("#soId").selectmenu("disable");
	
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});	

	$("input:radio").change(
			function(){
				if ($("input[name=serviceMgtSvcRateItmTypRadioInActYn]:checked").val() == 1){
					$(":text").removeClass('not-active'); 
					$(":text").removeAttr('disabled');

					$("#svcRateItmTypDesc").removeAttr('disabled');					
					
					$("#rateLocat, #invItm, #saleItm").selectmenu("enable");
					$( ".datepicker" ).datepicker("setDate", "1");
					
					$("#serviceMgtSvcRateItmTypListDateLabel").text('<spring:message code="LAB.M09.LAB00052" />');
				} else {
					$(":text").addClass('not-active'); 
					$(":text").attr('disabled', true);

					$("#svcRateItmTypDesc").attr("disabled","disabled"); 					
					
					$("#rateLocat, #invItm, #saleItm").selectmenu("disable");
					$( ".datepicker" ).datepicker("setDate", "0");
					
					$("#serviceMgtSvcRateItmTypListDateLabel").text('<spring:message code="LAB.M09.LAB00058" />');
				}
			}	
			
		);	
});	


function updateData(){
	var param = checkValidation();
	if(param != false){
		console.log(param);
		$.ajax({
			url : 'modSvcRateItmTyp.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M07.MSG00089" />');	//저장되었습니다.
					$("#serviceMgtSvcRateItmTypListGrid").trigger("reloadGrid");
					reloadProduct();
					$("#serviceMgtSvcRateItmTypListBtnClose").trigger('click');
					
				} else if (data.result == "-1") {
					alert('<spring:message code="MSG.M09.MSG00051" />');
					return;
				} else if (data.result == "-2") {
					alert('<spring:message code="MSG.M14.MSG00015" />');
					return;
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
	
	var soId = $.trim( $("#soId").val() );	//사업
	if(soId == ''){
		alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
		return false;
	}	
		
	var rateLocat = $("#rateLocat").val();	//요금영역
	if(rateLocat == ''){
		alert('<spring:message code="LAB.M01.LAB00132" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}

	var saleItm = $.trim( $("#saleItm").val() );	//요금영역
	if(saleItm == ''){
		alert('<spring:message code="LAB.M05.LAB00010" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}
	
	var invItm = $.trim( $("#invItm").val() );	//청구항목
	if(invItm == ''){
		alert('<spring:message code="LAB.M10.LAB00043" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	var dispPriNo = $("#dispPriNo").val();	//출력우선순위
	if(dispPriNo == ''){
		alert('<spring:message code="LAB.M10.LAB00081" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}	

	var ratePriNo = $("#ratePriNo").val();	//과금우선순위
	if(ratePriNo == ''){
		alert('<spring:message code="MSG.M01.MSG00066" /> <spring:message code="MSG.M01.MSG00066" />');
		return false;
	}	
	
	var actDt = $("#actDt").val();	//적용시작일
	
	if(actDt == ''){
		alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
		
	var svcCd = $("#serviceMgtSvcRateItmTypListUpdateSvcCd").val();
	var svcRateItmTypNm = $("#svcRateItmTypNm").val();
	var chrgCtgry = $("#chrgCtgry").val();
	var svcRateItmTypDesc = $("#svcRateItmTypDesc").val();
	var svcRateItmTypAbbrNm = $("#svcRateItmTypAbbrNm").val();
	var inActYn = $(':radio[name="serviceMgtSvcRateItmTypRadioInActYn"]:checked').val();
	var inactDt = $("#serviceMgtSvcRateItmTypListUpdateInactDt").val();
	var svcRateItmTypCd = $("#serviceMgtSvcRateItmTypListUpdateSvcRateItmTypCd").val();
	var baseActDt = $("#serviceMgtSvcRateItmTypListUpdateBaseActDt").val();
	var rateItmTyp = $("#rateItmTyp").val();
	var mstrFl = $("#serviceMgtSvcRateItmTypListUpdateMstrFl").val();
	var mainSvcCd = $("#serviceMgtSvcRateItmTypListUpdateMainSvcCd").val();
	
	param.soId = soId;
	param.svcCd = svcCd;
	param.rateItmTypCd = rateItmTyp;
	param.svcRateItmTypNm = svcRateItmTypNm;
	param.svcRateItmTypAbbrNm = svcRateItmTypAbbrNm;
	param.actDt = dateFormatToStringYYYYMMDD(actDt);
	
	param.chrgCtgry = chrgCtgry;
	param.rateLocat = rateLocat;
	param.saleItmCd = saleItm;
	param.invItmCd = invItm;
	param.dispPriNo = dispPriNo;
	param.ratePriNo = ratePriNo;
	param.svcRateItmTypDesc = svcRateItmTypDesc;
	param.inActYn = inActYn;
	param.inactDt = inactDt.replace(/\-/g,'');
	param.baseActDt = baseActDt;
	param.mstrFl = mstrFl;
	param.mainSvcCd = mainSvcCd;
	param.svcRateItmTypCd = svcRateItmTypCd;

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
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00175"/><!-- 서비스과금항목유형 --></h4>
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
						<input type="radio" id="serviceMgtSvcRateItmTypRadioInActYn" name="serviceMgtSvcRateItmTypRadioInActYn" value="1"checked="checked" />
						<label for="mstrFl"><spring:message code="LAB.M06.LAB00054" /></label>
						<input type="radio" id="serviceMgtSvcRateItmTypRadioInActYn" name="serviceMgtSvcRateItmTypRadioInActYn" value="2" /> 
						<label for="mstrFl2"> <spring:message code="LAB.M09.LAB00165" /></label>
					</div>	
				</td>	
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span>
				</th>
	            <td colspan="3">            	
					<select class="w100p" id="soId" name="soId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${serviceMgtInit.soId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>			
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00052"/><!-- 요금유형 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<input type="text" id="rateItmTypNm" name="rateItmTyp" value="${serviceMgtInit.rateItmTypNm}" class="w100p" disable> 
					<input hidden id="rateItmTyp" name="rateItmTyp" value="${serviceMgtInit.rateItmTypCd}" class="w100p">                                                                                   
				</td>	
			</tr>		
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00177"/><!-- 서비스과금항목유형명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<input type="text" id="svcRateItmTypNm" name="svcRateItmTypNm" value="${serviceMgtInit.svcRateItmTypNm}" class="w100p">                                            
	     		</td>
	 		</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00179"/><!-- 서비스과금항목유형약칭명 -->
				</th>
				<td colspan="3">
	       			<input type="text" id="svcRateItmTypAbbrNm" name="svcRateItmTypAbbrNm" value="${serviceMgtInit.svcRateItmTypAbbrNm}" class="w100p">                                            
	     		</td>
	 		</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M08.LAB00051"/><!-- 요금영역 -->
				</th>
				<td colspan="3">
	       			<input type="text" id="chrgCtgryNm" name="chrgCtgryNm" value="${serviceMgtInit.chrgCtgryNm}" class="w100p">  
	       			<input hidden id="chrgCtgry" name="chrgCtgry" value="${serviceMgtInit.chrgCtgry}" class="w100p">                                          
	     		</td>
	 		</tr>		 			 		
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00132"/><!-- 과금위치 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id=rateLocat name="rateLocat" class="w100p">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${rateLocat}" var="rateLocat" varStatus="status">
							<option value="${rateLocat.commonCd}"  <c:if test="${serviceMgtInit.rateLocat eq rateLocat.commonCd}"> selected="selected" </c:if> >${rateLocat.commonCdNm}</option>
						</c:forEach>
					</select>                                                                                   
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M05.LAB00010"/><!-- 매출항목 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id ="saleItm" name="saleItm" class="w100p">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${saleItm}" var="saleItm" varStatus="status">
							<option value="${saleItm.saleItmCd}"  <c:if test="${serviceMgtInit.saleItmCd eq saleItm.saleItmCd}"> selected="selected" </c:if> >${saleItm.saleItmNm}</option>
						</c:forEach>					
					</select>                                                                                   
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00043"/><!-- 청구항목 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id=invItm name="invItm" class="w100p">
				        <c:forEach items="${invItm}" var="invItm" varStatus="status">
							<option value="${invItm.invItmCd}" <c:if test="${serviceMgtInit.invItmCd eq invItm.invItmCd}"> selected="selected" </c:if> >${invItm.invItmNm}</option>
						</c:forEach>
					</select>                                                                                   
				</td>	
			</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M10.LAB00081"/><!-- 출력우선순위 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<input type="text" id="dispPriNo" name="dispPriNo" value="${serviceMgtInit.dispPriNo}" class="w200" numberonly="true">                                            
	     		</td>
	 		</tr>			
			<tr>		
				<th>
					<spring:message code="LAB.M01.LAB00274"/><!-- 과금우선순위 -->
				</th>
				<td colspan="3">
	       			<input type="text" id="ratePriNo" name="ratePriNo"  value="${serviceMgtInit.ratePriNo}"  class="w200" numberonly="true">                                            
	     		</td>
	 		</tr>		
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00178"/><!-- 서비스과금항목유형설명 -->
				</th>
				<td colspan="3">
	       			<textarea id="svcRateItmTypDesc" name="svcRateItmTypDesc" class="w100p h100">${serviceMgtInit.svcRateItmTypDesc}</textarea>                                           
	     		</td>
	 		</tr>
			<tr>	
				<th id="serviceMgtSvcRateItmTypListDateLabel">
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
	<a class="blue-btn" href="#" id="serviceMgtSvcRateItmTypListBtnUpdate"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="serviceMgtSvcRateItmTypListBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<input id="serviceMgtSvcRateItmTypListUpdateSvcCd" type='text' value="${serviceMgtInit.svcCd}"  hidden />
<input id="serviceMgtSvcRateItmTypListUpdateSvcRateItmTypCd" type='text' value="${serviceMgtInit.svcRateItmTypCd}"  hidden />
<input id="serviceMgtSvcRateItmTypListUpdateInactDt" type='text' value="${serviceMgtInit.inactDt}"  hidden />
<input id="serviceMgtSvcRateItmTypListUpdateBaseActDt" type='text' value="${serviceMgtInit.baseActDt}"  hidden />
<input id="serviceMgtSvcRateItmTypListUpdateMstrFl" type='text' value="${serviceMgtInit.mstrFl}"  hidden />
<input id="serviceMgtSvcRateItmTypListUpdateMainSvcCd" type='text' value="${targetMainSvcCd}"  hidden />

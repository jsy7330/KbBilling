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
		insertData();            
	});	
	
	$("#svcType, #subscripCmpsFl, #soId").selectmenu();
	$("#soId").selectmenu("disable");
	
});	

function insertData(){
	
	var param = checkValidation();
	
	if(param != false){
		
		$.ajax({
			url : 'insertServiceMgt.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					//$("#serviceMgtComListGrid").trigger("reloadGrid");
					reloadProduct();					
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
	var soId = $.trim( $("#soId").val() );	//사업
/*	
	if(soId == ''){
		alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
		return false;
	}
*/
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
	
	var actDt = $.trim( $("#actDt").val() );	//츨력우선순위
	if(actDt == ''){
		alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
	
	var svcAbbrNm = $("#svcAbbrNm").val();
	var targetMainSvcCd = $("#serviceMgtInsertMainSvcCd").val();
	var targetUpperCd = $("#serviceMgtInsertTargetUpperCd").val();
	
	param.soId = soId;
	param.svcNm = svcNm;
	param.svcTyp = svcType;
	param.subscripCmpsFl = subscripCmpsFl;
	param.dispPriNo = dispPriNo;
	
	param.actDt = dateFormatToStringYYYYMMDD(actDt);
	param.svcAbbrNm = svcAbbrNm;
	param.targetMainSvcCd = targetMainSvcCd;
	
	//기존은 선택한 TREE의 UpperCd를 기준으로 저장하였으나 soid 구별이 추가됨
	param.targetUpperCd = soId;	
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
					<spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span>
				</th>
	            <td colspan="3">
					<select class="w150" id="soId" name="soId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${serviceMgt.soId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>		 
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00185"/><!-- 서비스명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<input type="text" id="svcNm" name="svcNm" class="w200">                                            
	     		</td>
	 		</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00187"/><!-- 서비스약칭명 -->
				</th>
				<td colspan="3">
	       			<input type="text" id="svcAbbrNm" name="svcAbbrNm" class="w200">                                            
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
							<option value="${svcType.commonCd}">${svcType.commonCdNm}</option>
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
							<option value="${subscripCmpsFl.commonCd}">${subscripCmpsFl.commonCdNm}</option>
						</c:forEach>
					</select>                                                                                   
				</td>
				
			</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M10.LAB00081"/><!-- 출력우선순위 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<input type="text" id="dispPriNo" name="dispPriNo" class="w150" value="${dispPriNo}">                                            
	     		</td>
	 		</tr>
			<tr>	
				<th>
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
	<a class="blue-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<input id="serviceMgtInsertMainSvcCd" type='text' value="${targetMainSvcCd}"  hidden />
<input id="serviceMgtInsertTargetUpperCd" type='text' value="${targetUpperCd}"  hidden />	
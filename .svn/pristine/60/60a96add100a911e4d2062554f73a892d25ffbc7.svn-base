<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#modProductServiceInitPopupSubscripCmpsFl, #modProductServiceInitPopupEssFl").selectmenu()
	.selectmenu( "option", "width", "100%" );

	$("#modProductServiceInitPopupSvcCd, #modProductServiceInitPopupSvcNm").addClass('not-active');
	$("#modProductServiceInitPopupSvcCd, #modProductServiceInitPopupSvcNm").attr('disabled', true);		
	
	$('#modProductServiceInitPopupBtnInsert').click(function() {

		var param = checkValidation();        
       
		if(param != false){
			
			$.ajax({
				url : 'modProductService.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						$("#modProductServiceInitPopupBtnClose").trigger('click');
						$("#productServiceListGrid").trigger("reloadGrid");
						$("#tree").dynatree("getTree").reload();
						//reloadProduct();
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
		
	});	
	
	$("#modProductServiceInitPopupSubscripCmpsFl").selectmenu({
		change: function( event, ui ) {
			if ($(this).val() == "1") {
				$("#modProductServiceInitPopupEssFl").selectmenu("enable");
			} else {
				$("#modProductServiceInitPopupEssFl").selectmenu("disable");
				$('#modProductServiceInitPopupEssFl').val("").selectmenu('refresh');	
			}

		}
	});
	
	if ($("#modProductServiceInitPopupSubscripCmpsFl").val() == "1") {
		$("#modProductServiceInitPopupEssFl").selectmenu("enable");
	} else {
		$("#modProductServiceInitPopupEssFl").selectmenu("disable");
		$('#modProductServiceInitPopupEssFl').val("").selectmenu('refresh');
	}
});

function checkValidation(){
	var param = new Object();

	var subscripCmpsFl = $.trim( $("#modProductServiceInitPopupSubscripCmpsFl").val() );	
	if(subscripCmpsFl == ''){
		alert('<spring:message code="LAB.M01.LAB00007" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var essFl = $.trim( $("#modProductServiceInitPopupEssFl").val() );	
/* 	if(essFl == ''){
		alert('<spring:message code="LAB.M13.LAB00033" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	} */		
	
	
	var svcCd = $("#modProductServiceInitPopupSvcCd").val();
	var svcNm = $("#modProductServiceInitPopupSvcNm").val();
	var prodCd = $("#modProductServiceInitPopupProdCd").val();
	
	param.prodCd = prodCd;
	param.svcCd = svcCd;
	param.svcNm = svcNm;
	param.subscripCmpsFl = subscripCmpsFl;
	param.essFl = essFl;
	param.optSvcCmpsFl = "0";
	param.multiCmpsFl = "0";
	param.cmpsQtyFrom = "1";
	param.cmpsQtyTo = "1";
	
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
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00135"/></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:40%;">
			<col style="width:60%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00190"/><!-- 서비스코드 -->
				</th>
				<td>
					<input type="text" id="modProductServiceInitPopupSvcCd" name="modProductServiceInitPopupSvcCd" class="w100p" value="${modProductService.svcCd}">
				</td>	
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00185"/><!-- 서비스명 -->
				</th>
				<td>
					<input type="text" id="modProductServiceInitPopupSvcNm" name="modProductServiceInitPopupSvcNm" class="w100p" value="${productDevMgt.svcNm}">
				</td>	
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00007"/><!-- 가입계약구성여부 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="modProductServiceInitPopupSubscripCmpsFl" name="modProductServiceInitPopupSubscripCmpsFl">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${subscripCmpsFl}" var="subscripCmpsFl" varStatus="status">
							<option value="${subscripCmpsFl.commonCd}" <c:if test="${modProductService.subscripCmpsFl eq subscripCmpsFl.commonCd}"> selected="selected" </c:if> >${subscripCmpsFl.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M13.LAB00033"/><!-- 필수여부 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="modProductServiceInitPopupEssFl" name="modProductServiceInitPopupEssFl">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${essFl}" var="essFl" varStatus="status">
							<option value="${essFl.commonCd}" <c:if test="${modProductService.essFl eq essFl.commonCd}"> selected="selected" </c:if> >${essFl.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>								 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="modProductServiceInitPopupBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="modProductServiceInitPopupBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<input id="modProductServiceInitPopupProdCd" type='text' value="${productDevMgt.prodCd}"  hidden />
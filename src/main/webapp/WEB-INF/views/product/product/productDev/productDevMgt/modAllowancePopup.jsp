<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {

	$("select").selectmenu()
	.selectmenu( "option", "width", "100%" );
	
	$("#modAllowanceAlwnceCd").selectmenu("disable");	
	
	if($("#modAllowanceRateRefFl").val() == '0') {
		$("#modAllowanceRateVal").removeClass('not-active'); 
		$("#modAllowanceRateVal").removeAttr('disabled', true);	
		$("#modAllowanceRateFctrCd").selectmenu("disable");
	} else if ($("#modAllowanceRateRefFl").val() == '1') {
		$("#modAllowanceRateVal").addClass('not-active'); 
		$("#modAllowanceRateVal").attr('disabled', true);	
		$("#modAllowanceRateFctrCd").selectmenu("enable");
	}
	
	if($("#modAllowanceQtyRefFl").val() == '0') {
		$("#modAllowanceQty").removeClass('not-active'); 
		$("#modAllowanceQty").removeAttr('disabled', true);	
		$("#modAllowanceQtyFctrCd").selectmenu("disable");
	} else if ($("#modAllowanceQtyRefFl").val() == '1') {
		$("#modAllowanceQty").addClass('not-active'); 
		$("#modAllowanceQty").attr('disabled', true);	
		$("#modAllowanceQtyFctrCd").selectmenu("enable");
	}	
	
	if($("#modAllowanceCrncyRefFl").val() == '0') {
		$("#modAllowanceCrncyCd").selectmenu("enable");
		$("#modAllowanceCrncyFctrCd").selectmenu("disable");
	} else if ($("#modAllowanceCrncyRefFl").val() == '1') {
		$("#modAllowanceCrncyCd").selectmenu("disable");
		$("#modAllowanceCrncyFctrCd").selectmenu("enable");
	}	
	
	
	$("#modAllowanceRateRefFl").selectmenu({ //차후
		change: function( event, ui ) {	
			if($(this).val() == '0') {
				$("#modAllowanceRateVal").val("1");
				$("#modAllowanceRateVal").removeClass('not-active'); 
				$("#modAllowanceRateVal").removeAttr('disabled', true);	
				$("#modAllowanceRateFctrCd").selectmenu("disable");
			} else if ($(this).val() == '1') {
				$("#modAllowanceRateVal").val("");
				$("#modAllowanceRateVal").addClass('not-active'); 
				$("#modAllowanceRateVal").attr('disabled', true);	
				$("#modAllowanceRateFctrCd").selectmenu("enable");
			}	
		}
	});
			
	$("#modAllowanceQtyRefFl").selectmenu({ //차후
		change: function( event, ui ) {	
			if($(this).val() == '0') {
				$("#modAllowanceQty").val("1");
				$("#modAllowanceQty").removeClass('not-active'); 
				$("#modAllowanceQty").removeAttr('disabled', true);	
				$("#modAllowanceQtyFctrCd").selectmenu("disable");
			} else if ($(this).val() == '1') {
				$("#modAllowanceQty").val("");
				$("#modAllowanceQty").addClass('not-active'); 
				$("#modAllowanceQty").attr('disabled', true);	
				$("#modAllowanceQtyFctrCd").selectmenu("enable");
			}	
		}
	});	
	
	$("#modAllowanceCrncyRefFl").selectmenu({ //차후
		change: function( event, ui ) {	
			if($(this).val() == '0') {
				$("#modAllowanceCrncyCd").selectmenu("enable");
				$("#modAllowanceCrncyFctrCd").selectmenu("disable");
			} else if ($(this).val() == '1') {
				$("#modAllowanceCrncyCd").selectmenu("disable");
				$("#modAllowanceCrncyFctrCd").selectmenu("enable");
			}	
		}
	});		
	
 	$("#modAllowanceBtnUpdate").click(function() {
		updateData();            
	});	
	
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );}); 
	
});	

function updateData(){
	
	var param = checkValidation();
			
		$.ajax({
			url : 'modRate.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					$("#allowanceListGrid").trigger("reloadGrid");
					$("#tree").dynatree("getTree").reload();					
					$("#modAllowanceBtnClose").trigger('click');
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


function checkValidation(){
	var param = new Object();
	
	
	param.rateItmCd = $("#modAllowanceRateItmCd").val();
	param.rateRefFl = $("#modAllowanceRateRefFl").val();
	param.rateVal = $("#modAllowanceRateVal").val();
	if ($("#modAllowanceRateFctrCd").val() != "[]") {
		param.rateFctrCd = $("#modAllowanceRateFctrCd").val();
	}

	param.qtyRefFl = $("#modAllowanceQtyRefFl").val(); 
	
	if($("#modAllowanceQty").val()==null || $("#modAllowanceQty").val() ==""){
		param.qty = "0";
	}else{
		param.qty = $("#modAllowanceQty").val();
	}

	if ($("#modAllowanceQtyFctrCd").val() != "[]") {
		param.qtyFctrCd = $("#modAllowanceQtyFctrCd").val();
	}	
	
	param.crncyRefFl = $("#modAllowanceCrncyRefFl").val();
	param.crncyCd = $("#modAllowanceCrncyCd").val();
	if ($("#modAllowanceCrncyFctrCd").val() != "[]") {
		param.crncyFctrCd = $("#modAllowanceCrncyFctrCd").val();
	}			

	param.fctrVal1= $("#fctrVal1").val();
	param.fctrVal2= $("#fctrVal2").val();
	param.fctrVal3= $("#fctrVal3").val();
	param.fctrVal4= $("#fctrVal4").val();
	param.fctrVal5= $("#fctrVal5").val();
	param.fctrVal6= $("#fctrVal6").val();	
	param.fctrVal7= $("#fctrVal7").val();
	param.fctrVal8= $("#fctrVal8").val();
	param.fctrVal9= $("#fctrVal9").val();
		
	return param;
	
}

</script>
	
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M01.LAB00131"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00131"/><!-- 과금요율등록 --></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:20%;">
			<col style="width:10%;">
			<col style="width:20%;">
			<col style="width:15%;">
			<col style="width:20%;">
			
		</colgroup>
		<thead>

		 	<tr>
				<th>
					<c:if test="${!empty fctrList0}">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<c:if test="${1 eq fctr.fctrNo}"> ${fctr.fctrNm}</c:if>
						</c:forEach>
					</c:if>	
				</th>
	            <td>${rateList.fctrValNm1}</td>   
				<input type="hidden" id="fctrVal1" name="fctrVal1" class="w100p" value="${rateList.fctrVal1}">
				
				<th>
					<c:if test="${!empty fctrList1}">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<c:if test="${2 eq fctr.fctrNo}"> ${fctr.fctrNm}</c:if>
						</c:forEach>
					</c:if>
				</th>
	            <td>${rateList.fctrValNm2}</td>   
				<input type="hidden" id="fctrVal2" name="fctrVal2" class="w100p" value="${rateList.fctrVal2}">
				
				<th>
					<c:if test="${!empty fctrList2}">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<c:if test="${3 eq fctr.fctrNo}"> ${fctr.fctrNm}</c:if>
						</c:forEach>
					</c:if>	
				</th>
	            <td>${rateList.fctrValNm3}</td>  
				<input type="hidden" id="fctrVal3" name="fctrVal3" class="w100p" value="${rateList.fctrVal3}">				
			</tr>

		 	<tr>
				<th>
					<c:if test="${!empty fctrList3}">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<c:if test="${4 eq fctr.fctrNo}"> ${fctr.fctrNm}</c:if>
						</c:forEach>
					</c:if>	
				</th>
	            <td>${rateList.fctrValNm4}</td>   
				<input type="hidden" id="fctrVal4" name="fctrVal4" class="w100p" value="${rateList.fctrVal4}">
				<th>
					<c:if test="${!empty fctrList4}">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<c:if test="${5 eq fctr.fctrNo}"> ${fctr.fctrNm}</c:if>
						</c:forEach>
					</c:if>
				</th>
	            <td>${rateList.fctrValNm5}</td>    	
				<input type="hidden" id="fctrVal5" name="fctrVal5" class="w100p" value="${rateList.fctrVal5}">
				<th>
					<c:if test="${!empty fctrList5}">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<c:if test="${6 eq fctr.fctrNo}"> ${fctr.fctrNm}</c:if>
						</c:forEach>
					</c:if>	
				</th>
	            <td>${rateList.fctrValNm6}</td>   				
				<input type="hidden" id="fctrVal6" name="fctrVal6" class="w100p" value="${rateList.fctrVal6}">
			</tr>


		 	<tr>
				<th>
					<c:if test="${!empty fctrList6}">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<c:if test="${7 eq fctr.fctrNo}"> ${fctr.fctrNm}</c:if>
						</c:forEach>
					</c:if>	
				</th>
	            <td>${rateList.fctrValNm7}</td>
				<input type="hidden" id="fctrVal7" name="fctrVal7" class="w100p" value="${rateList.fctrVal7}">   
				<th>
					<c:if test="${!empty fctrList7}">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<c:if test="${8 eq fctr.fctrNo}"> ${fctr.fctrNm}</c:if>
						</c:forEach>
					</c:if>
				</th>
	            <td>${rateList.fctrValNm8}</td>    	
				<input type="hidden" id="fctrVal8" name="fctrVal8" class="w100p" value="${rateList.fctrVal8}">				
				<th>
					<c:if test="${!empty fctrList8}">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<c:if test="${9 eq fctr.fctrNo}"> ${fctr.fctrNm}</c:if>
						</c:forEach>
					</c:if>	
				</th>
	            <td>${rateList.fctrValNm9}</td>   
				<input type="hidden" id="fctrVal9" name="fctrVal9" class="w100p" value="${rateList.fctrVal9}">				
			</tr>
		 	<tr>
				<th>
					<spring:message code="LAB.M03.LAB00011"/><!-- 단가참조여부 -->
				</th>
	            <td>
					<select class="w100p" id="modAllowanceRateRefFl" name="modAllowanceRateRefFl">
						<c:forEach items="${rateRefFl}" var="rateRefFl" varStatus="status">
							<option value="${rateRefFl.commonCd}" <c:if test="${rateList.rateRefFl eq rateRefFl.commonCd}"> selected="selected" </c:if>>${rateRefFl.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
				<th>
					<spring:message code="LAB.M03.LAB00006"/><!-- 단가 -->
				</th>
				<td>
	       			<input type="text" id="modAllowanceRateVal" name="modAllowanceRateVal" value="${rateList.rateVal}" class="w100p" numberOnly>                                            
	     		</td>
				<th>
					<spring:message code="LAB.M03.LAB00012"/><!-- 단가참조요소 -->
				</th>
	            <td>
					<select class="w100p" id="modAllowanceRateFctrCd" name="modAllowanceRateFctrCd">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<option value="${fctr.fctrCd}" <c:if test="${rateList.rateFctrCd eq fctr.fctrCd}"> selected="selected" </c:if> >${fctr.fctrNm}</option>
						</c:forEach>
					</select>                                            
				</td>	     		                                          							 	
		 	</tr>
		 	<tr>
				<th>
					<spring:message code="LAB.M07.LAB00248"/><!-- 수량참조여부 -->
				</th>
	            <td>
					<select class="w100p" id="modAllowanceQtyRefFl" name="modAllowanceQtyRefFl">
						<c:forEach items="${qtyRefFl}" var="qtyRefFl" varStatus="status">
							<option value="${qtyRefFl.commonCd}" <c:if test="${rateList.qtyRefFl eq qtyRefFl.commonCd}"> selected="selected" </c:if> >${qtyRefFl.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
				<th>
					<spring:message code="LAB.M07.LAB00247"/><!-- 수량 -->
				</th>
				<td>
	       			<input type="text" id="modAllowanceQty" name="modAllowanceQty" value="${rateList.qty}" class="w100p" numberOnly>                                            
	     		</td>
				<th>
					<spring:message code="LAB.M07.LAB00249"/><!-- 수량참조요소 -->
				</th>
	            <td>
					<select class="w100p" id="modAllowanceQtyFctrCd" name="modAllowanceQtyFctrCd">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<option value="${fctr.fctrCd}" <c:if test="${rateList.qtyFctrCd eq fctr.fctrCd}"> selected="selected" </c:if>>${fctr.fctrNm}</option>
						</c:forEach>
					</select>                                            
				</td>	     		                                          							 	
		 	</tr>
		 	<tr>
				<th>
					<spring:message code="LAB.M12.LAB00003"/><!-- 통화참조여부 -->
				</th>
	            <td>
					<select class="w100p" id="modAllowanceCrncyRefFl" name="modAllowanceCrncyRefFl">
						<c:forEach items="${crncyRefFl}" var="crncyRefFl" varStatus="status">
							<option value="${crncyRefFl.commonCd}" <c:if test="${rateList.crncyRefFl eq crncyRefFl.commonCd}"> selected="selected" </c:if>>${crncyRefFl.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
				<th>
					<spring:message code="LAB.M12.LAB00005"/><!-- 통화코드 -->
				</th>
				<td>
					<select class="w100p" id="modAllowanceCrncyCd" name="modAllowanceCrncyCd">
						<c:forEach items="${crncyCd}" var="crncyCd" varStatus="status">
							<option value="${crncyCd.commonCd}" <c:if test="${rateList.crncyCd eq crncyCd.commonCd}"> selected="selected" </c:if> >${crncyCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
	     		</td>
				<th>
					<spring:message code="LAB.M12.LAB00004"/><!-- 통화참조요소 -->
				</th>
	            <td>
					<select class="w100p" id="modAllowanceCrncyFctrCd" name="modAllowanceCrncyFctrCd">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<option value="${fctr.fctrCd}" <c:if test="${rateList.crncyFctrCd eq fctr.fctrCd}"> selected="selected" </c:if> >${fctr.fctrNm}</option>
						</c:forEach>
					</select>                                            
				</td>	     		                                          							 	
		 	</tr>
		 	<tr>
				<th>
					<spring:message code="LAB.M01.LAB00085"/><!-- 공제코드 -->
				</th>
	            <td colspan="3">
					<select class="w100p" id="modAllowanceAlwnceCd" name="modAllowanceAlwnceCd">
					</select>                                            
				</td>	     		                                          							 	
		 	</tr>		 			 			 					 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="modAllowanceBtnUpdate"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="modAllowanceBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<input id="modAllowanceRateItmCd" type='text' value="${productDevMgt.rateItmCd}"  hidden />

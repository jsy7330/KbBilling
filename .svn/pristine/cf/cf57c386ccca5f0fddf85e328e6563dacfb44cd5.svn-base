<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {

	$("#btnInsert").click(function() {
		updateData();            
	});	
	
	$(".search").css("margin-top", "3px");
	
	$("#soId, #subsFl, #basicProdFl, #useMmTyp, #svcGrp").selectmenu()
		.selectmenu( "option", "width", "100%" );
	
	$("#prodTypNm, #prodGrp").addClass('not-active'); 
	$("#prodTypNm, #prodGrp").attr('disabled', true);	
	
	$("#basicProdFl").selectmenu("disable");
	
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
	
	$('#mnoProdCd').click(function() {
		if ($("#productDevMgtProductListProdTyp").val() == "23" || $("#productDevMgtProductListProdTyp").val() == "21") {
			var url="getEqtClCdComboListPopUp.ajax";
			$(parent.location).attr("href", "javascript:openModal3_sub('" + url + "');");
		}
		
	});		
	
	
});	

function updateData(){
	
	var param = checkValidation();
	
	if(param != false){
		
		$.ajax({
			url : 'modProduct.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					//$("#productDevMgtProductListGrid").trigger("reloadGrid");
					reloadProduct();
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
				reloadProduct();
			}
		});
	}
}

function checkValidation(){
	var param = new Object();
	
	var soId = $.trim( $("#soId").val() );	
	if(soId == ''){
		alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	var prodNm = $.trim( $("#prodNm").val() );	
	if(prodNm == ''){
		alert('<spring:message code="LAB.M07.LAB00130" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}			
	
	var subsFl = $.trim( $("#subsFl").val() );	
	if(subsFl == ''){
		alert('<spring:message code="LAB.M10.LAB00046" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}			

	var basicProdFl = $.trim( $("#basicProdFl").val() );	
	if(basicProdFl == ''){
		alert('<spring:message code="LAB.M01.LAB00212" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	var useMmTyp = $.trim( $("#useMmTyp").val() );	
	if(useMmTyp == ''){
		alert('<spring:message code="LAB.M07.LAB00027" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	var svcGrp = $.trim( $("#svcGrp").val() );	
	if(svcGrp == ''){
		alert('<spring:message code="LAB.M07.LAB00183" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var dispPriNo = $.trim( $("#dispPriNo").val() );
	if(dispPriNo == ''){
		alert('<spring:message code="LAB.M10.LAB00081" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
	
	var prodLvl = $.trim( $("#prodLvl").val() );
	if(prodLvl == ''){
		alert('<spring:message code="LAB.M07.LAB00115" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
	
	var modDesc = $.trim( $("#modDesc").val() );
	if(modDesc == ''){
		alert('<spring:message code="LAB.M06.LAB00059" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
	
	var prodDesc = $.trim( $("#prodDesc").val() );
	if(prodDesc == ''){
		alert('<spring:message code="LAB.M07.LAB00143" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
		
	var prodTyp = $.trim( $("#prodTyp").val() );
	var prodGrp = $.trim( $("#prodGrp").val() );	
	var prodAbbrNm = $("#prodAbbrNm").val();
	var mnoProdCd = $("#productListUpdateMnoProdCd").val();
	var baseSoId = $("#baseSoId").val();
	var prodCd = $("#productDevMgtProductListProdCd").val();
	
	
	param.soId = soId;
	param.prodTyp = prodTyp;
	param.prodNm = prodNm;
	param.prodGrp = prodGrp;
	param.subsFl = subsFl;
	param.basicProdFl = basicProdFl;
	param.useMmTyp = useMmTyp;
	param.svcGrp = svcGrp;
	param.dispPriNo = dispPriNo;
	param.prodLvl = prodLvl;
	param.modDesc = modDesc;
	param.prodDesc = prodDesc;
	param.prodAbbrNm = prodAbbrNm;
	param.mnoProdCd = mnoProdCd;
	param.prodEngNm = "";
	param.prodEngAbbrNm = "";
	param.beforeSoId = baseSoId;
	param.prodCd = prodCd;
	
		
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
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00119"/><!-- 상풍개발관리등록 --></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:30%;">
			<col style="width:20%;">
			<col style="width:30%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span>
				</th>
	            <td>
					<select class="w100p" id="soId" name="soId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${modProductInit.soId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
				<th>
					<spring:message code="LAB.M07.LAB00146"/><!-- 상품유형 --><span class="dot">*</span>
				</th>				
	            <td>
					<input type="text" id="prodTypNm" name="prodTypNm" value="${modProductInit.prodTypNm}" class="w100p">                                         
				</td>				
			</tr>			
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00130"/><!-- 상품명 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="prodNm" name="prodNm" value="${modProductInit.prodNm}" class="w100p">                                            
	     		</td>
				<th>
					<spring:message code="LAB.M07.LAB00126"/><!-- 상품군 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="prodGrp" name="prodGrp" value="${modProductInit.prodGrpNm}" class="w100p">                                            
	     		</td>	     		
	     		
	 		</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00144"/><!-- 상품약칭명 -->
				</th>
				<td>
	       			<input type="text" id="prodAbbrNm" name="prodAbbrNm" value="${modProductInit.prodAbbrNm}" class="w100p">                                            
	     		</td>
				<th>
					<spring:message code="LAB.M10.LAB00046"/><!-- 청약가능여부 --><span class="dot">*</span>
				</th>
				<td>
					<select class="w100p" id="subsFl" name="subsFl">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${subsFl}" var="subsFl" varStatus="status">
							<option value="${subsFl.commonCd}" <c:if test="${modProductInit.subsFl eq subsFl.commonCd}"> selected="selected" </c:if> >${subsFl.commonCdNm}</option>
						</c:forEach>
					</select>                                             
	     		</td>	     		
	 		</tr>
	 		<tr>
				<th>
					<spring:message code="LAB.M01.LAB00212"/><!-- 기본상품여부 --><span class="dot">*</span>
				</th>	 		
				<td>
					<select class="w100p" id="basicProdFl" name="basicProdFl">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${basicProdFl}" var="basicProdFl" varStatus="status">
							<option value="${basicProdFl.commonCd}" <c:if test="${modProductInit.basicProdFl eq basicProdFl.commonCd}"> selected="selected" </c:if> >${basicProdFl.commonCdNm}</option>
						</c:forEach>
					</select>                                             
	     		</td>
	     		<th>
					<spring:message code="LAB.M07.LAB00027"/><!-- 사용월구분 --><span class="dot">*</span>
				</th>	 		
				<td>
					<select class="w100p" id="useMmTyp" name="useMmTyp">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${useMmTyp}" var="useMmTyp" varStatus="status">
							<option value="${useMmTyp.commonCd}" <c:if test="${modProductInit.useMmTyp eq useMmTyp.commonCd}"> selected="selected" </c:if> >${useMmTyp.commonCdNm}</option>
						</c:forEach>
					</select>                                             
	     		</td>
	 		</tr>
	 		<tr>
				<th>
					<spring:message code="LAB.M07.LAB00183"/><!-- 서비스군 --><span class="dot">*</span>
				</th>	 		
				<td>
					<select class="w100p" id="svcGrp" name="svcGrp">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${svcGrp}" var="svcGrp" varStatus="status">
							<option value="${svcGrp.commonCd}" <c:if test="${modProductInit.svcGrp eq svcGrp.commonCd}"> selected="selected" </c:if> >${svcGrp.commonCdNm}</option>
						</c:forEach>
					</select>                                             
	     		</td>
				<th>
					<spring:message code="LAB.M10.LAB00081"/><!-- 출력우선순위 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="dispPriNo" name="dispPriNo" value="${modProductInit.dispPriNo}" class="w100p">                                            
	     		</td>	     			 		
	 		</tr>
	 		<tr>
				<th>
					<spring:message code="LAB.M07.LAB00115"/><!-- 상품Level --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="prodLvl" name="prodLvl" value="${modProductInit.prodLvl}" class="w100p">                                            
	     		</td>
				<th>
					<spring:message code="LAB.M15.LAB00061"/><!-- 상품Level --><span class="dot">*</span>
				</th>
				<td>
					<input id="productListUpdateMnoProdCd" name="productListUpdateMnoProdCd" value="${modProductInit.mnoProdCd}" type="text"   class="w180 not-active" disable> 
					<a href="#" id="mnoProdCd" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>			 		                                         
	     		</td>	     								
	 		</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M06.LAB00059"/><!-- 변경설명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<textarea id="modDesc" name="modDesc" class="w100p h100">${modProductInit.modDesc}</textarea>                                           
	     		</td>
	 		</tr>	 
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00143"/><!-- 상품설명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<textarea id="prodDesc" name="prodDesc" class="w100p h100">${modProductInit.prodDesc}</textarea>                                           
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

<input id="baseSoId" type='text' value="${modProductInit.soId}"  hidden />
<input id="productDevMgtProductListProdCd" type='text' value="${modProductInit.prodCd}"  hidden />
<input id="productDevMgtProductListProdTyp" type='text' value="${modProductInit.prodTyp}"  hidden />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {

	$("#btnInsert").click(function() {
		insertData();            
	});	
	
	$("#productDevMgtCopySoId, #productDevMgtCopyProdTyp, #productDevMgtCopyProdGrp, #productDevMgtCopySubsFl, #productDevMgtCopyBasicProdFl, #productDevMgtCopySvcGrp, #productDevMgtCopyUseMmTyp").selectmenu()
		.selectmenu( "option", "width", "100%" );
	
	// $("#productDevMgtCopySoId").selectmenu("disable");
	
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
	
	$(".search").css("margin-top", "3px");
	
	$('#cpPdSearch').click(function() {
		var url="getProductRelComboListPopUp.ajax";
		$(parent.location).attr("href", "javascript:openModal2_sub('" + url + "');");
		
	});		
	
	$('#mnoProdCd').click(function() {
		if ($("#productDevMgtCopyProdTyp").val() == "23" ) {
			var url="getEqtClCdComboListPopUp.ajax";
			$(parent.location).attr("href", "javascript:openModal3_sub('" + url + "');");
		}
		
	});	
	
});	

$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});	

function insertData(){
	
	var param = checkValidation();
	
	if(param != false){
		
		$.ajax({
			url : 'addCopyProduct.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					
					$("#btnClose").trigger('click');
					$("#tree").dynatree("getTree").reload();
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
	
	var soId = $.trim( $("#productDevMgtCopySoId").val() );	
	if(soId == ''){
		alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	var prodTyp = $.trim( $("#productDevMgtCopyProdTyp").val() );	
	if(prodTyp == ''){
		alert('<spring:message code="LAB.M07.LAB00146" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	var prodNm = $.trim( $("#productDevMgtCopyProdNm").val() );	
	if(prodNm == ''){
		alert('<spring:message code="LAB.M07.LAB00130" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	

	var prodGrp = $.trim( $("#productDevMgtCopyProdGrp").val() );	
	if(prodGrp == ''){
		alert('<spring:message code="LAB.M07.LAB00126" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var subsFl = $.trim( $("#productDevMgtCopySubsFl").val() );	
	if(subsFl == ''){
		alert('<spring:message code="LAB.M10.LAB00046" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}			

	var basicProdFl = $.trim( $("#productDevMgtCopyBasicProdFl").val() );	
	if(basicProdFl == ''){
		alert('<spring:message code="LAB.M01.LAB00212" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	var useMmTyp = $.trim( $("#productDevMgtCopyUseMmTyp").val() );	
	if(useMmTyp == ''){
		alert('<spring:message code="LAB.M07.LAB00027" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	var svcGrp = $.trim( $("#productDevMgtCopySvcGrp").val() );	
	if(svcGrp == ''){
		alert('<spring:message code="LAB.M07.LAB00183" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var dispPriNo = $.trim( $("#dispPriNo").val() );
	if(dispPriNo == ''){
		alert('<spring:message code="LAB.M10.LAB00081" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
	
	var prodLvl = $.trim( $("#productDevMgtCopyProdLvl").val() );
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
	
	
	var oldProdCd = $.trim( $("#oldProdCd").val() );
	if(oldProdCd == ''){
		alert('<spring:message code="MSG.M06.MSG00027" />');
		return false;
	}		
		
	var prodAbbrNm = $("#productDevMgtCopyProdAbbrNm").val();
	var mnoProdCd = $("#mnoProdCd").val();
	
	var usgRatePriNo = "";
	if(prodTyp == '11'){
		usgRatePriNo = "1000"
	}else if(prodTyp == '21'){
		usgRatePriNo = "100"
	}else if(prodTyp == '23'){
		usgRatePriNo = "1"
	}else if(prodTyp == '80'){
		usgRatePriNo = "1"
	}

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
	param.oldProdCd = oldProdCd;
	param.usgRatePriNo = usgRatePriNo;
	
	return param;
	
}

</script>
	
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00153"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">
		</colgroup>
		<tr>
			<th>
				<spring:message code="LAB.M06.LAB00081"/><!-- 복사대상상품 -->
			</th>
            <td>
				<input type="text" id="cpPdNm" name="cpPdNm" class="w580">
				<a href="#" id="cpPdSearch" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>                                       
			</td>		
		</tr>		
	</table>
	<div class="ly_btn_box">
		<div class="fl">
			<h3 class="ly_title"><spring:message code="LAB.M03.LAB00075"/><!-- 등록 --></h3>
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
					<select class="w100p" id="productDevMgtCopySoId" name="productDevMgtCopySoId">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${productDevMgt.soId eq soAuthList.so_id}"> selected="selected" </c:if>	>${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
				<th>
					<spring:message code="LAB.M07.LAB00146"/><!-- 상품유형 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="productDevMgtCopyProdTyp" name="productDevMgtCopyProdTyp">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${prodTyp}" var="prodTyp" varStatus="status">
							<option value="${prodTyp.commonCd}">${prodTyp.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>				
			</tr>			
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00130"/><!-- 상품명 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="productDevMgtCopyProdNm" name="productDevMgtCopyProdNm" class="w100p">                                            
	     		</td>
				<th>
					<spring:message code="LAB.M07.LAB00126"/><!-- 상품군 --><span class="dot">*</span>
				</th>
				<td>
					<select class="w100p" id="productDevMgtCopyProdGrp" name="productDevMgtCopyProdGrp">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${prodGrp}" var="prodGrp" varStatus="status">
							<option value="${prodGrp.commonCd}">${prodGrp.commonCdNm}</option>
						</c:forEach>
					</select>                                             
	     		</td>	     		
	     		
	 		</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00144"/><!-- 상품약칭명 -->
				</th>
				<td>
	       			<input type="text" id="productDevMgtCopyProdAbbrNm" name="productDevMgtCopyProdAbbrNm" class="w100p">                                            
	     		</td>
				<th>
					<spring:message code="LAB.M10.LAB00046"/><!-- 청약가능여부 --><span class="dot">*</span>
				</th>
				<td>
					<select class="w100p" id="productDevMgtCopySubsFl" name="productDevMgtCopySubsFl">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${subsFl}" var="subsFl" varStatus="status">
							<option value="${subsFl.commonCd}">${subsFl.commonCdNm}</option>
						</c:forEach>
					</select>                                             
	     		</td>	     		
	 		</tr>
	 		<tr>
				<th>
					<spring:message code="LAB.M01.LAB00212"/><!-- 기본상품여부 --><span class="dot">*</span>
				</th>	 		
				<td>
					<select class="w100p" id="productDevMgtCopyBasicProdFl" name="productDevMgtCopyBasicProdFl">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${basicProdFl}" var="basicProdFl" varStatus="status">
							<option value="${basicProdFl.commonCd}">${basicProdFl.commonCdNm}</option>
						</c:forEach>
					</select>                                             
	     		</td>
	     		<th>
					<spring:message code="LAB.M07.LAB00027"/><!-- 사용월구분 --><span class="dot">*</span>
				</th>	 		
				<td>
					<select class="w100p" id="productDevMgtCopyUseMmTyp" name="productDevMgtCopyUseMmTyp">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${useMmTyp}" var="useMmTyp" varStatus="status">
							<option value="${useMmTyp.commonCd}">${useMmTyp.commonCdNm}</option>
						</c:forEach>
					</select>                                             
	     		</td>
	 		</tr>
	 		<tr>
				<th>
					<spring:message code="LAB.M07.LAB00183"/><!-- 서비스군 --><span class="dot">*</span>
				</th>	 		
				<td>
					<select class="w100p" id="productDevMgtCopySvcGrp" name="productDevMgtCopySvcGrp">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${svcGrp}" var="svcGrp" varStatus="status">
							<option value="${svcGrp.commonCd}">${svcGrp.commonCdNm}</option>
						</c:forEach>
					</select>                                             
	     		</td>
				<th>
					<spring:message code="LAB.M10.LAB00081"/><!-- 출력우선순위 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="dispPriNo" name="dispPriNo" value="${dispPriNo}" class="w100p">                                            
	     		</td>	     			 		
	 		</tr>
	 		<tr>
				<th>
					<spring:message code="LAB.M07.LAB00115"/><!-- 상품Level --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="productDevMgtCopyProdLvl" name="productDevMgtCopyProdLvl" value="" class="w100p" numberOnly>                                            
	     		</td>
				<th>
					<spring:message code="LAB.M15.LAB00061"/><!-- 상품Level -->
				</th>
				<td>
					<input id="productDevMgtCopyMnoProdCd" name="productDevMgtCopyMnoProdCd" value="" type="text" class="w180 not-active" disable> 
					<a href="#" id="mnoProdCd" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>			 		                                         
	     		</td>	     								
	 		</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M06.LAB00059"/><!-- 변경설명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<textarea id="modDesc" name="modDesc" class="w100p h100"></textarea>                                           
	     		</td>
	 		</tr>	 
			<tr>		
				<th>
					<spring:message code="LAB.M07.LAB00143"/><!-- 상품설명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<textarea id="prodDesc" name="prodDesc" class="w100p h100"></textarea>                                           
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
<input id="oldProdCd" type='text' value=""  hidden />

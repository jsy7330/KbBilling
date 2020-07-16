<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {


	$("#addSuprtEquipPopUpSbjProdCd, #addSuprtEquipPopUpEqtClNm, #addSuprtEquipPopUpSvcCd").selectmenu()
	.selectmenu( "option", "width", "100%" );

	$("#addSuprtEquipPopUpSbjProdCd").selectmenu({
		change: function( event, ui ) {
			
			var param = new Object();
			param.sbjProdCd = $(this).val();
			
			$.ajax({
				url : 'getSbjSvcCdComboList.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					var options = [];
					
					$('#addSuprtEquipPopUpSvcCd').find('option').remove(); 
					
					$.each(data.sbjSvcCdComboList, function(i, item) {
						options.push('<option value="' + data.sbjSvcCdComboList[i].svcCd + '">' + data.sbjSvcCdComboList[i].svcNm + '</option>');
					});
					
				    $('#addSuprtEquipPopUpSvcCd')
			        .append(options.join(""))
			        .selectmenu();
				   
				    $('#addSuprtEquipPopUpSvcCd').val("[]").selectmenu('refresh');		
				},
			    error: function(request,status,error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    },
				complete : function() {
				}
			});				
			
		}
	});
	
	
	$('#addSuprtEquipPopUpBtnInsert').click(function() {

		var param = checkValidation();        
       
		if(param != false){
			
			$.ajax({
				url : 'addSuprtEquip.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						$("#addSuprtEquipPopUpBtnClose").trigger('click');
						$("#suprtEquipListGrid").trigger("reloadGrid");
						reloadProduct();
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
 
});

function checkValidation(){
	var param = new Object();

	var sbjProdCd = $.trim( $("#addSuprtEquipPopUpSbjProdCd").val() );	
	if(sbjProdCd == '[]'){
		alert('<spring:message code="LAB.M03.LAB00053" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var eqtClCd = $.trim( $("#addSuprtEquipPopUpEqtClNm").val() );	
	if(eqtClCd == '[]'){
		alert('<spring:message code="LAB.M09.LAB00043" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	var svcCd = $.trim( $("#addSuprtEquipPopUpSvcCd").val() );	
	if(svcCd == '[]'){
		alert('<spring:message code="LAB.M07.LAB00190" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var cmpsQtyFrom = $.trim( $("#addSuprtEquipPopUpCmpsQtyFrom").val() );
	if(cmpsQtyFrom == ''){
		alert('<spring:message code="LAB.M01.LAB00166" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}	

	var cmpsQtyTo = $.trim( $("#addSuprtEquipPopUpCmpsQtyTo").val() );
	if(cmpsQtyTo == ''){
		alert('<spring:message code="LAB.M01.LAB00167" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}	
	
	var dispPriNo = $.trim( $("#addSuprtEquipPopUpDispPriNo").val() );
	if(dispPriNo == ''){
		alert('<spring:message code="LAB.M10.LAB00081" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
	
	var prodCd = $("#addSuprtEquipPopUpPopupProdCd").val();
	
	param.prodCd = prodCd;
	param.svcCd = svcCd;
	param.eqtClCd = eqtClCd;
	param.sbjProdCd = sbjProdCd;
	param.cmpsQtyFrom = cmpsQtyFrom;
	param.cmpsQtyTo = cmpsQtyTo;
	param.dispPriNo = dispPriNo;
	
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
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00046"/></h4>
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
					<spring:message code="LAB.M03.LAB00053"/><!-- 대상상품코드 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select class="w100p" id="addSuprtEquipPopUpSbjProdCd" name="addSuprtEquipPopUpSbjProdCd">
						<c:forEach items="${sbjProdCdCombo}" var="sbjProdCdCombo" varStatus="status">
							<option value="${sbjProdCdCombo.prodCd}">${sbjProdCdCombo.prodNm}</option>
						</c:forEach>
					</select>  
				</td>	
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00043"/><!-- 대상상품코드 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select class="w100p" id="addSuprtEquipPopUpEqtClNm" name="addSuprtEquipPopUpEqtClNm">
						<c:forEach items="${eqtClCdCombo}" var="eqtClCdCombo" varStatus="status">
							<option value="${eqtClCdCombo.eqtClCd}">${eqtClCdCombo.eqtClNm}</option>
						</c:forEach>
					</select>  
				</td>	
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00190"/><!-- 대상상품코드 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select class="w100p" id="addSuprtEquipPopUpSvcCd" name="addSuprtEquipPopUpSvcCd">
					</select>  
				</td>	
			</tr>					
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00166"/><!-- 서비스명 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="addSuprtEquipPopUpCmpsQtyFrom" name="addSuprtEquipPopUpCmpsQtyFrom" class="w100p" value="1">
				</td>
				<th>
					<spring:message code="LAB.M01.LAB00167"/><!-- 서비스명 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="addSuprtEquipPopUpCmpsQtyTo" name="addSuprtEquipPopUpCmpsQtyTo" class="w100p" value="1">
				</td>					
			</tr>			
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00081"/><!-- 서비스명 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="addSuprtEquipPopUpDispPriNo" name="addSuprtEquipPopUpDispPriNo" class="w150" value="${nextDispPriNo}">
				</td>	
			</tr>						 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="addSuprtEquipPopUpBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="addSuprtEquipPopUpBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<input id="addSuprtEquipPopUpPopupProdCd" type='text' value="${productDevMgt.prodCd}"  hidden />
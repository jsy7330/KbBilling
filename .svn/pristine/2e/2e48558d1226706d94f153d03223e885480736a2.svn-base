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
	$("#addRateItmInitPopupSvcRateItmTyp, #addRateItmInitPopupRateLocat, #addRateItmInitPopupRateRefTyp, #addRateItmInitPopupDefltRateItm").selectmenu()
	.selectmenu( "option", "width", "100%" );	
	$("#addRateItmInitPopupDefltRateItm").selectmenu("disable");
	$("#addRateItmInitPopupChrgCtgry").addClass('not-active'); 
	$("#addRateItmInitPopupChrgCtgry").attr('disabled', true);
	
	$("#addRateItmInitPopupSvcRateItmTyp").selectmenu({
		change: function( event, ui ) {
			
			$("#addRateItmInitPopupChrgCtgry").val($(ui.item.element).data('chrgctgrynm'));
			$("#addRateItmInitPopupRateItmNm").val($(ui.item.element).data('rateitmnm'));
			$('#addRateItmInitPopupRateLocat').val($(ui.item.element).data('ratelocat')).selectmenu('refresh');
			ratePriNo = $(ui.item.element).data('rateprino');
			dispPriNo = $(ui.item.element).data('dispprino');
			chrgCtgry = $(ui.item.element).data('chrgctgry');
			
			var param = new Object();
			param.prodCd = $("#addRateItmInitPopupProdCd").val();
			param.svcCd = $("#addRateItmInitPopupSvcCd").val();
			param.svcRateItmTypCd = $(this).val();
			
			$.ajax({
				url : 'getDefltRateItemList.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					var options = [];
					
					$('#addRateItmInitPopupDefltRateItm').find('option').remove(); 
					
					$.each(data.defltRateItemList, function(i, item) {
						options.push('<option value="' + data.defltRateItemList[i].rateItmCd + '">' + data.defltRateItemList[i].rateItmNm + '</option>');
					});
					
				    $('#addRateItmInitPopupDefltRateItm')
			        .append(options.join(""))
			        .selectmenu();
				   
				    $('#addRateItmInitPopupDefltRateItm').val("[]").selectmenu('refresh');	
				    //$("#addRateItmInitPopupDefltRateItm").selectmenu("enable");
				},
			    error: function(request,status,error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    },
				complete : function() {
				}
			});				
			
		}
	});	

	$("#addRateItmInitPopupRateRefTyp").selectmenu({
		change: function( event, ui ) {	
			if ($(this).val() == "1") {
				$("#addRateItmInitPopupDefltRateItm").selectmenu("enable");
			} else {
				$("#addRateItmInitPopupDefltRateItm").selectmenu("disable");
			}
		}
	});		
		
	$('#addRateItmInitPopupBtnInsert').click(function() {

		var param = checkValidation();        
       
		if(param != false){
			
			$.ajax({
				url : 'addRateItm.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						$("#addRateItmInitPopupPopupBtnClose").trigger('click');
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

	var svcRateItmTyp = $.trim( $("#addRateItmInitPopupSvcRateItmTyp").val() );	
	if(svcRateItmTyp == ''){
		alert('<spring:message code="LAB.M07.LAB00175" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var rateItmNm = $("#addRateItmInitPopupRateItmNm").val();	//요소명
	if(rateItmNm == ''){
		alert('<spring:message code="LAB.M01.LAB00152" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		
	
	var rateLocat = $.trim( $("#addRateItmInitPopupRateLocat").val() );	
	if(rateLocat == ''){
		alert('<spring:message code="LAB.M01.LAB00148" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}
	
	var rateRefTyp = $.trim( $("#addRateItmInitPopupRateRefTyp").val() );	
	if(rateRefTyp == ''){
		alert('<spring:message code="LAB.M08.LAB00077" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	
	
	param.svcRateItmTypCd = svcRateItmTyp;
	param.rateItmNm = rateItmNm;
	param.prodCd = $("#addRateItmInitPopupProdCd").val();
	param.svcCd = $("#addRateItmInitPopupSvcCd").val();
	param.rateLocat = rateLocat;
	param.rateRefTyp = rateRefTyp;
	param.chrgCtgry = chrgCtgry;
	param.ratePriNo = ratePriNo;
	
	if(dispPriNo.length==null){
		param.dispPriNo = "0";

	}else{
		param.dispPriNo = dispPriNo;
		
	}
	param.defltRateItmCd = $("#addRateItmInitPopupDefltRateItm").val();
	param.mnoRateItmCd = $("#addRateItmInitPopupMnoRateItmCd").val();
	
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
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00150"/></h4>
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
					<select class="w100p" id="addRateItmInitPopupSvcRateItmTyp" name="addRateItmInitPopupSvcRateItmTyp">
						<c:forEach items="${svcRateItmTyp}" var="svcRateItmTyp" varStatus="status">
							<option value="${svcRateItmTyp.svcRateItmTypCd}" data-chrgCtgryNm="${svcRateItmTyp.chrgCtgryNm}" 
							data-rateItmNm="${svcRateItmTyp.rateItmNm}" data-rateLocat="${svcRateItmTyp.rateLocat}" 
							data-ratePriNo="${svcRateItmTyp.ratePriNo}" data-dispPriNo="${svcRateItmTyp.dispPriNo}" 
							data-chrgCtgry="${svcRateItmTyp.chrgCtgry}"  >${svcRateItmTyp.svcRateItmTypNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00051"/><!-- 요금영역 -->
				</th>
				<td>
					<input type="text" id="addRateItmInitPopupChrgCtgry" name="addRateItmInitPopupChrgCtgry" class="w100p">
				</td>	
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00152"/><!-- 과금항목명 --><span class="dot">*</span>
				</th>
				<td>
					<input type="text" id="addRateItmInitPopupRateItmNm" name="addRateItmInitPopupRateItmNm" class="w100p">
				</td>	
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00112"/><!-- 과금/편성위치 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="addRateItmInitPopupRateLocat" name="addRateItmInitPopupRateLocat">
						<c:forEach items="${rateLocat}" var="rateLocat" varStatus="status">
							<option value="${rateLocat.commonCd}">${rateLocat.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00077"/><!-- 요율참조유형 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="addRateItmInitPopupRateRefTyp" name="addRateItmInitPopupRateRefTyp">
						<c:forEach items="${rateRefTyp}" var="rateRefTyp" varStatus="status">
							<option value="${rateRefTyp.commonCd}">${rateRefTyp.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00100"/><!-- 공통과금항목 -->
				</th>				
	            <td>
					<select class="w100p" id="addRateItmInitPopupDefltRateItm" name="addRateItmInitPopupDefltRateItm">
					</select>                                            
				</td>
			</tr>						
			<tr>
				<th>
					<spring:message code="LAB.M15.LAB00060"/><!-- MNO과금항목코드 -->
				</th>
				<td>
					<input type="text" id="addRateItmInitPopupMnoRateItmCd" name="addRateItmInitPopupMnoRateItmCd" class="w100p">
				</td>	
			</tr>																	 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="addRateItmInitPopupBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="addRateItmInitPopupPopupBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<input id="addRateItmInitPopupProdCd" type='text' value="${productDevMgt.prodCd}"  hidden />
<input id="addRateItmInitPopupSvcCd" type='text' value="${productDevMgt.svcCd}"  hidden />
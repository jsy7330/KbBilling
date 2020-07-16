<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
var refCd = '${condInOut.refCd}';
var refTableId = '${condInOut.refTableId}';
var oprndFctrCd1 = '${condInOut.oprndFctrCd1}';
var oprndFctrCd2 = '${condInOut.oprndFctrCd1}';
var dataTyp = '${condInOut.dataTyp}';

$(document).ready(function() {
	$(".search").css("margin-top", "3px");
	$(".search").css("margin-right", "3px");
	
	$("#modRateItmCondFctr, #modRateItmCondOperator, #modRateItmCondOprndRefFl, #modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2").selectmenu()
	.selectmenu( "option", "width", "100%" );
	$("#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2").addClass('not-active'); 
	$("#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2").attr('disabled', true);
	$("#modRateItmCondFctr").selectmenu("disable");
	$('#btn_oprnd1').hide();
	$('#btn_oprnd2').hide();
	
	$("#modRateItmCondOperator, #modRateItmCondOprndRefFl").selectmenu({
		change: function( event, ui ) {
			$("#modRateItmCondOprndOprnd1Nm, #modRateItmCondOprndOprnd2Nm").val("");
			$("#modRateItmCondOprndOprnd1Nm, #modRateItmCondOprndOprnd2Nm").addClass('not-active'); 
			$("#modRateItmCondOprndOprnd1Nm, #modRateItmCondOprndOprnd2Nm").attr('disabled', true);
			$('#btn_oprnd1').hide();
			$('#btn_oprnd2').hide();
			$('#modRateItmCondOprndFctrCd1').find('option').remove();
			$('#modRateItmCondOprndFctrCd2').find('option').remove();
			$("#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2").selectmenu().selectmenu( "option", "width", "100%" );
			$("#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2").selectmenu("disable");
			
			
			if ($("#modRateItmCondOperator").val() == "6" ){
				if ($("#modRateItmCondOprndRefFl").val() == "0" ){
					if (refCd != "" || refTableId != "") {
						$('#btn_oprnd1').show();
						$('#btn_oprnd2').show();
					} else {
						$("#modRateItmCondOprndOprnd1Nm, #modRateItmCondOprndOprnd2Nm").removeClass('not-active'); 
						$("#modRateItmCondOprndOprnd1Nm, #modRateItmCondOprndOprnd2Nm").removeAttr('disabled');
					}
				} else {
					
					$("#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2").selectmenu("enable");
					
 					$("#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2 ").append('<option value="' + $("#modRateItmCondFctr").val() + '">' + $("#modRateItmCondFctr option:selected").text() + '</option>')
					.selectmenu(); 
				}
			} else {
				if ($("#modRateItmCondOprndRefFl").val() == "0" ){
					if (refCd != "" || refTableId != "") {;
						$('#btn_oprnd1').show();
					} else {
						$("#modRateItmCondOprndOprnd1Nm").removeClass('not-active'); 
						$("#modRateItmCondOprndOprnd1Nm").removeAttr('disabled');
					}
					
				} else {
					$("#modRateItmCondOprndFctrCd1").selectmenu("enable");
 					$("#modRateItmCondOprndFctrCd1").append('<option value="' + $("#modRateItmCondFctr").val() + '">' + $("#modRateItmCondFctr option:selected").text() + '</option>')
					.selectmenu(); 
				}
			}
		}
	});		
	
	$("#modRateItmCondFctr").selectmenu({
		change: function( event, ui ) {
			var param = new Object();
			param.rateItmCd = $("#modRateItmCondRateItmCd").val();
			
			refCd = $(ui.item.element).data('refcd');
			refTableId = $(ui.item.element).data('reftableid');
			
			$.ajax({
				url : 'getOprndFctrCd.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					$('#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2').find('option').remove(); 

					for(var i=0;i<data.oprndFctrCd.length;i++){
						$('#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2').append('<option value="'+data.oprndFctrCd[i].fctrCd+'">'+data.oprndFctrCd[i].fctrNm+'</option>');
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
	
	$('#btn_oprnd1').click(function() {
		var condOperator = $.trim( $("#modRateItmCondOperator").val() );
		var url="getAddDiscSvcRateItmTypInitPopup.ajax?refCd=" + refCd + "&condOperator="+condOperator+"&btnFlag=1";	

		$(parent.location).attr("href", "javascript:openModal3_sub_get('" + url + "');"); 			
				
	});
	
	$('#btn_oprnd2').click(function() {
		var condOperator = $.trim( $("#modRateItmCondOperator").val() );
		var url="getAddDiscSvcRateItmTypInitPopup.ajax?refCd=" + refCd + "&condOperator="+condOperator+"&btnFlag=21";	
	
		$(parent.location).attr("href", "javascript:openModal3_sub_get('" + url + "');"); 			
				
	});		
	
	if ($("#modRateItmCondOperator").val() == "6" ){
		if ($("#modRateItmCondOprndRefFl").val() == "0" ){
			if (refCd != "" || refTableId != "") {
				$('#btn_oprnd1').show();
				$('#btn_oprnd2').show();
			} else {
				$("#modRateItmCondOprndOprnd1Nm, #modRateItmCondOprndOprnd2Nm").removeClass('not-active'); 
				$("#modRateItmCondOprndOprnd1Nm, #modRateItmCondOprndOprnd2Nm").removeAttr('disabled');
			}
		} else {
			
			$("#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2").selectmenu("enable");
			
				$("#modRateItmCondOprndFctrCd1, #modRateItmCondOprndFctrCd2 ").append('<option value="' + $("#modRateItmCondFctr").val() + '">' + $("#modRateItmCondFctr option:selected").text() + '</option>')
			.selectmenu();
			$('#modRateItmCondOprndFctrCd1').val(oprndFctrCd1).selectmenu('refresh');
			$('#modRateItmCondOprndFctrCd2').val(oprndFctrCd2).selectmenu('refresh');	
		}
	} else {
		if ($("#modRateItmCondOprndRefFl").val() == "0" ){
			if (refCd != "" || refTableId != "") {;
				$('#btn_oprnd1').show();
			} else {
				$("#modRateItmCondOprndOprnd1Nm").removeClass('not-active'); 
				$("#modRateItmCondOprndOprnd1Nm").removeAttr('disabled');
			}
			
		} else {
			$("#modRateItmCondOprndFctrCd1").selectmenu("enable");
			$("#modRateItmCondOprndFctrCd1").append('<option value="' + $("#modRateItmCondFctr").val() + '">' + $("#modRateItmCondFctr option:selected").text() + '</option>')
			.selectmenu(); 
			$('#modRateItmCondOprndFctrCd1').val(oprndFctrCd1).selectmenu('refresh');		
		}
	}	
	
	
	$('#modRateItmCondBtnUpdate').click(function() {
		var param = checkValidation();        
		if(param != false){
			
			$.ajax({
				url : 'modRateItmCond.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						$("#modRateItmCondBtnClose").trigger('click');
						$("#rateItmCondListGrid").trigger("reloadGrid");
						//$("#tree").dynatree("getTree").reload();
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
	
	var fctr = $.trim( $("#modRateItmCondFctr").val() );	
	if(fctr == ''){
		alert('<spring:message code="LAB.M01.LAB00127" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
		
	var condOperator = $.trim( $("#modRateItmCondOperator").val() );	
	if(condOperator == ''){
		alert('<spring:message code="LAB.M06.LAB00100" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var oprndRefFl = $.trim( $("#modRateItmCondOprndRefFl").val() );	
	if(oprndRefFl == ''){
		alert('<spring:message code="LAB.M10.LAB00007" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}			
	
	param.rateItmCd = $("#modRateItmCondRateItmCd").val();
	param.fctrCd = fctr;
	param.condOperator = condOperator;
	param.oprndRefFl = oprndRefFl;
	param.condNo = $("#modRateItmCondCondNo").val();
	param.oprndFctrCd1 = $("#modRateItmCondOprndFctrCd1").val();
	param.oprndFctrCd2 = $("#modRateItmCondOprndFctrCd2").val();
	
	


//alert(dataTyp);

if(dataTyp == "4"){
	param.oprnd1 = $("#modRateItmCondOprndOprnd1Nm").val();
	param.oprnd2 = $("#modRateItmCondOprndOprnd2Nm").val();
}else{
	param.oprnd1 = $("#modRateItmCondOprndOprnd1CdNm").val();
	param.oprnd2 = $("#modRateItmCondOprndOprnd2CdNm").val();
}
/*
	alert($("#modRateItmCondOprndOprnd1Nm").val());
	alert($("#modRateItmCondOprndOprnd1CdNm").val());
	alert($("#modRateItmCondOprndOprnd2Nm").val());
	alert($("#modRateItmCondOprndOprnd2CdNm").val());
	
	return fales;
	*/
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
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00140"/></h4>
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
					<spring:message code="LAB.M01.LAB00127"/><!-- 과금요소명 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="modRateItmCondFctr" name="modRateItmCondFctr">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<option value="${fctr.fctrCd}" <c:if test="${condInOut.fctrCd eq fctr.fctrCd}"> selected="selected" </c:if>>${fctr.fctrNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M06.LAB00100"/><!-- 비교연산자 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="modRateItmCondOperator" name="modRateItmCondOperator">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${condOperator}" var="condOperator" varStatus="status">
							<option value="${condOperator.commonCd}" <c:if test="${condInOut.condOperator eq condOperator.commonCd}"> selected="selected" </c:if>>${condOperator.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00007"/><!-- 참조구분 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="modRateItmCondOprndRefFl" name="modRateItmCondOprndRefFl">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${oprndRefFl}" var="oprndRefFl" varStatus="status">
							<option value="${oprndRefFl.commonCd}" <c:if test="${condInOut.oprndRefFl eq oprndRefFl.commonCd}"> selected="selected" </c:if>>${oprndRefFl.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>					
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00120"/><!-- 과금요소1 -->
				</th>				
	            <td>
					<select class="w100p" id="modRateItmCondOprndFctrCd1" name="modRateItmCondOprndFctrCd1">
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00058"/><!-- 고정값1 -->
				</th>				
	            <td>
	            	<input type="hidden" id="modRateItmCondOprndOprnd1Nm" name="modRateItmCondOprndOprnd1Nm" class="w210" value="${condInOut.oprnd1}">
	            	<input type="text" id="modRateItmCondOprndOprnd1CdNm" name="modRateItmCondOprndOprnd1CdNm" class="w210" value="${condInOut.oprnd1Nm}">
	            	<a href="#" id="btn_oprnd1" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>                                        
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00121"/><!-- 과금요소2 -->
				</th>				
	            <td>
					<select class="w100p" id="modRateItmCondOprndFctrCd2" name="modRateItmCondOprndFctrCd2">
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00059"/><!-- 고정값2 -->
				</th>				
	            <td>
	            	<input type="hidden" id="modRateItmCondOprndOprnd2Nm" name="modRateItmCondOprndOprnd2Nm" class="w210" value="${condInOut.oprnd2}">
	            	<input type="text" id="modRateItmCondOprndOprnd2CdNm" name="modRateItmCondOprndOprnd2CdNm" class="w210" value="${condInOut.oprnd2Nm}">
	            	<a href="#" id="btn_oprnd2" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>                                           
				</td>
			</tr>										 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="modRateItmCondBtnUpdate"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="modRateItmCondBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<input id="modRateItmCondRateItmCd" type='text' value="${productDevMgt.rateItmCd}"  hidden />
<input id="modRateItmCondCondNo" type='text' value="${productDevMgt.condNo}"  hidden />

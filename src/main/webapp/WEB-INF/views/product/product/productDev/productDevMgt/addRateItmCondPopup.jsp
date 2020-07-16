<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
var refCd = "";
var refTableId = "";

$(document).ready(function() {
	$(".search").css("margin-top", "3px");
	$(".search").css("margin-right", "3px");
	
	$("#addRateItmCondFctr, #addRateItmCondOperator, #addRateItmCondOprndRefFl, #addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2").selectmenu()
	.selectmenu( "option", "width", "100%" );
	$('#btn_oprnd1').hide();
	$('#btn_oprnd2').hide();
 	
	$("#addRateItmCondOperator, #addRateItmCondOprndRefFl").selectmenu({
		change: function( event, ui ) {
			
			$("#addRateItmCondOprndOprnd1Nm, #addRateItmCondOprndOprnd2Nm").val("");
			$("#addRateItmCondOprndOprnd1Nm, #addRateItmCondOprndOprnd2Nm").addClass('not-active'); 
			$("#addRateItmCondOprndOprnd1Nm, #addRateItmCondOprndOprnd2Nm").attr('disabled', true);
			$('#btn_oprnd1').hide();
			$('#btn_oprnd2').hide();
			$('#addRateItmCondOprndFctrCd1').find('option').remove();
			$('#addRateItmCondOprndFctrCd2').find('option').remove();
			$("#addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2").selectmenu().selectmenu( "option", "width", "100%" );
			$("#addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2").selectmenu("disable");
			
			if ($("#addRateItmCondOperator").val() == "6" ){
				if ($("#addRateItmCondOprndRefFl").val() == "0" ){
					if (refCd != "" || refTableId != "") {
						$('#btn_oprnd1').show();
						$('#btn_oprnd2').show();
					} else {
						$("#addRateItmCondOprndOprnd1Nm, #addRateItmCondOprndOprnd2Nm").removeClass('not-active'); 
						$("#addRateItmCondOprndOprnd1Nm, #addRateItmCondOprndOprnd2Nm").removeAttr('disabled');
					}
				} else {
					
					$("#addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2").selectmenu("enable");
					
 					$("#addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2 ").append('<option value="' + $("#addRateItmCondFctr").val() + '">' + $("#addRateItmCondFctr option:selected").text() + '</option>')
					.selectmenu(); 
				}
			} else {
				if ($("#addRateItmCondOprndRefFl").val() == "0" ){
					if (refCd != "" || refTableId != "") {;
						$('#btn_oprnd1').show();
					} else {
						$("#addRateItmCondOprndOprnd1Nm").removeClass('not-active'); 
						$("#addRateItmCondOprndOprnd1Nm").removeAttr('disabled');
					}
					
				} else {
					$("#addRateItmCondOprndFctrCd1").selectmenu("enable");
 					$("#addRateItmCondOprndFctrCd1").append('<option value="' + $("#addRateItmCondFctr").val() + '">' + $("#addRateItmCondFctr option:selected").text() + '</option>')
					.selectmenu(); 
				}
			}
		}
	});	 
	
	$("#addRateItmCondFctr").selectmenu({
		change: function( event, ui ) {
			var param = new Object();
			param.rateItmCd = $("#addRateItmCondRateItmCd").val();
			
			refCd = $(ui.item.element).data('refcd');
			refTableId = $(ui.item.element).data('reftableid');
			
			$.ajax({
				url : 'getOprndFctrCd.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					$('#addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2').find('option').remove(); 

					for(var i=0;i<data.oprndFctrCd.length;i++){
						$('#addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2').append('<option value="'+data.oprndFctrCd[i].fctrCd+'">'+data.oprndFctrCd[i].fctrNm+'</option>');
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
		var condOperator = $.trim( $("#addRateItmCondOperator").val() );
		var url="getAddDiscSvcRateItmTypInitPopup.ajax?refCd=" + refCd + "&condOperator="+condOperator+"&btnFlag=1";		

		$(parent.location).attr("href", "javascript:openModal3_sub_get('" + url + "');"); 			
				
	});
	
	$('#btn_oprnd2').click(function() {
		var condOperator = $.trim( $("#addRateItmCondOperator").val() );
		var url="getAddDiscSvcRateItmTypInitPopup.ajax?refCd=" + refCd + "&condOperator="+condOperator+"&btnFlag=2";		
	
		$(parent.location).attr("href", "javascript:openModal3_sub_get('" + url + "');"); 			
				
	});	
	
	
		
	$('#addRateItmCondBtnInsert').click(function() {
		var param = checkValidation();        
		if(param != false){
			
			$.ajax({
				url : 'addRateItmCond.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						$("#addRateItmCondBtnClose").trigger('click');
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
	
	var fctr = $.trim( $("#addRateItmCondFctr").val() );	
	if(fctr == ''){
		alert('<spring:message code="LAB.M01.LAB00127" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
		
	var condOperator = $.trim( $("#addRateItmCondOperator").val() );	
	if(condOperator == ''){
		alert('<spring:message code="LAB.M06.LAB00100" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var oprndRefFl = $.trim( $("#addRateItmCondOprndRefFl").val() );	
	if(oprndRefFl == ''){
		alert('<spring:message code="LAB.M10.LAB00007" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}			
	
	param.rateItmCd = $("#addRateItmCondRateItmCd").val();
	param.fctrCd = fctr;
	param.condOperator = condOperator;
	param.oprndRefFl = oprndRefFl;

	if(oprndRefFl =='0'){
		param.oprndFctrCd1 = "";
		param.oprndFctrCd2 = "";
	}else{
		param.oprndFctrCd1 = $("#addRateItmCondOprndFctrCd1").val();
		param.oprndFctrCd2 = $("#addRateItmCondOprndFctrCd2").val();
	}
	

	if($("#addRateItmCondOprndOprnd1Nm").val() == null || $("#addRateItmCondOprndOprnd1Nm").val() ==""){
		param.oprnd1 = $("#addRateItmCondOprndOprnd1CdNm").val();
	}else{
		param.oprnd1 = $("#addRateItmCondOprndOprnd1Nm").val();
	}

	if($("#addRateItmCondOprndOprnd2Nm").val() == null || $("#addRateItmCondOprndOprnd2Nm").val() ==""){
		param.oprnd2 = $("#addRateItmCondOprndOprnd2CdNm").val();
	}else{
		param.oprnd2 = $("#addRateItmCondOprndOprnd2Nm").val();
	}

	return param;	
}

</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M01.LAB00140"/></div>
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
					<select class="w100p" id="addRateItmCondFctr" name="addRateItmCondFctr">
						<c:forEach items="${fctr}" var="fctr" varStatus="status">
							<option value="${fctr.fctrCd}" data-refCd="${fctr.refCd}" data-refTableId="${fctr.refTableId}" 
							data-refColmnId="${fctr.refColmnId}" data-refColmnNm="${fctr.refColmnNm}"
							data-refTableCond="${fctr.refTableCond}" >${fctr.fctrNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M06.LAB00100"/><!-- 비교연산자 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="addRateItmCondOperator" name="addRateItmCondOperator">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${condOperator}" var="condOperator" varStatus="status">
							<option value="${condOperator.commonCd}">${condOperator.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00007"/><!-- 참조구분 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="addRateItmCondOprndRefFl" name="addRateItmCondOprndRefFl">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${oprndRefFl}" var="oprndRefFl" varStatus="status">
							<option value="${oprndRefFl.commonCd}">${oprndRefFl.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>					
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00120"/><!-- 과금요소1 -->
				</th>				
	            <td>
					<select class="w100p" id="addRateItmCondOprndFctrCd1" name="addRateItmCondOprndFctrCd1">
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00058"/><!-- 고정값1 -->
				</th>				
	            <td>
	            	<input type="text" id="addRateItmCondOprndOprnd1CdNm" name="addRateItmCondOprndOprnd1CdNm" class="w170">
	            	<input type="hidden" id="addRateItmCondOprndOprnd1Nm" name="addRateItmCondOprndOprnd1Nm" class="w220">
	            	<a href="#" id="btn_oprnd1" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>                                           
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00121"/><!-- 과금요소2 -->
				</th>				
	            <td>
					<select class="w100p" id="addRateItmCondOprndFctrCd2" name="addRateItmCondOprndFctrCd2">
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00059"/><!-- 고정값2 -->
				</th>				
	            <td>
	            	<input type="text" id="addRateItmCondOprndOprnd2CdNm" name="addRateItmCondOprndOprnd2CdNm" class="w170">
	            	<input type="hidden" id="addRateItmCondOprndOprnd2Nm" name="addRateItmCondOprndOprnd2Nm" class="w220">
	            	<a href="#" id="btn_oprnd2" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>                                           
				</td>
			</tr>										 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="addRateItmCondBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="addRateItmCondBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<input id="addRateItmCondRateItmCd" type='text' value="${productDevMgt.rateItmCd}"  hidden />

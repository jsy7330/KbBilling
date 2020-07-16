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
	
	
	$("#productChargeTypeListInsertChrgCtgry").selectmenu()
	.selectmenu( "option", "width", "100%" );

	$("#productChargeTypeListInsertDispPriNo").addClass('not-active');
	$("#productChargeTypeListInsertDispPriNo").attr('disabled', true);
	
	$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});
	
	$("#productChargeTypeListInsertChrgCtgry").selectmenu({
		change: function( event, ui ) {
			var tmpParam = new Object();
			var commonCd = $(this).val();
			console.log("common : " + commonCd);
			if (commonCd == "Z") {
				alert("계산대상 요금항목이 아니고 청구에서만 사용하는 기타 항목입니다.");
				return;
			}
			
			tmpParam.commonCd = commonCd;
			
			$.ajax({
				url : 'getRateTypeMaxPriNo.json',
				type : 'POST',
				async: false,
				data : tmpParam,
				success : function(data) {
					$("#productChargeTypeListInsertDispPriNo").val(data.productChargeTypeListChargeType.maxPriNo);
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

function insertData(){
	
	var param = checkValidation();
	
	if(param != false){
		
		$.ajax({
			url : 'addRateType.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					$("#productChargeTypeListGrid").trigger("reloadGrid");
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

	var rateItmTypNm = $.trim( $("#productChargeTypeListInsertRateItmTypNm").val() );
	if(rateItmTypNm == ''){
		alert('<spring:message code="LAB.M08.LAB00055" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		

	var chrgCtgry = $.trim( $("#productChargeTypeListInsertChrgCtgry").val() );	
	if(chrgCtgry == ''){
		alert('<spring:message code="LAB.M08.LAB00051" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var dispPriNo = $.trim( $("#productChargeTypeListInsertDispPriNo").val() );
	if(dispPriNo == ''){
		alert('<spring:message code="LAB.M09.LAB00089" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}		

	var actDt = $("#productChargeTypeListInsertDispPriNoActDt").val();

	param.rateItmTypNm = rateItmTypNm;
	param.chrgCtgry = chrgCtgry;
	param.dispPriNo = dispPriNo;
	param.actDt = dateFormatToStringYYYYMMDD(actDt);

	return param;
}

</script>
	
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M08.LAB00056"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M08.LAB00054"/><!-- 요금유형등록 --></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:30%;">
			<col style="width:70%;">
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00055"/><!-- 요금유형명 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="productChargeTypeListInsertRateItmTypNm" name="productChargeTypeListInsertRateItmTypNm" class="w100p">                                            
	     		</td>			
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00051"/><!-- 요금영역 --><span class="dot">*</span>
				</th>				
	            <td>
					<select class="w100p" id="productChargeTypeListInsertChrgCtgry" name="productChargeTypeListInsertChrgCtgry">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${chrgCtgry}" var="chrgCtgry" varStatus="status">
							<option value="${chrgCtgry.commonCd}">${chrgCtgry.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>			
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00089"/><!-- 정렬순서 --><span class="dot">*</span>
				</th>
				<td>
	       			<input type="text" id="productChargeTypeListInsertDispPriNo" name="productChargeTypeListInsertDispPriNo" class="w100p" numberonly="true">                                            
	     		</td>			
			</tr>					
			<tr>	
				<th>
					<spring:message code="LAB.M09.LAB00052"/><!-- 적용일자 --><span class="dot">*</span>
				</th>
				<td>
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="productChargeTypeListInsertDispPriNoActDt" name="productChargeTypeListInsertDispPriNoActDt" class="datepicker" readonly="readonly" />
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

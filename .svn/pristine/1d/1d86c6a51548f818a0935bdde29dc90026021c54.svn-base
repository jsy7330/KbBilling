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
	$("#oprndRefFl1, #oprndFctrCd1, #oprndRefFl2, #oprndFctrCd2").selectmenu("disable");
	$("#oprnd1, #oprnd2").addClass('not-active'); 
	$("#oprnd1, #oprnd2").attr('disabled', true);
	
	$("#operator").selectmenu({ //차후
		change: function( event, ui ) {	
			if ($(this).val() == "6") {
				$("#oprndRefFl1").selectmenu("enable");
				$("#oprndRefFl2").selectmenu("enable");
			} else if ($(this).val() == "9") {
				$("#oprndRefFl1").selectmenu("enable");
				$("#oprndRefFl2").selectmenu("disable");
			} else {
				$("#oprndRefFl1").selectmenu("enable");
				$("#oprndRefFl2").selectmenu("enable");
			}
		}
	});
	
	$("#oprndRefFl1").selectmenu({ //차후
		change: function( event, ui ) {	
			if ($(this).val() == "0") {
				$("#oprndFctrCd1").selectmenu("disable");
				$("#oprnd1").removeClass('not-active'); 
				$("#oprnd1").removeAttr('disabled');
			} else if ($(this).val() == "1") {
				$("#oprndFctrCd1").selectmenu("enable");
				$("#oprnd1").addClass('not-active'); 
				$("#oprnd1").attr('disabled', true);
			} else {
				$("#oprndFctrCd1").selectmenu("disable");
				$("#oprnd1").addClass('not-active');
				$("#oprnd1").attr('disabled', true);
			}
		}
	});	
	
	$("#oprndRefFl2").selectmenu({ //차후
		change: function( event, ui ) {	
			if ($(this).val() == "0") {
				$("#oprndFctrCd2").selectmenu("disable");
				$("#oprnd2").removeClass('not-active'); 
				$("#oprnd2").removeAttr('disabled');
			} else if ($(this).val() == "1") {
				$("#oprndFctrCd2").selectmenu("enable");
				$("#oprnd2").addClass('not-active'); 
				$("#oprnd2").attr('disabled', true);
			} else {
				$("#oprndFctrCd2").selectmenu("disable");
				$("#oprnd2").addClass('not-active');
				$("#oprnd2").attr('disabled', true);
			}
		}
	});		
	
/* 	$("#addRateItmCondFctr, #addRateItmCondOperator, #addRateItmCondOprndRefFl, #addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2").selectmenu()
	.selectmenu( "option", "width", "100%" );
	$("#addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2").addClass('not-active'); 
	$("#addRateItmCondOprndFctrCd1, #addRateItmCondOprndFctrCd2").attr('disabled', true);
	 */
/* 	
	$("#addRateItmCondFctr").selectmenu({ //차후
		change: function( event, ui ) {			
			var param = new Object();
			param.rateItmCd = $("#addRateItmCondRateItmCd").val();
			//param.svcRateItmTypCd = $(this).val();
			
			$.ajax({
				url : 'getOprndFctrCd.json',
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
	});		 */
	

		
	$('#addDiscPerdPopupBtnInsert').click(function() {
		var param = checkValidation();        
		if(param != false){
			
			$.ajax({
				url : 'addDiscPerd.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						$("#addDiscPerdPopupBtnClose").trigger('click');
						$("#discPerdListGrid").trigger("reloadGrid");
						//$("#tree").dynatree("getTree").reload();
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
	
	var operator = $.trim( $("#operator").val() );	
	if(operator == ''){
		alert('<spring:message code="LAB.M06.LAB00100" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
		
/* 	var condOperator = $.trim( $("#addRateItmCondOperator").val() );	
	if(condOperator == ''){
		alert('<spring:message code="LAB.M06.LAB00100" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}		
	
	var oprndRefFl = $.trim( $("#addRateItmCondOprndRefFl").val() );	
	if(oprndRefFl == ''){
		alert('<spring:message code="LAB.M10.LAB00007" /> <spring:message code="MSG.M08.MSG00042" />');	
		return false;
	}	 */		
	
	param.rateItmCd = $("#addDiscPerdPopupRateItmCd").val();
	param.operator = $("#operator").val();
	param.oprndRefFl1 = $("#oprndRefFl1").val();
	param.oprndFctrCd1 = $("#oprndFctrCd1").val();
	param.oprnd1 = $("#oprnd1").val();
	param.oprndRefFl2 = $("#oprndRefFl2").val();
	param.oprndFctrCd2 = $("#oprndFctrCd2").val();
	param.oprnd2 = $("#oprnd2").val();
	
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
			<h4 class="ly_title"><spring:message code="LAB.M14.LAB00027"/></h4>
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
					<spring:message code="LAB.M06.LAB00100"/><!-- 비교연산자 -->
				</th>				
	            <td>
					<select class="w100p" id="operator" name="operator">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${operator}" var="operator" varStatus="status">
							<option value="${operator.commonCd}">${operator.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00008"/><!-- 참조구분1 -->
				</th>				
	            <td>
					<select class="w100p" id="oprndRefFl1" name="oprndRefFl1">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${oprndRefFl1}" var="oprndRefFl1" varStatus="status">
							<option value="${oprndRefFl1.commonCd}">${oprndRefFl1.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00120"/><!-- 과금요소1 -->
				</th>				
	            <td>
					<select class="w100p" id="oprndFctrCd1" name="oprndFctrCd1">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${oprndFctrCd}" var="oprndFctrCd" varStatus="status">
							<option value="${oprndFctrCd.fctrCd}">${oprndFctrCd.fctrNm}</option>
						</c:forEach>					
					</select>                                            
				</td>
			</tr>					
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00058"/><!-- 고정값1 -->
				</th>				
	            <td>
					<input type="text" id="oprnd1" name="oprnd1" class="w100p">                                            
				</td>
			</tr>	
				
			<tr>
				<th>
					<spring:message code="LAB.M10.LAB00009"/><!-- 참조구분2 -->
				</th>				
	            <td>
					<select class="w100p" id="oprndRefFl2" name="oprndRefFl2">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${oprndRefFl2}" var="oprndRefFl2" varStatus="status">
							<option value="${oprndRefFl2.commonCd}">${oprndRefFl2.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00121"/><!-- 과금요소2 -->
				</th>				
	            <td>
					<select class="w100p" id="oprndFctrCd2" name="oprndFctrCd2">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${oprndFctrCd}" var="oprndFctrCd" varStatus="status">
							<option value="${oprndFctrCd.fctrCd}">${oprndFctrCd.fctrNm}</option>
						</c:forEach>					
					</select>                                            
				</td>
			</tr>					
			<tr>
				<th>
					<spring:message code="LAB.M01.LAB00059"/><!-- 고정값2 -->
				</th>				
	            <td>
					<input type="text" id="oprnd2" name="oprnd2" class="w100p">                                            
				</td>
			</tr>		
		
									 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="addDiscPerdPopupBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="addDiscPerdPopupBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<input id="addDiscPerdPopupRateItmCd" type='text' value="${productDevMgt.rateItmCd}"  hidden />

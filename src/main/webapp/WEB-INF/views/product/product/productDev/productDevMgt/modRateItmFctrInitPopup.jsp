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
	
	
	$("#modRateItmFctrInitRateInfoExeFl").selectmenu()
	.selectmenu( "option", "width", "100%" );
	$("#modRateItmFctrInitFctrNm").addClass('not-active'); 
	$("#modRateItmFctrInitFctrNm").attr('disabled', true);		
		
	$('#modRateItmFctrInitBtnUpdate').click(function() {

		var param = checkValidation();        
       
		
		if(param != false){
			
			$.ajax({
				url : 'modRateItmFctr.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					if(data.result != "0" && data.result != "-1"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						$("#modRateItmFctrInitBtnClose").trigger('click');
						$("#rateItmFctrListGrid").trigger("reloadGrid");
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
		
	});	 
});

function checkValidation(){
	var param = new Object();
	
	var fctrNo = $("#modRateItmFctrInitFctrNo").val();	//요소명
	if(fctrNo == ''){
		alert('<spring:message code="LAB.M08.LAB00072" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}			
	
	param.fctrNo = fctrNo;
	param.fctrCd = $("#modRateItmFctrInitFctrCd").val();
	param.rateItmCd = $("#modRateItmFctrInitRateItmCd").val();
	param.rateInfoExeFl = $("#modRateItmFctrInitRateInfoExeFl").val();	
	
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
					<spring:message code="LAB.M08.LAB00064"/><!-- 요소명 -->
				</th>				
	            <td>
	            	<input type="text" id="modRateItmFctrInitFctrNm" name="modRateItmFctrInitFctrNm" value="${rateItmFctr.fctrNm}" class="w100p">                                       
				</td>
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00049"/><!-- 요금결정여부 -->
				</th>				
	            <td>
					<select class="w100p" id="modRateItmFctrInitRateInfoExeFl" name="modRateItmFctrInitRateInfoExeFl">
						<c:forEach items="${rateInfoExeFl}" var="rateInfoExeFl" varStatus="status">
							<option value="${rateInfoExeFl.commonCd}" <c:if test="${rateItmFctr.rateInfoExeFl eq rateInfoExeFl.commonCd}"> selected="selected" </c:if> >${rateInfoExeFl.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00072"/><!--요율요소번호 -->
				</th>				
	            <td>
	            	<input type="text" id="modRateItmFctrInitFctrNo" name="modRateItmFctrInitFctrNo" value="${rateItmFctr.fctrNo}" class="w100p">                                       
				</td>
			</tr>														 		
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="modRateItmFctrInitBtnUpdate"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="modRateItmFctrInitBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<input id="modRateItmFctrInitFctrCd" type='text' value="${productDevMgt.fctrCd}"  hidden />
<input id="modRateItmFctrInitRateItmCd" type='text' value="${productDevMgt.rateItmCd}"  hidden />

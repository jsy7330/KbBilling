<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#btnInsert").click(function() {
		var returnCon = confirm('<spring:message code="MSG.M09.MSG00008" />');
		
		if(returnCon){
			var param = new Object();     
	        param.prodCd = $("#confSbjListGrid").getRowData(rowId).prodCd;
	        param.prodDvlpDttm = $("#confSbjListGrid").getRowData(rowId).prodDvlpDttm;
	        param.confrCd = $("#confSbjListGrid").getRowData(rowId).confrCd;
	        param.confReqCd = $("#confSbjListGrid").getRowData(rowId).confReqCd;
	        param.confRslt = $("#confReturnPopupConfRslt").val();
	        param.confDesc = $("#procReason").val();
			
			if(param != false){
				
				$.ajax({
					url : 'modConfReturn.json',
					type : 'POST',
					async: false,
					data : param,
					success : function(data) {
						
						if(data.result != "0" && data.result != "-1"){
							alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
							
							$("#btnClose").trigger('click');
							$("#confSbjListGrid").trigger("reloadGrid");
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
	});
	
	var rowId = $("#confSbjListGrid").getGridParam("selrow");
	$("#pdNm").text($("#confSbjListGrid").getRowData(rowId).prodNm);
	$("#progGbNm").text($("#confSbjListGrid").getRowData(rowId).progGbNm);
	$("#modDesc").val($("#confSbjListGrid").getRowData(rowId).modDesc);
	$("#procReason").val($("#confSbjListGrid").getRowData(rowId).confDesc);
	
	$("#modDesc").prop("disabled", true);
});	

$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});	

</script>
	
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00262"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h3 class="ly_title"><spring:message code="LAB.M07.LAB00265"/><!-- 등록 --></h3>
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
					<spring:message code="LAB.M07.LAB00114"/><!-- 상품 -->
				</th>			
				<td id="pdNm">                                       
	     		</td>	
				<th>
					<spring:message code="LAB.M09.LAB00009"/><!-- 작업구분 -->
				</th>			
				<td id="progGbNm">                                       
	     		</td>		     				
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M06.LAB00057"/><!-- 변경내역 -->
				</th>			
				<td colspan="3">
	       			<textarea id="modDesc" name="modDesc" class="w100p h100"></textarea>                                           
	     		</td>			
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00268"/><!-- 승인내역 -->
				</th>			
				<td colspan="3">
					<select class="w100p" id="confReturnPopupConfRslt" name="confReturnPopupConfRslt">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${confRslt}" var="confRslt" varStatus="status">
							<option value="${confRslt.commonCd}" >${confRslt.commonCdNm}</option>
						</c:forEach>
					</select>    			                                        
	     		</td>			
			</tr>		
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00264"/><!-- 승인반려상세내역 -->
				</th>			
				<td colspan="3">
	       			<textarea id="procReason" name="procReason" class="w100p h100"></textarea>                                           
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


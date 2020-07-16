<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	
	var height = Number( $("#popup_dialog").css("height").replace("px", "") );
	var popHeaght = height - 70;
	
	$("#attrListMap").css("overflow-y", "auto");
	$("#attrListMap").css("height", popHeaght+"px");
	$("#popup_dialog").css("width", "430px");
	
	$('#productAttrListPopupBtnUpdate').click(function() {
		
		var formData = $("#attrListMap").serialize();		
		$.ajax({
			url : 'treatProdAttrDvlp.json',
			type : 'POST',
			async: false,
			data : formData,
			success : function(data) {
				console.log(data);
				
				if(data.result != "0" && data.result != "-1"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					
					$("#productAttrListPopupBtnClose").trigger('click');
					$("#productAttrListGrid").trigger("reloadGrid");
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
		
	});
});		
</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00172"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<form id="attrListMap" name="attrListMap" method="post" >
<input id="prodCd" name="prodCd" type='text' value="${prodCd}"  hidden />
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00171"/></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:50%;">
			<col style="width:50%;">
		</colgroup>
		<thead>
				
			<c:forEach items="${resultList}" var="resultList" varStatus="status">
				<c:set var="dataTyp" value="0" /> 
				<c:forEach items="${commonGrpList}" var="commonGrpList" varStatus="status">
					<c:if test="${resultList.refCd eq commonGrpList.commonGrp }">
						<c:set var="dataTyp" value="1" /> 
					</c:if>
				</c:forEach>
				
				<c:if test="${dataTyp eq '0'}">
					<tr>
						<th>
							${resultList.attrNm} 					
						</th>
						<td>
							<input type="text" id="${resultList.attrCd}" name="${resultList.attrCd}" class="w100p" value="${resultList.attrValNm}">
						</td>
					</tr>
				</c:if>
				
				<c:if test="${dataTyp eq '1'}">
					<tr>
						<th>
							${resultList.attrNm} 					
						</th>
						<td>
							<select class="w100p" id="${resultList.attrCd}" name="${resultList.attrCd}">
								<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
								<c:forEach items="${commonGrpList}" var="commonGrpList" varStatus="status">
									<c:if test="${resultList.refCd eq commonGrpList.commonGrp }">
										<option value="${commonGrpList.commonCd}" <c:if test="${resultList.attrVal eq commonGrpList.commonCd}"> selected="selected" </c:if> >${commonGrpList.commonCdNm}</option>
									</c:if>
								</c:forEach>							
							</select>
						</td>
					</tr>
				</c:if>				
				
			
			</c:forEach>	 		
		</thead>
	</table>
</div>

<div class="btn_box">
	<a class="blue-btn" href="#" id="productAttrListPopupBtnUpdate"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="productAttrListPopupBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
</form>
<!--// center -->

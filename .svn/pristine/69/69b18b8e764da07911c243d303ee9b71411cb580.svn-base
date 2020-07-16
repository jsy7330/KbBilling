<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	$("#modRateFacFlag").selectmenu({
		change: function() {
			$("#rateFacFlag").val($("#modRateFacFlag").val());
			$("#rateFacFlagNm").val($("#modRateFacFlag option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
		}
	});
	
	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goUpdateRateFac() {
	var rowId = $("#ratingFactorGrid").jqGrid('getGridParam', 'selrow');
	
	if($("#modRateFacFlag").val() == "" ) {
		alert("<spring:message code="MSG.M08.MSG00028"/>");
		$("#modRateFacFlag").focus();
		return;
	}
	
	$("#ratingFactorGrid").jqGrid('setCell', rowId, 'rateFacFlagNm', $("#rateFacFlagNm").val());
	$("#ratingFactorGrid").jqGrid('setCell', rowId, 'rateFacFlag', $("#rateFacFlag").val());
	$("#ratingFactorGrid").jqGrid('setCell', rowId, 'changedTag', '1');
	
	$("#btnClose").trigger('click');
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>

<!-- title -->
<div class="layer_top">
	<div class="title">
		<spring:message code="LAB.M07.LAB00138"/>
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="newProductDtlRateFacUpdateAction" name="newProductDtlRateFacUpdateAction" method="post">
  	<input type="hidden"  id="prodId" name="prodId" value="${newProductDtl.prodId }"/>
  	<input type="hidden" id="usgTyp" name="usgTyp" value="${newProductDtl.usgTyp }"/>
  	<input type="hidden" id="rateFacFlag" name="rateFacFlag" />
  	<input type="hidden" id="rateFacFlagNm" name="rateFacFlagNm" />
  	  	
 	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00128"/>
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width:11%;">
				<col style="width:12%;">
				<col style="width:11%;">
				<col style="width:12%;">
				<col style="width:11%;">
				<col style="width:13%;">
			</colgroup>
			<tbody>
				<tr>
					<th>
						<spring:message code="LAB.M01.LAB00118"/>
					</th>
					<td>
						<select id="rateFac" name="rateFac" class="w180" disabled="disabled" >
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${rateFacList}" var="rateFacList" varStatus="status">
								<option value="${rateFacList.commonCd}"
									<c:if test="${newProductDtl.rateFac eq rateFacList.commonCd}">
										selected="selected"
									</c:if>>
									${rateFacList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
		
					<th>
						<spring:message code="LAB.M08.LAB00070"/>
					</th>
					<td>
						<select id="modRateFacFlag" name="modRateFacFlag" class="w180">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${rateFacFlagList}" var="rateFacFlagList" varStatus="status">
								<option value="${rateFacFlagList.commonCd}"
									<c:if test="${newProductDtl.rateFacFlag eq rateFacFlagList.commonCd}">
										selected="selected"
									</c:if>>
									${rateFacFlagList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goUpdateRateFac();" id="btn_update">
			<span class="confirm_icon">
				<spring:message code="LAB.M09.LAB00048" />
			</span>
		</a>
		<a class="grey-btn close" href="#" id="btnClose">
			<span class="cancel_icon">
				<spring:message code="LAB.M03.LAB00027" />
			</span>
		</a>
	</div>
</form:form>
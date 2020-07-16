<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$(".date").datepicker();
	var date1 = new Date();
	date1.setDate(date1.getDate() + 1);
	result_y = date1.getFullYear();

	if ((date1.getMonth() + 1) < 10) {
		result_m = "0" + (date1.getMonth() + 1);
	} else {
		result_m = date1.getMonth() + 1;
	}
	if ((date1.getDate() + 1) < 10) {
		result_d = "0" + (date1.getDate());
	} else {
		result_d = date1.getDate();
	}

	var date2 = null;

	if ('${lngTyp}' == 'ko') {
		date2 = "" + result_y + result_m + result_d;
	} else {
		date2 = "" + result_m + result_d + result_y;
	}

	$("#insertEffDt").val(date2 + "000000");
	$("#modPeriodDefId").val($("#periodDef").val());
	$("#modPeriodDefId").selectmenu("refresh");

	$("#modPeriodDefId").selectmenu({
		change : function() {
			$("#periodDefId").val($("#modPeriodDefId").val());
		}
	});

	var resultMsg = "${resultMsg}";
	if (resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goUpdatePeriod() {
	var rowId = $("#timePeriodGrid").jqGrid('getGridParam', 'selrow');

	if ($("#modPeriodDefId").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00116" />");
		$("#modPeriodDefId").focus();
		return;
	}

	$("#timePeriodGrid").jqGrid('setCell', rowId, 'periodDefId', $("#periodDefId").val());
	$("#timePeriodGrid").jqGrid('setCell', rowId, 'changedTag', '1');
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
		<spring:message code="LAB.M07.LAB00137" />
	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->
<form:form commandName="newProductDtlPeriodUpdateAction"
	name="newProductDtlPeriodUpdateAction" method="post">
	<input type="hidden" id="prodId" name="prodId" value="${newProductDtl.prodId }" />
	<input type="hidden" id="usgTyp" name="usgTyp" value="${newProductDtl.usgTyp }" />
	<input type="hidden" id="periodDefId" name="periodDefId" value="${newProductDtl.periodDefId }" />
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M07.LAB00137" />
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 11%;">
				<col style="width: 12%;">
				<col style="width: 11%;">
				<col style="width: 12%;">
				<col style="width: 11%;">
				<col style="width: 13%;">
			</colgroup>
			<tbody>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00275" />
					</th>
					<td>
						<input type="text" id="actDay" name="actDay" value="${newProductDtl.actDay } " readonly="readonly" />
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00278" />
					</th>
					<td>
						<input type="text" id="deactDay" name="deactDay" value="${newProductDtl.deactDay }" readonly="readonly" />
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00277" />
					</th>
					<td>
						<select id="modPeriodDefId" name="modPeriodDefId" class="w180">
							<option value="">
								<spring:message code="LAB.M15.LAB00002" />
							</option>
							<option value="MORNING_1">
								<spring:message code="LAB.M08.LAB00040" />
							</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goUpdatePeriod();" id="btn_update"> 
			<span class="confirm_icon"> 
				<spring:message	code="LAB.M09.LAB00048" />
			</span>
		</a> 
		<a class="grey-btn close" href="#" id="btnClose"> 
			<span class="cancel_icon"> 
				<spring:message code="LAB.M03.LAB00027" />
			</span>
		</a>
	</div>
</form:form>
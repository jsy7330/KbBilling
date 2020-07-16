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

	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$( ".date" ).datepicker();
	var date1 = new Date();
	date1.setDate(date1.getDate() + 1);
	result_y = date1.getFullYear();
	
	if( (date1.getMonth() + 1) < 10 ) {
		result_m = "0" + (date1.getMonth() + 1);
	} else {
		result_m = date1.getMonth() + 1;
	}
	if( (date1.getDate()+1) < 10 ) {
		result_d = "0" + (date1.getDate());
	} else {
		result_d = date1.getDate();
	}
	
	var date2 = null;

	if( '${lngTyp}' == 'ko' ) {
		date2 = "" + result_y + result_m + result_d;
	} else {
		date2 = "" + result_m + result_d + result_y; 
	}

	$("#insertEffDt").val(date2 + "000000");
	
	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goInsertPeriod() {
	var param = new Object();
	var data = new Object();
	var rowId = $("#usgTypGrid3").jqGrid('getGridParam', 'selrow');
	
	
	if( $("#actDay").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00114"/>");
		$("#actDay").focus();
		return;
	}
	if( $("#deactDay").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00117"/>");
		$("#deactDay").focus();
		return;
	}
	if( $("#periodDefId").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00116"/>");
		$("#periodDefId").focus();
		return;
	}
	if( $("#actDay").val().length != 4 ) {
		alert("날짜를 네자리 숫자로 입력하세요.");
		return;
	}
	if( $("#deactDay").val().length != 4 ) {
		alert("날짜를 네자리 숫자로 입력하세요.")
		return;
	}
	param.prodId = $("#prodCd").val();
	param.usgTyp = $("#usgTyp5").val();
	param.effDt = $("#insertEffDt").val()
	param.expDt = $("#insertExpDt").val()
	param.actDay = $("#actDay").val().substring(0, 2) + '-' + $("#actDay").val().substring(2, 4);
	param.deactDay = $("#deactDay").val().substring(0, 2) + '-' + $("#deactDay").val().substring(2, 4);
	param.periodDefId = $("#periodDefId").val();
	param.changedTag = '1';
	
	data = $("#timePeriodGrid").getRowData();
	
	for( var i=0; i<data.length; i++ ) {
		if( param.prodId == data[i].prodId && param.usgTyp == data[i].usgTyp && param.actDay == data[i].actDay ) {
			alert("<spring:message code="MSG.M09.MSG00049"/>");
			return;
		}		
	}
	
	$("#timePeriodGrid").jqGrid('addRowData', $("#timePeriodGrid").getDataIDs().length+1, param);
	$("#usgTypGrid3").jqGrid('setCell', rowId, 'count', Number($("#usgTypGrid3").getRowData(rowId).count)+1);
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
   		<spring:message code="LAB.M07.LAB00136"/>
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="newProductDtlPeriodInsertAction" name="newProductDtlPeriodInsertAction" method="post">
	<input type="hidden" id="insertProdId" name="insertProdId" value="${newProductDtl.prodId }"/>
	<input type="hidden" id="insertUsgTyp" name="insertUsgTyp" value="${newProductDtl.usgTyp }"/>
	<input type="hidden" id="insertEffDt" name="insertEffDt" />
	<input type="hidden" id="insertExpDt" name="insertExpDt" value="99991231235959"/>

 	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M07.LAB00136"/>
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
						<spring:message code="LAB.M07.LAB00275"/>
					</th>
					<td>
						<input type="text" id="actDay" name="actDay"/>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00278"/>
					</th>
					<td>
						<input type="text" id="deactDay" name="deactDay"/>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00277"/>
					</th>
					<td>
						<select id="periodDefId" name="periodDefId" class="w180">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<option value="MORNING_1">
								<spring:message code="LAB.M08.LAB00040"/>
							</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goInsertPeriod();" id="btn_insert">
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
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
	
	$('#insertRateFac').selectmenu({
        change: function() {
        	$("#rateFacNm").val($("#insertRateFac option:selected").text().replace(/	/g, "").replace(/\n/g, ""));
			
        	var param = $("#ratingFactorGrid").getRowData();
        	
        	for( var i=0; i<param.length; i++ ) {
				if( $("#rateFacNm").val() == param[i].rateFacNm ) {
					alert("중복을 확인하세요.");
					$("#insertRateFac").val('');
					$("#rateFacNm").val('');
					$("#insertRateFac").selectmenu("refresh");
					return;
				}        		
        	}
        }
	});
	
	$('#insertRateFacFlag').selectmenu({
        change: function() {
        	$("#rateFacFlagNm").val($("#insertRateFacFlag option:selected").text().replace(/	/g, ""));
        }
	});
	
	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});
 
function goInsertRateFac() {
	var param = new Object();
	var rowId = $("#usgTypGrid2").jqGrid('getGridParam', 'selrow');
	
	if($("#insertRateFac").val() == "" ) {
		alert("<spring:message code="MSG.M01.MSG00036"/>");
		$("#insertRateFac").focus();
		return;
	}
	if($("#insertRateFacFlag").val() == "" ) {
		alert("<spring:message code="MSG.M08.MSG00028"/>");
		$("#insertRateFacFlag").focus();
		return;
	}
	
	param.prodId = $("#prodCd").val();
	param.usgTyp = $("#usgTypGrid2").getRowData(rowId).usgTyp;
	param.effDt = $("#insertEffDt").val();
	param.expDt = $("#insertExpDt").val();
	param.rateFac = $("#insertRateFac").val();
	param.rateFacFlag = $("#insertRateFacFlag").val();
	param.rateFacNm = $("#rateFacNm").val();
	param.rateFacFlagNm = $("#rateFacFlagNm").val();
	param.changedTag = '1';
	
	$("#ratingFactorGrid").jqGrid('addRowData', $("#ratingFactorGrid").getDataIDs().length+1, param);
	$("#usgTypGrid2").jqGrid('setCell', rowId, 'count', Number($("#usgTypGrid2").getRowData(rowId).count)+1);
	
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
<form:form commandName="newProductDtlRateFacInsertAction" name="newProductDtlRateFacInsertAction" method="post">
  	<input type="hidden"  id="insertProdId" name="insertProdId" value="${newProductDtl.prodId }"/>
  	<input type="hidden" id="insertUsgTyp" name="insertUsgTyp" value="${newProductDtl.usgTyp }"/>
  	<input type="hidden" id="insertEffDt" name="insertEffDt" />
  	<input type="hidden" id="insertExpDt" name="insertExpDt" value="99991231235959"/>
  	<input type="hidden" id="rateFacNm" name="rateFacNm" />
  	<input type="hidden" id="rateFacFlagNm" name="rateFacFlagNm" />
  	
 	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00126"/>
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
						<select id="insertRateFac" name="insertRateFac" class="w180" >
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${rateFacList}" var="rateFacList" varStatus="status">
									<option value="${rateFacList.commonCd}">
										${rateFacList.commonCdNm}
									</option>
							</c:forEach>
						</select>
					</td>
		
					<th>
						<spring:message code="LAB.M08.LAB00070"/>
					</th>
					<td>
						<select id="insertRateFacFlag" name="insertRateFacFlag" class="w180">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${rateFacFlagList}" var="rateFacFlagList" varStatus="status">
									<option value="${rateFacFlagList.commonCd}">
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
		<a class="blue-btn" href="javascript:goInsertRateFac();" id="btn_insert">
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
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--��¥ �޷� ����� ��� �ʿ�-->
<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$( ".date" ).datepicker();
	var date1=new Date();
	date1.setDate( date1.getDate() + 1 );
	reault_y = date1.getFullYear();
	reault_m = eval(date1.getMonth()+1);
	reault_d = date1.getDate();
	if(parseInt(reault_m)<10){reault_m="0"+reault_m;}
	if(parseInt(reault_d)<10){reault_d="0"+reault_d;}
	var date2 = null;	

	if('${lngTyp}'=='ko'){
		date2 = reault_y + "-" + reault_m + "-" + reault_d;	
	}else {
		date2 =  reault_m + "-" + reault_d + "-" +reault_y; 
	}

	$("#insInactDt").val(date2);
	$("#nowDate").val(date2);

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 


});

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

function goClose() {
	if($("#inactDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00013"/>");
		return;
	}
	if($("#inactDt").val() < $("#nowDate").val() ) {
		alert("<spring:message code="MSG.M09.MSG00010"/>");
		return;
	}

	if('${lngTyp}'=='ko'){
		insActDt = $('#insInactDt').val();
		nowDate = $('#nowDate').val();

	}else {
		insActDt = dateFormatToStringYYYYMMDD($('#insInactDt').val());
		nowDate = dateFormatToStringYYYYMMDD($('#nowDate').val());
	}

	$("#insInactDt").val(insActDt);	
	$("#nowDate").val(nowDate);	

	$("#attrTypMapDel").attr("action", "/product/refInfo/commonInfo/attrTypMap/attrTypMapUpdateAction");
	$("#attrTypMapDel").attr("method", "post");
	$("#attrTypMapDel").submit();
}
</Script>

<!-- title -->
<div class="layer_top">
   <div class="title"><spring:message code="LAB.M07.LAB00225" /></div>
   <a href="javascript:closeModal();" class="close">Close</a>
</div>
<!--// title -->   

<form id="attrTypMapDel" name="attrTypMapDel" method="post">			
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>

<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00226"/></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:30%;">
			<col style="width:70%;">
		</colgroup>
			 <thead> 
				   <tr>
						<th><spring:message code="LAB.M07.LAB00222"/> </th>
						<td>
							<input id="attrTypNm" name="attrTypNm" value="${attrTypMap.attrTypNm}" type="text" class="w180" readonly>
							<input id="insAttrTyp" name="insAttrTyp" value="${attrTypMap.attrTyp}" type="hidden"> 
						</td>
					</tr>
				   <tr>
						<th><spring:message code="LAB.M07.LAB00214"/> </th>
						<td>
								<input id="attrNm" name="attrNm" value="${attrTypMap.attrNm}" type="text" class="w180" readonly>
								<input id="insAttrCd" name="insAttrCd" value="${attrTypMap.attrCd}" type="hidden"> 
						</td>
					</tr>
				   <tr>
						<th><spring:message code="LAB.M09.LAB00058" /> </th>
						<td>
						   <div class="date_box">
							   <div class="inp_date w135">
							   	  <input id="insActDt" name="insActDt" value="${attrTypMap.actDt}" type="hidden"> 
								  <fmt:parseDate value="99991231" var="insInactDt" pattern="yyyyMMdd"/>
								  <input  type="text" id="insInactDt" name="insInactDt" value="<fmt:formatDate value="${insInactDt}" pattern="yyyy-MM-dd" />" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
								</div>
							</div>
						</td>
					</tr>
			</thead>
	</table> 	
</div>	

<div class="btn_box">
	<!--����-->
	<a class="blue-btn" href="javascript:goClose();" id="btn_update"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<!--�ݱ�-->
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
 </div>

</form>	
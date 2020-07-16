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
	var date2 = null;
	
	date1.setDate( date1.getDate() + 1 );
	result_y = date1.getFullYear();
	
	if( date1.getMonth()+1 < 10 ) {
		result_m = '0' + (date1.getMonth()+1); 
	}
	else {
		result_m = date1.getMonth()+1;
	}
	if( date1.getDate() < 10 ) {
		result_d = '0' + date1.getDate();
	}
	else {
		result_d = date1.getDate();
	}

	if( '${lngTyp}' == 'ko' ) {
		date2 = result_y + "-" + result_m + "-" + result_d;
	} else {
		date2 = result_m + "-" + result_d + "-" + result_y;
	}
	
	$("#insertEffDt").val(date2);
	$("#insertExpDt").val("9999-12-31");
	
	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function goInsert1() {
	if($("#insertUsgTyp").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00027"/>");
		$("#insertUsgTyp").focus();
		return;
	}
	if($("#insertChrgCdSeq").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00015"/>");
		$("#insertChrgCdSeq").focus();
		return;
	}
	if($("#insertSeq").val() == "" ) {
		alert("<spring:message code="MSG.M07.MSG00124"/>");
		$("#insertSeq").focus();
		return;
	}
	if($("#insertBranch1").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00007"/>");
		$("#insertBranch1").focus();
		return;
	}
	if($("#insertBranch2").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00008"/>");
		$("#insertBranch2").focus();
		return;
	}
	if($("#insertBranch3").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00009"/>");
		$("#insertBranch3").focus();
		return;
	}
	if($("#insertBranch4").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00010"/>");
		$("#insertBranch4").focus();
		return;
	}
	if($("#insertBranch5").val() == "" ) {
		alert("<spring:message code="MSG.M15.MSG00011"/>");
		$("#insertBranch5").focus();
		return;
	}
	if($("#insertEffDt").val() == "" ) {
		alert("<spring:message code="MSG.M08.MSG00038"/>");
		$("#insertEffDt").focus();
		return;
	}

	$("#ratingCodeMapInsertAction").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeMap/ratingCodeMapInsertAction");
	$("#ratingCodeMapInsertAction").attr("method", "post");
	$("#ratingCodeMapInsertAction").submit();
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}

</Script>


<!-- title -->
<div class="layer_top">
	<div class="title">
   		<spring:message code="LAB.M01.LAB00146" />
   	</div>
	<a href="#" class="close"></a>
</div>
<!--// title -->   
<form:form commandName="ratingCodeMapInsertAction" name="ratingCodeMapInsertAction" method="post">
  	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title">
					<spring:message code="LAB.M01.LAB00142"/>
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
				<col style="width:8%;">
			</colgroup>
			<tbody>
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00029" />
					</th>
					<td>
						<select id="insertUsgTyp" name="insertUsgTyp" class="w300p">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${usgTypList}" var="usgTypList" varStatus="status">
								<option value="${usgTypList.commonCd}">
									${usgTypList.commonCdNm}								
								</option>
							</c:forEach>
						</select>
					</td>
		
					<th>
						<spring:message code="LAB.M15.LAB00023" />
					</th>
					<td>
						<select id="insertChrgCdSeq" name="insertChrgCdSeq" class="w180p">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${seqList}" var="seqList" varStatus="status">
								<option value="${seqList.commonCd}">
									${seqList.commonCdNm}								
								</option>
							</c:forEach>
						</select>
					</td>
				
					<th>
						<spring:message code="LAB.M07.LAB00288" />
					</th>
					<td>
						<select id="insertSeqNo" name="insertSeqNo" class="w180p">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${seqList}" var="seqList" varStatus="status">
								<option value="${seqList.commonCd}">
									${seqList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M15.LAB00016" />
					</th>
					<td>
						<input type="text" id="insertBranch1" name="insertBranch1" class="w180p"/>
					</td>
		
					<th>
						<spring:message code="LAB.M15.LAB00017" />
					</th>
					<td>
						<input type="text" id="insertBranch2" name="insertBranch2" class="w180p"/>
					</td>
				
					<th>
						<spring:message code="LAB.M15.LAB00018" />
					</th>
					<td>
						<input type="text" id="insertBranch3" name="insertBranch3" class="w180p"/>
					</td>
				</tr>
				<tr>
					<th>
						<spring:message code="LAB.M15.LAB00019" />
					</th>
					<td>
						<input type="text" id="insertBranch4" name="insertBranch4" class="w180p"/>
					</td>
					<th>
						<spring:message code="LAB.M15.LAB00020" />
					</th>
					<td>
						<input type="text" id="insertBranch5" name="insertBranch5" class="w180p"/>
					</td>
					<th>
						<spring:message code="LAB.M08.LAB00108" />
					</th>
					<td>
						<div class="date_box">
							<div class="inp_date w135p">
					  			<input type="text"  id="insertEffDt" name="insertEffDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
					<th>
						<spring:message code="LAB.M08.LAB00110" />
					</th>
					<td>
						<div class="date_box">
							<div class="inp_date w135p">
					  			<input type="text"  id="insertExpDt" name="insertExpDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00141" />
					</th>
					<td>
						<select id="insertChrgCd" name="insertChrgCd" class="w180p">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${chrgCdList}" var="chrgCdList" varStatus="status">
								<option value="${chrgCdList.commonCd}">
									${chrgCdList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M01.LAB00117" />
					</th>
					<td>
						<select id="insertBillFlag" name="insertBillFlag" class="w180p">
							<option value="">
								<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${flagList}" var="flagList" varStatus="status">
								<option value="${flagList.commonCd}">
									${flagList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btn_box">
		<a class="blue-btn" href="javascript:goInsert1();" id="btn_insert1">
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
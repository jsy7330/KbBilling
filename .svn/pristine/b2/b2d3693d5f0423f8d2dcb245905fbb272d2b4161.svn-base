<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<link href="/styles/nccbs/styles.css" rel="stylesheet" type="text/css" />
<link href="/styles/nccbs/printPage.css" rel="stylesheet" type="text/css" />
<link href="/styles/nccbs/print.css" rel="stylesheet" type="text/css" media="print">

<script type="text/javascript">

$(document).ready(function() {
	$("#popup_soId").append('${ctrtInfo.soNm}');		
	$("#popup_custId").append('${ctrtInfo.custId}');
	$("#popup_custNm").append('${ctrtInfo.custNm}');
	$("#popup_custTp").append('${ctrtInfo.custTpNm}');
	$("#popup_custCl").append('${ctrtInfo.custClNm}');
	$("#popup_corpRegNoAsMask").append('${ctrtInfo.corpRegNoAsMask}');
	
	$("#popup_pymAcntId").append('${ctrtInfo.pymAcntId}');
	$("#popup_pymAcntNm").append('${ctrtInfo.pymAcntNm}');
	$("#popup_billCycle").append('${pymDtlInfo.billCyclNm}');
	$("#popup_pymMethodNm").append('${ctrtInfo.pymMthdNm}');
	$("#popup_emailAddress").append('${pymDtlInfo.emlAsMask}');
	$("#popup_telNo").append('${pymDtlInfo.mtelNoAsMask}');
	
	$("#popup_basicProdGrp").append('${basicProdInfo.prod_grp_nm}');
	$("#popup_basicProdName").append(getNameAndId('${basicProdInfo.prod_cd}','${basicProdInfo.prod_cd_nm}'));
	$("#popup_ctrtStat").append('${basicProdInfo.ctrt_stat_nm}');
	$("#popup_promCnt").append('${basicProdInfo.prom_cnt}'+'<spring:message code="LAB.M01.LAB00016"/>');
	$("#popup_rateStrtDt").append(stringToDateformatYYYYMMDD('${basicProdInfo.rate_strt_dt}'));
	$("#popup_actDttm").append(stringToDateformatYYYYMMDDHH24MISS('${basicProdInfo.act_dttm}'));
	$("#popup_lastActDttm").append(stringToDateformatYYYYMMDDHH24MISS('${chgDate}'));
	$("#popup_processingRate").append('${ctrtInfo.procRate}');
	$("#popup_postCode").append('${basicProdInfo.instl_zip_cd}');
	$("#popup_city").append('${basicProdInfo.instl_city}');
	$("#popup_state").append('${basicProdInfo.instl_state_nm}');
	if('${basicProdInfo.instl_addr_dtl}' == undefined || '${basicProdInfo.instl_addr_dtl}' == ''){
		$("#popup_address").append('${basicProdInfo.instl_base_addr}');
	}else{
		$("#popup_address").append('${basicProdInfo.instl_base_addr}'+" "+'${basicProdInfo.instl_addr_dtl}');
	}
	$("#popup_printDate").append('<spring:message code="LAB.M02.LAB00002"/>'+" : "+stringToDateformatYYYYMMDD('${today}'));
	$(window).resize(function() {
		var windowWidth = $(window).width();
		
		if(windowWidth <= '1036'){
			$("#popup_custInfoTbl").width('1036px');
			$("#popup_title").width($("#popup_custInfoTbl").width());
			$("#print_div").width($("#popup_custInfoTbl").width());
			$("#popup_pymInfoTbl").width('1036px');
			$("#popup_ctrtInfoTbl").width('1036px');
			$("#popup_addressInfoTbl").width('1036px');
			$("#popup_prodListInfoTbl").width('1036px');
			$("#popup_chargeTbl").width($("#popup_custInfoTbl").width());
		}else{
			$("#popup_custInfoTbl").width('100%');
			$("#popup_title").width($("#popup_custInfoTbl").width());
			$("#print_div").width($("#popup_custInfoTbl").width());
			$("#popup_pymInfoTbl").width('100%');
			$("#popup_ctrtInfoTbl").width('100%');
			$("#popup_addressInfoTbl").width('100%');
			$("#popup_prodListInfoTbl").width('100%');
			$("#popup_chargeTbl").width($("#popup_custInfoTbl").width());
		}
		
	});
	
	//계약정보 btn Event
	 $('#print_btn').on('click',function (e) {
		  	window.print();
		}
   );
});

/*
 * 명칭 포맷팅
 */
function getNameAndId(id, name){
	if(name == '' || name == null){
		return id;
	}else{
		return name + '(' + id + ')'; 
	}
	 
}

</script>
<div>

<div id="popup_title" class="main_btn_box">
	<div class="h3_bg">
		<h3><spring:message code="LAB.M01.LAB00259"/></h3>
		<div class="nav">                                        
			<div id="popup_printDate">
			</div>
		</div>
	</div>
</div>

<!--고객정보 -->
<div class="mt10">
	
<div id="print_div" class="main_btn_box">
	<div class="fl">
		<h4>● <spring:message code="LAB.M01.LAB00054"/></h4> 
	</div>
	<div class="fr">
		<a id="print_btn" class="grey-btn">
			<span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span>
		</a>
	</div>
</div>
	<table id="popup_custInfoTbl" class="writeview tdB">
		<colgroup>
			<col style="width:15%;">
			<col style="width:18%;">
			<col style="width:15%;">
			<col style="width:18%;">
			<col style="width:15%;">
			<col style="width:19%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/></th> 
				<td>
					<div id="popup_soId" class="inp_date np custInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00051"/></th>
				<td>
					<div id="popup_custId" class="inp_date np custInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00050"/></th> 
				<td>
					<div id="popup_custNm" class="inp_date np custInfoCls">
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00053"/></th> 
				<td>
					<div id="popup_custTp" class="inp_date np custInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00048"/></th>
				<td>
					<div id="popup_custCl" class="inp_date np custInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00188"/></th>
				<td>
					<div id="popup_corpRegNoAsMask" class="inp_date np custInfoCls">
					</div>
				</td>
			</tr>
		</thead>
	</table>   
</div> 
<!--납부정보 -->
<div class="mt10">
	<h4>● <spring:message code="LAB.M02.LAB00038"/></h4>
	<table id="popup_pymInfoTbl" class="writeview tdB">
		<colgroup>
			<col style="width:15%;">
			<col style="width:18%;">
			<col style="width:15%;">
			<col style="width:18%;">
			<col style="width:15%;">
			<col style="width:19%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M02.LAB00006"/></th> 
				<td>
					<div id="popup_pymAcntId" class="inp_date np pymInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M02.LAB00008"/></th>
				<td>
					<div id="popup_pymAcntNm" class="inp_date np pymInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M02.LAB00016"/></th> 
				<td>
					<div id="popup_pymMethodNm" class="inp_date np pymInfoCls">
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M10.LAB00042"/></th> 
				<td>
					<div id="popup_billCycle" class="inp_date np pymInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M08.LAB00183"/></th>
				<td>
					<div id="popup_emailAddress" class="inp_date np pymInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00065"/></th>
				<td>
					<div id="popup_telNo" class="inp_date np pymInfoCls">
					</div>
				</td>
			</tr>
		</thead>
	</table>   
</div> 

<!--기본계약정보 -->
<div class="mt10">
	<h4>● <spring:message code="LAB.M01.LAB00040"/></h4>
	<table id="popup_ctrtInfoTbl" class="writeview tdB">
		<colgroup>
			<col style="width:15%;">
			<col style="width:18%;">
			<col style="width:15%;">
			<col style="width:18%;">
			<col style="width:15%;">
			<col style="width:19%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M07.LAB00126"/></th> 
				<td>
					<div id="popup_basicProdGrp" class="inp_date np ctrtInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M07.LAB00130"/></th>
				<td  colspan="4">
					<div id="popup_basicProdName" class="inp_date np ctrtInfoCls">
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00037"/></th> 
				<td>
					<div id="popup_ctrtStat" class="inp_date np ctrtInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M08.LAB00002"/></th>
				<td>
					<div id="popup_promCnt" class="inp_date np ctrtInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00235"/></th>
				<td>
					<div id="popup_processingRate" class="inp_date np ctrtInfoCls">
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00230"/></th> 
				<td>
					<div id="popup_rateStrtDt" class="inp_date np ctrtInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M07.LAB00287"/></th>
				<td>
					<div id="popup_actDttm" class="inp_date np ctrtInfoCls">
					</div>
				</td>
				<th><spring:message code="LAB.M10.LAB00055"/></th>
				<td>
					<div id="popup_lastActDttm" class="inp_date np ctrtInfoCls">
					</div>
				</td>
			</tr>
		</thead>
	</table>   
</div> 
<!-- 설치주소 -->
<div class="mt10">
	<h4>● <spring:message code="LAB.M07.LAB00301"/></h4>
	<table id="popup_addressInfoTbl" class="writeview tdB">
		<colgroup>
			<col style="width:15%;">
			<col style="width:18%;">
			<col style="width:15%;">
			<col style="width:18%;">
			<col style="width:15%;">
			<col style="width:19%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M08.LAB00087"/></th> 
				<td>
					<div id="popup_postCode" class="inp_date np addressCls">
					</div>
				</td>
				<th><spring:message code="LAB.M03.LAB00087"/></th> 
				<td>
					<div id="popup_city" class="inp_date np addressCls">
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00232"/></th>
				<td>
					<div id="popup_state" class="inp_date np addressCls">
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00190"/></th>
				<td colspan="5">
					<div id="popup_address" class="inp_date np addressCls">
					</div>
				</td>				
			</tr>
		</thead>
	</table>
</div>
<!-- 장비/부가서비스 -->
<div class="mt10">
	<h4>● <spring:message code="LAB.M09.LAB00238"/></h4>
	<table id="popup_prodListInfoTbl" class="writeview tdB">
		<colgroup>
			<col style="width:15%;">
			<col style="width:85%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M10.LAB00020"/></th> 
				<td>
					<!-- <div id="popup_deviceProductList" class="inp_date np prodListCls">
					</div> -->
						<c:if test="${deviceInfoList != '[]'}">
							<c:forEach items="${deviceInfoList}" var="deviceInfoList" varStatus="status">
								${deviceInfoList.device_prod_cd_nm}<c:if test="${not status.last }">, </c:if>
						</c:forEach>
					</c:if>
				</td>
			<tr>
				<th><spring:message code="LAB.M06.LAB00083"/></th>
				<td>
					<!-- <div id="popup_addOnProductList" class="inp_date np addressCls">
					</div> -->
					<c:if test="${addInfoList != '[]'}">
						<c:forEach items="${addInfoList}" var="addInfoList" varStatus="status">
							${addInfoList.add_prod_nm}<c:if test="${not status.last }">, </c:if>
						</c:forEach>
					</c:if>
				</td>				
			</tr>
		</thead>
	</table>
</div>


<!-- Charge Information -->
<div class="mt10 grid">

	<h4>● <spring:message code="LAB.M08.LAB00180"/></h4>
	<table id="popup_chargeTbl" class="writeview tdB">
		<colgroup>
			<col style="width:7%;">
			<col style="width:19%;">
			<col style="width:7%;">
			<col style="width:7%;">
			<col style="width:10%;">
			<col style="width:10%;">
			<col style="width:10%;">
			<col style="width:10%;">
			<col style="width:10%;">
			<col style="width:10%;">
		</colgroup>
		<thead>
			<tr>
				<th rowspan="2"><spring:message code="LAB.M07.LAB00146"/></th> 
				<th rowspan="2"><spring:message code="LAB.M09.LAB00229"/></th> 
				<th rowspan="2"><spring:message code="LAB.M01.LAB00230"/></th> 
				<th rowspan="2"><spring:message code="LAB.M07.LAB00247"/></th> 
				<th colspan="3" style="text-align:center;"><spring:message code="LAB.M08.LAB00195"/></th> 
				<th colspan="3" style="text-align:center;"><spring:message code="LAB.M08.LAB00196"/></th> 
			</tr>
			<tr>
				<th><spring:message code="LAB.M13.LAB00004"/></th> 
				<th><spring:message code="LAB.M14.LAB00033"/></th> 
				<th><spring:message code="LAB.M14.LAB00078"/></th> 
				<th><spring:message code="LAB.M13.LAB00004"/></th> 
				<th><spring:message code="LAB.M14.LAB00033"/></th> 
				<th><spring:message code="LAB.M14.LAB00078"/></th>  
			</tr>
		</thead>
		<tbody>
			<c:if test="${chargeConfList != '[]'}">
				<c:forEach items="${chargeConfList}" var="chargeConfList" varStatus="status">
					<tr>
					<td style="text-align: center;" class="txt_c">${chargeConfList.nego_prod_cd}</td>
					<td>${chargeConfList.nego_prod_cd_nm}</td>
					<td style="text-align: center;"class="txt_c"><fmt:formatDate value='${chargeConfList.rate_strt_dt}' pattern='dd/MM/YYYY'/></td>
					<td style="text-align: center;"class="txt_c"><fmt:formatNumber value='${chargeConfList.nego_cnt}' groupingUsed='true'/></td>
					<td style="text-align: right;"class="txt_r">RM <fmt:formatNumber value='${chargeConfList.sale_amt_monthly}' groupingUsed='true'/></td>
					<td style="text-align: right;"class="txt_r"><fmt:formatNumber value='${chargeConfList.discount_rate_monthly}' groupingUsed='true'/>%</td>
					<td style="text-align: right;"class="txt_r"><fmt:formatNumber value='${chargeConfList.nego_amt_monthly}' groupingUsed='true'/></td>
					<td style="text-align: right;"class="txt_r">RM <fmt:formatNumber value='${chargeConfList.sale_amt_onetime}' groupingUsed='true'/></td>
					<td style="text-align: right;"class="txt_r"><fmt:formatNumber value='${chargeConfList.discount_rate_onetime}' groupingUsed='true'/>%</td>
					<td style="text-align: right;"class="txt_r"><fmt:formatNumber value='${chargeConfList.nego_amt_onetime}' groupingUsed='true'/></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
		
</div>

</div>
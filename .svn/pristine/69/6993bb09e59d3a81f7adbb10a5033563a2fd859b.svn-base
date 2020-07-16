<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

$(document).ready(function() {
	//화면초기화
	custPymListPopupInit();
	
	//그리드 처리
	$("#paymentGrid").jqGrid({
		url : '/customer/customer/payment/paymentManagement/getPymAcntInfoListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			soId : $("#paramSoId").val(),
			custId : $("#paramCustId").val(),
			isUnmaskYn : $("#isUnmaskYn").val()
		},
		colModel: [
			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
			{ label: 'custId' , name: 'custId', hidden:true,width : 0},
			{ label: 'custNm' , name: 'custNm', hidden:true,width : 0},
			{ label: 'zipCd' , name: 'zipCd', hidden:true,width : 0},
			{ label: 'baseAddr' , name: 'baseAddr', hidden:true,width : 0},
			{ label: 'addrDtl' , name: 'addrDtlAsMask', hidden:true,width : 0},
			// { label: 'city' , name: 'city', hidden:true,width : 0},
			// { label: 'stateNm' , name: 'stateNm', hidden:true,width : 0},
			{ label: 'eml' , name: 'emlAsMask', hidden:true,width : 0},
			{ label: 'telNo' , name: 'telNo', hidden:true,width : 0,formatter:telNoFormatter},
			{ label: 'mtelNo' , name: 'mtelNoAsMask', hidden:true,width : 0,formatter:telNoFormatter},
			{ label: 'faxNo' , name: 'faxNo', hidden:true,width : 0,formatter:telNoFormatter},
			{ label: 'pymMthd' , name: 'pymMthd', hidden:true,width : 0},
			{ label: 'pmcResn' , name: 'pmcResn', hidden:true,width : 0},
			{ label: 'billMdmGiroYn' , name: 'billMdmGiroYn', hidden:true,width : 0},
			{ label: 'billMdmEmlYn' , name: 'billMdmEmlYn', hidden:true,width : 0},
			{ label: 'billMdmSmsYn' , name: 'billMdmSmsYn', hidden:true,width : 0},
			{ label: 'bnkCd' , name: 'bnkCd', hidden:true,width : 0},
			{ label: 'acntOwnerNm' , name: 'acntOwnerNm', hidden:true,width : 0},
			{ label: 'acntNo' , name: 'acntNoAsMask', hidden:true,width : 0},
			{ label: 'cdtcdExpDt' , name: 'cdtcdExpDt', hidden:true, formatter:stringTypeFormatterYYYYMM,width : 0},
			{ label: 'pymDt' , name: 'pymDt', hidden:true,width : 0},
			{ label: 'cmsCl' , name: 'cmsCl', hidden:true,width : 0},
			{ label: 'tbrFlg' , name: 'tbrFlg', hidden:true,width : 0},
			{ label: 'arrsNobillYn' , name: 'arrsNobillYn', hidden:true,width : 0},
			{ label: 'useAcntNmYn' , name: 'useAcntNmYn', hidden:true,width : 0},
			{ label: 'rcptId' , name: 'rcptId', hidden:true,width : 0},
			{ label: 'billCyclCd' , name: 'billCyclCd', hidden:true,width : 0},
			{ label: 'mstBnkCd' , name: 'mstBnkCd', hidden:true,width : 0},
			{ label: 'vrAcntNo' , name: 'vrAcntNo', hidden:true,width : 0},
			{ label: 'orgId' , name: 'orgId', hidden:true,width : 0},
			{ label: 'orgNm' , name: 'orgNm', hidden:true,width : 0},
			{ label: 'usrId' , name: 'usrId', hidden:true,width : 0},
			{ label: 'usrNm' , name: 'usrNm', hidden:true,width : 0},
			{ label: 'extId' , name: 'extId', hidden:true,width : 0},
			{ label: 'regrId' , name: 'regrId', hidden:true,width : 0},
			{ label: 'chgrId' , name: 'chgrId', hidden:true,width : 0},
			{ label: 'chgrOrgId' , name: 'chgrOrgId', hidden:true,width : 0},
			{ label: 'chgrOrgNm' , name: 'chgrOrgNm', hidden:true,width : 0},
		    { label: '<spring:message code="LAB.M02.LAB00006"/>', name: 'pymAcntId', width : 120, align:"center"},
		    { label: '<spring:message code="LAB.M02.LAB00008"/>', name: 'acntNm', width : 180},
		    { label: '<spring:message code="LAB.M02.LAB00016"/>', name: 'pymMthdNm', width : 120}, 
		    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrNm', width : 150},           
		    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 150,align:"center"},    
		    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrNm', width : 150},
		    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 150,align:"center"},
  		    
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 130,
		width: 988,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "pymAcntInfoList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#paymentGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	custPymListPopup_SetSelectedDate(rowid);
        },
       	loadComplete : function () {
       		$("#pymAcntInfoList").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#pymAcntInfoList").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	
});


/*
 * 화면 초기화 함수
 */
function custPymListPopupInit(){
	
	//검색정보 초기화
	$("#paymentGrid").clearGridData();
	
	//입력부 초기화
	custPymListPopupInputInit();

}


/*
 * 입력부 초기화 함수
 */
function custPymListPopupInputInit(){
 	//납부계정정보 Disable처리
	$("#custPymListPopupBody #pymAcntBasicInfo input:text").val('');
	$("#custPymListPopupBody #pymAcntBasicInfo input:text").addClass('not-active');
	$("#custPymListPopupBody #pymAcntBasicInfo input:text").attr('disabled', true);

	$('#custPymListPopupBody #inputSoId').selectmenu({});
	$('#custPymListPopupBody #inputSoId').val('SEL');
	$('#custPymListPopupBody #inputSoId').selectmenu("refresh");
	$('#custPymListPopupBody #inputPymMthd').selectmenu({});
	$('#custPymListPopupBody #inputPymMthd').val('SEL');
	$('#custPymListPopupBody #inputPymMthd').selectmenu("refresh");
	$('#custPymListPopupBody #inputchgResn').selectmenu({});
	$('#custPymListPopupBody #inputchgResn').val('SEL');
	$('#custPymListPopupBody #inputchgResn').selectmenu("refresh");
	$('#custPymListPopupBody #inputBillCyclCd').val('SEL');
	$('#custPymListPopupBody #inputBillCyclCd').selectmenu({});
	$('#custPymListPopupBody #inputBillCyclCd').selectmenu("refresh");
	$("#custPymListPopupBody #pymAcntBasicInfo select").selectmenu("disable");
	
 		
	$("#custPymListPopupBody #pymAcntBasicInfo input:checkbox").removeAttr("checked");
	$("#custPymListPopupBody #pymAcntBasicInfo input:checkbox").addClass('not-active');
	$("#custPymListPopupBody #pymAcntBasicInfo input:checkbox").attr('disabled',true);
	$("#custPymListPopupBody #pymAcntBasicInfo label").addClass('not-active');
	$("#custPymListPopupBody #pymAcntBasicInfo label").attr('disabled',true);
	
	//청구서 정보 Disable 처리 invoiceInfo
	$("#custPymListPopupBody #invoiceInfo input:text").val('');
	$("#custPymListPopupBody #invoiceInfo input:text").addClass('not-active');
	$("#custPymListPopupBody #invoiceInfo input:text").attr('disabled', true);
	
	$('#custPymListPopupBody #inputPaperInvoice').selectmenu({});
	$('#custPymListPopupBody #inputPaperInvoice').val('SEL');
	$('#custPymListPopupBody #inputPaperInvoice').selectmenu("refresh");
	$('#custPymListPopupBody #inputEmailInvoice').selectmenu({});
	$('#custPymListPopupBody #inputEmailInvoice').val('SEL');
	$('#custPymListPopupBody #inputEmailInvoice').selectmenu("refresh");
	$('#custPymListPopupBody #inputSmsInvoice').selectmenu({});
	$('#custPymListPopupBody #inputSmsInvoice').val('SEL');
	$('#custPymListPopupBody #inputSmsInvoice').selectmenu("refresh");
	$("#custPymListPopupBody #invoiceInfo select").selectmenu("disable");
	
	//카드/은행정보 Disable 처리
	$("#custPymListPopupBody #cardRegInfo input:text").val('');
	$("#custPymListPopupBody #cardRegInfo input:text").addClass('not-active');
	$("#custPymListPopupBody #cardRegInfo input:text").attr('disabled', true);
	$('#custPymListPopupBody #inputCardBankCd').selectmenu({});
	$('#custPymListPopupBody #inputCardBankCd').val('SEL');
	$('#custPymListPopupBody #inputCardBankCd').selectmenu("refresh");
	$("#custPymListPopupBody #cardRegInfo select").selectmenu("disable");
}




/*
 * 데이터 선택 이벤트 처리
 */
function custPymListPopup_SetSelectedDate(rowId){
	var data = $("#paymentGrid").getRowData(rowId);
	$("#custPymListPopupBody #inputPymAcntId").val(data.pymAcntId);
	$("#custPymListPopupBody #inputSoId").val(data.soId);
	$('#custPymListPopupBody #inputSoId').selectmenu("refresh");
	$('#custPymListPopupBody #inputCustNm').val(data.custNm);
	$('#custPymListPopupBody #inputPymAcntNm').val(data.acntNm);
	$('#custPymListPopupBody #inputPymMthd').val(data.pymMthd);
	$('#custPymListPopupBody #inputPymMthd').selectmenu("refresh");
	$('#custPymListPopupBody #inputchgResn').val(data.pmcResn);
	$('#custPymListPopupBody #inputchgResn').selectmenu("refresh");
	$('#custPymListPopupBody #inputTelNo').val(data.telNo);
	$('#custPymListPopupBody #inputFaxNo').val(data.faxNo);
	$('#custPymListPopupBody #inputCellPhoneNo').val(data.mtelNoAsMask);
	$('#custPymListPopupBody #inputEmail').val(data.emlAsMask);
	$('#custPymListPopupBody #inputIssueTax').prop("checked",data.tbrFlg == 'Y' ? true : false);
	$('#custPymListPopupBody #inputExemptLateFee').prop("checked",data.arrsNobillYn == 'Y' ? true : false);
	$('#custPymListPopupBody #inputPrintPymNm').prop("checked",data.useAcntNmYn == 'Y' ? true : false);
	$('#custPymListPopupBody #inputPostNo').val(data.zipCd);
	$('#custPymListPopupBody #inputBaseAddr').val(data.baseAddr);
	$('#custPymListPopupBody #inputDtlAddr').val(data.addrDtlAsMask);
	// $('#custPymListPopupBody #inputCity').val(data.city);
	// $('#custPymListPopupBody #inputStateNm').val(data.stateNm);
	$('#custPymListPopupBody #inputBillCyclCd').val(data.billCyclCd);
	$('#custPymListPopupBody #inputBillCyclCd').selectmenu("refresh");
	$('#custPymListPopupBody #inputPaperInvoice').val(data.billMdmGiroYn == 'N' ? 'SEL' : data.billMdmGiroYn);
	$('#custPymListPopupBody #inputPaperInvoice').selectmenu("refresh");
	$('#custPymListPopupBody #inputEmailInvoice').val(data.billMdmEmlYn == 'N' ? 'SEL' : data.billMdmEmlYn);
	$('#custPymListPopupBody #inputEmailInvoice').selectmenu("refresh");
	$('#custPymListPopupBody #inputSmsInvoice').val(data.billMdmSmsYn == 'N' ? 'SEL' : data.billMdmSmsYn);
	$('#custPymListPopupBody #inputSmsInvoice').selectmenu("refresh");
	$('#custPymListPopupBody #inputRegrNm').val(getNameAndId(data.usrId, data.usrNm));
	$('#custPymListPopupBody #inputRegrOrgNm').val(getNameAndId(data.orgId, data.orgNm));
	$('#custPymListPopupBody #inputRegrDt').val(data.regDate);
	$('#custPymListPopupBody #inputChgrNm').val(getNameAndId(data.chgrId, data.chgrNm));
	$('#custPymListPopupBody #inputChgrOrgNm').val(getNameAndId(data.chgrOrgId, data.chgrOrgNm));
	$('#custPymListPopupBody #inputChgrDt').val(data.chgDate);
	
	//납부방법변경 이벤트 호출
	custPymListPopup_chagedPymMthd();
	
	$('#custPymListPopupBody #inputCardBankCd').val(data.bnkCd);
	$('#custPymListPopupBody #inputCardBankCd').selectmenu("refresh");
	$('#custPymListPopupBody #inputCardBankOwnNo').val(data.acntNoAsMask);
	$('#custPymListPopupBody #inputCardBankOwnNm').val(data.acntOwnerNm);
	$('#custPymListPopupBody #inputExp').val(data.cdtcdExpDt);
}



/*
 * 납부방법 변경 처리
 */
function custPymListPopup_chagedPymMthd(){
	if($('#custPymListPopupBody #inputPymMthd').val() == '02'){
        $('#custPymListPopupBody #inputCardBankCd').each( function() {
            $('#custPymListPopupBody #inputCardBankCd option:gt(0)').remove();
        });
		<c:forEach items="${bankCodeList}" var="bankCode" varStatus="status">
			var str = '<option value="${bankCode.commonCd}">${bankCode.commonCdNm}</option>';
			$('#custPymListPopupBody #inputCardBankCd').append(str);
		</c:forEach>
	}else if($('#custPymListPopupBody #inputPymMthd').val() == '03'){
        $('#custPymListPopupBody #inputCardBankCd').each( function() {
        	$('#custPymListPopupBody #inputCardBankCd option:gt(0)').remove();
        });
		<c:forEach items="${cardCodeList}" var="cardCode" varStatus="status">
			var str = '<option value="${cardCode.commonCd}">${cardCode.commonCdNm}</option>';
			$('#custPymListPopupBody #inputCardBankCd').append(str);
		</c:forEach>
	}else{
        $('#custPymListPopupBody #inputCardBankCd').each( function() {
        	$('#custPymListPopupBody #inputCardBankCd option:gt(0)').remove();
        });
	}
	$('#custPymListPopupBody #inputCardBankCd').val('SEL');
	$('#custPymListPopupBody #inputCardBankCd').selectmenu("refresh");
	$('#custPymListPopupBody #inputCardBankOwnNm').val('');
	$('#custPymListPopupBody #inputCardBankOwnNo').val('');
	$('#custPymListPopupBody #inputExp').val('');	
}


</script>
<!-- <div style="width:1000px;" > -->
		<!-- title -->
 <div class="layer_top">
     <div class="title"><spring:message code="LAB.M02.LAB00007"/></div>
     <a href="#" class="close"></a>
</div>
<!--// title -->
                                         
<!-- center -->
<div id='custPymListPopupBody' class="layer_box">
	<table id="paymentGrid" class="w100p"></table>
	<div id="paymentGridPager"></div>
	
	<!-- 납부계정기초정보 -->
	<div id="pymAcntBasicInfo">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M02.LAB00010"/></h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 10%;">
				<col style="width: 15%;">
				<col style="width: 10%;">
				<col style="width: 15%;">
				<col style="width: 10%;">
				<col style="width: 15%;">
				<col style="width: 10%;">
				<col style="width: 15%;">
			</colgroup>
			<tbody>
				<tr>
					<!-- 납부번호 -->
					<th><spring:message code="LAB.M02.LAB00006"/></th>
					<td><input id="inputPymAcntId" name="pymAcntId" type="text" class="w100p" />
					</td>
					<!-- 사업 -->
					<th><spring:message code="LAB.M07.LAB00003" /></th>
					<td><select id="inputSoId" name="soId" class="w135">
							<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
									<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
							</c:forEach>
					</select></td>
					<!-- 고객명 -->
					<th><spring:message code="LAB.M01.LAB00050" /></th>
					<td colspan="3"><input id="inputCustNm" name="custNm" type="text" class="w100p" /></td>
				</tr>
				<tr>
					<!-- 납부자명 -->
					<th><spring:message code="LAB.M02.LAB00008" /><span class="dot">*</span></th>
					<td colspan="3"><input id="inputPymAcntNm" name="acntNm" type="text" class="w100p" /></td>
					<th><spring:message code="LAB.M02.LAB00016" /><span class="dot">*</span></th>
					<!-- 납부방법 -->
					<td>
						<select id="inputPymMthd" name="pymMthd" class="w135">
							<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${pymMthdCodeList}" var="pymMthdCode" varStatus="status">
								<option value="${pymMthdCode.commonCd}">${pymMthdCode.commonCdNm}</option>
							</c:forEach>
						</select>
					</td>
					<!-- 변경사유 -->
					<th><spring:message code="LAB.M06.LAB00058" /><span class="dot">*</span></th>
					<td>
						<select id="inputchgResn" name="pmcResn" class="w135">
							<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${pymChngResnCodeList}" var="pymChngResnCode" varStatus="status">
								<option value="${pymChngResnCode.commonCd}">${pymChngResnCode.commonCdNm}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<!-- 전화번호 -->
					<th><spring:message code="LAB.M09.LAB00065" /></th>
					<td><input id="inputTelNo" name="inputTelNo" type="text" class="w100p" /></td>
					<!-- FAX번호 -->
					<th><spring:message code="LAB.M15.LAB00036" /></th>
					<td><input id="inputFaxNo" name="inputFaxNo" type="text" class="w100p" /></td>
					<!-- 휴대폰번호 -->
					<th><spring:message code="LAB.M14.LAB00076" /><span class="dot">*</span></th>
					<td><input id="inputCellPhoneNo" name="inputCellPhoneNo" type="text" class="w100p" /></td>
					<!-- 이메일주소 -->
					<th><spring:message code="LAB.M08.LAB00119" /><span class="dot">*</span></th>
					<td><input id="inputEmail" name="eml" type="text" class="w100p" /></td>
				</tr>
				<tr>
					<!-- 선택정보 -->
					<th><spring:message code="LAB.M07.LAB00198" /></th>
					<td colspan="3">
						<!-- 청구계정명 출력여부 --> 
						<input type="checkbox" id="inputPrintPymNm" name="useAcntNmYn" /> <label for="inputPrintPymNm"><spring:message code="LAB.M02.LAB00015" /></label>
					</td>
					<th><spring:message code="LAB.M10.LAB00042" /><span class="dot">*</span></th>
					<td colspan="3">
						<select id="inputBillCyclCd" name="billCyclCd" class="w135">
							<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${billCyclCodeList}" var="billCyclCode" varStatus="status">
								<option value="${billCyclCode.commonCd}">${billCyclCode.commonCdNm}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<!-- 청구서 정보 -->
	<div id="invoiceInfo">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M10.LAB00036" /></h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 10%;">
				<col style="width: 15%;">
				<col style="width: 10%;">
				<col style="width: 15%;">
				<col style="width: 10%;">
				<col style="width: 15%;">
				<col style="width: 10%;">
				<col style="width: 15%;">
			</colgroup>
			<tbody>
				<tr>
					<!-- 주소 -->
					<th><spring:message code="LAB.M08.LAB00087" /><span class="dot">*</span></th>
					<td>
						<input type="text" id="inputPostNo" name="zipCd" class="w100p" > 
					</td>
					<th><spring:message code="LAB.M01.LAB00218"/><span class="dot">*</span></th>
					<td colspan="2">
						<input type="text" id="inputBaseAddr" name="baseAddr" class="w100p">
					</td>
					<th><spring:message code="LAB.M07.LAB00102"/></th>
					<td colspan="2">
						<input type="text" id="inputDtlAddr" name="addrDtl" class="w100p">
					</td>
				</tr>
				<!-- <tr>
					<th><spring:message code="LAB.M03.LAB00087"/><span class="dot">*</span></th>
					<td colspan="2">
						<input type="text" id="inputCity" name="city" class="w100p">
					</td>
					<th><spring:message code="LAB.M09.LAB00232"/><span class="dot">*</span></th>
					<td colspan="4">
						<input type="text" id="inputStateNm" name="stateNm" class="w100p">
					</td>
				</tr> -->
				<tr>
					<!-- 청구매체 -->
					<th><spring:message code="LAB.M10.LAB00034" /><span class="dot">*</span></th>
					<td colspan="7">
						<div class="date_box">
							<div class="date_sort">
								<!-- 지로 -->
								<ul>
									<li><spring:message code="LAB.M09.LAB00199" /></li>
									<li><select id="inputPaperInvoice" name="billMdmGiroYn" class="w100">
											<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
											<c:forEach items="${paperInvoiceCodeList}" var="paperInvoiceCode" varStatus="status">
												<option value="${paperInvoiceCode.commonCd}">${paperInvoiceCode.commonCdNm}</option>
											</c:forEach>
										</select>
									</li>
								</ul>
							</div>
							<div class="date_sort">
								<!-- 이메일 -->
								<ul>
									<li>
										<spring:message code="LAB.M08.LAB00120" />
									</li>
									<li>
										<select id="inputEmailInvoice" name="billMdmEmlYn" class="w100">
											<option value='SEL'>
												<spring:message code="LAB.M15.LAB00002"/>
											</option>
											<c:forEach items="${emailInvoiceCodeList}" var="emailInvoiceCode" varStatus="status">
												<option value="${emailInvoiceCode.commonCd}">${emailInvoiceCode.commonCdNm}</option>
											</c:forEach>
										</select>
									</li>
								</ul>
							</div>
							<div class="date_sort">
								<!-- SMS / MMS -->
								<ul>
									<li>
										<spring:message code="LAB.M15.LAB00081" />
									</li>
									<li>
										<select id="inputSmsInvoice" name="billMdmSmsYn" class="w100">
											<option value='SEL'>
												<spring:message code="LAB.M15.LAB00002"/>
											</option>
											<c:forEach items="${smsInvoiceCodeList}" var="smsInvoiceCode" varStatus="status">
												<option value="${smsInvoiceCode.commonCd}">${smsInvoiceCode.commonCdNm}</option>
											</c:forEach>
										</select>
									</li>
								</ul>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- 카드/은행 정보 -->
	<div id="cardRegInfo">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M03.LAB00077"/>
				</h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 10%;">
				<col style="width: 23%;">
				<col style="width: 10%;">
				<col style="width: 23%;">
				<col style="width: 10%;">
				<col style="width: 24%;">
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="MSG.M10.MSG00039"/></th>
					<td><input id="inputRegrNm" name="inputRegrNm" type="text" class="w100p not-active" disabled="disabled"/></td>
					<th><spring:message code="MSG.M10.MSG00040"/></th>
					<td><input id="inputRegrOrgNm" name="inputRegrOrgNm" type="text" class="w100p not-active" disabled="disabled"/></td>
					<th><spring:message code="MSG.M10.MSG00041"/></th>
					<td><input id="inputRegrDt" name="inputRegrDt" type="text" class="w100p not-active" disabled="disabled"/></td>
				</tr>
				<tr>
					<th><spring:message code="MSG.M10.MSG00042"/></th>
					<td><input id="inputChgrNm" name="inputChgrNm" type="text" class="w100p not-active" disabled="disabled" /></td>
					<th><spring:message code="MSG.M10.MSG00043"/></th>
					<td><input id="inputChgrOrgNm" name="inputChgrOrgNm" type="text" class="w100p not-active" disabled="disabled"/></td>
					<th><spring:message code="MSG.M10.MSG00044"/></th>
					<td><input id="inputChgrDt" name="inputChgrDt" type="text" class="w100p not-active" disabled="disabled"/></td>
				</tr>
			</tbody>
	</table>
	</div>
						
	 <div class="btn_box">
	       <a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>
 </div>
		
<!-- </div> -->
<!-- footer -->
<input id="paramSoId" type="text" value="${SO_ID}" hidden />
<input id="paramCustId" type="text" value="${CUST_ID}" hidden />

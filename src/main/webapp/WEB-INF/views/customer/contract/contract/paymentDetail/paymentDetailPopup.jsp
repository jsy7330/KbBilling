<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	$(".Layer_wrap select").selectmenu();
	$('#popPymAcntId').append("${pymDtlInfo.pymAcntId}");
	$('#popSoNm').append("${pymDtlInfo.soNm}");
	$('#popCustNm').append("${pymDtlInfo.custNm}");
	$('#popPymAcntNm').append("${pymDtlInfo.acntNm}");
	$('#popPymMthd').append("${pymDtlInfo.pymMthdNm}");
	$('#popPmcResn').append("${pymDtlInfo.pmcResnNm}");
	$('#popBillCyclCdNm').append("${pymDtlInfo.billCyclNm}");
	$('#popEml').append("${pymDtlInfo.emlAsMask}");
	$('#popMtelNo').append("${pymDtlInfo.mtelNoAsMask}");
	$('#popTelNo').append("${pymDtlInfo.telNo}");
	$('#popFaxNo').append("${pymDtlInfo.faxNo}");
	$('#popOptionInfo').append("${pymDtlInfo.useAcntNmYn}");	//납부계정명사용여부
	
	$('#popZipCd').append("${pymDtlInfo.zipCd}");
	$('#popBaseAddr').append("${pymDtlInfo.baseAddr}");
	$('#popDtlAddr').append("${pymDtlInfo.addrDtlAsMask}");
	/* $('#popCity').append("${pymDtlInfo.city}");
	$('#popStateNm').append("${pymDtlInfo.stateNm}"); */
	
	var str = getAcntMthdYn();
	$('#popAcntMthd').append(str);		//청구방법
	$('#popRegrNm').append(getNameAndId("${pymDtlInfo.regrId}","${pymDtlInfo.regrNm}"));
	$('#popRegOrgNm').append(getNameAndId("${pymDtlInfo.orgId}","${pymDtlInfo.orgNm}"));
	$('#popRegDt').append(stringToDateformatYYYYMMDDHH24MISS("${pymDtlInfo.regDate}")); 
	$('#popChgrNm').append(getNameAndId("${pymDtlInfo.chgrId}","${pymDtlInfo.chgrNm}"));
	$('#popChgOrgNm').append(getNameAndId("${pymDtlInfo.chgrOrgId}","${pymDtlInfo.chgrOrgNm}"));
	$('#popChgDt').append(stringToDateformatYYYYMMDDHH24MISS("${pymDtlInfo.chgDate}")); 

});

function getAcntMthdYn(){
	var str =''
	if("${pymDtlInfo.billMdmGiroYn}" != 'N'){	//지로O
		str+="${pymDtlInfo.billMdmGiroNm}";
	}else if("${pymDtlInfo.billMdmEmlYn}" != 'N'){
		if("${pymDtlInfo.billMdmGiroYn}" != 'N'){	//지로o 이메일o
			str += "/"+"${pymDtlInfo.billMdmEmlNm}";
		}else{	//지로x 이메일o
			str += "${pymDtlInfo.billMdmEmlNm}";
		}
	}else if("${pymDtlInfo.billMdmEmlYn}" != 'N'){
		if("${pymDtlInfo.billMdmGiroYn}" != 'N' || "${pymDtlInfo.billMdmEmlYn}" != 'N'){	//지로 또는 이메일을 선택한 경우
			str += "/"+"${pymDtlInfo.billMdmEmlNm}";
		}else{ //지로x 이메일 x
			str += "${pymDtlInfo.billMdmEmlNm}";
		}
	}
	return str;
} 
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

	<div class='layer_top layer-minw1100'>
		<div class='title'><spring:message code="LAB.M07.LAB00306"/>
		</div>
		<a href='#' class='close'></a>
	</div>
	
	<div class='layer_box'>

		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M02.LAB00038"/></h4>
			</div>
		</div>
		<table class='writeview column_3 row_4'>
			<tr class='col3'>
				<th><spring:message code="LAB.M02.LAB00006"/></th><!-- 납부계정ID -->
				<td><div id="popPymAcntId" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="LAB.M07.LAB00003"/></th><!-- 사업 -->
				<td><div id="popSoNm" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="LAB.M01.LAB00050"/></th><!-- 고객명 -->
				<td><div id="popCustNm" class="inp_date np custInfoCls"></div></td>
			</tr>
			<tr class='col3'>
				<th><spring:message code="LAB.M02.LAB00008"/></th><!-- 납부계정명 -->
				<td>
					<div id="popPymAcntNm" class="inp_date np custInfoCls"></div>
				</td>
				<th><spring:message code="LAB.M02.LAB00016"/></th><!-- 납부방법 -->
				<td><div id="popPymMthd" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="LAB.M06.LAB00058"/></th><!-- 변경사유 -->
				<td><div id="popPmcResn" class="inp_date np custInfoCls"></div></td>
			</tr>
			<tr class='col3'>
				<th><spring:message code="LAB.M10.LAB00042"/></th><!-- 청구주기 -->
				<td><div id="popBillCyclCdNm" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="LAB.M08.LAB00183"/></th><!-- 이메일주소 -->
				<td>
					<div id="popEml" class="inp_date np custInfoCls"></div>
				</td>
				<th><spring:message code="LAB.M14.LAB00076"/></th><!-- 휴대폰번호 -->
				<td>
					<div id="popMtelNo" class="inp_date np custInfoCls"></div>
				</td>
			</tr>
			<tr class='col3'>
				<th><spring:message code="LAB.M08.LAB00184"/></th><!-- 유선번호 -->
				<td>
					<div id="popTelNo" class="inp_date np custInfoCls"></div>
				</td>
				<th><spring:message code="LAB.M15.LAB00035"/></th><!-- FAX번호 -->
				<td>
					<div id="popFaxNo" class="inp_date np custInfoCls"></div>
				</td>
				<th><spring:message code="LAB.M02.LAB00042"/></th><!-- 납부계정명사용여부 -->
				<td><div id="popOptionInfo" class="inp_date np custInfoCls"></div></td>
			</tr>
		</table>
		<!--//1th table-->
		<div class='main_btn_box'>
			<div class='fl'>
				<h4 class='sub_title'><spring:message code="LAB.M10.LAB00036"/></h4>
			</div>
		</div>
		<table class='writeview column_3 row_4'>
			<tr class='col3'>
				<th><spring:message code="LAB.M08.LAB00087"/></th><!--주소  -->
				<td><div id="popZipCd" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="LAB.M01.LAB00218"/></th><!--기본주소  -->
				<td><div id="popBaseAddr" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="LAB.M07.LAB00102"/></th><!--상세주소  -->
				<td><div id="popDtlAddr" class="inp_date np custInfoCls"></div></td>
			</tr>
			<%-- <tr class='col3'>
				<th><spring:message code="LAB.M03.LAB00087"/></th><!--도시 -->
				<td><div id="popCity" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="LAB.M09.LAB00232"/></th>
				<td colspan="3"><div id="popStateNm" class="inp_date np custInfoCls"></div></td>
			</tr> --%>
			<tr class='col3'>
				<th><spring:message code="LAB.M10.LAB00035"/></th><!-- 청구방법 -->
				<td><div id="popAcntMthd" class="inp_date np custInfoCls"></div></td>
			</tr>
		</table>
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M03.LAB00077"/></h4>
			</div>
		</div>
		<table class='writeview column_3 row_2'>
			<tr class='col3'>
				<th><spring:message code="MSG.M10.MSG00039"/></th>
				<td><div id="popRegrNm" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="MSG.M10.MSG00040"/></th>
				<td><div id="popRegOrgNm" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="MSG.M10.MSG00041"/></th>
				<td><div id="popRegDt" class="inp_date np custInfoCls"></div></td>
			</tr>
			<tr class='col3'>
				<th><spring:message code="MSG.M10.MSG00042"/></th>
				<td><div id="popChgrNm" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="MSG.M10.MSG00043"/></th>
				<td><div id="popChgOrgNm" class="inp_date np custInfoCls"></div></td>
				<th><spring:message code="MSG.M10.MSG00044"/></th>
				<td><div id="popChgDt" class="inp_date np custInfoCls"></div></td>
			</tr>
		</table>
		<div class='btn_box'>
			<a class="grey-btn close" id="" href="#">
				<span><spring:message code="LAB.M03.LAB00027"/></span>
			</a>
		</div><!--//btn_box-->
	</div>

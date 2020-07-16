<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

	var type = "I";	//저장버튼클릭시 등록, 수정 구분 값

	$(document).ready(function() {
		
		//저장버튼
		$("#btnInsert").click(function() {
			insertData();            
		});
		
		settingDatepicker();	//팝업달력 셋팅
		
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#excpStrtYymmddInp").val(stringToDateformatYYYYMMDD(yyyymmdd));
		$("#excpEndYymmddInp").val(stringToDateformatYYYYMMDD(yyyymmdd));
	});
	
	//등록
	function insertData(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		
		if(!check){
			return;
		}
		
		var param = checkValidation();
		var url = "insertDunningExemptionInfo.json";
		
		console.log(param);
		
		if(param != false){
			
			$.ajax({
				url : url,
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.resule != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();//부모창 새로고침
						inputClear();
						
						$("#btnClose").trigger('click');
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		}
	}
	
	//밸리데이션 체크
	function checkValidation(){
		var param = new Object();

		param.soId = $("#soIdInp").val();
		if(param.soId == ''){
			//사업 을(를) 선택하세요.
			alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		
		param.upymMngTp = $("#upymMngTpInp").val();
		if(param.upymMngTp == ''){
			//대응유형 을(를) 선택하세요.
			alert('<spring:message code="LAB.M03.LAB00057" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		
		param.upymExcpRsnCd = $("#upymExcpRsnInp").val();
		if(param.upymExcpRsnCd == ''){
			//제외사유 을(를) 선택하세요.
			alert('<spring:message code="LAB.M09.LAB00095" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}

		param.custId = $("#custIdInp").val();
		if(param.custId == ''){
			//고객ID을 입력하세요.
			alert('<spring:message code="LAB.M01.LAB00046" /> <spring:message code="MSG.M08.MSG00043" />');	
			return false;
		}
		param.ctrtId = $("#ctrtIdInp").val();
		if(param.ctrtId == ''){
			//계약ID을 입력하세요.
			alert('<spring:message code="LAB.M01.LAB00032" /> <spring:message code="MSG.M08.MSG00043" />');	
			return false;
		}
		
		param.pymCustId = $("#pymCustIdInp").val();
		param.pymAcntId = $("#pymAcntIdInp").val();
		if(param.pymCustId == '' || param.pymAcntId == ''){
			//납부고객/계정ID을 입력하세요.
			alert('<spring:message code="LAB.M02.LAB00012" /> <spring:message code="MSG.M08.MSG00043" />');	
			return false;
		}
		
		param.excpStrtYymmdd = dateFormatToStringYYYYMMDD($("#excpStrtYymmddInp").val());
		if(param.excpStrtYymmdd == ''){
			//제외시작일을 입력하세요.
			alert('<spring:message code="LAB.M09.LAB00096" /> <spring:message code="MSG.M08.MSG00043" />');	
			return false;
		}
		
		param.excpEndYymmdd = dateFormatToStringYYYYMMDD($("#excpEndYymmddInp").val());
		if(param.excpEndYymmdd == ''){
			//제외종료일을 입력하세요.
			alert('<spring:message code="LAB.M09.LAB00097" /> <spring:message code="MSG.M08.MSG00043" />');	
			return false;
		}
		
		param.excpCt = $("#excpCtInp").val();
		
		return param;
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00157"/><!-- 조치제외정보 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box" id="areaInP" >
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M03.LAB00078"/><!-- 등록상세정보 --></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:14%;" />
			<col style="width:10%;" />
			<col style="width:14%;" />
			<col style="width:16%;" />
			<col style="width:10%;" />
			<col style="width:36%;" />
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span></th>
				<td colspan="5">
					<select class="w150" id="soIdInp" name="soIdInp">
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00057"/><!-- 대응유형 --><span class="dot">*</span></th>
				<td>
					<select id="upymMngTpInp" name="upymMngTpInp" class="w180">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${dunningTpCd}" var="dunningTpCd" varStatus="status">
							<option value="${dunningTpCd.commonCd}">${dunningTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M09.LAB00095"/><!-- 제외사유 --><span class="dot">*</span></th>
				<td colspan="3">
					<select id="upymExcpRsnInp" name="upymExcpRsnInp" class="w180">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${dunningTpDtlCd}" var="dunningTpDtlCd" varStatus="status">
							<option value="${dunningTpDtlCd.commonCd}">${dunningTpDtlCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00045"/><!-- 고객 --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date w130">
							<input id="custIdInp" type="text" class="w100" />
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00032"/><!-- 계약ID --><span class="dot">*</span></th>
				<td>
					<input type="text" id="ctrtIdInp" name="ctrtIdInp" class="w100" />
				</td>
				<th><spring:message code="LAB.M02.LAB00012"/><!-- 납부고객/계정ID --><span class="dot">*</span></th>
				<td>
					<input type="text" id="pymCustIdInp" name="pymCustIdInp" class="w100" />
					/
					<input type="text" id="pymAcntIdInp" name="pymAcntIdInp" class="w100" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00096"/><!-- 제외시작일 --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date w170">
							<input type="text" id="excpStrtYymmddInp" name="excpStrtYymmddInp" class="datepicker" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00097"/><!-- 제외종료일 --><span class="dot">*</span></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date w170">
							<input type="text" id="excpEndYymmddInp" name="excpEndYymmddInp" class="datepicker" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00094"/><!-- 제외내역상세 --></th>
				<td colspan="5">
					<input type="text" id="excpCtInp" name="excpCtInp" class="w800" />
				</td>
			</tr>
		</tbody>
	</table>
</div>
<!--// center -->
                  
                  
<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>                  


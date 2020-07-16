<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	$(document).ready(function() {
		
		byteCheckKeyEvent();	//바이트체크
		settingDatepicker();	//팝업달력 셋팅
		
		$('#assrKndCdInP').selectmenu({});
		$('#assrCorpCdInP').selectmenu({});
		
		$('#stpAmtInP').number( true );
		$('#loanAmtInP').number( true );
		$('#loanArpiAmtInP').number( true );
		
		//조직검색 팝업
		$("#btnOrgPopInp").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=orgIdInP&returnId2=orgNmInP&popType2=04";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		$("#btnInsertInP").click(function() {
			insertDataInP();
		});
		
		
	});
	
	//등록
	function insertDataInP(){
		
		var param = checkValidationInP();
		
		if(param != false){
			
			$.ajax({
				url : 'insertCollInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();//부모창 새로고침
						
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
	function checkValidationInP(){
		var param = new Object();
		
		param.orgId = $("#orgIdInP").val();
		if(param.orgId == ''){
			// 조직 을(를) 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00138" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.insuCtrtrNm = $("#insuCtrtrNmInP").val();
		if(param.insuCtrtrNm == ''){
			//보험계약자명 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00078" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.stckNo = $("#stckNoInP").val();
		if(param.stckNo == ''){
			//증권번호 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M09.LAB00197" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.assrKndCd = $("#assrKndCdInP").val();
		if(param.assrKndCd == ''){
			// 보증보험종류 을(를) 선택해 주세요
			alert('<spring:message code="LAB.M06.LAB00075" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.assrCt = $("#assrCtInP").val();
		
		param.assrStrtDt = dateFormatToStringYYYYMMDD($("#assrStrtDtInP").val());
		if(param.assrStrtDt == ''){
			//보증개시일 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00069" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.assrEndDt = dateFormatToStringYYYYMMDD($("#assrEndDtInP").val());
		if(param.assrEndDt == ''){
			//보증만료일 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00072" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.assrCorpCd = $("#assrCorpCdInP").val();
		if(param.assrCorpCd == ''){
			// 보증회사명 을(를) 선택해 주세요
			alert('<spring:message code="LAB.M06.LAB00077" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.assrBrNm = $("#assrBrNmInP").val();
		param.assrAgncyNm = $("#assrAgncyNmInP").val();
		
		param.stpAmt = $("#stpAmtInP").val();
		if(param.stpAmt == ''){
			//보증금액 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00070" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.loanAmt = $("#loanAmtInP").val();
		if(param.loanAmt == ''){
			//단말여신금액 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M03.LAB00023" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.stpDt = dateFormatToStringYYYYMMDD($("#stpDtInP").val());
		if(param.stpDt == ''){
			//설정일 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M07.LAB00204" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.loanArpiAmt = $("#loanArpiAmtInP").val();
		if(param.loanArpiAmt == ''){
			//요금여신금액 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M08.LAB00050" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.termDt = dateFormatToStringYYYYMMDD($("#termDtInP").val());
		if(param.termDt == ''){
			//해지일자 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M14.LAB00049" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.rmrk = $("#rmrkInP").val();
		param.collOpt1 = "0";
		param.collOpt2 = "0";
		param.collKndCd = "01";
		param.loanStpResn = '<spring:message code="LAB.M03.LAB00037"/>';
		
		return param;
	}
	
	function setDataInP(orgId){
		
		
		var param = new Object();
		param.orgId = orgId;
		
		$.ajax({
			url : 'selectOrgInfo.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data.result.loanGvFlg);
				if(data.result.loanGvFlg != "1"){
					alert('<spring:message code="MSG.M09.MSG00035"/>');		//조직관리에서 여신부여 여부를 체크하여야만 변경 가능 합니다.
					
					$("#orgIdInP").val("");
					$("#orgNmInP").val("");
					$("#repNmInP").val("");
				}
			
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
		
		
	}
</script>	


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M03.LAB00037"/><!-- 담보관리-보증보험등록 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box" id="areaUpP" >
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M06.LAB00074"/><!-- 보증보험일반사항 --></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M09.LAB00138"/><!-- 조직 --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="orgIdInP" name="orgIdInP" disabled="disabled" class="w150 not-active">
						</div>
						<div class="inp_date p100">
							<input type="text" id="orgNmInP" name="orgNmInP" disabled="disabled" class="p100 not-active">
							<a href="#" id="btnOrgPopInp" class="search">search</a>
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M03.LAB00062"/><!-- 대표자 --></th>
				<td>
					<input type="text" id="repNmInP" name="repNmInP" class="w200" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00078"/><!-- 보험계약자명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="insuCtrtrNmInP" name="insuCtrtrNmInP" class="w200" checkbyte="150"/>
				</td>
				<th><spring:message code="LAB.M09.LAB00197"/><!-- 증권번호 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="stckNoInP" name="stckNoInP" class="w200" checkbyte="150" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00075"/><!-- 보증보험종류 --><span class="dot">*</span></th>
				<td>
					<select id="assrKndCdInP" name="assrKndCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${assrKndCd}" var="assrKndCd" varStatus="status">
							<option value="${assrKndCd.commonCd}">${assrKndCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M06.LAB00071"/><!-- 보증내용 --></th>
				<td>
					<input type="text" id="assrCtInP" name="assrCtInP" class="w200" checkbyte="1500" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00069"/><!-- 보증개시일 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="assrStrtDtInP" name="assrStrtDtInP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
				<th><spring:message code="LAB.M06.LAB00072"/><!-- 보증만료일 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="assrEndDtInP" name="assrEndDtInP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00077"/><!-- 보증회사명 --><span class="dot">*</span></th>
				<td colspan="3" >
					<select id="assrCorpCdInP" name="assrCorpCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${assrCorpCd}" var="assrCorpCd" varStatus="status">
							<option value="${assrCorpCd.commonCd}">${assrCorpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00076"/><!-- 보증보험지점명 --></th>
				<td>
					<input type="text" id="assrBrNmInP" name="assrBrNmInP" class="w200" checkbyte="450" />
				</td>
				<th><spring:message code="LAB.M06.LAB00079"/><!-- 보험대리점명 --></th>
				<td>
					<input type="text" id="assrAgncyNmInP" name="assrAgncyNmInP" class="w200" checkbyte="450" />
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00011"/><!-- 감정내역 --></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M06.LAB00070"/><!-- 보증금액 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="stpAmtInP" name="stpAmtInP" class="w200" checkbyte="12"/>
				</td>
				<th><spring:message code="LAB.M03.LAB00023"/><!-- 단말여신금액 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="loanAmtInP" name="loanAmtInP" class="w200" checkbyte="12" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00204"/><!-- 설정일 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="stpDtInP" name="stpDtInP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
				<th><spring:message code="LAB.M08.LAB00050"/><!-- 요금여신금액 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="loanArpiAmtInP" name="loanArpiAmtInP" class="w200" checkbyte="12" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00049"/><!-- 해지일자 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="termDtInP" name="termDtInP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
				<th><spring:message code="LAB.M12.LAB00008"/><!-- 특기사항 --></th>
				<td>
					<input type="text" id="rmrkInP" name="rmrkInP" class="w200" checkbyte="1500" />
				</td>
			</tr>
			
		</tbody>
	</table>
	
	
</div>
<!--// center -->
                  
                  
<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsertInP"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>         

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
		
		$('#assrKndCdUpP').selectmenu({});
		$('#assrCorpCdUpP').selectmenu({});
		
		$('#stpAmtUpP').number( true );
		$('#loanAmtUpP').number( true );
		$('#loanArpiAmtUpP').number( true );
		
		//조직검색 팝업
		$("#btnOrgPopInp").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=orgIdUpP&returnId2=orgNmUpP&popType2=04";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		$("#btnUpdateUpP").click(function() {
			updateDataUpP();
		});
		
		detailDataUpP();
		
	});
	
	//등록
	function updateDataUpP(){
		
		var param = checkValidationUpP();
		
		if(param != false){
			
			$.ajax({
				url : 'updateCollInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						
						var rowId  = $("#collateralTable").jqGrid("getGridParam", "selrow" );
						//detailData(rowId);//부모창 새로고침
						
						clearData();
						
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
	function checkValidationUpP(){
		var param = new Object();
		
		param.orgId = $("#orgIdUpP").val();
		if(param.orgId == ''){
			// 조직 을(를) 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00138" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.insuCtrtrNm = $("#insuCtrtrNmUpP").val();
		if(param.insuCtrtrNm == ''){
			//보험계약자명 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00078" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.stckNo = $("#stckNoUpP").val();
		if(param.stckNo == ''){
			//증권번호 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M09.LAB00197" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.assrKndCd = $("#assrKndCdUpP").val();
		if(param.assrKndCd == ''){
			// 보증보험종류 을(를) 선택해 주세요
			alert('<spring:message code="LAB.M06.LAB00075" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.assrCt = $("#assrCtUpP").val();
		
		param.assrStrtDt = dateFormatToStringYYYYMMDD($("#assrStrtDtUpP").val());
		if(param.assrStrtDt == ''){
			//보증개시일 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00069" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.assrEndDt = dateFormatToStringYYYYMMDD($("#assrEndDtUpP").val());
		if(param.assrEndDt == ''){
			//보증만료일 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00072" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.assrCorpCd = $("#assrCorpCdUpP").val();
		if(param.assrCorpCd == ''){
			// 보증회사명 을(를) 선택해 주세요
			alert('<spring:message code="LAB.M06.LAB00077" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.assrBrNm = $("#assrBrNmUpP").val();
		param.assrAgncyNm = $("#assrAgncyNmUpP").val();
		
		param.stpAmt = $("#stpAmtUpP").val();
		if(param.stpAmt == ''){
			//보증금액 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00070" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.loanAmt = $("#loanAmtUpP").val();
		if(param.loanAmt == ''){
			//단말여신금액 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M03.LAB00023" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.stpDt = dateFormatToStringYYYYMMDD($("#stpDtUpP").val());
		if(param.stpDt == ''){
			//설정일 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M07.LAB00204" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.loanArpiAmt = $("#loanArpiAmtUpP").val();
		if(param.loanArpiAmt == ''){
			//요금여신금액 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M08.LAB00050" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.termDt = dateFormatToStringYYYYMMDD($("#termDtUpP").val());
		if(param.termDt == ''){
			//해지일자 을(를)  입력해 주세요.
			alert('<spring:message code="LAB.M14.LAB00049" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.rmrk = $("#rmrkUpP").val();
		param.collOpt1 = "0";
		param.collOpt2 = "0";
		param.collKndCd = "01";
		param.loanStpResn = '<spring:message code="LAB.M03.LAB00038"/>';
		param.collOrg = $("#collOrgUpP").val();
		
		
		return param;
	}
	
	function setDataUpP(orgId){
		
		
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
					
					$("#orgIdUpP").val("");
					$("#orgNmUpP").val("");
					$("#repNmUpP").val("");
				}
			
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
	}
	
	
	function detailDataUpP(){
		
		var rowId  = $("#collateralTable").jqGrid("getGridParam", "selrow" );
		var data = $("#collateralTable").getRowData(rowId);
		
		var param = new Object();
		param.orgId = data.orgId;
		param.collOrg = data.collOrg;
		
		$.ajax({
			url : 'selectCollInfo.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data.result);
				setDataUpP2(data.result);
				
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
		
	}
	
	function setDataUpP2(data){
		
		$("#collOrgUpP").val(data.collOrg);
		
		$("#soNmUpP").val(data.soNm);
		$("#orgIdUpP").val(data.orgId);
		$("#orgNmUpP").val(data.orgNm);
		$("#insuCtrtrNmUpP").val(data.insuCtrtrNm);
		$("#repNmUpP").val(data.repNm);
		$("#assrKndCdUpP").val(data.assrKndCd);
		$("#assrKndCdUpP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#stckNoUpP").val(data.stckNo);
		$("#assrStrtDtUpP").val(stringToDateformatYYYYMMDD(data.assrStrtDt));
		$("#assrEndDtUpP").val(stringToDateformatYYYYMMDD(data.assrEndDt));
		$("#assrCorpCdUpP").val(data.assrCorpCd);
		$("#assrCorpCdUpP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#assrCtUpP").val(data.assrCt);
		$("#assrBrNmUpP").val(data.assrBrNm);
		$("#assrAgncyNmUpP").val(data.assrAgncyNm);
		$("#stpAmtUpP").val(data.stpAmt);
		$("#loanAmtUpP").val(data.loanAmt);
		$("#stpDtUpP").val(stringToDateformatYYYYMMDD(data.stpDt));
		$("#loanArpiAmtUpP").val(data.loanArpiAmt);
		$("#termDtUpP").val(stringToDateformatYYYYMMDD(data.termDt));
		$("#rmrkUpP").val(data.rmrk);
		
	}
	
	
</script>	


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M03.LAB00038"/><!-- 담보관리-보증보험등록 --></div>
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
							<input type="text" id="orgIdUpP" name="orgIdUpP" disabled="disabled" class="w150 not-active" />
							
							<input type="hidden" id="collOrgUpP" name="collOrgUpP" />

						</div>
						<div class="inp_date p100">
							<input type="text" id="orgNmUpP" name="orgNmUpP" disabled="disabled" class="p100 not-active">
							<!-- <a href="#" id="btnOrgPopInp" class="search">search</a> -->
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M03.LAB00062"/><!-- 대표자 --></th>
				<td>
					<input type="text" id="repNmUpP" name="repNmUpP" class="w200" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00078"/><!-- 보험계약자명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="insuCtrtrNmUpP" name="insuCtrtrNmUpP" class="w200" checkbyte="150"/>
				</td>
				<th><spring:message code="LAB.M09.LAB00197"/><!-- 증권번호 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="stckNoUpP" name="stckNoUpP" class="w200" checkbyte="150" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00075"/><!-- 보증보험종류 --><span class="dot">*</span></th>
				<td>
					<select id="assrKndCdUpP" name="assrKndCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${assrKndCd}" var="assrKndCd" varStatus="status">
							<option value="${assrKndCd.commonCd}">${assrKndCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M06.LAB00071"/><!-- 보증내용 --></th>
				<td>
					<input type="text" id="assrCtUpP" name="assrCtUpP" class="w200" checkbyte="1500" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00069"/><!-- 보증개시일 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="assrStrtDtUpP" name="assrStrtDtUpP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
				<th><spring:message code="LAB.M06.LAB00072"/><!-- 보증만료일 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="assrEndDtUpP" name="assrEndDtUpP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00077"/><!-- 보증회사명 --><span class="dot">*</span></th>
				<td colspan="3" >
					<select id="assrCorpCdUpP" name="assrCorpCdUpP" class="w150">
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
					<input type="text" id="assrBrNmUpP" name="assrBrNmUpP" class="w200" checkbyte="450" />
				</td>
				<th><spring:message code="LAB.M06.LAB00079"/><!-- 보험대리점명 --></th>
				<td>
					<input type="text" id="assrAgncyNmUpP" name="assrAgncyNmUpP" class="w200" checkbyte="450" />
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
					<input type="text" id="stpAmtUpP" name="stpAmtUpP" class="w200" checkbyte="12"/>
				</td>
				<th><spring:message code="LAB.M03.LAB00023"/><!-- 단말여신금액 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="loanAmtUpP" name="loanAmtUpP" class="w200" checkbyte="12" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00204"/><!-- 설정일 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="stpDtUpP" name="stpDtUpP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
				<th><spring:message code="LAB.M08.LAB00050"/><!-- 요금여신금액 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="loanArpiAmtUpP" name="loanArpiAmtUpP" class="w200" checkbyte="12" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00049"/><!-- 해지일자 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="termDtUpP" name="termDtUpP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
				<th><spring:message code="LAB.M12.LAB00008"/><!-- 특기사항 --></th>
				<td>
					<input type="text" id="rmrkUpP" name="rmrkUpP" class="w200" checkbyte="1500" />
				</td>
			</tr>
			
		</tbody>
	</table>
	
	
</div>
<!--// center -->
                  
                  
<div class="btn_box">
	<a class="grey-btn" href="#" id="btnUpdateUpP"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>         

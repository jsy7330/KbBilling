<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	var type = "I";	//저장버튼클릭시 등록, 수정 구분 값

	$(document).ready(function() {
		
		byteCheckKeyEvent();	//바이트체크
		settingDatepicker();	//팝업달력 셋팅
		
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#appyStrtDtInP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		
		$('#busiTpCdInP').selectmenu({});
		$('#privTpCdInP').selectmenu({});
		$('#orgStatCdInP').selectmenu({});
		$('#bnkCdInP').selectmenu({});
		
		//저장버튼
		$("#btnInsert").click(function() {
			insertDataInP();            
		});
		
		//조직검색 팝업
		$("#btnOrgPopInP").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=orgIdInP&returnId2=orgNmInP&popType2=03";
			
			var width = 815;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		$("#btnAddrInP").click(function() {
				
			var url="/system/common/common/postMng/postPopup.ajax";
			url = url + "?zipCd=zipCdInP";           //우편번호
			url = url + "&baseAddr=addr1InP"; // 기본주소
			url = url + "&addrDtl=addr2InP"; // 상세주소 
			url = url + "&mode=o";   //모드 o지정 
			 
			var width = 810;
			var height = 500;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			 
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no");
			
		});
		
		//담당자 검색
		$("#btnInchrgIdInP").click(function() {
			var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
			url = url + "?returnId1=psnInchrgNmInP";           //사용자이름
			url = url + "&returnId2=psnInchrgIdInP";           //사용자아이디
			url = url + "&popType=o";   //모드 o지정 
			 
			var width = 940;
			var height = 470;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			 
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no"); 
		});
		
		//정산담당자검색
		$("#btnSttlPsnInchrgInP").click(function() {
			var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
			url = url + "?returnId1=sttlPsnInchrgNmInP";           //사용자이름
			url = url + "&returnId2=sttlPsnInchrgInP";           //사용자아이디
			url = url + "&popType=o";   //모드 o지정 
			 
			var width = 940;
			var height = 470;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			 
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no"); 
		});
		
	});
	
	function setDataInP(appyStrtDt, appyEndDt, soId){
		
		var param = new Object();
		param.orgId = $("#orgIdInP").val();
		
		$.ajax({
			url : 'selectDistributionInfo.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data.distributionInfoVO);
				
				var distributionInfoVO = data.distributionInfoVO
				
				var orgIdInP = $("#orgIdInP").val();
				var orgNmInP = $("#orgNmInP").val();

				$("#areaInP input").val("");
				$("#areaInP select").val("");
				$("#areaInP select").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				$("#appyStrtDtInP").val(stringToDateformatYYYYMMDD(appyStrtDt));
				$("#appyEndDtInP").val(stringToDateformatYYYYMMDD(appyEndDt));
				$("#orgIdInP").val(orgIdInP);
				$("#orgNmInP").val(orgNmInP);
				$("#soIdInP").val(soId);
				
				if(distributionInfoVO == null){
					type = "I";
				}else{
					type = "U";
					
					$("#busiTpCdInP").val(distributionInfoVO.busiTpCd);
					$("#busiTpCdInP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					
					$("#privTpCdInP").val(distributionInfoVO.privTpCd);
					$("#privTpCdInP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					
					$("#borNoInP").val(distributionInfoVO.borNo);
					$("#corpRegNoInP").val(distributionInfoVO.corpRegNo);
					$("#busiTpInP").val(distributionInfoVO.busiTp);
					$("#busiCndtInP").val(distributionInfoVO.busiCndt);
					$("#repNmInP").val(distributionInfoVO.repNm);
					$("#repRnoInP").val(distributionInfoVO.repRno);
					$("#mngrNmInP").val(distributionInfoVO.mngrNm);
					$("#mngrRnoInP").val(distributionInfoVO.mngrRno);
					$("#telNoInP").val(distributionInfoVO.telNo);
					$("#faxNoInP").val(distributionInfoVO.faxNo);
					$("#zipCdInP").val(distributionInfoVO.zipCd);
					$("#addr1InP").val(distributionInfoVO.addr1);
					$("#addr2InP").val(distributionInfoVO.addr2);
					$("#psnInchrgIdInP").val(distributionInfoVO.psnInchrgId);
					$("#psnInchrgNmInP").val(distributionInfoVO.psnInchrgNm);
					$("#emlInP").val(distributionInfoVO.eml);
					$("#hmpgInP").val(distributionInfoVO.hmpg);
					
					$("#orgStatCdInP").val(distributionInfoVO.orgStatCd);
					$("#orgStatCdInP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					
					$("#termResnInP").val(distributionInfoVO.termResn);
					$("#sttlPsnInchrgInP").val(distributionInfoVO.sttlPsnInchrg);
					$("#sttlPsnInchrgNmInP").val(distributionInfoVO.sttlPsnInchrgNm);
					$("#dpstNmInP").val(distributionInfoVO.dpstNm);
					$("#sttlInchrgCpInP").val(distributionInfoVO.sttlInchrgCp);
					
					$("#bnkCdInP").val(distributionInfoVO.bnkCd);
					$("#bnkCdInP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					
					$("#sttlInchrgTelInP").val(distributionInfoVO.sttlInchrgTel);
					$("#acntNoInP").val(distributionInfoVO.acntNo);
					$("#sttlInchrgEmlInP").val(distributionInfoVO.sttlInchrgEml);
				}
				
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
	}
	
	//등록
	function insertDataInP(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		
		if(!check){
			return;
		}
		
		var param = checkValidationInP();
		var url = "";
		if(type == "I"){
			url = "insertDistributionInfo.json";
		}else if(type == "U"){
			url = "updateDistributionInfo.json";
		}
		console.log(param);
		
		if(param != false){
			
			$.ajax({
				url : url,
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
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
	function checkValidationInP(){
		var param = new Object();
		
		param.soId = $("#soIdInP").val();
		param.orgId = $("#orgIdInP").val();
		if(param.orgId == ''){
			//조직ID 을(를) 선택하세요.
			alert('<spring:message code="LAB.M09.LAB00139" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.orgNm = $("#orgNmInP").val();
		param.busiTpCd = $("#busiTpCdInP").val();
		if(param.busiTpCd == ''){
			//사업자구분 을(를) 선택하세요.
			alert('<spring:message code="LAB.M07.LAB00011" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.privTpCd = $("#privTpCdInP").val();
		if(param.privTpCd == ''){
			//자사구분 을(를) 선택하세요.
			alert('<spring:message code="LAB.M09.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.borNo = $("#borNoInP").val();
		param.corpRegNo = $("#corpRegNoInP").val();
		param.busiTp = $("#busiTpInP").val();
		param.busiCndt = $("#busiCndtInP").val();
		param.repNm = $("#repNmInP").val();
		if(param.repNm == ''){
			//대표자명 을(를) 입력해 주세요
			alert('<spring:message code="LAB.M03.LAB00063" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.repRno = $("#repRnoInP").val();
		param.mngrNm = $("#mngrNmInP").val();
		param.mngrRno = $("#mngrRnoInP").val();
		param.telNo = $("#telNoInP").val();
		param.faxNo = $("#faxNoInP").val();
		param.zipCd = $("#zipCdInP").val();
		param.addr1 = $("#addr1InP").val();
		param.addr2 = $("#addr2InP").val();
		param.psnInchrgId = $("#psnInchrgIdInP").val();
		if(param.psnInchrgId == ''){
			//담당자 을(를) 선택하세요.
			alert('<spring:message code="LAB.M03.LAB00031" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.psnInchrgNm = $("#psnInchrgNmInP").val();
		param.eml = $("#emlInP").val();
		param.hmpg = $("#hmpgInP").val();
		param.appyStrtDt = dateFormatToStringYYYYMMDD( $("#appyStrtDtInP").val() );
		if(param.appyStrtDt == ''){
			//개설일자 을(를) 입력해 주세요
			alert('<spring:message code="LAB.M01.LAB00015" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		param.appyEndDt = dateFormatToStringYYYYMMDD( $("#appyEndDtInP").val() );
		param.orgStatCd = $("#orgStatCdInP").val();
		if(param.orgStatCd == ''){
			//조직상태 을(를) 선택하세요.
			alert('<spring:message code="LAB.M09.LAB00150" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.termResn = $("#termResnInP").val();
		param.sttlPsnInchrg = $("#sttlPsnInchrgInP").val();
		param.sttlPsnInchrgNm = $("#sttlPsnInchrgNmInP").val();
		param.dpstNm = $("#dpstNmInP").val();
		param.sttlInchrgCp = $("#sttlInchrgCpInP").val();
		param.bnkCd = $("#bnkCdInP").val();
		param.sttlInchrgTel = $("#sttlInchrgTelInP").val();
		param.acntNo = $("#acntNoInP").val();
		param.sttlInchrgEml = $("#sttlInchrgEmlInP").val();
		
		
		param.onlnTpCd = "";
		param.eqtSchnAcntNo = "";
		param.eqtBnkCd = "";
		param.billSchnAcntNo = "";
		param.billBnkCd = "";
		
		return param;
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M08.LAB00102"/><!-- 유통정보보세정보 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box" id="areaInP" >
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M08.LAB00102"/><!-- 유통정보보세정보 --></h4>
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
				<th><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date p100">
							<input type="hidden" id="soIdInP" name="soIdInP" />
							<input type="text" id="orgIdInP" name="orgIdInP" class="p100" disabled="disabled" />
							<a href="#" id="btnOrgPopInP" name="btnOrgPopInP" class="search">search</a>
						</div> 
						<div class="inp_date w95">
							<input type="text" id="orgNmInP" name="orgNmInP" class="w95" disabled="disabled" />
						</div>
					</div>
				</td>
				<th></th>
				<td>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00011"/><!-- 사업자구분 --><span class="dot">*</span></th>
				<td>
					<select id="busiTpCdInP" name="busiTpCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${busiTpCd}" var="busiTpCd" varStatus="status">
							<option value="${busiTpCd.commonCd}">${busiTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M09.LAB00003"/><!-- 자사구분 --><span class="dot">*</span></th>
				<td>
					<select id="privTpCdInP" name="privTpCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${privTpCd}" var="privTpCd" varStatus="status">
							<option value="${privTpCd.commonCd}">${privTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00012"/><!-- 사업자등록번호 --></th>
				<td>
					<input type="text" id="borNoInP" name="borNoInP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M06.LAB00053"/><!-- 법인등록번호 --></th>
				<td>
					<input type="text" id="corpRegNoInP" name="corpRegNoInP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00011"/><!-- 업종 --></th>
				<td>	
					<input type="text" id="busiTpInP" name="busiTpInP" class="w200" checkbyte="450" />
				</td>
				<th><spring:message code="LAB.M08.LAB00012"/><!-- 업태 --></th>
				<td>
					<input type="text" id="busiCndtInP" name="busiCndtInP" class="w200" checkbyte="450" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00063"/><!-- 대표자명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="repNmInP" name="repNmInP" class="w200" checkbyte="150" />
				</td>
				<th><spring:message code="LAB.M03.LAB00064"/><!-- 대표자주민등록번호 --></th>
				<td>
					<input type="text" id="repRnoInP" name="repRnoInP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00293"/><!-- 실경영자명 --></th>
				<td>
					<input type="text" id="mngrNmInP" name="mngrNmInP" class="w200" checkbyte="100" />
				</td>
				<th><spring:message code="LAB.M07.LAB00294"/><!-- 실경영자주민등록번호 --></th>
				<td>
					<input type="text" id="mngrRnoInP" name="mngrRnoInP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00065"/><!-- 전화번호 --></th>
				<td>
					<input type="text" id="telNoInP" name="telNoInP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M13.LAB00015"/><!-- 팩스번호 --></th>
				<td>
					<input type="text" id="faxNoInP" name="faxNoInP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00190"/><!-- 주소 --></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="zipCdInP" name="zipCdInP" class="p100" disabled="disabled" />
							<a href="#" id="btnAddrInP" name="btnAddrInP" class="search">search</a>
						</div> 
						<div class="inp_date w233">
							<input type="text" id="addr1InP" name="addr1InP" class="w233" disabled="disabled" />
						</div>
						<div class="inp_date w233">
							<input type="text" id="addr2InP" name="addr2InP" class="w233" checkbyte="450" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00031"/><!-- 담당자명 --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="psnInchrgIdInP" name="psnInchrgIdInP" class="p100" disabled="disabled" />
							<a href="#" id="btnInchrgIdInP" name="btnInchrgIdInP" class="search">search</a>
						</div> 
						<div class="inp_date w95">
							<input type="text" id="psnInchrgNmInP" name="psnInchrgNmInP" class="w95" disabled="disabled" />
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M08.LAB00119"/><!-- 이메일 --></th>
				<td>
					<input id="emlInP" name="emlInP" type="text"  class="w200" checkbyte="450" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00062"/><!-- 홈페이지 --></th>
				<td colspan="3">
					<input id="hmpgInP" name="hmpgInP" type="text"  class="w590" checkbyte="450" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00015"/><!-- 개설일자 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="appyStrtDtInP" name="appyStrtDtInP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
				<th><spring:message code="LAB.M13.LAB00019"/><!-- 폐점일 --></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="appyEndDtInP" name="appyEndDtInP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00150"/><!-- 조직상태 --><span class="dot">*</span></th>
				<td>
					<select id="orgStatCdInP" name="orgStatCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${orgStatCd}" var="orgStatCd" varStatus="status">
							<option value="${orgStatCd.commonCd}">${orgStatCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M14.LAB00046"/><!-- 해지사유 --></th>
				<td>
					<input type="text" id="termResnInP" name="termResnInP" class="w200" checkbyte="1500" />
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M03.LAB00035"/><!-- 정산정보 --></h4>
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
				<th><spring:message code="LAB.M09.LAB00091"/><!-- 정산담당자 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="sttlPsnInchrgInP" name="sttlPsnInchrgInP" class="p100" disabled="disabled" />
							<a href="#" id="btnSttlPsnInchrgInP" name="btnSttlPsnInchrgInP" class="search">search</a>
						</div> 
						<div class="inp_date w95">
							<input type="text" id="sttlPsnInchrgNmInP" name="sttlPsnInchrgNmInP" class="w95" disabled="disabled" />
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M08.LAB00029"/><!-- 예금주명 --></th>
				<td>
					<input id="dpstNmInP" name="dpstNmInP" type="text" class="w200" checkbyte="100" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00036"/><!-- 담당휴대폰번호 --></th>
				<td>
					<input id="sttlInchrgCpInP" name="sttlInchrgCpInP" type="text"  class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M08.LAB00112"/><!-- 은행명 --></th>
				<td>
					<select id="bnkCdInP" name="bnkCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${bnkCd}" var="bnkCd" varStatus="status">
							<option value="${bnkCd.commonCd}">${bnkCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00035"/><!-- 담당전화번호 --></th>
				<td>
					<input id="sttlInchrgTelInP" name="sttlInchrgTelInP" type="text" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M01.LAB00044"/><!-- 계좌번호 --></th>
				<td>
					<input id="acntNoInP" name="acntNoInP" type="text"  class="w200" checkbyte="150" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00028"/><!-- 담당이메일 --></th>
				<td colspan="3">
					<input type="text" id="sttlInchrgEmlInP" name="sttlInchrgEmlInP" class="w200" checkbyte="450" />
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


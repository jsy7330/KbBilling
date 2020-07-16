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
		
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#appyStrtDtUpP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		
		$('#busiTpCdUpP').selectmenu({});
		$('#privTpCdUpP').selectmenu({});
		$('#orgStatCdUpP').selectmenu({});
		$('#bnkCdUpP').selectmenu({});
		
		
		//저장버튼
		$("#btnInsert").click(function() {
			insertDataUpP();            
		});
		
		setDataUpP();
		
		$("#btnAddrUpP").click(function() {
				
			var url="/system/common/common/postMng/postPopup.ajax";
			url = url + "?zipCd=zipCdUpP";           //우편번호
			url = url + "&baseAddr=addr1UpP"; // 기본주소
			url = url + "&addrDtl=addr2UpP"; // 상세주소 
			url = url + "&mode=o";   //모드 o지정 
			 
			var width = 810;
			var height = 500;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			 
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no");
			
		});
		
		//담당자 검색
		$("#btnInchrgIdUpP").click(function() {
			var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
			url = url + "?returnId1=psnInchrgNmUpP";           //사용자이름
			url = url + "&returnId2=psnInchrgIdUpP";           //사용자아이디
			url = url + "&popType=o";   //모드 o지정 
			 
			var width = 940;
			var height = 470;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			 
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no"); 
		});
		
		//정산담당자검색
		$("#btnSttlPsnInchrgUpP").click(function() {
			var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
			url = url + "?returnId1=sttlPsnInchrgNmUpP";           //사용자이름
			url = url + "&returnId2=sttlPsnInchrgUpP";           //사용자아이디
			url = url + "&popType=o";   //모드 o지정 
			 
			var width = 940;
			var height = 470;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			 
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no"); 
		});
		
	});
	
	function setDataUpP(){
		
		var rowId  = $("#distributionInfoTable").jqGrid("getGridParam", "selrow" );
		var data = $("#distributionInfoTable").getRowData(rowId);
		
		var param = new Object();
		param.orgId = data.orgId;
		
		$.ajax({
			url : 'selectDistributionInfo.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data.distributionInfoVO);
				
				var distributionInfoVO = data.distributionInfoVO

				if(distributionInfoVO == null){
				}else{
					
					$("#appyStrtDtUpP").val(stringToDateformatYYYYMMDD(distributionInfoVO.appyStrtDt));
					$("#appyEndDtUpP").val(stringToDateformatYYYYMMDD(distributionInfoVO.appyEndDt));
					$("#orgIdUpP").val(distributionInfoVO.orgId);
					$("#orgNmUpP").val(distributionInfoVO.orgNm);
					$("#soIdUpP").val(distributionInfoVO.soId);
					
					
					$("#busiTpCdUpP").val(distributionInfoVO.busiTpCd);
					$("#busiTpCdUpP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					
					$("#privTpCdUpP").val(distributionInfoVO.privTpCd);
					$("#privTpCdUpP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					
					$("#borNoUpP").val(distributionInfoVO.borNo);
					$("#corpRegNoUpP").val(distributionInfoVO.corpRegNo);
					$("#busiTpUpP").val(distributionInfoVO.busiTp);
					$("#busiCndtUpP").val(distributionInfoVO.busiCndt);
					$("#repNmUpP").val(distributionInfoVO.repNm);
					$("#repRnoUpP").val(distributionInfoVO.repRno);
					$("#mngrNmUpP").val(distributionInfoVO.mngrNm);
					$("#mngrRnoUpP").val(distributionInfoVO.mngrRno);
					$("#telNoUpP").val(distributionInfoVO.telNo);
					$("#faxNoUpP").val(distributionInfoVO.faxNo);
					$("#zipCdUpP").val(distributionInfoVO.zipCd);
					$("#addr1UpP").val(distributionInfoVO.addr1);
					$("#addr2UpP").val(distributionInfoVO.addr2);
					$("#psnInchrgIdUpP").val(distributionInfoVO.psnInchrgId);
					$("#psnInchrgNmUpP").val(distributionInfoVO.psnInchrgNm);
					$("#emlUpP").val(distributionInfoVO.eml);
					$("#hmpgUpP").val(distributionInfoVO.hmpg);
					
					$("#orgStatCdUpP").val(distributionInfoVO.orgStatCd);
					$("#orgStatCdUpP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					
					$("#termResnUpP").val(distributionInfoVO.termResn);
					$("#sttlPsnInchrgUpP").val(distributionInfoVO.sttlPsnInchrg);
					$("#sttlPsnInchrgNmUpP").val(distributionInfoVO.sttlPsnInchrgNm);
					$("#dpstNmUpP").val(distributionInfoVO.dpstNm);
					$("#sttlInchrgCpUpP").val(distributionInfoVO.sttlInchrgCp);
					
					$("#bnkCdUpP").val(distributionInfoVO.bnkCd);
					$("#bnkCdUpP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					
					$("#sttlInchrgTelUpP").val(distributionInfoVO.sttlInchrgTel);
					$("#acntNoUpP").val(distributionInfoVO.acntNo);
					$("#sttlInchrgEmlUpP").val(distributionInfoVO.sttlInchrgEml);
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
	function insertDataUpP(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		
		if(!check){
			return;
		}
		
		var param = checkValidationUpP();
		var url = "";
		url = "updateDistributionInfo.json";
		
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
	function checkValidationUpP(){
		var param = new Object();
		
		param.soId = $("#soIdUpP").val();
		param.orgId = $("#orgIdUpP").val();
		if(param.orgId == ''){
			//조직ID 을(를) 선택하세요.
			alert('<spring:message code="LAB.M09.LAB00139" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.orgNm = $("#orgNmUpP").val();
		param.busiTpCd = $("#busiTpCdUpP").val();
		if(param.busiTpCd == ''){
			//사업자구분 을(를) 선택하세요.
			alert('<spring:message code="LAB.M07.LAB00011" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.privTpCd = $("#privTpCdUpP").val();
		if(param.privTpCd == ''){
			//자사구분 을(를) 선택하세요.
			alert('<spring:message code="LAB.M09.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.borNo = $("#borNoUpP").val();
		param.corpRegNo = $("#corpRegNoUpP").val();
		param.busiTp = $("#busiTpUpP").val();
		param.busiCndt = $("#busiCndtUpP").val();
		param.repNm = $("#repNmUpP").val();
		if(param.repNm == ''){
			//대표자명 을(를) 입력해 주세요
			alert('<spring:message code="LAB.M03.LAB00063" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.repRno = $("#repRnoUpP").val();
		param.mngrNm = $("#mngrNmUpP").val();
		param.mngrRno = $("#mngrRnoUpP").val();
		param.telNo = $("#telNoUpP").val();
		param.faxNo = $("#faxNoUpP").val();
		param.zipCd = $("#zipCdUpP").val();
		param.addr1 = $("#addr1UpP").val();
		param.addr2 = $("#addr2UpP").val();
		param.psnInchrgId = $("#psnInchrgIdUpP").val();
		if(param.psnInchrgId == ''){
			//담당자 을(를) 선택하세요.
			alert('<spring:message code="LAB.M03.LAB00031" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.psnInchrgNm = $("#psnInchrgNmUpP").val();
		param.eml = $("#emlUpP").val();
		param.hmpg = $("#hmpgUpP").val();
		param.appyStrtDt = dateFormatToStringYYYYMMDD( $("#appyStrtDtUpP").val() );
		if(param.appyStrtDt == ''){
			//개설일자 을(를) 입력해 주세요
			alert('<spring:message code="LAB.M01.LAB00015" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		param.appyEndDt = dateFormatToStringYYYYMMDD( $("#appyEndDtUpP").val() );
		param.orgStatCd = $("#orgStatCdUpP").val();
		if(param.orgStatCd == ''){
			//조직상태 을(를) 선택하세요.
			alert('<spring:message code="LAB.M09.LAB00150" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		param.termResn = $("#termResnUpP").val();
		param.sttlPsnInchrg = $("#sttlPsnInchrgUpP").val();
		param.sttlPsnInchrgNm = $("#sttlPsnInchrgNmUpP").val();
		param.dpstNm = $("#dpstNmUpP").val();
		param.sttlInchrgCp = $("#sttlInchrgCpUpP").val();
		param.bnkCd = $("#bnkCdUpP").val();
		param.sttlInchrgTel = $("#sttlInchrgTelUpP").val();
		param.acntNo = $("#acntNoUpP").val();
		param.sttlInchrgEml = $("#sttlInchrgEmlUpP").val();
		
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
<div class="layer_box" id="areaUpP" >
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
				<th><spring:message code="LAB.M09.LAB00139"/><!-- 조직ID --><span class="dot">*</span></th>
				<td>
					<input type="hidden" id="soIdUpP" name="soIdUpP" />
					<input type="text" id="orgIdUpP" name="orgIdUpP" class="w200" disabled="disabled" />
				</td>
				<th><spring:message code="LAB.M09.LAB00147"/><!-- 조직명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="orgNmUpP" name="orgNmUpP" class="w200" checkbyte="450" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00011"/><!-- 사업자구분 --><span class="dot">*</span></th>
				<td>
					<select id="busiTpCdUpP" name="busiTpCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${busiTpCd}" var="busiTpCd" varStatus="status">
							<option value="${busiTpCd.commonCd}">${busiTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M09.LAB00003"/><!-- 자사구분 --><span class="dot">*</span></th>
				<td>
					<select id="privTpCdUpP" name="privTpCdUpP" class="w150">
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
					<input type="text" id="borNoUpP" name="borNoUpP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M06.LAB00053"/><!-- 법인등록번호 --></th>
				<td>
					<input type="text" id="corpRegNoUpP" name="corpRegNoUpP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00011"/><!-- 업종 --></th>
				<td>	
					<input type="text" id="busiTpUpP" name="busiTpUpP" class="w200" checkbyte="450" />
				</td>
				<th><spring:message code="LAB.M08.LAB00012"/><!-- 업태 --></th>
				<td>
					<input type="text" id="busiCndtUpP" name="busiCndtUpP" class="w200" checkbyte="450" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00063"/><!-- 대표자명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="repNmUpP" name="repNmUpP" class="w200" checkbyte="150" />
				</td>
				<th><spring:message code="LAB.M03.LAB00064"/><!-- 대표자주민등록번호 --></th>
				<td>
					<input type="text" id="repRnoUpP" name="repRnoUpP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00293"/><!-- 실경영자명 --></th>
				<td>
					<input type="text" id="mngrNmUpP" name="mngrNmUpP" class="w200" checkbyte="100" />
				</td>
				<th><spring:message code="LAB.M07.LAB00294"/><!-- 실경영자주민등록번호 --></th>
				<td>
					<input type="text" id="mngrRnoUpP" name="mngrRnoUpP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00065"/><!-- 전화번호 --></th>
				<td>
					<input type="text" id="telNoUpP" name="telNoUpP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M13.LAB00015"/><!-- 팩스번호 --></th>
				<td>
					<input type="text" id="faxNoUpP" name="faxNoUpP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00190"/><!-- 주소 --></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="zipCdUpP" name="zipCdUpP" class="p100" disabled="disabled" />
							<a href="#" id="btnAddrUpP" name="btnAddrUpP" class="search">search</a>
						</div> 
						<div class="inp_date w233">
							<input type="text" id="addr1UpP" name="addr1UpP" class="w233" disabled="disabled" />
						</div>
						<div class="inp_date w233">
							<input type="text" id="addr2UpP" name="addr2UpP" class="w233" checkbyte="450" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00031"/><!-- 담당자명 --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="psnInchrgIdUpP" name="psnInchrgIdUpP" class="p100" disabled="disabled" />
							<a href="#" id="btnInchrgIdUpP" name="btnInchrgIdUpP" class="search">search</a>
						</div> 
						<div class="inp_date w95">
							<input type="text" id="psnInchrgNmUpP" name="psnInchrgNmUpP" class="w95" disabled="disabled" />
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M08.LAB00119"/><!-- 이메일 --></th>
				<td>
					<input id="emlUpP" name="emlUpP" type="text"  class="w200" checkbyte="450" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00062"/><!-- 홈페이지 --></th>
				<td colspan="3">
					<input id="hmpgUpP" name="hmpgUpP" type="text"  class="w590" checkbyte="450" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00015"/><!-- 개설일자 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="appyStrtDtUpP" name="appyStrtDtUpP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
				<th><spring:message code="LAB.M13.LAB00019"/><!-- 폐점일 --></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="appyEndDtUpP" name="appyEndDtUpP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00150"/><!-- 조직상태 --><span class="dot">*</span></th>
				<td>
					<select id="orgStatCdUpP" name="orgStatCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${orgStatCd}" var="orgStatCd" varStatus="status">
							<option value="${orgStatCd.commonCd}">${orgStatCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M14.LAB00046"/><!-- 해지사유 --></th>
				<td>
					<input type="text" id="termResnUpP" name="termResnUpP" class="w200" checkbyte="1500" />
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
							<input type="text" id="sttlPsnInchrgUpP" name="sttlPsnInchrgUpP" class="p100" disabled="disabled" />
							<a href="#" id="btnSttlPsnInchrgUpP" name="btnSttlPsnInchrgUpP" class="search">search</a>
						</div> 
						<div class="inp_date w95">
							<input type="text" id="sttlPsnInchrgNmUpP" name="sttlPsnInchrgNmUpP" class="w95" disabled="disabled" />
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M08.LAB00029"/><!-- 예금주명 --></th>
				<td>
					<input id="dpstNmUpP" name="dpstNmUpP" type="text" class="w200" checkbyte="100" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00036"/><!-- 담당휴대폰번호 --></th>
				<td>
					<input id="sttlInchrgCpUpP" name="sttlInchrgCpUpP" type="text" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M08.LAB00112"/><!-- 은행명 --></th>
				<td>
					<select id="bnkCdUpP" name="bnkCdUpP" class="w150">
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
					<input id="sttlInchrgTelUpP" name="sttlInchrgTelUpP" type="text" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M01.LAB00044"/><!-- 계좌번호 --></th>
				<td>
					<input id="acntNoUpP" name="acntNoUpP" type="text"  class="w200" checkbyte="150" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00028"/><!-- 담당이메일 --></th>
				<td colspan="3">
					<input type="text" id="sttlInchrgEmlUpP" name="sttlInchrgEmlUpP" class="w200" checkbyte="450" />
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


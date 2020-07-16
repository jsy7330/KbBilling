<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/dnUtil.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		byteCheckKeyEvent();	//바이트체크
		settingDatepicker();	//팝업달력 셋팅
		
		$('#poQtyInP').number( true );
		$('#poUtPrcInP').number( true );
		
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#dlgdAgreeDtInP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		
		$('#soIdInP').selectmenu({});
		$('#itemTpCdInP').selectmenu({});
		$('#colorCdInP').selectmenu({});
		
		$("#btnInsert").click(function() {
			insertDataInP();
		});
	
		//제조사검색 팝업
		$("#btnSearchMncoInP").click(function() {
			
			var url="/system/common/common/manufacturerMng/manufacturerSearchPopup.ajax";
			url = url + "?soId=" + $("#soIdInP").val() + "&popType=o&returnId1=mncoIdInP&returnId2=mncoNmInP";
			
			var width = 510;
			var height = 430;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		//제품검색 팝업
		$("#btnSearchItemInP").click(function() {
			
			var url="/system/common/common/productMng/productSearchPopup.ajax";
			url = url + "?soId=" + $("#soIdInP").val() + "&popType=o&returnId1=itemIdInP&returnId2=itemNmInP&popType2=01";
			
			var width = 815;
			var height = 510;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		//조직검색 팝업
		$("#btnOrgPopInP").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup2.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=lgstCentIdInP&returnId2=lgstCentNmInP&popType2=LGSTI";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		$( "#poQtyInP" ).keyup(function(event) {
			var poQtyInP = Number($("#poQtyInP").val());
			var poUtPrcInP = Number($("#poUtPrcInP").val());
			
			$("#poAmtInP").val(poQtyInP * poUtPrcInP);
		});
		
		$( "#poUtPrcInP" ).keyup(function(event) {
			var poQtyInP = Number($("#poQtyInP").val());
			var poUtPrcInP = Number($("#poUtPrcInP").val());
			
			$("#poAmtInP").val(poQtyInP * poUtPrcInP);
		});
		
		//주소검색팝업
		$("#btnAddrInP").click(function() {
			
			var url="/system/common/common/postMng/postPopup.ajax";
			url = url + "?zipCd=dlvZipCdInP";           //우편번호
			url = url + "&baseAddr=dlvBssAddrInP"; // 기본주소
			url = url + "&addrDtl=dlvDtlAddrInP"; // 상세주소 
			url = url + "&mode=o";   //모드 o지정 
			 
			var width = 810;
			var height = 500;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			 
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no");
			
		});
		
	});
	
	
	//등록
	function insertDataInP(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidationInP();
		
		if(param != false){
			
			$.ajax({
				url : 'insertOrderPlacement.json',
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
		
		param.soId = $("#soIdInP").val();
		if(param.soId == ''){
			//사업을 선택해 주세요
			alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.poOrgId = "${session_user.orgId}";		//발주조직아이디
		param.lgstCentId = $("#lgstCentIdInP").val();	//물류센터ID
		if(param.lgstCentId == ''){
			//물류센터를 선택해 주세요
			alert('<spring:message code="LAB.M05.LAB00042" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.mncoId = $("#mncoIdInP").val();			//제조사ID
		if(param.mncoId == ''){
			//제조사를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00098" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.poPrgrStatCd = "10";						//발주진행상태코드 --발주등록 코드 값
		param.itemId = $("#itemIdInP").val();			//제품ID
		if(param.itemId == ''){
			//제품를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00105" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.itemTpCd = $("#itemTpCdInP").val();		//제품유형코드
		if(param.itemTpCd == ''){
			//제품유형을 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00121" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.colorCd = $("#colorCdInP").val();			//색상코드
		param.dlgdAgreeDt = dateFormatToStringYYYYMMDD($("#dlgdAgreeDtInP").val());			//납품합의일자
		if(param.dlgdAgreeDt == ''){
			//납품확인일자를 입력해 주세요.
			alert('<spring:message code="LAB.M02.LAB00031" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.poQty = $("#poQtyInP").val();				//발주수량
		if(param.poQty == ''){
			//발주수량을 입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00033" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.poUtPrc = $("#poUtPrcInP").val();			//발주단가
		if(param.poUtPrc == ''){
			//발주단가를 입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00025" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.poAmt = $("#poAmtInP").val();				//발주금액
		if(param.poAmt == ''){
			//발주금액을 입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00023" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.note = $("#noteInP").val();		//비고
		param.dlvZipCd = $("#dlvZipCdInP").val();		//배송지우편번호
		param.dlvBssAddr = $("#dlvBssAddrInP").val();	//배송지기본주소
		param.dlvDtlAddr = $("#dlvDtlAddrInP").val();	//배송지상세주소
		
		param.addTx = Number(param.poAmt) * (Number(getTaxRate()) / 100) ;	//부가가치세
		param.totPrchsAmt = Number(param.poAmt) + param.addTx;	//발주총금액
		param.poRegDt = $.datepicker.formatDate('yymmdd', new Date());	//발주일자  - 오늘 날짜
		param.poConfDt = "";	//발주확정일자
		param.actncInchrg = $("#actncInchrgInP").val();	//인수담당자
		param.actncInchrgTel = $("#actncInchrgTelInP").val();	//인수담당자전화번호
		
		return param;
	}
	
	function setItemTpCd(itemTpCd){
		
		$("#itemTpCdInP").val(itemTpCd);
		$("#itemTpCdInP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
	}
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M06.LAB00036"/><!-- 발주정보등록 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M06.LAB00036"/><!-- 발주정보등록 --></h4>
		</div>
		<div class="fr mt10">
			<div class="ask"><spring:message code="MSG.M15.MSG00005"/><!-- ※ 발주단가 : 매입단가 --></div>
		</div>
	</div>

	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:30%;">
			<col style="width:20%;">
			<col style="width:30%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><span class="dot">*</span><!-- 사업 --></th>
				<td colspan="3">
					
					<select class="w200" id="soIdInP" name="soIdInP">
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>
				                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date p100">
						<input type="text" id="mncoNmInP" name="mncoNmInP" class="p100" disabled="disabled" />
						<input type="hidden" id="mncoIdInP" name="mncoIdInP" />
						<a href="#" id="btnSearchMncoInP" name="btnSearchMncoInP" class="search">search</a>
					</div> 
				</td>
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --><span class="dot">*</span></th>
				<td>
					<select id="itemTpCdInP" name="itemTpCdInP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
							<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                          
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date p100">
						<input type="text" id="itemNmInP" name="itemNmInP" class="p100" disabled="disabled" />
						<input type="hidden" id="itemIdInP" name="itemIdInP" />
						<a href="#" id="btnSearchItemInP" name="btnSearchItemInP" class="search">search</a>
					</div> 
				</td>
				<th><spring:message code="LAB.M07.LAB00162"/><!-- 색상 --></th>
				<td>
					<select id="colorCdInP" name="colorCdInP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${colorCd}" var="colorCd" varStatus="status">
							<option value="${colorCd.commonCd}">${colorCd.commonCdNm}</option>
						</c:forEach>
					</select>  
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00033"/><!-- 발주수량 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="poQtyInP" name="poQtyInP" class="w200" checkbyte="10" />
				</td>
				<th><spring:message code="LAB.M06.LAB00025"/><!-- 발주단가 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="poUtPrcInP" name="poUtPrcInP" class="w200" checkbyte="12" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00023"/><!-- 발주금액 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="poAmtInP" name="poAmtInP" class="w200" disabled="disabled" >
				</td>
				<th><spring:message code="LAB.M02.LAB00031"/><!-- 납품합의 일자 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="dlgdAgreeDtInP" name="dlgdAgreeDtInP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --></th>
				<td colspan="3">
					<input type="text" id="noteInP" name="noteInP" class="w600">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M05.LAB00042"/><!-- 물류센터 --><span class="dot">*</span></th>
				<td colspan="3">
					<div class="inp_date w210">
						<input type="text" id="lgstCentNmInP" name="lgstCentNmInP" class="w180" disabled="disabled" />
						<input type="hidden" id="lgstCentIdInP" name="lgstCentIdInP" />
						<a href="#" id="btnOrgPopInP" name="btnOrgPopInP" class="search">search</a>
					</div> 
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00134"/><!-- 인수 담당자 --></th>
				<td>
					<input type="text" id="actncInchrgInP" name="actncInchrgInP" class="w200">
				</td>
				<th><spring:message code="LAB.M03.LAB00033"/><!-- 담당자 연락처 --></th>
				<td>
					<input type="text" id="actncInchrgTelInP" name="actncInchrgTelInP" class="w200">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00049"/><!-- 배송지 주소 --></th>
				<td colspan="3">
				<!-- 
					<input type="text" id="dlvZipCdInP" name="dlvZipCdInP" class="w50">
					<a href="#" id="btnAddrInP" name="btnAddrInP" class="search">search</a>
					<input type="text" id="dlvBssAddrInP" name="dlvBssAddrInP" class="w270">
					<input type="text" id="dlvDtlAddrInP" name="dlvDtlAddrInP" class="w270">
					 -->
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="dlvZipCdInP" name="dlvZipCdInP" class="p100" disabled="disabled" />
							<a href="#" id="btnAddrInP" name="btnAddrInP" class="search">search</a>
						</div> 
						<div class="inp_date w200">
							<input type="text" id="dlvBssAddrInP" name="dlvBssAddrInP" class="w200" disabled="disabled" />
						</div>
						<div class="inp_date w200">
							<input type="text" id="dlvDtlAddrInP" name="dlvDtlAddrInP" class="w200" checkbyte="450" />
						</div>
					</div>
				</td>
			</tr>
		</thead>
	</table> 
</div>
<!--// center -->

<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
	
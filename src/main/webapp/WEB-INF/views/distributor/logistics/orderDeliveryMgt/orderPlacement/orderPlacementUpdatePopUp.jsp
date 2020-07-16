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
		
		$('#poQtyUpP').number( true );
		$('#poUtPrcUpP').number( true );
		
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#dlgdAgreeDtUpP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		
		$('#soIdUpP').selectmenu({});
		$('#itemTpCdUpP').selectmenu({});
		$('#colorCdUpP').selectmenu({});
		
		setDataUpP();
		
		$("#btnInsert").click(function() {
			insertDataUpP();
		});
	
		//제조사검색 팝업
		$("#btnSearchMncoUpP").click(function() {
			
			var url="/system/common/common/manufacturerMng/manufacturerSearchPopup.ajax";
			url = url + "?soId=" + $("#soIdUpP").val() + "&popType=o&returnId1=mncoIdUpP&returnId2=mncoNmUpP";
			
			var width = 510;
			var height = 430;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		//제품검색 팝업
		$("#btnSearchItemUpP").click(function() {
			
			var url="/system/common/common/productMng/productSearchPopup.ajax";
			url = url + "?soId=" + $("#soIdUpP").val() + "&popType=o&returnId1=itemIdUpP&returnId2=itemNmUpP&popType2=02";
			
			var width = 815;
			var height = 510;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		//조직검색 팝업
		$("#btnOrgPopUpP").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup2.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=lgstCentIdUpP&returnId2=lgstCentNmUpP&popType2=LGSTU";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		$( "#poQtyUpP" ).keyup(function(event) {
			var poQtyUpP = Number($("#poQtyUpP").val());
			var poUtPrcUpP = Number($("#poUtPrcUpP").val());
			
			$("#poAmtUpP").val(poQtyUpP * poUtPrcUpP);
		});
		
		$( "#poUtPrcUpP" ).keyup(function(event) {
			var poQtyUpP = Number($("#poQtyUpP").val());
			var poUtPrcUpP = Number($("#poUtPrcUpP").val());
			
			$("#poAmtUpP").val(poQtyUpP * poUtPrcUpP);
		});
		
		//주소검색팝업
		$("#btnAddrUpP").click(function() {
			
			var url="/system/common/common/postMng/postPopup.ajax";
			url = url + "?zipCd=dlvZipCdUpP";           //우편번호
			url = url + "&baseAddr=dlvBssAddrUpP"; // 기본주소
			url = url + "&addrDtl=dlvDtlAddrUpP"; // 상세주소 
			url = url + "&mode=o";   //모드 o지정 
			 
			var width = 810;
			var height = 500;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			 
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no");
			
		});
		
	});
	
	function setDataUpP(){
		
		var rowId  = $("#orderPlacementTable").jqGrid("getGridParam", "selrow" );
		var data = $("#orderPlacementTable").getRowData(rowId);
		
		
		$("#poNoUpP").val(data.poNo);
		$("#soIdUpP").val(data.soId);
		$("#soIdUpP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#mncoNmUpP").val(data.mncoNm);
		$("#mncoIdUpP").val(data.mncoId);
		
		$("#itemTpCdUpP").val(data.itemTpCd);
		$("#itemTpCdUpP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#itemNmUpP").val(data.itemNm);
		$("#itemIdUpP").val(data.itemId);
		$("#colorCdUpP").val(data.colorCd);
		$("#colorCdUpP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#poQtyUpP").val(data.poQty);
		$("#poUtPrcUpP").val(data.poUtPrc);
		$("#poAmtUpP").val(data.poAmt);
		//$("#dlgdAgreeDtUpP").val(stringToDateformatYYYYMMDD(data.dlgdAgreeDt));
		$("#dlgdAgreeDtUpP").val(data.dlgdAgreeDt);
		$("#noteUpP").val(data.note);
		$("#lgstCentNmUpP").val(data.lgstCentNm);
		$("#lgstCentIdUpP").val(data.lgstCentId);
		$("#actncInchrgUpP").val(data.actncInchrg);
		$("#actncInchrgTelUpP").val(data.actncInchrgTel);
		
		$("#dlvZipCdUpP").val(data.dlvZipCd);
		$("#dlvBssAddrUpP").val(data.dlvBssAddr);
		$("#dlvDtlAddrUpP").val(data.dlvDtlAddr);
	
	}
	
	//등록
	function insertDataUpP(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidationUpP();
		
		if(param != false){
			
			$.ajax({
				url : 'updateOrderPlacement.json',
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
	function checkValidationUpP(){
		var param = new Object();
		
		param.poNo = $("#poNoUpP").val();
		param.soId = $("#soIdUpP").val();
		if(param.soId == ''){
			//사업을 선택해 주세요
			alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.poOrgId = "${session_user.orgId}";		//발주조직아이디
		param.lgstCentId = $("#lgstCentIdUpP").val();	//물류센터ID
		if(param.lgstCentId == ''){
			//물류센터를 선택해 주세요
			alert('<spring:message code="LAB.M05.LAB00042" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		param.mncoId = $("#mncoIdUpP").val();			//제조사ID
		if(param.mncoId == ''){
			//제조사를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00098" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.poPrgrStatCd = "10";						//발주진행상태코드 --발주등록 코드 값
		param.itemId = $("#itemIdUpP").val();			//제품ID
		if(param.mncoId == ''){
			//제품를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00105" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.itemTpCd = $("#itemTpCdUpP").val();		//제품유형코드
		if(param.itemTpCd == ''){
			//제품유형을 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00121" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.colorCd = $("#colorCdUpP").val();			//색상코드
		param.dlgdAgreeDt = dateFormatToStringYYYYMMDD($("#dlgdAgreeDtUpP").val());			//납품합의일자
		if(param.dlgdAgreeDt == ''){
			//납품확인일자를 입력해 주세요.
			alert('<spring:message code="LAB.M02.LAB00031" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.poQty = $("#poQtyUpP").val();				//발주수량
		if(param.poQty == ''){
			//발주수량을 입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00033" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.poUtPrc = $("#poUtPrcUpP").val();			//발주단가
		if(param.poUtPrc == ''){
			//발주단가를 입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00025" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.poAmt = $("#poAmtUpP").val();				//발주금액
		if(param.poAmt == ''){
			//발주금액을 입력해 주세요.
			alert('<spring:message code="LAB.M06.LAB00023" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.note = $("#noteUpP").val();		//비고
		param.dlvZipCd = $("#dlvZipCdUpP").val();		//배송지우편번호
		param.dlvBssAddr = $("#dlvBssAddrUpP").val();	//배송지기본주소
		param.dlvDtlAddr = $("#dlvDtlAddrUpP").val();	//배송지상세주소
		
		param.addTx = Number(param.poAmt) * (Number(getTaxRate()) / 100) ;	//부가가치세
		param.totPrchsAmt = Number(param.poAmt) + param.addTx;	//발주총금액
		param.poRegDt = $.datepicker.formatDate('yymmdd', new Date());	//발주일자  - 오늘 날짜
		param.poConfDt = "";	//발주확정일자
		param.actncInchrg = $("#actncInchrgUpP").val();	//인수담당자
		param.actncInchrgTel = $("#actncInchrgTelUpP").val();	//인수담당자전화번호
		
		return param;
	}
	
	function setItemTpCd(itemTpCd){
		
		$("#itemTpCdUpP").val(itemTpCd);
		$("#itemTpCdUpP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
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
					<input type="hidden" id="poNoUpP" name="poNoUpP" />
					<select class="w200" id="soIdUpP" name="soIdUpP">
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
						<input type="text" id="mncoNmUpP" name="mncoNmUpP" class="p100" disabled="disabled" />
						<input type="hidden" id="mncoIdUpP" name="mncoIdUpP" />
						<a href="#" id="btnSearchMncoUpP" name="btnSearchMncoUpP" class="search">search</a>
					</div> 
				</td>
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --><span class="dot">*</span></th>
				<td>
					<select id="itemTpCdUpP" name="itemTpCdUpP" class="w200">
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
						<input type="text" id="itemNmUpP" name="itemNmUpP" class="p100" disabled="disabled" />
						<input type="hidden" id="itemIdUpP" name="itemIdUpP" />
						<a href="#" id="btnSearchItemUpP" name="btnSearchItemUpP" class="search">search</a>
					</div> 
				</td>
				<th><spring:message code="LAB.M07.LAB00162"/><!-- 색상 --></th>
				<td>
					<select id="colorCdUpP" name="colorCdUpP" class="w200">
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
					<input type="text" id="poQtyUpP" name="poQtyUpP" class="w200" checkbyte="10" />
				</td>
				<th><spring:message code="LAB.M06.LAB00025"/><!-- 발주단가 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="poUtPrcUpP" name="poUtPrcUpP" class="w200" checkbyte="12" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00023"/><!-- 발주금액 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="poAmtUpP" name="poAmtUpP" class="w200" disabled="disabled" >
				</td>
				<th><spring:message code="LAB.M02.LAB00031"/><!-- 납품합의 일자 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="dlgdAgreeDtUpP" name="dlgdAgreeDtUpP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00093"/><!-- 비고 --></th>
				<td colspan="3">
					<input type="text" id="noteUpP" name="noteUpP" class="w600">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M05.LAB00042"/><!-- 물류센터 --><span class="dot">*</span></th>
				<td colspan="3">
					<div class="inp_date w210">
						<input type="text" id="lgstCentNmUpP" name="lgstCentNmUpP" class="w180" disabled="disabled" />
						<input type="hidden" id="lgstCentIdUpP" name="lgstCentIdUpP" class="w170" />
						<a href="#" id="btnOrgPopUpP" name="btnOrgPopUpP" class="search">search</a>
					</div> 
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00134"/><!-- 인수 담당자 --></th>
				<td>
					<input type="text" id="actncInchrgUpP" name="actncInchrgUpP" class="w200">
				</td>
				<th><spring:message code="LAB.M03.LAB00033"/><!-- 담당자 연락처 --></th>
				<td>
					<input type="text" id="actncInchrgTelUpP" name="actncInchrgTelUpP" class="w200">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00049"/><!-- 배송지 주소 --></th>
				<td colspan="3">
				<!-- 
					<input type="text" id="dlvZipCdUpP" name="dlvZipCdUpP" class="w50">
					<input type="text" id="dlvBssAddrUpP" name="dlvBssAddrUpP" class="w270">
					<input type="text" id="dlvDtlAddrUpP" name="dlvDtlAddrUpP" class="w270">
					 -->
					 <div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="dlvZipCdUpP" name="dlvZipCdUpP" class="p100" disabled="disabled" />
							<a href="#" id="btnAddrUpP" name="btnAddrUpP" class="search">search</a>
						</div> 
						<div class="inp_date w200">
							<input type="text" id="dlvBssAddrUpP" name="dlvBssAddrUpP" class="w200" disabled="disabled" />
						</div>
						<div class="inp_date w200">
							<input type="text" id="dlvDtlAddrUpP" name="dlvDtlAddrUpP" class="w200" checkbyte="450" />
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
	
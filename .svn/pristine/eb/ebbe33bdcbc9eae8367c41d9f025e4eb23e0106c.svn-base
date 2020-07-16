<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/dnUtil.js"></script>

<script type="text/javascript">

	var itemTpCd = "";
	var tax = Number(getTaxRate()) / 100;
	

	$(document).ready(function() {
		
		byteCheckKeyEvent();	//바이트체크
		settingDatepicker();	//팝업달력 셋팅
		
		$('#poQtyInP').number( true );
		$('#poUtPrcInP').number( true );
		
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#inoutDtInP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		
		$('#eqtUseCdInP').selectmenu({});
		$('#eqtRsrcClCdInP').selectmenu({});

		setDataInP();
		
		$("#btnInsert").click(function() {
			insertDataInP();
		});
		
		$( "#inoutQtyInP" ).keyup(function(event) {
			var inoutQtyInP = Number($("#inoutQtyInP").val());
			var inoutUtPrcInP = Number($("#inoutUtPrcInP").val());
			
			$("#inoutAddTxInP").val(inoutQtyInP * inoutUtPrcInP * tax);
			var inoutAddTxInP = Number($("#inoutAddTxInP").val());
			
			$("#inoutAmtInP").val(inoutQtyInP * inoutUtPrcInP);
			$("#inoutTotAmtInP").val(inoutQtyInP * inoutUtPrcInP + inoutAddTxInP);
		});
		
		
	});
	
	function setDataInP(){
			
		var rowId  = $("#deliveryInspectionTable").jqGrid("getGridParam", "selrow" );
		var data = $("#deliveryInspectionTable").getRowData(rowId);
		
		itemTpCd = data.itemTpCd;
		if(itemTpCd == "C"){
			$("#titleEqtUseCdInP").append('<span class="dot">*</span>');
			$("#titleEqtRsrcClCdInP").append('<span class="dot">*</span>');
		}else{
			$("#eqtUseCdInP").selectmenu("disable");
			$("#eqtRsrcClCdInP").selectmenu("disable");
		}
		
		
		$("#inoutIdInP").val("-");
		$("#inoutPrgrStatCdInP").val("-");
		$("#lgstCentNmInP").val(data.lgstCentNm);
		$("#inoutResnCdInP").val("-");
		$("#poNoInP").val(data.poNo);
		$("#itemNmInP").val(data.itemNm);
		
		var actncQty = Number(data.actncQty);	//납품수량
		var data2 = $("#deliveryInspectionTable2").getRowData();
		for(var i=0; i < data2.length; i++){
			actncQty = actncQty - Number(data2[i].inoutQty);
		}

		var actncUtPrc = Number(data.actncUtPrc);
		var actncAddTx = Number(data.actncAddTx);
		
		$("#inoutQtyInP").val(actncQty);
		$("#inoutUtPrcInP").val(data.actncUtPrc);
		$("#inoutAmtInP").val(actncQty * actncUtPrc);
		$("#inoutAddTxInP").val(data.actncAddTx);
		$("#inoutTotAmtInP").val(actncQty * actncUtPrc + actncAddTx);
		
		$("#soIdInP").val(data.soId);
		$("#lgstCentIdInP").val(data.lgstCentId);
		$("#itemIdInP").val(data.itemId);
		$("#inoutResnCdInP").val(data.inoutResnCd);
		$("#itemTpCdInP").val(data.itemTpCd);
		$("#clorCdInP").val(data.clorCd);
		$("#poNoInP").val(data.poNo);
		$("#actncNoInP").val(data.actncNo);
		
		$("#actncQtyInP").val(data.actncQty);
	
	}
	
	//등록
	function insertDataInP(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidationInP();
		
		if(param != false){
			
			$.ajax({
				url : 'insertInout.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData2();//부모창 새로고침
						
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
		
		param.eqtUseCd = $("#eqtUseCdInP").val();			//단말기용도
		if(param.eqtUseCd == '' && itemTpCd == "C"){
			//단말기용도를 선택해 주세요
			alert('<spring:message code="LAB.M03.LAB00016" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.eqtRsrcClCd = $("#eqtRsrcClCdInP").val();		//단말기자원
		if(param.eqtRsrcClCd == '' && itemTpCd == "C"){
			//단말기자원을 선택해 주세요
			alert('<spring:message code="LAB.M03.LAB00018" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.inoutDt = dateFormatToStringYYYYMMDD($("#inoutDtInP").val());			//입고일자
		if(param.inoutDt == ''){
			//입고일자를 입력해 주세요.
			alert('<spring:message code="LAB.M08.LAB00163" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.inoutQty = $("#inoutQtyInP").val();			//입고수량
		if(param.inoutQty == ''){
			//입고수량를 입력해 주세요.
			alert('<spring:message code="LAB.M08.LAB00163" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var actncQtyInP = Number($("#actncQtyInP").val());	//납품수량
		//전체 rowData 가져오기
		var data2 = $("#deliveryInspectionTable2").getRowData();
		for(var i=0; i < data2.length; i++){
			actncQtyInP = actncQtyInP - Number(data2[i].inoutQty);
		}
		
		if(actncQtyInP < Number(param.inoutQty)){
			alert('입고수량의 합계는 납품수량을 초과 할 수 없습니다.');		//입고수량의 합계는 납품수량을 초과 할 수 없습니다.
			return false;
		}
		
		param.inoutClCd = "1"; 		// 입출고구분코드 (입고)
		param.inoutPrgrStatCd = "10"; 	// 입고진행상태코드 (입고등록)
		param.ownOrgId = $("#lgstCentIdInP").val();		//보유조직ID - 물류센터ID
		param.inoutOrgId = "${session_user.orgId}";		//입출고조직ID
		param.relInoutId = "";		//관련입출고ID
		param.dlvId = "";		//배송ID
		param.itemId = $("#itemIdInP").val();			//제품ID
		param.inoutResnCd = $("#inoutResnCdInP").val();	//입출고사유코드
		param.itemTpCd = $("#itemTpCdInP").val();		//제품유형코드
		param.clorCd = $("#clorCdInP").val();			//제품유형코드
		
		param.inoutUtPrc = $("#inoutUtPrcInP").val();	//제품단가
		param.inoutAmt = $("#inoutAmtInP").val();		//공급가
		param.inoutAddTx = $("#inoutAddTxInP").val();	//부가세
		param.inoutTotAmt = $("#inoutTotAmtInP").val();	//총금액
		param.note = "";	//비고
		
		param.poNo = $("#poNoInP").val();	//발주번호
		param.actncNo = $("#actncNoInP").val();	//납품번호
		param.ordNo = "";	//주문번호
		
		return param;
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M08.LAB00152"/><!-- 입고내역등록 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M08.LAB00152"/><!-- 입고내역등록 --></h4>
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
				<th><spring:message code="LAB.M08.LAB00148"/><!-- 입고ID --></th>
				<td>
					<input type="text" id="inoutIdInP" name="inoutIdInP" class="w200" disabled="disabled" />
					
					<input type="hidden" id="soIdInP" name="soIdInP" />
					<input type="hidden" id="lgstCentIdInP" name="lgstCentIdInP" />
					<input type="hidden" id="itemIdInP" name="itemIdInP" />
					<input type="hidden" id="inoutResnCdInP" name="inoutResnCdInP" />
					<input type="hidden" id="itemTpCdInP" name="itemTpCdInP" />
					<input type="hidden" id="clorCdInP" name="clorCdInP" />
					<input type="hidden" id="actncNoInP" name="actncNoInP" />
					
					<input type="hidden" id="actncQtyInP" name="actncQtyInP" />
					
				                                        
				</td>
				<th><spring:message code="LAB.M09.LAB00215"/><!-- 진행상태--></th>
				<td>
					<input type="text" id="inoutPrgrStatCdInP" name="inoutPrgrStatCdInP" class="w200" disabled="disabled" />
				                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M05.LAB00042"/><!-- 물류센터 --></th>
				<td>
					<input type="text" id="lgstCentNmInP" name="lgstCentNmInP" class="w200" disabled="disabled" />
				                                        
				</td>
				<th><spring:message code="LAB.M08.LAB00158"/><!-- 입고사유 --></th>
				<td>
					<input type="text" id="inoutResnCdInP" name="inoutResnCdInP" class="w200" disabled="disabled" />
				                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00031"/><!-- 발주번호 --></th>
				<td>
					<input type="text" id="poNoInP" name="poNoInP" class="w200" disabled="disabled" />
				                                        
				</td>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
				<td>
					<input type="text" id="itemNmInP" name="itemNmInP" class="w200" disabled="disabled" />
				                                        
				</td>
			</tr>
			<tr>
				<th id="titleEqtUseCdInP"><spring:message code="LAB.M03.LAB00016"/><!-- 단말기용도 --></th>
				<td>
					<select id="eqtUseCdInP" name="eqtUseCdInP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${eqtUseCd}" var="eqtUseCd" varStatus="status">
							<option value="${eqtUseCd.commonCd}">${eqtUseCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th id="titleEqtRsrcClCdInP"><spring:message code="LAB.M03.LAB00018"/><!-- 단말기자원 --></th>
				<td>
					<select id="eqtRsrcClCdInP" name="eqtRsrcClCdInP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${eqtRsrcClCd}" var="eqtRsrcClCd" varStatus="status">
							<option value="${eqtRsrcClCd.commonCd}">${eqtRsrcClCd.commonCdNm}</option>
						</c:forEach>
					</select>                                          
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00163"/><!-- 입고일자 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="inoutDtInP" name="inoutDtInP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				                                        
				</td>
				<th><spring:message code="LAB.M08.LAB00160"/><!-- 입고수량 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="inoutQtyInP" name="inoutQtyInP" class="w200" />
				                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00110"/><!-- 제품단가 --></th>
				<td>
					<input type="text" id="inoutUtPrcInP" name="inoutUtPrcInP" class="w200" disabled="disabled" />
				                                        
				</td>
				<th><spring:message code="LAB.M01.LAB00061"/><!-- 공급가 --></th>
				<td>
					<input type="text" id="inoutAmtInP" name="inoutAmtInP" class="w200" disabled="disabled" />
				                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00088"/><!-- 부가세 --></th>
				<td>
					<input type="text" id="inoutAddTxInP" name="inoutAddTxInP" class="w200" disabled="disabled" />
				                                        
				</td>
				<th><spring:message code="LAB.M10.LAB00051"/><!-- 총금액 --></th>
				<td>
					<input type="text" id="inoutTotAmtInP" name="inoutTotAmtInP" class="w200" disabled="disabled" />
				                                        
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
	
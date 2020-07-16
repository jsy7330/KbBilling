<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/dnUtil.js"></script>

<script type="text/javascript">

	var tax = Number(getTaxRate()) / 100;
	
	$(document).ready(function() {
		
		//팝업달력 셋팅
		if($(".datepicker").length > 0){
			$( ".datepicker" ).datepicker({
			      changeMonth: true,
			      changeYear: true,
			      regional:lng
			    });
		}
		$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
		$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
		
		setDataInP();
		
		$("#btnInsert").click(function() {
			insertDataInP();
		});
		
		$( "#actncQtyInP" ).keyup(function(event) {
			var actncQtyInP = Number($("#actncQtyInP").val());
			var actncUtPrcInP = Number($("#actncUtPrcInP").val());
			
			$("#actncAddTxInP").val(actncQtyInP * actncUtPrcInP * tax);
			var actncAddTxInP = Number($("#actncAddTxInP").val());
			
			$("#actncAmtInP").val(actncQtyInP * actncUtPrcInP);
			$("#actncTotAmtInP").val( (actncQtyInP * actncUtPrcInP) + actncAddTxInP );
			
		});
		
		
	});
	
	
	function setDataInP(){
		
		var rowId  = $("#deliveryConfirmationTable").jqGrid("getGridParam", "selrow" );
		var data = $("#deliveryConfirmationTable").getRowData(rowId);
		
		$("#poNoInP").val(data.poNo);
		$("#soIdInP").val(data.soId);
		$("#mncoNmInP").val(data.mncoNm);
		$("#mncoIdInP").val(data.mncoId);
		$("#itemTpNmInP").val(data.itemTpNm);
		$("#itemTpCdInP").val(data.itemTpCd);
		$("#itemNmInP").val(data.itemNm);
		$("#itemIdInP").val(data.itemId);
		$("#colorNmInP").val(data.colorNm);
		$("#clorCdInP").val(data.clorCd);
		//$("#actncQtyInP").val(data.actncQty);
		$("#actncUtPrcInP").val(data.poUtPrc);
		//$("#actncAmtInP").val(data.actncAmt);
		$("#actncAddTxInP").val(data.addTx);
		$("#actncTotAmtInP").val(data.actncTotAmt);
		$("#lgstCentNmInP").val(data.lgstCentNm);
		$("#lgstCentIdInP").val(data.lgstCentId);
		$("#actncInchrgInP").val(data.actncInchrg);
		$("#actncInchrgTelInP").val(data.actncInchrgTel);
		$("#dlvZipCdInP").val(data.dlvZipCd);
		$("#dlvBssAddrInP").val(data.dlvBssAddr);
		$("#dlvDtlAddrInP").val(data.dlvDtlAddr);
		
		$("#poQtyInP").val(data.poQty);
		$("#poActncQtyInP").val(data.poActncQty);
	
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
				url : 'insertActnc.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();//부모창 새로고침
						searchData2($("#poNoInP").val());//부모창 새로고침
						
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
		
		param.poNo = $("#poNoInP").val();
		param.soId = $("#soIdInP").val();
		param.ordNo = "";
		param.itemId = $("#itemIdInP").val();
		param.itemTpCd = $("#itemTpCdInP").val();
		param.clorCd = $("#clorCdInP").val();
		param.actncDt = dateFormatToStringYYYYMMDD( $("#actncDtInP").val() );
		if(param.actncDt == ''){
			//납품일자를 입력해 주세요.
			alert('<spring:message code="LAB.M02.LAB00028" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		param.actncPrgrStatCd = "30";
		param.actncQty = $("#actncQtyInP").val();
		if(param.actncQty == ''){
			//납품수량
			alert('<spring:message code="LAB.M02.LAB00027" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		
		var qty = Number($("#poQtyInP").val() - $("#poActncQtyInP").val());
		if(param.actncQty > qty){
			alert("납품가능 수량보다 납품 수량이 많습니다. 납품가능 수량 : " + qty);
			return false;
		}
		
		param.actncUtPrc = $("#actncUtPrcInP").val();
		param.actncAmt = $("#actncAmtInP").val();
		param.actncAddTx = $("#actncAddTxInP").val();
		param.actncTotAmt = $("#actncTotAmtInP").val();
		param.actncOrgId = $("#lgstCentIdInP").val();	//물류센터아이디
		param.actncInchrgId = $("#actncInchrgInP").val();
		param.actncInchrgTelNo = $("#actncInchrgTelInP").val();
		param.note = $("#noteInP").val();
		param.inoutResnCd = "00";
		param.inoutClCd = "1";
		
		return param;
	}
</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00100"/><!-- 제조사납품등록 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->
	
	
<!-- center -->
<div class="layer_box">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M09.LAB00100"/><!-- 제조사납품등록 --></h4>
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
				<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --></th>
				<td>
					<input type="hidden" id="poNoInP" name="poNoInP" />
					<input type="hidden" id="soIdInP" name="soIdInP" />
					<input type="hidden" id="poQtyInP" name="poQtyInP" />
					<input type="hidden" id="poActncQtyInP" name="poActncQtyInP" />
					<input type="text" id="mncoNmInP" name="mncoNmInP" class="w200" disabled="disabled" />
					<input type="hidden" id="mncoIdInP" name="mncoIdInP" class="w170" />
				</td>
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
				<td>
					<input type="text" id="itemTpNmInP" name="itemTpNmInP" class="w200" disabled="disabled" />
					<input type="hidden" id="itemTpCdInP" name="itemTpCdInP" class="w170" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
				<td>
					<input type="text" id="itemNmInP" name="itemNmInP" class="w200" disabled="disabled" />
					<input type="hidden" id="itemIdInP" name="itemIdInP" class="w170" />
				</td>
				<th><spring:message code="LAB.M07.LAB00162"/><!-- 색상 --></th>
				<td>
					<input type="text" id="colorNmInP" name="colorNmInP" class="w200" disabled="disabled" />
					<input type="hidden" id="clorCdInP" name="clorCdInP" class="w170" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M02.LAB00027"/><!-- 납품수량 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="actncQtyInP" name="actncQtyInP" class="w200" />
				</td>
				<th><spring:message code="LAB.M02.LAB00022"/><!-- 납품단가 --></th>
				<td>
					<input type="text" id="actncUtPrcInP" name="actncUtPrcInP" class="w200" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M02.LAB00020"/><!-- 납품금액 --></th>
				<td>
					<input type="text" id="actncAmtInP" name="actncAmtInP" class="w200" disabled="disabled" />
				</td>
				<th><spring:message code="LAB.M02.LAB00026"/><!-- 납품부가세 --></th>
				<td>
					<input type="text" id="actncAddTxInP" name="actncAddTxInP" class="w200" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M02.LAB00030"/><!-- 납품총금액 --></th>
				<td>
					<input type="text" id="actncTotAmtInP" name="actncTotAmtInP" class="w200" disabled="disabled" />
				</td>
				<th><spring:message code="LAB.M02.LAB00028"/><!-- 납품일자 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="actncDtInP" name="actncDtInP" class="datepicker" readonly="readonly" />
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
				<th><spring:message code="LAB.M05.LAB00042"/><!-- 물류센터 --></th>
				<td colspan="3">
					<input type="text" id="lgstCentNmInP" name="lgstCentNmInP" class="w200" disabled="disabled" />
					<input type="hidden" id="lgstCentIdInP" name="lgstCentIdInP" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00134"/><!-- 인수 담당자 --></th>
				<td>
					<input type="text" id="actncInchrgInP" name="actncInchrgInP" class="w200" disabled="disabled" />
				</td>
				<th><spring:message code="LAB.M03.LAB00033"/><!-- 담당자 연락처 --></th>
				<td>
					<input type="text" id="actncInchrgTelInP" name="actncInchrgTelInP" class="w200" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00049"/><!-- 배송지 주소 --></th>
				<td colspan="3">
					<input type="text" id="dlvZipCdInP" name="dlvZipCdInP" class="w50" disabled="disabled" />
					<input type="text" id="dlvBssAddrInP" name="dlvBssAddrInP" class="w270" disabled="disabled" />
					<input type="text" id="dlvDtlAddrInP" name="dlvDtlAddrInP" class="w270" disabled="disabled" />
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
	
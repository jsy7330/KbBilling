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
		
		$('#poQtyUpP').number( true );
		$('#poUtPrcUpP').number( true );
		
		$('#eqtUseCdUpP').selectmenu({});
		$('#eqtRsrcClCdUpP').selectmenu({});

		setDataUpP();
		
		$("#btnInsert").click(function() {
			insertDataUpP();
		});
		
		$( "#inoutQtyUpP" ).keyup(function(event) {
			var inoutQtyUpP = Number($("#inoutQtyUpP").val());
			var inoutUtPrcUpP = Number($("#inoutUtPrcUpP").val());
			
			$("#inoutAddTxUpP").val(inoutQtyUpP * inoutUtPrcUpP * tax);
			var inoutAddTxUpP = Number($("#inoutAddTxUpP").val());
			
			
			$("#inoutAmtUpP").val(inoutQtyUpP * inoutUtPrcUpP);
			$("#inoutTotAmtUpP").val(inoutQtyUpP * inoutUtPrcUpP + inoutAddTxUpP);
		});
		
		
	});
	
	function setDataUpP(){
			
		var rowId  = $("#deliveryInspectionTable").jqGrid("getGridParam", "selrow" );
		var data = $("#deliveryInspectionTable").getRowData(rowId);
		
		$("#soIdUpP").val(data.soId);
		$("#lgstCentIdUpP").val(data.lgstCentId);
		$("#itemIdUpP").val(data.itemId);
		$("#inoutResnCdUpP").val(data.inoutResnCd);
		$("#itemTpCdUpP").val(data.itemTpCd);
		$("#clorCdUpP").val(data.clorCd);
		$("#actncNoUpP").val(data.actncNo);
		$("#actncQtyUpP").val(data.actncQty);
		
		itemTpCd = data.itemTpCd;
		if(itemTpCd == "C"){
			$("#titleEqtUseCdUpP").append('<span class="dot">*</span>');
			$("#titleEqtRsrcClCdUpP").append('<span class="dot">*</span>');
		}else{
			$("#eqtUseCdUpP").selectmenu("disable");
			$("#eqtRsrcClCdUpP").selectmenu("disable");
		}
		
		var rowId3  = $("#deliveryInspectionTable2").jqGrid("getGridParam", "selrow" );
		var data3 = $("#deliveryInspectionTable2").getRowData(rowId3);
	
		$("#inoutIdUpP").val(data3.inoutId);
		$("#inoutPrgrStatCdUpP").val(data3.inoutPrgrStatNm);
		$("#lgstCentNmUpP").val(data3.inoutOrgNm);
		$("#inoutResnNmUpP").val(data3.inoutResnNm);
		
		$("#poNoUpP").val(data3.poNo);
		$("#itemNmUpP").val(data3.itemNm);
		
		$("#eqtUseCdUpP").val(data3.eqtUseCd);
		$("#eqtUseCdUpP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#eqtRsrcClCdUpP").val(data3.eqtRsrcClCd);
		$("#eqtRsrcClCdUpP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		
		$("#inoutDtUpP").val(data3.inoutDt);
		
		$("#inoutQtyUpP").val(data3.inoutQty);
		$("#inoutUtPrcUpP").val(data3.inoutUtPrc);
		$("#inoutAmtUpP").val(data3.inoutAmt);
		$("#inoutAddTxUpP").val(data3.inoutAddTx);
		$("#inoutTotAmtUpP").val(data3.inoutTotAmt);
		
		$("#inoutIdUpP").val(data3.inoutId);
		
		
		
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
				url : 'updateInout.json',
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
	function checkValidationUpP(){
		var param = new Object();
		
		param.soId = $("#soIdUpP").val();
		
		param.eqtUseCd = $("#eqtUseCdUpP").val();			//단말기용도
		if(param.eqtUseCd == '' && itemTpCd == "C"){
			//단말기용도를 선택해 주세요
			alert('<spring:message code="LAB.M03.LAB00016" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.eqtRsrcClCd = $("#eqtRsrcClCdUpP").val();		//단말기자원
		if(param.eqtRsrcClCd == '' && itemTpCd == "C"){
			//단말기자원을 선택해 주세요
			alert('<spring:message code="LAB.M03.LAB00018" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.inoutDt = dateFormatToStringYYYYMMDD($("#inoutDtUpP").val());			//입고일자
		if(param.inoutDt == ''){
			//입고일자를 입력해 주세요.
			alert('<spring:message code="LAB.M08.LAB00163" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		param.inoutQty = $("#inoutQtyUpP").val();			//입고수량
		if(param.inoutQty == ''){
			//입고수량를 입력해 주세요.
			alert('<spring:message code="LAB.M08.LAB00163" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var actncQtyUpP = Number($("#actncQtyUpP").val());	//납품수량
		//전체 rowData 가져오기
		var data2 = $("#deliveryInspectionTable2").getRowData();
		for(var i=0; i < data2.length; i++){
			actncQtyUpP = actncQtyUpP - Number(data2[i].inoutQty);
		}
		
		if(actncQtyUpP < Number(param.inoutQty)){
			alert('입고수량의 합계는 납품수량을 초과 할 수 없습니다.');		//입고수량의 합계는 납품수량을 초과 할 수 없습니다.
			return false;
		}
		
		param.inoutClCd = "1"; 		// 입출고구분코드 (입고)
		param.inoutPrgrStatCd = "10"; 	// 입고진행상태코드 (입고등록)
		param.ownOrgId = $("#lgstCentIdUpP").val();		//보유조직ID - 물류센터ID
		param.inoutOrgId = "${session_user.orgId}";		//입출고조직ID
		param.relInoutId = "";		//관련입출고ID
		param.dlvId = "";		//배송ID
		param.itemId = $("#itemIdUpP").val();			//제품ID
		param.inoutResnCd = $("#inoutResnCdUpP").val();	//입출고사유코드
		param.itemTpCd = $("#itemTpCdUpP").val();		//제품유형코드
		param.clorCd = $("#clorCdUpP").val();			//제품유형코드
		
		param.inoutUtPrc = $("#inoutUtPrcUpP").val();	//제품단가
		param.inoutAmt = $("#inoutAmtUpP").val();		//공급가
		param.inoutAddTx = $("#inoutAddTxUpP").val();	//부가세
		param.inoutTotAmt = $("#inoutTotAmtUpP").val();	//총금액
		param.note = "";	//비고
		
		param.poNo = $("#poNoUpP").val();	//발주번호
		param.actncNo = $("#actncNoUpP").val();	//납품번호
		param.ordNo = "";	//주문번호
		
		param.inoutId = $("#inoutIdUpP").val();	//발주번호
		
		
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
					<input type="text" id="inoutIdUpP" name="inoutIdUpP" class="w200" disabled="disabled" />
					
					<input type="hidden" id="soIdUpP" name="soIdUpP" />
					<input type="hidden" id="lgstCentIdUpP" name="lgstCentIdUpP" />
					<input type="hidden" id="itemIdUpP" name="itemIdUpP" />
					<input type="hidden" id="inoutResnCdUpP" name="inoutResnCdUpP" />
					<input type="hidden" id="itemTpCdUpP" name="itemTpCdUpP" />
					<input type="hidden" id="clorCdUpP" name="clorCdUpP" />
					<input type="hidden" id="actncNoUpP" name="actncNoUpP" />
					
					<input type="hidden" id="actncQtyUpP" name="actncQtyUpP" />
					<input type="hidden" id="inoutIdUpP" name="inoutIdUpP" />
					
					
					
				                                        
				</td>
				<th><spring:message code="LAB.M09.LAB00215"/><!-- 진행상태--></th>
				<td>
					<input type="text" id="inoutPrgrStatCdUpP" name="inoutPrgrStatCdUpP" class="w200" disabled="disabled" />
				                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M05.LAB00042"/><!-- 물류센터 --></th>
				<td>
					<input type="text" id="lgstCentNmUpP" name="lgstCentNmUpP" class="w200" disabled="disabled" />
				                                        
				</td>
				<th><spring:message code="LAB.M08.LAB00158"/><!-- 입고사유 --></th>
				<td>
					<input type="text" id="inoutResnNmUpP" name="inoutResnNmUpP" class="w200" disabled="disabled" />
				                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00031"/><!-- 발주번호 --></th>
				<td>
					<input type="text" id="poNoUpP" name="poNoUpP" class="w200" disabled="disabled" />
				                                        
				</td>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
				<td>
					<input type="text" id="itemNmUpP" name="itemNmUpP" class="w200" disabled="disabled" />
				                                        
				</td>
			</tr>
			<tr>
				<th id="titleEqtUseCdUpP"><spring:message code="LAB.M03.LAB00016"/><!-- 단말기용도 --></th>
				<td>
					<select id="eqtUseCdUpP" name="eqtUseCdUpP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${eqtUseCd}" var="eqtUseCd" varStatus="status">
							<option value="${eqtUseCd.commonCd}">${eqtUseCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th id="titleEqtRsrcClCdUpP"><spring:message code="LAB.M03.LAB00018"/><!-- 단말기자원 --></th>
				<td>
					<select id="eqtRsrcClCdUpP" name="eqtRsrcClCdUpP" class="w200">
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
						<input type="text" id="inoutDtUpP" name="inoutDtUpP" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				                                        
				</td>
				<th><spring:message code="LAB.M08.LAB00160"/><!-- 입고수량 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="inoutQtyUpP" name="inoutQtyUpP" class="w200" />
				                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00110"/><!-- 제품단가 --></th>
				<td>
					<input type="text" id="inoutUtPrcUpP" name="inoutUtPrcUpP" class="w200" disabled="disabled" />
				                                        
				</td>
				<th><spring:message code="LAB.M01.LAB00061"/><!-- 공급가 --></th>
				<td>
					<input type="text" id="inoutAmtUpP" name="inoutAmtUpP" class="w200" disabled="disabled" />
				                                        
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00088"/><!-- 부가세 --></th>
				<td>
					<input type="text" id="inoutAddTxUpP" name="inoutAddTxUpP" class="w200" disabled="disabled" />
				                                        
				</td>
				<th><spring:message code="LAB.M10.LAB00051"/><!-- 총금액 --></th>
				<td>
					<input type="text" id="inoutTotAmtUpP" name="inoutTotAmtUpP" class="w200" disabled="disabled" />
				                                        
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
	
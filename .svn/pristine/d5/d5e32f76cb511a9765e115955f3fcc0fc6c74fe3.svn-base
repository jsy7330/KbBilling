<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		disableInputFields();

		$('#btnCancelChargePop').click(function() {

			var notResPvCount = $('#notResPvCount').val();

			// PV 응답없어 처리되지 않은 경우 이전 프로세스가 종료될 때 까지 이후 프로세스를 진행할 수 없음
			// if (notResPvCount == '1') {

			// }

			var rchrgTp = $('#rchrgTpPop').val();

			// Bundle 상품구매
			if (rchrgTp == '500' || rchrgTp == '600') {
				// 저장버튼 누를때 취소가능여부를 다시 호출한다.
				findCancel(function(ocsCancelYn) {
					var today = new Date();
					var rchrgTermDt = new Date('#{chrgInfo.rchrgTermDt}');

					if (rchrgTermDt.getTime() >= today.getTime()) {
						if (ocsCancelYn == 'N') {
							// 충전취소를 할 수 없습니다.
							alert('<spring:message code="MSG.M10.MSG00032" />');
							return false;
						}
					} else if (rchrgTermDt.getTime() < today.getTime()) {
						// 번들 상품의 유효기간이 종료되어 충전 취소를 할 수 없습니다.
						alert('<spring:message code="MSG.M06.MSG00020" />');
						return false;
					}
				});
			}

			// 바우쳐 충전취소
			if (rchrgTp == '300') {
				var rmnAmt = '${rmnAmt}';
				var chrgAmt = '${chrgInfo.chrgAmt}';

				if (rmnAmt < chrgAmt) {
					// 충전취소를 할 수 없습니다.
					alert('<spring:message code="MSG.M10.MSG00032" />');
					return false;
				}
			}

			// 충전취소 하시겠습니니까?
			var check = confirm('<spring:message code="MSG.M10.MSG00030" />');

			if (check == false) {
				return false;
			}

			var param = {
				rchrgTp : rchrgTp
				, chrgAmt : '${chrgInfo.chrgAmt}'
				, thrwyChrgId : '${chrgInfo.thrwyChrgId}'
				, rchrgDt : '${chrgInfo.rchrgDt}'
				, custId : '${chrgInfo.custId}'
				, ctrtId : '${chrgInfo.ctrtId}'
				, soId : '${chrgInfo.soId}'
				, prodCd : '${chrgInfo.prodCd}'
				, chrgAmt : '${chrgInfo.chrgAmt}'
				, comChrgAmt : '${chrgInfo.comChrgAmt}'
				, regrId : '${chrgInfo.regrId}'
				, chgrId : '${chrgInfo.chgrId}'
				, rchrgTp : '${chrgInfo.rchrgTp}'
				, rchrgStat : '${chrgInfo.rchrgStat}'
				, svcTelNo : '${chrgInfo.svcTelNo}'
				, svcEffDtm : '${chrgInfo.svcEffDtm}'
				, voSeqNo : '${chrgInfo.voSeqNo}'
				, voBarCd : '${chrgInfo.voBarCd}'
				, rchrgSeqNo : '${chrgInfo.rchrgSeqNo}'
				, rchrgCnt : '${chrgInfo.rchrgCnt}'
				, dataQuota : '${chrgInfo.dataQuota}'
				, rchrgTermDt : '${chrgInfo.rchrgTermDt}'
				, apprTp : '${chrgInfo.apprTp}'
				, ifSucYn : '${chrgInfo.ifSucYn}'
				, tr_type : '1'
			};

			$.ajax({
				url : 'cancelCharge.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					
					var result = data.result;

					if (result == 1) {
						var errorMsg = data.errorMsg;
						alert(errorMsg);
						return;
					}

					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});

		});
	});

	function findCancel(callback) {
		var custId = '${chrgInfo.custId}';
		var prodCd = '${chrgInfo.prodCd}';
		var thrwyChrgId = '${chrgInfo.thrwyChrgId}';

		var param = {
			custId : custId
			, prodCd : prodCd
			, thrwyChrgId : thrwyChrgId
		};

		$.ajax({
			url : 'getOcsCancelYn.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				
				var result = data.result;

				if (result == 1) {
					var errorMsg = data.errorMsg;
					alert(errorMsg);
					return;
				}

				var ocsCancelYn = data.ocsCancelYn;
				if (callback != null) {
					callback(ocsCancelYn);
				}
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
	}

	function disableInputFields() {
		disableInputField('rchrgTpPop');
		disableInputField('rchrgCntPop');
		disableInputField('rchrgDtPop');
		disableInputField('chrgAmtPop');
		disableInputField('voSeqNoPop');
		disableInputField('voBarCd');
		disableInputField('prodNmPop');
		disableInputField('svcEffDtmPop');
		disableInputField('apprTpPop');
		disableInputField('qosAmtPop');
		disableInputField('rchrgChgDtPop');
	}

	function disableInputField(id) {
		$("#" + id).addClass('not-active'); 
		$("#" + id).attr('disabled', true);
	}

	function enableInputField(id) {
		$("#" + id).removeClass('not-active');
		$("#" + id).removeAttr('disabled');
	}

</script>

<div class="layer_top">
     <div class="title"><spring:message code="LAB.M10.LAB00082"/><!-- 충전 --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">          
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00186"/><!-- 서비스번호 --></th>
				<td><input type="text" id="svcTelNoPop" name="svcTelNoPop" class="w150" value="${chrgInfo.svcTelNo}" /></td>
				<th><spring:message code="LAB.M01.LAB00032"/><!-- 계약ID --></th>
				<td><input type="text" id="ctrtIdPop" name="ctrtIdPop" class="w150" value=${chrgInfo.ctrtId} /></td>
				<th><spring:message code="LAB.M09.LAB00040"/><!-- 잔액 --></th>
				<td><input type="text" id="rmnAmtPop" name="rmnAmtPop" class="w150" value="${rmnAmt}" /></td>
			</tr>
		</thead>
	</table> 
</div>

<!-- center -->
<div class="layer_box">          
	<form id="chargeForm">
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M10.LAB00085"/><!-- 충전유형 --></th>
				<td colspan="5">
					<input type="text" id="rchrgTpPop" value="${chrgInfo.rchrgTp}" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M10.LAB00090"/><!-- 충전회차 --></th>
				<td><input type="text" id="rchrgCntPop" value="${chrgInfo.rchrgCnt}" /></td>
				<th><spring:message code="LAB.M10.LAB00086"/><!-- 충전일자 --></th>
				<td><input type="text" id="rchrgDtPop" value="${chrgInfo.rchrgDt}" /></td>
				<th><spring:message code="LAB.M10.LAB00083" /></th>
				<td><input type="text" id="chrgAmtPop" value="${chrgInfo.chrgAmt}" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00015"/><!-- 바우처일련번호 --></th>
				<td colspan="2"><input type="text" id="voSeqNoPop" value="${chrgInfo.voSeqNo}" /></td>
				<th><spring:message code="LAB.M06.LAB00004" /><!-- 바우처관리번호 --></th>
				<td colspan="2"><input type="text" id="voBarCd" value="${chrgInfo.voBarCd}" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00114" /><!-- 상품 --></th>
				<td>
					<input type="text" id="prodNmPop" value="${chrgInfo.prodNm}" />
					<input type="hidden" id="prodIdPop" value="${chrgInfo.prodId}" />
				</td>
				<th><spring:message code="LAB.M07.LAB00189" /><!-- 서비스종료일시 --></th>
				<td><input type="text" id="svcEffDtmPop" value="${chrgInfo.svcEffDtm}" /></td>
				<th><spring:message code="LAB.M07.LAB00273" /><!-- 승인유형 --></th>
				<td><input type="text" id="apprTpPop" value="${chrgInfo.apprTp}" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M11.LAB00015" /><!-- 쿼터 --></th>
				<td><input type="text" id="qosAmtPop" value="${chrgInfo.qosAmt}" /></td>
				<th><spring:message code="LAB.M10.LAB00092" /><!-- 취소가능일자 --></th>
				<td colspan="3"><input type="text" id="rchrgChgDtPop" value="${chrgInfo.rchrgChgDt}" /></td>
			</tr>
		</thead>
	</table>

	</form>
</div>

<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnCancelChargePop" href="#" ><span class="confirm_icon"><spring:message code="message code='LAB.M10.LAB00089" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<!-- notResPvCount -->
<input type="hidden" id="notResPvCount" value="${notResPvCount}" >

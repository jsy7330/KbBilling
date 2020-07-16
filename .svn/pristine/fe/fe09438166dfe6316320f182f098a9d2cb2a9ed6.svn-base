<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {

		$('#btnTransfer_voucherTransferPopup').click(function() {

			// 이관처리하시겠습니까?
			var check = confirm('<spring:message code="MSG.M08.MSG00045" />');
			if (check == false) {
				return;
			}

			var soId = $('#soIdSeP').val();
			var itemId = $('#itemId_voucherTransferPopup').val();
			var fromVoBarCd = $('#fromVoBarCd').val();
			var toVoBarCd = $('#toVoBarCd').val();
			var voProdCd = $('#voProdCd_voucherTransferPopup').val();

			var param = {
				soId : soId
				, itemId : itemId
				, fromVoBarCd : fromVoBarCd
				, toVoBarCd : toVoBarCd
				, voProdCd : voProdCd
			};

			console.log('soId : ' + soId);
			console.log('itemId : ' + itemId);
			console.log('fromVoBarCd : ' + fromVoBarCd);
			console.log('toVoBarCd : ' + toVoBarCd);
			console.log('voProdCd : ' + voProdCd);

			$.ajax({
				url : '/distributor/logistics/voucherMng/voucherGenerate/voucherTransfer.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {

					var result = data.result;

					if (result == 0) {
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						$("#btnColse1SeP").trigger('click');
						refreshGrid();
					} else {
						var errorMsg = data.errorMsg;
						alert(errorMsg);
					}

				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});

		});

		$('#btnSearchItem_voucherTransferPopup').click(function() {
			var url="/system/common/common/productMng/productSearchPopup.ajax";
			url = url + "?soId=${soId}&popType=o&returnId1=itemId_voucherTransferPopup&returnId2=itemNm_voucherTransferPopup&presetItemTpCd=V";
			
			var width = 815;
			var height = 510;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
		});

	});
	
</script>


<div class="layer_top">
     <div class="title"><spring:message code="LAB.M15.LAB00097"/><!-- Voucher Transfer --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td colspan="3">
				
					<select class="w200" id="soIdSeP" name="soIdSeP">
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00105"/><!-- 제품 --></th>
				<td colspan="3">
					<div class="inp_date w420">
						<span>
							<input type="text" id="itemId_voucherTransferPopup" name="itemId" class="w190" disabled="disabled" />
							<input type="text" id="itemNm_voucherTransferPopup" name="itemNm" class="w190" disabled="disabled" />
							<a href="#" id="btnSearchItem_voucherTransferPopup" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
						</span>
					</div>
				</td>
			</tr>
			</tr>
				<th><spring:message code="LAB.M01.LAB00161" /><!-- 관리번호 --></th>
				<td colspan="3">
					<!-- <div class="inp_date w280"> -->
						<span><input type="text" id="fromVoBarCd" class="w200" /> ~ <input type="text" id="toVoBarCd" class="w200" /></span>
					<!-- </div> -->
				</td>
			<tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00012" /><!-- 바우처상품 --></th>
				<td>
					<div class="inp_date w280">
						<input type="hidden" id="voProdCd_voucherTransferPopup" name="itemId" class="w250" disabled="disabled" />
						<input type="text" id="voProdNm_voucherTransferPopup" name="itemId" class="w250" disabled="disabled" />
						<a href="#" id="btnSearchVoucherItem_voucherTransferPopup" class="search not-active" disabled="disabled"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
			</tr>
		</thead>
	</table> 

</div>
<!--// center -->
<div class="btn_box">
<a class="grey-btn" id="btnTransfer_voucherTransferPopup" href="#" ><spring:message code="LAB.M08.LAB00118" /><!-- 이관 --></a>
</div>
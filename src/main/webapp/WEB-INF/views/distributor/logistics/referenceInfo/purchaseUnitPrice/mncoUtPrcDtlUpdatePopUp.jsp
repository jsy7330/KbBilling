<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		byteCheckKeyEvent();	//바이트체크
		$('#mncoOtptUtPrcUpP').number( true );
		
		setDataUpP();
		
		$("#btnInsert").click(function() {
			updateDataUpP();
		});
	
	});
	
	function setDataUpP(){
		
		var rowId  = $("#purchaseUnitPriceTable").jqGrid("getGridParam", "selrow" );
		var data = $("#purchaseUnitPriceTable").getRowData(rowId);
		
		var rowId2  = $("#purchaseUnitPriceTable2").jqGrid("getGridParam", "selrow" );
		var data2 = $("#purchaseUnitPriceTable2").getRowData(rowId2);
		
		$("#prcDtlSeqUpP").val(data2.prcDtlSeq);
		$("#mncoOtptUtPrcUpP").val(data2.mncoOtptUtPrc);
		
		$("#soIdUpP").val(data.soId);
		$("#itemIdUpP").val(data.itemId);
		$("#itemNmUpP").val(data.itemNm);
		
		$("#eftStrtDtUpP").val(data2.eftStrtDt);
		$("#eftEndDtUpP").val(data2.eftEndDt);
		
	}
	
	//수정
	function updateDataUpP(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		
		var param = checkValidationUpP();
		
		if(param != false){
			
			$.ajax({
				url : 'updateMncoUtPrcDtl.json',
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
		
		param.prcDtlSeq = $("#prcDtlSeqUpP").val();
		param.soId = $("#soIdUpP").val();
		param.itemId = $("#itemIdUpP").val();
		param.itemNm = $("#itemNmUpP").val();
		param.eftStrtDt = dateFormatToStringYYYYMMDD($("#eftStrtDtUpP").val());
		param.eftEndDt = dateFormatToStringYYYYMMDD($("#eftEndDtUpP").val());
		
		param.mncoOtptUtPrc = $("#mncoOtptUtPrcUpP").val();			//매입단가
		
		if(param.mncoOtptUtPrc == ''){
			//매입단가를 등록해 주세요.
			alert('<spring:message code="LAB.M05.LAB00005" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		return param;
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M05.LAB00007"/><!-- 매입단가수정 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M05.LAB00007"/><!-- 매입단가수정 --></h4>
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
				<th><spring:message code="LAB.M09.LAB00106"/><!-- 제품ID --><span class="dot">*</span></th>
				<td>
					<input type="text" id="itemIdUpP" name="itemIdUpP" class="w170" disabled="disabled">
					<input type="hidden" id="soIdUpP" name="soIdUpP" />
					<input type="hidden" id="prcDtlSeqUpP" name="prcDtlSeqUpP" />
				</td>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="itemNmUpP" name="itemNmUpP" class="w170" disabled="disabled">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00073"/><!-- 효력시작일자 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="eftStrtDtUpP" name="eftStrtDtUpP" class="w170" disabled="disabled" />
					<!-- <a href="#" class="btn_cal"></a> -->
				</td>
				<th><spring:message code="LAB.M14.LAB00075"/><!-- 효력종료일자 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="eftEndDtUpP" name="eftEndDtUpP" class="w170" disabled="disabled" />
					<!-- <a href="#" class="btn_cal"></a> -->
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M05.LAB00005"/><!-- 매입단가 --><span class="dot">*</span></th>
				<td colspan="3">
					<input type="text" id="mncoOtptUtPrcUpP" name="mncoOtptUtPrcUpP" class="w170" checkbyte="12" />
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
	
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	var type = "U";	//저장버튼클릭시 등록, 수정 구분 값

	$(document).ready(function() {
		
		//저장버튼
		$("#btnInsert").click(function() {
			insertData();            
		});
		
		/*
		//유형 셀렉트 박스 체인지 이벤트
		$('#upymMngBsTpUpp').selectmenu({
		    change: function() {
		    	changeTpDtlCd();
		    }
		});
		*/
		// 년월 레이어 datepicker
		$(".monthPicker").datepicker({
	        dateFormat: 'yymm',
	        changeMonth: true,
	        changeYear: true,
	        showButtonPanel: true,

	        onClose: function(dateText, inst) {
	            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	            $(this).val($.datepicker.formatDate('yymm', new Date(year, month, 1)));
	        }
	    });

		// 년월 레이어 focus
	    $(".monthPicker").focus(function () {
	        $(".ui-datepicker-calendar").hide();
	        $("#ui-datepicker-div").position({
	            my: "center top",
	            at: "center bottom",
	            of: $(this)
	        });
	    });
		
		setData();
	});
	
	function setData(){
		var rowId  = $("#dunningCriteriaTable").jqGrid("getGridParam", "selrow" );
		var data = $("#dunningCriteriaTable").getRowData(rowId);
		
		var param = new Object();
		param.soId = data.soId;
		param.upymMngTp = data.upymMngTp;
		param.upymMngBsTp = data.upymMngBsTp;
		param.eftBgnYymm = data.eftBgnYymm;
		param.eftEndYymm = data.eftEndYymm;
		
		$.ajax({
			url : 'selectDunningCriteriaInfo.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data.dunningInfo);
				
				var dunningInfo = data.dunningInfo;

				if(dunningInfo == null){
				}else{
					$("#soIdUpp").val(dunningInfo.soId);
					$("#soNmUpp").val(dunningInfo.soNm);
					$("#upymMngTpUpp").val(dunningInfo.upymMngTp);
					//$("#upymMngTpUpp").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					$("#upymMngBsTpUpp").val(dunningInfo.upymMngBsTp);
					//$("#upymMngBsTpUpp").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					$("#eftBgnYymmUpp").val(dunningInfo.eftBgnYymm);
					$("#eftEndYymmUpp").val(dunningInfo.eftEndYymm);
					$("#upymAmtFromUpp").val(dunningInfo.upymAmtFrom);
					$("#upymAmtToUpp").val(dunningInfo.upymAmtTo);
					$("#upymMonthsFromUpp").val(dunningInfo.upymMonthsFrom);
					$("#upymMonthsToUpp").val(dunningInfo.upymMonthsTo);
					
					changeTpDtlCd();
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
	function insertData(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		
		if(!check){
			return;
		}
		
		var param = checkValidation();
		var url = "";
		url = "updateDunningCriteriaInfo.json";
		
		if(param != false){
			
			$.ajax({
				url : url,
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.resule != "0"){
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
	function checkValidation(){
		var param = new Object();
		
		param.upymAmtFrom = $("#upymAmtFromUpp").val();
		param.upymAmtTo = $("#upymAmtToUpp").val();
		param.upymMonthsFrom = $("#upymMonthsFromUpp").val();
		param.upymMonthsTo = $("#upymMonthsToUpp").val();
		

		var vTpDtlCd = $("#upymMngBsTpUpp").val();
		
		// 금액
		if (vTpDtlCd == "1" || vTpDtlCd == "7") {
			if(param.upymAmtFrom == ''){
				//금액[율]시작값을 입력하세요.
				alert('<spring:message code="LAB.M01.LAB00194" /> <spring:message code="MSG.M08.MSG00043" />');	
				return false;
			}
			if(param.upymAmtTo == ''){
				//금액[율]종료값을 입력하세요.
				alert('<spring:message code="LAB.M01.LAB00195" /> <spring:message code="MSG.M08.MSG00043" />');	
				return false;
			}
		}
		// 개월
		else if (vTpDtlCd == "2" || vTpDtlCd == "4") {
			if(param.upymMonthsFrom == ''){
				//개월시작값을 입력하세요.
				alert('<spring:message code="LAB.M01.LAB00017" /> <spring:message code="MSG.M08.MSG00043" />');	
				return false;
			}
			if(param.upymMonthsTo == ''){
				//개월종료값을 입력하세요.
				alert('<spring:message code="LAB.M01.LAB00018" /> <spring:message code="MSG.M08.MSG00043" />');	
				return false;
			}
		}
		// 금액  + 개월
		else if (vTpDtlCd == "3") {
			if(param.upymAmtFrom == ''){
				//금액[율]시작값을 입력하세요.
				alert('<spring:message code="LAB.M01.LAB00194" /> <spring:message code="MSG.M08.MSG00043" />');	
				return false;
			}
			if(param.upymAmtTo == ''){
				//금액[율]종료값을 입력하세요.
				alert('<spring:message code="LAB.M01.LAB00195" /> <spring:message code="MSG.M08.MSG00043" />');	
				return false;
			}
			if(param.upymMonthsFrom == ''){
				//개월시작값을 입력하세요.
				alert('<spring:message code="LAB.M01.LAB00017" /> <spring:message code="MSG.M08.MSG00043" />');	
				return false;
			}
			if(param.upymMonthsTo == ''){
				//개월종료값을 입력하세요.
				alert('<spring:message code="LAB.M01.LAB00018" /> <spring:message code="MSG.M08.MSG00043" />');	
				return false;
			}
		}
		
		param.soId = $("#soIdUpp").val();
		param.upymMngTp = $("#upymMngTpUpp").val();
		param.upymMngBsTp = $("#upymMngBsTpUpp").val();
		param.upymMngBsTp = $("#upymMngBsTpUpp").val();
		param.eftBgnYymm = $("#eftBgnYymmUpp").val();
		param.eftEndYymm = $("#eftEndYymmUpp").val();
		
		return param;
	}
	

	function changeTpDtlCd() {
		var vTpDtlCd = $("#upymMngBsTpUpp").val();
		
		$('#upymAmtFromUpp').number( true );
		$('#upymAmtToUpp').number( true );
		$('#upymMonthsFromUpp').number( true );
		$('#upymMonthsToUpp').number( true );
		
		// 금액
		if (vTpDtlCd == "1" || vTpDtlCd == "7") {
			$("#upymAmtFromUpp").val("0");
			$("#upymAmtToUpp").val("0");
			$('#upymMonthsFromUpp').attr('disabled',false);
			$('#upymMonthsToUpp').attr('disabled',false);
			$('#upymAmtFromUpp').attr('disabled',true);
			$('#upymAmtToUpp').attr('disabled',true);
		}
		// 개월
		else if (vTpDtlCd == "2" || vTpDtlCd == "4") {
			$("#upymMonthsFromUpp").val("0");
			$("#upymMonthsToUpp").val("0");
			$('#upymMonthsFromUpp').attr('disabled',true);
			$('#upymMonthsToUpp').attr('disabled',true);
			$('#upymAmtFromUpp').attr('disabled',false);
			$('#upymAmtToUpp').attr('disabled',false);
		}
		// 금액  + 개월
		else if (vTpDtlCd == "3") {
			$('#upymMonthsFromUpp').attr('disabled',false);
			$('#upymMonthsToUpp').attr('disabled',false);
			$('#upymAmtFromUpp').attr('disabled',false);
			$('#upymAmtToUpp').attr('disabled',false);
		}
		$('#upymMngBsTpUpp').attr('disabled',true);
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M03.LAB00056"/><!-- 대응기준정보 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box" id="areaUpP" >
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00253"/><!-- 수정상세정보 --></h4>
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
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td colspan="3">
					<input type="hidden" id="soIdUpp" name="soIdUpp" disabled="disabled" />
					<input type="text" id="soNmUpp" name="soNmUpp" class="w200" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00057"/><!-- 대응유형 --></th>
				<td>
					<select id="upymMngTpUpp" name="upymMngTpUpp" class="w180" disabled="disabled">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${dunningTpCd}" var="dunningTpCd" varStatus="status">
							<option value="${dunningTpCd.commonCd}">${dunningTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M03.LAB00058"/><!-- 대응유형기준 --></th>
				<td>
					<select id="upymMngBsTpUpp" name="upymMngBsTpUpp" class="w180" !disabled="disabled">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${dunningTpDtlCd}" var="dunningTpDtlCd" varStatus="status">
							<option value="${dunningTpDtlCd.commonCd}">${dunningTpDtlCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00193"/><!-- 금액[율] --></th>
				<td>
					<input type="text" id="upymAmtFromUpp" name="upymAmtFromUpp" maxlength="9" class="w135" />
					<span class="dash">~</span>
					<input type="text" id="upymAmtToUpp" name="upymAmtToUpp" maxlength="9" class="w135" />
				</td>
				<th><spring:message code="LAB.M01.LAB00016"/><!-- 개월 --></th>
				<td>
					<input type="text" id="upymMonthsFromUpp" name="upymMonthsFromUpp" maxlength="3" class="w135" />
					<span class="dash">~</span>
					<input type="text" id="upymMonthsToUpp" name="upymMonthsToUpp" maxlength="3" class="w135" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00222"/><!-- 기준시작년월 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w170">
							<input type="text" id="eftBgnYymmUpp" name="eftBgnYymmUpp" class="monthPicker" disabled="disabled" />
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00224"/><!-- 기준종료년월 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w170">
							<input type="text" id="eftEndYymmUpp" name="eftEndYymmUpp" class="monthPicker" disabled="disabled" />
						</div>
					</div>
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


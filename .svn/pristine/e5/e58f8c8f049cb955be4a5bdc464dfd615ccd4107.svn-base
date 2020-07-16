<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

	var type = "I";	//저장버튼클릭시 등록, 수정 구분 값

	$(document).ready(function() {
		
		//저장버튼
		$("#btnInsert").click(function() {
			insertData();            
		});
		
		//유형 셀렉트 박스 체인지 이벤트
		$('#upymMngBsTpInp').selectmenu({
		    change: function() {
		    	changeTpDtlCd();
		    }
		});
		
		settingDatepicker();	//팝업달력 셋팅
		
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
		
	});
	
	//등록
	function insertData(){
		
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		
		if(!check){
			return;
		}
		
		var param = checkValidation();
		var url = "insertDunningCriteriaInfo.json";
		
		console.log(param);
		
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

		param.soId = $("#soIdInp").val();
		if(param.soId == ''){
			//사업 을(를) 선택하세요.
			alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		
		param.upymMngTp = $("#upymMngTpInp").val();
		if(param.upymMngTp == ''){
			//대응유형 을(를) 선택하세요.
			alert('<spring:message code="LAB.M03.LAB00057" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		
		param.upymMngBsTp = $("#upymMngBsTpInp").val();
		if(param.upymMngBsTp == ''){
			//대응유형기준 을(를) 선택하세요.
			alert('<spring:message code="LAB.M03.LAB00058" /> <spring:message code="MSG.M08.MSG00042" />');	
			return false;
		}
		
		param.upymAmtFrom = $("#upymAmtFromInp").val();
		param.upymAmtTo = $("#upymAmtToInp").val();
		param.upymMonthsFrom = $("#upymMonthsFromInp").val();
		param.upymMonthsTo = $("#upymMonthsToInp").val();
		
		var vTpDtlCd = $("#upymMngBsTpInp").val();
		
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
		
		param.eftBgnYymm = $("#eftBgnYymmInp").val();
		if(param.eftBgnYymm == ''){
			//기준시작년월을 입력하세요.
			alert('<spring:message code="LAB.M01.LAB00222" /> <spring:message code="MSG.M08.MSG00043" />');	
			return false;
		}
		
		param.eftEndYymm = $("#eftEndYymmInp").val();
		if(param.eftEndYymm == ''){
			//기준종료년월을 입력하세요.
			alert('<spring:message code="LAB.M01.LAB00224" /> <spring:message code="MSG.M08.MSG00043" />');	
			return false;
		}
		
		return param;
	}
	
	function changeTpDtlCd() {
		var vTpDtlCd = $("#upymMngBsTpInp").val();
		
		$('#upymAmtFromInp').number( true );
		$('#upymAmtToInp').number( true );
		$('#upymMonthsFromInp').number( true );
		$('#upymMonthsToInp').number( true );
		$("#upymAmtFromInp").val("0");
		$("#upymAmtToInp").val("0");
		$("#upymMonthsFromInp").val("0");
		$("#upymMonthsToInp").val("0");
		
		// 금액
		if (vTpDtlCd == "1" || vTpDtlCd == "7") {
			$('#upymMonthsFromInp').attr('disabled',false);
			$('#upymMonthsToInp').attr('disabled',false);
			$('#upymAmtFromInp').attr('disabled',true);
			$('#upymAmtToInp').attr('disabled',true);
		}
		// 개월
		else if (vTpDtlCd == "2" || vTpDtlCd == "4") {
			$('#upymMonthsFromInp').attr('disabled',true);
			$('#upymMonthsToInp').attr('disabled',true);
			$('#upymAmtFromInp').attr('disabled',false);
			$('#upymAmtToInp').attr('disabled',false);
		}
		// 금액  + 개월
		else if (vTpDtlCd == "3") {
			$('#upymMonthsFromInp').attr('disabled',false);
			$('#upymMonthsToInp').attr('disabled',false);
			$('#upymAmtFromInp').attr('disabled',false);
			$('#upymAmtToInp').attr('disabled',false);
		}
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M03.LAB00056"/><!-- 대응기준정보 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box" id="areaInP" >
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M03.LAB00078"/><!-- 등록상세정보 --></h4>
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
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span></th>
				<td colspan="3">
					<select class="w150" id="soIdInp" name="soIdInp">
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00057"/><!-- 대응유형 --><span class="dot">*</span></th>
				<td>
					<select id="upymMngTpInp" name="upymMngTpInp" class="w180">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${dunningTpCd}" var="dunningTpCd" varStatus="status">
							<option value="${dunningTpCd.commonCd}">${dunningTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M03.LAB00058"/><!-- 대응유형기준 --><span class="dot">*</span></th>
				<td>
					<select id="upymMngBsTpInp" name="upymMngBsTpInp" class="w180">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${dunningTpDtlCd}" var="dunningTpDtlCd" varStatus="status">
							<option value="${dunningTpDtlCd.commonCd}">${dunningTpDtlCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00193"/><!-- 금액[율] --><span class="dot">*</span></th>
				<td>
					<input type="text" id="upymAmtFromInp" name="upymAmtFromInp" maxlength="9" class="w135" />
					<span class="dash">~</span>
					<input type="text" id="upymAmtToInp" name="upymAmtToInp" maxlength="9" class="w135" />
				</td>
				<th><spring:message code="LAB.M01.LAB00016"/><!-- 개월 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="upymMonthsFromInp" name="upymMonthsFromInp" maxlength="3" class="w135" />
					<span class="dash">~</span>
					<input type="text" id="upymMonthsToInp" name="upymMonthsToInp" maxlength="3" class="w135" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00222"/><!-- 기준시작년월 --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date w170">
							<input type="text" id="eftBgnYymmInp" name="eftBgnYymmInp" class="monthPicker" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00224"/><!-- 기준종료년월 --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date w170">
							<input type="text" id="eftEndYymmInp" name="eftEndYymmInp" class="monthPicker" />
							<a href="#" class="btn_cal"></a>
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


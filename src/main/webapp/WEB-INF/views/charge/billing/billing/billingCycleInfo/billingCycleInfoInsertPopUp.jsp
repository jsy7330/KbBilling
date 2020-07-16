<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<style type="text/css">
table.ui-datepicker-calendar { display:none; }
</style>

<script type="text/javascript">

$(document).ready(function() { 
			
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	
	//달력처리
	if($(".month-picker").length > 0){
		if(lng == 'ko'){
			format = 'yy-mm';
		}else if (lng == 'en'){
			format = 'mm/yy';
		}
		 $('.month-picker').datepicker( {
		        changeMonth: true,
		        changeYear: true,
		        showButtonPanel: true,
		        dateFormat: format,
		        onClose: function(dateText, inst) {
		            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		            $(this).datepicker('setDate', new Date(year, month, 1));
		        }
		    }
		 ).datepicker("setDate", new Date()); 
	}
	
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	//$( ".month-picker" ).datepicker( "option", "disabled", true );

	$('#soId').selectmenu({});
	$('#soId').selectmenu("refresh");
	
	//저장버튼
	$("#btnInsert").click(function() {
		goInsert();            
	});

});

//등록
function goInsert(){
	
	var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
	
	if(!check){
		return;
	}
	
	var param = checkValidation();
	var url = "insertBillingCycleInfo.json";
	
	if(param != false){
		
		$.ajax({
			url : url,
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				if(data.result != "0"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					//부모창 재검색
					goSearch();
					$("#btnClose").trigger('click');
				}
			},
			error: function(err){
		    	if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
	     			alert(err.responseJSON.exceptionMsg);
	     		}else{
	     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
	     		}
		    },
			complete : function() {
			}
		});
	}
}

//유효성 체크
function checkValidation(){
		var param = new Object();
		param.soId = $("#soId").val();	//사업
		
		if(param.soId == ''){
			alert('<spring:message code="MSG.M07.MSG00006" />');	  //사업을 선택하세요
			return false;
		}
		
		param.billYymm = dateFormatToStringYYYYMM($("#billYymm").val());		//효력종료월
		
		if(param.billYymm == ''){
			alert("<spring:message code="MSG.M10.MSG00006" />");  //청구년월을 입력해 주세요
			return false;
		}		
		return param;
}

</Script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M06.LAB00112"/></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<form id="insertList" name="insertList" method="post">
 
	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M06.LAB00112" /></h4>
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
		</colgroup>
		 <thead> 
			   <tr>
					<th><spring:message code="LAB.M07.LAB00003"/><span class="dot">*</span></th>
					<td>
					<select class="w200" id="soId" name="soId">
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
					</select>
					</td>
					<th><spring:message code="LAB.M10.LAB00033"/><span class="dot">*</span><!-- 청구년월 --></th>
					<td>
					  
					  <div class="date_box">
						<div class="inp_date w130">
							<input type="text" id="billYymm" name="billYymm"  class="month-picker" readonly="readonly" />
							<a href="#" id='btnCal' class="btn_cal"></a>
						</div>
					</div>
					
					
					</td>
				</tr>
		</thead>
	</table> 
</form>

<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div> 

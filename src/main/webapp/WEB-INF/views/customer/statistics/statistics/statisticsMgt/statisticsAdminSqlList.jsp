<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	$("#sqlCond").hide();
	$("#sqlList").hide();

	$("#soIdDot").css("display", "none"); 
	$("#acntIdDot").css("display", "none"); 
	$("#strtDdDot").css("display", "none"); 
	$("#endDdDot").css("display", "none"); 
	$("#svcTelNoDot").css("display", "none"); 
	$("#billBgnMthDot").css("display", "none"); 
	$("#billEndMthDot").css("display", "none"); 
	$("#ctrtStatDot").css("display", "none"); 
	$("#custIdDot").css("display", "none"); 
	$("#openBgnDdDot").css("display", "none"); 
	$("#openEndDdDot").css("display", "none"); 
	$("#searchDdDot").css("display", "none"); 

	$("#searchSoId").selectmenu({
	    change: function() {
    	   $('#searchStsCd').each( function() {
            	$('#searchStsCd option:gt(0)').remove();
            });
           $('#searchStsCd').val('SEL');
           $('#searchStsCd').selectmenu("refresh");
           getStmteList();
	    }
	});

	$("#searchStsCd").selectmenu({
	    change: function() {
			var param = new Object();
			param.searchStsCd =  $("#searchStsCd").val();
			param.searchStsCd = $(this).val();
			if($("#searchStsCd").val() == null || $("#searchStsCd").val() == ""){
				location.reload();
				return;
			}
			$.ajax({
				url : 'statisticsMgtListSearch.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					getSplit(data.getStatisticeDtl.varDefn);
					$('#tmout').val(data.getStatisticeDtl.tmout);
            		$('#stmt').val(data.getStatisticeDtl.stmt);
				},
			    error: function(request,status,error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    },
				complete : function() {
				}
			});	
	    }
	});

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
		 );
	}
	//엑셀출력
   	$('#printBtn').on('click',function (e) {
	   		if($("#printBtn").hasClass('not-active')){
				return;
			}
    		printLog();
		}
    );
});

function getSplit(str){
	var varDefn = str.split(",");

		$('#soId').val('');
		$('#soId').selectmenu("refresh");
		$("#soId").selectmenu("disable");

		$('#acntId').val('');
		$("#acntId").addClass('not-active');
		$("#acntId").attr('disabled',true);
	
		$('#strtDd').val('');
		$("#strtDd").addClass('not-active');
		$("#strtDd").attr('disabled',true);
		$("#btnCal3").addClass('not-active');
		$("#btnCal3").attr('disabled',true);
		
		$('#endDd').val('');
		$("#endDd").addClass('not-active');
		$("#endDd").attr('disabled',true);
		$("#btnCal4").addClass('not-active');
		$("#btnCal4").attr('disabled',true);
		
		$('#svcTelNo').val('');
		$("#svcTelNo").addClass('not-active');
		$("#svcTelNo").attr('disabled',true);
		
		$('#billBgnMth').val('');
		$("#billBgnMth").addClass('not-active');
		$("#billBgnMth").attr('disabled',true);
		$("#btnCal").addClass('not-active');
		$("#btnCal").attr('disabled',true);

		$('#billEndMth').val('');
		$("#billEndMth").addClass('not-active');
		$("#billEndMth").attr('disabled',true);
		$("#btnCal1").addClass('not-active');
		$("#btnCal1").attr('disabled',true);
		
		$('#ctrtStat').val('');
		$('#ctrtStat').selectmenu("refresh");
		$("#ctrtStat").selectmenu("disable");

		$('#custId').val('');
		$("#custId").addClass('not-active');
		$("#custId").attr('disabled',true);

		$('#openBgnDd').val('');
		$("#openBgnDd").addClass('not-active');
		$("#openBgnDd").attr('disabled',true);
		$("#btnCal5").addClass('not-active');
		$("#btnCal5").attr('disabled',true);

		$('#openEndDd').val('');
		$("#openEndDd").addClass('not-active');
		$("#openEndDd").attr('disabled',true);
		$("#btnCal6").addClass('not-active');
		$("#btnCal6").attr('disabled',true);

		$('#searchDd').val('');
		$("#searchDd").addClass('not-active');
		$("#searchDd").attr('disabled',true);
		$("#btnCal7").addClass('not-active');
		$("#btnCal7").attr('disabled',true);

		for(i=0; i<varDefn.length; i++){
			if(varDefn[i] =="SO_ID"){
				$('#soId').val('');
				$('#soId').selectmenu("refresh");
				$("#soId").selectmenu("enable");
				$("#soIdDot").css("display", ""); 
				$('#soId').val('');

			}else if(varDefn[i] =="ACNT_ID"){
				$("#acntId").removeClass('not-active');
				$("#acntId").removeAttr('disabled');
				$("#acntIdDot").css("display", ""); 

			}else if(varDefn[i] =="STRT_DD"){
				$("#strtDd").removeClass('not-active');
				$("#strtDd").removeAttr('disabled');
				$("#btnCal3").removeClass('not-active');
				$("#btnCal3").removeAttr('disabled');
				$("#strtDdDot").css("display", ""); 
			}else if(varDefn[i] =="END_DD"){
				$("#endDd").removeClass('not-active');
				$("#endDd").removeAttr('disabled');
				$("#btnCal4").removeClass('not-active');
				$("#btnCal4").removeAttr('disabled');
				$("#endDdDot").css("display", ""); 
			}else if(varDefn[i] =="SVC_TEL_NO"){
				$("#svcTelNo").removeClass('not-active');
				$("#svcTelNo").removeAttr('disabled');
				$("#svcTelNoDot").css("display", ""); 
			}else if(varDefn[i] =="BILL_BGN_MTH"){
				$("#billBgnMth").removeClass('not-active');
				$("#billBgnMth").removeAttr('disabled');
				$("#btnCal").removeClass('not-active');
				$("#btnCal").removeAttr('disabled');
				$("#billBgnMthDot").css("display", ""); 
			}else if(varDefn[i] =="BILL_END_MTH"){
				$("#billEndMth").removeClass('not-active');
				$("#billEndMth").removeAttr('disabled');
				$("#btnCal1").removeClass('not-active');
				$("#btnCal1").removeAttr('disabled');
				$("#billEndMthDot").css("display", ""); 
			}else if(varDefn[i] =="CTRT_STAT"){
				$('#ctrtStat').val('');
				$('#ctrtStat').selectmenu("refresh");
				$("#ctrtStat").selectmenu("enable");
				$("#ctrtStatDot").css("display", ""); 
			}else if(varDefn[i] =="CUST_ID"){
				$("#custId").removeClass('not-active');
				$("#custId").removeAttr('disabled');
				$("#custIdDot").css("display", ""); 
			}else if(varDefn[i] =="OPEN_BGN_DD"){
				$("#openBgnDd").removeClass('not-active');
				$("#openBgnDd").removeAttr('disabled');
				$("#openBgnDdDot").css("display", ""); 
			}else if(varDefn[i] =="OPEN_END_DD"){
				$("#openEndDd").removeClass('not-active');
				$("#openEndDd").removeAttr('disabled');
				$("#openEndDdDot").css("display", ""); 
			}else if(varDefn[i] =="SEARCH_DD"){
				$("#searchDd").removeClass('not-active');
				$("#searchDd").removeAttr('disabled');
				$("#searchDdDot").css("display", ""); 
			}
			if(varDefn.length>0){
				$("#sqlCond").show();
			}
		}	      
}
function getStmteList(){

	var url = '/customer/statistics/statisticsMgt/statisticsStmtList.json';
	var searchSoId = $('#searchSoId').val();
	$.ajax({
        url:url,
        type:'POST',
        async: false,
        data : {
      	  searchSoId : searchSoId
        	},
        dataType: 'json',
        success: function(data){
      	  $(data.statisticeList).each(function(index, item){
			     var str = '<option value="'+ item.stmtCd+'">'+ item.stmtNm+'</option>';
			     $('#searchStsCd').append(str);  
      	  });
     	//	$('#searchStsCd').val('SEL');
     		$('#searchStsCd').selectmenu("refresh");
        },
     	beforeSend: function(data){
     			
     	},
     	error : function(err){
     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
   			alert(err.responseJSON.exceptionMsg);
   		}else{
   			alert('<spring:message code="MSG.M10.MSG00005"/>');	
   		}
     	}
    });
}


function test(){
	var param = new Object();
	param.stmt = $("#stmt").val();
	param.tmout = $("#tmout").val();
	$('#head').empty();
	var url = '/customer/statistics/statisticsMgt/testSql.json';
	$.ajax({
        url:url,
        type:'POST',
        data : param,
        dataType: 'json',
        success: function(data){
			//alert(data.colNm[0].name);

			for(var i=0;i<data.colNm.length;i++){
			     var str = '<th>'+ data.colNm[i].name+'</th>';
			     $('#head').append(str);  
			 }
			$("#sqlList").show();
			sqlList();
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
     			alert(err.responseJSON.exceptionMsg);
     		}else{
     			alert('<spring:message code="MSG.M07.MSG00126"/>');	
     		}
     		
     	}
    });
}

function sqlList(){
	var param = new Object();
	param.stmt = $("#stmt").val();
	param.tmout = $("#tmout").val();
	param.soId= $("#soId").val();
	param.acntId= $("#acntId").val();
	param.strtDd= $("#strtDd").val();
	param.endDd= $("#endDd").val();
	param.svcTelNo= $("#svcTelNo").val();
	param.billBgnMth= $("#billBgnMth").val();
	param.billEndMth= $("#billEndMth").val();
	param.ctrtStat= $("#ctrtStat").val();
	param.custId= $("#custId").val();
	param.openBgnDd= $("#openBgnDd").val();
	param.openEndDd= $("#openEndDd").val();
	param.searchDd= $("#searchDd").val();

	$('#head2').empty();
	var url = '/customer/statistics/statisticsMgt/sqlList.json';

	$.ajax({
        url:url,
        type:'POST',
        data : param,
        dataType: 'json',
        success: function(data){
			for(var i=0;i<data.sqlResult.length;i++){
				var str2 = "<tr>";
				for(var j=0;j<Object.values(data.sqlResult[i]).length;j++){
					str2 = str2 + '<td>'+ Object.values(data.sqlResult[i])[j] +'</td>';
				}
				str2 = str2+"</tr>"
				$('#head2').append(str2);  
			 }
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
     			alert(err.responseJSON.exceptionMsg);
     		}else{
     			alert('<spring:message code="MSG.M07.MSG00126"/>');	
     		}
     		
     	}
    });
}
function printLog(){
	var param = new Object();
	param.searchStsCd =  $("#searchStsCd").val();
	param.stmt = $("#stmt").val();
	param.tmout = $("#tmout").val();
	param.soId= $("#soId").val();
	param.acntId= $("#acntId").val();
	param.strtDd= $("#strtDd").val();
	param.endDd= $("#endDd").val();
	param.svcTelNo= $("#svcTelNo").val();
	param.billBgnMth= $("#billBgnMth").val();
	param.billEndMth= $("#billEndMth").val();
	param.ctrtStat= $("#ctrtStat").val();
	param.custId= $("#custId").val();
	param.openBgnDd= $("#openBgnDd").val();
	param.openEndDd= $("#openEndDd").val();
	param.searchDd= $("#searchDd").val();
	
	$.download('getExcelAction.xlsx',param,'post');
}
</script>

<!--NaviGator-->
<div class="h3_bg">
	<h3>${menuName}</h3>
	<!-- Navigator -->
		<div class="nav">                                        
		   <a href="#" class="home"></a>
			<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
			<span>&gt;</span>${mu.menuName}
		</c:forEach>
		</div>                               
   <!--// Navigator -->
</div>

<!--검색-->	
<form id="statisticsMgtList" name="statisticsMgtList" method="post">
	<input type="hidden" id="varDefn" name="varDefn" />	
	<input type="hidden" id="tmout" name="tmout" />	
	<input type="hidden" id="stmt" name="stmt" />	

	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M12.LAB00012" />		
				</h4>
			</div>
			<div class="fr mt10">
			<ntels:auth auth="${menuAuthP}">		
				<a id="printBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
			</ntels:auth>
				<a href="javascript:test();" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:20%;">
			<col style="width:10%;">
			<col style="width:20%;">
			<col style="width:10%;">
			<col style="width:30%;">			
		</colgroup>
		 <thead> 
			   <tr>
					<th>
						<spring:message code="LAB.M07.LAB00003" />	
					</th>
					<td>
						<select class="w150" id="searchSoId" name="searchSoId">
							<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
								<option value="${soAuthList.so_id}" <c:if test="${serviceMgt.soId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
							</c:forEach>
						</select>             
					</td>
					<th>
						<spring:message code="LAB.M12.LAB00013" />	
					</th>
					<td colspan='5'>
						<select name="searchStsCd" id="searchStsCd" class="w270">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${statisticeList}" var="statisticeList" varStatus="status">
								<option value="${statisticeList.stmtCd}">
									${getStatisticeList.stmtNm }
								</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>		
		</thead>
	</table> 


<!--사용자부 -->

	
<!-- 검색조건 -->
<div id="sqlCond">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M09.LAB00245" />	
			</h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
		</colgroup>
		<tbody>
			<tr>
				<!-- 사업 -->
				<th><spring:message code="LAB.M07.LAB00003" /><span id="soIdDot" class="dot">*</span></th>
				<td>
						<select class="w150" id="soId" name="soId">
							<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
								<option value="${soAuthList.so_id}" <c:if test="${serviceMgt.soId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
							</c:forEach>
						</select>        
				</td>
				<!-- 납부자 -->
				<th><spring:message code="LAB.M02.LAB00018"/><span id="acntIdDot" class="dot">*</span></th>
				<td>
					<input type="text" id="acntId" name="acntId" class="w100p">
				</td>
				<!-- 조회시작일 -->
				<th><spring:message code="LAB.M09.LAB00248"/><span id="strtDdDot" class="dot">*</span></th>
				<td>
					<div class="inp_date w150">
						<input type="text" id="strtDd" name="strtDd" class="datepicker"/>
						<a href="#"  id='btnCal3' class="btn_cal"></a>
					</div>
				</td>
				<!-- 조회종료일 -->
				<th><spring:message code="LAB.M09.LAB00249"/><span id="endDdDot" class="dot">*</span></th>
				<td>
					<div class="inp_date w150">
						<input type="text" id="endDd" name="endDd" class="datepicker"/>
						<a href="#"  id='btnCal4' class="btn_cal"></a>
					</div>
				</td>
			</tr>
			<tr>
				<!-- 이동전화번호 -->
				<th><spring:message code="LAB.M08.LAB00205"/><span id="svcTelNoDot" class="dot">*</span></th>
				<td>
					<input type="text" id="svcTelNo" name="svcTelNo" class="w100p">
				</td>
				<!-- 청구년월(시작) -->
				<th><spring:message code="LAB.M10.LAB00033"/>(<spring:message code="LAB.M07.LAB00357"/>) <span id="billBgnMthDot" class="dot">*</span> </th>
				<td>
					<div class="date_box">
						<div class="inp_date w135">
							<input  type="text"  id="billBgnMth" name="billBgnMth" class="month-picker">
							<a href="#" id='btnCal' class="btn_cal"></a>
						</div>
					</div>
				</td>
				<!-- 청구년월(종료)  -->
				<th><spring:message code="LAB.M10.LAB00033"/>(<spring:message code="MSG.M09.MSG00041"/>) <span id="billEndMthDot" class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date w135">
							<input  type="text"  id="billEndMth" name="billEndMth" class="month-picker">
							<a href="#" id='btnCal1' class="btn_cal"></a>
						</div>
					</div>
				</td>
				<!-- 서비스상태 -->
				<th><spring:message code="LAB.M07.LAB00358"/><span id="ctrtStatDot" class="dot">*</span></th>
				<td>
					<select class="w150" id="ctrtStat" name="ctrtStat">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${ctrtStatList}" var="ctrtStatList" varStatus="status">
								<option value="${ctrtStatList.commonCd}">
									${ctrtStatList.commonCdNm }
								</option>
						</c:forEach>
					</select>        
				</td>
			</tr>
			<tr>
				<!-- 고객ID -->
				<th><spring:message code="LAB.M01.LAB00046"/><span id="custIdDot" class="dot">*</span></th>
				<td>
					<input type="text" id="custId" name="custId" class="w100p">
				</td>
				<!-- 가입일-->
				<th><spring:message code="LAB.M01.LAB00009"/>(<spring:message code="LAB.M07.LAB00357"/>)  <span id="openBgnDdDot" class="dot">*</span></th>
				<td>
					<div class="inp_date w150">
						<input type="text" id="openBgnDd" name="openBgnDd" class="datepicker"/>
						<a href="#"  id='btnCal5' class="btn_cal"></a>
					</div>
				</td>
				<!-- 가입일-->
				<th><spring:message code="LAB.M01.LAB00009"/>(<spring:message code="MSG.M09.MSG00041"/>) <span id="openEndDdDot" class="dot">*</span></th>
				<td>
					<div class="inp_date w150">
						<input type="text" id="openEndDd" name="openEndDd" class="datepicker" />
						<a href="#"  id='btnCal6' class="btn_cal"></a>
					</div>
				</td>
				<!-- 조회일 -->
				<th><spring:message code="LAB.M09.LAB00250"/> <span id="searchDdDot" class="dot">*</span></th>
				<td>
					<div class="inp_date w150">
						<input type="text" id="searchDd" name="searchDd" class="datepicker"/>
						<a href="#"  id='btnCal7' class="btn_cal"></a>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<div id="sqlList">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M15.LAB00107"/>
			</h4>
		</div>
	</div>
	<table class="writeview">
		<tbody>
			<tr id="head"></tr>
		</tbody>
		<tbody id="head2"></tbody>
	</table>
</div>
</form>	
<div id="pop_layer" style="height:600px;width:500px;overflow-x:none;overflow-y:auto;display:none;"></div>       




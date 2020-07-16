<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/dnUtil.js"></script>

<script type="text/javascript">

	

	$(document).ready(function() {
		
		$('#salesDivisionIdP').selectmenu({});
		$('#salesTeamIdP').selectmenu({});
		
		
		$('#salesDivisionIdP').selectmenu({
		    change: function() {
		    	changeSelect();
		    }
		});
		
		
		
		$("#btnInsertP").click(function() {
			
			if($('#salesDivisionIdP').val() == null || $('#salesDivisionIdP').val() == ''){
				alert('<spring:message code="MSG.M08.MSG00016"/>');		//영업본부를 선택해 주세요.
				return;
			}
			if($('#salesTeamIdP').val() == null || $('#salesTeamIdP').val() == ''){
				alert('<spring:message code="MSG.M08.MSG00017"/>');		//영업팀을 선택해 주세요.
				return;	
			}
			
			var rowId  = $("#deliveryInspectionTable2").jqGrid("getGridParam", "selrow" );
			var data = $("#deliveryInspectionTable2").getRowData(rowId);
			
			data.salesDivisionId = $('#salesDivisionIdP').val();
			data.salesTeamId = $('#salesTeamIdP').val();
			
			insertWearingAc(data);
			
			$("#btnClose").trigger('click');
			
		});
		
	});
	
	
	
	function changeSelect(){
		
		var rowId  = $("#deliveryInspectionTable2").jqGrid("getGridParam", "selrow" );
		var data = $("#deliveryInspectionTable2").getRowData(rowId);
		
		var param = new Object();
		param.soId = data.soId;
		param.orgId = $("#salesDivisionIdP").val();
		
		var sHtml = '';
		sHtml = sHtml + '<option value=""><spring:message code="LAB.M07.LAB00195"/></option>';
		
		if(param.orgId == ''){
			$("#salesTeamIdP").html("");
			$("#salesTeamIdP").html(sHtml);
			$("#salesTeamIdP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			
		}else{
			
			$.ajax({
				url : 'selectSalesTeam.json',
				type : 'POST',
				data : param,
				success : function(data) {
					
					$.each(data.salesTeam, function(index,item){
						sHtml = sHtml + '<option value="'+item.orgId+'">'+item.orgNm+'</option>';						
					}); 
					
					$("#salesTeamIdP").html("");
					$("#salesTeamIdP").html(sHtml);
					$("#salesTeamIdP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
			
		}
		
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M08.LAB00161"/><!-- 입고승인 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M08.LAB00161"/><!-- 입고승인 --></h4>
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
				<th><spring:message code="LAB.M08.LAB00026"/><!-- 영업본부 --></th>
				<td>
					<select id="salesDivisionIdP" name="salesDivisionIdP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${salesDivision}" var="salesDivision" varStatus="status">
							<option value="${salesDivision.orgId}">${salesDivision.orgNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M08.LAB00027"/><!-- 영업팀 --></th>
				<td>
					<select id="salesTeamIdP" name="salesTeamIdP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${salesTeam}" var="salesTeam" varStatus="status">
							<option value="${salesTeam.orgId}">${salesTeam.orgNm}</option>
						</c:forEach>
					</select>                                          
				</td>
			</tr>
			
			
		</thead>
	</table> 
</div>
<!--// center -->

<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsertP"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
	
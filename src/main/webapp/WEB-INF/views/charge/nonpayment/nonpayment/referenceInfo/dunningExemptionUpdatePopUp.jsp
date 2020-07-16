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
		
		setData();
	});
	
	function setData(){
		var rowId  = $("#dunningExemptionTable").jqGrid("getGridParam", "selrow" );
		var data = $("#dunningExemptionTable").getRowData(rowId);
		
		var param = new Object();
		param.soId = data.soId;
		param.ctrtId = data.ctrtId;
		param.upymMngTp = data.upymMngTp;
		param.excpStrtYymmdd = data.excpStrtYymmdd;
		param.excpEndYymmdd = data.excpEndYymmdd;
		
		$.ajax({
			url : 'selectDunningExemptionInfo.json',
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
					$("#upymExcpRsnUpp").val(dunningInfo.upymExcpRsnCd);
					//$("#upymExcpRsnUpp").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					$("#custIdUpp").val(dunningInfo.custId);
					$("#custNmUpp").val(dunningInfo.custNm);
					$("#ctrtIdUpp").val(dunningInfo.ctrtId);
					$("#pymCustIdUpp").val(dunningInfo.pymCustId);
					$("#pymAcntIdUpp").val(dunningInfo.pymAcntId);
					$("#excpStrtYymmddUpp").val(dunningInfo.excpStrtYymmdd);
					$("#excpEndYymmddUpp").val(dunningInfo.excpEndYymmdd);
					$("#excpCtUpp").val(dunningInfo.excpCt);
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
		url = "updateDunningExemptionInfo.json";
		
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

		param.soId = $("#soIdUpp").val();
		param.upymMngTp = $("#upymMngTpUpp").val();
		param.upymExcpRsnCd = $("#upymExcpRsnUpp").val();
		param.custId = $("#custIdUpp").val();
		param.ctrtId = $("#ctrtIdUpp").val();
		param.pymCustId = $("#pymCustIdUpp").val();
		param.pymAcntId = $("#pymAcntIdUpp").val();
		
		var vExcpStrtYymmdd = $("#excpStrtYymmddUpp").val();
		vExcpStrtYymmdd = vExcpStrtYymmdd.replace("-","").replace("-","");
		param.excpStrtYymmdd = vExcpStrtYymmdd;
		
		var vExcpEndYymmdd = $("#excpEndYymmddUpp").val();
		vExcpEndYymmdd = vExcpEndYymmdd.replace("-","").replace("-","");
		param.excpEndYymmdd = vExcpEndYymmdd;
		
		param.excpCt = $("#excpCtUpp").val();
		
		return param;
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00157"/><!-- 조치제외정보 --></div>
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
			<col style="width:14%;" />
			<col style="width:10%;" />
			<col style="width:14%;" />
			<col style="width:16%;" />
			<col style="width:10%;" />
			<col style="width:36%;" />
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td colspan="5">
					<input type="text" id="soNmUpp" name="soNmUpp" class="w150" disabled="disabled" />
					<input type="hidden" id="soIdUpp" name="soIdUpp" disabled="disabled" />
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
				<th><spring:message code="LAB.M09.LAB00095"/><!-- 제외사유 --></th>
				<td colspan="3">
					<select id="upymExcpRsnUpp" name="upymExcpRsnUpp" class="w180" disabled="disabled">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${dunningTpDtlCd}" var="dunningTpDtlCd" varStatus="status">
							<option value="${dunningTpDtlCd.commonCd}">${dunningTpDtlCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00045"/><!-- 고객 --></th>
				<td>
					<input type="text" id="custNmUpp" name="custNmUpp" class="w100" disabled="disabled" />
					<input type="hidden" id="custIdUpp" name="custIdUpp" class="w100" />
				</td>
				<th><spring:message code="LAB.M01.LAB00032"/><!-- 계약ID --></th>
				<td>
					<input type="text" id="ctrtIdUpp" name="ctrtIdUpp" class="w100" disabled="disabled" />
				</td>
				<th><spring:message code="LAB.M02.LAB00012"/><!-- 납부고객/계정ID --></th>
				<td>
					<input type="text" id="pymCustIdUpp" name="pymCustIdUpp" class="w100" disabled="disabled" />
					/
					<input type="text" id="pymAcntIdUpp" name="pymAcntIdUpp" class="w100" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00096"/><!-- 제외시작일 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w170">
							<input type="text" id="excpStrtYymmddUpp" name="excpStrtYymmddUpp" class="datepicker" disabled="disabled" />
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00097"/><!-- 제외종료일 --></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date w170">
							<input type="text" id="excpEndYymmddUpp" name="excpEndYymmddUpp" class="datepicker" disabled="disabled" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00094"/><!-- 제외내역상세 --></th>
				<td colspan="5">
					<input type="text" id="excpCtUpp" name="excpCtUpp" class="w800" />
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


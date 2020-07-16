<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	$(document).ready(function() {
	
		settingDatepicker();	//팝업달력 셋팅
		
		//날짜 세팅
		$("#appyEndDtInP").val(stringToDateformatYYYYMMDD("99991231"));
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#appyStrtDtInP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		
		//셀렉트 박스 세팅
		$('#relTpCdInP').selectmenu({});
		
		//저장버튼
		$("#btnInsert").click(function() {
			insertData();            
		});
		
		//조직검색 팝업
		$("#btnOrgPopInP").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=partOrgIdInP&returnId2=partOrgNmInP&popType2=01";
					
			//param.popType2 = "01";				//01 - 리터받는값에 유형, 상세유형, 조직레벨도 보내줌
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		
	});
	
	
	
	//등록
	function insertData(){
		
		var param = checkValidation();
		console.log(param);
		if(param != false){
			
			$.ajax({
				url : 'insertOrganizationRelHist.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						ajaxDetail();	//새로고침
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
		
		var relTpCdInP = $("#relTpCdInP").val();		//관계유형
		if(relTpCdInP == ''){
			//관계유형을 선택해 주세요
			alert('<spring:message code="LAB.M01.LAB00160" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var appyStrtDtInP = $.trim( $("#appyStrtDtInP").val() );		//적용시작일
		if(appyStrtDtInP == ''){
			//적용시작일을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var appyEndDtInP = $.trim( $("#appyEndDtInP").val() );			//적용종료일
		if(appyEndDtInP == ''){
			//적용종료일을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00058" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var partOrgIdInP = $("#partOrgIdInP").val();		//상대조직아이디
		if(partOrgIdInP == ''){
			// 상대조직을 등록해 주세요
			alert('<spring:message code="LAB.M07.LAB00097" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		
		var soIdInP = $("#soIdInP").val();				//사업ID
		var soNmInP = $("#soNmInP").val();				//사업명
		var orgIdInP = $("#orgIdInP").val();			//조직ID
		var orgNmInP = $("#orgNmInP").val();			//조직명
		var inqPermCdInP = $("#inqPermCdInP").val();	//조회권한코드
		var orgLvCdInP = $("#orgLvCdInP").val();		//조직레벨코드
		
		if(orgIdInP == partOrgIdInP){
			alert('조직아이디와 상위 조직 아이디는 달라야 합니다. ');
			return false;
		}
		
		param.soId = soIdInP;			//사업
		param.soNm = soNmInP;			//사업
		param.orgId = orgIdInP;			//조직아이디
		param.orgNm = orgNmInP;			//조직명
		param.relTpCd = relTpCdInP;		//관계유형
		param.appyStrtDt = dateFormatToStringYYYYMMDD(appyStrtDtInP);		//적용시작일
		param.appyEndDt = dateFormatToStringYYYYMMDD(appyEndDtInP);		//적용종료일
		param.partOrgId = partOrgIdInP;		//상대조직ID
		param.inqPermCd = inqPermCdInP;		//지역분류
		param.orgLvCd = orgLvCdInP;		//지역구분
		
		return param;
	}
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00142"/><!-- 조직관계등록 --></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00142"/><!-- 조직관계등록 --></h4>
		</div>
	</div>
	
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td>
					<input type="hidden" id="soIdInP" name="soIdInP" value="${organizationVO.soId }" />
					<input type="hidden" id="inqPermCdInP" name="inqPermCdInP" value="${organizationVO.inqPermCd }" />
					<input type="hidden" id="orgLvCdInP" name="orgLvCdInP" value="${organizationVO.orgLvCd }" />
					<input type="text" id="soNmInP" name="soNmInP" value="${organizationVO.soNm }" class="w305 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --></th>
				<td>
					<input type="text" id="orgIdInP" name="orgIdInP" value="${organizationVO.orgId }" class="w150 not-active" disabled="disabled" />
					<input type="text" id="orgNmInP" name="orgNmInP" value="${organizationVO.orgNm }" class="w150 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00160"/><!-- 관계유형 --><span class="dot">*</span></th>
				<td>
					<select class="w305" id="relTpCdInP" name="relTpCdInP" >
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${relTpCd}" var="relTpCd" varStatus="status">
							<option value="${relTpCd.commonCd}">${relTpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00056"/><!-- 적용일자 --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date w140">
							<input type="text" id="appyStrtDtInP" name="appyStrtDtInP" class="datepicker" />
							<a href="#" class="btn_cal"></a>
						</div>
						<span class="dash">~</span>
						<div class="inp_date w140">
							<input type="text" id="appyEndDtInP" name="appyEndDtInP" class="datepicker not-active" disabled="disabled" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00097"/><!-- 상대조직ID --><span class="dot">*</span></th>
				<td>
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="partOrgIdInP" name="partOrgIdInP" class="p100 not-active" disabled="disabled" />
							<a href="#" id="btnOrgPopInP" class="search">search</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00099"/><!-- 상대조직명 --></th>
				<td>
					<input type="text" id="partOrgNmInP" name="partOrgNmInP" class="w305 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00096"/><!-- 상대유형 --></th>
				<td>
					<input type="text" id="partTpCdInP" name="partTpCdInP" class="w150 not-active" disabled="disabled" />
					<input type="text" id="partTpNmInP" name="partTpNmInP" class="w150 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00095"/><!-- 상대상세유형 --></th>
				<td>
					<input type="text" id="partTpDtlCdInP" name="partTpDtlCdInP" class="w150 not-active" disabled="disabled" />
					<input type="text" id="partTpDtlNmInP" name="partTpDtlNmInP" class="w150 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00098"/><!-- 상대조직레벨 --></th>
				<td>
					<input type="text" id="partOrgLvIdInP" name="partOrgLvIdInP" class="w150 not-active" disabled="disabled" />
					<input type="text" id="partOrgLvNmInP" name="partOrgLvNmInP" class="w150 not-active" disabled="disabled" />
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
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
		$("#appyStrtDtUpP").val(stringToDateformatYYYYMMDD("${organizationVO.appyStrtDt }"));
		$("#appyEndDtUpP").val(stringToDateformatYYYYMMDD("${organizationVO.appyEndDt }"));
		
		//셀렉트 박스 세팅 relTpCd
		$('#relTpCdUpP').selectmenu({});
		$('#relTpCdUpP').val("${organizationVO.relTpCd }");
		$("#relTpCdUpP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		$('#relTpCdUpP').selectmenu("disable");
		
		//저장버튼
		$("#btnInsert").click(function() {
			updateData();            
		});
		
		//조직검색 팝업
		$("#btnOrgPopUpP").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=partOrgIdUpP&returnId2=partOrgNmUpP&popType2=02";
					
			//param.popType2 = "01";				//01 - 리터받는값에 유형, 상세유형, 조직레벨도 보내줌
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
	});
	
	//수정
	function updateData(){
		
		var param = checkValidation();
		console.log(param);
		if(param != false){
			
			$.ajax({
				url : 'updateOrganizationRelHist.json',
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
		
		var relTpCdUpP = $("#relTpCdUpP").val();		//관계유형
		if(relTpCdUpP == ''){
			//관계유형을 선택해 주세요
			alert('<spring:message code="LAB.M01.LAB00160" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var appyStrtDtUpP = $.trim( $("#appyStrtDtUpP").val() );		//적용시작일
		if(appyStrtDtUpP == ''){
			//적용시작일을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var appyEndDtUpP = $.trim( $("#appyEndDtUpP").val() );			//적용종료일
		if(appyEndDtUpP == ''){
			//적용종료일을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00058" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var partOrgIdUpP = $("#partOrgIdUpP").val();		//상대조직아이디
		if(partOrgIdUpP == ''){
			// 상대조직을 등록해 주세요
			alert('<spring:message code="LAB.M07.LAB00097" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		
		var soIdUpP = $("#soIdUpP").val();				//사업ID
		var soNmUpP = $("#soNmUpP").val();				//사업명
		var orgIdUpP = $("#orgIdUpP").val();			//조직ID
		var orgNmUpP = $("#orgNmUpP").val();			//조직명
		var inqPermCdUpP = $("#inqPermCdUpP").val();	//조회권한코드
		var orgLvCdUpP = $("#orgLvCdUpP").val();		//조직레벨코드
		
		param.soId = soIdUpP;			//사업
		param.soNm = soNmUpP;			//사업
		param.orgId = orgIdUpP;			//조직아이디
		param.orgNm = orgNmUpP;			//조직명
		param.relTpCd = relTpCdUpP;		//관계유형
		param.appyStrtDt = dateFormatToStringYYYYMMDD(appyStrtDtUpP);		//적용시작일
		param.appyEndDt = dateFormatToStringYYYYMMDD(appyEndDtUpP);		//적용종료일
		param.partOrgId = partOrgIdUpP;		//상대조직ID
		param.inqPermCd = inqPermCdUpP;		//지역분류
		param.orgLvCd = orgLvCdUpP;		//지역구분
		
		return param;
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00143"/><!-- 조직관계수정 --></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00143"/><!-- 조직관계수정 --></h4>
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
					<input type="hidden" id="soIdUpP" name="soIdUpP" value="${organizationVO.soId }" />
					<input type="hidden" id="inqPermCdUpP" name="inqPermCdUpP" value="${organizationVO.inqPermCd }" />
					<input type="hidden" id="orgLvCdUpP" name="orgLvCdUpP" value="${organizationVO.orgLvCd }" />
					<input type="text" id="soNmUpP" name="soNmUpP" value="${organizationVO.soNm }" class="w305 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --></th>
				<td>
					<input type="text" id="orgIdUpP" name="orgIdUpP" value="${organizationVO.orgId }" class="w150 not-active" disabled="disabled" />
					<input type="text" id="orgNmUpP" name="orgNmUpP" value="${organizationVO.orgNm }" class="w150 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00160"/><!-- 관계유형 --><span class="dot">*</span></th>
				<td>
					<select class="w305" id="relTpCdUpP" name="relTpCdUpP" disabled="disabled" >
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
							<input type="text" id="appyStrtDtUpP" name="appyStrtDtUpP" class="datepicker" />
							<a href="#" class="btn_cal"></a>
						</div>
						<span class="dash">~</span>
						<div class="inp_date w140">
							<input type="text" id="appyEndDtUpP" name="appyEndDtUpP" class="datepicker not-active" disabled="disabled" />
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
							<input type="text" id="partOrgIdUpP" name="partOrgIdUpP" value="${uppData.orgId }" class="p100 not-active" disabled="disabled" />
							<a href="#" id="btnOrgPopUpP" class="search">search</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00099"/><!-- 상대조직명 --></th>
				<td>
					<input type="text" id="partOrgNmUpP" name="partOrgNmUpP" value="${uppData.orgNm }" class="w305 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00096"/><!-- 상대유형 --></th>
				<td>
					<input type="text" id="partTpCdUpP" name="partTpCdUpP" value="${uppData.tpCd }" class="w150 not-active" disabled="disabled" />
					<input type="text" id="partTpNmUpP" name="partTpNmUpP" value="${uppData.tpNm }" class="w150 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00095"/><!-- 상대상세유형 --></th>
				<td>
					<input type="text" id="partTpDtlCdUpP" name="partTpDtlCdUpP" value="${uppData.tpDtlCd }" class="w150 not-active" disabled="disabled" />
					<input type="text" id="partTpDtlNmUpP" name="partTpDtlNmUpP" value="${uppData.tpDtlNm }" class="w150 not-active" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00098"/><!-- 상대조직레벨 --></th>
				<td>
					<input type="text" id="partOrgLvIdUpP" name="partOrgLvIdUpP" value="${uppData.orgLvCd }" class="w150 not-active" disabled="disabled" />
					<input type="text" id="partOrgLvNmUpP" name="partOrgLvNmUpP" value="${uppData.orgLvNm }" class="w150 not-active" disabled="disabled" />
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
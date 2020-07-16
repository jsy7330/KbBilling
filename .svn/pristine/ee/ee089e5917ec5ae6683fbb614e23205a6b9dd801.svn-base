<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
	
		getOrgNm();
		
		initGrid();
		
		ajaxDetail();
		
		//조회버튼
		$("#btnSearch").click(function() {
			ajaxDetail();            
		});
		
		//등록버튼
		$("#btn_insert").click(function() {
			var url="organizationRelHistInsertPopUp.ajax";
			var param = new Object();
			param.soId = $("#soId").val();
			param.soNm = $("#soNm").val();
			param.orgId = $("#orgId").val();
			param.orgNm = $("#orgNm").val();
			param.inqPermCd = $("#inqPermCd").val();	//조회권한
			param.orgLvCd = $("#orgLvCd").val();		//조직레벨코드
			
			openModalPopup(url, param);
		});
		
		//수정버튼
		$("#btn_update").click(function() {
			var url="organizationRelHistUpdatePopUp.ajax";
			var param = new Object();
			param.soId = $("#soId").val();
			param.soNm = $("#soNm").val();
			param.orgId = $("#orgId").val();
			param.orgNm = $("#orgNm").val();
			param.inqPermCd = $("#inqPermCd").val();	//조회권한
			param.orgLvCd = $("#orgLvCd").val();		//조직레벨코드
			param.relTpCd = $("#relTpCd").val();		//관계유형코드
			param.appyStrtDt = dateFormatToStringYYYYMMDD( $("#appyStrtDt").val() );	//개설일자
			param.appyEndDt = dateFormatToStringYYYYMMDD( $("#appyEndDt").val() );	//폐점일자
			
			openModalPopup(url, param);
		});
		
		//삭제버튼
		$('#btn_delete').addClass('white-btn');
		$('#btn_delete').removeClass('grey-btn');
		$('#btn_delete').addClass('not-active');
		$('#btn_delete').attr('disabled',true);
		/* 
		$("#btn_delete").click(function() {
			deleteOrgRelHist();            
		});
		 */
		
		//조직검색 팝업
		$("#searchOrgPopup").click(function() {
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			var param = new Object();
			param.orgId = $("#searchOrgId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchOrgId";	//리턴받는 orgId 의 태그아이디값
			param.returnId2 = "searchOrgNm";	//리턴받는 orgNm 의 태그아이디값
			
			openModalPopup(url, param);
		});
		
		//초기화버튼
		$('#btn_init').addClass('white-btn');
		$('#btn_init').removeClass('grey-btn');
		$('#btn_init').addClass('not-active');
		$('#btn_init').attr('disabled',true);
		/* 
		$("#btn_init").click(function() {
			$("#searchOrgId").val("${session_user.orgId}");
			getOrgNm();
		});
		 */
	});
	
	
	function initGrid() {
		
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		
		$("#organizationRelTable").jqGrid({
			
		   	url:'/distributor/distributor/distributorMgt/organization/organizationRelListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[
		   		{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100},
		   		{label:'<spring:message code="LAB.M01.LAB00160" />',name:'relTpNm', width:100},
		   		{label:'<spring:message code="LAB.M09.LAB00052" />',name:'appyStrtDt', width:100, align:'center', formatter: stringTypeFormatterYYYYMMDD},
		   		{label:'<spring:message code="LAB.M09.LAB00058" />',name:'appyEndDt', width:100, align:'center', formatter: stringTypeFormatterYYYYMMDD},
		   		{label:'<spring:message code="LAB.M07.LAB00097" />',name:'partOrgId', width:100},
		   		{label:'<spring:message code="LAB.M07.LAB00099" />',name:'partOrgNm', width:100},
		   		{label:'<spring:message code="LAB.M07.LAB00096" />',name:'partTpNm', width:100},
		   		{label:'<spring:message code="LAB.M07.LAB00095" />',name:'partTpDtlNm', width:100},
		   		{label:'<spring:message code="LAB.M07.LAB00098" />',name:'partOrgLvNm', width:100}
		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#pager3',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 100,					//화면 상태에 따라 크기 조절 할 것
			loadComplete: function(obj){
				$("#organizationRelTable").setGridWidth($('#gridDiv1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			},
			
			sortable: { update: function(permutation) {
	        	$("#organizationRelTable").setGridWidth($('#gridDiv1').width(),false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
			
		});
		
		$("#organizationRelTable").setGridWidth($('#gridDiv1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#organizationRelTable").setGridWidth($('#gridDiv1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function ajaxDetail(){

		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		
		$.ajax({
			url : 'getOrganizationDetailAction.json',
			type : 'POST',
			data : param,
			success : function(data) {
				viewDetail(data);
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
		$("#organizationRelTable").clearGridData();  // 이전 데이터 삭제
        $("#organizationRelTable").setGridParam({ postData: param }).trigger("reloadGrid");
		
	}
	
	function viewDetail(data){
		
		var organizationRelHistVO = data.organizationRelHistVO;
		
		
		if(organizationRelHistVO != null){
			$("#soId").val(organizationRelHistVO.soId);					//사업ID
			$("#soNm").val(organizationRelHistVO.soNm);					//사업명
			$("#orgId").val(organizationRelHistVO.orgId);				//조직ID
			$("#orgNm").val(organizationRelHistVO.orgNm);				//조직명
			$("#busiTpNm").val(organizationRelHistVO.busiTpNm);			//사업자구분
			$("#privTpNm").val(organizationRelHistVO.privTpNm);			//자사구분
			$("#borNo").val(organizationRelHistVO.borNo);				//사업자등록번호
			$("#corpRegNo").val(organizationRelHistVO.corpRegNo);		//법인등록번호
			$("#busiTp").val(organizationRelHistVO.busiTp);				//업종
			$("#busiCndt").val(organizationRelHistVO.busiCndt);			//업태
			$("#repNm").val(organizationRelHistVO.repNm);				//대표자명
			$("#repRno").val(organizationRelHistVO.repRno);				//대표자주민등록번호
			$("#eml").val(organizationRelHistVO.eml);					//이메일
			$("#hmpg").val(organizationRelHistVO.hmpg);					//홈페이지
			$("#telNo").val(organizationRelHistVO.telNo);				//전화번호
			$("#faxNo").val(organizationRelHistVO.faxNo);				//팩스번호
			$("#appyStrtDt").val(stringToDateformatYYYYMMDD(organizationRelHistVO.appyStrtDt));	//적용시작일
			$("#appyEndDt").val(stringToDateformatYYYYMMDD(organizationRelHistVO.appyEndDt));	//적용종료일
			$("#tpNm").val(organizationRelHistVO.tpNm);					//유형코드명
			$("#tpDtlNm").val(organizationRelHistVO.tpDtlNm);			//유형상세코드명
			$("#inqPermNm").val(organizationRelHistVO.inqPermNm);		//조회권한명
			$("#orgLvNm").val(organizationRelHistVO.orgLvNm);			//조직레벨코드명
			$("#arClNm").val(organizationRelHistVO.arClNm);				//지역분류
			$("#arTpNm").val(organizationRelHistVO.arTpNm);				//지역구분
			$("#relTpCd").val(organizationRelHistVO.relTpCd);			//관계유형코드
			
			$("#inqPermCd").val(organizationRelHistVO.inqPermCd);		//조회권한코드
			$("#orgLvCd").val(organizationRelHistVO.orgLvCd);			//조직레벨코드
		}else{
			$("#areaView input").val("");
		}
		
	}

	function deleteOrgRelHist(){
		
		var param = new Object();
		param.orgId = $("#orgId").val();
		
		var returnCon = confirm("삭제 하시겠습니까?");
		
		if(returnCon){
			$.ajax({
				url : 'deleteOrganizationRelHist.json',
				type : 'POST',
				data : param,
				success : function(data) {
					if(data.result != "0"){
						alert('삭제되었습니다.');	//삭제되었습니다.
						
						initGrid();
						
						ajaxDetail();
						
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
	
	//사업명 불러 오기
	function getOrgNm(){
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		
		$.ajax({
			url : '/distributor/distributor/distributorMgt/organization/getOrganizationListAction.json',
			type : 'POST',
			data : param,
			success : function(data) {
				
				if(data.organizationData != null){
					$("#searchOrgNm").val(data.organizationData.orgNm);	
				}else{
					$("#searchOrgNm").val("");
				}
				
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
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


<div class="main_btn_box">
	<div class="fr mt10">
		<a href="#" id="btnSearch" class="grey-btn" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
	</div>
</div>

<table class="writeview">
	<colgroup>
		<col style="width:15%;">
		<col style="width:85%;">
	</colgroup>
	<thead> 
		<tr>
			<th  title="latest" ><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --></th>
			<td>
				<div class="date_box">
					<div class="inp_date p100">
						<input type="text" id="searchOrgId" name="searchOrgId" value="${session_user.orgId}" maxlength="10" class="p100">
						<a href="#" id="searchOrgPopup" name="searchOrgPopup" class="search">search</a>
					</div> 
					<div class="inp_date w80p">
						<input type="text" id="searchOrgNm" name="searchOrgNm" class="w370 not-active" disabled="disabled">
					</div>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00149"/><!-- 조직상세정보 --></h4>
	</div>
</div>

<table class="writeview" id="areaView" >
	<colgroup>
		<col style="width:15%;">
		<col style="width:35%;">
		<col style="width:15%;">
		<col style="width:35%;">
	</colgroup>
	<tbody> 
		<tr>
			<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
			<td>
				<input id="soNm" name="soNm" type="text"  class="w270 not-active" disabled="disabled" />
				<input id="soId" name="soId" type="hidden" />
				<input id="relTpCd" name="relTpCd" type="hidden" /><!-- 관계유형코드 -->
				
			</td>
			<th><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --></th>
			<td>
				<input id="orgId" name="orgId" type="text"  class="w130 not-active" disabled="disabled" />
				/
				<input id="orgNm" name="orgNm" type="text"  class="w130 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00011"/><!-- 사업자구분 --></th>
			<td>
				<input id="busiTpNm" name="busiTpNm" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M09.LAB00003"/><!-- 자사구분 --></th>
			<td>
				<input id="privTpNm" name="privTpNm" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00012"/><!-- 사업자등록번호 --></th>
			<td>
				<input id="borNo" name="borNo" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M06.LAB00053"/><!-- 법인등록번호 --></th>
			<td>
				<input id="corpRegNo" name="corpRegNo" type="text" class="w270 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00011"/><!-- 업종 --></th>
			<td>
				<input id="busiTp" name="busiTp" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M08.LAB00012"/><!-- 업태 --></th>
			<td>
				<input id="busiCndt" name="busiCndt" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M03.LAB00063"/><!-- 대표자명 --></th>
			<td>
				<input id="repNm" name="repNm" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M03.LAB00064"/><!-- 대표자주민등록번호 --></th>
			<td>
				<input id="repRno" name="repRno" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00119"/><!-- 이메일 --></th>
			<td>
				<input id="eml" name="eml" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M14.LAB00062"/><!-- 홈페이지 --></th>
			<td>
				<input id="hmpg" name="hmpg" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00065"/><!-- 전화번호 --></th>
			<td>
				<input id="telNo" name="telNo" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M13.LAB00015"/><!-- 팩스번호 --></th>
			<td>
				<input id="faxNo" name="faxNo" type="text"  class="w270 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M01.LAB00015"/><!-- 개설일자 --></th>
			<td>
				<div class="inp_date w265">
					<input id="appyStrtDt" name="appyStrtDt"  type="text"  class="datepicker not-active" disabled="disabled"  />
					<a href="#" class="btn_cal"></a> 
				</div>
			</td>
			<th><spring:message code="LAB.M13.LAB00019"/><!-- 폐점일 --></th>
			<td>
				<div class="inp_date w265">
					<input id="appyEndDt" name="appyEndDt"  type="text"  class="datepicker not-active" disabled="disabled"  />
					<a href="#" class="btn_cal"></a> 
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00103"/><!-- 유형 --></th>
			<td>
				<input id="tpNm" name="tpNm" type="text"  class="w270 not-active" disabled="disabled"  />
			</td>
			<th><spring:message code="LAB.M08.LAB00105"/><!-- 유형상세 --></th>
			<td>
				<input id="tpDtlNm" name="tpDtlNm" type="text"  class="w270 not-active" disabled="disabled"  />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00160"/><!-- 조회권한 --></th>
			<td>
				<input id="inqPermNm" name="inqPermNm" type="text"  class="w270 not-active" disabled="disabled"  />
				<input id="inqPermCd" name="inqPermCd" type="hidden"/>
			</td>
			<th><spring:message code="LAB.M09.LAB00146"/><!-- 조직레벨 --></th>
			<td>
				<input id="orgLvNm" name="orgLvNm" type="text"  class="w270 not-active" disabled="disabled"  />
				<input id="orgLvCd" name="orgLvCd" type="hidden" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00201"/><!-- 지역분류 --></th>
			<td>
				<input id="arClNm" name="arClNm" type="text"  class="w270 not-active" disabled="disabled"  />
			</td>
			<th><spring:message code="LAB.M09.LAB00200"/><!-- 지역구분 --></th>
			<td>
				<input id="arTpNm" name="arTpNm" type="text"  class="w270 not-active" disabled="disabled"  />
			</td>
		</tr>
	</tbody>
</table>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00144"/><!-- 조직관계이력정보 --></h4>
	</div>
</div>

<div id='gridDiv1'>
	<table id="organizationRelTable" class="w100p"></table>
	<div id="pager3"></div>
</div>

<div class="main_btn_box">
	<div class="fr">
		<!--초기화-->		
		<a class="grey-btn" href="#" id="btn_init"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
		<!--등록-->
		<ntels:auth auth="${menuAuthC}">
			<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>		
		<!--수정-->
		<ntels:auth auth="${menuAuthU}">
			<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		<!--삭제-->
		<ntels:auth auth="${menuAuthD}">
			<a class="grey-btn" href="#" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
		<%-- 
		<!--출력-->
		<ntels:auth auth="${menuAuthP}">
			<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
		 --%>
	</div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>


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
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();
			
			inputClear();
		});
		
		
		//수정버튼
		$("#btn_update").click(function() {
			
			var rowId  = $("#pointContactTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M07.MSG00106"/>');	//수정할 조직을 선택해 주세요.
				return;
			}
			
			var url="pointContactUpdatePopUp.ajax";
			var param = new Object();
			
			$("#popup_dialog").css("width", 800);
			openModalPopup(url, param);
		});
		
		
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
		
	});
	
	function initGrid() {
		
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		$("#pointContactTable").jqGrid({
			
		   	url:'pointContactListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'busiTpCd' , name: 'busiTpCd', hidden:true,width : 0},
				{ label: 'busiTpNm' , name: 'busiTpNm', hidden:true,width : 0},
				{ label: 'borNo' , name: 'borNo', hidden:true,width : 0},
				{ label: 'corpRegNo' , name: 'corpRegNo', hidden:true,width : 0},
				{ label: 'busiTp' , name: 'busiTp', hidden:true,width : 0},
				{ label: 'busiCndt' , name: 'busiCndt', hidden:true,width : 0},
				{ label: 'repRno' , name: 'repRno', hidden:true,width : 0},
				{ label: 'hmpg' , name: 'hmpg', hidden:true,width : 0},
				{ label: 'faxNo' , name: 'faxNo', hidden:true,width : 0},
				{ label: 'eml' , name: 'eml', hidden:true,width : 0},
				{ label: 'zipCd' , name: 'zipCd', hidden:true,width : 0},
				{ label: 'addr1' , name: 'addr1', hidden:true,width : 0},
				{ label: 'addr2' , name: 'addr2', hidden:true,width : 0},
				{ label: 'onlnTpCd' , name: 'onlnTpCd', hidden:true,width : 0},
				{ label: 'onlyTpNm' , name: 'onlyTpNm', hidden:true,width : 0},
				{ label: 'orgStatCd' , name: 'orgStatCd', hidden:true,width : 0},
				{ label: 'orgStatNm' , name: 'orgStatNm', hidden:true,width : 0},
				{ label: 'termResn' , name: 'termResn', hidden:true,width : 0},

	   		    { label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'soNm', width : 100},				//사업
	   		 	{ label: '<spring:message code="LAB.M03.LAB00042"/>', name: 'partOrgId', width : 100},		//대리점ID
	   			{ label: '<spring:message code="LAB.M03.LAB00043"/>', name: 'partOrgNm', width : 100},		//대리점명
	   		 	{ label: '<spring:message code="LAB.M09.LAB00079"/>', name: 'orgId', width : 100},			//접점ID
	   			{ label: '<spring:message code="LAB.M09.LAB00080"/>', name: 'orgNm', width : 100},			//접점명
	   			{ label: '<spring:message code="LAB.M03.LAB00063"/>', name: 'repNm', width : 100},		//대표자명
	   			{ label: '<spring:message code="LAB.M09.LAB00065"/>', name: 'telNo', width : 100},		//전화번호
	   			{ label: '<spring:message code="LAB.M01.LAB00015"/>', name: 'appyStrtDt', formatter:stringTypeFormatterYYYYMMDD, align:"center", width : 100},		//개설일자
	   			{ label: '<spring:message code="LAB.M13.LAB00019"/>', name: 'appyEndDt', formatter:stringTypeFormatterYYYYMMDD, align:"center", width : 100},		//폐점일
	   			
	   			
	   		],
	   		
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#pointContactPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 240,					//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowid, index, contents, event){
	        	setSelectedDate(rowid);
	        },
	        loadComplete: function(obj){
	        	$("#pointContactTable").setGridWidth($('#pointContactGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        },
	        sortable: { update: function(permutation) {
	        	$("#pointContactTable").setGridWidth($('#pointContactGrid').width(),false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
		});
		
		$("#pointContactTable").setGridWidth($('#pointContactGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#pointContactTable").setGridWidth($('#pointContactGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	function searchData(){
		
		$("#pointContactTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		
        $("#pointContactTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function setSelectedDate(rowId) {
		
		var data = $("#pointContactTable").getRowData(rowId);
		$("#soNm").val(data.soNm);
		$("#soId").val(data.soId);
		$("#partOrgId").val(data.partOrgId);
		$("#partOrgNm").val(data.partOrgNm);
		$("#orgId").val(data.orgId);
		$("#orgNm").val(data.orgNm);
		$("#busiTpNm").val(data.busiTpNm);
		$("#busiTpCd").val(data.busiTpCd);
		$("#onlyTpNm").val(data.onlyTpNm);
		$("#onlnTpCd").val(data.onlnTpCd);
		$("#borNo").val(data.borNo);
		$("#corpRegNo").val(data.corpRegNo);
		$("#busiTp").val(data.busiTp);
		$("#busiCndt").val(data.busiCndt);
		$("#repNm").val(data.repNm);
		$("#repRno").val(data.repRno);
		$("#eml").val(data.eml);
		$("#hmpg").val(data.hmpg);
		$("#telNo").val(data.telNo);
		$("#faxNo").val(data.faxNo);
		$("#zipCd").val(data.zipCd);
		$("#addr1").val(data.addr1);
		$("#addr2").val(data.addr2);
		$("#appyStrtDt").val(data.appyStrtDt);
		$("#appyEndDt").val(data.appyEndDt);
		$("#orgStatNm").val(data.orgStatNm);
		$("#orgStatCd").val(data.orgStatCd);
		$("#termResn").val(data.termResn);
		
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
	
	function inputClear(){
		$("#areaInfo01 input").val("");
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
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00081"/><!-- 접점목록 --></h4>
	</div>
</div>

<div id="pointContactGrid">
	<table id="pointContactTable" class="w100p"></table>
	<div id="pointContactPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00087"/><!-- 접점상세정보 --></h4>
	</div>
</div>

<table class="writeview" id="areaInfo01" >
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
				<input type="text" id="soNm" name="soNm" class="w270" disabled="disabled" />
				<input type="hidden" id="soId" name="soId" class="w270" />
			</td>
			<th><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --></th>
			<td>
				<input type="text" id="partOrgId" name="partOrgId" class="w130" disabled="disabled" />
				/
				<input type="text" id="partOrgNm" name="partOrgNm" class="w130" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00079"/><!-- 접점ID --></th>
			<td>
				<input type="text" id="orgId" name="orgId" class="w270" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M09.LAB00080"/><!-- 접점명 --></th>
			<td>
				<input type="text" id="orgNm" name="orgNm" class="w270" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00011"/><!-- 사업자구분 --></th>
			<td>
				<input type="text" id="busiTpNm" name="busiTpNm" class="w270" disabled="disabled" />
				<input type="hidden" id="busiTpCd" name="busiTpCd" class="w270" />
			</td>
			<th><spring:message code="LAB.M08.LAB00041"/><!-- 온라인구분 --></th>
			<td>
				<input type="text" id="onlyTpNm" name="onlyTpNm" class="w270" disabled="disabled" />
				<input type="hidden" id="onlnTpCd" name="onlnTpCd" class="w270" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00012"/><!-- 사업자등록번호 --></th>
			<td>
				<input type="text" id="borNo" name="borNo" class="w270" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M06.LAB00053"/><!-- 법인등록번호 --></th>
			<td>
				<input type="text" id="corpRegNo" name="corpRegNo" class="w270" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00011"/><!-- 업종 --></th>
			<td>
				<input type="text" id="busiTp" name="busiTp" class="w270" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M08.LAB00012"/><!-- 업태 --></th>
			<td>
				<input type="text" id="busiCndt" name="busiCndt" class="w270" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M03.LAB00063"/><!-- 대표자명 --></th>
			<td>
				<input type="text" id="repNm" name="repNm" class="w270" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M03.LAB00064"/><!-- 대표자주민등록번호 --></th>
			<td>
				<input type="text" id="repRno" name="repRno" class="w270" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00119"/><!-- 이메일 --></th>
			<td>
				<input type="text" id="eml" name="eml" class="w270" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M14.LAB00062"/><!-- 홈페이지 --></th>
			<td>
				<input type="text" id="hmpg" name="hmpg" class="w270" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00065"/><!-- 전화번호 --></th>
			<td>
				<input type="text" id="telNo" name="telNo" class="w270" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M13.LAB00015"/><!-- 팩스번호 --></th>
			<td>
				<input type="text" id="faxNo" name="faxNo" class="w270" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00190"/><!-- 주소 --></th>
			<td colspan="3">
				<div class="date_box">
					<div class="inp_date p100">
						<input type="text" id="zipCd" name="zipCd" class="p100" disabled="disabled" />
						<a href="#" class="search">search</a>
					</div> 
					<div class="inp_date w320">
						<input type="text" id="addr1" name="addr1" class="w320" disabled="disabled" />
					</div>
					<div class="inp_date w320">
						<input type="text" id="addr2" name="addr2" class="w320" disabled="disabled" />
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M01.LAB00015"/><!-- 개설일자 --></th>
			<td>
				<div class="inp_date w265">
					<input type="text" id="appyStrtDt" name="appyStrtDt" class="datepicker" disabled="disabled" />
					<!-- <a href="#" class="btn_cal"></a> --> 
				</div>
			</td>
			<th><spring:message code="LAB.M13.LAB00019"/><!-- 폐점일 --></th>
			<td>
				<div class="inp_date w265">
					<input type="text" id="appyEndDt" name="appyEndDt" class="datepicker" disabled="disabled" />
					<!-- <a href="#" class="btn_cal"></a> --> 
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00150"/><!-- 조직상태 --></th>
			<td>
				<input type="text" id="orgStatNm" name="orgStatNm" class="w270" disabled="disabled" />
				<input type="hidden" id="orgStatCd" name="orgStatCd" class="w270" />
			</td>
			<th><spring:message code="LAB.M14.LAB00046"/><!-- 해지사유 --></th>
			<td>
				<input type="text" id="termResn" name="termResn" class="w270" disabled="disabled" />
			</td>
		</tr>
	</tbody>
</table>

<div class="main_btn_box">
	<div class="fl">
	</div>
	<div class="fr">
		<!--초기화-->		
		<a class="grey-btn" href="#" id="btn_init"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
		<%-- 		
		<!--등록-->
		<ntels:auth auth="${menuAuthC}">
			<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>	
			 --%>
		<!--수정-->
		<ntels:auth auth="${menuAuthU}">
			<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		<!--삭제-->
		<%-- 
		<ntels:auth auth="${menuAuthD}">
			<a class="grey-btn" href="#" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
		 --%>
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
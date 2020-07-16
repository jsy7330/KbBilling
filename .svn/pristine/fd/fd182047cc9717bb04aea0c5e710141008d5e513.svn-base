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
			search();
			inputClear();
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
		
		//등록버튼
		$("#btn_insert").click(function() {
			var url="employeeInsertPopUp.ajax";
			var param = new Object();
			
			openModalPopup(url, param);
		});
		
		//수정버튼
		$("#btn_update").click(function() {
			if($("#userId").val() == ""){
				alert('<spring:message code="MSG.M07.MSG00100"/>');	//수정할 사용자를 선택해 주세요.
				return;
			}
			
			var url="employeeUpdatePopUp.ajax";
			var param = new Object();
			
			openModalPopup(url, param);
		});
		
		//삭제버튼
		$("#btn_delete").click(function() {
			
			if($("#userId").val() == ""){
				alert('<spring:message code="MSG.M07.MSG00057"/>');	//삭제할 사용자를 선택해 주세요.
				return;
			}
			
			deleteOrgRelHist();            
		});
		
		$('#btn_init').addClass('white-btn');
		$('#btn_init').removeClass('grey-btn');
		$('#btn_init').addClass('not-active');
		$('#btn_init').attr('disabled',true);
	});
	
	function initGrid() {
		
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		
		$("#employeeTable").jqGrid({
			
		   	url:'employeeListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'password' , name: 'password', hidden:true,width : 0},
				{ label: 'userGroupId' , name: 'userGroupId', hidden:true,width : 0},
				{ label: 'userGroupNm' , name: 'userGroupNm', hidden:true,width : 0},
				{ label: 'crrTp' , name: 'crrTp', hidden:true,width : 0},
				{ label: 'empNo' , name: 'empNo', hidden:true,width : 0},
				{ label: 'telNo' , name: 'telNo', hidden:true,width : 0,formatter:telNoFormatter},
				{ label: 'eMail' , name: 'eMail', hidden:true,width : 0},
				{ label: 'ipBandwidth' , name: 'ipBandwidth', hidden:true,width : 0},
				{ label: 'loginFailCount' , name: 'loginFailCount', hidden:true,width : 0},
				{ label: 'passwordDueDate' , name: 'passwordDueDate', hidden:true,width : 0,  formatter:stringTypeFormatterYYYYMMDD},
				{ label: 'passwordChangePeriod' , name: 'passwordChangePeriod', hidden:true,width : 0},
				{ label: 'lastLoginDate' , name: 'lastLoginDate', hidden:true,width : 0},
				{ label: 'lastLoginTime' , name: 'lastLoginTime', hidden:true,width : 0},
				{ label: 'useYn' , name: 'useYn', hidden:true,width : 0},
				{ label: 'accountLock' , name: 'accountLock', hidden:true,width : 0},

	   		    { label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'soNm', width : 100},		//사업
	   		 	{ label: '<spring:message code="LAB.M09.LAB00139"/>', name: 'orgId', width : 100},		//조직ID
	   			{ label: '<spring:message code="LAB.M09.LAB00147"/>', name: 'orgNm', width : 100},		//조직명
	   			{ label: '<spring:message code="LAB.M09.LAB00138"/><spring:message code="LAB.M08.LAB00103"/>', name: 'tpNm', width : 100},		//조직유형
	   			{ label: '<spring:message code="LAB.M08.LAB00105"/>', name: 'tpDtlNm', width : 100},	//유형상세
	   			{ label: '<spring:message code="LAB.M07.LAB00067"/>', name: 'userId', width : 100},	//사용자ID
	   			{ label: '<spring:message code="LAB.M07.LAB00071"/>', name: 'userName', width : 100},	//사용자명
	   			{ label: '<spring:message code="LAB.M07.LAB00001"/>', name: 'empNo', width : 100},		//사번
	   			{ label: '<spring:message code="LAB.M14.LAB00052"/>', name: 'mtelNo', width : 100},		//핸드폰번호
	   			{ label: '<spring:message code="LAB.M08.LAB00119"/>', name: 'eMail', width : 100},		//이메일
	   			
			    
	   		],
	   		
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#employeePager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 240,					//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowid, index, contents, event){
	        	setSelectedDate(rowid);
	        },
	        loadComplete: function(obj){
	        	$("#employeeTable").setGridWidth($('#employeeGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        },
	        
	        sortable: { update: function(permutation) {
	        	$("#employeeTable").setGridWidth($('#employeeGrid').width(),false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
		});
		
		$("#employeeTable").setGridWidth($('#employeeGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#employeeTable").setGridWidth($('#employeeGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
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
	
	function search(){
		
		$("#employeeTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		
        $("#employeeTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	/*
	 * 데이터 선택 이벤트 처리
	 */
	function setSelectedDate(rowId) {

		var data = $("#employeeTable").getRowData(rowId);
		$("#userId").val(data.userId);
		$("#userName").val(data.userName);
		$("#password").val(data.password);
		$("#userGroupId").val(data.userGroupId);
		$("#userGroupNm").val(data.userGroupNm);
		$("#orgId").val(data.orgId);
		$("#orgNm").val(data.orgNm);
		$("#crrTp").val(data.crrTp);
		$("#empNo").val(data.empNo);
		$("#telNo").val(data.telNo);
		$("#mtelNo").val(data.mtelNo);
		$("#eMail").val(data.eMail);
		$("#ipBandwidth").val(data.ipBandwidth);
		$("#loginFailCount").val(data.loginFailCount);
		$("#passwordDueDate").val(data.passwordDueDate);
		$("#passwordChangePeriod").val(data.passwordChangePeriod);
		
		$('#crrTp').val(data.crrTp);
		$('#crrTp').selectmenu("refresh");
		
		$('#useYn').val(data.useYn);
		$('#useYn').selectmenu("refresh");
		
		$('#passwordChangePeriod').val(data.passwordChangePeriod);
		$('#passwordChangePeriod').selectmenu("refresh");
		
		$('#accountLock').val(data.accountLock);
		$('#accountLock').selectmenu("refresh");
		
	}
	
	function inputClear(){
		$("#userId").val("");
		$("#userName").val("");
		$("#password").val("");
		$("#userGroupId").val("");
		$("#userGroupNm").val("");
		$("#orgId").val("");
		$("#orgNm").val("");
		$("#crrTp").val("");
		$("#empNo").val("");
		$("#telNo").val("");
		$("#mtelNo").val("");
		$("#eMail").val("");
		$("#ipBandwidth").val("");
		$("#loginFailCount").val("");
		$("#passwordDueDate").val("");
		$("#passwordChangePeriod").val("");
		
		$('#crrTp').val("");
		$('#crrTp').selectmenu("refresh");
		
		$('#useYn').val("");
		$('#useYn').selectmenu("refresh");
		
		$('#passwordChangePeriod').val("");
		$('#passwordChangePeriod').selectmenu("refresh");
		
		$('#accountLock').val("");
		$('#accountLock').selectmenu("refresh");
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
		<col style="width:15%;" />
		<col style="width:85%;" />
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
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00208"/><!-- 직원목록 --></h4>
	</div>
</div>

<div id="employeeGrid">
	<table id="employeeTable" class="w100p"></table>
	<div id="employeePager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00209"/><!-- 직원상세정보 --></h4>
	</div>
</div>


<table class="writeview">
	<colgroup>
		<col style="width: 8%;">
		<col style="width: 17%;">
		<col style="width: 8%;">
		<col style="width: 17%;">
		<col style="width: 8%;">
		<col style="width: 17%;">
		<col style="width: 8%;">
		<col style="width: 17%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M07.LAB00067"/><!-- 사용자ID --></th>
			<td>
				<input name="userId" id="userId"type="text" class="w160" disabled="disabled" /> 
				
			</td>
			<th><spring:message code="LAB.M07.LAB00071"/><!-- 사용자명 --></th>
			<td>
				<input type="text" id="userName" name="userName"class="w160" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M13.LAB00013"/><!-- 패스워드 --></th>
			<td colspan="3">
				<input type="password" id="password" name="password"class="w250" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00138"/><!-- 조직 --></th>
			<td>
				<div class="inp_date w170">
					<input type="hidden" id="orgId" name="orgId"> 
					<input type="text" id="orgNm" name="orgNm" class="w160" disabled="disabled" /> 
				</div>
			</td>
			<th><spring:message code="LAB.M01.LAB00202"/><!-- 기본그룹 --></th>
			<td>
				<div class="inp_date w170">
					<input type="text" id="userGroupNm" name="userGroupNm" class="w160" disabled="disabled" />
					<input type="hidden" id="userGroupId" name="userGroupId">
				</div>
			</td>
			<th><spring:message code="LAB.M07.LAB00074"/><!-- 사용자유형 --></th>
			<td>
				<select id="crrTp" name="crrTp" class="w160" disabled="disabled" />
					<option value=''><spring:message code="LAB.M07.LAB00195"/></option>
					<c:forEach items="${crrTp}" var="crrTp" varStatus="status">
					 	<option value="${crrTp.commonCd}">${crrTp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M07.LAB00028"/><!-- 사용유무 --></th>
			<td>
				<select id="useYn" name="useYn" class="w160" disabled="disabled">
					<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					<option value="Y">Y</option>
					<option value="N">N</option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00001"/><!-- 사번 --></th>
			<td>
				<input id="empNo" name="empNo" type="text" class="w160" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M09.LAB00065"/><!-- 전화번호 --></th>
			<td>
				<input id="telNo" name="telNo" type="text" class="w160" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M14.LAB00052"/><!-- 핸드폰번호 --></th>
			<td>
				<input id="mtelNo" name="mtelNo" type="text" class="w160" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M08.LAB00119"/><!-- 이메일 --></th>
			<td>
				<input id="eMail" name="eMail" type="text" class="w160" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M14.LAB00053"/><!-- 허용IP대역 --></th>
			<td>
				<input id="ipBandwidth" name="ipBandwidth" type="text" class="w160" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M15.LAB00075"/><!-- PW변경예정일 --></th>
			<td>
				<div class="inp_date w160">
					<fmt:parseDate value="${passwordDueDate}" var="passwordDueDate" pattern="${dateFormat4}"/> 
			 		<input  type="text" id="passwordDueDate" name="passwordDueDate" value="<fmt:formatDate value="${passwordDueDate}"  pattern="${dateToStrFormat4}"/>" class="datepicker" disabled="disabled"><a href="#" class="btn_cal"></a> 
				</div>
			</td>
			<th><spring:message code="LAB.M15.LAB00076"/><!-- PW주기 --></th>
			<td>
				<select id="passwordChangePeriod" name="passwordChangePeriod" class="w160" disabled="disabled">
					<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					<option value="30">30</option>
					<option value="60">60</option>
					<option value="90">90</option>
				</select>
			</td>
			<th><spring:message code="LAB.M09.LAB00041"/><!-- 잠김여부 --></th>
			<td>
				<select id="accountLock" name="accountLock" class="w160" disabled="disabled">
					<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					<option value="Y">Y</option>
					<option value="N">N</option>
				</select>
			</td>
		</tr>
	</thead>
</table>


<%-- 
<table class="writeview">
	<colgroup>
		<col style="width:15%;" />
		<col style="width:85%;" />
	</colgroup>
	<tbody>
		<tr>
			<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
			<td>
				<input type="text" id="input"class="w500" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --></th>
			<td>
				<input type="text" id="input"class="w145" />
				<input type="text" id="input"class="w350" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00104"/><!-- 유형/유형상세 --></th>
			<td>
				<input type="text" id="input"class="w145" />
				<input type="text" id="input"class="w350" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00068"/><!-- 사용자ID/명 --></th>
			<td>
				<input type="text" id="input"class="w145" />
				<input type="text" id="input"class="w350" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00001"/><!-- 사번 --></th>
			<td>
				<input type="text" id="input"class="w500" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M14.LAB00052"/><!-- 핸드폰번호 --></th>
			<td>
				<input type="text" id="input"class="w500" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00065"/><!-- 전화번호 --></th>
			<td>
				<input type="text" id="input"class="w500" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00119"/><!-- 이메일 -->이메일</th>
			<td>
				<input type="text" id="input"class="w500" />
			</td>
		</tr>
	</tbody>
</table>
 --%>
 
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
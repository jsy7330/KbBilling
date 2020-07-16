<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {

		//조회버튼
		$("#btnSearch").click(function() {
			searchData();            
		});
		
		//유형 셀렉트 박스 체인지 이벤트
		$('#searchTpCd').selectmenu({
		    change: function() {
		    	changeTpCd();
		    }
		});
		
		//그리드 시작
		initGrid();
		//사업명 불러오기
		getOrgNm();
		
		//조직이력조회팝업
		$("#orgHisPopUp").click(function() {
			var param = new Object();
			param.orgId = $("#orgId").val();
			var url="organizationRelListPopUp.ajax";
			openModalPopup(url, param);
		});
		
		//등록팝업
		$("#btn_insert").click(function() {
			var url="organizationInsertPopUp.ajax";
			var param = new Object();
			openModalPopup(url, param);    
		});
		//등록팝업
		$("#btn_update").click(function() {
			var url="organizationUpdatePopUp.ajax";
			var param = new Object();
			param.orgId = $("#orgId").val();
			openModalPopup(url, param);
		});
		
		//조직검색 팝업
		$("#searchOrgPopup").click(function() {
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			var param = new Object();
			param.orgId = $("#orgId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchOrgId";	//리턴받는 orgId 의 태그아이디값
			param.returnId2 = "searchOrgNm";	//리턴받는 orgNm 의 태그아이디값
			
			$("#popup_dialog").css("width", 1000);
			openModalPopup(url, param);
		});
		
		//키이벤트
		$("#searchOrgId").keyup(function(){
			var inputLength = $(this).val().length;
			if(inputLength == 10){
				getOrgNm();
			}
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
			
			$("#searchTpCd").val("");
			$("#searchArClCd").val("");
			$("#searchArTpCd").val("");
			
			$( "#searchTpCd" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			$( "#searchArClCd" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			$( "#searchArTpCd" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			
			changeTpCd();
			
		});
		 */
		
	});
	
	function getOrgNm(){
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		
		$.ajax({
			url : 'getOrganizationListAction.json',
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

	function initGrid() {
		
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		
		$("#organizationTable").jqGrid({
	
		   	url:'organizationListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[
		   		{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100},
		   		{label:'<spring:message code="LAB.M09.LAB00139" />',name:'orgId', width:100, align:'center' },
		   		{label:'<spring:message code="LAB.M09.LAB00147" />',name:'orgNm', width:100},
		   		{label:'<spring:message code="LAB.M08.LAB00103" />',name:'tpNm', width:100},
		   		{label:'<spring:message code="LAB.M08.LAB00105" />',name:'tpDtlNm', width:100},
		   		{label:'<spring:message code="LAB.M09.LAB00160" />',name:'inqPermNm', width:100, align:'center'},
		   		{label:'<spring:message code="LAB.M09.LAB00052" />',name:'appyStrtDt', width:100, align:'center', formatter: stringTypeFormatterYYYYMMDD},
		   		{label:'<spring:message code="LAB.M09.LAB00058" />',name:'appyEndDt', width:100, align:'center', formatter: stringTypeFormatterYYYYMMDD}
		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#pager2',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 100,					//화면 상태에 따라 크기 조절 할 것
		    onCellSelect : function(rowid, index, contents, event){
	        	ajaxDetail($("#organizationTable").getRowData(rowid).orgId);
	        },
		    loadComplete: function(obj){
		    	
		    	if(obj.total > 0){
		    		//상세정보 조회
					ajaxDetail($("#organizationTable").getRowData(1).orgId);	
		    	}else{
		    		reset();
		    	}
		    	
		    	$("#organizationTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    	
		    },
		    sortable: { update: function(permutation) {
	        	$("#organizationTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
		});
		
		$("#organizationTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#organizationTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
		
		
		$("#organizationRelTable").jqGrid({
			
		   	url:'organizationRelListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
		   	colModel:[
		   		{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100},
		   		{label:'<spring:message code="LAB.M01.LAB00160" />',name:'relTpNm', width:100},
		   		{label:'<spring:message code="LAB.M07.LAB00097" />',name:'partOrgId', width:100},
		   		{label:'<spring:message code="LAB.M07.LAB00099" />',name:'partOrgNm', width:100},
		   		{label:'<spring:message code="LAB.M07.LAB00098" />',name:'partOrgLvNm', width:100}
		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#pager3',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 50,					//화면 상태에 따라 크기 조절 할 것
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
	
	function searchData(){
		
		$("#organizationTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		param.tpCd = $("#searchTpCd").val();
		param.tpDtlCd = $("#searchTpDtlCd").val();
		param.arClCd = $("#searchArClCd").val();
		param.arTpCd = $("#searchArTpCd").val();
		
        $("#organizationTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function changeTpCd(){
		
		var grpCd = "DN00039";
		var ref1 = $("#searchTpCd").val();
		
		var param = new Object();
		param.grpCd = grpCd;
		param.ref1 = ref1;
		
		var sHtml = '';
		sHtml = sHtml + '<option value=""><spring:message code="LAB.M07.LAB00195"/></option>';
		
		if(ref1 == ''){
			$("#searchTpDtlCd").html("");
			$("#searchTpDtlCd").html(sHtml);
			$( "#searchTpDtlCd" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			
		}else{
			
			$.ajax({
				url : 'getCommonCodeListByRef1ListAction.json',
				type : 'POST',
				data : param,
				success : function(data) {
					
					$.each(data.codeList, function(index,item){
						sHtml = sHtml + '<option value="'+item.commonCd+'">'+item.commonCdNm+'</option>';						
					}); 
					
					$("#searchTpDtlCd").html("");
					$("#searchTpDtlCd").html(sHtml);
					$( "#searchTpDtlCd" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
			
		}
		
	}
	
	function ajaxDetail(orgId){

		var param = new Object();
		param.orgId = orgId;
		
		$.ajax({
			url : 'getOrganizationListAction.json',
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
		
		var organizationData = data.organizationData;
		
		$("#soNm").val(organizationData.soNm);				//사업ID
		$("#orgId").val(organizationData.orgId);			//조직ID
		$("#orgNm").val(organizationData.orgNm);			//조직명
		$("#tpNm").val(organizationData.tpNm);				//유형코드명
		$("#tpDtlNm").val(organizationData.tpDtlNm);		//유형상세코드명
		$("#inqPermNm").val(organizationData.inqPermNm);	//조회권한명
		$("#appyStrtDt").val(stringToDateformatYYYYMMDD(organizationData.appyStrtDt));	//적용시작일
		$("#appyEndDt").val(stringToDateformatYYYYMMDD(organizationData.appyEndDt));	//적용종료일
		$("#orgStatNm").val(organizationData.orgStatNm);	//조직상태코드명
		$("#orgLvNm").val(organizationData.orgLvNm);		//조직레벨코드명
		$("#privTpNm").val(organizationData.privTpNm);		//자사구분코드명
		//지역분류 구분 ??????
		
		if(organizationData.permLogin == "1"){				//업무권한-로그인
			$("input:checkbox[id='permLogin']").prop("checked", true);
		}else{
			$("input:checkbox[id='permLogin']").prop("checked", false);
		}
		
		if(organizationData.permPym == "1"){				//업무권한-수납
			$("input:checkbox[id='permPym']").prop("checked", true);
		}else{
			$("input:checkbox[id='permPym']").prop("checked", false);
		}
		
		if(organizationData.permSubs == "1"){				//업무권한-가입
			$("input:checkbox[id='permSubs']").prop("checked", true);
		}else{
			$("input:checkbox[id='permSubs']").prop("checked", false);
		}
		
		if(organizationData.permChg == "1"){				//업무권한-기변
			$("input:checkbox[id='permChg']").prop("checked", true);
		}else{
			$("input:checkbox[id='permChg']").prop("checked", false);
		}
		
		if(organizationData.permTerm == "1"){				//업무권한-해지
			$("input:checkbox[id='permTerm']").prop("checked", true);
		}else{
			$("input:checkbox[id='permTerm']").prop("checked", false);
		}
		
		if(organizationData.loanGvFlg == "1"){				//여신부여여부
			$("input:checkbox[id='loanGvFlg']").prop("checked", true);
		}else{
			$("input:checkbox[id='loanGvFlg']").prop("checked", false);
		}
		
		if(organizationData.onlineClCd == "1"){				//온라인구분
			$("input:checkbox[id='onlineClCd']").prop("checked", true);
		}else{
			$("input:checkbox[id='onlineClCd']").prop("checked", false);
		}
		
		if(organizationData.tbiFlg == "1"){					//세금계산서발행여부
			$("input:checkbox[id='tbiFlg']").prop("checked", true);
		}else{
			$("input:checkbox[id='tbiFlg']").prop("checked", false);
		}
		
		if(organizationData.feePvFlg == "1"){				//수수료지급여부
			$("input:checkbox[id='feePvFlg']").prop("checked", true);
		}else{
			$("input:checkbox[id='feePvFlg']").prop("checked", false);
		}
	}
	
	function reset(){
		$("#areaView input").val("");
		$("#areaView input:checkbox").prop("checked", false);
		$("#organizationRelTable").clearGridData();  // 이전 데이터 삭제
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
		<a href="#" id="btnSearch" class="grey-btn " ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
	</div>
</div>

<table class="writeview">
	<colgroup>
		<col style="width:15%;" />
		<col style="width:35%;" />
		<col style="width:15%;" />
		<col style="width:35%;" />
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --></th>
			<td colspan="3">
				<div class="date_box">
					<div class="inp_date p100">
						<input type="text" id="searchOrgId" name="searchOrgId" value="${session_user.orgId}" maxlength="10" class="p100" />
						<a href="#" id="searchOrgPopup" class="search">search</a>
					</div>
					<div class="inp_date w370">
						<input type="text" id="searchOrgNm" name="searchOrgNm" class="w370 not-active" disabled="disabled"/>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00103"/><!-- 유형 --></th>
			<td>
				<select id="searchTpCd" name="searchTpCd" class="w270">
					<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					<c:forEach items="${tpCd}" var="tpCd" varStatus="status">
						<option value="${tpCd.commonCd}">${tpCd.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M08.LAB00105"/><!-- 유형상세 --> </th>
			<td>
				<select id="searchTpDtlCd" name="searchTpDtlCd" class="w270">
					<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00201"/><!-- 지역분류 --></th>
			<td>
				<select id="searchArClCd" name="searchArClCd" class="w270">
					<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					<c:forEach items="${arClCd}" var="arClCd" varStatus="status">
						<option value="${arClCd.commonCd}">${arClCd.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M09.LAB00200"/><!-- 지역구분 --> </th>
			<td>
				<select id="searchArTpCd" name="searchArTpCd" class="w270">
					<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					<c:forEach items="${arTpCd}" var="arTpCd" varStatus="status">
						<option value="${arTpCd.commonCd}">${arTpCd.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
</table>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00148"/><!-- 조직목록 --></h4>
	</div>
</div>

<div id='gridDiv'>
	<table id="organizationTable" class="w100p"></table>
	<div id="pager2"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00149"/><!-- 조직상세정보 --></h4>
	</div>
</div>

<table class="writeview" id="areaView" >
	<colgroup>
		<col style="width:15%;" />
		<col style="width:35%;" />
		<col style="width:15%;" />
		<col style="width:35%;" />
	</colgroup>
	<tbody>
		<tr>
			<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --> </th>
			<td>
				<input id="soNm" name="soNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M09.LAB00138"/><!-- 조직 --> </th>
			<td>
				<div class="date_box">
					<div class="inp_date w180">
						<input type="text" id="orgId" name="orgId" class="w180 not-active" disabled="disabled" />
					</div>
					<div class="inp_date w180">
						<input type="text" id="orgNm" name="orgNm" class="w180 not-active" disabled="disabled" />
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00104"/><!-- 유형/유형상세 --></th>
			<td>
				<div class="date_box">
					<div class="inp_date w180">
						<input type="text" id="tpNm" name="tpNm" class="w180 not-active" disabled="disabled" />
					</div>
					<div class="inp_date w180">
						<input type="text" id="tpDtlNm" name="tpDtlNm" class="w180 not-active" disabled="disabled" />
					</div>
				</div>
			</td>
			<th><spring:message code="LAB.M09.LAB00160"/><!-- 조회권한 --></th>
			<td>
				<input id="inqPermNm" name="inqPermNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00056"/><!-- 적용일자 --></th>
			<td>
				<div class="date_box">
					<div class="inp_date w170">
						<input type="text" id="appyStrtDt" name="appyStrtDt" class="datepicker not-active" disabled="disabled"  />
						<a href="#" class="btn_cal"></a>
					</div>
					<span class="dash">~</span>
					<div class="inp_date w170">
						<input type="text" id="appyEndDt" name="appyEndDt" class="datepicker not-active" disabled="disabled"  />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th><spring:message code="LAB.M09.LAB00150"/><!-- 조직상태 --></th>
			<td>
				<input id="orgStatNm" name="orgStatNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00146"/><!-- 조직레벨 --></th>
			<td>
				<input id="orgLvNm" name="orgLvNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M09.LAB00003"/><!-- 자사구분 --></th>
			<td>
				<input id="privTpNm" name="privTpNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00202"/><!-- 지역분류/구분 --></th>
			<td colspan="3">
				<div class="date_box">
					<div class="inp_date w180">
						<input type="text" id="input"class="w180 not-active" disabled="disabled" />
					</div>
					<div class="inp_date w180">
						<input type="text" id="input"class="w180 not-active" disabled="disabled" />
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00010"/><!-- 업무권한 --></th>
			<td colspan="3">
				<input type="checkbox" id="permLogin" name="permLogin" class="not-active" disabled="disabled" />
				<label for="permLogin" class="not-active" disabled="disabled" ><spring:message code="LAB.M04.LAB00006"/><!-- 로그인 --></label>
				<input type="checkbox" id="permPym" name="permPym" class="not-active" disabled="disabled" />
				<label for="permPym" class="not-active" disabled="disabled" ><spring:message code="LAB.M07.LAB00235"/><!-- 수납 --></label>
				<input type="checkbox" id="permSubs" name="permSubs" class="not-active" disabled="disabled" />
				<label for="permSubs" class="not-active" disabled="disabled"><spring:message code="LAB.M01.LAB00019"/><!-- 개통 --></label>
				<input type="checkbox" id="permChg" name="permChg" class="not-active" disabled="disabled" />
				<label for="permChg" class="not-active" disabled="disabled" ><spring:message code="LAB.M01.LAB00199"/><!-- 기변 --></label>
				<input type="checkbox" id="permTerm" name="permTerm" class="not-active" disabled="disabled" />
				<label for="permTerm" class="not-active" disabled="disabled"><spring:message code="LAB.M14.LAB00045"/><!-- 해지 --></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M08.LAB00018"/><!-- 여신부여여부 --></th>
			<td>
				<input type="checkbox" id="loanGvFlg" name="loanGvFlg" class="not-active" disabled="disabled" />
				<label for="loanGvFlg"></label>
			</td>
			<th><spring:message code="LAB.M08.LAB00041"/><!-- 온라인구분 --></th>
			<td>
				<input type="checkbox" id="onlineClCd" name="onlineClCd" class="not-active" disabled="disabled" />
				<label for="onlineClCd"></label>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00209"/><!-- 세금계산서발행여부 --></th>
			<td>
				<input type="checkbox" id="tbiFlg" name="tbiFlg" class="not-active" disabled="disabled" />
				<label for="input2"></label>
			</td>
			<th><spring:message code="LAB.M07.LAB00251"/><!-- 수수료지급여부 --></th>
			<td>
				<input type="checkbox" id="feePvFlg" name="feePvFlg" class="not-active" disabled="disabled" />
				<label for="feePvFlg"></label>
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
	<div class="fl">
		<a class="grey-btn" title="<spring:message code='LAB.M09.LAB00153'/>" id="orgHisPopUp" href="#" ><spring:message code="LAB.M09.LAB00153"/><!-- 조직이력조회 --></a>
	</div>

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
		<%-- 
		<!--삭제-->
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


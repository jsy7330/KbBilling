<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		initGrid();
		
		//사업명 불러오기
		getOrgNm();
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();            
		});
		
		//키이벤트
		$("#searchOrgId").keyup(function(){
			var inputLength = $(this).val().length;
			if(inputLength == 10){
				getOrgNm();
			}
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
		
		//등록팝업
		$("#btnInsert").click(function() {
			var url="collateralInsertPopUp.ajax";
			var param = new Object();
			
			$("#popup_dialog").css("width", 1000);
			openModalPopup(url, param);    
		});
		
		//수정팝업
		$("#btn_update").click(function() {
			
			var rowId  = $("#collateralTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M03.MSG00010" />');	//담보목록을 선택해 주세요.
				return;
			}
			
			var url="collateralUpdatePopUp.ajax";
			var param = new Object();
			
			$("#popup_dialog").css("width", 1000);
			openModalPopup(url, param);    
		});
		
		
		$("#btn_delete").click(function() {
			
			var rowId  = $("#collateralTable").jqGrid("getGridParam", "selrow" );
			if(rowId == null){
				alert('<spring:message code="MSG.M03.MSG00010" />');	//담보목록을 선택해 주세요.
				return;
			}
			
			deleteCollInfo();
			
		});
		
	});
	
	function initGrid() {
		
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		
		$("#collateralTable").jqGrid({
	
		   	url:'collateralListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
						{ label: 'soId' , name: 'soId', hidden:true,width : 0},
						{ label: 'collKndCd' , name: 'collKndCd', hidden:true,width : 0},
						{ label: 'collOrg' , name: 'collOrg', hidden:true,width : 0},

						{ label: '<spring:message code="LAB.M07.LAB00003" />', name: 'soNm', width : 100},	//사업
						{ label: '<spring:message code="LAB.M03.LAB00040" />', name: 'collKndNm', width : 100},	//담보유형
						{ label: '<spring:message code="LAB.M09.LAB00139" />', name: 'orgId', width : 100},	//조직ID
						{ label: '<spring:message code="LAB.M09.LAB00147" />', name: 'orgNm', width : 100},	//조직명
						{ label: '<spring:message code="LAB.M03.LAB00062" />', name: 'repNm', width : 100},	//대표자
						{ label: '<spring:message code="LAB.M07.LAB00202" />', name: 'stpAmt', formatter: 'integer', align:'right', width : 100},	//설정금액
						{ label: '<spring:message code="LAB.M03.LAB00023" />', name: 'loanAmt', formatter: 'integer', align:'right', width : 100},	//단말여신금액
						{ label: '<spring:message code="LAB.M08.LAB00050" />', name: 'loanArpiAmt', formatter: 'integer', align:'right', width : 100},	//요금여신금액
						{ label: '<spring:message code="LAB.M07.LAB00204" />', name: 'stpDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//설정일
			   		 	
						
			   		],
			   		
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#collateralPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 100,					//화면 상태에 따라 크기 조절 할 것
		    onCellSelect : function(rowid, index, contents, event){
				detailData(rowid);
		    },
		    loadComplete: function(obj){
		    	
		    	$("#collateralTable").setGridWidth($('#collateralGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
	        	$("#collateralTable").setGridWidth($('#collateralGrid').width(),false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
		    
		});
		
		$("#collateralTable").setGridWidth($('#collateralGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#collateralTable").setGridWidth($('#collateralGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
		
		
	}
	
	function searchData(){
		
		$("#collateralTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		param.collKndCd = $("#searchCollKndCd").val();
		
        $("#collateralTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
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
	
	
	function detailData(rowId){
		
		var data = $("#collateralTable").getRowData(rowId);
		
		console.log(data);
		
		var param = new Object();
		param.orgId = data.orgId;
		param.collOrg = data.collOrg;
		
		$.ajax({
			url : 'selectCollInfo.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data.result);
				setData(data.result);
				
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
		
	}
	
	function setData(data){
		
		$("#soNm").val(data.soNm);
		$("#orgId").val(data.orgId);
		$("#orgNm").val(data.orgNm);
		$("#insuCtrtrNm").val(data.insuCtrtrNm);
		$("#repNm").val(data.repNm);
		$("#assrKndNm").val(data.assrKndNm);
		$("#stckNo").val(data.stckNo);
		$("#assrStrtDt").val(stringToDateformatYYYYMMDD(data.assrStrtDt));
		$("#assrEndDt").val(stringToDateformatYYYYMMDD(data.assrEndDt));
		$("#assrCorpNm").val(data.assrCorpNm);
		$("#assrCt").val(data.assrCt);
		$("#assrBrNm").val(data.assrBrNm);
		$("#assrAgncyNm").val(data.assrAgncyNm);
		$("#stpAmt").val(data.stpAmt);
		$("#loanAmt").val(data.loanAmt);
		$("#stpDt").val(stringToDateformatYYYYMMDD(data.stpDt));
		$("#loanArpiAmt").val(data.loanArpiAmt);
		$("#termDt").val(stringToDateformatYYYYMMDD(data.termDt));
		$("#rmrk").val(data.rmrk);
		
	}
	
	function clearData(){
		
		$("#soNm").val("");
		$("#orgId").val("");
		$("#orgNm").val("");
		$("#insuCtrtrNm").val("");
		$("#repNm").val("");
		$("#assrKndNm").val("");
		$("#stckNo").val("");
		$("#assrStrtDt").val("");
		$("#assrEndDt").val("");
		$("#assrCorpNm").val("");
		$("#assrCt").val("");
		$("#assrBrNm").val("");
		$("#assrAgncyNm").val("");
		$("#stpAmt").val("");
		$("#loanAmt").val("");
		$("#stpDt").val("");
		$("#loanArpiAmt").val("");
		$("#termDt").val("");
		$("#rmrk").val("");
		
	}
	
	function deleteCollInfo(){
		
		var rowId  = $("#collateralTable").jqGrid("getGridParam", "selrow" );
		var param = $("#collateralTable").getRowData(rowId);
		
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');

		if(check){
			
		
			$.ajax({
				url : 'deleteCollInfo.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M07.MSG00053" />');	//저장되었습니다.
						
						searchData();//부모창 새로고침
						
						clearData();
						
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
			<th><spring:message code="LAB.M03.LAB00040"/><!-- 담보유형 --></th>
			<td colspan="3">
				<select id="searchCollKndCd" name="searchCollKndCd" class="w270">
					<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					<c:forEach items="${collKndCd}" var="collKndCd" varStatus="status">
						<option value="${collKndCd.commonCd}">${collKndCd.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
</table>	
	
	
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M03.LAB00039"/><!-- 담보목록 --></h4>
	</div>
</div>

<div id="collateralGrid">
	<table id="collateralTable" class="w100p"></table>
	<div id="collateralPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M06.LAB00074"/><!-- 보증보험일반사항 --></h4>
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
			<th><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --></th>
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
			<th><spring:message code="LAB.M06.LAB00078"/><!-- 보험계약자명 --></th>
			<td>
				<input id="insuCtrtrNm" name="insuCtrtrNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M03.LAB00062"/><!-- 대표자 --></th>
			<td>
				<input id="repNm" name="repNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M06.LAB00075"/><!-- 보증보험종류 --></th>
			<td>
				<input id="assrKndNm" name="assrKndNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M09.LAB00197"/><!-- 증권번호 --></th>
			<td>
				<input id="stckNo" name="stckNo" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M06.LAB00069"/><!-- 보증개시일 --></th>
			<td>
				<input id="assrStrtDt" name="assrStrtDt" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M06.LAB00072"/><!-- 보증만료일 --></th>
			<td>
				<input id="assrEndDt" name="assrEndDt" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M06.LAB00077"/><!-- 보증회사명 --></th>
			<td>
				<input id="assrCorpNm" name="assrCorpNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M06.LAB00071"/><!-- 보증내용 --></th>
			<td>
				<input id="assrCt" name="assrCt" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M06.LAB00076"/><!-- 보증보험지점명 --></th>
			<td>
				<input id="assrBrNm" name="assrBrNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M06.LAB00079"/><!-- 보험대리점명 --></th>
			<td>
				<input id="assrAgncyNm" name="assrAgncyNm" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		
	</tbody>
</table>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M01.LAB00011"/><!-- 감정내역 --></h4>
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
			<th><spring:message code="LAB.M06.LAB00070"/><!-- 보증금액 --></th>
			<td>
				<input id="stpAmt" name="stpAmt" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M03.LAB00023"/><!-- 단말여신금액 --></th>
			<td>
				<input id="loanAmt" name="loanAmt" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00204"/><!-- 설정일 --></th>
			<td>
				<input id="stpDt" name="stpDt" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M08.LAB00050"/><!-- 요금여신금액 --></th>
			<td>
				<input id="loanArpiAmt" name="loanArpiAmt" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M14.LAB00049"/><!-- 해지일자 --></th>
			<td>
				<input id="termDt" name="termDt" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
			<th><spring:message code="LAB.M12.LAB00008"/><!-- 특기사항 --></th>
			<td>
				<input id="rmrk" name="rmrk" type="text"  class="w365 not-active" disabled="disabled" />
			</td>
		</tr>
		
	</tbody>
</table>


<div class="main_btn_box">
	<div class="fl">
		<a class="grey-btn" title="<spring:message code='LAB.M06.LAB00073'/>" id="btnInsert" href="#" ><spring:message code="LAB.M06.LAB00073"/><!-- 보증보험등록 --></a>
	</div>

	<div class="fr">
		<!--초기화-->		
		<a class="white-btn not-active" href="#" id="btn_init" disabled="disabled"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
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
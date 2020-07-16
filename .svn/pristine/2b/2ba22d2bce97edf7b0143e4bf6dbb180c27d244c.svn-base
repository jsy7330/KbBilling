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
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();
		});
		
		
		//조직검색 팝업
		$("#btnSearchOrg").click(function() {
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			var param = new Object();
			//param.orgId = $("#orgId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchOrgId";	//리턴받는 orgId 의 태그아이디값
			param.returnId2 = "searchOrgNm";	//리턴받는 orgNm 의 태그아이디값
			
			openModalPopup(url, param);
		});
		
		//제품검색 팝업
		$("#btnSearchItem").click(function() {
			var url="/system/common/common/productMng/productSearchPopup.ajax";
			var param = new Object();
			param.soId = $("#searchSoId").val();
			param.popType = "m";				//팝업타입 m:모달 o:일반
			param.returnId1 = "searchItemId";	//리턴받는 Id 의 태그아이디값
			param.returnId2 = "searchItemNm";	//리턴받는 Nm 의 태그아이디값
			
			$("#popup_dialog").css("width", 800);
			openModalPopup(url, param);
		});
		
		
		//등록
		$("#btn_insert").click(function() {
			
			
			var url="productStatusInsertPopUp.ajax";
			var param = new Object();
			$("#popup_dialog").css("width", 900);
			
			openModalPopup(url, param);
		});
		
		
	});
	
	
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#searchSoId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		
		$("#productStatusTable").jqGrid({
			
		   	url:'eqtStatListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M08.LAB00139" />', name: 'eqtSeq', width : 100},		//일련번호
				{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', width : 100},			//색상
	   			{ label: '<spring:message code="LAB.M06.LAB00068" />', name: 'ownLocNm', width : 100},		//보유조직
	   			{ label: '<spring:message code="LAB.M05.LAB00046" />', name: 'lgstProcStatNm', width : 100},		//물류처리상태
	   			{ label: '<spring:message code="LAB.M03.LAB00016" />', name: 'eqtUseNm', width : 100},		//단말기용도
	   			{ label: '<spring:message code="LAB.M06.LAB00063" />', name: 'updBfrNm', width : 100},		//변경전상태
	   			{ label: '<spring:message code="LAB.M06.LAB00065" />', name: 'aftrUpdNm', width : 100},		//변경후상태
	   			{ label: '<spring:message code="LAB.M10.LAB00023" />', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDD, align:'center', width : 100},		//처리일자
	   			{ label: '<spring:message code="LAB.M10.LAB00024" />', name: 'updProcId', width : 100},		//처리자
	   			
			    
			    
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#productStatusPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 330,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){

			},
	        loadComplete: function(obj){
	        	$("#productStatusTable").setGridWidth($('#productStatusGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#productStatusTable").setGridWidth($('#productStatusGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#productStatusTable").setGridWidth($('#productStatusGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#productStatusTable").setGridWidth($('#productStatusGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	//그리드 재검색 
	function searchData(){
		
		$("#productStatusTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		param.itemId = $("#searchItemId").val();
		
        $("#productStatusTable").setGridParam({ postData: param }).trigger("reloadGrid");
        
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


<table class="writeview" >
	<colgroup>
		<col style="width:10%;">
		<col style="width:40%;">
		<col style="width:10%;">
		<col style="width:40%;">
	</colgroup>
	<thead> 
		<tr>
			<th><spring:message code="LAB.M06.LAB00068"/><!-- 보유조직 --></th>
			<td>
				<div class="inp_date w280">
					<input type="text" id="searchOrgNm" name="searchOrgNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchOrgId" name="searchOrgId" />
					<a href="#" id="btnSearchOrg" name="btnSearchOrg" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
			</td>
			<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
			<td>
				<select class="w150" id="searchSoId" name="searchSoId">
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
			<td>
				<select id="searchItemTpCd" name="searchItemTpCd" class="w270">
					<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
						<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
					</c:forEach>
				</select>                                        
			</td>
			<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
			<td >
				<div class="inp_date w280">
					<input type="text" id="searchItemNm" name="searchItemNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchItemId" name="searchItemId" />
					<a href="#" id="btnSearchItem" name="btnSearchItem" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div> 
			</td>
		</tr>
		
		<tr>
			<th><spring:message code="LAB.M06.LAB00063"/><!-- 변경전상태 --></th>
			<td>
				<select id="searchBeqtStatCd" name="searchBeqtStatCd" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${eqtStatCd}" var="eqtStatCd" varStatus="status">
						<option value="${eqtStatCd.commonCd}">${eqtStatCd.commonCdNm}</option>
					</c:forEach>
				</select>                                        
			</td>
			<th><spring:message code="LAB.M06.LAB00065"/><!-- 변경후상태 --></th>
			<td >
				<select id="searchAeqtStatCd" name="searchAeqtStatCd" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${eqtStatCd}" var="eqtStatCd" varStatus="status">
						<option value="${eqtStatCd.commonCd}">${eqtStatCd.commonCdNm}</option>
					</c:forEach>
				</select> 
			</td>
		</tr>
		
		<tr>
			<th><spring:message code="LAB.M08.LAB00139"/><!-- 일련번호 --></th>
			<td>
				<input type="text" id="search" name="search" class="w250" />                                      
			</td>
			<th><spring:message code="LAB.M10.LAB00023"/><!-- 처리일자 --></th>
			<td >
				<div class="date_box">
					<div class="inp_date w135">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
					<span class="dash">~</span>
					<div class="inp_date w135">
						<input type="text" id="searchEndDt" name="searchEndDt" class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
		
		
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00111"/><!-- 상태변경내역 --></h4>
	</div>
</div>

<div id="productStatusGrid">
	<table id="productStatusTable" class="w100p"></table>
	<div id="productStatusPager"></div>
</div>


<div class="main_btn_box">
	<div class="fl">
		
	</div>

	<div class="fr">
		<!--초기화-->		
		<a class="grey-btn" href="#" id="btn_init"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
		<!--등록-->
		<ntels:auth auth="${menuAuthC}">
			<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>
			<%-- 	
		<!--수정-->
		<ntels:auth auth="${menuAuthU}">
			<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
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

	
	
	
	
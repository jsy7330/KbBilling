<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
	
		console.log("${session_user}");
		
		$("#searchOrgId").val("${session_user.orgId}");
		$("#searchOrgNm").val("${session_user.orgNm}");
		
		initGrid();
		
		//조회버튼
		$("#btnSearch").click(function() {
			searchData();
		});
		
		//초기화버튼
		$('#btn_init').addClass('white-btn');
		$('#btn_init').removeClass('grey-btn');
		$('#btn_init').addClass('not-active');
		$('#btn_init').attr('disabled',true);
		
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
			
			
			var url="contactProductInsertPopUp.ajax";
			var param = new Object();
			param.orgId = $("#searchOrgId").val();
			$("#popup_dialog").css("width", 900);
			
			openModalPopup(url, param);
		});
		
	});
	
	
	
	function initGrid() {
		
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		
		$("#contactProductTable").jqGrid({
			
		   	url:'ownOrgEqtInfoListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},

	   		 	{ label: '<spring:message code="LAB.M09.LAB00139" />', name: 'orgId', width : 100},				//조직ID
	   			{ label: '<spring:message code="LAB.M09.LAB00147" />', name: 'orgNm', width : 100},			//조직명
	   			{ label: '<spring:message code="LAB.M09.LAB00079" />', name: 'ctOrgId', width : 100},			//접접ID
	   			{ label: '<spring:message code="LAB.M09.LAB00080" />', name: 'ctOrgNm', width : 100},			//접접명
	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', width : 100},			//색상
	   			{ label: '<spring:message code="LAB.M08.LAB00139" />', name: 'eqtSeq', width : 100},			//일련번호
	   			{ label: '<spring:message code="LAB.M01.LAB00161" />', name: 'eqtBarCd', width : 100},			//관리번호
	   			{ label: '<spring:message code="LAB.M09.LAB00083" />', name: 'ctChgDt', formatter: dateTypeFormatterYYYYMMDD, align:'center', width : 100},		//접점변경일
	   			{ label: '<spring:message code="LAB.M09.LAB00084" />', name: 'ctChgId', width : 100},			//접점변경자
	   			{ label: '<spring:message code="LAB.M05.LAB00046" />', name: 'lgstProcStatNm', width : 100},			//물류처리상태
	   			{ label: '<spring:message code="LAB.M03.LAB00044" />', name: 'ctChgDt', formatter: dateTypeFormatterYYYYMMDD, align:'center', width : 100},		//대리점입고일자
	   			{ label: '<spring:message code="LAB.M10.LAB00066" />', name: 'dstrbUtPrc', formatter: 'integer', align:'right', width : 100},	//출고가
	   			
			    
			    
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#contactProductPager',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 330,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){

			},
	        loadComplete: function(obj){
	        	$("#contactProductTable").setGridWidth($('#contactProductGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#contactProductTable").setGridWidth($('#contactProductGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#contactProductTable").setGridWidth($('#contactProductGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#contactProductTable").setGridWidth($('#contactProductGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}
	
	//그리드 재검색 
	function searchData(){
		
		$("#contactProductTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.orgId = $("#searchOrgId").val();
		param.itemTpCd = $("#searchItemTpCd").val();
		param.itemId = $("#searchItemId").val();
		
        $("#contactProductTable").setGridParam({ postData: param }).trigger("reloadGrid");
        
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
				<input type="text" id="searchOrgNm" name="searchOrgNm" class="w250" disabled="disabled" />
				<input type="hidden" id="searchOrgId" name="searchOrgId" />
			</td>
			<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
			<td>
				<select id="searchItemTpCd" name="searchItemTpCd" class="w270">
					<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
						<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
					</c:forEach>
				</select>                                        
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
			<td colspan="3">
				<div class="inp_date w280">
					<input type="text" id="searchItemNm" name="searchItemNm" class="w250" disabled="disabled" />
					<input type="hidden" id="searchItemId" name="searchItemId" />
					<a href="#" id="btnSearchItem" name="btnSearchItem" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div> 
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M06.LAB00067"/><!-- 보유접점별제품내역 --></h4>
	</div>
</div>

<div id="contactProductGrid">
	<table id="contactProductTable" class="w100p"></table>
	<div id="contactProductPager"></div>
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
	
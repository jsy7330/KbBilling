<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/dnUtil.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		$("#searchOrgIdInP").val("${session_user.orgId}");
		$("#searchOrgNmInP").val("${session_user.orgNm}");
		
		
		$('#searchItemTpCdInP').selectmenu({});
		$('#searchEqtStatCdInP').selectmenu({});
		$('#insertOrgIdInP').selectmenu({});
		
		initGridInP();
		
		$("#btnSearchInP").click(function() {
			searchDataInP();
		});
		
		
		//제품검색 팝업
		$("#btnSearchItemInP").click(function() {
			
			var url="/system/common/common/productMng/productSearchPopup.ajax";
			url = url + "?soId=" + "${session_user.soId}" + "&popType=o&returnId1=searchItemIdInP&returnId2=searchItemNmInP";
			
			var width = 815;
			var height = 510;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		$("#btnAddInP").click(function() {
			
			
			var ids = $("#contactInPTable").jqGrid("getGridParam", "selarrrow" );
			
			if(ids.length < 1){
				alert('접점등록할 단말기를 선택해 주세요.');	//접점등록할 단말기를 선택해 주세요.
				return;
			}
			
			var orgNm = $("#insertOrgIdInP").text();
			var orgId = $("#insertOrgIdInP").val();
			
			for(var i=0; i<ids.length; i++){
				
				$("#contactInPTable").jqGrid('setCell', ids[i], 'ctOrgNm', orgNm);
				$("#contactInPTable").jqGrid('setCell', ids[i], 'ctOrgId', orgId);
			}
			
			
		});
		
		
		$("#btnDelInP").click(function() {
			
			var ids = $("#contactInPTable").jqGrid("getGridParam", "selarrrow" );
			
			
			for(var i=0; i<ids.length; i++){
				
				$("#contactInPTable").jqGrid('setCell', ids[i], 'ctOrgNm', ' ');
				$("#contactInPTable").jqGrid('setCell', ids[i], 'ctOrgId', ' ');
			}
			
		});
		
		
		$("#btnInsertInP").click(function() {
			
			
			var ids = $("#contactInPTable").jqGrid("getGridParam", "selarrrow" );
			
			if(ids.length < 1){
				alert('저장할 단말기를 선택해 주세요.');	//저장할 단말기를 선택해 주세요.
				return;
			}
			
			var params = new Array();
			for(var i=0; i<ids.length; i++){
				
				var param = $("#contactInPTable").getRowData(ids[i]);
				param.ctOrgNm = "";
				
				param.ctOrgId = $.trim(param.ctOrgId);
				params.push(param);
			}
			
			console.log(params);
			updateEqtCtOrg(params);
			
		});
	});

	
	function initGridInP() {
		
		var param = new Object();
		param.itemTpCd = $("#searchItemTpCdInP").val();
		param.ownLocCd = $("#searchOrgIdInP").val();
		
		
		$("#contactInPTable").jqGrid({
			
		   	url:'ctOrgEqtInfoListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'ctOrgId' , name: 'ctOrgId', width : 100, hidden:true},
				{ label: 'itemTpCd' , name: 'itemTpCd', width : 100, hidden:true},

				{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},	//제품ID       
				{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},	//제품명
				{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', width : 100},	//색상
				{ label: '<spring:message code="LAB.M03.LAB00017" />', name: 'eqtSeq', width : 100},	//단말기일련번호
				{ label: '<spring:message code="LAB.M03.LAB00014" />', name: 'eqtBarCd', width : 100},	//단말기바코드
				{ label: '<spring:message code="LAB.M09.LAB00078" />', name: 'ctOrgNm', width : 100},	//접점
				{ label: '<spring:message code="LAB.M03.LAB00015" />', name: 'eqtStatNm', width : 100},	//단말기상태
	   		 	
				/* { label: 'ctOrgId' , name: 'ctOrgId', hidden:true,width : 0}, */
				
			    
	   		],

		   	rowNum:99999999,					//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 250,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#contactInPTable").setGridWidth(880,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#contactInPTable").setGridWidth(880,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#contactInPTable").setGridWidth(880,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function searchDataInP(){
		
		$("#contactInPTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.itemTpCd = $("#searchItemTpCdInP").val();
		param.ownLocCd = $("#searchOrgIdInP").val();
		param.itemId = $("#searchItemIdInP").val();
		param.eqtStatCd = $("#searchEqtStatCdInP").val();
		param.eqtSeqStat = $("#searchEqtSeqStartInp").val();
		param.eqtSeqEnd = $("#searchEqtSeqEndInp").val();

		$("#contactInPTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	
	//updateEqtCtOrg
	function updateEqtCtOrg(param){
		
		if(param != false){
			
			param = JSON.stringify(param)
			
			$.ajax({
				url : 'updateEqtCtOrg.json',
				type : 'POST',
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					//if(data.result != "0"){
						
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchDataInP();
						
						//$("#btnClose").trigger('click');
						
					//}
				},
			    error: function(e){
			        alert("Failed to save.");
			    },
				complete : function() {
				}
			});
		}
	}
	
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00085"/><!-- 점점별제품관리 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->


<!-- center -->
<div class="layer_box">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M09.LAB00086"/><!-- 접점별제품등록 --></h4>
		</div>
		<div class="fr mt10">
			<a href="#" class="grey-btn" id="btnSearchInP"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
		</div>
	</div>
	
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M06.LAB00068"/><!-- 보유조직 --></th>
				<td>
					<input type="text" id="searchOrgNmInP" name="searchOrgNmInP" class="w250" disabled="disabled" />
					<input type="hidden" id="searchOrgIdInP" name="searchOrgIdInP" />
				</td>
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
				<td>
					<select id="searchItemTpCdInP" name="searchItemTpCdInP" class="w270">
						<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
							<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
				<td>
					<div class="inp_date w280">
					<input type="text" id="searchItemNmInP" name="searchItemNmInP" class="w250" disabled="disabled" />
					<input type="hidden" id="searchItemIdInP" name="searchItemIdInP" />
					<a href="#" id="btnSearchItemInP" name="btnSearchItemInP" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
				</td>
				<th><spring:message code="LAB.M03.LAB00015"/><!-- 단말기상태 --></th>
				<td>
					<select id="searchEqtStatCdInP" name="searchEqtStatCdInP" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${eqtStatCd}" var="eqtStatCd" varStatus="status">
							<option value="${eqtStatCd.commonCd}">${eqtStatCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00017"/><!-- 단말기일련번호 --></th>
				<td colspan="3">
					<input type="text" id="searchEqtSeqStartInp" naem="searchEqtSeqStartInp" class="w200">
					~       
					<input type="text" id="searchEqtSeqEndInp" naem="searchEqtSeqEndInp" class="w200">                                      
				</td>
			</tr>
		</thead>
	</table> 

	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M03.LAB00013"/><!-- 단말기내역 --></h4>
		</div>
	</div>

	
	<div class="layer_box" id="contactInPGrid" >
		<table id="contactInPTable" class="w100p"></table>
		<div id="contactInPPager"></div>
	</div>
		
	
	<table class="writeview mt10">
		<colgroup>
			<col style="width:15%;">
			<col style="width:85%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M03.LAB00085"/><!-- 등록접점 --></th>
				<td>
					<select id="insertOrgIdInP" name="insertOrgIdInP" class="w270">
						<c:forEach items="${orgList}" var="orgList" varStatus="status">
							<option value="${orgList.orgId}">${orgList.orgNm}</option>
						</c:forEach>
					</select>
					<a class="grey-btn" id="btnAddInP" href="#" >선택</a>
					<a class="grey-btn" id="btnDelInP" href="#" >삭제</a>
				</td>
			</tr>
		</thead>
	</table> 
</div>

<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsertInP"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<!--// center -->	
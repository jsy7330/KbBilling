<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="/scripts/nccbs/dnUtil.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		$('#searchItemTpCdInP').selectmenu({});
		$('#searchSoIdInP').selectmenu({});
		$('#searchEqtStatCdInP').selectmenu({});
		$('#afterUpdCdInP').selectmenu({});
		
		
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
		
		
		//조직검색 팝업
		$("#btnSearchOrgInP").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=searchOrgIdInP&returnId2=searchOrgNmInP";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		
		$("#btnInsertInP").click(function() {
			
			
			var ids = $("#productInPTable").jqGrid("getGridParam", "selarrrow" );
			
			if(ids.length < 1){
				alert('상태변경할 단말기를 선택해 주세요.');	//상태변경할 단말기를 선택해 주세요.
				return;
			}
			
			var stat = $("#afterUpdCdInP").val();
			
			if(stat == ''){
				alert('변경후상태를 선택해 주세요.');	//변경후상태를 선택해 주세요.
				return;
			}
			
			
			var params = new Array();
			for(var i=0; i<ids.length; i++){
				
				var param = $("#productInPTable").getRowData(ids[i]);

				param.eqtStatCd2 = stat;
				
				params.push(param);
			}
			
			console.log(params);
			
			updateEqtStat(params);
			
		});
		
		
	});
	
	
	function initGridInP() {
		
		var param = new Object();
		param.soId = $("#searchSoIdInP").val();
		param.itemTpCd = $("#searchItemTpCdInP").val();
		//param.ownLocCd = $("#searchOrgIdInP").val();
		
		
		$("#productInPTable").jqGrid({
			
		   	url:'eqtStatInfoListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
				{ label: 'eqtStatCd' , name: 'eqtStatCd', hidden:true,width : 0},

				{ label: '<spring:message code="LAB.M03.LAB00017" />', name: 'eqtSeq', width : 80},	//단말기일련번호		   	           
				{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 80},	//제품ID       
				{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},	//제품명
				{ label: '<spring:message code="LAB.M07.LAB00162" />', name: 'colorNm', width : 70},	//색상
				{ label: '<spring:message code="LAB.M06.LAB00068" />', name: 'ownLocNm', width : 100},	//보유조직
				{ label: '<spring:message code="LAB.M05.LAB00046" />', name: 'lgstProcStatNm', width : 100},	//물류처리상태
				{ label: '<spring:message code="LAB.M03.LAB00016" />', name: 'eqtUseNm', width : 80},	//단말기용도
				{ label: '<spring:message code="LAB.M14.LAB00057" />', name: 'eqtStatNm', width : 100},	//현재상태
				{ label: '<spring:message code="LAB.M06.LAB00065" />', name: 'eqtStatCd2', width : 100},	//변경후상태
	   		 	
	   		],

		   	rowNum:99999999,					//한번에 노출되는 row 수
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 250,				//화면 상태에 따라 크기 조절 할 것
			multiselect : true,
			onCellSelect : function(rowId, index, contents, event){
				
	        },
	        loadComplete: function(obj){
	        	$("#productInPTable").setGridWidth(880,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    sortable: { update: function(permutation) {
		    	$("#productInPTable").setGridWidth(880,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#productInPTable").setGridWidth(880,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function searchDataInP(){
		
		$("#productInPTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#searchSoIdInP").val();
		param.itemTpCd = $("#searchItemTpCdInP").val();
		param.ownLocCd = $("#searchOrgIdInP").val();
		param.itemId = $("#searchItemIdInP").val();
		param.eqtStatCd = $("#searchEqtStatCdInP").val();
		param.eqtSeq = $("#searchEqtSeqInp").val();

		$("#productInPTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function updateEqtStat(param){
		
		if(param != false){
			
			param = JSON.stringify(param)
			
			$.ajax({
				url : 'updateEqtStat.json',
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
	<div class="title"><spring:message code="LAB.M09.LAB00115"/><!-- 제품상태변경관리 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->


<!-- center -->
<div class="layer_box">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M09.LAB00114"/><!-- 제품상태변경 --></h4>
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
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
				<td>
					<select id="searchItemTpCdInP" name="searchItemTpCdInP" class="w270">
						<c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
							<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td>
					<select class="w150" id="searchSoIdInP" name="searchSoIdInP">
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>
				</td>
				
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00068"/><!-- 보유조직 --></th>
				<td>
					<div class="inp_date w280">
						<input type="text" id="searchOrgNmInP" name="searchOrgNmInP" class="w250" disabled="disabled" />
						<input type="hidden" id="searchOrgIdInP" name="searchOrgIdInP" />
						<a href="#" id="btnSearchOrgInP" name="btnSearchOrgInP" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
					
				</td>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
				<td>
					<div class="inp_date w280">
						<input type="text" id="searchItemNmInP" name="searchItemNmInP" class="w250" disabled="disabled" />
						<input type="hidden" id="searchItemIdInP" name="searchItemIdInP" />
						<a href="#" id="btnSearchItemInP" name="btnSearchItemInP" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
				
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00015"/><!-- 단말기상태 --></th>
				<td>
					<select id="searchEqtStatCdInP" name="searchEqtStatCdInP" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${eqtStatCd}" var="eqtStatCd" varStatus="status">
							<option value="${eqtStatCd.commonCd}">${eqtStatCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M03.LAB00017"/><!-- 단말기일련번호 --></th>
				<td>
					<input type="text" id="searchEqtSeqInp" naem="searchEqtSeqInp" class="w200">
				</td>
			</tr>
		</thead>
	</table> 

	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M03.LAB00013"/><!-- 단말기내역 --></h4>
		</div>
	</div>

	
	<div class="layer_box" id="productInPGrid" >
		<table id="productInPTable" class="w100p"></table>
		<div id="productInPPager"></div>
	</div>
		
	
	<table class="writeview mt10">
		<colgroup>
			<col style="width:15%;">
			<col style="width:85%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M06.LAB00065"/><!-- 변경후상태 --></th>
				<td>
					<select id="afterUpdCdInP" name="afterUpdCdInP" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${eqtStatCd}" var="eqtStatCd" varStatus="status">
							<option value="${eqtStatCd.commonCd}">${eqtStatCd.commonCdNm}</option>
						</c:forEach>
					</select>
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
	
	
	
	
	
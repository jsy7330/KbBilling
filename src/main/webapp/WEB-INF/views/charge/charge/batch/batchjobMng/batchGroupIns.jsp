<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {

	pageInit();

	//그리드 처리
	$("#batGrpGrid").jqGrid({
		url : '/charge/charge/batch/batchjobMng/batchGroupList',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'soId', width : 100, align:"center", hidden:true},
		    { label: 'batGrpTp', name: 'batGrpTp', width : 100, align:"center", hidden:true},
		    { label: 'batProcTp', name: 'batProcTp', width : 100, align:"center", hidden:true},
		    // { label: '사업', name: 'soNm', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M06.LAB00121"/>', name: 'batGrpId', width : 100, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M06.LAB00122"/>', name: 'batGrpNm', width : 200, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M06.LAB00123"/>', name: 'batGrpTpNm', width : 100, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M06.LAB00124"/>', name: 'batProcTpNm', width : 150, sortable:false},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "batchGroupList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#batGrpGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#batGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#batGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	$("#batchProgramGrid").jqGrid({
		url : '/charge/charge/batch/batchjobMng/batchProgramForBatchGroup',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: '<spring:message code="LAB.M06.LAB00125"/>', name: 'batPgmId', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M06.LAB00126"/>', name: 'pgmNm', width : 100, align:"left", sortable:false}		    
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 240,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "batchProgramList"
		},
        rowNum: -1,
        onSelectRow : function(rowid, status, event){
        },
       	loadComplete : function () {
       		$("#batchProgramGrid").jqGrid('hideCol', 'cb');
  	      	$("#batchProgramGrid").setGridWidth($('#batchProgramGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#batchProgramGrid").setGridWidth($('#batchProgramGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	$("#batchWorkMapGrid").jqGrid({
		url : '/charge/charge/batch/batchjobMng/batchWorkMapList',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: '<spring:message code="LAB.M09.LAB00230"/>', name: 'wrkProcOrd', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M06.LAB00125"/>', name: 'batPgmId', width : 100, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M06.LAB00127"/>', name: 'pgmNm', width : 100, sortable:false,index:'USE_YN',editable: true, edittype: 'select', editoptions: { value: "Y:Yes;N:No" }}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 240,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "batchWorkMapList"
		},
        rowNum: -1,
        onCellSelect : function(rowid, index, contents, event){
        },
       	loadComplete : function (data) {
  	      	$("#batchWorkMapGrid").setGridWidth($('#batchWorkMapGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	$("#batchWorkMapGrid").setGridParam({
				datatype: 'local'
			});

  	      	var rowIds = $("#batchWorkMapGrid").jqGrid('getDataIDs');
  	      	if(rowIds.length > 0){
  	      		btnEnable('updateWorkUserBtn');
				btnEnable('deleteWorkUserBtn');	
  	      	}else{
  	      		btnDisable('updateWorkUserBtn');
				btnDisable('deleteWorkUserBtn');	
  	      	}
  	      	
        },
    	sortable: { update: function(permutation) {
    		$("#batchWorkMapGrid").setGridWidth($('#batchWorkMapGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
    	afterEditCell:function (rowid, cellname, value, iRow, iCol){
    		$("#" + iRow+'_'+cellname).selectmenu().selectmenu( "option", "width", "100%" );
		},
		afterSaveCell (rowid, cellname, value, iRow, iCol){
			if(cellname == 'USE_YN_NM'){
				$("#batchWorkMapGrid").jqGrid('setCell', rowid, 'USE_YN', value);
			}else if(cellname == 'SMS_YN_NM'){
				$("#batchWorkMapGrid").jqGrid('setCell', rowid, 'SMS_YN', value);
			}
		} 
	});
	$("#batGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#batchProgramGrid").setGridWidth($('#batchProgramGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#batchWorkMapGrid").setGridWidth($('#batchWorkMapGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#batGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#batchProgramGrid").setGridWidth($('#batchProgramGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#batchWorkMapGrid").setGridWidth($('#batchWorkMapGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	//작업명 조회 이벤트
	$( "#searchForm" ).keypress(function(event) {
		if($("#searchBtn").hasClass('not-active')){
			return;
		}
      if(event.keyCode == 13){
        searchWorkGrpList();
      }
    });

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
	    	if($("#searchBtn").hasClass('not-active')){
				return;
			}
			searchBatchGroupList();
		}
    );


    //초기화 버튼 이벤트
   	$('#initBtn').on('click',function (e) {
	   		if($("#initBtn").hasClass('not-active')){
				return;
			}
    		initBtn();
		}
    );

    //신규등록 버튼 이벤트
    $('#newBtn').on('click',function (e) {
      	if($("#newBtn").hasClass('not-active')){
          return;
  		  }
    		insertBtn();
		  }
    );

    //수정 버튼 이벤트
    $('#updateBtn').on('click',function (e) {
      	if($("#updateBtn").hasClass('not-active')){
          return;
  		  }
    		updateBtn();
		  }
    );

    //삭제 버튼 이벤트
    $('#deleteBtn').on('click',function (e) {
      	if($("#deleteBtn").hasClass('not-active')){
          return;
  		  }
    		deleteBtn();
		  }
    );

    //워크 맵 추가 버튼
    $('#addWorkMapBtn').on('click',function (e) {
      	if($("#addWorkMapBtn").hasClass('not-active')){
          return;
  		  }
    		addBatchWorkMap();
		  }
    );

    $('#deleteWorkMapBtn').click(function () {
      	if($("#deleteWorkMapBtn").hasClass('not-active')){
			return;
		}

	  	deleteWorkMap();
    })
});

/*
 * 페이지 초기화
 */
function pageInit(){

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#batGrpGrid").clearGridData();
	$("#batchProgramGrid").clearGridData();
	$("#batchWorkMapGrid").clearGridData();

	$('#insertForm')[0].reset();

	$('#insertForm input[name="batGrpId"]').addClass('not-active');
	$('#insertForm input[name="batGrpId"]').attr('disabled', true);
	$("#insertForm select").selectmenu('refresh');
	$("#insertForm select").selectmenu('enable');


}

function searchBatchGroupList() {

	pageInit();
	
	$("#batGrpGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
			soId: $('#searchForm input[name="soId"]').val()
			, batGrpNm: $('#searchForm input[name="batGrpNm"]').val()
			, batGrpTp: $('#searchForm input[name="batGrpTp"]').val()
			, batProcTp: $('#searchForm input[name="batProcTp"]').val()
		}
	});

   	$("#batGrpGrid").trigger("reloadGrid");

}

/*
 * 작업그룹 선택 이벤트
 */

function setSelectedData(rowId){
	var data = $("#batGrpGrid").getRowData(rowId);

	$('#insertForm input[name="batGrpId"]').val(data.batGrpId);
	$('#insertForm input[name="batGrpNm"]').val(data.batGrpNm);
	$('#insertForm select[name="batGrpTp"]').val(data.batGrpTp);
	$('#insertForm select[name="batProcTp"]').val(data.batProcTp);
	$('#insertForm select').selectmenu('refresh');

	$("#batchProgramGrid").clearGridData();
	$("#batchWorkMapGrid").clearGridData();

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnEnable('updateBtn');
	btnEnable('deleteBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#batchProgramGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			batGrpId: data.batGrpId
		}
	});
   	$("#batchProgramGrid").trigger("reloadGrid");	

   	$("#batchWorkMapGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			batGrpId: data.batGrpId
		}
	});
   	$("#batchWorkMapGrid").trigger("reloadGrid");	
}

/*
 * 초기화 버튼
 */
function initBtn(){

	btnEnable('initBtn');
	btnEnable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#batchProgramGrid").clearGridData();
	$("#batchWorkMapGrid").clearGridData();

	$('#insertForm')[0].reset();

	$('#insertForm input[name="batGrpId"]').addClass('not-active');
	$('#insertForm input[name="batGrpId"]').attr('disabled', true);
	$("#insertForm select").selectmenu('refresh');
	$("#insertForm select").selectmenu('enable');
	$('#insertForm input[name="batGrpNm"]').focus();

	$("#batchProgramGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
		postData: {
			batGrpId: ''
		}
	});

   	$("#batchProgramGrid").trigger("reloadGrid");	

}

function createBatchGroupInfo() {

	var params = {
		batGrpId: $('#insertForm input[name="batGrpId"]').val()
		, batGrpNm: $('#insertForm input[name="batGrpNm"]').val()
		, batProcTp: $('#insertForm select[name="batProcTp"]').val()
		, batGrpTp: $('#insertForm select[name="batGrpTp"]').val()
	}

	var idList = $('#batchWorkMapGrid').getDataIDs();

	for (var i = 0; i < idList.length; i++) {
		var rowData = $('#batchWorkMapGrid').getRowData(idList[i]);
		params['batchWorkMapList[' + i + '].batPgmId'] = rowData.batPgmId;
		params['batchWorkMapList[' + i + '].wrkProcOrd'] = i + 1;
	}

	return params;
}

/*
 * 신규등록처리
 */
function insertBtn(){

	$.post('insertBatchGroup', createBatchGroupInfo(), function (response) {
		if (response.success == true) {
			alert('<spring:message code="MSG.M07.MSG00084"/>');
			searchBatchGroupList();
			$('#batchProgramGrid').clearGridData();
			$('#batchWorkMapGrid').clearGridData();
		} else {
			alert(response.message);
		}
	});
}


/*
 * 수정처리
 */
function updateBtn(){

	var isConfirm = confirm('<spring:message code="MSG.M07.MSG00088"/>');

	if (isConfirm == false) {
		return;
	}

	$.post('updateBatchGroup', createBatchGroupInfo(), function (response) {
		if (response.success == true) {
			alert('<spring:message code="MSG.M07.MSG00089"/>');
			searchBatchGroupList();
		} else {
			alert(response.message);
		}
	});
}

/*
 * 삭제처리
 */
function deleteBtn(){

	var isConfirm = confirm('<spring:message code="MSG.M07.MSG00052"/>');

	if (isConfirm == false) {
		return;
	}

	var params = {
		batGrpId: $('#insertForm input[name="batGrpId"]').val()
	}

	$.post('deleteBatchGroup', params, function (response) {
		if (response.success == true) {
			alert('<spring:message code="MSG.M07.MSG00053"/>');
			searchBatchProgramList();
		} else {
			alert(response.message);
		}
	});
	
//	$("#batGrpGrid").trigger("reloadGrid");
	
}
/**
 * 수정 CHANHEE
 */
 function searchBatchProgramList(){
	
	 $("#batGrpGrid").trigger("reloadGrid");
	 
	 $('#insertForm')[0].reset();

		$('#insertForm input[name="batGrpId"]').addClass('not-active');
		$('#insertForm input[name="batGrpId"]').attr('disabled', true);
		$("#insertForm select").selectmenu('refresh');
		$("#insertForm select").selectmenu('enable');
}
function addBatchWorkMap() {

	var rowId = $('#batchProgramGrid').getGridParam('selrow');
	
	if (rowId == null || rowId <= 0) {
		return;
	}

	var rowData = $('#batchProgramGrid').getRowData(rowId);
	$('#batchProgramGrid').delRowData(rowId);

	var wrkProcOrd = $('#batchWorkMapGrid').getGridParam("records") + 1;
	$('#batchWorkMapGrid').setGridParam({
 		rowNum: wrkProcOrd
	});

	rowData.wrkProcOrd = wrkProcOrd;

	$('#batchWorkMapGrid').addRowData (undefined, rowData);

}

function deleteWorkMap() {
	var rowId = $('#batchWorkMapGrid').getGridParam('selrow');
	var rowData = $('#batchWorkMapGrid').getRowData(rowId);
	$('#batchProgramGrid').addRowData (undefined, rowData);

	$('#batchWorkMapGrid').delRowData(rowId);
}

/*
 * 버튼 비활성화 처리
 */
function btnDisable(id){
	$('#' + id ).addClass('white-btn');
	$('#' + id ).removeClass('grey-btn');
	$('#' + id ).addClass('not-active');
	$('#' + id ).attr('disabled',true);
	
}

/*
 * 버튼 활성화 처리
 */
function btnEnable(id){
	$('#' + id ).addClass('grey-btn');
	$('#' + id ).removeClass('white-btn');
	$('#' + id ).removeClass('not-active');
	$('#' + id ).removeAttr('disabled');
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


<!-- 검색 버튼 -->
<div class="main_btn_box">
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='searchBtn' href="#" class="grey-btn" title='<spring:message code="LAB.M09.LAB00158"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</ntels:auth>
</div>


<!-- 검색부 -->
<form id="searchForm">
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<thead>
			<tr>
<!-- 				<th><spring:message code="LAB.M07.LAB00003" /></th>
				<td>
					<select name="soId" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
								<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>
				</td> -->
				<th><spring:message code="LAB.M06.LAB00122"/></th>
				<td colspan="3">
					<input name="batGrpNm" type="text" class="w100p" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00123"/></th>
				<td>
					<select name="batGrpTp" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${batchGroupTp}" var="batchGroupTp" varStatus="status">
							<option value="${batchGroupTp.commonCd}">${batchGroupTp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M06.LAB00124"/></th>
				<td>
					<select name="batProcTp" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${batchTrtTp}" var="batchTrtTp" varStatus="status">
							<option value="${batchTrtTp.commonCd}">${batchTrtTp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</thead>
	</table> 
</form>

<!--배치그룹표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M06.LAB00128"/></h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="batGrpGrid" class="w100p"></table>
	<div id="batGrpGridPager"></div>
</div>

<!--작업그룹상세-->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M06.LAB00129"/></h4>
	</div>
</div>

<form id="insertForm">
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:30%;">
			<col style="width:20%;">
			<col style="width:30%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M06.LAB00121"/><span class="dot">*</span></th>
				<td>
					<input name="batGrpId" type="text" class="w100p" />
				</td>
				<th><spring:message code="LAB.M06.LAB00122"/><span class="dot">*</span></th>
				<td>
					<input name="batGrpNm" type="text" class="w100p" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M06.LAB00124"/><span class="dot">*</span></th>
				<td>
					<select name="batProcTp" class="w100p">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${batchTrtTp}" var="batchTrtTp" varStatus="status">
							<option value="${batchTrtTp.commonCd}">${batchTrtTp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M06.LAB00123"/><span class="dot">*</span></th>
				<td>
					<select name="batGrpTp" class="w100p">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${batchGroupTp}" var="batchGroupTp" varStatus="status">
							<option value="${batchGroupTp.commonCd}">${batchGroupTp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</thead>
	</table>
</form>

<div class="main_btn_box">
	<div class="fr">
		<span id="commonBtn">
			<ntels:auth auth="${menuAuthR}">
			<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthC}">
			<a id="newBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthU}">
			<a id="updateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthD}">
			<a id="deleteBtn"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</ntels:auth>
		</span>
	</div>
</div>

<!--사용자부 -->
<div class="lay_box">
	<div class="lay_left">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title"><spring:message code="LAB.M03.LAB00055"/></h4>
			</div>
		</div>
		<div id='batchProgramGridDiv'>
			<table id="batchProgramGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fr">
				<span id="batchProgramBtn">
					<ntels:auth auth="${menuAuthU}">
					<a id="addWorkMapBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00059"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M10.LAB00059"/></span></a>
					</ntels:auth>
				</span>
			</div>
		</div>-
	</div>
	<div class="lay_right">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title"><spring:message code="LAB.M03.LAB00075"/> </h4>
			</div>
		</div>
		<div id='batchWorkMapGridDiv'>
			<table id="batchWorkMapGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fr">
				<span id="workUserBtn">
					<ntels:auth auth="${menuAuthD}">
					<a id="deleteWorkMapBtn"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
					</ntels:auth>
				</span>
			</div>
		</div>
	</div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>


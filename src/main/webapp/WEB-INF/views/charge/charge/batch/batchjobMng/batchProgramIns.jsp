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
	$("#batPgmGrid").jqGrid({
		url : '/charge/charge/batch/batchjobMng/getBatchProgramList',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'soId', width : 100, align:"center", hidden:true},
		    { label: 'clsStpCl', name: 'clsStpCl', width : 100, hidden: true},
		    { label: 'clsTskCl', name: 'clsTskCl', width : 100, hidden: true},
		    { label: '<spring:message code="LAB.M13.LAB00035"/>', name: 'batPgmId', width : 100, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M13.LAB00022"/>', name: 'pgmNm', width : 100, sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00314"/>', name: 'execObj', width : 150, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M13.LAB00014"/>', name: 'pkgNm', width : 150, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M05.LAB00056"/>', name: 'clsStpNm', width : 100, align:"center", sortable:false},
		    { label: '<spring:message code="LAB.M05.LAB00057"/>', name: 'clsTskNm', width : 100, sortable:false},
		    { label: '<spring:message code="LAB.M13.LAB00038"/>', name: 'pgmCt', width : 150, sortable:false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 240,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "batchProgramList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#batPgmGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#batPgmGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#batPgmGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#batPgmGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	//작업명 조회 이벤트
	$( "#searchForm" ).keypress(function(event) {
		if($("#searchBtn").hasClass('not-active')){
			return;
		}
      if(event.keyCode == 13){
        searchBatchProgramList();
      }
    });

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
	    	if($("#searchBtn").hasClass('not-active')){
				return;
			}
			searchBatchProgramList();
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

	$('#workGrpNmTxt').keyup(function(){
	  		var str = getMaxStr($('#workGrpNmTxt').val(), 30);
	  		if(str != $('#workGrpNmTxt').val()){
	  			$('#workGrpNmTxt').val(str);
	  		}
  		}
	);
});

/*
 * 페이지 초기화
 */
function pageInit() {
	$('#insertForm input[name="batPgmId"]').attr('disabled', true);
	initBtn();
}

// 배치 프로그램 리스트 조회
function searchBatchProgramList() {

	pageInit();
	
	$("#batPgmGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			batPgmId: $('#searchForm input[name="batPgmId"]').val()
  			, pgmNm: $('#searchForm input[name="pgmNm"]').val()
		}
	});

   	$("#batPgmGrid").trigger("reloadGrid");	

}

/*
 * 작업그룹 선택 이벤트
 */

function setSelectedData(rowId){
	var data = $("#batPgmGrid").getRowData(rowId);

	$('#insertForm input[name="batPgmId"]').val(data.batPgmId);
	$('#insertForm input[name="batPgmId"]').attr('disabled', true)

	$('#insertForm input[name="pgmNm"]').val(data.pgmNm);
	$('#insertForm input[name="execObj"]').val(data.execObj);
	$('#insertForm input[name="pkgNm"]').val(data.pkgNm);
	$('#insertForm select[name="clsStpCl"]').val(data.clsStpCl);
	$('#insertForm select[name="clsTskCl"]').val(data.clsTskCl);
	$('#insertForm textarea[name="pgmCt"]').val(data.pgmCt);

    $("#insertForm select").selectmenu('refresh');
	$("#insertForm select").selectmenu('enable');

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnEnable('updateBtn');
	btnEnable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');


}

/*
 * 초기화 버튼
 */
function initBtn(){

	btnEnable('initBtn');
	btnEnable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

    $('#insertForm input').val('');
	$('#insertForm input').removeClass('not-active');
    $('#insertForm input').removeAttr('disabled');
    $('#insertForm select').val('');
    $('#insertForm select').selectmenu('refresh');
	$('#insertForm select').selectmenu('enable');
	$('#insertForm textarea').val('');
}

function getEditParams() {

	var params = {
		batPgmId: $('#insertForm input[name="batPgmId"]').val()
		, pgmNm: $('#insertForm input[name="pgmNm"]').val()
		, execObj: $('#insertForm input[name="execObj"]').val()
		, pkgNm: $('#insertForm input[name="pkgNm"]').val()
		, clsStpCl: $('#insertForm select[name="clsStpCl"]').val()
		, clsTskCl: $('#insertForm select[name="clsTskCl"]').val()
		, pgmCt: $('#insertForm textarea[name="pgmCt"]').val()
	}

	return params;

}

/*
 * 신규등록처리
 */
function insertBtn(){

	$.post('insertBatchProgram', getEditParams(), function (response) {
		if (response.success == true) {
			alert('<spring:message code="MSG.M07.MSG00084"/>');
			searchBatchProgramList();
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

	$.post('updateBatchProgram', getEditParams(), function (response) {
		if (response.success == true) {
			alert('<spring:message code="MSG.M07.MSG00089"/>');
			searchBatchProgramList();
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
		batPgmId: $('#insertForm input[name="batPgmId"]').val()
	}

	$.post('deleteBatchProgram', params, function (response) {
		if (response.success == true) {
			alert('<spring:message code="MSG.M07.MSG00053"/>');
			searchBatchProgramList();
		} else {
			alert(response.message);
		}
	});
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
				<th><spring:message code="LAB.M13.LAB00035"/></th>
				<td>
					<input name="batPgmId" type="text" class="w100p" />
				</td>
				<th><spring:message code="LAB.M13.LAB00022"/></th>
				<td>
					<input name="pgmNm" type="text" class="w100p" />
				</td>
			</tr>
		</thead>
	</table> 
</form>

<!--작업그룹표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M13.LAB00036"/></h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="batPgmGrid" class="w100p"></table>
	<div id="batPgmGridPager"></div>
</div>

<!--작업그룹상세-->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M13.LAB00037"/></h4>
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
				<th><spring:message code="LAB.M13.LAB00022"/><span class="dot">*</span></th>
				<td>
					<input name="pgmNm" type="text" class="w100p" />
				</td>
				<th><spring:message code="LAB.M13.LAB00035"/><span class="dot">*</span></th>
				<td>
					<input name="batPgmId" type="text" class="w100p" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00314"/><span class="dot">*</span></th>
				<td>
					<input name="execObj" type="text" class="w100p" />
				</td>
				<th><spring:message code="LAB.M13.LAB00014"/><span class="dot">*</span></th>
				<td>
					<input name="pkgNm" type="text" class="w100p"/>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M05.LAB00057"/><span class="dot">*</span></th>
				<td>
					<select name="clsTskCl" class="w100p">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${clsTskClList}" var="clsTskCl" varStatus="status">
							<option value="${clsTskCl.commonCd}">${clsTskCl.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M05.LAB00056"/><span class="dot">*</span></th>
				<td>
					<select name="clsStpCl" class="w100p">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${clsStpClList}" var="clsStpCl" varStatus="status">
							<option value="${clsStpCl.commonCd}">${clsStpCl.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M13.LAB00038"/><span class="dot">*</span></th>
				<td colspan="3">
					<textarea name="pgmCt" class="w100p h50" maxlength = "1500"></textarea>
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



<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>


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
	$("#workGrpGrid").jqGrid({
		url : '/product/service/serviceMgt/workGrpMng/getWorkGrpListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
		    { label: '적용년월', name: 'SO_NM', width : 100, align:"left", sortable:false},
		    { label: '사업', name: 'SVC_WRK_GRP_ID', width : 100, align:"center", sortable:false},
		    { label: '조직ID', name: 'SVC_WRK_GRP_NM', width : 200, align:"left", sortable:false},
		    { label: '조직명', name: 'USE_YN_NM', width : 100, align:"center", sortable:false},
		    { label: '수수료명', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: 'From(건수or금액)', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: 'To(건수or금액)', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '지급률(%)', name: 'CHGR_NM', width : 150, sortable:false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "workGrpList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#workGrpGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	$("#userGrid").jqGrid({
		url : '/product/service/serviceMgt/workGrpMng/getUserListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: '배치프로그램ID', name: 'ORG_ID', width : 100, align:"left", sortable:false},
		    { label: '배치프로그램명', name: 'ORG_NM', width : 100, align:"left", sortable:false}		    
		],
		viewrecords: true,
		shrinkToFit:false,
		multiselect: true,
		height: 240,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "userList"
		},
        rowNum: -1,
        onSelectRow : function(rowid, status, event){
        	var rowIds = $("#userGrid").jqGrid('getGridParam', 'selarrrow');
        	if(rowIds.length == 0){
        		btnDisable('addUserBtn');
        	}else{
        		btnEnable('addUserBtn');
        	}
        },
       	loadComplete : function () {
       		$("#userGrid").jqGrid('hideCol', 'cb');
  	      	$("#userGrid").setGridWidth($('#userGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#userGrid").setGridWidth($('#userGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	$("#workGrpUserGrid").jqGrid({
		url : '/product/service/serviceMgt/workGrpMng/getWorkGrpUserListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
			{ label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
			{ label: 'smsYn', name: 'SMS_YN', width : 100, align:"center", hidden:true},
		    { label: '작업순서', name: 'USER_ID', width : 100, align:"left", sortable:false},
		    { label: '배치프로그램ID', name: 'USER_NAME', width : 100, align:"left", sortable:false},
		    { label: '배치프로그램', name: 'USE_YN_NM', width : 100, sortable:false,index:'USE_YN',editable: true, edittype: 'select', editoptions: { value: "Y:Yes;N:No" }}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 240,
		cellEdit: true,
		sortable : true,
		multiselect: true,
		cellsubmit: 'clientArray',
		jsonReader: {
			repeatitems : true,
			root : "workGrpUserList"
		},
        rowNum: -1,
        onCellSelect : function(rowid, index, contents, event){
        },
       	loadComplete : function (data) {
  	      	$("#workGrpUserGrid").setGridWidth($('#workGrpUserGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
  	      	$("#workGrpUserGrid").setGridParam({
				datatype: 'local'
			});

  	      	var rowIds = $("#workGrpUserGrid").jqGrid('getDataIDs');
  	      	if(rowIds.length > 0){
  	      		btnEnable('updateWorkUserBtn');
				btnEnable('deleteWorkUserBtn');	
  	      	}else{
  	      		btnDisable('updateWorkUserBtn');
				btnDisable('deleteWorkUserBtn');	
  	      	}
  	      	
        },
    	sortable: { update: function(permutation) {
    		$("#workGrpUserGrid").setGridWidth($('#workGrpUserGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
    	afterEditCell:function (rowid, cellname, value, iRow, iCol){
    		$("#" + iRow+'_'+cellname).selectmenu().selectmenu( "option", "width", "100%" );
		},
		afterSaveCell (rowid, cellname, value, iRow, iCol){
			if(cellname == 'USE_YN_NM'){
				$("#workGrpUserGrid").jqGrid('setCell', rowid, 'USE_YN', value);
			}else if(cellname == 'SMS_YN_NM'){
				$("#workGrpUserGrid").jqGrid('setCell', rowid, 'SMS_YN', value);
			}
		} 
	});
	$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#userGrid").setGridWidth($('#userGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#workGrpUserGrid").setGridWidth($('#workGrpUserGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#userGrid").setGridWidth($('#userGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#workGrpUserGrid").setGridWidth($('#workGrpUserGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});


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
function pageInit(){

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#workGrpGrid").clearGridData();
	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

	$('#workGrpIdTxt').val('');
	$('#workGrpIdTxt').addClass('not-active');
    $('#workGrpIdTxt').attr('disabled', true);
    $('#workGrpNmTxt').val('');
	$('#workGrpNmTxt').addClass('not-active');
    $('#workGrpNmTxt').attr('disabled', true);
    $("#workGrpSoSel").val('SEL');
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('disable');
    $("#workGrpUseYnSel").val('SEL');
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('disable');
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


	$('#workGrpIdTxt').val('');
	$('#workGrpIdTxt').addClass('not-active');
    $('#workGrpIdTxt').attr('disabled', true);
    $('#workGrpNmTxt').val('');
	$('#workGrpNmTxt').removeClass('not-active');
    $('#workGrpNmTxt').removeAttr('disabled');
    $("#workGrpSoSel").val('SEL');
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('enable');
    $("#workGrpUseYnSel").val('SEL');
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('enable');
	$('#workGrpSoSel-button').focus();
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
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:40%;">
		<col style="width:10%;">
		<col style="width:40%;">
	</colgroup>
	<thead>
		<tr>
			<th>수수료구분</th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${chargeTp}" var="chargeTp" varStatus="status">
						<option value="${chargeTp.commonCd}">${chargeTp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th>조직ID</th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w120" />
					<input id="condCustNm" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
		<tr>
			<th>적용년월</th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th>수수료코드</th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${chargeCode}" var="chargeCode" varStatus="status">
						<option value="${chargeCode.commonCd}">${chargeCode.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
</table> 

<!--작업그룹표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">조회내역</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>

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


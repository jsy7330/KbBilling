<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {

	pageInit();

	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "-7"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
	

	if($(".datepicker1").length > 0){
		$( ".datepicker1" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "0"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker1.disabled" ).datepicker( "option", "disabled", true );

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
		    { label: '정책명', name: 'SO_NM', width : 100, align:"left", sortable:false},
		    { label: '정책ID', name: 'SVC_WRK_GRP_ID', width : 100, align:"center", sortable:false},
		    { label: '사업', name: 'SVC_WRK_GRP_NM', width : 200, align:"left", sortable:false},
		    { label: '정책채널', name: 'USE_YN_NM', width : 100, align:"center", sortable:false},
		    { label: '접점유형', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '적용년월', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '적용시작일', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '적용종료일', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '계산유형', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '지급방식', name: 'CHGR_NM', width : 150, sortable:false}
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
		    { label: '단말모델', name: 'ORG_ID', width : 100, align:"left", sortable:false},
		    { label: '개통구분', name: 'ORG_NM', width : 100, align:"left", sortable:false},
		    { label: '사업자', name: 'ORG_NM', width : 100, align:"left", sortable:false},
		    { label: '할인구분', name: 'ORG_NM', width : 100, align:"left", sortable:false},
		    { label: '할부등급', name: 'ORG_NM', width : 100, align:"left", sortable:false},
		    { label: '할부금액(F)', name: 'ORG_NM', width : 100, align:"left", sortable:false},
		    { label: '할부금액(T)', name: 'ORG_NM', width : 100, align:"left", sortable:false},
		    { label: '수수료', name: 'ORG_NM', width : 100, align:"left", sortable:false}		    
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
		    { label: '단말모델', name: 'USER_ID', width : 100, align:"left", sortable:false},
		    { label: '상품명', name: 'USER_NAME', width : 100, align:"left", sortable:false},
		    { label: '수수료', name: 'USE_YN_NM', width : 100, sortable:false,index:'USE_YN',editable: true, edittype: 'select', editoptions: { value: "Y:Yes;N:No" }}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 240,
		cellEdit: true,
		sortable : true,
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

	//작업명 조회 이벤트
	$( "#condWorkGrpNm" ).keypress(function(event) {
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
    		searchWorkGrpList();
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

    //사용자 추가 버튼
    $('#addUserBtn').on('click',function (e) {
      	if($("#addUserBtn").hasClass('not-active')){
          return;
  		  }
    		addUserBtn();
		  }
    );

    //사용자수정버튼
    $('#updateWorkUserBtn').on('click',function (e) {
      	if($("#updateWorkUserBtn").hasClass('not-active')){
          return;
  		  }
    		updateWorkUserBtn();
		  }
    );

    //사용자삭제버튼
    $('#deleteWorkUserBtn').on('click',function (e) {
      	if($("#deleteWorkUserBtn").hasClass('not-active')){
          return;
  		  }
    		deleteWorkUserBtn();
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
		<col style="width:8%;">
		<col style="width:25%;">
		<col style="width:8%;">
		<col style="width:25%;">
		<col style="width:8%;">
		<col style="width:25%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M07.LAB00003" /></th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th>적용년월</th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th>접점유형</th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
				</select>
			</td>
		</tr>
		<tr>
			<th>정책채널</th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${policyChanel}" var="policyChanel" varStatus="status">
						<option value="${policyChanel.commonCd}">${policyChanel.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th>계산유형</th>
			<td colspan="3">
				<select id="condSo" class="w40p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${calculationTp}" var="calculationTp" varStatus="status">
						<option value="${calculationTp.commonCd}">${calculationTp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">등급내역</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>

<!--사용자부 -->
<div class="lay_box">
	<div class="lay_left">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title">대상정보</h4>
			</div>
		</div>
		<div id='userGridDiv'>
			<table id="userGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fr">
				<span id="userGridBtn">
					<ntels:auth auth="${menuAuthU}">
					<a id="addUserBtn" class="grey-btn" title='정책일괄등록' href="#">정책일괄등록</span></a>
					<a id="addUserBtn" class="grey-btn" title='정책일괄등록' href="#">정책자동생성</span></a>
					</ntels:auth>
				</span>
			</div>
		</div>
	</div>
	<div class="lay_right">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title">등록 </h4>
			</div>
		</div>
		<div id='workGrpUserGridDiv'>
			<table id="workGrpUserGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fr">
				<span id="workUserBtn">
					<ntels:auth auth="${menuAuthU}">
					<a id="updateWorkUserBtn" class="grey-btn" title='정책삭제' href="#"><span class="edit_icon">삭제</span></a>
					</ntels:auth>
					<ntels:auth auth="${menuAuthD}">
					<a id="deleteWorkUserBtn"  class="grey-btn" title='수정' href="#"><span class="trashcan_icon">수정</span></a>
					</ntels:auth>
					<ntels:auth auth="${menuAuthC}">
					<a id="deleteWorkUserBtn"  class="grey-btn" title='등록' href="#"><span class="trashcan_icon">등록</span></a>
					</ntels:auth>
				</span>
			</div>
		</div>
	</div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>


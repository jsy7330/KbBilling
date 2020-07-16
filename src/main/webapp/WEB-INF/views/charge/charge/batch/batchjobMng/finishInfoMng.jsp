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
		    }).datepicker("setDate", "0"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );

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
		    { label: '청구년월', name: 'SO_NM', width : 100, align:"left", sortable:false},
		    { label: '마감업무구분', name: 'SVC_WRK_GRP_ID', width : 100, align:"center", sortable:false},
		    { label: '마감상태', name: 'SVC_WRK_GRP_NM', width : 200, align:"left", sortable:false},
		    { label: '마감일자', name: 'USE_YN_NM', width : 100, align:"center", sortable:false},
		    { label: '등록자', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '등록일', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '변경자', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '변경일자', name: 'CHGR_NM', width : 150, sortable:false}
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
 * 작업그룹조회
 */
function searchWorkGrpList(){

	pageInit();
	
	$("#workGrpGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			soId : $('#condSo').val(),
        	workGrpNm : $('#condWorkGrpNm').val()
		}
	});
	      
   	$("#workGrpGrid").trigger("reloadGrid");	
}

/*
 * 작업그룹 선택 이벤트
 */

function setSelectedData(rowId){
	var data = $("#workGrpGrid").getRowData(rowId);

	$('#workGrpIdTxt').val(data.SVC_WRK_GRP_ID);
	$('#workGrpNmTxt').val(data.SVC_WRK_GRP_NM);
	$('#workGrpNmTxt').removeClass('not-active');
	$("#workGrpNmTxt").removeAttr('disabled');
    $("#workGrpUseYnSel").val(data.USE_YN);
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('enable');
	$("#workGrpSoSel").val(data.SO_ID);
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('disable');

	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnEnable('updateBtn');
	btnEnable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#userGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : data.SVC_WRK_GRP_ID
		}
	});
   	$("#userGrid").trigger("reloadGrid");	

   	$("#workGrpUserGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : data.SVC_WRK_GRP_ID
		}
	});
   	$("#workGrpUserGrid").trigger("reloadGrid");	
}

function reloadUserDtl(workGrpId){
	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnEnable('updateBtn');
	btnEnable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#userGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : workGrpId
		}
	});
   	$("#userGrid").trigger("reloadGrid");	

   	$("#workGrpUserGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			svcWrkGrpId : workGrpId
		}
	});
   	$("#workGrpUserGrid").trigger("reloadGrid");	

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
 * 사용자추가
 */
function addUserBtn(){
	var rowIds = $("#userGrid").jqGrid('getGridParam', 'selarrrow');

	var addUserList = [];
	for(var i = 0; i < rowIds.length;i++){
		var userData = $("#userGrid").getRowData(rowIds[i]);
		var addUserInfo = new Object();
		addUserInfo.WORK_GRP_ID = $('#workGrpIdTxt').val();
		addUserInfo.ADD_USER_ID = userData.USER_ID;
		addUserList[i] = addUserInfo;
	}

	var url = '/product/service/serviceMgt/workGrpMng/inserWorkGrpUserAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : JSON.stringify(addUserList),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	reloadUserDtl($('#workGrpIdTxt').val());
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });


}

/*
 * 사용자수정
 */
function updateWorkUserBtn(){
	var data = $("#workGrpUserGrid").getRowData();
	$.each(data, function(index, value){
		$("#workGrpUserGrid").editCell(index,3,false);
		$("#workGrpUserGrid").editCell(index,4,false);
	});
	var selectedRowIds = $("#workGrpUserGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
	var updateDataList = [];
	$.each(selectedRowIds, function(index, value){
		var rowData = $("#workGrpUserGrid").jqGrid('getRowData', value);
		var updateData = new Object();
		updateData.WORK_GRP_ID = $('#workGrpIdTxt').val();
		updateData.USER_ID = rowData.USER_ID;
		updateData.USE_YN = rowData.USE_YN;
		updateData.SMS_YN = rowData.SMS_YN;
		updateDataList[index] = updateData;
	});

	

	if(updateDataList.length == 0){
		alert('<spring:message code="MSG.M07.MSG00073"/>');
		return;
	}
	var url = '/product/service/serviceMgt/workGrpMng/updateWorkGrpUserAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : JSON.stringify(updateDataList),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	reloadUserDtl($('#workGrpIdTxt').val());

        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}


/*
 * 사용자삭제
 */
function deleteWorkUserBtn(){
	var selectedRowIds = $("#workGrpUserGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
	var deleteDataList = [];
	$.each(selectedRowIds, function(index, value){
		var rowData = $("#workGrpUserGrid").jqGrid('getRowData', value);
		var deleteData = new Object();
		deleteData.WORK_GRP_ID = $('#workGrpIdTxt').val();
		deleteData.USER_ID = rowData.USER_ID;
		deleteDataList[index] = deleteData;
	});

	if(deleteDataList.length == 0){
		alert('<spring:message code="MSG.M07.MSG00073"/>');
		return;
	}
	var url = '/product/service/serviceMgt/workGrpMng/deleteWorkGrpUserAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : JSON.stringify(deleteDataList),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	reloadUserDtl($('#workGrpIdTxt').val());

        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}

/*
 * 신규등록처리
 */
function insertBtn(){

	var soId = $("#workGrpSoSel").val();
	if($('#workGrpSoSel').val()== 'SEL'){
		
		var item = '<spring:message code="LAB.M07.LAB00003" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
    }

	//작업그룹명 필수
	var workGrpNm = $("#workGrpNmTxt").val();
	if(workGrpNm == null || workGrpNm.length == 0){
		$("#workGrpNmTxt").focus();
		var item = '<spring:message code="LAB.M09.LAB00013" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
	}


	//사용유무
	var useYn = $("#workGrpUseYnSel").val();
	if($('#workGrpUseYnSel').val()== 'SEL'){
		$('#workGrpUseYnSel-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
    }

    var url = '/product/service/serviceMgt/workGrpMng/insertWorkGrpAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
		  workGrpNm : workGrpNm
		 ,soId : soId
		 ,useYn : useYn 
			},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}


/*
 * 수정처리
 */
function updateBtn(){

	//작업그룹명 필수
	var workGrpNm = $("#workGrpNmTxt").val();
	if(workGrpNm == null || workGrpNm.length == 0){
		$("#workGrpNmTxt").focus();
		var item = '<spring:message code="LAB.M09.LAB00013" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
	}


	//사용유무
	var useYn = $("#workGrpUseYnSel").val();
	if($('#workGrpUseYnSel').val()== 'SEL'){
		$('#workGrpUseYnSel-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
    }

    var url = '/product/service/serviceMgt/workGrpMng/updateWorkGrpAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
		  workGrpId : $('#workGrpIdTxt').val()
		 ,workGrpNm : workGrpNm 
		 ,useYn : useYn 
			},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
}

/*
 * 삭제처리
 */
function deleteBtn(){
    var url = '/product/service/serviceMgt/workGrpMng/deleteWorkGrpAction.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
		  workGrpId : $('#workGrpIdTxt').val()
		},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
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
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:40%;">
		<col style="width:10%;">
		<col style="width:40%;">
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
			<th>청구년월</th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>마감구분</th>
			<td colspan="3">
				<select id="condSo" class="w40p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${finishTp}" var="finishTp" varStatus="status">
						<option value="${finishTp.commonCd}">${finishTp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
</table> 

<!--작업그룹표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">마감정보</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>

<!--작업그룹상세-->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">마감정보설정</h4>
	</div>
</div>
<table class="writeview">
	<colgroup>
		<col style="width:20%;">
		<col style="width:30%;">
		<col style="width:20%;">
		<col style="width:30%;">
	</colgroup>
	<thead>
		<tr>
			<th>사업<span class="dot">*</span></th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
				</select>
			</td>
			<th>청구년월<span class="dot">*</span></th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt"  readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>마감업무구분<span class="dot">*</span></th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
				</select>
			</td>
			<th>마감일자<span class="dot">*</span></th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>마감상태<span class="dot">*</span></th>
			<td colspan="3">
					<div class="inp_date w280">
						<div class="date_box">
							<input type="radio" id="mstrFl" name="mstrFl" value="1"checked="checked" />
								<label for="mstrFl">마감전</label>
							<input type="radio" id="mstrFl2" name="mstrFl" value="0" /> 
							<label for="mstrFl2"> 마감후</label>
						</div>
					</div>
			</td>
		</tr>
	</thead>
</table> 
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


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
  ul.dynatree-container {
	  border: none;
  };
</style>
<script type="text/javascript">
$(document).ready(function() {
	
	var tree = $("#tree");
	

	//화면 초기화
	pageInit();
	
	//권한그룹 그리드
	$("#authGroupGrid").jqGrid({
		url : '/system/auth/authMng/authMng/getUserGroupListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			condGroupId : $("#condGroupId").val(),
			condGroupNm : $("#condGroupNm").val()
		},
		colModel: [
				{ label: '<spring:message code="LAB.M05.LAB00031"/>', name: 'mainView', hidden:true}, 
				{ label: '<spring:message code="LAB.M07.LAB00200"/>', name: 'description',hidden:true},
		     	{ label: '<spring:message code="LAB.M01.LAB00182"/>', name: 'userGroupId', width : 100, align:"left"},
			    { label: '<spring:message code="LAB.M01.LAB00186"/>', name: 'userGroupName', width : 150, align:"left"},
			    
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 385,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "groupList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowNum: -1,  
        onCellSelect : function(rowid, index, contents, event){
        	$('#condMenu').val('');
        	$("#onlyAssignMenuYn").removeAttr("checked");
        	var data = $("#authGroupGrid").getRowData(rowid);
        	$("#description").val(data.description);
        	$("#mainView").val(data.mainView);
        	
        	getAuthList(data.userGroupId, '');
        },
       	loadComplete : function (data) {
       		$("#authGroupGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       		getAuthList('','');
       	},
    	sortable: { update: function(permutation) {
    		$("#authGroupGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
		});
	
		$("#authGroupGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

		//그리드 화면 재조정
		$(window).resize(function() {
			$("#authGroupGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}); 
				
		//그룹정보 검색 버튼 이벤트
		$('#btn_authSrch').click(function() {
			fnSearch();
		});
		
		
		// 그룹ID 키 이벤트
	    $("#condGroupId").keypress(function(event) {
	   		if(event.keyCode == 13){
	   			fnSearch();
	   		}
	   	});
		
	 	// 그룹명 키 이벤트
	    $("#condGroupNm").keypress(function(event) {
	   		if(event.keyCode == 13){
	   			fnSearch();
	   		}
	   	});
	 	
	  	//변경 버튼 이벤트
	    $('#updateBtn').on('click',function (e) {
	    	if($("#updateBtn").hasClass('not-active')){
				return;
			}
	    		updateAuthInfo();
			}
	    );
	 	
		//트리 초기화
		$(tree).dynatree({
	 		initAjax: {
	 			data : {
	 				userGroupId : null,
				},
	 			type: "POST",
	 			url: "/system/auth/authMng/authMng/getAuthListAction.json",
				complete : function(){
					/* if(tree.dynatree("getTree").activateKey('ROOT').data != null){
						var userGroupId = tree.dynatree("getTree").activateKey('ROOT').data.userGroupId;
						if(userGroupId == null || userGroupId == ''){
							$('#condGroupId').focus();
						}	
					} */
				}
	 		},
	        onActivate: function(node) {
	                // A DynaTreeNode object is passed to the activation handler
	                // Note: we also get this event, if persistence is on, and the page is reloaded.
	                setAuthInfo(node);

	        },
			clickFolderMode: 3,
			minExpandLevel: 2, //1: root node is not collapsible
			selectMode: 1 // 1:single, 2:multi, 3:multi-hier
		});
		
		//권한정보 검색
	    $("#condMenu").keypress(function(event) {
	   		if(event.keyCode == 13){
	   			var result = seachFolderNodeWithName($("#condMenu").val(), tree);
	   			
	   			if(!result){
	   				$("#tree").dynatree("getTree").activateKey('ROOT'); //ROOT KEY 지정
	   				
	   				$("#tree").dynatree("getRoot").visit(function (node) {
	   			    	node.expand(false);
	   			    });
	   			}
	   			
	   			$('#condMenu').focus();
	   			
	   		}
	   	});
	    
	    
		//할당메뉴만체크
	    $('#onlyAssignMenuYn').click(function() {
	    	var rowid  = $("#authGroupGrid").jqGrid('getGridParam', 'selrow');
	    	var data = $("#authGroupGrid").getRowData(rowid);
        	getAuthList(data.userGroupId,'');
	    });
		
		//전체선택
	    $('#chkAuthAll').click(function() {
	    	var chkAuthAllYn = ($('#chkAuthAll').prop("checked") ? 'Y' : 'N');
	    	
	    	if(chkAuthAllYn == 'N'){
	    		$('#chkAuthR').prop("checked",false);
	    		$('#chkAuthC').prop("checked",false);
	    		$('#chkAuthU').prop("checked",false);
	    		$('#chkAuthD').prop("checked",false);
	    		$('#chkAuthP').prop("checked",false);
	    	}else{
	    		$('#chkAuthR').prop("checked",true);
	    		$('#chkAuthC').prop("checked",true);
	    		$('#chkAuthU').prop("checked",true);
	    		$('#chkAuthD').prop("checked",true);
	    		$('#chkAuthP').prop("checked",true);
	    	}
	    });
		
	    $('#chkAuthR').click(function() {
	    	var chkAuthR = ($('#chkAuthR').prop("checked") ? 'Y' : 'N');
	    	var chkAuthC = ($('#chkAuthC').prop("checked") ? 'Y' : 'N');
	    	var chkAuthU = ($('#chkAuthU').prop("checked") ? 'Y' : 'N');
	    	var chkAuthD = ($('#chkAuthD').prop("checked") ? 'Y' : 'N');
	    	var chkAuthP = ($('#chkAuthP').prop("checked") ? 'Y' : 'N');
	    	
	    	
	    	if(chkAuthR == 'N'){
	    		$('#chkAuthAll').prop("checked",false);
	    	}else if(chkAuthR == 'Y' && chkAuthC == 'Y' && chkAuthU == 'Y' && chkAuthD == 'Y' && chkAuthP =='Y'){
	    		$('#chkAuthAll').prop("checked",true);
	    	}
	    });
	    
	    $('#chkAuthC').click(function() {
	    	var chkAuthR = ($('#chkAuthR').prop("checked") ? 'Y' : 'N');
	    	var chkAuthC = ($('#chkAuthC').prop("checked") ? 'Y' : 'N');
	    	var chkAuthU = ($('#chkAuthU').prop("checked") ? 'Y' : 'N');
	    	var chkAuthD = ($('#chkAuthD').prop("checked") ? 'Y' : 'N');
	    	var chkAuthP = ($('#chkAuthP').prop("checked") ? 'Y' : 'N');
	    	
	    	if(chkAuthC == 'N'){
	    		$('#chkAuthAll').prop("checked",false);
	    	}else if(chkAuthR == 'Y' && chkAuthC == 'Y' && chkAuthU == 'Y' && chkAuthD == 'Y' && chkAuthP =='Y'){
	    		$('#chkAuthAll').prop("checked",true);
	    	}
	    });
	    
	    $('#chkAuthU').click(function() {
	    	var chkAuthR = ($('#chkAuthR').prop("checked") ? 'Y' : 'N');
	    	var chkAuthC = ($('#chkAuthC').prop("checked") ? 'Y' : 'N');
	    	var chkAuthU = ($('#chkAuthU').prop("checked") ? 'Y' : 'N');
	    	var chkAuthD = ($('#chkAuthD').prop("checked") ? 'Y' : 'N');
	    	var chkAuthP = ($('#chkAuthP').prop("checked") ? 'Y' : 'N');
	    	
	    	if(chkAuthU == 'N'){
	    		$('#chkAuthAll').prop("checked",false);
	    	}else if(chkAuthR == 'Y' && chkAuthC == 'Y' && chkAuthU == 'Y' && chkAuthD == 'Y' && chkAuthP =='Y'){
	    		$('#chkAuthAll').prop("checked",true);
	    	}
	    });
	    
	    $('#chkAuthD').click(function() {
	    	var chkAuthR = ($('#chkAuthR').prop("checked") ? 'Y' : 'N');
	    	var chkAuthC = ($('#chkAuthC').prop("checked") ? 'Y' : 'N');
	    	var chkAuthU = ($('#chkAuthU').prop("checked") ? 'Y' : 'N');
	    	var chkAuthD = ($('#chkAuthD').prop("checked") ? 'Y' : 'N');
	    	var chkAuthP = ($('#chkAuthP').prop("checked") ? 'Y' : 'N');
	    	
	    	if(chkAuthD == 'N'){
	    		$('#chkAuthAll').prop("checked",false);
	    	}else if(chkAuthR == 'Y' && chkAuthC == 'Y' && chkAuthU == 'Y' && chkAuthD == 'Y' && chkAuthP =='Y'){
	    		$('#chkAuthAll').prop("checked",true);
	    	}
	    });
	    
	    $('#chkAuthP').click(function() {
	    	var chkAuthR = ($('#chkAuthR').prop("checked") ? 'Y' : 'N');
	    	var chkAuthC = ($('#chkAuthC').prop("checked") ? 'Y' : 'N');
	    	var chkAuthU = ($('#chkAuthU').prop("checked") ? 'Y' : 'N');
	    	var chkAuthD = ($('#chkAuthD').prop("checked") ? 'Y' : 'N');
	    	var chkAuthP = ($('#chkAuthP').prop("checked") ? 'Y' : 'N');
	    	
	    	if(chkAuthP == 'N'){
	    		$('#chkAuthAll').prop("checked",false);
	    	}else if(chkAuthR == 'Y' && chkAuthC == 'Y' && chkAuthU == 'Y' && chkAuthD == 'Y' && chkAuthP =='Y'){
	    		$('#chkAuthAll').prop("checked",true);
	    	}
	    });
});
/*
 * 화면 초기화 함수
 */
function pageInit() {

	//검색정보 초기화
	$('#condGroupId').focus();
	$('#condGroupId').val('');
	$('#condGroupNm').val('');
	$('#condMenu').val('');
	
	$("#groupInfo input:text").addClass('not-active'); 
	$("#groupInfo input:text").attr('disabled', true);
	
	$("#onlyAssignMenuYn").removeAttr("checked");
	$("#onlyAssignMenuYn").addClass('not-active');
	$("#onlyAssignMenuYn").attr('disabled',true);
	
	$("#menuAuthInfo input:text").val('');
	$("#menuAuthInfo input:text").addClass('not-active'); 
	$("#menuAuthInfo input:text").attr('disabled', true);
	
	$("#menuAuthInfo input:checkbox").removeAttr("checked");
	$("#menuAuthInfo input:checkbox").addClass('not-active');
	$("#menuAuthInfo input:checkbox").attr('disabled',true);
	
	$("#condMenu").addClass('not-active'); 
	$("#condMenu").attr('disabled', true);
	
	
	//입력부 초기화
	inputInit('N');
}
function fnSearch(){
	//입력부 초기화
	inputInit('Y');

	$("#authGroupGrid").setGridParam({
		postData : {
			condGroupId : $("#condGroupId").val(),
			condGroupNm : $("#condGroupNm").val(),
			isSearchYn:"Y"
		}
	});
	
	$("#authGroupGrid").trigger("reloadGrid");
	
}
/*
 * 입력부 초기화 함수
 */
function inputInit(isSearchYn) {
	$("#authGroupGrid").clearGridData();
	$("#groupInfo input:text").val('');
	$('#condMenu').val('');
	
	$("#onlyAssignMenuYn").removeAttr("checked");
	$("#onlyAssignMenuYn").addClass('not-active');
	$("#onlyAssignMenuYn").attr('disabled',true);
	$("#menuAuthInfo input:text").val('');
	$("#menuAuthInfo input:text").addClass('not-active'); 
	$("#menuAuthInfo input:text").attr('disabled', true);
	
	$("#menuAuthInfo input:checkbox").removeAttr("checked");
	$("#menuAuthInfo input:checkbox").addClass('not-active');
	$("#menuAuthInfo input:checkbox").attr('disabled',true);
	
	
	$("#condMenu").addClass('not-active'); 
	$("#condMenu").attr('disabled', true);
	
	if (isSearchYn=='Y') {	//검색조건유지
		$('#condGroupId').val($('#condGroupId').val());
		$('#condGroupNm').val($('#condGroupNm').val());
		
	}else{//검색정보 초기화
		$('#condUserGroupId').val('');
		$('#condUserGroupNm').val('');
	}
	btnCtrl('I');
}
function btnCtrl(mode) {
	//공통 버튼 처리

	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled',true); 

	if (mode == 'U') {
		btnEnable('updateBtn');
	}
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
/*
 * 사용자 그룹 선택시 권한 정보 재조회
 */
function getAuthList(userGroupId, menuNo){
	var onlyAssignMenuYn = ($('#onlyAssignMenuYn').prop("checked") ? 'Y' : 'N');
	
    $("#tree").dynatree("option", "initAjax", {
	   	data : {
				userGroupId : userGroupId,
				onlyAssignMenuYn : onlyAssignMenuYn
		},
		type: "POST",
		url: "/system/auth/authMng/authMng/getAuthListAction.json",
		complete : function(){
			var userGroupId = $("#tree").dynatree("getTree").activateKey('ROOT').data.userGroupId;
			if(userGroupId == null || userGroupId == ''){
				$('#condGroupId').focus();	
			}else{
				$("#condMenu").removeClass('not-active'); 
				$("#condMenu").removeAttr('disabled');
				$("#onlyAssignMenuYn").removeClass('not-active');
				$("#onlyAssignMenuYn").removeAttr('disabled');
				if(menuNo != ''){
		   			var result = seachFolderNodeWithName(menuNo, $("#tree"));
		   			
		   			if(!result){
		   				$("#tree").dynatree("getTree").activateKey('ROOT'); //ROOT KEY 지정
		   				
		   				$("#tree").dynatree("getRoot").visit(function (node) {
		   			    	node.expand(false);
		   			    });
		   			} 					
				}
			}
				
		}
     });

    $("#tree").dynatree("getTree").reload();
	
}


/*
 * 노드 선택 함수
 */
function seachFolderNodeWithName(name, tree) {
    if (name == null) {
        return undefined;
    }
	var activatedNode = tree.dynatree("getActiveNode");
	var preActivatedChildOrder = '';
	var preActivatedParentOrder = '';
	
    if (activatedNode != null) {
    	if(activatedNode.getLevel() == 5){
    		preActivatedChildOrder = activatedNode.data.order;
    		preActivatedParentOrder = activatedNode.getParent().data.order;
    	}
    }
    
    var match = undefined;
    tree.dynatree("getRoot").visit(function (node) {
        if (node.getLevel()==5 && node.data.searchKey.indexOf(name) != -1) {
        	var nodeChildOrder = node.data.order;
        	var nodeParentOrder = node.getParent().data.order;
        	if(preActivatedChildOrder == '' && preActivatedParentOrder == ''){
        		match = node;
                return false;	
        	}else{
        		if(nodeParentOrder == preActivatedParentOrder){ //동일 노드 내에서 검색
                	if(nodeChildOrder > preActivatedChildOrder){
                		match = node;
                        return false;
                	}        	
        		}else if(nodeParentOrder > preActivatedParentOrder){ //이전 선택 노드 이후의 노드에서 검색
        			match = node;
                    return false;
        		}
	
        	}

        }
    });
    if(match != undefined){
    	tree.dynatree("getTree").activateKey(match.data.key);
    	return true;
    }else{
    	return false;
    }
};


function setAuthInfo(node){
	$("#menuAuthInfo input:text").val('');
	$("#menuAuthInfo input:text").addClass('not-active'); 
	$("#menuAuthInfo input:text").attr('disabled', true);
	
	$("#menuAuthInfo input:checkbox").removeAttr("checked");
	$("#menuAuthInfo input:checkbox").addClass('not-active');
	$("#menuAuthInfo input:checkbox").attr('disabled',true);
	
	var level = node.getLevel();
	if(level > 1){
		$('#inputMenuNo').val(node.data.key);
		$('#inputMenuName').val(node.data.title);
		$('#chkAuthAll').prop("checked",node.data.checkAll == 'Y' ? true : false);
		$('#chkAuthR').prop("checked",node.data.authRYn == 'Y' ? true : false);
		$('#chkAuthC').prop("checked",node.data.authCYn == 'Y' ? true : false);
		$('#chkAuthU').prop("checked",node.data.authUYn == 'Y' ? true : false);
		$('#chkAuthD').prop("checked",node.data.authDYn == 'Y' ? true : false);
		$('#chkAuthP').prop("checked",node.data.authPYn == 'Y' ? true : false);
		if(level < 5){
			$("#chkAuthAll").removeClass('not-active');
			$("#chkAuthAll").removeAttr('disabled',true);
		}else{
			$("#menuAuthInfo input:checkbox").removeClass('not-active');
			$("#menuAuthInfo input:checkbox").removeAttr('disabled',true);
		}
		btnCtrl('U');
	}else{
		btnCtrl('I');
	}
}


/*
 * 변경처리
 */
function updateAuthInfo(){
	
	var rowid  = $("#authGroupGrid").jqGrid('getGridParam', 'selrow');
	var data = $("#authGroupGrid").getRowData(rowid);
	var userGroupId = data.userGroupId;
	
	var activatedNode = $("#tree").dynatree("getActiveNode");
	var menuNo = activatedNode.data.key;
	var level = activatedNode.getLevel();
	var authRYn = $('#chkAuthR').prop("checked") ? 'Y' : 'N';
	var authCYn = $('#chkAuthC').prop("checked") ? 'Y' : 'N';
	var authUYn = $('#chkAuthU').prop("checked") ? 'Y' : 'N';
	var authDYn = $('#chkAuthD').prop("checked") ? 'Y' : 'N';
	var authPYn = $('#chkAuthP').prop("checked") ? 'Y' : 'N';
	
	var authInfo = 'userGroupId=' + userGroupId;
	authInfo = authInfo + '&menuNo=' + menuNo;
	authInfo = authInfo + '&level=' + level;
	authInfo = authInfo + '&authRYn=' +  authRYn;
	authInfo = authInfo + '&authCYn=' +  authCYn;
	authInfo = authInfo + '&authUYn=' +  authUYn;
	authInfo = authInfo + '&authDYn=' +  authDYn;
	authInfo = authInfo + '&authPYn=' +  authPYn;
	
	
	//상위 레벨 메뉴 삭제 처리시 확인 메세지
	if(authRYn =='N' && authCYn == 'N' && authUYn == 'N' && authDYn == 'N' && authPYn == 'N'){
		var check = confirm('<spring:message code="MSG.M09.MSG00016" />');
		if(!check){
			return;
		}	
	}
	
	
	
	var url = '/system/auth/authMng/authMng/updateAuthAction.json';
 	$.ajax({
        url:url,
        type:'POST',
        data : authInfo,
        dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M09.MSG00005"/>');
        	var data = $("#authGroupGrid").getRowData(rowid);
        	getAuthList(data.userGroupId,menuNo);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
     			alert(err.responseJSON.exceptionMsg);
     		}else{
     			alert('<spring:message code="MSG.M09.MSG00006"/>');	
     		}
     	}
    });
}

</script>
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
<div class="group_box mt10">
	<div class="gr_left">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M01.LAB00229"/></h4>
			</div>
			<div class="fr mt10">
				<ntels:auth auth="${menuAuthR}">
					<a href="#" id="btn_authSrch" class="grey-btn" title="<spring:message code="LAB.M09.LAB00158"/>"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
				</ntels:auth>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 35%;">
				<col style="width: 65%;">
			</colgroup>
			<thead>
				<tr>
					<th><spring:message code="LAB.M01.LAB00182"/></th>
					<td><input type="text" id="condGroupId" name="condGroupId" class="w100p"></td>
				</tr>
				<tr>
					<th><spring:message code="LAB.M01.LAB00186"/></th>
					<td><input type="text" id="condGroupNm" name="condGroupNm" class="w100p"></td>
				</tr>
			</thead>
		</table>
		<div id='gridDiv' class="mt10">
			<table id="authGroupGrid" class="w100p"></table>
		</div>
		<div id="groupInfo">
			<table class="writeview mt10">
				<colgroup>
					<col style="width: 30%;">
					<col style="width: 70%;">
				</colgroup>
				<thead>
					<tr>
						<th><spring:message code="LAB.M07.LAB00200"/></th>
					<td><input id="description" name="description" type="text" class="w100p" /></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M05.LAB00031"/></th>
					<td><input id="mainView" name="mainView" type="text" class="w100p" /></td>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="gr_right">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M01.LAB00180"/></h4>
			</div>
		</div>
		<table class="writeview">
				<colgroup>
					<col style="width: 30%;">
					<col style="width: 70%;">
				</colgroup>
			<thead>
				<tr>
					<th><spring:message code="LAB.M05.LAB00027"/>/<spring:message code="LAB.M05.LAB00026"/></th>
					<td>
						<input id="condMenu" type="text" class="w50p" />
						<input type="checkbox" id="onlyAssignMenuYn"/><spring:message code="LAB.M14.LAB00016"/>
					</td>
				</tr>
			</thead>
		</table>
		<div id="tree" class="line mt10 w100p">
		</div>
		<div id="menuAuthInfo">
			<table class="writeview mt10">
				<colgroup>
					<col style="width: 15%;">
					<col style="width: 30%;">
					<col style="width: 15%;">
					<col style="width: 30%;">
				</colgroup>
				<thead>
					<tr>
						<th><spring:message code="LAB.M05.LAB00027"/></th>
						<td><input id="inputMenuNo" name="inputMenuNo" type="text" class="w100p" /></td>
						<th><spring:message code="LAB.M05.LAB00026"/></th>
						<td><input id="inputMenuName" name="inputMenuName" type="text" class="w100p" /></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M01.LAB00176"/></th>
						<td colspan="3">
							<input type="checkbox" id="chkAuthAll"/><spring:message code="LAB.M09.LAB00063"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="chkAuthR"/><spring:message code="LAB.M09.LAB00158"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="chkAuthC"/><spring:message code="LAB.M03.LAB00075"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="chkAuthU"/><spring:message code="LAB.M07.LAB00252"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="chkAuthD"/><spring:message code="LAB.M07.LAB00082"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="chkAuthP"/><spring:message code="LAB.M10.LAB00079"/>
						</td>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	
	
</div>
<div class="main_btn_box">
	<div id="commonBtn" class="fr">
				<ntels:auth auth="${menuAuthC}">
				<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
				<a id="newBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
				</ntels:auth>
				<ntels:auth auth="${menuAuthU}">
				<a id="updateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
				</ntels:auth>
				 <ntels:auth auth="${menuAuthD}">
				<a id="deleteBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
				</ntels:auth> 
		</div>
</div>
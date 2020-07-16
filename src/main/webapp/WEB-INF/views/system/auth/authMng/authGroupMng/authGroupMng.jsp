<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	pageInit();
	
	$("#authGroupGrid").jqGrid({
		url : '/system/auth/authMng/authGroupMng/mainListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			condGroupId : $("#condGroupId").val(),
			condGroupNm : $("#condGroupNm").val()
		},
		colModel: [
				{ label: 'userGroupLevel' , name: 'userGroupLevel', hidden:true,width : 0},
		     	{ label: '<spring:message code="LAB.M01.LAB00182"/>', name: 'userGroupId', width : 150, align:"center"},
			    { label: '<spring:message code="LAB.M01.LAB00186"/>', name: 'userGroupName', width : 200},
			    { label: '<spring:message code="LAB.M05.LAB00031"/>', name: 'mainView', width : 150,align:"left",sortable:false}, 
			    { label: '<spring:message code="LAB.M07.LAB00200"/>', name: 'description', width : 180,sortable:false},           
			    { label: '<spring:message code="LAB.M13.LAB00021"/>', name: 'displayOrder', width : 180,align:"center"},    
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 240,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "authGroupList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#jqGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedDate(rowid);
        },
       	loadComplete : function (data) {
       		$("#authGroupGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

       	},
    	sortable: 
    		{ update: function(permutation) {
    			$("#authGroupGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
		});

		$("#authGroupGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

		//그리드 화면 재조정
		$(window).resize(function() {
			$("#authGroupGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}); 
		
		//검색 버튼 이벤트
		$('#btn_srch').click(function() {
			fnSearch();
		});
		//검색 enter 이벤트
		$( "#condGroupId" ).keypress(function(event) {
			var checkR = "<c:out value='${menuAuthR}'/>"; 
			if(checkR == 'N') return;
			
	   		if(event.keyCode == 13){
	   			fnSearch();
	   		}
		});
		
		//검색 enter 이벤트
		$( "#condGroupNm" ).keypress(function(event) {
			var checkR = "<c:out value='${menuAuthR}'/>"; 
			if(checkR == 'N') return;
			
	   		if(event.keyCode == 13){
	   			fnSearch();
	   		}
		});
		
		//초기화 버튼 이벤트
		$('#initBtn').click(function() {
			if($("#initBtn").hasClass('not-active')){
				return;
			}
			
			initBtn();
		});
		$("#groupId-btn").click(function() {
			if($("#groupId-btn").hasClass('not-active')){
	    		return;
	    	}
			checkUserGroupId();
		});
		//사업자ID keyup 이벤트
	  	$('#userGroupId').keyup(function(){
		  	var str = getMaxStr($('#userGroupId').val(), 7);
		  	if(str != $('#userGroupId').val()){
		  		$('#userGroupId').val(str);
		  	}
		  	
		  	if (!(event.keyCode >=37 && event.keyCode<=40)) {
		        var inputVal = $(this).val();
		        $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
		     }
	  	});
		//사용자명 keyup 이벤트
	  	$('#userGroupName').keyup(function(){
		  	var str = getMaxStr($('#userGroupName').val(), 50);
		  	if(str != $('#userGroupName').val()){
		  		$('#userGroupName').val(str);
		  	}
	  	});
		//mainView keyup이벤트
	  	$('#mainView').keyup(function(){
	  		var str = getMaxStr($('#mainView').val(), 256);
		  	if(str != $('#mainView').val()){
		  		$('#mainView').val(str);
		  	}
	  	});
		//description keyup이벤트
	  	$('#description').keyup(function(){
	  		var str = getMaxStr($('#description').val(), 100);
		  	if(str != $('#description').val()){
		  		$('#description').val(str);
		  	}
	  	});
	 	 //displayOrder keyup이벤트	//숫자 2자리만 입력가능
	  	$('#displayOrder').keyup(function(){
	  		var str = getMaxStr($('#displayOrder').val(), 2);
		  	if(str != $('#displayOrder').val()){
		  		$('#displayOrder').val(str);
		  	}
		  	$('#displayOrder').val( $('#displayOrder').val().replace(/[^0-9:\-]/gi,"") );
	  	});
	  
		//추가 버튼 클릭
		$("#newBtn").click(function() {
			if($("#newBtn").hasClass('not-active')){
				return;
			}
			var authGroup = 'userGroupId=' + $('#userGroupId').val().replace(/ /g, '');
			authGroup = authGroup + '&userGroupName=' + $('#userGroupName').val().trim();
			authGroup = authGroup + '&displayOrder=' + $('#displayOrder').val().trim();
			authGroup = authGroup + '&description=' + $('#description').val().trim();
			authGroup = authGroup + '&mainView=' +$('#mainView').val().trim();
   			
	   		if($("#userGroupId").val() == ''|| ($("#userGroupId").val().replace(/ /g, '')).length ==0){
	   			$("#userGroupId").focus();
   				alert('<spring:message code="MSG.M01.MSG00051"/>');
   				return;
   			}
	   		if(!$("#userGroupId").hasClass('not-active')){
	   			$("#userGroupId").focus();
   				alert('<spring:message code="MSG.M01.MSG00048"/>');
   				return;
   			}
   			if($("#userGroupName").val() == '' || ($("#userGroupName").val().trim()).length ==0){
   				$("#userGroupName").focus();
   				alert('<spring:message code="MSG.M01.MSG00052"/>');
   				return;
   			}
   			if($("#mainView").val() == '' || ($("#mainView").val().trim()).length ==0){
   				$("#mainView").focus();
   				alert('<spring:message code="MSG.M05.MSG00011"/>');
   				return;
   			}
   			if($("#description").val() == '' || ($("#description").val().trim()).length ==0){
   				$("#description").focus();
   				alert('<spring:message code="MSG.M07.MSG00079"/>');
   				return;
   			}
   			if($("#displayOrder").val() == '' || ($("#displayOrder").val().trim()).length ==0){
   				$("#displayOrder").focus();
   				alert('<spring:message code="MSG.M13.MSG00015"/>');
   				return;
   			}
			$.ajax({
   	            url:'/system/auth/authMng/authGroupMng/insertAction.json',
   	            type:'POST',
   	            data : authGroup,
   	            dataType: 'json',
   	            success: function(data){
   	   	          		$("#authGroupGrid").clearGridData();
   	   	        		//입력부 초기화
		   	   	      	inputInit('N');
		   	   	      	
		   	   	      	//버튼 컨트롤
		   	   	      	btnCtrl('I');
		   	   	      	
   	   	          		$("#authGroupGrid").setGridParam({
			   	   	        postData : {
			   	   	      		condGroupId : $("#condGroupId").val(),
			  					condGroupNm : $("#condGroupNm").val()
			   	   			}
   	   	          		});
   	   	          		
   	   	         		alert('<spring:message code="MSG.M09.MSG00005"/>');
   	   	         		
   	   	          		$("#authGroupGrid").trigger("reloadGrid"); 
 
   	            	
   	            },
   	         	beforeSend: function(data){
   	         	},
   	         	error : function(err){
   	         		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
   	         			alert(err.responseJSON.exceptionMsg);
   	         		}else{
   	         			alert('<spring:message code="MSG.M10.MSG00005"/>');
   	         		}
   	         	}
   	        });
		});	//추가
		
		//수정 버튼 클릭
		$("#updateBtn").click(function() {
			if($("#updateBtn").hasClass('not-active')){
				return;
			}
			var authGroup = 'userGroupId=' + $('#userGroupId').val().replace(/ /g, '');
			authGroup = authGroup + '&userGroupName=' + $('#userGroupName').val().trim();
			authGroup = authGroup + '&displayOrder=' + $('#displayOrder').val().trim();
			authGroup = authGroup + '&description=' + $('#description').val().trim();
			authGroup = authGroup + '&mainView=' +$('#mainView').val().trim();
   			
			if($("#userGroupId").val() == ''|| ($("#userGroupId").val().replace(/ /g, '')).length ==0){
	   			$("#userGroupId").focus();
   				alert('<spring:message code="MSG.M01.MSG00051"/>');
   				return;
   			}
	   		if(!$("#userGroupId").hasClass('not-active')){
	   			$("#userGroupId").focus();
   				alert('<spring:message code="MSG.M01.MSG00048"/>');
   				return;
   			}
   			if($("#userGroupName").val() == '' || ($("#userGroupName").val().trim()).length ==0){
   				$("#userGroupName").focus();
   				alert('<spring:message code="MSG.M01.MSG00052"/>');
   				return;
   			}
   			if($("#mainView").val() == '' || ($("#mainView").val().trim()).length ==0){
   				$("#mainView").focus();
   				alert('<spring:message code="MSG.M05.MSG00011"/>');
   				return;
   			}
   			if($("#description").val() == '' || ($("#description").val().trim()).length ==0){
   				$("#description").focus();
   				alert('<spring:message code="MSG.M07.MSG00079"/>');
   				return;
   			}
   			if($("#displayOrder").val() == '' || ($("#displayOrder").val().trim()).length ==0){
   				$("#displayOrder").focus();
   				alert('<spring:message code="MSG.M13.MSG00015"/>');
   				return;
   			}
			
			$.ajax({
   	            url:'/system/auth/authMng/authGroupMng/updateAction.json',
   	            type:'POST',
   	            data : authGroup,
   	            dataType: 'json',
   	            success: function(data){
   	   	          		$("#authGroupGrid").clearGridData();
   	   	        		//입력부 초기화
		   	   	      	inputInit('N');
		   	   	      	
		   	   	      	//버튼 컨트롤
		   	   	      	btnCtrl('I');
		   	   	      	
   	   	          		$("#authGroupGrid").setGridParam({
			   	   	        postData : {
			   	   	      		condGroupId : $("#condGroupId").val(),
			  					condGroupNm : $("#condGroupNm").val()
			   	   			}
   	   	          		});
   	   	         		alert('<spring:message code="MSG.M07.MSG00089"/>');
   	   	          		$("#authGroupGrid").trigger("reloadGrid"); 
 
   	            	
   	            },
   	         	beforeSend: function(data){
   	         	},
   	         	error : function(err){
   	         		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
	         			alert(err.responseJSON.exceptionMsg);
	         		}else{
	         			alert('<spring:message code="MSG.M10.MSG00005"/>');
	         		}
   	         	}
   	        });
		});	//수정
		
	
		//삭제 버튼 클릭
		$("#deleteBtn").click(function() {
			if($("#deleteBtn").hasClass('not-active')){
				return;
			}
			
			if(confirm('<spring:message code="MSG.M01.MSG00047"/>')==true){
			var authGroup = 'userGroupId=' + $('#userGroupId').val();
			
			$.ajax({
   	            url:'/system/auth/authMng/authGroupMng/deleteAction.json',
   	            type:'POST',
   	            data : authGroup,
   	            dataType: 'json',
   	            success: function(data){
   	   	          		$("#authGroupGrid").clearGridData();
   	   	        		//입력부 초기화
		   	   	      	inputInit('N');
		   	   	      	
		   	   	      	//버튼 컨트롤
		   	   	      	btnCtrl('I');
		   	   	      	
   	   	          		$("#authGroupGrid").setGridParam({
			   	   	        postData : {
			   	   	      		condGroupId : $("#condGroupId").val(),
			  					condGroupNm : $("#condGroupNm").val()
			   	   			}
   	   	          		});
   	   	         		alert('<spring:message code="MSG.M07.MSG00053"/>');
   	   	         		
   	   	          		$("#authGroupGrid").trigger("reloadGrid"); 
 
   	            	
   	            },
   	         	beforeSend: function(data){
   	         	},
   	         	error : function(err){
   	         		alert('<spring:message code="MSG.M09.MSG00020"/>');
   	         	}
   	        });
			}//confirm창 [E]
		});//삭제버튼[E]
		
		//기본그룹 팝업 
		//조직 Popup
	    $('#mainView_popup').on('click',function (e) {
	    	if($("#mainView_popup").hasClass('not-active')){
	    		return;
	    	}
	    	var url="/system/common/common/mainViewMng/mainViewMngPopup.ajax";
	    	var param = new Object();
	    	param.popType = "m";			//팝업타입 m:모달 o:일반
	    	param.returnId1 = "mainView";	//리턴받는 orgId 의 태그아이디값

	    	$.ajax({
	    		type : "post",
	    		url : url,
	    		data : param,
	    		async: true,
	    		success : function(data) {
	    			var html = data;
					$("#popup_dialog").html(html);
	    		},		
	    		complete : function(){
	    			wrapWindowByMask(); // 팝업 오픈
	    		}
	    	}); 
	    });
});	// document ready
function fnSearch(){
	$("#authGroupGrid").clearGridData();
	//입력부 초기화
	inputInit('Y');

	//버튼 컨트롤
	btnCtrl('I');

	$("#authGroupGrid").setGridParam({
		postData : {
			condGroupId : $("#condGroupId").val(),
			condGroupNm : $("#condGroupNm").val(),
			srchYn:"Y"
		}
	});
	
	$("#authGroupGrid").trigger("reloadGrid");
}

/*
 * 화면 초기화 함수
 */
function pageInit() {

	//검색정보 초기화
	$('#condGroupId').focus();
	$('#condGroupId').val('');
	$('#condGroupNm').val('');

	$("#authGroupGrid").clearGridData();
	
	//입력부 초기화

	inputInit('N');
}
/*
 * 입력부 초기화 함수
 */
function inputInit(srch) {
	if (srch=='Y') {	//검색조건유지
		$('#condGroupId').val($('#condGroupId').val());
		$('#condGroupNm').val($('#condGroupNm').val());
		
	}else{//검색정보 초기화
		
		$('#condUserGroupId').val('');
		$('#condUserGroupNm').val('');
		
	}
	//사용자 리스트 Disable처리
	$("#authGroupInfo input:text").val('');
	$("#authGroupInfo input:text").addClass('not-active');
	$("#authGroupInfo input:text").attr('disabled', true);
	
	$("#groupId-btn").addClass('not-active');	//중복확인 버튼 클릭안되게
	$("#groupId-btn").attr('disabled', true);
	$("#groupId-btn").removeClass('td-grey-btn');
	$("#groupId-btn").addClass('td-btn');
	
	$(".search").addClass('not-active');
	$(".search").attr('disabled', true);
	//공통 버튼 처리
	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled', true);

	if (srch=='Y') {
		
		$("#initBtn").addClass('grey-btn');
		$("#initBtn").removeClass('white-btn');
		$("#initBtn").removeClass('not-active');
		$("#initBtn").removeAttr('disabled');
	}
		
}
function btnCtrl(mode) {
	//공통 버튼 처리

$("#commonBtn a").addClass('white-btn');
$("#commonBtn a").removeClass('grey-btn');
$("#commonBtn a").addClass('not-active');
$("#commonBtn a").attr('disabled',true); 

	if (mode == 'I') {
		btnEnable('initBtn');
	} else if (mode == 'N') {
		btnEnable('initBtn');
		btnEnable('newBtn');
	} else if (mode == 'U') {
		btnEnable('initBtn');
		btnEnable('updateBtn');	//updateBtn 활설화
		btnEnable('deleteBtn');	//detelBtn 활설화
	}
}
function initBtn() {//초기화 버튼 클릭시

	//입력정보 초기화
	inputInit('Y');
	
	//기본정보 활성화 처리
	
	$('#userGroupId').removeClass('not-active');
	$('#userGroupId').removeAttr('disabled'); 
	$("#userGroupId").removeAttr('readonly', 'readonly');
	
	$("#authGroupInfo input:text").removeAttr('disabled');
	$("#authGroupInfo input:text").removeClass('not-active');
	$("#mainView").addClass('not-active');
	$("#mainView").attr('disabled', true);
	
	$('#userGroupId').focus();
	
	$("#groupId-btn").removeClass('not-active');
	$("#groupId-btn").removeAttr('disabled');
	$("#groupId-btn").removeClass('td-btn');
	$("#groupId-btn").addClass('td-grey-btn');
	
	$(".search").removeClass('not-active');
	$(".search").removeAttr('disabled');	//mainview팝업
	//버튼 컨트롤
	btnCtrl('N');
	$("#userGrid").jqGrid("resetSelection");
}
/*
 * 데이터 선택 이벤트 처리
 */
function setSelectedDate(rowId) {

	$("#authGroupInfo input:text").removeClass('not-active');
	$("#authGroupInfo input:text").removeAttr('disabled');
	$("#userGroupId").addClass('not-active');
	$("#userGroupId").attr('disabled', true);
	$("#mainView").addClass('not-active');
	$("#mainView").attr('disabled', true);
	
	$("#groupId-btn").addClass('not-active');	//중복확인 버튼 클릭안되게
	$("#groupId-btn").attr('disabled', true);
	$("#groupId-btn").removeClass('td-grey-btn');
	$("#groupId-btn").addClass('td-btn');
	
	$(".search").removeClass('not-active');
	$(".search").removeAttr('disabled');	//mainview팝업
	
	var data = $("#authGroupGrid").getRowData(rowId);
	$("#userGroupId").val(data.userGroupId);
	$("#userGroupName").val(data.userGroupName);
	$("#displayOrder").val(data.displayOrder);
	$("#description").val(data.description);
	$("#mainView").val(data.mainView);
	$("#userGroupName").focus();
	
	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled', true);

	btnCtrl('U');
}
function checkUserGroupId(){	//ID중복체크
	var authGroup = 'userGroupId=' + $('#userGroupId').val().replace(/ /g, '');
	
 	if($('#userGroupId').val()=="" || ($('#userGroupId').val().trim()).length==0){
		alert('<spring:message code="MSG.M01.MSG00051"/>');
		return;
	}else{
		$('#userGroupId').val($('#userGroupId').val().replace(/ /g, ''));
	} 
		$.ajax({
	            url:'/system/auth/authMng/authGroupMng/getCheckUserGroupIdAction.json',
	            type:'POST',
	            data : authGroup,
	            dataType: 'json',
	            success: function(mode){
	            		
	            		if(mode.check==1){
		            		alert('<spring:message code="MSG.M01.MSG00049"/>'); //아이디 이미존재
		            		$("#userGroupId").val("");	//아이디 초기화
		            		return false;
	            		}else{
	            			alert('<spring:message code="MSG.M07.MSG00013"/>');	//ID사용가능
	            			$("#userGroupId").addClass('not-active');
	            			$("#userGroupId").attr('readonly', 'true');
	            			$("#userGroupId").attr('disabled', true);
	            			
	            			$("#groupId-btn").addClass('not-active');	//중복확인 버튼 클릭안되게
	            			$("#groupId-btn").attr('disabled', true);
	            			$("#groupId-btn").removeClass('td-grey-btn');
	            			$("#groupId-btn").addClass('td-btn');

	            		}
	            },
	         	beforeSend: function(data){
	         	},
	         	error : function(err){
	         		alert('<spring:message code="MSG.M09.MSG00020"/>');
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
			<ntels:auth auth="${menuAuthR}">
				<a href="#" id="btn_srch" class="grey-btn" title="<spring:message code="LAB.M09.LAB00158"/>"><span
					class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
			</ntels:auth>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 40%;">
			<col style="width: 10%;">
			<col style="width: 40%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M01.LAB00182"/></th>
				<td><input type="text" id="condGroupId" name="condGroupId" class="w100p"></td>
				<th><spring:message code="LAB.M01.LAB00186"/></th>
				<td><input type="text" id="condGroupNm" name="condGroupNm" class="w100p"></td>
			</tr>
		</thead>
	</table>
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M01.LAB00184"/></h4>
		</div>
	</div>
	<div id='gridDiv' class="w100p">
		<table id="authGroupGrid" class="w100p"></table>
		<div id="jqGridPager"></div>
	</div> 
<div id="authGroupInfo">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M01.LAB00229"/></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 8%;">
			<col style="width: 25%;">
			<col style="width: 8%;">
			<col style="width: 25%;">
			<col style="width: 8%;">
			<col style="width: 26%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M01.LAB00182"/><span class="dot">*</span></th>
				<td>
					<input id="userGroupId" name="userGroupId" type="text" class="w150" />
					<a href="#" id="groupId-btn" class="td-btn"><spring:message code="LAB.M07.LAB00066"/></a>
				</td>
				<th><spring:message code="LAB.M01.LAB00186"/><span class="dot">*</span></th>
				<td><input id="userGroupName" name="userGroupName" type="text" class="w100p" /></td>
				<th><spring:message code="LAB.M05.LAB00031"/><span class="dot">*</span></th>
				<td>
					<div class="inp_date w100p">
						<input id="mainView" name="mainView" type="text" class="w100p" /> 
						<a href="#" id="mainView_popup" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00200"/><span class="dot">*</span></th>
				<td colspan="3"><input id="description" name="description" type="text" class="w100p" /></td>
				
				<th><spring:message code="LAB.M13.LAB00021"/><span class="dot">*</span></th>
				<td><input id="displayOrder" name="displayOrder" type="text" class="w100p" /></td>
			</tr>
		</thead>
	</table>
</div>
<div class="main_btn_box">
	<div id="commonBtn" class="fl">
		<ntels:auth auth="${menuAuthP}">
		<a class="grey-btn" href="#" title='<spring:message code="LAB.M10.LAB00079"/>'><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
	</div>
	<div id="commonBtn" class="fr">
			<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
			<ntels:auth auth="${menuAuthC}">
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

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;" >
</div>
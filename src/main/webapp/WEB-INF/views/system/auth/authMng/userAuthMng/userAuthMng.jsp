<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels"%>

<script type="text/javascript">
$(document).ready(function() {
	pageInit();	
	$("#userAuthGrid").jqGrid({
		url : '/system/auth/authMng/userAuthMng/mainListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			condUserId : $("#condUserId").val(),
			condUserNm : $("#condUserNm").val(),
			condOrgId : $("#condOrgId").val()
		},
		colModel: [ 
		     	{ label: '<spring:message code="LAB.M07.LAB00067"/>', name: 'userId', width : 100},
			    { label: '<spring:message code="LAB.M07.LAB00071"/>', name: 'userName', width : 100},
			    { label: '<spring:message code="LAB.M09.LAB00138"/>', name: 'orgId', width : 150,align:"center"}, 
			    { label: '<spring:message code="LAB.M09.LAB00147"/>', name: 'orgNm', width : 150}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 370,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "userAuthList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowNum: -1,  
	    onCellSelect : function(rowid, index, contents, event){
	    	
        	var data = $("#userAuthGrid").getRowData(rowid);
        	var id=data.userId;
        	$("#idUser").val(id);
        	clickId(id);
	    },
	   	loadComplete : function (data) {
	   		$("#userAuthGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	   	},
    	sortable: { update: function(permutation) {
    		$("#userAuthGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
		});


	$("#userAuthGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#userAuthGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}); 
	
	
	$("#userPerAuthGrid").jqGrid({
		url : '/system/auth/authMng/userAuthMng/userAuthListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
		},
		colModel: [ 
		     	{ label: '<spring:message code="LAB.M01.LAB00178"/>', name: 'userGroupId', width : 100},
			    { label: '<spring:message code="LAB.M01.LAB00179"/>', name: 'userGroupName', width : 150}

		],
		multiselect : true,
		viewrecords: true,
		shrinkToFit:false,
		height: 465,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "userAuthList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowNum: -1,  
	    onCellSelect : function(rowid, index, contents, event){
	    	
	    },
	   	loadComplete : function (data) {
	   		$("#tmp").val('');
	   		
	   		$("#userPerAuthGrid").trigger("reloadGrid");
	   		$("#userPerAuthGrid").setGridWidth($('#userPerAuthDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	   	},
    	sortable: { update: function(permutation) {
    		$("#userPerAuthGrid").setGridWidth($('#userPerAuthDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
		});
	
	$("#userPerAuthGrid").setGridWidth($('#userPerAuthDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#userPerAuthGrid").setGridWidth($('#userPerAuthDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}); 
	
	//사용자 정보 검색 버튼 이벤트
	$('#btn_srch').click(function() {
		fnSearch();
	});
	
	//검색 enter 이벤트
	$( "#condUserId" ).keypress(function(event) {
		var checkR = "<c:out value='${menuAuthR}'/>"; 
		if(checkR == 'N') return;
		
   		if(event.keyCode == 13){
   			fnSearch();
   		}
	});
	
	//검색 enter 이벤트
	$( "#condUserNm" ).keypress(function(event) {
		var checkR = "<c:out value='${menuAuthR}'/>"; 
		if(checkR == 'N') return;
		
   		if(event.keyCode == 13){
   			fnSearch();
   		}
	});
	
	 $('#condOrgNm_popup').on('click',function (e) {	//조직검색 이벤트
		 $("#condOrgId").val('');	//돋보기 클릭시 초기화
		 $("#condOrgNm").val('');
	    	var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
	    	var param = new Object();
	    	
	    	param.popType = "m";			//팝업타입 m:모달 o:일반
	    	param.returnId1 = "condOrgId";	//리턴받는 orgId 의 태그아이디값
	    	param.returnId2 = "condOrgNm";	//리턴받는 orgNm 의 태그아이디값
			
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
	 
	 
	 $("#updateBtn").click(function() {	//추가
		
		 var data = $("#userPerAuthGrid").jqGrid('getDataIDs');
		 
		 var tmp=$("#tmp").val();
		 for(var i=0;i<data.length;i++){
			 var data2 = $("#userPerAuthGrid").getRowData(data[i]);
			 tmp+=data2.userGroupId+",";
		 }
		tmp=tmp.slice(0,-1);
		
		$("#tmp").val(tmp);
		fnNew(); 
		
		$("#userPerAuthGrid").setGridParam({
	   	        postData : {
	   	        	condUserId : $("#condUserId").val(),
	   				condUserNm : $("#condUserNm").val(),
	   				condOrgId : $("#condOrgId").val(),
	   				clickYn:"Y",
	   				userId:$("#idUser").val(),
	   				baseId:$("#baseId").val()
	   			}
        });
		
		$('.Layer_wrap ').on('click','.close',function (e) {	//닫기버튼을 눌렀을때 tmp를 초기화하고 리로드
			$("#tmp").val('');
			$("#userPerAuthGrid").setGridParam({
	   	        postData : {
	   	        	condUserId : $("#condUserId").val(),
	   				condUserNm : $("#condUserNm").val(),
	   				condOrgId : $("#condOrgId").val(),
	   				clickYn:"Y",
	   				userId:$("#idUser").val()
	   			}
       		 });
			$("#userPerAuthGrid").trigger("reloadGrid");
			
		});
		
	 });
	 
	 $("#deleteBtn").click(function() {
		 fnDelete();
	 });
});//ready
/*
 * 화면 초기화 함수
 */
function pageInit() {

	//검색정보 초기화
	
	$('#condUserId').val('');
	$('#condUserNm').val('');
	$('#condOrgNm').val('');
	$("#condOrgNm").addClass('not-active');
	$("#condOrgNm").attr('disabled', true);
	$("#userAuthGrid").clearGridData();
	$('#condUserId').focus();
	//입력부 초기화

	inputInit('N');
}

function fnSearch(){
	$("#userAuthGrid").clearGridData();
	$("#userPerAuthGrid").clearGridData();
	//입력부 초기화
	inputInit('Y');

	//버튼 컨트롤
	btnCtrl('I');

	$("#userAuthGrid").setGridParam({
		postData : {
			condUserId : $("#condUserId").val(),
			condUserNm : $("#condUserNm").val(),
			condOrgId : $("#condOrgId").val(),
			srchYn:"Y"
		}
	});
	
	$("#userAuthGrid").trigger("reloadGrid");
	
}
/*
 * 입력부 초기화 함수
 */
function inputInit(srch) {
	if (srch=='Y') {	//검색조건유지
		$('#condUserId').val($('#condUserId').val());
		$('#condUserNm').val($('#condUserNm').val());
		$('#condOrgId').val($('#condOrgId').val());
		
	}else{//검색정보 초기화
		$('#condUserId').val('');
		$('#condUserNm').val('');
		$('#condOrgId').val('');
		
	}
	
	//공통 버튼 처리
	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled', true);

	if (srch=='Y') {
		btnEnable('initBtn');
	}
		
}
function clickId(id){	//사용자에 해당하는 권한 그룹보여주기
	var userAuth = 'userId=' + $('#idUser').val();
	$("#userPerAuthGrid").clearGridData();

	$.ajax({
       url:'/system/auth/authMng/userAuthMng/getBaseUserGroupIdAction.json',
       type:'POST',
       data : userAuth,
       dataType: 'json',
       success: function(data){
       		$("#baseId").val(data.baseId);
       		
       },
    	beforeSend: function(data){
    	},
    	error : function(err){
         		alert('<spring:message code="MSG.M09.MSG00020"/>');
         }
   });
	
	btnCtrl('N');
	$("#userPerAuthGrid").setGridParam({
		postData : {
			condUserId : $("#condUserId").val(),
			condUserNm : $("#condUserNm").val(),
			condOrgId : $("#condOrgId").val(),
			clickYn:"Y",
			userId:id
		}
	});
	 $("#userPerAuthGrid").trigger("reloadGrid"); 
	 $("#userPerAuthGrid").setGridWidth($('#userPerAuthDiv').width(), false); 
}
function btnCtrl(mode) {
	//공통 버튼 처리

	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled',true); 

	if (mode == 'I') {
		btnDisable('initBtn');
	} else if (mode == 'N') {
		/* btnEnable('initBtn'); */
		btnEnable('updateBtn');
		btnEnable('deleteBtn');	//detelBtn 활설화
	} else if (mode == 'U') {
		btnEnable('initBtn');
		btnEnable('updateBtn');	//updateBtn 활설화
		btnEnable('deleteBtn');	//detelBtn 활설화
	}
}
function fnNew(){
	//기본그룹 팝업 

	var param = new Object();

	param.baseId=$("#baseId").val();
	param.userId=$("#idUser").val();
	param.userGroupId =$("#tmp").val();
	param.userGroupName = 'userGroupName';
	var url = "/system/common/common/userGroupMng/userGroupListPopup.ajax";
		$.ajax({
			type : "post",
			url : url,
			data : param,
			async : true,
			success : function(data) {
				var html = data;
				$("#popup_dialog").html(html);
				
			},
			complete : function() {
				wrapWindowByMask(); // 팝업 오픈
				
			}
		});
}
function fnDelete(){	//체크한것 삭제
	var rowid=  $("#userPerAuthGrid").jqGrid('getGridParam', 'selarrrow');
	var tmpId="";
	if(rowid.length==0){
		alert('<spring:message code="MSG.M10.MSG00009"/>');
		return;
	}
	if(confirm('<spring:message code="MSG.M07.MSG00054"/>')==true){
		
	
		for(var i=0; i<rowid.length; i++){
			var rowObject = $("#userPerAuthGrid").getRowData(rowid[i]);		
			if(rowObject.userGroupId==$("#baseId").val()){
				alert(rowObject.userGroupName+'<spring:message code="MSG.M02.MSG00009"/>');
				return;
			}
			var valueId = rowObject.userGroupId;														
			tmpId +=valueId+",";
				
		}
		tmpId=tmpId.slice(0,-1);
	
		var userAuth = 'userId=' + $('#idUser').val();
		userAuth = userAuth + '&userGroupId=' + tmpId;
	
		$.ajax({
           url:'/system/auth/authMng/userAuthMng/deleteAction.json',
           type:'POST',
           data : userAuth,
           dataType: 'json',
           success: function(data){
  	          		$("#userPerAuthGrid").clearGridData();
  	        		//입력부 초기화
   	   	      	inputInit('Y');
   	   	      	
   	   	      	//버튼 컨트롤
   	   	      	btnCtrl('N');
   	   	      	
	   	   	  $("#userPerAuthGrid").setGridParam({
		   	        postData : {
		   	        	condUserId : $("#condUserId").val(),
		   				condUserNm : $("#condUserNm").val(),
		   				condOrgId : $("#condOrgId").val(),
		   				clickYn:"Y",
		   				userId:$("#idUser").val()
		   			}
	     	 });
  	         		 alert('<spring:message code="MSG.M07.MSG00053"/>');
  	         		 $("#userPerAuthGrid").trigger("reloadGrid");  

           	
           },
        	beforeSend: function(data){
        	},
        	error : function(err){
	         		alert('<spring:message code="MSG.M09.MSG00020"/>');
	         }
       });
	}

}
	/*
	 * 버튼 비활성화 처리
	 */
function btnDisable(id) {
	$('#' + id).addClass('white-btn');
	$('#' + id).removeClass('grey-btn');
	$('#' + id).addClass('not-active');
	$('#' + id).attr('disabled', true);

}

/*
 * 버튼 활성화 처리
 */
function btnEnable(id) {
	$('#' + id).addClass('grey-btn');
	$('#' + id).removeClass('white-btn');
	$('#' + id).removeClass('not-active');
	$('#' + id).removeAttr('disabled');
}
</script>
<input type="hidden" id="idUser" name="idUser"/>	<!--리스트에서 선택한 user_id  -->
<input type="hidden" id="tmp" name="tmp"/>	<!-- 리스트에서 선택한 user_id의 user_group_id -->
<input type="hidden" id="baseId" name="baseId"/>
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
	<div class="user_left">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M07.LAB00076"/></h4>
			</div>
			<div class="fr mt10">
				<ntels:auth auth="${menuAuthR}">
				<a href="#" id="btn_srch" class="grey-btn" title="<spring:message code="LAB.M09.LAB00158"/>"><span
					class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
			</ntels:auth>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 15%;" />
				<col style="width: 35%;" />
				<col style="width: 15%;" />
				<col style="width: 35%;" />
			</colgroup>
			<thead>
				<tr>
					<th><spring:message code="LAB.M07.LAB00067"/></th>
					<td><input id="condUserId" name="condUserId" type="text" class="w100p" /></td>
					<th><spring:message code="LAB.M07.LAB00071"/></th>
					<td><input id="condUserNm" name="condUserNm" type="text" class="w100p" /></td>
				</tr>
				<tr>
					<th><spring:message code="LAB.M09.LAB00138"/></th>
					<td colspan="3">
						<div class="inp_date w100p">
							<input type="text" id="condOrgNm" name="condOrgNm" class="w100p">
							<input type="hidden" id="condOrgId" name="condOrgId">
							<a href="#" id="condOrgNm_popup"
						class="search"><spring:message code="LAB.M09.LAB00158"/></a>
						</div>
					</td>
				</tr>
			</thead>
		</table>
		<div id='gridDiv' class="w100p mt10">
			<table id="userAuthGrid" class="w100p"></table>
		</div>
	</div>
	<div class="user_right">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M07.LAB00073"/></h4>
			</div>
		</div>
		<div id='userPerAuthDiv' class="w100p">
			<table id="userPerAuthGrid" class="w100p"></table>
		</div>
	</div>
	<div class="user_right"></div> 
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
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;" >
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
		
	pageInit();
		
	$("#userGrid").jqGrid({
		url : '/system/user/userMng/userMng/mainListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			condSoId : $("#condSoId").val(),
			condOrgId : $("#condOrgId").val(),
			condOrgNm : $("#condOrgNm").val(),
			condUserNm:$("#condUserNm").val(),
			condYn : $("#condYn").val(),
			condLockYn:$("#condLockYn").val()
		},
		colModel: [
				{ label: 'password' , name: 'password', hidden:true,width : 0},
				{ label: 'userGroupId' , name: 'userGroupId', hidden:true,width : 0},
				{ label: 'userGroupName' , name: 'userGroupName', hidden:true,width : 0},
				{ label: 'orgId' , name: 'orgId', hidden:true,width : 0},
				{ label: 'orgNm' , name: 'orgNm', hidden:true,width : 0},
				{ label: 'crrTp' , name: 'crrTp', hidden:true,width : 0},
				{ label: 'empNo' , name: 'empNo', hidden:true,width : 0},
				{ label: 'telNo' , name: 'telNo', hidden:true,width : 0,formatter:telNoFormatter},
				{ label: 'mtelNo' , name: 'mtelNo', hidden:true,width : 0,formatter:telNoFormatter},
				{ label: 'eMail' , name: 'eMail', hidden:true,width : 0},
				{ label: 'ipBandwidth' , name: 'ipBandwidth', hidden:true,width : 0},
				{ label: 'loginFailCount' , name: 'loginFailCount', hidden:true,width : 0},
				{ label: 'passwordDueDate' , name: 'passwordDueDate', hidden:true,width : 0,  formatter:stringTypeFormatterYYYYMMDD},
				{ label: 'passwordChangePeriod' , name: 'passwordChangePeriod', hidden:true,width : 0},
				{ label: 'lastLoginDate' , name: 'lastLoginDate', hidden:true,width : 0},
				{ label: 'lastLoginTime' , name: 'lastLoginTime', hidden:true,width : 0},
				{ label: 'accountLock' , name: 'accountLock', hidden:true,width : 0},
		     	{ label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'soNm', width : 150, align:"center"},
		     	{ label: '<spring:message code="LAB.M07.LAB00067"/>' , name: 'userId',width : 150, align:"left"},
			    { label: '<spring:message code="LAB.M07.LAB00071"/>', name: 'userName', width : 200},
			    { label: '<spring:message code="LAB.M07.LAB00028"/>', name: 'useYn', width : 150,align:"center"}, 
			    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrId', width : 150},           
			    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 180,align:"center"},    
			    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrId', width : 150},
			    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 180,align:"center"},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 240,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "userList",
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
       		$("#condUserNm").focus();
       		$("#userGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       	},
    	sortable: { update: function(permutation) {
    		$("#userGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

		$("#userGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

		//그리드 화면 재조정
		$(window).resize(function() {
			$("#userGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}); 
		
		//검색 버튼 이벤트
		$('#btn_srch').click(function() {
			fnSearch();

		});
		//검색 enter 이벤트
		$( "#condUserNm" ).keypress(function(event) {
			var checkR = "<c:out value='${menuAuthR}'/>"; 
			if(checkR == 'N') return;
			
	   		if(event.keyCode == 13){
	   			fnSearch();
	   		}
		});
		//pw주기 변경 이벤트
	    $('#passwordChangePeriod').selectmenu({
	        change: function() {
	        	changePeriod();
	        }
	    });
		//초기화 버튼 이벤트
		$('#initBtn').click(function() {
			if($("#initBtn").hasClass('not-active')){
				return;
			}
			
			initBtn();
		});
			
		//사업자ID keyup 이벤트
	  	$('#userId').keyup(function(){
	  		var str = getMaxStr($('#userId').val(), 20);
		  	if(str != $('#userId').val()){
		  		$('#userId').val(str);
			}
		    if (!(event.keyCode >=37 && event.keyCode<=40)) {
		        var inputVal = $(this).val();
		        $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
		     }
	  	});
		//사용자명 keyup 이벤트
	  	$('#userName').keyup(function(){
		  	var str = getMaxStr($('#userName').val(), 30);
		  	if(str != $('#userName').val()){
		  		$('#userName').val(str);
		  	}
	  	});
		//전화번호 keyup이벤트
	  	$('#telNo').keyup(function(){
	  		$('#telNo').val(telNoAutoFormatter($('#telNo').val()));
	  		$('#telNo').val(getMaxStr($('#telNo').val(), 20));
	  	});
		//핸드폰번호 keyup이벤트
	  	$('#mtelNo').keyup(function(){
	  		$('#mtelNo').val(telNoAutoFormatter($('#mtelNo').val()));
	  		$('#mtelNo').val(getMaxStr($('#mtelNo').val(), 20));
	  		}
		);
		  	
	    //사원번호 keyup 이벤트 영문,숫자만 입력가능하게
	  	$('#empNo').keyup(function(){
		  	var str = getMaxStr($('#empNo').val(), 10);
		  	if(str != $('#empNo').val()){
		  		$('#empNo').val(str);
			}
		    if (!(event.keyCode >=37 && event.keyCode<=40)) {
		        var inputVal = $(this).val();
		        $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
		     }
	  	});
	 	//Email keyup 이벤트
	  	$('#eMail').keyup(function(){
		  	var str = getMaxStr($('#eMail').val(), 50);
		  	if(str != $('#eMail').val()){
		  		$('#eMail').val(str);
		 	}
	  	});
		  	
		 $('#ipBandwidth').focusout(function(){
		 	checkIP($('#ipBandwidth').val());
		});
		 
		//기본그룹 팝업 
		$("#btn_popUp").click(function() {
			if($("#btn_popUp").hasClass('not-active')){
				return;
			}
				var param = new Object(); 
				
				param.multiFlag="N";
				param.userGroupId='userGroupId';
				param.userGroupName='userGroupName';
				var url="/system/common/common/userGroupMng/userGroupListPopup.ajax";
				

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
			 
			//추가 버튼 클릭
			$("#newBtn").click(function() {
				if($("#newBtn").hasClass('not-active')){
					return;
				}
				var infoData = 'userId=' + $('#userId').val().trim();
	   			infoData = infoData + '&password=' + $('#password').val();
	   			infoData = infoData + '&userName=' + $('#userName').val().trim();
	   			infoData = infoData + '&orgId=' + $('#orgId').val();
	   			infoData = infoData + '&userGroupId=' +$('#userGroupId').val();
	   			infoData = infoData + '&telNo=' +getTelNo($('#telNo').val().trim());
	   			infoData = infoData + '&mtelNo=' +getTelNo($('#mtelNo').val().trim());
	   			infoData = infoData + '&empNo=' + $('#empNo').val().trim();
	   			infoData = infoData + '&eMail=' + $('#eMail').val().trim();
	   			infoData = infoData + '&ipBandwidth=' + $('#ipBandwidth').val().trim();
	   			infoData = infoData + '&passwordDueDate=' + dateFormatToStringYYYYMMDD($('#passwordDueDate').val());
	   			infoData = infoData + '&crrTp=' + "A";
	   			var useYn=$('#userInfo select').serialize();
	   			var user=infoData+"&"+useYn;
	   			
	   			if($("#userId").val() == ''|| ($("#userId").val().trim()).length==0){
	   				$("#userId").focus();
	  				alert('<spring:message code="MSG.M07.MSG00042"/>');
	  				return;
   				}
	   			if(!$("#userId").hasClass('not-active')){
	   				$("#userId").focus();
	   				alert('<spring:message code="MSG.M07.MSG00041"/>');
	   				return;
	   			}
	   			
	   			if($("#userName").val() == ''|| ($("#userName").val().trim()).length==0){
	   				$("#userName").focus();
	   				alert('<spring:message code="MSG.M07.MSG00045"/>');
	   				return;
	   			}
	   			
	   			if($("#orgNm").val() == ''){
	   				$("#orgNm").focus();
	   				alert('<spring:message code="MSG.M09.MSG00037"/>');
	   				return;
	   			}
	   			if($("#userGroupName").val() == ''){
	   				$("#userGroupName").focus();
	   				alert('<spring:message code="MSG.M01.MSG00054"/>');
	   				return;
	   			}
	   			if($("#crrTp").val() == 'SEL'){
	   				alert('<spring:message code="MSG.M07.MSG00046"/>');
					return;
				}
	   			if($("#useYn").val() == 'SEL'){
	   				$("#useYn-button").focus();
	   				alert('<spring:message code="MSG.M07.MSG00017"/>');
	   				return;
	   			}
	   			if($("#mtelNo").val() == ''|| ($("#mtelNo").val().trim()).length==0){
	   				$("#mtelNo").focus();
	   				alert('<spring:message code="MSG.M14.MSG00026"/>');
	   				return;
	   			} 
	   			if($("#eMail").val() == null || ($("#eMail").val().trim()).length==0){
	   				$('#eMail').focus();
	   				alert('<spring:message code="MSG.M08.MSG00048"/>');
	   				return;
	   			}

				// Email형식체크
				if(checkEmailStr($("#eMail").val()) == false){
					$('#eMail').focus();
					alert('<spring:message code="MSG.M08.MSG00046" />');
					return false;
				}
	   			if($("#passwordChangePeriod").val() == 'SEL'){
	   				alert('<spring:message code="MSG.M15.MSG00030"/>');
					return;
				}
	   			if($("#ipBandwidth").val() == '' || ($("#ipBandwidth").val().trim()).length==0){
	   				$('#ipBandwidth').focus();
   					alert('<spring:message code="MSG.M15.MSG00027"/>');
					return;
				}  
	   			if($("#accountLock").val() == 'SEL'){
	   				$("#accountLock-button").focus();
	   				alert('<spring:message code="MSG.M09.MSG00001"/>');
	   				return;
	   			}
				
				$.ajax({
	   	            url:'/system/user/userMng/userMng/insertAction.json',
	   	            type:'POST',
	   	            data : user,
	   	            dataType: 'json',
	   	            success: function(data){
	   	   	          		$("#userGrid").clearGridData();
	   	   	        		//입력부 초기화
			   	   	      	inputInit('Y');
			   	   	      	
			   	   	      	//버튼 컨트롤
			   	   	      	btnCtrl('I');
			   	   	      	
	   	   	          		$("#userGrid").setGridParam({
				   	   	        postData : {
				   	   	    	condSoNm : $("#condSoNm").val(),
				   	   			condOrgId : $("#condOrgId").val(),
								condOrgNm : $("#condOrgNm").val(),
								condUserNm:$("#condUserNm").val(),
								condYn : $("#condYn").val(),
								condLockYn:$("#condLockYn").val()
				   	   			}
	   	   	          		});
	   	   	         		alert('<spring:message code="MSG.M09.MSG00005"/>');
	   	   	          		$("#userGrid").trigger("reloadGrid"); 
	 
	   	            	
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
			});
			
			
			//수정 버튼 클릭
			$("#updateBtn").click(function() {
				if($("#updateBtn").hasClass('not-active')){
					return;
				}
				if($("#accountLock").val()=="N" && $("#gridLockYn").val()=="Y"){
					$("#loginFailCount").val(0);
				}
				
				var infoData = 'userId=' + $('#userId').val().trim();
	   			infoData = infoData + '&password=' + $('#password').val();
	   			infoData = infoData + '&userName=' + $('#userName').val().trim();
	   			infoData = infoData + '&orgId=' + $('#orgId').val();
	   			infoData = infoData + '&userGroupId=' +$('#userGroupId').val();
	   			infoData = infoData + '&telNo=' +getTelNo($('#telNo').val().trim());
	   			infoData = infoData + '&mtelNo=' +getTelNo($('#mtelNo').val().trim());
	   			infoData = infoData + '&empNo=' + $('#empNo').val().trim();
	   			infoData = infoData + '&eMail=' + $('#eMail').val().trim();
	   			infoData = infoData + '&ipBandwidth=' + $('#ipBandwidth').val().trim();
	   			infoData = infoData + '&passwordDueDate=' + dateFormatToStringYYYYMMDD($('#passwordDueDate').val());
	   			infoData = infoData + '&crrTp=' + $('#crrTp').val();
	   			infoData = infoData + '&loginFailCount=' + $('#loginFailCount').val();
	   			var useYn=$('#userInfo select').serialize();
	   			var user=infoData+"&"+useYn;
	   			
	   			if($("#userId").val() == ''|| ($("#userId").val().trim()).length==0){
		   				$("#userId").focus();
	   				alert('<spring:message code="MSG.M07.MSG00042"/>');
	   				return;
	   			}
	   			if(!$("#userId").hasClass('not-active')){
	   				$("#userId").focus();
	   				alert('<spring:message code="MSG.M07.MSG00041"/>');
	   				return;
	   			}
	   			
	   			if($("#userName").val() == ''|| ($("#userName").val().trim()).length==0){
	   				$("#userName").focus();
	   				alert('<spring:message code="MSG.M07.MSG00045"/>');
	   				return;
	   			}
	   			
	   			if($("#orgNm").val() == ''){
	   				$("#orgNm").focus();
	   				alert('<spring:message code="MSG.M09.MSG00037"/>');
	   				return;
	   			}
	   			if($("#userGroupName").val() == ''){
	   				$("#userGroupName").focus();
	   				alert('<spring:message code="MSG.M01.MSG00054"/>');
	   				return;
	   			}
	   			if($("#crrTp").val() == 'SEL'){
	   				alert('<spring:message code="MSG.M07.MSG00046"/>');
					return;
				}
	   			if($("#useYn").val() == 'SEL'){
	   				$("#useYn-button").focus();
	   				alert('<spring:message code="MSG.M07.MSG00017"/>');
	   				return;
	   			}
	   			if($("#mtelNo").val() == ''|| ($("#mtelNo").val().trim()).length==0){
	   				$("#mtelNo").focus();
	   				alert('<spring:message code="MSG.M14.MSG00026"/>');
	   				return;
	   			} 
	   			if($("#eMail").val() == null || ($("#eMail").val().trim()).length==0){
	   				$('#eMail').focus();
	   				alert('<spring:message code="MSG.M08.MSG00048"/>');
	   				return;
	   			}

				// Email형식체크
				if(checkEmailStr($("#eMail").val()) == false){
					$('#eMail').focus();
					alert('<spring:message code="MSG.M08.MSG00046" />');
					return false;
				}
	   			if($("#passwordChangePeriod").val() == 'SEL'){
	   				alert('<spring:message code="MSG.M15.MSG00030"/>');
					return;
				}
	   			if($("#ipBandwidth").val() == '' || ($("#ipBandwidth").val().trim()).length==0){
	   				$('#ipBandwidth').focus();
   					alert('<spring:message code="MSG.M15.MSG00027"/>');
					return;
				}  
	   			if($("#accountLock").val() == 'SEL'){
	   				$("#accountLock-button").focus();
	   				alert('<spring:message code="MSG.M09.MSG00001"/>');
	   				return;
	   			}
				$.ajax({
	   	            url:'/system/user/userMng/userMng/updateAction.json',
	   	            type:'POST',
	   	            data : user,
	   	            dataType: 'json',
	   	            success: function(data){
	   	   	          		$("#userGrid").clearGridData();
	   	   	        		//입력부 초기화
			   	   	      	inputInit('Y');
			   	   	      	
			   	   	      	//버튼 컨트롤
			   	   	      	btnCtrl('I');
			   	   	      	
	   	   	          		$("#userGrid").setGridParam({
				   	   	        postData : {
				   	   	     	condSoNm : $("#condSoNm").val(),
				   	  		 	condOrgId : $("#condOrgId").val(),
								condOrgNm : $("#condOrgNm").val(),
								condUserNm:$("#condUserNm").val(),
								condYn : $("#condYn").val(),
								condLockYn:$("#condLockYn").val()
				   	   			}
	   	   	          		});
	   	   	          		alert('<spring:message code="MSG.M07.MSG00089"/>');
	   	   	          		$("#userGrid").trigger("reloadGrid"); 
	 
	   	            	
	   	            },
	   	         	beforeSend: function(data){
	   	         	},
	   	         	error : function(err){
	   	         		alert('<spring:message code="MSG.M09.MSG00020"/>');
	   	         	}
	   	        });
			});
				
				
			$("#userId-btn").click(function() {
				checkUserId();
			});
				
			$('#condOrgNm_popup').on('click',function (e) {
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
				//조직 Popup
		    $('#org_popup').on('click',function (e) {
		    	if($("#org_popup").hasClass('not-active')){
		    		return;
		    	}
		    	var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
		    	var param = new Object();
		    	param.popType = "m";			//팝업타입 m:모달 o:일반
		    	param.returnId1 = "orgId";	//리턴받는 orgId 의 태그아이디값
		    	param.returnId2 = "orgNm";	//리턴받는 orgNm 의 태그아이디값
		    	 

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
			    
		    $('#password-btn').on('click',function (e) {
		    	if($("#password-btn").hasClass('not-active')){
		    		return;
		    	}
		    	var user = 'userId=' + $('#userId').val();
				user=user + '&eMail=' + $('#eMail').val();
		    	$.ajax({
	   	            url:'/system/user/userMng/userMng/updatePasswordResetAction.json',
	   	            type:'POST',
	   	            data : user,
	   	            dataType: 'json',
	   	            success: function(data){
	   	            	alert('<spring:message code="MSG.M10.MSG00011"/>');
	   	            	$("#password").val(data.change);
	   	            },
	   	         	beforeSend: function(data){
	   	         	},
	   	         	error : function(err){
	   	         		alert('<spring:message code="MSG.M09.MSG00020"/>');
	   	         	}
	   	        });
		    });
		    
		    $('#userId_popup').on('click',function (e) {
				 $("#condUserNm").val('');	//돋보기 클릭시 초기화
			    	/*  var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
			    	var param = new Object();
			    	param.popType = "m";			//팝업타입 m:모달 o:일반
			    	param.returnId1 = "condUserNm";
			    	param.returnId2 = "condUserId";
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
			    	});  */    
			    	
				 var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
				 url = url + "?returnId1=condUserNm";           //사용자이름
				 url = url + "&returnId2=condUserId"; 			//사용자ID
				 url = url + "&popType=o";   //모드 o지정 
				  
				 var width = 840;
				 var height = 478;
				 var LeftPosition=(screen.width-width)/2;
				 var TopPosition=(screen.height-height)/2;
				  
				 window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no");
				  
			 });
})
/*
화면 초기화 함수
*/
function pageInit() {

	//검색정보 초기화
	
	$('#condSoId').val('SEL');
	$('#condSoId').selectmenu("refresh");
	$('#condOrgId').val('');
	$('#condOrgNm').val('');
	$("#condOrgNm").addClass('not-active');
	$("#condOrgNm").attr('disabled', true);
	$('#condUserNm').val('');
	$('#condYn').val('SEL');
	$('#condYn').selectmenu("refresh");
	$('#condLockYn').val('SEL');
	$('#condLockYn').selectmenu("refresh");
	$("#userGrid").clearGridData();
	
	//입력부 초기화

	inputInit('N');

}
/*
 * 입력부 초기화 함수
 */
function inputInit(srch) {
	if (srch=='Y') {	//검색조건유지
		$('#condSoId').val($('#condSoId').val());
		$('#condOrgId').val($('#condOrgId').val());
		$('#condOrgNm').val($('#condOrgNm').val());
		$('#condUserNm').val($('#condUserNm').val());
		$('#condYn').val($('#condYn').val());
		$('#condLockYn').val($('#condLockYn').val());
	}else{//검색정보 초기화
		
		$('#condSoId').val('SEL');
		$('#condSoId').selectmenu("refresh");
		$('#condOrgId').val('');
		$('#condOrgNm').val('');
		$('#condUserNm').val('');
		$('#condYn').val('SEL');
		$('#condYn').selectmenu("refresh");
		$('#condLockYn').val('SEL');
		$('#condLockYn').selectmenu("refresh");
	}
	//사용자 리스트 Disable처리
	$("#userInfo input:text").val('');
	$("#userInfo input:text").addClass('not-active');
	$("#userInfo input:text").attr('disabled', true);
	$("#password").val('');
	$("#password").addClass('not-active');
	$("#password").attr('disabled', true);
	
	$(".td-btn").addClass('not-active');
	$(".td-btn").attr('disabled', true);
	$(".btn_cal").addClass('not-active');
	$(".btn_cal").attr('disabled', true);
	$(".search").addClass('not-active');
	$(".search").attr('disabled', true);
	$("#condOrgNm_popup").removeClass('not-active');
	$("#condOrgNm_popup").removeAttr('disabled');
	$("#userId_popup").removeClass('not-active');
	$("#userId_popup").removeAttr('disabled');
	
	$('#useYn').val('SEL');
	$('#useYn').selectmenu("refresh");
	$("#userInfo .ui-selectmenu-button").addClass('not-active');
	$("#userInfo .ui-selectmenu-button").attr('disabled', 'true'); 
	$("#userInfo select").selectmenu("disable");
	$('#crrTp').val('SEL');
	$('#crrTp').selectmenu("refresh");
	
	$("#password-btn").addClass('not-active');	//패스워드 초기화 버튼 클릭안되게
	$("#password-btn").attr('disabled', true);
	$("#password-btn").removeClass('td-grey-btn');
	$("#password-btn").addClass('td-btn');
	
	$("#userId-btn").addClass('not-active');	//중복확인 버튼 클릭안되게
	$("#userId-btn").attr('disabled', true);
	$("#userId-btn").removeClass('td-grey-btn');
	$("#userId-btn").addClass('td-btn');
	
	$('#passwordChangePeriod').val('SEL');
	$('#passwordChangePeriod').selectmenu("refresh");
	$('#accountLock').val('SEL');
	$('#accountLock').selectmenu("refresh");
	
	//로그인정보 Disable처리
	$("#loginInfo input:text").val('');
	$("#loginInfo input:text").addClass('not-active');
	$("#loginInfo input:text").attr('disabled', true);

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
	$("#commonBtn a").attr('disabled', true);

	if (mode == 'I') {

		btnEnable('initBtn');

	} else if (mode == 'N') {
		btnEnable('initBtn');
		btnEnable('newBtn');

	} else if (mode == 'U') {
		btnEnable('initBtn');
		btnEnable('updateBtn'); //updateBtn 활설화
		btnDisable('deleteBtn');
	}
}

function initBtn() {//초기화 버튼 클릭시
	//입력정보 초기화
	inputInit('Y');

	//기본정보 활성화 처리
	
	$('#userId').removeClass('not-active');
	$('#userId').removeAttr('disabled');
	$("#userId").removeAttr('readonly', 'readonly');
	$('#userName').removeClass('not-active');
	$('#userName').removeAttr('disabled');
	$('#empNo').removeClass('not-active');
	$('#empNo').removeAttr('disabled');
	$('#telNo').removeClass('not-active');
	$('#telNo').removeAttr('disabled');
	$('#mtelNo').removeClass('not-active');
	$('#mtelNo').removeAttr('disabled');
	$('#eMail').removeClass('not-active');
	$('#eMail').removeAttr('disabled');
	$('#ipBandwidth').removeClass('not-active');
	$('#ipBandwidth').removeAttr('disabled');

	$('#userGroupId').removeClass('not-active');
	$("#userInfo select").selectmenu("enable");
	$("#useYn-button").removeClass('not-active');
	$("#useYn-button").removeAttr('disabled');
	$("#userId").focus();
	$("#passwordChangePeriod").val("30"); //pw주기는 insert시 30으로 SET!
	$('#passwordChangePeriod').selectmenu("refresh");
	$("#accountLock").val('SEL'); //등록시에 잠금여부는 Y
	$('#accountLock').selectmenu("refresh");
	$("#accountLock-button").removeClass('not-active');
	$("#accountLock-button").removeAttr('disabled');
	$("#crrTp-button").addClass('not-active');
	$("#crrTp-button").attr('disabled');
	$("#crrTp").selectmenu("disable");
	$("#crrTp").val('A');
	$('#crrTp').selectmenu("refresh");

	var d = new Date();
	var dt = new Date(d.getFullYear(), (d.getMonth()), d.getDate());
	$('#passwordDueDate').val(dateFormatterUsingDateYYYYMMDD(dt)); //pw변경일자는 insert시 현재날짜로 SET!

	$(".search").removeClass('not-active');
	$(".search").removeAttr('disabled');
	$(".td-btn").removeClass('not-active');
	$(".td-btn").removeAttr('disabled');
	$("#password-btn").addClass('not-active');
	$("#password-btn").attr('disabled', true);
	
	$("#userId-btn").removeClass('not-active');
	$("#userId-btn").removeAttr('disabled');
	$("#userId-btn").removeClass('td-btn');
	$("#userId-btn").addClass('td-grey-btn');
	/* btnEnable('userId-btn'); */
	//버튼 컨트롤
	btnCtrl('N');
	$("#userGrid").jqGrid("resetSelection");
}

	/*
	 * 데이터 선택 이벤트 처리
	 */
function setSelectedDate(rowId) {
	$("#userInfo input:text").removeClass('not-active');
	$("#userInfo input:text").removeAttr('disabled');
	$("#userInfo .ui-selectmenu-button").removeClass('not-active');
	$("#userInfo .ui-selectmenu-button").removeAttr('disabled');
	$("#userId").addClass('not-active');
	$("#userId").attr('readonly', 'true');
	$("#userId").attr('disabled', true);
	$("#password").addClass('not-active');
	$("#password").attr('readonly', 'true');
	$("#password").attr('disabled', true);
	$("#orgId").addClass('not-active');
	$("#orgId").attr('readonly', 'true');
	$("#orgNm").addClass('not-active');
	$("#orgNm").attr('readonly', 'true');
	$("#orgNm").attr('disabled', true);
	$("#userGroupId").addClass('not-active');
	$("#userGroupId").attr('readonly', 'true');
	$("#userGroupName").addClass('not-active');
	$("#userGroupName").attr('readonly', 'true');
	$("#userGroupName").attr('disabled', true);
	$("#passwordDueDate").attr('readonly', 'true');	

	$(".search").removeClass('not-active');
	$(".search").removeAttr('disabled');
	$(".btn_cal").removeClass('not-active');
	$(".btn_cal").removeAttr('disabled',true);

	$("#password-btn").removeClass('not-active');
	$("#password-btn").removeAttr('disabled');
	$("#password-btn").removeClass('td-btn');
	$("#password-btn").addClass('td-grey-btn');

	$("#userId-btn").addClass('not-active');	//중복확인 버튼 클릭안되게
	$("#userId-btn").attr('disabled', true);
	$("#userId-btn").removeClass('td-grey-btn');
	$("#userId-btn").addClass('td-btn');
	
	var data = $("#userGrid").getRowData(rowId);
	$("#userId").val(data.userId);
	$("#userName").val(data.userName);
	$("#userName").focus();
	$("#password").val(data.password);
	$("#userGroupId").val(data.userGroupId);
	$("#userGroupName").val(data.userGroupName);
	$("#orgId").val(data.orgId);
	$("#orgNm").val(data.orgNm);
	$("#crrTp").val(data.crrTp);
	$("#empNo").val(data.empNo);
	$("#telNo").val(data.telNo);
	$("#mtelNo").val(data.mtelNo);
	$("#eMail").val(data.eMail);
	$("#ipBandwidth").val(data.ipBandwidth);
	$("#loginFailCount").val(data.loginFailCount);
	$("#passwordDueDate").val(data.passwordDueDate);
	$("#passwordChangePeriod").val(data.passwordChangePeriod);
	var lastLoginTime = data.lastLoginTime.split("|");
	var lastLoginDate = data.lastLoginDate.split("|");
	
	if (lastLoginTime.length == 2) {
		if (lastLoginTime[0] == "") {
			var a = "";
		} else {
			var a = lastLoginTime[0];
		}
		if (lastLoginTime[1] == "") {
			var b = "";
		} else {
			var b = lastLoginTime[1];
		}
	} else {
		var a="";
		var b="";
	}
	
	if (lastLoginDate.length == 2) {
		if (lastLoginDate[0] == "") {
			var c = "";
		} else {
			var c = lastLoginDate[0];
		}
		
		if (lastLoginDate[1] == "") {
			var d = "";
		} else {
			var d = lastLoginDate[1];
		}
	} else {
		var c="";
		var d="";
	}
	
	var beforeLogin=c+a;
	var login=d+b;
	
	login=stringToDateformatYYYYMMDDHH24MISS(login);
	beforeLogin=stringToDateformatYYYYMMDDHH24MISS(beforeLogin);
	$("#lastLoginTime").val(login);
	$("#beforeLoginTime").val(beforeLogin); 
	//사용자유형은 우선 disabled

	$('#crrTp').val(data.crrTp);
	$('#crrTp').selectmenu("refresh");
	$('#useYn').val(data.useYn);
	$('#useYn').selectmenu("refresh");
	$('#useYn').selectmenu("enable");
	$('#passwordChangePeriod').val(data.passwordChangePeriod);
	$('#passwordChangePeriod').selectmenu("refresh");
	$('#passwordChangePeriod').selectmenu("enable");
	$('#accountLock').val(data.accountLock);
	$('#accountLock').selectmenu("refresh");
	$('#accountLock').selectmenu("enable");
	$('#gridLockYn').val(data.accountLock);
	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled', true);

	btnCtrl('U');
}

function checkIP(strIP) { //IP체크
	if($("#ipBandwidth").val() == '' || ($("#ipBandwidth").val().trim()).length==0){
 		return;
 	}
		 	
	var strIP = strIP.split(".");
	if (strIP.length != 4) {
		alert('<spring:message code="MSG.M15.MSG00027"/>');
		$('#ipBandwidth').val('');
		$('#ipBandwidth').focus();
		return;
	}
	if (((0 <= strIP[0] && strIP[0] <= 255) || strIP[0] == "*")
			&& ((0 <= strIP[1] && strIP[1] <= 255) || strIP[1] == "*")
			&& ((0 <= strIP[2] && strIP[2] <= 255) || strIP[2] == "*")
			&& ((0 <= strIP[3] && strIP[3] <= 255) || strIP[3] == "*")) {

	} else {
		alert('<spring:message code="MSG.M15.MSG00027"/>');
		$('#ipBandwidth').val('');
		$('#ipBandwidth').focus();
		return;
	}

}

function changePeriod() { //pw주기 선택시 현재날짜에서 pw주기만큼 증가
	var d = new Date();
	if ($('#passwordChangePeriod').val() == '30') {
		var dt = new Date(d.getFullYear(), (d.getMonth()), d.getDate() + 30);
	}
	if ($('#passwordChangePeriod').val() == '60') {
		var dt = new Date(d.getFullYear(), (d.getMonth()), d.getDate() + 60);
	}
	if ($('#passwordChangePeriod').val() == '90') {
		var dt = new Date(d.getFullYear(), (d.getMonth()), d.getDate() + 90);
	}

	$('#passwordDueDate').val(dateFormatterUsingDateYYYYMMDD(dt));

}
function checkUserId() { //ID중복체크
	if ($("#userId-btn").hasClass('not-active')) {
		return;
	}
	var user = 'userId=' + $('#userId').val();

	if ($('#userId').val() == ""|| ($("#userId").val().trim()).length==0) {
		$('#userId').val($("#userId").val().trim());	//공백제거 후 focus
		$('#userId').focus();
		alert('<spring:message code="MSG.M07.MSG00043"/>');
		return;
	}
	
			$.ajax({
				url : '/system/user/userMng/userMng/getCheckUserIdAction.json',
				type : 'POST',
				data : user,
				dataType : 'json',
				success : function(mode) {

					if (mode.check == 1) {
						alert('<spring:message code="MSG.M14.MSG00018"/>'); //아이디 이미존재
						$("#userId").val(""); //아이디 초기화
						return false;
					} else {
						alert('<spring:message code="MSG.M07.MSG00011"/>'); //ID사용가능
						$("#userId").addClass('not-active');
						$("#userId").attr('readonly', 'true');
						$("#userId").attr('disabled', true);

						$("#userId-btn").addClass('not-active');	//중복확인 버튼 클릭안되게
						$("#userId-btn").attr('disabled', true);
						$("#userId-btn").removeClass('td-grey-btn');
						$("#userId-btn").addClass('td-btn');

					}

				},
				beforeSend : function(data) {
				},
				error : function(err) {
					alert('<spring:message code="MSG.M09.MSG00020"/>');
				}
			});
}

function fnSearch() {
	$("#userGrid").clearGridData();
	//입력부 초기화
	inputInit('Y');

	//버튼 컨트롤
	btnCtrl('I');

	$("#userGrid").setGridParam({
		postData : {
			condSoId : $("#condSoId").val(),
			condOrgId : $("#condOrgId").val(),
			condOrgNm : $("#condOrgNm").val(),
			condUserNm : $("#condUserNm").val(),
			condYn : $("#condYn").val(),
			condLockYn : $("#condLockYn").val(),
			srchYn : "Y"
		}
	});

	$("#userGrid").trigger("reloadGrid");
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
<input type="hidden" id="gridLockYn" name="gridLockYn"/> <!--update시 grid의 잠김여부와 수정할 잠김여부 비교  -->
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
		<col style="width: 30%;">
		<col style="width: 10%;">
		<col style="width: 20%;">
		<col style="width: 10%;">
		<col style="width: 20%;">
	</colgroup>
	<thead>
		<tr>
			<th title="<spring:message code="LAB.M07.LAB00003"/>"><spring:message code="LAB.M07.LAB00003"/></th>
				<td>
					<select id="condSoId" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
					</select>
				</td>
			
			<th><spring:message code="LAB.M07.LAB00071"/></th>
			<td colspan="3">
			<div class="inp_date w280">
			<input name="condUserNm" id="condUserNm" type="text" class="w270" />
			<input name="condUserId" id="condUserId" type="hidden" class="w270" />
			</div>
			<%-- <a href="#" id="userId_popup"class="search"><spring:message code="LAB.M09.LAB00158"/></a>  --%>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00138"/></th>
			<td>
				<div class="inp_date w100p">
					<input type="text" id="condOrgNm" name="condOrgNm" class="w100p">
					<input type="hidden" id="condOrgId" name="condOrgId">
					<a href="#" id="condOrgNm_popup" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
				</div>
			</td> 
			<th><spring:message code="LAB.M07.LAB00028"/></th>
			<td>
				<select id="condYn" name="condYn" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<option value="Y">Y</option>
					<option value="N">N</option>
				</select>
				</td>
			<th><spring:message code="LAB.M09.LAB00041"/></th>
			<td colspan="">
				<select id="condLockYn"name="condLockYn" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<option value="Y">Y</option>
					<option value="N">N</option>
				</select>
			</td>
		</tr>
	</thead>
</table>
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00070"/></h4>
	</div>
</div>
<div id='gridDiv' class="w100p">
	<table id="userGrid" class="w100p"></table>
	<div id="jqGridPager"></div>
</div> 
<div id="userInfo">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00076"/></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 8%;">
			<col style="width: 17%;">
			<col style="width: 8%;">
			<col style="width: 17%;">
			<col style="width: 8%;">
			<col style="width: 17%;">
			<col style="width: 8%;">
			<col style="width: 17%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M07.LAB00067"/><span class="dot">*</span></th>
				<td>
					<div class="w160">
						<input name="userId" id="userId" type="text" class="w90" /> 
						<a href="#" id="userId-btn" class="td-btn"><spring:message code="LAB.M07.LAB00066"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M07.LAB00071"/><span class="dot">*</span></th>
				<td><input type="text" id="userName" name="userName" class="w100p" /></td>
				<th><spring:message code="LAB.M13.LAB00013"/><span class="dot">*</span></th>
				<td colspan="3">
					<input type="password" id="password" name="password" class="w280" />
					 <a href="#" id="password-btn" class="td-btn"><spring:message code="LAB.M10.LAB00050"/></a>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00138"/><span class="dot">*</span></th>
				<td>
					<div class="inp_date w100p">
						<input type="hidden" id="orgId" name="orgId"> 
						<input type="text" id="orgNm" name="orgNm" class="w100p">
						<a href="#" id="org_popup" class="search"><spring:message code="LAB.M01.LAB00025"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00202"/><span class="dot">*</span></th>
				<td>
					<div class="inp_date w100p">
						<input type="text" id="userGroupName" name="userGroupName" class="w100p">
						<input type="hidden" id="userGroupId" name="userGroupId">
						 <a href="#" id="btn_popUp" class="search"><spring:message code="LAB.M01.LAB00025"/></a>
					</div>
				</td>
				<th><spring:message code="LAB.M07.LAB00074"/><span class="dot">*</span></th>
				<td>
					<select id="crrTp" name="crrTp" class="w100p">
						<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${commonCodeList}" var="crrTpCode" varStatus="status">
					 	<option value="${crrTpCode.commonCd}">${crrTpCode.commonCdNm}</option>
					</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M07.LAB00028"/><span class="dot">*</span></th>
				<td>
					<select id="useYn" name="useYn" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<option value="Y">Y</option>
						<option value="N">N</option>
				</select>
			</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00081"/></th>
				<td><input id="empNo" name="empNo" type="text" class="w100p" /></td>
				<th><spring:message code="LAB.M09.LAB00065"/></th>
				<td><input id="telNo" name="telNo" type="text" class="w100p" /></td>
				<th><spring:message code="LAB.M14.LAB00076"/><span class="dot">*</span></th>
				<td><input id="mtelNo" name="mtelNo" type="text" class="w100p" /></td>
				<th><spring:message code="LAB.M08.LAB00119"/><span class="dot">*</span></th>
				<td><input id="eMail" name="eMail" type="text" class="w100p" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00053"/><span class="dot">*</span></th>
				<td><input id="ipBandwidth" name="ipBandwidth" type="text" class="w100p" /></td>
				<th><spring:message code="LAB.M15.LAB00075"/></th>
				<td>
					<div class="inp_date w100p">
					<fmt:parseDate value="${passwordDueDate}" var="passwordDueDate" pattern="${dateFormat4}"/> 
				 	<input  type="text" id="passwordDueDate" name="passwordDueDate" value="<fmt:formatDate value="${passwordDueDate}"  pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a> 
					</div>
				</td>
				<th><spring:message code="LAB.M15.LAB00076"/><span class="dot">*</span></th>
				<td><select id="passwordChangePeriod" name="passwordChangePeriod" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
							<option value="30">30</option>
							<option value="60">60</option>
							<option value="90">90</option>
				</select></td>
				<th><spring:message code="LAB.M09.LAB00041"/><span class="dot">*</span></th>
				<td>
					<select id="accountLock" name="accountLock" class="w100p">
							<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
							<option value="Y">Y</option>
							<option value="N">N</option>
					</select>
				</td>
			</tr>
		</thead>
	</table>
	</div>
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M04.LAB00012"/></h4>
	</div>
</div>
<div id="loginInfo">
	<table class="writeview">
		<colgroup>
			<col style="width: 8%;">
			<col style="width: 25%;">
			<col style="width: 10%;">
			<col style="width: 25%;">
			<col style="width: 8%;">
			<col style="width: 23%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message code="LAB.M10.LAB00054"/></th>
				<td><input id="lastLoginTime" name="lastLoginTime" type="text" class="w100p" /></td>
				<th><spring:message code="LAB.M08.LAB00124"/></th>
				<td><input id="beforeLoginTime" name="beforeLoginTime" type="text" class="w100p" /></td>
				<th><spring:message code="LAB.M04.LAB00011"/></th>
				<td colspan="3"><input id="loginFailCount" name="loginFailCount" type="text" class="w100p" /></td>
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
		<a class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth> 
	</div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;" >
</div>


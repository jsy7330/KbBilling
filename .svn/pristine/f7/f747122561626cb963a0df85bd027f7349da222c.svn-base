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
	$("#useYn").selectmenu("disable");	//로드시 useYn 비활성화
	$('#useYn').val('SEL');
	$('#useYn').selectmenu("refresh");
	
	var tree = $("#menuTree");
	
	var downMenuList = new Object();
	//트리 초기화
	$(tree).dynatree({
 		initAjax: {
 			type: "POST",
 			url: "/system/menu/menuMng/menuMng/getMenuListAction.json",
			complete : function(){
				tree.dynatree("getTree").activateKey('ROOT'); 	//로드시 루트 활성화
			}
 		},
 		onActivate: function(node) {
 			
            // A DynaTreeNode object is passed to the activation handler
            // Note: we also get this event, if persistence is on, and the page is reloaded.
            getCodeList(node);

    	},
		clickFolderMode: 3,
		minExpandLevel: 2, //1: root node is not collapsible
		selectMode: 1 // 1:single, 2:multi, 3:multi-hier
	});
	inputInit('');
	btnCtrl('');
	
	//코드상세 그리드
	$("#downMenuGrid").jqGrid({
		url: "/system/menu/menuMng/menuMng/getDownMenuListAction.json",
		datatype : 'json',
		mtype: 'POST',
		postData : {
			condUpMenuNo : "",
			topMenu : "ROOT"
		},
		colModel: [ 
					{ label: 'upMenuNo' , name: 'upMenuNo', hidden:true,width : 0},
					{ label: 'upMenuName' , name: 'upMenuName', hidden:true,width : 0},
					{ label: 'description' , name: 'description', hidden:true,width : 0},
					{ label: 'viewPath' , name: 'viewPath', hidden:true,width : 0},
					{ label: 'menuName' , name: 'menuName', hidden:true,width : 0},
			     	{ label: '<spring:message code="LAB.M05.LAB00027"/>', name: 'menuNo', width : 100, align:"left",key:true},
				    { label: '<spring:message code="LAB.M05.LAB00026"/>', name: 'cdMenuName', width : 100, align:"left"},
				    { label: '<spring:message code="LAB.M10.LAB00080"/>', name: 'displayOrder', width : 100, align:"left"},
				    { label: '<spring:message code="LAB.M07.LAB00026"/>', name: 'useYn', width : 70, align:"center"},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 277,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "downMenuList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowNum: -1,  
        onCellSelect : function(rowid, index, contents, event){
        	
        	setSelectedDate(rowid,downMenuList);
        	
        },
       	loadComplete : function (data) {
       		
       		var activatedNode = tree.dynatree("getActiveNode");
       		
       		if(activatedNode != null && activatedNode.getLevel() == 5){
       			$("#downMenuGrid").jqGrid("setSelection", activatedNode.data.key);
       			setSelectedDate(activatedNode.data.key,data.downMenuList);
       		}
       		downMenuList = data.downMenuList;
       		$("#downMenuGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       	},
    	sortable: { update: function(permutation) {
    		$("#downMenuGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	//언어정보 그리드
	$("#lngGrid").jqGrid({
		datatype : 'local',
		colModel: [
				{ label: 'menu_no', name: 'menu_no', width : 200, align:"left",sortable:false,hidden:true},
				{ label: 'country_code', name: 'country_code', width : 200,hidden:true},
				{ label: 'language_code', name: 'language_code', width : 200,hidden:true},
				{ label: '<spring:message code="LAB.M03.LAB00001"/>', name: 'language_name', width : 100, align:"left",sortable:false,key:true},
				{ label: '<spring:message code="LAB.M05.LAB00026"/>', name: 'menu_name', width : 200, align:"left",sortable:false, editable:true}
		],
		data : '',
		viewrecords: true,
		shrinkToFit:false,
		height: 77,
		sortable : false,
		cellEdit : true,
		cellsubmit:'clientArray',
		rowNum: -1, 
        onCellSelect : function(rowid, index, contents, event){
        	
        },
       	loadComplete : function (data) {
       		$("#lngGrid").setGridWidth($('#gridLngDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       	},
    	sortable: { update: function(permutation) {
    		$("#lngGrid").setGridWidth($('#gridLngDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	$("#lngGrid").setGridWidth($('#gridLngDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#downMenuGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	$(window).resize(function() {
		$("#lngGrid").setGridWidth($('#gridLngDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#downMenuGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}); 
	
 	$("#condName").keypress(function(event) {
   		if(event.keyCode == 13){
   			var result = seachFolderNodeWithName($("#condName").val(), tree);
   			if(!result){
   				tree.dynatree("getTree").activateKey('ROOT'); //ROOT KEY 지정
   				
   			    tree.dynatree("getRoot").visit(function (node) {
   			    	node.expand(false);
   			    });
   			}
   			
   			$('#condName').focus();
   			
   		}
   	});
 	

 	//초기화 버튼 이벤트
   	$('#detailInitBtn').on('click',function (e) {
	   		if($("#detailInitBtn").hasClass('not-active')){
				return;
			}
    		initBtn();
	});
 	
 	//신규등록 버튼 이벤트
    $('#detailUpdateBtn').on('click',function (e) {
    	if($("#detailUpdateBtn").hasClass('not-active')){
			return;
		}
    		updateMenu();
		}
    );
 	
  //수정 버튼 이벤트
    $('#detailNewBtn').on('click',function (e) {
    	if($("#detailNewBtn").hasClass('not-active')){
			return;
		}
    		insertMenu();
		}
    );
  
 	 //삭제 버튼 이벤트
    $('#detailDeleteBtn').on('click',function (e) {
    	if($("#detailDeleteBtn").hasClass('not-active')){
			return;
		}
    	if(confirm('<spring:message code="MSG.M05.MSG00008"/>')==true){
    		deleteMenu();
    	}
	});
 	 
    $('#viewPath_popup').on('click',function (e) {
	    	if($("#viewPath_popup").hasClass('not-active')){
				return;
			}
	    	var url="/system/common/common/viewPathMng/viewPathMngPopup.ajax";
	    	var param = new Object();
	    	param.popType = "m";			//팝업타입 m:모달 o:일반
	    	param.returnId1 = "viewPath";	//리턴받는 viewPath 의 태그아이디값
			
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
  
  //코드영문 숫자만 입력
    $("#menuNo").keyup(function(event){ 
    	 if (!(event.keyCode >=37 && event.keyCode<=40)) {
		 	    var inputVal = $(this).val();
		 	    $(this).val(inputVal.replace(/[^0-9]/gi,''));
		 	    
		  	    var str = getMaxStr($('#menuNo').val(), 10);
		  	    if(str != $('#menuNo').val()){
		  			$('#menuNo').val(str);
		  	    }
	 	   }
	});
  	//메뉴명 keyup 이벤트
  	$('#menuName').keyup(function(){
	  	var str = getMaxStr($('#menuName').val(), 75);
	  	if(str != $('#menuName').val()){
	  		$('#menuName').val(str);
	  	}
  	});
	//순서 숫자만 입력
    $("#displayOrder").keyup(function(event){ 
	 	   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		 	    var inputVal = $(this).val();
		 	    $(this).val(inputVal.replace(/[^0-9]/gi,''));
		 	    
		  	    var str = getMaxStr($('#displayOrder').val(), 5);
		  	    if(str != $('#displayOrder').val()){
		  			$('#displayOrder').val(str);
		  	    }
	 	   }
	});
  //설명 keyup 이벤트
  	$('#description').keyup(function(){
	  	var str = getMaxStr($('#description').val(), 100);
	  	if(str != $('#description').val()){
	  		$('#description').val(str);
	  	}
  	});
  //URL keyup 이벤트
  	$('#viewPath').keyup(function(){
	  	var str = getMaxStr($('#viewPath').val(), 256);
	  	if(str != $('#viewPath').val()){
	  		$('#viewPath').val(str);
	  	}
  	});

});

function getCodeList(node){
	
	var menuNo = '';
	if(node.getLevel() == 5){
		menuNo = node.data.upMenuNo;
	}else{
		menuNo = node.data.key;
	}
	
	$("#stepNo").val(node.data.stepNo);
	
	$("#downMenuGrid").clearGridData();
	inputInit('I');
	$("#downMenuGrid").setGridParam({
		postData : {
			condUpMenuNo : menuNo,
			topMenu : node.data.key,
			stepNo : $("#stepNo").val()
		}
	});
	$("#downMenuGrid").trigger("reloadGrid");
	btnCtrl('I');
}
	

/*
 * 데이터 선택 이벤트 처리
 */
function setSelectedDate(rowId,downMenuList) {
	inputInit('U');
	$("#menuNo").attr('disabled', true);
	$("#menuNo").addClass('not-active'); 
	
	var data = $("#downMenuGrid").getRowData(rowId);
	$("#upMenuNo").val(data.upMenuNo);
	$("#upMenuName").val(data.upMenuName);
	$("#title").focus();
	$("#menuNo").val(data.menuNo);
	$("#menuName").val(data.menuName);
	$("#userGroupName").val(data.userGroupName);
	$("#displayOrder").val(data.displayOrder);
	$("#viewPath").val(data.viewPath);
	$("#description").val(data.description);
	$("#useYn").val(data.useYn);
	$('#useYn').selectmenu("refresh");
	
	var lngData = new Object();
	$.each(downMenuList, function(index, value){
		if(data.menuName == value.menuName && data.menuNo == value.menuNo){
			lngData = value.menuLngList;
			return false;
		}
	});
	
	$("#lngGrid").clearGridData();
	
	$("#lngGrid").setGridParam({
 		data : lngData,
 		rowNum: lngData.length
	});
	
 	
	$("#lngGrid").trigger("reloadGrid");
	btnCtrl('U');
	
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
/*
 * 입력부 초기화 함수
 */
function inputInit(mode) {
	
	$("#lngGrid").clearGridData();
	$("#menuInfo input:text").val('');
	$("#menuInfo input:text").addClass('not-active'); 
	$("#menuInfo input:text").attr('disabled', true);
	$('#useYn').val('SEL');
	$('#useYn').selectmenu("refresh");
	$(".search").attr('disabled',true);
	$(".search").addClass('not-active');

	if(mode == 'U'){ //추가 / 수정모드
		$("#menuNo").removeAttr('disabled');
		$("#menuNo").removeClass('not-active'); 
		$("#menuName").removeAttr('disabled');
		$("#menuName").removeClass('not-active'); 
		$("#displayOrder").removeAttr('disabled');
		$("#displayOrder").removeClass('not-active'); 
		$("#description").removeAttr('disabled');
		$("#description").removeClass('not-active'); 
		//$("#viewPath").removeAttr('disabled');
		//$("#viewPath").removeClass('not-active'); 
		$("#useYn").selectmenu("enable");
		var activatedNode = $("#menuTree").dynatree("getActiveNode");
		if(activatedNode.data.stepNo =='3' || activatedNode.data.stepNo =='4'){
			$(".search").removeAttr('disabled');
			$(".search").removeClass('not-active');		
		}
		
	}
}

/*
 * 버튼 제어
 */
function btnCtrl(mode) {
	//공통 버튼 처리

	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled',true);
	//I초기화
	//N신규
	//U수정
	if (mode == 'N') {
		btnEnable('detailInitBtn');
		btnEnable('detailNewBtn');
	}else if(mode =='U'){
		btnEnable('detailInitBtn');
		btnEnable('detailUpdateBtn');
		btnEnable('detailDeleteBtn');
	}else if(mode =='I'){
		btnEnable('detailInitBtn');
		//btnEnable('detailNewBtn');
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

function insertMenu(){
	if(checkValidation() == false){
		return;
	}
	var menu=menu + '&upMenuNo=' + $('#upMenuNo').val();
	menu = menu + '&upMenuName=' +$('#upMenuName').val();
	menu = menu + '&menuNo=' + $('#menuNo').val();
	menu = menu + '&menuName=' + $('#menuName').val();
	menu = menu + '&displayOrder=' + $('#displayOrder').val();
	menu = menu + '&description=' +$('#description').val();
	menu = menu + '&viewPath=' + $('#viewPath').val();
	menu = menu + '&stepNo=' + $('#stepNo').val();
	menu = menu + '&useYn=' + $('#useYn').val();
	var lngList= $("#lngGrid").getRowData() ;
	var menuNm="";
	var lngNm=""
	var cntryCd=""
	var lngCd="";
	for(var i=0;i<lngList.length;i++){
		menuNm+=lngList[i].menu_name+",";
		lngNm+=lngList[i].language_name+",";
		cntryCd+=lngList[i].country_code+",";
		lngCd += lngList[i].language_code+",";
	}
	menuNm=menuNm.slice(0,-1);
	lngNm=lngNm.slice(0,-1);
	cntryNm=cntryCd.slice(0,-1);
	lngCd=lngCd.slice(0,-1);
	
	menu = menu +'&menuNm=' + menuNm;
	menu = menu +'&lngNm=' + lngNm; 
	menu = menu +'&cntryCd=' + cntryCd; 
	menu = menu +'&lngCd=' + lngCd; 
	
	$.ajax({
        url:'/system/menu/menuMng/menuMng/insertMenuAction.json',
        type:'POST',
        data : menu,
        dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	inputInit('I');
    		btnCtrl('N');
    		$("#downMenuGrid").setGridParam({
    			postData : {
    				condUpMenuNo : data.menuNo,
    				topMenu : data.topMenu
    			}
    		});
    		$("#downMenuGrid").trigger("reloadGrid");	
    		
    		$("#menuTree").dynatree("option", "initAjax", {
    		   	data : {
    			},
    			type: "POST",
    			url: "/system/menu/menuMng/menuMng/getMenuListAction.json",
    			complete : function(){
    				
    			 var match = undefined;
   				 var activatedNode =  $("#menuTree").dynatree("getActiveNode");
   				 $("#menuTree").dynatree("getRoot").visit(function (node) {
   				    
   				        if (node.data.key == data.upMenuNo) {
   				        	var nodeChildOrder = node.data.order;
   				        	var nodeParentOrder = node.getParent().data.order;
   				        	match = node;
   				        	return false;
   				        }
   				    });
   				    if(match != undefined){
   				    	$("#menuTree").dynatree("getTree").activateKey(match.data.key);
   				    }else{
   				    	$("#menuTree").dynatree("getTree").activateKey("ROOT");
   				    }
    					
    			}
    	     });

    		 $("#menuTree").dynatree("getTree").reload(); 
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
}


function updateMenu(){
	if(checkValidation() == false){
		return;
	}
	var menu='upMenuNo=' + $('#upMenuNo').val();
	menu = menu + '&upMenuName=' +$('#upMenuName').val();
	menu = menu + '&menuNo=' + $('#menuNo').val();
	menu = menu + '&menuName=' + $('#menuName').val();
	menu = menu + '&displayOrder=' + $('#displayOrder').val();
	menu = menu + '&description=' +$('#description').val();
	menu = menu + '&viewPath=' + $('#viewPath').val();
	
	menu = menu + '&useYn=' + $('#useYn').val();
	var lngList= $("#lngGrid").getRowData() ;
	var menuNm="";
	var lngNm=""
	var cntryCd=""
	var lngCd="";
	for(var i=0;i<lngList.length;i++){
		menuNm+=lngList[i].menu_name+",";
		lngNm+=lngList[i].language_name+",";
		cntryCd+=lngList[i].country_code+",";
		lngCd += lngList[i].language_code+",";
	}
	menuNm=menuNm.slice(0,-1);
	lngNm=lngNm.slice(0,-1);
	cntryNm=cntryCd.slice(0,-1);
	lngCd=lngCd.slice(0,-1);
	
	menu = menu +'&menuNm=' + menuNm;
	menu = menu +'&lngNm=' + lngNm; 
	menu = menu +'&cntryCd=' + cntryCd; 
	menu = menu +'&lngCd=' + lngCd; 
	
	$.ajax({
        url:'/system/menu/menuMng/menuMng/updateMenuAction.json',
        type:'POST',
        data : menu,
        dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	inputInit('I');
    		btnCtrl('N');
    		$("#downMenuGrid").setGridParam({
    			postData : {
    				condUpMenuNo : data.menuNo,
    				topMenu : data.topMenu
    			}
    		});
    		$("#downMenuGrid").trigger("reloadGrid");	
    		
    		$("#menuTree").dynatree("option", "initAjax", {
    		   	data : {
    			},
    			type: "POST",
    			url: "/system/menu/menuMng/menuMng/getMenuListAction.json",
    			complete : function(){
    				
    			 var match = undefined;
   				 var activatedNode =  $("#menuTree").dynatree("getActiveNode");
   				 $("#menuTree").dynatree("getRoot").visit(function (node) {
   				    
   				        if (node.data.key == data.upMenuNo) {
   				        	var nodeChildOrder = node.data.order;
   				        	var nodeParentOrder = node.getParent().data.order;
   				        	match = node;
   				        	return false;
   				        }
   				    });
   				    if(match != undefined){
   				    	$("#menuTree").dynatree("getTree").activateKey(match.data.key);
   				    }else{
   				    	$("#menuTree").dynatree("getTree").activateKey("ROOT");
   				    }
    					
    			}
    	     });

    		 $("#menuTree").dynatree("getTree").reload(); 
        
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
   			alert('<spring:message code="MSG.M10.MSG00005"/>');	
     	}
    });
}

/*
 * 초기화 버튼 이벤트
 */
function initBtn(){
	var activatedNode = $("#menuTree").dynatree("getActiveNode");
	inputInit('U');
	btnCtrl('N');
	$("#menuNo").removeAttr('disabled');
	$("#menuNo").removeClass('not-active');
	if(activatedNode.data.stepNo=='4'){
		$("#upMenuNo").val(activatedNode.data.upMenuNo);
		$("#upMenuName").val(activatedNode.data.upMenuName);
	}else if(activatedNode.data.stepNo=='0'){
		$("#upMenuNo").val('0');
		$("#upMenuName").val('/');
	}else{
		$("#upMenuNo").val(activatedNode.data.key);
		$("#upMenuName").val(activatedNode.data.title);
	}
	
	$("#menuNo").focus();
	var url = "/system/menu/menuMng/menuMng/getLngListAction.json"
 	$.ajax({
        url:url,
        type:'POST',
        dataType: 'json',
        success: function(data){
        	$("#lngGrid").clearGridData();
         	$("#lngGrid").setGridParam({
         		data : data.lngList,
         		rowNum: data.lngList.length
        	});
        	$("#lngGrid").trigger("reloadGrid");
        	
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     	}
    }); 
	
}

function deleteMenu(){
		
	var menu='menuNo=' + $('#menuNo').val();
	menu = menu + '&stepNo=' +$('#stepNo').val();
	menu = menu + '&upMenuNo=' +$('#upMenuNo').val();
	
	$.ajax({
        url:'/system/menu/menuMng/menuMng/deleteAction.json',
        type:'POST',
        data : menu,
        dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	inputInit('I');
    		btnCtrl('N');
    		$("#downMenuGrid").setGridParam({
    			postData : {
    				condUpMenuNo : data.menuNo,
    				topMenu : data.topMenu
    			}
    		});
    		$("#downMenuGrid").trigger("reloadGrid");	
    		
    		$("#menuTree").dynatree("option", "initAjax", {
    		   	data : {
    			},
    			type: "POST",
    			url: "/system/menu/menuMng/menuMng/getMenuListAction.json",
    			complete : function(){
    				
    			 var match = undefined;
   				 var activatedNode =  $("#menuTree").dynatree("getActiveNode");
   				 $("#menuTree").dynatree("getRoot").visit(function (node) {
   				    
   				        if (node.data.key == data.upMenuNo) {
   				        	var nodeChildOrder = node.data.order;
   				        	var nodeParentOrder = node.getParent().data.order;
   				        	match = node;
   				        	return false;
   				        }
   				    });
   				    if(match != undefined){
   				    	$("#menuTree").dynatree("getTree").activateKey(match.data.key);
   				    }else{
   				    	$("#menuTree").dynatree("getTree").activateKey("ROOT");
   				    }
    					
    			}
    	     });

    		 $("#menuTree").dynatree("getTree").reload(); 
    		
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
   			alert('<spring:message code="MSG.M10.MSG00005"/>');	
     	}
    });
}
/*
 * 저장 체크
 */
function checkValidation(){
	//코드 필수 체크
	var menuNo = $("#menuNo").val();
	if(menuNo == null || menuNo.length == 0){
		$("#menuNo").focus();
		var item = '<spring:message code="LAB.M05.LAB00027" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//코드명 필수 체크
	var menuName = $("#menuName").val();
	if(menuName == null || menuName.length == 0){
		$("#menuName").focus();
		var item = '<spring:message code="LAB.M05.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//순서 필수 체크
	var displayOrder = $("#displayOrder").val();
	if(displayOrder == null || displayOrder.length == 0){
		$("#displayOrder").focus();
		var item = '<spring:message code="LAB.M10.LAB00080" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//기본여부 필수체크
	if($('#useYn').val()== 'SEL'){
		$('#useYn-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	} 
	
	//View Path 필수체크
	var viewPath = $("#viewPath").val();
	
	if(!$("#viewPath_popup").hasClass('not-active')){
		if(viewPath == null || viewPath.length == 0){
			$('#viewPath').focus();
			var item = '<spring:message code="LAB.M15.LAB00088" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
	}
	//언어 필수 체크
	var lngList= $("#lngGrid").getRowData() ;
	var checkGrid = true;
	
	for(var i=0;i<lngList.length;i++){
		$("#lngGrid").editCell(i,1,false);
		
		if(lngList[i].menu_name==''){
			checkGrid=false;
			alert('<spring:message code="MSG.M03.MSG00001"/>');
			return false;
		}
	}
	
	if(checkGrid == false){
		return false;
	}
}
</script>
<input type="hidden" id="stepNo" name="stepNo"/>
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
				<h4 class="sub_title"><spring:message code="LAB.M05.LAB00025"/></h4>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 30%;" />
				<col style="width: 70%;" />
			</colgroup>
			<thead>
				<tr>
					<th><spring:message code="LAB.M05.LAB00024"/></th>
					<td>
						<input id="condName" type="text" class="w100p" />
					</td>
				</tr>
			</thead>
		</table>
		<div id="menuTree" class="line mt10 w100p">
		</div>
		<div class="fr  mt10">
		</div>
	</div>
	<div class="gr_right">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M14.LAB00001"/></h4>
			</div>
		</div>
		<div id='gridDiv'>
			<table id="downMenuGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M05.LAB00029"/></h4>
			</div>
		</div>
		<div id="menuInfo">
		<table class="writeview">
			<colgroup>
				<col style="width:15%;">
                <col style="width:35%;">
                <col style="width:15%;">
                <col style="width:35%;">
			</colgroup>
			<thead>
				<tr>
					<th><spring:message code="LAB.M07.LAB00105"/></th>
					<td><input type="text" id="upMenuNo" name="upMenuNo" class="w100p not-active" disabled="disabled"></td>
					<th><spring:message code="LAB.M07.LAB00104"/></th>
					<td><input type="text" id="upMenuName" name="upMenuName" class="w100p not-active" disabled="disabled"></td>
				</tr>
				<tr>
					<th><spring:message code="LAB.M05.LAB00027"/><span class="dot">*</span></th>
					<td><input type="text" id="menuNo" name="menuNo" class="w100p not-active" disabled="disabled"></td>
					<th><spring:message code="LAB.M05.LAB00026"/><span class="dot">*</span></th>
					<td><input type="text" id="menuName" name="menuName" class="w100p not-active" disabled="disabled"></td>
				</tr>
				<tr>
					<th><spring:message code="LAB.M10.LAB00080"/><span class="dot">*</span></th>
					<td><input type="text" id="displayOrder" name="displayOrder" class="w100p not-active" disabled="disabled"></td>
					<th><spring:message code="LAB.M07.LAB00026"/><span class="dot">*</span></th>
					<td>
						<select id="useYn" class="w100p">
							<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
							<option value="Y"><spring:message code="LAB.M15.LAB00103"/></option>
							<option value="N"><spring:message code="LAB.M15.LAB00063"/></option>
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="LAB.M07.LAB00200"/></th>
					<td colspan='3'><input type="text" id="description" name="description" class="w100p not-active" disabled="disabled"></td>
				</tr>
				<tr>
					<th><spring:message code="LAB.M15.LAB00088"/><span class="dot">*</span></th>
					<td colspan='3'>
						<div class="date_box">
							<div class="inp_date w100p">
							<input type="text" id="viewPath" name="viewPath" class="w100p not-active" disabled="disabled">
							<a href="#" id="viewPath_popup"class="search"><spring:message code="LAB.M09.LAB00158"/></a>
							</div>
						</div>
					</td>
				</tr>
			</thead>
		</table>
		</div>
		<div id='gridLngDiv' class='mt10'>
			<table id="lngGrid" class="w100p"></table>
		</div>
	</div>
</div>
<div class="fr">
		<span id="commonBtn">
		<ntels:auth auth="${menuAuthC}">
		<a id="detailInitBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
		<a id="detailNewBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>
		<ntels:auth auth="${menuAuthU}">
		<a id="detailUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		<ntels:auth auth="${menuAuthD}">
		<a id="detailDeleteBtn"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
		</span>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;" >
</div>
	
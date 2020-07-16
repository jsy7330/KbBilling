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

$(document).ready(function(){
	btnGrpCtrl('');
	btnCtrl('');
	inputInit('');
	
	var tree = $("#tree");
	
	var commonCodeList = new Object();
	
	//트리 초기화
	$(tree).dynatree({
 		initAjax: {
 			type: "POST",
 			url: "/system/configuration/codeMng/commonCodeMng/getCommonCodeGrpListAction.json",
			complete : function(){
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
	
	
	
	//코드상세 그리드
	$("#commonCodeGrid").jqGrid({
		url: "/system/configuration/codeMng/commonCodeMng/getCommonCodeListAction.json",
		datatype : 'json',
		mtype: 'POST',
		postData : {
			condCodeGrpId : ""
		},
		colModel: [ 
					{ label: 'defaultYn' , name: 'defaultYn', hidden:true,width : 0},
					{ label: 'commonGrp' , name: 'commonGrp', hidden:true,width : 0},
					{ label: 'commonGrpNm' , name: 'commonGrpNm', hidden:true,width : 0},
					{ label: 'orgCommonCdNm' , name: 'orgCommonCdNm', hidden:true,width : 0},
					{ label: 'refCode' , name: 'refCode', hidden:true,width : 0},
					{ label: 'refCode2' , name: 'refCode2', hidden:true,width : 0},
					{ label: 'refCode3' , name: 'refCode3', hidden:true,width : 0},
					{ label: 'refCode4' , name: 'refCode4', hidden:true,width : 0},
					{ label: 'rmrk' , name: 'rmrk', hidden:true,width : 0},
			     	{ label: '<spring:message code="LAB.M11.LAB00006"/>', name: 'commonCd', width : 100, align:"left"},
				    { label: '<spring:message code="LAB.M11.LAB00010"/>', name: 'commonCdNm', width : 200, align:"left"},
				    { label: '<spring:message code="LAB.M07.LAB00258"/>', name: 'sortNo', width : 70, align:"right"},
				    { label: '<spring:message code="LAB.M07.LAB00026"/>', name: 'useYn', width : 70, align:"center"},
				    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrNm', width : 150, align:"left",sortable:false},        
				    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center",sortable:false},    
				    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrNm', width : 150, align:"left",sortable:false},
				    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center",sortable:false},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 210,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "codeList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowNum: -1,  
        onCellSelect : function(rowid, index, contents, event){
        	setCodeDetailInfo(rowid, commonCodeList);
        },
       	loadComplete : function (data) {
       		$("#commonCodeGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       		commonCodeList = data.codeList;
       	},
    	sortable: { update: function(permutation) {
    		$("#commonCodeGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	//언어정보 그리드
	$("#lngGrid").jqGrid({
		datatype : 'local',
		colModel: [
				{ label: 'language_code' , name: 'language_code', hidden:true,width : 0},
				{ label: '<spring:message code="LAB.M08.LAB00004"/>', name: 'language_name', width : 100, align:"left",sortable:false},
				{ label: '<spring:message code="LAB.M03.LAB00001"/>', name: 'code_nm', width : 200, align:"left",sortable:false, editable:true}
				
		],
		data : '',
		viewrecords: true,
		shrinkToFit:false,
		height: 44,
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

	$("#commonCodeGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#commonCodeGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#lngGrid").setGridWidth($('#gridLngDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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
	$('#btn_srch').on('click',function (e) {
		if($("#btn_srch").hasClass('not-active')){
			return;
		}
		var result = seachFolderNodeWithName($("#condName").val(), tree);
			if(!result){
				tree.dynatree("getTree").activateKey('ROOT'); //ROOT KEY 지정
				
			    tree.dynatree("getRoot").visit(function (node) {
			    	node.expand(false);
			    });
			}
			
			$('#condName').focus();
	});
  	//초기화 버튼 이벤트
   	$('#detailInitBtn').on('click',function (e) {
	   		if($("#detailInitBtn").hasClass('not-active')){
				return;
			}
    		initBtn();
		}
    );
  	
  	//코드영문 숫자만 입력
    $("#inputCode").keyup(function(event){ 
	 	   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		 	    var inputVal = $(this).val();
		 	    $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
		 	    
		  	    var str = getMaxStr($('#inputCode').val(), 30);
		  	    if(str != $('#inputCode').val()){
		  			$('#inputCode').val(str);
		  	    }
	 	   }
	});
  	
  	//순서 숫자만 입력
    $("#inputSortNo").keyup(function(event){ 
	 	   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		 	    var inputVal = $(this).val();
		 	    $(this).val(inputVal.replace(/[^0-9]/gi,''));
		 	    
		  	    var str = getMaxStr($('#inputSortNo').val(), 5);
		  	    if(str != $('#inputSortNo').val()){
		  			$('#inputSortNo').val(str);
		  	    }
	 	   }
	});
  	
  	//코드 상세 신규등록 버튼 이벤트
    $('#detailNewBtn').on('click',function (e) {
    	if($("#detailNewBtn").hasClass('not-active')){
			return;
		}
    		insertNewCodeDetail();
		}
    );
 	 //코드 상세 수정 버튼 이벤트
    $('#detailUpdateBtn').on('click',function (e) {
    	if($("#detailUpdateBtn").hasClass('not-active')){
			return;
		}
    		updateCodeDetail();
		}
    );
  	
  	//코드 상세 삭제 버튼 이벤트
    $('#detailDeleteBtn').on('click',function (e) {
    	if($("#detailDeleteBtn").hasClass('not-active')){
			return;
		}
    		deleteCodeDetail();
		}
    );
  	
  	//코드 그룹 삭제 버튼 이벤트
    $('#mastDeleteBtn').on('click',function (e) {
    	if($("#mastDeleteBtn").hasClass('not-active')){
			return;
		}
    		deleteCodeGrp();
		}
    );
  	
  	//코드명 keyup 이벤트
  	$('#inputCodeNm').keyup(function(){
	  		var str = getMaxStr($('#inputCodeNm').val(), 100);
	  		if(str != $('#inputCodeNm').val()){
	  			$('#inputCodeNm').val(str);
	  		}
  		}
	);
  	
  	//Ref1 keyup 이벤트
  	$('#inputRef1').keyup(function(){
	  		var str = getMaxStr($('#inputRef1').val(), 400);
	  		if(str != $('#inputRef1').val()){
	  			$('#inputRef1').val(str);
	  		}
  		}
	);
  	
  	//Ref2 keyup 이벤트
  	$('#inputRef2').keyup(function(){
	  		var str = getMaxStr($('#inputRef2').val(), 400);
	  		if(str != $('#inputRef2').val()){
	  			$('#inputRef2').val(str);
	  		}
  		}
	);
  	
  	//Ref3 keyup 이벤트
  	$('#inputRef3').keyup(function(){
	  		var str = getMaxStr($('#inputRef3').val(), 400);
	  		if(str != $('#inputRef3').val()){
	  			$('#inputRef3').val(str);
	  		}
  		}
	);
  	
  	//Ref4 keyup 이벤트
  	$('#inputRef4').keyup(function(){
	  		var str = getMaxStr($('#inputRef4').val(), 400);
	  		if(str != $('#inputRef4').val()){
	  			$('#inputRef4').val(str);
	  		}
  		}
	);
  	
  	//inputRmrk 이벤트
  	$('#inputRmrk').keyup(function(){
	  		var str = getMaxStr($('#inputRmrk').val(), 2000);
	  		if(str != $('#inputRmrk').val()){
	  			$('#inputRmrk').val(str);
	  		}
  		}
	);
  	
  	
  	//그룹추가
    $('#mastNewBtn').on('click',function (e) {
    	if($("#mastNewBtn").hasClass('not-active')){
			return;
		}
    	//현재 선택된 노드 세팅
    	var activatedNode = tree.dynatree("getActiveNode");
    	
    	var parameter = new Object();
    	parameter.mode = 'I';
    	parameter.systemId = activatedNode.data.systemId;
    	parameter.systemNm = activatedNode.data.systemNm;
    	parameter.codeGrp = '';
    	parameter.codeGrpNm = '';
    	
		$.ajax({
			type : "post",
			url : '/system/configuration/codeMng/commonCodeMng/openCommonCodeGrpPopup.ajax',
			data : parameter,
			async: true,
			success : function(data) {
				var html = data;
				$("#popup_dialog").html(html);
				
			},		
			complete : function(){
				wrapWindowByMask(); // 팝업 오픈
			}
		}); 
		}
    );
  	
  	//그룹수정
    $('#mastUpdateBtn').on('click',function (e) {
    	if($("#mastUpdateBtn").hasClass('not-active')){
			return;
		}
    	//현재 선택된 노드 세팅
    	var activatedNode = tree.dynatree("getActiveNode");
    	
    	var parameter = new Object();
    	parameter.mode = 'U';
    	parameter.systemId = activatedNode.data.systemId;
    	parameter.systemNm = activatedNode.data.systemNm;
    	parameter.codeGrp = activatedNode.data.key;
    	parameter.codeGrpNm = activatedNode.data.name;
    	
		$.ajax({
			type : "post",
			url : '/system/configuration/codeMng/commonCodeMng/openCommonCodeGrpPopup.ajax',
			data : parameter,
			async: true,
			success : function(data) {
				var html = data;
				$("#popup_dialog").html(html);
				
			},		
			complete : function(){
				wrapWindowByMask(); // 팝업 오픈
			}
		}); 
		}
    );
  	
	
});

/*
 * 노드 검색 함수
 */
function seachFolderNodeWithName(name, tree) {
    if (name == null) {
        return undefined;
    }
	var activatedNode = tree.dynatree("getActiveNode");
	var preActivatedChildOrder = '';
	var preActivatedParentOrder = '';
	
    if (activatedNode != null) {
    	if(activatedNode.getLevel() == 3){
    		preActivatedChildOrder = activatedNode.data.order;
    		preActivatedParentOrder = activatedNode.getParent().data.order;
    	}
    }
    
    var match = undefined;
    tree.dynatree("getRoot").visit(function (node) {
        if (node.getLevel()==3 && node.data.title.indexOf(name) != -1) {
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
 * 선택 노드 조회
 */
function getCodeList(node){
	$("#commonCodeGrid").clearGridData();
	inputInit('');
	
	if(node.getLevel() == 1){
		btnGrpCtrl('');
	}else if(node.getLevel() == 2){
		btnGrpCtrl('A');	
	}else if(node.getLevel() == 3){
		btnGrpCtrl('B');	
	}
	
	if(node.getLevel() == 3){
		btnCtrl('N');
		$("#commonCodeGrid").setGridParam({
			postData : {
				condGroupId : node.data.key
			}
		});
		$("#commonCodeGrid").trigger("reloadGrid");	
	}else{
		btnCtrl('');
	}
	
}

/*
 * 데이터 세팅
 */
function setCodeDetailInfo(rowid, commonCodeList){
	inputInit('U');
	btnCtrl('U');
	var data = $("#commonCodeGrid").getRowData(rowid);
	//데이터 세팅
	$("#inputCodeGrp").val(data.commonGrp);
	$("#inputCodeGrpNm").val(data.commonGrpNm);
	$("#inputCode").val(data.commonCd);
	$("#inputCodeNm").val(data.orgCommonCdNm);
	$("#inputSortNo").val(data.sortNo);
	$("#inputRef1").val(data.refCode);
	$("#inputRef2").val(data.refCode2);
	$("#inputRef3").val(data.refCode3);
	$("#inputRef4").val(data.refCode4);
	$("#inputRmrk").val(data.rmrk);
	$("#selectDefaultYn").val(data.defaultYn);
	$('#selectDefaultYn').selectmenu("refresh");
	$("#selectUseYn").val(data.useYn);
	$('#selectUseYn').selectmenu("refresh");
	
	var lngData = new Object();
	$.each(commonCodeList, function(index, value){
		if(data.commonGrp == value.commonGrp && data.commonCd == value.commonCd){
			lngData = value.codeLngList;
			return false;
		}
	});
	$("#lngGrid").clearGridData();
 	$("#lngGrid").setGridParam({
 		data : lngData,
 		rowNum: lngData.length
	});
	$("#lngGrid").trigger("reloadGrid");
}

/*
 * 초기화 버튼 이벤트
 */
function initBtn(){
	var activatedNode = $("#tree").dynatree("getActiveNode");
	inputInit('I');
	btnCtrl('I');
	$("#inputCodeGrp").val(activatedNode.data.key);
	$("#inputCodeGrpNm").val(activatedNode.data.name);
	$("#inputCode").focus();
	var url = "/system/configuration/codeMng/commonCodeMng/getLngListAction.json"
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
/*
 * 입력부 초기화 함수
 */
function inputInit(mode) {
	
	$("#lngGrid").clearGridData();
	$("#inputCodeDetailDiv input:text").val('');
	$("#inputCodeDetailDiv input:text").addClass('not-active'); 
	$("#inputCodeDetailDiv input:text").attr('disabled', true);
	$('#selectDefaultYn').val('SEL');
	$('#selectDefaultYn').selectmenu("refresh");
	$('#selectUseYn').val('SEL');
	$('#selectUseYn').selectmenu("refresh");
	$("#inputCodeDetailDiv select").selectmenu("disable");
	
	if(mode == 'U'){ //수정모드
		$("#inputCodeNm").removeClass('not-active'); 
		$("#inputCodeNm").removeAttr('disabled');
		$("#inputSortNo").removeClass('not-active'); 
		$("#inputSortNo").removeAttr('disabled');
		$("#inputRef1").removeClass('not-active'); 
		$("#inputRef1").removeAttr('disabled');
		$("#inputRef2").removeClass('not-active'); 
		$("#inputRef2").removeAttr('disabled');
		$("#inputRef3").removeClass('not-active'); 
		$("#inputRef3").removeAttr('disabled');
		$("#inputRef4").removeClass('not-active'); 
		$("#inputRef4").removeAttr('disabled');
		$("#inputRmrk").removeClass('not-active'); 
		$("#inputRmrk").removeAttr('disabled');
		$("#selectDefaultYn").selectmenu("enable");
		$("#selectUseYn").selectmenu("enable");
	}else if(mode == 'I'){
		$("#inputCode").removeClass('not-active'); 
		$("#inputCode").removeAttr('disabled');
		$("#inputCodeNm").removeClass('not-active'); 
		$("#inputCodeNm").removeAttr('disabled');
		$("#inputSortNo").removeClass('not-active'); 
		$("#inputSortNo").removeAttr('disabled');
		$("#inputRef1").removeClass('not-active'); 
		$("#inputRef1").removeAttr('disabled');
		$("#inputRef2").removeClass('not-active'); 
		$("#inputRef2").removeAttr('disabled');
		$("#inputRef3").removeClass('not-active'); 
		$("#inputRef3").removeAttr('disabled');
		$("#inputRef4").removeClass('not-active'); 
		$("#inputRef4").removeAttr('disabled');
		$("#inputRmrk").removeClass('not-active'); 
		$("#inputRmrk").removeAttr('disabled');
		$("#selectDefaultYn").selectmenu("enable");
		$("#selectUseYn").selectmenu("enable");
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
	}else if(mode =='U'){
		btnEnable('detailInitBtn');
		btnEnable('detailUpdateBtn');
		btnEnable('detailDeleteBtn');
	}else if(mode =='I'){
		btnEnable('detailInitBtn');
		btnEnable('detailNewBtn');
	}
}

function btnGrpCtrl(mode) {
	$("#commonGrpBtn a").addClass('white-btn');
	$("#commonGrpBtn a").removeClass('grey-btn');
	$("#commonGrpBtn a").addClass('not-active');
	$("#commonGrpBtn a").attr('disabled',true);
	
	
	if (mode == 'A') {
		$("#mastNewBtn").addClass('grey-btn');
		$("#mastNewBtn").removeClass('white-btn');
		$("#mastNewBtn").removeClass('not-active');
		$("#mastNewBtn").removeAttr('disabled');
	}else if(mode =='B'){
		$("#commonGrpBtn a").addClass('grey-btn');
		$("#commonGrpBtn a").removeClass('white-btn');
		$("#commonGrpBtn a").removeClass('not-active');
		$("#commonGrpBtn a").removeAttr('disabled');
	}
}



/**
 * 코드 상세 등록
 */
function insertNewCodeDetail(){
	if(checkValidation() == false){
		return;
	}

	var url = "/system/configuration/codeMng/commonCodeMng/insertCodeDetailAction.json"
	
	var commonCode = new Object();
	commonCode.commonGrp = $('#inputCodeGrp').val();
	commonCode.commonGrpNm = $('#inputCodeGrpNm').val();
	commonCode.commonCd = $('#inputCode').val();
	commonCode.commonCdNm = $('#inputCodeNm').val();
	commonCode.sortNo = $('#inputSortNo').val();
	commonCode.defaultYn = $('#selectDefaultYn').val();
	commonCode.useYn = $('#selectUseYn').val();
	commonCode.refCode = $('#inputRef1').val();
	commonCode.refCode2 = $('#inputRef2').val();
	commonCode.refCode3 = $('#inputRef3').val();
	commonCode.refCode4 = $('#inputRef4').val();
	commonCode.rmrk = $('#inputRmrk').val();
	commonCode.codeLngList = $("#lngGrid").getRowData();
	
	$.ajax({
        url:url,
        type:'POST',
        data : JSON.stringify(commonCode),
        dataType: 'json',
        contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	inputInit('');
    		btnCtrl('N');
    		$("#commonCodeGrid").setGridParam({
    			postData : {
    				condGroupId : data.codeGrpId
    			}
    		});
    		$("#commonCodeGrid").trigger("reloadGrid");	
    		
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



/**
 * 코드 상세 수정
 */
function updateCodeDetail(){
	if(checkValidation() == false){
		return;
	}

	var url = "/system/configuration/codeMng/commonCodeMng/updateCodeDetailAction.json"
	
	var commonCode = new Object();
	commonCode.commonGrp = $('#inputCodeGrp').val();
	commonCode.commonGrpNm = $('#inputCodeGrpNm').val();
	commonCode.commonCd = $('#inputCode').val();
	commonCode.commonCdNm = $('#inputCodeNm').val();
	commonCode.sortNo = $('#inputSortNo').val();
	commonCode.defaultYn = $('#selectDefaultYn').val();
	commonCode.useYn = $('#selectUseYn').val();
	commonCode.refCode = $('#inputRef1').val();
	commonCode.refCode2 = $('#inputRef2').val();
	commonCode.refCode3 = $('#inputRef3').val();
	commonCode.refCode4 = $('#inputRef4').val();
	commonCode.rmrk = $('#inputRmrk').val();
	commonCode.codeLngList = $("#lngGrid").getRowData();
	
	$.ajax({
        url:url,
        type:'POST',
        data : JSON.stringify(commonCode),
        dataType: 'json',
        contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	inputInit('I');
    		btnCtrl('N');
    		$("#commonCodeGrid").setGridParam({
    			postData : {
    				condGroupId : data.codeGrpId
    			}
    		});
    		$("#commonCodeGrid").trigger("reloadGrid");	
    		
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

/**
 * 코드 상세 그룹 삭제
 */
function deleteCodeGrp(){
	
	var check = confirm('<spring:message code="MSG.M11.MSG00004" />');
	if(!check){
		return;
	}
	
	
	var url = "/system/configuration/codeMng/commonCodeMng/deleteCodeGrpAction.json"
	
	//현재 선택된 노드 세팅
   	var activatedNode = $("#tree").dynatree("getActiveNode");
   	
	
	var targetCode = "codeGrp=" + activatedNode.data.key; 
	
	$.ajax({
        url:url,
        type:'POST',
        data : targetCode,
        dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	$("#commonCodeGrid").clearGridData();
        	inputInit('I');
    		btnCtrl('N');
    		reloadCodeGrp('');
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



/**
 * 코드 그룹 삭제
 */
function deleteCodeDetail(){
	
	var check = confirm('<spring:message code="MSG.M11.MSG00002" />');
	if(!check){
		return;
	}
	
	
	var url = "/system/configuration/codeMng/commonCodeMng/deleteCodeDetailAction.json"
	
	var targetCode = "codeGrp=" + $('#inputCodeGrp').val(); 
	targetCode = targetCode + "&code=" + $('#inputCode').val(); 
		
	
	$.ajax({
        url:url,
        type:'POST',
        data : targetCode,
        dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	inputInit('I');
    		btnCtrl('N');
    		$("#commonCodeGrid").setGridParam({
    			postData : {
    				condGroupId : data.codeGrpId
    			}
    		});
    		$("#commonCodeGrid").trigger("reloadGrid");
    		
    		
    		
    		
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





/*
 * 저장 체크
 */
function checkValidation(){
	//코드 필수 체크
	var code = $("#inputCode").val();
	if(code == null || code.length == 0){
		$("#inputCode").focus();
		var item = '<spring:message code="LAB.M11.LAB00006" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//코드명 필수 체크
	var codeNm = $("#inputCodeNm").val();
	if(codeNm == null || codeNm.length == 0){
		$("#inputCodeNm").focus();
		var item = '<spring:message code="LAB.M11.LAB00010" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//순서 필수 체크
	var order = $("#inputSortNo").val();
	if(order == null || order.length == 0){
		$("#inputSortNo").focus();
		var item = '<spring:message code="LAB.M07.LAB00258" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//기본여부 필수체크
	if($('#selectDefaultYn').val()== 'SEL'){
		$('#selectDefaultYn-button').focus();
		var item = '<spring:message code="LAB.M01.LAB00200" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//사용유무 필수체크
	if($('#selectUseYn').val()== 'SEL'){
		$('#selectUseYn-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//언어 필수 체크
	var data = $("#lngGrid").getRowData();
	var checkGrid = true;
	$.each(data, function(index, value){
		$("#lngGrid").editCell(index,1,false);
		var row = $("#lngGrid").jqGrid('getRowData', index);
		if(row.code_nm == ''){
			var item = '<spring:message code="LAB.M03.LAB00001" />' + ':' + row.language_name;
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			checkGrid = false;
			return false;
		}
	});
	
	if(checkGrid == false){
		return false;
	}
}



/*
 * 공통코드 그룹 재조회
 */
function reloadCodeGrp(codeGrp){
    $("#tree").dynatree("option", "initAjax", {
		type: "POST",
		url: "/system/configuration/codeMng/commonCodeMng/getCommonCodeGrpListAction.json",
		complete : function(){
			
			if(codeGrp != null && codeGrp != ''){
				var result = seachFolderNodeWithName(codeGrp, $("#tree"));
	   			if(!result){
	   				$("#tree").dynatree("getTree").activateKey('ROOT'); //ROOT KEY 지정
	   				
	   				$("#tree").dynatree("getRoot").visit(function (node) {
	   			    	node.expand(false);
	   			    });
	   			}
			}else if (codeGrp == ''){
   				$("#tree").dynatree("getTree").activateKey('ROOT'); //ROOT KEY 지정
   				
   				$("#tree").dynatree("getRoot").visit(function (node) {
   			    	node.expand(false);
   			    });
			}
				
		}
     });

    $("#tree").dynatree("getTree").reload();
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

<div class="group_box mt10">
	<div class="gr_left">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M11.LAB00009"/></h4>
			</div>
			<div class="fr mt10">
				<ntels:auth auth="${menuAuthR}">
				<a href="#" id="btn_srch" class="grey-btn" title="<spring:message code="LAB.M09.LAB00158"/>">
				<span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
				</ntels:auth>
			</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 30%;" />
				<col style="width: 70%;" />
			</colgroup>
			<thead>
				<tr>
					<th><spring:message code="LAB.M11.LAB00016"/></th>
					<td>
						<input id="condName" type="text" class="w100p" />
					</td>
				</tr>
			</thead>
		</table>
		<div id="tree" class="line mt10 w100p">
		</div>
		<div class="fr  mt10">
			<span id="commonGrpBtn">
			<ntels:auth auth="${menuAuthC}">
			<a id="mastNewBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthU}">
			<a id="mastUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthD}">
			<a id="mastDeleteBtn"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</ntels:auth>
			</span>
		</div>
	</div>

	<div class="gr_right">
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M11.LAB00009"/></h4>
			</div>
		</div>
		<div id='gridDiv'>
			<table id="commonCodeGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M11.LAB00012"/></h4>
			</div>
		</div>
		<div id="inputCodeDetailDiv">
			<table class="writeview">
				<colgroup>
					<col style="width: 16%;">
					<col style="width: 17%;">
					<col style="width: 16%;">
					<col style="width: 17%;">
					<col style="width: 16%;">
					<col style="width: 18%;">
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="LAB.M11.LAB00007"/></th>
						<td><input type="text" id="inputCodeGrp" name="commonGrp" class="w100p not-active" disabled="disabled"></td>
						<th><spring:message code="LAB.M11.LAB00008"/></th>
						<td colspan='3'><input type="text" id="inputCodeGrpNm" name="commonGrpNm" class="w100p not-active" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M11.LAB00006"/><span class="dot">*</span></th>
						<td><input type="text" id="inputCode" name="commonCd" class="w100p not-active" disabled="disabled"></td>
						<th><spring:message code="LAB.M11.LAB00010"/><span class="dot">*</span></th>
						<td colspan='3'><input type="text" id="inputCodeNm" name="commonCdNm" class="w100p not-active" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00258"/><span class="dot">*</span></th>
						<td><input type="text" id="inputSortNo" name="sortNo" class="w100p not-active" disabled="disabled"></td>
						<th><spring:message code="LAB.M01.LAB00200"/><span class="dot">*</span></th>
						<td>
							<select id="selectDefaultYn" class="w100p">
								<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
								<option value="Y"><spring:message code="LAB.M15.LAB00103"/></option>
								<option value="N"><spring:message code="LAB.M15.LAB00063"/></option>
							</select>
						</td>
						<th><spring:message code="LAB.M07.LAB00026"/><span class="dot">*</span></th>
						<td>
							<select id="selectUseYn" class="w100p">
								<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
								<option value="Y"><spring:message code="LAB.M15.LAB00103"/></option>
								<option value="N"><spring:message code="LAB.M15.LAB00063"/></option>
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M10.LAB00003"/></th>
						<td colspan='5'><input type="text" id="inputRef1" name="refCode" class="w100p not-active" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M10.LAB00004"/></th>
						<td colspan='5'><input type="text" id="inputRef2" name="refCode2" class="w100p not-active" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M10.LAB00005"/></th>
						<td colspan='5'><input type="text" id="inputRef3" name="refCode3" class="w100p not-active" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M10.LAB00006"/></th>
						<td colspan='5'><input type="text" id="inputRef4" name="refCode4" class="w100p not-active" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00200"/></th>
						<td colspan='5'><input type="text" id="inputRmrk" name="rmrk" class="w100p not-active" disabled="disabled"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id='gridLngDiv' class='mt10'>
			<table id="lngGrid" class="w100p"></table>
		</div>
		<div class="fr mt10">
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
	</div>
</div>
<!-- 팝업 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

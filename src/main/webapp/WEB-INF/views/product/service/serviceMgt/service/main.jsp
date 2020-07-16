<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
/*   ul.dynatree-container {
  overflow: scroll;
  position: relative;
  width: 192px;
  height: 563px;
  }; */
ul.dynatree-container {
	border: none;
};

</style>

<script type="text/javascript">
 var publicGridId = "#serviceMgtComListGrid";
 var publicDivId = "#serviceMgtComListGridDiv";
 var publicTargetCd = "";
 var publicMstrFl = "";
 var publicChrgCtgry = "";
 var publicSoId = "";
 
 var userSoId = '${userSoId}';
 
$(function(){
	
	$(".ser_left").css("min-height","591px");
	$(".ser_left").css("height","591px");
	$(".ser_left").css("padding", "0px");
	$(".ser_left").css("width", "300px");

	$("ul.tabs li:eq(1)").hide();
   	$("ul.tabs li:eq(2)").hide();
	$("ul.tabs li:eq(3)").hide();
   	$("ul.tabs li:eq(4)").hide();
	
	var tree = $("#tree");
	initGrid();
	
	// Initialize the tree inside the <div>element.
	// The tree structure is read from the contained <ul> tag.
	$(tree).dynatree({
 		initAjax: {
 			url: "/product/service/serviceMgt/service/getTreeAction.json"
 		},

		clickFolderMode: 1,
		minExpandLevel: 2,
		
		onActivate: function(node) {
			selectTree(node.data.targetCd, node.data.mstrFl, node.data.isFolder, node.data.targetChrgCtgry, node.data.targetSoId, node.data.treeLevel);
			$("#targetUpperCd").val(node.data.targetCd);
			$("#targetMainSvcCd").val(node.data.targetMainSvcCd);
			$("#serviceMgtListTargetNm").val(node.data.title);
			$("#serviceMgtListSelectNodeParent").val(node.getParent().data.key); 
			$("#serviceMgtListSelectNode").val(node.data.key);
			$("#serviceMgtListSelectCurrentLvl").val(node.data.treeLevel);			
			
		}
		
	});
	
	$("ul.tabs li").click(function () {

		$("ul.tabs li").removeClass("active");
		$("ul.tabs li").css("color", "#b2b2b2");
		$(this).addClass("active");
		$(this).addClass("active").css("color", "#8ab700");
		$(".tab_content").hide();
	    var activeTab = $(this).attr("rel");
	    $("#" + activeTab).show();

		var index = $("ul.tabs li").index(this);
	    console.log("index: " + index);
		if (index == 0){
			excuteGridServiceMgtComList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicTargetCd, publicMstrFl, publicSoId);
		}
		
		if (index == 1){
			excuteGridServiceMgtSaleItmList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicTargetCd, publicSoId);
		}
		
		if (index == 2){
			excuteGridServiceMgtSvcRateItmTypList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicTargetCd, publicSoId);
		}		

		if (index == 3){
			excuteGridServiceMgtSvcRateItmTypAttrList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicTargetCd, publicChrgCtgry, publicSoId);
		}	
		
		if (index == 4){
			excuteGridServiceMgtSvcRateItmTypFctrList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicTargetCd);
		}			
		
	});
	
	$('#serviceMgtComListNewBtn').click(function() {
		var url="serviceMgtInsertPopUp.ajax";
		var param = new Object();
		param.targetUpperCd = $("#targetUpperCd").val();
		param.targetMainSvcCd = $("#targetMainSvcCd").val();
		param.soId = publicSoId;
		openModal(url, param); 		
	});

	$('#serviceMgtComListUpdateBtn').click(function() {
		var rowId = $("#serviceMgtComListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00101" />');
			return;
		}
		
		var gridActDt = $("#serviceMgtComListGrid").getRowData(rowId).actDt;
		var gridSvcCd = $("#serviceMgtComListGrid").getRowData(rowId).svcCd;
		var gridSoId = $("#serviceMgtComListGrid").getRowData(rowId).soId;
		
		var url="serviceMgtUpdatePopUp.ajax";
		var param = new Object();
		
		param.targetMainSvcCd = $("#targetMainSvcCd").val();
		param.actDt = dateFormatToStringYYYYMMDD(gridActDt);
		param.svcCd = gridSvcCd;
		param.soId = gridSoId;
		openModal(url, param); 
	});	
	
	$('#serviceMgtSaleItmListNewBtn').click(function() {
		var url="serviceMgtSaleItmListInsertPopUp.ajax";
		var param = new Object();
		param.svcCd = $("#targetUpperCd").val();
		param.soId = publicSoId;
		openModal(url, param); 		
	});	
	
	$('#serviceMgtSaleItmListUpdateBtn').click(function() {
		var rowId = $("#serviceMgtSaleItmListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00101" />');
			return;
		}

		var gridActDt = $("#serviceMgtSaleItmListGrid").getRowData(rowId).actDt;
		var gridSaleItmCd = $("#serviceMgtSaleItmListGrid").getRowData(rowId).saleItmCd;
		var gridSoId = $("#serviceMgtSaleItmListGrid").getRowData(rowId).soId;
		var gridSvcCd = $("#serviceMgtSaleItmListGrid").getRowData(rowId).svcCd;		
		
		var url="serviceMgtSaleItmListUpdatePopUp.ajax";
		var param = new Object();
		
		param.actDt = dateFormatToStringYYYYMMDD(gridActDt);
		param.saleItmCd = gridSaleItmCd;
		param.soId = gridSoId;
		param.svcCd = gridSvcCd;		
		openModal(url, param); 
	});		

	$('#serviceMgtSvcRateItmTypListNewBtn').click(function() {
		var url="serviceMgtSvcRateItmTypListInsertPopUp.ajax";
		var param = new Object();
		param.svcCd = $("#targetUpperCd").val();
		param.targetMainSvcCd = $("#targetMainSvcCd").val();
		param.soId = publicSoId;
		openModal2(url, param); 		
	});	
	
	$('#serviceMgtSvcRateItmTypListUpdateBtn').click(function() {
		var rowId = $("#serviceMgtSvcRateItmTypListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00101" />');
			return;
		}

		var gridSvcRateItmTypCd = $("#serviceMgtSvcRateItmTypListGrid").getRowData(rowId).svcRateItmTypCd;
		var gridSoId = $("#serviceMgtSvcRateItmTypListGrid").getRowData(rowId).soId;
		var gridActDt = $("#serviceMgtSvcRateItmTypListGrid").getRowData(rowId).actDt;
		var gridRateItmTypCd = $("#serviceMgtSvcRateItmTypListGrid").getRowData(rowId).rateItmTypCd;
		
		var url="serviceMgtSvcRateItmTypListUpdatePopUp.ajax";
		var param = new Object();
		
		param.svcCd = publicTargetCd;
		param.svcRateItmTypCd = gridSvcRateItmTypCd;
		param.rateItmTypCd = gridRateItmTypCd;
		param.actDt = dateFormatToStringYYYYMMDD(gridActDt);
		param.soId = gridSoId;
		param.targetMainSvcCd = $("#targetMainSvcCd").val();
		openModal2(url, param); 
	});	
		
	$('#svcRateItmTypAttrListSearch').click(function() {
		var url="svcRateItmTypAttrListSearchPopUp.ajax";
		var param = new Object();
		param.chrgCtgry = publicChrgCtgry;
		param.svcRateItmTypCd = $("#targetUpperCd").val();
		param.soId = publicSoId;
		openModal(url, param); 		
	});		
	
	$('#serviceMgtSvcRateItmTypFctrListNewBtn').click(function() {
		var url="svcRateItmTypFctrListInsertPopUp.ajax";
		var param = new Object();
		param.svcRateItmTypCd = $("#targetUpperCd").val();
		openModal(url, param); 		
	});		
	
	$('#serviceMgtSvcRateItmTypFctrListUpdateBtn').click(function() {
		var rowId = $("#serviceMgtSvcRateItmTypFctrListGrid").getGridParam("selrow");
		
		if (rowId == null){
			alert('<spring:message code="MSG.M07.MSG00101" />');
			return;
		}		
		
		var gridFctrCd = $("#serviceMgtSvcRateItmTypFctrListGrid").getRowData(rowId).fctrCd;
		var gridActDt = $("#serviceMgtSvcRateItmTypFctrListGrid").getRowData(rowId).actDt;
		var gridInactDt = $("#serviceMgtSvcRateItmTypFctrListGrid").getRowData(rowId).inactDt;
		var gridFctrNm = $("#serviceMgtSvcRateItmTypFctrListGrid").getRowData(rowId).fctrNm;

		
		var url="svcRateItmTypFctrListUpdatePopUp.ajax";
		var param = new Object();
		param.svcRateItmTypCd = $("#targetUpperCd").val();
		param.fctrCd = gridFctrCd;
		param.actDt = gridActDt;
		param.inactDt = gridInactDt;
		param.fctrNm = gridFctrNm;
		
		openModal(url, param); 		
	});		
	
	setTimeout("selectNode()",1000*0.5);
	
});

function reloadProduct(){
    $("#tree").dynatree("option", "initAjax", {
		type: "POST",
		url: "/product/service/serviceMgt/service/getTreeAction.json",
		complete : function(){
			var result = seachFolderNodeWithName($("#tree"));
   			if(!result){
   				$("#tree").dynatree("getTree").activateKey('ROOT'); //ROOT KEY 지정
   				
   				$("#tree").dynatree("getRoot").visit(function (node) {
   			    	node.expand(false);
   			    });
   			}
				
		},
		onActivate: function(node) {
			selectTree(node.data.prodCd, node.data.prodTyp, node.data.prodGrp, node.data.soId, node.data.treeLevel, node.data.svcCd, node.data.rateItmCd, node.data.chrgCtgry);
			$("#serviceMgtListSelectNodeParent").val(node.getParent().data.key); 
			$("#serviceMgtListSelectNode").val(node.data.key);
			$("#serviceMgtListSelectCurrentLvl").val(node.data.treeLevel);			
		},
		onPostInit: function (isReloading, isError) {
		}
     });

    $("#tree").dynatree("getTree").reload();
}

function selectNode(){
	var ProdTree = $("#tree").dynatree("getTree");
    var nodeKey = "";
	
	ProdTree.visit(function(node){
         if(node.data.key === userSoId){
             node.activate();
             //node 실행​
             node.select();
         } 
	 });
}

function seachFolderNodeWithName(tree) {
	var activatedNode = tree.dynatree("getActiveNode");
	
    var nodeKey = undefined;
	if ($("#serviceMgtListSelectCurrentLvl").val() <= 2) {
		nodeKey	= $("#serviceMgtListSelectNode").val();
	} else {
		nodeKey = $("#serviceMgtListSelectNodeParent").val();
	}
	console.log("key: " + nodeKey);
    tree.dynatree("getRoot").visit(function (node) {        
        
    });
    if(nodeKey != undefined){
    	tree.dynatree("getTree").activateKey(nodeKey);
    	return true;
    }else{
    	return false;
    }
}


function openModal(url, param) {
	
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
			var maskHeight = $(document).height();  
			var maskWidth = $(window).width();  

			//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
			$('#mask').css({'height':maskHeight});  

			//애니메이션 효과 - 일단 1초동안 까맣게 됐다가 80% 불투명도로 간다.
			$('#mask').fadeIn(100);      
			$('#mask').fadeTo("slow",0.5);    

			//윈도우 같은 거 띄운다.
			$('#popup_dialog').show();
		}
	}); 
	
}

function openModal2(url, param) {
	$.ajax({
		type : "post",
		url : url,
		data : param,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog2").html(html);
		},		
		complete : function(){
			var maskHeight = $(document).height();  
			var maskWidth = $(window).width();  

			//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
			$('#mask').css({'height':maskHeight});  

			//애니메이션 효과 - 일단 1초동안 까맣게 됐다가 80% 불투명도로 간다.
			$('#mask').fadeIn(100);      
			$('#mask').fadeTo("slow",0.5);    

			//윈도우 같은 거 띄운다.
			$('#popup_dialog2').show();
		}
	}); 
	
}



$(window).resize(function() {
	$(publicGridId).setGridWidth($(publicDivId).width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});

function initGrid() {
	
	$("#serviceMgtComListGrid").jqGrid({
		//url:'serviceMgtComListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : {
	   		targetCd : "0000000000",
	   		mstrfl : "1",
	   	},
	   	colModel:[
			{label:'<spring:message code="LAB.M07.LAB00168" />',name:'svcCd', width:50, align:"center"},
			{label:'<spring:message code="LAB.M07.LAB00185" />',name:'svcNm', width:100},
			{label:'<spring:message code="LAB.M08.LAB00003" />',name:'svcAbbrNm', width:80},
			{label:'<spring:message code="LAB.M07.LAB00188" />',name:'svcTypNm', width:80},
			{label:'<spring:message code="LAB.M10.LAB00081" />',name:'dispPriNo', width:80, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00054" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowNum:10,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#serviceMgtComListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 500,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#serviceMgtComListGrid").setGridWidth($("#serviceMgtComListGrid").width(),false);
	    },
		sortable: { update: function(permutation) {
			$("#serviceMgtComListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#serviceMgtComListGrid").setGridWidth($('#serviceMgtComListGridDiv').width(),false);
}

function selectTree(targetCd, mstrFl, isFolder, targetChrgCtgry, targetSoId, treeLevel) {
	console.log("level: " + treeLevel);
	publicTargetCd = targetCd;
	publicMstrFl = mstrFl;
	publicChrgCtgry = targetChrgCtgry;
	publicSoId = targetSoId;
	$("ul.tabs li").removeClass("active");
	$("ul.tabs li").css("color", "#b2b2b2");
	
	if (isFolder == "TRUE"){
		$("ul.tabs li:first").addClass("active");
		$("ul.tabs li:first").addClass("active").css("color", "#8ab700");
		$(".tab_content").hide();
	    var activeTab = $("ul.tabs li:first").attr("rel");
	    $("#" + activeTab).fadeIn();
	    excuteGridServiceMgtComList("#serviceMgtComListGrid", "#serviceMgtComListGridDiv", targetCd, mstrFl, targetSoId);
	    if (treeLevel == 0 || treeLevel == 1) {
	    	$("ul.tabs li:eq(0)").show();
		    $("ul.tabs li:eq(1)").hide();
	    	$("ul.tabs li:eq(2)").hide(); 		
		    $("ul.tabs li:eq(3)").hide();
	    	$("ul.tabs li:eq(4)").hide();
	    } else {
	 		$("ul.tabs li:eq(0)").show();
		    $("ul.tabs li:eq(1)").show();
	    	$("ul.tabs li:eq(2)").show(); 		
		    $("ul.tabs li:eq(3)").hide();
	    	$("ul.tabs li:eq(4)").hide();
	    }

	} else {
		$("ul.tabs li:eq(3)").addClass("active");
		$("ul.tabs li:eq(3)").addClass("active").css("color", "#8ab700");
		$(".tab_content").hide();
	    var activeTab = $("ul.tabs li:eq(3)").attr("rel");
	    $("#" + activeTab).fadeIn();
	    excuteGridServiceMgtSvcRateItmTypAttrList("#serviceMgtSvcRateItmTypAttrListGrid", "#serviceMgtSvcRateItmTypAttrListGridDiv", targetCd, targetChrgCtgry, targetSoId);
 		$("ul.tabs li:eq(0)").hide();
	    $("ul.tabs li:eq(1)").hide();
    	$("ul.tabs li:eq(2)").hide();
	    $("ul.tabs li:eq(3)").show();
    	$("ul.tabs li:eq(4)").show();    	
	}	
}


function excuteGridServiceMgtComList(id, divId, targetCd, mstrFl, targetSoId) {
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'serviceMgtComListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : {
	   		targetCd : targetCd,
	   		mstrfl : mstrFl,
	   		soId : targetSoId
	   	},
	   	colModel:[
			{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soId', width:80, align:"center", hidden:true},
			{label:'<spring:message code="LAB.M07.LAB00168" />',name:'svcCd', width:50, align:"center"},
			{label:'<spring:message code="LAB.M07.LAB00185" />',name:'svcNm', width:100},
			{label:'<spring:message code="LAB.M08.LAB00003" />',name:'svcAbbrNm', width:80},
			{label:'<spring:message code="LAB.M07.LAB00188" />',name:'svcTypNm', width:80},
			{label:'<spring:message code="LAB.M10.LAB00081" />',name:'dispPriNo', width:80, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00054" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}

	   	],
	   	shrinkToFit:false,
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#serviceMgtComListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 500,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#serviceMgtSaleItmListGrid").setGridWidth($("#serviceMgtSaleItmListGrid").width(),false);
	    },
		sortable: { update: function(permutation) {
			$("#serviceMgtSaleItmListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		},
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		}
	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;
}

function excuteGridServiceMgtSaleItmList(id, divId, targetCd, targetSoId) {
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'serviceMgtSaleItmListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : {
	   		targetCd : targetCd,
	   		soId : targetSoId
	   	},
	   	colModel:[
			{label:'<spring:message code="LAB.M05.LAB00015" />',name:'saleItmCd', width:100, align:"center"},
			{label:'<spring:message code="LAB.M05.LAB00012" />',name:'saleItmNm', width:150},
			{label:'<spring:message code="LAB.M05.LAB00014" />',name:'saleItmAbbrNm', width:100},
			{label:'<spring:message code="LAB.M08.LAB00055" />',name:'rateItmTypNm', width:120}, 
			{label:'<spring:message code="LAB.M05.LAB00008" />',name:'saleTyp', width:80},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soId', width:80, align:"center", hidden:true},
			{name:'svcCd', hidden:true}
	   	],
	   	shrinkToFit:false,
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	
	   	pager: '#serviceMgtSaleItmListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 500,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		}

	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;
}

function excuteGridServiceMgtSvcRateItmTypList(id, divId, targetCd, targetSoId) {
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'serviceMgtSvcRateItmTypListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : {
	   		targetCd : targetCd,
	   		soId : targetSoId
	   	},
	   	colModel:[
			{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soId', width:80, align:"center", hidden:true},
			{name:'rateItmTypCd', width:80, align:"center", hidden:true},
			{label:'<spring:message code="LAB.M01.LAB00154" />',name:'svcRateItmTypCd', width:100, align:"center"},
			{label:'<spring:message code="LAB.M01.LAB00155" />',name:'svcRateItmTypNm', width:200},
			{label:'<spring:message code="LAB.M08.LAB00003" />',name:'svcRateItmTypAbbrNm', width:150},
			{label:'<spring:message code="LAB.M08.LAB00051" />',name:'chrgCtgryNm', width:80, align:"center"}, 
			{label:'<spring:message code="LAB.M01.LAB00132" />',name:'rateLocatNm', width:100, align:"center"},
			{label:'<spring:message code="LAB.M05.LAB00010" />',name:'saleItmNm', width:100},
			{label:'<spring:message code="LAB.M10.LAB00043" />',name:'invItmNm', width:100},
			{label:'<spring:message code="LAB.M10.LAB00081" />',name:'dispPriNo', width:50, align:"center"},
			{label:'<spring:message code="LAB.M01.LAB00274" />',name:'ratePriNo', width:50, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}

	   	],
	   	shrinkToFit:false,
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	
	   	pager: '#serviceMgtSvcRateItmTypListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 500,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#serviceMgtSvcRateItmTypListGrid").setGridWidth($("#serviceMgtSvcRateItmTypListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);			
		},
		sortable: { update: function(permutation) {
			$("#serviceMgtSvcRateItmTypListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;	
}

function excuteGridServiceMgtSvcRateItmTypAttrList(id, divId, targetCd, targetChrgCtgry, soId) {
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getSvcRateItmTypAttrList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : {
	   		targetCd : targetCd,
	   		chrgCtgry : targetChrgCtgry,
	   		soId : soId
	   	},
	   	colModel:[
			{label:'<spring:message code="LAB.M07.LAB00232" />',name:'attrCd', width:100, align:"center"},     
			{label:'<spring:message code="LAB.M07.LAB00214" />',name:'attrNm', width:250},   
			{label:'<spring:message code="LAB.M07.LAB00215" />',name:'attrValNm', width:150, align:"center"},    
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	
	   	//pager: '#serviceMgtSvcRateItmTypListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 500,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#serviceMgtSvcRateItmTypAttrListGrid").setGridWidth($("#serviceMgtSvcRateItmTypAttrListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#serviceMgtSvcRateItmTypAttrListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}

	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;
}

function excuteGridServiceMgtSvcRateItmTypFctrList(id, divId, targetCd) {
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getSvcRateItmTypFctrList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : {
	   		targetCd : targetCd
	   	},
	   	colModel:[
			{name:'actDt', width:80, align:"center", hidden:true},
			{name:'inactDt', width:80, align:"center", hidden:true},
			{label:'<spring:message code="LAB.M08.LAB00068" />',name:'fctrCd', width:50, align:"center"},       
			{label:'<spring:message code="LAB.M08.LAB00062" />',name:'fctrNm', width:150},  
			{label:'<spring:message code="LAB.M10.LAB00010" />',name:'fctrRefTypNm', width:100, align:"center"}, 
			{label:'<spring:message code="LAB.M12.LAB00001" />',name:'tableNm', width:150}, 
			{label:'<spring:message code="LAB.M11.LAB00004" />',name:'colmnId', width:150},  
			{label:'<spring:message code="LAB.M03.LAB00069" />',name:'dataTypNm', width:100, align:"center"}, 
			{label:'<spring:message code="LAB.M10.LAB00016" />',name:'refTableId', width:150},  
			{label:'<spring:message code="LAB.M15.LAB00044" />',name:'refColmnId', width:150},  
			{label:'<spring:message code="LAB.M05.LAB00036" />',name:'refColmnNm', width:150}, 
			{label:'<spring:message code="LAB.M10.LAB00012" />',name:'refTableCond', width:100}, 
			{label:'<spring:message code="LAB.M10.LAB00002" />',name:'refNm', width:100}, 
			{label:'<spring:message code="LAB.M10.LAB00017" />',name:'refFunc', width:150}
	   	],	
	   	shrinkToFit:false,
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	
	   	//pager: '#serviceMgtSvcRateItmTypListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 500,					//화면 상태에 따라 크기 조절 할 것
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#serviceMgtSvcRateItmTypFctrListGrid").setGridWidth($("#serviceMgtSvcRateItmTypFctrListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#serviceMgtSvcRateItmTypFctrListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}

	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;
}

</script>
	<h3>${menuName}</h3>
	<!-- Navigator -->
		<div class="nav">                                        
		   <a href="#" class="home"></a>
			<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
			<span>&gt;</span>${mu.menuName}
		</c:forEach>
		</div>                               
   <!--// Navigator -->
	<div id="tree" style="overflow: auto;" class="ser_left">
	</div>
	<div class="ser_right">
	<!-- tab -->
	<ul id="serviceMgtComListUl" class="tabs">
		<li class="active" rel="serviceMgtComList"><spring:message code="LAB.M07.LAB00181"/></li>
		<li rel="serviceMgtSaleItmList"><spring:message code="LAB.M05.LAB00010"/></li>
		<li rel="serviceMgtSvcRateItmTypList"><spring:message code="LAB.M07.LAB00169"/></li>
		<li rel="serviceMgtSvcRateItmTypAttrList"><spring:message code="LAB.M07.LAB00172"/></li>
		<li rel="serviceMgtSvcRateItmTypFctrList"><spring:message code="LAB.M07.LAB00173"/></li>		
	</ul>
	<div class="tab_container">
		<div id="serviceMgtComList" class="tab_content">
			<div id='serviceMgtComListGridDiv'>
				<table id="serviceMgtComListGrid" class="w100p"></table>
				<div id="serviceMgtComListGridPager"></div>
			</div>
			<div class="main_btn_box">
				<div class="fr">
					<span id="commonBtn">
						<ntels:auth auth="${menuAuthC}">
						<a id="serviceMgtComListNewBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
						</ntels:auth>
						<ntels:auth auth="${menuAuthU}">
						<a id="serviceMgtComListUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
						</ntels:auth>
					</span>				
				</div>
			</div>
		</div>
		<!-- #tab1 -->
		<div id="serviceMgtSaleItmList" class="tab_content">
			<div id='serviceMgtSaleItmListGridDiv'>
				<table id="serviceMgtSaleItmListGrid" class="w100p"></table>
				<div id="serviceMgtSaleItmListPager"></div>
			</div>
			<div class="main_btn_box">
				<div class="fr">
					<span id="commonBtn">
						<ntels:auth auth="${menuAuthC}">
						<a id="serviceMgtSaleItmListNewBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
						</ntels:auth>
						<ntels:auth auth="${menuAuthU}">
						<a id="serviceMgtSaleItmListUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
						</ntels:auth>
					</span>				
				</div>
			</div>
		</div>
		<!-- #tab2 -->		
		<div id="serviceMgtSvcRateItmTypList" class="tab_content">
			<div id='serviceMgtSvcRateItmTypListGridDiv'>
				<table id="serviceMgtSvcRateItmTypListGrid" class="w100p"></table>
				<div id="serviceMgtSvcRateItmTypListPager"></div>
			</div>
			<div class="main_btn_box">
				<div class="fr">
					<span id="commonBtn">
						<ntels:auth auth="${menuAuthC}">
						<a id="serviceMgtSvcRateItmTypListNewBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
						</ntels:auth>
						<ntels:auth auth="${menuAuthU}">
						<a id="serviceMgtSvcRateItmTypListUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
						</ntels:auth>
					</span>				
				</div>
			</div>
		</div>
		<!-- #tab3 -->
		<div id="serviceMgtSvcRateItmTypAttrList" class="tab_content">
			<div id='serviceMgtSvcRateItmTypAttrListGridDiv'>
				<table id="serviceMgtSvcRateItmTypAttrListGrid" class="w100p"></table>
				<!-- <div id="serviceMgtSvcRateItmTypAttrListPager"></div>-->
			</div>
			<div class="main_btn_box">
				<div class="fr">
					<span id="commonBtn">
					<ntels:auth auth="${menuAuthU}">
					<a id="svcRateItmTypAttrListSearch" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
					</ntels:auth>					
					</span>				
				</div>
			</div>
		</div>
		<!-- #tab4 -->
		<div id="serviceMgtSvcRateItmTypFctrList" class="tab_content">
			<div id='serviceMgtSvcRateItmTypFctrListGridDiv'>
				<table id="serviceMgtSvcRateItmTypFctrListGrid" class="w100p"></table>
				<div id="serviceMgtSvcRateItmTypFctrListPager"></div>
			</div>
			<div class="main_btn_box">
				<div class="fr">
					<span id="commonBtn">
						<ntels:auth auth="${menuAuthC}">
						<a id="serviceMgtSvcRateItmTypFctrListNewBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
						</ntels:auth>
						<ntels:auth auth="${menuAuthU}">
						<a id="serviceMgtSvcRateItmTypFctrListUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
						</ntels:auth>
					</span>				
				</div>
			</div>
		</div>
		<!-- #tab5 -->				
	    </div>
	 </div>   
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:400px;" >
</div>
<div id="popup_dialog2" class="Layer_wrap" style="display: none;width:800px;" >
</div>
<input id="targetUpperCd" value='0' type='text' hidden />
<input id="targetMainSvcCd" value='0' type='text' hidden />
<input id="serviceMgtListTargetNm" value='0' type='text' hidden />

<input id="serviceMgtListSelectNode" type='text'  hidden />
<input id="serviceMgtListSelectNodeParent" type='text'  hidden />
<input id="serviceMgtListSelectCurrentLvl" type='text'  hidden />
		    
</html>
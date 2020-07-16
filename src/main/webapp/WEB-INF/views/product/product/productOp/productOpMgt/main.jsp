<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
/* Layer */
.Layer_wrap2 {  overflow-y:auto;
	  position:absolute;  
	  left: 45%;
	  top: 45%;
	-ms-transform: translate(-45%, -45%);
	-webkit-transform: translate(-45%, -45%);
	-moz-transform: translate(-45%, -45%);
    transform: translate(-45%, -45%);
	  z-index:9999;
	 margin:0; 
	 padding:0; 
	 border:2px solid #69822b;
	 background:#fff;
	 
	 }  
	 
 ul.dynatree-container {
	border: none;
}; 
  	 
</style>

<script type="text/javascript">
 var publicGridId = "#productDevMgtProductListGrid";
 var publicDivId = "#productDevMgtProductListGridDiv";
 var publicProdCd = "";
 var publicProdTyp = "";
 var publicProdGrp = "";
 var publicSoId = "";
 var publicSvcCd = "";
 var publicRateItmCd = "";
 var publicChrgCtgry = "";
 var publicDvlpStatus = "";
 var publicSelectNode = "";

 var userSoId = '${userSoId}';
 
$(function(){
	$(".search").css("margin-top", "3px");
	
	$(".home_wrap").css("min-width", "1340px");
	
	$("ul.tabs li").each(function(i){
		if (i != 0) {
			$(this).hide();
		}
	});
	
	//닫기 버튼을 눌렀을 때
	$('.Layer_wrap2 ').on('click','.close',function (e) {
	    //링크 기본동작은 작동하지 않도록 한다.
	    e.preventDefault();  
	    $('#mask, .Layer_wrap2').hide();  
	});

	//검은 막을 눌렀을 때
	$('#mask').click(function () {  
	    $(this).hide();   
	    $('.Layer_wrap2').hide();  
	}); 		
	
	$(".ser_left").css("min-height","628px");
	$(".ser_left").css("height","591px");
	$(".ser_left").css("padding", "0px");
	$(".ser_left").css("width", "300px");	
	
	var tree = $("#tree");
	initGrid();
	
	$(tree).dynatree({
 		initAjax: {
 			url: "/product/product/productOp/productOpMgt/getTreeAction2.json"
 		},
		clickFolderMode: 1,
		minExpandLevel: 2,	
		onActivate: function(node) {
			selectTree(node.data.prodCd, node.data.prodTyp, node.data.prodGrp, node.data.soId, node.data.treeLevel, node.data.svcCd, node.data.rateItmCd, node.data.chrgCtgry);
			$("#productDevMgtProductListSelectNodeParent").val(node.getParent().data.key); 
			$("#productDevMgtProductListSelectNode").val(node.data.key);
			$("#productDevMgtProductListSelectCurrentLvl").val(node.data.treeLevel);
		},
		onPostInit: function (isReloading, isError) {
			// 트리 리로딩 이벤트때도 실행되서 주석 처리
			//selectNode();
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
		if (index == 0){
			excuteGridProductDevMgtProductList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicProdTyp, publicProdGrp, publicSoId);
		}
		
		if (index == 1){
			excuteGridProductServiceList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicProdCd, publicSoId);
		}
		
		if (index == 2){
			excuteGridSuprtEquipList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicProdCd, publicSoId);
		}		

		if (index == 3){
			excuteGridProductAttrList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicProdCd, publicProdTyp);
		}	
		
		if (index == 4){
			excuteGridProductRelationshipList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicProdCd);
		}			
		
		if (index == 5){
			excuteGridRateItmList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicProdCd, publicSvcCd, publicSoId); /*han 2020.05.19 */
		}	
		
		if (index == 6){
			excuteGridRateItmFctrList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicRateItmCd, publicSoId); /*han 2020.05.19 */
			excuteGridRateItmRegCnt("#rateItmRegCntGrid", "#rateItmRegCntGridDiv", publicRateItmCd, publicChrgCtgry);
		}		
		
		if (index == 7){
			excuteGridRateItmAttrList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicRateItmCd, publicChrgCtgry, publicSoId); /*han 2020.05.19 */
			excuteGridRateItmRegCnt("#rateItmAttrListRateItmRegCntGrid", "#rateItmAttrListRateItmRegCntGridDiv", publicRateItmCd, publicChrgCtgry);
		}	
		
		if (index == 8){
			excuteGridRateItmCondList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicRateItmCd, publicSoId);  /*han 2020.05.19 */
			excuteGridRateItmRegCnt("#rateItmCondListRateItmRegCntGrid", "#rateItmCondListRateItmRegCntGridDiv", publicRateItmCd, publicChrgCtgry);
		}		

		if (index == 9){
			excuteGridAllowanceList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicProdCd);
			excuteGridRateItmRegCnt("#allowanceListRateItmRegCntGrid", "#allowanceListRateItmRegCntGridDiv", publicRateItmCd, publicChrgCtgry);
		}			
		
		if (index == 10){
			excuteGridDiscExclList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicRateItmCd);
			excuteGridRateItmRegCnt("#discExclListRateItmRegCntGrid", "#discExclListRateItmRegCntGridDiv", publicRateItmCd, publicChrgCtgry);
		}	
		
		if (index == 11){
			excuteGridDiscSvcRateItmTypList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicRateItmCd);
			excuteGridRateItmRegCnt("#discSvcRateItmTypListRateItmRegCntGrid", "#discSvcRateItmTypListRateItmRegCntGridDiv", publicRateItmCd, publicChrgCtgry);
		}	
		
		if (index == 12){
			excuteGridDiscPerdList("#" + activeTab + "Grid", "#" + activeTab + "GridDiv", publicRateItmCd);
			excuteGridRateItmRegCnt("#discPerdListRateItmRegCntGrid", "#discPerdListRateItmRegCntGridDiv", publicRateItmCd, publicChrgCtgry);
		}			

		
		
	});		
	
	setTimeout("selectNode()",1000*0.5);
	
});



function reloadProduct(){
    $("#tree").dynatree("option", "initAjax", {
		type: "POST",
		url: "/product/product/productDev/productDevMgt/getTreeAction.json",
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
			$("#productDevMgtProductListSelectNodeParent").val(node.getParent().data.key); 
			$("#productDevMgtProductListSelectNode").val(node.data.key);
			$("#productDevMgtProductListSelectCurrentLvl").val(node.data.treeLevel);			
		},
		onPostInit: function (isReloading, isError) {
		}
     });

    $("#tree").dynatree("getTree").reload();
}

function seachFolderNodeWithName(tree) {
	var activatedNode = tree.dynatree("getActiveNode");
	
    var nodeKey = undefined;
	if ($("#productDevMgtProductListSelectCurrentLvl").val() <= 5) {
		nodeKey	= $("#productDevMgtProductListSelectNode").val();
	} else {
		nodeKey = $("#productDevMgtProductListSelectNodeParent").val();
	}

    tree.dynatree("getRoot").visit(function (node) {        
        
    });
    if(nodeKey != undefined){
    	tree.dynatree("getTree").activateKey(nodeKey);
    	return true;
    }else{
    	return false;
    }
}


function chkDvlpStatus() {
	if (publicDvlpStatus != "1") {
		alert('<spring:message code="MSG.M07.MSG00111" />');
		return false;
	} else {
		return true;
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

function openModal2_sub(url) {
	$.ajax({
		type : "post",
		url : url,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog2_sub").html(html);
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
			$('#popup_dialog2_sub').show();
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

function openModal3_sub(url) {
	$.ajax({
		type : "post",
		url : url,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog3_sub").html(html);
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
			$('#popup_dialog3_sub').show();
		}
	}); 
	
}

function openModal3_sub_get(url) {
	$.ajax({
		type : "get",
		url : url,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog3_sub").html(html);
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
			$('#popup_dialog3_sub').show();
		}
	}); 
	
}

function openModal3(url, param) {
	
	$.ajax({
		type : "post",
		url : url,
		data : param,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog3").html(html);
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
			$('#popup_dialog3').show();
		}
	}); 
	
}

function openModal4(url, param) {
	
	$.ajax({
		type : "post",
		url : url,
		data : param,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog4").html(html);
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
			$('#popup_dialog4').show();
		}
	}); 
	
}

$(window).resize(function() {
	$(publicGridId).setGridWidth($(publicDivId).width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});

function initGrid() {
	
	$("#productDevMgtProductListGrid").jqGrid({
		//url:'serviceMgtComListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	colModel:[
			{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100, align:"center"},      
			{label:'<spring:message code="LAB.M07.LAB00146" />',name:'prodTypNm', width:80},
			{label:'<spring:message code="LAB.M07.LAB00156" />',name:'prodCd', width:80, align:"center"}, 
			{label:'<spring:message code="LAB.M07.LAB00130" />',name:'prodNm', width:200},
			{label:'<spring:message code="LAB.M01.LAB00212" />',name:'basicProdFlNm', width:80, align:"center"}, 
			{label:'<spring:message code="LAB.M07.LAB00126" />',name:'prodGrpNm', width:200, align:"center"},
			{label:'<spring:message code="LAB.M07.LAB00116" />',name:'prodLvl', width:50, align:"right"}, 
			{label:'<spring:message code="LAB.M07.LAB00183" />',name:'svcGrpNm', width:150, align:"center"},
			{label:'<spring:message code="LAB.M07.LAB00027" />',name:'useMmTypNm', width:50, align:"center"}, 
			{label:'<spring:message code="LAB.M10.LAB00046" />',name:'subsFlNm', width:100, align:"center"}, 
			{label:'<spring:message code="LAB.M10.LAB00081" />',name:'dispPriNo', width:50, align:"center"}, 
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00106" />',name:'mnoProdCd', width:100},
			{name:'dvlpStatus', width:80, align:"center", hidden:true}
	   	],
	   	shrinkToFit:false,
	   	rowNum:10,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#productDevMgtProductListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 537,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		}
	});
	
	$("#productDevMgtProductListGrid").setGridWidth($('#productDevMgtProductListGridDiv').width(),false);
}

function selectTree(prodCd, prodTyp, prodGrp, soId, treeLevel, svcCd, rateItmCd, chrgCtgry) {
	publicProdCd = prodCd;
	publicProdTyp = prodTyp;
	publicProdGrp = prodGrp;
	publicSoId = soId;
	publicSvcCd = svcCd;
	publicRateItmCd = rateItmCd;
	publicChrgCtgry = chrgCtgry;
	
	
	$("ul.tabs li").removeClass("active");
	$("ul.tabs li").css("color", "#b2b2b2");	
	
	if (treeLevel == '1' || treeLevel == '2' || treeLevel == '3' || treeLevel == '0' ) {
		$("ul.tabs li:first").addClass("active");
		$("ul.tabs li:first").addClass("active").css("color", "#8ab700");
		$(".tab_content").hide();
    	var activeTab = $("ul.tabs li:first").attr("rel");
	    $("#" + activeTab).fadeIn();
   		excuteGridProductDevMgtProductList("#productDevMgtProductListGrid", "#productDevMgtProductListGridDiv", prodTyp, prodGrp, soId);
   		
   		$("ul.tabs li").each(function(i){
   			if (i == 0) {
   				$(this).show();
   			} else {
   				$(this).hide();
   			} 
   		});
	} else if (treeLevel == '4') {
		$("ul.tabs li:eq(1)").addClass("active");
		$("ul.tabs li:eq(1)").addClass("active").css("color", "#8ab700");
		$(".tab_content").hide();
	    var activeTab = $("ul.tabs li:eq(1)").attr("rel");
	    $("#" + activeTab).fadeIn();
	    excuteGridProductServiceList("#productServiceListGrid", "#productServiceListGridDiv", prodCd, soId);
	    
   		$("ul.tabs li").each(function(i){
   			if (i == 1 || i == 2 || i == 3 || i == 4) {
   				$(this).show();
   			} else {
   				$(this).hide();
   			}
   		});
	    
	} else if (treeLevel == '5') {
		$("ul.tabs li:eq(5)").addClass("active");
		$("ul.tabs li:eq(5)").addClass("active").css("color", "#8ab700");
		$(".tab_content").hide();
	    var activeTab = $("ul.tabs li:eq(5)").attr("rel");
	    $("#" + activeTab).fadeIn();
	    excuteGridRateItmList("#rateItmListGrid", "#rateItmListGridDiv", prodCd, svcCd, soId);  /*han 2020.05.19 */
	    
   		$("ul.tabs li").each(function(i){
   			if (i == 5) {
   				$(this).show();
   			} else {
   				$(this).hide();
   			}
   		});
	} else if (treeLevel == '6') {
		$("ul.tabs li:eq(6)").addClass("active");
		$("ul.tabs li:eq(6)").addClass("active").css("color", "#8ab700");
		$(".tab_content").hide();
	    var activeTab = $("ul.tabs li:eq(6)").attr("rel");
	    $("#" + activeTab).fadeIn();
	    excuteGridRateItmFctrList("#rateItmFctrListGrid", "#rateItmFctrListGridDiv", rateItmCd, soId);  /*han 2020.05.19 */
	    excuteGridRateItmRegCnt("#rateItmRegCntGrid", "#rateItmRegCntGridDiv", rateItmCd, chrgCtgry);
	    if (chrgCtgry == "M" || chrgCtgry == "D" || chrgCtgry == "A") {
	   		$("ul.tabs li").each(function(i){
	   			if (i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12) {
	   				$(this).show();
	   			} else {
	   				$(this).hide();
	   			}
	   		});		    	
	    } else {
	   		$("ul.tabs li").each(function(i){
	   			if (i == 6 || i == 7 || i == 8 || i == 9) {
	   				$(this).show();
	   			} else {
	   				$(this).hide();
	   			}
	   		});	    	
	    }

	}
	
	//getProdDvlpStatus(prodCd);
	
	}

function getProdDvlpStatus(prodCd){
	publicDvlpStatus = -1;
	if (prodCd != null){
		$.ajax({
			url : 'getProdDvlpStatus.json',
			type : 'POST',
			async: false,
			data : {"prodCd" : prodCd},
			success : function(data) {
				if (data.productDevMgt != null) {
					publicDvlpStatus = data.productDevMgt.dvlpStatus;
				} else {
					publicDvlpStatus = -1;
				}
			},
		    error: function(request,status,error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    },
			complete : function() {
			}
		});
	}
}

function excuteGridProductDevMgtProductList(id, divId, prodTyp, prodGrp, soId) {
	var param = new Object();
	
	if (prodTyp != null) {
		param.prodTyp = prodTyp;
	}
	
	if (prodGrp != null) {
		param.prodGrp = prodGrp;
	}
	
	if (soId != null) {
		param.soId = soId;
	}	
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'productDevMgtProductListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
  			{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100, align:"center"},      
			{label:'<spring:message code="LAB.M07.LAB00146" />',name:'prodTypNm', width:80},
			{label:'<spring:message code="LAB.M07.LAB00156" />',name:'prodCd', width:80, align:"center"}, 
			{label:'<spring:message code="LAB.M07.LAB00130" />',name:'prodNm', width:200},
			{label:'<spring:message code="LAB.M01.LAB00212" />',name:'basicProdFlNm', width:80, align:"center"}, 
			{label:'<spring:message code="LAB.M07.LAB00126" />',name:'prodGrpNm', width:200, align:"center"},
			{label:'<spring:message code="LAB.M07.LAB00116" />',name:'prodLvl', width:50, align:"right"}, 
			{label:'<spring:message code="LAB.M07.LAB00183" />',name:'svcGrpNm', width:150, align:"center"},
			{label:'<spring:message code="LAB.M07.LAB00027" />',name:'useMmTypNm', width:50, align:"center"}, 
			{label:'<spring:message code="LAB.M10.LAB00046" />',name:'subsFlNm', width:100, align:"center"}, 
			{label:'<spring:message code="LAB.M10.LAB00081" />',name:'dispPriNo', width:50, align:"center"}, 
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00106" />',name:'mnoProdCd', width:100},
			{name:'dvlpStatus', width:80, align:"center", hidden:true},	
			{name:'soId', width:80, align:"center", hidden:true}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#productDevMgtProductListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 537,					//화면 상태에 따라 크기 조절 할 것
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


function excuteGridProductServiceList(id, divId, prodCd, soId) {
	var param = new Object();
	
	param.prodCd = prodCd;
	param.soId = soId;
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getProductServiceList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'dvlpStatus', hidden:true},	
  			{label:'<spring:message code="LAB.M07.LAB00190" />',name:'svcCd', width:100, align:"center"},  
  			{label:'<spring:message code="LAB.M07.LAB00167" />',name:'svcNm', width:150},  
  			{label:'<spring:message code="LAB.M07.LAB00107" />',name:'uprSvcNm', width:150}, 
  			{label:'<spring:message code="LAB.M05.LAB00032" />',name:'mainSvcNm', width:150}, 
  			{label:'<spring:message code="LAB.M01.LAB00006" />',name:'subscripCmpsFlNm', width:150},   			
  			{label:'<spring:message code="LAB.M13.LAB00031" />',name:'essFlNm', width:150},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#productServiceListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 537,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	//$("#productServiceListGrid").setGridWidth($("#productServiceListGrid").width(),false);
			$("#productServiceListGrid").setGridWidth($('#productServiceListGridDiv').width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			//$("#productServiceListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			$("#productServiceListGrid").setGridWidth($('#productServiceListGridDiv').width(),false);
			}
		}
	});
	
	//$(id).setGridWidth($(divId).width(),false);
	$("#productServiceListGrid").setGridWidth($('#productServiceListGridDiv').width(),false);
	publicGridId = id;
	publicDivId = divId;	
}

function excuteGridSuprtEquipList(id, divId, prodCd, soId) {
	var param = new Object();
	
	param.prodCd = prodCd;
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getSuprtEquipList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'dvlpStatus', hidden:true},	
  			{label:'<spring:message code="LAB.M09.LAB00045" />',name:'eqtClCd', width:150, align:"center"},  
  			{label:'<spring:message code="LAB.M09.LAB00043" />',name:'eqtClNm', width:200},  
  			{label:'<spring:message code="LAB.M03.LAB00052" />',name:'sbjProdNm', width:150}, 
  			{label:'<spring:message code="LAB.M07.LAB00185" />',name:'svcNm', width:150}, 
  			{label:'<spring:message code="LAB.M01.LAB00166" />',name:'cmpsQtyFrom', width:100, align:"center"},   			
  			{label:'<spring:message code="LAB.M01.LAB00167" />',name:'cmpsQtyTo', width:100, align:"center"},
  			{label:'<spring:message code="LAB.M10.LAB00081" />',name:'dispPriNo', width:100, align:"center"}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#suprtEquipListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 537,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#suprtEquipListGrid").setGridWidth($("#suprtEquipListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#suprtEquipListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;	
}

function excuteGridProductAttrList(id, divId, prodCd, prodTyp) {
	var param = new Object();
	
	param.prodCd = prodCd;
	param.prodTyp= prodTyp;
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getProductAttrList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'dvlpStatus', hidden:true},	
  			{label:'<spring:message code="LAB.M07.LAB00232" />',name:'attrCd', width:150, align:"center"},  
  			{label:'<spring:message code="LAB.M07.LAB00214" />',name:'attrNm', width:200},  
  			{label:'<spring:message code="LAB.M07.LAB00215" />',name:'attrValNm', width:150}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#productAttrListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 537,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#productAttrListGrid").setGridWidth($("#productAttrListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#productAttrListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;	
}

function excuteGridProductRelationshipList(id, divId, prodCd) {
	var param = new Object();
	
	param.prodCd = prodCd;
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getProductRelationshipList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'dvlpStatus', hidden:true},	
			{name:'prodRelTyp', hidden:true},
			{name:'relProdCd', hidden:true},
  			{label:'<spring:message code="LAB.M01.LAB00221" />',name:'prodNm', width:150},  
  			{label:'<spring:message code="LAB.M01.LAB00158" />',name:'prodRelTypNm', width:150},  
  			{label:'<spring:message code="LAB.M01.LAB00159" />',name:'relProdNm', width:200}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#productRelationshipListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 537,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#productRelationshipListGrid").setGridWidth($("#productRelationshipListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#productRelationshipListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;	
}

function excuteGridRateItmList(id, divId, prodCd, svcCd, soId) { /* han 2020.05.19 */
	var param = new Object();
	
	param.prodCd = prodCd;
	param.svcCd = svcCd;
	param.soId = soId;          /* han 2020.05.19 */
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getRateItmList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
  			{name:'svcRateItmTypCd', hidden:true},
  			{name:'svcCd', hidden:true},
			{name:'dvlpStatus', hidden:true},
  			{label:'<spring:message code="LAB.M01.LAB00156" />',name:'rateItmCd', width:80, align:"center"},  
  			{label:'<spring:message code="LAB.M01.LAB00152" />',name:'rateItmNm', width:200},  
  			{label:'<spring:message code="LAB.M08.LAB00051" />',name:'chrgCtgryNm', width:50, align:"center"}, 
  			{label:'<spring:message code="LAB.M07.LAB00175" />',name:'svcRateItmTypNm', width:120}, 
  			{label:'<spring:message code="LAB.M01.LAB00148" />',name:'rateLocatNm', width:50, align:"center"}, 
  			{label:'<spring:message code="LAB.M08.LAB00077" />',name:'rateRefTypNm', width:120, align:"center"}, 
  			{label:'<spring:message code="LAB.M01.LAB00101" />',name:'defltRateItmNm', width:100, align:"center"}, 
  			{label:'<spring:message code="LAB.M15.LAB00060" />',name:'mnoRateItmCd', width:100, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#rateItmListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 537,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	//$("#rateItmListGrid").setGridWidth($("#rateItmListGrid").width(),false);
			$("#rateItmListGrid").setGridWidth($('#rateItmListGridDiv').width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			//$("#rateItmListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			$("#rateItmListGrid").setGridWidth($('#rateItmListGridDiv').width(),false);
			}
		}
	});
	
	//$(id).setGridWidth($(divId).width(),false);
	$("#rateItmListGrid").setGridWidth($('#rateItmListGridDiv').width(),false);
	publicGridId = id;
	publicDivId = divId;	
}

function excuteGridRateItmFctrList(id, divId, rateItmCd, soId) { /* han 2020.05.19 */
	var param = new Object();
	
	param.rateItmCd = rateItmCd;
	param.soId = soId;          /* han 2020.05.19 */
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getRateItmFctrList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'dvlpStatus', hidden:true},
			{label:'<spring:message code="LAB.M08.LAB00068" />',name:'fctrCd', width:100, align:"center"},        
  			{label:'<spring:message code="LAB.M08.LAB00064" />',name:'fctrNm', width:150},
  			{label:'<spring:message code="LAB.M08.LAB00069" />',name:'rateInfoExeFlNm', width:100, align:"center"},
  			{label:'<spring:message code="LAB.M08.LAB00072" />',name:'fctrNo', width:100, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#rateItmFctrListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	//$("#rateItmFctrListGrid").setGridWidth($("#rateItmFctrListGrid").width(),false);
			$("#rateItmFctrListGrid").setGridWidth($('#rateItmFctrListGridDiv').width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
		//	$("#rateItmFctrListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			$("#rateItmFctrListGrid").setGridWidth($('#rateItmFctrListGridDiv').width(),false);
			}
		}
	});
	
	//$(id).setGridWidth($(divId).width(),false);
	$("#rateItmFctrListGrid").setGridWidth($('#rateItmFctrListGridDiv').width(),false);
	publicGridId = id;
	publicDivId = divId;	
	//그리드 화면 재조정
	$(window).resize(function() {
	//	$(id).setGridWidth($(divId).width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#rateItmFctrListGrid").setGridWidth($('#rateItmFctrListGridDiv').width(),false);
	});
}

function excuteGridRateItmRegCnt(id, divId, rateItmCd, chrgCtgry) {
	var colN;
	var colM;
	
	if (chrgCtgry == "M" || chrgCtgry == "D" || chrgCtgry == "A") {
		colN = ['과금항목요소', '과금항목속성', '과금조건', '요율', '할인배타관계', '할인대상과금항목', '할인기간' ];
		colM = [
				{name:'fctrCnt', width:100, align:"center"},
				{name:'attrCnt', width:100, align:"center"},
				{name:'condCnt', width:100, align:"center"},
				{name:'rateCnt', width:100, align:"center"},
				{name:'discExclCnt', width:100, align:"center"},
				{name:'discSvcRateItmTypCnt', width:100, align:"center"},
				{name:'discPerdCnt', width:100, align:"center"}
		        ];		
	} else {
		colN = ['과금항목요소', '과금항목속성', '과금조건', '요율'];
		colM = [
				{name:'fctrCnt', width:200, align:"center"},
				{name:'attrCnt', width:200, align:"center"},
				{name:'condCnt', width:200, align:"center"},
				{name:'rateCnt', width:200, align:"center"}
		        ];			
	}
	
	var param = new Object();
	
	param.rateItmCd = rateItmCd;
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'/product/product/productDev/productDevMgt/getRateItmRegCnt.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colNames : colN,
	   	colModel : colM,
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 23,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			root : "rows",	
		}
	});
	
	$(id).setGridWidth($(divId).width(),false);
//	$("#rateItmAttrListGrid").setGridWidth($('#rateItmAttrListGridDiv').width(),false);
	publicGridId = id;
	publicDivId = divId;	

}

function excuteGridRateItmAttrList(id, divId, rateItmCd, chrgCtgry, soId) {  /* han 2020.05.19 */
	var param = new Object();
	
	param.rateItmCd = rateItmCd;
	param.chrgCtgry = chrgCtgry;
	param.soId = soId;             /* han 2020.05.19 */
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getRateItmAttrList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'dvlpStatus', hidden:true},
			{label:'<spring:message code="LAB.M07.LAB00232" />',name:'attrCd', width:100, align:"center"},        
  			{label:'<spring:message code="LAB.M07.LAB00214" />',name:'attrNm', width:250},
  			{label:'<spring:message code="LAB.M07.LAB00232" />',name:'attrValNm', width:200}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#rateItmAttrListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
			$("#rateItmAttrListGrid").setGridWidth($('#rateItmAttrListGridDiv').width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#rateItmAttrListGrid").setGridWidth($('#rateItmAttrListGridDiv').width(),false);
			}
		}
	});
	
	$("#rateItmAttrListGrid").setGridWidth($('#rateItmAttrListGridDiv').width(),false);

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#rateItmAttrListGrid").setGridWidth($('#rateItmAttrListGridDiv').width(),false);
	});
}

function excuteGridRateItmCondList(id, divId, rateItmCd, soId) { /* han 2020.05.19 */
	var param = new Object();
	
	param.rateItmCd = rateItmCd;
	param.soId = soId;            /* han 2020.05.19 */
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getRateItmCondList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'condNo', hidden:true},
			{name:'dvlpStatus', hidden:true},
			{label:'<spring:message code="LAB.M01.LAB00129" />',name:'fctrCd', width:100, align:"center"},      
			{label:'<spring:message code="LAB.M01.LAB00127" />',name:'fctrNm', width:100},       
			{label:'<spring:message code="LAB.M06.LAB00100" />',name:'condOperatorNm', width:100, align:"center"},
			{label:'<spring:message code="LAB.M10.LAB00007" />',name:'oprndRefFlNm', width:100, align:"center"},
			{label:'<spring:message code="LAB.M01.LAB00058" />',name:'oprnd1Nm', width:150},
			{label:'<spring:message code="LAB.M01.LAB00120" />',name:'oprndFctrCd1Nm', width:80},
			{label:'<spring:message code="LAB.M01.LAB00059" />',name:'oprnd2', width:150},
			{label:'<spring:message code="LAB.M01.LAB00121" />',name:'oprndFctrCd2Nm', width:80},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#rateItmCondListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	//$("#rateItmCondListGrid").setGridWidth($("#rateItmCondListGrid").width(),false);
			$("#rateItmCondListGrid").setGridWidth($('#rateItmCondListGridDiv').width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			//$("#rateItmCondListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			$("#rateItmCondListGrid").setGridWidth($('#rateItmCondListGridDiv').width(),false);
			}
		}
	});
	
	//$(id).setGridWidth($(divId).width(),false);
	$("#rateItmCondListGrid").setGridWidth($('#rateItmCondListGridDiv').width(),false);
	publicGridId = id;
	publicDivId = divId;	
	//그리드 화면 재조정
	$(window).resize(function() {
		//$(id).setGridWidth($(divId).width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#rateItmCondListGrid").setGridWidth($('#rateItmCondListGridDiv').width(),false);
	});
}

function excuteGridAllowanceList(id, divId, prodCd) {
	var param = new Object();
	
	param.prodCd = prodCd;
	param.rateItmCd = publicRateItmCd;
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getAllowanceList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'dvlpStatus', hidden:true},
			{label:'<spring:message code="LAB.M08.LAB00209" />',name:'fctrValNm1', width:100, align:"center"},         
			{label:'<spring:message code="LAB.M08.LAB00210" />',name:'fctrValNm2', width:100, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00211" />',name:'fctrValNm3', width:100, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00212" />',name:'fctrValNm4', width:100, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00213" />',name:'fctrValNm5', width:100, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00214" />',name:'fctrValNm6', width:100, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00215" />',name:'fctrValNm7', width:100, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00216" />',name:'fctrValNm8', width:100, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00217" />',name:'fctrValNm9', width:100, align:"center"},  
			{label:'<spring:message code="LAB.M03.LAB00011" />',name:'rateRefFlNm', width:100, align:"center"},         
			{label:'<spring:message code="LAB.M03.LAB00006" />',name:'rateVal', width:70, align:"right"},  
			{label:'<spring:message code="LAB.M03.LAB00012" />',name:'rateFctrNm', width:150},
			{label:'<spring:message code="LAB.M07.LAB00248" />',name:'qtyRefFlNm', width:100, align:"center"},
			{label:'<spring:message code="LAB.M07.LAB00247" />',name:'qty', width:70, align:"right"},  
			{label:'<spring:message code="LAB.M07.LAB00249" />',name:'qtyFctrNm', width:150},
			{label:'<spring:message code="LAB.M12.LAB00006" />',name:'crncyRefFlNm', width:100, align:"center"}, 
			{label:'<spring:message code="LAB.M12.LAB00005" />',name:'crncyCdNm', width:100, align:"center"},
			{label:'<spring:message code="LAB.M12.LAB00007" />',name:'crncyFctrNm', width:150},
			{label:'<spring:message code="LAB.M01.LAB00085" />',name:'alwnceNm', width:100, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}

	   	],
	   	shrinkToFit:false,

		rowNum : 10000,
	   	pager: '#allowanceListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 380,					//화면 상태에 따라 크기 조절 할 것

	    loadComplete: function(obj){
	    
			$("#allowanceListGrid").setGridWidth($('#allowanceListGridDiv').width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#allowanceListGrid").setGridWidth($('#allowanceListGridDiv').width(),false);
			}
		}
	});
	$("#allowanceListGrid").setGridWidth($('#allowanceListGridDiv').width(),false);
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#allowanceListGrid").setGridWidth($('#allowanceListGridDiv').width(),false);
	});
}

function excuteGridDiscExclList(id, divId, publicRateItmCd) {
	var param = new Object();
	
	param.rateItmCd = publicRateItmCd;
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getDiscExclList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'svcRateItmTypCd', hidden:true},
			{label:'<spring:message code="LAB.M05.LAB00033" />',name:'mainSvcNm', width:150},     
			{label:'<spring:message code="LAB.M07.LAB00190" />',name:'svcCd', width:80, align:"center"}, 
			{label:'<spring:message code="LAB.M07.LAB00185" />',name:'svcNm', width:150},  
			{label:'<spring:message code="LAB.M07.LAB00177" />',name:'svcRateItmTypNm', width:200},
						{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#discExclListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#discExclListGrid").setGridWidth($("#discExclListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#discExclListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;	
}

function excuteGridDiscSvcRateItmTypList(id, divId, publicRateItmCd) {
	var param = new Object();
	
	param.rateItmCd = publicRateItmCd;
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getDiscSvcRateItmTypList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[   
			{label:'<spring:message code="LAB.M07.LAB00175" />',name:'svcRateItmTypCd', width:150, align:"center"},  
			{label:'<spring:message code="LAB.M07.LAB00177" />',name:'svcRateItmTypNm', width:250},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#discSvcRateItmTypListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#discSvcRateItmTypListGrid").setGridWidth($("#discSvcRateItmTypListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#discSvcRateItmTypListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;	
}

function excuteGridDiscPerdList(id, divId, publicRateItmCd) {
	var param = new Object();
	
	param.rateItmCd = publicRateItmCd;
	
	$(id).jqGrid("GridUnload"); 
	$(id).jqGrid({
		url:'getDiscPerdList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[   
			{name:'rateItmCd', hidden:true},
			{label:'<spring:message code="LAB.M06.LAB00100" />',name:'operatorNm', width:100},
			{label:'<spring:message code="LAB.M06.LAB00096" />',name:'oprndRefFl1Nm', width:150, align:"center"},
			{label:'<spring:message code="LAB.M06.LAB00098" />',name:'oprndFctrCd1Nm', width:200},
			{label:'<spring:message code="LAB.M06.LAB00094" />',name:'oprnd1', width:100, align:"center"},
			{label:'<spring:message code="LAB.M06.LAB00097" />',name:'oprndRefFl2Nm', width:150, align:"center"},
			{label:'<spring:message code="LAB.M06.LAB00099" />',name:'oprndFctrCd2Nm', width:200},
			{label:'<spring:message code="LAB.M06.LAB00095" />',name:'oprnd2', width:100, align:"center"},
			{label:'<spring:message code="LAB.M09.LAB00052" />',name:'actDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD},
			{label:'<spring:message code="LAB.M09.LAB00058" />',name:'inactDt', width:80, align:"center", formatter:stringTypeFormatterYYYYMMDD}

	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#discPerdListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
	    loadComplete: function(obj){
	    	$("#discPerdListGrid").setGridWidth($("#discPerdListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#discPerdListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$(id).setGridWidth($(divId).width(),false);
	
	publicGridId = id;
	publicDivId = divId;	
}

function selectNode(){
			var ProdTree = $("#tree").dynatree("getTree");
		    var nodeKey = "";
			if ($("#productDevMgtProductListSelectCurrentLvl").val() <= 3) {
				nodeKey	= $("#productDevMgtProductListSelectNode").val();
			} else {
				nodeKey = $("#productDevMgtProductListSelectNodeParent").val();
			}
			
			ProdTree.visit(function(node){
		         if(node.data.key === userSoId){
		             node.activate();
		             //node 실행​
		             node.select();
		         } 
			 });
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
	<ul id="serviceMgtComListUl" class="tabs">
		<li class="active" rel="productDevMgtProductList"><spring:message code="LAB.M07.LAB00153" /></li>
		<li rel="productServiceList"><spring:message code="LAB.M07.LAB00133" /></li>
		<li rel="suprtEquipList"><spring:message code="LAB.M07.LAB00151" /></li>
		<li rel="productAttrList"><spring:message code="LAB.M07.LAB00300" /></li>
		<li rel="productRelationshipList"><spring:message code="LAB.M07.LAB00120" /></li>
		<li rel="rateItmList"><spring:message code="LAB.M01.LAB00149" /></li>
		<li rel="rateItmFctrList"><spring:message code="LAB.M01.LAB00118" /></li>
		<li rel="rateItmAttrList"><spring:message code="LAB.M01.LAB00116" /></li>
		<li rel="rateItmCondList"><spring:message code="LAB.M01.LAB00139" /></li>
		<li rel="allowanceList"><spring:message code="LAB.M01.LAB00130" /></li>
		<li rel="discExclList"><spring:message code="LAB.M14.LAB00031" /></li>	
		<li rel="discSvcRateItmTypList"><spring:message code="LAB.M14.LAB00028" /></li>	
		<li rel="discPerdList"><spring:message code="LAB.M14.LAB00026" /></li>				
	</ul>	
	<div class="tab_container">
		<div id="productDevMgtProductList" class="tab_content">
			<div id='productDevMgtProductListGridDiv'>
				<table id="productDevMgtProductListGrid" class="w100p"></table>
				<div id="productDevMgtProductListGridPager"></div>
			</div>
		</div>
		<!-- #tab1 -->
		<div id="productServiceList" class="tab_content">
			<div id='productServiceListGridDiv'>
				<table id="productServiceListGrid" class="w100p"></table>
				<div id="productServiceListGridPager"></div>
			</div>
		</div>
		<!-- #tab2 -->	
		<div id="suprtEquipList" class="tab_content">
			<div id='suprtEquipListGridDiv'>
				<table id="suprtEquipListGrid" class="w100p"></table>
				<div id="suprtEquipListGridPager"></div>
			</div>
		</div>
		<!-- #tab3 -->			
		<div id="productAttrList" class="tab_content">
			<div id='productAttrListGridDiv'>
				<table id="productAttrListGrid" class="w100p"></table>
				<div id="productAttrListGridPager"></div>
			</div>
		</div>
		<!-- #tab4 -->	
		<div id="productRelationshipList" class="tab_content">
			<div id='productRelationshipListGridDiv'>
				<table id="productRelationshipListGrid" class="w100p"></table>
				<div id="productRelationshipListGridPager"></div>
			</div>
		</div>
		<!-- #tab5 -->		
		<div id="rateItmList" class="tab_content">
			<div id='rateItmListGridDiv'>
				<table id="rateItmListGrid" class="w100p"></table>
				<div id="rateItmListGridPager"></div>
			</div>
		</div>
		<!-- #tab5 -->	
		<div id="rateItmFctrList" class="tab_content">
			<div id='rateItmFctrListGridDiv'>
				<table id="rateItmFctrListGrid" class="w100p"></table>
				<div id="rateItmFctrListGridPager"></div>
			</div>
		</div>
		<!-- #tab6 -->		
		<div id="rateItmAttrList" class="tab_content">
			<div id='rateItmAttrListGridDiv'>
				<table id="rateItmAttrListGrid" class="w100p"></table>
				<div id="rateItmAttrListGridPager"></div>
			</div>
		</div>
		<!-- #tab7 -->	
		<div id="rateItmCondList" class="tab_content">
			<div id='rateItmCondListGridDiv'>
				<table id="rateItmCondListGrid" class="w100p"></table>
				<div id="rateItmCondListGridPager"></div>
			</div>
		</div>
		<!-- #tab8 -->
		<div id="allowanceList" class="tab_content">
			<div id='allowanceListGridDiv'>
				<table id="allowanceListGrid" class="w100p"></table>
				<div id="allowanceListGridPager"></div>
			</div>
		</div>
		<!-- #tab9 -->		
		<div id="discExclList" class="tab_content">
			<div id='discExclListGridDiv'>
				<table id="discExclListGrid" class="w100p"></table>
				<div id="discExclListGridPager"></div>
			</div>
		</div>
		<!-- #tab10 -->
		<div id="discSvcRateItmTypList" class="tab_content">
			<div id='discSvcRateItmTypListGridDiv'>
				<table id="discSvcRateItmTypListGrid" class="w100p"></table>
				<div id="discSvcRateItmTypListGridPager"></div>
			</div>
		</div>
		<!-- #tab11 -->
		<div id="discPerdList" class="tab_content">
			<div id='discPerdListGridDiv'>
				<table id="discPerdListGrid" class="w100p"></table>
				<div id="discPerdListGridPager"></div>
			</div>
		</div>
		<!-- #tab12 -->												
	 </div>
	 </div>   
</html>
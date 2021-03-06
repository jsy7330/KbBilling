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

<link href="/styles/nccbs/styles.css" rel="stylesheet" type="text/css" />
<link href="/styles/nccbs/printPage.css" rel="stylesheet" type="text/css" />
<link href="/styles/nccbs/print.css" rel="stylesheet" type="text/css" media="print">

<script type="text/javascript">
$(document).ready(function() {


	btnDisable("popupEstimate");

	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$( ".date" ).datepicker();
	var date1=new Date();
	date1.setDate( date1.getDate() + 1 );
	reault_y = date1.getFullYear();
	reault_m = eval(date1.getMonth()+1);
	reault_d = date1.getDate();
	if(parseInt(reault_m)<10){reault_m="0"+reault_m;}
	if(parseInt(reault_d)<10){reault_d="0"+reault_d;}

	var date2 = null;	
	date2 = reault_y + "-" + reault_m + "-" + reault_d;	
	$("#StartDt").val(date2);


	//Tree
	$(".ser_left").css("min-height","250px");
	$(".ser_left").css("height","250px");
	$(".ser_left").css("padding", "0px");
	$(".ser_left").css("width", "420px");	

	var tree = $("#tree");	
	var soId = $("#common_soId").val();
	$(tree).dynatree({
 		initAjax: {
			url: "/system/sample/sample/example/getMenuListAction.json?soId="+soId
 		},
		data : {
	        	  soId : $("#common_soId").val()
	    },
		clickFolderMode: 1,
		minExpandLevel: 2,	
		onActivate: function(node) {
			getProdDvlpStatus(node.data.prodList);
		},
		onPostInit: function (isReloading, isError) {
		}
		
	});	

	// TAB 초기화
	$(".tab_content").hide();
	var _thisParent = $('ul.tabs').next('.tab_box');
	var _thisFirst = _thisParent.find('.tab_content:first');
	_thisFirst.show();

	/**
	  * 상품정보 Tab Event
	  */
    $("#popupProdInfoTabs li").click(function () {
		var _this = $(this);
		var _parent = _this.parent('#popupProdInfoTabs');
		var _preTab = _parent.find('li.active').attr("rel");
		var activeTab = $(this).attr("rel");
		if(_preTab == activeTab){
			return;
		}

		_parent.find('li').removeClass('active').css('color','#8b8d90');
		$(this).addClass("active").css('color','#2c7ed9');
        var _targetDiv = _parent.next('.tab_box');
		var _targetC = _targetDiv.find('.tab_content');
		
		_targetC.hide();
        
        _targetDiv.find("#" + activeTab).fadeIn();

        var index = $("#popupProdInfoTabs li").index(this);

		if(index == 0){
			$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfo_basicProdListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 1){
			$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			$("#popupDeviceProdConfListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceConfListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 2){
			$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			$("#popupAddonProdConfListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonConfListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 3){
			// 주소 처리 없음
		}else if(index == 4){
			$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_montlyFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_onetimeFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

			setNegoAmtData(); //협정가 정보 설정
		}
    });
	

	/** 납부 정보 그리드 */
	$("#popupPymListGrid").jqGrid({
		url : '/customer/contract/contract/orderManagement/getPymAcntListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			soId : $("#common_soId").val(),
			custId : $("#common_custId").val()
		},
		colModel: [
		    { label: '<spring:message code="LAB.M02.LAB00006"/>', name: 'pymAcntId', width : 150, align:"center",sortable :false},
		    { label: '<spring:message code="LAB.M02.LAB00008"/>', name: 'acntNm', width : 200, align:"left",sortable :false},
		    { label: '<spring:message code="LAB.M02.LAB00016"/>', name: 'pymMthdNm', width : 150, align:"center",sortable :false}, 
		    { label: '<spring:message code="LAB.M10.LAB00042"/>', name: 'billCyclCdNm',width : 100, align:"center",sortable :false},
		    { label: '<spring:message code="LAB.M08.LAB00119"/>', name: 'emlAsMask',width : 100, align:"left",sortable :false},
		    { label: '<spring:message code="LAB.M14.LAB00076"/>', name: 'mtelNoAsMask',width : 150, align:"left",sortable :false, formatter:telNoFormatter}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 22,
		sortable : false,
		scrollrows : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "pymAcntList"
		},
        onCellSelect : function(rowid, index, contents, event){
        	
        },
       	loadComplete : function (data) {
       		$("#popupPymListGrid").setGridWidth($('#popupPymListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupPymListGrid").setGridParam({
                 datatype:'local'
            });
            $("#popupPymListGrid").trigger("reloadGrid");
        },
    	sortable: { 
    		update: function(permutation) {
    			$("#popupPymListGrid").setGridWidth($('#popupPymListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
    	loadError: function (jqXHR, textStatus, errorThrown) {
            ajaxErrorCallback(jqXHR);
        }
	});

	//기본상품그리드
	$("#popupBasicProdListGrid").jqGrid({
		url : '/customer/contract/contract/orderManagement/getBasicProdListAction.json',
		datatype : 'local',
		mtype: 'POST',
		colModel: [
					{ label: 'soId' , name: 'so_id', hidden:true,width : 0},
					{ label: 'basicProdGrp' , name: 'basic_prod_grp', hidden:true,width : 0},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0},
					{ label: 'basicProdFl' , name: 'basic_prod_fl', hidden:true,width : 0},
					{ label: 'prodPrice' , name: 'prod_price', hidden:true,width : 0, formatter : 'integer'},
					{ label: 'prod_cnt' , name: 'prod_cnt', hidden:true,width : 0, formatter : 'integer'},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: '<spring:message code="LAB.M01.LAB00211"/>', name: 'basic_prod_grp_nm', width : 150, align:"left"},
					{ label: '<spring:message code="LAB.M01.LAB00213"/>', name: 'basic_prod_cd', width : 100, align:"center", key:true},
				    { label: '<spring:message code="LAB.M01.LAB00210"/>', name: 'basic_prod_cd_nm', width : 200, align:"left"},
				    { label: '<spring:message code="LAB.M08.LAB00093"/>', name: 'monthly_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number'},
				    { label: '<spring:message code="LAB.M08.LAB00144"/>', name: 'onetime_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number'},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'is_nego_nm', width : 120, align:"center"}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 138,
		sortable : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "basicProdList"
		},
        onCellSelect : function(rowid, index, contents, event){
        	var data = $("#popupBasicProdListGrid").getRowData(rowid);
        	selectBasicProd(rowid);
        },
        loadComplete : function () {
        	$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfo_basicProdListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupBasicProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	        
	    },
    	sortable: { 
    		update: function(permutation) {
    			$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfo_basicProdListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
    	beforeSelectRow: function(rowid, e) {
    		if ($(this).jqGrid("getGridParam", "selrow") === rowid) {
    			return false;
		    } else {
		    	return true;
	    	}
    	}
	});

	/**
	  * 장비상품그리드
	  */ 
	$("#popupDeviceProdListGrid").jqGrid({
		url : '/customer/contract/contract/orderManagement/getDeviceProdListAction.json',
		datatype : 'local',
		mtype: 'POST',
		colModel: [
					{ label: 'soId' , name: 'so_id', hidden:true,width : 0},
					{ label: 'deviceProdGrp' , name: 'device_prod_grp', hidden:true,width : 0},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0},
					{ label: 'isMandatoryYn' , name: 'is_mandatory_yn', hidden:true,width : 0},
					{ label: 'device_cnt' , name: 'device_cnt', hidden:true,width : 0},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: 'isDupYn' , name: 'is_dup_yn', hidden:true,width : 0, sortable :false},
					{ label: 'dupCnt' , name: 'dup_cnt', hidden:true,width : 0, sortable :false},
				    { label: '<spring:message code="LAB.M09.LAB00228"/>', name: 'device_prod_cd', width : 120, align:"center"},
				    { label: '<spring:message code="LAB.M09.LAB00229"/>', name: 'device_prod_cd_nm', width : 200, align:"left"},
				    { label: '<spring:message code="LAB.M08.LAB00093"/>', name: 'monthly_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number'},
				    { label: '<spring:message code="LAB.M08.LAB00144"/>', name: 'onetime_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number'},
				    { label: '<spring:message code="LAB.M13.LAB00033"/>', name: 'is_mandatory_yn_nm', width : 80, align:"center"}
		],
		viewrecords: true,
		shrinkToFit:false,
		loadonce : true,
		height: 170,
		sortable : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "deviceProdList"
		},
        onCellSelect : function(rowid, index, contents, event){

        },
        loadComplete : function (data) {
        	$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupDeviceProdListGrid").trigger("reloadGrid");
            //필수 항목 이동 처리
            //협정가일 경우, 이동 이후 삭제
            moveDeviceMandatory('popupDeviceProdListGrid', 'popupDeviceProdConfListGrid',11);
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    },
    	sortable: { 
    		update: function(permutation) {
	    		$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

 
	/**
	  * 장비상품설정그리드
	  */ 
	$("#popupDeviceProdConfListGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
					{ label: 'soId' , name: 'so_id', hidden:true,width : 0, sortable :false},
					{ label: 'deviceProdGrp' , name: 'device_prod_grp', hidden:true,width : 0, sortable :false},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0, sortable :false},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0, sortable :false},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0, sortable :false},
					{ label: 'org_monthly_fee' , name: 'org_monthly_fee', hidden:true,width : 0, sortable :false},
					{ label: 'org_onetime_fee' , name: 'org_onetime_fee', hidden:true,width : 0, sortable :false},
					{ label: 'isMandatoryYn' , name: 'is_mandatory_yn', hidden:true,width : 0, sortable :false},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: 'isDupYn' , name: 'is_dup_yn', hidden:true,width : 0, sortable :false},
					{ label: 'dupCnt' , name: 'dup_cnt', hidden:true,width : 0, sortable :false},
					{ label: '<spring:message code="LAB.M09.LAB00228"/>', name: 'device_prod_cd', width : 120, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M09.LAB00229"/>', name: 'device_prod_cd_nm', width : 200, align:"left", sortable :false},
				    { label: '<spring:message code="LAB.M08.LAB00093"/>', name: 'monthly_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number', sortable :false},
				    { label: '<spring:message code="LAB.M08.LAB00144"/>', name: 'onetime_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number', sortable :false},
				    { label: '<spring:message code="LAB.M13.LAB00033"/>' , name: 'is_mandatory_yn_nm', width : 80, align:"center", sortable :false},
					{label:'상세정보등록', name: 'device_prod_cd', width:80, fixed:true,align:'center', formatter:formatOpt1}
		],
		data : '',
		viewrecords: true,
		shrinkToFit:false,
		height: 170,
		sortable : false,
		cellEdit : true,
		loadonce : true,
		rowNum : 10000,
		cellsubmit:'clientArray',
        onCellSelect : function(rowid, index, contents, event){
		//	var rowData = $('#popupDeviceProdConfListGrid').getRowData(rowid);

        },
        loadComplete : function () {
        	$("#popupDeviceProdConfListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceConfListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupDeviceProdConfListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    }
	});
	
	/**
	  * 부가상품그리드
	  */ 
	$("#popupAddonProdListGrid").jqGrid({
		url : '/customer/contract/contract/orderManagement/getAddonProdListAction.json',
		datatype : 'local',
		mtype: 'POST',
		colModel: [
					{ label: 'soId' , name: 'so_id', hidden:true,width : 0},
					{ label: 'addProdGrp' , name: 'add_prod_grp', hidden:true,width : 0},
					{ label: 'bProdList' , name: 'rel_b_prod_list', hidden:true,width : 0},
					{ label: 'xProdList' , name: 'rel_x_prod_list', hidden:true,width : 0},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0},
					{ label: 'basicProdFl' , name: 'basic_prod_fl', hidden:true,width : 0},
					{ label: 'mantatory' , name: 'add_prod_mandatory_yn', hidden:true,width : 0},
					{ label: 'add_cnt' , name: 'add_cnt', hidden:true,width : 0},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: 'isDupYn' , name: 'is_dup_yn', hidden:true,width : 0, sortable :false},
					{ label: 'dupCnt' , name: 'dup_cnt', hidden:true,width : 0, sortable :false},
					{ label: 'add_prod_cd_1' , name: 'add_prod_cd_1', hidden:true,width : 0, sortable :false},
					{ label: '옵션 상품 코드', name: 'add_prod_cd', width : 100, align:"center"},
					{ label: '옵션 상품 명', name: 'add_prod_nm', width : 100, align:"left"},
				    { label: '<spring:message code="LAB.M13.LAB00033"/>', name: 'add_prod_mandatory_yn_nm', width : 80, align:"center"}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 170,
		sortable : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "addProdList"
		},
        onCellSelect : function(rowid, index, contents, event){
        },
        loadComplete : function () {
        	$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupAddonProdListGrid").trigger("reloadGrid");

            //필수 항목 이동 처리
            moveAddMandatory('popupAddonProdListGrid', 'popupAddonProdConfListGrid',13);
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	        
	    },
    	sortable: { update: function(permutation) {
    		$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});


	/**
	  * 부가상품설정그리드
	  */ 
	$("#popupAddonProdConfListGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
				    { label: 'soId' , name: 'so_id', hidden:true,width : 0,sortable :false},
					{ label: 'addProdGrp' , name: 'add_prod_grp', hidden:true,width : 0,sortable :false},
					{ label: 'bProdList' , name: 'rel_b_prod_list', hidden:true,width : 0,sortable :false},
					{ label: 'xProdList' , name: 'rel_x_prod_list', hidden:true,width : 0,sortable :false},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0,sortable :false},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0,sortable :false},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0,sortable :false},
					{ label: 'basicProdFl' , name: 'basic_prod_fl', hidden:true,width : 0,sortable :false},
					{ label: 'org_monthly_fee' , name: 'org_monthly_fee', hidden:true,width : 0, sortable :false},
					{ label: 'org_onetime_fee' , name: 'org_onetime_fee', hidden:true,width : 0, sortable :false},
					{ label: 'mantatory' , name: 'add_prod_mandatory_yn', hidden:true,width : 0, sortable :false},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: 'isDupYn' , name: 'is_dup_yn', hidden:true,width : 0, sortable :false},
					{ label: 'dupCnt' , name: 'dup_cnt', hidden:true,width : 0, sortable :false},
					{ label: 'add_prod_cd_1' , name: 'add_prod_cd_1', hidden:true,width : 0, sortable :false},
					{ label: '옵션 상품 코드', name: 'add_prod_cd', width : 100, align:"center",sortable :false},
					{ label: '옵션 상품 명', name: 'add_prod_nm', width : 100, align:"left",sortable:false},
				    { label: '<spring:message code="LAB.M13.LAB00033"/>', name: 'add_prod_mandatory_yn_nm', width : 80, align:"center",sortable :false},
					{ label:'상세정보등록', name: 'add_prod_cd', width:80, fixed:true,align:'center', formatter:formatOpt1}
		],
		data : '',
		viewrecords: true,
		shrinkToFit:false,
		height: 170,
		sortable : false,
		cellEdit : true,
		loadonce : true,
		rowNum : 10000,
		cellsubmit:'clientArray',
        onCellSelect : function(rowid, index, contents, event){
		//	var rowData = $('#popupAddonProdConfListGrid').getRowData(rowid);

        },
        loadComplete : function () {
            $("#popupAddonProdConfListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonConfListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupAddonProdConfListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	        
	    }
	});


	/**
	  * 월정액설정그리드
	  */ 
	$("#popupMonthlyFeeGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
					{ label: 'soId' , name: 'so_id', hidden:true,width : 0},
					{ label: 'ProdGrp' , name: 'prod_grp', hidden:true,width : 0},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0},
					{ label: 'prodKey' , name: 'prod_key', hidden:true,width : 0, key:true},
					{ label: 'singleKey' , name: 'single_key', hidden:true,width : 0},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: '<spring:message code="LAB.M07.LAB00325"/>', name: 'nego_prod_cd', width : 120, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'nego_prod_cd_nm', width : 180, align:"left", sortable :false},
				    { label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'nego_cnt', width : 70, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M13.LAB00004"/>', name: 'sale_amt', width : 100, formatter : 'integer', align : "right"},
				    { label: '<spring:message code="LAB.M14.LAB00033"/>', name: 'discount_rate', width : 80, align:"center", editable:false, formatter: makeMonthlyRate, sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'nego_amt', width : 100, formatter : 'integer', align : "right", sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00082"/>', name: 'is_nego_nm', width : 120, align:"center", sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 170,
		loadonce : true,
		sortable : false,
		cellEdit : true,
		rowNum : 10000,
		cellsubmit:'clientArray',
        onCellSelect : function(rowid, index, contents, event){
        },
        loadComplete : function () {
        	$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_montlyFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupMonthlyFeeGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    }
	});

	/**
	  * 일회성요금설정그리드
	  */ 
	$("#popupOnetimeChargeGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
					{ label: 'soId' , name: 'so_id', hidden:true,width : 0},
					{ label: 'ProdGrp' , name: 'prod_grp', hidden:true,width : 0},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0},
					{ label: 'prodKey' , name: 'prod_key', hidden:true,width : 0, key:true},
					{ label: 'singleKey' , name: 'single_key', hidden:true,width : 0},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: '<spring:message code="LAB.M07.LAB00325"/>', name: 'nego_prod_cd', width : 120, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'nego_prod_cd_nm', width : 180, align:"left", sortable :false},
				    { label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'nego_cnt', width : 70, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M13.LAB00004"/>', name: 'sale_amt', width : 100, formatter : 'integer', align : "right"},
				    { label: '<spring:message code="LAB.M14.LAB00033"/>', name: 'discount_rate', width : 80, align:"center", editable:false, formatter: makeOnetimeRate, sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'nego_amt', width : 100, formatter : 'integer', align : "right", sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00082"/>', name: 'is_nego_nm', width : 120, align:"center", sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 170,
		loadonce : true,
		sortable : false,
		cellEdit : true,
		rowNum : 10000,
		cellsubmit:'clientArray',
        onCellSelect : function(rowid, index, contents, event){
        },
        loadComplete : function () {
        	$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_onetimeFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupOnetimeChargeGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	        
	    }
	});


	//상품그룹 변경 이벤트
	$('#popupProdInfo_basicProdInfo_prodGrpSel').selectmenu({
        change: function() {
        	//기본상품 정보 Clear
        	initBasicProdInfo();
        	//장비상품 정보 Clear
        	initDeviceProdInfo();
        	//부가서비스 정보 Clear
        	initAddProdInfo();
			//협정가 정보 Clear
        	initNegoAmtInfo();
        	//기본상품조회
        	getBasicProdList();
        }
    });


    //장비상품 추가 이벤트
    $('#popupProdInfo_deviceProdInfo_btnRight').on('click',function (e) {
    	if($("#popupProdInfo_deviceProdInfo_btnRight").hasClass('not-active')){
			return;
		}
		moveDeviceRight('popupDeviceProdListGrid', 'popupDeviceProdConfListGrid');
		
    });

    //장비상품 삭제 이벤트
    $('#popupProdInfo_deviceProdInfo_btnLeft').on('click',function (e) {
    	if($("#popupProdInfo_deviceProdInfo_btnLeft").hasClass('not-active')){
			return;
		}
		var rowid = $('#popupDeviceProdConfListGrid').jqGrid("getGridParam", "selrow");
		var rowData = $('#popupDeviceProdConfListGrid').getRowData(rowid);
		if(rowData.is_mandatory_yn == 'Y'){ //필수 항목 삭제 불가
			alert('<spring:message code="MSG.M13.MSG00028"/>');
			return false;
		}

		deleteRow('popupDeviceProdConfListGrid', rowid);
		var prodKey = rowData.device_prod_cd + rowid;
		//협정가 정보 삭제
		deleteRow('popupMonthlyFeeGrid', prodKey);
		deleteRow('popupOnetimeChargeGrid', prodKey);
    });

    //부가상품 추가 이벤트
    $('#popupProdInfo_addonProdInfo_btnRight').on('click',function (e) {
    	if($("#popupProdInfo_addonProdInfo_btnRight").hasClass('not-active')){
			return;
		}
		moveAddRight('popupAddonProdListGrid', 'popupAddonProdConfListGrid');
		
    });

    //부가상품 삭제 이벤트
    $('#popupProdInfo_addonProdInfo_btnLeft').on('click',function (e) {
    	if($("#popupProdInfo_addonProdInfo_btnLeft").hasClass('not-active')){
			return;
		}
		var rowid = $('#popupAddonProdConfListGrid').jqGrid("getGridParam", "selrow");
		var rowData = $('#popupAddonProdConfListGrid').getRowData(rowid);
		if(rowData.add_prod_mandatory_yn == 'Y'){ //필수 항목 삭제 불가
			alert('<spring:message code="MSG.M13.MSG00028"/>');
			return false;
		}

		deleteRow('popupAddonProdConfListGrid', rowid);
		var prodKey = rowData.add_prod_cd + rowid;
		//협정가 정보 삭제
		deleteRow('popupMonthlyFeeGrid', prodKey);
		deleteRow('popupOnetimeChargeGrid', prodKey);
    });

    //우편번호 키이벤트
	$('#popupProdInfo_instAddrInfo_zipCd').keyup(function(){
	  		if (!(event.keyCode >=37 && event.keyCode<=40)) {
		        var inputVal = $(this).val();
		        $(this).val(inputVal.replace(/[^0-9]/gi,''));
		    }
  			var str = getMaxStr($('#popupProdInfo_instAddrInfo_zipCd').val(), 6);
	  		if(str != $('#popupProdInfo_instAddrInfo_zipCd').val()){
	  			$('#popupProdInfo_instAddrInfo_zipCd').val(str);
	  		}
  		}
	);

	//고객정보의 주소 복사 이벤트
    $('#popupProdInfo_copyCustInfo').on('click',function (e) {
    	if($("#popupProdInfo_copyCustInfo").hasClass('not-active')){
			return;
		}

		//고객정보조회
		var url = '/customer/contract/contract/contractManagement/getCustAddrInfoAction.json';
		$.ajax({
	          url:url,
	          type:'POST',
	          data : {
	        	  soId : $("#common_soId").val(),
	        	  custId : $("#common_custId").val()
	          	},
	          dataType: 'json',
	          success: function(data){
	          	$("#popupProdInfo_instAddrInfo_zipCd").val(data.custInfo.zipCd);
	          	$("#popupProdInfo_instAddrInfo_baseAddr").val(data.custInfo.baseAddr);
	          	$("#popupProdInfo_instAddrInfo_dtlAddr").val(data.custInfo.addrDtl);
	          	//$("#popupProdInfo_instAddrInfo_city").val(data.custInfo.city);
	          	//$("#popupProdInfo_instAddrInfo_stateSel").val(data.custInfo.state);
	          	//$('#popupProdInfo_instAddrInfo_stateSel').selectmenu("refresh");
	          },
	       	beforeSend: function(data){
	       	},
	       	error : function(err){
	       		ajaxErrorCallback(err);
	       	}
	      });
    });

	//기본주소 keyup 이벤트
  	$('#popupProdInfo_instAddrInfo_baseAddr').keyup(function(){
	  		var str = getMaxStr($('#popupProdInfo_instAddrInfo_baseAddr').val(), 200);
	  		if(str != $('#popupProdInfo_instAddrInfo_baseAddr').val()){
	  			$('#popupProdInfo_instAddrInfo_baseAddr').val(str);
	  		}
  		}
	);

	//상세주소 keyup 이벤트
  	$('#popupProdInfo_instAddrInfo_dtlAddr').keyup(function(){
	  		var str = getMaxStr($('#popupProdInfo_instAddrInfo_dtlAddr').val(), 300);
	  		if(str != $('#popupProdInfo_instAddrInfo_dtlAddr').val()){
	  			$('#popupProdInfo_instAddrInfo_dtlAddr').val(str);
	  		}
  		}
	);

	//주소변경 Popup
    $('#popupProdInfo_searchAddrBtn').on('click',function (e) {
        if($("#popupProdInfo_searchAddrBtn").hasClass('not-active')){
        return;
      }
          var url="/system/common/common/postMng/postPopup.ajax";
	      url = url + "?zipCd=popupProdInfo_instAddrInfo_zipCd";           //우편번호
	      url = url + "&baseAddr=popupProdInfo_instAddrInfo_baseAddr"; // 기본주소
	      url = url + "&addrDtl=popupProdInfo_instAddrInfo_dtlAddr"; // 상세주소 
	      url = url + "&mode=o";   //모드 o지정 
	       
	      var width = 795;
	      var height = 500;
	      var LeftPosition=(screen.width-width)/2;
	      var TopPosition=(screen.height-height)/2;
	       
	      window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no, resizeable=no,fullscreen=no");
      }
    );

	//도시 Keyup 이벤트
 //  	$('#popupProdInfo_instAddrInfo_city').keyup(function(){
	//   		var str = getMaxStr($('#popupProdInfo_instAddrInfo_city').val(), 40);
	//   		if(str != $('#popupProdInfo_instAddrInfo_city').val()){
	//   			$('#popupProdInfo_instAddrInfo_city').val(str);
	//   		}
 //  		}
	// );

	/**
	 * 신규화면 초기 데이터 로드
	 */ 
	//getProdGrp();

	/**
	 * 코드 리스트 조회
	 */
	getCodeList();

	$("#popupProdInfo_instAddrInfo_zipCd").addClass('not-active');
	$("#popupProdInfo_instAddrInfo_zipCd").attr('disabled',true);
	$("#popupProdInfo_instAddrInfo_baseAddr").addClass('not-active');
	$("#popupProdInfo_instAddrInfo_baseAddr").attr('disabled',true);

});

/**
  * 오더 화면 Resize
  */
function commonPupupResize(){
	$("#popupPymListGrid").setGridWidth($('#popupPymListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfo_basicProdListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupDeviceProdConfListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceConfListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupAddonProdConfListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonConfListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_montlyFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_onetimeFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	$(".Layer_wrap select").selectmenu();
}

/**
  * 기본상품그룹조회
  */
function getProdGrp(){
	//기본상품그룹 조회
	var url = '/customer/contract/contract/orderManagement/getBasicProdGrpAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        	soId : $('#common_soId').val(),
        	basicSvcCd : $('#common_basicSvcCd').val(),
        	custTp : $('#common_custTp').val()
        },
        dataType: 'json',
        success: function(data){
        	//조회 후 기본 상품그룹 세팅
        	$('#popupProdInfo_basicProdInfo_prodGrpSel').each( function() {
		    	$('#popupProdInfo_basicProdInfo_prodGrpSel option:gt(0)').remove();
		    });

        	$(data.basicProdGrpList).each(function(index, item){
				var str = '<option value="'+ item.prod_grp+'">'+ item.prod_grp_nm+'</option>';
				$('#popupProdInfo_basicProdInfo_prodGrpSel').append(str);  	
		    });

		    $('#popupProdInfo_basicProdInfo_prodGrpSel').val('SEL');
			$('#popupProdInfo_basicProdInfo_prodGrpSel').selectmenu("refresh");
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
}


/**
  * 코드 리스트 조회
  */
function getCodeList(){
	//기본상품그룹 조회
	var url = '/customer/contract/contract/orderManagement/getCodeListAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        },
        dataType: 'json',
        success: function(data){
        	//조회 후 기본 상품그룹 세팅
      //   	$('#popupProdInfo_instAddrInfo_stateSel').each( function() {
		    // 	$('#popupProdInfo_instAddrInfo_stateSel option:gt(0)').remove();
		    // });
		    //기본상품의 사용가능 상품 그룹만 세팅
   //      	$(data.stateCodeList).each(function(index, item){
			// 	var str = '<option value="'+ item.commonCd+'">'+ item.commonCdNm+'</option>';
			// 	$('#popupProdInfo_instAddrInfo_stateSel').append(str);  	
		 //    });
		 //    $('#popupProdInfo_instAddrInfo_stateSel').val('SEL');
			// $('#popupProdInfo_instAddrInfo_stateSel').selectmenu("refresh");

			//진행중 오더의 경우 오더 정보 조회
			getOrderInfoInProgress();
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
}

/**
  * 기본상품정보 초기화
  */
function initBasicProdInfo(){
	// 기본상품그리드 초기화
	$("#popupBasicProdListGrid").clearGridData();
	$("#popupBasicProdListGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupBasicProdListGrid").trigger("reloadGrid");

   	//약정정보 초기화
	$('#popupProdInfo_basicProdInfo_contractPeriodSel').each( function() {
    	$('#popupProdInfo_basicProdInfo_contractPeriodSel option:gt(0)').remove();
    });
    $('#popupProdInfo_basicProdInfo_contractPeriodSel').val('SEL');
	$('#popupProdInfo_basicProdInfo_contractPeriodSel').selectmenu("refresh");

	//기본상품정보초기화
	$(".popupBasicProdInfo").empty();
}

/**
 * 장비상품정보초기화
 */
 function initDeviceProdInfo(){
 	// 장비상품그리드 초기화
	$("#popupDeviceProdListGrid").clearGridData();
	$("#popupDeviceProdListGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupDeviceProdListGrid").trigger("reloadGrid");

   	//장비상품설정그리드 초기화
   	$("#popupDeviceProdConfListGrid").clearGridData();
	$("#popupDeviceProdConfListGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupDeviceProdConfListGrid").trigger("reloadGrid");
 }

 /** 
  * 부가서비스정보초기화
  */
 function initAddProdInfo(){
	// 부가상품그리드 초기화
	$("#popupAddonProdListGrid").clearGridData();
	$("#popupAddonProdListGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupAddonProdListGrid").trigger("reloadGrid");

   	//부가상품설정그리드 초기화
   	$("#popupAddonProdConfListGrid").clearGridData();
	$("#popupAddonProdConfListGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupAddonProdConfListGrid").trigger("reloadGrid");
 }

 /**
  * 협정가정보초기화
  */
 function initNegoAmtInfo(){
 	// 월정액그리드 초기화
	$("#popupMonthlyFeeGrid").clearGridData();
	$("#popupMonthlyFeeGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupMonthlyFeeGrid").trigger("reloadGrid");

   	// 일회성그리드 초기화
   	$("#popupOnetimeChargeGrid").clearGridData();
	$("#popupOnetimeChargeGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupOnetimeChargeGrid").trigger("reloadGrid");
 }

 /**
  * 기본상품조회
  */ 
 function getBasicProdList(){
	// alert($('#common_soId').val());
 	if($('#popupProdInfo_basicProdInfo_prodGrpSel').val() == 'SEL'){
		return;
	}
	$("#popupBasicProdListGrid").clearGridData();
	$("#popupBasicProdListGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			soId : $('#common_soId').val(),
        	basicSvcCd : $('#common_basicSvcCd').val(),
        	basicProdGrp : $('#popupProdInfo_basicProdInfo_prodGrpSel').val(),
        	custTp : $('#common_custTp').val()
		}
	});
	      	
   	$("#popupBasicProdListGrid").trigger("reloadGrid");
 }

 /**
  * 기본상품선택이벤트
  */
 function selectBasicProd(rowid){

 	//장비상품 정보 Clear
	initDeviceProdInfo();
	
	//부가서비스 정보 Clear
	initAddProdInfo();
	
	//협정가 정보 Clear
	initNegoAmtInfo();
	
	//기본상품 정보 조회
	//getBasinProdDetailInfo(rowid);

	//장비상품 조회
	getDeviceProdInfo(rowid);

	//부가상품 조회
	getAddProdInfo(rowid);
 }

 /**
  * 기본상품부가정보조회
  */
 function getBasinProdDetailInfo(rowid){
 	//기본상품 부가정보 초기화
	$('#popupProdInfo_basicProdInfo_contractPeriodSel').val('SEL');
	$('#popupProdInfo_basicProdInfo_contractPeriodSel').selectmenu("refresh");
	//기본상품정보초기화
	$(".popupBasicProdInfo").empty();

	var basicProdInfo = $("#popupProdInfo_basicProdInfo_prodGrpSel").getRowData(rowid);
//	alert(basicProdInfo.prod_grp);

 	//기본상품그룹 조회
	var url = '/customer/contract/contract/orderManagement/getBasinProdDetailInfo.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
  			soId : basicProdInfo.so_id,
        	basicProdGrp : basicProdInfo.basic_prod_grp,
        	basicProdCd : basicProdInfo.basic_prod_cd
        },
        dataType: 'json',
        success: function(data){
        	setBasicProdDetailInfo(data);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
 }

 /**
  * 장비상품조회
  */
function getDeviceProdInfo(rowid){
	var basicProdInfo = $("#popupBasicProdListGrid").getRowData(rowid);

	$("#popupDeviceProdListGrid").clearGridData();
	$("#popupDeviceProdListGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			soId : rowid.split("/")[1],
        	basicProdGrp : rowid.split("/")[2],
        	basicProdCd : rowid.split("/")[0]
		}
	});
	      
   	$("#popupDeviceProdListGrid").trigger("reloadGrid");
}

/**
  * 부가상품조회
  */
function getAddProdInfo(rowid){

	var basicProdInfo = $("#popupBasicProdListGrid").getRowData(rowid);

	$("#popupAddonProdListGrid").clearGridData();
	$("#popupAddonProdListGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			soId : rowid.split("/")[1],
        	basicProdGrp : rowid.split("/")[2],
        	basicProdCd : rowid.split("/")[0],
			StartDt : $("#StartDt").val().replace(/-/g,"")
		}
	});
	      
   	$("#popupAddonProdListGrid").trigger("reloadGrid");
}

/**
 * 장비설정정보 Update
 */
function updateDeviceConfByCnt(value, cnt){
	var rowData = $("#popupDeviceProdConfListGrid").getRowData(value);
	if(cnt == null || cnt =='' || cnt == '0'){
		cnt = 1;	
	}
	var onetime_fee = rowData.org_onetime_fee * cnt;
	var monthly_fee = rowData.org_monthly_fee * cnt;
	$('#popupDeviceProdConfListGrid').jqGrid('setCell', value, "device_cnt" , cnt);
    $('#popupDeviceProdConfListGrid').jqGrid('setCell', value, "onetime_fee" , onetime_fee);
    $('#popupDeviceProdConfListGrid').jqGrid('setCell', value, "monthly_fee" , monthly_fee);
}

/**
 * 부가설정정보 Update
 */
function updateAddonConfByCnt(value, cnt){
	var rowData = $("#popupAddonProdConfListGrid").getRowData(value);
	if(cnt == null || cnt =='' || cnt == '0'){
		cnt = 1;	
	}
	var onetime_fee = rowData.org_onetime_fee * cnt;
	var monthly_fee = rowData.org_monthly_fee * cnt;
	$('#popupAddonProdConfListGrid').jqGrid('setCell', value, "add_cnt" , cnt);
    $('#popupAddonProdConfListGrid').jqGrid('setCell', value, "onetime_fee" , onetime_fee);
    $('#popupAddonProdConfListGrid').jqGrid('setCell', value, "monthly_fee" , monthly_fee);
}

/**
 * 월정액정보 Update
 */
function updateMonthlyConfByRate(value, rate){
	var rowData = $("#popupMonthlyFeeGrid").getRowData(value);

	if(rate == null || rate == ''){
		rate = 0;
	}
	var nego_amt = rowData.sale_amt * (100 - rate) / 100;
    $('#popupMonthlyFeeGrid').jqGrid('setCell', value, "discount_rate" , rate);
    $('#popupMonthlyFeeGrid').jqGrid('setCell', value, "nego_amt" , nego_amt.toFixed(2));
}

/**
 * 일회성요금정보 Update
 */
function updateOnetimeConfByRate(value, rate){
	var rowData = $("#popupOnetimeChargeGrid").getRowData(value);
	if(rate == null || rate == ''){
		rate = 0;
	}
	var nego_amt = rowData.sale_amt * (100 - rate) / 100;
	$('#popupOnetimeChargeGrid').jqGrid('setCell', value, "discount_rate" , rate);
    $('#popupOnetimeChargeGrid').jqGrid('setCell', value, "nego_amt" , nego_amt.toFixed(2));
}

/**
 * 협정가 정보 재설정
 */
function setNegoAmtData(){
	if($('#common_orderStat').val() == '01' || $('#common_orderStat').val() == '02'){
		return;
	}

	//기본상품 협정가 처리
	var basicProdIds = $('#popupBasicProdListGrid').getDataIDs();
	var basicProdRowId = $('#popupBasicProdListGrid').jqGrid("getGridParam", "selrow");
	$.each(basicProdIds, function(index, value){
		if(basicProdRowId == value){
			return true;
		}
		deleteRow('popupOnetimeChargeGrid', value);
		deleteRow('popupMonthlyFeeGrid', value);
	});

	var basicProdRowId = $('#popupBasicProdListGrid').jqGrid("getGridParam", "selrow");
	if(basicProdRowId != null){

		var rowMonthlyData = $('#popupBasicProdListGrid').getRowData(basicProdRowId);
		var preMonthly = $('#popupMonthlyFeeGrid').getInd(basicProdRowId);

		if(preMonthly == false && rowMonthlyData.monthly_fee != null && rowMonthlyData.monthly_fee != '' && rowMonthlyData.monthly_fee != '0'){
			var rowMonthly = new Object();
			rowMonthly.so_id = rowMonthlyData.so_id;
			rowMonthly.prod_grp = rowMonthlyData.basic_prod_grp;
			rowMonthly.svc_grp = rowMonthlyData.svc_grp;
			rowMonthly.svc_cd = rowMonthlyData.svc_cd;
			rowMonthly.prod_typ = rowMonthlyData.prod_typ;
			rowMonthly.prod_key = basicProdRowId;
			rowMonthly.single_key = basicProdRowId;
			rowMonthly.nego_prod_cd = rowMonthlyData.basic_prod_cd;
			rowMonthly.nego_prod_cd_nm = rowMonthlyData.basic_prod_cd_nm;
			rowMonthly.nego_cnt = rowMonthlyData.prod_cnt;
			rowMonthly.sale_amt = rowMonthlyData.monthly_fee;
			rowMonthly.discount_rate = '0';
			rowMonthly.nego_amt = rowMonthlyData.monthly_fee;
			rowMonthly.is_nego = rowMonthlyData.is_nego;
			rowMonthly.is_nego_nm = rowMonthlyData.is_nego_nm;
			var selOrgListCnt = $('#popupMonthlyFeeGrid').getGridParam("records") + 1;
			$('#popupMonthlyFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupMonthlyFeeGrid').addRowData(undefined,rowMonthly);
			$("#popupMonthlyFeeGrid").trigger("reloadGrid");
		}

		var rowOnetimeData = $('#popupBasicProdListGrid').getRowData(basicProdRowId);
		var preOnetime = $('#popupOnetimeChargeGrid').getInd(basicProdRowId);
		if(preOnetime == false && rowOnetimeData.onetime_fee != null && rowOnetimeData.onetime_fee != '' && rowOnetimeData.onetime_fee != '0'){
			var rowOnetime = new Object();
			rowOnetime.so_id = rowOnetimeData.so_id;
			rowOnetime.prod_grp = rowOnetimeData.basic_prod_grp;
			rowOnetime.svc_grp = rowOnetimeData.svc_grp;
			rowOnetime.svc_cd = rowOnetimeData.svc_cd;
			rowOnetime.prod_typ = rowOnetimeData.prod_typ;
			rowOnetime.prod_key = basicProdRowId;
			rowOnetime.single_key = basicProdRowId;
			rowOnetime.nego_prod_cd = rowOnetimeData.basic_prod_cd;
			rowOnetime.nego_prod_cd_nm = rowOnetimeData.basic_prod_cd_nm;
			rowOnetime.nego_cnt = rowOnetimeData.prod_cnt;
			rowOnetime.sale_amt = rowOnetimeData.onetime_fee;
			rowOnetime.discount_rate = '0';
			rowOnetime.nego_amt = rowOnetimeData.onetime_fee;
			rowOnetime.is_nego = rowOnetimeData.is_nego;
			rowOnetime.is_nego_nm = rowOnetimeData.is_nego_nm;
			var selOrgListCnt = $('#popupOnetimeChargeGrid').getGridParam("records") + 1;
			$('#popupOnetimeChargeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupOnetimeChargeGrid').addRowData(undefined,rowOnetime);
			$("#popupOnetimeChargeGrid").trigger("reloadGrid");
		}
	}


	//월정액 단말 처리
	var addDeviceConfRowIds = $('#popupDeviceProdConfListGrid').getDataIDs();
	$.each(addDeviceConfRowIds, function(index, value){
		var rowData = $('#popupDeviceProdConfListGrid').getRowData(value);
		var prodKey = rowData.device_prod_cd + value;

		console.log("******************* 1. 월정액 단말 처리 : " + prodKey + "*******************");	
		//월정액이 아닌 데이터 제외
		if(rowData.monthly_fee == null || rowData.monthly_fee == '' || rowData.monthly_fee == '0'){
			console.log("******************* 2. 월정액 단말 처리 : " + prodKey);	
			return true;
		}
		if(rowData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
			rowData.device_cnt = $('#popupDeviceText_' + value).val();
		}

		if(rowData.device_cnt == null || rowData.device_cnt == '' || rowData.device_cnt == '0'){
			rowData.device_cnt = '1';
		}
		updateDeviceConfByCnt(value, rowData.device_cnt);
		//기존에 존재하는 경우
		var preRow = $('#popupMonthlyFeeGrid').getInd(prodKey);
		if(preRow != false){
			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupMonthlyFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.device_cnt){
				console.log("******************* 3. 월정액 단말 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
				console.log("******************* 4. 월정액 단말 처리 : " + prodKey);	
			    $('#popupMonthlyFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.device_cnt);
			    $('#popupMonthlyFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.monthly_fee);
			    $('#popupMonthlyFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupMonthlyFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.monthly_fee);
			    $("#popupMonthlyFeeGrid").trigger("reloadGrid");
			}
		}else{
			var row = new Object();
			row.so_id = rowData.so_id;
			row.prod_grp = rowData.device_prod_grp;
			row.svc_grp = rowData.svc_grp;
			row.svc_cd = rowData.svc_cd;
			row.prod_typ = rowData.prod_typ;
			row.prod_key = prodKey;
			row.single_key = value;
			row.nego_prod_cd = rowData.device_prod_cd;
			row.nego_prod_cd_nm = rowData.device_prod_cd_nm;
			row.nego_cnt = rowData.device_cnt;
			row.sale_amt = rowData.monthly_fee;
			row.discount_rate = '0';
			row.nego_amt = rowData.monthly_fee;
			row.is_nego = rowData.is_nego;
			row.is_nego_nm = rowData.is_nego_nm;
			var selOrgListCnt = $('#popupMonthlyFeeGrid').getGridParam("records") + 1;
			$('#popupMonthlyFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupMonthlyFeeGrid').addRowData(undefined,row);
			$("#popupMonthlyFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 월정액 단말 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});

	//일회성 단말 처리
	$.each(addDeviceConfRowIds, function(index, value){
		var rowData = $('#popupDeviceProdConfListGrid').getRowData(value);
		var prodKey = rowData.device_prod_cd + value;
		console.log("******************* 1. 일회성 단말 처리 : " + prodKey + "*******************");	

		//월정액이 아닌 데이터 제외
		if(rowData.onetime_fee == null || rowData.onetime_fee == '' || rowData.onetime_fee == '0'){
			console.log("******************* 2. 일회성 단말 처리 : " + prodKey);	
			return true;
		}
		
		if(rowData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
			rowData.device_cnt = $('#popupDeviceText_' + value).val();
		}

		if(rowData.device_cnt == null || rowData.device_cnt == '' || rowData.device_cnt == '0'){
			rowData.device_cnt = '1';
		}
		updateDeviceConfByCnt(index, rowData.device_cnt);
		//기존에 존재하는 경우
		var preRow = $('#popupOnetimeChargeGrid').getInd(prodKey);
		if(preRow != false){

			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupOnetimeChargeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.device_cnt){
				console.log("******************* 3. 일회성 단말 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
			    $('#popupOnetimeChargeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.device_cnt);
			    $('#popupOnetimeChargeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.onetime_fee);
			    $('#popupOnetimeChargeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupOnetimeChargeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.onetime_fee);
			    $("#popupOnetimeChargeGrid").trigger("reloadGrid");
			    console.log("******************* 4. 일회성 단말 처리 : " + prodKey);	
			}
		}else{
			var row = new Object();
			row.so_id = rowData.so_id;
			row.prod_grp = rowData.device_prod_grp;
			row.svc_grp = rowData.svc_grp;
			row.svc_cd = rowData.svc_cd;
			row.prod_typ = rowData.prod_typ;
			row.prod_key = prodKey;
			row.single_key = value;
			row.nego_prod_cd = rowData.device_prod_cd;
			row.nego_prod_cd_nm = rowData.device_prod_cd_nm;
			row.nego_cnt = rowData.device_cnt;
			row.sale_amt = rowData.onetime_fee;
			row.discount_rate = '0';
			row.nego_amt = rowData.onetime_fee;
			row.is_nego = rowData.is_nego;
			row.is_nego_nm = rowData.is_nego_nm;
			var selOrgListCnt = $('#popupOnetimeChargeGrid').getGridParam("records") + 1;
			$('#popupOnetimeChargeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupOnetimeChargeGrid').addRowData(undefined,row);
			$("#popupOnetimeChargeGrid").trigger("reloadGrid");
			console.log("******************* 5. 일회성 단말 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});

	//월정액 부가서비스 처리
	var addAddConfRowIds = $('#popupAddonProdConfListGrid').getDataIDs();
	$.each(addAddConfRowIds, function(index, value){
		var rowData = $('#popupAddonProdConfListGrid').getRowData(value);
		var prodKey = rowData.add_prod_cd + value;

		console.log("******************* 1. 월정액 부가 처리 : " + prodKey + "*******************");	
		//월정액이 아닌 데이터 제외
		if(rowData.monthly_fee == null || rowData.monthly_fee == '' || rowData.monthly_fee == '0'){
			console.log("******************* 2. 월정액 부가 처리 : " + prodKey);	
			return true;
		}
		
		if(rowData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
			rowData.add_cnt = $('#popupAddText_' + value).val();
		}
		if(rowData.add_cnt == null || rowData.add_cnt == '' || rowData.add_cnt == '0'){
			rowData.add_cnt = '1';
		}
		updateAddonConfByCnt(index, rowData.add_cnt);
		//기존에 존재하는 경우
		var preRow = $('#popupMonthlyFeeGrid').getInd(prodKey);
		if(preRow != false){
			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupMonthlyFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.add_cnt){
				console.log("******************* 3. 월정액 부가 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
				console.log("******************* 4. 월정액 부가 처리 : " + prodKey);	
			    $('#popupMonthlyFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.add_cnt);
			    $('#popupMonthlyFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.monthly_fee);
			    $('#popupMonthlyFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupMonthlyFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.monthly_fee);
			    $("#popupMonthlyFeeGrid").trigger("reloadGrid");
			}
		}else{
			var row = new Object();
			row.so_id = rowData.so_id;
			row.prod_grp = rowData.add_prod_grp;
			row.svc_grp = rowData.svc_grp;
			row.svc_cd = rowData.svc_cd;
			row.prod_typ = rowData.prod_typ;
			row.prod_key = prodKey;
			row.single_key = value;
			row.nego_prod_cd = rowData.add_prod_cd;
			row.nego_prod_cd_nm = rowData.add_prod_nm;
			row.nego_cnt = rowData.add_cnt;
			row.sale_amt = rowData.monthly_fee;
			row.discount_rate = '0';
			row.nego_amt = rowData.monthly_fee;
			row.is_nego = rowData.is_nego;
			row.is_nego_nm = rowData.is_nego_nm;
			var selOrgListCnt = $('#popupMonthlyFeeGrid').getGridParam("records") + 1;
			$('#popupMonthlyFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupMonthlyFeeGrid').addRowData(undefined,row);
			$("#popupMonthlyFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 월정액 부가 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});

	//일회성 부가 처리
	$.each(addAddConfRowIds, function(index, value){
		var rowData = $('#popupAddonProdConfListGrid').getRowData(value);
		var prodKey = rowData.add_prod_cd + value;
		console.log("******************* 1. 일회성 부가 처리 : " + prodKey + "*******************");	

		//월정액이 아닌 데이터 제외
		if(rowData.onetime_fee == null || rowData.onetime_fee == '' || rowData.onetime_fee == '0'){
			console.log("******************* 2. 일회성 부가 처리 : " + prodKey);	
			return true;
		}
		
		if(rowData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
			rowData.add_cnt = $('#popupAddText_' + value).val();
		}
		if(rowData.add_cnt == null || rowData.add_cnt == '' || rowData.add_cnt == '0'){
			rowData.add_cnt = '1';
		}
		updateAddonConfByCnt(index, rowData.add_cnt);


		//기존에 존재하는 경우
		var preRow = $('#popupOnetimeChargeGrid').getInd(prodKey);
		if(preRow != false){

			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupOnetimeChargeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.add_cnt){
				console.log("******************* 3. 일회성 부가 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
			    $('#popupOnetimeChargeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.add_cnt);
			    $('#popupOnetimeChargeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.onetime_fee);
			    $('#popupOnetimeChargeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupOnetimeChargeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.onetime_fee);
			    $("#popupOnetimeChargeGrid").trigger("reloadGrid");
			    console.log("******************* 4. 일회성 부가 처리 : " + prodKey);	
			}
		}else{
			var row = new Object();
			row.so_id = rowData.so_id;
			row.prod_grp = rowData.add_prod_grp;
			row.svc_grp = rowData.svc_grp;
			row.svc_cd = rowData.svc_cd;
			row.prod_typ = rowData.prod_typ;
			row.prod_key = prodKey;
			row.single_key = value;
			row.nego_prod_cd = rowData.add_prod_cd;
			row.nego_prod_cd_nm = rowData.add_prod_nm;
			row.nego_cnt = rowData.add_cnt;
			row.sale_amt = rowData.onetime_fee;
			row.discount_rate = '0';
			row.nego_amt = rowData.onetime_fee;
			row.is_nego = rowData.is_nego;
			row.is_nego_nm = rowData.is_nego_nm;
			var selOrgListCnt = $('#popupOnetimeChargeGrid').getGridParam("records") + 1;
			$('#popupOnetimeChargeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupOnetimeChargeGrid').addRowData(undefined,row);
			$("#popupOnetimeChargeGrid").trigger("reloadGrid");
			console.log("******************* 5. 일회성 부가 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});
	
}

/**
 * 신규생성 계약 저장 체크
 */ 
function precheckOrderSave(){
	//협정가 설정 처리
	setNegoAmtData();


	//납부계정선택체크
	var pymRowId = $("#popupPymListGrid").jqGrid("getGridParam", "selrow");
	if(pymRowId == null || pymRowId == undefined){
		alert('<spring:message code="MSG.M02.MSG00001"/>'); //납부계정 정보가 없습니다.
		return false;
	}

	//기본상품선택체크
	//var basicProdRowId = $("#popupBasicProdListGrid").jqGrid("getGridParam", "selrow");
	if($("#popupProdInfo_prodCd").val() == null || $("#popupProdInfo_prodCd").val() == undefined){
	//	$("#popupProdInfo_basicProdInfo").trigger("click");
		alert('<spring:message code="MSG.M01.MSG00059"/>');  //기본상품 정보가 존재하지 않습니다.
		return false;
	}

	//약정여부체크
	var contractSel = $("#popupProdInfo_basicProdInfo_contractPeriodSel").val();
	if(contractSel == 'SEL'){
		$("#popupProdInfo_basicProdInfo").trigger("click");
		$('#popupProdInfo_basicProdInfo_contractPeriodSel-button').focus();
		alert('<spring:message code="MSG.M15.MSG00042"/>');  //약정 기간을 선택해 주세요.
		return false;
	}

	//필수 장비 선택 여부 체크
	var addDeviceDatas = $('#popupDeviceProdListGrid').getRowData();
	var isError = false;
	$.each(addDeviceDatas, function(index, value){
		if(value.is_mandatory_yn != 'Y'){
			return true;
		}
		var isSelectedDevice = false;	
		var addDeviceConfDatas = $('#popupDeviceProdConfListGrid').getRowData();
		$.each(addDeviceConfDatas, function(confIndex, confValue){
			if(value.device_prod_cd == confValue.device_prod_cd){
				isSelectedDevice = true;
				 return false;
			}
		});

		if(!isSelectedDevice){
			$("#popupProdInfo_deviceProdInfo").trigger("click"); 
			alert('<spring:message code="MSG.M13.MSG00022" arguments="' + value.device_prod_cd + ',' + value.device_prod_cd_nm + '"/>'); // 필수 관계 체크 에러입니다.(상품명:{0}({1}))
			isError = true;
			return false;		
		}
	});
	if(isError){
		return false;
	}

	//필수 부가서비스 선택 여부 체크
	var addProdDatas = $('#popupAddonProdListGrid').getRowData();
	$.each(addProdDatas, function(index, value){
		if(value.add_prod_mandatory_yn != 'Y'){
			return true;
		}
		var isSelectedAddon = false;
		var addAddonConfDatas = $('#popupAddonProdConfListGrid').getRowData();
		$.each(addAddonConfDatas, function(confIndex, confValue){
			if(value.add_prod_cd == confValue.add_prod_cd){
				isSelectedAddon = true;
				 return false;
			}
		});

		if(!isSelectedAddon){
			$("#popupProdInfo_addonProdInfo").trigger("click"); 
			alert('<spring:message code="MSG.M13.MSG00022" arguments="' + value.add_prod_cd + ',' + value.add_prod_nm + '"/>');// 필수 관계 체크 에러입니다.(상품명:{0}({1}))
			isError = true;
			return false;		
		}
	});
	if(isError){
		return false;
	}

	//주소 정보 필수 입력 체크
	var zipCd = $("#popupProdInfo_instAddrInfo_zipCd").val();
	if(zipCd == null || zipCd.length == 0){
		$("#popupProdInfo_instAddrInfo").trigger("click");
		$("#popupProdInfo_instAddrInfo_zipCd").focus();
		alert('<spring:message code="MSG.M07.MSG00128"/>');  //설치 주소 정보를 입력해주세요.
		return false;
	}

	//주소 정보 필수 입력 체크
	var baseAddr = $("#popupProdInfo_instAddrInfo_baseAddr").val();
	if(baseAddr == null || baseAddr.length == 0){
		$("#popupProdInfo_instAddrInfo").trigger("click");
		$("#popupProdInfo_instAddrInfo_zipCd").focus();
		alert('<spring:message code="MSG.M07.MSG00128"/>');  //설치 주소 정보를 입력해주세요.
		return false;
	}
	//도시 정보 필수 입력 체크
	// var city = $("#popupProdInfo_instAddrInfo_city").val();
	// if(city == null || city.length == 0){
	// 	$("#popupProdInfo_instAddrInfo").trigger("click");
	// 	$("#popupProdInfo_instAddrInfo_city").focus();
	// 	alert('<spring:message code="MSG.M07.MSG00127"/>');  //설치 주소 정보를 입력해주세요.
	// 	return false;
	// }

	//주 정보 필수 입력 체크
	// var stateSel = $("#popupProdInfo_instAddrInfo_stateSel").val();
	// if(stateSel == 'SEL'){
	// 	$("#popupProdInfo_instAddrInfo").trigger("click");
	// 	$("#popupProdInfo_instAddrInfo_stateSel-button").focus();
	// 	alert('<spring:message code="MSG.M07.MSG00127"/>');  //설치 주소 정보를 입력해주세요.
	// 	return false;
	// }
}



/*
	오더 저장처리
*/
function popupOrderProcessSave(){
	//alert("111");
	var orderRequestInfoVo = new Object();

	//공통영역
	orderRequestInfoVo.soId = $('#common_soId').val();
	orderRequestInfoVo.custId = $('#common_custId').val();
	orderRequestInfoVo.ctrtId = '0000000000';
	orderRequestInfoVo.rcptId = $('#common_rcptId').val();
	orderRequestInfoVo.orderId = $('#common_orderId').val();
	orderRequestInfoVo.orderCd = $('#common_orderCd').val();
	orderRequestInfoVo.orderTp = $('#common_orderTp').val();
	orderRequestInfoVo.orderStat = $('#common_orderStat').val();
	orderRequestInfoVo.cnslMstCl = $('#common_cnslMstCl').val();
	orderRequestInfoVo.cnslMidCl = $('#common_cnslMidCl').val();
	orderRequestInfoVo.cnslSlvCl = $('#common_cnslSlvCl').val();
	orderRequestInfoVo.basicSvcCd = $('#common_basicSvcCd').val();
	orderRequestInfoVo.custTp = $('#common_custTp').val();
	//alert($('#ctrtNm').val());

	orderRequestInfoVo.ctrtNm = $('#ctrtNm').val();

	//납부계정ID
	var pymRowId = $("#popupPymListGrid").jqGrid("getGridParam", "selrow");
	var pymData = $("#popupPymListGrid").getRowData(pymRowId);
	orderRequestInfoVo.pymAcntId = pymData.pymAcntId;

	//기본요금제
	var addBasicProdList = [];
	var basicProdRowId = $("#popupBasicProdListGrid").jqGrid("getGridParam", "selrow");
	var basicProdData = $("#popupBasicProdListGrid").getRowData(basicProdRowId);
	var basicProdInfo = new Object();
	
	
	
	basicProdInfo.prodGrp = $("#popupProdInfo_prodGrp").val();
	basicProdInfo.prodCd = $("#popupProdInfo_prodCd").val();
	basicProdInfo.prodNm = $("#popupProdInfo_prodNm").val();
	basicProdInfo.svcCd =  $("#popupProdInfo_svcCd").val();
	basicProdInfo.svcGrp =  $("#popupProdInfo_svcGrp").val();
	basicProdInfo.prodTyp =  $("#popupProdInfo_prodTyp").val();
	basicProdInfo.basicProdFl=  $("#popupProdInfo_basicProdFl").val();
	basicProdInfo.coreCnt = $("#CORE_CNT").val();
	basicProdInfo.basicCoreCnt=$("#BASIC_CORE_CNT").val();
	basicProdInfo.addCoreCnt = $("#ADD_CORE_CNT").val();
	basicProdInfo.rtId = $("#RT_ID").val();
	basicProdInfo.local = $("#LOCAL").val();
	basicProdInfo.osTyp = $("#OS_TYP").val();
	
	basicProdInfo.item01 = $("#item01").val();
	basicProdInfo.monthlyNegoAmt=$("#MONTHLY_NEGO_AMT").val();
//	basicProdInfo.item02 = $("#item02").val();
//	basicProdInfo.item03 = $("#item03").val();
//	basicProdInfo.item04 = $("#item04").val();
//	basicProdInfo.item05 = $("#item05").val();
//	basicProdInfo.item06 = $("#item06").val();
//	basicProdInfo.item07 = $("#item07").val();
//	basicProdInfo.item08 = $("#item08").val();
//	basicProdInfo.item09 = $("#item09").val();
//	basicProdInfo.item10 = $("#item10").val();
	basicProdInfo.packageCd = $("#PACKAGE_CD").val();

	// 로직제거 (화면입력대로 저장)
	/*
	if($("#SVC_LVL").val() == null || $("#SVC_LVL").val() ==""){
		basicProdInfo.svcLvl = "*";
	}else{
		basicProdInfo.svcLvl = $("#SVC_LVL").val();
	}

	var mNo = $("#M_NO").val();
	var mCd = "";

	if(mNo == 'SEL' || mNo == null || mNo.length == 0){
			mCd = "";
	}else{
			mCd = mNo.substr(2,2);
			basicProdInfo.mCd = mCd;
			basicProdInfo.mNo = mNo;
	}
	basicProdInfo.fixRate = $("#FIX_RATE").val();
	basicProdInfo.useRate = $("#USE_RATE").val();

	var basicMonthlyData = $('#popupMonthlyFeeGrid').getRowData(basicProdData.basic_prod_cd);
	var basicOnetimeData = $('#popupOnetimeChargeGrid').getRowData(basicProdData.basic_prod_cd);
	if(basicProdData.onetime_fee  != null && basicProdData.onetime_fee != 0){
		if(basicOnetimeData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
			basicOnetimeData.discount_rate = $('#popupOnetimeRateText_' + basicProdRowId).val();
		}
		if(basicOnetimeData.discount_rate  == null || basicOnetimeData.discount_rate  == '' || basicOnetimeData.discount_rate  == '0'){
			basicOnetimeData.discount_rate = '0';
		}

		basicProdInfo.onetimeFee = basicOnetimeData.sale_amt;
		basicProdInfo.onetimeFeeNegoRate = basicOnetimeData.discount_rate;	
		basicProdInfo.onetimeFeeNegoAmt = basicOnetimeData.nego_amt;	
		basicProdInfo.onetimeInstMonths = "1";	
	}else{
		basicProdInfo.onetimeFee = "0";
		basicProdInfo.onetimeFeeNegoRate = "0";	
		basicProdInfo.onetimeFeeNegoAmt = "0";	
		basicProdInfo.onetimeInstMonths = "1";	
	}
	if(basicProdData.monthly_fee != null && basicProdData.monthly_fee != 0){
		if(basicMonthlyData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
			basicMonthlyData.discount_rate = $('#popupMonthlyRateText_' + basicProdRowId).val();
		}
		if(basicMonthlyData.discount_rate  == null || basicMonthlyData.discount_rate  == '' || basicMonthlyData.discount_rate  == '0'){
			basicMonthlyData.discount_rate = '0';
		}

		basicProdInfo.monthlyFee = basicMonthlyData.sale_amt;
		basicProdInfo.monthlyFeeNegoRate = basicMonthlyData.discount_rate;	
		basicProdInfo.monthlyFeeNegoAmt = basicMonthlyData.nego_amt;	
	}else{
		basicProdInfo.monthlyFee = "0";
		basicProdInfo.monthlyFeeNegoRate = "0";	
		basicProdInfo.monthlyFeeNegoAmt = "0";	
	}
	alert("=MONTHLY_NEGO_AMT=>"+$("#MONTHLY_NEGO_AMT").val());
	basicProdInfo.monthlyNegoAmt=$("#MONTHLY_NEGO_AMT").val();
	*/
	addBasicProdList[0] = basicProdInfo;
	orderRequestInfoVo.addBasicProdList = addBasicProdList;
	
	//장비상품 정보
	var addDeviceProdList = [];
	var addDeviceConfIds = $('#popupDeviceProdConfListGrid').getDataIDs();
	var deviceIdx = 0;
	$.each(addDeviceConfIds, function(index, value){
		var confValue = $('#popupDeviceProdConfListGrid').getRowData(value);
		var addDeviceInfo = new Object();

		var prodKey = confValue.device_prod_cd + value;
		console.log("DEVICE KEY : " + prodKey);
		
		var monthlyData = $('#popupMonthlyFeeGrid').getRowData(prodKey);
		var onetimeData = $('#popupOnetimeChargeGrid').getRowData(prodKey);
		if(confValue.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
			confValue.device_cnt = $('#popupDeviceText_' + value).val();
		}
		if(confValue.device_cnt == null || confValue.device_cnt == '' || confValue.device_cnt == '0'){
			confValue.device_cnt = '1';
		}

		addDeviceInfo.prodGrp = confValue.device_prod_grp;
		addDeviceInfo.prodCd = confValue.device_prod_cd;
		addDeviceInfo.prodNm = confValue.device_prod_cd_nm;
		addDeviceInfo.svcCd = confValue.svc_cd;
		addDeviceInfo.svcGrp = confValue.svc_grp;
		addDeviceInfo.prodTyp = confValue.prod_typ;
		addDeviceInfo.prodCnt = confValue.device_cnt;

		if(confValue.monthly_fee != null && confValue.monthly_fee != 0){
			if(monthlyData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
				monthlyData.discount_rate = $('#popupMonthlyRateText_' + prodKey).val();
			}
			if(monthlyData.discount_rate  == null || monthlyData.discount_rate  == '' || monthlyData.discount_rate  == '0'){
				monthlyData.discount_rate = '0';
			}
			addDeviceInfo.monthlyFee = monthlyData.sale_amt;
			addDeviceInfo.monthlyFeeNegoRate = monthlyData.discount_rate;	
			addDeviceInfo.monthlyFeeNegoAmt = monthlyData.nego_amt;	
		}else{
			addDeviceInfo.monthlyFee = "0";
			addDeviceInfo.monthlyFeeNegoRate = "0";	
			addDeviceInfo.monthlyFeeNegoAmt = "0";	
		}
		if(confValue.onetime_fee  != null && confValue.onetime_fee != 0){
			if(onetimeData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
				onetimeData.discount_rate = $('#popupOnetimeRateText_' + prodKey).val();
			}
			if(onetimeData.discount_rate  == null || onetimeData.discount_rate  == '' || onetimeData.discount_rate  == '0'){
				onetimeData.discount_rate = '0';
			}
			addDeviceInfo.onetimeFee = onetimeData.sale_amt;
			addDeviceInfo.onetimeFeeNegoRate = onetimeData.discount_rate;	
			addDeviceInfo.onetimeFeeNegoAmt = onetimeData.nego_amt;	
			addDeviceInfo.onetimeInstMonths = "1";	
		}else{
			addDeviceInfo.onetimeFee = "0";
			addDeviceInfo.onetimeFeeNegoRate = "0";	
			addDeviceInfo.onetimeFeeNegoAmt = "0";	
			addDeviceInfo.onetimeInstMonths = "1";	
		}
		addDeviceProdList[deviceIdx++] = addDeviceInfo;
	});
	orderRequestInfoVo.addDeviceProdList = addDeviceProdList;

	//부가상품 정보
	var addAddonProdList = [];
	var addAddConfIds = $('#popupAddonProdConfListGrid').getDataIDs();
	/* alert("addAddConfIds="+addAddConfIds); */
	var addIdx = 0;

/*
	alert($("#CORE_CNT").val());
	alert($("#BASIC_CORE_CNT").val());
	alert($("#ADD_CORE_CNT").val());
	alert($("#RT_ID").val());
	alert($("#LOCAL").val());
	alert($("#OS_TYP").val());
	alert($("#SVC_LVL").val());		
				
return false;
*/				

	$.each(addAddConfIds, function(index, value){
		var confValue = $('#popupAddonProdConfListGrid').getRowData(value);

		var addonProdInfo = new Object();



		var prodKey = confValue.add_prod_cd_1 + value;
		console.log("ADDON KEY : " + prodKey);
/*
//alert($("#"+confValue.add_prod_cd_1+'_RT_ID_A').val());
//alert($("#"+confValue.add_prod_cd_1+'_OS_TYP_A').val());
//alert($("#"+confValue.add_prod_cd_1+'_CNT_A').val());

//alert("confValue.add_prod_grp====>"+confValue.add_prod_grp);
//alert("confValue.add_prod_cd====>"+confValue.add_prod_cd);
//alert("confValue.add_prod_nm====>"+confValue.add_prod_nm);
//alert("confValue.svc_cd====>"+confValue.svc_cd);
return false;
*/
		var monthlyData = $('#popupMonthlyFeeGrid').getRowData(prodKey);
		var onetimeData = $('#popupOnetimeChargeGrid').getRowData(prodKey);

		if(confValue.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
			confValue.add_cnt = $('#popupAddText_' + value).val();
		}
		if(confValue.add_cnt == null || confValue.add_cnt == '' || confValue.add_cnt == '0'){
			confValue.add_cnt = '1';
		}
		
		
		addonProdInfo.prodGrp = confValue.add_prod_grp;
		addonProdInfo.prodCd = confValue.add_prod_cd_1;
		addonProdInfo.prodNm = confValue.add_prod_nm;
		addonProdInfo.svcCd = confValue.svc_cd;
		addonProdInfo.svcGrp = confValue.svc_grp;
		addonProdInfo.prodTyp = confValue.prod_typ;
		addonProdInfo.prodCnt = confValue.add_cnt;

		addonProdInfo.rtId =  $("#RT_ID").val();
		addonProdInfo.osTyp = $("#OS_TYP").val();
		addonProdInfo.packageCd = $("#PACKAGE_CD").val();
		
		addonProdInfo.cnt =$("#"+confValue.add_prod_cd_1+'_CNT_A').val();
		// 로직제거 (화면입력대로 저장)
		
		addonProdInfo.other = $("#"+confValue.add_prod_cd_1+'_OTHER_A').val();
		addonProdInfo.monthlyNegoRate = $("#"+confValue.add_prod_cd_1+'_MONTHLY_NEGO_RATE_A').val();
		addonProdInfo.monthlyNegoAmt = $("#"+confValue.add_prod_cd_1+'_MONTHLY_NEGO_AMT_A').val();
		
		addonProdInfo.item01 = $("#"+confValue.add_prod_cd_1+'_item01_A').val();
		addonProdInfo.item02 = $("#"+confValue.add_prod_cd_1+'_item02_A').val();
		addonProdInfo.item03 = $("#"+confValue.add_prod_cd_1+'_item03_A').val();
		
		addonProdInfo.item04 = $("#"+confValue.add_prod_cd_1+'_item04_A').val();
		addonProdInfo.item05 = $("#"+confValue.add_prod_cd_1+'_item05_A').val();
		addonProdInfo.item06 = $("#"+confValue.add_prod_cd_1+'_item06_A').val();
		addonProdInfo.item07 = $("#"+confValue.add_prod_cd_1+'_item07_A').val();
		addonProdInfo.item08 = $("#"+confValue.add_prod_cd_1+'_item08_A').val();
		addonProdInfo.item09 = $("#"+confValue.add_prod_cd_1+'_item09_A').val();
		addonProdInfo.item10 = $("#"+confValue.add_prod_cd_1+'_item10_A').val();
		
		
/*
		if(confValue.monthly_fee != null && confValue.monthly_fee != 0){
			if(monthlyData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
				monthlyData.discount_rate = $('#popupMonthlyRateText_' + prodKey).val();
			}
			if(monthlyData.discount_rate  == null || monthlyData.discount_rate  == '' || monthlyData.discount_rate  == '0'){
				monthlyData.discount_rate = '0';
			}
			addonProdInfo.monthlyFee = monthlyData.sale_amt;
			addonProdInfo.monthlyFeeNegoRate = monthlyData.discount_rate;	
			addonProdInfo.monthlyFeeNegoAmt = monthlyData.nego_amt;	
		}else{
			addonProdInfo.monthlyFee = "0";
			addonProdInfo.monthlyFeeNegoRate = "0";	
			addonProdInfo.monthlyFeeNegoAmt = "0";	
		}
		if(confValue.onetime_fee  != null && confValue.onetime_fee != 0){
			if(onetimeData.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
				onetimeData.discount_rate = $('#popupOnetimeRateText_' + prodKey).val();
			}
			if(onetimeData.discount_rate  == null || onetimeData.discount_rate  == '' || onetimeData.discount_rate  == '0'){
				onetimeData.discount_rate = '0';
			}
			addonProdInfo.onetimeFee = onetimeData.sale_amt;
			addonProdInfo.onetimeFeeNegoRate = onetimeData.discount_rate;	
			addonProdInfo.onetimeFeeNegoAmt = onetimeData.nego_amt;	
			addonProdInfo.onetimeInstMonths = "1";	
		}else{
			addonProdInfo.onetimeFee = "0";
			addonProdInfo.onetimeFeeNegoRate = "0";	
			addonProdInfo.onetimeFeeNegoAmt = "0";	
			addonProdInfo.onetimeInstMonths = "1";	
		}*/

//		addonProdInfo.item04 = $("#item04").val();
//		addonProdInfo.item05 = $("#item05").val();
//		addonProdInfo.item06 = $("#item06").val();
//		addonProdInfo.item07 = $("#item07").val();
//		addonProdInfo.item08 = $("#item08").val();
//		addonProdInfo.item09 = $("#item09").val();
//		addonProdInfo.item10 = $("#item10").val();
		addAddonProdList[addIdx++] = addonProdInfo;

	});
	orderRequestInfoVo.addAddonProdList = addAddonProdList;

	orderRequestInfoVo.reqUsrId = '${session_user.userId}';
	orderRequestInfoVo.reqOrgId = '${session_user.orgId}';
	orderRequestInfoVo.promCnt = $('#popupProdInfo_basicProdInfo_contractPeriodSel').val();
	orderRequestInfoVo.instZipCode = $('#popupProdInfo_instAddrInfo_zipCd').val();
	orderRequestInfoVo.instBaseAddr = $('#popupProdInfo_instAddrInfo_baseAddr').val();
	orderRequestInfoVo.instAddrDtl = $('#popupProdInfo_instAddrInfo_dtlAddr').val();
	orderRequestInfoVo.instCity = '';
	orderRequestInfoVo.instState = '';
	orderRequestInfoVo.reqDesc = $('#rcptDesc').val();
	
	$.ajax({
		type : "POST",
		url : '/customer/contract/contract/orderManagement/insertOrderAction.json',
		data : JSON.stringify(orderRequestInfoVo),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			btnDisable("popupRcptBtn_save");
			btnEnable("popupRcptBtn_cancel");
			btnEnable("popupRcptBtn_complete");
			btnEnable("popupEstimate");

			$('#common_ctrtId').val(data.orderRequestInfo.ctrtId);
			$('#common_orderId').val(data.orderRequestInfo.orderId);
			$('#common_orderStat').val(data.orderRequestInfo.orderStat);
			$('#common_rcptId').val(data.orderRequestInfo.rcptId);

			$('#popupCommonCtrtId').empty();
			$('#popupCommonRcptId').empty();
			$('#popupCommonOrderStatNm').empty();

			$('#popupCommonCtrtId').append(data.orderRequestInfo.ctrtId);
			$('#popupCommonRcptId').append(data.orderRequestInfo.rcptId);
			$('#popupCommonOrderStatNm').append(data.orderRequestInfo.orderStatNm);

			orderPopup_setDisable();
			alert('<spring:message code="MSG.M07.MSG00084"/>');	 //성공했습니다.
		},		
		beforeSend: function(data){
   		},
		error : function(err){
     		ajaxErrorCallback(err);
     	}
	}); 	
}


/**
 * 오더 완료 처리
 */
function popupOrderProcessCmpl(){
	
	//alert("popupOrderProcessCmpl..");
	
	var orderRequestInfoVo = new Object();

	//공통영역
	orderRequestInfoVo.soId = $('#common_soId').val();
	orderRequestInfoVo.custId = $('#common_custId').val();
	orderRequestInfoVo.ctrtId = $('#common_ctrtId').val();
	orderRequestInfoVo.rcptId = $('#common_rcptId').val();
	orderRequestInfoVo.orderId = $('#common_orderId').val();
	orderRequestInfoVo.orderCd = $('#common_orderCd').val();
	orderRequestInfoVo.orderTp = $('#common_orderTp').val();
	orderRequestInfoVo.orderStat = $('#common_orderStat').val();
	orderRequestInfoVo.cnslMstCl = $('#common_cnslMstCl').val();
	orderRequestInfoVo.cnslMidCl = $('#common_cnslMidCl').val();
	orderRequestInfoVo.cnslSlvCl = $('#common_cnslSlvCl').val();
	orderRequestInfoVo.basicSvcCd = $('#common_basicSvcCd').val();
	orderRequestInfoVo.custTp = $('#common_custTp').val();

	orderRequestInfoVo.reqUsrId = '${session_user.userId}';
	orderRequestInfoVo.reqOrgId = '${session_user.orgId}';
	orderRequestInfoVo.reqDesc = $('#rcptDesc').val();


	$.ajax({
		type : "POST",
		url : '/customer/contract/contract/orderManagement/saveProgressOrderAction.json',
		data : JSON.stringify(orderRequestInfoVo),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			btnDisable("popupRcptBtn_cancel");
			btnDisable("popupRcptBtn_complete");
			$('#common_ctrtId').val(data.orderRequestInfo.ctrtId);
			$('#common_orderId').val(data.orderRequestInfo.orderId);
			$('#common_orderStat').val(data.orderRequestInfo.orderStat);

			$('#popupCommonOrderStatNm').empty();
			$('#popupCommonOrderStatNm').append(data.orderRequestInfo.orderStatNm);
			alert('<spring:message code="MSG.M07.MSG00137"/>');	
		},		
		beforeSend: function(data){
   		},
		error : function(err){
     		ajaxErrorCallback(err);
     	}
	}); 	
}


/**
 * 오더 취소 처리
 */
function popupOrderProcessCancel(){
	var orderRequestInfoVo = new Object();

	//공통영역
	orderRequestInfoVo.soId = $('#common_soId').val();
	orderRequestInfoVo.custId = $('#common_custId').val();
	orderRequestInfoVo.ctrtId = $('#common_ctrtId').val();
	orderRequestInfoVo.rcptId = $('#common_rcptId').val();
	orderRequestInfoVo.orderId = $('#common_orderId').val();
	orderRequestInfoVo.orderCd = $('#common_orderCd').val();
	orderRequestInfoVo.orderTp = $('#common_orderTp').val();
	orderRequestInfoVo.orderStat = $('#common_orderStat').val();
	orderRequestInfoVo.cnslMstCl = $('#common_cnslMstCl').val();
	orderRequestInfoVo.cnslMidCl = $('#common_cnslMidCl').val();
	orderRequestInfoVo.cnslSlvCl = $('#common_cnslSlvCl').val();
	orderRequestInfoVo.basicSvcCd = $('#common_basicSvcCd').val();
	orderRequestInfoVo.custTp = $('#common_custTp').val();

	orderRequestInfoVo.reqUsrId = '${session_user.userId}';
	orderRequestInfoVo.reqOrgId = '${session_user.orgId}';
	orderRequestInfoVo.reqDesc = $('#rcptDesc').val();


	$.ajax({
		type : "POST",
		url : '/customer/contract/contract/orderManagement/saveCancelOrderAction.json',
		data : JSON.stringify(orderRequestInfoVo),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			btnDisable("popupRcptBtn_cancel");
			btnDisable("popupRcptBtn_complete");
			$('#common_ctrtId').val(data.orderRequestInfo.ctrtId);
			$('#common_orderId').val(data.orderRequestInfo.orderId);
			$('#common_orderStat').val(data.orderRequestInfo.orderStat);
			$('#popupCommonOrderStatNm').empty();
			$('#popupCommonOrderStatNm').append(data.orderRequestInfo.orderStatNm);
			alert('<spring:message code="MSG.M07.MSG00084"/>');	
		},		
		beforeSend: function(data){
   		},
		error : function(err){
     		ajaxErrorCallback(err);
     	}

	}); 	
}

// 화면 수정 불가 처리
function orderPopup_setDisable(){

	//납부정보그리드 Disable
	disable_popup_grid('popupPymListGrid');

	//기본상품정보
	$('#popupProdInfo_basicProdInfo_prodGrpSel').selectmenu("disable");
	$('#popupProdInfo_basicProdInfo_contractPeriodSel').selectmenu("disable");
	disable_popup_grid('popupBasicProdListGrid');

	//장비상품정보
	$('#popupDeviceProdListGrid').jqGrid("resetSelection");
	$('#popupDeviceProdConfListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupDeviceProdListGrid');
	disable_popup_grid('popupDeviceProdConfListGrid');
	$("#popupDeviceProdConfListGrid").trigger("reloadGrid");

	//부가상품정보
	$('#popupAddonProdListGrid').jqGrid("resetSelection");
	$('#popupAddonProdConfListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupAddonProdListGrid');
	disable_popup_grid('popupAddonProdConfListGrid');
	$("#popupAddonProdConfListGrid").trigger("reloadGrid");


	//협정가정보
	$('#popupMonthlyFeeGrid').jqGrid("resetSelection");
	$('#popupOnetimeChargeGrid').jqGrid("resetSelection");
	disable_popup_grid('popupMonthlyFeeGrid');
	disable_popup_grid('popupOnetimeChargeGrid');
	$("#popupMonthlyFeeGrid").trigger("reloadGrid");
	$("#popupOnetimeChargeGrid").trigger("reloadGrid");

	//설치주소
	$("#popupProdInfo_instAddrInfo_zipCd").addClass('not-active');
	$("#popupProdInfo_instAddrInfo_zipCd").attr('disabled',true);
	$("#popupProdInfo_instAddrInfo_baseAddr").addClass('not-active');
	$("#popupProdInfo_instAddrInfo_baseAddr").attr('disabled',true);
	$("#popupProdInfo_instAddrInfo_dtlAddr").addClass('not-active');
	$("#popupProdInfo_searchAddrBtn").addClass('not-active');
	$("#popupProdInfo_searchAddrBtn").attr('disabled',true);
	btnDisable('popupProdInfo_copyCustInfo');
	//$("#popupProdInfo_instAddrInfo_city").attr('disabled',true);
	//$('#popupProdInfo_instAddrInfo_stateSel').selectmenu("disable");
}

/**
 * 진행중인 오더 정보 조회
 */
function getOrderInfoInProgress(){
	if($('#common_orderStat').val() != '01' && $('#common_orderStat').val() != '02'){
		return;
	}

	var url = '/customer/contract/contract/orderManagement/getOrderInfoNewContractInProgressAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        	 soId : $('#common_soId').val()
        	,custId : $('#common_custId').val()
        	,ctrtId : $('#common_ctrtId').val()
        	,orderId : $('#common_orderId').val()	
        	,svcCd : $('#common_basicSvcCd').val()
        },
        dataType: 'json',
        success: function(data){
			setOrderInfoInProgress(data);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
}
/**
 * 진행 오더 설정
 */ 
function setOrderInfoInProgress(data){
	var octrtMast = data.octrtMast;
	//납부계정선택
	var pymAcntRowId = findRow($("#popupPymListGrid"), "pymAcntId", octrtMast.pymAcntId, 0);
	$("#popupPymListGrid").setSelection(pymAcntRowId, false);
	
	//기본상품설정
	$('#popupProdInfo_basicProdInfo_prodGrpSel').val(octrtMast.prodGrp);
	$('#popupProdInfo_basicProdInfo_prodGrpSel').selectmenu("refresh");
	$('#popupProdInfo_basicProdInfo_prodGrpSel').trigger('select');
	$("#popupBasicProdListGrid").clearGridData();
	$("#popupBasicProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridBasicProd,
        rowNum: data.gridBasicProd.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupBasicProdListGrid").trigger("reloadGrid");
	var basicProdRowId = findRow($("#popupBasicProdListGrid"), "basic_prod_cd", octrtMast.prodCd, 0);
	$("#popupBasicProdListGrid").setSelection(octrtMast.prodCd, false);
	//기본상품상세정보설정
	setBasicProdDetailInfo(data);

	//설치주소설정
	$("#popupProdInfo_instAddrInfo_zipCd").val(octrtMast.instlZipCd);
	$("#popupProdInfo_instAddrInfo_baseAddr").val(octrtMast.instlBaseAddr);
	$("#popupProdInfo_instAddrInfo_dtlAddr").val(octrtMast.instlAddrDtl);
	//$("#popupProdInfo_instAddrInfo_city").val(octrtMast.instlCity);
	//$("#popupProdInfo_instAddrInfo_stateSel").val(octrtMast.instlState);
	//$('#popupProdInfo_instAddrInfo_stateSel').selectmenu("refresh"); 

	//약정설정
	$('#popupProdInfo_basicProdInfo_contractPeriodSel').val(data.promCnt); 
	$('#popupProdInfo_basicProdInfo_contractPeriodSel').selectmenu("refresh"); 

	//단말상품리스트설정
	$("#popupDeviceProdListGrid").clearGridData();
	$("#popupDeviceProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridDeviceProdList,
        rowNum: data.gridDeviceProdList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupDeviceProdListGrid").trigger("reloadGrid");
	//부가상품리스트설정
	$("#popupAddonProdListGrid").clearGridData();
	$("#popupAddonProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridAddProdList,
        rowNum: data.gridAddProdList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupAddonProdListGrid").trigger("reloadGrid");

	//단말상품설정
	$("#popupDeviceProdConfListGrid").clearGridData();
	$("#popupDeviceProdConfListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridDeviceConfList,
        rowNum : data.gridDeviceConfList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupDeviceProdConfListGrid").trigger("reloadGrid");

	//부가상품설정
	$("#popupAddonProdConfListGrid").clearGridData();
	$("#popupAddonProdConfListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridAddConfList,
        rowNum : data.gridAddConfList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupAddonProdConfListGrid").trigger("reloadGrid");

	//월정액설정
	$("#popupMonthlyFeeGrid").clearGridData();
	$("#popupMonthlyFeeGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridMonthlyFeeList,
        rowNum : data.gridMonthlyFeeList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupMonthlyFeeGrid").trigger("reloadGrid");

	$("#popupOnetimeChargeGrid").clearGridData();
	$("#popupOnetimeChargeGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridOnetimeFeeList,
        rowNum : data.gridOnetimeFeeList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupOnetimeChargeGrid").trigger("reloadGrid");

	orderPopup_setDisable();

	orderPopup_commonBtnSet();

	//버튼제어
	//btnDisable("popupRcptBtn_save");
	//btnEnable("popupRcptBtn_cancel");
	//btnEnable("popupRcptBtn_complete");

}

/**
 * 기본상품 상세 정보 세팅
 */
function setBasicProdDetailInfo(data){

	$('#popupProdInfo_basicProdInfo_contractPeriodSel').each( function() {
    	$('#popupProdInfo_basicProdInfo_contractPeriodSel option:gt(0)').remove();
    });
	$(data.penatyCodeList).each(function(index, item){
		var str = '<option value="'+ item.commonCd+'">'+ item.commonCdNm+'</option>';
		$('#popupProdInfo_basicProdInfo_contractPeriodSel').append(str);  
    });

    $('#popupProdInfo_basicProdInfo_contractPeriodSel').val('SEL');
	$('#popupProdInfo_basicProdInfo_contractPeriodSel').selectmenu("refresh");

	//기본상품정보초기화
	$(".popupBasicProdInfo").empty();

	var lng = '${sessionLanguage}';
	if(lng == 'ko'){
		$('#popupProdInfo_basicProdInfo_penaltyAmt').append(numberAutoFormatter(data.rateInfo_007.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
		$('#popupProdInfo_basicProdInfo_basicAmt').append(numberAutoFormatter(data.rateInfo_008.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
		$('#popupProdInfo_basicProdInfo_instAmt').append(numberAutoFormatter(data.rateInfo_004.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
		$('#popupProdInfo_basicProdInfo_maintenanceAmt').append(numberAutoFormatter(data.rateInfo_009.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
		$('#popupProdInfo_basicProdInfo_rentalFee').append(numberAutoFormatter(data.rateInfo_010.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
	}else{
		$('#popupProdInfo_basicProdInfo_penaltyAmt').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_007.rate_val));
		$('#popupProdInfo_basicProdInfo_basicAmt').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_008.rate_val));
		$('#popupProdInfo_basicProdInfo_instAmt').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_004.rate_val));
		$('#popupProdInfo_basicProdInfo_maintenanceAmt').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_009.rate_val));
		$('#popupProdInfo_basicProdInfo_rentalFee').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_010.rate_val));
	}

}

/**
 * 카운트 Key Down
 */
function countKeyDown(event){
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) {
		return;
	}
	else{
		return false;
	}
}

/**
 * 수량 비어 있을 경우 기본 숫자 세팅
 */
function countFocusOut(event){
	if(event.target.value == '' || event.target.value == '0'){
		$('#' + event.target.id).val("1");
	}
	return;
}

/**
 * 할인율 비어 있을 경우 기본 숫자 세팅
 */
function rateFocusOut(event){
	if(event.target.value == '' || event.target.value == '0'){
		$('#' + event.target.id).val("0");
	}
	return;
}

/**
 * 장비설정 카운트 Text만들기
 */
function makeDeviceConfCnt (cellvalue, options, rowObject) {
	var id = "popupDeviceText_" + options.rowId;
	if($('#common_orderStat').val() != ''){
		return rowObject.device_cnt;
	}
	if(rowObject.is_nego != "1"){
		return rowObject.device_cnt;
	}
	var inputTag = '<input type="text" id="' + id +'" placeholder="' + '<spring:message code="LAB.M07.LAB00247"/>' + '" value="' + cellvalue + '" class="txt_c cntCls w80p" onkeydown="return countKeyDown(event)" onkeyup="deviceConfKeyUp(event)" onfocusout="countFocusOut(event)" maxlength="4"/>';
	return inputTag;
}

/**
 * 부가설정 카운트 Text만들기
 */
function makeAddConfCnt (cellvalue, options, rowObject) {
	var id = "popupAddText_" + options.rowId;

	if($('#common_orderStat').val() != ''){
		return rowObject.add_cnt;
	}
	if(rowObject.is_nego != "1"){
		return rowObject.add_cnt;
	}
	var inputTag = '<input type="text" id="' + id +'" placeholder="' + '<spring:message code="LAB.M07.LAB00247"/>' + '" value="' + cellvalue + '" class="txt_c cntCls w80p" onkeydown="return countKeyDown(event)" onkeyup="addConfKeyUp(event)" onfocusout="countFocusOut(event)" maxlength="4"/>';
	return inputTag;
}

/**
 * 월정액 협정가 Text만들기
 */
function makeMonthlyRate (cellvalue, options, rowObject) {
	var id = "popupMonthlyRateText_" + options.rowId;

	if($('#common_orderStat').val() != ''){
		return rowObject.discount_rate;
	}

	if(rowObject.is_nego != "1"){
		return rowObject.discount_rate;
	}
	var inputTag = '<input type="text" id="' + id +'" placeholder="' + '<spring:message code="LAB.M14.LAB00033"/>' + '" value="' + cellvalue + '" class="txt_c cntCls w80p" onkeydown="return countKeyDown(event)" onkeyup="monthlyRateKeyUp(event)" onfocusout="rateFocusOut(event)" maxlength="3"/>';
	return inputTag;
}

/**
 * 일회성 협정가 Text만들기
 */
function makeOnetimeRate (cellvalue, options, rowObject) {
	var id = "popupOnetimeRateText_" + options.rowId;

	if($('#common_orderStat').val() != ''){
		return rowObject.discount_rate;
	}

	if(rowObject.is_nego != "1"){
		return rowObject.discount_rate;
	}
	var inputTag = '<input type="text" id="' + id +'" placeholder="' + '<spring:message code="LAB.M14.LAB00033"/>' + '" value="' + cellvalue + '" class="txt_c cntCls w80p" onkeydown="return countKeyDown(event)" onkeyup="onetimeRateKeyUp(event)" onfocusout="rateFocusOut(event)" maxlength="3"/>';
	return inputTag;
}

/**
 * 장비 설정 Key Up
 */
function deviceConfKeyUp(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) {
		updateDeviceConfByCnt(event.target.id.replace("popupDeviceText_",""), event.target.value );
		return;
	}
	else{
		event.target.value = Number(event.target.value.replace(/[^0-9]/g, ""));
		updateDeviceConfByCnt(event.target.id.replace("popupDeviceText_",""), event.target.value );
	}
}

/**
 * 부가 설정 Key Up
 */
function addConfKeyUp(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) {
		updateAddonConfByCnt(event.target.id.replace("popupAddText_",""), event.target.value );
		return;
	}
	else{
		event.target.value = Number(event.target.value.replace(/[^0-9]/g, ""));
		updateAddonConfByCnt(event.target.id.replace("popupAddText_",""), event.target.value );
	}
}

/**
 * 월정액 설정 Key Up
 */
function monthlyRateKeyUp(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) {
		if(event.target.value < 0 || event.target.value > 100){
			event.target.value = 0;	
		}
		updateMonthlyConfByRate(event.target.id.replace("popupMonthlyRateText_",""), event.target.value );
		return;
	}
	else{
		event.target.value = Number(event.target.value.replace(/[^0-9]/g, ""));
		if(event.target.value < 0 || event.target.value > 100){
			event.target.value = 0;	
		}
		updateMonthlyConfByRate(event.target.id.replace("popupMonthlyRateText_",""), event.target.value );
	}
}

/**
 * 월정액 설정 Key Up
 */
function onetimeRateKeyUp(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) {
		if(event.target.value < 0 || event.target.value > 100){
			event.target.value = 0;	
		}
		updateOnetimeConfByRate(event.target.id.replace("popupOnetimeRateText_",""), event.target.value );
		return;
	}
	else{
		event.target.value = Number(event.target.value.replace(/[^0-9]/g, ""));
		if(event.target.value < 0 || event.target.value > 100){
			event.target.value = 0;	
		}
		updateOnetimeConfByRate(event.target.id.replace("popupOnetimeRateText_",""), event.target.value );
	}
}

function getinputList(data){
	var inputList = data.prodList;
		/*if(inputList == null){
			alert("Data가 없습니다.");
			return;
		}*/
		var contents = '<colgroup><col style="width: 30%;"><col style="width: 70%;"></colgroup><thead >';

		$('#addInput').empty();
		colspan=0;

	for(i =0; i < inputList.length; i++){
		
		if(inputList[i].colTp =="C"){

			var commonCodeList = inputList[i].commonCodeList

			contents += '<tr>';
			contents +=  '<th class=w' +inputList[i].colSize+' >'+inputList[i].title+'</th>';
			contents +=  '<td >';
			contents +=  '<select class=w' +inputList[i].colSize+' name='+inputList[i].columnId+' id='+inputList[i].columnId+ '>';
			contents +=  '<option value=""><spring:message code="LAB.M15.LAB00002"/></option>';
			for(j = 0; j < commonCodeList.length; j++ ){
							contents +=  '<option value=' + commonCodeList[j].commonCd + ' >' +  commonCodeList[j].commonCdNm  +'</option>';
			}
			contents +=  '</select></td>';
			contents += '</tr>';
		}else if(inputList[i].colTp =="T"){
			contents += '<tr>';
			contents +=  '<th class=w' +inputList[i].colSize+'>'+inputList[i].title+'</th>';
			contents +=  '<td><input type=text name='+ inputList[i].columnId +' id='+inputList[i].columnId+' class=w'+inputList[i].colSize+' /></td>';
			contents += '</tr>';
			
		}
	}
		contents +=  '</thead>';
	onVisible('addInput');
	$('#addInput').append(contents); // 추가기능
 }


 function onVisible(divid) {
	  var strObj = eval(divid);
	  strObj.style.visibility = "visible";
 }
 function disVisible(divid){
	  var strObj = eval(divid);
	  strObj.style.visibility = "hidden";
 }

function getProdDvlpStatus(prodList){
			var param = new Object();
			
			param.StartDt = $("#StartDt").val().replace(/-/g,"");
			param.prodCd = prodList;
			param.basicYn = "B";

			var url = '/system/sample/sample/example/getInputProdList.json';

			$.ajax({
				url:url,
				type:'POST',
				async: false,
				data : param,
				dataType: 'json',
				success : function(data) {
					getinputList(data); // 화면에 Input 값 노출
					getProdInfo(prodList); //기본 상품 정보 값 가져오기
					getPordListAction(prodList);
					//selectBasicProd(prodList);
				},
				beforeSend: function(data){
				},
				error : function(err){
					ajaxErrorCallback(err);     		
				}
			});		
}
	function getPordListAction(prodList){

		var param = new Object();
		param.prodCd = prodList;

		var url = '/system/sample/sample/example/getPordListAction.json';

		$.ajax({
			url:url,
			type:'POST',
			async: false,
			data : param,
			dataType: 'json',

		success : function(data) {
				selectBasicProd(data.prodList[0].prodCd + '/'+ data.prodList[0].soId +'/'+ data.prodList[0].prodGrp );
			},
			beforeSend: function(data){
			},
			error : function(err){
				ajaxErrorCallback(err);     		
			}
		});
}
	$('#popupProdInfo_basicProdInfo_prodGrpSel').selectmenu({
	    change: function() {
			var param = new Object();
			
			param.StartDt = $("#StartDt").val().replace(/-/g,"");
			param.prodCd = $("#popupProdInfo_basicProdInfo_prodGrpSel").val().split("/")[0];

			var url = '/system/sample/sample/example/getInputProdList.json';

			$.ajax({
				url:url,
				type:'POST',
				async: false,
				data : param,
				dataType: 'json',
				success : function(data) {
					getinputList(data); // 화면에 Input 값 노출
					getProdInfo($("#popupProdInfo_basicProdInfo_prodGrpSel").val().split("/")[0]); //기본 상품 정보 값 가져오기
					selectBasicProd($("#popupProdInfo_basicProdInfo_prodGrpSel").val());
				},
				beforeSend: function(data){
				},
				error : function(err){
					ajaxErrorCallback(err);     		
				}
			});		
		}
	});

	$("#popupProdInfo_basicProdInfo_prodGrpSel").selectmenu();

	$(".search").css("margin-top", "3px");
	
	$(".home_wrap").css("min-width", "1340px");
	
 function getinputList2(prodCd){
	var url="/system/sample/sample/example/inputInsertPopUp.ajax";
	var basicYn = "B"; //B:기본 V:부가 E:장비
	$(parent.location).attr("href", "javascript:openModal2('" + url + "','" + prodCd + "','" + basicYn + "');");
 }
 function openModal2(url, add_prod_cd, basicYn) {
	var param = new Object();
	param.prodCd = add_prod_cd;
	param.basicYn = "V";
	param.StartDt = $("#StartDt").val().replace(/-/g,"");
	
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
function formatOpt1(cellvalue, options, rowObject){ 

	var str = "";
	var val1 = "";	
	for(i=0; i<cellvalue.length; i++){
		val1 +=cellvalue[i];
	}	
	val1 = "'"+val1+"'";	
	str += '<a class="grey-btn" href="javascript:getinputList2('+val1+');"><span>추가</span></a>';
	return str;

}
 function getProdInfo(prodCd){
	 
		var url = '/customer/contract/contract/orderManagement/getBasicProdListAction2.json';
		$.ajax({
	          url:url,
	          type:'POST',
	          data : {
	        	  soId : $("#common_soId").val(),
				  prodCd : prodCd
	          	},
	          dataType: 'json',
	          success: function(data){

				$("#popupProdInfo_prodGrp").val(data.basicProdList.basicProdGrp);
				$("#popupProdInfo_prodNm").val(data.basicProdList.basicProdCdNm);
				$("#popupProdInfo_svcCd").val(data.basicProdList.svcCd);
				$("#popupProdInfo_prodTyp").val(data.basicProdList.prodTyp);
				$("#popupProdInfo_basicProdFl").val(data.basicProdList.basicProdFl);
				$("#popupProdInfo_svcGrp").val(data.basicProdList.svcGrp);
				$("#popupProdInfo_prodCd").val(data.basicProdList.basicProdCd);

	          },
	       	beforeSend: function(data){
	       	},
	       	error : function(err){
	       		ajaxErrorCallback(err);
	       	}
	      });
 }
  function estimateView(){
		var url="/system/sample/sample/example/estimatePopUp.ajax";
		var soId = $("#common_soId").val();
		var orderId = $("#common_orderId").val();

//		alert(orderId);
//		alert(soId);


		$(parent.location).attr("href", "javascript:openModal3('" + url + "','" + soId + "','" + orderId + "');");
 }
  function openModal3(url, soId, orderId) {
	var param = new Object();
	param.soId = soId;
	param.orderId = orderId;

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
</script>
<!-- 납부 계정 리스트 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M02.LAB00007"/></h4> <!-- 납부계정리스트 -->  
	</div>
</div>
<div id='popupPymListGridDiv'> <!-- 납부계정리스트 그리드 -->
	<table id='popupPymListGrid' class='w100p'></table>
</div>

<div class='main_btn_box'>
	<div class='fl'>
		<h4 class='sub_title'>견적기준일자 : <input id="StartDt" type='text'/>&nbsp;&nbsp;<a href="javascript:estimateView();">견적서보기</a>
		</a>
		
		</h4> 
	</div>
</div>
<div class='main_btn_box'>
	<div class='fl'>
		<h4 class='sub_title'>HOST NAME : <input id="ctrtNm" name ="ctrtNm" type='text'/></h4> 
	</div>
</div>
<!-- 상품 정보 -->
<div class='main_btn_box'>
	<div class='fl'>
		<h4 class='sub_title'><spring:message code="LAB.M07.LAB00149"/></h4> <!-- 상품 정보 -->
	</div>
</div>
<ul id='popupProdInfoTabs' class="tabs">
	<li id="popupProdInfo_basicProdInfo" class="active" rel="popupProdInfo_basicProdInfoDiv"><spring:message code="LAB.M01.LAB00210"/></li> <!-- 기본상품 -->
	<li id="popupProdInfo_deviceProdInfo" rel="popupProdInfo_deviceProdInfoDiv"><spring:message code="LAB.M09.LAB00042"/></li> <!-- 장비상품 -->
	<li id="popupProdInfo_addonProdInfo" rel="popupProdInfo_addonProdInfoDiv">부가상품</li> <!-- 부가서비스 -->
	<li id="popupProdInfo_instAddrInfo" rel="popupProdInfo_instAddrInfoDiv"><spring:message code="LAB.M07.LAB00301"/></li> <!-- 설치주소 -->
	<li id="popupProdInfo_negoAmtInfo" rel="popupProdInfo_negoAmtInfoDiv" style="display:none"><spring:message code="LAB.M14.LAB00078"/></li> <!-- 협정가 -->
</ul>

<div class="tab_box table_col_box h300"> <!-- 세로 사이즈 지정 -->
	<div class='inpCaution'>
		<span class='dot'>*</span>
		<spring:message code="LAB.M13.LAB00034"/> <!-- 필수입력 -->
	</div>
	
<!-- 기본 상품 시작 -->	
	<div id="popupProdInfo_basicProdInfoDiv" class="tab_content">

		<!-- 기본 상품 Left-->
		<div class='col_left'>
			<!-- Tree -->	
			<div id="tree" style="overflow: auto;" class="ser_left"></div>		
		</div><!--//left-->
		<div class='col_right'>
			<!--<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M07.LAB00307"/></span>
					<select id="popupProdInfo_basicProdInfo_prodGrpSel" class='w300'>
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</select>
				</div>
			</div>-->
			<div id='popupProdInfo_basicProdInfo_basicProdListDiv'>
				<!--<table id="popupBasicProdListGrid" class="w100p"></table>-->
			</div>
				<!--<table class="writeview">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 90%;">
						</colgroup>
					<thead>
					<tr>
						<th>
							상품선택
						</th>	
						<td>
							<select id="popupProdInfo_basicProdInfo_prodGrpSel" name="popupProdInfo_basicProdInfo_prodGrpSel" class="w300">
						</td>
					</tr>
					</thead>
				</table><br>-->
				<div style="height:280px;overflow:auto;white-space:nowrap;">
					<table class="writeview" id="addInput" style = "visibility:hidden"></table>
				</div>
		</div><!--//right-->
	</div>


	<!-- 장비상품 시작 -->
	<div id="popupProdInfo_deviceProdInfoDiv" class="tab_content" >
		<div class='col_left twinTable'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M09.LAB00221"/></span> <!--전체장비상품 --> 
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupProdInfo_deviceProdInfo_deviceListDiv'> <!-- 장비상품그리드 -->
				<table id="popupDeviceProdListGrid" class="w100p"></table>
			</div>
		</div><!--//left-->
		<div class='col_right twinTable'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M09.LAB00222"/></span> <!-- 장비상품설정 -->
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupProdInfo_deviceProdInfo_deviceConfListDiv'> <!-- 장비상품설정그리드 -->
				<table id="popupDeviceProdConfListGrid" class="w100p"></table>
			</div>
		</div><!--//right-->
		<div class='tableBtn'>
			<a class="white-btn" id="popupProdInfo_deviceProdInfo_btnRight" href="#">
				<span class="next_icon"></span>
			</a>
			<a class="white-btn" id="popupProdInfo_deviceProdInfo_btnLeft" href="#">
				<span class="prev_icon"></span>
			</a>
		</div>
		<div class='btn_box cauption'>
			<span class='dot'>*</span><spring:message code="MSG.M15.MSG00040"/> <!-- 전체 장비상품에서 설정하려는 장비 선택 후 '▶'버튼을 클릭해주세요.-->
		</div>
	</div>

	<!-- 부가서비스 시작 -->
	<div id="popupProdInfo_addonProdInfoDiv" class="tab_content" >
		<div class='col_left twinTable'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M09.LAB00224"/></span> <!--전체부가서비스--> 
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupProdInfo_addonProdInfo_addonListDiv'> <!-- 부가상품그리드 -->
				<table id="popupAddonProdListGrid" class="w100p"></table>
			</div>
		</div><!--//left-->
		<div class='col_right twinTable'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M06.LAB00120"/></span> <!-- 부가서비스설정 -->
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupProdInfo_addonProdInfo_addonConfListDiv'> <!-- 부가상품설정그리드 -->
				<table id="popupAddonProdConfListGrid" class="w100p"></table>
			</div>
		</div><!--//right-->
		<div class='tableBtn'>
			<a class="white-btn" id="popupProdInfo_addonProdInfo_btnRight" href="#">
				<span class="next_icon"></span>
			</a>
			<a class="white-btn" id="popupProdInfo_addonProdInfo_btnLeft" href="#">
				<span class="prev_icon"></span>
			</a>
		</div>
		<div class='btn_box cauption'>
			<span class='dot'>*</span><spring:message code="MSG.M09.MSG00064"/> <!-- 전체 부가상품에서 설정하려는 장비 선택 후 '▶'버튼을 클릭해주세요.-->
		</div>
	</div>

	<!-- 설치주소 -->
	<div id="popupProdInfo_instAddrInfoDiv" class="tab_content" >
		<table class='writeview column_2 row_1'>
			<tbody>
				<tr class='col2_left'>
					<th>
						<span class='dot'>*</span><!-- 우편번호 -->
						<spring:message code="LAB.M08.LAB00087"/>
					</th>
					<td>
						<div class="inp_date w100p">
							<input id="popupProdInfo_instAddrInfo_zipCd" type='text' placeholder='<spring:message code="LAB.M08.LAB00087"/>'/>
							<a id="popupProdInfo_searchAddrBtn" href="#" class="search" title='<spring:message code="LAB.M09.LAB00191" />' ></a>
						</div>
					</td>
					<th>
						<span class='dot'>*</span> <!-- 주소 -->
						<spring:message code="LAB.M09.LAB00190"/>
					</th>
					<td>
						<input id="popupProdInfo_instAddrInfo_baseAddr" type='text' placeholder='<spring:message code="LAB.M01.LAB00218"/>' class='w35p'/><!--  기본주소 -->
						<input id="popupProdInfo_instAddrInfo_dtlAddr" type='text' placeholder='<spring:message code="LAB.M07.LAB00102"/>' class='w64p'/> <!-- 상세주소입력 -->
					</td>
				</tr>
				<!-- <tr class='col2_left'>
					<th>
						<span class='dot'>*</span>
						<spring:message code="LAB.M03.LAB00087"/>
					</th>
					<td>
						<input id="popupProdInfo_instAddrInfo_city" type="text"  placeholder='<spring:message code="LAB.M03.LAB00087"/>' class='w100p' /> 
					</td>
					<th>
						<span class='dot'>*</span>
						<spring:message code="LAB.M09.LAB00232"/>
					</th>
					<td>
						<select id="popupProdInfo_instAddrInfo_stateSel" class="w200">
							<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
						</select>
					</td>
				</tr> -->
			</tbody>
		</table>
		<div class="fr mt10">
			<a id="popupProdInfo_copyCustInfo" class="grey-btn" href="#" title='<spring:message code="LAB.M01.LAB00056"/>'><spring:message code="LAB.M01.LAB00056"/></a>
		</div>
		<div class='btn_box cauption'><!-- 모든 입력란에 정확한 설치주소를 입력해주세요. -->
			<span class='dot'>*</span><spring:message code="MSG.M15.MSG00037"/>
		</div>
	</div>

	<!-- 협정가 -->
	<div id="popupProdInfo_negoAmtInfoDiv" class="tab_content" >
		<div class='col_left'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M08.LAB00093"/></span> <!-- 월정액금액 -->
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupProdInfo_negoAmtInfo_montlyFeeDiv'> <!-- 월정액그리드 -->
				<table id="popupMonthlyFeeGrid" class="w100p"></table>
			</div>
		</div><!--//left-->
		<div class='col_right'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M08.LAB00144"/></span> <!-- 일회성금액 -->
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupProdInfo_negoAmtInfo_onetimeFeeDiv'> <!-- 일회성그리드 -->
				<table id="popupOnetimeChargeGrid" class="w100p"></table>
			</div>
		</div><!--//right-->
		<div class='btn_box cauption'>
			<span class='dot'>*</span> <spring:message code="MSG.M15.MSG00038"/> <!-- 할인율 입력 또는 분납개월수 선택을 진행해주세요. -->
		</div>
	</div>
</div>
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:400px;" >
</div>
<div id="popup_dialog2" class="Layer_wrap2" style="display: none;width:500px;" >
</div>

<input id="popupProdInfo_prodGrp" type='hidden'/>
<input id="popupProdInfo_prodNm" type='hidden'/>
<input id="popupProdInfo_svcCd" type='hidden'/>
<input id="popupProdInfo_prodTyp" type='hidden'/>
<input id="popupProdInfo_basicProdFl" type='hidden'/>
<input id="popupProdInfo_svcGrp" type='hidden'/>
<input id="popupProdInfo_prodCd" type='hidden'/>

<input id="PD00000181_RT_ID_A" name ="PD00000181_RT_ID_A" type='hidden'/>
<input id="PD00000181_OS_TYP_A" name ="PD00000181_OS_TYP_A" type='hidden'/>
<input id="PD00000181_CNT_A" name ="PD00000181_CNT_A" type='hidden'/>

<input id="PD00000182_RT_ID_A" name ="PD00000182_RT_ID_A" type='hidden'/>
<input id="PD00000182_OS_TYP_A" name ="PD00000182_OS_TYP_A" type='hidden'/>
<input id="PD00000182_CNT_A" name ="PD00000182_CNT_A" type='hidden'/>

<input id="PD00000183_RT_ID_A" name ="PD00000183_RT_ID_A" type='hidden'/>
<input id="PD00000183_OS_TYP_A" name ="PD00000183_OS_TYP_A" type='hidden'/>
<input id="PD00000183_CNT_A" name ="PD00000183_CNT_A" type='hidden'/>

<input id="PD00000184_RT_ID_A" name ="PD00000184_RT_ID_A" type='hidden'/>
<input id="PD00000184_OS_TYP_A" name ="PD00000184_OS_TYP_A" type='hidden'/>
<input id="PD00000184_CNT_A" name ="PD00000184_CNT_A" type='hidden'/>

<input id="PD00000185_RT_ID_A" name ="PD00000185_RT_ID_A" type='hidden'/>
<input id="PD00000185_OS_TYP_A" name ="PD00000185_OS_TYP_A" type='hidden'/>
<input id="PD00000185_CNT_A" name ="PD00000185_CNT_A" type='hidden'/>

<input id="PD00000186_RT_ID_A" name ="PD00000186_RT_ID_A" type='hidden'/>
<input id="PD00000186_OS_TYP_A" name ="PD00000186_OS_TYP_A" type='hidden'/>
<input id="PD00000186_CNT_A" name ="PD00000186_CNT_A" type='hidden'/>

<input id="PD00000187_RT_ID_A" name ="PD00000187_RT_ID_A" type='hidden'/>
<input id="PD00000187_OS_TYP_A" name ="PD00000187_OS_TYP_A" type='hidden'/>
<input id="PD00000187_CNT_A" name ="PD00000187_CNT_A" type='hidden'/>

<input id="PD00000188_RT_ID_A" name ="PD00000188_RT_ID_A" type='hidden'/>
<input id="PD00000188_OS_TYP_A" name ="PD00000188_OS_TYP_A" type='hidden'/>
<input id="PD00000188_CNT_A" name ="PD00000188_CNT_A" type='hidden'/>

<input id="PD00000189_RT_ID_A" name ="PD00000189_RT_ID_A" type='hidden'/>
<input id="PD00000189_OS_TYP_A" name ="PD00000189_OS_TYP_A" type='hidden'/>
<input id="PD00000189_CNT_A" name ="PD00000189_CNT_A" type='hidden'/>

<input id="PD00000190_RT_ID_A" name ="PD00000190_RT_ID_A" type='hidden'/>
<input id="PD00000190_OS_TYP_A" name ="PD00000190_OS_TYP_A" type='hidden'/>
<input id="PD00000190_CNT_A" name ="PD00000190_CNT_A" type='hidden'/>

<input id="PD00000191_RT_ID_A" name ="PD00000191_RT_ID_A" type='hidden'/>
<input id="PD00000191_OS_TYP_A" name ="PD00000191_OS_TYP_A" type='hidden'/>
<input id="PD00000191_CNT_A" name ="PD00000191_CNT_A" type='hidden'/>

<input id="PD00000192_RT_ID_A" name ="PD00000192_RT_ID_A" type='hidden'/>
<input id="PD00000192_OS_TYP_A" name ="PD00000192_OS_TYP_A" type='hidden'/>
<input id="PD00000192_CNT_A" name ="PD00000192_CNT_A" type='hidden'/>

<input id="PD00000002_CNT_A" name ="PD00000002_CNT_A" type='hidden'/>
<input id="PD00000002_OTHER_A" name ="PD00000002_OTHER_A" type='hidden'/>
<input id="PD00000002_MONTHLY_NEGO_AMT_A" name ="PD00000002_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000002_MONTHLY_NEGO_RATE_A" name ="PD00000002_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000002_item01_A" name ="PD00000002_item01_A" type='hidden'/>
<input id="PD00000002_item02_A" name ="PD00000002_item02_A" type='hidden'/>
<input id="PD00000002_item03_A" name ="PD00000002_item03_A" type='hidden'/>
<input id="PD00000002_item04_A" name ="PD00000002_item04_A" type='hidden'/>
<input id="PD00000002_item05_A" name ="PD00000002_item05_A" type='hidden'/>
<input id="PD00000002_item06_A" name ="PD00000002_item06_A" type='hidden'/>
<input id="PD00000002_item07_A" name ="PD00000002_item07_A" type='hidden'/>
<input id="PD00000002_item08_A" name ="PD00000002_item08_A" type='hidden'/>
<input id="PD00000002_item09_A" name ="PD00000002_item09_A" type='hidden'/>
<input id="PD00000002_item10_A" name ="PD00000002_item10_A" type='hidden'/>

<input id="PD00000003_CNT_A" name ="PD00000003_CNT_A" type='hidden'/>
<input id="PD00000003_OTHER_A" name ="PD00000003_OTHER_A" type='hidden'/>
<input id="PD00000003_MONTHLY_NEGO_AMT_A" name ="PD00000003_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000003_MONTHLY_NEGO_RATE_A" name ="PD00000003_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000003_item01_A" name ="PD00000003_item01_A" type='hidden'/>
<input id="PD00000003_item02_A" name ="PD00000003_item02_A" type='hidden'/>
<input id="PD00000003_item03_A" name ="PD00000003_item03_A" type='hidden'/>
<input id="PD00000003_item04_A" name ="PD00000003_item04_A" type='hidden'/>
<input id="PD00000003_item05_A" name ="PD00000003_item05_A" type='hidden'/>
<input id="PD00000003_item06_A" name ="PD00000003_item06_A" type='hidden'/>
<input id="PD00000003_item07_A" name ="PD00000003_item07_A" type='hidden'/>
<input id="PD00000003_item08_A" name ="PD00000003_item08_A" type='hidden'/>
<input id="PD00000003_item09_A" name ="PD00000003_item09_A" type='hidden'/>
<input id="PD00000003_item10_A" name ="PD00000003_item10_A" type='hidden'/>

<input id="PD00000004_CNT_A" name ="PD00000004_CNT_A" type='hidden'/>
<input id="PD00000004_OTHER_A" name ="PD00000004_OTHER_A" type='hidden'/>
<input id="PD00000004_MONTHLY_NEGO_AMT_A" name ="PD00000004_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000004_MONTHLY_NEGO_RATE_A" name ="PD00000004_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000004_item01_A" name ="PD00000004_item01_A" type='hidden'/>
<input id="PD00000004_item02_A" name ="PD00000004_item02_A" type='hidden'/>
<input id="PD00000004_item03_A" name ="PD00000004_item03_A" type='hidden'/>
<input id="PD00000004_item04_A" name ="PD00000004_item04_A" type='hidden'/>
<input id="PD00000004_item05_A" name ="PD00000004_item05_A" type='hidden'/>
<input id="PD00000004_item06_A" name ="PD00000004_item06_A" type='hidden'/>
<input id="PD00000004_item07_A" name ="PD00000004_item07_A" type='hidden'/>
<input id="PD00000004_item08_A" name ="PD00000004_item08_A" type='hidden'/>
<input id="PD00000004_item09_A" name ="PD00000004_item09_A" type='hidden'/>
<input id="PD00000004_item10_A" name ="PD00000004_item10_A" type='hidden'/>

<input id="PD00000005_CNT_A" name ="PD00000005_CNT_A" type='hidden'/>
<input id="PD00000005_OTHER_A" name ="PD00000005_OTHER_A" type='hidden'/>
<input id="PD00000005_MONTHLY_NEGO_AMT_A" name ="PD00000005_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000005_MONTHLY_NEGO_RATE_A" name ="PD00000005_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000005_item01_A" name ="PD00000005_item01_A" type='hidden'/>
<input id="PD00000005_item02_A" name ="PD00000005_item02_A" type='hidden'/>
<input id="PD00000005_item03_A" name ="PD00000005_item03_A" type='hidden'/>
<input id="PD00000005_item04_A" name ="PD00000005_item04_A" type='hidden'/>
<input id="PD00000005_item05_A" name ="PD00000005_item05_A" type='hidden'/>
<input id="PD00000005_item06_A" name ="PD00000005_item06_A" type='hidden'/>
<input id="PD00000005_item07_A" name ="PD00000005_item07_A" type='hidden'/>
<input id="PD00000005_item08_A" name ="PD00000005_item08_A" type='hidden'/>
<input id="PD00000005_item09_A" name ="PD00000005_item09_A" type='hidden'/>
<input id="PD00000005_item10_A" name ="PD00000005_item10_A" type='hidden'/>

<input id="PD00000006_CNT_A" name ="PD00000006_CNT_A" type='hidden'/>
<input id="PD00000006_OTHER_A" name ="PD00000006_OTHER_A" type='hidden'/>
<input id="PD00000006_MONTHLY_NEGO_AMT_A" name ="PD00000006_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000006_MONTHLY_NEGO_RATE_A" name ="PD00000006_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000006_item01_A" name ="PD00000006_item01_A" type='hidden'/>
<input id="PD00000006_item02_A" name ="PD00000006_item02_A" type='hidden'/>
<input id="PD00000006_item03_A" name ="PD00000006_item03_A" type='hidden'/>
<input id="PD00000006_item04_A" name ="PD00000006_item04_A" type='hidden'/>
<input id="PD00000006_item05_A" name ="PD00000006_item05_A" type='hidden'/>
<input id="PD00000006_item06_A" name ="PD00000006_item06_A" type='hidden'/>
<input id="PD00000006_item07_A" name ="PD00000006_item07_A" type='hidden'/>
<input id="PD00000006_item08_A" name ="PD00000006_item08_A" type='hidden'/>
<input id="PD00000006_item09_A" name ="PD00000006_item09_A" type='hidden'/>
<input id="PD00000006_item10_A" name ="PD00000006_item10_A" type='hidden'/>

<input id="PD00000007_CNT_A" name ="PD00000007_CNT_A" type='hidden'/>
<input id="PD00000007_OTHER_A" name ="PD00000007_OTHER_A" type='hidden'/>
<input id="PD00000007_MONTHLY_NEGO_AMT_A" name ="PD00000007_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000007_MONTHLY_NEGO_RATE_A" name ="PD00000007_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000007_item01_A" name ="PD00000007_item01_A" type='hidden'/>
<input id="PD00000007_item02_A" name ="PD00000007_item02_A" type='hidden'/>
<input id="PD00000007_item03_A" name ="PD00000007_item03_A" type='hidden'/>
<input id="PD00000007_item04_A" name ="PD00000007_item04_A" type='hidden'/>
<input id="PD00000007_item05_A" name ="PD00000007_item05_A" type='hidden'/>
<input id="PD00000007_item06_A" name ="PD00000007_item06_A" type='hidden'/>
<input id="PD00000007_item07_A" name ="PD00000007_item07_A" type='hidden'/>
<input id="PD00000007_item08_A" name ="PD00000007_item08_A" type='hidden'/>
<input id="PD00000007_item09_A" name ="PD00000007_item09_A" type='hidden'/>
<input id="PD00000007_item10_A" name ="PD00000007_item10_A" type='hidden'/>

<input id="PD00000008_CNT_A" name ="PD00000008_CNT_A" type='hidden'/>
<input id="PD00000008_OTHER_A" name ="PD00000008_OTHER_A" type='hidden'/>
<input id="PD00000008_MONTHLY_NEGO_AMT_A" name ="PD00000008_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000008_MONTHLY_NEGO_RATE_A" name ="PD00000008_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000008_item01_A" name ="PD00000008_item01_A" type='hidden'/>
<input id="PD00000008_item02_A" name ="PD00000008_item02_A" type='hidden'/>
<input id="PD00000008_item03_A" name ="PD00000008_item03_A" type='hidden'/>
<input id="PD00000008_item04_A" name ="PD00000008_item04_A" type='hidden'/>
<input id="PD00000008_item05_A" name ="PD00000008_item05_A" type='hidden'/>
<input id="PD00000008_item06_A" name ="PD00000008_item06_A" type='hidden'/>
<input id="PD00000008_item07_A" name ="PD00000008_item07_A" type='hidden'/>
<input id="PD00000008_item08_A" name ="PD00000008_item08_A" type='hidden'/>
<input id="PD00000008_item09_A" name ="PD00000008_item09_A" type='hidden'/>
<input id="PD00000008_item10_A" name ="PD00000008_item10_A" type='hidden'/>

<input id="PD00000009_CNT_A" name ="PD00000009_CNT_A" type='hidden'/>
<input id="PD00000009_OTHER_A" name ="PD00000009_OTHER_A" type='hidden'/>
<input id="PD00000009_MONTHLY_NEGO_AMT_A" name ="PD00000009_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000009_MONTHLY_NEGO_RATE_A" name ="PD00000009_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000009_item01_A" name ="PD00000009_item01_A" type='hidden'/>
<input id="PD00000009_item02_A" name ="PD00000009_item02_A" type='hidden'/>
<input id="PD00000009_item03_A" name ="PD00000009_item03_A" type='hidden'/>
<input id="PD00000009_item04_A" name ="PD00000009_item04_A" type='hidden'/>
<input id="PD00000009_item05_A" name ="PD00000009_item05_A" type='hidden'/>
<input id="PD00000009_item06_A" name ="PD00000009_item06_A" type='hidden'/>
<input id="PD00000009_item07_A" name ="PD00000009_item07_A" type='hidden'/>
<input id="PD00000009_item08_A" name ="PD00000009_item08_A" type='hidden'/>
<input id="PD00000009_item09_A" name ="PD00000009_item09_A" type='hidden'/>
<input id="PD00000009_item10_A" name ="PD00000009_item10_A" type='hidden'/>

<input id="PD00000011_CNT_A" name ="PD00000011_CNT_A" type='hidden'/>
<input id="PD00000011_OTHER_A" name ="PD00000011_OTHER_A" type='hidden'/>
<input id="PD00000011_MONTHLY_NEGO_AMT_A" name ="PD00000011_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000011_MONTHLY_NEGO_RATE_A" name ="PD00000011_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000011_item01_A" name ="PD00000011_item01_A" type='hidden'/>
<input id="PD00000011_item02_A" name ="PD00000011_item02_A" type='hidden'/>
<input id="PD00000011_item03_A" name ="PD00000011_item03_A" type='hidden'/>
<input id="PD00000011_item04_A" name ="PD00000011_item04_A" type='hidden'/>
<input id="PD00000011_item05_A" name ="PD00000011_item05_A" type='hidden'/>
<input id="PD00000011_item06_A" name ="PD00000011_item06_A" type='hidden'/>
<input id="PD00000011_item07_A" name ="PD00000011_item07_A" type='hidden'/>
<input id="PD00000011_item08_A" name ="PD00000011_item08_A" type='hidden'/>
<input id="PD00000011_item09_A" name ="PD00000011_item09_A" type='hidden'/>
<input id="PD00000011_item10_A" name ="PD00000011_item10_A" type='hidden'/>

<input id="PD00000012_CNT_A" name ="PD00000012_CNT_A" type='hidden'/>
<input id="PD00000012_OTHER_A" name ="PD00000012_OTHER_A" type='hidden'/>
<input id="PD00000012_MONTHLY_NEGO_AMT_A" name ="PD00000012_MONTHLY_NEGO_AMT_A" type='hidden'/>
<input id="PD00000012_MONTHLY_NEGO_RATE_A" name ="PD00000012_MONTHLY_NEGO_RATE_A" type='hidden'/>
<input id="PD00000012_item01_A" name ="PD00000012_item01_A" type='hidden'/>
<input id="PD00000012_item02_A" name ="PD00000012_item02_A" type='hidden'/>
<input id="PD00000012_item03_A" name ="PD00000012_item03_A" type='hidden'/>
<input id="PD00000012_item04_A" name ="PD00000012_item04_A" type='hidden'/>
<input id="PD00000012_item05_A" name ="PD00000012_item05_A" type='hidden'/>
<input id="PD00000012_item06_A" name ="PD00000012_item06_A" type='hidden'/>
<input id="PD00000012_item07_A" name ="PD00000012_item07_A" type='hidden'/>
<input id="PD00000012_item08_A" name ="PD00000012_item08_A" type='hidden'/>
<input id="PD00000012_item09_A" name ="PD00000012_item09_A" type='hidden'/>
<input id="PD00000012_item10_A" name ="PD00000012_item10_A" type='hidden'/>

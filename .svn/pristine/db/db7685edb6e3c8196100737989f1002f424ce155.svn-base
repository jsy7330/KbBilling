<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	/**
	  * 장비상품그리드
	  */ 
	$("#popupDeviceProdListGrid").jqGrid({
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
			{ label: '<spring:message code="LAB.M09.LAB00228"/>', name: 'device_prod_cd', width : 120, align:"center", sortable :false},
		    { label: '<spring:message code="LAB.M09.LAB00229"/>', name: 'device_prod_cd_nm', width : 200, align:"left", sortable :false},
		    { label: '<spring:message code="LAB.M09.LAB00111"/>', name: 'item_nm', width : 200, align:"left", sortable :false},
		    { label: '<spring:message code="LAB.M09.LAB00122"/>', name: 'serial_no', width : 100, align:"left", sortable :false},
		    { label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'device_cnt', width : 80, align:"center", sortable :false},
		    { label: '<spring:message code="LAB.M08.LAB00093"/>', name: 'monthly_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number', sortable :false},
		    { label: '<spring:message code="LAB.M08.LAB00144"/>', name: 'onetime_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number', sortable :false},
		    { label: '<spring:message code="LAB.M13.LAB00033"/>' , name: 'is_mandatory_yn_nm', width : 80, align:"center", sortable :false},
		    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'is_nego_nm', width : 120, align:"center", sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		loadonce : true,
		height: 130,
		sortable : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "deviceProdList"
		},
       onCellSelect : function(rowid, index, contents, event){

       },
       loadComplete : function () {
       		$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupDeviceProdListGrid").trigger("reloadGrid");
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
	  * 부가상품그리드
	  */ 
	$("#popupAddonProdListGrid").jqGrid({
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
			{ label: '<spring:message code="LAB.M06.LAB00087"/>', name: 'add_prod_cd', width : 100, align:"center",sortable :false},
			{ label: '<spring:message code="LAB.M06.LAB00085"/>', name: 'add_prod_nm', width : 200, align:"left",sortable:false},
			{ label: '<spring:message code="LAB.M09.LAB00111"/>', name: 'item_nm', width : 200, align:"left", sortable :false},
		    { label: '<spring:message code="LAB.M09.LAB00122"/>', name: 'serial_no', width : 100, align:"left", sortable :false},
			{ label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'add_cnt', width : 80, align:"center", editable:true, sortable :false},
		    { label: '<spring:message code="LAB.M08.LAB00093"/>', name: 'monthly_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number',sortable :false},
		    { label: '<spring:message code="LAB.M08.LAB00144"/>', name: 'onetime_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number',sortable :false},
		    { label: '<spring:message code="LAB.M13.LAB00033"/>', name: 'add_prod_mandatory_yn_nm', width : 80, align:"center",sortable :false},
		    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'is_nego_nm', width : 120, align:"center", sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 130,
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
				    { label: '<spring:message code="LAB.M14.LAB00033"/>', name: 'discount_rate', width : 80, align:"center",sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'nego_amt', width : 100, formatter : 'integer', align : "right", sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 130,
		loadonce : true,
		sortable : false,
		cellEdit : true,
		rowNum : 10000,
		cellsubmit:'clientArray',
		onCellSelect : function(rowid, index, contents, event){
		},
	  	loadComplete : function () {
	  		$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_monthlyFeeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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
				    { label: '<spring:message code="LAB.M14.LAB00033"/>', name: 'discount_rate', width : 80, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'nego_amt', width : 100, formatter : 'integer', align : "right", sortable :false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 130,
		loadonce : true,
		sortable : false,
		cellEdit : true,
		rowNum : 10000,
		cellsubmit:'clientArray',
       	onCellSelect : function(rowid, index, contents, event){
       	},
       	loadComplete : function () {
       		$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_onetimeChargeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
           	$("#popupOnetimeChargeGrid").trigger("reloadGrid");
       	},
       	loadError: function (jqXHR, textStatus, errorThrown) {
        	ajaxErrorCallback(jqXHR);
	        
	    }
	});
	
	/**
	  * 사용상품정보 Tab Event
	  */
  	$("#popupProdInfoTabs li").click(function () {
		var _this = $(this);
		var _parent = _this.parent('#popupProdInfoTabs');
		_parent.find('li').removeClass('active').css('color','#8b8d90');
		$(this).addClass("active").css('color','#2c7ed9');
      	var _targetDiv = _parent.next('.tab_box');
		var _targetC = _targetDiv.find('.tab_content');
		_targetC.hide();
      	var activeTab = $(this).attr("rel");
      	_targetDiv.find("#" + activeTab).fadeIn();

      	var index = $("#popupProdInfoTabs li").index(this);
		if(index == 0){
		}else if(index == 1){
			$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 2){
			$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 3){
			
		}else if(index == 4){
			$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_monthlyFeeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 5){
			$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_onetimeChargeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}
  	});

  	//기본상품그리드
	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").jqGrid({
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
		loadonce : true,
		sortable : false,
		rowNum : 10000,
        onCellSelect : function(rowid, index, contents, event){
        	var data = $("#popupChgProdInfo_basicProdInfo_basicProdListGrid").getRowData(rowid);
        	selectBasicProd(rowid);
        },
        loadComplete : function (data) {
        	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").setGridWidth($('#popupChgProdInfo_basicProdInfo_basicProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfo_basicProdInfo_basicProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	        
	    },
    	sortable: { 
    		update: function(permutation) {
    			$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").setGridWidth($('#popupChgProdInfo_basicProdInfo_basicProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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
	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").jqGrid({
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
					{ label: 'prodCmpsId' , name: 'prod_cmps_id', hidden:true,width : 0, sortable :false},
					{ label: 'changeTp' , name: 'change_tp', hidden:true,width : 0, sortable :false},
					{ label: '<spring:message code="LAB.M01.LAB00262"/>', name: 'change_tp_nm', width : 130, align:"center", sortable :false},
					{ label: '<spring:message code="LAB.M09.LAB00228"/>', name: 'device_prod_cd', width : 100, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M09.LAB00229"/>', name: 'device_prod_cd_nm', width : 200, align:"left", sortable :false},
				    { label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'device_cnt', width : 80, align:"center", editable:false, formatter: makeDeviceConfCnt, sortable :false},
				    { label: '<spring:message code="LAB.M08.LAB00093"/>', name: 'monthly_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number', sortable :false},
				    { label: '<spring:message code="LAB.M08.LAB00144"/>', name: 'onetime_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number', sortable :false},
				    { label: '<spring:message code="LAB.M13.LAB00033"/>' , name: 'is_mandatory_yn_nm', width : 80, align:"center", sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'is_nego_nm', width : 120, align:"center", sortable :false}
		],
		data : '',
		viewrecords: true,
		shrinkToFit:false,
		height: 170,
		sortable : false,
		cellEdit : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "changeDeviceProdList"
		},
		cellsubmit:'clientArray',
        onCellSelect : function(rowid, index, contents, event){

        },
        loadComplete : function (data) {
        	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").setGridWidth($('#popupChgProdInfo_deviceProdInfo_deviceProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    },
    	sortable: { 
    		update: function(permutation) {
	    		$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").setGridWidth($('#popupChgProdInfo_deviceProdInfo_deviceProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});


	/**
	  * 부가상품설정그리드
	  */ 
	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").jqGrid({
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
					{ label: 'prodCmpsId' , name: 'prod_cmps_id', hidden:true,width : 0, sortable :false},
					{ label: 'changeTp' , name: 'change_tp', hidden:true,width : 0, sortable :false},
					{ label: '<spring:message code="LAB.M01.LAB00262"/>', name: 'change_tp_nm', width : 130, align:"center", sortable :false},
					{ label: '<spring:message code="LAB.M06.LAB00087"/>', name: 'add_prod_cd', width : 100, align:"center",sortable :false},
					{ label: '<spring:message code="LAB.M06.LAB00085"/>', name: 'add_prod_nm', width : 200, align:"left",sortable:false},
					{ label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'add_cnt', width : 80, align:"center", editable:false, formatter: makeAddConfCnt, sortable :false},
				    { label: '<spring:message code="LAB.M08.LAB00093"/>', name: 'monthly_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number',sortable :false},
				    { label: '<spring:message code="LAB.M08.LAB00144"/>', name: 'onetime_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number',sortable :false},
				    { label: '<spring:message code="LAB.M13.LAB00033"/>', name: 'add_prod_mandatory_yn_nm', width : 80, align:"center",sortable :false},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'is_nego_nm', width : 120, align:"center", sortable :false}
		],
		data : '',
		viewrecords: true,
		shrinkToFit:false,
		height: 170,
		sortable : false,
		cellEdit : true,
		loadonce : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "changeAddProdList"
		},
		cellsubmit:'clientArray',
        onCellSelect : function(rowid, index, contents, event){
        	
        },
        loadComplete : function () {
        	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").setGridWidth($('#popupChgProdInfo_addonProdInfo_addonProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfo_addonProdInfo_addonProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    }
	});


    /**
	  * 월정액설정그리드
	  */ 
	$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").jqGrid({
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
        	$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").setGridWidth($('#popupChgProdInfo_negoAmtInfo_monthlyFeeGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    }
	});



	/**
	  * 일회성요금설정그리드
	  */ 
	$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").jqGrid({
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
        	$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").setGridWidth($('#popupChgProdInfo_negoAmtInfo_onetimeFeeGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	        
	    }
	});


	//변경이력 Tab 처리
	$("#popupChgProdInfoTabs li").click(function () {
		
		var _this = $(this);
		var _parent = _this.parent('#popupChgProdInfoTabs');
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

        var index = $("#popupChgProdInfoTabs li").index(this);

		if(index==0){
			$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").setGridWidth($('#popupChgProdInfo_basicProdInfo_basicProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 1){
			$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").setGridWidth($('#popupChgProdInfo_deviceProdInfo_deviceProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 2){
			$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").setGridWidth($('#popupChgProdInfo_addonProdInfo_addonProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 3){
			$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").setGridWidth($('#popupChgProdInfo_negoAmtInfo_monthlyFeeGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").setGridWidth($('#popupChgProdInfo_negoAmtInfo_onetimeFeeGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			setNegoAmtData(); //협정가 정보 설정

		}
	});


	// TAB 초기화
	$(".tab_content").hide();
	var _thisParent = $('#popupProdInfoTabs').next('.tab_box');
	var _thisFirst = _thisParent.find('.tab_content:first');
	_thisFirst.show();

	var _chgParent = $('#popupChgProdInfoTabs').next('.tab_box');
	var _chgFirst = _chgParent.find('.tab_content:first');
	_chgParent.show();

	//상품그룹 변경 이벤트
	$('#popupChgProdInfo_basicProdInfo_prodGrpSel').selectmenu({
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

  	getProdInfo();
});


/**
 * 사용중인 상품 조회
 */
function getProdInfo() {

	var url = '/customer/contract/contract/orderManagement/getCtrtInfoAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        	 soId : $('#common_soId').val()
        	,custId : $('#common_custId').val()
        	,ctrtId : $('#common_ctrtId').val()
        },
        dataType: 'json',
        success: function(data){
        	setUsingCtrtInfo(data);
			getProdGrp();
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
	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").clearGridData();
	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").trigger("reloadGrid");

   	//약정정보 초기화
	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').each( function() {
    	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel option:gt(0)').remove();
    });
    $('#popupChgProdInfo_basicProdInfo_contractPeriodSel').val('SEL');
	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').selectmenu("refresh");

	//기본상품정보초기화
	$(".popupChgProdInfoBasicInfo").empty();
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
        	$('#popupChgProdInfo_basicProdInfo_prodGrpSel').each( function() {
		    	$('#popupChgProdInfo_basicProdInfo_prodGrpSel option:gt(0)').remove();
		    });
		    //기본상품의 사용가능 상품 그룹만 세팅
        	$(data.basicProdGrpList).each(function(index, item){
        		var str = '<option value="'+ item.prod_grp+'">'+ item.prod_grp_nm+'</option>';
				$('#popupChgProdInfo_basicProdInfo_prodGrpSel').append(str);  	
		    });
		    $('#popupChgProdInfo_basicProdInfo_prodGrpSel').val('SEL');
			$('#popupChgProdInfo_basicProdInfo_prodGrpSel').selectmenu("refresh");
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
}


/**
 * 장비상품정보초기화
 */
 function initDeviceProdInfo(){
 	// 장비상품그리드 초기화
	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").clearGridData();
	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").trigger("reloadGrid");
 }

 /** 
  * 부가서비스정보초기화
  */
 function initAddProdInfo(){
	// 부가상품그리드 초기화
	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").clearGridData();
	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").trigger("reloadGrid");
 }

 /**
  * 협정가정보초기화
  */
 function initNegoAmtInfo(){
 	// 월정액그리드 초기화
	$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").clearGridData();
	$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").trigger("reloadGrid");

   	// 일회성그리드 초기화
   	$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").clearGridData();
	$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").setGridParam({
		datatype : 'local'
	});
   	$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").trigger("reloadGrid");
 }


/**
  * 기본상품조회
  */ 
 function getBasicProdList(){
 	if($('#popupChgProdInfo_basicProdInfo_prodGrpSel').val() == 'SEL'){
		return;
	}
	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").clearGridData();
	//기본상품그룹 조회
	var url = '/customer/contract/contract/orderManagement/getBasicProdListAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
  			soId : $('#common_soId').val(),
        	basicSvcCd : $('#common_basicSvcCd').val(),
        	basicProdGrp : $('#popupChgProdInfo_basicProdInfo_prodGrpSel').val(),
        	custTp : $('#common_custTp').val()
        },
        dataType: 'json',
        success: function(data){
        	$.each(data.basicProdList, function(index, value){
        		if($('#popupProdInfo_basicProdCd').val() == value.basic_prod_cd){
        			return true;
        		}
        		$('#popupChgProdInfo_basicProdInfo_basicProdListGrid').addRowData(undefined,value);
        	});
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").trigger("reloadGrid");
   	
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
	getBasinProdDetailInfo(rowid);

	//장비상품 조회
	getAddProdInfo(rowid);

	//부가상품 조회
	//getAddProdInfo(rowid);
 }



 /**
  * 상품조회
  */
function getAddProdInfo(rowid){
	var basicProdInfo = $("#popupChgProdInfo_basicProdInfo_basicProdListGrid").getRowData(rowid);

	var url = '/customer/contract/contract/orderManagement/getProdListTobeChangeAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        	 soId : $('#common_soId').val()
        	,custId : $('#common_custId').val()
        	,ctrtId : $('#common_ctrtId').val()
        	,basicProdGrp : basicProdInfo.basic_prod_grp
        	,basicProdCd : basicProdInfo.basic_prod_cd
        },
        dataType: 'json',
        success: function(data){

        	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").clearGridData();
			$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").setGridParam({
				datatype : "jsonstring",
		        datastr: data.changeDeviceProdList,
		        jsonReader: { repeatitems: false }
			});
			$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").trigger("reloadGrid");

			$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").clearGridData();
			$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").setGridParam({
				datatype : "jsonstring",
		        datastr: data.changeAddProdList,
		        jsonReader: { repeatitems: false }
			});
			$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").trigger("reloadGrid");

        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
}



 /**
  * 기본상품부가정보조회
  */
 function getBasinProdDetailInfo(rowid){
 	//기본상품 부가정보 초기화
	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').val('SEL');
	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').selectmenu("refresh");
	//기본상품정보초기화
	$(".popupChgProdInfoBasicInfo").empty();

	var basicProdInfo = $("#popupChgProdInfo_basicProdInfo_basicProdListGrid").getRowData(rowid);

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
 * 사용중인 계약 정보 세팅
 */ 
function setUsingCtrtInfo(data){
	//사용중인 기본상품정보 조회
	$("#popupProdInfo_basicProdInfo_prodGrp").append(data.basicProdInfo.prod_grp_nm);
	$("#popupProdInfo_basicProdInfo_rateStrtDt").append(stringToDateformatYYYYMMDD(data.basicProdInfo.rate_strt_dt));
	$("#popupProdInfo_basicProdInfo_prodNm").append(getNameAndId(data.basicProdInfo.prod_cd , data.basicProdInfo.prod_cd_nm));
	$("#popupProdInfo_basicProdInfo_actDttm").append(stringToDateformatYYYYMMDDHH24MISS(data.basicProdInfo.act_dttm));
	$("#popupProdInfo_basicProdInfo_ctrtStat").append(data.basicProdInfo.ctrt_stat_nm);
	$("#popupProdInfo_basicProdInfo_promPrd").append(data.basicProdInfo.prom_cnt+" "+'<spring:message code="LAB.M01.LAB00016"/>');
	$("#popupProdInfo_basicProdInfo_isNego").val(data.basicProdInfo.is_nego);
	$('#popupProdInfo_basicProdCd').val(data.basicProdInfo.prod_cd);

	//사용중인 장비상품정보 조회
	$("#popupDeviceProdListGrid").setGridParam({
		data: data.deviceInfoList
		,rowNum : data.deviceInfoList.length
	});
	
   	$("#popupDeviceProdListGrid").trigger("reloadGrid");
   	
   	//사용중인 부가상품정보조회
   	$("#popupAddonProdListGrid").setGridParam({
		data: data.addInfoList
		,rowNum : data.addInfoList.length
	});
   	$("#popupAddonProdListGrid").trigger("reloadGrid");
   	
   	//사용중인 월정액 조회
   	$("#popupMonthlyFeeGrid").setGridParam({
		data: data.monthlyFeeList
		,rowNum : data.monthlyFeeList.length
	});
   	$("#popupMonthlyFeeGrid").trigger("reloadGrid");
	
   	//사용중인 일회성 조회
	$("#popupOnetimeChargeGrid").setGridParam({
		data: data.oneTimeFeeList
		,rowNum : data.oneTimeFeeList.length
	});
   	$("#popupOnetimeChargeGrid").trigger("reloadGrid");
   
  	//사용중인 설치주소정보 조회
	$("#popupProdInfo_instAddrInfo_zipCd").append(data.basicProdInfo.instl_zip_cd);
  	
  	var addr = data.basicProdInfo.instl_base_addr;
  	
  	if(data.basicProdInfo.instl_addr_dtl != undefined){
  		addr = addr +" "+data.basicProdInfo.instl_addr_dtl;
  	}
	$("#popupProdInfo_instAddrInfo_addr").append(addr);
	// $("#popupProdInfo_instAddrInfo_city").append(data.basicProdInfo.instl_city);
	// $("#popupProdInfo_instAddrInfo_state").append(data.basicProdInfo.instl_state_nm);
	
	//GRID disable
	$('#popupDeviceProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupDeviceProdListGrid');
	
	$('#popupAddonProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupAddonProdListGrid');
	
	$('#popupMonthlyFeeGrid').jqGrid("resetSelection");
	$('#popupOnetimeChargeGrid').jqGrid("resetSelection");
	disable_popup_grid('popupMonthlyFeeGrid');
	disable_popup_grid('popupOnetimeChargeGrid');

	initBasicProdInfo();

	initDeviceProdInfo();

	initAddProdInfo();

	initNegoAmtInfo();

    getOrderInfoInProgress();
}

/*
 * 명칭 포맷팅
 */
function getNameAndId(id, name){
	if(name == '' || name == null){
		return id;
	}else{
		return name + '(' + id + ')'; 
	}
	 
}


/**
 * 기본상품 상세 정보 세팅
 */
function setBasicProdDetailInfo(data){

	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').each( function() {
    	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel option:gt(0)').remove();
    });
	$(data.penatyCodeList).each(function(index, item){
		var str = '<option value="'+ item.commonCd+'">'+ item.commonCdNm+'</option>';
		$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').append(str);  
    });

    $('#popupChgProdInfo_basicProdInfo_contractPeriodSel').val('SEL');
	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').selectmenu("refresh");

	//기본상품정보초기화
	$(".popupChgProdInfoBasicInfo").empty();

	var lng = '${sessionLanguage}';
	if(lng == 'ko'){
		$('#popupChgProdInfo_basicProdInfo_penaltyAmt').append(numberAutoFormatter(data.rateInfo_007.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
		$('#popupChgProdInfo_basicProdInfo_basicAmt').append(numberAutoFormatter(data.rateInfo_008.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
		$('#popupChgProdInfo_basicProdInfo_instAmt').append(numberAutoFormatter(data.rateInfo_004.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
		$('#popupChgProdInfo_basicProdInfo_maintenanceAmt').append(numberAutoFormatter(data.rateInfo_009.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
		$('#popupChgProdInfo_basicProdInfo_entalFee').append(numberAutoFormatter(data.rateInfo_010.rate_val) + " " + '<spring:message code="LAB.M08.LAB00192"/>');
	}else{
		$('#popupChgProdInfo_basicProdInfo_penaltyAmt').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_007.rate_val));
		$('#popupChgProdInfo_basicProdInfo_basicAmt').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_008.rate_val));
		$('#popupChgProdInfo_basicProdInfo_instAmt').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_004.rate_val));
		$('#popupChgProdInfo_basicProdInfo_maintenanceAmt').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_009.rate_val));
		$('#popupChgProdInfo_basicProdInfo_rentalFee').append('<spring:message code="LAB.M08.LAB00192"/>' + ' ' + numberAutoFormatter(data.rateInfo_010.rate_val));
	}

}



/**
 * 오더 화면 Resize
 */
function commonPupupResize(){
	$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupMonthlfFeeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_montlyFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_onetimeFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리


	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").setGridWidth($('#popupChgProdInfo_basicProdInfo_basicProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").setGridWidth($('#popupChgProdInfo_deviceProdInfo_deviceProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").setGridWidth($('#popupChgProdInfo_addonProdInfo_addonProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	$('#popupChgProdInfo_deviceProdInfo').trigger('click');
	$('#popupChgProdInfo_basicProdInfo').trigger('click');

	$(".Layer_wrap select").selectmenu();
}


/**
 * 협정가 정보 재설정
 */
function setNegoAmtData(){
	if($('#common_orderStat').val() == '01' || $('#common_orderStat').val() == '02'){
		return;
	}


	var basicProdRowId = $('#popupChgProdInfo_basicProdInfo_basicProdListGrid').jqGrid("getGridParam", "selrow");
	if(basicProdRowId != null){

		var rowMonthlyData = $('#popupChgProdInfo_basicProdInfo_basicProdListGrid').getRowData(basicProdRowId);
		var preMonthly = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getInd(basicProdRowId);

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
			var selOrgListCnt = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').addRowData(undefined,rowMonthly);
			$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").trigger("reloadGrid");
		}

		var rowOnetimeData = $('#popupChgProdInfo_basicProdInfo_basicProdListGrid').getRowData(basicProdRowId);
		var preOnetime = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getInd(basicProdRowId);
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
			var selOrgListCnt = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').addRowData(undefined,rowOnetime);
			$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").trigger("reloadGrid");
		}
	}



	//월정액 단말 처리
	var addDeviceConfRowIds = $('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').getDataIDs();
	$.each(addDeviceConfRowIds, function(index, value){
		var rowData = $('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').getRowData(value);
		var prodKey = rowData.device_prod_cd + value;

		console.log("******************* 1. 월정액 단말 처리 : " + prodKey + "*******************");	

		//삭제 상품은 제외
		if(rowData.change_tp == 'D'){
			console.log("******************* 삭제상품 " + prodKey);	
			return true;
		}

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
		var preRow = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getInd(prodKey);
		if(preRow != false){
			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.device_cnt){
				console.log("******************* 3. 월정액 단말 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
				console.log("******************* 4. 월정액 단말 처리 : " + prodKey);	
			    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.device_cnt);
			    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.monthly_fee);
			    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.monthly_fee);
			    $("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").trigger("reloadGrid");
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
			var selOrgListCnt = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').addRowData(undefined,row);
			$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 월정액 단말 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});

	//일회성 단말 처리
	$.each(addDeviceConfRowIds, function(index, value){
		var rowData = $('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').getRowData(value);
		var prodKey = rowData.device_prod_cd + value;
		console.log("******************* 1. 일회성 단말 처리 : " + prodKey + "*******************");	

		//삭제 상품은 제외
		if(rowData.change_tp == 'D'){
			console.log("******************* 삭제상품 " + prodKey);	
			return true;
		}

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
		var preRow = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getInd(prodKey);
		if(preRow != false){

			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.device_cnt){
				console.log("******************* 3. 일회성 단말 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
			    $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.device_cnt);
			    $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.onetime_fee);
			    $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.onetime_fee);
			    $("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").trigger("reloadGrid");
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
			var selOrgListCnt = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').addRowData(undefined,row);
			$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 일회성 단말 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});





	//월정액 부가서비스 처리
	var addAddConfRowIds = $('#popupChgProdInfo_addonProdInfo_addonProdListGrid').getDataIDs();
	$.each(addAddConfRowIds, function(index, value){
		var rowData = $('#popupChgProdInfo_addonProdInfo_addonProdListGrid').getRowData(value);
		var prodKey = rowData.add_prod_cd + value;

		console.log("******************* 1. 월정액 부가 처리 : " + prodKey + "*******************");	
		//삭제 상품은 제외
		if(rowData.change_tp == 'D'){
			console.log("******************* 삭제상품 " + prodKey);	
			return true;
		}

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
		var preRow = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getInd(prodKey);
		if(preRow != false){
			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.add_cnt){
				console.log("******************* 3. 월정액 부가 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
				console.log("******************* 4. 월정액 부가 처리 : " + prodKey);	
			    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.add_cnt);
			    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.monthly_fee);
			    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.monthly_fee);
			    $("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").trigger("reloadGrid");
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
			var selOrgListCnt = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').addRowData(undefined,row);
			$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 월정액 부가 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});

	//일회성 부가 처리
	$.each(addAddConfRowIds, function(index, value){
		var rowData = $('#popupChgProdInfo_addonProdInfo_addonProdListGrid').getRowData(value);
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
		var preRow = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getInd(prodKey);
		if(preRow != false){

			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.add_cnt){
				console.log("******************* 3. 일회성 부가 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
			    $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.add_cnt);
			    $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.onetime_fee);
			    $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.onetime_fee);
			    $("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").trigger("reloadGrid");
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
			var selOrgListCnt = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').addRowData(undefined,row);
			$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 일회성 부가 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});
}
/**
 * 저장 체크
 */ 
function precheckOrderSave(){
	//협정가 설정 처리
	setNegoAmtData();

	//기본상품선택체크
	var basicProdRowId = $("#popupChgProdInfo_basicProdInfo_basicProdListGrid").jqGrid("getGridParam", "selrow");
	if(basicProdRowId == null || basicProdRowId == undefined){
		$("#popupChgProdInfo_basicProdInfo").trigger("click");
		alert('<spring:message code="MSG.M01.MSG00059"/>');  //기본상품 정보가 존재하지 않습니다.
		return false;
	}

	//약정여부체크
	var contractSel = $("#popupChgProdInfo_basicProdInfo_contractPeriodSel").val();
	if(contractSel == 'SEL'){
		$("#popupChgProdInfo_basicProdInfo").trigger("click");
		$('#popupChgProdInfo_basicProdInfo_contractPeriodSel-button').focus();
		alert('<spring:message code="MSG.M15.MSG00042"/>');  //약정 기간을 선택해 주세요.
		return false;
	}
}

// 화면 수정 불가 처리
function orderPopup_setDisable(){
	//기본상품정보
	$('#popupChgProdInfo_basicProdInfo_prodGrpSel').selectmenu("disable");
	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').selectmenu("disable");
	disable_popup_grid('popupChgProdInfo_basicProdInfo_basicProdListGrid');

	//장비상품정보
	$('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupChgProdInfo_deviceProdInfo_deviceProdListGrid');
	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").trigger("reloadGrid");

	//장비정보
	$('#popupChgProdInfo_addonProdInfo_addonProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupChgProdInfo_addonProdInfo_addonProdListGrid');
	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").trigger("reloadGrid");
	
	//협정가정보
	$('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid("resetSelection");
	$('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid("resetSelection");
	disable_popup_grid('popupChgProdInfo_negoAmtInfo_monthlyFeeGrid');
	disable_popup_grid('popupChgProdInfo_negoAmtInfo_onetimeFeeGrid');
	$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").trigger("reloadGrid");
	$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").trigger("reloadGrid");
}


/**
 * 진행중인 오더 정보 조회
 */
function getOrderInfoInProgress(){
	if($('#common_orderStat').val() != '01' && $('#common_orderStat').val() != '02'){
		return;
	}
	var url = '/customer/contract/contract/orderManagement/getOrderInfoBasicProdChangeInProgressAction.json';
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
	//기본상품설정
	$('#popupChgProdInfo_basicProdInfo_prodGrpSel').val(octrtMast.prodGrp);
	$('#popupChgProdInfo_basicProdInfo_prodGrpSel').selectmenu("refresh");
	$('#popupChgProdInfo_basicProdInfo_prodGrpSel').trigger('select');

	//기본상품상세정보설정
	setBasicProdDetailInfo(data);

	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").clearGridData();
	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridBasicProd,
        rowNum: data.gridBasicProd.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").trigger("reloadGrid");

	var basicProdRowId = findRow($("#popupChgProdInfo_basicProdInfo_basicProdListGrid"), "basic_prod_cd", octrtMast.prodCd, 0);
	$("#popupChgProdInfo_basicProdInfo_basicProdListGrid").setSelection(octrtMast.prodCd, false);

	//약정설정
	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').val(data.promCnt); 
	$('#popupChgProdInfo_basicProdInfo_contractPeriodSel').selectmenu("refresh"); 

	//단말상품설정
	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").clearGridData();
	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridDeviceConfList,
        rowNum : data.gridDeviceConfList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").trigger("reloadGrid");

	//부가상품설정
	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").clearGridData();
	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridAddConfList,
        rowNum : data.gridAddConfList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfo_addonProdInfo_addonProdListGrid").trigger("reloadGrid");

	//월정액설정
	$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").clearGridData();
	$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridMonthlyFeeList,
        rowNum : data.gridMonthlyFeeList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").trigger("reloadGrid");

	$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").clearGridData();
	$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridOnetimeFeeList,
        rowNum : data.gridOnetimeFeeList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").trigger("reloadGrid");

	orderPopup_setDisable();
	//버튼제어
	btnDisable("popupRcptBtn_save");
	btnEnable("popupRcptBtn_cancel");
	btnEnable("popupRcptBtn_complete");
}


/*
 *오더 저장처리
 */
function popupOrderProcessSave(){
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

	//기본요금제
	var addBasicProdList = [];
	var basicProdRowId = $("#popupChgProdInfo_basicProdInfo_basicProdListGrid").jqGrid("getGridParam", "selrow");
	var basicProdData = $("#popupChgProdInfo_basicProdInfo_basicProdListGrid").getRowData(basicProdRowId);
	var basicProdInfo = new Object();
	basicProdInfo.prodGrp = basicProdData.basic_prod_grp;
	basicProdInfo.prodCd = basicProdData.basic_prod_cd;
	basicProdInfo.prodNm = basicProdData.basic_prod_cd_nm;
	basicProdInfo.svcCd =  basicProdData.svc_cd;
	basicProdInfo.svcGrp =  basicProdData.svc_grp;
	basicProdInfo.prodTyp =  basicProdData.prod_typ;
	basicProdInfo.basicProdFl=  basicProdData.basic_prod_fl;
	var basicMonthlyData = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getRowData(basicProdData.basic_prod_cd);
	var basicOnetimeData = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getRowData(basicProdData.basic_prod_cd);
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
	addBasicProdList[0] = basicProdInfo;
	orderRequestInfoVo.addBasicProdList = addBasicProdList;

	//삭제 요금제
	var delBasicProdList = [];
	var delBasicProdInfo = new Object();
	delBasicProdInfo.prodCd = $('#popupProdInfo_basicProdCd').val();
	delBasicProdList[0] = delBasicProdInfo;
	orderRequestInfoVo.delBasicProdList = delBasicProdList;	


	//추가상품 정보
	var addDeviceProdList = [];
	var addDeviceConfIds = $('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').getDataIDs();
	var deviceIdx = 0;
	$.each(addDeviceConfIds, function(index, value){
		var confValue = $('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').getRowData(value);

		if(confValue.change_tp == 'D') return true;


		var addDeviceInfo = new Object();

		var prodKey = confValue.device_prod_cd + value;
		console.log("DEVICE KEY : " + prodKey);
		
		var monthlyData = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getRowData(prodKey);
		var onetimeData = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getRowData(prodKey);
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

	var delDeviceProdList = [];
	var delDeviceIndex = 0;
	$.each(addDeviceConfIds, function(index, value){
		var delDeviceProd = $('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').getRowData(value);
		console.log(delDeviceProd);
		if(delDeviceProd.change_tp == 'A') return true;
		var delDeviceInfo = new Object();
		delDeviceInfo.prodCd = delDeviceProd.device_prod_cd;
		delDeviceInfo.prodCdNm = delDeviceProd.device_prod_cd_nm;
		delDeviceInfo.prodCmpsId = delDeviceProd.prod_cmps_id;
		delDeviceProdList[delDeviceIndex++] = delDeviceInfo;
	});

	orderRequestInfoVo.delDeviceProdList = delDeviceProdList;


	//부가상품 정보
	var addAddonProdList = [];
	var addAddConfIds = $('#popupChgProdInfo_addonProdInfo_addonProdListGrid').getDataIDs();
	var addIdx = 0;
	$.each(addAddConfIds, function(index, value){
		var confValue = $('#popupChgProdInfo_addonProdInfo_addonProdListGrid').getRowData(value);
		if(confValue.change_tp == 'D') return true;
		var addonProdInfo = new Object();

		var prodKey = confValue.add_prod_cd + value;
		
		var monthlyData = $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').getRowData(prodKey);
		var onetimeData = $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').getRowData(prodKey);

		if(confValue.is_nego == '1'){ // 협정가일 경우 input의 value 를 가져옴
			confValue.add_cnt = $('#popupAddText_' + value).val();
		}
		if(confValue.add_cnt == null || confValue.add_cnt == '' || confValue.add_cnt == '0'){
			confValue.add_cnt = '1';
		}
		addonProdInfo.prodGrp = confValue.add_prod_grp;
		addonProdInfo.prodCd = confValue.add_prod_cd;
		addonProdInfo.prodNm = confValue.add_prod_nm;
		addonProdInfo.svcCd = confValue.svc_cd;
		addonProdInfo.svcGrp = confValue.svc_grp;
		addonProdInfo.prodTyp = confValue.prod_typ;
		addonProdInfo.prodCnt = confValue.add_cnt;
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
		}
		addAddonProdList[addIdx++] = addonProdInfo;

	});
	orderRequestInfoVo.addAddonProdList = addAddonProdList;

	var delAddProdList = [];
	var delAddIndex = 0;
	$.each(addAddConfIds, function(index, value){
		var delAddProd = $('#popupChgProdInfo_addonProdInfo_addonProdListGrid').getRowData(value);
		if(delAddProd.change_tp == 'A') return true;
		var delAddInfo = new Object();
		delAddInfo.prodCd = delAddProd.add_prod_cd;
		delAddInfo.prodCdNm = delAddProd.add_prod_cd_nm;
		delAddInfo.prodCmpsId = delAddProd.prod_cmps_id;
		delAddProdList[delAddIndex++] = delAddInfo;
	});
	orderRequestInfoVo.delAddonProdList = delAddProdList;

	orderRequestInfoVo.promCnt = $('#popupChgProdInfo_basicProdInfo_contractPeriodSel').val();
	orderRequestInfoVo.reqUsrId = '${session_user.userId}';
	orderRequestInfoVo.reqOrgId = '${session_user.orgId}';
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
		url : '/customer/contract/contract/orderManagement/saveCmplOrderAction.json',
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
	if(rowObject.change_tp == "D"){ // 삭제유형
		return rowObject.add_cnt;
	}
	var inputTag = '<input type="text" id="' + id +'" placeholder="' + '<spring:message code="LAB.M07.LAB00247"/>' + '" value="' + cellvalue + '" class="txt_c cntCls w80p" onkeydown="return countKeyDown(event)" onkeyup="addConfKeyUp(event)" onfocusout="countFocusOut(event)" maxlength="4"/>';
	return inputTag;
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
	if(rowObject.change_tp == "D"){ // 삭제유형
		return rowObject.device_cnt;
	}
	var inputTag = '<input type="text" id="' + id +'" placeholder="' + '<spring:message code="LAB.M07.LAB00247"/>' + '" value="' + cellvalue + '" class="txt_c cntCls w80p" onkeydown="return countKeyDown(event)" onkeyup="deviceConfKeyUp(event)" onfocusout="countFocusOut(event)" maxlength="4"/>';
	return inputTag;
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
 * 장비설정정보 Update
 */
function updateDeviceConfByCnt(value, cnt){
	var rowData = $("#popupChgProdInfo_deviceProdInfo_deviceProdListGrid").getRowData(value);
	if(cnt == null || cnt =='' || cnt == '0'){
		cnt = 1;	
	}
	var onetime_fee = rowData.org_onetime_fee * cnt;
	var monthly_fee = rowData.org_monthly_fee * cnt;

	$('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').jqGrid('setCell', value, "device_cnt" , cnt);
    $('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').jqGrid('setCell', value, "onetime_fee" , onetime_fee);
    $('#popupChgProdInfo_deviceProdInfo_deviceProdListGrid').jqGrid('setCell', value, "monthly_fee" , monthly_fee);
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
 * 부가설정정보 Update
 */
function updateAddonConfByCnt(value, cnt){
	var rowData = $("#popupChgProdInfo_addonProdInfo_addonProdListGrid").getRowData(value);
	if(cnt == null || cnt =='' || cnt == '0'){
		cnt = 1;	
	}
	var onetime_fee = rowData.org_onetime_fee * cnt;
	var monthly_fee = rowData.org_monthly_fee * cnt;
	$('#popupChgProdInfo_addonProdInfo_addonProdListGrid').jqGrid('setCell', value, "add_cnt" , cnt);
    $('#popupChgProdInfo_addonProdInfo_addonProdListGrid').jqGrid('setCell', value, "onetime_fee" , onetime_fee);
    $('#popupChgProdInfo_addonProdInfo_addonProdListGrid').jqGrid('setCell', value, "monthly_fee" , monthly_fee);
}


/**
 * 월정액정보 Update
 */
function updateMonthlyConfByRate(value, rate){
	var rowData = $("#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid").getRowData(value);

	if(rate == null || rate == ''){
		rate = 0;
	}
	var nego_amt = rowData.sale_amt * (100 - rate) / 100;
    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', value, "discount_rate" , rate);
    $('#popupChgProdInfo_negoAmtInfo_monthlyFeeGrid').jqGrid('setCell', value, "nego_amt" , nego_amt.toFixed(2));
}

/**
 * 일회성요금정보 Update
 */
function updateOnetimeConfByRate(value, rate){
	var rowData = $("#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid").getRowData(value);
	if(rate == null || rate == ''){
		rate = 0;
	}
	var nego_amt = rowData.sale_amt * (100 - rate) / 100;
	$('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', value, "discount_rate" , rate);
    $('#popupChgProdInfo_negoAmtInfo_onetimeFeeGrid').jqGrid('setCell', value, "nego_amt" , nego_amt.toFixed(2));
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
 * 수량 비어 있을 경우 기본 숫자 세팅
 */
function countFocusOut(event){
	if(event.target.value == '' || event.target.value == '0'){
		$('#' + event.target.id).val("1");
	}
	return;
}

</script>
			
<div class='main_btn_box'>
	<div class='fl'>
		<h4 class='sub_title'><spring:message code="LAB.M07.LAB00308"/></h4>
	</div>
</div>
<ul id='popupProdInfoTabs' class="tabs">
	<li id="popupProdInfo_basicProdInfo" class="active" rel="popupProdInfo_basicProdInfoDiv"><spring:message code="LAB.M01.LAB00210"/></li> <!-- 기본상품 -->
	<li id="popupProdInfo_deviceProdInfo" rel="popupProdInfo_deviceProdInfoDiv"><spring:message code="LAB.M09.LAB00042"/></li> <!-- 장비상품 -->
	<li id="popupProdInfo_addonProdInfo" rel="popupProdInfo_addonProdInfoDiv"><spring:message code="LAB.M06.LAB00118"/></li> <!-- 부가서비스 -->
	<li id="popupProdInfo_instAddrInfo" rel="popupProdInfo_instAddrInfoDiv"><spring:message code="LAB.M07.LAB00301"/></li> <!-- 설치주소 -->
	<li id="popupProdInfo_monthlyFeeInfo" rel="popupProdInfo_monthlyFeeInfoDiv"><spring:message code="LAB.M08.LAB00195"/></li> <!-- 월정액 -->
	<li id="popupProdInfo_onetimeChargeInfo" rel="popupProdInfo_onetimeChargeInfoDiv"><spring:message code="LAB.M08.LAB00196"/></li> <!-- 일회성 -->
</ul>
<!--  -->
<div class="tab_box table_col_box h174">	<!-- 사용상품현황 -->
	<div id="popupProdInfo_basicProdInfoDiv" class="tab_content">
		<table class='writeview tdB'>
			<tbody>
				<tr class='col2'>
					<th><spring:message code="LAB.M07.LAB00307"/></th>	<!-- 상품그룹 -->
					<td><div id="popupProdInfo_basicProdInfo_prodGrp" class="inp_date np custInfoCls"></div></td>
					<th><spring:message code="LAB.M01.LAB00230"/></th><!-- 과금시작일 -->
					<td><div id="popupProdInfo_basicProdInfo_rateStrtDt" class="inp_date np custInfoCls"></div></td>
				</tr>
				<tr class='col2'>
					<th><spring:message code="LAB.M07.LAB00130"/>(<spring:message code="LAB.M07.LAB00156"/>)</th><!-- 상품명(상품코드) -->
					<td><div id="popupProdInfo_basicProdInfo_prodNm" class="inp_date np custInfoCls"></div>
					<input type="text" id="popupProdInfo_basicProdCd" hidden />
					</td>
					<th><spring:message code="LAB.M07.LAB00287"/></th><!-- 시작일시 -->
					<td><div id="popupProdInfo_basicProdInfo_actDttm" class="inp_date np custInfoCls"></div></td>
				</tr>
				<tr class='col2'>
					<th><spring:message code="LAB.M01.LAB00037"/></th><!-- 계약상태 -->
					<td><div id="popupProdInfo_basicProdInfo_ctrtStat" class="inp_date np custInfoCls"></div></td>
					<th><spring:message code="LAB.M08.LAB00002"/></th><!-- 약정기간 -->
					<td><div id="popupProdInfo_basicProdInfo_promPrd" class="inp_date np custInfoCls"></div></td>
				</tr>
			</tbody>
		</table>
		<input id="popupProdInfo_basicProdInfo_isNego" type="input" value="" hidden />
	</div>
<!-- //popupProdInfo_basicProdInfoDiv -->
	<div id="popupProdInfo_deviceProdInfoDiv" class="tab_content"><!-- 기본상품변경 GRID -->
		<table id="popupDeviceProdListGrid" class="w100p"></table>
	</div>
<!-- //popupProdInfo_deviceProdInfoDiv -->
	<div id="popupProdInfo_addonProdInfoDiv" class="tab_content"><!-- 분납개월수GRID -->
		<table id="popupAddonProdListGrid" class="w100p"></table>
	</div>
<!-- //popupProdInfo_addonProdInfoDiv -->
	<div id="popupProdInfo_instAddrInfoDiv" class="tab_content">
		<table class='writeview column_2 row_1'>
			<tbody>
				<tr class='col2_left'>
					<th>
						<spring:message code="LAB.M08.LAB00087"/><!-- 우편번호 -->
					</th>
					<td>
						<div id="popupProdInfo_instAddrInfo_zipCd" class="inp_date np custInfoCls"></div>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00190"/><!-- 주소 -->
					</th>
					<td>
						<div id="popupProdInfo_instAddrInfo_addr" class="inp_date np custInfoCls"></div>
					</td>
				</tr>
				<!-- <tr class='col2_left'>
					<th>
						<spring:message code="LAB.M03.LAB00087"/>
					</th>
					<td>
						<div id="popupProdInfo_instAddrInfo_city" class="inp_date np custInfoCls"></div>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00232"/>
					</th>
					<td>
						<div id="popupProdInfo_instAddrInfo_state" class="inp_date np custInfoCls"></div>
					</td>
				</tr> -->
			</tbody>
		</table>
	</div>
<!-- //popupProdInfo_instAddrInfoDiv -->
	<div id="popupProdInfo_monthlyFeeInfoDiv" class="tab_content"><!-- 월정액금액GRID -->
		<table id="popupMonthlyFeeGrid" class="w100p"></table>
	</div>
<!-- //popupProdInfo_monthlyFeeInfoDiv -->		
	<div id="popupProdInfo_onetimeChargeInfoDiv" class="tab_content"><!-- 일회성금액GRID -->
		<table id="popupOnetimeChargeGrid" class="w100p"></table>
	</div>
<!-- //popupProdInfo_onetimeChargeInfoDiv -->
</div><!--//tab_box-->

<!-- 변경부 시작 -->
<!-- 상품 정보 -->
<div class='main_btn_box'>
	<div class='fl'>
		<h4 class='sub_title'><spring:message code="LAB.M07.LAB00310"/></h4> <!-- 상품 정보 -->
	</div>
</div>
<ul id='popupChgProdInfoTabs' class="tabs">
	<li id="popupChgProdInfo_basicProdInfo" class="active" rel="popupChgProdInfo_basicProdInfoDiv"><spring:message code="LAB.M01.LAB00210"/></li> <!-- 기본상품 -->
	<li id="popupChgProdInfo_deviceProdInfo" rel="popupChgProdInfo_deviceProdInfoDiv"><spring:message code="LAB.M09.LAB00042"/></li> <!-- 장비상품 -->
	<li id="popupChgProdInfo_addonProdInfo" rel="popupChgProdInfo_addonProdInfoDiv"><spring:message code="LAB.M06.LAB00118"/></li> <!-- 부가서비스 -->
	<li id="popupChgProdInfo_negoAmtInfo" rel="popupChgProdInfo_negoAmtInfoDiv"><spring:message code="LAB.M14.LAB00078"/></li> <!-- 협정가 -->
</ul>
<div class="tab_box table_col_box h300">
	<div class='inpCaution'>
		<span class='dot'>*</span>
		<spring:message code="LAB.M13.LAB00034"/> 
	</div>
	<div id="popupChgProdInfo_basicProdInfoDiv" class="tab_content">
		<!-- 기본 상품 Left-->
		<div class='col_left'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M07.LAB00307"/></span>
					<select id="popupChgProdInfo_basicProdInfo_prodGrpSel" class='w300'>
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</select>
				</div>
			</div>
			<div id='popupChgProdInfo_basicProdInfo_basicProdListGridDiv'>
				<table id="popupChgProdInfo_basicProdInfo_basicProdListGrid" class="w100p"></table>
			</div>
		</div><!--//left-->
		<div class='col_right'>
			<table class='writeview'>
				<tbody>
					<tr class='col2'>
						<th><spring:message code="LAB.M08.LAB00002"/> <!-- 약정기간 -->
							<span class="dot">*</span>
						</th>
						<td>
							<select id="popupChgProdInfo_basicProdInfo_contractPeriodSel" class='w100p'>
								<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
							</select>
						</td>
					</tr>
					<tr class='col1'>
						<th><spring:message code="LAB.M08.LAB00187"/></th> <!-- 위약금 -->
						<td>
							<div id='popupChgProdInfo_basicProdInfo_penaltyAmt' class="popupChgProdInfoBasicInfo fr">
							</div>
						</td>
					</tr>
					<tr class='col1'>
						<th><spring:message code="LAB.M01.LAB00205"/></th> <!-- 기본료 -->
						<td>
							<div id='popupChgProdInfo_basicProdInfo_basicAmt' class="popupChgProdInfoBasicInfo fr">
							</div>
						</td>
					</tr>
					<tr class='col1'>
						<th><spring:message code="LAB.M07.LAB00309"/></th> <!-- 설치비 -->
						<td>
							<div id='popupChgProdInfo_basicProdInfo_instAmt' class="popupChgProdInfoBasicInfo fr">
							</div>
						</td>
					</tr>
					<tr class='col1'>
						<th><spring:message code="LAB.M08.LAB00186"/></th> <!-- 유지보수비 -->
						<td>
							<div id='popupChgProdInfo_basicProdInfo_maintenanceAmt' class="popupChgProdInfoBasicInfo fr">
							</div>
						</td>
					</tr>
					<tr class='col1'>
						<th><spring:message code="LAB.M08.LAB00193"/></th> <!-- 임대료 -->
						<td>
							<div id='popupChgProdInfo_basicProdInfo_rentalFee' class="popupChgProdInfoBasicInfo fr">
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div><!--//right-->
	</div>
	<div id="popupChgProdInfo_deviceProdInfoDiv" class="tab_content">
		<div class='btn_box'>
			<div class='tabSelect'>
				<span class='selectText'>- <spring:message code="LAB.M09.LAB00239"/></span> <!--자동 변경 상품 --> 
				<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
			</div>
		</div>
		<div id='popupChgProdInfo_deviceProdInfo_deviceProdListGridDiv'> <!-- 장비상품그리드 -->
			<table id="popupChgProdInfo_deviceProdInfo_deviceProdListGrid" class="w100p"></table>
		</div>
	</div>
	<div id="popupChgProdInfo_addonProdInfoDiv" class="tab_content">
		<div class='btn_box'>
			<div class='tabSelect'>
				<span class='selectText'>- <spring:message code="LAB.M09.LAB00239"/></span> <!--자동 변경 상품 --> 
				<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
			</div>
		</div>
		<div id='popupChgProdInfo_addonProdInfo_addonProdListGridDiv'> <!-- 부가상품그리드 -->
			<table id="popupChgProdInfo_addonProdInfo_addonProdListGrid" class="w100p"></table>
		</div>
	</div>
	<!-- 협정가 -->
	<div id="popupChgProdInfo_negoAmtInfoDiv" class="tab_content" >
		<div class='col_left'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M08.LAB00093"/></span> <!-- 월정액금액 -->
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupChgProdInfo_negoAmtInfo_monthlyFeeGridDiv'> <!-- 월정액그리드 -->
				<table id="popupChgProdInfo_negoAmtInfo_monthlyFeeGrid" class="w100p"></table>
			</div>
		</div><!--//left-->
		<div class='col_right'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M08.LAB00144"/></span> <!-- 일회성금액 -->
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupChgProdInfo_negoAmtInfo_onetimeFeeGridDiv'> <!-- 일회성그리드 -->
				<table id="popupChgProdInfo_negoAmtInfo_onetimeFeeGrid" class="w100p"></table>
			</div>
		</div><!--//right-->
		<div class='btn_box cauption'>
			<span class='dot'>*</span> <spring:message code="MSG.M15.MSG00038"/> <!-- 할인율 입력 또는 분납개월수 선택을 진행해주세요. -->
		</div>
	</div>
</div>

			

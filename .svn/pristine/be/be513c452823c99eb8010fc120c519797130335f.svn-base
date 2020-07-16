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
			//$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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

	//설정부 시작

	/**
	  * 장비상품그리드
	  */ 
	$("#popupChgProdInfoDeviceProdListGrid").jqGrid({
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
				    { label: '<spring:message code="LAB.M13.LAB00033"/>', name: 'is_mandatory_yn_nm', width : 80, align:"center"},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'is_nego_nm', width : 120, align:"center"}
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
        loadComplete : function () {
        	$("#popupChgProdInfoDeviceProdListGrid").setGridWidth($('#popupChgProdInfoDeviceProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfoDeviceProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    },
    	sortable: { 
    		update: function(permutation) {
	    		$("#popupChgProdInfoDeviceProdListGrid").setGridWidth($('#popupChgProdInfoDeviceProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

 
	/**
	  * 장비상품설정그리드
	  */ 
	$("#popupChgProdInfoDeviceConfProdListGrid").jqGrid({
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
		cellsubmit:'clientArray',
        onCellSelect : function(rowid, index, contents, event){
        	
        },
        loadComplete : function () {
        	$("#popupChgProdInfoDeviceConfProdListGrid").setGridWidth($('#popupChgProdInfoDeviceConfProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfoDeviceConfProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    }
	});

	/**
	  * 장비상품그리드
	  */ 
	$("#popupChgProdInfoDeviceUseProdListGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
					{ label: 'soId' , name: 'so_id', hidden:true,width : 0},
					{ label: 'deviceProdGrp' , name: 'device_prod_grp', hidden:true,width : 0},
					{ label: 'svcGrp' , name: 'svc_grp', hidden:true,width : 0},
					{ label: 'svcCd' , name: 'svc_cd', hidden:true,width : 0},
					{ label: 'prodTyp' , name: 'prod_typ', hidden:true,width : 0},
					{ label: 'isMandatoryYn' , name: 'is_mandatory_yn', hidden:true,width : 0},
					{ label: 'isNego' , name: 'is_nego', hidden:true,width : 0, sortable :false},
					{ label: 'isDupYn' , name: 'is_dup_yn', hidden:true,width : 0, sortable :false},
					{ label: 'dupCnt' , name: 'dup_cnt', hidden:true,width : 0, sortable :false},
					{ label: 'prodCmpsId' , name: 'prod_cmps_id', hidden:true,width : 0, sortable :false},
				    { label: '<spring:message code="LAB.M09.LAB00228"/>', name: 'device_prod_cd', width : 120, align:"center"},
				    { label: '<spring:message code="LAB.M09.LAB00229"/>', name: 'device_prod_cd_nm', width : 200, align:"left"},
				    { label: '<spring:message code="LAB.M01.LAB00009"/>', name: 'rate_strt_dt', width : 100, align:"center", formatter : stringTypeFormatterYYYYMMDD},
				    { label: '<spring:message code="LAB.M08.LAB00093"/>', name: 'monthly_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number'},
				    { label: '<spring:message code="LAB.M08.LAB00144"/>', name: 'onetime_fee', width : 90, align : "right", formatter : 'integer', sorttype:'number'},
				    { label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'device_cnt', width : 90, align : "center", formatter : 'integer', sorttype:'number'},
				    { label: '<spring:message code="LAB.M13.LAB00033"/>', name: 'is_mandatory_yn_nm', width : 80, align:"center"},
				    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'is_nego_nm', width : 120, align:"center"}
		],
		viewrecords: true,
		shrinkToFit:false,
		loadonce : true,
		height: 170,
		multiselect: true,
		sortable : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "deviceUseProdList"
		},
        onCellSelect : function(rowid, index, contents, event){
        },
        onSelectRow : function(rowid, status, e){
			var rowData = $('#popupChgProdInfoDeviceUseProdListGrid').getRowData(rowid);
        	if(status == true){
        		return true;
        	}
        	if(rowData.is_dup_yn == 'N'){ //중복 불가 인 경우 삭제 처리
				var ids = $('#popupChgProdInfoDeviceConfProdListGrid').getDataIDs();
				$.each(ids, function(index, value){
					var confData = $('#popupChgProdInfoDeviceConfProdListGrid').getRowData(value);
					if(rowData.device_prod_cd == confData.device_prod_cd){
						deleteRow('popupChgProdInfoDeviceConfProdListGrid', value);
						return false;
					}
				});
        	}else if(rowData.is_dup_yn == 'Y'){ //종복 허용인 경우 수량 체크해서 return;
        		if(rowData.dup_cnt != null && rowData.dup_cnt != '' && rowData.dup_cnt != '0'){ //미설정인 경우 무제한
        			var ids = $('#popupChgProdInfoDeviceConfProdListGrid').getDataIDs();
					var checkCnt = 0;
					$.each(ids, function(index, value){
						var checkData = $('#popupChgProdInfoDeviceConfProdListGrid').getRowData(value);
						if(checkData.device_prod_cd == rowData.device_prod_cd){
							checkCnt = checkCnt + 1;
						}
					});
					var useIds = $('#popupChgProdInfoDeviceUseProdListGrid').getDataIDs();
					$.each(useIds, function(index, value){
						var useRow = $('#popupChgProdInfoDeviceUseProdListGrid').getRowData(value);
						if(rowData.device_prod_cd == useRow.device_prod_cd){
							checkCnt = checkCnt + 1;
						}
					});
					var checkIds=$("#popupChgProdInfoDeviceUseProdListGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
					$.each(checkIds, function(idx, val){
						var checkRowData = $('#popupChgProdInfoDeviceUseProdListGrid').getRowData(val);
						if(rowData.device_prod_cd == checkRowData.device_prod_cd){
							checkCnt = checkCnt - 1;
						}
					});
					//허용초과
					if(rowData.dup_cnt < checkCnt){
						$("#popupChgProdInfoDeviceUseProdListGrid").setSelection(rowid,  false);
						alert('<spring:message code="MSG.M08.MSG00085" arguments="' + rowData.dup_cnt + '"/>'); 
					}
				}
        	}
        },
        loadComplete : function () {
        	$("#popupChgProdInfoDeviceUseProdListGrid").setGridWidth($('#popupChgProdInfoDeviceUseProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfoDeviceUseProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    },
    	sortable: { 
    		update: function(permutation) {
	    		$("#popupChgProdInfoDeviceUseProdListGrid").setGridWidth($('#popupChgProdInfoDeviceUseProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});


    /**
	  * 월정액설정그리드
	  */ 
	$("#popupChgProdInfoMonthlyFeeGrid").jqGrid({
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
        	$("#popupChgProdInfoMonthlyFeeGrid").setGridWidth($('#popupChgProdInfoMonthlyFeeGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfoMonthlyFeeGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    }
	});



	/**
	  * 일회성요금설정그리드
	  */ 
	$("#popupChgProdInfoOnetimeFeeGrid").jqGrid({
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
        	$("#popupChgProdInfoOnetimeFeeGrid").setGridWidth($('#popupChgProdInfoOnetimeFeeGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfoOnetimeFeeGrid").trigger("reloadGrid");
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
			$("#popupChgProdInfoDeviceProdListGrid").setGridWidth($('#popupChgProdInfoDeviceProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			$("#popupChgProdInfoDeviceConfProdListGrid").setGridWidth($('#popupChgProdInfoDeviceConfProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 1){
			$("#popupChgProdInfoDeviceUseProdListGrid").setGridWidth($('#popupChgProdInfoDeviceUseProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 2){
			$("#popupChgProdInfoMonthlyFeeGrid").setGridWidth($('#popupChgProdInfoMonthlyFeeGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			$("#popupChgProdInfoOnetimeFeeGrid").setGridWidth($('#popupChgProdInfoOnetimeFeeGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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

  	getProdInfo();

	//장비상품 추가 이벤트
	$('#popupChgProdInfo_deviceProdInfo_btnRight').on('click',function (e) {
		if($("#popupChgProdInfo_deviceProdInfo_btnRight").hasClass('not-active')){
			return;
		}
	    var selectedRowId = $('#popupChgProdInfoDeviceProdListGrid').jqGrid('getGridParam','selrow');
	
		if(selectedRowId == null){
			return;
		}
		var addRow = $('#popupChgProdInfoDeviceProdListGrid').getRowData(selectedRowId);
		if(addRow.is_dup_yn == 'N'){ //중복 구매 불가인 경우
			var ids = $('#popupChgProdInfoDeviceUseProdListGrid').getDataIDs();
			var isDup = false;
			$.each(ids, function(index, value){
				var rowData = $('#popupChgProdInfoDeviceUseProdListGrid').getRowData(value);
				if(addRow.device_prod_cd == rowData.device_prod_cd){
					isDup = true;
					return false;
				}
			});

			var isChecked = false;
			if(isDup){
				var checkIds=$("#popupChgProdInfoDeviceUseProdListGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
				$.each(checkIds, function(idx, val){
					var checkRowData = $('#popupChgProdInfoDeviceUseProdListGrid').getRowData(val);
					if(addRow.device_prod_cd == checkRowData.device_prod_cd){
						isChecked = true;
						return false;
					}
				});
			}
			if(isDup == true && isChecked == false){
				alert('<spring:message code="MSG.M08.MSG00080"/>');
				return;
			}else{
				var addDup = false;
				var targetIds = $('#popupChgProdInfoDeviceConfProdListGrid').getDataIDs();
				$.each(targetIds, function(index, value){
					var rowData = $('#popupChgProdInfoDeviceConfProdListGrid').getRowData(value);
					if(addRow.device_prod_cd == rowData.device_prod_cd){
						addDup = true;
						return false;
					}
				});
				if(addDup == true){
					alert('<spring:message code="MSG.M08.MSG00080"/>');
					return;
				}
			}
		}else if(addRow.is_dup_yn == 'Y'){
			if(addRow.dup_cnt != null && addRow.dup_cnt != '' && addRow.dup_cnt != '0'){ //미설정인 경우 무제한
				var ids = $('#popupChgProdInfoDeviceConfProdListGrid').getDataIDs();
				var checkCnt = 0;
				$.each(ids, function(index, value){
					var rowData = $('#popupChgProdInfoDeviceConfProdListGrid').getRowData(value);
					if(addRow.device_prod_cd == rowData.device_prod_cd){
						checkCnt = checkCnt + 1;
					}
				});
				var useIds = $('#popupChgProdInfoDeviceUseProdListGrid').getDataIDs();
				$.each(useIds, function(index, value){
					var useRow = $('#popupChgProdInfoDeviceUseProdListGrid').getRowData(value);
					if(addRow.device_prod_cd == useRow.device_prod_cd){
						checkCnt = checkCnt + 1;
					}
				});

				var checkIds=$("#popupChgProdInfoDeviceUseProdListGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
				$.each(checkIds, function(idx, val){
					var checkRowData = $('#popupChgProdInfoDeviceUseProdListGrid').getRowData(val);
					if(addRow.device_prod_cd == checkRowData.device_prod_cd){
						checkCnt = checkCnt - 1;
					}
				});
				if(addRow.dup_cnt <= checkCnt){
					alert('<spring:message code="MSG.M08.MSG00085" arguments="' + addRow.dup_cnt + '"/>'); 
					return false;
				}
			}
		}
		moveDeviceRightWithoutCheck('popupChgProdInfoDeviceProdListGrid', 'popupChgProdInfoDeviceConfProdListGrid');
	});

	//장비상품 삭제 이벤트
	$('#popupChgProdInfo_deviceProdInfo_btnLeft').on('click',function (e) {
		if($("#popupChgProdInfo_deviceProdInfo_btnLeft").hasClass('not-active')){
			return;
		}
		var rowid = $('#popupChgProdInfoDeviceConfProdListGrid').jqGrid("getGridParam", "selrow");
		var rowData = $('#popupChgProdInfoDeviceConfProdListGrid').getRowData(rowid);
		if(rowData.is_mandatory_yn == 'Y'){ //필수 항목 삭제 불가
			alert('<spring:message code="MSG.M13.MSG00028"/>');
			return false;
		}

		deleteRow('popupChgProdInfoDeviceConfProdListGrid', rowid);
		var prodKey = rowData.device_prod_cd + rowid;
		//협정가 정보 삭제
		deleteRow('popupChgProdInfoMonthlyFeeGrid', prodKey);
		deleteRow('popupChgProdInfoOnetimeFeeGrid', prodKey);
	});

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

	//상품설정그리드 초기화
	$("#popupChgProdInfoDeviceConfProdListGrid").clearGridData();
	$("#popupChgProdInfoDeviceConfProdListGrid").trigger("reloadGrid");
	//협정가 초기화
	$("#popupChgProdInfoMonthlyFeeGrid").clearGridData();
	$("#popupChgProdInfoMonthlyFeeGrid").trigger("reloadGrid");
	$("#popupChgProdInfoOnetimeFeeGrid").clearGridData();
	$("#popupChgProdInfoOnetimeFeeGrid").trigger("reloadGrid");

	//전체상품조회
	$("#popupChgProdInfoDeviceProdListGrid").clearGridData();
	$("#popupChgProdInfoDeviceProdListGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			soId : $("#common_soId").val(),
        	basicProdGrp : data.basicProdInfo.prod_grp_cd,
        	basicProdCd : data.basicProdInfo.prod_cd
		}
	});
	$("#popupChgProdInfoDeviceProdListGrid").trigger("reloadGrid");
	
	//사용중인 장비상품정보 조회
	$("#popupChgProdInfoDeviceUseProdListGrid").setGridParam({
		data: data.deviceInfoList
		,rowNum: data.deviceInfoList.length
	});
   	$("#popupChgProdInfoDeviceUseProdListGrid").trigger("reloadGrid");

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
 * 오더 화면 Resize
 */
function commonPupupResize(){
	$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupMonthlfFeeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_montlyFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_onetimeFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	$('#popupChgProdInfo_delProdInfo').trigger('click');
	$('#popupChgProdInfo_addProdInfo').trigger('click');

	$(".Layer_wrap select").selectmenu();
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
 * 장비설정정보 Update
 */
function updateDeviceConfByCnt(value, cnt){
	var rowData = $("#popupChgProdInfoDeviceConfProdListGrid").getRowData(value);
	if(cnt == null || cnt =='' || cnt == '0'){
		cnt = 1;	
	}
	var onetime_fee = rowData.org_onetime_fee * cnt;
	var monthly_fee = rowData.org_monthly_fee * cnt;
	$('#popupChgProdInfoDeviceConfProdListGrid').jqGrid('setCell', value, "device_cnt" , cnt);
    $('#popupChgProdInfoDeviceConfProdListGrid').jqGrid('setCell', value, "onetime_fee" , onetime_fee);
    $('#popupChgProdInfoDeviceConfProdListGrid').jqGrid('setCell', value, "monthly_fee" , monthly_fee);
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
 * 할인율 비어 있을 경우 기본 숫자 세팅
 */
function rateFocusOut(event){
	if(event.target.value == '' || event.target.value == '0'){
		$('#' + event.target.id).val("0");
	}
	return;
}




/**
 * 협정가 정보 재설정
 */
function setNegoAmtData(){
	if($('#common_orderStat').val() == '01' || $('#common_orderStat').val() == '02'){
		return;
	}

	//월정액 단말 처리
	var addDeviceConfRowIds = $('#popupChgProdInfoDeviceConfProdListGrid').getDataIDs();
	$.each(addDeviceConfRowIds, function(index, value){
		var rowData = $('#popupChgProdInfoDeviceConfProdListGrid').getRowData(value);
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
		var preRow = $('#popupChgProdInfoMonthlyFeeGrid').getInd(prodKey);
		if(preRow != false){
			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupChgProdInfoMonthlyFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.device_cnt){
				console.log("******************* 3. 월정액 단말 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
				console.log("******************* 4. 월정액 단말 처리 : " + prodKey);	
			    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.device_cnt);
			    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.monthly_fee);
			    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.monthly_fee);
			    $("#popupChgProdInfoMonthlyFeeGrid").trigger("reloadGrid");
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
			var selOrgListCnt = $('#popupChgProdInfoMonthlyFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfoMonthlyFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfoMonthlyFeeGrid').addRowData(undefined,row);
			$("#popupChgProdInfoMonthlyFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 월정액 단말 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});

	//일회성 단말 처리
	$.each(addDeviceConfRowIds, function(index, value){
		var rowData = $('#popupChgProdInfoDeviceConfProdListGrid').getRowData(value);
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
		var preRow = $('#popupChgProdInfoOnetimeFeeGrid').getInd(prodKey);
		if(preRow != false){

			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupChgProdInfoOnetimeFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.device_cnt){
				console.log("******************* 3. 일회성 단말 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
			    $('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.device_cnt);
			    $('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.onetime_fee);
			    $('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.onetime_fee);
			    $("#popupChgProdInfoOnetimeFeeGrid").trigger("reloadGrid");
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
			var selOrgListCnt = $('#popupChgProdInfoOnetimeFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfoOnetimeFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfoOnetimeFeeGrid').addRowData(undefined,row);
			$("#popupChgProdInfoOnetimeFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 일회성 단말 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});
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

/**
 * 월정액정보 Update
 */
function updateMonthlyConfByRate(value, rate){
	var rowData = $("#popupChgProdInfoMonthlyFeeGrid").getRowData(value);

	if(rate == null || rate == ''){
		rate = 0;
	}
	var nego_amt = rowData.sale_amt * (100 - rate) / 100;
    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', value, "discount_rate" , rate);
    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', value, "nego_amt" , nego_amt.toFixed(2));
}

/**
 * 일회성요금정보 Update
 */
function updateOnetimeConfByRate(value, rate){
	var rowData = $("#popupChgProdInfoOnetimeFeeGrid").getRowData(value);
	if(rate == null || rate == ''){
		rate = 0;
	}
	var nego_amt = rowData.sale_amt * (100 - rate) / 100;
	$('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', value, "discount_rate" , rate);
    $('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', value, "nego_amt" , nego_amt.toFixed(2));
}

/**
 * 저장 체크
 */ 
function precheckOrderSave(){
	//협정가 설정 처리
	setNegoAmtData();

	var isAddProd = false;
	var isDelProd = false;
	var addProdInfoIds = $('#popupChgProdInfoDeviceConfProdListGrid').getDataIDs();
	if(addProdInfoIds.length != 0){
		isAddProd = true;
	}
	var delProdInfoIds = $("#popupChgProdInfoDeviceUseProdListGrid").jqGrid('getGridParam', 'selarrrow');
	if(delProdInfoIds.length != 0){
		isDelProd = true;
	}

	if(isAddProd == false && isDelProd == false){
		alert('<spring:message code="MSG.M06.MSG00026"/>');  //변경 정보가 없습니다.
		return false;
	}
}

// 화면 수정 불가 처리
function orderPopup_setDisable(){

	$('#popupChgProdInfoDeviceProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupChgProdInfoDeviceProdListGrid');
	$("#popupChgProdInfoDeviceProdListGrid").trigger("reloadGrid");


	//추가장비
	$('#popupChgProdInfoDeviceConfProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupChgProdInfoDeviceConfProdListGrid');
	$("#popupChgProdInfoDeviceConfProdListGrid").trigger("reloadGrid");
	//삭제장비
	disable_popup_grid('popupChgProdInfoDeviceUseProdListGrid');

	$("#popupChgProdInfoDeviceUseProdListGridDiv input:checkbox").addClass('not-active');
	//$("#popupChgProdInfoDeviceUseProdListGridDiv input:checkbox").attr('disabled',true);

	//협정가정보
	$('#popupChgProdInfoOnetimeFeeGrid').jqGrid("resetSelection");
	$('#popupChgProdInfoMonthlyFeeGrid').jqGrid("resetSelection");
	disable_popup_grid('popupChgProdInfoOnetimeFeeGrid');
	disable_popup_grid('popupChgProdInfoMonthlyFeeGrid');
	$("#popupChgProdInfoOnetimeFeeGrid").trigger("reloadGrid");
	$("#popupChgProdInfoMonthlyFeeGrid").trigger("reloadGrid");
}


/**
 * 진행중인 오더 정보 조회
 */
function getOrderInfoInProgress(){
	if($('#common_orderStat').val() != '01' && $('#common_orderStat').val() != '02'){
		return;
	}
	var url = '/customer/contract/contract/orderManagement/getOrderInfoDeviceChangeInProgressAction.json';
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
	//단말상품리스트설정
	$("#popupChgProdInfoDeviceProdListGrid").clearGridData();
	$("#popupChgProdInfoDeviceProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridDeviceProdList,
        rowNum : data.gridDeviceProdList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfoDeviceProdListGrid").trigger("reloadGrid");
	//단말상품설정
	$("#popupChgProdInfoDeviceConfProdListGrid").clearGridData();
	$("#popupChgProdInfoDeviceConfProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridDeviceConfList,
        rowNum : data.gridDeviceConfList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfoDeviceConfProdListGrid").trigger("reloadGrid");

	//월정액설정
	$("#popupChgProdInfoMonthlyFeeGrid").clearGridData();
	$("#popupChgProdInfoMonthlyFeeGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridMonthlyFeeList,
        rowNum : data.gridMonthlyFeeList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfoMonthlyFeeGrid").trigger("reloadGrid");

	$("#popupChgProdInfoOnetimeFeeGrid").clearGridData();
	$("#popupChgProdInfoOnetimeFeeGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridOnetimeFeeList,
        rowNum : data.gridOnetimeFeeList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfoOnetimeFeeGrid").trigger("reloadGrid");

	var delDeviceList = data.delDeviceList;
	$.each(delDeviceList, function(index, value){
		var rowId = findRow($("#popupChgProdInfoDeviceUseProdListGrid"), "prod_cmps_id", value.prod_cmps_id, 0);
		$("#popupChgProdInfoDeviceUseProdListGrid").setSelection(rowId,  false);
	});

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

	//추가상품 정보
	var addDeviceProdList = [];
	var addDeviceConfIds = $('#popupChgProdInfoDeviceConfProdListGrid').getDataIDs();
	var deviceIdx = 0;
	$.each(addDeviceConfIds, function(index, value){
		var confValue = $('#popupChgProdInfoDeviceConfProdListGrid').getRowData(value);
		var addDeviceInfo = new Object();

		var prodKey = confValue.device_prod_cd + value;
		console.log("DEVICE KEY : " + prodKey);
		
		var monthlyData = $('#popupChgProdInfoMonthlyFeeGrid').getRowData(prodKey);
		var onetimeData = $('#popupChgProdInfoOnetimeFeeGrid').getRowData(prodKey);
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
	var delProdInfoIds = $("#popupChgProdInfoDeviceUseProdListGrid").jqGrid('getGridParam', 'selarrrow');
	var delIndex = 0;
	$.each(delProdInfoIds, function(index, value){
		var delProd = $('#popupChgProdInfoDeviceUseProdListGrid').getRowData(value);
		var delDeviceInfo = new Object();
		delDeviceInfo.prodCd = delProd.device_prod_cd;
		delDeviceInfo.prodCdNm = delProd.device_prod_cd_nm;
		delDeviceInfo.prodCmpsId = delProd.prod_cmps_id;
		delDeviceProdList[delIndex++] = delDeviceInfo;
	});

	orderRequestInfoVo.delDeviceProdList = delDeviceProdList;
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

<div class='main_btn_box'>
	<div class='fl'>
		<h4 class='sub_title'><spring:message code="LAB.M07.LAB00310"/></h4>
	</div>
</div>
<ul id='popupChgProdInfoTabs' class="tabs">
	<li id="popupChgProdInfo_addProdInfo" class="active" rel="popupChgProdInfo_addProdInfoDiv"><spring:message code="LAB.M07.LAB00334"/></li>
	<li id="popupChgProdInfo_delProdInfo" rel="popupChgProdInfo_delProdInfoDiv"><spring:message code="LAB.M07.LAB00335"/></li>
	<li id="popupChgProdInfo_negoAmtInfo" rel="popupChgProdInfo_negoAmtInfoDiv"><spring:message code="LAB.M14.LAB00078"/></li>
</ul>
<div class="tab_box table_col_box h300">
	<div class='inpCaution'>
		<span class='dot'>*</span>
		<spring:message code="LAB.M13.LAB00034"/> 
	</div>
	<!-- 추가 장비상품 시작-->
	<div id="popupChgProdInfo_addProdInfoDiv" class="tab_content" >
		<div class='col_left twinTable'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M09.LAB00221"/></span> <!--전체장비상품 --> 
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupChgProdInfoDeviceProdListGridDiv'> <!-- 장비상품그리드 -->
				<table id="popupChgProdInfoDeviceProdListGrid" class="w100p"></table>
			</div>
		</div><!--//left-->
		<div class='col_right twinTable'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M09.LAB00222"/></span> <!-- 장비상품설정 -->
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupChgProdInfoDeviceConfProdListGridDiv'> <!-- 장비상품설정그리드 -->
				<table id="popupChgProdInfoDeviceConfProdListGrid" class="w100p"></table>
			</div>
		</div><!--//right-->
		<div class='tableBtn'>
			<a class="white-btn" id="popupChgProdInfo_deviceProdInfo_btnRight" href="#">
				<span class="next_icon"></span>
			</a>
			<a class="white-btn" id="popupChgProdInfo_deviceProdInfo_btnLeft" href="#">
				<span class="prev_icon"></span>
			</a>
		</div>
		<div class='btn_box cauption'>
			<span class='dot'>*</span><spring:message code="MSG.M15.MSG00040"/> <!-- 전체 장비상품에서 설정하려는 장비 선택 후 '▶'버튼을 클릭해주세요.-->
		</div>
	</div>

	<!-- 삭제 장비상품 시작-->
	<div id="popupChgProdInfo_delProdInfoDiv" class="tab_content" >
		<div class='btn_box'>
			<div class='tabSelect'>
				<span class='selectText'>- <spring:message code="LAB.M07.LAB00340"/></span> <!--사용장비상품 --> 
				<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
			</div>
		</div>
		<div id='popupChgProdInfoDeviceUseProdListGridDiv'> <!-- 사용장비상품그리드 -->
			<table id="popupChgProdInfoDeviceUseProdListGrid" class="w100p"></table>
		</div>
		<div class='btn_box cauption'>
			<span class='dot'>*</span><spring:message code="MSG.M07.MSG00133"/> 
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
			<div id='popupChgProdInfoMonthlyFeeGridDiv'> <!-- 월정액그리드 -->
				<table id="popupChgProdInfoMonthlyFeeGrid" class="w100p"></table>
			</div>
		</div><!--//left-->
		<div class='col_right'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M08.LAB00144"/></span> <!-- 일회성금액 -->
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupChgProdInfoOnetimeFeeGridDiv'> <!-- 일회성그리드 -->
				<table id="popupChgProdInfoOnetimeFeeGrid" class="w100p"></table>
			</div>
		</div><!--//right-->
		<div class='btn_box cauption'>
			<span class='dot'>*</span> <spring:message code="MSG.M15.MSG00038"/> <!-- 할인율 입력 또는 분납개월수 선택을 진행해주세요. -->
		</div>
	</div>
</div>


			

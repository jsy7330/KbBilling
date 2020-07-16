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
  	 
</style>
<script type="text/javascript">
$(document).ready(function() {
	
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
			{ label: '옵션 상품 코드', name: 'add_prod_cd', width : 100, align:"center",sortable :false},
			{ label: '옵션 상품 명', name: 'add_prod_nm', width : 200, align:"left",sortable:false},
			{ label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'cnt', width : 80, align:"center", editable:true, sortable :false},
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
	  * 부가상품그리드
	  */ 
	$("#popupChgProdInfoAddProdListGrid").jqGrid({
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
		loadonce : true,
		height: 170,
		sortable : true,
		rowNum : 10000,
		jsonReader: {
			repeatitems : true,
			root : "addProdList"
		},
        onCellSelect : function(rowid, index, contents, event){

        },
        loadComplete : function () {
        	$("#popupChgProdInfoAddProdListGrid").setGridWidth($('#popupChgProdInfoAddProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfoAddProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    },
    	sortable: { 
    		update: function(permutation) {
	    		$("#popupChgProdInfoAddProdListGrid").setGridWidth($('#popupChgProdInfoAddProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

 
	/**
	  * 부가상품설정그리드
	  */ 
	$("#popupChgProdInfoAddConfProdListGrid").jqGrid({
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
					{ label:'상세정보등록', name: 'add_prod_cd', width:80, fixed:true,align:'center', formatter:formatOpt2}
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
        	$("#popupChgProdInfoAddConfProdListGrid").setGridWidth($('#popupChgProdInfoAddConfProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfoAddConfProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    }
	});

	/**
	  * 삭제 부가 상품그리드
	  */ 
	$("#popupChgProdInfoAddUseProdListGrid").jqGrid({
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
				{ label: 'prodCmpsId' , name: 'prod_cmps_id', hidden:true,width : 0, sortable :false},
				{ label: '옵션 상품 코드', name: 'add_prod_cd', width : 100, align:"center",sortable :false},
				{ label: '옵션 상품 코드', name: 'add_prod_nm', width : 100, align:"left",sortable:false},
				{ label: '<spring:message code="LAB.M07.LAB00247"/>', name: 'cnt', width : 80, align:"center", editable:true, sortable :false},
			    { label: '<spring:message code="LAB.M13.LAB00033"/>', name: 'add_prod_mandatory_yn_nm', width : 80, align:"center",sortable :false},
			    { label: '<spring:message code="LAB.M14.LAB00078"/>', name: 'is_nego_nm', width : 120, align:"center", sortable :false}
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
			root : "addUseProdList"
		},
        onCellSelect : function(rowid, index, contents, event){
        },
        onSelectRow : function(rowid, status, e){
        	var rowData = $('#popupChgProdInfoAddUseProdListGrid').getRowData(rowid);
        	if(status == true){
        		return true;
        	}
        	if(rowData.is_dup_yn == 'N'){ //중복 불가 인 경우 삭제 처리
				var ids = $('#popupChgProdInfoAddConfProdListGrid').getDataIDs();
				$.each(ids, function(index, value){
					var confData = $('#popupChgProdInfoAddConfProdListGrid').getRowData(value);
					if(rowData.add_prod_cd == confData.add_prod_cd){
						deleteRow('popupChgProdInfoAddConfProdListGrid', value);
						return false;
					}
				});
        	}else if(rowData.is_dup_yn == 'Y'){ //종복 허용인 경우 수량 체크해서 return;
        		if(rowData.dup_cnt != null && rowData.dup_cnt != '' && rowData.dup_cnt != '0'){ //미설정인 경우 무제한
        			var ids = $('#popupChgProdInfoAddConfProdListGrid').getDataIDs();
					var checkCnt = 0;
					$.each(ids, function(index, value){
						var checkData = $('#popupChgProdInfoAddConfProdListGrid').getRowData(value);
						if(checkData.add_prod_cd == rowData.add_prod_cd){
							checkCnt = checkCnt + 1;
						}
					});
					var useIds = $('#popupChgProdInfoAddUseProdListGrid').getDataIDs();
					$.each(useIds, function(index, value){
						var useRow = $('#popupChgProdInfoAddUseProdListGrid').getRowData(value);
						if(rowData.add_prod_cd == useRow.add_prod_cd){
							checkCnt = checkCnt + 1;
						}
					});
					var checkIds=$("#popupChgProdInfoAddUseProdListGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
					$.each(checkIds, function(idx, val){
						var checkRowData = $('#popupChgProdInfoAddUseProdListGrid').getRowData(val);
						if(rowData.add_prod_cd == checkRowData.add_prod_cd){
							checkCnt = checkCnt - 1;
						}
					});
					//허용초과
					if(rowData.dup_cnt < checkCnt){
						$("#popupChgProdInfoAddUseProdListGrid").setSelection(rowid,  false);
						alert('<spring:message code="MSG.M08.MSG00085" arguments="' + rowData.dup_cnt + '"/>'); 
					}
				}
        	}
        },
        loadComplete : function () {
        	$("#popupChgProdInfoAddUseProdListGrid").setGridWidth($('#popupChgProdInfoAddUseProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#popupChgProdInfoAddUseProdListGrid").trigger("reloadGrid");
        },
        loadError: function (jqXHR, textStatus, errorThrown) {
         	ajaxErrorCallback(jqXHR);
	    },
    	sortable: { 
    		update: function(permutation) {
	    		$("#popupChgProdInfoAddUseProdListGrid").setGridWidth($('#popupChgProdInfoAddUseProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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
			$("#popupChgProdInfoAddProdListGrid").setGridWidth($('#popupChgProdInfoAddProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			$("#popupChgProdInfoAddConfProdListGrid").setGridWidth($('#popupChgProdInfoAddConfProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 1){
			$("#popupChgProdInfoAddUseProdListGrid").setGridWidth($('#popupChgProdInfoAddUseProdListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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

	//부가상품 추가 이벤트
	$('#popupChgProdInfo_addProdInfo_btnRight').on('click',function (e) {
		if($("#popupChgProdInfo_addProdInfo_btnRight").hasClass('not-active')){
			return;
		}
	    var selectedRowId = $('#popupChgProdInfoAddProdListGrid').jqGrid('getGridParam','selrow');
	
		if(selectedRowId == null){
			return;
		}
		var addRow = $('#popupChgProdInfoAddProdListGrid').getRowData(selectedRowId);
		if(addRow.is_dup_yn == 'N'){ //중복 구매 불가인 경우
			var ids = $('#popupChgProdInfoAddUseProdListGrid').getDataIDs();
			var isDup = false;
			$.each(ids, function(index, value){
				var rowData = $('#popupChgProdInfoAddUseProdListGrid').getRowData(value);
				if(addRow.add_prod_cd == rowData.add_prod_cd){
					isDup = true;
					return false;
				}
			});

			var isChecked = false;
			if(isDup){
				var checkIds=$("#popupChgProdInfoAddUseProdListGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
				$.each(checkIds, function(idx, val){
					var checkRowData = $('#popupChgProdInfoAddUseProdListGrid').getRowData(val);
					if(addRow.add_prod_cd == checkRowData.add_prod_cd){
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
				var targetIds = $('#popupChgProdInfoAddConfProdListGrid').getDataIDs();
				$.each(targetIds, function(index, value){
					var rowData = $('#popupChgProdInfoAddConfProdListGrid').getRowData(value);
					if(addRow.add_prod_cd == rowData.add_prod_cd){
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
				var ids = $('#popupChgProdInfoAddConfProdListGrid').getDataIDs();
				var checkCnt = 0;
				$.each(ids, function(index, value){
					var rowData = $('#popupChgProdInfoAddConfProdListGrid').getRowData(value);
					if(addRow.add_prod_cd == rowData.add_prod_cd){
						checkCnt = checkCnt + 1;
					}
				});
				var useIds = $('#popupChgProdInfoAddUseProdListGrid').getDataIDs();
				$.each(useIds, function(index, value){
					var useRow = $('#popupChgProdInfoAddUseProdListGrid').getRowData(value);
					if(addRow.add_prod_cd == useRow.add_prod_cd){
						checkCnt = checkCnt + 1;
					}
				});

				var checkIds=$("#popupChgProdInfoAddUseProdListGrid").jqGrid('getGridParam', 'selarrrow');	//체크된 row id들을 배열로 반환
				$.each(checkIds, function(idx, val){
					var checkRowData = $('#popupChgProdInfoAddUseProdListGrid').getRowData(val);
					if(addRow.add_prod_cd == checkRowData.add_prod_cd){
						checkCnt = checkCnt - 1;
					}
				});
				if(addRow.dup_cnt <= checkCnt){
					alert('<spring:message code="MSG.M08.MSG00085" arguments="' + addRow.dup_cnt + '"/>'); 
					return false;
				}
			}
		}
		moveAddRightWithoutCheck('popupChgProdInfoAddProdListGrid', 'popupChgProdInfoAddConfProdListGrid');
	});

	//부가상품 삭제 이벤트
	$('#popupChgProdInfo_addProdInfo_btnLeft').on('click',function (e) {
		if($("#popupChgProdInfo_addProdInfo_btnLeft").hasClass('not-active')){
			return;
		}
		var rowid = $('#popupChgProdInfoAddConfProdListGrid').jqGrid("getGridParam", "selrow");
		var rowData = $('#popupChgProdInfoAddConfProdListGrid').getRowData(rowid);
		if(rowData.is_mandatory_yn == 'Y'){ //필수 항목 삭제 불가
			alert('<spring:message code="MSG.M13.MSG00028"/>');
			return false;
		}

		deleteRow('popupChgProdInfoAddConfProdListGrid', rowid);
		var prodKey = rowData.add_prod_cd + rowid;
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
	$("#popupProdInfo_basicProdInfo_ctrtNm").append(data.basicProdInfo.ctrt_nm);

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
	$("#popupChgProdInfoAddConfProdListGrid").clearGridData();
	$("#popupChgProdInfoAddConfProdListGrid").trigger("reloadGrid");
	//협정가 초기화
	$("#popupChgProdInfoMonthlyFeeGrid").clearGridData();
	$("#popupChgProdInfoMonthlyFeeGrid").trigger("reloadGrid");
	$("#popupChgProdInfoOnetimeFeeGrid").clearGridData();
	$("#popupChgProdInfoOnetimeFeeGrid").trigger("reloadGrid");

	//전체상품조회
	$("#popupChgProdInfoAddProdListGrid").clearGridData();
	$("#popupChgProdInfoAddProdListGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			soId : $("#common_soId").val(),
        	basicProdGrp : data.basicProdInfo.prod_grp_cd,
        	basicProdCd : data.basicProdInfo.prod_cd,
			StartDt : $("#StartDt").val().replace(/-/g,"")
		}
	});
	$("#popupChgProdInfoAddProdListGrid").trigger("reloadGrid");
	
	//사용중인 부가상품정보 조회
	$("#popupChgProdInfoAddUseProdListGrid").setGridParam({
		data: data.addInfoList
		,rowNum : data.addInfoList.length
	});
   	$("#popupChgProdInfoAddUseProdListGrid").trigger("reloadGrid");

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
 * 부가설정정보 Update
 */
function updateAddonConfByCnt(value, cnt){
	var rowData = $("#popupChgProdInfoAddConfProdListGrid").getRowData(value);
	if(cnt == null || cnt =='' || cnt == '0'){
		cnt = 1;	
	}
	var onetime_fee = rowData.org_onetime_fee * cnt;
	var monthly_fee = rowData.org_monthly_fee * cnt;
	$('#popupChgProdInfoAddConfProdListGrid').jqGrid('setCell', value, "add_cnt" , cnt);
    $('#popupChgProdInfoAddConfProdListGrid').jqGrid('setCell', value, "onetime_fee" , onetime_fee);
    $('#popupChgProdInfoAddConfProdListGrid').jqGrid('setCell', value, "monthly_fee" , monthly_fee);
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

	//월정액 부가서비스 처리
	var addAddConfRowIds = $('#popupChgProdInfoAddConfProdListGrid').getDataIDs();
	$.each(addAddConfRowIds, function(index, value){
		var rowData = $('#popupChgProdInfoAddConfProdListGrid').getRowData(value);
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
		var preRow = $('#popupChgProdInfoMonthlyFeeGrid').getInd(prodKey);
		if(preRow != false){
			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupChgProdInfoMonthlyFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.add_cnt){
				console.log("******************* 3. 월정액 부가 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
				console.log("******************* 4. 월정액 부가 처리 : " + prodKey);	
			    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.add_cnt);
			    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.monthly_fee);
			    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupChgProdInfoMonthlyFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.monthly_fee);
			    $("#popupChgProdInfoMonthlyFeeGrid").trigger("reloadGrid");
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
			var selOrgListCnt = $('#popupChgProdInfoMonthlyFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfoMonthlyFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfoMonthlyFeeGrid').addRowData(undefined,row);
			$("#popupChgProdInfoMonthlyFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 월정액 부가 처리 : " + prodKey + "/ " + selOrgListCnt);	
		}
	});

	//일회성 부가 처리
	$.each(addAddConfRowIds, function(index, value){
		var rowData = $('#popupChgProdInfoAddConfProdListGrid').getRowData(value);
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
		var preRow = $('#popupChgProdInfoOnetimeFeeGrid').getInd(prodKey);
		if(preRow != false){

			//이전과 금액이 같은 경우 리턴
			var preRowData = $('#popupChgProdInfoOnetimeFeeGrid').getRowData(prodKey);
			if(preRowData.nego_cnt == rowData.add_cnt){
				console.log("******************* 3. 일회성 부가 처리 : " + prodKey);	
				return true;
			}else{ //이전 금액과 다른 경우 수정
			    $('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', prodKey, "nego_cnt" , rowData.add_cnt);
			    $('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', prodKey, "sale_amt" , rowData.onetime_fee);
			    $('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', prodKey, "discount_rate" , '0');
			    $('#popupChgProdInfoOnetimeFeeGrid').jqGrid('setCell', prodKey, "nego_amt" , rowData.onetime_fee);
			    $("#popupChgProdInfoOnetimeFeeGrid").trigger("reloadGrid");
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
			var selOrgListCnt = $('#popupChgProdInfoOnetimeFeeGrid').getGridParam("records") + 1;
			$('#popupChgProdInfoOnetimeFeeGrid').setGridParam({
		 		rowNum: selOrgListCnt
			});
			$('#popupChgProdInfoOnetimeFeeGrid').addRowData(undefined,row);
			$("#popupChgProdInfoOnetimeFeeGrid").trigger("reloadGrid");
			console.log("******************* 5. 일회성 부가 처리 : " + prodKey + "/ " + selOrgListCnt);	
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
	var addProdInfoIds = $('#popupChgProdInfoAddConfProdListGrid').getDataIDs();
	if(addProdInfoIds.length != 0){
		isAddProd = true;
	}
	var delProdInfoIds = $("#popupChgProdInfoAddUseProdListGrid").jqGrid('getGridParam', 'selarrrow');
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

	$('#popupChgProdInfoAddProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupChgProdInfoAddProdListGrid');
	$("#popupChgProdInfoAddProdListGrid").trigger("reloadGrid");


	//추가부가
	$('#popupChgProdInfoAddConfProdListGrid').jqGrid("resetSelection");
	disable_popup_grid('popupChgProdInfoAddConfProdListGrid');
	$("#popupChgProdInfoAddConfProdListGrid").trigger("reloadGrid");
	//삭제부가
	disable_popup_grid('popupChgProdInfoAddUseProdListGrid');

	$("#popupChgProdInfoAddUseProdListGridDiv input:checkbox").addClass('not-active');
	//$("#popupChgProdInfoAddUseProdListGridDiv input:checkbox").attr('disabled',true);

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
	var url = '/customer/contract/contract/orderManagement/getOrderInfoAddChangeInProgressAction.json';
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
	//부가상품리스트설정
	$("#popupChgProdInfoAddProdListGrid").clearGridData();
	$("#popupChgProdInfoAddProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridAddProdList,
        rowNum:data.gridAddProdList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfoAddProdListGrid").trigger("reloadGrid");
	//부가상품설정
	$("#popupChgProdInfoAddConfProdListGrid").clearGridData();
	$("#popupChgProdInfoAddConfProdListGrid").setGridParam({
		datatype : "jsonstring",
        datastr: data.gridAddConfList,
        rowNum : data.gridAddConfList.length,
        jsonReader: { repeatitems: false }
	});
	$("#popupChgProdInfoAddConfProdListGrid").trigger("reloadGrid");

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

	var delAddonList = data.delAddonList;
	$.each(delAddonList, function(index, value){
		var rowId = findRow($("#popupChgProdInfoAddUseProdListGrid"), "prod_cmps_id", value.prod_cmps_id, 0);
		$("#popupChgProdInfoAddUseProdListGrid").setSelection(rowId,  false);
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
	
	alert("저장11111");
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

	//부가상품 정보
	var addAddonProdList = [];
	var addAddConfIds = $('#popupChgProdInfoAddConfProdListGrid').getDataIDs();
	var addIdx = 0;
	$.each(addAddConfIds, function(index, value){
		var confValue = $('#popupChgProdInfoAddConfProdListGrid').getRowData(value);

		var addonProdInfo = new Object();

		var prodKey = confValue.add_prod_cd + value;
		console.log("ADDON KEY : " + prodKey);
		
		var monthlyData = $('#popupChgProdInfoMonthlyFeeGrid').getRowData(prodKey);
		var onetimeData = $('#popupChgProdInfoOnetimeFeeGrid').getRowData(prodKey);

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

		addonProdInfo.rtId = $("#"+confValue.add_prod_cd_1+'_RT_ID_A').val();
		addonProdInfo.osTyp = $("#"+confValue.add_prod_cd_1+'_OS_TYP_A').val();
		addonProdInfo.cnt =$("#"+confValue.add_prod_cd_1+'_CNT_A').val();

		if($("#SVC_LVL").val() == null || $("#SVC_LVL").val() ==""){
			addonProdInfo.svcLvl = "*";
		}else{
			addonProdInfo.svcLvl = $("#SVC_LVL").val();
		}

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
	var delProdInfoIds = $("#popupChgProdInfoAddUseProdListGrid").jqGrid('getGridParam', 'selarrrow');
	var delIndex = 0;
	$.each(delProdInfoIds, function(index, value){
		var delProd = $('#popupChgProdInfoAddUseProdListGrid').getRowData(value);
		var delAddInfo = new Object();
		delAddInfo.prodCd = delProd.add_prod_cd;
		delAddInfo.prodCdNm = delProd.add_prod_cd_nm;
		delAddInfo.prodCmpsId = delProd.prod_cmps_id;
		delAddProdList[delIndex++] = delAddInfo;
	});

	orderRequestInfoVo.delAddonProdList = delAddProdList;
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
function formatOpt2(cellvalue, options, rowObject){ 

	var str = "";
	var val1 = "";	
	for(i=0; i<cellvalue.length; i++){
		val1 +=cellvalue[i];
	}	
	val1 = "'"+val1+"'";	
	str += '<a class="grey-btn" href="javascript:getinputList2('+val1+');"><span>추가</span></a>';
	return str;

}

 function getinputList2(prodCd){

		var url="/system/sample/sample/example/inputInsertPopUp.ajax";
		var basicYn = "B"; 

		$(parent.location).attr("href", "javascript:openModal2('" + url + "','" + prodCd + "','" + basicYn + "');");
 }

 function openModal2(url, add_prod_cd, basicYn) {
	var param = new Object();
	param.prodCd = add_prod_cd;
	param.basicYn = "B";
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
			
<div class='main_btn_box'>
	<div class='fl'>
		<h4 class='sub_title'><spring:message code="LAB.M07.LAB00308"/></h4>
	</div>
</div>

<div class='main_btn_box'>
	<div class='fl'>
		<!-- <h4 class='sub_title'>견적기준일자 : <input id="StartDt" type='text'/></h4> -->
		<h4 class='sub_title'>견적기준일자 : <input id="StartDt" type='text'/>&nbsp;&nbsp;<a href="javascript:estimateView();">견적서보기</a></h4> 
	</div>
</div>

<ul id='popupProdInfoTabs' class="tabs">
	<li id="popupProdInfo_basicProdInfo" class="active" rel="popupProdInfo_basicProdInfoDiv"><spring:message code="LAB.M01.LAB00210"/></li> <!-- 기본상품 -->
	<li id="popupProdInfo_deviceProdInfo" rel="popupProdInfo_deviceProdInfoDiv"><spring:message code="LAB.M09.LAB00042"/></li> <!-- 장비상품 -->
	<li id="popupProdInfo_addonProdInfo" rel="popupProdInfo_addonProdInfoDiv">옵션</li> <!-- 부가서비스 -->
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
				<tr class='col2'>
					<th>HOST NAME</th>
					<td> <div id="popupProdInfo_basicProdInfo_ctrtNm" class="inp_date np custInfoCls"></div></td>
					<th></th>
					<td></td>
				</tr>
			</tbody>
		</table>
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
	<!-- 추가 부가상품 시작-->
	<div id="popupChgProdInfo_addProdInfoDiv" class="tab_content" >
		<div class='col_left twinTable'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M09.LAB00224"/></span> <!--전체부가서비스--> 
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupChgProdInfoAddProdListGridDiv'> <!-- 장비상품그리드 -->
				<table id="popupChgProdInfoAddProdListGrid" class="w100p"></table>
			</div>
		</div><!--//left-->
		<div class='col_right twinTable'>
			<div class='btn_box'>
				<div class='tabSelect'>
					<span class='selectText'>- <spring:message code="LAB.M06.LAB00120"/></span> <!-- 부가서비스설정 -->
					<span class='rtText'><spring:message code="LAB.M15.LAB00104"/></span> <!-- 단위 -->
				</div>
			</div>
			<div id='popupChgProdInfoAddConfProdListGridDiv'> <!-- 장비상품설정그리드 -->
				<table id="popupChgProdInfoAddConfProdListGrid" class="w100p"></table>
			</div>
		</div><!--//right-->
		<div class='tableBtn'>
			<a class="white-btn" id="popupChgProdInfo_addProdInfo_btnRight" href="#">
				<span class="next_icon"></span>
			</a>
			<a class="white-btn" id="popupChgProdInfo_addProdInfo_btnLeft" href="#">
				<span class="prev_icon"></span>
			</a>
		</div>
		<div class='btn_box cauption'>
			<span class='dot'>*</span><spring:message code="MSG.M09.MSG00064"/> <!-- 전체 부가상품에서 설정하려는 장비 선택 후 '▶'버튼을 클릭해주세요.-->
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
		<div id='popupChgProdInfoAddUseProdListGridDiv'> <!-- 사용장비상품그리드 -->
			<table id="popupChgProdInfoAddUseProdListGrid" class="w100p"></table>
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
<div id="popup_dialog2" class="Layer_wrap2" style="display: none;width:400px;" >
</div>
			
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
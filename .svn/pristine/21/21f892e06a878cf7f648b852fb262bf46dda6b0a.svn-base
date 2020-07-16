<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	
	popupPageInit();
	
	$(".tab_content").hide();
	var _thisParent = $('ul.tabs').next('.tab_box');
	var _thisFirst = _thisParent.find('.tab_content:first');
	_thisFirst.show();
	
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
		url : '/customer/contract/contract/orderManagement/getAddonProdListAction.json',
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
	 *  정지 이력 그리드
	 */
	$("#popChgSuspentionListGrid").jqGrid({
		datatype: "local",
		colModel: [
			{ label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'act_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M09.LAB00168"/>', name: 'inact_dttm', width : 150, align:"center",formatter: stringTypeFormatterYYYYMMDDHH24MISS},
			{ label: '<spring:message code="LAB.M01.LAB00037"/>', name: 'ctrt_stat_nm', width : 80, align:"center"},
			{ label: '<spring:message code="LAB.M06.LAB00058"/>', name: 'ctrt_chg_resn_cd_nm', width : 150, align:"center"},
			{ label: '<spring:message code="LAB.M06.LAB00093"/>', name: 'remark', width : 250, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgr_nm', width : 150, align:"center"}
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
      	},
      	loadComplete : function () {
      		$("#popChgSuspentionListGrid").setGridWidth($('#popChgSuspentionInfo_suspentionListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
          	$("#popChgSuspentionListGrid").trigger("reloadGrid");
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
			$("#popupDeviceProdConfListGrid").setGridWidth($('#popupProdInfo_deviceProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 2){
			$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			$("#popupAddonProdConfListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonConfListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 3){
			
		}else if(index == 4){
			$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_monthlyFeeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}else if(index == 5){
			$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_onetimeChargeInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}
 	});

  	//비고 keyup 이벤트
  	$('#popupChgPauseInfo_remark').keyup(function(){
  		var str = getMaxStr($('#popupChgPauseInfo_remark').val(), 300);
  		if(str != $('#popupChgPauseInfo_remark').val()){
  			$('#popupChgPauseInfo_remark').val(str);
  		}
  	});

  	$("#popup_check_release").click(function () {	//정지해제 radio 버튼 클릭 막기
		return false;
	});

	//사용중인 상품 조회
 	getProdInfo();

 	//공통코드 조회
 	getCodeList();

 	//정지이력조회
 	getSusHist();
});

/**
 * 사용 중인 계약 정보 조회
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
 * 사용 중인 계약 정보 조회
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
  * 코드 리스트 조회
  */
function getCodeList(){
	//일시정지 코드 리스트 조회
	var url = '/customer/contract/contract/orderManagement/getSuspentionCodeListAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        },
        dataType: 'json',
        success: function(data){
        
			//정지사유
        	$('#popupChgPauseInfo_chgResn').each( function() {
		    	$('#popupChgPauseInfo_chgResn option:gt(0)').remove();
		    });
		    
        	$(data.resnList).each(function(index, item){
				var str = '<option value="'+ item.commonCd + '">'+ item.commonCdNm+'</option>';
				$('#popupChgPauseInfo_chgResn').append(str);  	
		    });
		    $('#popupChgPauseInfo_chgResn').val('SEL');
			$('#popupChgPauseInfo_chgResn').selectmenu("refresh");

			//정지 이력 조회
			//getSusHist();
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
}


/**
 * 정지이력 조회
 */
function getSusHist() {

	var url = '/customer/contract/contract/orderManagement/getCtrtSusHistAction.json';
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
        	//일시정지 이력 정보 조회
		   	$("#popChgSuspentionListGrid").setGridParam({
				data: data.suspentionHistList
				,rowNum: data.suspentionHistList.length
			});
		   	$("#popChgSuspentionListGrid").trigger("reloadGrid");
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });


}
/**
 * 계약 정보 세팅
 */
function setUsingCtrtInfo(data){
	//사용중인 기본상품정보 조회
	$("#popupProdInfo_basicProdInfo_prodGrp").append(data.basicProdInfo.prod_grp_nm);
	$("#popupProdInfo_basicProdInfo_rateStrtDt").append(stringToDateformatYYYYMMDD(data.basicProdInfo.rate_strt_dt));
	$("#popupProdInfo_basicProdInfo_prodNm").append(getNameAndId(data.basicProdInfo.prod_cd , data.basicProdInfo.prod_cd_nm));
	$("#popupProdInfo_basicProdInfo_actDttm").append(stringToDateformatYYYYMMDDHH24MISS(data.basicProdInfo.act_dttm));
	$("#popupProdInfo_basicProdInfo_ctrtStat").append(data.basicProdInfo.ctrt_stat_nm);
	$("#popupProdInfo_basicProdInfo_promPrd").append(data.basicProdInfo.prom_cnt+" "+'<spring:message code="LAB.M01.LAB00016"/>');
	
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
	
	$('#popChgSuspentionListGrid').jqGrid("resetSelection");
	disable_popup_grid('popChgSuspentionListGrid');
	
	getOrderInfoInProgress();
}

function getOrderInfoInProgress(){
	if($('#common_orderStat').val() != '01' && $('#common_orderStat').val() != '02'){
		return;
	}


	var url = '/customer/contract/contract/orderManagement/getOrderInfoSuspentionInProgressAction.json';
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
	
	$('#popChgSuspentionListGrid').jqGrid("resetSelection");
	disable_popup_grid('popChgSuspentionListGrid');

	$("#popupChgPauseInfo_chgResn").val(data.chngResn);
	$("#popupChgPauseInfo_startHopeDt").val(stringToDateformatYYYYMMDD(data.startHopeDt));
	$("#popupChgPauseInfo_endHopeDt").val(stringToDateformatYYYYMMDD(data.endHopeDt));
	$("#popupChgPauseInfo_remark").val(data.remark);
	
	
 	$("#popupChgPauseInfo_chgResn").selectmenu("refresh");
	$("#popupChgPauseInfo_chgResn").selectmenu("disable");
	$("#popupChgPauseInfo_remark").addClass('not-active');
	$("#popupChgPauseInfo_remark").attr('disabled',true);
	$(".btn_cal").addClass('not-active');
	$(".btn_cal").attr('disabled',true);
	//버튼제어
	btnDisable("popupRcptBtn_save");
	btnEnable("popupRcptBtn_cancel");
	btnEnable("popupRcptBtn_complete");
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
 * 오더 화면 Resize
 */
function commonPupupResize(){
	$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupMonthlfFeeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_montlyFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_onetimeFeeDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#popChgSuspentionListGrid").setGridWidth($('#popChgSuspentionInfo_suspentionListDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	$(".Layer_wrap select").selectmenu();
}

/**
 * Page Init
 */
function popupPageInit(){
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	if($("#popupChgPauseInfo_startHopeDt").length > 0){
		$( "#popupChgPauseInfo_startHopeDt" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "0"); 
	}
	
	if($("#popupChgPauseInfo_endHopeDt").length > 0){
		$( "#popupChgPauseInfo_endHopeDt" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "7"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
	
	$('#popupChgPauseInfo_startHopeDt').datepicker();
    $('#popupChgPauseInfo_startHopeDt').datepicker("option", "minDate", $("#popupChgPauseInfo_startHopeDt").val());
    $('#popupChgPauseInfo_startHopeDt').datepicker("option", "onClose", function ( selectedDate ) {
        $("#popupChgPauseInfo_endHopeDt").datepicker( "option", "minDate", selectedDate);
    });
 
    $('#popupChgPauseInfo_endHopeDt').datepicker();
    $('#popupChgPauseInfo_endHopeDt').datepicker("option", "minDate", $("#popupChgPauseInfo_startHopeDt").val());
    $('#popupChgPauseInfo_endHopeDt').datepicker("option", "onClose", function ( selectedDate ) {
        $("#popupChgPauseInfo_startHopeDt").datepicker( "option", "maxDate", selectedDate );
    });

    $("#popupChgPauseInfo_startHopeDt").addClass('not-active');
	$("#popupChgPauseInfo_startHopeDt").attr('disabled',true);
}

//오더 저장 체크
function precheckOrderSave(){
	if($("#popupChgPauseInfo_chgResn").val() == 'SEL'){
		var item = '<spring:message code="LAB.M07.LAB00311" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		$("#popupChgPauseInfo_chgResn-button").focus();
		return false;
	}
	
	if($("#popupChgPauseInfo_startHopeDt").val() == null || $("#popPauseFrom").val() == ''
			|| $("#popupChgPauseInfo_endHopeDt").val() == null || $("#popupChgPauseInfo_endHopeDt").val() == ''){
		var item = '<spring:message code="LAB.M08.LAB00190" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	return true;
}

/*
 * 오더 저장처리
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
	
	orderRequestInfoVo.reqUsrId = '${session_user.userId}';
	orderRequestInfoVo.reqOrgId = '${session_user.orgId}';
	orderRequestInfoVo.reqDesc = $('#rcptDesc').val();
	
	orderRequestInfoVo.chngResn = $("#popupChgPauseInfo_chgResn").val();
	orderRequestInfoVo.startHopeDt = dateFormatToStringYYYYMMDD($("#popupChgPauseInfo_startHopeDt").val());
	orderRequestInfoVo.endHopeDt = dateFormatToStringYYYYMMDD($("#popupChgPauseInfo_endHopeDt").val());
	orderRequestInfoVo.remark = $("#popupChgPauseInfo_remark").val();
	
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

// 화면 수정 불가 처리
function orderPopup_setDisable(){

	//일시정지 내역
	$('#popChgSuspentionListGrid').jqGrid("resetSelection");
	disable_popup_grid('popChgSuspentionListGrid');

	//일시정지 설정
	$('#popupChgPauseInfo_chgResn').selectmenu("disable");
	$("#popupChgPauseInfo_startHopeDt").addClass('not-active');
	$("#popupChgPauseInfo_startHopeDt").attr('disabled',true);
	$("#popupChgPauseInfo_endHopeDt").addClass('not-active');
	$("#popupChgPauseInfo_endHopeDt").attr('disabled',true);
	$("#popupChgPauseInfo_remark").addClass('not-active');
	$("#popupChgPauseInfo_remark").attr('disabled',true);
}
</script>

<!--  -->
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
						<spring:message code="LAB.M08.LAB00087"/>
					</th>
					<td>
						<div id="popupProdInfo_instAddrInfo_zipCd" class="inp_date np custInfoCls"></div>
					</td>
					<th>
						<spring:message code="LAB.M09.LAB00190"/>
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
<!-- //popupProdInfo_monthlyFeeInfoDiv -->
		<div id="popupProdInfo_monthlyFeeInfoDiv" class="tab_content"><!-- 월정액금액GRID -->
			<table id="popupMonthlyFeeGrid" class="w100p"></table>
		</div>
<!-- //popupProdInfo_onetimeChargeInfoDiv -->		
		<div id="popupProdInfo_onetimeChargeInfoDiv" class="tab_content"><!-- 일회성금액GRID -->
			<table id="popupOnetimeChargeGrid" class="w100p"></table>
		</div>
	</div><!--//tab_box-->
		<div class='main_btn_box'>
			<div class='fl'>
				<h4 class='sub_title'><spring:message code="LAB.M08.LAB00188"/></h4>
			</div>
		</div>
		<!-- -->
		<div class='table_col_box bdT h150'>
			<div class='col_left'>
				<div class='btn_box'>
					<div class='tabSelect'>
						<span class='selectText'>- <spring:message code="LAB.M08.LAB00182"/></span>
					</div>
				</div>
				<div id='popChgSuspentionInfo_suspentionListDiv'>
					<table id="popChgSuspentionListGrid" class="w100p"></table>
				</div>
			</div><!--//left-->
			<div class='col_right'>
				<div class='btn_box'>
					<div class='tabSelect'>
						<span class='selectText'>- <spring:message code="LAB.M08.LAB00188"/></span>
						<div class="inpCaution">
							<span class="dot">*</span>
							<spring:message code="LAB.M13.LAB00034"/> 
						</div>
					</div>
				</div>
				<table class='writeview'>
					<tbody>
						<tr class='col1'>
							<th><spring:message code="LAB.M01.LAB00164"/>
								<span class="dot">*</span>
							<td>
								<input type='radio' id='popup_check_suspention' name='suspention' required='required' checked='checked'/>
								<label for='popup_check_suspention'><spring:message code="LAB.M08.LAB00142"/></label>
								<input type='radio' id='popup_check_release' name='suspentionRelease' required='required'/>
								<label for='popup_check_release'><spring:message code="LAB.M09.LAB00223"/></label>
							</td>
						</tr>
						<tr class='col1'>
							<th><spring:message code="LAB.M08.LAB00190"/>
								<span class="dot">*</span>
							</th>
							<td>
								<div class="date_box">
									<div class="inp_date w45p">
							 			<input type="text" id="popupChgPauseInfo_startHopeDt" name="popupChgPauseInfo_startHopeDt" value='<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>' class='datepicker' readonly='readonly'><a href="#" class="btn_cal"></a> 
									</div>
									<span class="dash">~</span>
									<div class="inp_date w45p">
										<input type="text" id="popupChgPauseInfo_endHopeDt" name="popupChgPauseInfo_endHopeDt" value='<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>' class='datepicker' readonly='readonly'><a href="#" class="btn_cal"></a> 
									</div>
								</div>
							</td>
						</tr>
						<tr class='col1'>
							<th><spring:message code="LAB.M07.LAB00311"/>
								<span class="dot">*</span>
							</th>
							<td>
								<select id='popupChgPauseInfo_chgResn' class='w100p'>
									<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
								</select>
							</td>
						</tr>
						<tr class='col1'>
							<th><spring:message code="LAB.M06.LAB00093"/></th>
							<td>
								<div class='inp_date np'>
									<textarea id='popupChgPauseInfo_remark' placeholder='<spring:message code="MSG.M15.MSG00043"/>'></textarea>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div><!--//right-->
		</div>

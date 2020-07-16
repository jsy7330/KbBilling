<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	// TAB ì´ê¸°í
	$(".tab_content").hide();
	var _thisParent = $('ul.tabs').next('.tab_box');
	var _thisFirst = _thisParent.find('.tab_content:first');
	_thisFirst.show();
	
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "0"); 
	}
	//$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
	
	$('.datepicker').datepicker();
    $('.datepicker').datepicker("option", "minDate", $(".datepicker").val());
    $('.datepicker').datepicker("option", "onClose", function ( selectedDate ) {
        $(".datepicker1").datepicker( "option", "maxDate", selectedDate );
    });
	
    /**
	  * ì¥ë¹ìíê·¸ë¦¬ë
	  */ 
	$("#popupDeviceProdListGrid").jqGrid({
		//url : '/customer/contract/contract/orderManagement/getDeviceProdListAction.json',
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
	    		$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceListDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
	        $("#popupDeviceProdListGrid").trigger("reloadGrid");
	    },
	    loadError: function (jqXHR, textStatus, errorThrown) {
	     	ajaxErrorCallback(jqXHR);
	    },
		sortable: { 
		update: function(permutation) {
	    		$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfo_deviceListDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
			}
		}
	});
	
	/**
	  * ë¶ê°ìíê·¸ë¦¬ë
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

    		$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonListDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
        	$("#popupAddonProdListGrid").trigger("reloadGrid");
    	},
    	loadError: function (jqXHR, textStatus, errorThrown) {
     	ajaxErrorCallback(jqXHR);
	        
	    },
		sortable: { update: function(permutation) {
			$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonListDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
		}
		}
	});

	
	/**
	  * ìì ì¡ì¤ì ê·¸ë¦¬ë
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
	  		$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_monthlyFeeInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
	      	$("#popupMonthlyFeeGrid").trigger("reloadGrid");
	  	},
	  	loadError: function (jqXHR, textStatus, errorThrown) {
	   		ajaxErrorCallback(jqXHR);
		}
	});
		
	/**
	  * ì¼íì±ìê¸ì¤ì ê·¸ë¦¬ë
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
    		$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_onetimeChargeInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
        	$("#popupOnetimeChargeGrid").trigger("reloadGrid");
    	},
    	loadError: function (jqXHR, textStatus, errorThrown) {
     	ajaxErrorCallback(jqXHR);
	        
	    }
	});
		
	
	/**
	  * ì¬ì©ìíì ë³´ Tab Event
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
			//$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
		}else if(index == 1){
			$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
			$("#popupDeviceProdConfListGrid").setGridWidth($('#popupProdInfo_deviceProdInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
		}else if(index == 2){
			$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
			$("#popupAddonProdConfListGrid").setGridWidth($('#popupProdInfo_addonProdInfo_addonConfListDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
		}else if(index == 3){
			
		}else if(index == 4){
			$("#popupMonthlyFeeGrid").setGridWidth($('#popupProdInfo_monthlyFeeInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
		}else if(index == 5){
			$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_onetimeChargeInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
		}
	});

	getProdInfo();
	
	//í´ì§ì¬ì  ê³µíµì½ë ë¦¬ì¤í¸
	getCodeList();

	//ë¹ê³  keyup ì´ë²¤í¸
	$('#popupChgTerminationInfo_remark').keyup(function(){
		var str = getMaxStr($('#popupChgTerminationInfo_remark').val(), 300);
		if(str != $('#popupChgTerminationInfo_remark').val()){
			$('#popupChgTerminationInfo_remark').val(str);
		}
	});
	
	$("#popupChgTerminationInfo_ctrtTermCnsl").click(function () {	//í´ì§ì·¨ì radio ë²í¼ í´ë¦­ ë§ê¸°
		return false;
	});
});

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
 * ì½ë ë¦¬ì¤í¸ ì¡°í
 */
function getCodeList(){
	//ì¼ìì ì§ ì½ë ë¦¬ì¤í¸ ì¡°í
	var url = '/customer/contract/contract/orderManagement/getTerminationCodeListAction.json';
	$.ajax({
       url:url,
       type:'POST',
       data : {
       },
       dataType: 'json',
       success: function(data){
       
			//í´ì§ì¬ì 
       		$('#popupChgTerminationInfo_defResnCd').each( function() {
		    	$('#popupChgTerminationInfo_defResnCd option:gt(0)').remove();
		    });
		    
       		$(data.resnList).each(function(index, item){
				var str = '<option value="'+ item.commonCd + '">'+ item.commonCdNm+'</option>';
				$('#popupChgTerminationInfo_defResnCd').append(str);  	
		    });
		    $('#popupChgTerminationInfo_defResnCd').val('SEL');
			$('#popupChgTerminationInfo_defResnCd').selectmenu("refresh");

       	},
    	beforeSend: function(data){
    	},
    	error : function(err){
    		ajaxErrorCallback(err);
    	}
   });
}
function setUsingCtrtInfo(data){
	//ì¬ì©ì¤ì¸ ê¸°ë³¸ìíì ë³´ ì¡°í
	$("#popupProdInfo_basicProdInfo_prodGrp").append(data.basicProdInfo.prod_grp_nm);
	$("#popupProdInfo_basicProdInfo_rateStrtDt").append(stringToDateformatYYYYMMDD(data.basicProdInfo.rate_strt_dt));
	$("#popupProdInfo_basicProdInfo_prodNm").append(getNameAndId(data.basicProdInfo.prod_cd , data.basicProdInfo.prod_cd_nm));
	$("#popupProdInfo_basicProdInfo_actDttm").append(stringToDateformatYYYYMMDDHH24MISS(data.basicProdInfo.act_dttm));
	$("#popupProdInfo_basicProdInfo_ctrtStat").append(data.basicProdInfo.ctrt_stat_nm);
	$("#popupProdInfo_basicProdInfo_promPrd").append(data.basicProdInfo.prom_cnt+" "+'<spring:message code="LAB.M01.LAB00016"/>');
	
	//ì¬ì©ì¤ì¸ ì¥ë¹ìíì ë³´ ì¡°í
	$("#popupDeviceProdListGrid").setGridParam({
		data: data.deviceInfoList
		,rowNum : data.deviceInfoList.length
	});
	
   	$("#popupDeviceProdListGrid").trigger("reloadGrid");
   	
   	//ì¬ì©ì¤ì¸ ë¶ê°ìíì ë³´ì¡°í
   	$("#popupAddonProdListGrid").setGridParam({
		data: data.addInfoList
		,rowNum : data.addInfoList.length
	});
   	$("#popupAddonProdListGrid").trigger("reloadGrid");
   	
   	//ì¬ì©ì¤ì¸ ìì ì¡ ì¡°í
   	$("#popupMonthlyFeeGrid").setGridParam({
		data: data.monthlyFeeList
		,rowNum : data.monthlyFeeList.length
	});
   	$("#popupMonthlyFeeGrid").trigger("reloadGrid");
	
   	//ì¬ì©ì¤ì¸ ì¼íì± ì¡°í
	$("#popupOnetimeChargeGrid").setGridParam({
		data: data.oneTimeFeeList
		,rowNum : data.oneTimeFeeList.length
	});
   	$("#popupOnetimeChargeGrid").trigger("reloadGrid");
   
  	//ì¬ì©ì¤ì¸ ì¤ì¹ì£¼ìì ë³´ ì¡°í
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
	
	getOrderInfoInProgress();
}

function getOrderInfoInProgress(){
	if($('#common_orderStat').val() != '01' && $('#common_orderStat').val() != '02'){
		return;
	}
	var url = '/customer/contract/contract/orderManagement/getOrderInfoTerminationInProgressAction.json';
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
 * ì§í ì¤ë ì¤ì 
 */ 
function setOrderInfoInProgress(data){
	
	$("#popupChgTerminationInfo_defResnCd").val(data.chngResn);
	$("#popupChgPauseInfo_startHopeDt").val(stringToDateformatYYYYMMDD(data.startHopeDt));
	$("#popupChgPauseInfo_endHopeDt").val(stringToDateformatYYYYMMDD(data.endHopeDt));
	$("#popupChgTerminationInfo_remark").val(data.remark);
	
	
 	$("#popupChgTerminationInfo_defResnCd").selectmenu("refresh");
	$("#popupChgTerminationInfo_defResnCd").selectmenu("disable");
	$("#popupChgTerminationInfo_remark").addClass('not-active');
	$("#popupChgTerminationInfo_remark").attr('disabled',true);
	//$(".btn_cal").addClass('not-active');
	//$(".btn_cal").attr('disabled',true);
	//ë²í¼ì ì´
	btnDisable("popupRcptBtn_save");
	btnEnable("popupRcptBtn_cancel");
	btnEnable("popupRcptBtn_complete");
}

/*
 * ëªì¹­ í¬ë§·í
 */
function getNameAndId(id, name){
	if(name == '' || name == null){
		return id;
	}else{
		return name + '(' + id + ')'; 
	}
	 
}

//ì¤ë ì ì¥ ì²´í¬
function precheckOrderSave(){
	if($("#popupChgTerminationInfo_defResnCd").val() == 'SEL'){
		var item = '<spring:message code="LAB.M07.LAB00311" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		$("#popupChgTerminationInfo_defResnCd-button").focus();
		return false;
	}

	return true;
}
/*
ì¤ë ì ì¥ì²ë¦¬
*/
function popupOrderProcessSave(){
	
	//alert("ddd");
	
	var orderRequestInfoVo = new Object();

	//ê³µíµìì­
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
	
	orderRequestInfoVo.chngResn = $("#popupChgTerminationInfo_defResnCd").val();	//í´ì§ì¬ì ì½ë
	orderRequestInfoVo.termHopeDt = dateFormatToStringYYYYMMDD($("#popupChgTerminationInfo_termHopeDt").val());
	orderRequestInfoVo.remark = $("#popupChgTerminationInfo_remark").val();
	
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
			alert('<spring:message code="MSG.M07.MSG00084"/>');	 //ì±ê³µíìµëë¤.
	 	},		
	 	beforeSend: function(data){
    		},
	 	error : function(err){
      		ajaxErrorCallback(err);
      	}
	 });
	
}
/**
 * ì¤ë ìë£ ì²ë¦¬
 */
function popupOrderProcessCmpl(){
	var orderRequestInfoVo = new Object();

	//ê³µíµìì­
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
 * ì¤ë ì·¨ì ì²ë¦¬
 */
function popupOrderProcessCancel(){
	alert("해지저장");
	var orderRequestInfoVo = new Object();

	//ê³µíµìì­
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

// íë©´ ìì  ë¶ê° ì²ë¦¬
function orderPopup_setDisable(){

	//ê³ì½í´ì§ ì¤ì 
	$('#popupChgTerminationInfo_defResnCd').selectmenu("disable");
	$("#popupChgTerminationInfo_remark").addClass('not-active');
	$("#popupChgTerminationInfo_remark").attr('disabled',true);
	
}
/**
 * ì¤ë íë©´ Resize
 */
function commonPupupResize(){
	//$("#popupBasicProdListGrid").setGridWidth($('#popupProdInfo_basicProdInfo_basicProdListDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
	$("#popupDeviceProdListGrid").setGridWidth($('#popupProdInfo_deviceProdInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
	$("#popupAddonProdListGrid").setGridWidth($('#popupProdInfo_addonProdInfoDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
	$("#popupMonthlfFeeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_montlyFeeDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
	$("#popupOnetimeChargeGrid").setGridWidth($('#popupProdInfo_negoAmtInfo_onetimeFeeDiv').width(), false); //ê·¸ë¦¬ë íì´ë¸ì DIV(widht 100% : w100p)ë¡ ê°ì¸ì ì²ë¦¬
	
	$(".Layer_wrap select").selectmenu();
}
</script>
<!--  -->
		<div class='main_btn_box'>
			<div class='fl'>
				<h4 class='sub_title'><spring:message code="LAB.M07.LAB00308"/></h4>
			</div>
		</div>
		<ul id='popupProdInfoTabs' class="tabs">
			<li id="popupProdInfo_basicProdInfo" class="active" rel="popupProdInfo_basicProdInfoDiv"><spring:message code="LAB.M01.LAB00210"/></li> <!-- ê¸°ë³¸ìí -->
			<li id="popupProdInfo_deviceProdInfo" rel="popupProdInfo_deviceProdInfoDiv"><spring:message code="LAB.M09.LAB00042"/></li> <!-- ì¥ë¹ìí -->
			<li id="popupProdInfo_addonProdInfo" rel="popupProdInfo_addonProdInfoDiv"><spring:message code="LAB.M06.LAB00118"/></li> <!-- ë¶ê°ìë¹ì¤ -->
			<li id="popupProdInfo_instAddrInfo" rel="popupProdInfo_instAddrInfoDiv"><spring:message code="LAB.M07.LAB00301"/></li> <!-- ì¤ì¹ì£¼ì -->
			<li id="popupProdInfo_monthlyFeeInfo" rel="popupProdInfo_monthlyFeeInfoDiv"><spring:message code="LAB.M08.LAB00195"/></li> <!-- ìì ì¡ -->
			<li id="popupProdInfo_onetimeChargeInfo" rel="popupProdInfo_onetimeChargeInfoDiv"><spring:message code="LAB.M08.LAB00196"/></li> <!-- ì¼íì± -->
		</ul>
<!--  -->
		<div class="tab_box table_col_box h174">	<!-- ì¬ì©ìííí© -->
			<div id="popupProdInfo_basicProdInfoDiv" class="tab_content">
				<table class='writeview tdB'>
					<tbody>
						<tr class='col2'>
							<th><spring:message code="LAB.M07.LAB00307"/>111</th>	<!-- ìíê·¸ë£¹ -->
							<td><div id="popupProdInfo_basicProdInfo_prodGrp" class="inp_date np custInfoCls"></div></td>
							<th><spring:message code="LAB.M01.LAB00230"/></th><!-- ê³¼ê¸ììì¼ -->
							<td><div id="popupProdInfo_basicProdInfo_rateStrtDt" class="inp_date np custInfoCls"></div></td>
						</tr>
						<tr class='col2'>
							<th><spring:message code="LAB.M07.LAB00130"/>(<spring:message code="LAB.M07.LAB00156"/>)</th><!-- ìíëª(ìíì½ë) -->
							<td><div id="popupProdInfo_basicProdInfo_prodNm" class="inp_date np custInfoCls"></div>
							</td>
							<th><spring:message code="LAB.M07.LAB00287"/></th><!-- ììì¼ì -->
							<td><div id="popupProdInfo_basicProdInfo_actDttm" class="inp_date np custInfoCls"></div></td>
						</tr>
						<tr class='col2'>
							<th><spring:message code="LAB.M01.LAB00037"/></th><!-- ê³ì½ìí -->
							<td><div id="popupProdInfo_basicProdInfo_ctrtStat" class="inp_date np custInfoCls"></div></td>
							<th><spring:message code="LAB.M08.LAB00002"/></th><!-- ì½ì ê¸°ê° -->
							<td><div id="popupProdInfo_basicProdInfo_promPrd" class="inp_date np custInfoCls"></div></td>
						</tr>
					</tbody>
				</table>
			</div>
		
<!-- //popupProdInfo_basicProdInfoDiv -->
		<div id="popupProdInfo_deviceProdInfoDiv" class="tab_content"><!-- ê¸°ë³¸ìíë³ê²½ GRID -->
			<table id="popupDeviceProdListGrid" class="w100p"></table>
		</div>
<!-- //popupProdInfo_deviceProdInfoDiv -->
		<div id="popupProdInfo_addonProdInfoDiv" class="tab_content"><!-- ë¶ë©ê°ììGRID -->
			<table id="popupAddonProdListGrid" class="w100p"></table>
		</div>
<!-- //popupProdInfo_addonProdInfoDiv -->
		<div id="popupProdInfo_instAddrInfoDiv" class="tab_content">
			<table class='writeview column_2 row_1'>
				<tbody>
					<tr class='col2_left'>
						<th>
							<spring:message code="LAB.M08.LAB00087"/><!-- ì°í¸ë²í¸ -->
						</th>
						<td>
							<div id="popupProdInfo_instAddrInfo_zipCd" class="inp_date np custInfoCls"></div>
						</td>
						<th>
							<spring:message code="LAB.M09.LAB00190"/><!-- ì£¼ì -->
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
		<div id="popupProdInfo_monthlyFeeInfoDiv" class="tab_content"><!-- ìì ì¡ê¸ì¡GRID -->
			<table id="popupMonthlyFeeGrid" class="w100p"></table>
		</div>
<!-- //popupProdInfo_monthlyFeeInfoDiv -->		
		<div id="popupProdInfo_onetimeChargeInfoDiv" class="tab_content"><!-- ì¼íì±ê¸ì¡GRID -->
			<table id="popupOnetimeChargeGrid" class="w100p"></table>
		</div>
<!-- //popupProdInfo_onetimeChargeInfoDiv -->
	</div>

	<div class='main_btn_box'>
		<div class='fl'>
			<h4 class='sub_title'><spring:message code="LAB.M01.LAB00241"/></h4>
		</div>
	</div>
	<!-- -->
	<table class='writeview tdB column_1 row_2'>
		<tbody>
			<tr class='col1'>
				<th><spring:message code="LAB.M01.LAB00164"/>
					<span class='dot'>*</span>
				</th>
				<td>
					<input type='radio' id='popupChgTerminationInfo_ctrtTerm' name='popupChgTerminationInfo_ctrtTermConf' required='required' checked='checked'/>
					<label for='popupChgTerminationInfo_ctrtTermConf'><spring:message code="LAB.M01.LAB00242"/></label>
					<input type='radio' id='popupChgTerminationInfo_ctrtTermCnsl' name='popupChgTerminationInfo_ctrtTermConf' required='required'/>
					<label for='popupChgTerminationInfo_ctrtTermConf'><spring:message code="LAB.M14.LAB00081"/></label>
				</td>
			</tr>
			<tr class='col1'>
				<th><spring:message code="LAB.M07.LAB00311"/>
					<span class='dot'>*</span>
				</th>
				<td>
					<select id="popupChgTerminationInfo_defResnCd" class='w40p'>
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</select>
				</td>
			</tr>
			<tr class='col1'>
				<th><spring:message code="LAB.M14.LAB00079"/>
					<span class='dot'>*</span>
				</th>
				<td>
					<div class='inp_date w20p'>
						<input type='text'id="popupChgTerminationInfo_termHopeDt" class='datepicker' readonly='readonly' required='required'/>
						<a href='#' class='btn_cal'></a>
					</div>
				</td>
			</tr>
			<tr class='col1'>
				<th><spring:message code="LAB.M06.LAB00093"/></th>
				<td>
					<div class='inp_date np'>
						<textarea id="popupChgTerminationInfo_remark" placeholder='<spring:message code="MSG.M15.MSG00043"/>'></textarea>
					</div>
				</td>
			</tr>
		</tbody>
	</table>

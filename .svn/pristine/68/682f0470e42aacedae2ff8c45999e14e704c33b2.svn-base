<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	$(".Layer_wrap select").selectmenu();
	var soId = '${soId}';
	var custId = '${custId}';
	var ctrtId = '${ctrtId}';
	var pymAcntId = '${pymAcntId}';

	/**
	  * 청구리스트
	  */ 
	$("#popup_billingListGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
			{ label: 'billSeq' , name: 'bill_seq_no', hidden:true,width : 0, sortable :false},
			{ label: '<spring:message code="LAB.M10.LAB00033"/>', name: 'bill_yymm', width : 120, align:"center",formatter: stringTypeFormatterYYYYMM},
		    { label: '<spring:message code="LAB.M10.LAB00040"/>', name: 'bill_dt', width : 80, align:"center",formatter: stringTypeFormatterYYYYMMDD},
		    { label: '<spring:message code="LAB.M02.LAB00006"/>', name: 'pym_acnt_id', width : 120, align:"left"},
		    { label: '<spring:message code="LAB.M10.LAB00058"/>', name: 'adj_prv_bill_amt', width : 200, align:"right", formatter : 'integer',sorttype:'number'},
		    { label: '<spring:message code="LAB.M09.LAB00134"/>', name: 'adj_amt', width : 130, align:"right", formatter : 'integer',sorttype:'number'},
		    { label: '<spring:message code="LAB.M10.LAB00031"/>', name: 'bill_amt', width : 130, align : "right", formatter : 'integer', sorttype:'number'},
		    { label: '<spring:message code="LAB.M07.LAB00237"/>', name: 'rcpt_amt', width : 130, align : "right", formatter : 'integer', sorttype:'number'},
		    { label: '<spring:message code="LAB.M02.LAB00003"/>', name: 'pay_due_dt', width : 120, align:"center",formatter: stringTypeFormatterYYYYMMDD}
		],
		viewrecords: true,
		shrinkToFit:false,
		loadonce : true,
		height: 150,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "billList"
	  },
      onCellSelect : function(rowid, index, contents, event){
      	var data = $("#popup_billingListGrid").getRowData(rowid);
		popup_selectBill(data.bill_seq_no, data.bill_yymm, data.bill_dt, data.pym_acnt_id);

      },
      loadComplete : function () {
      	  $("#popup_billingListGrid").setGridWidth($('#popup_billingListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
          $("#popup_billingListGrid").trigger("reloadGrid");
      },
      loadError: function (jqXHR, textStatus, errorThrown) {
       	ajaxErrorCallback(jqXHR);
      },
  	  sortable: { 
	   		update: function(permutation) {
		    		$("#popup_billingListGrid").setGridWidth($('#popup_billingListGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	   		}
  		}
	});

	/**
	  * 계약별청구내역
	  */ 
	$("#popup_billingListPerCtrtGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
			{ label: 'soId' , name: 'so_id', hidden:true,width : 0, sortable :false},
			{ label: 'billSeq' , name: 'bill_seq_no', hidden:true,width : 0, sortable :false},
			{ label: 'pymAcntId' , name: 'pym_acnt_id', hidden:true,width : 0, sortable :false},
			{ label: 'billYymm' , name: 'bill_yymm', hidden:true,width : 0, sortable :false},
			{ label: 'billDt' , name: 'bill_dt', hidden:true,width : 0, sortable :false},
			{ label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'cust_id', width : 90, align:"center"},
		    { label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'cust_nm', width : 200, align:"left"},
		    { label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'ctrt_id', width : 90, align:"center"},
		    { label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'prod_nm', width : 200, align:"left"},
		    { label: '<spring:message code="LAB.M10.LAB00058"/>', name: 'adj_prv_bill_amt', width : 200, align:"right", formatter : 'integer',sorttype:'number'},
		    { label: '<spring:message code="LAB.M09.LAB00134"/>', name: 'adj_amt', width : 130, align:"right", formatter : 'integer',sorttype:'number'},
		    { label: '<spring:message code="LAB.M10.LAB00031"/>', name: 'bill_amt', width : 130, align : "right", formatter : 'integer', sorttype:'number'},
		    { label: '<spring:message code="LAB.M07.LAB00237"/>', name: 'rcpt_amt', width : 130, align : "right", formatter : 'integer', sorttype:'number'},
		],
		viewrecords: true,
		shrinkToFit:false,
		loadonce : true,
		height: 150,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "billCtrtList"
	  },
      onCellSelect : function(rowid, index, contents, event){

      	var data = $("#popup_billingListPerCtrtGrid").getRowData(rowid);
		popup_selectCtrtBill(data.bill_seq_no, data.bill_yymm, data.bill_dt, data.so_id, data.ctrt_id, data.pym_acnt_id);

      },
      loadComplete : function () {
      	  $("#popup_billingListPerCtrtGrid").setGridWidth($('#popup_billingListPerCtrtGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
          $("#popup_billingListPerCtrtGrid").trigger("reloadGrid");
      },
      loadError: function (jqXHR, textStatus, errorThrown) {
       	ajaxErrorCallback(jqXHR);
      },
  	  sortable: { 
	   		update: function(permutation) {
		    		$("#popup_billingListPerCtrtGrid").setGridWidth($('#popup_billingListPerCtrtGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	   		}
  		}
	});

	/**
	  * 계약별청구내역
	  */ 
	$("#popup_billingListDtlGrid").jqGrid({
		datatype : 'local',
		mtype: 'POST',
		colModel: [
		    { label: '<spring:message code="LAB.M10.LAB00033"/>', name: 'bill_yymm', width : 120, align:"center",formatter: stringTypeFormatterYYYYMM},
		    { label: '<spring:message code="LAB.M07.LAB00021"/>', name: 'use_yymm', width : 120, align:"center",formatter: stringTypeFormatterYYYYMM},
		    { label: '<spring:message code="LAB.M08.LAB00058"/>', name: 'chrg_itm_nm', width : 200, align:"left"},
		    { label: '<spring:message code="LAB.M10.LAB00058"/>', name: 'adj_prv_bill_amt', width : 200, align:"right", formatter : 'integer',sorttype:'number'},
		    { label: '<spring:message code="LAB.M09.LAB00134"/>', name: 'adj_amt', width : 130, align:"right", formatter : 'integer',sorttype:'number'},
		    { label: '<spring:message code="LAB.M10.LAB00031"/>', name: 'bill_amt', width : 130, align : "right", formatter : 'integer', sorttype:'number'},
		    { label: '<spring:message code="LAB.M07.LAB00237"/>', name: 'rcpt_amt', width : 130, align : "right", formatter : 'integer', sorttype:'number'},
		],
		viewrecords: true,
		shrinkToFit:false,
		loadonce : true,
		height: 150,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "billDtlList"
	  },
      onCellSelect : function(rowid, index, contents, event){
      },
      loadComplete : function () {
      	  $("#popup_billingListDtlGrid").setGridWidth($('#popup_billingListDtlGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
          $("#popup_billingListDtlGrid").trigger("reloadGrid");
      },
      loadError: function (jqXHR, textStatus, errorThrown) {
       	ajaxErrorCallback(jqXHR);
      },
  	  sortable: { 
	   		update: function(permutation) {
		    		$("#popup_billingListDtlGrid").setGridWidth($('#popup_billingListDtlGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	   		}
  		}
	});

	//출력
	$('#popup_pringBtn').on('click',function (e) {
		  	if($("#popup_pringBtn").hasClass('not-active')){
				return;
			}

			var rowId  = $("#popup_billingListGrid").jqGrid('getGridParam', 'selrow');
			if(rowId == null){
				alert('<spring:message code="MSG.M07.MSG00073"/>');
				return;	
			} 
		  	var data = $("#popup_billingListGrid").getRowData(rowId);
			$.ajax({
				type : "post",
				url : '/customer/contract/contract/invoiceDetail/getInvoiceDownloadCheckAction.json',
				data : {
					  billSeqNo : data.bill_seq_no
				},
		        dataType: 'json',
		        success: function(data){
		        	console.log(data);
		        	location.href = '/customer/contract/contract/invoiceDetail/getInvoiceDownloadAction?billSeqNo='+data.billSeqNo
		        },
		     	beforeSend: function(data){
		     	},
		     	error : function(err){
		     		ajaxErrorCallback(err);
		     	}
			}); 
		}
   );

	popup_getBillList();
});

/**
 * 빌링 데이터 조회
 */ 
function popup_getBillList(){
	var soId = '${soId}';
	var custId = '${custId}';
	var ctrtId = '${ctrtId}';
	var pymAcntId = '${pymAcntId}';
	$("#popup_billingListGrid").clearGridData();
	$("#popup_billingListGrid").setGridParam({
		url : '/customer/contract/contract/invoiceDetail/getBillListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			soId : soId,
  			custId : custId,
  			ctrtId : ctrtId,
  			pymAcntId : pymAcntId
		}
	});
	      	
   	$("#popup_billingListGrid").trigger("reloadGrid");
}

/**
 * 계약별 청구내역조회
 */ 
function popup_selectBill(billSeqNo, billYymm, billDt, pymAcntId){
	$("#popup_billingListPerCtrtGrid").clearGridData();
	$("#popup_billingListDtlGrid").clearGridData();
	$("#popup_billingListPerCtrtGrid").setGridParam({
		url : '/customer/contract/contract/invoiceDetail/getBillCtrtListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			billSeqNo : billSeqNo,
  			billYymm : dateFormatToStringYYYYMM(billYymm),
  			billDt : dateFormatToStringYYYYMMDD(billDt),
  			pymAcntId : pymAcntId
		}
	});
	      	
   	$("#popup_billingListPerCtrtGrid").trigger("reloadGrid");
}

/**
 * 청구내역상세
 */ 
function popup_selectCtrtBill(bill_seq_no, bill_yymm, bill_dt, so_id, ctrt_id, pym_acnt_id){
	$("#popup_billingListDtlGrid").clearGridData();
	$("#popup_billingListDtlGrid").setGridParam({
		url : '/customer/contract/contract/invoiceDetail/getBillCtrtDtlListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			billSeqNo : bill_seq_no,
  			billYymm : bill_yymm,
  			billDt : bill_dt,
  			soId : so_id,
  			ctrtId : ctrt_id,
  			pymAcntId : pym_acnt_id
		}
	});
	      	
   	$("#popup_billingListDtlGrid").trigger("reloadGrid");

}
</script>

<div class='layer_top'>
	<div class='title'>
		<spring:message code="LAB.M10.LAB00036" />
	</div>
	<a href='#' class='close'></a>
</div>
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title">
				<spring:message code="LAB.M10.LAB00098" />
				<!-- 청구내역 -->
			</h4>
		</div>
		<div class="fr">
			<a id="popup_pringBtn" class="grey-btn">
				<span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span>
			</a>
		</div>
	</div>
	
	<!-- center -->
	<div id="popup_billingListGridDiv" class="w1100">
		<table id="popup_billingListGrid" class="w100p"></table>
	</div>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M01.LAB00260" />
			</h4>
		</div>
	</div>
	<div id="popup_billingListPerCtrtGridDiv" class="w1100">
		<table id="popup_billingListPerCtrtGrid" class="w100p"></table>
	</div>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M01.LAB00261" />
			</h4>
		</div>
	</div>
	<div id="popup_billingListDtlGridDiv" class="w1100">
		<table id="popup_billingListDtlGrid" class="w100p"></table>
	</div>
	<div class="btn_box">
		<a class="grey-btn close" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027"/></span></a>
	</div>

</div>
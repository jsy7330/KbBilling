<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

$(document).ready(function() {
	//닫기 버튼을 눌렀을 때

	$("#virAcntGrid").jqGrid({
		url : '/customer/customer/payment/paymentManagement/getVirAcntListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			soId : $("#paramSoId").val(),
			pymAcntId :$("#paramPymAcntId").val()
		},
		colModel: [
			{ label: 'virAcntCd' , name: 'bnkCd', hidden:true,width : 0},
		    { label: '<spring:message code="LAB.M01.LAB00004"/>', name: 'bnkCdNm', width : 120, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00003"/>', name: 'vrAcntNo', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00005"/>', name: 'qtaDt', width : 100, align:"center",formatter:stringTypeFormatterYYYYMMDD,sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrNm', width : 150,sortable:false},           
		    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center",sortable:false},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 200,
		width: 688,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "virAcntList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#virAcntGridPager",
       	loadComplete : function () {
       		$("#virAcntGrid").setGridWidth(688,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#virAcntGrid").setGridWidth(688,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	
	
  	//변경 이벤트
   	$('#virChngBtn').on('click',function (e) {
   		
  		// 변경된 값만 파라미터로 넘김
  		var rowid  = $("#virAcntGrid").jqGrid('getGridParam', 'selrow');
  		
  		if(rowid == null){
  			alert('<spring:message code="MSG.M03.MSG00015"/>');
  			return;
  		}
  		
  		var data = $("#virAcntGrid").getRowData(rowid);
  		
  		var url = '/customer/customer/payment/paymentManagement/updateVirAcntAction.json';
  		$.ajax({
  	        url:url,
  	        type:'POST',
			data : {
				soId : $("#paramSoId").val(),
				pymAcntId :$("#paramPymAcntId").val(),
				vrBnkCd  :data.bnkCd,
				vrAcntNo : data.vrAcntNo
			},
  	        dataType: 'json',
  	        success: function(data){
  	        	alert('<spring:message code="MSG.M07.MSG00084"/>');
  	          	$("#virAcntGrid").clearGridData();
  	          	$("#virAcntGrid").setGridParam({
  	 	   	        postData : {
	  	 	 			soId : $("#paramSoId").val(),
	  	 				pymAcntId :$("#paramPymAcntId").val()
  	 	   			}
  	          	});
  	      		$("#virAcntGrid").trigger("reloadGrid");
  	        	
  	        },
  	     	beforeSend: function(data){
  	     	},
  	     	error : function(err){
  	     		alert('<spring:message code="MSG.M10.MSG00005"/>');
  	     	}
  	    });
   	});
});
</script>
<div style="width:700px;" >
		<!-- title -->
		 <div class="layer_top">
		     <div class="title"><spring:message code="LAB.M01.LAB00002"/></div>
		     <a href="#" class="close"></a>
		</div>
		<!--// title -->
		                                         
		<!-- center -->
		<div class="layer_box">
				<table id="virAcntGrid" class="w100p"></table>
				<div id="virAcntGridPager"></div>
		 </div>
		<!-- footer -->
		 <div class="btn_box">
		 	   <ntels:auth auth="${menuAuthU}">
	 		   <a id='virChngBtn' class="grey-btn" href="#" ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252" /></span></a>
	 		   </ntels:auth>
		       <a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
		</div>
</div>
<input id="paramSoId" type="text" value="${SO_ID}" hidden />
<input id="paramPymAcntId" type="text" value="${PYM_ACNT_ID}" hidden />

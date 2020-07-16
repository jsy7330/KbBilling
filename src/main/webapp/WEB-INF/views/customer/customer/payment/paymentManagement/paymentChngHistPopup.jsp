<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

$(document).ready(function() {
	//닫기 버튼을 눌렀을 때

	$("#paymentHistGrid").jqGrid({
		url : '/customer/customer/payment/paymentManagement/getPymAcntChngHistListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			soId : $("#soId").val(),
			pymAcntId :$("#pymAcntId").val(),
			isUnmaskYn : $("#isUnmaskYn").val()
		},
		colModel: [ 
		    { label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'actDttm', width : 130, align:"center", formatter:stringTypeFormatterYYYYMMDDHH24MISS},
		    { label: '<spring:message code="LAB.M02.LAB00008"/>', name: 'acntNm', width : 180, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M02.LAB00016"/>', name: 'pymMthdNm', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00087"/>', name: 'zipCd', width : 70, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00218"/>', name: 'baseAddr', width : 200, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00102"/>', name: 'addrDtlAsMask', width : 200, align:"left",sortable:false},
		    /* { label: '<spring:message code="LAB.M03.LAB00087"/>', name: 'city', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00232"/>', name: 'stateNm', width : 150, align:"left",sortable:false}, */
		    { label: '<spring:message code="LAB.M08.LAB00119"/>', name: 'emlAsMask', width : 130, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00065"/>', name: 'telNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M14.LAB00076"/>', name: 'mtelNoAsMask', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M15.LAB00036"/>', name: 'faxNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M06.LAB00058"/>', name: 'pmcResnNm', width : 120, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00199"/>', name: 'billMdmGiroYnNm', width : 120, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00120"/>', name: 'billMdmEmlYnNm', width : 120, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M15.LAB00081"/>', name: 'billMdmSmsYnNm', width : 120, align:"left",sortable:false},
		   /*  { label: '<spring:message code="LAB.M08.LAB00111"/>', name: 'bnkCdNm', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M05.LAB00035"/>', name: 'acntOwnerNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00043"/>', name: 'acntNoAsMask', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00107"/>', name: 'cdtcdExpDt', width : 100, align:"center",sortable:false, formatter:stringTypeFormatterYYYYMM},
		    { label: '<spring:message code="LAB.M07.LAB00208"/>', name: 'tbrFlg', width : 120, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00022"/>', name: 'arrsNobillYn', width : 120, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M02.LAB00015"/>', name: 'useAcntNmYn', width : 120, align:"center",sortable:false}, */
		    { label: '<spring:message code="LAB.M10.LAB00042"/>', name: 'billCyclCdNm', width : 100, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrNm', width : 150,sortable:false},           
		    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},    
		    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrNm', width : 150,sortable:false},
		    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},
		],
		viewrecords: true,
		/* autowidth: true, */
		shrinkToFit:false,
		height: 300,
		width: 988,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "pymAcntChngHistList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#paymentHistGridPager",
       	loadComplete : function () {
       		$("#paymentHistGrid").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#paymentHistGrid").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	
});
</script>
<!-- <div style="width:1000px;" > -->
		<!-- title -->
		 <div class="layer_top">
		     <div class="title"><spring:message code="LAB.M02.LAB00009"/></div>
		     <a href="#" class="close"></a>
		</div>
		<!--// title -->
		                                         
		<!-- center -->
		<div class="layer_box">
			<table id="paymentHistGrid" class="w100p"></table>
			<div id="paymentHistGridPager"></div>
			 <div class="btn_box">
			       <a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
			</div>
		 </div>
		<!-- footer -->
<!-- </div> -->
<input id="soId" type="text" value="${SO_ID}" hidden />
<input id="pymAcntId" type="text" value="${PYM_ACNT_ID}" hidden />

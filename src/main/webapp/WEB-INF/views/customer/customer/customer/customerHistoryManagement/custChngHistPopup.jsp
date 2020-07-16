<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">

$(document).ready(function() {
	
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "-7"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
	

	if($(".datepicker1").length > 0){
		$( ".datepicker1" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "0"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker1.disabled" ).datepicker( "option", "disabled", true ); 

	$('.datepicker').datepicker();
    $('.datepicker').datepicker("option", "maxDate", $(".datepicker1").val());
    $('.datepicker').datepicker("option", "onClose", function ( selectedDate ) {
        $(".datepicker1").datepicker( "option", "minDate", selectedDate );
    });
    
    $('.datepicker1').datepicker();
    $('.datepicker1').datepicker("option", "minDate", $(".datepicker").val());
    $('.datepicker1').datepicker("option", "onClose", function ( selectedDate ) {
        $(".datepicker").datepicker( "option", "maxDate", selectedDate );
    });
	
	$("#custHistGrid").jqGrid({
		url : '/customer/customer/customer/customerHistoryManagement/getCustChngHistAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			soId 	: $("#soId").val(),
			custId  : $("#custId").val(),
			sdate 	: dateFormatToStringYYYYMMDD($("#sdate").val()),
			edate 	: dateFormatToStringYYYYMMDD($("#edate").val()),
			isUnmaskYn : $("#isUnmaskYn").val()
		},
		colModel: [ 
		    { label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'actDttm', width : 150, align:"center", formatter:stringTypeFormatterYYYYMMDDHH24MISS},
		    { label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'custId', width : 120, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'custNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'soNm', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00053"/>', name: 'custTpNm', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00048"/>', name: 'custClNm', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00188"/>', name: 'corpRegNoAsMask', width : 180, align:"center",sortable:false, formatter:corpRegNoFormatter},
		    { label: '<spring:message code="LAB.M08.LAB00087"/>', name: 'zipCd', width : 70, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00218"/>', name: 'baseAddr', width : 200, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00102"/>', name: 'addrDtlAsMask', width : 200, align:"left",sortable:false},
		    /* { label: '<spring:message code="LAB.M03.LAB00087"/>', name: 'city', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00232"/>', name: 'stateNm', width : 150, align:"left",sortable:false}, */
		    { label: '<spring:message code="LAB.M08.LAB00119"/>', name: 'emlAsMask', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00065"/>', name: 'telNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M15.LAB00036"/>', name: 'faxNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M14.LAB00076"/>', name: 'mtelNoAsMask', width : 120, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M03.LAB00063"/>', name: 'repNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00012"/>', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00011"/>', name: 'busiTpNm', width : 180, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrNm', width : 150,sortable:false},           
		    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 150,align:"center"},    
		    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrNm', width : 150,sortable:false},
		    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 150,align:"center"},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 300,
		width: 988,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "custChngHistList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#custHistGridPager",
        loadComplete : function () {
        	$("#custHistGrid").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#custHistGrid").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	//조회 버튼 이벤트
	$('#btn_srch').on('click',function (e) {
		$("#custHistGrid").setGridParam({
			postData : {
				sdate : dateFormatToStringYYYYMMDD($("#sdate").val()),
				edate : dateFormatToStringYYYYMMDD($("#edate").val()),
				srchYn:"Y"
			}
		});

		$("#custHistGrid").trigger("reloadGrid");	
	});
	
	
});
</script>

	<!-- title -->
	 <div class="layer_top">
	     <div class="title"><spring:message code="LAB.M01.LAB00055"/></div>
	     <a href="#" class="close"></a>
	</div>
	<div class="layer_box">
		<div class="ly_btn_box">
			<div class="fl">
				<h4 class="ly_title"><spring:message code="LAB.M01.LAB00055"/><!-- 고객변경이력 --></h4>
			</div>
			<div class="fr">
				<a href="#" id="btn_srch" class="grey-btn" title="<spring:message code="LAB.M09.LAB00158"/>">
				<span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
			</div>
		</div>
		<!--// title -->
		<table class="writeview">
			<colgroup>
				<col style="width: 10%;">
				<col style="width: 23%;">
				<col style="width: 10%;">
				<col style="width: 23%;">
			</colgroup>
			<thead>
				<tr id="cond">
					<th title="<spring:message code="LAB.M01.LAB00196"/>"><spring:message code="LAB.M01.LAB00196"/></th><!-- 기간 -->
					<td colspan="3">
						<div class="date_box">
							<div class="inp_date w135">
								<fmt:parseDate value="${sdate}" var="sdate" pattern="${dateFormat1}"/> 
					 			<input  type="text" id="sdate" name="sdate" value="<fmt:formatDate value="${sdate}"  pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a> 
							</div>
							<span class="dash">~</span>
							<div class="inp_date w135">
							  <input  type="text"  id="edate" name="edate" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker1" readonly="readonly"><a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>
				</tr>
			</thead>
		</table>                                         
		<!-- center -->
		<div class="layer_box">
				<table id="custHistGrid" class="w100p"></table>
				<div id="custHistGridPager"></div>
		 </div>
		 <div class="btn_box">
	       <a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
		 </div>
	</div>
	<!-- footer -->
	
<input id="soId" type="text" value="${SO_ID}" hidden />
<input id="custId" type="text" value="${CUST_ID}" hidden />

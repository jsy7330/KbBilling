<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';

$(document).ready(function() {
	
	//달력처리
	if($(".month-picker").length > 0){
		if(lng == 'ko'){
			format = 'yy-mm';
		}else if (lng == 'en'){
			format = 'mm/yy';
		}
		$('.month-picker').datepicker( {
			changeMonth: true,
			changeYear: true,
			showButtonPanel: true,
			dateFormat: format,
			onClose: function(dateText, inst) {
				var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
				var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
				$(this).datepicker('setDate', new Date(year, month, 1));
			},
			beforeShow : function (dateText, inst) {
		        	
				var selectDate = $(this).val().split("-");
				var year = Number(selectDate[0]);
				var month = Number(selectDate[1]) - 1;
				$(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
				$(this).datepicker('setDate', new Date(year, month, 1));
		            
			}
		}); 
		 
		// 년월 레이어 focus
	    $(".month-picker").focus(function () {
	        $(".ui-datepicker-calendar").hide();
	        $("#ui-datepicker-div").position({
	            my: "center top",
	            at: "center bottom",
	            of: $(this)
	        });
	    });
		
	}
	
	$('#searchYyyyMm').datepicker('setDate', new Date());
	
	//납부계정 조회
	$('#btnSearchPym').on('click',function (e) {
		var url="/system/common/common/pymAcntSearch/pymAcntPopup.ajax";
		var param = new Object();
		param.popType = "m";            //팝업타입 m:모달 o:일반
		param.returnId1 = "searchAcntNm";
		param.returnId2 = "searchPymAcntId";
		
		$.ajax({
			type : "post",
			url : url,
			data : param,
			async: true,
			success : function(data) {
				var html = data;
				$("#popup_dialog").html(html);
			},      
			complete : function(){
				wrapWindowByMask(); // 팝업 오픈
			}
		}); 
	});
	
	$("#billDtlGrid").jqGrid({
		url:'getBillInvoiceListAction.json?',
		datatype: "local",
		mtype:"POST",
		postData: {},
		colModel: [
			//차후 안쓰는 필드는 hidden처리
			{ label: 'soId', name: 'soId', hidden:true, width:0},
			{ label: 'billCycl', name: 'billCycl', hidden:true, width:0},
			{ label: '<spring:message code="LAB.M07.LAB00006" />', name: 'soNm', width : 100, align:"center"}, 		//사업명
			{ label: '<spring:message code="LAB.M10.LAB00033" />', formatter:stringTypeFormatterYYYYMM, name: 'billYymm', width : 100, align:"center"}, 	//청구년월
			{ label: '<spring:message code="LAB.M10.LAB00040" />', formatter:stringTypeFormatterYYYYMMDD, name: 'billDt', width : 100, align:"center"}, //청구일자
			{ label: '<spring:message code="LAB.M02.LAB00005" />', name: 'pymAcntId', width : 100, align:"center"}, //납부계정
			{ label: '<spring:message code="LAB.M10.LAB00039" />', name: 'billSeqNo', width : 150, align:"center"}, //청구일련번호
			{ label: '<spring:message code="LAB.M10.LAB00058" />', formatter:'integer', name: 'adjPrvBillAmt', width : 100, align:"right"}, //최초조정전청구액 ADJ_PRV_BILL_AMT
			{ label: '<spring:message code="LAB.M02.LAB00037" />', formatter:'integer', name: 'adjAmt', width : 100, align:"right"}, //누적조정금액 ADJ_AMT
			{ label: '<spring:message code="LAB.M10.LAB00031" />', formatter:'integer', name: 'billAmt', width : 100, align:"right"}, //청구금액 BILL_AMT
			{ label: '<spring:message code="LAB.M07.LAB00237" />', formatter:'integer', name: 'rcptAmt', width : 100, align:"right"}, //수납금액 RCPT_AMT
			{ label: '<spring:message code="LAB.M05.LAB00048" />', formatter:'integer', name: 'unpayAmt', width : 100, align:"right"}, //미납금액 UNPAY_AMT
			{ label: '<spring:message code="LAB.M10.LAB00035" />', name: 'payMthd', width : 100, align:"center"}, //청구방법 PAY_MTHD
			{ label: '<spring:message code="LAB.M02.LAB00043" />', formatter:stringTypeFormatterYYYYMMDD, name: 'payDueDt', width : 100, align:"center"}, //납기마감일 PAY_DUE_DT
			{ label: '<spring:message code="LAB.M08.LAB00043" />', name: 'fullPayYn', width : 100, align:"center"}, //완납여부 FULL_PAY_YN
			{ label: '<spring:message code="LAB.M10.LAB00038" />', name: 'billexptYn', width : 100, align:"center"}, //청구제외여부 BILL_EXPT_YN
			{ label: '<spring:message code="LAB.M09.LAB00136" />', name: 'adjApplConts', width : 100, align:"center"}, //조정사유 ADJ_APPL_CONTS
			{ label: '조정<spring:message code="LAB.M07.LAB00255" />', formatter:stringTypeFormatterYYYYMMDD, name: 'chgDt', width : 100, align:"center"}, //수정일 CHG_DT
			{ label: '<spring:message code="LAB.M10.LAB00037" />', name: 'smry', width : 100, align:"center"}, //청구제외사유  SMRY
		],
		sortable:true,
		viewrecords: true,
		height: 220,
		rowNum:10,                   //한번에 노출되는 row 수
		rowList:[5,10,20,30,50],    //선택시 노출되는 row 수
		pager: '#billDtlGridPager',
		onCellSelect : function(rowId, index, contents, event){
			setSelectedData(rowId);
		},       
		loadComplete: function(obj){	
	   		$("#billDtlGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		},
	});
   
	$("#billDtlGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
   
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#billDtlGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});	


	//그리드2
	$("#dtlGrid").jqGrid({
	           
		url:'getBillDtlList.json?',
		datatype: "local",
		mtype:"POST",
		postData: {},
		//multiselect: true,
		
		colModel: [ 
			//차후 안쓰는 필드는 hidden처리
			{ label: '<spring:message code="LAB.M10.LAB00039" />', name: 'billSeqNo', width : 150, align:"center", sortable:false}, //청구일련번호
			{ label: '사용년월', name: 'useYymm', formatter:stringTypeFormatterYYYYMM, width : 100, align:"center", sortable:true},  
			{ label: '상품명', name: 'prodNm', width : 100, align:"left"},
			{ label: '상품구성ID', name: 'prodCmpsId', width : 100, align:"center"},  
			{ label: '서비스구성ID', name: 'svcCmpsId', width : 100, align:"center"},  
			{ label: '서비스과금항목ID', name: 'chrgItmCd', width : 100, align:"center"},  
			{ label: '서비스과금항목명', name: 'chrgItmNm', width : 100, align:"left"},  
			{ label: '<spring:message code="LAB.M01.LAB00046" />', name: 'custId', width : 100, align:"center"}, //고객ID
			{ label: '<spring:message code="LAB.M01.LAB00032" />', name: 'ctrtId', width : 100, align:"center"}, //계약ID
			{ label: '원청구금액', name: 'adjPrvBillAmt', formatter: 'integer',  width : 100, align:"right"},  
			{ label: '조정금액', name: 'adjAmt', formatter: 'integer', width : 100, align:"right"},  
			{ label: '청구금액', name: 'billAmt', formatter: 'integer', width : 100, align:"right"},  
			{ label: '수납금액', name: 'rcptAmt', formatter: 'integer', width : 100, align:"right"},  
			{ label: '완납여부', name: 'fullPayYn', width : 100, align:"center"},  
		],
		height: 220,
		onSelectRow: function(row, isSelected) {
		},
		loadComplete: function(obj){	
	   		$("#dtlGrid").setGridWidth($('#dtlGridlDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		},
	});
   
	$("#dtlGrid").setGridWidth($('#dtlGridlDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
   
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#dtlGrid").setGridWidth($('#dtlGridlDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	
	//조회
	$('#btnSearch').click(function(){
		searchGridList();
	});
	
	//상세조회	 param전달 
	function setSelectedData(rowId) {	
		var data = $("#billDtlGrid").getRowData(rowId);
		getSelectedData(data);
	}

	//상세 리스트 조회
	function getSelectedData(row) {	  
		var param = new Object();
		param.soId = row.soId;
		param.billYymm = dateFormatToStringYYYYMM(row.billYymm);
		param.billCycl = row.billCycl;
		param.pymAcntId = row.pymAcntId;
		param.billSeqNo = row.billSeqNo;
		
		console.log(param);
  	
		$("#dtlGrid").setGridParam({
			datatype: "json",
			postData : param             
		});
      
		$("#dtlGrid").trigger("reloadGrid");		
	}
	
});
    	
function searchGridList(){
	
	var param = new Object();
	param.soId = $("#searchSoId").val();
	param.billYymm = dateFormatToStringYYYYMM($("#searchYyyyMm").val());
	param.pymAcntId = $("#searchPymAcntId").val();
	
/* 	if(param.pymAcntId == null || param.pymAcntId == ''){
		alert('<spring:message code="MSG.M02.MSG00013"/>');	//MSG.M02.MSG00013 납부계정을 입력해 주세요.
		return;
	} */
	
	$("#billDtlGrid").setGridParam({
		datatype : "json",
		postData :param    
	});
    
	$("#billDtlGrid").trigger("reloadGrid");
	
}

</script>


<!--NaviGator-->
<div class="h3_bg">
    <h3>${menuName}</h3>
    <!-- Navigator -->
        <div class="nav">                                        
           <a href="#" class="home"></a>
            <c:forEach items="${naviMenuList}" var="mu" varStatus="status">
            <span>&gt;</span>${mu.menuName}
        </c:forEach>
        </div>                               
   <!--// Navigator -->
</div>

<div class="main_btn_box">
    <div class="fr mt10">
        <a href="#" id="btnSearch" class="grey-btn" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
    </div>
</div>


<table class="writeview" id="areaSearch" >
    <colgroup>
        <col style="width:10%;">
        <col style="width:40%;">
        <col style="width:10%;">
        <col style="width:40%;">
    </colgroup>
    <thead> 
        <tr>
           <th><spring:message code="LAB.M07.LAB00003"/><span class="dot">*</span><!-- 사업 --></th>
           <td>
               <select class="w150" id="searchSoId" name="searchSoId">
                   <spring:message code="LAB.M15.LAB00002"/>
                   <c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
                       <option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
                   </c:forEach>
               </select>                                           
           </td>
           <th><spring:message code="LAB.M02.LAB00005"/><span class="dot">*</span><!-- 납부계정 --></th>
           <td>
               <div class="inp_date w280">
                   <input id="searchAcntNm" name="searchAcntNm" type="text" class="w120" disabled="disabled"/>
					<input id="searchPymAcntId" name="searchPymAcntId" type="text" class="w120" disabled="disabled"/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnSearchPym"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
               </div> 
           </td>
        </tr>
        <tr>
          <th><spring:message code="LAB.M10.LAB00033"/><!-- 청구년월 --></th>
          <td>
              <div class="date_box">
                  <div class="inp_date w150">
                      <input type="text" id="searchYyyyMm" name="searchYyyyMm" class="month-picker" readonly="readonly"/>
                    <a href="#" class="btn_cal"></a>
                  </div>
              </div>
          </td>
        </tr>
    </thead>
</table> 

	<div class="main_btn_box">
	    <div class="fl">
	        <h4 class="sub_title"><spring:message code="LAB.M10.LAB00032"/><!-- 청구내역 --></h4>
	    </div>
	</div>
	
	<div id='gridDiv'>
		<table id="billDtlGrid" class="w100p"></table>
		<div id="billDtlGridPager"></div>
	</div>
	
	<div class="main_btn_box">
	    <div class="fl">
	        <h4 class="sub_title">청구내역상세</h4>
	    </div>
	</div>
		
	<div id="dtlGridlDiv">
	    <table id="dtlGrid" class="w100p"></table>
		<div id="detailGridPager"></div>
	</div>

</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>
    
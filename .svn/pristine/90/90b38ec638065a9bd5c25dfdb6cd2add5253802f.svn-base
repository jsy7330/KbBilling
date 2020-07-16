<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

$(document).ready(function(){
	//계약변경이력 그리드
	
	pageInit();
    $("#receptionInfoGrid").jqGrid({
    	url : '/customer/contract/receipt/receiptInfo/getReceiptInfoListAction.json',
    	datatype : 'local',
		colModel: [ 
					{ label: 'soId' , name: 'soId', hidden:true,width : 0},
					{ label: 'custId' , name: 'custId', hidden:true,width : 0},
					{ label: 'orderTp' , name: 'orderTp', hidden:true,width : 0},
					{ label: 'orderStat' , name: 'orderStat', hidden:true,width : 0},
					{ label: 'ctrtStat' , name: 'ctrtStat', hidden:true,width : 0},
					{ label: 'orderTp' , name: 'orderTp', hidden:true,width : 0},
					{ label: 'pymAcntId' , name: 'pymAcntId', hidden:true,width : 0},
					{ label: 'basicProdGrp' , name: 'basicProdGrp', hidden:true,width : 0},
					{ label: 'basicProdCd' , name: 'basicProdCd', hidden:true,width : 0},
					{ label: 'rcptId' , name: 'rcptId', hidden:true,width : 0},
					{ label: 'rcptUsrId' , name: 'rcptUsrId', hidden:true,width : 0},
					{ label: 'cmplUsrId' , name: 'cmplUsrId', hidden:true,width : 0},
					{ label: 'rcptDesc' , name: 'rcptDesc', hidden:true,width : 0}, 
					{ label: '<spring:message code="LAB.M07.LAB00013"/>', name: 'soNm', width : 100},
					{ label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'custId', width : 100 , align:"center"},
					{ label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'custNm', width : 120},
					{ label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'ctrtId', width : 100, align:"center"}, 
					{ label: '<spring:message code="LAB.M08.LAB00033"/>', name: 'orderId', width : 100, align:"center"}, 
					{ label: '<spring:message code="LAB.M08.LAB00036"/>', name: 'orderTpNm', width : 150, align:"left"}, 
					{ label: '<spring:message code="LAB.M08.LAB00035"/>', name: 'orderStatNm', width : 100, align:"left"}, 
					{ label: '<spring:message code="LAB.M01.LAB00243"/>', name: 'basicProdCdNm', width : 200, align:"left"},
					{ label: '<spring:message code="LAB.M01.LAB00037"/>', name: 'ctrtStatNm', width : 100, align:"left"},
					{ label: '<spring:message code="LAB.M09.LAB00076"/>', name: 'rcptDttm', width : 150, align:"center", formatter:stringToDateformatYYYYMMDDHH24MISS},
					{ label: '<spring:message code="LAB.M09.LAB00077"/>', name: 'rcptUsrNm', width : 150, align:"left", formatter:concatRcptUsrNm},
					{ label: '<spring:message code="LAB.M08.LAB00045"/>', name: 'cmplDttm', width : 150, align:"center", formatter:stringToDateformatYYYYMMDDHH24MISS},
					{ label: '<spring:message code="LAB.M08.LAB00046"/>', name: 'cmplUsrNm', width : 150, align:"left", formatter:concatCmplUsrNm}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 480,
		sortable : true,
		scrollrows : true,
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 20,
		pager: "#receptionInfoGridPager",
		jsonReader: {
			repeatitems : true,
			root : "recieptInfoList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
        onCellSelect : function(rowid, index, contents, event){
        	var data = $("#receptionInfoGrid").getRowData(rowid);
        	selectPageMove(data);
        },
       	loadComplete : function (data) {
       		$("#receptionInfoGrid").setGridWidth($('#receptionInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#receptionInfoGrid").setGridParam({
                datatype:'local',
                loadonce:true,
                rowTotal: data.totalCount
            });
            $("#receptionInfoGrid").trigger("reloadGrid");
       	},
       	sortable: { 
       		update: function(permutation) {
       			$("#receptionInfoGrid").setGridWidth($('#receptionInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	},
        loadError: function (jqXHR, textStatus, errorThrown) {
            ajaxErrorCallback(jqXHR);
        }

	});
	
    $("#receptionInfoGrid").setGridWidth($('#receptionInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#receptionInfoGrid").setGridWidth($('#receptionInfoDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}); 
	
	// 고객조회 버튼 이벤트
	$('#btnCustSearch').on('click',function (e) {
		if($("#btnCustSearch").hasClass('not-active')){
			return;
		}
		openCustSearchPopup();	
	});
	
	// 고객조회 버튼 이벤트
	$('#btnRcptSearch').on('click',function (e) {
		if($("#btnRcptSearch").hasClass('not-active')){
			return;
		}
		openRcptSearchPopup();	
	});
	
	//고객명 키 이벤트
    $( "#condCustNm" ).keypress(function(event) {
		if(event.keyCode == 13){
			$('#condCustId').val('');
			
			searchCustInfo();
		}
    });
	
	//조회 버튼 이벤트
	$('#search_btn').on('click',function (e) {
		if($("#search_btn").hasClass('not-active')){
			return;
		}
		fnSearch();	
	});
	
	//접수자명 키 이벤트
    $( "#condRcptUsrNm" ).keypress(function(event) {
		if(event.keyCode == 13){
			fnSearch();
		}
    });
	
  //계약ID 키 이벤트
    $( "#condCtrtId" ).keypress(function(event) {
		if(event.keyCode == 13){
			fnSearch();
		}
    });
	
});

/*
 * 화면 초기화 함수
 */
function pageInit() {
	 
	$('#sdate').datepicker();
	$('#sdate').datepicker("setDate", "-7");
    $('#sdate').datepicker("option", "maxDate", $("#edate").val());
    $('#sdate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#edate").datepicker( "option", "minDate", selectedDate );
    });
 
    $('#edate').datepicker();
    $('#edate').datepicker("option", "minDate", $("#sdate").val());
    $('#edate').datepicker("option", "onClose", function ( selectedDate ) {
        $("#sdate").datepicker( "option", "maxDate", selectedDate );
    });
    
	$("#receptionInfoGrid").clearGridData();

	//입력부 초기화
	//inputInit('N');

}

/*
 * 고객조회팝업
 */
function openCustSearchPopup(){
	
	$.ajax({
		type : "post",
		url : '/system/common/common/customerSearch/customerSearchPopup.ajax',
		data : {
			 inputCustNm : $('#condCustNm').val()   //input Customer Name
			,inputIsUnmaskYn : 'N'                  //마스크 처리
			,outputCustNm : 'condCustNm'            //output Customer Name Text
			,outputCustId : 'condCustId'            //output Customer ID Text

		},
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").hide();
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
			$("#txtCustSearchCustNm").focus(); //오픈 후 focus위치
		}
	}); 
}

function openRcptSearchPopup(){
	var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
	var param = new Object();
	param.popType = "m";			//팝업타입 m:모달 o:일반
	param.returnId1 = "condRcptUsrNm";
	param.returnId2 = "condRcptUsrId";
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
}
/* 접수처리 결과 조회 */
function fnSearch(){
	
	$("#receptionInfoGrid").clearGridData();
	$("#receptionInfoGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  	    	condSdate : dateFormatToStringYYYYMMDD($("#sdate").val()),
			condEdate : dateFormatToStringYYYYMMDD($("#edate").val()),
  			condCustId : $('#condCustId').val(),
  			condRcptUsrNm : $('#condRcptUsrNm').val(),
  			condOrderTpSel : $('#condOrderTpSel').val(),
  			condOrderStatusSel : $('#condOrderStatusSel').val(),
  			condCtrtId : $('#condCtrtId').val()
		}
	});
	      
   	$("#receptionInfoGrid").trigger("reloadGrid");
}

/**
 * 접수자 연결
 */
function concatRcptUsrNm( cellvalue, options, rowObject ){
	var rcptUsrId = rowObject.rcptUsrId == null ? '' : rowObject.rcptUsrId;
	var rcptUsrNm = rowObject.rcptUsrNm == null ? '' : rowObject.rcptUsrNm; 
	return rcptUsrId == '' ? '' : rcptUsrNm + '(' + rcptUsrId + ')';
}

/**
 * 완료자 연결
 */
function concatCmplUsrNm( cellvalue, options, rowObject ){
	var cmpsUsrId = rowObject.cmpsUsrId == null ? '' : rowObject.cmpsUsrId;
	var cmpsUsrNm = rowObject.cmpsUsrNm == null ? '' : rowObject.cmpsUsrNm; 
	return cmpsUsrId == '' ? '' : cmpsUsrNm + '(' + cmpsUsrId + ')';
}

/*
 * 고객정보찾기
 */
function searchCustInfo(){
	var checkR = "<c:out value='${menuAuthR}'/>"; 
	if(checkR == 'N') return;
	
	var condCustSoId = $('#condCustSoId').val();
  	var condCustNm = $('#condCustNm').val();
  	var condCustId = $('#condCustId').val();
	var condSearchTp = $('#condSearchTp').val();
	var condSearchKey = getTelNo($('#condSearchKey').val());
	var isUnmaskYn = 'N'; //마스크 처리
	
	if(condCustNm == '' && condSearchTp=='SEL'){
		alert('<spring:message code="MSG.M01.MSG00016"/>');
		return;
	}
	
	var url = '/customer/contract/contract/contractManagement/getCustInfoSearchAction.json';
	
	$.ajax({
          url:url,
          type:'POST',
          data : {
        	condCustSoId : condCustSoId,
        	condCustNm : condCustNm,
        	condCustId : condCustId,
        	condSearchTp : condSearchTp,
        	condSearchKey : condSearchKey,
            isUnmaskYn : isUnmaskYn
          },
          dataType: 'json',
          success: function(data){
          	if(data.custListCnt == '0'){
          		alert('<spring:message code="MSG.M09.MSG00039"/>');	
          	}else if(data.custListCnt == 1){
            	//$('#condCustSoId').val(data.custList[0].so_id);
            	//$('#condCustSoId').selectmenu("refresh");
             	$('#condCustNm').val(data.custList[0].cust_nm);
            	$('#condCustId').val(data.custList[0].cust_id);
            	//getCustInfo($('#condCustSoId').val(), $('#condCustId').val(), isUnmaskYn);
          	}else{
          		//다수 존재시 팝업호출
          		openCustSearchPopup();
          	}
          	
          },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
      });
}

function selectPageMove(data){
	$.ajax({
		type : "post",
		url : '/customer/contract/receipt/receiptInfo/receiptInfoInquiryPopup.ajax',
		data : {
			 soId : data.soId   
			,ctrtId : data.ctrtId                 
			,orderStat : data.orderStat
		},
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").hide();
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
		}
	}); 
}

/**
 * 오더 진행 프로세스
 */
function btnOrderProcessEvent(){

	var rcptRowId  = $("#receptionInfoGrid").jqGrid('getGridParam', 'selrow');
  	var rcptData = $("#receptionInfoGrid").getRowData(rcptRowId);
  	var ctrtId = '';
  	var rcptId = rcptData.rcptId;
	
  	if(rcptData.orderStat == '04'){
  		alert('<spring:message code="MSG.M08.MSG00024"/>');
		return false;
  	}
  	if(rcptRowId != null){
  		ctrtId = rcptData.ctrtId;
  	}else{
  		ctrtId = "0000000000";

  	}
	var url = '/customer/contract/contract/orderManagement/getOrderMastInfoAction.json';
	$.ajax({
        url:url,
        type:'POST',
        data : {
        	 soId : rcptData.soId
        	,custId : rcptData.custId
        	,ctrtId : rcptData.ctrtId
        	,orderCd : rcptData.orderTp
        	,rcptId : rcptData.rcptId
		},
        dataType: 'json',
        success: function(data){
        	openOrderPage(data.orderCommonInfo);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     		
     	}
    });
}

/**
 * 오더 진행 페이지 오픈
 */
function openOrderPage(orderCommonInfo){
	$.ajax({
		type : "post",
		url : '/customer/contract/contract/orderManagement/openOrderProcess.ajax',
		data : JSON.stringify(orderCommonInfo),
		contentType : "application/json; charset=UTF-8",
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").css('width','1100');
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			
		},
		error : function(err){
     		alert('<spring:message code="MSG.M10.MSG00005"/>');	
     	}
	}); 	

}

function getMenuNo(){
	var rcptRowId  = $("#receptionInfoGrid").jqGrid('getGridParam', 'selrow');
  	var rcptData = $("#receptionInfoGrid").getRowData(rcptRowId);

	$.ajax({
        url:'/customer/contract/receipt/receiptInfo/getMenuInfo.json',
        type:'POST',
        data : {
        	menuNo :"1020101"
           ,condCustSoId : rcptData.soId
           ,condCustNm : rcptData.custNm
           ,condCustId : rcptData.custId
           ,condCtrtId : rcptData.ctrtId
           ,condOrderId : rcptData.orderId
        },
        dataType: 'json',
        success: function(data){
        	goContractManagementPage(data);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		ajaxErrorCallback(err);
     	}
    });
}

function goContractManagementPage(data){
	
 	var rcptRowId  = $("#receptionInfoGrid").jqGrid('getGridParam', 'selrow');
  	var rcptData = $("#receptionInfoGrid").getRowData(rcptRowId);
  	
	var f = makeform('/customer/contract/contract/contractManagement/contractManagement');
	
	f.appendChild(AddData('menuNo', data.menuInfo.menuNo2));                	//대상화면의 2Level Menu No
	f.appendChild(AddData('selectMenuNo', data.menuInfo.menuNo4));          	//대상화면의 4Level Menu No
	f.appendChild(AddData('selectMenuNm', data.menuInfo.menuNm4)); 			    //대상화면의 4Level Menu No
	f.appendChild(AddData('topMenuNo', data.menuInfo.menuNo1));             	//대상화면의 1Level Menu No
	f.appendChild(AddData('topMenuNm', data.menuInfo.menuNm1)); 				//대상화면의 1Level Menu Name
	f.submit(); 
}
</script>
<div class="h3_bg">
	<h3>${menuName}</h3>
		<!-- Navigator -->
		<div class="nav">                                        
   			<a href="#" class="home">Home</a>
			<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
				<span>&gt;</span>${mu.menuName}
			</c:forEach>
		</div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00225"/></h4><!--접수처리조회  -->
	</div>
	<div class="fr mt10">
		<a href='#' class='grey-btn'>
			<span id="search_btn" class='search_icon'><spring:message code="LAB.M01.LAB00025"/></span>
		</a>
		<div class='inpCaution'>
			<span class='dot'>*</span>
			<spring:message code="LAB.M13.LAB00034"/> <!-- 필수입력 -->
		</div>
	</div>
</div>
<!--  -->
	<table class='writeview column3 row_2'>
		<tbody>
			<tr class='col3'>
				<th><spring:message code="LAB.M09.LAB00072"/><!-- 접수기간 -->
					<span class='dot'>*</span>
				</th>
				<td>
					<div class='date_box'>
						<div class='inp_date w40p'>
							<%-- <fmt:parseDate value="${sdate}" var="sdate" pattern="${dateFormat1}"/>  --%>
			 				<input  type="text" id="sdate" name="sdate" value="<fmt:formatDate value="${sdate}"  pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly">
			 				<a href="#" class="btn_cal"></a>
						</div>
						<span class='dash'>~</span>
						<div class='inp_date w40p'>
							<input  type="text"  id="edate" name="edate" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly">
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00050"/></th> <!-- 고객명 -->
				<td>
					<div class='inp_date w100p'>
						<input type='text' id="condCustNm" placeholder='<spring:message code="LAB.M01.LAB00256"/>'/>
						<input id="condCustId" type="text" hidden >
						<a id="btnCustSearch" href="#" title='' class="search"></a>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00077"/></th><!-- 접수자 -->
				<td>
					<div class='inp_date w100p'>
						<input type='text'id="condRcptUsrNm" placeholder='<spring:message code="LAB.M09.LAB00226"/>'/>
						<input id="condRcptUsrId" type="text" hidden >
						<a id="btnRcptSearch" href='#' class='search'></a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00036"/></th><!-- 오더유형 -->
				<td>
					<select id="condOrderTpSel" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${orderTpCodeList}" var="orderTpCode" varStatus="status">
						<option value="${orderTpCode.commonCd}">${orderTpCode.commonCdNm}</option>
					</c:forEach>
				</select>
				</td>
				<th><spring:message code="LAB.M08.LAB00035"/></th><!-- 오더상태 -->
				<td>
					<select id="condOrderStatusSel" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${orderStatusCodeList}" var="CondOrderStatus" varStatus="status">
							<option value="${CondOrderStatus.commonCd}">${CondOrderStatus.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M01.LAB00032"/></th><!-- 계약ID -->
				<td>
					<input type='text' id="condCtrtId" placeholder='<spring:message code="LAB.M01.LAB00244"/>'/>
				</td>
			</tr>
		</tbody>
	</table>
<!--  -->
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M09.LAB00227"/></h4><!-- 조회결과 -->
		</div>
	</div>
<!--  -->
	<div id='receptionInfoDiv'><!-- 조회결과 GRID -->
		<table id='receptionInfoGrid' class='w100p'></table>
		<div id="receptionInfoGridPager"></div>
	</div>

<!-- Layer Popup-->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

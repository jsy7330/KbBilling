<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	custSearchInit();
	
	//그리드 선언
	$("#custPopupGrid").jqGrid({
		url : '/system/common/common/customerSearch/getCustomerListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
			
		},
		colModel: [
			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
			{ label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'soNm', width : 100, align:"left"},
			{ label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'custId', width : 100, align:"center"},
		    { label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'custNm', width : 180, align:"left"},
		    { label: '<spring:message code="LAB.M01.LAB00053"/>', name: 'custTpNm', width : 130, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00048"/>', name: 'custClNm', width : 130, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00188"/>', name: 'corpRegNoAsMask', width : 130, align:"left",sortable:false, formatter:corpRegNoFormatter},
		    { label: '<spring:message code="LAB.M07.LAB00014"/>', name: 'bizRegNo', width : 130, align:"left",sortable:false, formatter:bizRegNoFormatter},
		    // { label: '<spring:message code="LAB.M01.LAB00157"/>', name: 'taxTpNm', width : 130, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00087"/>', name: 'zipCd', width : 80, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00218"/>', name: 'baseAddr', width : 200, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00102"/>', name: 'addrDtlAsMask', width : 200, align:"left",sortable:false},
		    /* { label: '<spring:message code="LAB.M03.LAB00087"/>', name: 'city', width : 100, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00232"/>', name: 'stateNm', width : 150, align:"center",sortable:false}, */
		    { label: '<spring:message code="LAB.M08.LAB00119"/>', name: 'emlAsMask', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00065"/>', name: 'telNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M15.LAB00036"/>', name: 'faxNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M14.LAB00076"/>', name: 'mtelNoAsMask', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    // { label: '<spring:message code="LAB.M06.LAB00058"/>', name: 'chngResnNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M03.LAB00063"/>', name: 'repNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00012"/>', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00011"/>', name: 'busiTpNm', width : 150, align:"left",sortable:false},
		    // { label: '<spring:message code="LAB.M08.LAB00047"/>', name: 'foreignerExpiratDt', width : 150, align:"left",sortable:false, formatter:stringTypeFormatterYYYYMMDD},
		    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrNm', width : 150,sortable:false},           
		    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center",sortable:false},    
		    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrNm', width : 150,sortable:false},
		    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center",sortable:false},
  		    
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 300,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "custInfoList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#custPopupGridPager",
        ondblClickRow : function(rowid){
        	selectCustInfo(rowid);
        },
        loadComplete : function (data) {
        	$("#custPopupGrid").setGridWidth($('#popupOrderProcessDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	
       		if( data.totalCount != null && data.totalCount > 0){
       			//선택처리
       		  $("#custPopupGrid").setSelection(1, false);
       		}
        },
    	sortable: { update: function(permutation) {
    		$("#custPopupGrid").setGridWidth($('#popupOrderProcessDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	$("#custPopupGrid").setGridWidth($('#popupOrderProcessDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	//검색 버튼
   	$('#btnSearchCustPopup').on('click',function (e) {
		  	if($("#btnSearchCustPopup").hasClass('not-active')){
				return;
			}
		  	custSearchPopup('S');
		}
    );
	
	$("#btnCustSearchSelect").on('click',function(e){
			if($("#btnCustSearchSelect").hasClass('not-active')){
				return;
			}
			var rowid  = $("#custPopupGrid").jqGrid('getGridParam', 'selrow');
			if(rowid == null){
				alert('<spring:message code="MSG.M03.MSG00015"/>');
	  			return;
			}
			selectCustInfo(rowid);	
		
		}
   	);
   	
  	//고객명 키 이벤트
    $( "#txtCustSearchCustNm" ).keypress(function(event) {
		  	if($("#btnSearchCustPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				custSearchPopup('S');
			}
    	}
    );
	//고객ID 키 이벤트
    $( "#txtCustSearchCustId" ).keypress(function(event) {
		  	if($("#btnSearchCustPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				custSearchPopup('S');
			}
    	}
    );
	
  	//계약ID 키 이벤트
    $( "#txtCustSearchCtrtId" ).keypress(function(event) {
		  	if($("#btnSearchCustPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				custSearchPopup('S');
			}
    	}
    );
  	
  	//전화번호 키 이벤트
   //  $( "#txtCustSearchSvcTelNo" ).keypress(function(event) {
		 //  	if($("#btnSearchCustPopup").hasClass('not-active')){
			// 	return;
			// }
			// if(event.keyCode == 13){
			// 	custSearchPopup('S');
			// }
   //  	}
   //  );
  	//주민번호 키 이벤트
    $( "#txtCustSearchCorpReg" ).keypress(function(event) {
		  	if($("#btnSearchCustPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				custSearchPopup('S');
			} else if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
	          	event.preventDefault();
	        }
    	}
    );

    //사업자번호 숫자입력만 허용
    $('#txtCustSearchBizNo').on('keypress',function (event) {
		if(event.keyCode == 13){
				custSearchPopup('S');
		} else if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
          	event.preventDefault();
        }
        
        if($('#txtCustSearchBizNo').val().length == 3 || $('#txtCustSearchBizNo').val().length == 6){
          var str = $('#txtCustSearchBizNo').val();
          $('#txtCustSearchBizNo').val(str + '-')
        }
      });
  	
  	
    // 고객ID Keyup Event
  	$('#txtCustSearchCustId').keyup(function(){
	  		var str = getMaxStr($('#txtCustSearchCustId').val(), 10);
	  		if(str != $('#txtCustSearchCustId').val()){
	  			$('#txtCustSearchCustId').val(str);
	  		}
  		}
	);
    
 	// 계약ID Keyup Event
  	$('#txtCustSearchCtrtId').keyup(function(){
	  		var str = getMaxStr($('#txtCustSearchCtrtId').val(), 10);
	  		if(str != $('#txtCustSearchCtrtId').val()){
	  			$('#txtCustSearchCtrtId').val(str);
	  		}
  		}
	);
	//전화번호내용 Keyup Event
 //  	$('#txtCustSearchSvcTelNo').keyup(function(){
 //  		$('#txtCustSearchSvcTelNo').val(telNoAutoFormatter($('#txtCustSearchSvcTelNo').val()));
 //  		$('#txtCustSearchSvcTelNo').val(getMaxStr($('#txtCustSearchSvcTelNo').val(), 13));
 //  		}
	// );
	
	
 	// 생년월일 Keyup Event
  	$('#txtCustSearchCorpReg').keyup(function(){
	  		var str = getMaxStr($('#txtCustSearchCorpReg').val(), 20);
	  		if(str != $('#txtCustSearchCorpReg').val()){
	  			$('#txtCustSearchCorpReg').val(str);
	  		}
  		}
	);
 	
  	custSearchPopup('I');
});

function custSearchInit(){
	var soId = "<c:out value='${INPUT_SO_ID}'/>";
	var custNm = "<c:out value='${INPUT_CUST_NM}'/>";
	$('#selCustSearchCondSoId').val(soId);
	$('#selCustSearchCondSoId').selectmenu({});
	$('#selCustSearchCondSoId').selectmenu("refresh");
	$('#txtCustSearchCustNm').val(custNm);
}

function custSearchPopup(mode){
	// if(mode=='I'){
	// 	if($('#txtCustSearchCustNm').val() == ''){
	// 		$('#txtCustSearchCustNm').focus();
	// 		return;
	// 	}
	// }else{
 // 		if($('#txtCustSearchCustNm').val() == '' &&
	// 			$('#txtCustSearchCustId').val() == '' &&
	// 			$('#txtCustSearchSvcTelNo').val() == '' &&
	// 			$('#txtCustSearchCtrtId').val() == '' &&
	// 			$('#txtCustSearchCorpReg').val() == ''){
	// 		alert('<spring:message code="MSG.M01.MSG00016"/>');
	// 		$('#txtCustSearchCustNm').focus();
	// 		return;
	// 	}
 		
 // 		if($('#txtCustSearchCustNm').val() == '' &&
	// 			$('#txtCustSearchCustId').val() == '' &&
	// 			$('#txtCustSearchSvcTelNo').val() == '' &&
	// 			$('#txtCustSearchCtrtId').val() == '' &&
	// 			$('#txtCustSearchCorpReg').val() != ''){
	// 		alert('<spring:message code="MSG.M01.MSG00016"/>');
	// 		$('#txtCustSearchCustNm').focus();
	// 		return;
	// 	}
	// }

	if(mode == 'I' && $('#txtCustSearchCustNm').val() == ''){
		$('#txtCustSearchCustNm').focus();
	}
	$("#custPopupGrid").clearGridData();
	$("#custPopupGrid").setGridParam({
		url : '/system/common/common/customerSearch/getCustomerListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			soId : $('#selCustSearchCondSoId').val(),
  			custNm : $('#txtCustSearchCustNm').val(),
  			custId : $('#txtCustSearchCustId').val(),
  			ctrtId : $('#txtCustSearchCtrtId').val(),
  			//svcTelNo : getTelNo($('#txtCustSearchSvcTelNo').val()),
  			svcTelNo : '',
  			corpRegNo : $('#txtCustSearchCorpReg').val(),
  			bizNo : $('#txtCustSearchBizNo').val().replace(/[^0-9]/g, ''),
  			isUnmaskYn : $('#popupInputIsUnmaskYn').val()
		}
	});
	      	
   	$("#custPopupGrid").trigger("reloadGrid");
}

function selectCustInfo(rowid){
	var data = $("#custPopupGrid").getRowData(rowid);
	
	$('#' + $('#popupOuputSoId').val()).val(data.soId);
	$('#' + $('#popupOuputSoId').val()).selectmenu("refresh");
	$('#' + $('#popupOuputCustNm').val()).val(data.custNm);
	$('#' + $('#popupOuputCustId').val()).val(data.custId);
	$("#btnCustSearchClose").trigger('click');
	if (typeof customerSearchCallback == 'function') { 
		customerSearchCallback();  
	}
}


</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M01.LAB00047"/></div>
	<a href="#" id="btnCustSearchClose" class="close">Close</a>
</div>
<!--// title -->

<!-- center --> 
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00047"/></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="btnSearchCustPopup" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>            
	                
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:20%;">
			<col style="width:15%;">
			<col style="width:20%;">
			<col style="width:15%;">
			<col style="width:20%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/></th>
				<td>
					<select class="w170" id="selCustSearchCondSoId">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
				<th><spring:message code="LAB.M01.LAB00050"/></th>
				<td>
					<input type="text" id="txtCustSearchCustNm" name="txtCustSearchCustNm" class="w100p">
				</td>
				<th><spring:message code="LAB.M01.LAB00046"/></th>
				<td>
					<input type="text" id="txtCustSearchCustId" class="w100p">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00032"/></th>
				<td>
					<input type="text" id="txtCustSearchCtrtId"  class="w100p">  
				</td>
				<th><spring:message code="LAB.M09.LAB00189"/></th>
				<td>
					<input type="text" id="txtCustSearchCorpReg" class="w100p" maxlength='6'>
				</td>
				<th><spring:message code="LAB.M07.LAB00014"/></th>
				<td>
					<input type="text" id="txtCustSearchBizNo" class="w100p" maxlength='12'>
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M01.LAB00049"/></h4>
		</div>
	</div>
	<div id="popupOrderProcessDiv" class="w1100">
		<table id="custPopupGrid" class="w100p"></table>
		<div id="custPopupGridPager"></div>
	</div>

	<!--// center -->
	<div class="btn_box">
	      <a class="grey-btn" id="btnCustSearchSelect" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
	      <a class="grey-btn close" id="btnCustSearchClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>
</div>


<input id="popupInputIsUnmaskYn" value="${INPUT_IS_UNMASK_YN}" hidden /> 
<input id="popupOuputSoId" value="${OUTPUT_SO_ID}" hidden /> 
<input id="popupOuputCustNm" value="${OUTPUT_CUST_NM}" hidden /> 
<input id="popupOuputCustId" value="${OUTPUT_CUST_ID}" hidden /> 
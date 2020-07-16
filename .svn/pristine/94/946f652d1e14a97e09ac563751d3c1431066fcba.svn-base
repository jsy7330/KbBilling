<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	pymAcntSearchInit();
	
	//그리드 선언
	$("#pymPopupGrid").jqGrid({
		url : '/system/common/common/pymAcntSearch/getPymAcntListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
			
		},
		colModel: [
			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
			{ label: '<spring:message code="LAB.M07.LAB00003"/>', name: 'soNm', width : 100, align:"left"},
			{ label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'custId', width : 100, align:"center"},
			{ label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'custNm', width : 180, align:"left"},
			{ label: '<spring:message code="LAB.M02.LAB00006"/>', name: 'pymAcntId', width : 100, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M02.LAB00008"/>', name: 'acntNm', width : 180, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M02.LAB00016"/>', name: 'pymMthdNm', width : 130, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00087"/>', name: 'zipCd', width : 50, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00218"/>', name: 'baseAddr', width : 200, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00102"/>', name: 'addrDtlAsMask', width : 200, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00119"/>', name: 'emlAsMask', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00065"/>', name: 'telNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M14.LAB00076"/>', name: 'mtelNoAsMask', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M15.LAB00036"/>', name: 'faxNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M06.LAB00058"/>', name: 'pmcResnNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00199"/>', name: 'billMdmGiroYnNm', width : 120, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00120"/>', name: 'billMdmEmlYnNm', width : 120, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M15.LAB00081"/>', name: 'billMdmSmsYnNm', width : 120, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00111"/>', name: 'bnkCdNm', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M05.LAB00035"/>', name: 'acntOwnerNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00043"/>', name: 'acntNoAsMask', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00107"/>', name: 'cdtcdExpDt', width : 100, align:"center",sortable:false, formatter:stringTypeFormatterYYYYMM},
		    { label: '<spring:message code="LAB.M07.LAB00208"/>', name: 'tbrFlg', width : 120, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00022"/>', name: 'arrsNobillYn', width : 120, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M02.LAB00015"/>', name: 'useAcntNmYn', width : 120, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrNm', width : 150,sortable:false},           
		    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center", sortable:false},    
		    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrNm', width : 150,sortable:false},
		    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center", sortable:false},
  		    
		],
		viewrecords: true,
		shrinkToFit:false,
		width: 888,
		height: 300,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "pymAcntInfoList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#pymPopupGridPager",
        ondblClickRow : function(rowid){
        	selectPymAcntInfo(rowid);
        },
        loadComplete : function (data) {
        	$("#pymPopupGrid").setGridWidth(888,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	
       		if( data.totalCount != null && data.totalCount > 0){
       			//선택처리
       		  $("#pymPopupGrid").setSelection(1, false);
       		}
        },
    	sortable: { update: function(permutation) {
    		$("#pymPopupGrid").setGridWidth(888,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	
	$("#pymPopupGrid").setGridWidth(888,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//검색 버튼
   	$('#btnSearchPymPopup').on('click',function (e) {
		  	if($("#btnSearchPymPopup").hasClass('not-active')){
				return;
			}
		  	pymSearchPopup('S');
		}
    );
	
	//데이터 선택 이벤트
	$("#btnPymSearchSelect").on('click',function(e){
			if($("#btnPymSearchSelect").hasClass('not-active')){
				return;
			}
			var rowid  = $("#pymPopupGrid").jqGrid('getGridParam', 'selrow');
			if(rowid == null){
				alert('<spring:message code="MSG.M03.MSG00015"/>');
	  			return;
			}
			selectPymAcntInfo(rowid);	
		
		}
   	);
   	
  	//고객명 키 이벤트
    $( "#txtPymSearchCustNm" ).keypress(function(event) {
		  	if($("#btnSearchPymPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				pymSearchPopup('S');
			}
    	}
    );
	//고객ID 키 이벤트
    $( "#txtPymSearchCustId" ).keypress(function(event) {
		  	if($("#btnSearchPymPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				pymSearchPopup('S');
			}
    	}
    );
	
  	//계약ID 키 이벤트
    $( "#txtPymSearchCtrtId" ).keypress(function(event) {
		  	if($("#btnSearchPymPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				pymSearchPopup('S');
			}
    	}
    );
  	
  	//전화번호 키 이벤트
    $( "#txtPymSearchSvcTelNo" ).keypress(function(event) {
		  	if($("#btnSearchPymPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				pymSearchPopup('S');
			}
    	}
    );
  	//납부번호 키 이벤트
    $( "#txtPymSearchAcntId" ).keypress(function(event) {
		  	if($("#btnSearchPymPopup").hasClass('not-active')){
				return;
			}
			if(event.keyCode == 13){
				pymSearchPopup('S');
			}
    	}
    );
  	
  	
    // 고객ID Keyup Event
  	$('#txtPymSearchCustId').keyup(function(){
	  		var str = getMaxStr($('#txtPymSearchCustId').val(), 10);
	  		if(str != $('#txtPymSearchCustId').val()){
	  			$('#txtPymSearchCustId').val(str);
	  		}
  		}
	);
    
 	// 계약ID Keyup Event
  	$('#txtPymSearchCtrtId').keyup(function(){
	  		var str = getMaxStr($('#txtPymSearchCtrtId').val(), 10);
	  		if(str != $('#txtPymSearchCtrtId').val()){
	  			$('#txtPymSearchCtrtId').val(str);
	  		}
  		}
	);
	//전화번호내용 Keyup Event
  	$('#txtPymSearchSvcTelNo').keyup(function(){
  		$('#txtPymSearchSvcTelNo').val(telNoAutoFormatter($('#txtPymSearchSvcTelNo').val()));
  		$('#txtPymSearchSvcTelNo').val(getMaxStr($('#txtPymSearchSvcTelNo').val(), 13));
  		}
	);
	
	
 	// 납부번호 Keyup Event
  	$('#txtPymSearchAcntId').keyup(function(){
	  		var str = getMaxStr($('#txtPymSearchAcntId').val(), 10);
	  		if(str != $('#txtPymSearchAcntId').val()){
	  			$('#txtPymSearchAcntId').val(str);
	  		}
  		}
	);
  	pymSearchPopup('I');
});

/*
 * 화면 초기화
 */
function pymAcntSearchInit(){
	var soId = "<c:out value='${INPUT_SO_ID}'/>";
	var custNm = "<c:out value='${INPUT_CUST_NM}'/>";
	var pymAcntId = "<c:out value='${INPUT_PYM_ACNT_ID}'/>";
	$('#selPymSearchCondSoId').val(soId);
	$('#selPymSearchCondSoId').selectmenu({});
	$('#selPymSearchCondSoId').selectmenu("refresh");
	$('#txtPymSearchCustNm').val(custNm);
	$('#txtPymSearchAcntId').val(pymAcntId);
}

/*
 * 계정 조회처리
 */
function pymSearchPopup(mode){
	if(mode=='I'){
		if($('#txtPymSearchCustNm').val() == '' && $('#txtPymSearchAcntId').val() == ''){
			$('#txtPymSearchCustNm').focus();
			return;
		}
	}else{
 		if($('#txtPymSearchCustNm').val() == '' &&
				$('#txtPymSearchCustId').val() == '' &&
				$('#txtPymSearchSvcTelNo').val() == '' &&
				$('#txtPymSearchCtrtId').val() == '' &&
				$('#txtPymSearchAcntId').val() == ''){
			alert('<spring:message code="MSG.M01.MSG00016"/>');
			$('#txtPymSearchCustNm').focus();
			return;
		}
	}
	
	$("#pymPopupGrid").clearGridData();
	$("#pymPopupGrid").setGridParam({
		url : '/system/common/common/pymAcntSearch/getPymAcntListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			soId : $('#selPymSearchCondSoId').val(),
  			custNm : $('#txtPymSearchCustNm').val(),
  			custId : $('#txtPymSearchCustId').val(),
  			ctrtId : $('#txtPymSearchCtrtId').val(),
  			svcTelNo : getTelNo($('#txtPymSearchSvcTelNo').val()),
  			pymAcntId : $('#txtPymSearchAcntId').val(),
  			isUnmaskYn : $('#popupInputIsUnmaskYn').val()
		}
	});
	      	
   	$("#pymPopupGrid").trigger("reloadGrid");
}

function selectPymAcntInfo(rowid){
	var data = $("#pymPopupGrid").getRowData(rowid);
	$('#' + $('#popupOuputSoId').val()).val(data.soId);
	$('#' + $('#popupOuputSoId').val()).selectmenu("refresh");
	$('#' + $('#popupOuputCustNm').val()).val(data.custNm);
	$('#' + $('#popupOuputCustId').val()).val(data.custId);
	$('#' + $('#popupOuputPymAcntId').val()).val(data.pymAcntId);
	$('#' + $('#popupOuputPymAcntNm').val()).val(data.acntNm);
	$("#btnPymSearchClose").trigger('click');
	if (typeof pymAcntSearchCallback == 'function') { 
		pymAcntSearchCallback();  
	}
	
}
</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M02.LAB00011" /></div>
	<a href="#" id="btnPymSearchClose" class="close">Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M02.LAB00011"/></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="btnSearchPymPopup" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>            
	                
	<table class="writeview">
		<colgroup>
			<col style="width:13%;">
			<col style="width:20%;">
			<col style="width:13%;">
			<col style="width:20%;">
			<col style="width:13%;">
			<col style="width:21%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/></th>
				<td>
					<select class="w170" id="selPymSearchCondSoId">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
				<th><spring:message code="LAB.M01.LAB00050"/></th>
				<td>
					<input type="text" id="txtPymSearchCustNm" name="txtPymSearchCustNm" class="w100p">
				</td>
				<th><spring:message code="LAB.M01.LAB00046"/></th>
				<td>
					<input type="text" id="txtPymSearchCustId" class="w100p">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00032"/></th>
				<td>
					<input type="text" id="txtPymSearchCtrtId"  class="w100p">  
				</td>
				<th><spring:message code="LAB.M07.LAB00186"/></th>
				<td>
					<input type="text" id="txtPymSearchSvcTelNo" class="w100p">
				</td>
				<th><spring:message code="LAB.M02.LAB00006"/></th>
				<td>
					<input type="text" id="txtPymSearchAcntId" class="w100p">
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M02.LAB00007"/></h4>
		</div>
	</div>
	
	<div class="layer_box">
		<table id="pymPopupGrid" class="w100p"></table>
		<div id="pymPopupGridPager"></div>
	</div>
</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnPymSearchSelect" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnPymSearchClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<input id="popupInputIsUnmaskYn" value="${INPUT_IS_UNMASK_YN}" hidden /> 
<input id="popupOuputSoId" value="${OUTPUT_SO_ID}" hidden /> 
<input id="popupOuputCustNm" value="${OUTPUT_CUST_NM}" hidden /> 
<input id="popupOuputCustId" value="${OUTPUT_CUST_ID}" hidden /> 
<input id="popupOuputPymAcntId" value="${OUTPUT_PYM_ACNT_ID}" hidden />
<input id="popupOuputPymAcntNm" value="${OUTPUT_PYM_ACNT_NM}" hidden /> 